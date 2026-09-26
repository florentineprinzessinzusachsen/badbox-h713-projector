package d0;

import androidx.work.CoroutineWorker;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class g extends a2.i implements i2.p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f446h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f447i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ CoroutineWorker f448j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g(CoroutineWorker coroutineWorker, y1.c cVar, int i4) {
        super(2, cVar);
        this.f446h = i4;
        this.f448j = coroutineWorker;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        r2.v vVar = (r2.v) obj;
        y1.c cVar = (y1.c) obj2;
        switch (this.f446h) {
            case 0:
                g gVar = (g) i(vVar, cVar);
                u1.k kVar = u1.k.f2301a;
                gVar.l(kVar);
                return kVar;
            default:
                return ((g) i(vVar, cVar)).l(u1.k.f2301a);
        }
    }

    @Override // a2.a
    public final y1.c i(Object obj, y1.c cVar) {
        switch (this.f446h) {
            case 0:
                return new g(this.f448j, cVar, 0);
            default:
                return new g(this.f448j, cVar, 1);
        }
    }

    @Override // a2.a
    public final Object l(Object obj) {
        switch (this.f446h) {
            case 0:
                int i4 = this.f447i;
                if (i4 == 0) {
                    l0.M(obj);
                    this.f447i = 1;
                    throw new IllegalStateException("Not implemented");
                }
                if (i4 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                l0.M(obj);
                return obj;
            default:
                int i5 = this.f447i;
                if (i5 != 0) {
                    if (i5 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                    return obj;
                }
                l0.M(obj);
                this.f447i = 1;
                Object objC = this.f448j.c(this);
                z1.a aVar = z1.a.f2781d;
                return objC == aVar ? aVar : objC;
        }
    }
}
