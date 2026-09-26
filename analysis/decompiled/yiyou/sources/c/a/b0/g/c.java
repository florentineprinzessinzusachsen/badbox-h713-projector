package c.a.b0.g;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: InstantPeriodicTask.java */
/* JADX INFO: loaded from: classes.dex */
final class c implements Callable<Void>, c.a.y.b {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static final FutureTask<Void> f3010f = new FutureTask<>(c.a.b0.b.a.f1758b, null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Runnable f3011a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final ExecutorService f3014d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    Thread f3015e;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final AtomicReference<Future<?>> f3013c = new AtomicReference<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final AtomicReference<Future<?>> f3012b = new AtomicReference<>();

    c(Runnable runnable, ExecutorService executorService) {
        this.f3011a = runnable;
        this.f3014d = executorService;
    }

    void a(Future<?> future) {
        Future<?> future2;
        do {
            future2 = this.f3013c.get();
            if (future2 == f3010f) {
                future.cancel(this.f3015e != Thread.currentThread());
                return;
            }
        } while (!this.f3013c.compareAndSet(future2, future));
    }

    void b(Future<?> future) {
        Future<?> future2;
        do {
            future2 = this.f3012b.get();
            if (future2 == f3010f) {
                future.cancel(this.f3015e != Thread.currentThread());
                return;
            }
        } while (!this.f3012b.compareAndSet(future2, future));
    }

    @Override // c.a.y.b
    public void dispose() {
        Future<?> andSet = this.f3013c.getAndSet(f3010f);
        if (andSet != null && andSet != f3010f) {
            andSet.cancel(this.f3015e != Thread.currentThread());
        }
        Future<?> andSet2 = this.f3012b.getAndSet(f3010f);
        if (andSet2 == null || andSet2 == f3010f) {
            return;
        }
        andSet2.cancel(this.f3015e != Thread.currentThread());
    }

    @Override // java.util.concurrent.Callable
    public Void call() {
        this.f3015e = Thread.currentThread();
        try {
            this.f3011a.run();
            b(this.f3014d.submit(this));
            this.f3015e = null;
        } catch (Throwable th) {
            this.f3015e = null;
            c.a.e0.a.b(th);
        }
        return null;
    }
}
