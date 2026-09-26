package g;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class l implements r0.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WeakReference f941d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final k f942e = new k(this);

    public l(i iVar) {
        this.f941d = new WeakReference(iVar);
    }

    @Override // r0.a
    public final void a(Runnable runnable, Executor executor) {
        this.f942e.a(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z3) {
        i iVar = (i) this.f941d.get();
        boolean zCancel = this.f942e.cancel(z3);
        if (zCancel && iVar != null) {
            iVar.f936a = null;
            iVar.f937b = null;
            iVar.f938c.j(null);
        }
        return zCancel;
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        return this.f942e.get();
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.f942e.f933d instanceof a;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.f942e.isDone();
    }

    public final String toString() {
        return this.f942e.toString();
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j4, TimeUnit timeUnit) {
        return this.f942e.get(j4, timeUnit);
    }
}
