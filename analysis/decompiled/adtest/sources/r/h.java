package r;

import d0.l0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class h implements u2.h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1891d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f1892e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f1893f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final u1.a f1894g;

    public h(u2.h hVar, p.t tVar, d0.h hVar2) {
        this.f1892e = hVar;
        this.f1893f = tVar;
        this.f1894g = hVar2;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x002e  */
    @Override // u2.h
    public final Object c(Object obj, y1.c cVar) {
        g gVar;
        u2.h hVar;
        switch (this.f1891d) {
            case 0:
                if (cVar instanceof g) {
                    gVar = (g) cVar;
                    int i4 = gVar.f1888h;
                    if ((i4 & Integer.MIN_VALUE) != 0) {
                        gVar.f1888h = i4 - Integer.MIN_VALUE;
                    } else {
                        gVar = new g(this, cVar);
                    }
                } else {
                    gVar = new g(this, cVar);
                }
                Object obj2 = gVar.f1887g;
                int i5 = gVar.f1888h;
                z1.a aVar = z1.a.f2781d;
                if (i5 != 0) {
                    if (i5 == 1) {
                        hVar = gVar.f1889i;
                        l0.M(obj2);
                    } else {
                        if (i5 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        l0.M(obj2);
                    }
                    return u1.k.f2301a;
                }
                l0.M(obj2);
                u2.h hVar2 = (u2.h) this.f1892e;
                p.t tVar = (p.t) this.f1893f;
                d0.h hVar3 = (d0.h) this.f1894g;
                gVar.f1889i = hVar2;
                gVar.f1888h = 1;
                Object objX = l3.h.X(tVar, true, hVar3, gVar);
                if (objX == aVar) {
                    return aVar;
                }
                obj2 = objX;
                hVar = hVar2;
                gVar.f1889i = null;
                gVar.f1888h = 2;
                if (hVar.c(obj2, gVar) == aVar) {
                    return aVar;
                }
                return u1.k.f2301a;
            default:
                Object objA = v2.c.a((y1.h) this.f1892e, obj, this.f1893f, (h0.f) this.f1894g, cVar);
                return objA == z1.a.f2781d ? objA : u1.k.f2301a;
        }
    }

    public h(u2.h hVar, y1.h hVar2) {
        this.f1892e = hVar2;
        this.f1893f = w2.a.k(hVar2);
        this.f1894g = new h0.f(hVar, null, 7);
    }
}
