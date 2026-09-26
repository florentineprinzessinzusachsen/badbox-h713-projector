package c.a.b0.g;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: AbstractDirectTask.java */
/* JADX INFO: loaded from: classes.dex */
abstract class a extends AtomicReference<Future<?>> implements c.a.y.b, c.a.f0.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected static final FutureTask<Void> f2993c = new FutureTask<>(c.a.b0.b.a.f1758b, null);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected static final FutureTask<Void> f2994d = new FutureTask<>(c.a.b0.b.a.f1758b, null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final Runnable f2995a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected Thread f2996b;

    a(Runnable runnable) {
        this.f2995a = runnable;
    }

    public final void a(Future<?> future) {
        Future<?> future2;
        do {
            future2 = get();
            if (future2 == f2993c) {
                return;
            }
            if (future2 == f2994d) {
                future.cancel(this.f2996b != Thread.currentThread());
                return;
            }
        } while (!compareAndSet(future2, future));
    }

    @Override // c.a.y.b
    public final void dispose() {
        FutureTask<Void> futureTask;
        Future<?> future = get();
        if (future == f2993c || future == (futureTask = f2994d) || !compareAndSet(future, futureTask) || future == null) {
            return;
        }
        future.cancel(this.f2996b != Thread.currentThread());
    }
}
