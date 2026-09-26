package w2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class u implements y1.f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f2652d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ThreadLocal f2653e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final v f2654f;

    public u(r.s sVar, ThreadLocal threadLocal) {
        this.f2652d = sVar;
        this.f2653e = threadLocal;
        this.f2654f = new v(threadLocal);
    }

    @Override // y1.h
    public final y1.h C(y1.g gVar) {
        return this.f2654f.equals(gVar) ? y1.i.f2726d : this;
    }

    @Override // y1.h
    public final Object K(Object obj, i2.p pVar) {
        return pVar.f(obj, this);
    }

    public final void a(Object obj) {
        this.f2653e.set(obj);
    }

    public final Object d(y1.h hVar) {
        ThreadLocal threadLocal = this.f2653e;
        Object obj = threadLocal.get();
        threadLocal.set(this.f2652d);
        return obj;
    }

    @Override // y1.f
    public final y1.g getKey() {
        return this.f2654f;
    }

    @Override // y1.h
    public final y1.f k(y1.g gVar) {
        if (this.f2654f.equals(gVar)) {
            return this;
        }
        return null;
    }

    @Override // y1.h
    public final y1.h l(y1.h hVar) {
        return l3.h.Y(this, hVar);
    }

    public final String toString() {
        return "ThreadLocal(value=" + this.f2652d + ", threadLocal = " + this.f2653e + ')';
    }
}
