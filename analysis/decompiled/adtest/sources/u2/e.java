package u2;

import d0.l0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e implements h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2317d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ h f2318e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ j2.n f2319f;

    public e(f fVar, j2.n nVar, h hVar) {
        this.f2319f = nVar;
        this.f2318e = hVar;
    }

    /* JADX WARN: Code duplicated, block: B:36:0x0065  */
    /* JADX WARN: Code duplicated, block: B:9:0x0018  */
    @Override // u2.h
    public final Object c(Object obj, y1.c cVar) throws Throwable {
        d dVar;
        k kVar;
        e eVar;
        switch (this.f2317d) {
            case 0:
                if (cVar instanceof d) {
                    dVar = (d) cVar;
                    int i4 = dVar.f2316i;
                    if ((i4 & Integer.MIN_VALUE) != 0) {
                        dVar.f2316i = i4 - Integer.MIN_VALUE;
                    } else {
                        dVar = new d(this, cVar);
                    }
                } else {
                    dVar = new d(this, cVar);
                }
                Object obj2 = dVar.f2314g;
                int i5 = dVar.f2316i;
                u1.k kVar2 = u1.k.f2301a;
                if (i5 != 0) {
                    if (i5 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj2);
                    return kVar2;
                }
                l0.M(obj2);
                j2.n nVar = this.f2319f;
                Object obj3 = nVar.f1276d;
                if (obj3 != v2.c.f2527b && j2.i.a(obj3, obj)) {
                    return kVar2;
                }
                nVar.f1276d = obj;
                dVar.f2316i = 1;
                Object objC = this.f2318e.c(obj, dVar);
                z1.a aVar = z1.a.f2781d;
                return objC == aVar ? aVar : kVar2;
            default:
                if (cVar instanceof k) {
                    kVar = (k) cVar;
                    int i6 = kVar.f2333j;
                    if ((i6 & Integer.MIN_VALUE) != 0) {
                        kVar.f2333j = i6 - Integer.MIN_VALUE;
                    } else {
                        kVar = new k(this, cVar);
                    }
                } else {
                    kVar = new k(this, cVar);
                }
                Object obj4 = kVar.f2331h;
                int i7 = kVar.f2333j;
                if (i7 != 0) {
                    if (i7 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    eVar = kVar.f2330g;
                    try {
                        l0.M(obj4);
                        return u1.k.f2301a;
                    } catch (Throwable th) {
                        th = th;
                        eVar.f2319f.f1276d = th;
                        throw th;
                    }
                }
                l0.M(obj4);
                try {
                    h hVar = this.f2318e;
                    kVar.f2330g = this;
                    kVar.f2333j = 1;
                    Object objC2 = hVar.c(obj, kVar);
                    z1.a aVar2 = z1.a.f2781d;
                    if (objC2 == aVar2) {
                        return aVar2;
                    }
                    return u1.k.f2301a;
                } catch (Throwable th2) {
                    th = th2;
                    eVar = this;
                    eVar.f2319f.f1276d = th;
                    throw th;
                }
        }
    }

    public e(h hVar, j2.n nVar) {
        this.f2318e = hVar;
        this.f2319f = nVar;
    }
}
