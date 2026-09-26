package a.c.a.j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: ConstraintWidgetContainer.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends q {
    private p n0;
    int o0;
    int p0;
    int q0;
    int r0;
    private boolean l0 = false;
    protected a.c.a.e m0 = new a.c.a.e();
    int s0 = 0;
    int t0 = 0;
    d[] u0 = new d[4];
    d[] v0 = new d[4];
    public List<h> w0 = new ArrayList();
    public boolean x0 = false;
    public boolean y0 = false;
    public boolean z0 = false;
    public int A0 = 0;
    public int B0 = 0;
    private int C0 = 7;
    public boolean D0 = false;
    private boolean E0 = false;
    private boolean F0 = false;

    private void V() {
        this.s0 = 0;
        this.t0 = 0;
    }

    private void e(f fVar) {
        int i = this.t0 + 1;
        d[] dVarArr = this.u0;
        if (i >= dVarArr.length) {
            this.u0 = (d[]) Arrays.copyOf(dVarArr, dVarArr.length * 2);
        }
        this.u0[this.t0] = new d(fVar, 1, P());
        this.t0++;
    }

    @Override // a.c.a.j.q, a.c.a.j.f
    public void D() {
        this.m0.f();
        this.o0 = 0;
        this.q0 = 0;
        this.p0 = 0;
        this.r0 = 0;
        this.w0.clear();
        this.D0 = false;
        super.D();
    }

    /* JADX WARN: Code duplicated, block: B:107:0x0252  */
    /* JADX WARN: Code duplicated, block: B:110:0x0267  */
    /* JADX WARN: Code duplicated, block: B:113:0x0283  */
    /* JADX WARN: Code duplicated, block: B:114:0x0290  */
    /* JADX WARN: Code duplicated, block: B:116:0x0293  */
    /* JADX WARN: Code duplicated, block: B:118:0x029c A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:124:0x02ba A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:128:0x02d0 A[PHI: r0 r9
      0x02d0: PHI (r0v34 boolean) = (r0v33 boolean), (r0v36 boolean), (r0v36 boolean), (r0v36 boolean) binds: [B:115:0x0291, B:123:0x02b8, B:124:0x02ba, B:126:0x02c0] A[DONT_GENERATE, DONT_INLINE]
      0x02d0: PHI (r9v15 boolean) = (r9v14 boolean), (r9v17 boolean), (r9v17 boolean), (r9v17 boolean) binds: [B:115:0x0291, B:123:0x02b8, B:124:0x02ba, B:126:0x02c0] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:162:0x018d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:72:0x0186  */
    /* JADX WARN: Code duplicated, block: B:74:0x018f  */
    /* JADX WARN: Code duplicated, block: B:76:0x0197  */
    /* JADX WARN: Code duplicated, block: B:88:0x01da  */
    /* JADX WARN: Type inference failed for: r8v20 */
    /* JADX WARN: Type inference failed for: r8v21, types: [boolean] */
    /* JADX WARN: Type inference failed for: r8v22 */
    @Override // a.c.a.j.q
    public void K() {
        int i;
        boolean z;
        boolean z2;
        int i2;
        f fVar;
        char c2;
        int i3;
        boolean z3;
        int iMax;
        int iMax2;
        ?? r8;
        boolean zD;
        int i4 = this.I;
        int i5 = this.J;
        int iMax3 = Math.max(0, s());
        int iMax4 = Math.max(0, i());
        this.E0 = false;
        this.F0 = false;
        if (this.D != null) {
            if (this.n0 == null) {
                this.n0 = new p(this);
            }
            this.n0.b(this);
            r(this.o0);
            s(this.p0);
            E();
            a(this.m0.d());
        } else {
            this.I = 0;
            this.J = 0;
        }
        int i6 = 32;
        if (this.C0 != 0) {
            if (!t(8)) {
                S();
            }
            if (!t(32)) {
                R();
            }
            this.m0.g = true;
        } else {
            this.m0.g = false;
        }
        f.b[] bVarArr = this.C;
        f.b bVar = bVarArr[1];
        f.b bVar2 = bVarArr[0];
        V();
        if (this.w0.size() == 0) {
            this.w0.clear();
            this.w0.add(0, new h(this.k0));
        }
        int size = this.w0.size();
        ArrayList<f> arrayList = this.k0;
        boolean z4 = j() == f.b.WRAP_CONTENT || q() == f.b.WRAP_CONTENT;
        boolean z5 = false;
        int i7 = 0;
        while (i7 < size && !this.D0) {
            if (this.w0.get(i7).f151d) {
                i = size;
            } else {
                if (t(i6)) {
                    if (j() == f.b.FIXED && q() == f.b.FIXED) {
                        this.k0 = (ArrayList) this.w0.get(i7).a();
                    } else {
                        this.k0 = (ArrayList) this.w0.get(i7).f148a;
                    }
                }
                V();
                int size2 = this.k0.size();
                for (int i8 = 0; i8 < size2; i8++) {
                    f fVar2 = this.k0.get(i8);
                    if (fVar2 instanceof q) {
                        ((q) fVar2).K();
                    }
                }
                boolean z6 = z5;
                int i9 = 0;
                boolean z7 = true;
                while (z7) {
                    boolean z8 = z7;
                    int i10 = i9 + 1;
                    try {
                        this.m0.f();
                        V();
                        b(this.m0);
                        int i11 = 0;
                        while (i11 < size2) {
                            z = z6;
                            try {
                                this.k0.get(i11).b(this.m0);
                                i11++;
                                z6 = z;
                            } catch (Exception e2) {
                                e = e2;
                                zD = z8;
                                e.printStackTrace();
                                z2 = zD;
                                System.out.println("EXCEPTION : " + e);
                                if (z2) {
                                    a(this.m0, k.f155a);
                                } else {
                                    c(this.m0);
                                    i2 = 0;
                                    while (true) {
                                        if (i2 < size2) {
                                            fVar = this.k0.get(i2);
                                            if (fVar.C[0] != f.b.MATCH_CONSTRAINT) {
                                            }
                                            if (fVar.C[1] != f.b.MATCH_CONSTRAINT) {
                                            }
                                            i2++;
                                        }
                                    }
                                    if (z4) {
                                        i3 = i10;
                                        z6 = z;
                                        z3 = false;
                                    } else {
                                        i3 = i10;
                                        z6 = z;
                                        z3 = false;
                                    }
                                    iMax = Math.max(this.R, s());
                                    if (iMax > s()) {
                                        o(iMax);
                                        this.C[0] = f.b.FIXED;
                                        z3 = true;
                                        z6 = true;
                                    }
                                    iMax2 = Math.max(this.S, i());
                                    if (iMax2 > i()) {
                                        g(iMax2);
                                        r8 = 1;
                                        this.C[1] = f.b.FIXED;
                                        z3 = true;
                                        z6 = true;
                                    } else {
                                        r8 = 1;
                                    }
                                    if (z6) {
                                        z7 = z3;
                                    } else {
                                        if (this.C[0] == f.b.WRAP_CONTENT) {
                                            this.E0 = r8;
                                            this.C[0] = f.b.FIXED;
                                            o(iMax3);
                                            z3 = true;
                                            z6 = true;
                                        }
                                        if (this.C[r8] == f.b.WRAP_CONTENT) {
                                            z7 = z3;
                                        } else {
                                            z7 = z3;
                                        }
                                    }
                                    i9 = i3;
                                    size = size;
                                }
                                c2 = 2;
                                if (z4) {
                                    i3 = i10;
                                    z6 = z;
                                    z3 = false;
                                } else {
                                    i3 = i10;
                                    z6 = z;
                                    z3 = false;
                                }
                                iMax = Math.max(this.R, s());
                                if (iMax > s()) {
                                    o(iMax);
                                    this.C[0] = f.b.FIXED;
                                    z3 = true;
                                    z6 = true;
                                }
                                iMax2 = Math.max(this.S, i());
                                if (iMax2 > i()) {
                                    g(iMax2);
                                    r8 = 1;
                                    this.C[1] = f.b.FIXED;
                                    z3 = true;
                                    z6 = true;
                                } else {
                                    r8 = 1;
                                }
                                if (z6) {
                                    z7 = z3;
                                } else {
                                    if (this.C[0] == f.b.WRAP_CONTENT) {
                                        this.E0 = r8;
                                        this.C[0] = f.b.FIXED;
                                        o(iMax3);
                                        z3 = true;
                                        z6 = true;
                                    }
                                    if (this.C[r8] == f.b.WRAP_CONTENT) {
                                        z7 = z3;
                                    } else {
                                        z7 = z3;
                                    }
                                }
                                i9 = i3;
                                size = size;
                            }
                        }
                        z = z6;
                        zD = d(this.m0);
                        if (zD) {
                            try {
                                this.m0.e();
                            } catch (Exception e3) {
                                e = e3;
                                e.printStackTrace();
                                z2 = zD;
                                System.out.println("EXCEPTION : " + e);
                            }
                        }
                        z2 = zD;
                    } catch (Exception e4) {
                        e = e4;
                        z = z6;
                    }
                    if (z2) {
                        a(this.m0, k.f155a);
                    } else {
                        c(this.m0);
                        i2 = 0;
                        while (true) {
                            if (i2 < size2) {
                                fVar = this.k0.get(i2);
                                if (fVar.C[0] != f.b.MATCH_CONSTRAINT && fVar.s() < fVar.u()) {
                                    k.f155a[2] = true;
                                } else {
                                    if (fVar.C[1] != f.b.MATCH_CONSTRAINT && fVar.i() < fVar.t()) {
                                        c2 = 2;
                                        k.f155a[2] = true;
                                        break;
                                    }
                                    i2++;
                                }
                            }
                        }
                        if (z4 || i10 >= 8 || !k.f155a[c2]) {
                            i3 = i10;
                            z6 = z;
                            z3 = false;
                        } else {
                            int i12 = 0;
                            int iMax5 = 0;
                            int iMax6 = 0;
                            while (i12 < size2) {
                                f fVar3 = this.k0.get(i12);
                                iMax5 = Math.max(iMax5, fVar3.I + fVar3.s());
                                iMax6 = Math.max(iMax6, fVar3.J + fVar3.i());
                                i12++;
                                i10 = i10;
                            }
                            i3 = i10;
                            int iMax7 = Math.max(this.R, iMax5);
                            int iMax8 = Math.max(this.S, iMax6);
                            if (bVar2 != f.b.WRAP_CONTENT || s() >= iMax7) {
                                z3 = false;
                            } else {
                                o(iMax7);
                                this.C[0] = f.b.WRAP_CONTENT;
                                z3 = true;
                                z = true;
                            }
                            if (bVar != f.b.WRAP_CONTENT || i() >= iMax8) {
                                z6 = z;
                            } else {
                                g(iMax8);
                                this.C[1] = f.b.WRAP_CONTENT;
                                z3 = true;
                                z6 = true;
                            }
                        }
                        iMax = Math.max(this.R, s());
                        if (iMax > s()) {
                            o(iMax);
                            this.C[0] = f.b.FIXED;
                            z3 = true;
                            z6 = true;
                        }
                        iMax2 = Math.max(this.S, i());
                        if (iMax2 > i()) {
                            g(iMax2);
                            r8 = 1;
                            this.C[1] = f.b.FIXED;
                            z3 = true;
                            z6 = true;
                        } else {
                            r8 = 1;
                        }
                        if (z6) {
                            z7 = z3;
                        } else {
                            if (this.C[0] == f.b.WRAP_CONTENT && iMax3 > 0 && s() > iMax3) {
                                this.E0 = r8;
                                this.C[0] = f.b.FIXED;
                                o(iMax3);
                                z3 = true;
                                z6 = true;
                            }
                            if (this.C[r8] == f.b.WRAP_CONTENT || iMax4 <= 0 || i() <= iMax4) {
                                z7 = z3;
                            } else {
                                this.F0 = r8;
                                this.C[r8] = f.b.FIXED;
                                g(iMax4);
                                z7 = true;
                                z6 = true;
                            }
                        }
                        i9 = i3;
                        size = size;
                    }
                    c2 = 2;
                    if (z4) {
                        i3 = i10;
                        z6 = z;
                        z3 = false;
                    } else {
                        i3 = i10;
                        z6 = z;
                        z3 = false;
                    }
                    iMax = Math.max(this.R, s());
                    if (iMax > s()) {
                        o(iMax);
                        this.C[0] = f.b.FIXED;
                        z3 = true;
                        z6 = true;
                    }
                    iMax2 = Math.max(this.S, i());
                    if (iMax2 > i()) {
                        g(iMax2);
                        r8 = 1;
                        this.C[1] = f.b.FIXED;
                        z3 = true;
                        z6 = true;
                    } else {
                        r8 = 1;
                    }
                    if (z6) {
                        z7 = z3;
                    } else {
                        if (this.C[0] == f.b.WRAP_CONTENT) {
                            this.E0 = r8;
                            this.C[0] = f.b.FIXED;
                            o(iMax3);
                            z3 = true;
                            z6 = true;
                        }
                        if (this.C[r8] == f.b.WRAP_CONTENT) {
                            z7 = z3;
                        } else {
                            z7 = z3;
                        }
                    }
                    i9 = i3;
                    size = size;
                }
                i = size;
                this.w0.get(i7).b();
                z5 = z6;
            }
            i7++;
            size = i;
            i6 = 32;
        }
        this.k0 = arrayList;
        if (this.D != null) {
            int iMax9 = Math.max(this.R, s());
            int iMax10 = Math.max(this.S, i());
            this.n0.a(this);
            o(iMax9 + this.o0 + this.q0);
            g(iMax10 + this.p0 + this.r0);
        } else {
            this.I = i4;
            this.J = i5;
        }
        if (z5) {
            f.b[] bVarArr2 = this.C;
            bVarArr2[0] = bVar2;
            bVarArr2[1] = bVar;
        }
        a(this.m0.d());
        if (this == J()) {
            H();
        }
    }

    public int M() {
        return this.C0;
    }

    public boolean N() {
        return false;
    }

    public boolean O() {
        return this.F0;
    }

    public boolean P() {
        return this.l0;
    }

    public boolean Q() {
        return this.E0;
    }

    public void R() {
        if (!t(8)) {
            a(this.C0);
        }
        U();
    }

    public void S() {
        int size = this.k0.size();
        F();
        for (int i = 0; i < size; i++) {
            this.k0.get(i).F();
        }
    }

    public void T() {
        S();
        a(this.C0);
    }

    public void U() {
        m mVarD = a(e.d.LEFT).d();
        m mVarD2 = a(e.d.TOP).d();
        mVarD.a((m) null, 0.0f);
        mVarD2.a((m) null, 0.0f);
    }

    public void a(a.c.a.e eVar, boolean[] zArr) {
        zArr[2] = false;
        c(eVar);
        int size = this.k0.size();
        for (int i = 0; i < size; i++) {
            f fVar = this.k0.get(i);
            fVar.c(eVar);
            if (fVar.C[0] == f.b.MATCH_CONSTRAINT && fVar.s() < fVar.u()) {
                zArr[2] = true;
            }
            if (fVar.C[1] == f.b.MATCH_CONSTRAINT && fVar.i() < fVar.t()) {
                zArr[2] = true;
            }
        }
    }

    public void c(boolean z) {
        this.l0 = z;
    }

    public boolean d(a.c.a.e eVar) {
        a(eVar);
        int size = this.k0.size();
        for (int i = 0; i < size; i++) {
            f fVar = this.k0.get(i);
            if (fVar instanceof g) {
                f.b[] bVarArr = fVar.C;
                f.b bVar = bVarArr[0];
                f.b bVar2 = bVarArr[1];
                if (bVar == f.b.WRAP_CONTENT) {
                    fVar.a(f.b.FIXED);
                }
                if (bVar2 == f.b.WRAP_CONTENT) {
                    fVar.b(f.b.FIXED);
                }
                fVar.a(eVar);
                if (bVar == f.b.WRAP_CONTENT) {
                    fVar.a(bVar);
                }
                if (bVar2 == f.b.WRAP_CONTENT) {
                    fVar.b(bVar2);
                }
            } else {
                k.a(this, eVar, fVar);
                fVar.a(eVar);
            }
        }
        if (this.s0 > 0) {
            c.a(this, eVar, 0);
        }
        if (this.t0 > 0) {
            c.a(this, eVar, 1);
        }
        return true;
    }

    public void f(int i, int i2) {
        n nVar;
        n nVar2;
        if (this.C[0] != f.b.WRAP_CONTENT && (nVar2 = this.f137c) != null) {
            nVar2.a(i);
        }
        if (this.C[1] == f.b.WRAP_CONTENT || (nVar = this.f138d) == null) {
            return;
        }
        nVar.a(i2);
    }

    public boolean t(int i) {
        return (this.C0 & i) == i;
    }

    public void u(int i) {
        this.C0 = i;
    }

    @Override // a.c.a.j.f
    public void a(int i) {
        super.a(i);
        int size = this.k0.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.k0.get(i2).a(i);
        }
    }

    void a(f fVar, int i) {
        if (i == 0) {
            d(fVar);
        } else if (i == 1) {
            e(fVar);
        }
    }

    private void d(f fVar) {
        int i = this.s0 + 1;
        d[] dVarArr = this.v0;
        if (i >= dVarArr.length) {
            this.v0 = (d[]) Arrays.copyOf(dVarArr, dVarArr.length * 2);
        }
        this.v0[this.s0] = new d(fVar, 0, P());
        this.s0++;
    }
}
