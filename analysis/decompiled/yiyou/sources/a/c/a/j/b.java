package a.c.a.j;

import java.util.ArrayList;

/* JADX INFO: compiled from: Barrier.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends j {
    private int m0 = 0;
    private ArrayList<m> n0 = new ArrayList<>(4);
    private boolean o0 = true;

    @Override // a.c.a.j.f
    public void F() {
        super.F();
        this.n0.clear();
    }

    @Override // a.c.a.j.f
    public void G() {
        m mVarD;
        float f2;
        m mVar;
        int i = this.m0;
        float f3 = Float.MAX_VALUE;
        if (i != 0) {
            if (i == 1) {
                mVarD = this.u.d();
            } else if (i == 2) {
                mVarD = this.t.d();
            } else if (i != 3) {
                return;
            } else {
                mVarD = this.v.d();
            }
            f3 = 0.0f;
        } else {
            mVarD = this.s.d();
        }
        int size = this.n0.size();
        m mVar2 = null;
        for (int i2 = 0; i2 < size; i2++) {
            m mVar3 = this.n0.get(i2);
            if (mVar3.f162b != 1) {
                return;
            }
            int i3 = this.m0;
            if (i3 == 0 || i3 == 2) {
                f2 = mVar3.g;
                if (f2 < f3) {
                    mVar = mVar3.f159f;
                    mVar2 = mVar;
                    f3 = f2;
                }
            } else {
                f2 = mVar3.g;
                if (f2 > f3) {
                    mVar = mVar3.f159f;
                    mVar2 = mVar;
                    f3 = f2;
                }
            }
        }
        if (a.c.a.e.h() != null) {
            a.c.a.e.h().y++;
        }
        mVarD.f159f = mVar2;
        mVarD.g = f3;
        mVarD.a();
        int i4 = this.m0;
        if (i4 == 0) {
            this.u.d().a(mVar2, f3);
            return;
        }
        if (i4 == 1) {
            this.s.d().a(mVar2, f3);
        } else if (i4 == 2) {
            this.v.d().a(mVar2, f3);
        } else {
            if (i4 != 3) {
                return;
            }
            this.t.d().a(mVar2, f3);
        }
    }

    @Override // a.c.a.j.f
    public void a(int i) {
        m mVarD;
        m mVarD2;
        f fVar = this.D;
        if (fVar != null && ((g) fVar).t(2)) {
            int i2 = this.m0;
            if (i2 == 0) {
                mVarD = this.s.d();
            } else if (i2 == 1) {
                mVarD = this.u.d();
            } else if (i2 == 2) {
                mVarD = this.t.d();
            } else if (i2 != 3) {
                return;
            } else {
                mVarD = this.v.d();
            }
            mVarD.b(5);
            int i3 = this.m0;
            if (i3 == 0 || i3 == 1) {
                this.t.d().a((m) null, 0.0f);
                this.v.d().a((m) null, 0.0f);
            } else {
                this.s.d().a((m) null, 0.0f);
                this.u.d().a((m) null, 0.0f);
            }
            this.n0.clear();
            for (int i4 = 0; i4 < this.l0; i4++) {
                f fVar2 = this.k0[i4];
                if (this.o0 || fVar2.a()) {
                    int i5 = this.m0;
                    if (i5 == 0) {
                        mVarD2 = fVar2.s.d();
                    } else if (i5 == 1) {
                        mVarD2 = fVar2.u.d();
                    } else if (i5 != 2) {
                        mVarD2 = i5 != 3 ? null : fVar2.v.d();
                    } else {
                        mVarD2 = fVar2.t.d();
                    }
                    if (mVarD2 != null) {
                        this.n0.add(mVarD2);
                        mVarD2.a(mVarD);
                    }
                }
            }
        }
    }

    @Override // a.c.a.j.f
    public boolean a() {
        return true;
    }

    public void c(boolean z) {
        this.o0 = z;
    }

    public void t(int i) {
        this.m0 = i;
    }

    @Override // a.c.a.j.f
    public void a(a.c.a.e eVar) {
        e[] eVarArr;
        boolean z;
        int i;
        int i2;
        e[] eVarArr2 = this.A;
        eVarArr2[0] = this.s;
        eVarArr2[2] = this.t;
        eVarArr2[1] = this.u;
        eVarArr2[3] = this.v;
        int i3 = 0;
        while (true) {
            eVarArr = this.A;
            if (i3 >= eVarArr.length) {
                break;
            }
            eVarArr[i3].i = eVar.a(eVarArr[i3]);
            i3++;
        }
        int i4 = this.m0;
        if (i4 < 0 || i4 >= 4) {
            return;
        }
        e eVar2 = eVarArr[i4];
        int i5 = 0;
        while (true) {
            if (i5 >= this.l0) {
                z = false;
                break;
            }
            f fVar = this.k0[i5];
            if ((this.o0 || fVar.a()) && ((((i = this.m0) == 0 || i == 1) && fVar.j() == f.b.MATCH_CONSTRAINT) || (((i2 = this.m0) == 2 || i2 == 3) && fVar.q() == f.b.MATCH_CONSTRAINT))) {
                z = true;
                break;
            }
            i5++;
        }
        int i6 = this.m0;
        if (i6 == 0 || i6 == 1 ? k().j() == f.b.WRAP_CONTENT : k().q() == f.b.WRAP_CONTENT) {
            z = false;
        }
        for (int i7 = 0; i7 < this.l0; i7++) {
            f fVar2 = this.k0[i7];
            if (this.o0 || fVar2.a()) {
                a.c.a.i iVarA = eVar.a(fVar2.A[this.m0]);
                e[] eVarArr3 = fVar2.A;
                int i8 = this.m0;
                eVarArr3[i8].i = iVarA;
                if (i8 != 0 && i8 != 2) {
                    eVar.a(eVar2.i, iVarA, z);
                } else {
                    eVar.b(eVar2.i, iVarA, z);
                }
            }
        }
        int i9 = this.m0;
        if (i9 == 0) {
            eVar.a(this.u.i, this.s.i, 0, 6);
            if (z) {
                return;
            }
            eVar.a(this.s.i, this.D.u.i, 0, 5);
            return;
        }
        if (i9 == 1) {
            eVar.a(this.s.i, this.u.i, 0, 6);
            if (z) {
                return;
            }
            eVar.a(this.s.i, this.D.s.i, 0, 5);
            return;
        }
        if (i9 == 2) {
            eVar.a(this.v.i, this.t.i, 0, 6);
            if (z) {
                return;
            }
            eVar.a(this.t.i, this.D.v.i, 0, 5);
            return;
        }
        if (i9 == 3) {
            eVar.a(this.t.i, this.v.i, 0, 6);
            if (z) {
                return;
            }
            eVar.a(this.t.i, this.D.t.i, 0, 5);
        }
    }
}
