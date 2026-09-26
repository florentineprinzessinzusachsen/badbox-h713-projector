package h0;

import d0.l0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class o implements u2.g {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1027d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f1028e;

    public /* synthetic */ o(int i4, Object obj) {
        this.f1027d = i4;
        this.f1028e = obj;
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0018  */
    @Override // u2.g
    public final Object b(u2.h hVar, y1.c cVar) {
        u2.a aVar;
        Throwable th;
        v2.o oVar;
        switch (this.f1027d) {
            case 0:
                u2.g[] gVarArr = (u2.g[]) this.f1028e;
                v2.i iVar = new v2.i(gVarArr, new m(gVarArr), new n(3, null), hVar, null);
                v2.k kVar = new v2.k(cVar, cVar.g());
                Object objB = z1.d.b(kVar, kVar, iVar);
                u1.k kVar2 = u1.k.f2301a;
                z1.a aVar2 = z1.a.f2781d;
                if (objB != aVar2) {
                    objB = kVar2;
                }
                return objB == aVar2 ? objB : kVar2;
            case 1:
                Object objB2 = ((u2.m) this.f1028e).b(new o0.e(0, hVar), cVar);
                return objB2 == z1.a.f2781d ? objB2 : u1.k.f2301a;
            default:
                if (cVar instanceof u2.a) {
                    aVar = (u2.a) cVar;
                    int i4 = aVar.f2307j;
                    if ((i4 & Integer.MIN_VALUE) != 0) {
                        aVar.f2307j = i4 - Integer.MIN_VALUE;
                    } else {
                        aVar = new u2.a(this, cVar);
                    }
                } else {
                    aVar = new u2.a(this, cVar);
                }
                Object obj = aVar.f2305h;
                int i5 = aVar.f2307j;
                if (i5 == 0) {
                    l0.M(obj);
                    y1.h hVar2 = aVar.f42e;
                    j2.i.b(hVar2);
                    v2.o oVar2 = new v2.o(hVar, hVar2);
                    try {
                        aVar.f2304g = oVar2;
                        aVar.f2307j = 1;
                        ((m0.n) this.f1028e).f(oVar2, aVar);
                        return z1.a.f2781d;
                    } catch (Throwable th2) {
                        th = th2;
                        oVar = oVar2;
                    }
                } else {
                    if (i5 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    oVar = aVar.f2304g;
                    try {
                        l0.M(obj);
                        oVar.p();
                        return u1.k.f2301a;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
                oVar.p();
                throw th;
        }
    }
}
