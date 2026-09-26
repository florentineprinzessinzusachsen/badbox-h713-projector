package c.a.y;

/* JADX INFO: compiled from: RunnableDisposable.java */
/* JADX INFO: loaded from: classes.dex */
final class e extends d<Runnable> {
    e(Runnable runnable) {
        super(runnable);
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public String toString() {
        return "RunnableDisposable(disposed=" + a() + ", " + get() + ")";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // c.a.y.d
    public void a(Runnable runnable) {
        runnable.run();
    }
}
