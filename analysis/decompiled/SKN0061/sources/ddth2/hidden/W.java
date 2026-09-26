package ddth2.hidden;

/* JADX INFO: loaded from: classes.dex */
public final class W implements Runnable {
    public final /* synthetic */ C0011k a;

    public W(C0011k c0011k) {
        this.a = c0011k;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C0011k c0011k = this.a;
        c0011k.getClass();
        long jNanoTime = System.nanoTime();
        for (V v : c0011k.e.values()) {
            if (!v.h.get() && v.i.get()) {
                int i = v.b.k;
                if (i <= 0) {
                    i = 60000;
                }
                if (jNanoTime - v.s > ((long) i) * 1000000) {
                    v.a(9);
                }
            }
        }
    }
}
