package a.c.a.j;

import java.util.ArrayList;

/* JADX INFO: compiled from: ConstraintWidget.java */
/* JADX INFO: loaded from: classes.dex */
public class f {
    public static float j0 = 0.5f;
    protected b[] C;
    f D;
    int E;
    int F;
    protected float G;
    protected int H;
    protected int I;
    protected int J;
    int K;
    int L;
    private int M;
    private int N;
    protected int O;
    protected int P;
    int Q;
    protected int R;
    protected int S;
    private int T;
    private int U;
    float V;
    float W;
    private Object X;
    private int Y;
    private String Z;
    private String a0;
    boolean b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    n f137c;
    boolean c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    n f138d;
    boolean d0;
    int e0;
    int f0;
    float[] g0;
    protected f[] h0;
    protected f[] i0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f135a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f136b = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f139e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f140f = 0;
    int[] g = new int[2];
    int h = 0;
    int i = 0;
    float j = 1.0f;
    int k = 0;
    int l = 0;
    float m = 1.0f;
    int n = -1;
    float o = 1.0f;
    h p = null;
    private int[] q = {Integer.MAX_VALUE, Integer.MAX_VALUE};
    private float r = 0.0f;
    e s = new e(this, e.d.LEFT);
    e t = new e(this, e.d.TOP);
    e u = new e(this, e.d.RIGHT);
    e v = new e(this, e.d.BOTTOM);
    e w = new e(this, e.d.BASELINE);
    e x = new e(this, e.d.CENTER_X);
    e y = new e(this, e.d.CENTER_Y);
    e z = new e(this, e.d.CENTER);
    protected e[] A = {this.s, this.u, this.t, this.v, this.w, this.z};
    protected ArrayList<e> B = new ArrayList<>();

    /* JADX INFO: compiled from: ConstraintWidget.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f141a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f142b = new int[b.values().length];

        static {
            try {
                f142b[b.FIXED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f142b[b.WRAP_CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f142b[b.MATCH_PARENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f142b[b.MATCH_CONSTRAINT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            f141a = new int[e.d.values().length];
            try {
                f141a[e.d.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f141a[e.d.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f141a[e.d.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f141a[e.d.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f141a[e.d.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f141a[e.d.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f141a[e.d.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f141a[e.d.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f141a[e.d.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    /* JADX INFO: compiled from: ConstraintWidget.java */
    public enum b {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public f() {
        b bVar = b.FIXED;
        this.C = new b[]{bVar, bVar};
        this.D = null;
        this.E = 0;
        this.F = 0;
        this.G = 0.0f;
        this.H = -1;
        this.I = 0;
        this.J = 0;
        this.K = 0;
        this.L = 0;
        this.M = 0;
        this.N = 0;
        this.O = 0;
        this.P = 0;
        this.Q = 0;
        float f2 = j0;
        this.V = f2;
        this.W = f2;
        this.Y = 0;
        this.Z = null;
        this.a0 = null;
        this.b0 = false;
        this.c0 = false;
        this.d0 = false;
        this.e0 = 0;
        this.f0 = 0;
        this.g0 = new float[]{-1.0f, -1.0f};
        this.h0 = new f[]{null, null};
        this.i0 = new f[]{null, null};
        J();
    }

    private void J() {
        this.B.add(this.s);
        this.B.add(this.t);
        this.B.add(this.u);
        this.B.add(this.v);
        this.B.add(this.x);
        this.B.add(this.y);
        this.B.add(this.z);
        this.B.add(this.w);
    }

    public boolean A() {
        e eVar = this.t;
        e eVar2 = eVar.f118d;
        if (eVar2 != null && eVar2.f118d == eVar) {
            return true;
        }
        e eVar3 = this.v;
        e eVar4 = eVar3.f118d;
        return eVar4 != null && eVar4.f118d == eVar3;
    }

    public boolean B() {
        return this.f140f == 0 && this.G == 0.0f && this.k == 0 && this.l == 0 && this.C[1] == b.MATCH_CONSTRAINT;
    }

    public boolean C() {
        return this.f139e == 0 && this.G == 0.0f && this.h == 0 && this.i == 0 && this.C[0] == b.MATCH_CONSTRAINT;
    }

    public void D() {
        this.s.j();
        this.t.j();
        this.u.j();
        this.v.j();
        this.w.j();
        this.x.j();
        this.y.j();
        this.z.j();
        this.D = null;
        this.r = 0.0f;
        this.E = 0;
        this.F = 0;
        this.G = 0.0f;
        this.H = -1;
        this.I = 0;
        this.J = 0;
        this.M = 0;
        this.N = 0;
        this.O = 0;
        this.P = 0;
        this.Q = 0;
        this.R = 0;
        this.S = 0;
        this.T = 0;
        this.U = 0;
        float f2 = j0;
        this.V = f2;
        this.W = f2;
        b[] bVarArr = this.C;
        b bVar = b.FIXED;
        bVarArr[0] = bVar;
        bVarArr[1] = bVar;
        this.X = null;
        this.Y = 0;
        this.a0 = null;
        this.e0 = 0;
        this.f0 = 0;
        float[] fArr = this.g0;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.f135a = -1;
        this.f136b = -1;
        int[] iArr = this.q;
        iArr[0] = Integer.MAX_VALUE;
        iArr[1] = Integer.MAX_VALUE;
        this.f139e = 0;
        this.f140f = 0;
        this.j = 1.0f;
        this.m = 1.0f;
        this.i = Integer.MAX_VALUE;
        this.l = Integer.MAX_VALUE;
        this.h = 0;
        this.k = 0;
        this.n = -1;
        this.o = 1.0f;
        n nVar = this.f137c;
        if (nVar != null) {
            nVar.d();
        }
        n nVar2 = this.f138d;
        if (nVar2 != null) {
            nVar2.d();
        }
        this.p = null;
        this.b0 = false;
        this.c0 = false;
        this.d0 = false;
    }

    public void E() {
        f fVarK = k();
        if (fVarK != null && (fVarK instanceof g) && ((g) k()).N()) {
            return;
        }
        int size = this.B.size();
        for (int i = 0; i < size; i++) {
            this.B.get(i).j();
        }
    }

    public void F() {
        for (int i = 0; i < 6; i++) {
            this.A[i].d().d();
        }
    }

    public void G() {
    }

    public void H() {
        int i = this.I;
        int i2 = this.J;
        this.M = i;
        this.N = i2;
    }

    public void I() {
        for (int i = 0; i < 6; i++) {
            this.A[i].d().g();
        }
    }

    public void a(int i) {
        k.a(i, this);
    }

    public void a(boolean z) {
    }

    public void b(a.c.a.e eVar) {
        eVar.a(this.s);
        eVar.a(this.t);
        eVar.a(this.u);
        eVar.a(this.v);
        if (this.Q > 0) {
            eVar.a(this.w);
        }
    }

    public void b(boolean z) {
    }

    public int c() {
        return this.Q;
    }

    public int d(int i) {
        if (i == 0) {
            return s();
        }
        if (i == 1) {
            return i();
        }
        return 0;
    }

    public Object e() {
        return this.X;
    }

    public String f() {
        return this.Z;
    }

    public int g() {
        return this.M + this.O;
    }

    public int h() {
        return this.N + this.P;
    }

    public void i(int i) {
        this.q[1] = i;
    }

    public void j(int i) {
        this.q[0] = i;
    }

    public f k() {
        return this.D;
    }

    public n l() {
        if (this.f138d == null) {
            this.f138d = new n();
        }
        return this.f138d;
    }

    public n m() {
        if (this.f137c == null) {
            this.f137c = new n();
        }
        return this.f137c;
    }

    public void n(int i) {
        this.Y = i;
    }

    protected int o() {
        return this.I + this.O;
    }

    protected int p() {
        return this.J + this.P;
    }

    public void q(int i) {
        this.T = i;
    }

    public int r() {
        return this.Y;
    }

    public int s() {
        if (this.Y == 8) {
            return 0;
        }
        return this.E;
    }

    public int t() {
        return this.U;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (this.a0 != null) {
            str = "type: " + this.a0 + " ";
        } else {
            str = "";
        }
        sb.append(str);
        if (this.Z != null) {
            str2 = "id: " + this.Z + " ";
        }
        sb.append(str2);
        sb.append("(");
        sb.append(this.I);
        sb.append(", ");
        sb.append(this.J);
        sb.append(") - (");
        sb.append(this.E);
        sb.append(" x ");
        sb.append(this.F);
        sb.append(") wrap: (");
        sb.append(this.T);
        sb.append(" x ");
        sb.append(this.U);
        sb.append(")");
        return sb.toString();
    }

    public int u() {
        return this.T;
    }

    public int v() {
        return this.I;
    }

    public int w() {
        return this.J;
    }

    public boolean x() {
        return this.Q > 0;
    }

    public boolean y() {
        return this.s.d().f162b == 1 && this.u.d().f162b == 1 && this.t.d().f162b == 1 && this.v.d().f162b == 1;
    }

    public boolean z() {
        e eVar = this.s;
        e eVar2 = eVar.f118d;
        if (eVar2 != null && eVar2.f118d == eVar) {
            return true;
        }
        e eVar3 = this.u;
        e eVar4 = eVar3.f118d;
        return eVar4 != null && eVar4.f118d == eVar3;
    }

    private boolean t(int i) {
        int i2 = i * 2;
        e[] eVarArr = this.A;
        if (eVarArr[i2].f118d != null && eVarArr[i2].f118d.f118d != eVarArr[i2]) {
            int i3 = i2 + 1;
            if (eVarArr[i3].f118d != null && eVarArr[i3].f118d.f118d == eVarArr[i3]) {
                return true;
            }
        }
        return false;
    }

    public void a(a.c.a.c cVar) {
        this.s.a(cVar);
        this.t.a(cVar);
        this.u.a(cVar);
        this.v.a(cVar);
        this.w.a(cVar);
        this.z.a(cVar);
        this.x.a(cVar);
        this.y.a(cVar);
    }

    public void c(int i, int i2) {
        this.I = i;
        this.J = i2;
    }

    public void e(int i, int i2) {
        this.J = i;
        this.F = i2 - i;
        int i3 = this.F;
        int i4 = this.S;
        if (i3 < i4) {
            this.F = i4;
        }
    }

    public void f(int i) {
        this.Q = i;
    }

    public void g(int i) {
        this.F = i;
        int i2 = this.F;
        int i3 = this.S;
        if (i2 < i3) {
            this.F = i3;
        }
    }

    public void h(int i) {
        this.e0 = i;
    }

    public int i() {
        if (this.Y == 8) {
            return 0;
        }
        return this.F;
    }

    public b j() {
        return this.C[0];
    }

    public void k(int i) {
        if (i < 0) {
            this.S = 0;
        } else {
            this.S = i;
        }
    }

    public int n() {
        return v() + this.E;
    }

    public void o(int i) {
        this.E = i;
        int i2 = this.E;
        int i3 = this.R;
        if (i2 < i3) {
            this.E = i3;
        }
    }

    public void p(int i) {
        this.U = i;
    }

    public b q() {
        return this.C[1];
    }

    public void r(int i) {
        this.I = i;
    }

    public int d() {
        return w() + this.F;
    }

    public void s(int i) {
        this.J = i;
    }

    public void c(float f2) {
        this.W = f2;
    }

    void d(int i, int i2) {
        if (i2 == 0) {
            this.K = i;
        } else if (i2 == 1) {
            this.L = i;
        }
    }

    public void l(int i) {
        if (i < 0) {
            this.R = 0;
        } else {
            this.R = i;
        }
    }

    public void m(int i) {
        this.f0 = i;
    }

    public b c(int i) {
        if (i == 0) {
            return j();
        }
        if (i == 1) {
            return q();
        }
        return null;
    }

    public void d(float f2) {
        this.g0[1] = f2;
    }

    int e(int i) {
        if (i == 0) {
            return this.K;
        }
        if (i == 1) {
            return this.L;
        }
        return 0;
    }

    public float b(int i) {
        if (i == 0) {
            return this.V;
        }
        if (i == 1) {
            return this.W;
        }
        return -1.0f;
    }

    public void c(a.c.a.e eVar) {
        int iB = eVar.b(this.s);
        int iB2 = eVar.b(this.t);
        int iB3 = eVar.b(this.u);
        int iB4 = eVar.b(this.v);
        int i = iB4 - iB2;
        if (iB3 - iB < 0 || i < 0 || iB == Integer.MIN_VALUE || iB == Integer.MAX_VALUE || iB2 == Integer.MIN_VALUE || iB2 == Integer.MAX_VALUE || iB3 == Integer.MIN_VALUE || iB3 == Integer.MAX_VALUE || iB4 == Integer.MIN_VALUE || iB4 == Integer.MAX_VALUE) {
            iB4 = 0;
            iB = 0;
            iB2 = 0;
            iB3 = 0;
        }
        a(iB, iB2, iB3, iB4);
    }

    public ArrayList<e> b() {
        return this.B;
    }

    public void a(f fVar) {
        this.D = fVar;
    }

    public void b(int i, int i2) {
        this.O = i;
        this.P = i2;
    }

    public void a(f fVar, float f2, int i) {
        e.d dVar = e.d.CENTER;
        a(dVar, fVar, dVar, i, 0);
        this.r = f2;
    }

    public void b(int i, int i2, int i3, float f2) {
        this.f140f = i;
        this.k = i2;
        this.l = i3;
        this.m = f2;
        if (f2 >= 1.0f || this.f140f != 0) {
            return;
        }
        this.f140f = 2;
    }

    public void a(String str) {
        this.Z = str;
    }

    public void a(int i, int i2, int i3, float f2) {
        this.f139e = i;
        this.h = i2;
        this.i = i3;
        this.j = f2;
        if (f2 >= 1.0f || this.f139e != 0) {
            return;
        }
        this.f139e = 2;
    }

    /* JADX WARN: Code duplicated, block: B:38:0x0084 A[PHI: r0
      0x0084: PHI (r0v2 int) = (r0v1 int), (r0v0 int), (r0v0 int), (r0v0 int), (r0v0 int), (r0v0 int) binds: [B:45:0x0084, B:35:0x007d, B:23:0x004f, B:25:0x0055, B:27:0x0061, B:29:0x0065] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0084 -> B:39:0x0085). Please report as a decompilation issue!!! */
    public void b(String str) {
        float fAbs;
        int i = 0;
        if (str != null && str.length() != 0) {
            int i2 = -1;
            int length = str.length();
            int iIndexOf = str.indexOf(44);
            int i3 = 0;
            if (iIndexOf > 0 && iIndexOf < length - 1) {
                String strSubstring = str.substring(0, iIndexOf);
                if (strSubstring.equalsIgnoreCase("W")) {
                    i2 = 0;
                } else if (strSubstring.equalsIgnoreCase("H")) {
                    i2 = 1;
                }
                i3 = iIndexOf + 1;
            }
            int iIndexOf2 = str.indexOf(58);
            try {
                if (iIndexOf2 >= 0 && iIndexOf2 < length - 1) {
                    String strSubstring2 = str.substring(i3, iIndexOf2);
                    String strSubstring3 = str.substring(iIndexOf2 + 1);
                    if (strSubstring2.length() <= 0 || strSubstring3.length() <= 0) {
                        fAbs = 0.0f;
                    } else {
                        float f2 = Float.parseFloat(strSubstring2);
                        float f3 = Float.parseFloat(strSubstring3);
                        if (f2 <= 0.0f || f3 <= 0.0f) {
                            fAbs = 0.0f;
                        } else if (i2 == 1) {
                            fAbs = Math.abs(f3 / f2);
                        } else {
                            fAbs = Math.abs(f2 / f3);
                        }
                    }
                } else {
                    String strSubstring4 = str.substring(i3);
                    if (strSubstring4.length() > 0) {
                        fAbs = Float.parseFloat(strSubstring4);
                    } else {
                        fAbs = 0.0f;
                    }
                }
            } catch (NumberFormatException unused) {
            }
            i = (fAbs > i ? 1 : (fAbs == i ? 0 : -1));
            if (i > 0) {
                this.G = fAbs;
                this.H = i2;
                return;
            }
            return;
        }
        this.G = 0.0f;
    }

    public void a(float f2) {
        this.V = f2;
    }

    public void a(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = i3 - i;
        int i8 = i4 - i2;
        this.I = i;
        this.J = i2;
        if (this.Y == 8) {
            this.E = 0;
            this.F = 0;
            return;
        }
        if (this.C[0] != b.FIXED || i7 >= (i5 = this.E)) {
            i5 = i7;
        }
        if (this.C[1] != b.FIXED || i8 >= (i6 = this.F)) {
            i6 = i8;
        }
        this.E = i5;
        this.F = i6;
        int i9 = this.F;
        int i10 = this.S;
        if (i9 < i10) {
            this.F = i10;
        }
        int i11 = this.E;
        int i12 = this.R;
        if (i11 < i12) {
            this.E = i12;
        }
        this.c0 = true;
    }

    public void a(int i, int i2, int i3) {
        if (i3 == 0) {
            a(i, i2);
        } else if (i3 == 1) {
            e(i, i2);
        }
        this.c0 = true;
    }

    public void a(int i, int i2) {
        this.I = i;
        this.E = i2 - i;
        int i3 = this.E;
        int i4 = this.R;
        if (i3 < i4) {
            this.E = i4;
        }
    }

    public void b(float f2) {
        this.g0[0] = f2;
    }

    public void b(b bVar) {
        this.C[1] = bVar;
        if (bVar == b.WRAP_CONTENT) {
            g(this.U);
        }
    }

    public void a(Object obj) {
        this.X = obj;
    }

    public boolean a() {
        return this.Y != 8;
    }

    public void a(e.d dVar, f fVar, e.d dVar2, int i, int i2) {
        a(dVar).a(fVar.a(dVar2), i, i2, e.c.STRONG, 0, true);
    }

    public e a(e.d dVar) {
        switch (a.f141a[dVar.ordinal()]) {
            case 1:
                return this.s;
            case 2:
                return this.t;
            case 3:
                return this.u;
            case 4:
                return this.v;
            case 5:
                return this.w;
            case 6:
                return this.z;
            case 7:
                return this.x;
            case 8:
                return this.y;
            case 9:
                return null;
            default:
                throw new AssertionError(dVar.name());
        }
    }

    public void a(b bVar) {
        this.C[0] = bVar;
        if (bVar == b.WRAP_CONTENT) {
            o(this.T);
        }
    }

    /* JADX WARN: Code duplicated, block: B:102:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:106:0x01ab A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:110:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:113:0x01be  */
    /* JADX WARN: Code duplicated, block: B:116:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:118:0x01d4  */
    /* JADX WARN: Code duplicated, block: B:119:0x01dd  */
    /* JADX WARN: Code duplicated, block: B:122:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:123:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:125:0x0237  */
    /* JADX WARN: Code duplicated, block: B:128:0x0248 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:129:0x0249  */
    /* JADX WARN: Code duplicated, block: B:131:0x0252  */
    /* JADX WARN: Code duplicated, block: B:134:0x0258  */
    /* JADX WARN: Code duplicated, block: B:136:0x025b  */
    /* JADX WARN: Code duplicated, block: B:141:0x0265  */
    /* JADX WARN: Code duplicated, block: B:144:0x026b  */
    /* JADX WARN: Code duplicated, block: B:146:0x0275  */
    /* JADX WARN: Code duplicated, block: B:147:0x0281  */
    /* JADX WARN: Code duplicated, block: B:149:0x0295  */
    /* JADX WARN: Code duplicated, block: B:150:0x02a0  */
    /* JADX WARN: Code duplicated, block: B:152:0x02a4 A[PHI: r4 r10
      0x02a4: PHI (r4v5 a.c.a.i) = (r4v6 a.c.a.i), (r4v7 a.c.a.i) binds: [B:151:0x02a2, B:148:0x0293] A[DONT_GENERATE, DONT_INLINE]
      0x02a4: PHI (r10v4 a.c.a.e) = (r10v5 a.c.a.e), (r10v6 a.c.a.e) binds: [B:151:0x02a2, B:148:0x0293] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:155:0x02aa  */
    /* JADX WARN: Code duplicated, block: B:156:0x02b3  */
    /* JADX WARN: Code duplicated, block: B:159:0x02b9  */
    /* JADX WARN: Code duplicated, block: B:160:0x02c1  */
    /* JADX WARN: Code duplicated, block: B:163:0x02f8  */
    /* JADX WARN: Code duplicated, block: B:165:0x0300  */
    /* JADX WARN: Code duplicated, block: B:166:0x0310  */
    /* JADX WARN: Code duplicated, block: B:167:0x0321  */
    /* JADX WARN: Code duplicated, block: B:170:0x032b  */
    /* JADX WARN: Code duplicated, block: B:172:? A[RETURN, SYNTHETIC] */
    public void a(a.c.a.e eVar) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        a.c.a.i iVar;
        int i;
        int i2;
        int i3;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8;
        boolean z9;
        boolean z10;
        a.c.a.e eVar2;
        boolean z11;
        a.c.a.i iVar2;
        f fVar;
        a.c.a.i iVarA;
        f fVar2;
        a.c.a.i iVarA2;
        a.c.a.i iVar3;
        f fVar3;
        a.c.a.i iVar4;
        e eVar3;
        int i4;
        f fVar4;
        a.c.a.i iVarA3;
        f fVar5;
        a.c.a.i iVarA4;
        int i5;
        int i6;
        boolean z12;
        boolean zA;
        a.c.a.i iVarA5 = eVar.a(this.s);
        a.c.a.i iVarA6 = eVar.a(this.u);
        a.c.a.i iVarA7 = eVar.a(this.t);
        a.c.a.i iVarA8 = eVar.a(this.v);
        a.c.a.i iVarA9 = eVar.a(this.w);
        f fVar6 = this.D;
        if (fVar6 != null) {
            z = fVar6 != null && fVar6.C[0] == b.WRAP_CONTENT;
            f fVar7 = this.D;
            boolean z13 = fVar7 != null && fVar7.C[1] == b.WRAP_CONTENT;
            if (t(0)) {
                ((g) this.D).a(this, 0);
                z12 = true;
            } else {
                z12 = z();
            }
            if (t(1)) {
                ((g) this.D).a(this, 1);
                zA = true;
            } else {
                zA = A();
            }
            if (z && this.Y != 8 && this.s.f118d == null && this.u.f118d == null) {
                eVar.b(eVar.a(this.D.u), iVarA6, 0, 1);
            }
            if (z13 && this.Y != 8 && this.t.f118d == null && this.v.f118d == null && this.w == null) {
                eVar.b(eVar.a(this.D.v), iVarA8, 0, 1);
            }
            z2 = z13;
            z3 = z12;
            z4 = zA;
        } else {
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
        }
        int i7 = this.E;
        int i8 = this.R;
        if (i7 < i8) {
            i7 = i8;
        }
        int i9 = this.F;
        int i10 = this.S;
        if (i9 < i10) {
            i9 = i10;
        }
        boolean z14 = this.C[0] != b.MATCH_CONSTRAINT;
        boolean z15 = this.C[1] != b.MATCH_CONSTRAINT;
        this.n = this.H;
        float f2 = this.G;
        this.o = f2;
        int i11 = this.f139e;
        int i12 = this.f140f;
        if (f2 <= 0.0f || this.Y == 8) {
            iVar = iVarA9;
            i11 = i11;
            i = i7;
            i2 = i9;
            i3 = i12;
        } else {
            iVar = iVarA9;
            if (this.C[0] == b.MATCH_CONSTRAINT && i11 == 0) {
                i11 = 3;
            }
            if (this.C[1] == b.MATCH_CONSTRAINT && i12 == 0) {
                i12 = 3;
            }
            b[] bVarArr = this.C;
            b bVar = bVarArr[0];
            b bVar2 = b.MATCH_CONSTRAINT;
            if (bVar == bVar2 && bVarArr[1] == bVar2) {
                i6 = 3;
                if (i11 == 3 && i12 == 3) {
                    a(z, z2, z14, z15);
                }
                i = i7;
                i2 = i9;
                i3 = i12;
                z5 = true;
                int[] iArr = this.g;
                iArr[0] = i11;
                iArr[1] = i3;
                z6 = !z5 && ((i5 = this.n) == 0 || i5 == -1);
                if (this.C[0] == b.WRAP_CONTENT || !(this instanceof g)) {
                    z7 = false;
                } else {
                    z7 = true;
                }
                z8 = !this.z.i();
                if (this.f135a != 2) {
                    fVar4 = this.D;
                    if (fVar4 != null) {
                        iVarA3 = eVar.a(fVar4.u);
                    } else {
                        iVarA3 = null;
                    }
                    fVar5 = this.D;
                    if (fVar5 != null) {
                        iVarA4 = eVar.a(fVar5.s);
                    } else {
                        iVarA4 = null;
                    }
                    a(eVar, z, iVarA4, iVarA3, this.C[0], z7, this.s, this.u, this.I, i, this.R, this.q[0], this.V, z6, z3, i11, this.h, this.i, this.j, z8);
                }
                if (this.f136b == 2) {
                    return;
                }
                if (this.C[1] == b.WRAP_CONTENT || !(this instanceof g)) {
                    z9 = false;
                } else {
                    z9 = true;
                }
                if (z5 || !((i4 = this.n) == 1 || i4 == -1)) {
                    z10 = false;
                } else {
                    z10 = true;
                }
                if (this.Q > 0) {
                    if (this.w.d().f162b == 1) {
                        eVar2 = eVar;
                        this.w.d().a(eVar2);
                    } else {
                        eVar2 = eVar;
                        iVar4 = iVar;
                        iVar2 = iVarA7;
                        eVar2.a(iVar4, iVar2, c(), 6);
                        eVar3 = this.w.f118d;
                        if (eVar3 != null) {
                            eVar2.a(iVar4, eVar2.a(eVar3), 0, 6);
                            z11 = false;
                        } else {
                            z11 = z8;
                        }
                    }
                    fVar = this.D;
                    if (fVar != null) {
                        iVarA = eVar2.a(fVar.v);
                    } else {
                        iVarA = null;
                    }
                    fVar2 = this.D;
                    if (fVar2 != null) {
                        iVarA2 = eVar2.a(fVar2.t);
                    } else {
                        iVarA2 = null;
                    }
                    iVar3 = iVar2;
                    a(eVar, z2, iVarA2, iVarA, this.C[1], z9, this.t, this.v, this.J, i2, this.S, this.q[1], this.W, z10, z4, i3, this.k, this.l, this.m, z11);
                    if (z5) {
                        fVar3 = this;
                        if (fVar3.n == 1) {
                            eVar.a(iVarA8, iVar3, iVarA6, iVarA5, fVar3.o, 6);
                        } else {
                            eVar.a(iVarA6, iVarA5, iVarA8, iVar3, fVar3.o, 6);
                        }
                    } else {
                        fVar3 = this;
                    }
                    if (fVar3.z.i()) {
                        eVar.a(fVar3, fVar3.z.g().c(), (float) Math.toRadians(fVar3.r + 90.0f), fVar3.z.b());
                    }
                }
                eVar2 = eVar;
                iVar2 = iVarA7;
                z11 = z8;
                fVar = this.D;
                if (fVar != null) {
                    iVarA = eVar2.a(fVar.v);
                } else {
                    iVarA = null;
                }
                fVar2 = this.D;
                if (fVar2 != null) {
                    iVarA2 = eVar2.a(fVar2.t);
                } else {
                    iVarA2 = null;
                }
                iVar3 = iVar2;
                a(eVar, z2, iVarA2, iVarA, this.C[1], z9, this.t, this.v, this.J, i2, this.S, this.q[1], this.W, z10, z4, i3, this.k, this.l, this.m, z11);
                if (z5) {
                    fVar3 = this;
                    if (fVar3.n == 1) {
                        eVar.a(iVarA8, iVar3, iVarA6, iVarA5, fVar3.o, 6);
                    } else {
                        eVar.a(iVarA6, iVarA5, iVarA8, iVar3, fVar3.o, 6);
                    }
                } else {
                    fVar3 = this;
                }
                if (fVar3.z.i()) {
                    eVar.a(fVar3, fVar3.z.g().c(), (float) Math.toRadians(fVar3.r + 90.0f), fVar3.z.b());
                }
            }
            i6 = 3;
            b[] bVarArr2 = this.C;
            b bVar3 = bVarArr2[0];
            b bVar4 = b.MATCH_CONSTRAINT;
            if (bVar3 == bVar4 && i11 == i6) {
                this.n = 0;
                i = (int) (this.o * this.F);
                if (bVarArr2[1] == bVar4) {
                    i2 = i9;
                    i3 = i12;
                    z5 = true;
                    int[] iArr2 = this.g;
                    iArr2[0] = i11;
                    iArr2[1] = i3;
                    if (z5) {
                    }
                    if (this.C[0] == b.WRAP_CONTENT) {
                        z7 = false;
                    } else {
                        z7 = false;
                    }
                    z8 = !this.z.i();
                    if (this.f135a != 2) {
                        fVar4 = this.D;
                        if (fVar4 != null) {
                            iVarA3 = eVar.a(fVar4.u);
                        } else {
                            iVarA3 = null;
                        }
                        fVar5 = this.D;
                        if (fVar5 != null) {
                            iVarA4 = eVar.a(fVar5.s);
                        } else {
                            iVarA4 = null;
                        }
                        a(eVar, z, iVarA4, iVarA3, this.C[0], z7, this.s, this.u, this.I, i, this.R, this.q[0], this.V, z6, z3, i11, this.h, this.i, this.j, z8);
                    }
                    if (this.f136b == 2) {
                        return;
                    }
                    if (this.C[1] == b.WRAP_CONTENT) {
                        z9 = false;
                    } else {
                        z9 = false;
                    }
                    if (z5) {
                        z10 = false;
                    } else {
                        z10 = false;
                    }
                    if (this.Q > 0) {
                        if (this.w.d().f162b == 1) {
                            eVar2 = eVar;
                            this.w.d().a(eVar2);
                        } else {
                            eVar2 = eVar;
                            iVar4 = iVar;
                            iVar2 = iVarA7;
                            eVar2.a(iVar4, iVar2, c(), 6);
                            eVar3 = this.w.f118d;
                            if (eVar3 != null) {
                                eVar2.a(iVar4, eVar2.a(eVar3), 0, 6);
                                z11 = false;
                            } else {
                                z11 = z8;
                            }
                        }
                        fVar = this.D;
                        if (fVar != null) {
                            iVarA = eVar2.a(fVar.v);
                        } else {
                            iVarA = null;
                        }
                        fVar2 = this.D;
                        if (fVar2 != null) {
                            iVarA2 = eVar2.a(fVar2.t);
                        } else {
                            iVarA2 = null;
                        }
                        iVar3 = iVar2;
                        a(eVar, z2, iVarA2, iVarA, this.C[1], z9, this.t, this.v, this.J, i2, this.S, this.q[1], this.W, z10, z4, i3, this.k, this.l, this.m, z11);
                        if (z5) {
                            fVar3 = this;
                            if (fVar3.n == 1) {
                                eVar.a(iVarA8, iVar3, iVarA6, iVarA5, fVar3.o, 6);
                            } else {
                                eVar.a(iVarA6, iVarA5, iVarA8, iVar3, fVar3.o, 6);
                            }
                        } else {
                            fVar3 = this;
                        }
                        if (fVar3.z.i()) {
                            eVar.a(fVar3, fVar3.z.g().c(), (float) Math.toRadians(fVar3.r + 90.0f), fVar3.z.b());
                        }
                    }
                    eVar2 = eVar;
                    iVar2 = iVarA7;
                    z11 = z8;
                    fVar = this.D;
                    if (fVar != null) {
                        iVarA = eVar2.a(fVar.v);
                    } else {
                        iVarA = null;
                    }
                    fVar2 = this.D;
                    if (fVar2 != null) {
                        iVarA2 = eVar2.a(fVar2.t);
                    } else {
                        iVarA2 = null;
                    }
                    iVar3 = iVar2;
                    a(eVar, z2, iVarA2, iVarA, this.C[1], z9, this.t, this.v, this.J, i2, this.S, this.q[1], this.W, z10, z4, i3, this.k, this.l, this.m, z11);
                    if (z5) {
                        fVar3 = this;
                        if (fVar3.n == 1) {
                            eVar.a(iVarA8, iVar3, iVarA6, iVarA5, fVar3.o, 6);
                        } else {
                            eVar.a(iVarA6, iVarA5, iVarA8, iVar3, fVar3.o, 6);
                        }
                    } else {
                        fVar3 = this;
                    }
                    if (fVar3.z.i()) {
                        eVar.a(fVar3, fVar3.z.g().c(), (float) Math.toRadians(fVar3.r + 90.0f), fVar3.z.b());
                    }
                }
                i2 = i9;
                i3 = i12;
                i11 = 4;
            } else {
                if (this.C[1] == b.MATCH_CONSTRAINT && i12 == 3) {
                    this.n = 1;
                    if (this.H == -1) {
                        this.o = 1.0f / this.o;
                    }
                    i2 = (int) (this.o * this.E);
                    i11 = i11;
                    i = i7;
                    if (this.C[0] != b.MATCH_CONSTRAINT) {
                        i3 = 4;
                    }
                    int[] iArr3 = this.g;
                    iArr3[0] = i11;
                    iArr3[1] = i3;
                    if (z5) {
                    }
                    if (this.C[0] == b.WRAP_CONTENT) {
                        z7 = false;
                    } else {
                        z7 = false;
                    }
                    z8 = !this.z.i();
                    if (this.f135a != 2) {
                        fVar4 = this.D;
                        if (fVar4 != null) {
                            iVarA3 = eVar.a(fVar4.u);
                        } else {
                            iVarA3 = null;
                        }
                        fVar5 = this.D;
                        if (fVar5 != null) {
                            iVarA4 = eVar.a(fVar5.s);
                        } else {
                            iVarA4 = null;
                        }
                        a(eVar, z, iVarA4, iVarA3, this.C[0], z7, this.s, this.u, this.I, i, this.R, this.q[0], this.V, z6, z3, i11, this.h, this.i, this.j, z8);
                    }
                    if (this.f136b == 2) {
                        return;
                    }
                    if (this.C[1] == b.WRAP_CONTENT) {
                        z9 = false;
                    } else {
                        z9 = false;
                    }
                    if (z5) {
                        z10 = false;
                    } else {
                        z10 = false;
                    }
                    if (this.Q > 0) {
                        if (this.w.d().f162b == 1) {
                            eVar2 = eVar;
                            this.w.d().a(eVar2);
                        } else {
                            eVar2 = eVar;
                            iVar4 = iVar;
                            iVar2 = iVarA7;
                            eVar2.a(iVar4, iVar2, c(), 6);
                            eVar3 = this.w.f118d;
                            if (eVar3 != null) {
                                eVar2.a(iVar4, eVar2.a(eVar3), 0, 6);
                                z11 = false;
                            } else {
                                z11 = z8;
                            }
                        }
                        fVar = this.D;
                        if (fVar != null) {
                            iVarA = eVar2.a(fVar.v);
                        } else {
                            iVarA = null;
                        }
                        fVar2 = this.D;
                        if (fVar2 != null) {
                            iVarA2 = eVar2.a(fVar2.t);
                        } else {
                            iVarA2 = null;
                        }
                        iVar3 = iVar2;
                        a(eVar, z2, iVarA2, iVarA, this.C[1], z9, this.t, this.v, this.J, i2, this.S, this.q[1], this.W, z10, z4, i3, this.k, this.l, this.m, z11);
                        if (z5) {
                            fVar3 = this;
                            if (fVar3.n == 1) {
                                eVar.a(iVarA8, iVar3, iVarA6, iVarA5, fVar3.o, 6);
                            } else {
                                eVar.a(iVarA6, iVarA5, iVarA8, iVar3, fVar3.o, 6);
                            }
                        } else {
                            fVar3 = this;
                        }
                        if (fVar3.z.i()) {
                            eVar.a(fVar3, fVar3.z.g().c(), (float) Math.toRadians(fVar3.r + 90.0f), fVar3.z.b());
                        }
                    }
                    eVar2 = eVar;
                    iVar2 = iVarA7;
                    z11 = z8;
                    fVar = this.D;
                    if (fVar != null) {
                        iVarA = eVar2.a(fVar.v);
                    } else {
                        iVarA = null;
                    }
                    fVar2 = this.D;
                    if (fVar2 != null) {
                        iVarA2 = eVar2.a(fVar2.t);
                    } else {
                        iVarA2 = null;
                    }
                    iVar3 = iVar2;
                    a(eVar, z2, iVarA2, iVarA, this.C[1], z9, this.t, this.v, this.J, i2, this.S, this.q[1], this.W, z10, z4, i3, this.k, this.l, this.m, z11);
                    if (z5) {
                        fVar3 = this;
                        if (fVar3.n == 1) {
                            eVar.a(iVarA8, iVar3, iVarA6, iVarA5, fVar3.o, 6);
                        } else {
                            eVar.a(iVarA6, iVarA5, iVarA8, iVar3, fVar3.o, 6);
                        }
                    } else {
                        fVar3 = this;
                    }
                    if (fVar3.z.i()) {
                        eVar.a(fVar3, fVar3.z.g().c(), (float) Math.toRadians(fVar3.r + 90.0f), fVar3.z.b());
                    }
                }
                i = i7;
                i2 = i9;
                i3 = i12;
                z5 = true;
                int[] iArr4 = this.g;
                iArr4[0] = i11;
                iArr4[1] = i3;
                if (z5) {
                }
                if (this.C[0] == b.WRAP_CONTENT) {
                    z7 = false;
                } else {
                    z7 = false;
                }
                z8 = !this.z.i();
                if (this.f135a != 2) {
                    fVar4 = this.D;
                    if (fVar4 != null) {
                        iVarA3 = eVar.a(fVar4.u);
                    } else {
                        iVarA3 = null;
                    }
                    fVar5 = this.D;
                    if (fVar5 != null) {
                        iVarA4 = eVar.a(fVar5.s);
                    } else {
                        iVarA4 = null;
                    }
                    a(eVar, z, iVarA4, iVarA3, this.C[0], z7, this.s, this.u, this.I, i, this.R, this.q[0], this.V, z6, z3, i11, this.h, this.i, this.j, z8);
                }
                if (this.f136b == 2) {
                    return;
                }
                if (this.C[1] == b.WRAP_CONTENT) {
                    z9 = false;
                } else {
                    z9 = false;
                }
                if (z5) {
                    z10 = false;
                } else {
                    z10 = false;
                }
                if (this.Q > 0) {
                    if (this.w.d().f162b == 1) {
                        eVar2 = eVar;
                        this.w.d().a(eVar2);
                    } else {
                        eVar2 = eVar;
                        iVar4 = iVar;
                        iVar2 = iVarA7;
                        eVar2.a(iVar4, iVar2, c(), 6);
                        eVar3 = this.w.f118d;
                        if (eVar3 != null) {
                            eVar2.a(iVar4, eVar2.a(eVar3), 0, 6);
                            z11 = false;
                        } else {
                            z11 = z8;
                        }
                    }
                    fVar = this.D;
                    if (fVar != null) {
                        iVarA = eVar2.a(fVar.v);
                    } else {
                        iVarA = null;
                    }
                    fVar2 = this.D;
                    if (fVar2 != null) {
                        iVarA2 = eVar2.a(fVar2.t);
                    } else {
                        iVarA2 = null;
                    }
                    iVar3 = iVar2;
                    a(eVar, z2, iVarA2, iVarA, this.C[1], z9, this.t, this.v, this.J, i2, this.S, this.q[1], this.W, z10, z4, i3, this.k, this.l, this.m, z11);
                    if (z5) {
                        fVar3 = this;
                        if (fVar3.n == 1) {
                            eVar.a(iVarA8, iVar3, iVarA6, iVarA5, fVar3.o, 6);
                        } else {
                            eVar.a(iVarA6, iVarA5, iVarA8, iVar3, fVar3.o, 6);
                        }
                    } else {
                        fVar3 = this;
                    }
                    if (fVar3.z.i()) {
                        eVar.a(fVar3, fVar3.z.g().c(), (float) Math.toRadians(fVar3.r + 90.0f), fVar3.z.b());
                    }
                }
                eVar2 = eVar;
                iVar2 = iVarA7;
                z11 = z8;
                fVar = this.D;
                if (fVar != null) {
                    iVarA = eVar2.a(fVar.v);
                } else {
                    iVarA = null;
                }
                fVar2 = this.D;
                if (fVar2 != null) {
                    iVarA2 = eVar2.a(fVar2.t);
                } else {
                    iVarA2 = null;
                }
                iVar3 = iVar2;
                a(eVar, z2, iVarA2, iVarA, this.C[1], z9, this.t, this.v, this.J, i2, this.S, this.q[1], this.W, z10, z4, i3, this.k, this.l, this.m, z11);
                if (z5) {
                    fVar3 = this;
                    if (fVar3.n == 1) {
                        eVar.a(iVarA8, iVar3, iVarA6, iVarA5, fVar3.o, 6);
                    } else {
                        eVar.a(iVarA6, iVarA5, iVarA8, iVar3, fVar3.o, 6);
                    }
                } else {
                    fVar3 = this;
                }
                if (fVar3.z.i()) {
                    eVar.a(fVar3, fVar3.z.g().c(), (float) Math.toRadians(fVar3.r + 90.0f), fVar3.z.b());
                }
            }
        }
        z5 = false;
        int[] iArr5 = this.g;
        iArr5[0] = i11;
        iArr5[1] = i3;
        if (z5) {
        }
        if (this.C[0] == b.WRAP_CONTENT) {
            z7 = false;
        } else {
            z7 = false;
        }
        z8 = !this.z.i();
        if (this.f135a != 2) {
            fVar4 = this.D;
            if (fVar4 != null) {
                iVarA3 = eVar.a(fVar4.u);
            } else {
                iVarA3 = null;
            }
            fVar5 = this.D;
            if (fVar5 != null) {
                iVarA4 = eVar.a(fVar5.s);
            } else {
                iVarA4 = null;
            }
            a(eVar, z, iVarA4, iVarA3, this.C[0], z7, this.s, this.u, this.I, i, this.R, this.q[0], this.V, z6, z3, i11, this.h, this.i, this.j, z8);
        }
        if (this.f136b == 2) {
            return;
        }
        if (this.C[1] == b.WRAP_CONTENT) {
            z9 = false;
        } else {
            z9 = false;
        }
        if (z5) {
            z10 = false;
        } else {
            z10 = false;
        }
        if (this.Q > 0) {
            if (this.w.d().f162b == 1) {
                eVar2 = eVar;
                this.w.d().a(eVar2);
            } else {
                eVar2 = eVar;
                iVar4 = iVar;
                iVar2 = iVarA7;
                eVar2.a(iVar4, iVar2, c(), 6);
                eVar3 = this.w.f118d;
                if (eVar3 != null) {
                    eVar2.a(iVar4, eVar2.a(eVar3), 0, 6);
                    z11 = false;
                } else {
                    z11 = z8;
                }
            }
            fVar = this.D;
            if (fVar != null) {
                iVarA = eVar2.a(fVar.v);
            } else {
                iVarA = null;
            }
            fVar2 = this.D;
            if (fVar2 != null) {
                iVarA2 = eVar2.a(fVar2.t);
            } else {
                iVarA2 = null;
            }
            iVar3 = iVar2;
            a(eVar, z2, iVarA2, iVarA, this.C[1], z9, this.t, this.v, this.J, i2, this.S, this.q[1], this.W, z10, z4, i3, this.k, this.l, this.m, z11);
            if (z5) {
                fVar3 = this;
                if (fVar3.n == 1) {
                    eVar.a(iVarA8, iVar3, iVarA6, iVarA5, fVar3.o, 6);
                } else {
                    eVar.a(iVarA6, iVarA5, iVarA8, iVar3, fVar3.o, 6);
                }
            } else {
                fVar3 = this;
            }
            if (fVar3.z.i()) {
                eVar.a(fVar3, fVar3.z.g().c(), (float) Math.toRadians(fVar3.r + 90.0f), fVar3.z.b());
            }
        }
        eVar2 = eVar;
        iVar2 = iVarA7;
        z11 = z8;
        fVar = this.D;
        if (fVar != null) {
            iVarA = eVar2.a(fVar.v);
        } else {
            iVarA = null;
        }
        fVar2 = this.D;
        if (fVar2 != null) {
            iVarA2 = eVar2.a(fVar2.t);
        } else {
            iVarA2 = null;
        }
        iVar3 = iVar2;
        a(eVar, z2, iVarA2, iVarA, this.C[1], z9, this.t, this.v, this.J, i2, this.S, this.q[1], this.W, z10, z4, i3, this.k, this.l, this.m, z11);
        if (z5) {
            fVar3 = this;
            if (fVar3.n == 1) {
                eVar.a(iVarA8, iVar3, iVarA6, iVarA5, fVar3.o, 6);
            } else {
                eVar.a(iVarA6, iVarA5, iVarA8, iVar3, fVar3.o, 6);
            }
        } else {
            fVar3 = this;
        }
        if (fVar3.z.i()) {
            eVar.a(fVar3, fVar3.z.g().c(), (float) Math.toRadians(fVar3.r + 90.0f), fVar3.z.b());
        }
    }

    public void a(boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.n == -1) {
            if (z3 && !z4) {
                this.n = 0;
            } else if (!z3 && z4) {
                this.n = 1;
                if (this.H == -1) {
                    this.o = 1.0f / this.o;
                }
            }
        }
        if (this.n == 0 && (!this.t.i() || !this.v.i())) {
            this.n = 1;
        } else if (this.n == 1 && (!this.s.i() || !this.u.i())) {
            this.n = 0;
        }
        if (this.n == -1 && (!this.t.i() || !this.v.i() || !this.s.i() || !this.u.i())) {
            if (this.t.i() && this.v.i()) {
                this.n = 0;
            } else if (this.s.i() && this.u.i()) {
                this.o = 1.0f / this.o;
                this.n = 1;
            }
        }
        if (this.n == -1) {
            if (z && !z2) {
                this.n = 0;
            } else if (!z && z2) {
                this.o = 1.0f / this.o;
                this.n = 1;
            }
        }
        if (this.n == -1) {
            if (this.h > 0 && this.k == 0) {
                this.n = 0;
            } else if (this.h == 0 && this.k > 0) {
                this.o = 1.0f / this.o;
                this.n = 1;
            }
        }
        if (this.n == -1 && z && z2) {
            this.o = 1.0f / this.o;
            this.n = 1;
        }
    }

    /* JADX WARN: Code duplicated, block: B:104:0x01ed  */
    /* JADX WARN: Code duplicated, block: B:162:0x02a2  */
    /* JADX WARN: Code duplicated, block: B:164:0x02d5 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:167:0x02df A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:169:0x02e5  */
    /* JADX WARN: Code duplicated, block: B:173:0x02f4  */
    /* JADX WARN: Code duplicated, block: B:175:0x02f8 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:176:0x02fa A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:179:0x0305 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:180:0x0307 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:183:0x0313  */
    /* JADX WARN: Code duplicated, block: B:184:0x031c  */
    /* JADX WARN: Code duplicated, block: B:188:0x0323  */
    /* JADX WARN: Code duplicated, block: B:197:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:98:0x01d8 A[ADDED_TO_REGION] */
    private void a(a.c.a.e eVar, boolean z, a.c.a.i iVar, a.c.a.i iVar2, b bVar, boolean z2, e eVar2, e eVar3, int i, int i2, int i3, int i4, float f2, boolean z3, boolean z4, int i5, int i6, int i7, float f3, boolean z5) {
        boolean z6;
        int iMin;
        int i8;
        a.c.a.i iVarA;
        a.c.a.i iVarA2;
        int i9;
        boolean z7;
        int i10;
        a.c.a.i iVar3;
        boolean z8;
        int i11;
        boolean z9;
        a.c.a.i iVar4;
        a.c.a.i iVar5;
        a.c.a.i iVar6;
        boolean z10;
        boolean z11;
        int i12;
        int i13;
        a.c.a.i iVar7;
        int i14;
        int i15;
        boolean z12;
        int i16;
        boolean z13;
        a.c.a.i iVarA3 = eVar.a(eVar2);
        a.c.a.i iVarA4 = eVar.a(eVar3);
        a.c.a.i iVarA5 = eVar.a(eVar2.g());
        a.c.a.i iVarA6 = eVar.a(eVar3.g());
        if (eVar.g && eVar2.d().f162b == 1 && eVar3.d().f162b == 1) {
            if (a.c.a.e.h() != null) {
                a.c.a.e.h().r++;
            }
            eVar2.d().a(eVar);
            eVar3.d().a(eVar);
            if (z4 || !z) {
                return;
            }
            eVar.b(iVar2, iVarA4, 0, 6);
            return;
        }
        if (a.c.a.e.h() != null) {
            a.c.a.e.h().z++;
        }
        boolean zI = eVar2.i();
        boolean zI2 = eVar3.i();
        boolean zI3 = this.z.i();
        int i17 = zI ? 1 : 0;
        if (zI2) {
            i17++;
        }
        if (zI3) {
            i17++;
        }
        int i18 = i17;
        int i19 = z3 ? 3 : i5;
        int i20 = a.f142b[bVar.ordinal()];
        boolean z14 = (i20 == 1 || i20 == 2 || i20 == 3 || i20 != 4 || i19 == 4) ? false : true;
        if (this.Y == 8) {
            iMin = 0;
            z6 = false;
        } else {
            z6 = z14;
            iMin = i2;
        }
        if (z5) {
            if (!zI && !zI2 && !zI3) {
                eVar.a(iVarA3, i);
            } else if (zI && !zI2) {
                i8 = 6;
                eVar.a(iVarA3, iVarA5, eVar2.b(), 6);
            }
            i8 = 6;
        } else {
            i8 = 6;
        }
        if (z6) {
            int i21 = i6 == -2 ? iMin : i6;
            if (i7 == -2) {
                i7 = iMin;
            }
            if (i21 > 0) {
                eVar.b(iVarA4, iVarA3, i21, 6);
                iMin = Math.max(iMin, i21);
            }
            if (i7 > 0) {
                eVar.c(iVarA4, iVarA3, i7, 6);
                iMin = Math.min(iMin, i7);
            }
            if (i19 == 1) {
                if (z) {
                    eVar.a(iVarA4, iVarA3, iMin, 6);
                    z6 = z6;
                    i9 = 0;
                } else if (z4) {
                    eVar.a(iVarA4, iVarA3, iMin, 4);
                } else {
                    eVar.a(iVarA4, iVarA3, iMin, 1);
                }
                z7 = z6;
                if (z7 || i18 == 2 || z3) {
                    i10 = i21;
                    z6 = z7;
                } else {
                    int iMax = Math.max(i21, iMin);
                    if (i7 > 0) {
                        iMax = Math.min(i7, iMax);
                    }
                    eVar.a(iVarA4, iVarA3, iMax, 6);
                    i10 = i21;
                    z6 = false;
                }
            } else {
                if (i19 == 2) {
                    if (eVar2.h() != e.d.TOP && eVar2.h() != e.d.BOTTOM) {
                        iVarA = eVar.a(this.D.a(e.d.LEFT));
                        iVarA2 = eVar.a(this.D.a(e.d.RIGHT));
                    } else {
                        iVarA = eVar.a(this.D.a(e.d.TOP));
                        iVarA2 = eVar.a(this.D.a(e.d.BOTTOM));
                    }
                    a.c.a.i iVar8 = iVarA2;
                    a.c.a.b bVarB = eVar.b();
                    i19 = i19;
                    iVarA5 = iVarA5;
                    iMin = iMin;
                    i18 = i18;
                    i9 = 0;
                    i21 = i21;
                    iVarA6 = iVarA6;
                    bVarB.a(iVarA4, iVarA3, iVar8, iVarA, f3);
                    eVar.a(bVarB);
                    z7 = false;
                }
                if (z7) {
                    i10 = i21;
                    z6 = z7;
                } else {
                    i10 = i21;
                    z6 = z7;
                }
            }
            i9 = 0;
            z7 = z6;
            if (z7) {
                i10 = i21;
                z6 = z7;
            } else {
                i10 = i21;
                z6 = z7;
            }
        } else {
            if (z2) {
                eVar.a(iVarA4, iVarA3, 0, 3);
                if (i3 > 0) {
                    eVar.b(iVarA4, iVarA3, i3, 6);
                }
                if (i4 < Integer.MAX_VALUE) {
                    eVar.c(iVarA4, iVarA3, i4, 6);
                }
            } else {
                eVar.a(iVarA4, iVarA3, iMin, i8);
            }
            i7 = i7;
            i19 = i19;
            i18 = i18;
            iVarA6 = iVarA6;
            iVarA5 = iVarA5;
            i9 = 0;
            i10 = i6;
        }
        if (!z5 || z4) {
            if (i18 >= 2 || !z) {
                return;
            }
            eVar.b(iVarA3, iVar, 0, 6);
            eVar.b(iVar2, iVarA4, 0, 6);
            return;
        }
        if (zI || zI2 || zI3) {
            if (!zI || zI2) {
                if (zI || !zI2) {
                    a.c.a.i iVar9 = iVarA6;
                    if (zI && zI2) {
                        if (z6) {
                            if (z && i3 == 0) {
                                eVar.b(iVarA4, iVarA3, 0, 6);
                            }
                            if (i19 != 0) {
                                int i22 = i19;
                                iVar3 = iVarA5;
                                if (i22 == 1) {
                                    z8 = true;
                                    i11 = 6;
                                    z9 = true;
                                } else if (i22 == 3) {
                                    int i23 = (z3 || this.n == -1 || i7 > 0) ? 4 : 6;
                                    eVar.a(iVarA3, iVar3, eVar2.b(), i23);
                                    eVar.a(iVarA4, iVar9, -eVar3.b(), i23);
                                    z8 = true;
                                    i11 = 5;
                                    z9 = true;
                                } else {
                                    z8 = false;
                                }
                                if (z8) {
                                    z11 = true;
                                    iVar5 = iVar9;
                                    iVar4 = iVar3;
                                    iVar6 = iVarA4;
                                    eVar.a(iVarA3, iVar3, eVar2.b(), f2, iVar9, iVarA4, eVar3.b(), i11);
                                    z12 = eVar2.f118d.f116b instanceof a.c.a.j.b;
                                    boolean z15 = eVar3.f118d.f116b instanceof a.c.a.j.b;
                                    if (z12 || z15) {
                                        if (z12 && z15) {
                                            z10 = z;
                                            i12 = 6;
                                        }
                                        i13 = 5;
                                    } else {
                                        z11 = z;
                                        z10 = true;
                                        i12 = 5;
                                        i13 = 6;
                                    }
                                    if (z9) {
                                        i12 = 6;
                                        i13 = 6;
                                    }
                                    if ((z6 && z11) || z9) {
                                        eVar.b(iVarA3, iVar4, eVar2.b(), i12);
                                    }
                                    if ((z6 && z10) || z9) {
                                        eVar.c(iVar6, iVar5, -eVar3.b(), i13);
                                    }
                                    if (z) {
                                        iVar7 = iVar6;
                                        i14 = 6;
                                        i15 = 0;
                                        eVar.b(iVarA3, iVar, 0, 6);
                                    } else {
                                        iVar7 = iVar6;
                                    }
                                    if (z) {
                                        eVar.b(iVar2, iVar7, i15, i14);
                                    }
                                }
                                iVar4 = iVar3;
                                iVar5 = iVar9;
                                iVar6 = iVarA4;
                                z10 = z;
                                z11 = z10;
                                i12 = 5;
                                i13 = 5;
                                if (z9) {
                                    i12 = 6;
                                    i13 = 6;
                                }
                                if (z6) {
                                    eVar.b(iVarA3, iVar4, eVar2.b(), i12);
                                } else {
                                    eVar.b(iVarA3, iVar4, eVar2.b(), i12);
                                }
                                if (z6) {
                                    eVar.c(iVar6, iVar5, -eVar3.b(), i13);
                                } else {
                                    eVar.c(iVar6, iVar5, -eVar3.b(), i13);
                                }
                                if (z) {
                                    iVar7 = iVar6;
                                    i14 = 6;
                                    i15 = 0;
                                    eVar.b(iVarA3, iVar, 0, 6);
                                } else {
                                    iVar7 = iVar6;
                                }
                                if (z) {
                                    eVar.b(iVar2, iVar7, i15, i14);
                                }
                            }
                            if (i7 > 0 || i10 > 0) {
                                i16 = 4;
                                z13 = true;
                            } else {
                                i16 = 6;
                                z13 = false;
                            }
                            iVar3 = iVarA5;
                            eVar.a(iVarA3, iVar3, eVar2.b(), i16);
                            eVar.a(iVarA4, iVar9, -eVar3.b(), i16);
                            z8 = i7 > 0 || i10 > 0;
                            z9 = z13;
                            i11 = 5;
                            if (z8) {
                                z11 = true;
                                iVar5 = iVar9;
                                iVar4 = iVar3;
                                iVar6 = iVarA4;
                                eVar.a(iVarA3, iVar3, eVar2.b(), f2, iVar9, iVarA4, eVar3.b(), i11);
                                z12 = eVar2.f118d.f116b instanceof a.c.a.j.b;
                                boolean z16 = eVar3.f118d.f116b instanceof a.c.a.j.b;
                                if (z12) {
                                }
                                if (z12) {
                                }
                                i13 = 5;
                                if (z9) {
                                    i12 = 6;
                                    i13 = 6;
                                }
                                if (z6) {
                                    eVar.b(iVarA3, iVar4, eVar2.b(), i12);
                                } else {
                                    eVar.b(iVarA3, iVar4, eVar2.b(), i12);
                                }
                                if (z6) {
                                    eVar.c(iVar6, iVar5, -eVar3.b(), i13);
                                } else {
                                    eVar.c(iVar6, iVar5, -eVar3.b(), i13);
                                }
                                if (z) {
                                    iVar7 = iVar6;
                                    i14 = 6;
                                    i15 = 0;
                                    eVar.b(iVarA3, iVar, 0, 6);
                                } else {
                                    iVar7 = iVar6;
                                }
                                if (z) {
                                    eVar.b(iVar2, iVar7, i15, i14);
                                }
                            }
                            iVar4 = iVar3;
                            iVar5 = iVar9;
                            iVar6 = iVarA4;
                            z10 = z;
                            z11 = z10;
                            i12 = 5;
                            i13 = 5;
                            if (z9) {
                                i12 = 6;
                                i13 = 6;
                            }
                            if (z6) {
                                eVar.b(iVarA3, iVar4, eVar2.b(), i12);
                            } else {
                                eVar.b(iVarA3, iVar4, eVar2.b(), i12);
                            }
                            if (z6) {
                                eVar.c(iVar6, iVar5, -eVar3.b(), i13);
                            } else {
                                eVar.c(iVar6, iVar5, -eVar3.b(), i13);
                            }
                            if (z) {
                                iVar7 = iVar6;
                                i14 = 6;
                                i15 = 0;
                                eVar.b(iVarA3, iVar, 0, 6);
                            } else {
                                iVar7 = iVar6;
                            }
                            if (z) {
                                eVar.b(iVar2, iVar7, i15, i14);
                            }
                        }
                        iVar3 = iVarA5;
                        z8 = true;
                        i11 = 5;
                        z9 = false;
                        if (z8) {
                            z11 = true;
                            iVar5 = iVar9;
                            iVar4 = iVar3;
                            iVar6 = iVarA4;
                            eVar.a(iVarA3, iVar3, eVar2.b(), f2, iVar9, iVarA4, eVar3.b(), i11);
                            z12 = eVar2.f118d.f116b instanceof a.c.a.j.b;
                            boolean z17 = eVar3.f118d.f116b instanceof a.c.a.j.b;
                            if (z12) {
                            }
                            if (z12) {
                            }
                            i13 = 5;
                            if (z9) {
                                i12 = 6;
                                i13 = 6;
                            }
                            if (z6) {
                                eVar.b(iVarA3, iVar4, eVar2.b(), i12);
                            } else {
                                eVar.b(iVarA3, iVar4, eVar2.b(), i12);
                            }
                            if (z6) {
                                eVar.c(iVar6, iVar5, -eVar3.b(), i13);
                            } else {
                                eVar.c(iVar6, iVar5, -eVar3.b(), i13);
                            }
                            if (z) {
                                iVar7 = iVar6;
                                i14 = 6;
                                i15 = 0;
                                eVar.b(iVarA3, iVar, 0, 6);
                            } else {
                                iVar7 = iVar6;
                            }
                            if (z) {
                                eVar.b(iVar2, iVar7, i15, i14);
                            }
                        }
                        iVar4 = iVar3;
                        iVar5 = iVar9;
                        iVar6 = iVarA4;
                        z10 = z;
                        z11 = z10;
                        i12 = 5;
                        i13 = 5;
                        if (z9) {
                            i12 = 6;
                            i13 = 6;
                        }
                        if (z6) {
                            eVar.b(iVarA3, iVar4, eVar2.b(), i12);
                        } else {
                            eVar.b(iVarA3, iVar4, eVar2.b(), i12);
                        }
                        if (z6) {
                            eVar.c(iVar6, iVar5, -eVar3.b(), i13);
                        } else {
                            eVar.c(iVar6, iVar5, -eVar3.b(), i13);
                        }
                        if (z) {
                            iVar7 = iVar6;
                            i14 = 6;
                            i15 = 0;
                            eVar.b(iVarA3, iVar, 0, 6);
                        } else {
                            iVar7 = iVar6;
                        }
                        if (z) {
                            eVar.b(iVar2, iVar7, i15, i14);
                        }
                    }
                    i14 = 6;
                    i15 = 0;
                    if (z) {
                        eVar.b(iVar2, iVar7, i15, i14);
                    }
                }
                eVar.a(iVarA4, iVarA6, -eVar3.b(), 6);
                if (z) {
                    eVar.b(iVarA3, iVar, i9, 5);
                }
            } else if (z) {
                eVar.b(iVar2, iVarA4, i9, 5);
            }
        } else if (z) {
            eVar.b(iVar2, iVarA4, i9, 5);
        }
        iVar7 = iVarA4;
        i14 = 6;
        i15 = 0;
        if (z) {
            eVar.b(iVar2, iVar7, i15, i14);
        }
    }
}
