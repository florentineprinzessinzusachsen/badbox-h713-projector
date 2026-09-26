package a1;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class b implements Closeable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final StringReader f11d;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f18k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f19l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f20m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int[] f21n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String[] f23p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int[] f24q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f25r = 2;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final char[] f12e = new char[1024];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f13f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f14g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f15h = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f16i = 0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f17j = 0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f22o = 1;

    static {
        a.f9e = new a(0);
    }

    public b(StringReader stringReader) {
        int[] iArr = new int[32];
        this.f21n = iArr;
        iArr[0] = 6;
        this.f23p = new String[32];
        this.f24q = new int[32];
        this.f11d = stringReader;
    }

    public final void A() throws IOException {
        int iL = this.f17j;
        if (iL == 0) {
            iL = l();
        }
        if (iL != 4) {
            throw o0("END_ARRAY");
        }
        int i4 = this.f22o;
        this.f22o = i4 - 1;
        int[] iArr = this.f24q;
        int i5 = i4 - 2;
        iArr[i5] = iArr[i5] + 1;
        this.f17j = 0;
    }

    public final void C() throws IOException {
        int iL = this.f17j;
        if (iL == 0) {
            iL = l();
        }
        if (iL != 2) {
            throw o0("END_OBJECT");
        }
        int i4 = this.f22o;
        int i5 = i4 - 1;
        this.f22o = i5;
        this.f23p[i5] = null;
        int[] iArr = this.f24q;
        int i6 = i4 - 2;
        iArr[i6] = iArr[i6] + 1;
        this.f17j = 0;
    }

    public final boolean J(int i4) throws IOException {
        int i5;
        int i6;
        int i7 = this.f16i;
        int i8 = this.f13f;
        this.f16i = i7 - i8;
        int i9 = this.f14g;
        char[] cArr = this.f12e;
        if (i9 != i8) {
            int i10 = i9 - i8;
            this.f14g = i10;
            System.arraycopy(cArr, i8, cArr, 0, i10);
        } else {
            this.f14g = 0;
        }
        this.f13f = 0;
        do {
            int i11 = this.f14g;
            int i12 = this.f11d.read(cArr, i11, cArr.length - i11);
            if (i12 == -1) {
                return false;
            }
            i5 = this.f14g + i12;
            this.f14g = i5;
            if (this.f15h == 0 && (i6 = this.f16i) == 0 && i5 > 0 && cArr[0] == 65279) {
                this.f13f++;
                this.f16i = i6 + 1;
                i4++;
            }
        } while (i5 < i4);
        return true;
    }

    public final String K(boolean z3) {
        StringBuilder sb = new StringBuilder("$");
        int i4 = 0;
        while (true) {
            int i5 = this.f22o;
            if (i4 >= i5) {
                return sb.toString();
            }
            int i6 = this.f21n[i4];
            switch (i6) {
                case 1:
                case 2:
                    int i7 = this.f24q[i4];
                    if (z3 && i7 > 0 && i4 == i5 - 1) {
                        i7--;
                    }
                    sb.append('[');
                    sb.append(i7);
                    sb.append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    sb.append('.');
                    String str = this.f23p[i4];
                    if (str != null) {
                        sb.append(str);
                    }
                    break;
                case 6:
                case 7:
                case 8:
                    break;
                default:
                    throw new AssertionError(c.c(i6, "Unknown scope value: "));
            }
            i4++;
        }
    }

    public final boolean S() throws IOException {
        int iL = this.f17j;
        if (iL == 0) {
            iL = l();
        }
        return (iL == 2 || iL == 4 || iL == 17) ? false : true;
    }

    public final boolean T(char c4) throws e {
        if (c4 == '\t' || c4 == '\n' || c4 == '\f' || c4 == '\r' || c4 == ' ') {
            return false;
        }
        if (c4 != '#') {
            if (c4 == ',') {
                return false;
            }
            if (c4 != '/' && c4 != '=') {
                if (c4 == '{' || c4 == '}' || c4 == ':') {
                    return false;
                }
                if (c4 != ';') {
                    switch (c4) {
                        case '[':
                        case ']':
                            return false;
                        case '\\':
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        k();
        return false;
    }

    public final String U() {
        return " at line " + (this.f15h + 1) + " column " + ((this.f13f - this.f16i) + 1) + " path " + K(false);
    }

    public final boolean V() throws IOException {
        int iL = this.f17j;
        if (iL == 0) {
            iL = l();
        }
        if (iL == 5) {
            this.f17j = 0;
            int[] iArr = this.f24q;
            int i4 = this.f22o - 1;
            iArr[i4] = iArr[i4] + 1;
            return true;
        }
        if (iL != 6) {
            throw o0("a boolean");
        }
        this.f17j = 0;
        int[] iArr2 = this.f24q;
        int i5 = this.f22o - 1;
        iArr2[i5] = iArr2[i5] + 1;
        return false;
    }

    public final double W() throws IOException {
        int iL = this.f17j;
        if (iL == 0) {
            iL = l();
        }
        if (iL == 15) {
            this.f17j = 0;
            int[] iArr = this.f24q;
            int i4 = this.f22o - 1;
            iArr[i4] = iArr[i4] + 1;
            return this.f18k;
        }
        if (iL == 16) {
            this.f20m = new String(this.f12e, this.f13f, this.f19l);
            this.f13f += this.f19l;
        } else if (iL == 8 || iL == 9) {
            this.f20m = c0(iL == 8 ? '\'' : '\"');
        } else if (iL == 10) {
            this.f20m = e0();
        } else if (iL != 11) {
            throw o0("a double");
        }
        this.f17j = 11;
        double d4 = Double.parseDouble(this.f20m);
        if (this.f25r != 1 && (Double.isNaN(d4) || Double.isInfinite(d4))) {
            n0("JSON forbids NaN and infinities: " + d4);
            throw null;
        }
        this.f20m = null;
        this.f17j = 0;
        int[] iArr2 = this.f24q;
        int i5 = this.f22o - 1;
        iArr2[i5] = iArr2[i5] + 1;
        return d4;
    }

    public final int X() throws IOException {
        int iL = this.f17j;
        if (iL == 0) {
            iL = l();
        }
        if (iL == 15) {
            long j4 = this.f18k;
            int i4 = (int) j4;
            if (j4 != i4) {
                throw new NumberFormatException("Expected an int but was " + this.f18k + U());
            }
            this.f17j = 0;
            int[] iArr = this.f24q;
            int i5 = this.f22o - 1;
            iArr[i5] = iArr[i5] + 1;
            return i4;
        }
        if (iL == 16) {
            this.f20m = new String(this.f12e, this.f13f, this.f19l);
            this.f13f += this.f19l;
        } else {
            if (iL != 8 && iL != 9 && iL != 10) {
                throw o0("an int");
            }
            if (iL == 10) {
                this.f20m = e0();
            } else {
                this.f20m = c0(iL == 8 ? '\'' : '\"');
            }
            try {
                int i6 = Integer.parseInt(this.f20m);
                this.f17j = 0;
                int[] iArr2 = this.f24q;
                int i7 = this.f22o - 1;
                iArr2[i7] = iArr2[i7] + 1;
                return i6;
            } catch (NumberFormatException unused) {
            }
        }
        this.f17j = 11;
        double d4 = Double.parseDouble(this.f20m);
        int i8 = (int) d4;
        if (i8 != d4) {
            throw new NumberFormatException("Expected an int but was " + this.f20m + U());
        }
        this.f20m = null;
        this.f17j = 0;
        int[] iArr3 = this.f24q;
        int i9 = this.f22o - 1;
        iArr3[i9] = iArr3[i9] + 1;
        return i8;
    }

    public final long Y() throws IOException {
        int iL = this.f17j;
        if (iL == 0) {
            iL = l();
        }
        if (iL == 15) {
            this.f17j = 0;
            int[] iArr = this.f24q;
            int i4 = this.f22o - 1;
            iArr[i4] = iArr[i4] + 1;
            return this.f18k;
        }
        if (iL == 16) {
            this.f20m = new String(this.f12e, this.f13f, this.f19l);
            this.f13f += this.f19l;
        } else {
            if (iL != 8 && iL != 9 && iL != 10) {
                throw o0("a long");
            }
            if (iL == 10) {
                this.f20m = e0();
            } else {
                this.f20m = c0(iL == 8 ? '\'' : '\"');
            }
            try {
                long j4 = Long.parseLong(this.f20m);
                this.f17j = 0;
                int[] iArr2 = this.f24q;
                int i5 = this.f22o - 1;
                iArr2[i5] = iArr2[i5] + 1;
                return j4;
            } catch (NumberFormatException unused) {
            }
        }
        this.f17j = 11;
        double d4 = Double.parseDouble(this.f20m);
        long j5 = (long) d4;
        if (j5 != d4) {
            throw new NumberFormatException("Expected a long but was " + this.f20m + U());
        }
        this.f20m = null;
        this.f17j = 0;
        int[] iArr3 = this.f24q;
        int i6 = this.f22o - 1;
        iArr3[i6] = iArr3[i6] + 1;
        return j5;
    }

    public final String Z() throws IOException {
        String strC0;
        int iL = this.f17j;
        if (iL == 0) {
            iL = l();
        }
        if (iL == 14) {
            strC0 = e0();
        } else if (iL == 12) {
            strC0 = c0('\'');
        } else {
            if (iL != 13) {
                throw o0("a name");
            }
            strC0 = c0('\"');
        }
        this.f17j = 0;
        this.f23p[this.f22o - 1] = strC0;
        return strC0;
    }

    public final int a0(boolean z3) throws IOException {
        int i4 = this.f13f;
        int i5 = this.f14g;
        while (true) {
            if (i4 == i5) {
                this.f13f = i4;
                if (!J(1)) {
                    if (!z3) {
                        return -1;
                    }
                    throw new EOFException("End of input" + U());
                }
                i4 = this.f13f;
                i5 = this.f14g;
            }
            int i6 = i4 + 1;
            char[] cArr = this.f12e;
            char c4 = cArr[i4];
            if (c4 == '\n') {
                this.f15h++;
                this.f16i = i6;
            } else if (c4 != ' ' && c4 != '\r' && c4 != '\t') {
                if (c4 == '/') {
                    this.f13f = i6;
                    if (i6 == i5) {
                        this.f13f = i4;
                        boolean zJ = J(2);
                        this.f13f++;
                        if (!zJ) {
                        }
                        return c4;
                    }
                    k();
                    int i7 = this.f13f;
                    char c5 = cArr[i7];
                    if (c5 == '*') {
                        this.f13f = i7 + 1;
                        while (true) {
                            if (this.f13f + 2 > this.f14g && !J(2)) {
                                n0("Unterminated comment");
                                throw null;
                            }
                            int i8 = this.f13f;
                            if (cArr[i8] != '\n') {
                                int i9 = 0;
                                while (true) {
                                    if (i9 >= 2) {
                                        i4 = this.f13f + 2;
                                        i5 = this.f14g;
                                        break;
                                    }
                                    if (cArr[this.f13f + i9] != "*/".charAt(i9)) {
                                        break;
                                    }
                                    i9++;
                                }
                            } else {
                                this.f15h++;
                                this.f16i = i8 + 1;
                            }
                            this.f13f++;
                        }
                    } else {
                        if (c5 != '/') {
                            return c4;
                        }
                        this.f13f = i7 + 1;
                        k0();
                        i4 = this.f13f;
                        i5 = this.f14g;
                    }
                } else {
                    if (c4 != '#') {
                        this.f13f = i6;
                        return c4;
                    }
                    this.f13f = i6;
                    k();
                    k0();
                    i4 = this.f13f;
                    i5 = this.f14g;
                }
            }
            i4 = i6;
        }
    }

    public final void b() throws IOException {
        int iL = this.f17j;
        if (iL == 0) {
            iL = l();
        }
        if (iL != 3) {
            throw o0("BEGIN_ARRAY");
        }
        g0(1);
        this.f24q[this.f22o - 1] = 0;
        this.f17j = 0;
    }

    public final void b0() throws IOException {
        int iL = this.f17j;
        if (iL == 0) {
            iL = l();
        }
        if (iL != 7) {
            throw o0("null");
        }
        this.f17j = 0;
        int[] iArr = this.f24q;
        int i4 = this.f22o - 1;
        iArr[i4] = iArr[i4] + 1;
    }

    public final void c() throws IOException {
        int iL = this.f17j;
        if (iL == 0) {
            iL = l();
        }
        if (iL != 1) {
            throw o0("BEGIN_OBJECT");
        }
        g0(3);
        this.f17j = 0;
    }

    public final String c0(char c4) throws e {
        int i4;
        char[] cArr;
        StringBuilder sb = null;
        do {
            int i5 = this.f13f;
            int i6 = this.f14g;
            while (true) {
                int i7 = i6;
                i4 = i5;
                while (true) {
                    cArr = this.f12e;
                    if (i5 < i7) {
                        int i8 = i5 + 1;
                        char c5 = cArr[i5];
                        if (this.f25r == 3 && c5 < ' ') {
                            n0("Unescaped control characters (\\u0000-\\u001F) are not allowed in strict mode");
                            throw null;
                        }
                        if (c5 == c4) {
                            this.f13f = i8;
                            int i9 = (i8 - i4) - 1;
                            if (sb == null) {
                                return new String(cArr, i4, i9);
                            }
                            sb.append(cArr, i4, i9);
                            return sb.toString();
                        }
                        if (c5 == '\\') {
                            this.f13f = i8;
                            int i10 = i8 - i4;
                            int i11 = i10 - 1;
                            if (sb == null) {
                                sb = new StringBuilder(Math.max(i10 * 2, 16));
                            }
                            sb.append(cArr, i4, i11);
                            sb.append(h0());
                            i5 = this.f13f;
                            i6 = this.f14g;
                        } else {
                            if (c5 == '\n') {
                                this.f15h++;
                                this.f16i = i8;
                            }
                            i5 = i8;
                        }
                    }
                }
            }
            if (sb == null) {
                sb = new StringBuilder(Math.max((i5 - i4) * 2, 16));
            }
            sb.append(cArr, i4, i5 - i4);
            this.f13f = i5;
        } while (J(1));
        n0("Unterminated string");
        throw null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.f17j = 0;
        this.f21n[0] = 8;
        this.f22o = 1;
        this.f11d.close();
    }

    public final String d0() throws IOException {
        String str;
        int iL = this.f17j;
        if (iL == 0) {
            iL = l();
        }
        if (iL == 10) {
            str = e0();
        } else if (iL == 8) {
            str = c0('\'');
        } else if (iL == 9) {
            str = c0('\"');
        } else if (iL == 11) {
            str = this.f20m;
            this.f20m = null;
        } else if (iL == 15) {
            str = Long.toString(this.f18k);
        } else {
            if (iL != 16) {
                throw o0("a string");
            }
            str = new String(this.f12e, this.f13f, this.f19l);
            this.f13f += this.f19l;
        }
        this.f17j = 0;
        int[] iArr = this.f24q;
        int i4 = this.f22o - 1;
        iArr[i4] = iArr[i4] + 1;
        return str;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x0044. Please report as an issue. */
    public final String e0() throws e {
        String string;
        StringBuilder sb = null;
        int i4 = 0;
        while (true) {
            int i5 = 0;
            while (true) {
                int i6 = this.f13f;
                int i7 = i6 + i5;
                int i8 = this.f14g;
                char[] cArr = this.f12e;
                if (i7 < i8) {
                    char c4 = cArr[i6 + i5];
                    if (c4 != '\t' && c4 != '\n' && c4 != '\f' && c4 != '\r' && c4 != ' ') {
                        if (c4 != '#') {
                            if (c4 != ',') {
                                if (c4 != '/' && c4 != '=') {
                                    if (c4 != '{' && c4 != '}' && c4 != ':') {
                                        if (c4 != ';') {
                                            switch (c4) {
                                                case '[':
                                                case ']':
                                                    break;
                                                case '\\':
                                                    break;
                                                default:
                                                    i5++;
                                                    break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        k();
                    }
                    i4 = i5;
                } else if (i5 >= cArr.length) {
                    if (sb == null) {
                        sb = new StringBuilder(Math.max(i5, 16));
                    }
                    sb.append(cArr, this.f13f, i5);
                    this.f13f += i5;
                    if (!J(1)) {
                    }
                } else if (!J(i5 + 1)) {
                    i4 = i5;
                }
                if (sb == null) {
                    string = new String(cArr, this.f13f, i4);
                } else {
                    sb.append(cArr, this.f13f, i4);
                    string = sb.toString();
                }
                this.f13f += i4;
                return string;
            }
        }
    }

    public final int f0() {
        int iL = this.f17j;
        if (iL == 0) {
            iL = l();
        }
        switch (iL) {
            case 1:
                return 3;
            case 2:
                return 4;
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
            case 6:
                return 8;
            case 7:
                return 9;
            case 8:
            case 9:
            case 10:
            case 11:
                return 6;
            case 12:
            case 13:
            case 14:
                return 5;
            case 15:
            case 16:
                return 7;
            case 17:
                return 10;
            default:
                throw new AssertionError();
        }
    }

    public final void g0(int i4) throws e {
        int i5 = this.f22o;
        if (i5 - 1 >= 255) {
            throw new e("Nesting limit 255 reached" + U());
        }
        int[] iArr = this.f21n;
        if (i5 == iArr.length) {
            int i6 = i5 * 2;
            this.f21n = Arrays.copyOf(iArr, i6);
            this.f24q = Arrays.copyOf(this.f24q, i6);
            this.f23p = (String[]) Arrays.copyOf(this.f23p, i6);
        }
        int[] iArr2 = this.f21n;
        int i7 = this.f22o;
        this.f22o = i7 + 1;
        iArr2[i7] = i4;
    }

    public final char h0() throws e {
        int i4;
        if (this.f13f == this.f14g && !J(1)) {
            n0("Unterminated escape sequence");
            throw null;
        }
        int i5 = this.f13f;
        int i6 = i5 + 1;
        this.f13f = i6;
        char[] cArr = this.f12e;
        char c4 = cArr[i5];
        if (c4 != '\n') {
            if (c4 != '\"') {
                if (c4 != '\'') {
                    if (c4 != '/' && c4 != '\\') {
                        if (c4 == 'b') {
                            return '\b';
                        }
                        if (c4 == 'f') {
                            return '\f';
                        }
                        if (c4 == 'n') {
                            return '\n';
                        }
                        if (c4 == 'r') {
                            return '\r';
                        }
                        if (c4 == 't') {
                            return '\t';
                        }
                        if (c4 != 'u') {
                            n0("Invalid escape sequence");
                            throw null;
                        }
                        if (i5 + 5 > this.f14g && !J(4)) {
                            n0("Unterminated escape sequence");
                            throw null;
                        }
                        int i7 = this.f13f;
                        int i8 = i7 + 4;
                        int i9 = 0;
                        while (i7 < i8) {
                            char c5 = cArr[i7];
                            int i10 = i9 << 4;
                            if (c5 >= '0' && c5 <= '9') {
                                i4 = c5 - '0';
                            } else if (c5 >= 'a' && c5 <= 'f') {
                                i4 = c5 - 'W';
                            } else {
                                if (c5 < 'A' || c5 > 'F') {
                                    n0("Malformed Unicode escape \\u".concat(new String(cArr, this.f13f, 4)));
                                    throw null;
                                }
                                i4 = c5 - '7';
                            }
                            i9 = i4 + i10;
                            i7++;
                        }
                        this.f13f += 4;
                        return (char) i9;
                    }
                }
            }
            return c4;
        }
        if (this.f25r == 3) {
            n0("Cannot escape a newline character in strict mode");
            throw null;
        }
        this.f15h++;
        this.f16i = i6;
        if (this.f25r == 3) {
            n0("Invalid escaped character \"'\" in strict mode");
            throw null;
        }
        return c4;
    }

    public final void i0(int i4) {
        if (i4 == 0) {
            throw null;
        }
        this.f25r = i4;
    }

    public final void j0(char c4) throws e {
        do {
            int i4 = this.f13f;
            int i5 = this.f14g;
            while (i4 < i5) {
                int i6 = i4 + 1;
                char c5 = this.f12e[i4];
                if (c5 == c4) {
                    this.f13f = i6;
                    return;
                }
                if (c5 == '\\') {
                    this.f13f = i6;
                    h0();
                    i4 = this.f13f;
                    i5 = this.f14g;
                } else {
                    if (c5 == '\n') {
                        this.f15h++;
                        this.f16i = i6;
                    }
                    i4 = i6;
                }
            }
            this.f13f = i4;
        } while (J(1));
        n0("Unterminated string");
        throw null;
    }

    public final void k() throws e {
        if (this.f25r == 1) {
            return;
        }
        n0("Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON");
        throw null;
    }

    public final void k0() {
        char c4;
        do {
            if (this.f13f >= this.f14g && !J(1)) {
                return;
            }
            int i4 = this.f13f;
            int i5 = i4 + 1;
            this.f13f = i5;
            c4 = this.f12e[i4];
            if (c4 == '\n') {
                this.f15h++;
                this.f16i = i5;
                return;
            }
        } while (c4 != '\r');
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0145  */
    /* JADX WARN: Code duplicated, block: B:104:0x014e  */
    /* JADX WARN: Code duplicated, block: B:112:0x016b  */
    /* JADX WARN: Code duplicated, block: B:114:0x0173  */
    /* JADX WARN: Code duplicated, block: B:119:0x0188 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:120:0x0189  */
    /* JADX WARN: Code duplicated, block: B:123:0x019b  */
    /* JADX WARN: Code duplicated, block: B:126:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:129:0x01ac  */
    /* JADX WARN: Code duplicated, block: B:130:0x01b2 A[PHI: r4 r14
      0x01b2: PHI (r4v10 int) = (r4v9 int), (r4v15 int) binds: [B:122:0x0199, B:129:0x01ac] A[DONT_GENERATE, DONT_INLINE]
      0x01b2: PHI (r14v6 int) = (r14v5 int), (r14v7 int) binds: [B:122:0x0199, B:129:0x01ac] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:132:0x01ba  */
    /* JADX WARN: Code duplicated, block: B:134:0x01be  */
    /* JADX WARN: Code duplicated, block: B:172:0x021d  */
    /* JADX WARN: Code duplicated, block: B:173:0x021f  */
    /* JADX WARN: Code duplicated, block: B:185:0x0240 A[DONT_INVERT, PHI: r13
      0x0240: PHI (r13v24 int) = (r13v23 int), (r13v25 int) binds: [B:171:0x021b, B:177:0x0228] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:186:0x0242  */
    /* JADX WARN: Code duplicated, block: B:199:0x025f  */
    /* JADX WARN: Code duplicated, block: B:201:0x0262  */
    /* JADX WARN: Code duplicated, block: B:204:0x0267 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:208:0x0270 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:209:0x0271  */
    /* JADX WARN: Code duplicated, block: B:211:0x027b  */
    /* JADX WARN: Code duplicated, block: B:213:0x0281  */
    /* JADX WARN: Code duplicated, block: B:215:0x0287  */
    /* JADX WARN: Code duplicated, block: B:217:0x028a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:218:0x028c  */
    /* JADX WARN: Code duplicated, block: B:220:0x0290  */
    /* JADX WARN: Code duplicated, block: B:230:0x02ab  */
    /* JADX WARN: Code duplicated, block: B:232:0x02b3  */
    /* JADX WARN: Code duplicated, block: B:273:0x019e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:274:0x019e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:275:0x01a9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:285:0x0164 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x00ea A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:67:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:71:0x00f4 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:72:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:74:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:92:0x012b  */
    /* JADX WARN: Code duplicated, block: B:95:0x0134  */
    /* JADX WARN: Code duplicated, block: B:96:0x0136  */
    /* JADX WARN: Code duplicated, block: B:99:0x013e  */
    public final int l() throws IOException {
        int iA0;
        int i4;
        int iA1;
        char c4;
        String str;
        String str2;
        int i5;
        int i6;
        int length;
        int i7;
        char c5;
        int i8;
        int i9;
        int i10;
        int i11;
        boolean z3;
        char c6;
        int i12;
        int i13;
        int[] iArr = this.f21n;
        int i14 = this.f22o - 1;
        int i15 = iArr[i14];
        char[] cArr = this.f12e;
        if (i15 == 1) {
            iArr[i14] = 2;
        } else if (i15 == 2) {
            int iA2 = a0(true);
            if (iA2 != 44) {
                if (iA2 != 59) {
                    if (iA2 == 93) {
                        this.f17j = 4;
                        return 4;
                    }
                    n0("Unterminated array");
                    throw null;
                }
                k();
            }
        } else {
            if (i15 == 3 || i15 == 5) {
                iArr[i14] = 4;
                if (i15 == 5 && (iA0 = a0(true)) != 44) {
                    if (iA0 != 59) {
                        if (iA0 == 125) {
                            this.f17j = 2;
                            return 2;
                        }
                        n0("Unterminated object");
                        throw null;
                    }
                    k();
                }
                int iA3 = a0(true);
                if (iA3 == 34) {
                    this.f17j = 13;
                    return 13;
                }
                if (iA3 == 39) {
                    k();
                    this.f17j = 12;
                    return 12;
                }
                if (iA3 == 125) {
                    if (i15 != 5) {
                        this.f17j = 2;
                        return 2;
                    }
                    n0("Expected name");
                    throw null;
                }
                k();
                this.f13f--;
                if (T((char) iA3)) {
                    this.f17j = 14;
                    return 14;
                }
                n0("Expected name");
                throw null;
            }
            if (i15 != 4) {
                if (i15 == 6) {
                    if (this.f25r == 1) {
                        a0(true);
                        int i16 = this.f13f;
                        this.f13f = i16 - 1;
                        if (i16 + 4 <= this.f14g || J(5)) {
                            int i17 = this.f13f;
                            if (cArr[i17] == ')' && cArr[i17 + 1] == ']' && cArr[i17 + 2] == '}' && cArr[i17 + 3] == '\'' && cArr[i17 + 4] == '\n') {
                                this.f13f = i17 + 5;
                            }
                        }
                    }
                    this.f21n[this.f22o - 1] = 7;
                } else if (i15 == 7) {
                    i4 = 0;
                    if (a0(false) == -1) {
                        this.f17j = 17;
                        return 17;
                    }
                    k();
                    this.f13f--;
                } else {
                    i4 = 0;
                    if (i15 == 8) {
                        throw new IllegalStateException("JsonReader is closed");
                    }
                }
                iA1 = a0(true);
                if (iA1 != 34) {
                    this.f17j = 9;
                    return 9;
                }
                if (iA1 != 39) {
                    k();
                    this.f17j = 8;
                    return 8;
                }
                if (iA1 != 44 && iA1 != 59) {
                    if (iA1 != 91) {
                        this.f17j = 3;
                        return 3;
                    }
                    if (iA1 != 93) {
                        if (iA1 != 123) {
                            this.f17j = 1;
                            return 1;
                        }
                        int i18 = this.f13f - 1;
                        this.f13f = i18;
                        c4 = cArr[i18];
                        if (c4 != 't' || c4 == 'T') {
                            str = "true";
                            str2 = "TRUE";
                            i5 = 5;
                        } else {
                            if (c4 != 'f' && c4 != 'F') {
                                if (c4 != 'n' && c4 != 'N') {
                                    i5 = i4;
                                    break;
                                }
                                str = "null";
                                str2 = "NULL";
                                i5 = 7;
                                if (i5 != 0) {
                                    return i5;
                                }
                                int i19 = this.f13f;
                                i8 = this.f14g;
                                i9 = i4;
                                i10 = i9;
                                int i20 = i10;
                                i11 = i19;
                                z3 = true;
                                long j4 = 0;
                                while (true) {
                                    if (i11 + i10 != i8) {
                                        c6 = cArr[i11 + i10];
                                        if (c6 != '+') {
                                            if (c6 != 'E' || c6 == 'e') {
                                                if (i9 != 2 || i9 == 4) {
                                                    i9 = 5;
                                                    i10++;
                                                }
                                            } else if (c6 == '-') {
                                                if (i9 == 0) {
                                                    i9 = 1;
                                                    i20 = 1;
                                                } else {
                                                    if (i9 != 5) {
                                                    }
                                                    i9 = 6;
                                                }
                                                i10++;
                                            } else if (c6 != '.') {
                                                if (c6 >= '0' && c6 <= '9') {
                                                    if (i9 == 1 || i9 == 0) {
                                                        j4 = -(c6 - '0');
                                                        i9 = 2;
                                                    } else if (i9 == 2) {
                                                        if (j4 != 0) {
                                                            long j5 = (10 * j4) - ((long) (c6 - '0'));
                                                            z3 &= j4 > -922337203685477580L || (j4 == -922337203685477580L && j5 < j4);
                                                            j4 = j5;
                                                        }
                                                    } else if (i9 == 3) {
                                                        i9 = 4;
                                                    } else if (i9 == 5 || i9 == 6) {
                                                        i9 = 7;
                                                    }
                                                    i10++;
                                                } else if (!T(c6)) {
                                                    i13 = 2;
                                                    if (i9 != 2) {
                                                        if (i9 != i13 || i9 == 4 || i9 == 7) {
                                                            this.f19l = i10;
                                                            i12 = 16;
                                                            this.f17j = 16;
                                                        }
                                                    } else if (z3 || ((j4 == Long.MIN_VALUE && i20 == 0) || (j4 == 0 && i20 != 0))) {
                                                        i13 = 2;
                                                        if (i9 != i13) {
                                                        }
                                                        this.f19l = i10;
                                                        i12 = 16;
                                                        this.f17j = 16;
                                                    } else {
                                                        if (i20 == 0) {
                                                            j4 = -j4;
                                                        }
                                                        this.f18k = j4;
                                                        this.f13f += i10;
                                                        i12 = 15;
                                                        this.f17j = 15;
                                                    }
                                                }
                                            } else if (i9 == 2) {
                                                i9 = 3;
                                                i10++;
                                            }
                                            if (i12 != 0) {
                                                return i12;
                                            }
                                            if (T(cArr[this.f13f])) {
                                                n0("Expected value");
                                                throw null;
                                            }
                                            k();
                                            this.f17j = 10;
                                            return 10;
                                        }
                                        if (i9 != 5) {
                                        }
                                        i9 = 6;
                                        i10++;
                                    } else if (i10 != cArr.length) {
                                        if (J(i10 + 1)) {
                                            i11 = this.f13f;
                                            i8 = this.f14g;
                                            c6 = cArr[i11 + i10];
                                            if (c6 != '+') {
                                                if (c6 != 'E') {
                                                    if (i9 != 2) {
                                                    }
                                                    i9 = 5;
                                                    i10++;
                                                } else {
                                                    if (i9 != 2) {
                                                    }
                                                    i9 = 5;
                                                    i10++;
                                                }
                                                if (i12 != 0) {
                                                    return i12;
                                                }
                                                if (T(cArr[this.f13f])) {
                                                    n0("Expected value");
                                                    throw null;
                                                }
                                                k();
                                                this.f17j = 10;
                                                return 10;
                                            }
                                            if (i9 != 5) {
                                            }
                                            i9 = 6;
                                            i10++;
                                        }
                                        i13 = 2;
                                        if (i9 != 2) {
                                            if (i9 != i13) {
                                            }
                                            this.f19l = i10;
                                            i12 = 16;
                                            this.f17j = 16;
                                        } else {
                                            if (z3) {
                                            }
                                            i13 = 2;
                                            if (i9 != i13) {
                                            }
                                            this.f19l = i10;
                                            i12 = 16;
                                            this.f17j = 16;
                                        }
                                        if (i12 != 0) {
                                            return i12;
                                        }
                                        if (T(cArr[this.f13f])) {
                                            n0("Expected value");
                                            throw null;
                                        }
                                        k();
                                        this.f17j = 10;
                                        return 10;
                                    }
                                    i12 = 0;
                                    if (i12 != 0) {
                                        return i12;
                                    }
                                    if (T(cArr[this.f13f])) {
                                        n0("Expected value");
                                        throw null;
                                    }
                                    k();
                                    this.f17j = 10;
                                    return 10;
                                }
                            }
                            str = "false";
                            str2 = "FALSE";
                            i5 = 6;
                        }
                        if (this.f25r != 3) {
                            i6 = 1;
                        } else {
                            i6 = i4;
                        }
                        length = str.length();
                        i7 = i4;
                        while (true) {
                            if (i7 >= length) {
                                if ((this.f13f + length < this.f14g && !J(length + 1)) || !T(cArr[this.f13f + length])) {
                                    this.f13f += length;
                                    this.f17j = i5;
                                    break;
                                }
                                break;
                            }
                            if ((this.f13f + i7 >= this.f14g || J(i7 + 1)) && ((c5 = cArr[this.f13f + i7]) == str.charAt(i7) || (i6 != 0 && c5 == str2.charAt(i7)))) {
                            }
                            i5 = i4;
                            break;
                        }
                        if (i5 != 0) {
                            return i5;
                        }
                        int i110 = this.f13f;
                        i8 = this.f14g;
                        i9 = i4;
                        i10 = i9;
                        int i21 = i10;
                        i11 = i110;
                        z3 = true;
                        long j6 = 0;
                        while (true) {
                            if (i11 + i10 != i8) {
                                c6 = cArr[i11 + i10];
                                if (c6 != '+') {
                                    if (c6 != 'E') {
                                        if (i9 != 2) {
                                        }
                                        i9 = 5;
                                        i10++;
                                    } else {
                                        if (i9 != 2) {
                                        }
                                        i9 = 5;
                                        i10++;
                                    }
                                    if (i12 != 0) {
                                        return i12;
                                    }
                                    if (T(cArr[this.f13f])) {
                                        n0("Expected value");
                                        throw null;
                                    }
                                    k();
                                    this.f17j = 10;
                                    return 10;
                                }
                                if (i9 != 5) {
                                }
                                i9 = 6;
                                i10++;
                            } else if (i10 != cArr.length) {
                                if (J(i10 + 1)) {
                                    i11 = this.f13f;
                                    i8 = this.f14g;
                                    c6 = cArr[i11 + i10];
                                    if (c6 != '+') {
                                        if (c6 != 'E') {
                                            if (i9 != 2) {
                                            }
                                            i9 = 5;
                                            i10++;
                                        } else {
                                            if (i9 != 2) {
                                            }
                                            i9 = 5;
                                            i10++;
                                        }
                                        if (i12 != 0) {
                                            return i12;
                                        }
                                        if (T(cArr[this.f13f])) {
                                            n0("Expected value");
                                            throw null;
                                        }
                                        k();
                                        this.f17j = 10;
                                        return 10;
                                    }
                                    if (i9 != 5) {
                                    }
                                    i9 = 6;
                                    i10++;
                                }
                                i13 = 2;
                                if (i9 != 2) {
                                    if (i9 != i13) {
                                    }
                                    this.f19l = i10;
                                    i12 = 16;
                                    this.f17j = 16;
                                } else {
                                    if (z3) {
                                    }
                                    i13 = 2;
                                    if (i9 != i13) {
                                    }
                                    this.f19l = i10;
                                    i12 = 16;
                                    this.f17j = 16;
                                }
                                if (i12 != 0) {
                                    return i12;
                                }
                                if (T(cArr[this.f13f])) {
                                    n0("Expected value");
                                    throw null;
                                }
                                k();
                                this.f17j = 10;
                                return 10;
                            }
                            i12 = 0;
                            if (i12 != 0) {
                                return i12;
                            }
                            if (T(cArr[this.f13f])) {
                                n0("Expected value");
                                throw null;
                            }
                            k();
                            this.f17j = 10;
                            return 10;
                        }
                    }
                    if (i15 == 1) {
                        this.f17j = 4;
                        return 4;
                    }
                }
                if (i15 == 1 && i15 != 2) {
                    n0("Unexpected value");
                    throw null;
                }
                k();
                this.f13f--;
                this.f17j = 7;
                return 7;
            }
            iArr[i14] = 5;
            int iA4 = a0(true);
            if (iA4 != 58) {
                if (iA4 != 61) {
                    n0("Expected ':'");
                    throw null;
                }
                k();
                if (this.f13f < this.f14g || J(1)) {
                    int i22 = this.f13f;
                    if (cArr[i22] == '>') {
                        this.f13f = i22 + 1;
                    }
                }
            }
        }
        i4 = 0;
        iA1 = a0(true);
        if (iA1 != 34) {
            this.f17j = 9;
            return 9;
        }
        if (iA1 != 39) {
            k();
            this.f17j = 8;
            return 8;
        }
        if (iA1 != 44) {
            if (iA1 != 91) {
                this.f17j = 3;
                return 3;
            }
            if (iA1 != 93) {
                if (iA1 != 123) {
                    this.f17j = 1;
                    return 1;
                }
                int i111 = this.f13f - 1;
                this.f13f = i111;
                c4 = cArr[i111];
                if (c4 != 't') {
                    str = "true";
                    str2 = "TRUE";
                    i5 = 5;
                    if (this.f25r != 3) {
                        i6 = 1;
                    } else {
                        i6 = i4;
                    }
                    length = str.length();
                    i7 = i4;
                    while (true) {
                        if (i7 >= length) {
                            if (this.f13f + length < this.f14g) {
                            }
                            this.f13f += length;
                            this.f17j = i5;
                            break;
                        }
                        i7 = this.f13f + i7 >= this.f14g ? i7 + 1 : i7 + 1;
                    }
                    if (i5 != 0) {
                        return i5;
                    }
                    int i112 = this.f13f;
                    i8 = this.f14g;
                    i9 = i4;
                    i10 = i9;
                    int i23 = i10;
                    i11 = i112;
                    z3 = true;
                    long j7 = 0;
                    while (true) {
                        if (i11 + i10 != i8) {
                            c6 = cArr[i11 + i10];
                            if (c6 != '+') {
                                if (c6 != 'E') {
                                    if (i9 != 2) {
                                    }
                                    i9 = 5;
                                    i10++;
                                } else {
                                    if (i9 != 2) {
                                    }
                                    i9 = 5;
                                    i10++;
                                }
                                if (i12 != 0) {
                                    return i12;
                                }
                                if (T(cArr[this.f13f])) {
                                    n0("Expected value");
                                    throw null;
                                }
                                k();
                                this.f17j = 10;
                                return 10;
                            }
                            if (i9 != 5) {
                            }
                            i9 = 6;
                            i10++;
                        } else if (i10 != cArr.length) {
                            if (J(i10 + 1)) {
                                i11 = this.f13f;
                                i8 = this.f14g;
                                c6 = cArr[i11 + i10];
                                if (c6 != '+') {
                                    if (c6 != 'E') {
                                        if (i9 != 2) {
                                        }
                                        i9 = 5;
                                        i10++;
                                    } else {
                                        if (i9 != 2) {
                                        }
                                        i9 = 5;
                                        i10++;
                                    }
                                    if (i12 != 0) {
                                        return i12;
                                    }
                                    if (T(cArr[this.f13f])) {
                                        n0("Expected value");
                                        throw null;
                                    }
                                    k();
                                    this.f17j = 10;
                                    return 10;
                                }
                                if (i9 != 5) {
                                }
                                i9 = 6;
                                i10++;
                            }
                            i13 = 2;
                            if (i9 != 2) {
                                if (i9 != i13) {
                                }
                                this.f19l = i10;
                                i12 = 16;
                                this.f17j = 16;
                            } else {
                                if (z3) {
                                }
                                i13 = 2;
                                if (i9 != i13) {
                                }
                                this.f19l = i10;
                                i12 = 16;
                                this.f17j = 16;
                            }
                            if (i12 != 0) {
                                return i12;
                            }
                            if (T(cArr[this.f13f])) {
                                n0("Expected value");
                                throw null;
                            }
                            k();
                            this.f17j = 10;
                            return 10;
                        }
                        i12 = 0;
                        if (i12 != 0) {
                            return i12;
                        }
                        if (T(cArr[this.f13f])) {
                            n0("Expected value");
                            throw null;
                        }
                        k();
                        this.f17j = 10;
                        return 10;
                    }
                }
                str = "true";
                str2 = "TRUE";
                i5 = 5;
                if (this.f25r != 3) {
                    i6 = 1;
                } else {
                    i6 = i4;
                }
                length = str.length();
                i7 = i4;
                while (true) {
                    if (i7 >= length) {
                        if (this.f13f + length < this.f14g) {
                        }
                        this.f13f += length;
                        this.f17j = i5;
                        break;
                    }
                    if (this.f13f + i7 >= this.f14g) {
                    }
                }
                if (i5 != 0) {
                    return i5;
                }
                int i113 = this.f13f;
                i8 = this.f14g;
                i9 = i4;
                i10 = i9;
                int i24 = i10;
                i11 = i113;
                z3 = true;
                long j8 = 0;
                while (true) {
                    if (i11 + i10 != i8) {
                        c6 = cArr[i11 + i10];
                        if (c6 != '+') {
                            if (c6 != 'E') {
                                if (i9 != 2) {
                                }
                                i9 = 5;
                                i10++;
                            } else {
                                if (i9 != 2) {
                                }
                                i9 = 5;
                                i10++;
                            }
                            if (i12 != 0) {
                                return i12;
                            }
                            if (T(cArr[this.f13f])) {
                                n0("Expected value");
                                throw null;
                            }
                            k();
                            this.f17j = 10;
                            return 10;
                        }
                        if (i9 != 5) {
                        }
                        i9 = 6;
                        i10++;
                    } else if (i10 != cArr.length) {
                        if (J(i10 + 1)) {
                            i11 = this.f13f;
                            i8 = this.f14g;
                            c6 = cArr[i11 + i10];
                            if (c6 != '+') {
                                if (c6 != 'E') {
                                    if (i9 != 2) {
                                    }
                                    i9 = 5;
                                    i10++;
                                } else {
                                    if (i9 != 2) {
                                    }
                                    i9 = 5;
                                    i10++;
                                }
                                if (i12 != 0) {
                                    return i12;
                                }
                                if (T(cArr[this.f13f])) {
                                    n0("Expected value");
                                    throw null;
                                }
                                k();
                                this.f17j = 10;
                                return 10;
                            }
                            if (i9 != 5) {
                            }
                            i9 = 6;
                            i10++;
                        }
                        i13 = 2;
                        if (i9 != 2) {
                            if (i9 != i13) {
                            }
                            this.f19l = i10;
                            i12 = 16;
                            this.f17j = 16;
                        } else {
                            if (z3) {
                            }
                            i13 = 2;
                            if (i9 != i13) {
                            }
                            this.f19l = i10;
                            i12 = 16;
                            this.f17j = 16;
                        }
                        if (i12 != 0) {
                            return i12;
                        }
                        if (T(cArr[this.f13f])) {
                            n0("Expected value");
                            throw null;
                        }
                        k();
                        this.f17j = 10;
                        return 10;
                    }
                    i12 = 0;
                    if (i12 != 0) {
                        return i12;
                    }
                    if (T(cArr[this.f13f])) {
                        n0("Expected value");
                        throw null;
                    }
                    k();
                    this.f17j = 10;
                    return 10;
                }
                i5 = i4;
                if (i5 != 0) {
                    return i5;
                }
                int i114 = this.f13f;
                i8 = this.f14g;
                i9 = i4;
                i10 = i9;
                int i25 = i10;
                i11 = i114;
                z3 = true;
                long j9 = 0;
                while (true) {
                    if (i11 + i10 != i8) {
                        c6 = cArr[i11 + i10];
                        if (c6 != '+') {
                            if (c6 != 'E') {
                                if (i9 != 2) {
                                }
                                i9 = 5;
                                i10++;
                            } else {
                                if (i9 != 2) {
                                }
                                i9 = 5;
                                i10++;
                            }
                            if (i12 != 0) {
                                return i12;
                            }
                            if (T(cArr[this.f13f])) {
                                n0("Expected value");
                                throw null;
                            }
                            k();
                            this.f17j = 10;
                            return 10;
                        }
                        if (i9 != 5) {
                        }
                        i9 = 6;
                        i10++;
                    } else if (i10 != cArr.length) {
                        if (J(i10 + 1)) {
                            i11 = this.f13f;
                            i8 = this.f14g;
                            c6 = cArr[i11 + i10];
                            if (c6 != '+') {
                                if (c6 != 'E') {
                                    if (i9 != 2) {
                                    }
                                    i9 = 5;
                                    i10++;
                                } else {
                                    if (i9 != 2) {
                                    }
                                    i9 = 5;
                                    i10++;
                                }
                                if (i12 != 0) {
                                    return i12;
                                }
                                if (T(cArr[this.f13f])) {
                                    n0("Expected value");
                                    throw null;
                                }
                                k();
                                this.f17j = 10;
                                return 10;
                            }
                            if (i9 != 5) {
                            }
                            i9 = 6;
                            i10++;
                        }
                        i13 = 2;
                        if (i9 != 2) {
                            if (i9 != i13) {
                            }
                            this.f19l = i10;
                            i12 = 16;
                            this.f17j = 16;
                        } else {
                            if (z3) {
                            }
                            i13 = 2;
                            if (i9 != i13) {
                            }
                            this.f19l = i10;
                            i12 = 16;
                            this.f17j = 16;
                        }
                        if (i12 != 0) {
                            return i12;
                        }
                        if (T(cArr[this.f13f])) {
                            n0("Expected value");
                            throw null;
                        }
                        k();
                        this.f17j = 10;
                        return 10;
                    }
                    i12 = 0;
                    if (i12 != 0) {
                        return i12;
                    }
                    if (T(cArr[this.f13f])) {
                        n0("Expected value");
                        throw null;
                    }
                    k();
                    this.f17j = 10;
                    return 10;
                }
            }
            if (i15 == 1) {
                this.f17j = 4;
                return 4;
            }
        }
        if (i15 == 1) {
        }
        k();
        this.f13f--;
        this.f17j = 7;
        return 7;
    }

    public final void l0() throws e {
        do {
            int i4 = 0;
            while (true) {
                int i5 = this.f13f;
                if (i5 + i4 < this.f14g) {
                    char c4 = this.f12e[i5 + i4];
                    if (c4 != '\t' && c4 != '\n' && c4 != '\f' && c4 != '\r' && c4 != ' ') {
                        if (c4 != '#') {
                            if (c4 != ',') {
                                if (c4 != '/' && c4 != '=') {
                                    if (c4 != '{' && c4 != '}' && c4 != ':') {
                                        if (c4 != ';') {
                                            switch (c4) {
                                                case '[':
                                                case ']':
                                                    break;
                                                case '\\':
                                                    break;
                                                default:
                                                    i4++;
                                                    break;
                                            }
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                        k();
                    }
                    this.f13f += i4;
                    return;
                }
                this.f13f = i5 + i4;
            }
        } while (J(1));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void m0() throws IOException {
        int i4 = 0;
        do {
            int iL = this.f17j;
            if (iL == 0) {
                iL = l();
            }
            switch (iL) {
                case 1:
                    g0(3);
                    i4++;
                    this.f17j = 0;
                    break;
                case 2:
                    if (i4 == 0) {
                        this.f23p[this.f22o - 1] = null;
                    }
                    this.f22o--;
                    i4--;
                    this.f17j = 0;
                    break;
                case 3:
                    g0(1);
                    i4++;
                    this.f17j = 0;
                    break;
                case 4:
                    this.f22o--;
                    i4--;
                    this.f17j = 0;
                    break;
                case 5:
                case 6:
                case 7:
                case 11:
                case 15:
                default:
                    this.f17j = 0;
                    break;
                case 8:
                    j0('\'');
                    this.f17j = 0;
                    break;
                case 9:
                    j0('\"');
                    this.f17j = 0;
                    break;
                case 10:
                    l0();
                    this.f17j = 0;
                    break;
                case 12:
                    j0('\'');
                    if (i4 == 0) {
                        this.f23p[this.f22o - 1] = "<skipped>";
                    }
                    this.f17j = 0;
                    break;
                case 13:
                    j0('\"');
                    if (i4 == 0) {
                        this.f23p[this.f22o - 1] = "<skipped>";
                    }
                    this.f17j = 0;
                    break;
                case 14:
                    l0();
                    if (i4 == 0) {
                        this.f23p[this.f22o - 1] = "<skipped>";
                    }
                    this.f17j = 0;
                    break;
                case 16:
                    this.f13f += this.f19l;
                    this.f17j = 0;
                    break;
                case 17:
                    break;
            }
            return;
        } while (i4 > 0);
        int[] iArr = this.f24q;
        int i5 = this.f22o - 1;
        iArr[i5] = iArr[i5] + 1;
    }

    public final void n0(String str) throws e {
        throw new e(str + U() + "\nSee " + "https://github.com/google/gson/blob/main/Troubleshooting.md#".concat("malformed-json"));
    }

    public final IllegalStateException o0(String str) {
        return new IllegalStateException("Expected " + str + " but was " + c.h(f0()) + U() + "\nSee " + "https://github.com/google/gson/blob/main/Troubleshooting.md#".concat(f0() == 9 ? "adapter-not-null-safe" : "unexpected-json-structure"));
    }

    public final String toString() {
        return b.class.getSimpleName() + U();
    }
}
