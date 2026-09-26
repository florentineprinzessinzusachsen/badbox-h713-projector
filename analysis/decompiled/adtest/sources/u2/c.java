package u2;

import d0.l0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c extends v2.d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final a2.i f2312g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final a2.i f2313h;

    /* JADX WARN: Multi-variable type inference failed */
    public c(i2.p pVar, y1.h hVar, int i4, t2.a aVar) {
        super(hVar, i4, aVar);
        a2.i iVar = (a2.i) pVar;
        this.f2312g = iVar;
        this.f2313h = iVar;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r6v3, types: [a2.i, i2.p] */
    @Override // v2.d
    public final Object c(t2.s sVar, y1.c cVar) {
        b bVar;
        if (cVar instanceof b) {
            bVar = (b) cVar;
            int i4 = bVar.f2311j;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                bVar.f2311j = i4 - Integer.MIN_VALUE;
            } else {
                bVar = new b(this, (a2.c) cVar);
            }
        } else {
            bVar = new b(this, (a2.c) cVar);
        }
        Object obj = bVar.f2309h;
        int i5 = bVar.f2311j;
        u1.k kVar = u1.k.f2301a;
        if (i5 == 0) {
            l0.M(obj);
            bVar.f2308g = sVar;
            bVar.f2311j = 1;
            Object objF = this.f2312g.f(sVar, bVar);
            z1.a aVar = z1.a.f2781d;
            if (objF != aVar) {
                objF = kVar;
            }
            if (objF == aVar) {
                return aVar;
            }
        } else {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            sVar = bVar.f2308g;
            l0.M(obj);
        }
        if (((t2.r) sVar).f2229g.u()) {
            return kVar;
        }
        throw new IllegalStateException("'awaitClose { yourCallbackOrListener.cancel() }' should be used in the end of callbackFlow block.\nOtherwise, a callback/listener may leak in case of external cancellation.\nSee callbackFlow API documentation for the details.");
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [a2.i, i2.p] */
    @Override // v2.d
    public final v2.d d(y1.h hVar, int i4, t2.a aVar) {
        return new c(this.f2313h, hVar, i4, aVar);
    }

    @Override // v2.d
    public final String toString() {
        return "block[" + this.f2312g + "] -> " + super.toString();
    }
}
