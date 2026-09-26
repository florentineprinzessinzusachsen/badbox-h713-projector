package e0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class l0 implements i2.l {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ d0.z f657d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ r0.a f658e;

    public l0(d0.z zVar, r0.a aVar) {
        this.f657d = zVar;
        this.f658e = aVar;
    }

    @Override // i2.l
    public final Object h(Object obj) {
        Throwable th = (Throwable) obj;
        if (th instanceof z) {
            this.f657d.f516c.compareAndSet(-256, ((z) th).f702d);
        }
        this.f658e.cancel(false);
        return u1.k.f2301a;
    }
}
