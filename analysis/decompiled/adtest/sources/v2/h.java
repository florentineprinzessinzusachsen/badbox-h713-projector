package v2;

import d0.l0;
import java.util.concurrent.atomic.AtomicInteger;
import r2.v;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class h extends a2.i implements i2.p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2538h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ u2.g[] f2539i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ int f2540j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ AtomicInteger f2541k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ t2.e f2542l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(u2.g[] gVarArr, int i4, AtomicInteger atomicInteger, t2.e eVar, y1.c cVar) {
        super(2, cVar);
        this.f2539i = gVarArr;
        this.f2540j = i4;
        this.f2541k = atomicInteger;
        this.f2542l = eVar;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        return ((h) i((v) obj, (y1.c) obj2)).l(u1.k.f2301a);
    }

    @Override // a2.a
    public final y1.c i(Object obj, y1.c cVar) {
        return new h(this.f2539i, this.f2540j, this.f2541k, this.f2542l, cVar);
    }

    @Override // a2.a
    public final Object l(Object obj) {
        int i4 = this.f2538h;
        AtomicInteger atomicInteger = this.f2541k;
        t2.e eVar = this.f2542l;
        try {
            if (i4 == 0) {
                l0.M(obj);
                u2.g[] gVarArr = this.f2539i;
                int i5 = this.f2540j;
                u2.g gVar = gVarArr[i5];
                g gVar2 = new g(eVar, i5);
                this.f2538h = 1;
                Object objB = gVar.b(gVar2, this);
                z1.a aVar = z1.a.f2781d;
                if (objB == aVar) {
                    return aVar;
                }
            } else {
                if (i4 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                l0.M(obj);
            }
            if (atomicInteger.decrementAndGet() == 0) {
                eVar.j(null);
            }
            return u1.k.f2301a;
        } catch (Throwable th) {
            if (atomicInteger.decrementAndGet() == 0) {
                eVar.j(null);
            }
            throw th;
        }
    }
}
