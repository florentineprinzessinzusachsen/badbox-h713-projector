package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.IOUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public final class JSONScanner extends JSONLexerBase {
    private final int len;
    private final String text;

    static boolean checkDate(char c, char c2, char c3, char c4, char c5, char c6, int i, int i2) {
        if (c < '0' || c > '9' || c2 < '0' || c2 > '9' || c3 < '0' || c3 > '9' || c4 < '0' || c4 > '9') {
            return false;
        }
        if (c5 == '0') {
            if (c6 < '1' || c6 > '9') {
                return false;
            }
        } else {
            if (c5 != '1') {
                return false;
            }
            if (c6 != '0' && c6 != '1' && c6 != '2') {
                return false;
            }
        }
        if (i == 48) {
            return i2 >= 49 && i2 <= 57;
        }
        if (i == 49 || i == 50) {
            return i2 >= 48 && i2 <= 57;
        }
        if (i == 51) {
            return i2 == 48 || i2 == 49;
        }
        return false;
    }

    private boolean checkTime(char c, char c2, char c3, char c4, char c5, char c6) {
        if (c == '0') {
            if (c2 < '0' || c2 > '9') {
                return false;
            }
        } else if (c == '1') {
            if (c2 < '0' || c2 > '9') {
                return false;
            }
        } else if (c != '2' || c2 < '0' || c2 > '4') {
            return false;
        }
        if (c3 < '0' || c3 > '5') {
            if (c3 != '6' || c4 != '0') {
                return false;
            }
        } else if (c4 < '0' || c4 > '9') {
            return false;
        }
        if (c5 < '0' || c5 > '5') {
            return c5 == '6' && c6 == '0';
        }
        return c6 >= '0' && c6 <= '9';
    }

    public JSONScanner(String str) {
        this(str, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(String str, int i) {
        super(i);
        this.text = str;
        this.len = this.text.length();
        this.bp = -1;
        next();
        if (this.ch == 65279) {
            next();
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final char charAt(int i) {
        return i >= this.len ? JSONLexer.EOI : this.text.charAt(i);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final char next() {
        int i = this.bp + 1;
        this.bp = i;
        char cCharAt = i >= this.len ? JSONLexer.EOI : this.text.charAt(i);
        this.ch = cCharAt;
        return cCharAt;
    }

    public JSONScanner(char[] cArr, int i) {
        this(cArr, i, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(char[] cArr, int i, int i2) {
        this(new String(cArr, 0, i), i2);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    protected final void copyTo(int i, int i2, char[] cArr) {
        this.text.getChars(i, i2 + i, cArr, 0);
    }

    static boolean charArrayCompare(String str, int i, char[] cArr) {
        int length = cArr.length;
        if (length + i > str.length()) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (cArr[i2] != str.charAt(i + i2)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final boolean charArrayCompare(char[] cArr) {
        return charArrayCompare(this.text, this.bp, cArr);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final int indexOf(char c, int i) {
        return this.text.indexOf(c, i);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final String addSymbol(int i, int i2, int i3, SymbolTable symbolTable) {
        return symbolTable.addSymbol(this.text, i, i2, i3);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public byte[] bytesValue() {
        if (this.token == 26) {
            int i = this.np + 1;
            int i2 = this.sp;
            if (i2 % 2 != 0) {
                throw new JSONException("illegal state. " + i2);
            }
            byte[] bArr = new byte[i2 / 2];
            for (int i3 = 0; i3 < bArr.length; i3++) {
                int i4 = (i3 * 2) + i;
                char cCharAt = this.text.charAt(i4);
                char cCharAt2 = this.text.charAt(i4 + 1);
                char c = '7';
                int i5 = cCharAt - (cCharAt <= '9' ? '0' : '7');
                if (cCharAt2 <= '9') {
                    c = '0';
                }
                bArr[i3] = (byte) ((i5 << 4) | (cCharAt2 - c));
            }
            return bArr;
        }
        return IOUtils.decodeBase64(this.text, this.np + 1, this.sp);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final String stringVal() {
        if (!this.hasSpecial) {
            return subString(this.np + 1, this.sp);
        }
        return new String(this.sbuf, 0, this.sp);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final String subString(int i, int i2) {
        if (ASMUtils.IS_ANDROID) {
            if (i2 < this.sbuf.length) {
                this.text.getChars(i, i + i2, this.sbuf, 0);
                return new String(this.sbuf, 0, i2);
            }
            char[] cArr = new char[i2];
            this.text.getChars(i, i2 + i, cArr, 0);
            return new String(cArr);
        }
        return this.text.substring(i, i2 + i);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final char[] sub_chars(int i, int i2) {
        if (ASMUtils.IS_ANDROID && i2 < this.sbuf.length) {
            this.text.getChars(i, i2 + i, this.sbuf, 0);
            return this.sbuf;
        }
        char[] cArr = new char[i2];
        this.text.getChars(i, i2 + i, cArr, 0);
        return cArr;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final String numberString() {
        char cCharAt = charAt((this.np + this.sp) - 1);
        int i = this.sp;
        if (cCharAt == 'L' || cCharAt == 'S' || cCharAt == 'B' || cCharAt == 'F' || cCharAt == 'D') {
            i--;
        }
        return subString(this.np, i);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final BigDecimal decimalValue() {
        char cCharAt = charAt((this.np + this.sp) - 1);
        int i = this.sp;
        if (cCharAt == 'L' || cCharAt == 'S' || cCharAt == 'B' || cCharAt == 'F' || cCharAt == 'D') {
            i--;
        }
        int i2 = this.np;
        if (i < this.sbuf.length) {
            this.text.getChars(i2, i2 + i, this.sbuf, 0);
            return new BigDecimal(this.sbuf, 0, i);
        }
        char[] cArr = new char[i];
        this.text.getChars(i2, i + i2, cArr, 0);
        return new BigDecimal(cArr);
    }

    public boolean scanISO8601DateIfMatch() {
        return scanISO8601DateIfMatch(true);
    }

    public boolean scanISO8601DateIfMatch(boolean z) {
        return scanISO8601DateIfMatch(z, this.len - this.bp);
    }

    /* JADX WARN: Code duplicated, block: B:122:0x01b8  */
    /* JADX WARN: Code duplicated, block: B:136:0x0205 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:138:0x0207  */
    /* JADX WARN: Code duplicated, block: B:140:0x0223  */
    /* JADX WARN: Code duplicated, block: B:158:0x02b0  */
    /* JADX WARN: Code duplicated, block: B:197:0x0361 A[ADDED_TO_REGION, RETURN] */
    /* JADX WARN: Code duplicated, block: B:199:0x0363  */
    /* JADX WARN: Code duplicated, block: B:207:0x03c4 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:209:0x03c6  */
    /* JADX WARN: Code duplicated, block: B:211:0x03e1  */
    /* JADX WARN: Code duplicated, block: B:213:0x03e5 A[ADDED_TO_REGION, RETURN] */
    /* JADX WARN: Code duplicated, block: B:215:0x03e7  */
    /* JADX WARN: Code duplicated, block: B:222:0x03fc  */
    /* JADX WARN: Code duplicated, block: B:226:0x0410  */
    /* JADX WARN: Code duplicated, block: B:228:0x0414  */
    /* JADX WARN: Code duplicated, block: B:233:0x042c  */
    /* JADX WARN: Code duplicated, block: B:236:0x0430  */
    /* JADX WARN: Code duplicated, block: B:239:0x0447  */
    /* JADX WARN: Code duplicated, block: B:251:0x0475  */
    /* JADX WARN: Code duplicated, block: B:253:0x0485  */
    /* JADX WARN: Code duplicated, block: B:297:0x0541  */
    /* JADX WARN: Code duplicated, block: B:305:0x0558 A[ADDED_TO_REGION, ORIG_RETURN, RETURN] */
    /* JADX WARN: Code duplicated, block: B:307:0x055a  */
    /* JADX WARN: Code duplicated, block: B:51:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:58:0x0103 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:60:0x0105  */
    /* JADX WARN: Code duplicated, block: B:70:0x012e A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:78:0x014b  */
    /* JADX WARN: Code duplicated, block: B:80:0x014f  */
    /* JADX WARN: Code duplicated, block: B:82:0x0153 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:86:0x0168  */
    /* JADX WARN: Code duplicated, block: B:87:0x0170  */
    /* JADX WARN: Code duplicated, block: B:89:0x0174  */
    /* JADX WARN: Code duplicated, block: B:96:0x0180  */
    private boolean scanISO8601DateIfMatch(boolean z, int i) {
        int i2;
        boolean z2;
        char cCharAt;
        char c;
        char c2;
        char c3;
        int i3;
        int i4;
        int i5;
        int i6;
        char c4;
        char cCharAt2;
        char cCharAt3;
        char c5;
        char c6;
        int i7;
        char cCharAt4;
        int i8;
        int i9;
        char cCharAt5;
        char cCharAt6;
        char cCharAt7;
        char cCharAt8;
        char cCharAt9;
        char cCharAt10;
        char cCharAt11;
        int i10;
        int i11;
        char cCharAt12;
        char cCharAt13;
        char cCharAt14;
        int i12;
        char c7;
        char cCharAt15;
        char cCharAt16;
        char cCharAt17;
        int i13;
        char cCharAt18;
        int i14;
        int i15;
        char cCharAt19;
        char cCharAt20;
        if (i < 8) {
            return false;
        }
        char cCharAt21 = charAt(this.bp);
        char cCharAt22 = charAt(this.bp + 1);
        char cCharAt23 = charAt(this.bp + 2);
        char cCharAt24 = charAt(this.bp + 3);
        char cCharAt25 = charAt(this.bp + 4);
        char cCharAt26 = charAt(this.bp + 5);
        char cCharAt27 = charAt(this.bp + 6);
        cCharAt22 = charAt(this.bp + 7);
        if (!z && i > 13) {
            char cCharAt28 = charAt((this.bp + i) - 1);
            char cCharAt29 = charAt((this.bp + i) - 2);
            if (cCharAt21 == '/' && cCharAt22 == 'D' && cCharAt23 == 'a' && cCharAt24 == 't' && cCharAt25 == 'e' && cCharAt26 == '(' && cCharAt28 == '/' && cCharAt29 == ')') {
                int i16 = -1;
                for (int i17 = 6; i17 < i; i17++) {
                    char cCharAt30 = charAt(this.bp + i17);
                    if (cCharAt30 != '+') {
                        if (cCharAt30 < '0' || cCharAt30 > '9') {
                            break;
                        }
                    } else {
                        i16 = i17;
                    }
                }
                if (i16 == -1) {
                    return false;
                }
                int i18 = this.bp + 6;
                long j = Long.parseLong(subString(i18, (this.bp + i16) - i18));
                this.calendar = Calendar.getInstance(this.timeZone, this.locale);
                this.calendar.setTimeInMillis(j);
                this.token = 5;
                return true;
            }
        }
        if (i == 8 || i == 14) {
            i2 = 5;
            z2 = false;
        } else {
            if (i == 16) {
                char cCharAt31 = charAt(this.bp + 10);
                if (cCharAt31 == 'T') {
                    i2 = 5;
                    z2 = false;
                } else if (cCharAt31 != ' ') {
                    if (i == 17) {
                    }
                    if (i < 9) {
                        return false;
                    }
                    cCharAt22 = charAt(this.bp + 8);
                    cCharAt4 = charAt(this.bp + 9);
                    if (cCharAt25 == '-') {
                        if (cCharAt25 == '-') {
                            if (cCharAt23 != '.') {
                                if (cCharAt22 == 'T') {
                                    cCharAt26 = cCharAt25;
                                    cCharAt26 = cCharAt26;
                                    cCharAt22 = cCharAt27;
                                    cCharAt22 = cCharAt22;
                                    i8 = 8;
                                    cCharAt22 = cCharAt22;
                                } else {
                                    if (cCharAt25 == 24180) {
                                    }
                                    if (cCharAt22 == 26376) {
                                    }
                                    if (cCharAt4 != 26085) {
                                    }
                                    cCharAt22 = cCharAt22;
                                    cCharAt22 = '0';
                                    cCharAt26 = cCharAt27;
                                    i8 = 10;
                                }
                            } else if (cCharAt22 == 'T') {
                                cCharAt26 = cCharAt25;
                                cCharAt26 = cCharAt26;
                                cCharAt22 = cCharAt27;
                                cCharAt22 = cCharAt22;
                                i8 = 8;
                                cCharAt22 = cCharAt22;
                            } else {
                                if (cCharAt25 == 24180) {
                                }
                                if (cCharAt22 == 26376) {
                                }
                                if (cCharAt4 != 26085) {
                                }
                                cCharAt22 = cCharAt22;
                                cCharAt22 = '0';
                                cCharAt26 = cCharAt27;
                                i8 = 10;
                            }
                        } else if (cCharAt23 != '.') {
                            if (cCharAt22 == 'T') {
                                cCharAt26 = cCharAt25;
                                cCharAt26 = cCharAt26;
                                cCharAt22 = cCharAt27;
                                cCharAt22 = cCharAt22;
                                i8 = 8;
                                cCharAt22 = cCharAt22;
                            } else {
                                if (cCharAt25 == 24180) {
                                }
                                if (cCharAt22 == 26376) {
                                }
                                if (cCharAt4 != 26085) {
                                }
                                cCharAt22 = cCharAt22;
                                cCharAt22 = '0';
                                cCharAt26 = cCharAt27;
                                i8 = 10;
                            }
                        } else if (cCharAt22 == 'T') {
                            cCharAt26 = cCharAt25;
                            cCharAt26 = cCharAt26;
                            cCharAt22 = cCharAt27;
                            cCharAt22 = cCharAt22;
                            i8 = 8;
                            cCharAt22 = cCharAt22;
                        } else {
                            if (cCharAt25 == 24180) {
                            }
                            if (cCharAt22 == 26376) {
                            }
                            if (cCharAt4 != 26085) {
                            }
                            cCharAt22 = cCharAt22;
                            cCharAt22 = '0';
                            cCharAt26 = cCharAt27;
                            i8 = 10;
                        }
                    } else if (cCharAt25 == '-') {
                        if (cCharAt23 != '.') {
                            if (cCharAt22 == 'T') {
                                cCharAt26 = cCharAt25;
                                cCharAt26 = cCharAt26;
                                cCharAt22 = cCharAt27;
                                cCharAt22 = cCharAt22;
                                i8 = 8;
                                cCharAt22 = cCharAt22;
                            } else {
                                if (cCharAt25 == 24180) {
                                }
                                if (cCharAt22 == 26376) {
                                }
                                if (cCharAt4 != 26085) {
                                }
                                cCharAt22 = cCharAt22;
                                cCharAt22 = '0';
                                cCharAt26 = cCharAt27;
                                i8 = 10;
                            }
                        } else if (cCharAt22 == 'T') {
                            cCharAt26 = cCharAt25;
                            cCharAt26 = cCharAt26;
                            cCharAt22 = cCharAt27;
                            cCharAt22 = cCharAt22;
                            i8 = 8;
                            cCharAt22 = cCharAt22;
                        } else {
                            if (cCharAt25 == 24180) {
                            }
                            if (cCharAt22 == 26376) {
                            }
                            if (cCharAt4 != 26085) {
                            }
                            cCharAt22 = cCharAt22;
                            cCharAt22 = '0';
                            cCharAt26 = cCharAt27;
                            i8 = 10;
                        }
                    } else if (cCharAt23 != '.') {
                        if (cCharAt22 == 'T') {
                            cCharAt26 = cCharAt25;
                            cCharAt26 = cCharAt26;
                            cCharAt22 = cCharAt27;
                            cCharAt22 = cCharAt22;
                            i8 = 8;
                            cCharAt22 = cCharAt22;
                        } else {
                            if (cCharAt25 == 24180) {
                            }
                            if (cCharAt22 == 26376) {
                            }
                            if (cCharAt4 != 26085) {
                            }
                            cCharAt22 = cCharAt22;
                            cCharAt22 = '0';
                            cCharAt26 = cCharAt27;
                            i8 = 10;
                        }
                    } else if (cCharAt22 == 'T') {
                        cCharAt26 = cCharAt25;
                        cCharAt26 = cCharAt26;
                        cCharAt22 = cCharAt27;
                        cCharAt22 = cCharAt22;
                        i8 = 8;
                        cCharAt22 = cCharAt22;
                    } else {
                        if (cCharAt25 == 24180) {
                        }
                        if (cCharAt22 == 26376) {
                        }
                        if (cCharAt4 != 26085) {
                        }
                        cCharAt22 = cCharAt22;
                        cCharAt22 = '0';
                        cCharAt26 = cCharAt27;
                        i8 = 10;
                    }
                    if (!checkDate(cCharAt21, cCharAt22, cCharAt23, cCharAt24, cCharAt26, cCharAt26, cCharAt22, cCharAt22)) {
                        return false;
                    }
                    i9 = i8;
                    setCalendar(cCharAt21, cCharAt22, cCharAt23, cCharAt24, cCharAt26, cCharAt26, cCharAt22, cCharAt22);
                    cCharAt5 = charAt(this.bp + i9);
                    if (cCharAt5 != 'T') {
                    }
                    if (cCharAt5 == 'T') {
                    }
                    if (i >= i9 + 9) {
                        return false;
                    }
                    cCharAt6 = charAt(this.bp + i9 + 1);
                    cCharAt7 = charAt(this.bp + i9 + 2);
                    cCharAt8 = charAt(this.bp + i9 + 4);
                    cCharAt9 = charAt(this.bp + i9 + 5);
                    cCharAt10 = charAt(this.bp + i9 + 7);
                    cCharAt11 = charAt(this.bp + i9 + 8);
                    if (!checkTime(cCharAt6, cCharAt7, cCharAt8, cCharAt9, cCharAt10, cCharAt11)) {
                        return false;
                    }
                    setTime(cCharAt6, cCharAt7, cCharAt8, cCharAt9, cCharAt10, cCharAt11);
                    if (charAt(this.bp + i9 + 9) == '.') {
                        i13 = i9 + 11;
                        if (i >= i13) {
                            return false;
                        }
                        i14 = cCharAt18 - '0';
                        if (i > i13) {
                            i11 = i14;
                            i15 = 1;
                        } else {
                            i11 = i14;
                            i15 = 1;
                        }
                        if (i15 == 2) {
                            i10 = i15;
                        } else {
                            i10 = i15;
                        }
                    } else {
                        i10 = -1;
                        i11 = 0;
                    }
                    this.calendar.set(14, i11);
                    cCharAt12 = charAt(this.bp + i9 + 10 + i10);
                    if (cCharAt12 != '+') {
                        cCharAt13 = charAt(this.bp + i9 + 10 + i10 + 1);
                        if (cCharAt13 >= '0') {
                            return false;
                        }
                        return false;
                    }
                    cCharAt13 = charAt(this.bp + i9 + 10 + i10 + 1);
                    if (cCharAt13 >= '0') {
                        return false;
                    }
                    return false;
                    int i19 = i9 + 10 + i10 + i12;
                    cCharAt17 = charAt(this.bp + i19);
                    if (cCharAt17 == 26) {
                    }
                    int i20 = this.bp + i19;
                    this.bp = i20;
                    this.ch = charAt(i20);
                    this.token = 5;
                    return true;
                }
            } else if (i == 17 || charAt(this.bp + 6) == '-') {
                if (i < 9) {
                    return false;
                }
                cCharAt22 = charAt(this.bp + 8);
                cCharAt4 = charAt(this.bp + 9);
                if ((cCharAt25 == '-' || cCharAt22 != '-') && !(cCharAt25 == '/' && cCharAt22 == '/')) {
                    if (cCharAt25 == '-' || cCharAt27 != '-') {
                        if ((cCharAt23 != '.' && cCharAt26 == '.') || (cCharAt23 == '-' && cCharAt26 == '-')) {
                            cCharAt22 = cCharAt22;
                            cCharAt23 = cCharAt22;
                            cCharAt22 = cCharAt21;
                            cCharAt26 = cCharAt25;
                            cCharAt21 = cCharAt27;
                            i8 = 10;
                            cCharAt24 = cCharAt4;
                            cCharAt26 = cCharAt24;
                        } else if (cCharAt22 == 'T') {
                            cCharAt26 = cCharAt25;
                            cCharAt26 = cCharAt26;
                            cCharAt22 = cCharAt27;
                            cCharAt22 = cCharAt22;
                            i8 = 8;
                            cCharAt22 = cCharAt22;
                        } else {
                            if (cCharAt25 == 24180 && cCharAt25 != 45380) {
                                return false;
                            }
                            if (cCharAt22 == 26376 && cCharAt22 != 50900) {
                                if (cCharAt27 != 26376 && cCharAt27 != 50900) {
                                    return false;
                                }
                                if (cCharAt22 == 26085 || cCharAt22 == 51068) {
                                    i8 = 10;
                                    cCharAt26 = '0';
                                    cCharAt22 = '0';
                                    cCharAt22 = cCharAt22;
                                } else {
                                    if (cCharAt4 != 26085 && cCharAt4 != 51068) {
                                        return false;
                                    }
                                    i8 = 10;
                                    cCharAt26 = '0';
                                    cCharAt22 = cCharAt22;
                                    cCharAt26 = cCharAt26;
                                }
                            } else if (cCharAt4 != 26085 || cCharAt4 == 51068) {
                                cCharAt22 = cCharAt22;
                                cCharAt22 = '0';
                            } else {
                                if (charAt(this.bp + 10) != 26085 && charAt(this.bp + 10) != 51068) {
                                    return false;
                                }
                                cCharAt22 = cCharAt22;
                                cCharAt22 = cCharAt4;
                                cCharAt26 = cCharAt26;
                                cCharAt26 = cCharAt27;
                                i8 = 11;
                                cCharAt22 = cCharAt22;
                            }
                        }
                    } else if (cCharAt22 == ' ') {
                        i8 = 8;
                        cCharAt26 = '0';
                        cCharAt22 = '0';
                        cCharAt22 = cCharAt22;
                    } else {
                        i8 = 9;
                        cCharAt26 = '0';
                        cCharAt22 = cCharAt22;
                        cCharAt26 = cCharAt26;
                    }
                    if (!checkDate(cCharAt21, cCharAt22, cCharAt23, cCharAt24, cCharAt26, cCharAt26, cCharAt22, cCharAt22)) {
                        return false;
                    }
                    i9 = i8;
                    setCalendar(cCharAt21, cCharAt22, cCharAt23, cCharAt24, cCharAt26, cCharAt26, cCharAt22, cCharAt22);
                    cCharAt5 = charAt(this.bp + i9);
                    if (cCharAt5 != 'T' && i == 16 && i9 == 8 && charAt(this.bp + 15) == 'Z') {
                        char cCharAt32 = charAt(this.bp + i9 + 1);
                        char cCharAt33 = charAt(this.bp + i9 + 2);
                        char cCharAt34 = charAt(this.bp + i9 + 3);
                        char cCharAt35 = charAt(this.bp + i9 + 4);
                        char cCharAt36 = charAt(this.bp + i9 + 5);
                        char cCharAt37 = charAt(this.bp + i9 + 6);
                        if (!checkTime(cCharAt32, cCharAt33, cCharAt34, cCharAt35, cCharAt36, cCharAt37)) {
                            return false;
                        }
                        setTime(cCharAt32, cCharAt33, cCharAt34, cCharAt35, cCharAt36, cCharAt37);
                        this.calendar.set(14, 0);
                        if (this.calendar.getTimeZone().getRawOffset() != 0) {
                            String[] availableIDs = TimeZone.getAvailableIDs(0);
                            if (availableIDs.length > 0) {
                                this.calendar.setTimeZone(TimeZone.getTimeZone(availableIDs[0]));
                            }
                        }
                        this.token = 5;
                        return true;
                    }
                    if (cCharAt5 == 'T' && (cCharAt5 != ' ' || z)) {
                        if (cCharAt5 == '\"' || cCharAt5 == 26 || cCharAt5 == 26085 || cCharAt5 == 51068) {
                            this.calendar.set(11, 0);
                            this.calendar.set(12, 0);
                            this.calendar.set(13, 0);
                            this.calendar.set(14, 0);
                            int i21 = this.bp + i9;
                            this.bp = i21;
                            this.ch = charAt(i21);
                            this.token = 5;
                            return true;
                        }
                        if ((cCharAt5 != '+' && cCharAt5 != '-') || this.len != i9 + 6 || charAt(this.bp + i9 + 3) != ':' || charAt(this.bp + i9 + 4) != '0' || charAt(this.bp + i9 + 5) != '0') {
                            return false;
                        }
                        setTime('0', '0', '0', '0', '0', '0');
                        this.calendar.set(14, 0);
                        setTimeZone(cCharAt5, charAt(this.bp + i9 + 1), charAt(this.bp + i9 + 2));
                        return true;
                    }
                    if (i >= i9 + 9 || charAt(this.bp + i9 + 3) != ':' || charAt(this.bp + i9 + 6) != ':') {
                        return false;
                    }
                    cCharAt6 = charAt(this.bp + i9 + 1);
                    cCharAt7 = charAt(this.bp + i9 + 2);
                    cCharAt8 = charAt(this.bp + i9 + 4);
                    cCharAt9 = charAt(this.bp + i9 + 5);
                    cCharAt10 = charAt(this.bp + i9 + 7);
                    cCharAt11 = charAt(this.bp + i9 + 8);
                    if (!checkTime(cCharAt6, cCharAt7, cCharAt8, cCharAt9, cCharAt10, cCharAt11)) {
                        return false;
                    }
                    setTime(cCharAt6, cCharAt7, cCharAt8, cCharAt9, cCharAt10, cCharAt11);
                    if (charAt(this.bp + i9 + 9) == '.') {
                        i13 = i9 + 11;
                        if (i >= i13 || (cCharAt18 = charAt(this.bp + i9 + 10)) < '0' || cCharAt18 > '9') {
                            return false;
                        }
                        i14 = cCharAt18 - '0';
                        if (i > i13 || (cCharAt20 = charAt(this.bp + i9 + 11)) < '0' || cCharAt20 > '9') {
                            i11 = i14;
                            i15 = 1;
                        } else {
                            i11 = (i14 * 10) + (cCharAt20 - '0');
                            i15 = 2;
                        }
                        if (i15 == 2 || (cCharAt19 = charAt(this.bp + i9 + 12)) < '0' || cCharAt19 > '9') {
                            i10 = i15;
                        } else {
                            i11 = (i11 * 10) + (cCharAt19 - '0');
                            i10 = 3;
                        }
                    } else {
                        i10 = -1;
                        i11 = 0;
                    }
                    this.calendar.set(14, i11);
                    cCharAt12 = charAt(this.bp + i9 + 10 + i10);
                    if (cCharAt12 != '+' || cCharAt12 == '-') {
                        cCharAt13 = charAt(this.bp + i9 + 10 + i10 + 1);
                        if (cCharAt13 >= '0' || cCharAt13 > '1' || (cCharAt14 = charAt(this.bp + i9 + 10 + i10 + 2)) < '0' || cCharAt14 > '9') {
                            return false;
                        }
                        char cCharAt38 = charAt(this.bp + i9 + 10 + i10 + 3);
                        if (cCharAt38 == ':') {
                            char cCharAt39 = charAt(this.bp + i9 + 10 + i10 + 4);
                            if ((cCharAt39 != '0' && cCharAt39 != '3') || (cCharAt15 = charAt(this.bp + i9 + 10 + i10 + 5)) != '0') {
                                return false;
                            }
                            c7 = cCharAt39;
                            i12 = 6;
                        } else {
                            if (cCharAt38 == '0') {
                                cCharAt16 = charAt(this.bp + i9 + 10 + i10 + 4);
                                if (cCharAt16 != '0' && cCharAt16 != '3') {
                                    return false;
                                }
                            } else if (cCharAt38 == '3' && charAt(this.bp + i9 + 10 + i10 + 4) == '0') {
                                cCharAt16 = '3';
                            } else if (cCharAt38 == '4' && charAt(this.bp + i9 + 10 + i10 + 4) == '5') {
                                cCharAt15 = '5';
                                i12 = 5;
                                c7 = '4';
                            } else {
                                i12 = 3;
                                c7 = '0';
                                cCharAt15 = '0';
                            }
                            c7 = cCharAt16;
                            i12 = 5;
                            cCharAt15 = '0';
                        }
                        setTimeZone(cCharAt12, cCharAt13, cCharAt14, c7, cCharAt15);
                    } else if (cCharAt12 == 'Z') {
                        if (this.calendar.getTimeZone().getRawOffset() != 0) {
                            String[] availableIDs2 = TimeZone.getAvailableIDs(0);
                            if (availableIDs2.length > 0) {
                                this.calendar.setTimeZone(TimeZone.getTimeZone(availableIDs2[0]));
                            }
                        }
                        i12 = 1;
                    } else {
                        i12 = 0;
                    }
                    int i110 = i9 + 10 + i10 + i12;
                    cCharAt17 = charAt(this.bp + i110);
                    if (cCharAt17 == 26 && cCharAt17 != '\"') {
                        return false;
                    }
                    int i22 = this.bp + i110;
                    this.bp = i22;
                    this.ch = charAt(i22);
                    this.token = 5;
                    return true;
                }
                cCharAt22 = cCharAt4;
                cCharAt22 = cCharAt22;
                cCharAt26 = cCharAt27;
                i8 = 10;
                if (!checkDate(cCharAt21, cCharAt22, cCharAt23, cCharAt24, cCharAt26, cCharAt26, cCharAt22, cCharAt22)) {
                    return false;
                }
                i9 = i8;
                setCalendar(cCharAt21, cCharAt22, cCharAt23, cCharAt24, cCharAt26, cCharAt26, cCharAt22, cCharAt22);
                cCharAt5 = charAt(this.bp + i9);
                if (cCharAt5 != 'T') {
                }
                if (cCharAt5 == 'T') {
                }
                if (i >= i9 + 9) {
                    return false;
                }
                cCharAt6 = charAt(this.bp + i9 + 1);
                cCharAt7 = charAt(this.bp + i9 + 2);
                cCharAt8 = charAt(this.bp + i9 + 4);
                cCharAt9 = charAt(this.bp + i9 + 5);
                cCharAt10 = charAt(this.bp + i9 + 7);
                cCharAt11 = charAt(this.bp + i9 + 8);
                if (!checkTime(cCharAt6, cCharAt7, cCharAt8, cCharAt9, cCharAt10, cCharAt11)) {
                    return false;
                }
                setTime(cCharAt6, cCharAt7, cCharAt8, cCharAt9, cCharAt10, cCharAt11);
                if (charAt(this.bp + i9 + 9) == '.') {
                    i13 = i9 + 11;
                    if (i >= i13) {
                        return false;
                    }
                    i14 = cCharAt18 - '0';
                    if (i > i13) {
                        i11 = i14;
                        i15 = 1;
                    } else {
                        i11 = i14;
                        i15 = 1;
                    }
                    if (i15 == 2) {
                        i10 = i15;
                    } else {
                        i10 = i15;
                    }
                } else {
                    i10 = -1;
                    i11 = 0;
                }
                this.calendar.set(14, i11);
                cCharAt12 = charAt(this.bp + i9 + 10 + i10);
                if (cCharAt12 != '+') {
                    cCharAt13 = charAt(this.bp + i9 + 10 + i10 + 1);
                    if (cCharAt13 >= '0') {
                        return false;
                    }
                    return false;
                }
                cCharAt13 = charAt(this.bp + i9 + 10 + i10 + 1);
                if (cCharAt13 >= '0') {
                    return false;
                }
                return false;
                int i111 = i9 + 10 + i10 + i12;
                cCharAt17 = charAt(this.bp + i111);
                if (cCharAt17 == 26) {
                }
                int i23 = this.bp + i111;
                this.bp = i23;
                this.ch = charAt(i23);
                this.token = 5;
                return true;
            }
            z2 = false;
            i2 = 5;
        }
        if (z) {
            return z2;
        }
        char cCharAt40 = charAt(this.bp + 8);
        boolean z3 = cCharAt25 == '-' && cCharAt22 == '-';
        boolean z4 = z3 && i == 16;
        boolean z5 = z3 && i == 17;
        if (z5 || z4) {
            cCharAt = charAt(this.bp + 9);
            c = cCharAt26;
            c2 = cCharAt27;
            c3 = cCharAt40;
        } else if (cCharAt25 == '-' && cCharAt27 == '-') {
            c2 = cCharAt26;
            cCharAt = cCharAt22;
            c = '0';
            c3 = '0';
        } else {
            c = cCharAt25;
            c2 = cCharAt26;
            c3 = cCharAt27;
            cCharAt = cCharAt22;
        }
        if (!checkDate(cCharAt21, cCharAt22, cCharAt23, cCharAt24, c, c2, c3, cCharAt)) {
            return false;
        }
        setCalendar(cCharAt21, cCharAt22, cCharAt23, cCharAt24, c, c2, c3, cCharAt);
        if (i != 8) {
            char cCharAt41 = charAt(this.bp + 9);
            char cCharAt42 = charAt(this.bp + 10);
            char cCharAt43 = charAt(this.bp + 11);
            char cCharAt44 = charAt(this.bp + 12);
            char cCharAt45 = charAt(this.bp + 13);
            if ((z5 && cCharAt42 == 'T' && cCharAt45 == ':' && charAt(this.bp + 16) == 'Z') || (z4 && ((cCharAt42 == ' ' || cCharAt42 == 'T') && cCharAt45 == ':'))) {
                cCharAt2 = charAt(this.bp + 14);
                cCharAt3 = charAt(this.bp + 15);
                cCharAt40 = cCharAt43;
                c4 = cCharAt44;
                c5 = '0';
                c6 = '0';
            } else {
                c4 = cCharAt41;
                cCharAt2 = cCharAt42;
                cCharAt3 = cCharAt43;
                c5 = cCharAt44;
                c6 = cCharAt45;
            }
            if (!checkTime(cCharAt40, c4, cCharAt2, cCharAt3, c5, c6)) {
                return false;
            }
            if (i != 17 || z5) {
                i7 = 0;
            } else {
                char cCharAt46 = charAt(this.bp + 14);
                char cCharAt47 = charAt(this.bp + 15);
                char cCharAt48 = charAt(this.bp + 16);
                if (cCharAt46 < '0' || cCharAt46 > '9' || cCharAt47 < '0' || cCharAt47 > '9' || cCharAt48 < '0' || cCharAt48 > '9') {
                    return false;
                }
                i7 = ((cCharAt46 - '0') * 100) + ((cCharAt47 - '0') * 10) + (cCharAt48 - '0');
            }
            i5 = ((cCharAt2 - '0') * 10) + (cCharAt3 - '0');
            i6 = ((c5 - '0') * 10) + (c6 - '0');
            i4 = i7;
            i3 = ((cCharAt40 - '0') * 10) + (c4 - '0');
        } else {
            i3 = 0;
            i4 = 0;
            i5 = 0;
            i6 = 0;
        }
        this.calendar.set(11, i3);
        this.calendar.set(12, i5);
        this.calendar.set(13, i6);
        this.calendar.set(14, i4);
        this.token = i2;
        return true;
    }

    protected void setTime(char c, char c2, char c3, char c4, char c5, char c6) {
        this.calendar.set(11, ((c - '0') * 10) + (c2 - '0'));
        this.calendar.set(12, ((c3 - '0') * 10) + (c4 - '0'));
        this.calendar.set(13, ((c5 - '0') * 10) + (c6 - '0'));
    }

    protected void setTimeZone(char c, char c2, char c3) {
        setTimeZone(c, c2, c3, '0', '0');
    }

    protected void setTimeZone(char c, char c2, char c3, char c4, char c5) {
        int i = ((((c2 - '0') * 10) + (c3 - '0')) * 3600 * 1000) + ((((c4 - '0') * 10) + (c5 - '0')) * 60 * 1000);
        if (c == '-') {
            i = -i;
        }
        if (this.calendar.getTimeZone().getRawOffset() != i) {
            String[] availableIDs = TimeZone.getAvailableIDs(i);
            if (availableIDs.length > 0) {
                this.calendar.setTimeZone(TimeZone.getTimeZone(availableIDs[0]));
            }
        }
    }

    private void setCalendar(char c, char c2, char c3, char c4, char c5, char c6, char c7, char c8) {
        this.calendar = Calendar.getInstance(this.timeZone, this.locale);
        this.calendar.set(1, ((c - '0') * 1000) + ((c2 - '0') * 100) + ((c3 - '0') * 10) + (c4 - '0'));
        this.calendar.set(2, (((c5 - '0') * 10) + (c6 - '0')) - 1);
        this.calendar.set(5, ((c7 - '0') * 10) + (c8 - '0'));
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public boolean isEOF() {
        if (this.bp != this.len) {
            return this.ch == 26 && this.bp + 1 == this.len;
        }
        return true;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public int scanFieldInt(char[] cArr) {
        int i;
        char cCharAt;
        int i2;
        char cCharAt2;
        this.matchStat = 0;
        int i3 = this.bp;
        char c = this.ch;
        if (!charArrayCompare(this.text, this.bp, cArr)) {
            this.matchStat = -2;
            return 0;
        }
        int length = this.bp + cArr.length;
        int i4 = length + 1;
        char cCharAt3 = charAt(length);
        boolean z = cCharAt3 == '\"';
        if (z) {
            i = i4 + 1;
            cCharAt = charAt(i4);
        } else {
            i = i4;
            cCharAt = cCharAt3;
        }
        boolean z2 = cCharAt == '-';
        if (z2) {
            int i5 = i + 1;
            char cCharAt4 = charAt(i);
            i = i5;
            cCharAt = cCharAt4;
        }
        if (cCharAt < '0' || cCharAt > '9') {
            this.matchStat = -1;
            return 0;
        }
        int i6 = cCharAt - '0';
        while (true) {
            i2 = i + 1;
            cCharAt2 = charAt(i);
            if (cCharAt2 < '0' || cCharAt2 > '9') {
                break;
            }
            i6 = (i6 * 10) + (cCharAt2 - '0');
            i = i2;
        }
        if (cCharAt2 == '.') {
            this.matchStat = -1;
            return 0;
        }
        if (i6 < 0) {
            this.matchStat = -1;
            return 0;
        }
        if (z) {
            if (cCharAt2 != '\"') {
                this.matchStat = -1;
                return 0;
            }
            int i7 = i2 + 1;
            char cCharAt5 = charAt(i2);
            i2 = i7;
            cCharAt2 = cCharAt5;
        }
        while (cCharAt2 != ',' && cCharAt2 != '}') {
            if (isWhitespace(cCharAt2)) {
                int i8 = i2 + 1;
                char cCharAt6 = charAt(i2);
                i2 = i8;
                cCharAt2 = cCharAt6;
            } else {
                this.matchStat = -1;
                return 0;
            }
        }
        int i9 = i2 - 1;
        this.bp = i9;
        if (cCharAt2 == ',') {
            int i10 = this.bp + 1;
            this.bp = i10;
            this.ch = charAt(i10);
            this.matchStat = 3;
            this.token = 16;
            return z2 ? -i6 : i6;
        }
        if (cCharAt2 == '}') {
            this.bp = i9;
            int i11 = this.bp + 1;
            this.bp = i11;
            char cCharAt7 = charAt(i11);
            while (true) {
                if (cCharAt7 == ',') {
                    this.token = 16;
                    int i12 = this.bp + 1;
                    this.bp = i12;
                    this.ch = charAt(i12);
                    break;
                }
                if (cCharAt7 == ']') {
                    this.token = 15;
                    int i13 = this.bp + 1;
                    this.bp = i13;
                    this.ch = charAt(i13);
                    break;
                }
                if (cCharAt7 == '}') {
                    this.token = 13;
                    int i14 = this.bp + 1;
                    this.bp = i14;
                    this.ch = charAt(i14);
                    break;
                }
                if (cCharAt7 == 26) {
                    this.token = 20;
                    break;
                }
                if (isWhitespace(cCharAt7)) {
                    int i15 = this.bp + 1;
                    this.bp = i15;
                    cCharAt7 = charAt(i15);
                } else {
                    this.bp = i3;
                    this.ch = c;
                    this.matchStat = -1;
                    return 0;
                }
            }
            this.matchStat = 4;
        }
        return z2 ? -i6 : i6;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public String scanFieldString(char[] cArr) {
        this.matchStat = 0;
        int i = this.bp;
        char c = this.ch;
        while (!charArrayCompare(this.text, this.bp, cArr)) {
            if (isWhitespace(this.ch)) {
                next();
            } else {
                this.matchStat = -2;
                return stringDefaultValue();
            }
        }
        int length = this.bp + cArr.length;
        int i2 = length + 1;
        if (charAt(length) != '\"') {
            this.matchStat = -1;
            return stringDefaultValue();
        }
        int iIndexOf = indexOf('\"', i2);
        if (iIndexOf == -1) {
            throw new JSONException("unclosed str");
        }
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
            int length2 = iIndexOf - ((this.bp + cArr.length) + 1);
            strSubString = readString(sub_chars(this.bp + cArr.length + 1, length2), length2);
        }
        char cCharAt = charAt(iIndexOf + 1);
        while (cCharAt != ',' && cCharAt != '}') {
            if (isWhitespace(cCharAt)) {
                iIndexOf++;
                cCharAt = charAt(iIndexOf + 1);
            } else {
                this.matchStat = -1;
                return stringDefaultValue();
            }
        }
        this.bp = iIndexOf + 1;
        this.ch = cCharAt;
        if (cCharAt == ',') {
            int i5 = this.bp + 1;
            this.bp = i5;
            this.ch = charAt(i5);
            this.matchStat = 3;
            return strSubString;
        }
        int i6 = this.bp + 1;
        this.bp = i6;
        char cCharAt2 = charAt(i6);
        if (cCharAt2 == ',') {
            this.token = 16;
            int i7 = this.bp + 1;
            this.bp = i7;
            this.ch = charAt(i7);
        } else if (cCharAt2 == ']') {
            this.token = 15;
            int i8 = this.bp + 1;
            this.bp = i8;
            this.ch = charAt(i8);
        } else if (cCharAt2 == '}') {
            this.token = 13;
            int i9 = this.bp + 1;
            this.bp = i9;
            this.ch = charAt(i9);
        } else if (cCharAt2 == 26) {
            this.token = 20;
        } else {
            this.bp = i;
            this.ch = c;
            this.matchStat = -1;
            return stringDefaultValue();
        }
        this.matchStat = 4;
        return strSubString;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public Date scanFieldDate(char[] cArr) {
        char cCharAt;
        long j;
        Date date;
        int i;
        boolean z = false;
        this.matchStat = 0;
        int i2 = this.bp;
        char c = this.ch;
        if (!charArrayCompare(this.text, this.bp, cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = this.bp + cArr.length;
        int i3 = length + 1;
        char cCharAt2 = charAt(length);
        if (cCharAt2 == '\"') {
            int iIndexOf = indexOf('\"', i3);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            this.bp = i3;
            if (scanISO8601DateIfMatch(false, iIndexOf - i3)) {
                Date time = this.calendar.getTime();
                char cCharAt3 = charAt(iIndexOf + 1);
                this.bp = i2;
                while (cCharAt3 != ',' && cCharAt3 != '}') {
                    if (isWhitespace(cCharAt3)) {
                        iIndexOf++;
                        cCharAt3 = charAt(iIndexOf + 1);
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                }
                this.bp = iIndexOf + 1;
                this.ch = cCharAt3;
                char c2 = cCharAt3;
                date = time;
                cCharAt = c2;
            } else {
                this.bp = i2;
                this.matchStat = -1;
                return null;
            }
        } else {
            char c3 = '9';
            char c4 = '0';
            if (cCharAt2 != '-' && (cCharAt2 < '0' || cCharAt2 > '9')) {
                this.matchStat = -1;
                return null;
            }
            if (cCharAt2 == '-') {
                cCharAt2 = charAt(i3);
                i3++;
                z = true;
            }
            if (cCharAt2 < '0' || cCharAt2 > '9') {
                cCharAt = cCharAt2;
                j = 0;
            } else {
                j = cCharAt2 - '0';
                while (true) {
                    i = i3 + 1;
                    cCharAt = charAt(i3);
                    if (cCharAt < c4 || cCharAt > c3) {
                        break;
                    }
                    j = (j * 10) + ((long) (cCharAt - '0'));
                    i3 = i;
                    c3 = '9';
                    c4 = '0';
                }
                if (cCharAt == ',' || cCharAt == '}') {
                    this.bp = i - 1;
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
            int i4 = this.bp + 1;
            this.bp = i4;
            this.ch = charAt(i4);
            this.matchStat = 3;
            this.token = 16;
            return date;
        }
        int i5 = this.bp + 1;
        this.bp = i5;
        char cCharAt4 = charAt(i5);
        if (cCharAt4 == ',') {
            this.token = 16;
            int i6 = this.bp + 1;
            this.bp = i6;
            this.ch = charAt(i6);
        } else if (cCharAt4 == ']') {
            this.token = 15;
            int i7 = this.bp + 1;
            this.bp = i7;
            this.ch = charAt(i7);
        } else if (cCharAt4 == '}') {
            this.token = 13;
            int i8 = this.bp + 1;
            this.bp = i8;
            this.ch = charAt(i8);
        } else if (cCharAt4 == 26) {
            this.token = 20;
        } else {
            this.bp = i2;
            this.ch = c;
            this.matchStat = -1;
            return null;
        }
        this.matchStat = 4;
        return date;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public long scanFieldSymbol(char[] cArr) {
        this.matchStat = 0;
        if (!charArrayCompare(this.text, this.bp, cArr)) {
            this.matchStat = -2;
            return 0L;
        }
        int length = this.bp + cArr.length;
        int i = length + 1;
        if (charAt(length) != '\"') {
            this.matchStat = -1;
            return 0L;
        }
        long j = -3750763034362895579L;
        while (true) {
            int i2 = i + 1;
            char cCharAt = charAt(i);
            if (cCharAt == '\"') {
                this.bp = i2;
                char cCharAt2 = charAt(this.bp);
                this.ch = cCharAt2;
                while (cCharAt2 != ',') {
                    if (cCharAt2 == '}') {
                        next();
                        skipWhitespace();
                        char current = getCurrent();
                        if (current == ',') {
                            this.token = 16;
                            int i3 = this.bp + 1;
                            this.bp = i3;
                            this.ch = charAt(i3);
                        } else if (current == ']') {
                            this.token = 15;
                            int i4 = this.bp + 1;
                            this.bp = i4;
                            this.ch = charAt(i4);
                        } else if (current == '}') {
                            this.token = 13;
                            int i5 = this.bp + 1;
                            this.bp = i5;
                            this.ch = charAt(i5);
                        } else if (current == 26) {
                            this.token = 20;
                        } else {
                            this.matchStat = -1;
                            return 0L;
                        }
                        this.matchStat = 4;
                        return j;
                    }
                    if (isWhitespace(cCharAt2)) {
                        int i6 = this.bp + 1;
                        this.bp = i6;
                        cCharAt2 = charAt(i6);
                    } else {
                        this.matchStat = -1;
                        return 0L;
                    }
                }
                int i7 = this.bp + 1;
                this.bp = i7;
                this.ch = charAt(i7);
                this.matchStat = 3;
                return j;
            }
            if (i2 > this.len) {
                this.matchStat = -1;
                return 0L;
            }
            j = (j ^ ((long) cCharAt)) * 1099511628211L;
            i = i2;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
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

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public Collection<String> scanFieldStringArray(char[] cArr, Class<?> cls) {
        int i;
        Collection<String> collection;
        char cCharAt;
        int i2;
        char cCharAt2;
        int i3;
        char cCharAt3;
        this.matchStat = 0;
        while (true) {
            if (this.ch != '\n' && this.ch != ' ') {
                break;
            }
            int i4 = this.bp + 1;
            this.bp = i4;
            this.ch = i4 >= this.len ? (char) 26 : this.text.charAt(i4);
        }
        if (!charArrayCompare(this.text, this.bp, cArr)) {
            this.matchStat = -2;
            return null;
        }
        Collection<String> collectionNewCollectionByType = newCollectionByType(cls);
        int i5 = this.bp;
        char c = this.ch;
        int length = this.bp + cArr.length;
        int i6 = length + 1;
        if (charAt(length) == '[') {
            int i7 = i6 + 1;
            char cCharAt4 = charAt(i6);
            while (true) {
                if (cCharAt4 == '\"') {
                    int iIndexOf = indexOf('\"', i7);
                    if (iIndexOf == -1) {
                        throw new JSONException("unclosed str");
                    }
                    String strSubString = subString(i7, iIndexOf - i7);
                    if (strSubString.indexOf(92) != -1) {
                        while (true) {
                            int i8 = 0;
                            for (int i9 = iIndexOf - 1; i9 >= 0 && charAt(i9) == '\\'; i9--) {
                                i8++;
                            }
                            if (i8 % 2 == 0) {
                                break;
                            }
                            iIndexOf = indexOf('\"', iIndexOf + 1);
                        }
                        int i10 = iIndexOf - i7;
                        strSubString = readString(sub_chars(i7, i10), i10);
                    }
                    int i11 = iIndexOf + 1;
                    i3 = i11 + 1;
                    cCharAt3 = charAt(i11);
                    collectionNewCollectionByType.add(strSubString);
                } else if (cCharAt4 == 'n' && this.text.startsWith("ull", i7)) {
                    int i12 = i7 + 3;
                    i3 = i12 + 1;
                    cCharAt3 = charAt(i12);
                    collectionNewCollectionByType.add(null);
                } else {
                    if (cCharAt4 == ']' && collectionNewCollectionByType.size() == 0) {
                        i2 = i7 + 1;
                        cCharAt2 = charAt(i7);
                        break;
                    }
                    this.matchStat = -1;
                    return null;
                }
                if (cCharAt3 != ',') {
                    if (cCharAt3 == ']') {
                        i2 = i3 + 1;
                        cCharAt2 = charAt(i3);
                        while (isWhitespace(cCharAt2)) {
                            cCharAt2 = charAt(i2);
                            i2++;
                        }
                        break;
                    }
                    this.matchStat = -1;
                    return null;
                }
                i7 = i3 + 1;
                cCharAt4 = charAt(i3);
            }
            collection = collectionNewCollectionByType;
            cCharAt = cCharAt2;
            i = 3;
        } else if (this.text.startsWith("ull", i6)) {
            i = 3;
            int i13 = i6 + 3;
            collection = null;
            cCharAt = charAt(i13);
            i2 = i13 + 1;
        } else {
            this.matchStat = -1;
            return null;
        }
        this.bp = i2;
        if (cCharAt == ',') {
            this.ch = charAt(this.bp);
            this.matchStat = i;
            return collection;
        }
        if (cCharAt == '}') {
            char cCharAt5 = charAt(this.bp);
            while (cCharAt5 != ',') {
                if (cCharAt5 == ']') {
                    this.token = 15;
                    int i14 = this.bp + 1;
                    this.bp = i14;
                    this.ch = charAt(i14);
                } else if (cCharAt5 == '}') {
                    this.token = 13;
                    int i15 = this.bp + 1;
                    this.bp = i15;
                    this.ch = charAt(i15);
                } else if (cCharAt5 == 26) {
                    this.token = 20;
                    this.ch = cCharAt5;
                } else {
                    boolean z = false;
                    while (isWhitespace(cCharAt5)) {
                        int i16 = i2 + 1;
                        char cCharAt6 = charAt(i2);
                        this.bp = i16;
                        z = true;
                        cCharAt5 = cCharAt6;
                        i2 = i16;
                    }
                    if (!z) {
                        this.matchStat = -1;
                        return null;
                    }
                }
                this.matchStat = 4;
                return collection;
            }
            this.token = 16;
            int i17 = this.bp + 1;
            this.bp = i17;
            this.ch = charAt(i17);
            this.matchStat = 4;
            return collection;
        }
        this.ch = c;
        this.bp = i5;
        this.matchStat = -1;
        return null;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public long scanFieldLong(char[] cArr) {
        int i;
        char cCharAt;
        boolean z;
        int i2;
        char cCharAt2;
        int i3;
        char cCharAt3;
        this.matchStat = 0;
        int i4 = this.bp;
        char c = this.ch;
        if (!charArrayCompare(this.text, this.bp, cArr)) {
            this.matchStat = -2;
            return 0L;
        }
        int length = this.bp + cArr.length;
        int i5 = length + 1;
        char cCharAt4 = charAt(length);
        boolean z2 = cCharAt4 == '\"';
        if (z2) {
            i = i5 + 1;
            cCharAt = charAt(i5);
        } else {
            i = i5;
            cCharAt = cCharAt4;
        }
        if (cCharAt == '-') {
            int i6 = i + 1;
            char cCharAt5 = charAt(i);
            z = true;
            i = i6;
            cCharAt = cCharAt5;
        } else {
            z = false;
        }
        if (cCharAt >= '0') {
            char c2 = '9';
            if (cCharAt <= '9') {
                long j = cCharAt - '0';
                while (true) {
                    i2 = i + 1;
                    cCharAt2 = charAt(i);
                    if (cCharAt2 < '0' || cCharAt2 > c2) {
                        break;
                    }
                    j = (j * 10) + ((long) (cCharAt2 - '0'));
                    i = i2;
                    c2 = '9';
                }
                if (cCharAt2 == '.') {
                    this.matchStat = -1;
                    return 0L;
                }
                if (!z2) {
                    i3 = i2;
                    cCharAt3 = cCharAt2;
                } else {
                    if (cCharAt2 != '\"') {
                        this.matchStat = -1;
                        return 0L;
                    }
                    i3 = i2 + 1;
                    cCharAt3 = charAt(i2);
                }
                if (cCharAt3 == ',' || cCharAt3 == '}') {
                    this.bp = i3 - 1;
                }
                if (!(j >= 0 || (j == Long.MIN_VALUE && z))) {
                    this.bp = i4;
                    this.ch = c;
                    this.matchStat = -1;
                    return 0L;
                }
                while (cCharAt3 != ',') {
                    if (cCharAt3 == '}') {
                        int i7 = this.bp + 1;
                        this.bp = i7;
                        char cCharAt6 = charAt(i7);
                        while (true) {
                            if (cCharAt6 == ',') {
                                this.token = 16;
                                int i8 = this.bp + 1;
                                this.bp = i8;
                                this.ch = charAt(i8);
                                break;
                            }
                            if (cCharAt6 == ']') {
                                this.token = 15;
                                int i9 = this.bp + 1;
                                this.bp = i9;
                                this.ch = charAt(i9);
                                break;
                            }
                            if (cCharAt6 == '}') {
                                this.token = 13;
                                int i10 = this.bp + 1;
                                this.bp = i10;
                                this.ch = charAt(i10);
                                break;
                            }
                            if (cCharAt6 == 26) {
                                this.token = 20;
                                break;
                            }
                            if (isWhitespace(cCharAt6)) {
                                int i11 = this.bp + 1;
                                this.bp = i11;
                                cCharAt6 = charAt(i11);
                            } else {
                                this.bp = i4;
                                this.ch = c;
                                this.matchStat = -1;
                                return 0L;
                            }
                        }
                        this.matchStat = 4;
                        return z ? -j : j;
                    }
                    if (isWhitespace(cCharAt3)) {
                        this.bp = i3;
                        int i12 = i3 + 1;
                        char cCharAt7 = charAt(i3);
                        i3 = i12;
                        cCharAt3 = cCharAt7;
                    } else {
                        this.matchStat = -1;
                        return 0L;
                    }
                }
                int i13 = this.bp + 1;
                this.bp = i13;
                this.ch = charAt(i13);
                this.matchStat = 3;
                this.token = 16;
                return z ? -j : j;
            }
        }
        this.bp = i4;
        this.ch = c;
        this.matchStat = -1;
        return 0L;
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0171  */
    /* JADX WARN: Code duplicated, block: B:104:0x0177 A[LOOP:0: B:79:0x00f8->B:104:0x0177, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:110:0x0113 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:111:0x0182 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:113:0x016e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:115:0x0130 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:116:0x0142 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:117:0x0156 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:82:0x010f  */
    /* JADX WARN: Code duplicated, block: B:87:0x012c  */
    /* JADX WARN: Code duplicated, block: B:90:0x0140 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:92:0x0152  */
    /* JADX WARN: Code duplicated, block: B:97:0x015e  */
    /* JADX WARN: Code duplicated, block: B:99:0x0164 A[LOOP:1: B:85:0x011c->B:99:0x0164, LOOP_END] */
    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public boolean scanFieldBoolean(char[] cArr) {
        int i;
        char cCharAt;
        int i2;
        char cCharAt2;
        int i3;
        boolean z;
        int i4;
        char cCharAt3;
        this.matchStat = 0;
        if (!charArrayCompare(this.text, this.bp, cArr)) {
            this.matchStat = -2;
            return false;
        }
        int i5 = this.bp;
        int length = this.bp + cArr.length;
        int i6 = length + 1;
        char cCharAt4 = charAt(length);
        boolean z2 = cCharAt4 == '\"';
        if (z2) {
            i = i6 + 1;
            cCharAt = charAt(i6);
        } else {
            i = i6;
            cCharAt = cCharAt4;
        }
        if (cCharAt == 't') {
            int i7 = i + 1;
            if (charAt(i) != 'r') {
                this.matchStat = -1;
                return false;
            }
            int i8 = i7 + 1;
            if (charAt(i7) != 'u') {
                this.matchStat = -1;
                return false;
            }
            int i9 = i8 + 1;
            if (charAt(i8) != 'e') {
                this.matchStat = -1;
                return false;
            }
            if (z2) {
                int i10 = i9 + 1;
                if (charAt(i9) != '\"') {
                    this.matchStat = -1;
                    return false;
                }
                i9 = i10;
            }
            this.bp = i9;
            cCharAt2 = charAt(this.bp);
        } else {
            if (cCharAt == 'f') {
                int i11 = i + 1;
                if (charAt(i) != 'a') {
                    this.matchStat = -1;
                    return false;
                }
                int i12 = i11 + 1;
                if (charAt(i11) != 'l') {
                    this.matchStat = -1;
                    return false;
                }
                int i13 = i12 + 1;
                if (charAt(i12) != 's') {
                    this.matchStat = -1;
                    return false;
                }
                int i14 = i13 + 1;
                if (charAt(i13) != 'e') {
                    this.matchStat = -1;
                    return false;
                }
                if (z2) {
                    i4 = i14 + 1;
                    if (charAt(i14) != '\"') {
                        this.matchStat = -1;
                        return false;
                    }
                } else {
                    i4 = i14;
                }
                this.bp = i4;
                cCharAt2 = charAt(this.bp);
            } else if (cCharAt == '1') {
                if (z2) {
                    i3 = i + 1;
                    if (charAt(i) != '\"') {
                        this.matchStat = -1;
                        return false;
                    }
                } else {
                    i3 = i;
                }
                this.bp = i3;
                cCharAt2 = charAt(this.bp);
            } else if (cCharAt == '0') {
                if (z2) {
                    i2 = i + 1;
                    if (charAt(i) != '\"') {
                        this.matchStat = -1;
                        return false;
                    }
                } else {
                    i2 = i;
                }
                this.bp = i2;
                cCharAt2 = charAt(this.bp);
            } else {
                this.matchStat = -1;
                return false;
            }
            z = false;
            while (cCharAt2 != ',') {
                if (cCharAt2 == '}') {
                    int i15 = this.bp + 1;
                    this.bp = i15;
                    cCharAt3 = charAt(i15);
                    while (cCharAt3 != ',') {
                        if (cCharAt3 == ']') {
                            this.token = 15;
                            int i16 = this.bp + 1;
                            this.bp = i16;
                            this.ch = charAt(i16);
                        } else if (cCharAt3 == '}') {
                            this.token = 13;
                            int i17 = this.bp + 1;
                            this.bp = i17;
                            this.ch = charAt(i17);
                        } else if (cCharAt3 == 26) {
                            this.token = 20;
                        } else if (isWhitespace(cCharAt3)) {
                            int i18 = this.bp + 1;
                            this.bp = i18;
                            cCharAt3 = charAt(i18);
                        } else {
                            this.matchStat = -1;
                            return false;
                        }
                        this.matchStat = 4;
                        return z;
                    }
                    this.token = 16;
                    int i19 = this.bp + 1;
                    this.bp = i19;
                    this.ch = charAt(i19);
                    this.matchStat = 4;
                    return z;
                }
                if (isWhitespace(cCharAt2)) {
                    int i20 = this.bp + 1;
                    this.bp = i20;
                    cCharAt2 = charAt(i20);
                } else {
                    this.bp = i5;
                    charAt(this.bp);
                    this.matchStat = -1;
                    return false;
                }
            }
            int i21 = this.bp + 1;
            this.bp = i21;
            this.ch = charAt(i21);
            this.matchStat = 3;
            this.token = 16;
            return z;
        }
        z = true;
        while (cCharAt2 != ',') {
            if (cCharAt2 == '}') {
                int i110 = this.bp + 1;
                this.bp = i110;
                cCharAt3 = charAt(i110);
                while (cCharAt3 != ',') {
                    if (cCharAt3 == ']') {
                        this.token = 15;
                        int i111 = this.bp + 1;
                        this.bp = i111;
                        this.ch = charAt(i111);
                    } else if (cCharAt3 == '}') {
                        this.token = 13;
                        int i112 = this.bp + 1;
                        this.bp = i112;
                        this.ch = charAt(i112);
                    } else if (cCharAt3 == 26) {
                        this.token = 20;
                    } else if (isWhitespace(cCharAt3)) {
                        int i113 = this.bp + 1;
                        this.bp = i113;
                        cCharAt3 = charAt(i113);
                    } else {
                        this.matchStat = -1;
                        return false;
                    }
                    this.matchStat = 4;
                    return z;
                }
                this.token = 16;
                int i114 = this.bp + 1;
                this.bp = i114;
                this.ch = charAt(i114);
                this.matchStat = 4;
                return z;
            }
            if (isWhitespace(cCharAt2)) {
                int i22 = this.bp + 1;
                this.bp = i22;
                cCharAt2 = charAt(i22);
            } else {
                this.bp = i5;
                charAt(this.bp);
                this.matchStat = -1;
                return false;
            }
        }
        int i23 = this.bp + 1;
        this.bp = i23;
        this.ch = charAt(i23);
        this.matchStat = 3;
        this.token = 16;
        return z;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final int scanInt(char c) {
        int i;
        char cCharAt;
        char cCharAt2;
        int i2;
        this.matchStat = 0;
        int i3 = this.bp;
        int i4 = i3 + 1;
        char cCharAt3 = charAt(i3);
        while (isWhitespace(cCharAt3)) {
            int i5 = i4 + 1;
            char cCharAt4 = charAt(i4);
            i4 = i5;
            cCharAt3 = cCharAt4;
        }
        boolean z = cCharAt3 == '\"';
        if (z) {
            int i6 = i4 + 1;
            char cCharAt5 = charAt(i4);
            i4 = i6;
            cCharAt3 = cCharAt5;
        }
        boolean z2 = cCharAt3 == '-';
        if (z2) {
            int i7 = i4 + 1;
            char cCharAt6 = charAt(i4);
            i4 = i7;
            cCharAt3 = cCharAt6;
        }
        if (cCharAt3 >= '0' && cCharAt3 <= '9') {
            int i8 = cCharAt3 - '0';
            while (true) {
                i = i4 + 1;
                cCharAt = charAt(i4);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                i8 = (i8 * 10) + (cCharAt - '0');
                i4 = i;
            }
            if (cCharAt == '.') {
                this.matchStat = -1;
                return 0;
            }
            if (!z) {
                cCharAt2 = cCharAt;
                i2 = i;
            } else {
                if (cCharAt != '\"') {
                    this.matchStat = -1;
                    return 0;
                }
                i2 = i + 1;
                cCharAt2 = charAt(i);
            }
            if (i8 < 0) {
                this.matchStat = -1;
                return 0;
            }
            while (cCharAt2 != c) {
                if (isWhitespace(cCharAt2)) {
                    cCharAt2 = charAt(i2);
                    i2++;
                } else {
                    this.matchStat = -1;
                    return z2 ? -i8 : i8;
                }
            }
            this.bp = i2;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return z2 ? -i8 : i8;
        }
        if (cCharAt3 == 'n') {
            int i9 = i4 + 1;
            if (charAt(i4) == 'u') {
                int i10 = i9 + 1;
                if (charAt(i9) == 'l') {
                    int i11 = i10 + 1;
                    if (charAt(i10) == 'l') {
                        this.matchStat = 5;
                        int i12 = i11 + 1;
                        char cCharAt7 = charAt(i11);
                        if (z && cCharAt7 == '\"') {
                            int i13 = i12 + 1;
                            char cCharAt8 = charAt(i12);
                            i12 = i13;
                            cCharAt7 = cCharAt8;
                        }
                        while (cCharAt7 != ',') {
                            if (cCharAt7 == ']') {
                                this.bp = i12;
                                this.ch = charAt(this.bp);
                                this.matchStat = 5;
                                this.token = 15;
                                return 0;
                            }
                            if (isWhitespace(cCharAt7)) {
                                int i14 = i12 + 1;
                                char cCharAt9 = charAt(i12);
                                i12 = i14;
                                cCharAt7 = cCharAt9;
                            } else {
                                this.matchStat = -1;
                                return 0;
                            }
                        }
                        this.bp = i12;
                        this.ch = charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 16;
                        return 0;
                    }
                }
            }
        }
        this.matchStat = -1;
        return 0;
    }

    /* JADX WARN: Code duplicated, block: B:55:0x00c0  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x00c4 -> B:52:0x00b4). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public double scanDouble(char r22) {
        /*
            Method dump skipped, instruction units count: 397
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.scanDouble(char):double");
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public long scanLong(char c) {
        int i;
        char cCharAt;
        boolean z = false;
        this.matchStat = 0;
        int i2 = this.bp;
        int i3 = i2 + 1;
        char cCharAt2 = charAt(i2);
        boolean z2 = cCharAt2 == '\"';
        if (z2) {
            int i4 = i3 + 1;
            char cCharAt3 = charAt(i3);
            i3 = i4;
            cCharAt2 = cCharAt3;
        }
        boolean z3 = cCharAt2 == '-';
        if (z3) {
            int i5 = i3 + 1;
            char cCharAt4 = charAt(i3);
            i3 = i5;
            cCharAt2 = cCharAt4;
        }
        char c2 = '0';
        if (cCharAt2 >= '0' && cCharAt2 <= '9') {
            long j = cCharAt2 - '0';
            while (true) {
                i = i3 + 1;
                cCharAt = charAt(i3);
                if (cCharAt < c2 || cCharAt > '9') {
                    break;
                }
                j = (j * 10) + ((long) (cCharAt - '0'));
                i3 = i;
                c2 = '0';
            }
            if (cCharAt == '.') {
                this.matchStat = -1;
                return 0L;
            }
            if (z2) {
                if (cCharAt != '\"') {
                    this.matchStat = -1;
                    return 0L;
                }
                cCharAt = charAt(i);
                i++;
            }
            if (j >= 0 || (j == Long.MIN_VALUE && z3)) {
                z = true;
            }
            if (!z) {
                this.matchStat = -1;
                return 0L;
            }
            while (cCharAt != c) {
                if (isWhitespace(cCharAt)) {
                    cCharAt = charAt(i);
                    i++;
                } else {
                    this.matchStat = -1;
                    return j;
                }
            }
            this.bp = i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return z3 ? -j : j;
        }
        if (cCharAt2 == 'n') {
            int i6 = i3 + 1;
            if (charAt(i3) == 'u') {
                int i7 = i6 + 1;
                if (charAt(i6) == 'l') {
                    int i8 = i7 + 1;
                    if (charAt(i7) == 'l') {
                        this.matchStat = 5;
                        int i9 = i8 + 1;
                        char cCharAt5 = charAt(i8);
                        if (z2 && cCharAt5 == '\"') {
                            int i10 = i9 + 1;
                            char cCharAt6 = charAt(i9);
                            i9 = i10;
                            cCharAt5 = cCharAt6;
                        }
                        while (cCharAt5 != ',') {
                            if (cCharAt5 == ']') {
                                this.bp = i9;
                                this.ch = charAt(this.bp);
                                this.matchStat = 5;
                                this.token = 15;
                                return 0L;
                            }
                            if (isWhitespace(cCharAt5)) {
                                int i11 = i9 + 1;
                                char cCharAt7 = charAt(i9);
                                i9 = i11;
                                cCharAt5 = cCharAt7;
                            } else {
                                this.matchStat = -1;
                                return 0L;
                            }
                        }
                        this.bp = i9;
                        this.ch = charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 16;
                        return 0L;
                    }
                }
            }
        }
        this.matchStat = -1;
        return 0L;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public Date scanDate(char c) {
        boolean z;
        int i;
        char cCharAt;
        long j;
        char cCharAt2;
        Date date;
        int i2;
        this.matchStat = 0;
        int i3 = this.bp;
        char c2 = this.ch;
        int i4 = this.bp;
        int i5 = i4 + 1;
        char cCharAt3 = charAt(i4);
        if (cCharAt3 == '\"') {
            int iIndexOf = indexOf('\"', i5);
            if (iIndexOf == -1) {
                throw new JSONException("unclosed str");
            }
            this.bp = i5;
            if (scanISO8601DateIfMatch(false, iIndexOf - i5)) {
                date = this.calendar.getTime();
                cCharAt2 = charAt(iIndexOf + 1);
                this.bp = i3;
                while (cCharAt2 != ',' && cCharAt2 != ']') {
                    if (isWhitespace(cCharAt2)) {
                        iIndexOf++;
                        cCharAt2 = charAt(iIndexOf + 1);
                    } else {
                        this.bp = i3;
                        this.ch = c2;
                        this.matchStat = -1;
                        return null;
                    }
                }
                this.bp = iIndexOf + 1;
                this.ch = cCharAt2;
            } else {
                this.bp = i3;
                this.ch = c2;
                this.matchStat = -1;
                return null;
            }
        } else {
            char c3 = '9';
            char c4 = '0';
            if (cCharAt3 != '-' && (cCharAt3 < '0' || cCharAt3 > '9')) {
                if (cCharAt3 == 'n') {
                    int i6 = i5 + 1;
                    if (charAt(i5) == 'u') {
                        int i7 = i6 + 1;
                        if (charAt(i6) == 'l') {
                            int i8 = i7 + 1;
                            if (charAt(i7) == 'l') {
                                cCharAt2 = charAt(i8);
                                this.bp = i8;
                                date = null;
                            }
                        }
                    }
                }
                this.bp = i3;
                this.ch = c2;
                this.matchStat = -1;
                return null;
            }
            if (cCharAt3 == '-') {
                i = i5 + 1;
                cCharAt3 = charAt(i5);
                z = true;
            } else {
                z = false;
                i = i5;
            }
            if (cCharAt3 < '0' || cCharAt3 > '9') {
                cCharAt = cCharAt3;
                j = 0;
            } else {
                j = cCharAt3 - '0';
                while (true) {
                    i2 = i + 1;
                    cCharAt = charAt(i);
                    if (cCharAt < c4 || cCharAt > c3) {
                        break;
                    }
                    j = (j * 10) + ((long) (cCharAt - '0'));
                    i = i2;
                    c3 = '9';
                    c4 = '0';
                }
                if (cCharAt == ',' || cCharAt == ']') {
                    this.bp = i2 - 1;
                }
            }
            if (j < 0) {
                this.bp = i3;
                this.ch = c2;
                this.matchStat = -1;
                return null;
            }
            if (z) {
                j = -j;
            }
            cCharAt2 = cCharAt;
            date = new Date(j);
        }
        if (cCharAt2 == ',') {
            int i9 = this.bp + 1;
            this.bp = i9;
            this.ch = charAt(i9);
            this.matchStat = 3;
            return date;
        }
        int i10 = this.bp + 1;
        this.bp = i10;
        char cCharAt4 = charAt(i10);
        if (cCharAt4 == ',') {
            this.token = 16;
            int i11 = this.bp + 1;
            this.bp = i11;
            this.ch = charAt(i11);
        } else if (cCharAt4 == ']') {
            this.token = 15;
            int i12 = this.bp + 1;
            this.bp = i12;
            this.ch = charAt(i12);
        } else if (cCharAt4 == '}') {
            this.token = 13;
            int i13 = this.bp + 1;
            this.bp = i13;
            this.ch = charAt(i13);
        } else if (cCharAt4 == 26) {
            this.ch = JSONLexer.EOI;
            this.token = 20;
        } else {
            this.bp = i3;
            this.ch = c2;
            this.matchStat = -1;
            return null;
        }
        this.matchStat = 4;
        return date;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    protected final void arrayCopy(int i, char[] cArr, int i2, int i3) {
        this.text.getChars(i, i3 + i, cArr, i2);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public String info() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int i2 = 1;
        int i3 = 1;
        while (i < this.bp) {
            if (this.text.charAt(i) == '\n') {
                i2++;
                i3 = 1;
            }
            i++;
            i3++;
        }
        sb.append("pos ");
        sb.append(this.bp);
        sb.append(", line ");
        sb.append(i2);
        sb.append(", column ");
        sb.append(i3);
        if (this.text.length() < 65535) {
            sb.append(this.text);
        } else {
            sb.append(this.text.substring(0, 65535));
        }
        return sb.toString();
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public String[] scanFieldStringArray(char[] cArr, int i, SymbolTable symbolTable) {
        int i2;
        char cCharAt;
        int i3 = this.bp;
        char c = this.ch;
        while (isWhitespace(this.ch)) {
            next();
        }
        if (cArr != null) {
            this.matchStat = 0;
            if (!charArrayCompare(cArr)) {
                this.matchStat = -2;
                return null;
            }
            int length = this.bp + cArr.length;
            int i4 = length + 1;
            char cCharAt2 = this.text.charAt(length);
            while (isWhitespace(cCharAt2)) {
                cCharAt2 = this.text.charAt(i4);
                i4++;
            }
            if (cCharAt2 == ':') {
                i2 = i4 + 1;
                cCharAt = this.text.charAt(i4);
                while (isWhitespace(cCharAt)) {
                    cCharAt = this.text.charAt(i2);
                    i2++;
                }
            } else {
                this.matchStat = -1;
                return null;
            }
        } else {
            i2 = this.bp + 1;
            cCharAt = this.ch;
        }
        if (cCharAt == '[') {
            this.bp = i2;
            this.ch = this.text.charAt(this.bp);
            String[] strArr = i >= 0 ? new String[i] : new String[4];
            int i5 = 0;
            while (true) {
                if (isWhitespace(this.ch)) {
                    next();
                } else {
                    if (this.ch != '\"') {
                        this.bp = i3;
                        this.ch = c;
                        this.matchStat = -1;
                        return null;
                    }
                    String strScanSymbol = scanSymbol(symbolTable, '\"');
                    if (i5 == strArr.length) {
                        String[] strArr2 = new String[strArr.length + (strArr.length >> 1) + 1];
                        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
                        strArr = strArr2;
                    }
                    int i6 = i5 + 1;
                    strArr[i5] = strScanSymbol;
                    while (isWhitespace(this.ch)) {
                        next();
                    }
                    if (this.ch == ',') {
                        next();
                        i5 = i6;
                    } else {
                        if (strArr.length != i6) {
                            String[] strArr3 = new String[i6];
                            System.arraycopy(strArr, 0, strArr3, 0, i6);
                            strArr = strArr3;
                        }
                        while (isWhitespace(this.ch)) {
                            next();
                        }
                        if (this.ch == ']') {
                            next();
                            return strArr;
                        }
                        this.bp = i3;
                        this.ch = c;
                        this.matchStat = -1;
                        return null;
                    }
                }
            }
        } else {
            if (cCharAt == 'n' && this.text.startsWith("ull", this.bp + 1)) {
                this.bp += 4;
                this.ch = this.text.charAt(this.bp);
                return null;
            }
            this.matchStat = -1;
            return null;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public boolean matchField2(char[] cArr) {
        while (isWhitespace(this.ch)) {
            next();
        }
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return false;
        }
        int length = this.bp + cArr.length;
        int i = length + 1;
        char cCharAt = this.text.charAt(length);
        while (isWhitespace(cCharAt)) {
            cCharAt = this.text.charAt(i);
            i++;
        }
        if (cCharAt == ':') {
            this.bp = i;
            this.ch = charAt(this.bp);
            return true;
        }
        this.matchStat = -2;
        return false;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final void skipObject() {
        int i = this.bp;
        boolean z = false;
        int i2 = 0;
        while (i < this.text.length()) {
            char cCharAt = this.text.charAt(i);
            if (cCharAt == '\\') {
                if (i >= this.len - 1) {
                    this.ch = cCharAt;
                    this.bp = i;
                    throw new JSONException("illegal str, " + info());
                }
                i++;
            } else if (cCharAt == '\"') {
                z = !z;
            } else if (cCharAt == '{') {
                if (!z) {
                    i2++;
                }
            } else if (cCharAt == '}' && !z && (i2 = i2 - 1) == -1) {
                int i3 = i + 1;
                this.bp = i3;
                this.ch = this.text.charAt(i3);
                if (this.ch == ',') {
                    this.token = 16;
                    int i4 = this.bp + 1;
                    this.bp = i4;
                    this.ch = i4 >= this.text.length() ? JSONLexer.EOI : this.text.charAt(i4);
                    return;
                }
                if (this.ch == '}') {
                    this.token = 13;
                    next();
                    return;
                } else if (this.ch == ']') {
                    this.token = 15;
                    next();
                    return;
                } else {
                    nextToken(16);
                    return;
                }
            }
            i++;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final void skipArray() {
        int i = this.bp;
        boolean z = false;
        int i2 = 0;
        while (i < this.text.length()) {
            char cCharAt = this.text.charAt(i);
            if (cCharAt == '\\') {
                if (i >= this.len - 1) {
                    this.ch = cCharAt;
                    this.bp = i;
                    throw new JSONException("illegal str, " + info());
                }
                i++;
            } else if (cCharAt == '\"') {
                z = !z;
            } else if (cCharAt == '[') {
                if (!z) {
                    i2++;
                }
            } else if (cCharAt == ']' && !z && (i2 = i2 - 1) == -1) {
                int i3 = i + 1;
                this.bp = i3;
                this.ch = this.text.charAt(i3);
                nextToken(16);
                return;
            }
            i++;
        }
    }

    public final void skipString() {
        if (this.ch == '\"') {
            int i = this.bp;
            while (true) {
                i++;
                if (i < this.text.length()) {
                    char cCharAt = this.text.charAt(i);
                    if (cCharAt == '\\') {
                        if (i < this.len - 1) {
                            i++;
                        }
                    } else if (cCharAt == '\"') {
                        String str = this.text;
                        int i2 = i + 1;
                        this.bp = i2;
                        this.ch = str.charAt(i2);
                        return;
                    }
                } else {
                    throw new JSONException("unclosed str");
                }
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public boolean seekArrayToItem(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("index must > 0, but " + i);
        }
        if (this.token == 20) {
            return false;
        }
        if (this.token != 14) {
            throw new UnsupportedOperationException();
        }
        int i2 = 0;
        while (true) {
            boolean z = true;
            if (i2 < i) {
                skipWhitespace();
                if (this.ch == '\"' || this.ch == '\'') {
                    skipString();
                    if (this.ch == ',') {
                        next();
                    } else {
                        if (this.ch == ']') {
                            next();
                            nextToken(16);
                            return false;
                        }
                        throw new JSONException("illegal json.");
                    }
                } else {
                    if (this.ch == '{') {
                        next();
                        this.token = 12;
                        skipObject();
                    } else if (this.ch == '[') {
                        next();
                        this.token = 14;
                        skipArray();
                    } else {
                        int i3 = this.bp + 1;
                        while (true) {
                            if (i3 >= this.text.length()) {
                                z = false;
                                break;
                            }
                            char cCharAt = this.text.charAt(i3);
                            if (cCharAt == ',') {
                                this.bp = i3 + 1;
                                this.ch = charAt(this.bp);
                                break;
                            }
                            if (cCharAt == ']') {
                                this.bp = i3 + 1;
                                this.ch = charAt(this.bp);
                                nextToken();
                                return false;
                            }
                            i3++;
                        }
                        if (!z) {
                            throw new JSONException("illegal json.");
                        }
                    }
                    if (this.token != 16) {
                        if (this.token == 15) {
                            return false;
                        }
                        throw new UnsupportedOperationException();
                    }
                }
                i2++;
            } else {
                nextToken();
                return true;
            }
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public int seekObjectToField(long j, boolean z) {
        if (this.token == 20) {
            return -1;
        }
        if (this.token != 13) {
            int i = 15;
            if (this.token != 15) {
                if (this.token != 12 && this.token != 16) {
                    throw new UnsupportedOperationException(JSONToken.name(this.token));
                }
                while (this.ch != '}') {
                    char c = this.ch;
                    char cCharAt = JSONLexer.EOI;
                    if (c == 26) {
                        return -1;
                    }
                    if (this.ch != '\"') {
                        skipWhitespace();
                    }
                    if (this.ch == '\"') {
                        long j2 = -3750763034362895579L;
                        int i2 = this.bp + 1;
                        while (i2 < this.text.length()) {
                            char cCharAt2 = this.text.charAt(i2);
                            if (cCharAt2 == '\\') {
                                i2++;
                                if (i2 == this.text.length()) {
                                    throw new JSONException("unclosed str, " + info());
                                }
                                cCharAt2 = this.text.charAt(i2);
                            }
                            if (cCharAt2 == '\"') {
                                this.bp = i2 + 1;
                                this.ch = this.bp >= this.text.length() ? (char) 26 : this.text.charAt(this.bp);
                                break;
                            }
                            j2 = (j2 ^ ((long) cCharAt2)) * 1099511628211L;
                            i2++;
                        }
                        if (j2 == j) {
                            if (this.ch != ':') {
                                skipWhitespace();
                            }
                            if (this.ch != ':') {
                                return 3;
                            }
                            int i3 = this.bp + 1;
                            this.bp = i3;
                            this.ch = i3 >= this.text.length() ? (char) 26 : this.text.charAt(i3);
                            if (this.ch == ',') {
                                int i4 = this.bp + 1;
                                this.bp = i4;
                                if (i4 < this.text.length()) {
                                    cCharAt = this.text.charAt(i4);
                                }
                                this.ch = cCharAt;
                                this.token = 16;
                                return 3;
                            }
                            if (this.ch == ']') {
                                int i5 = this.bp + 1;
                                this.bp = i5;
                                if (i5 < this.text.length()) {
                                    cCharAt = this.text.charAt(i5);
                                }
                                this.ch = cCharAt;
                                this.token = i;
                                return 3;
                            }
                            if (this.ch == '}') {
                                int i6 = this.bp + 1;
                                this.bp = i6;
                                if (i6 < this.text.length()) {
                                    cCharAt = this.text.charAt(i6);
                                }
                                this.ch = cCharAt;
                                this.token = 13;
                                return 3;
                            }
                            if (this.ch >= '0' && this.ch <= '9') {
                                this.sp = 0;
                                this.pos = this.bp;
                                scanNumber();
                                return 3;
                            }
                            nextToken(2);
                            return 3;
                        }
                        if (this.ch != ':') {
                            skipWhitespace();
                        }
                        if (this.ch == ':') {
                            int i7 = this.bp + 1;
                            this.bp = i7;
                            this.ch = i7 >= this.text.length() ? (char) 26 : this.text.charAt(i7);
                            if (this.ch != '\"' && this.ch != '\'' && this.ch != '{' && this.ch != '[' && this.ch != '0' && this.ch != '1' && this.ch != '2' && this.ch != '3' && this.ch != '4' && this.ch != '5' && this.ch != '6' && this.ch != '7' && this.ch != '8' && this.ch != '9' && this.ch != '+' && this.ch != '-') {
                                skipWhitespace();
                            }
                            if (this.ch == '-' || this.ch == '+' || (this.ch >= '0' && this.ch <= '9')) {
                                next();
                                while (this.ch >= '0' && this.ch <= '9') {
                                    next();
                                }
                                if (this.ch == '.') {
                                    next();
                                    while (this.ch >= '0' && this.ch <= '9') {
                                        next();
                                    }
                                }
                                if (this.ch == 'E' || this.ch == 'e') {
                                    next();
                                    if (this.ch == '-' || this.ch == '+') {
                                        next();
                                    }
                                    while (this.ch >= '0' && this.ch <= '9') {
                                        next();
                                    }
                                }
                                if (this.ch != ',') {
                                    skipWhitespace();
                                }
                                if (this.ch == ',') {
                                    next();
                                }
                            } else if (this.ch == '\"') {
                                skipString();
                                if (this.ch != ',' && this.ch != '}') {
                                    skipWhitespace();
                                }
                                if (this.ch == ',') {
                                    next();
                                }
                            } else if (this.ch == 't') {
                                next();
                                if (this.ch == 'r') {
                                    next();
                                    if (this.ch == 'u') {
                                        next();
                                        if (this.ch == 'e') {
                                            next();
                                        }
                                    }
                                }
                                if (this.ch != ',' && this.ch != '}') {
                                    skipWhitespace();
                                }
                                if (this.ch == ',') {
                                    next();
                                }
                            } else if (this.ch == 'n') {
                                next();
                                if (this.ch == 'u') {
                                    next();
                                    if (this.ch == 'l') {
                                        next();
                                        if (this.ch == 'l') {
                                            next();
                                        }
                                    }
                                }
                                if (this.ch != ',' && this.ch != '}') {
                                    skipWhitespace();
                                }
                                if (this.ch == ',') {
                                    next();
                                }
                            } else if (this.ch == 'f') {
                                next();
                                if (this.ch == 'a') {
                                    next();
                                    if (this.ch == 'l') {
                                        next();
                                        if (this.ch == 's') {
                                            next();
                                            if (this.ch == 'e') {
                                                next();
                                            }
                                        }
                                    }
                                }
                                if (this.ch != ',' && this.ch != '}') {
                                    skipWhitespace();
                                }
                                if (this.ch == ',') {
                                    next();
                                }
                            } else if (this.ch == '{') {
                                int i8 = this.bp + 1;
                                this.bp = i8;
                                this.ch = i8 >= this.text.length() ? JSONLexer.EOI : this.text.charAt(i8);
                                if (z) {
                                    this.token = 12;
                                    return 1;
                                }
                                skipObject();
                                if (this.token == 13) {
                                    return -1;
                                }
                            } else if (this.ch == '[') {
                                next();
                                if (z) {
                                    this.token = 14;
                                    return 2;
                                }
                                skipArray();
                                if (this.token == 13) {
                                    return -1;
                                }
                            } else {
                                throw new UnsupportedOperationException();
                            }
                            i = 15;
                        } else {
                            throw new JSONException("illegal json, " + info());
                        }
                    } else {
                        throw new UnsupportedOperationException();
                    }
                }
                next();
                nextToken();
                return -1;
            }
        }
        nextToken();
        return -1;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public int seekObjectToField(long[] jArr) {
        if (this.token != 12 && this.token != 16) {
            throw new UnsupportedOperationException();
        }
        while (this.ch != '}') {
            char c = this.ch;
            char cCharAt = JSONLexer.EOI;
            if (c == 26) {
                this.matchStat = -1;
                return -1;
            }
            if (this.ch != '\"') {
                skipWhitespace();
            }
            if (this.ch == '\"') {
                long j = -3750763034362895579L;
                int i = this.bp;
                while (true) {
                    i++;
                    if (i >= this.text.length()) {
                        break;
                    }
                    char cCharAt2 = this.text.charAt(i);
                    if (cCharAt2 == '\\') {
                        i++;
                        if (i == this.text.length()) {
                            throw new JSONException("unclosed str, " + info());
                        }
                        cCharAt2 = this.text.charAt(i);
                    }
                    if (cCharAt2 == '\"') {
                        this.bp = i + 1;
                        this.ch = this.bp >= this.text.length() ? (char) 26 : this.text.charAt(this.bp);
                        break;
                    }
                    j = (j ^ ((long) cCharAt2)) * 1099511628211L;
                }
                int i2 = 0;
                while (true) {
                    if (i2 >= jArr.length) {
                        i2 = -1;
                        break;
                    }
                    if (j == jArr[i2]) {
                        break;
                    }
                    i2++;
                }
                if (i2 != -1) {
                    if (this.ch != ':') {
                        skipWhitespace();
                    }
                    if (this.ch == ':') {
                        int i3 = this.bp + 1;
                        this.bp = i3;
                        this.ch = i3 >= this.text.length() ? (char) 26 : this.text.charAt(i3);
                        if (this.ch == ',') {
                            int i4 = this.bp + 1;
                            this.bp = i4;
                            if (i4 < this.text.length()) {
                                cCharAt = this.text.charAt(i4);
                            }
                            this.ch = cCharAt;
                            this.token = 16;
                        } else if (this.ch == ']') {
                            int i5 = this.bp + 1;
                            this.bp = i5;
                            if (i5 < this.text.length()) {
                                cCharAt = this.text.charAt(i5);
                            }
                            this.ch = cCharAt;
                            this.token = 15;
                        } else if (this.ch == '}') {
                            int i6 = this.bp + 1;
                            this.bp = i6;
                            if (i6 < this.text.length()) {
                                cCharAt = this.text.charAt(i6);
                            }
                            this.ch = cCharAt;
                            this.token = 13;
                        } else if (this.ch >= '0' && this.ch <= '9') {
                            this.sp = 0;
                            this.pos = this.bp;
                            scanNumber();
                        } else {
                            nextToken(2);
                        }
                    }
                    this.matchStat = 3;
                    return i2;
                }
                if (this.ch != ':') {
                    skipWhitespace();
                }
                if (this.ch == ':') {
                    int i7 = this.bp + 1;
                    this.bp = i7;
                    this.ch = i7 >= this.text.length() ? (char) 26 : this.text.charAt(i7);
                    if (this.ch != '\"' && this.ch != '\'' && this.ch != '{' && this.ch != '[' && this.ch != '0' && this.ch != '1' && this.ch != '2' && this.ch != '3' && this.ch != '4' && this.ch != '5' && this.ch != '6' && this.ch != '7' && this.ch != '8' && this.ch != '9' && this.ch != '+' && this.ch != '-') {
                        skipWhitespace();
                    }
                    if (this.ch == '-' || this.ch == '+' || (this.ch >= '0' && this.ch <= '9')) {
                        next();
                        while (this.ch >= '0' && this.ch <= '9') {
                            next();
                        }
                        if (this.ch == '.') {
                            next();
                            while (this.ch >= '0' && this.ch <= '9') {
                                next();
                            }
                        }
                        if (this.ch == 'E' || this.ch == 'e') {
                            next();
                            if (this.ch == '-' || this.ch == '+') {
                                next();
                            }
                            while (this.ch >= '0' && this.ch <= '9') {
                                next();
                            }
                        }
                        if (this.ch != ',') {
                            skipWhitespace();
                        }
                        if (this.ch == ',') {
                            next();
                        }
                    } else if (this.ch == '\"') {
                        skipString();
                        if (this.ch != ',' && this.ch != '}') {
                            skipWhitespace();
                        }
                        if (this.ch == ',') {
                            next();
                        }
                    } else if (this.ch == '{') {
                        int i8 = this.bp + 1;
                        this.bp = i8;
                        if (i8 < this.text.length()) {
                            cCharAt = this.text.charAt(i8);
                        }
                        this.ch = cCharAt;
                        skipObject();
                    } else if (this.ch == '[') {
                        next();
                        skipArray();
                    } else {
                        throw new UnsupportedOperationException();
                    }
                } else {
                    throw new JSONException("illegal json, " + info());
                }
            } else {
                throw new UnsupportedOperationException();
            }
        }
        next();
        nextToken();
        this.matchStat = -1;
        return -1;
    }
}
