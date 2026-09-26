package v;

import d0.l0;
import i2.l;
import i2.p;
import p.t;
import r2.v;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c extends a2.i implements p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2376h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ t f2377i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ boolean f2378j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ boolean f2379k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ l f2380l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(l lVar, t tVar, y1.c cVar, boolean z3, boolean z4) {
        super(2, cVar);
        this.f2377i = tVar;
        this.f2378j = z3;
        this.f2379k = z4;
        this.f2380l = lVar;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        return ((c) i((v) obj, (y1.c) obj2)).l(u1.k.f2301a);
    }

    @Override // a2.a
    public final y1.c i(Object obj, y1.c cVar) {
        return new c(this.f2380l, this.f2377i, cVar, this.f2378j, this.f2379k);
    }

    @Override // a2.a
    public final Object l(Object obj) {
        int i4 = this.f2376h;
        if (i4 != 0) {
            if (i4 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            l0.M(obj);
            return obj;
        }
        l0.M(obj);
        l lVar = this.f2380l;
        t tVar = this.f2377i;
        boolean z3 = this.f2379k;
        boolean z4 = this.f2378j;
        b bVar = new b(lVar, tVar, null, z3, z4);
        this.f2376h = 1;
        Object objQ = tVar.q(z4, bVar, this);
        z1.a aVar = z1.a.f2781d;
        return objQ == aVar ? aVar : objQ;
    }
}
