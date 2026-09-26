package a1;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import s0.i;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class d implements Closeable, Flushable {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final Pattern f26o = Pattern.compile("-?(?:0|[1-9][0-9]*)(?:\\.[0-9]+)?(?:[eE][-+]?[0-9]+)?");

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final String[] f27p = new String[128];

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final String[] f28q;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Writer f29d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f30e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f31f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public i f32g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f33h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f34i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f35j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f36k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f37l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f38m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f39n;

    static {
        for (int i4 = 0; i4 <= 31; i4++) {
            f27p[i4] = String.format("\\u%04x", Integer.valueOf(i4));
        }
        String[] strArr = f27p;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        f28q = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public d(Writer writer) {
        int[] iArr = new int[32];
        this.f30e = iArr;
        this.f31f = 0;
        if (iArr.length == 0) {
            this.f30e = Arrays.copyOf(iArr, 0);
        }
        int[] iArr2 = this.f30e;
        int i4 = this.f31f;
        this.f31f = i4 + 1;
        iArr2[i4] = 6;
        this.f36k = 2;
        this.f39n = true;
        Objects.requireNonNull(writer, "out == null");
        this.f29d = writer;
        U(i.f2087d);
    }

    public void A() throws IOException {
        l(1, 2, ']');
    }

    public void C() throws IOException {
        l(3, 5, '}');
    }

    public void J(String str) {
        Objects.requireNonNull(str, "name == null");
        if (this.f38m != null) {
            throw new IllegalStateException("Already wrote a name, expecting a value.");
        }
        int iT = T();
        if (iT != 3 && iT != 5) {
            throw new IllegalStateException("Please begin an object before writing a name.");
        }
        this.f38m = str;
    }

    public final void K() throws IOException {
        if (this.f35j) {
            return;
        }
        String str = this.f32g.f2089a;
        Writer writer = this.f29d;
        writer.write(str);
        int i4 = this.f31f;
        for (int i5 = 1; i5 < i4; i5++) {
            writer.write(this.f32g.f2090b);
        }
    }

    public d S() throws IOException {
        if (this.f38m != null) {
            if (!this.f39n) {
                this.f38m = null;
                return this;
            }
            c0();
        }
        b();
        this.f29d.write("null");
        return this;
    }

    public final int T() {
        int i4 = this.f31f;
        if (i4 != 0) {
            return this.f30e[i4 - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final void U(i iVar) {
        Objects.requireNonNull(iVar);
        this.f32g = iVar;
        this.f34i = ",";
        if (iVar.f2091c) {
            this.f33h = ": ";
            if (iVar.f2089a.isEmpty()) {
                this.f34i = ", ";
            }
        } else {
            this.f33h = ":";
        }
        this.f35j = this.f32g.f2089a.isEmpty() && this.f32g.f2090b.isEmpty();
    }

    public final void V(int i4) {
        if (i4 == 0) {
            throw null;
        }
        this.f36k = i4;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0034  */
    public final void W(String str) throws IOException {
        String str2;
        String[] strArr = this.f37l ? f28q : f27p;
        Writer writer = this.f29d;
        writer.write(34);
        int length = str.length();
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            char cCharAt = str.charAt(i5);
            if (cCharAt < 128) {
                str2 = strArr[cCharAt];
                if (str2 != null) {
                    if (i4 < i5) {
                        writer.write(str, i4, i5 - i4);
                    }
                    writer.write(str2);
                    i4 = i5 + 1;
                }
            } else {
                if (cCharAt == 8232) {
                    str2 = "\\u2028";
                } else if (cCharAt == 8233) {
                    str2 = "\\u2029";
                }
                if (i4 < i5) {
                    writer.write(str, i4, i5 - i4);
                }
                writer.write(str2);
                i4 = i5 + 1;
            }
        }
        if (i4 < length) {
            writer.write(str, i4, length - i4);
        }
        writer.write(34);
    }

    public void X(double d4) throws IOException {
        c0();
        if (this.f36k == 1 || !(Double.isNaN(d4) || Double.isInfinite(d4))) {
            b();
            this.f29d.append((CharSequence) Double.toString(d4));
        } else {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + d4);
        }
    }

    public void Y(long j4) throws IOException {
        c0();
        b();
        this.f29d.write(Long.toString(j4));
    }

    public void Z(Number number) throws IOException {
        if (number == null) {
            S();
            return;
        }
        c0();
        String string = number.toString();
        Class<?> cls = number.getClass();
        if (cls != Integer.class && cls != Long.class && cls != Byte.class && cls != Short.class && cls != BigDecimal.class && cls != BigInteger.class && cls != AtomicInteger.class && cls != AtomicLong.class) {
            if (string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN")) {
                if (this.f36k != 1) {
                    throw new IllegalArgumentException("Numeric values must be finite, but was ".concat(string));
                }
            } else if (cls != Float.class && cls != Double.class && !f26o.matcher(string).matches()) {
                throw new IllegalArgumentException("String created by " + cls + " is not a valid JSON number: " + string);
            }
        }
        b();
        this.f29d.append((CharSequence) string);
    }

    public void a0(String str) throws IOException {
        if (str == null) {
            S();
            return;
        }
        c0();
        b();
        W(str);
    }

    public final void b() throws IOException {
        int iT = T();
        if (iT == 1) {
            this.f30e[this.f31f - 1] = 2;
            K();
            return;
        }
        Writer writer = this.f29d;
        if (iT == 2) {
            writer.append((CharSequence) this.f34i);
            K();
        } else {
            if (iT == 4) {
                writer.append((CharSequence) this.f33h);
                this.f30e[this.f31f - 1] = 5;
                return;
            }
            if (iT != 6) {
                if (iT != 7) {
                    throw new IllegalStateException("Nesting problem.");
                }
                if (this.f36k != 1) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            this.f30e[this.f31f - 1] = 7;
        }
    }

    public void b0(boolean z3) throws IOException {
        c0();
        b();
        this.f29d.write(z3 ? "true" : "false");
    }

    public void c() throws IOException {
        c0();
        b();
        int i4 = this.f31f;
        int[] iArr = this.f30e;
        if (i4 == iArr.length) {
            this.f30e = Arrays.copyOf(iArr, i4 * 2);
        }
        int[] iArr2 = this.f30e;
        int i5 = this.f31f;
        this.f31f = i5 + 1;
        iArr2[i5] = 1;
        this.f29d.write(91);
    }

    public final void c0() throws IOException {
        if (this.f38m != null) {
            int iT = T();
            if (iT == 5) {
                this.f29d.write(this.f34i);
            } else if (iT != 3) {
                throw new IllegalStateException("Nesting problem.");
            }
            K();
            this.f30e[this.f31f - 1] = 4;
            W(this.f38m);
            this.f38m = null;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f29d.close();
        int i4 = this.f31f;
        if (i4 > 1 || (i4 == 1 && this.f30e[i4 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.f31f = 0;
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        if (this.f31f == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.f29d.flush();
    }

    public void k() throws IOException {
        c0();
        b();
        int i4 = this.f31f;
        int[] iArr = this.f30e;
        if (i4 == iArr.length) {
            this.f30e = Arrays.copyOf(iArr, i4 * 2);
        }
        int[] iArr2 = this.f30e;
        int i5 = this.f31f;
        this.f31f = i5 + 1;
        iArr2[i5] = 3;
        this.f29d.write(123);
    }

    public final void l(int i4, int i5, char c4) throws IOException {
        int iT = T();
        if (iT != i5 && iT != i4) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.f38m != null) {
            throw new IllegalStateException("Dangling name: " + this.f38m);
        }
        this.f31f--;
        if (iT == i5) {
            K();
        }
        this.f29d.write(c4);
    }
}
