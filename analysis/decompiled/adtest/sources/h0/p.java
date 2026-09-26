package h0;

import d0.l0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class p implements u2.h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1029d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f1030e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f1031f;

    public p(i iVar, l0.p pVar) {
        this.f1030e = iVar;
        this.f1031f = pVar;
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0018  */
    /* JADX WARN: Type inference failed for: r1v2, types: [a2.i, i2.p] */
    @Override // u2.h
    public final Object c(Object obj, y1.c cVar) {
        u2.o oVar;
        Object obj2;
        u2.h hVar;
        switch (this.f1029d) {
            case 0:
                ((i) this.f1030e).b((l0.p) this.f1031f, (c) obj);
                return u1.k.f2301a;
            default:
                if (cVar instanceof u2.o) {
                    oVar = (u2.o) cVar;
                    int i4 = oVar.f2349h;
                    if ((i4 & Integer.MIN_VALUE) != 0) {
                        oVar.f2349h = i4 - Integer.MIN_VALUE;
                    } else {
                        oVar = new u2.o(this, cVar);
                    }
                } else {
                    oVar = new u2.o(this, cVar);
                }
                Object obj3 = oVar.f2348g;
                int i5 = oVar.f2349h;
                z1.a aVar = z1.a.f2781d;
                if (i5 != 0) {
                    if (i5 == 1) {
                        hVar = oVar.f2352k;
                        obj2 = oVar.f2351j;
                        l0.M(obj3);
                    } else {
                        if (i5 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        l0.M(obj3);
                    }
                    return u1.k.f2301a;
                }
                l0.M(obj3);
                u2.h hVar2 = (u2.h) this.f1030e;
                ?? r4 = (a2.i) this.f1031f;
                oVar.f2351j = obj;
                oVar.f2352k = hVar2;
                oVar.f2349h = 1;
                if (r4.f(obj, oVar) == aVar) {
                    return aVar;
                }
                obj2 = obj;
                hVar = hVar2;
                oVar.f2351j = null;
                oVar.f2352k = null;
                oVar.f2349h = 2;
                if (hVar.c(obj2, oVar) == aVar) {
                    return aVar;
                }
                return u1.k.f2301a;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public p(u2.h hVar, i2.p pVar) {
        this.f1030e = hVar;
        this.f1031f = (a2.i) pVar;
    }
}
