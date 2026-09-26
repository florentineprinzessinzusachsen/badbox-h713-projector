package ddth2.hidden;

/* JADX INFO: renamed from: ddth2.hidden.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0002b implements Runnable {
    final /* synthetic */ Runnable a;

    RunnableC0002b(Runnable runnable) {
        this.a = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.a.run();
        } catch (Throwable unused) {
        }
    }
}
