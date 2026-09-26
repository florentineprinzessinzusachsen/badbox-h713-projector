package a.c.a;

import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: LinearSystem.java */
/* JADX INFO: loaded from: classes.dex */
public class e {
    private static int p = 1000;
    public static f q;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a f85c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f87e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    b[] f88f;
    public boolean g;
    private boolean[] h;
    int i;
    int j;
    private int k;
    final c l;
    private i[] m;
    private int n;
    private final a o;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f83a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private HashMap<String, i> f84b = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f86d = 32;

    /* JADX INFO: compiled from: LinearSystem.java */
    interface a {
        i a(e eVar, boolean[] zArr);

        void a(a aVar);

        void a(i iVar);

        void clear();

        i getKey();
    }

    public e() {
        int i = this.f86d;
        this.f87e = i;
        this.f88f = null;
        this.g = false;
        this.h = new boolean[i];
        this.i = 1;
        this.j = 0;
        this.k = i;
        this.m = new i[p];
        this.n = 0;
        b[] bVarArr = new b[i];
        this.f88f = new b[i];
        j();
        this.l = new c();
        this.f85c = new d(this.l);
        this.o = new b(this.l);
    }

    private final void d(b bVar) {
        if (this.j > 0) {
            bVar.f78d.a(bVar, this.f88f);
            if (bVar.f78d.f69a == 0) {
                bVar.f79e = true;
            }
        }
    }

    private void g() {
        for (int i = 0; i < this.j; i++) {
            b bVar = this.f88f[i];
            bVar.f75a.f101e = bVar.f76b;
        }
    }

    public static f h() {
        return q;
    }

    private void i() {
        this.f86d *= 2;
        this.f88f = (b[]) Arrays.copyOf(this.f88f, this.f86d);
        c cVar = this.l;
        cVar.f82c = (i[]) Arrays.copyOf(cVar.f82c, this.f86d);
        int i = this.f86d;
        this.h = new boolean[i];
        this.f87e = i;
        this.k = i;
        f fVar = q;
        if (fVar != null) {
            fVar.f92d++;
            fVar.o = Math.max(fVar.o, i);
            f fVar2 = q;
            fVar2.A = fVar2.o;
        }
    }

    private void j() {
        int i = 0;
        while (true) {
            b[] bVarArr = this.f88f;
            if (i >= bVarArr.length) {
                return;
            }
            b bVar = bVarArr[i];
            if (bVar != null) {
                this.l.f80a.a(bVar);
            }
            this.f88f[i] = null;
            i++;
        }
    }

    public i a(Object obj) {
        i iVarE = null;
        if (obj == null) {
            return null;
        }
        if (this.i + 1 >= this.f87e) {
            i();
        }
        if (obj instanceof a.c.a.j.e) {
            a.c.a.j.e eVar = (a.c.a.j.e) obj;
            iVarE = eVar.e();
            if (iVarE == null) {
                eVar.a(this.l);
                iVarE = eVar.e();
            }
            int i = iVarE.f98b;
            if (i == -1 || i > this.f83a || this.l.f82c[i] == null) {
                if (iVarE.f98b != -1) {
                    iVarE.a();
                }
                this.f83a++;
                this.i++;
                int i2 = this.f83a;
                iVarE.f98b = i2;
                iVarE.g = i.a.UNRESTRICTED;
                this.l.f82c[i2] = iVarE;
            }
        }
        return iVarE;
    }

    public b b() {
        b bVarA = this.l.f80a.a();
        if (bVarA == null) {
            bVarA = new b(this.l);
        } else {
            bVarA.d();
        }
        i.b();
        return bVarA;
    }

    public i c() {
        f fVar = q;
        if (fVar != null) {
            fVar.m++;
        }
        if (this.i + 1 >= this.f87e) {
            i();
        }
        i iVarA = a(i.a.SLACK, (String) null);
        this.f83a++;
        this.i++;
        int i = this.f83a;
        iVarA.f98b = i;
        this.l.f82c[i] = iVarA;
        return iVarA;
    }

    public void e() {
        f fVar = q;
        if (fVar != null) {
            fVar.f93e++;
        }
        if (!this.g) {
            a(this.f85c);
            return;
        }
        f fVar2 = q;
        if (fVar2 != null) {
            fVar2.q++;
        }
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= this.j) {
                z = true;
                break;
            } else if (!this.f88f[i].f79e) {
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            a(this.f85c);
            return;
        }
        f fVar3 = q;
        if (fVar3 != null) {
            fVar3.p++;
        }
        g();
    }

    public void f() {
        c cVar;
        int i = 0;
        while (true) {
            cVar = this.l;
            i[] iVarArr = cVar.f82c;
            if (i >= iVarArr.length) {
                break;
            }
            i iVar = iVarArr[i];
            if (iVar != null) {
                iVar.a();
            }
            i++;
        }
        cVar.f81b.a(this.m, this.n);
        this.n = 0;
        Arrays.fill(this.l.f82c, (Object) null);
        HashMap<String, i> map = this.f84b;
        if (map != null) {
            map.clear();
        }
        this.f83a = 0;
        this.f85c.clear();
        this.i = 1;
        for (int i2 = 0; i2 < this.j; i2++) {
            this.f88f[i2].f77c = false;
        }
        j();
        this.j = 0;
    }

    private void b(b bVar) {
        bVar.a(this, 0);
    }

    public c d() {
        return this.l;
    }

    public int b(Object obj) {
        i iVarE = ((a.c.a.j.e) obj).e();
        if (iVarE != null) {
            return (int) (iVarE.f101e + 0.5f);
        }
        return 0;
    }

    private int b(a aVar) {
        float f2;
        boolean z;
        int i = 0;
        while (true) {
            f2 = 0.0f;
            if (i >= this.j) {
                z = false;
                break;
            }
            b[] bVarArr = this.f88f;
            if (bVarArr[i].f75a.g != i.a.UNRESTRICTED && bVarArr[i].f76b < 0.0f) {
                z = true;
                break;
            }
            i++;
        }
        if (!z) {
            return 0;
        }
        boolean z2 = false;
        int i2 = 0;
        while (!z2) {
            f fVar = q;
            if (fVar != null) {
                fVar.k++;
            }
            i2++;
            int i3 = 0;
            int i4 = -1;
            int i5 = -1;
            float f3 = Float.MAX_VALUE;
            int i6 = 0;
            while (i3 < this.j) {
                b bVar = this.f88f[i3];
                if (bVar.f75a.g != i.a.UNRESTRICTED && !bVar.f79e && bVar.f76b < f2) {
                    int i7 = 1;
                    while (i7 < this.i) {
                        i iVar = this.l.f82c[i7];
                        float fB = bVar.f78d.b(iVar);
                        if (fB > f2) {
                            int i8 = i6;
                            float f4 = f3;
                            int i9 = i5;
                            int i10 = i4;
                            for (int i11 = 0; i11 < 7; i11++) {
                                float f5 = iVar.f102f[i11] / fB;
                                if ((f5 < f4 && i11 == i8) || i11 > i8) {
                                    i9 = i7;
                                    i10 = i3;
                                    f4 = f5;
                                    i8 = i11;
                                }
                            }
                            i4 = i10;
                            i5 = i9;
                            f3 = f4;
                            i6 = i8;
                        }
                        i7++;
                        f2 = 0.0f;
                    }
                }
                i3++;
                f2 = 0.0f;
            }
            if (i4 != -1) {
                b bVar2 = this.f88f[i4];
                bVar2.f75a.f99c = -1;
                f fVar2 = q;
                if (fVar2 != null) {
                    fVar2.j++;
                }
                bVar2.d(this.l.f82c[i5]);
                i iVar2 = bVar2.f75a;
                iVar2.f99c = i4;
                iVar2.c(bVar2);
            } else {
                z2 = true;
            }
            if (i2 > this.i / 2) {
                z2 = true;
            }
            f2 = 0.0f;
        }
        return i2;
    }

    private final void c(b bVar) {
        b[] bVarArr = this.f88f;
        int i = this.j;
        if (bVarArr[i] != null) {
            this.l.f80a.a(bVarArr[i]);
        }
        b[] bVarArr2 = this.f88f;
        int i2 = this.j;
        bVarArr2[i2] = bVar;
        i iVar = bVar.f75a;
        iVar.f99c = i2;
        this.j = i2 + 1;
        iVar.c(bVar);
    }

    public i a() {
        f fVar = q;
        if (fVar != null) {
            fVar.n++;
        }
        if (this.i + 1 >= this.f87e) {
            i();
        }
        i iVarA = a(i.a.SLACK, (String) null);
        this.f83a++;
        this.i++;
        int i = this.f83a;
        iVarA.f98b = i;
        this.l.f82c[i] = iVarA;
        return iVarA;
    }

    public void c(i iVar, i iVar2, int i, int i2) {
        b bVarB = b();
        i iVarC = c();
        iVarC.f100d = 0;
        bVarB.b(iVar, iVar2, iVarC, i);
        if (i2 != 6) {
            a(bVarB, (int) (bVarB.f78d.b(iVarC) * (-1.0f)), i2);
        }
        a(bVarB);
    }

    void a(b bVar, int i, int i2) {
        bVar.a(a(i2, (String) null), i);
    }

    public i a(int i, String str) {
        f fVar = q;
        if (fVar != null) {
            fVar.l++;
        }
        if (this.i + 1 >= this.f87e) {
            i();
        }
        i iVarA = a(i.a.ERROR, str);
        this.f83a++;
        this.i++;
        int i2 = this.f83a;
        iVarA.f98b = i2;
        iVarA.f100d = i;
        this.l.f82c[i2] = iVarA;
        this.f85c.a(iVarA);
        return iVarA;
    }

    public void b(i iVar, i iVar2, int i, int i2) {
        b bVarB = b();
        i iVarC = c();
        iVarC.f100d = 0;
        bVarB.a(iVar, iVar2, iVarC, i);
        if (i2 != 6) {
            a(bVarB, (int) (bVarB.f78d.b(iVarC) * (-1.0f)), i2);
        }
        a(bVarB);
    }

    private i a(i.a aVar, String str) {
        i iVarA = this.l.f81b.a();
        if (iVarA == null) {
            iVarA = new i(aVar, str);
            iVarA.a(aVar, str);
        } else {
            iVarA.a();
            iVarA.a(aVar, str);
        }
        int i = this.n;
        int i2 = p;
        if (i >= i2) {
            p = i2 * 2;
            this.m = (i[]) Arrays.copyOf(this.m, p);
        }
        i[] iVarArr = this.m;
        int i3 = this.n;
        this.n = i3 + 1;
        iVarArr[i3] = iVarA;
        return iVarA;
    }

    public void b(i iVar, i iVar2, boolean z) {
        b bVarB = b();
        i iVarC = c();
        iVarC.f100d = 0;
        bVarB.b(iVar, iVar2, iVarC, 0);
        if (z) {
            a(bVarB, (int) (bVarB.f78d.b(iVarC) * (-1.0f)), 1);
        }
        a(bVarB);
    }

    void a(a aVar) {
        f fVar = q;
        if (fVar != null) {
            fVar.s++;
            fVar.t = Math.max(fVar.t, this.i);
            f fVar2 = q;
            fVar2.u = Math.max(fVar2.u, this.j);
        }
        d((b) aVar);
        b(aVar);
        a(aVar, false);
        g();
    }

    public void a(b bVar) {
        i iVarC;
        if (bVar == null) {
            return;
        }
        f fVar = q;
        if (fVar != null) {
            fVar.f94f++;
            if (bVar.f79e) {
                fVar.g++;
            }
        }
        if (this.j + 1 >= this.k || this.i + 1 >= this.f87e) {
            i();
        }
        boolean z = false;
        if (!bVar.f79e) {
            d(bVar);
            if (bVar.c()) {
                return;
            }
            bVar.a();
            if (bVar.a(this)) {
                i iVarA = a();
                bVar.f75a = iVarA;
                c(bVar);
                this.o.a(bVar);
                a(this.o, true);
                if (iVarA.f99c == -1) {
                    if (bVar.f75a == iVarA && (iVarC = bVar.c(iVarA)) != null) {
                        f fVar2 = q;
                        if (fVar2 != null) {
                            fVar2.j++;
                        }
                        bVar.d(iVarC);
                    }
                    if (!bVar.f79e) {
                        bVar.f75a.c(bVar);
                    }
                    this.j--;
                }
                z = true;
            }
            if (!bVar.b()) {
                return;
            }
        }
        if (z) {
            return;
        }
        c(bVar);
    }

    private final int a(a aVar, boolean z) {
        f fVar = q;
        if (fVar != null) {
            fVar.h++;
        }
        for (int i = 0; i < this.i; i++) {
            this.h[i] = false;
        }
        boolean z2 = false;
        int i2 = 0;
        while (!z2) {
            f fVar2 = q;
            if (fVar2 != null) {
                fVar2.i++;
            }
            i2++;
            if (i2 >= this.i * 2) {
                return i2;
            }
            if (aVar.getKey() != null) {
                this.h[aVar.getKey().f98b] = true;
            }
            i iVarA = aVar.a(this, this.h);
            if (iVarA != null) {
                boolean[] zArr = this.h;
                int i3 = iVarA.f98b;
                if (zArr[i3]) {
                    return i2;
                }
                zArr[i3] = true;
            }
            if (iVarA != null) {
                int i4 = -1;
                float f2 = Float.MAX_VALUE;
                for (int i5 = 0; i5 < this.j; i5++) {
                    b bVar = this.f88f[i5];
                    if (bVar.f75a.g != i.a.UNRESTRICTED && !bVar.f79e && bVar.b(iVarA)) {
                        float fB = bVar.f78d.b(iVarA);
                        if (fB < 0.0f) {
                            float f3 = (-bVar.f76b) / fB;
                            if (f3 < f2) {
                                i4 = i5;
                                f2 = f3;
                            }
                        }
                    }
                }
                if (i4 > -1) {
                    b bVar2 = this.f88f[i4];
                    bVar2.f75a.f99c = -1;
                    f fVar3 = q;
                    if (fVar3 != null) {
                        fVar3.j++;
                    }
                    bVar2.d(iVarA);
                    i iVar = bVar2.f75a;
                    iVar.f99c = i4;
                    iVar.c(bVar2);
                }
            }
            z2 = true;
        }
        return i2;
    }

    public void a(i iVar, i iVar2, boolean z) {
        b bVarB = b();
        i iVarC = c();
        iVarC.f100d = 0;
        bVarB.a(iVar, iVar2, iVarC, 0);
        if (z) {
            a(bVarB, (int) (bVarB.f78d.b(iVarC) * (-1.0f)), 1);
        }
        a(bVarB);
    }

    public void a(i iVar, i iVar2, int i, float f2, i iVar3, i iVar4, int i2, int i3) {
        b bVarB = b();
        bVarB.a(iVar, iVar2, i, f2, iVar3, iVar4, i2);
        if (i3 != 6) {
            bVarB.a(this, i3);
        }
        a(bVarB);
    }

    public void a(i iVar, i iVar2, i iVar3, i iVar4, float f2, int i) {
        b bVarB = b();
        bVarB.a(iVar, iVar2, iVar3, iVar4, f2);
        if (i != 6) {
            bVarB.a(this, i);
        }
        a(bVarB);
    }

    public b a(i iVar, i iVar2, int i, int i2) {
        b bVarB = b();
        bVarB.a(iVar, iVar2, i);
        if (i2 != 6) {
            bVarB.a(this, i2);
        }
        a(bVarB);
        return bVarB;
    }

    public void a(i iVar, int i) {
        int i2 = iVar.f99c;
        if (i2 != -1) {
            b bVar = this.f88f[i2];
            if (bVar.f79e) {
                bVar.f76b = i;
                return;
            }
            if (bVar.f78d.f69a == 0) {
                bVar.f79e = true;
                bVar.f76b = i;
                return;
            } else {
                b bVarB = b();
                bVarB.c(iVar, i);
                a(bVarB);
                return;
            }
        }
        b bVarB2 = b();
        bVarB2.b(iVar, i);
        a(bVarB2);
    }

    public static b a(e eVar, i iVar, i iVar2, i iVar3, float f2, boolean z) {
        b bVarB = eVar.b();
        if (z) {
            eVar.b(bVarB);
        }
        bVarB.a(iVar, iVar2, iVar3, f2);
        return bVarB;
    }

    public void a(a.c.a.j.f fVar, a.c.a.j.f fVar2, float f2, int i) {
        i iVarA = a(fVar.a(a.c.a.j.e.d.LEFT));
        i iVarA2 = a(fVar.a(a.c.a.j.e.d.TOP));
        i iVarA3 = a(fVar.a(a.c.a.j.e.d.RIGHT));
        i iVarA4 = a(fVar.a(a.c.a.j.e.d.BOTTOM));
        i iVarA5 = a(fVar2.a(a.c.a.j.e.d.LEFT));
        i iVarA6 = a(fVar2.a(a.c.a.j.e.d.TOP));
        i iVarA7 = a(fVar2.a(a.c.a.j.e.d.RIGHT));
        i iVarA8 = a(fVar2.a(a.c.a.j.e.d.BOTTOM));
        b bVarB = b();
        double d2 = f2;
        double dSin = Math.sin(d2);
        double d3 = i;
        Double.isNaN(d3);
        bVarB.b(iVarA2, iVarA4, iVarA6, iVarA8, (float) (dSin * d3));
        a(bVarB);
        b bVarB2 = b();
        double dCos = Math.cos(d2);
        Double.isNaN(d3);
        bVarB2.b(iVarA, iVarA3, iVarA5, iVarA7, (float) (dCos * d3));
        a(bVarB2);
    }
}
