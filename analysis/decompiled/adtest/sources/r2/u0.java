package r2;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class u0 extends y0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f2031i = AtomicIntegerFieldUpdater.newUpdater(u0.class, "_invoked$volatile");
    private volatile /* synthetic */ int _invoked$volatile = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final p.g f2032h;

    public u0(p.g gVar) {
        this.f2032h = gVar;
    }

    @Override // r2.y0
    public final boolean k() {
        return true;
    }

    @Override // r2.y0
    public final void l(Throwable th) {
        if (f2031i.compareAndSet(this, 0, 1)) {
            this.f2032h.h(th);
        }
    }
}
