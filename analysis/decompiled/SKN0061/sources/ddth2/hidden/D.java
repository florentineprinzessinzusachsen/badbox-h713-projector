package ddth2.hidden;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class D implements Runnable {
    public final /* synthetic */ F a;

    public D(F f) {
        this.a = f;
    }

    @Override // java.lang.Runnable
    public final void run() {
        F f = this.a;
        while (f.e.get()) {
            try {
                f.a();
                f.k = 5000;
            } catch (Throwable unused) {
                if (f.e.get()) {
                    Objects.toString(f.c);
                    try {
                        Thread.sleep(f.k);
                    } catch (InterruptedException unused2) {
                        Thread.currentThread().interrupt();
                    }
                    f.k = Math.min(30000, f.k * 2);
                }
            }
        }
    }
}
