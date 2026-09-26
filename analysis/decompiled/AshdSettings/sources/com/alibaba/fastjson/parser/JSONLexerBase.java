package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.IOUtils;
import com.android.settingslib.accessibility.AccessibilityUtils;
import java.io.Closeable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public abstract class JSONLexerBase implements JSONLexer, Closeable {
    protected static final int INT_MULTMIN_RADIX_TEN = -214748364;
    protected static final long MULTMIN_RADIX_TEN = -922337203685477580L;
    protected int bp;
    protected char ch;
    protected int eofPos;
    protected int features;
    protected boolean hasSpecial;
    protected int np;
    protected int pos;
    protected char[] sbuf;
    protected int sp;
    protected String stringDefaultValue;
    protected int token;
    private static final ThreadLocal<char[]> SBUF_LOCAL = new ThreadLocal<>();
    protected static final char[] typeFieldName = ("\"" + JSON.DEFAULT_TYPE_KEY + "\":\"").toCharArray();
    protected static final int[] digits = new int[103];
    protected Calendar calendar = null;
    protected TimeZone timeZone = JSON.defaultTimeZone;
    protected Locale locale = JSON.defaultLocale;
    public int matchStat = 0;

    public static boolean isWhitespace(char c) {
        return c <= ' ' && (c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == '\f' || c == '\b');
    }

    public abstract String addSymbol(int i, int i2, int i3, SymbolTable symbolTable);

    protected abstract void arrayCopy(int i, char[] cArr, int i2, int i3);

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract byte[] bytesValue();

    protected abstract boolean charArrayCompare(char[] cArr);

    public abstract char charAt(int i);

    protected abstract void copyTo(int i, int i2, char[] cArr);

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract BigDecimal decimalValue();

    public abstract int indexOf(char c, int i);

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public String info() {
        return "";
    }

    public abstract boolean isEOF();

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract char next();

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract String numberString();

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public abstract String stringVal();

    public abstract String subString(int i, int i2);

    protected abstract char[] sub_chars(int i, int i2);

    protected void lexError(String str, Object... objArr) {
        this.token = 1;
    }

    static {
        for (int i = 48; i <= 57; i++) {
            digits[i] = i - 48;
        }
        for (int i2 = 97; i2 <= 102; i2++) {
            digits[i2] = (i2 - 97) + 10;
        }
        for (int i3 = 65; i3 <= 70; i3++) {
            digits[i3] = (i3 - 65) + 10;
        }
    }

    public JSONLexerBase(int i) {
        this.stringDefaultValue = null;
        this.features = i;
        if ((i & Feature.InitStringFieldAsEmpty.mask) != 0) {
            this.stringDefaultValue = "";
        }
        this.sbuf = SBUF_LOCAL.get();
        if (this.sbuf == null) {
            this.sbuf = new char[512];
        }
    }

    public final int matchStat() {
        return this.matchStat;
    }

    public void setToken(int i) {
        this.token = i;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void nextToken() {
        this.sp = 0;
        while (true) {
            this.pos = this.bp;
            if (this.ch == '/') {
                skipComment();
            } else {
                if (this.ch == '\"') {
                    scanString();
                    return;
                }
                if (this.ch == ',') {
                    next();
                    this.token = 16;
                    return;
                }
                if (this.ch >= '0' && this.ch <= '9') {
                    scanNumber();
                    return;
                }
                if (this.ch == '-') {
                    scanNumber();
                    return;
                }
                switch (this.ch) {
                    case '\b':
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                        next();
                        break;
                    case '\'':
                        if (!isEnabled(Feature.AllowSingleQuotes)) {
                            throw new JSONException("Feature.AllowSingleQuotes is false");
                        }
                        scanStringSingleQuote();
                        return;
                    case '(':
                        next();
                        this.token = 10;
                        return;
                    case ')':
                        next();
                        this.token = 11;
                        return;
                    case '+':
                        next();
                        scanNumber();
                        return;
                    case '.':
                        next();
                        this.token = 25;
                        return;
                    case ':':
                        next();
                        this.token = 17;
                        return;
                    case ';':
                        next();
                        this.token = 24;
                        return;
                    case 'N':
                    case 'S':
                    case 'T':
                    case 'u':
                        scanIdent();
                        return;
                    case '[':
                        next();
                        this.token = 14;
                        return;
                    case ']':
                        next();
                        this.token = 15;
                        return;
                    case 'f':
                        scanFalse();
                        return;
                    case 'n':
                        scanNullOrNew();
                        return;
                    case 't':
                        scanTrue();
                        return;
                    case 'x':
                        scanHex();
                        return;
                    case '{':
                        next();
                        this.token = 12;
                        return;
                    case '}':
                        next();
                        this.token = 13;
                        return;
                    default:
                        if (isEOF()) {
                            if (this.token == 20) {
                                throw new JSONException("EOF error");
                            }
                            this.token = 20;
                            int i = this.bp;
                            this.pos = i;
                            this.eofPos = i;
                            return;
                        }
                        if (this.ch <= 31 || this.ch == 127) {
                            next();
                        } else {
                            lexError("illegal.char", String.valueOf((int) this.ch));
                            next();
                            return;
                        }
                        break;
                        break;
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:117:0x007b A[SYNTHETIC] */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void nextToken(int i) {
        this.sp = 0;
        while (true) {
            if (i == 2) {
                if (this.ch >= '0' && this.ch <= '9') {
                    this.pos = this.bp;
                    scanNumber();
                    return;
                }
                if (this.ch == '\"') {
                    this.pos = this.bp;
                    scanString();
                    return;
                } else if (this.ch == '[') {
                    this.token = 14;
                    next();
                    return;
                } else if (this.ch == '{') {
                    this.token = 12;
                    next();
                    return;
                }
            } else if (i == 4) {
                if (this.ch == '\"') {
                    this.pos = this.bp;
                    scanString();
                    return;
                }
                if (this.ch >= '0' && this.ch <= '9') {
                    this.pos = this.bp;
                    scanNumber();
                    return;
                } else if (this.ch == '[') {
                    this.token = 14;
                    next();
                    return;
                } else if (this.ch == '{') {
                    this.token = 12;
                    next();
                    return;
                }
            } else if (i == 12) {
                if (this.ch == '{') {
                    this.token = 12;
                    next();
                    return;
                } else if (this.ch == '[') {
                    this.token = 14;
                    next();
                    return;
                }
            } else {
                if (i == 18) {
                    nextIdent();
                    return;
                }
                if (i != 20) {
                    switch (i) {
                        case 14:
                            if (this.ch == '[') {
                                this.token = 14;
                                next();
                            } else if (this.ch == '{') {
                                this.token = 12;
                                next();
                            }
                            break;
                        case 15:
                            if (this.ch == ']') {
                                this.token = 15;
                                next();
                            }
                            if (this.ch == 26) {
                                this.token = 20;
                            }
                            break;
                        case 16:
                            if (this.ch == ',') {
                                this.token = 16;
                                next();
                            } else if (this.ch == '}') {
                                this.token = 13;
                                next();
                            } else if (this.ch == ']') {
                                this.token = 15;
                                next();
                            } else if (this.ch == 26) {
                                this.token = 20;
                            }
                            break;
                    }
                    return;
                }
                if (this.ch == 26) {
                    this.token = 20;
                    return;
                }
            }
            if (this.ch == ' ' || this.ch == '\n' || this.ch == '\r' || this.ch == '\t' || this.ch == '\f' || this.ch == '\b') {
                next();
            } else {
                nextToken();
                return;
            }
        }
    }

    public final void nextIdent() {
        while (isWhitespace(this.ch)) {
            next();
        }
        if (this.ch == '_' || this.ch == '$' || Character.isLetter(this.ch)) {
            scanIdent();
        } else {
            nextToken();
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void nextTokenWithColon() {
        nextTokenWithChar(AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR);
    }

    public final void nextTokenWithChar(char c) {
        this.sp = 0;
        while (this.ch != c) {
            if (this.ch == ' ' || this.ch == '\n' || this.ch == '\r' || this.ch == '\t' || this.ch == '\f' || this.ch == '\b') {
                next();
            } else {
                throw new JSONException("not match " + c + " - " + this.ch + ", info : " + info());
            }
        }
        next();
        nextToken();
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final int token() {
        return this.token;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final String tokenName() {
        return JSONToken.name(this.token);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final int pos() {
        return this.pos;
    }

    public final String stringDefaultValue() {
        return this.stringDefaultValue;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final Number integerValue() throws NumberFormatException {
        long j;
        long j2;
        boolean z = false;
        if (this.np == -1) {
            this.np = 0;
        }
        int i = this.np;
        int i2 = this.np + this.sp;
        char c = ' ';
        char cCharAt = charAt(i2 - 1);
        if (cCharAt == 'B') {
            i2--;
            c = 'B';
        } else if (cCharAt == 'L') {
            i2--;
            c = 'L';
        } else if (cCharAt == 'S') {
            i2--;
            c = 'S';
        }
        if (charAt(this.np) == '-') {
            j = Long.MIN_VALUE;
            i++;
            z = true;
        } else {
            j = -9223372036854775807L;
        }
        long j3 = MULTMIN_RADIX_TEN;
        if (i < i2) {
            j2 = -(charAt(i) - '0');
            i++;
        } else {
            j2 = 0;
        }
        while (i < i2) {
            int i3 = i + 1;
            int iCharAt = charAt(i) - '0';
            if (j2 < j3) {
                return new BigInteger(numberString());
            }
            long j4 = j2 * 10;
            long j5 = iCharAt;
            if (j4 < j + j5) {
                return new BigInteger(numberString());
            }
            j2 = j4 - j5;
            i = i3;
            j3 = MULTMIN_RADIX_TEN;
        }
        if (!z) {
            long j6 = -j2;
            if (j6 > 2147483647L || c == 'L') {
                return Long.valueOf(j6);
            }
            if (c == 'S') {
                return Short.valueOf((short) j6);
            }
            if (c == 'B') {
                return Byte.valueOf((byte) j6);
            }
            return Integer.valueOf((int) j6);
        }
        if (i <= this.np + 1) {
            throw new NumberFormatException(numberString());
        }
        if (j2 < -2147483648L || c == 'L') {
            return Long.valueOf(j2);
        }
        if (c == 'S') {
            return Short.valueOf((short) j2);
        }
        if (c == 'B') {
            return Byte.valueOf((byte) j2);
        }
        return Integer.valueOf((int) j2);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void nextTokenWithColon(int i) {
        nextTokenWithChar(AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public float floatValue() {
        char cCharAt;
        String strNumberString = numberString();
        float f = Float.parseFloat(strNumberString);
        if ((f != 0.0f && f != Float.POSITIVE_INFINITY) || (cCharAt = strNumberString.charAt(0)) <= '0' || cCharAt > '9') {
            return f;
        }
        throw new JSONException("float overflow : " + strNumberString);
    }

    public double doubleValue() {
        return Double.parseDouble(numberString());
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public void config(Feature feature, boolean z) {
        this.features = Feature.config(this.features, feature, z);
        if ((this.features & Feature.InitStringFieldAsEmpty.mask) != 0) {
            this.stringDefaultValue = "";
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final boolean isEnabled(Feature feature) {
        return isEnabled(feature.mask);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final boolean isEnabled(int i) {
        return (this.features & i) != 0;
    }

    public final boolean isEnabled(int i, int i2) {
        return ((this.features & i2) == 0 && (i & i2) == 0) ? false : true;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final char getCurrent() {
        return this.ch;
    }

    protected void skipComment() {
        next();
        if (this.ch == '/') {
            do {
                next();
                if (this.ch == '\n') {
                    next();
                    return;
                }
            } while (this.ch != 26);
            return;
        }
        if (this.ch == '*') {
            next();
            while (this.ch != 26) {
                if (this.ch == '*') {
                    next();
                    if (this.ch == '/') {
                        next();
                        return;
                    }
                } else {
                    next();
                }
            }
            return;
        }
        throw new JSONException("invalid comment");
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final String scanSymbol(SymbolTable symbolTable) {
        skipWhitespace();
        if (this.ch == '\"') {
            return scanSymbol(symbolTable, '\"');
        }
        if (this.ch == '\'') {
            if (!isEnabled(Feature.AllowSingleQuotes)) {
                throw new JSONException("syntax error");
            }
            return scanSymbol(symbolTable, '\'');
        }
        if (this.ch == '}') {
            next();
            this.token = 13;
            return null;
        }
        if (this.ch == ',') {
            next();
            this.token = 16;
            return null;
        }
        if (this.ch == 26) {
            this.token = 20;
            return null;
        }
        if (!isEnabled(Feature.AllowUnQuotedFieldNames)) {
            throw new JSONException("syntax error");
        }
        return scanSymbolUnQuoted(symbolTable);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final String scanSymbol(SymbolTable symbolTable, char c) {
        String strAddSymbol;
        this.np = this.bp;
        this.sp = 0;
        boolean z = false;
        int i = 0;
        while (true) {
            char next = next();
            if (next == c) {
                this.token = 4;
                if (!z) {
                    strAddSymbol = addSymbol(this.np == -1 ? 0 : this.np + 1, this.sp, i, symbolTable);
                } else {
                    strAddSymbol = symbolTable.addSymbol(this.sbuf, 0, this.sp, i);
                }
                this.sp = 0;
                next();
                return strAddSymbol;
            }
            if (next == 26) {
                throw new JSONException("unclosed.str");
            }
            if (next == '\\') {
                if (!z) {
                    if (this.sp >= this.sbuf.length) {
                        int length = this.sbuf.length * 2;
                        if (this.sp > length) {
                            length = this.sp;
                        }
                        char[] cArr = new char[length];
                        System.arraycopy(this.sbuf, 0, cArr, 0, this.sbuf.length);
                        this.sbuf = cArr;
                    }
                    arrayCopy(this.np + 1, this.sbuf, 0, this.sp);
                    z = true;
                }
                char next2 = next();
                switch (next2) {
                    case '/':
                        i = (i * 31) + 47;
                        putChar('/');
                        break;
                    case '0':
                        i = (i * 31) + next2;
                        putChar((char) 0);
                        break;
                    case '1':
                        i = (i * 31) + next2;
                        putChar((char) 1);
                        break;
                    case '2':
                        i = (i * 31) + next2;
                        putChar((char) 2);
                        break;
                    case '3':
                        i = (i * 31) + next2;
                        putChar((char) 3);
                        break;
                    case '4':
                        i = (i * 31) + next2;
                        putChar((char) 4);
                        break;
                    case '5':
                        i = (i * 31) + next2;
                        putChar((char) 5);
                        break;
                    case '6':
                        i = (i * 31) + next2;
                        putChar((char) 6);
                        break;
                    case '7':
                        i = (i * 31) + next2;
                        putChar((char) 7);
                        break;
                    default:
                        switch (next2) {
                            case 't':
                                i = (i * 31) + 9;
                                putChar('\t');
                                break;
                            case 'u':
                                int i2 = Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16);
                                i = (i * 31) + i2;
                                putChar((char) i2);
                                break;
                            case 'v':
                                i = (i * 31) + 11;
                                putChar((char) 11);
                                break;
                            default:
                                switch (next2) {
                                    case '\"':
                                        i = (i * 31) + 34;
                                        putChar('\"');
                                        break;
                                    case '\'':
                                        i = (i * 31) + 39;
                                        putChar('\'');
                                        break;
                                    case 'F':
                                    case 'f':
                                        i = (i * 31) + 12;
                                        putChar('\f');
                                        break;
                                    case '\\':
                                        i = (i * 31) + 92;
                                        putChar('\\');
                                        break;
                                    case 'b':
                                        i = (i * 31) + 8;
                                        putChar('\b');
                                        break;
                                    case 'n':
                                        i = (i * 31) + 10;
                                        putChar('\n');
                                        break;
                                    case 'r':
                                        i = (i * 31) + 13;
                                        putChar('\r');
                                        break;
                                    case 'x':
                                        char next3 = next();
                                        this.ch = next3;
                                        char next4 = next();
                                        this.ch = next4;
                                        char c2 = (char) ((digits[next3] * 16) + digits[next4]);
                                        i = (i * 31) + c2;
                                        putChar(c2);
                                        break;
                                    default:
                                        this.ch = next2;
                                        throw new JSONException("unclosed.str.lit");
                                }
                                break;
                        }
                        break;
                }
            } else {
                i = (i * 31) + next;
                if (!z) {
                    this.sp++;
                } else if (this.sp == this.sbuf.length) {
                    putChar(next);
                } else {
                    char[] cArr2 = this.sbuf;
                    int i3 = this.sp;
                    this.sp = i3 + 1;
                    cArr2[i3] = next;
                }
            }
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void resetStringPosition() {
        this.sp = 0;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final String scanSymbolUnQuoted(SymbolTable symbolTable) {
        if (this.token == 1 && this.pos == 0 && this.bp == 1) {
            this.bp = 0;
        }
        boolean[] zArr = IOUtils.firstIdentifierFlags;
        int i = this.ch;
        if (!(this.ch >= zArr.length || zArr[i])) {
            throw new JSONException("illegal identifier : " + this.ch + info());
        }
        boolean[] zArr2 = IOUtils.identifierFlags;
        this.np = this.bp;
        this.sp = 1;
        while (true) {
            char next = next();
            if (next < zArr2.length && !zArr2[next]) {
                break;
            }
            i = (i * 31) + next;
            this.sp++;
        }
        this.ch = charAt(this.bp);
        this.token = 18;
        if (this.sp == 4 && i == 3392903 && charAt(this.np) == 'n' && charAt(this.np + 1) == 'u' && charAt(this.np + 2) == 'l' && charAt(this.np + 3) == 'l') {
            return null;
        }
        if (symbolTable == null) {
            return subString(this.np, this.sp);
        }
        return addSymbol(this.np, this.sp, i, symbolTable);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void scanString() {
        this.np = this.bp;
        this.hasSpecial = false;
        while (true) {
            char next = next();
            if (next == '\"') {
                this.token = 4;
                this.ch = next();
                return;
            }
            if (next == 26) {
                if (!isEOF()) {
                    putChar(JSONLexer.EOI);
                } else {
                    throw new JSONException("unclosed string : " + next);
                }
            } else if (next == '\\') {
                if (!this.hasSpecial) {
                    this.hasSpecial = true;
                    if (this.sp >= this.sbuf.length) {
                        int length = this.sbuf.length * 2;
                        if (this.sp > length) {
                            length = this.sp;
                        }
                        char[] cArr = new char[length];
                        System.arraycopy(this.sbuf, 0, cArr, 0, this.sbuf.length);
                        this.sbuf = cArr;
                    }
                    copyTo(this.np + 1, this.sp, this.sbuf);
                }
                char next2 = next();
                switch (next2) {
                    case '/':
                        putChar('/');
                        break;
                    case '0':
                        putChar((char) 0);
                        break;
                    case '1':
                        putChar((char) 1);
                        break;
                    case '2':
                        putChar((char) 2);
                        break;
                    case '3':
                        putChar((char) 3);
                        break;
                    case '4':
                        putChar((char) 4);
                        break;
                    case '5':
                        putChar((char) 5);
                        break;
                    case '6':
                        putChar((char) 6);
                        break;
                    case '7':
                        putChar((char) 7);
                        break;
                    default:
                        switch (next2) {
                            case 't':
                                putChar('\t');
                                break;
                            case 'u':
                                putChar((char) Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16));
                                break;
                            case 'v':
                                putChar((char) 11);
                                break;
                            default:
                                switch (next2) {
                                    case '\"':
                                        putChar('\"');
                                        break;
                                    case '\'':
                                        putChar('\'');
                                        break;
                                    case 'F':
                                    case 'f':
                                        putChar('\f');
                                        break;
                                    case '\\':
                                        putChar('\\');
                                        break;
                                    case 'b':
                                        putChar('\b');
                                        break;
                                    case 'n':
                                        putChar('\n');
                                        break;
                                    case 'r':
                                        putChar('\r');
                                        break;
                                    case 'x':
                                        putChar((char) ((digits[next()] * 16) + digits[next()]));
                                        break;
                                    default:
                                        this.ch = next2;
                                        throw new JSONException("unclosed string : " + next2);
                                }
                                break;
                        }
                        break;
                }
            } else if (!this.hasSpecial) {
                this.sp++;
            } else if (this.sp == this.sbuf.length) {
                putChar(next);
            } else {
                char[] cArr2 = this.sbuf;
                int i = this.sp;
                this.sp = i + 1;
                cArr2[i] = next;
            }
        }
    }

    public Calendar getCalendar() {
        return this.calendar;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public Locale getLocale() {
        return this.locale;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final int intValue() {
        int i;
        boolean z;
        int i2 = 0;
        if (this.np == -1) {
            this.np = 0;
        }
        int i3 = this.np;
        int i4 = this.np + this.sp;
        if (charAt(this.np) == '-') {
            i3++;
            i = Integer.MIN_VALUE;
            z = true;
        } else {
            i = -2147483647;
            z = false;
        }
        if (i3 < i4) {
            i2 = -(charAt(i3) - '0');
            i3++;
        }
        while (i3 < i4) {
            int i5 = i3 + 1;
            char cCharAt = charAt(i3);
            if (cCharAt == 'L' || cCharAt == 'S' || cCharAt == 'B') {
                i3 = i5;
                break;
            }
            int i6 = cCharAt - '0';
            if (i2 < -214748364) {
                throw new NumberFormatException(numberString());
            }
            int i7 = i2 * 10;
            if (i7 < i + i6) {
                throw new NumberFormatException(numberString());
            }
            i2 = i7 - i6;
            i3 = i5;
        }
        if (!z) {
            return -i2;
        }
        if (i3 > this.np + 1) {
            return i2;
        }
        throw new NumberFormatException(numberString());
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.sbuf.length <= 8192) {
            SBUF_LOCAL.set(this.sbuf);
        }
        this.sbuf = null;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final boolean isRef() {
        return this.sp == 4 && charAt(this.np + 1) == '$' && charAt(this.np + 2) == 'r' && charAt(this.np + 3) == 'e' && charAt(this.np + 4) == 'f';
    }

    public final int scanType(String str) {
        this.matchStat = 0;
        if (!charArrayCompare(typeFieldName)) {
            return -2;
        }
        int length = this.bp + typeFieldName.length;
        int length2 = str.length();
        for (int i = 0; i < length2; i++) {
            if (str.charAt(i) != charAt(length + i)) {
                return -1;
            }
        }
        int i2 = length + length2;
        if (charAt(i2) != '\"') {
            return -1;
        }
        int i3 = i2 + 1;
        this.ch = charAt(i3);
        if (this.ch == ',') {
            int i4 = i3 + 1;
            this.ch = charAt(i4);
            this.bp = i4;
            this.token = 16;
            return 3;
        }
        if (this.ch == '}') {
            i3++;
            this.ch = charAt(i3);
            if (this.ch == ',') {
                this.token = 16;
                i3++;
                this.ch = charAt(i3);
            } else if (this.ch == ']') {
                this.token = 15;
                i3++;
                this.ch = charAt(i3);
            } else if (this.ch == '}') {
                this.token = 13;
                i3++;
                this.ch = charAt(i3);
            } else {
                if (this.ch != 26) {
                    return -1;
                }
                this.token = 20;
            }
            this.matchStat = 4;
        }
        this.bp = i3;
        return this.matchStat;
    }

    public final boolean matchField(char[] cArr) {
        while (!charArrayCompare(cArr)) {
            if (!isWhitespace(this.ch)) {
                return false;
            }
            next();
        }
        this.bp += cArr.length;
        this.ch = charAt(this.bp);
        if (this.ch == '{') {
            next();
            this.token = 12;
        } else if (this.ch == '[') {
            next();
            this.token = 14;
        } else if (this.ch == 'S' && charAt(this.bp + 1) == 'e' && charAt(this.bp + 2) == 't' && charAt(this.bp + 3) == '[') {
            this.bp += 3;
            this.ch = charAt(this.bp);
            this.token = 21;
        } else {
            nextToken();
        }
        return true;
    }

    public int matchField(long j) {
        throw new UnsupportedOperationException();
    }

    public boolean seekArrayToItem(int i) {
        throw new UnsupportedOperationException();
    }

    public int seekObjectToField(long j, boolean z) {
        throw new UnsupportedOperationException();
    }

    public int seekObjectToField(long[] jArr) {
        throw new UnsupportedOperationException();
    }

    public int seekObjectToFieldDeepScan(long j) {
        throw new UnsupportedOperationException();
    }

    public void skipObject() {
        throw new UnsupportedOperationException();
    }

    public void skipArray() {
        throw new UnsupportedOperationException();
    }

    public String scanFieldString(char[] cArr) {
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return stringDefaultValue();
        }
        int length = cArr.length;
        int i = length + 1;
        if (charAt(this.bp + length) != '\"') {
            this.matchStat = -1;
            return stringDefaultValue();
        }
        int iIndexOf = indexOf('\"', this.bp + cArr.length + 1);
        if (iIndexOf == -1) {
            throw new JSONException("unclosed str");
        }
        int length2 = this.bp + cArr.length + 1;
        String strSubString = subString(length2, iIndexOf - length2);
        if (strSubString.indexOf(92) != -1) {
            while (true) {
                int i2 = 0;
                for (int i3 = iIndexOf - 1; i3 >= 0 && charAt(i3) == '\\'; i3--) {
                    i2++;
                }
                if (i2 % 2 == 0) {
                    break;
                }
                iIndexOf = indexOf('\"', iIndexOf + 1);
            }
            int length3 = iIndexOf - ((this.bp + cArr.length) + 1);
            strSubString = readString(sub_chars(this.bp + cArr.length + 1, length3), length3);
        }
        int length4 = i + (iIndexOf - ((this.bp + cArr.length) + 1)) + 1;
        int i4 = length4 + 1;
        char cCharAt = charAt(this.bp + length4);
        if (cCharAt == ',') {
            this.bp += i4;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            return strSubString;
        }
        if (cCharAt == '}') {
            int i5 = i4 + 1;
            char cCharAt2 = charAt(this.bp + i4);
            if (cCharAt2 == ',') {
                this.token = 16;
                this.bp += i5;
                this.ch = charAt(this.bp);
            } else if (cCharAt2 == ']') {
                this.token = 15;
                this.bp += i5;
                this.ch = charAt(this.bp);
            } else if (cCharAt2 == '}') {
                this.token = 13;
                this.bp += i5;
                this.ch = charAt(this.bp);
            } else if (cCharAt2 == 26) {
                this.token = 20;
                this.bp += i5 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return stringDefaultValue();
            }
            this.matchStat = 4;
            return strSubString;
        }
        this.matchStat = -1;
        return stringDefaultValue();
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public String scanString(char c) {
        this.matchStat = 0;
        char cCharAt = charAt(this.bp + 0);
        if (cCharAt == 'n') {
            if (charAt(this.bp + 1) == 'u' && charAt(this.bp + 1 + 1) == 'l' && charAt(this.bp + 1 + 2) == 'l') {
                if (charAt(this.bp + 4) == c) {
                    this.bp += 5;
                    this.ch = charAt(this.bp);
                    this.matchStat = 3;
                    return null;
                }
                this.matchStat = -1;
                return null;
            }
            this.matchStat = -1;
            return null;
        }
        int i = 1;
        while (cCharAt != '\"') {
            if (isWhitespace(cCharAt)) {
                cCharAt = charAt(this.bp + i);
                i++;
            } else {
                this.matchStat = -1;
                return stringDefaultValue();
            }
        }
        int i2 = this.bp + i;
        int iIndexOf = indexOf('\"', i2);
        if (iIndexOf == -1) {
            throw new JSONException("unclosed str");
        }
        String strSubString = subString(this.bp + i, iIndexOf - i2);
        if (strSubString.indexOf(92) != -1) {
            while (true) {
                int i3 = 0;
                for (int i4 = iIndexOf - 1; i4 >= 0 && charAt(i4) == '\\'; i4--) {
                    i3++;
                }
                if (i3 % 2 == 0) {
                    break;
                }
                iIndexOf = indexOf('\"', iIndexOf + 1);
            }
            int i5 = iIndexOf - i2;
            strSubString = readString(sub_chars(this.bp + 1, i5), i5);
        }
        int i6 = i + (iIndexOf - i2) + 1;
        int i7 = i6 + 1;
        char cCharAt2 = charAt(this.bp + i6);
        while (cCharAt2 != c) {
            if (isWhitespace(cCharAt2)) {
                cCharAt2 = charAt(this.bp + i7);
                i7++;
            } else {
                this.matchStat = -1;
                return strSubString;
            }
        }
        this.bp += i7;
        this.ch = charAt(this.bp);
        this.matchStat = 3;
        return strSubString;
    }

    public long scanFieldSymbol(char[] cArr) {
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0L;
        }
        int length = cArr.length;
        int i = length + 1;
        if (charAt(this.bp + length) != '\"') {
            this.matchStat = -1;
            return 0L;
        }
        long j = -3750763034362895579L;
        while (true) {
            int i2 = i + 1;
            char cCharAt = charAt(this.bp + i);
            if (cCharAt == '\"') {
                int i3 = i2 + 1;
                char cCharAt2 = charAt(this.bp + i2);
                if (cCharAt2 == ',') {
                    this.bp += i3;
                    this.ch = charAt(this.bp);
                    this.matchStat = 3;
                    return j;
                }
                if (cCharAt2 == '}') {
                    int i4 = i3 + 1;
                    char cCharAt3 = charAt(this.bp + i3);
                    if (cCharAt3 == ',') {
                        this.token = 16;
                        this.bp += i4;
                        this.ch = charAt(this.bp);
                    } else if (cCharAt3 == ']') {
                        this.token = 15;
                        this.bp += i4;
                        this.ch = charAt(this.bp);
                    } else if (cCharAt3 == '}') {
                        this.token = 13;
                        this.bp += i4;
                        this.ch = charAt(this.bp);
                    } else if (cCharAt3 == 26) {
                        this.token = 20;
                        this.bp += i4 - 1;
                        this.ch = JSONLexer.EOI;
                    } else {
                        this.matchStat = -1;
                        return 0L;
                    }
                    this.matchStat = 4;
                    return j;
                }
                this.matchStat = -1;
                return 0L;
            }
            j = (j ^ ((long) cCharAt)) * 1099511628211L;
            if (cCharAt == '\\') {
                this.matchStat = -1;
                return 0L;
            }
            i = i2;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public Enum<?> scanEnum(Class<?> cls, SymbolTable symbolTable, char c) {
        String strScanSymbolWithSeperator = scanSymbolWithSeperator(symbolTable, c);
        if (strScanSymbolWithSeperator == null) {
            return null;
        }
        return Enum.valueOf(cls, strScanSymbolWithSeperator);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public String scanSymbolWithSeperator(SymbolTable symbolTable, char c) {
        this.matchStat = 0;
        char cCharAt = charAt(this.bp + 0);
        if (cCharAt == 'n') {
            if (charAt(this.bp + 1) == 'u' && charAt(this.bp + 1 + 1) == 'l' && charAt(this.bp + 1 + 2) == 'l') {
                if (charAt(this.bp + 4) == c) {
                    this.bp += 5;
                    this.ch = charAt(this.bp);
                    this.matchStat = 3;
                    return null;
                }
                this.matchStat = -1;
                return null;
            }
            this.matchStat = -1;
            return null;
        }
        if (cCharAt != '\"') {
            this.matchStat = -1;
            return null;
        }
        int i = 0;
        int i2 = 1;
        while (true) {
            int i3 = i2 + 1;
            char cCharAt2 = charAt(this.bp + i2);
            if (cCharAt2 == '\"') {
                int i4 = this.bp + 0 + 1;
                String strAddSymbol = addSymbol(i4, ((this.bp + i3) - i4) - 1, i, symbolTable);
                int i5 = i3 + 1;
                char cCharAt3 = charAt(this.bp + i3);
                while (cCharAt3 != c) {
                    if (isWhitespace(cCharAt3)) {
                        cCharAt3 = charAt(this.bp + i5);
                        i5++;
                    } else {
                        this.matchStat = -1;
                        return strAddSymbol;
                    }
                }
                this.bp += i5;
                this.ch = charAt(this.bp);
                this.matchStat = 3;
                return strAddSymbol;
            }
            i = (i * 31) + cCharAt2;
            if (cCharAt2 == '\\') {
                this.matchStat = -1;
                return null;
            }
            i2 = i3;
        }
    }

    public Collection<String> newCollectionByType(Class<?> cls) {
        if (cls.isAssignableFrom(HashSet.class)) {
            return new HashSet();
        }
        if (cls.isAssignableFrom(ArrayList.class)) {
            return new ArrayList();
        }
        try {
            return (Collection) cls.newInstance();
        } catch (Exception e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public Collection<String> scanFieldStringArray(char[] cArr, Class<?> cls) {
        int i;
        char cCharAt;
        int i2;
        char cCharAt2;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        Collection<String> collectionNewCollectionByType = newCollectionByType(cls);
        int length = cArr.length;
        int i3 = length + 1;
        if (charAt(this.bp + length) != '[') {
            this.matchStat = -1;
            return null;
        }
        int i4 = i3 + 1;
        char cCharAt3 = charAt(this.bp + i3);
        while (true) {
            if (cCharAt3 == '\"') {
                int iIndexOf = indexOf('\"', this.bp + i4);
                if (iIndexOf == -1) {
                    throw new JSONException("unclosed str");
                }
                int i5 = this.bp + i4;
                String strSubString = subString(i5, iIndexOf - i5);
                if (strSubString.indexOf(92) != -1) {
                    while (true) {
                        int i6 = 0;
                        for (int i7 = iIndexOf - 1; i7 >= 0 && charAt(i7) == '\\'; i7--) {
                            i6++;
                        }
                        if (i6 % 2 == 0) {
                            break;
                        }
                        iIndexOf = indexOf('\"', iIndexOf + 1);
                    }
                    int i8 = iIndexOf - (this.bp + i4);
                    strSubString = readString(sub_chars(this.bp + i4, i8), i8);
                }
                int i9 = i4 + (iIndexOf - (this.bp + i4)) + 1;
                i2 = i9 + 1;
                cCharAt2 = charAt(this.bp + i9);
                collectionNewCollectionByType.add(strSubString);
            } else if (cCharAt3 == 'n' && charAt(this.bp + i4) == 'u' && charAt(this.bp + i4 + 1) == 'l' && charAt(this.bp + i4 + 2) == 'l') {
                int i10 = i4 + 3;
                i2 = i10 + 1;
                cCharAt2 = charAt(this.bp + i10);
                collectionNewCollectionByType.add(null);
            } else {
                if (cCharAt3 == ']' && collectionNewCollectionByType.size() == 0) {
                    i = i4 + 1;
                    cCharAt = charAt(this.bp + i4);
                    break;
                }
                throw new JSONException("illega str");
            }
            if (cCharAt2 != ',') {
                if (cCharAt2 == ']') {
                    i = i2 + 1;
                    cCharAt = charAt(this.bp + i2);
                    break;
                }
                this.matchStat = -1;
                return null;
            }
            i4 = i2 + 1;
            cCharAt3 = charAt(this.bp + i2);
        }
        if (cCharAt == ',') {
            this.bp += i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            return collectionNewCollectionByType;
        }
        if (cCharAt == '}') {
            int i11 = i + 1;
            char cCharAt4 = charAt(this.bp + i);
            if (cCharAt4 == ',') {
                this.token = 16;
                this.bp += i11;
                this.ch = charAt(this.bp);
            } else if (cCharAt4 == ']') {
                this.token = 15;
                this.bp += i11;
                this.ch = charAt(this.bp);
            } else if (cCharAt4 == '}') {
                this.token = 13;
                this.bp += i11;
                this.ch = charAt(this.bp);
            } else if (cCharAt4 == 26) {
                this.bp += i11 - 1;
                this.token = 20;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return collectionNewCollectionByType;
        }
        this.matchStat = -1;
        return null;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public void scanStringArray(Collection<String> collection, char c) {
        int i;
        char cCharAt;
        int i2;
        char cCharAt2;
        this.matchStat = 0;
        char cCharAt3 = charAt(this.bp + 0);
        char c2 = 'u';
        char c3 = 'n';
        if (cCharAt3 == 'n' && charAt(this.bp + 1) == 'u' && charAt(this.bp + 1 + 1) == 'l' && charAt(this.bp + 1 + 2) == 'l' && charAt(this.bp + 1 + 3) == c) {
            this.bp += 5;
            this.ch = charAt(this.bp);
            this.matchStat = 5;
            return;
        }
        if (cCharAt3 != '[') {
            this.matchStat = -1;
            return;
        }
        char cCharAt4 = charAt(this.bp + 1);
        int i3 = 2;
        while (true) {
            if (cCharAt4 == c3 && charAt(this.bp + i3) == c2 && charAt(this.bp + i3 + 1) == 'l' && charAt(this.bp + i3 + 2) == 'l') {
                int i4 = i3 + 3;
                i = i4 + 1;
                cCharAt = charAt(this.bp + i4);
                collection.add(null);
            } else {
                if (cCharAt4 == ']' && collection.size() == 0) {
                    i2 = i3 + 1;
                    cCharAt2 = charAt(this.bp + i3);
                    break;
                }
                if (cCharAt4 != '\"') {
                    this.matchStat = -1;
                    return;
                }
                int i5 = this.bp + i3;
                int iIndexOf = indexOf('\"', i5);
                if (iIndexOf == -1) {
                    throw new JSONException("unclosed str");
                }
                String strSubString = subString(this.bp + i3, iIndexOf - i5);
                if (strSubString.indexOf(92) != -1) {
                    while (true) {
                        int i6 = 0;
                        for (int i7 = iIndexOf - 1; i7 >= 0 && charAt(i7) == '\\'; i7--) {
                            i6++;
                        }
                        if (i6 % 2 == 0) {
                            break;
                        } else {
                            iIndexOf = indexOf('\"', iIndexOf + 1);
                        }
                    }
                    int i8 = iIndexOf - i5;
                    strSubString = readString(sub_chars(this.bp + i3, i8), i8);
                }
                int i9 = i3 + (iIndexOf - (this.bp + i3)) + 1;
                i = i9 + 1;
                cCharAt = charAt(this.bp + i9);
                collection.add(strSubString);
            }
            if (cCharAt != ',') {
                if (cCharAt == ']') {
                    i2 = i + 1;
                    cCharAt2 = charAt(this.bp + i);
                    break;
                } else {
                    this.matchStat = -1;
                    return;
                }
            }
            i3 = i + 1;
            cCharAt4 = charAt(this.bp + i);
            c2 = 'u';
            c3 = 'n';
        }
        if (cCharAt2 == c) {
            this.bp += i2;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            return;
        }
        this.matchStat = -1;
    }

    public int scanFieldInt(char[] cArr) {
        int i;
        char cCharAt;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0;
        }
        int length = cArr.length;
        int i2 = length + 1;
        char cCharAt2 = charAt(this.bp + length);
        boolean z = cCharAt2 == '-';
        if (z) {
            cCharAt2 = charAt(this.bp + i2);
            i2++;
        }
        if (cCharAt2 < '0' || cCharAt2 > '9') {
            this.matchStat = -1;
            return 0;
        }
        int i3 = cCharAt2 - '0';
        while (true) {
            i = i2 + 1;
            cCharAt = charAt(this.bp + i2);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            i3 = (i3 * 10) + (cCharAt - '0');
            i2 = i;
        }
        if (cCharAt == '.') {
            this.matchStat = -1;
            return 0;
        }
        if ((i3 < 0 || i > cArr.length + 14) && !(i3 == Integer.MIN_VALUE && i == 17 && z)) {
            this.matchStat = -1;
            return 0;
        }
        if (cCharAt == ',') {
            this.bp += i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return z ? -i3 : i3;
        }
        if (cCharAt == '}') {
            int i4 = i + 1;
            char cCharAt3 = charAt(this.bp + i);
            if (cCharAt3 == ',') {
                this.token = 16;
                this.bp += i4;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == ']') {
                this.token = 15;
                this.bp += i4;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == '}') {
                this.token = 13;
                this.bp += i4;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == 26) {
                this.token = 20;
                this.bp += i4 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return 0;
            }
            this.matchStat = 4;
            return z ? -i3 : i3;
        }
        this.matchStat = -1;
        return 0;
    }

    public final int[] scanFieldIntArray(char[] cArr) {
        int i;
        boolean z;
        int i2;
        char cCharAt;
        int i3;
        int i4;
        char cCharAt2;
        int[] iArr;
        int[] iArr2;
        this.matchStat = 0;
        int[] iArr3 = null;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = cArr.length;
        int i5 = length + 1;
        if (charAt(this.bp + length) != '[') {
            this.matchStat = -2;
            return null;
        }
        int i6 = i5 + 1;
        char cCharAt3 = charAt(this.bp + i5);
        int[] iArr4 = new int[16];
        if (cCharAt3 == ']') {
            i4 = i6 + 1;
            cCharAt2 = charAt(this.bp + i6);
            i3 = 0;
        } else {
            int i7 = 0;
            while (true) {
                if (cCharAt3 == '-') {
                    i = i6 + 1;
                    cCharAt3 = charAt(this.bp + i6);
                    z = true;
                } else {
                    i = i6;
                    z = false;
                }
                if (cCharAt3 >= '0' && cCharAt3 <= '9') {
                    int i8 = cCharAt3 - '0';
                    while (true) {
                        i2 = i + 1;
                        cCharAt = charAt(this.bp + i);
                        if (cCharAt < '0' || cCharAt > '9') {
                            break;
                        }
                        i8 = (i8 * 10) + (cCharAt - '0');
                        i = i2;
                    }
                    if (i7 >= iArr4.length) {
                        int[] iArr5 = new int[(iArr4.length * 3) / 2];
                        System.arraycopy(iArr4, 0, iArr5, 0, i7);
                        iArr4 = iArr5;
                    }
                    i3 = i7 + 1;
                    if (z) {
                        i8 = -i8;
                    }
                    iArr4[i7] = i8;
                    if (cCharAt == ',') {
                        i6 = i2 + 1;
                        cCharAt = charAt(this.bp + i2);
                        iArr = null;
                    } else {
                        if (cCharAt == ']') {
                            i4 = i2 + 1;
                            cCharAt2 = charAt(this.bp + i2);
                            break;
                        }
                        iArr = null;
                        i6 = i2;
                    }
                    i7 = i3;
                    char c = cCharAt;
                    iArr3 = iArr;
                    cCharAt3 = c;
                } else {
                    int[] iArr6 = iArr3;
                    this.matchStat = -1;
                    return iArr6;
                }
            }
        }
        if (i3 != iArr4.length) {
            iArr2 = new int[i3];
            System.arraycopy(iArr4, 0, iArr2, 0, i3);
        } else {
            iArr2 = iArr4;
        }
        if (cCharAt2 == ',') {
            this.bp += i4 - 1;
            next();
            this.matchStat = 3;
            this.token = 16;
            return iArr2;
        }
        if (cCharAt2 == '}') {
            int i9 = i4 + 1;
            char cCharAt4 = charAt(this.bp + i4);
            if (cCharAt4 == ',') {
                this.token = 16;
                this.bp += i9 - 1;
                next();
            } else if (cCharAt4 == ']') {
                this.token = 15;
                this.bp += i9 - 1;
                next();
            } else if (cCharAt4 == '}') {
                this.token = 13;
                this.bp += i9 - 1;
                next();
            } else if (cCharAt4 == 26) {
                this.bp += i9 - 1;
                this.token = 20;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return iArr2;
        }
        this.matchStat = -1;
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:37:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:39:0x00b3 A[LOOP:0: B:34:0x009b->B:39:0x00b3, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:43:0x00be A[SYNTHETIC] */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    public boolean scanBoolean(char c) {
        boolean z = false;
        this.matchStat = 0;
        char cCharAt = charAt(this.bp + 0);
        int i = 2;
        if (cCharAt == 't') {
            if (charAt(this.bp + 1) == 'r' && charAt(this.bp + 1 + 1) == 'u' && charAt(this.bp + 1 + 2) == 'e') {
                cCharAt = charAt(this.bp + 4);
                i = 5;
            } else {
                this.matchStat = -1;
                return false;
            }
        } else {
            if (cCharAt == 'f') {
                if (charAt(this.bp + 1) == 'a' && charAt(this.bp + 1 + 1) == 'l' && charAt(this.bp + 1 + 2) == 's' && charAt(this.bp + 1 + 3) == 'e') {
                    cCharAt = charAt(this.bp + 5);
                    i = 6;
                } else {
                    this.matchStat = -1;
                    return false;
                }
            } else if (cCharAt == '1') {
                cCharAt = charAt(this.bp + 1);
            } else if (cCharAt == '0') {
                cCharAt = charAt(this.bp + 1);
            } else {
                i = 1;
            }
            while (cCharAt != c) {
                if (isWhitespace(cCharAt)) {
                    cCharAt = charAt(this.bp + i);
                    i++;
                } else {
                    this.matchStat = -1;
                    return z;
                }
            }
            this.bp += i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            return z;
        }
        z = true;
        while (cCharAt != c) {
            if (isWhitespace(cCharAt)) {
                cCharAt = charAt(this.bp + i);
                i++;
            } else {
                this.matchStat = -1;
                return z;
            }
        }
        this.bp += i;
        this.ch = charAt(this.bp);
        this.matchStat = 3;
        return z;
    }

    /* JADX WARN: Code duplicated, block: B:61:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:65:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:67:0x0103  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x00ce -> B:57:0x00cf). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    public int scanInt(char r14) {
        /*
            Method dump skipped, instruction units count: 275
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanInt(char):int");
    }

    public boolean scanFieldBoolean(char[] cArr) {
        boolean z;
        int i;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return false;
        }
        int length = cArr.length;
        int i2 = length + 1;
        char cCharAt = charAt(this.bp + length);
        if (cCharAt == 't') {
            int i3 = i2 + 1;
            if (charAt(this.bp + i2) != 'r') {
                this.matchStat = -1;
                return false;
            }
            int i4 = i3 + 1;
            if (charAt(this.bp + i3) != 'u') {
                this.matchStat = -1;
                return false;
            }
            i = i4 + 1;
            if (charAt(this.bp + i4) != 'e') {
                this.matchStat = -1;
                return false;
            }
            z = true;
        } else if (cCharAt == 'f') {
            int i5 = i2 + 1;
            if (charAt(this.bp + i2) != 'a') {
                this.matchStat = -1;
                return false;
            }
            int i6 = i5 + 1;
            if (charAt(this.bp + i5) != 'l') {
                this.matchStat = -1;
                return false;
            }
            int i7 = i6 + 1;
            if (charAt(this.bp + i6) != 's') {
                this.matchStat = -1;
                return false;
            }
            int i8 = i7 + 1;
            if (charAt(this.bp + i7) != 'e') {
                this.matchStat = -1;
                return false;
            }
            z = false;
            i = i8;
        } else {
            this.matchStat = -1;
            return false;
        }
        int i9 = i + 1;
        char cCharAt2 = charAt(this.bp + i);
        if (cCharAt2 == ',') {
            this.bp += i9;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return z;
        }
        if (cCharAt2 == '}') {
            int i10 = i9 + 1;
            char cCharAt3 = charAt(this.bp + i9);
            if (cCharAt3 == ',') {
                this.token = 16;
                this.bp += i10;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == ']') {
                this.token = 15;
                this.bp += i10;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == '}') {
                this.token = 13;
                this.bp += i10;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == 26) {
                this.token = 20;
                this.bp += i10 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return false;
            }
            this.matchStat = 4;
            return z;
        }
        this.matchStat = -1;
        return false;
    }

    public long scanFieldLong(char[] cArr) {
        int i;
        boolean z;
        int i2;
        char cCharAt;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0L;
        }
        int length = cArr.length;
        int i3 = length + 1;
        char cCharAt2 = charAt(this.bp + length);
        if (cCharAt2 == '-') {
            i = i3 + 1;
            cCharAt2 = charAt(this.bp + i3);
            z = true;
        } else {
            i = i3;
            z = false;
        }
        if (cCharAt2 < '0' || cCharAt2 > '9') {
            this.matchStat = -1;
            return 0L;
        }
        long j = cCharAt2 - '0';
        while (true) {
            i2 = i + 1;
            cCharAt = charAt(this.bp + i);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            j = (j * 10) + ((long) (cCharAt - '0'));
            i = i2;
        }
        if (cCharAt == '.') {
            this.matchStat = -1;
            return 0L;
        }
        if (!(i2 - cArr.length < 21 && (j >= 0 || (j == Long.MIN_VALUE && z)))) {
            this.matchStat = -1;
            return 0L;
        }
        if (cCharAt == ',') {
            this.bp += i2;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return z ? -j : j;
        }
        if (cCharAt == '}') {
            int i4 = i2 + 1;
            char cCharAt3 = charAt(this.bp + i2);
            if (cCharAt3 == ',') {
                this.token = 16;
                this.bp += i4;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == ']') {
                this.token = 15;
                this.bp += i4;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == '}') {
                this.token = 13;
                this.bp += i4;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == 26) {
                this.token = 20;
                this.bp += i4 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return 0L;
            }
            this.matchStat = 4;
            return z ? -j : j;
        }
        this.matchStat = -1;
        return 0L;
    }

    /* JADX WARN: Code duplicated, block: B:73:0x0122  */
    /* JADX WARN: Code duplicated, block: B:77:0x013a  */
    /* JADX WARN: Code duplicated, block: B:79:0x0140  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:68:0x010b -> B:69:0x010c). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    public long scanLong(char r21) {
        /*
            Method dump skipped, instruction units count: 336
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanLong(char):long");
    }

    public final float scanFieldFloat(char[] cArr) {
        int i;
        char cCharAt;
        int i2;
        int length;
        int i3;
        char cCharAt2;
        float f;
        int i4;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0.0f;
        }
        int length2 = cArr.length;
        int i5 = length2 + 1;
        char cCharAt3 = charAt(this.bp + length2);
        boolean z = cCharAt3 == '\"';
        if (z) {
            cCharAt3 = charAt(this.bp + i5);
            i5++;
        }
        boolean z2 = cCharAt3 == '-';
        if (z2) {
            cCharAt3 = charAt(this.bp + i5);
            i5++;
        }
        if (cCharAt3 >= '0') {
            char c = '9';
            if (cCharAt3 <= '9') {
                long j = cCharAt3 - '0';
                while (true) {
                    i = i5 + 1;
                    cCharAt = charAt(this.bp + i5);
                    if (cCharAt < '0' || cCharAt > '9') {
                        break;
                    }
                    j = (j * 10) + ((long) (cCharAt - '0'));
                    i5 = i;
                }
                if (cCharAt == '.') {
                    int i6 = i + 1;
                    char cCharAt4 = charAt(this.bp + i);
                    if (cCharAt4 < '0' || cCharAt4 > '9') {
                        this.matchStat = -1;
                        return 0.0f;
                    }
                    j = (j * 10) + ((long) (cCharAt4 - '0'));
                    i2 = 10;
                    while (true) {
                        i4 = i6 + 1;
                        cCharAt = charAt(this.bp + i6);
                        if (cCharAt < '0' || cCharAt > c) {
                            break;
                        }
                        j = (j * 10) + ((long) (cCharAt - '0'));
                        i2 *= 10;
                        i6 = i4;
                        c = '9';
                    }
                    i = i4;
                } else {
                    i2 = 1;
                }
                boolean z3 = cCharAt == 'e' || cCharAt == 'E';
                if (z3) {
                    int i7 = i + 1;
                    char cCharAt5 = charAt(this.bp + i);
                    if (cCharAt5 == '+' || cCharAt5 == '-') {
                        int i8 = i7 + 1;
                        cCharAt = charAt(this.bp + i7);
                        i = i8;
                    } else {
                        i = i7;
                        cCharAt = cCharAt5;
                    }
                    while (cCharAt >= '0' && cCharAt <= '9') {
                        int i9 = i + 1;
                        cCharAt = charAt(this.bp + i);
                        i = i9;
                    }
                }
                if (!z) {
                    length = this.bp + cArr.length;
                    i3 = ((this.bp + i) - length) - 1;
                    cCharAt2 = cCharAt;
                } else {
                    if (cCharAt != '\"') {
                        this.matchStat = -1;
                        return 0.0f;
                    }
                    int i10 = i + 1;
                    cCharAt2 = charAt(this.bp + i);
                    length = this.bp + cArr.length + 1;
                    i3 = ((this.bp + i10) - length) - 2;
                    i = i10;
                }
                if (z3 || i3 >= 17) {
                    f = Float.parseFloat(subString(length, i3));
                } else {
                    f = (float) (j / ((double) i2));
                    if (z2) {
                        f = -f;
                    }
                }
                if (cCharAt2 == ',') {
                    this.bp += i;
                    this.ch = charAt(this.bp);
                    this.matchStat = 3;
                    this.token = 16;
                    return f;
                }
                if (cCharAt2 == '}') {
                    int i11 = i + 1;
                    char cCharAt6 = charAt(this.bp + i);
                    if (cCharAt6 == ',') {
                        this.token = 16;
                        this.bp += i11;
                        this.ch = charAt(this.bp);
                    } else if (cCharAt6 == ']') {
                        this.token = 15;
                        this.bp += i11;
                        this.ch = charAt(this.bp);
                    } else if (cCharAt6 == '}') {
                        this.token = 13;
                        this.bp += i11;
                        this.ch = charAt(this.bp);
                    } else if (cCharAt6 == 26) {
                        this.bp += i11 - 1;
                        this.token = 20;
                        this.ch = JSONLexer.EOI;
                    } else {
                        this.matchStat = -1;
                        return 0.0f;
                    }
                    this.matchStat = 4;
                    return f;
                }
                this.matchStat = -1;
                return 0.0f;
            }
        }
        boolean z4 = z;
        if (cCharAt3 == 'n' && charAt(this.bp + i5) == 'u' && charAt(this.bp + i5 + 1) == 'l' && charAt(this.bp + i5 + 2) == 'l') {
            this.matchStat = 5;
            int i12 = i5 + 3;
            int i13 = i12 + 1;
            char cCharAt7 = charAt(this.bp + i12);
            if (z4 && cCharAt7 == '\"') {
                cCharAt7 = charAt(this.bp + i13);
                i13++;
            }
            while (cCharAt7 != ',') {
                if (cCharAt7 == '}') {
                    this.bp += i13;
                    this.ch = charAt(this.bp);
                    this.matchStat = 5;
                    this.token = 13;
                    return 0.0f;
                }
                if (isWhitespace(cCharAt7)) {
                    cCharAt7 = charAt(this.bp + i13);
                    i13++;
                } else {
                    this.matchStat = -1;
                    return 0.0f;
                }
            }
            this.bp += i13;
            this.ch = charAt(this.bp);
            this.matchStat = 5;
            this.token = 16;
            return 0.0f;
        }
        this.matchStat = -1;
        return 0.0f;
    }

    /* JADX WARN: Code duplicated, block: B:55:0x00ca A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x00cc -> B:52:0x00b8). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final float scanFloat(char r24) {
        /*
            Method dump skipped, instruction units count: 448
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFloat(char):float");
    }

    /* JADX WARN: Code duplicated, block: B:57:0x00d7 A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x00d9 -> B:54:0x00c7). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    public double scanDouble(char r24) {
        /*
            Method dump skipped, instruction units count: 462
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanDouble(char):double");
    }

    /* JADX WARN: Code duplicated, block: B:53:0x00ae A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x00b0 -> B:50:0x009e). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    public java.math.BigDecimal scanDecimal(char r20) {
        /*
            Method dump skipped, instruction units count: 491
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanDecimal(char):java.math.BigDecimal");
    }

    public final float[] scanFieldFloatArray(char[] cArr) {
        int i;
        int i2;
        char cCharAt;
        int i3;
        float f;
        float[] fArr;
        char c;
        boolean z;
        char cCharAt2;
        boolean z2 = false;
        this.matchStat = 0;
        float[] fArr2 = null;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = cArr.length;
        int i4 = length + 1;
        if (charAt(this.bp + length) != '[') {
            this.matchStat = -2;
            return null;
        }
        int i5 = i4 + 1;
        char cCharAt3 = charAt(this.bp + i4);
        float[] fArr3 = new float[16];
        int i6 = 0;
        while (true) {
            int i7 = (this.bp + i5) - 1;
            boolean z3 = cCharAt3 == '-' ? true : z2;
            if (z3) {
                i = i5 + 1;
                cCharAt3 = charAt(this.bp + i5);
            } else {
                i = i5;
            }
            if (cCharAt3 < '0' || cCharAt3 > '9') {
                break;
            }
            int i8 = cCharAt3 - '0';
            while (true) {
                i2 = i + 1;
                cCharAt = charAt(this.bp + i);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                i8 = (i8 * 10) + (cCharAt - '0');
                i = i2;
            }
            if (cCharAt == '.' ? true : z2) {
                int i9 = i2 + 1;
                char cCharAt4 = charAt(this.bp + i2);
                if (cCharAt4 < '0' || cCharAt4 > '9') {
                    this.matchStat = -1;
                    return fArr2;
                }
                i8 = (i8 * 10) + (cCharAt4 - '0');
                int i10 = 10;
                while (true) {
                    i2 = i9 + 1;
                    cCharAt2 = charAt(this.bp + i9);
                    if (cCharAt2 < '0' || cCharAt2 > '9') {
                        break;
                    }
                    i8 = (i8 * 10) + (cCharAt2 - '0');
                    i10 *= 10;
                    i9 = i2;
                }
                int i11 = i10;
                cCharAt = cCharAt2;
                i3 = i11;
            } else {
                i3 = 1;
            }
            boolean z4 = cCharAt == 'e' || cCharAt == 'E';
            if (z4) {
                int i12 = i2 + 1;
                cCharAt = charAt(this.bp + i2);
                if (cCharAt == '+' || cCharAt == '-') {
                    int i13 = i12 + 1;
                    cCharAt = charAt(this.bp + i12);
                    i2 = i13;
                } else {
                    i2 = i12;
                }
                while (cCharAt >= '0' && cCharAt <= '9') {
                    int i14 = i2 + 1;
                    cCharAt = charAt(this.bp + i2);
                    i2 = i14;
                }
            }
            int i15 = ((this.bp + i2) - i7) - 1;
            if (z4 || i15 >= 10) {
                f = Float.parseFloat(subString(i7, i15));
            } else {
                f = i8 / i3;
                if (z3) {
                    f = -f;
                }
            }
            if (i6 >= fArr3.length) {
                float[] fArr4 = new float[(fArr3.length * 3) / 2];
                System.arraycopy(fArr3, 0, fArr4, 0, i6);
                fArr3 = fArr4;
            }
            int i16 = i6 + 1;
            fArr3[i6] = f;
            if (cCharAt == ',') {
                i5 = i2 + 1;
                cCharAt = charAt(this.bp + i2);
                fArr = null;
                c = 16;
                z = false;
            } else {
                if (cCharAt == ']') {
                    int i17 = i2 + 1;
                    char cCharAt5 = charAt(this.bp + i2);
                    if (i16 != fArr3.length) {
                        float[] fArr5 = new float[i16];
                        System.arraycopy(fArr3, 0, fArr5, 0, i16);
                        fArr3 = fArr5;
                    }
                    if (cCharAt5 == ',') {
                        this.bp += i17 - 1;
                        next();
                        this.matchStat = 3;
                        this.token = 16;
                        return fArr3;
                    }
                    if (cCharAt5 == '}') {
                        int i18 = i17 + 1;
                        char cCharAt6 = charAt(this.bp + i17);
                        if (cCharAt6 == ',') {
                            this.token = 16;
                            this.bp += i18 - 1;
                            next();
                        } else if (cCharAt6 == ']') {
                            this.token = 15;
                            this.bp += i18 - 1;
                            next();
                        } else if (cCharAt6 == '}') {
                            this.token = 13;
                            this.bp += i18 - 1;
                            next();
                        } else if (cCharAt6 == 26) {
                            this.bp += i18 - 1;
                            this.token = 20;
                            this.ch = JSONLexer.EOI;
                        } else {
                            this.matchStat = -1;
                            return null;
                        }
                        this.matchStat = 4;
                        return fArr3;
                    }
                    this.matchStat = -1;
                    return null;
                }
                fArr = null;
                c = 16;
                z = false;
                i5 = i2;
            }
            i6 = i16;
            z2 = z;
            fArr2 = fArr;
            cCharAt3 = cCharAt;
        }
        float[] fArr6 = fArr2;
        this.matchStat = -1;
        return fArr6;
    }

    public final float[][] scanFieldFloatArray2(char[] cArr) {
        int i;
        int i2;
        char cCharAt;
        char cCharAt2;
        int i3;
        float f;
        int i4;
        float[][] fArr;
        int i5;
        float[][] fArr2;
        int i6;
        char c;
        float[][] fArr3;
        int i7;
        int i8 = 0;
        this.matchStat = 0;
        float[][] fArr4 = null;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return (float[][]) null;
        }
        int length = cArr.length;
        int i9 = length + 1;
        char c2 = '[';
        if (charAt(this.bp + length) != '[') {
            this.matchStat = -2;
            return (float[][]) null;
        }
        int i10 = i9 + 1;
        char cCharAt3 = charAt(this.bp + i9);
        int i11 = 16;
        float[][] fArr5 = new float[16][];
        int i12 = 0;
        loop0: while (true) {
            if (cCharAt3 == c2) {
                int i13 = i10 + 1;
                char cCharAt4 = charAt(this.bp + i10);
                float[] fArr6 = new float[i11];
                int i14 = i8;
                while (true) {
                    int i15 = (this.bp + i13) - 1;
                    int i16 = cCharAt4 == '-' ? 1 : i8;
                    if (i16 != 0) {
                        i = i13 + 1;
                        cCharAt4 = charAt(this.bp + i13);
                    } else {
                        i = i13;
                    }
                    if (cCharAt4 < '0' || cCharAt4 > '9') {
                        break loop0;
                    }
                    int i17 = cCharAt4 - '0';
                    while (true) {
                        i2 = i + 1;
                        cCharAt = charAt(this.bp + i);
                        if (cCharAt < '0' || cCharAt > '9') {
                            break;
                        }
                        i17 = (i17 * 10) + (cCharAt - '0');
                        i = i2;
                    }
                    if (cCharAt == '.') {
                        int i18 = i2 + 1;
                        char cCharAt5 = charAt(this.bp + i2);
                        if (cCharAt5 < '0' || cCharAt5 > '9') {
                            this.matchStat = -1;
                            return fArr4;
                        }
                        i17 = (i17 * 10) + (cCharAt5 - '0');
                        i3 = 10;
                        while (true) {
                            i2 = i18 + 1;
                            cCharAt2 = charAt(this.bp + i18);
                            if (cCharAt2 < '0' || cCharAt2 > '9') {
                                break;
                            }
                            i17 = (i17 * 10) + (cCharAt2 - '0');
                            i3 *= 10;
                            i18 = i2;
                        }
                    } else {
                        cCharAt2 = cCharAt;
                        i3 = 1;
                    }
                    boolean z = cCharAt2 == 'e' || cCharAt2 == 'E';
                    if (z) {
                        int i19 = i2 + 1;
                        cCharAt2 = charAt(this.bp + i2);
                        if (cCharAt2 == '+' || cCharAt2 == '-') {
                            i2 = i19 + 1;
                            cCharAt2 = charAt(this.bp + i19);
                        } else {
                            i2 = i19;
                        }
                        while (cCharAt2 >= '0' && cCharAt2 <= '9') {
                            int i20 = i2 + 1;
                            char cCharAt6 = charAt(this.bp + i2);
                            i2 = i20;
                            cCharAt2 = cCharAt6;
                        }
                    }
                    int i21 = ((this.bp + i2) - i15) - 1;
                    if (z || i21 >= 10) {
                        f = Float.parseFloat(subString(i15, i21));
                    } else {
                        f = i17 / i3;
                        if (i16 != 0) {
                            f = -f;
                        }
                    }
                    if (i14 >= fArr6.length) {
                        float[] fArr7 = new float[(fArr6.length * 3) / 2];
                        System.arraycopy(fArr6, 0, fArr7, 0, i14);
                        fArr6 = fArr7;
                    }
                    int i22 = i14 + 1;
                    fArr6[i14] = f;
                    if (cCharAt2 == ',') {
                        cCharAt4 = charAt(this.bp + i2);
                        i13 = i2 + 1;
                        c = 16;
                        fArr3 = null;
                        i7 = 0;
                    } else {
                        if (cCharAt2 == ']') {
                            int i23 = i2 + 1;
                            char cCharAt7 = charAt(this.bp + i2);
                            if (i22 != fArr6.length) {
                                float[] fArr8 = new float[i22];
                                i4 = 0;
                                System.arraycopy(fArr6, 0, fArr8, 0, i22);
                                fArr6 = fArr8;
                            } else {
                                i4 = 0;
                            }
                            if (i12 >= fArr5.length) {
                                fArr5 = new float[(fArr5.length * 3) / 2][];
                                System.arraycopy(fArr6, i4, fArr5, i4, i22);
                            }
                            int i24 = i12 + 1;
                            fArr5[i12] = fArr6;
                            if (cCharAt7 == ',') {
                                i10 = i23 + 1;
                                cCharAt3 = charAt(this.bp + i23);
                                i5 = 16;
                                fArr2 = null;
                                i6 = 0;
                            } else {
                                if (cCharAt7 == ']') {
                                    int i25 = i23 + 1;
                                    char cCharAt8 = charAt(this.bp + i23);
                                    if (i24 != fArr5.length) {
                                        fArr = new float[i24][];
                                        System.arraycopy(fArr5, 0, fArr, 0, i24);
                                    } else {
                                        fArr = fArr5;
                                    }
                                    if (cCharAt8 == ',') {
                                        this.bp += i25 - 1;
                                        next();
                                        this.matchStat = 3;
                                        this.token = 16;
                                        return fArr;
                                    }
                                    if (cCharAt8 == '}') {
                                        int i26 = i25 + 1;
                                        char cCharAt9 = charAt(this.bp + i25);
                                        if (cCharAt9 == ',') {
                                            this.token = 16;
                                            this.bp += i26 - 1;
                                            next();
                                        } else if (cCharAt9 == ']') {
                                            this.token = 15;
                                            this.bp += i26 - 1;
                                            next();
                                        } else if (cCharAt9 == '}') {
                                            this.token = 13;
                                            this.bp += i26 - 1;
                                            next();
                                        } else if (cCharAt9 == 26) {
                                            this.bp += i26 - 1;
                                            this.token = 20;
                                            this.ch = JSONLexer.EOI;
                                        } else {
                                            this.matchStat = -1;
                                            return (float[][]) null;
                                        }
                                        this.matchStat = 4;
                                        return fArr;
                                    }
                                    this.matchStat = -1;
                                    return (float[][]) null;
                                }
                                i5 = 16;
                                fArr2 = null;
                                i6 = 0;
                                cCharAt3 = cCharAt7;
                                i10 = i23;
                            }
                            i12 = i24;
                            i11 = i5;
                            fArr4 = fArr2;
                            i8 = i6;
                            break;
                        }
                        c = 16;
                        fArr3 = null;
                        i7 = 0;
                        cCharAt4 = cCharAt2;
                        i13 = i2;
                    }
                    i14 = i22;
                    fArr4 = fArr3;
                    i8 = i7;
                }
            }
            c2 = '[';
        }
        this.matchStat = -1;
        return fArr4;
    }

    public final double scanFieldDouble(char[] cArr) {
        int i;
        int i2;
        int i3;
        char cCharAt;
        int length;
        int i4;
        double d;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0.0d;
        }
        int length2 = cArr.length;
        int i5 = length2 + 1;
        char cCharAt2 = charAt(this.bp + length2);
        boolean z = cCharAt2 == '\"';
        if (z) {
            cCharAt2 = charAt(this.bp + i5);
            i5++;
        }
        boolean z2 = cCharAt2 == '-';
        if (z2) {
            i = i5 + 1;
            cCharAt2 = charAt(this.bp + i5);
        } else {
            i = i5;
        }
        if (cCharAt2 >= '0') {
            char c = '9';
            if (cCharAt2 <= '9') {
                long j = cCharAt2 - '0';
                while (true) {
                    i3 = i + 1;
                    cCharAt = charAt(this.bp + i);
                    if (cCharAt < '0' || cCharAt > '9') {
                        break;
                    }
                    j = (j * 10) + ((long) (cCharAt - '0'));
                    i = i3;
                    z2 = z2;
                }
                boolean z3 = z2;
                long j2 = 1;
                if (cCharAt == '.') {
                    int i6 = i3 + 1;
                    char cCharAt3 = charAt(this.bp + i3);
                    if (cCharAt3 < '0' || cCharAt3 > '9') {
                        this.matchStat = -1;
                        return 0.0d;
                    }
                    j = (j * 10) + ((long) (cCharAt3 - '0'));
                    long j3 = 10;
                    while (true) {
                        i3 = i6 + 1;
                        cCharAt = charAt(this.bp + i6);
                        if (cCharAt < '0' || cCharAt > c) {
                            break;
                        }
                        j = (j * 10) + ((long) (cCharAt - '0'));
                        j3 *= 10;
                        i6 = i3;
                        c = '9';
                    }
                    j2 = j3;
                }
                boolean z4 = cCharAt == 'e' || cCharAt == 'E';
                if (z4) {
                    int i7 = i3 + 1;
                    cCharAt = charAt(this.bp + i3);
                    if (cCharAt == '+' || cCharAt == '-') {
                        int i8 = i7 + 1;
                        cCharAt = charAt(this.bp + i7);
                        i3 = i8;
                    } else {
                        i3 = i7;
                    }
                    while (cCharAt >= '0' && cCharAt <= '9') {
                        int i9 = i3 + 1;
                        cCharAt = charAt(this.bp + i3);
                        i3 = i9;
                    }
                }
                if (!z) {
                    length = this.bp + cArr.length;
                    i4 = ((this.bp + i3) - length) - 1;
                } else {
                    if (cCharAt != '\"') {
                        this.matchStat = -1;
                        return 0.0d;
                    }
                    int i10 = i3 + 1;
                    cCharAt = charAt(this.bp + i3);
                    length = this.bp + cArr.length + 1;
                    i4 = ((this.bp + i10) - length) - 2;
                    i3 = i10;
                }
                if (z4 || i4 >= 17) {
                    d = Double.parseDouble(subString(length, i4));
                } else {
                    d = j / j2;
                    if (z3) {
                        d = -d;
                    }
                }
                if (cCharAt == ',') {
                    this.bp += i3;
                    this.ch = charAt(this.bp);
                    this.matchStat = 3;
                    this.token = 16;
                    return d;
                }
                if (cCharAt == '}') {
                    int i11 = i3 + 1;
                    char cCharAt4 = charAt(this.bp + i3);
                    if (cCharAt4 == ',') {
                        this.token = 16;
                        this.bp += i11;
                        this.ch = charAt(this.bp);
                    } else if (cCharAt4 == ']') {
                        this.token = 15;
                        this.bp += i11;
                        this.ch = charAt(this.bp);
                    } else if (cCharAt4 == '}') {
                        this.token = 13;
                        this.bp += i11;
                        this.ch = charAt(this.bp);
                    } else if (cCharAt4 == 26) {
                        this.token = 20;
                        this.bp += i11 - 1;
                        this.ch = JSONLexer.EOI;
                    } else {
                        this.matchStat = -1;
                        return 0.0d;
                    }
                    this.matchStat = 4;
                    return d;
                }
                this.matchStat = -1;
                return 0.0d;
            }
        }
        if (cCharAt2 == 'n' && charAt(this.bp + i) == 'u' && charAt(this.bp + i + 1) == 'l' && charAt(this.bp + i + 2) == 'l') {
            this.matchStat = 5;
            int i12 = i + 3;
            int i13 = i12 + 1;
            char cCharAt5 = charAt(this.bp + i12);
            if (z && cCharAt5 == '\"') {
                i2 = i13 + 1;
                cCharAt5 = charAt(this.bp + i13);
            } else {
                i2 = i13;
            }
            while (cCharAt5 != ',') {
                if (cCharAt5 == '}') {
                    this.bp += i2;
                    this.ch = charAt(this.bp);
                    this.matchStat = 5;
                    this.token = 13;
                    return 0.0d;
                }
                if (isWhitespace(cCharAt5)) {
                    cCharAt5 = charAt(this.bp + i2);
                    i2++;
                } else {
                    this.matchStat = -1;
                    return 0.0d;
                }
            }
            this.bp += i2;
            this.ch = charAt(this.bp);
            this.matchStat = 5;
            this.token = 16;
            return 0.0d;
        }
        this.matchStat = -1;
        return 0.0d;
    }

    /* JADX WARN: Code duplicated, block: B:55:0x00bc A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x00be -> B:52:0x00ac). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public java.math.BigDecimal scanFieldDecimal(char[] r20) {
        /*
            Method dump skipped, instruction units count: 514
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldDecimal(char[]):java.math.BigDecimal");
    }

    public BigInteger scanFieldBigInteger(char[] cArr) {
        int i;
        char cCharAt;
        int length;
        int i2;
        BigInteger bigIntegerValueOf;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length2 = cArr.length;
        int i3 = length2 + 1;
        char cCharAt2 = charAt(this.bp + length2);
        boolean z = cCharAt2 == '\"';
        if (z) {
            cCharAt2 = charAt(this.bp + i3);
            i3++;
        }
        boolean z2 = cCharAt2 == '-';
        if (z2) {
            cCharAt2 = charAt(this.bp + i3);
            i3++;
        }
        if (cCharAt2 >= '0') {
            char c = '9';
            if (cCharAt2 <= '9') {
                long j = cCharAt2 - '0';
                while (true) {
                    i = i3 + 1;
                    cCharAt = charAt(this.bp + i3);
                    if (cCharAt < '0' || cCharAt > c) {
                        break;
                    }
                    j = (j * 10) + ((long) (cCharAt - '0'));
                    i3 = i;
                    c = '9';
                }
                if (!z) {
                    length = this.bp + cArr.length;
                    i2 = ((this.bp + i) - length) - 1;
                } else {
                    if (cCharAt != '\"') {
                        this.matchStat = -1;
                        return null;
                    }
                    int i4 = i + 1;
                    cCharAt = charAt(this.bp + i);
                    length = this.bp + cArr.length + 1;
                    i2 = ((this.bp + i4) - length) - 2;
                    i = i4;
                }
                if (i2 < 20 || (z2 && i2 < 21)) {
                    if (z2) {
                        j = -j;
                    }
                    bigIntegerValueOf = BigInteger.valueOf(j);
                } else {
                    bigIntegerValueOf = new BigInteger(subString(length, i2));
                }
                if (cCharAt == ',') {
                    this.bp += i;
                    this.ch = charAt(this.bp);
                    this.matchStat = 3;
                    this.token = 16;
                    return bigIntegerValueOf;
                }
                if (cCharAt == '}') {
                    int i5 = i + 1;
                    char cCharAt3 = charAt(this.bp + i);
                    if (cCharAt3 == ',') {
                        this.token = 16;
                        this.bp += i5;
                        this.ch = charAt(this.bp);
                    } else if (cCharAt3 == ']') {
                        this.token = 15;
                        this.bp += i5;
                        this.ch = charAt(this.bp);
                    } else if (cCharAt3 == '}') {
                        this.token = 13;
                        this.bp += i5;
                        this.ch = charAt(this.bp);
                    } else if (cCharAt3 == 26) {
                        this.token = 20;
                        this.bp += i5 - 1;
                        this.ch = JSONLexer.EOI;
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                    this.matchStat = 4;
                    return bigIntegerValueOf;
                }
                this.matchStat = -1;
                return null;
            }
        }
        if (cCharAt2 == 'n' && charAt(this.bp + i3) == 'u' && charAt(this.bp + i3 + 1) == 'l' && charAt(this.bp + i3 + 2) == 'l') {
            this.matchStat = 5;
            int i6 = i3 + 3;
            int i7 = i6 + 1;
            char cCharAt4 = charAt(this.bp + i6);
            if (z && cCharAt4 == '\"') {
                cCharAt4 = charAt(this.bp + i7);
                i7++;
            }
            while (cCharAt4 != ',') {
                if (cCharAt4 == '}') {
                    this.bp += i7;
                    this.ch = charAt(this.bp);
                    this.matchStat = 5;
                    this.token = 13;
                    return null;
                }
                if (isWhitespace(cCharAt4)) {
                    cCharAt4 = charAt(this.bp + i7);
                    i7++;
                } else {
                    this.matchStat = -1;
                    return null;
                }
            }
            this.bp += i7;
            this.ch = charAt(this.bp);
            this.matchStat = 5;
            this.token = 16;
            return null;
        }
        this.matchStat = -1;
        return null;
    }

    public Date scanFieldDate(char[] cArr) {
        char cCharAt;
        int i;
        long j;
        Date date;
        boolean z = false;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = cArr.length;
        int i2 = length + 1;
        char cCharAt2 = charAt(this.bp + length);
        if (cCharAt2 == '\"') {
            int iIndexOf = indexOf('\"', this.bp + cArr.length + 1);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            int length2 = this.bp + cArr.length + 1;
            String strSubString = subString(length2, iIndexOf - length2);
            if (strSubString.indexOf(92) != -1) {
                while (true) {
                    int i3 = 0;
                    for (int i4 = iIndexOf - 1; i4 >= 0 && charAt(i4) == '\\'; i4--) {
                        i3++;
                    }
                    if (i3 % 2 == 0) {
                        break;
                    }
                    iIndexOf = indexOf('\"', iIndexOf + 1);
                }
                int length3 = iIndexOf - ((this.bp + cArr.length) + 1);
                strSubString = readString(sub_chars(this.bp + cArr.length + 1, length3), length3);
            }
            int length4 = i2 + (iIndexOf - ((this.bp + cArr.length) + 1)) + 1;
            i = length4 + 1;
            cCharAt = charAt(this.bp + length4);
            JSONScanner jSONScanner = new JSONScanner(strSubString);
            try {
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    date = jSONScanner.getCalendar().getTime();
                    jSONScanner.close();
                } else {
                    this.matchStat = -1;
                    jSONScanner.close();
                    return null;
                }
            } catch (Throwable th) {
                jSONScanner.close();
                throw th;
            }
        } else {
            if (cCharAt2 != '-' && (cCharAt2 < '0' || cCharAt2 > '9')) {
                this.matchStat = -1;
                return null;
            }
            if (cCharAt2 == '-') {
                cCharAt2 = charAt(this.bp + i2);
                i2++;
                z = true;
            }
            if (cCharAt2 < '0' || cCharAt2 > '9') {
                cCharAt = cCharAt2;
                i = i2;
                j = 0;
            } else {
                j = cCharAt2 - '0';
                while (true) {
                    i = i2 + 1;
                    cCharAt = charAt(this.bp + i2);
                    if (cCharAt < '0' || cCharAt > '9') {
                        break;
                    }
                    j = (j * 10) + ((long) (cCharAt - '0'));
                    i2 = i;
                }
            }
            if (j < 0) {
                this.matchStat = -1;
                return null;
            }
            if (z) {
                j = -j;
            }
            date = new Date(j);
        }
        if (cCharAt == ',') {
            this.bp += i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            return date;
        }
        if (cCharAt == '}') {
            int i5 = i + 1;
            char cCharAt3 = charAt(this.bp + i);
            if (cCharAt3 == ',') {
                this.token = 16;
                this.bp += i5;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == ']') {
                this.token = 15;
                this.bp += i5;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == '}') {
                this.token = 13;
                this.bp += i5;
                this.ch = charAt(this.bp);
            } else if (cCharAt3 == 26) {
                this.token = 20;
                this.bp += i5 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return date;
        }
        this.matchStat = -1;
        return null;
    }

    public Date scanDate(char c) {
        long j;
        int i;
        Date date;
        boolean z = false;
        this.matchStat = 0;
        char cCharAt = charAt(this.bp + 0);
        if (cCharAt == '\"') {
            int iIndexOf = indexOf('\"', this.bp + 1);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            int i2 = this.bp + 1;
            String strSubString = subString(i2, iIndexOf - i2);
            if (strSubString.indexOf(92) != -1) {
                while (true) {
                    int i3 = 0;
                    for (int i4 = iIndexOf - 1; i4 >= 0 && charAt(i4) == '\\'; i4--) {
                        i3++;
                    }
                    if (i3 % 2 == 0) {
                        break;
                    }
                    iIndexOf = indexOf('\"', iIndexOf + 1);
                }
                int i5 = iIndexOf - (this.bp + 1);
                strSubString = readString(sub_chars(this.bp + 1, i5), i5);
            }
            int i6 = (iIndexOf - (this.bp + 1)) + 1 + 1;
            int i7 = i6 + 1;
            cCharAt = charAt(this.bp + i6);
            JSONScanner jSONScanner = new JSONScanner(strSubString);
            try {
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    date = jSONScanner.getCalendar().getTime();
                    jSONScanner.close();
                    i = i7;
                } else {
                    this.matchStat = -1;
                    jSONScanner.close();
                    return null;
                }
            } catch (Throwable th) {
                jSONScanner.close();
                throw th;
            }
        } else {
            char c2 = '9';
            int i8 = 2;
            if (cCharAt == '-' || (cCharAt >= '0' && cCharAt <= '9')) {
                if (cCharAt == '-') {
                    cCharAt = charAt(this.bp + 1);
                    z = true;
                } else {
                    i8 = 1;
                }
                if (cCharAt < '0' || cCharAt > '9') {
                    j = 0;
                    i = i8;
                } else {
                    j = cCharAt - '0';
                    while (true) {
                        i = i8 + 1;
                        cCharAt = charAt(this.bp + i8);
                        if (cCharAt < '0' || cCharAt > c2) {
                            break;
                        }
                        j = (j * 10) + ((long) (cCharAt - '0'));
                        i8 = i;
                        c2 = '9';
                    }
                }
                if (j < 0) {
                    this.matchStat = -1;
                    return null;
                }
                if (z) {
                    j = -j;
                }
                date = new Date(j);
            } else if (cCharAt == 'n' && charAt(this.bp + 1) == 'u' && charAt(this.bp + 1 + 1) == 'l' && charAt(this.bp + 1 + 2) == 'l') {
                this.matchStat = 5;
                cCharAt = charAt(this.bp + 4);
                i = 5;
                date = null;
            } else {
                this.matchStat = -1;
                return null;
            }
        }
        if (cCharAt == ',') {
            this.bp += i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return date;
        }
        if (cCharAt == ']') {
            int i9 = i + 1;
            char cCharAt2 = charAt(this.bp + i);
            if (cCharAt2 == ',') {
                this.token = 16;
                this.bp += i9;
                this.ch = charAt(this.bp);
            } else if (cCharAt2 == ']') {
                this.token = 15;
                this.bp += i9;
                this.ch = charAt(this.bp);
            } else if (cCharAt2 == '}') {
                this.token = 13;
                this.bp += i9;
                this.ch = charAt(this.bp);
            } else if (cCharAt2 == 26) {
                this.token = 20;
                this.bp += i9 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return date;
        }
        this.matchStat = -1;
        return null;
    }

    public UUID scanFieldUUID(char[] cArr) {
        int i;
        char cCharAt;
        UUID uuid;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = cArr.length;
        int i9 = length + 1;
        char cCharAt2 = charAt(this.bp + length);
        char c = 4;
        if (cCharAt2 == '\"') {
            int iIndexOf = indexOf('\"', this.bp + cArr.length + 1);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            int length2 = this.bp + cArr.length + 1;
            int i10 = iIndexOf - length2;
            char c2 = 'F';
            char c3 = 'f';
            char c4 = 'A';
            char c5 = 'a';
            char c6 = '0';
            if (i10 == 36) {
                int i11 = 0;
                long j = 0;
                while (i11 < 8) {
                    char cCharAt3 = charAt(length2 + i11);
                    if (cCharAt3 >= '0' && cCharAt3 <= '9') {
                        i8 = cCharAt3 - '0';
                    } else if (cCharAt3 >= 'a' && cCharAt3 <= 'f') {
                        i8 = (cCharAt3 - 'a') + 10;
                    } else {
                        if (cCharAt3 < c4 || cCharAt3 > c2) {
                            this.matchStat = -2;
                            return null;
                        }
                        i8 = (cCharAt3 - 'A') + 10;
                    }
                    j = (j << 4) | ((long) i8);
                    i11++;
                    c4 = 'A';
                    c2 = 'F';
                }
                int i12 = 9;
                while (i12 < 13) {
                    char cCharAt4 = charAt(length2 + i12);
                    if (cCharAt4 >= '0' && cCharAt4 <= '9') {
                        i7 = cCharAt4 - '0';
                    } else if (cCharAt4 >= 'a' && cCharAt4 <= c3) {
                        i7 = (cCharAt4 - 'a') + 10;
                    } else {
                        if (cCharAt4 < 'A' || cCharAt4 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i7 = (cCharAt4 - 'A') + 10;
                    }
                    j = (j << 4) | ((long) i7);
                    i12++;
                    iIndexOf = iIndexOf;
                    c3 = 'f';
                }
                int i13 = iIndexOf;
                long j2 = j;
                for (int i14 = 14; i14 < 18; i14++) {
                    char cCharAt5 = charAt(length2 + i14);
                    if (cCharAt5 >= '0' && cCharAt5 <= '9') {
                        i6 = cCharAt5 - '0';
                    } else if (cCharAt5 >= 'a' && cCharAt5 <= 'f') {
                        i6 = (cCharAt5 - 'a') + 10;
                    } else {
                        if (cCharAt5 < 'A' || cCharAt5 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i6 = (cCharAt5 - 'A') + 10;
                    }
                    j2 = (j2 << 4) | ((long) i6);
                }
                int i15 = 19;
                long j3 = 0;
                while (i15 < 23) {
                    char cCharAt6 = charAt(length2 + i15);
                    if (cCharAt6 >= '0' && cCharAt6 <= '9') {
                        i5 = cCharAt6 - '0';
                    } else if (cCharAt6 >= 'a' && cCharAt6 <= 'f') {
                        i5 = (cCharAt6 - 'a') + 10;
                    } else {
                        if (cCharAt6 < 'A' || cCharAt6 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i5 = (cCharAt6 - 'A') + 10;
                    }
                    j3 = (j3 << c) | ((long) i5);
                    i15++;
                    j2 = j2;
                    c = 4;
                }
                long j4 = j2;
                long j5 = j3;
                for (int i16 = 24; i16 < 36; i16++) {
                    char cCharAt7 = charAt(length2 + i16);
                    if (cCharAt7 >= '0' && cCharAt7 <= '9') {
                        i4 = cCharAt7 - '0';
                    } else if (cCharAt7 >= 'a' && cCharAt7 <= 'f') {
                        i4 = (cCharAt7 - 'a') + 10;
                    } else {
                        if (cCharAt7 < 'A' || cCharAt7 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i4 = (cCharAt7 - 'A') + 10;
                    }
                    j5 = (j5 << 4) | ((long) i4);
                }
                uuid = new UUID(j4, j5);
                int length3 = i9 + (i13 - ((this.bp + cArr.length) + 1)) + 1;
                i = length3 + 1;
                cCharAt = charAt(this.bp + length3);
            } else if (i10 == 32) {
                int i17 = 0;
                long j6 = 0;
                for (int i18 = 16; i17 < i18; i18 = 16) {
                    char cCharAt8 = charAt(length2 + i17);
                    if (cCharAt8 >= '0' && cCharAt8 <= '9') {
                        i3 = cCharAt8 - '0';
                    } else if (cCharAt8 >= 'a' && cCharAt8 <= 'f') {
                        i3 = (cCharAt8 - 'a') + 10;
                    } else {
                        if (cCharAt8 < 'A' || cCharAt8 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i3 = (cCharAt8 - 'A') + 10;
                    }
                    j6 = (j6 << 4) | ((long) i3);
                    i17++;
                }
                int i19 = 16;
                long j7 = 0;
                while (i19 < 32) {
                    char cCharAt9 = charAt(length2 + i19);
                    if (cCharAt9 >= c6 && cCharAt9 <= '9') {
                        i2 = cCharAt9 - '0';
                    } else if (cCharAt9 >= c5 && cCharAt9 <= 'f') {
                        i2 = (cCharAt9 - 'a') + 10;
                    } else {
                        if (cCharAt9 < 'A' || cCharAt9 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i2 = (cCharAt9 - 'A') + 10;
                    }
                    j7 = (j7 << 4) | ((long) i2);
                    i19++;
                    c6 = '0';
                    c5 = 'a';
                }
                uuid = new UUID(j6, j7);
                int length4 = i9 + (iIndexOf - ((this.bp + cArr.length) + 1)) + 1;
                i = length4 + 1;
                cCharAt = charAt(this.bp + length4);
            } else {
                this.matchStat = -1;
                return null;
            }
        } else {
            if (cCharAt2 == 'n') {
                int i20 = i9 + 1;
                if (charAt(this.bp + i9) == 'u') {
                    int i21 = i20 + 1;
                    if (charAt(this.bp + i20) == 'l') {
                        int i22 = i21 + 1;
                        if (charAt(this.bp + i21) == 'l') {
                            i = i22 + 1;
                            cCharAt = charAt(this.bp + i22);
                            uuid = null;
                        }
                    }
                }
            }
            this.matchStat = -1;
            return null;
        }
        if (cCharAt == ',') {
            this.bp += i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            return uuid;
        }
        if (cCharAt == '}') {
            int i23 = i + 1;
            char cCharAt10 = charAt(this.bp + i);
            if (cCharAt10 == ',') {
                this.token = 16;
                this.bp += i23;
                this.ch = charAt(this.bp);
            } else if (cCharAt10 == ']') {
                this.token = 15;
                this.bp += i23;
                this.ch = charAt(this.bp);
            } else if (cCharAt10 == '}') {
                this.token = 13;
                this.bp += i23;
                this.ch = charAt(this.bp);
            } else if (cCharAt10 == 26) {
                this.token = 20;
                this.bp += i23 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return uuid;
        }
        this.matchStat = -1;
        return null;
    }

    public UUID scanUUID(char c) {
        int i;
        char cCharAt;
        UUID uuid;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        this.matchStat = 0;
        char cCharAt2 = charAt(this.bp + 0);
        char c2 = 4;
        if (cCharAt2 == '\"') {
            int iIndexOf = indexOf('\"', this.bp + 1);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            int i9 = this.bp + 1;
            int i10 = iIndexOf - i9;
            char c3 = 'F';
            char c4 = 'f';
            char c5 = '9';
            char c6 = 'A';
            char c7 = 'a';
            char c8 = '0';
            if (i10 == 36) {
                int i11 = 0;
                long j = 0;
                while (i11 < 8) {
                    char cCharAt3 = charAt(i9 + i11);
                    if (cCharAt3 >= '0' && cCharAt3 <= '9') {
                        i8 = cCharAt3 - '0';
                    } else if (cCharAt3 >= 'a' && cCharAt3 <= c4) {
                        i8 = (cCharAt3 - 'a') + 10;
                    } else {
                        if (cCharAt3 < 'A' || cCharAt3 > c3) {
                            this.matchStat = -2;
                            return null;
                        }
                        i8 = (cCharAt3 - 'A') + 10;
                    }
                    j = (j << 4) | ((long) i8);
                    i11++;
                    c3 = 'F';
                    c4 = 'f';
                }
                int i12 = 9;
                while (i12 < 13) {
                    char cCharAt4 = charAt(i9 + i12);
                    if (cCharAt4 >= '0' && cCharAt4 <= '9') {
                        i7 = cCharAt4 - '0';
                    } else if (cCharAt4 >= c7 && cCharAt4 <= 'f') {
                        i7 = (cCharAt4 - 'a') + 10;
                    } else {
                        if (cCharAt4 < c6 || cCharAt4 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i7 = (cCharAt4 - 'A') + 10;
                    }
                    j = (j << 4) | ((long) i7);
                    i12++;
                    c6 = 'A';
                    c7 = 'a';
                }
                long j2 = j;
                for (int i13 = 14; i13 < 18; i13++) {
                    char cCharAt5 = charAt(i9 + i13);
                    if (cCharAt5 >= '0' && cCharAt5 <= '9') {
                        i6 = cCharAt5 - '0';
                    } else if (cCharAt5 >= 'a' && cCharAt5 <= 'f') {
                        i6 = (cCharAt5 - 'a') + 10;
                    } else {
                        if (cCharAt5 < 'A' || cCharAt5 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i6 = (cCharAt5 - 'A') + 10;
                    }
                    j2 = (j2 << 4) | ((long) i6);
                }
                int i14 = 19;
                long j3 = 0;
                while (i14 < 23) {
                    char cCharAt6 = charAt(i9 + i14);
                    if (cCharAt6 >= '0' && cCharAt6 <= c5) {
                        i5 = cCharAt6 - '0';
                    } else if (cCharAt6 >= 'a' && cCharAt6 <= 'f') {
                        i5 = (cCharAt6 - 'a') + 10;
                    } else {
                        if (cCharAt6 < 'A' || cCharAt6 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i5 = (cCharAt6 - 'A') + 10;
                    }
                    j3 = (j3 << 4) | ((long) i5);
                    i14++;
                    iIndexOf = iIndexOf;
                    c5 = '9';
                }
                int i15 = iIndexOf;
                int i16 = 24;
                long j4 = j3;
                while (i16 < 36) {
                    char cCharAt7 = charAt(i9 + i16);
                    if (cCharAt7 >= c8 && cCharAt7 <= '9') {
                        i4 = cCharAt7 - '0';
                    } else if (cCharAt7 >= 'a' && cCharAt7 <= 'f') {
                        i4 = (cCharAt7 - 'a') + 10;
                    } else {
                        if (cCharAt7 < 'A' || cCharAt7 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i4 = (cCharAt7 - 'A') + 10;
                    }
                    j4 = (j4 << c2) | ((long) i4);
                    i16++;
                    c8 = '0';
                    c2 = 4;
                }
                uuid = new UUID(j2, j4);
                int i17 = (i15 - (this.bp + 1)) + 1 + 1;
                i = i17 + 1;
                cCharAt = charAt(this.bp + i17);
            } else if (i10 == 32) {
                long j5 = 0;
                for (int i18 = 0; i18 < 16; i18++) {
                    char cCharAt8 = charAt(i9 + i18);
                    if (cCharAt8 >= '0' && cCharAt8 <= '9') {
                        i3 = cCharAt8 - '0';
                    } else if (cCharAt8 >= 'a' && cCharAt8 <= 'f') {
                        i3 = (cCharAt8 - 'a') + 10;
                    } else {
                        if (cCharAt8 < 'A' || cCharAt8 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i3 = (cCharAt8 - 'A') + 10;
                    }
                    j5 = (j5 << 4) | ((long) i3);
                }
                long j6 = 0;
                for (int i19 = 16; i19 < 32; i19++) {
                    char cCharAt9 = charAt(i9 + i19);
                    if (cCharAt9 >= '0' && cCharAt9 <= '9') {
                        i2 = cCharAt9 - '0';
                    } else if (cCharAt9 >= 'a' && cCharAt9 <= 'f') {
                        i2 = (cCharAt9 - 'a') + 10;
                    } else {
                        if (cCharAt9 < 'A' || cCharAt9 > 'F') {
                            this.matchStat = -2;
                            return null;
                        }
                        i2 = (cCharAt9 - 'A') + 10;
                    }
                    j6 = (j6 << 4) | ((long) i2);
                }
                uuid = new UUID(j5, j6);
                int i20 = (iIndexOf - (this.bp + 1)) + 1 + 1;
                i = i20 + 1;
                cCharAt = charAt(this.bp + i20);
            } else {
                this.matchStat = -1;
                return null;
            }
        } else if (cCharAt2 == 'n' && charAt(this.bp + 1) == 'u' && charAt(this.bp + 2) == 'l' && charAt(this.bp + 3) == 'l') {
            i = 5;
            cCharAt = charAt(this.bp + 4);
            uuid = null;
        } else {
            this.matchStat = -1;
            return null;
        }
        if (cCharAt == ',') {
            this.bp += i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            return uuid;
        }
        if (cCharAt == ']') {
            int i21 = i + 1;
            char cCharAt10 = charAt(this.bp + i);
            if (cCharAt10 == ',') {
                this.token = 16;
                this.bp += i21;
                this.ch = charAt(this.bp);
            } else if (cCharAt10 == ']') {
                this.token = 15;
                this.bp += i21;
                this.ch = charAt(this.bp);
            } else if (cCharAt10 == '}') {
                this.token = 13;
                this.bp += i21;
                this.ch = charAt(this.bp);
            } else if (cCharAt10 == 26) {
                this.token = 20;
                this.bp += i21 - 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return uuid;
        }
        this.matchStat = -1;
        return null;
    }

    public final void scanTrue() {
        if (this.ch != 't') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch != 'r') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch != 'u') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch != 'e') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.ch == ' ' || this.ch == ',' || this.ch == '}' || this.ch == ']' || this.ch == '\n' || this.ch == '\r' || this.ch == '\t' || this.ch == 26 || this.ch == '\f' || this.ch == '\b' || this.ch == ':' || this.ch == '/') {
            this.token = 6;
            return;
        }
        throw new JSONException("scan true error");
    }

    public final void scanNullOrNew() {
        if (this.ch != 'n') {
            throw new JSONException("error parse null or new");
        }
        next();
        if (this.ch == 'u') {
            next();
            if (this.ch != 'l') {
                throw new JSONException("error parse null");
            }
            next();
            if (this.ch != 'l') {
                throw new JSONException("error parse null");
            }
            next();
            if (this.ch == ' ' || this.ch == ',' || this.ch == '}' || this.ch == ']' || this.ch == '\n' || this.ch == '\r' || this.ch == '\t' || this.ch == 26 || this.ch == '\f' || this.ch == '\b') {
                this.token = 8;
                return;
            }
            throw new JSONException("scan null error");
        }
        if (this.ch != 'e') {
            throw new JSONException("error parse new");
        }
        next();
        if (this.ch != 'w') {
            throw new JSONException("error parse new");
        }
        next();
        if (this.ch == ' ' || this.ch == ',' || this.ch == '}' || this.ch == ']' || this.ch == '\n' || this.ch == '\r' || this.ch == '\t' || this.ch == 26 || this.ch == '\f' || this.ch == '\b') {
            this.token = 9;
            return;
        }
        throw new JSONException("scan new error");
    }

    public final void scanFalse() {
        if (this.ch != 'f') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 'a') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 'l') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 's') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch != 'e') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.ch == ' ' || this.ch == ',' || this.ch == '}' || this.ch == ']' || this.ch == '\n' || this.ch == '\r' || this.ch == '\t' || this.ch == 26 || this.ch == '\f' || this.ch == '\b' || this.ch == ':' || this.ch == '/') {
            this.token = 7;
            return;
        }
        throw new JSONException("scan false error");
    }

    public final void scanIdent() {
        this.np = this.bp - 1;
        this.hasSpecial = false;
        do {
            this.sp++;
            next();
        } while (Character.isLetterOrDigit(this.ch));
        String strStringVal = stringVal();
        if ("null".equalsIgnoreCase(strStringVal)) {
            this.token = 8;
            return;
        }
        if ("new".equals(strStringVal)) {
            this.token = 9;
            return;
        }
        if ("true".equals(strStringVal)) {
            this.token = 6;
            return;
        }
        if ("false".equals(strStringVal)) {
            this.token = 7;
            return;
        }
        if ("undefined".equals(strStringVal)) {
            this.token = 23;
            return;
        }
        if ("Set".equals(strStringVal)) {
            this.token = 21;
        } else if ("TreeSet".equals(strStringVal)) {
            this.token = 22;
        } else {
            this.token = 18;
        }
    }

    public static String readString(char[] cArr, int i) {
        int i2;
        char[] cArr2 = new char[i];
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            char c = cArr[i3];
            if (c != '\\') {
                cArr2[i4] = c;
                i4++;
            } else {
                i3++;
                char c2 = cArr[i3];
                switch (c2) {
                    case '/':
                        i2 = i4 + 1;
                        cArr2[i4] = '/';
                        break;
                    case '0':
                        i2 = i4 + 1;
                        cArr2[i4] = 0;
                        break;
                    case '1':
                        i2 = i4 + 1;
                        cArr2[i4] = 1;
                        break;
                    case '2':
                        i2 = i4 + 1;
                        cArr2[i4] = 2;
                        break;
                    case '3':
                        i2 = i4 + 1;
                        cArr2[i4] = 3;
                        break;
                    case '4':
                        i2 = i4 + 1;
                        cArr2[i4] = 4;
                        break;
                    case '5':
                        i2 = i4 + 1;
                        cArr2[i4] = 5;
                        break;
                    case '6':
                        i2 = i4 + 1;
                        cArr2[i4] = 6;
                        break;
                    case '7':
                        i2 = i4 + 1;
                        cArr2[i4] = 7;
                        break;
                    default:
                        switch (c2) {
                            case 't':
                                i2 = i4 + 1;
                                cArr2[i4] = '\t';
                                break;
                            case 'u':
                                i2 = i4 + 1;
                                int i5 = i3 + 1;
                                int i6 = i5 + 1;
                                int i7 = i6 + 1;
                                i3 = i7 + 1;
                                cArr2[i4] = (char) Integer.parseInt(new String(new char[]{cArr[i5], cArr[i6], cArr[i7], cArr[i3]}), 16);
                                break;
                            case 'v':
                                i2 = i4 + 1;
                                cArr2[i4] = 11;
                                break;
                            default:
                                switch (c2) {
                                    case '\"':
                                        i2 = i4 + 1;
                                        cArr2[i4] = '\"';
                                        break;
                                    case '\'':
                                        i2 = i4 + 1;
                                        cArr2[i4] = '\'';
                                        break;
                                    case 'F':
                                    case 'f':
                                        i2 = i4 + 1;
                                        cArr2[i4] = '\f';
                                        break;
                                    case '\\':
                                        i2 = i4 + 1;
                                        cArr2[i4] = '\\';
                                        break;
                                    case 'b':
                                        i2 = i4 + 1;
                                        cArr2[i4] = '\b';
                                        break;
                                    case 'n':
                                        i2 = i4 + 1;
                                        cArr2[i4] = '\n';
                                        break;
                                    case 'r':
                                        i2 = i4 + 1;
                                        cArr2[i4] = '\r';
                                        break;
                                    case 'x':
                                        i2 = i4 + 1;
                                        int i8 = i3 + 1;
                                        int i9 = digits[cArr[i8]] * 16;
                                        i3 = i8 + 1;
                                        cArr2[i4] = (char) (i9 + digits[cArr[i3]]);
                                        break;
                                    default:
                                        throw new JSONException("unclosed.str.lit");
                                }
                                break;
                        }
                        break;
                }
                i4 = i2;
            }
            i3++;
        }
        return new String(cArr2, 0, i4);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public boolean isBlankInput() {
        int i = 0;
        while (true) {
            char cCharAt = charAt(i);
            if (cCharAt == 26) {
                this.token = 20;
                return true;
            }
            if (!isWhitespace(cCharAt)) {
                return false;
            }
            i++;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void skipWhitespace() {
        while (this.ch <= '/') {
            if (this.ch == ' ' || this.ch == '\r' || this.ch == '\n' || this.ch == '\t' || this.ch == '\f' || this.ch == '\b') {
                next();
            } else if (this.ch != '/') {
                return;
            } else {
                skipComment();
            }
        }
    }

    private void scanStringSingleQuote() {
        this.np = this.bp;
        this.hasSpecial = false;
        while (true) {
            char next = next();
            if (next == '\'') {
                this.token = 4;
                next();
                return;
            }
            if (next == 26) {
                if (!isEOF()) {
                    putChar(JSONLexer.EOI);
                } else {
                    throw new JSONException("unclosed single-quote string");
                }
            } else if (next == '\\') {
                if (!this.hasSpecial) {
                    this.hasSpecial = true;
                    if (this.sp > this.sbuf.length) {
                        char[] cArr = new char[this.sp * 2];
                        System.arraycopy(this.sbuf, 0, cArr, 0, this.sbuf.length);
                        this.sbuf = cArr;
                    }
                    copyTo(this.np + 1, this.sp, this.sbuf);
                }
                char next2 = next();
                switch (next2) {
                    case '/':
                        putChar('/');
                        break;
                    case '0':
                        putChar((char) 0);
                        break;
                    case '1':
                        putChar((char) 1);
                        break;
                    case '2':
                        putChar((char) 2);
                        break;
                    case '3':
                        putChar((char) 3);
                        break;
                    case '4':
                        putChar((char) 4);
                        break;
                    case '5':
                        putChar((char) 5);
                        break;
                    case '6':
                        putChar((char) 6);
                        break;
                    case '7':
                        putChar((char) 7);
                        break;
                    default:
                        switch (next2) {
                            case 't':
                                putChar('\t');
                                break;
                            case 'u':
                                putChar((char) Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16));
                                break;
                            case 'v':
                                putChar((char) 11);
                                break;
                            default:
                                switch (next2) {
                                    case '\"':
                                        putChar('\"');
                                        break;
                                    case '\'':
                                        putChar('\'');
                                        break;
                                    case 'F':
                                    case 'f':
                                        putChar('\f');
                                        break;
                                    case '\\':
                                        putChar('\\');
                                        break;
                                    case 'b':
                                        putChar('\b');
                                        break;
                                    case 'n':
                                        putChar('\n');
                                        break;
                                    case 'r':
                                        putChar('\r');
                                        break;
                                    case 'x':
                                        putChar((char) ((digits[next()] * 16) + digits[next()]));
                                        break;
                                    default:
                                        this.ch = next2;
                                        throw new JSONException("unclosed single-quote string");
                                }
                                break;
                        }
                        break;
                }
            } else if (!this.hasSpecial) {
                this.sp++;
            } else if (this.sp == this.sbuf.length) {
                putChar(next);
            } else {
                char[] cArr2 = this.sbuf;
                int i = this.sp;
                this.sp = i + 1;
                cArr2[i] = next;
            }
        }
    }

    protected final void putChar(char c) {
        if (this.sp == this.sbuf.length) {
            char[] cArr = new char[this.sbuf.length * 2];
            System.arraycopy(this.sbuf, 0, cArr, 0, this.sbuf.length);
            this.sbuf = cArr;
        }
        char[] cArr2 = this.sbuf;
        int i = this.sp;
        this.sp = i + 1;
        cArr2[i] = c;
    }

    public final void scanHex() {
        char next;
        if (this.ch != 'x') {
            throw new JSONException("illegal state. " + this.ch);
        }
        next();
        if (this.ch != '\'') {
            throw new JSONException("illegal state. " + this.ch);
        }
        this.np = this.bp;
        next();
        if (this.ch == '\'') {
            next();
            this.token = 26;
            return;
        }
        while (true) {
            next = next();
            if ((next < '0' || next > '9') && (next < 'A' || next > 'F')) {
                break;
            } else {
                this.sp++;
            }
        }
        if (next == '\'') {
            this.sp++;
            next();
            this.token = 26;
        } else {
            throw new JSONException("illegal state. " + next);
        }
    }

    /* JADX WARN: Code duplicated, block: B:56:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:57:0x00e4  */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final void scanNumber() {
        this.np = this.bp;
        boolean z = true;
        if (this.ch == '-') {
            this.sp++;
            next();
        }
        while (this.ch >= '0' && this.ch <= '9') {
            this.sp++;
            next();
        }
        boolean z2 = false;
        if (this.ch == '.') {
            this.sp++;
            next();
            while (this.ch >= '0' && this.ch <= '9') {
                this.sp++;
                next();
            }
            z2 = true;
        }
        if (this.ch == 'L' || this.ch == 'S' || this.ch == 'B') {
            this.sp++;
            next();
        } else {
            if (this.ch == 'F' || this.ch == 'D') {
                this.sp++;
                next();
            } else if (this.ch == 'e' || this.ch == 'E') {
                this.sp++;
                next();
                if (this.ch == '+' || this.ch == '-') {
                    this.sp++;
                    next();
                }
                while (this.ch >= '0' && this.ch <= '9') {
                    this.sp++;
                    next();
                }
                if (this.ch == 'D' || this.ch == 'F') {
                    this.sp++;
                    next();
                }
            }
            if (z) {
                this.token = 3;
            } else {
                this.token = 2;
            }
        }
        z = z2;
        if (z) {
            this.token = 3;
        } else {
            this.token = 2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x003c  */
    /* JADX WARN: Code duplicated, block: B:33:0x0078  */
    /* JADX WARN: Code duplicated, block: B:35:0x007d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:36:0x007e  */
    /* JADX WARN: Code duplicated, block: B:38:0x0088  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x0073 -> B:12:0x0036). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final long longValue() throws NumberFormatException {
        long j;
        long j2;
        int i;
        char cCharAt;
        boolean z = false;
        if (this.np == -1) {
            this.np = 0;
        }
        int i2 = this.np;
        int i3 = this.np + this.sp;
        if (charAt(this.np) == '-') {
            j = Long.MIN_VALUE;
            i2++;
            z = true;
        } else {
            j = -9223372036854775807L;
        }
        if (i2 >= i3) {
            j2 = 0;
            if (i2 < i3) {
                i = i2 + 1;
                cCharAt = charAt(i2);
                if (cCharAt != 'L' || cCharAt == 'S' || cCharAt == 'B') {
                    i2 = i;
                } else {
                    int i4 = cCharAt - '0';
                    if (j2 < MULTMIN_RADIX_TEN) {
                        throw new NumberFormatException(numberString());
                    }
                    long j3 = j2 * 10;
                    long j4 = i4;
                    if (j3 < j + j4) {
                        throw new NumberFormatException(numberString());
                    }
                    j2 = j3 - j4;
                }
            }
            if (z) {
                return -j2;
            }
            if (i2 > this.np + 1) {
                return j2;
            }
            throw new NumberFormatException(numberString());
        }
        i = i2 + 1;
        j2 = -(charAt(i2) - '0');
        i2 = i;
        if (i2 < i3) {
            i = i2 + 1;
            cCharAt = charAt(i2);
            if (cCharAt != 'L') {
            }
            i2 = i;
        }
        if (z) {
            return -j2;
        }
        if (i2 > this.np + 1) {
            return j2;
        }
        throw new NumberFormatException(numberString());
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public final Number decimalValue(boolean z) {
        char cCharAt = charAt((this.np + this.sp) - 1);
        try {
            if (cCharAt == 'F') {
                return Float.valueOf(Float.parseFloat(numberString()));
            }
            if (cCharAt == 'D') {
                return Double.valueOf(Double.parseDouble(numberString()));
            }
            if (z) {
                return decimalValue();
            }
            return Double.valueOf(doubleValue());
        } catch (NumberFormatException e) {
            throw new JSONException(e.getMessage() + ", " + info());
        }
    }

    public String[] scanFieldStringArray(char[] cArr, int i, SymbolTable symbolTable) {
        throw new UnsupportedOperationException();
    }

    public boolean matchField2(char[] cArr) {
        throw new UnsupportedOperationException();
    }

    @Override // com.alibaba.fastjson.parser.JSONLexer
    public int getFeatures() {
        return this.features;
    }
}
