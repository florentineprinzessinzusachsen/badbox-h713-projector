package c.a.b0.g;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: ScheduledDirectTask.java */
/* JADX INFO: loaded from: classes.dex */
public final class j extends a implements Callable<Void> {
    public j(Runnable runnable) {
        super(runnable);
    }

    @Override // java.util.concurrent.Callable
    public Void call() {
        this.f2996b = Thread.currentThread();
        try {
            this.f2995a.run();
            return null;
        } finally {
            lazySet(a.f2993c);
            this.f2996b = null;
        }
    }
}
