package h0;

import d0.k0;
import d0.l0;
import l0.t;
import l0.u;
import l0.v;
import r2.j1;
import t2.r;
import t2.s;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e implements i2.l {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1001d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f1002e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f1003f;

    public /* synthetic */ e(int i4, Object obj, Object obj2) {
        this.f1001d = i4;
        this.f1002e = obj;
        this.f1003f = obj2;
    }

    @Override // i2.l
    public final Object h(Object obj) {
        int i4 = this.f1001d;
        u1.k kVar = u1.k.f2301a;
        Object obj2 = this.f1003f;
        Object obj3 = this.f1002e;
        switch (i4) {
            case 0:
                ((j1) obj3).b(null);
                ((r) ((s) obj2)).h((c) obj);
                return kVar;
            case 1:
                w.a aVar = (w.a) obj;
                j2.i.e(aVar, "_connection");
                ((l0.d) obj3).f1308b.c(aVar, (l0.a) obj2);
                return kVar;
            case 2:
                w.a aVar2 = (w.a) obj;
                j2.i.e(aVar2, "_connection");
                ((l0.f) obj3).f1312b.c(aVar2, (l0.e) obj2);
                return kVar;
            case 3:
                w.a aVar3 = (w.a) obj;
                j2.i.e(aVar3, "_connection");
                ((l0.j) obj3).f1320b.c(aVar3, (l0.h) obj2);
                return kVar;
            case 4:
                w.a aVar4 = (w.a) obj;
                j2.i.e(aVar4, "_connection");
                ((l0.m) obj3).f1326b.c(aVar4, (l0.l) obj2);
                return kVar;
            case 5:
                k0 k0Var = (k0) obj3;
                String str = (String) obj2;
                w.a aVar5 = (w.a) obj;
                j2.i.e(aVar5, "_connection");
                w.c cVarP = aVar5.P("UPDATE workspec SET state=? WHERE id=?");
                try {
                    cVarP.a(1, l3.h.k0(k0Var));
                    cVarP.o(2, str);
                    cVarP.F();
                    return Integer.valueOf(l0.y(aVar5));
                } finally {
                    cVarP.close();
                }
            case 6:
                d0.j jVar = (d0.j) obj3;
                String str2 = (String) obj2;
                w.a aVar6 = (w.a) obj;
                j2.i.e(aVar6, "_connection");
                w.c cVarP2 = aVar6.P("UPDATE workspec SET output=? WHERE id=?");
                try {
                    d0.j jVar2 = d0.j.f464b;
                    cVarP2.d(1, l3.h.n0(jVar));
                    cVarP2.o(2, str2);
                    cVarP2.F();
                    return kVar;
                } finally {
                    cVarP2.close();
                }
            case 7:
                w.a aVar7 = (w.a) obj;
                j2.i.e(aVar7, "_connection");
                ((t) obj3).f1362b.c(aVar7, (l0.p) obj2);
                return kVar;
            case 8:
                w.a aVar8 = (w.a) obj;
                j2.i.e(aVar8, "_connection");
                ((v) obj3).f1366b.c(aVar8, (u) obj2);
                return kVar;
            default:
                ((s2.d) obj3).f2146f.removeCallbacks((f0.a) obj2);
                return kVar;
        }
    }
}
