package v;

import d0.l0;
import i2.l;
import i2.p;
import p.t;
import r2.v;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d extends a2.i implements p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2381h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ t f2382i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ boolean f2383j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ l f2384k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(l lVar, t tVar, y1.c cVar, boolean z3) {
        super(2, cVar);
        this.f2382i = tVar;
        this.f2383j = z3;
        this.f2384k = lVar;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        return ((d) i((v) obj, (y1.c) obj2)).l(u1.k.f2301a);
    }

    @Override // a2.a
    public final y1.c i(Object obj, y1.c cVar) {
        return new d(this.f2384k, this.f2382i, cVar, this.f2383j);
    }

    @Override // a2.a
    public final Object l(Object obj) {
        int i4 = this.f2381h;
        if (i4 != 0) {
            if (i4 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            l0.M(obj);
            return obj;
        }
        l0.M(obj);
        l lVar = this.f2384k;
        t tVar = this.f2382i;
        boolean z3 = this.f2383j;
        f fVar = new f(lVar, tVar, null, z3);
        this.f2381h = 1;
        Object objQ = tVar.q(z3, fVar, this);
        z1.a aVar = z1.a.f2781d;
        return objQ == aVar ? aVar : objQ;
    }
}
