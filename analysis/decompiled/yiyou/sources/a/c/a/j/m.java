package a.c.a.j;

import com.baidu.mobstat.Config;

/* JADX INFO: compiled from: ResolutionAnchor.java */
/* JADX INFO: loaded from: classes.dex */
public class m extends o {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    e f156c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    m f157d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    float f158e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    m f159f;
    float g;
    private m i;
    int h = 0;
    private n j = null;
    private int k = 1;
    private n l = null;
    private int m = 1;

    public m(e eVar) {
        this.f156c = eVar;
    }

    String a(int i) {
        if (i == 1) {
            return "DIRECT";
        }
        if (i == 2) {
            return "CENTER";
        }
        if (i == 3) {
            return "MATCH";
        }
        if (i == 4) {
            return "CHAIN";
        }
        return i == 5 ? "BARRIER" : "UNCONNECTED";
    }

    public void a(m mVar, float f2) {
        if (this.f162b == 0 || !(this.f159f == mVar || this.g == f2)) {
            this.f159f = mVar;
            this.g = f2;
            if (this.f162b == 1) {
                b();
            }
            a();
        }
    }

    public void b(int i) {
        this.h = i;
    }

    @Override // a.c.a.j.o
    public void d() {
        super.d();
        this.f157d = null;
        this.f158e = 0.0f;
        this.j = null;
        this.k = 1;
        this.l = null;
        this.m = 1;
        this.f159f = null;
        this.g = 0.0f;
        this.i = null;
        this.h = 0;
    }

    @Override // a.c.a.j.o
    public void e() {
        m mVar;
        m mVar2;
        m mVar3;
        m mVar4;
        m mVar5;
        m mVar6;
        float f2;
        float f3;
        float fS;
        float f4;
        m mVar7;
        boolean z = true;
        if (this.f162b == 1 || this.h == 4) {
            return;
        }
        n nVar = this.j;
        if (nVar != null) {
            if (nVar.f162b != 1) {
                return;
            } else {
                this.f158e = this.k * nVar.f160c;
            }
        }
        n nVar2 = this.l;
        if (nVar2 != null) {
            if (nVar2.f162b != 1) {
                return;
            } else {
                float f5 = nVar2.f160c;
            }
        }
        if (this.h == 1 && ((mVar7 = this.f157d) == null || mVar7.f162b == 1)) {
            m mVar8 = this.f157d;
            if (mVar8 == null) {
                this.f159f = this;
                this.g = this.f158e;
            } else {
                this.f159f = mVar8.f159f;
                this.g = mVar8.g + this.f158e;
            }
            a();
            return;
        }
        if (this.h != 2 || (mVar4 = this.f157d) == null || mVar4.f162b != 1 || (mVar5 = this.i) == null || (mVar6 = mVar5.f157d) == null || mVar6.f162b != 1) {
            if (this.h != 3 || (mVar = this.f157d) == null || mVar.f162b != 1 || (mVar2 = this.i) == null || (mVar3 = mVar2.f157d) == null || mVar3.f162b != 1) {
                if (this.h == 5) {
                    this.f156c.f116b.G();
                    return;
                }
                return;
            }
            if (a.c.a.e.h() != null) {
                a.c.a.e.h().w++;
            }
            m mVar9 = this.f157d;
            this.f159f = mVar9.f159f;
            m mVar10 = this.i;
            m mVar11 = mVar10.f157d;
            mVar10.f159f = mVar11.f159f;
            this.g = mVar9.g + this.f158e;
            mVar10.g = mVar11.g + mVar10.f158e;
            a();
            this.i.a();
            return;
        }
        if (a.c.a.e.h() != null) {
            a.c.a.e.h().v++;
        }
        this.f159f = this.f157d.f159f;
        m mVar12 = this.i;
        mVar12.f159f = mVar12.f157d.f159f;
        e.d dVar = this.f156c.f117c;
        int i = 0;
        if (dVar != e.d.RIGHT && dVar != e.d.BOTTOM) {
            z = false;
        }
        if (z) {
            f2 = this.f157d.g;
            f3 = this.i.f157d.g;
        } else {
            f2 = this.i.f157d.g;
            f3 = this.f157d.g;
        }
        float f6 = f2 - f3;
        e eVar = this.f156c;
        e.d dVar2 = eVar.f117c;
        if (dVar2 == e.d.LEFT || dVar2 == e.d.RIGHT) {
            fS = f6 - this.f156c.f116b.s();
            f4 = this.f156c.f116b.V;
        } else {
            fS = f6 - eVar.f116b.i();
            f4 = this.f156c.f116b.W;
        }
        int iB = this.f156c.b();
        int iB2 = this.i.f156c.b();
        if (this.f156c.g() == this.i.f156c.g()) {
            f4 = 0.5f;
            iB2 = 0;
        } else {
            i = iB;
        }
        float f7 = i;
        float f8 = iB2;
        float f9 = (fS - f7) - f8;
        if (z) {
            m mVar13 = this.i;
            mVar13.g = mVar13.f157d.g + f8 + (f9 * f4);
            this.g = (this.f157d.g - f7) - (f9 * (1.0f - f4));
        } else {
            this.g = this.f157d.g + f7 + (f9 * f4);
            m mVar14 = this.i;
            mVar14.g = (mVar14.f157d.g - f8) - (f9 * (1.0f - f4));
        }
        a();
        this.i.a();
    }

    public float f() {
        return this.g;
    }

    public void g() {
        e eVarG = this.f156c.g();
        if (eVarG == null) {
            return;
        }
        if (eVarG.g() == this.f156c) {
            this.h = 4;
            eVarG.d().h = 4;
        }
        int iB = this.f156c.b();
        e.d dVar = this.f156c.f117c;
        if (dVar == e.d.RIGHT || dVar == e.d.BOTTOM) {
            iB = -iB;
        }
        a(eVarG.d(), iB);
    }

    public String toString() {
        if (this.f162b != 1) {
            return "{ " + this.f156c + " UNRESOLVED} type: " + a(this.h);
        }
        if (this.f159f == this) {
            return "[" + this.f156c + ", RESOLVED: " + this.g + "]  type: " + a(this.h);
        }
        return "[" + this.f156c + ", RESOLVED: " + this.f159f + Config.TRACE_TODAY_VISIT_SPLIT + this.g + "] type: " + a(this.h);
    }

    public void b(m mVar, float f2) {
        this.i = mVar;
    }

    public void b(m mVar, int i, n nVar) {
        this.i = mVar;
        this.l = nVar;
        this.m = i;
    }

    public void a(int i, m mVar, int i2) {
        this.h = i;
        this.f157d = mVar;
        this.f158e = i2;
        this.f157d.a(this);
    }

    public void a(m mVar, int i) {
        this.f157d = mVar;
        this.f158e = i;
        this.f157d.a(this);
    }

    public void a(m mVar, int i, n nVar) {
        this.f157d = mVar;
        this.f157d.a(this);
        this.j = nVar;
        this.k = i;
        this.j.a(this);
    }

    void a(a.c.a.e eVar) {
        a.c.a.i iVarE = this.f156c.e();
        m mVar = this.f159f;
        if (mVar == null) {
            eVar.a(iVarE, (int) (this.g + 0.5f));
        } else {
            eVar.a(iVarE, eVar.a(mVar.f156c), (int) (this.g + 0.5f), 6);
        }
    }
}
