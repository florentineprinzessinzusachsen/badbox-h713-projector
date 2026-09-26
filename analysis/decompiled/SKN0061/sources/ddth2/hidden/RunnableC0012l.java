package ddth2.hidden;

/* JADX INFO: renamed from: ddth2.hidden.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0012l implements Runnable {
    public final /* synthetic */ C0014n a;

    public RunnableC0012l(C0014n c0014n) {
        this.a = c0014n;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C0014n c0014n = this.a;
        while (c0014n.d.get()) {
            try {
                c0014n.a();
                c0014n.i = 5000;
            } catch (Throwable unused) {
                if (c0014n.d.get()) {
                    try {
                        Thread.sleep(c0014n.i);
                    } catch (InterruptedException unused2) {
                        Thread.currentThread().interrupt();
                    }
                    c0014n.i = Math.min(30000, c0014n.i * 2);
                }
            }
        }
    }
}
