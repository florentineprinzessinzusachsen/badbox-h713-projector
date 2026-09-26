package c.a.b0.g;

/* JADX INFO: compiled from: ScheduledDirectPeriodicTask.java */
/* JADX INFO: loaded from: classes.dex */
public final class i extends a implements Runnable {
    public i(Runnable runnable) {
        super(runnable);
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f2996b = Thread.currentThread();
        try {
            this.f2995a.run();
            this.f2996b = null;
        } catch (Throwable th) {
            this.f2996b = null;
            lazySet(a.f2993c);
            c.a.e0.a.b(th);
        }
    }
}
