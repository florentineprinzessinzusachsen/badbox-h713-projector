package ddth2.hidden;

/* JADX INFO: renamed from: ddth2.hidden.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0004d implements Runnable {
    RunnableC0004d() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean zB;
        long jCurrentTimeMillis = System.currentTimeMillis();
        while (C0001a.a.get() && C0001a.c.get()) {
            try {
                zB = C0009i.b();
            } catch (Throwable unused) {
                zB = false;
            }
            if (!zB) {
                C0001a.b();
            }
            C0001a.a(System.currentTimeMillis() - jCurrentTimeMillis < 180000 ? 5000L : 30000L);
        }
    }
}
