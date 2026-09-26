package c.a.b0.j;

import java.util.concurrent.CountDownLatch;

/* JADX INFO: compiled from: BlockingHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {
    public static void a(CountDownLatch countDownLatch, c.a.y.b bVar) {
        if (countDownLatch.getCount() == 0) {
            return;
        }
        try {
            a();
            countDownLatch.await();
        } catch (InterruptedException e2) {
            bVar.dispose();
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting for subscription to complete.", e2);
        }
    }

    public static void a() {
        if (c.a.e0.a.a()) {
            if ((Thread.currentThread() instanceof c.a.b0.g.g) || c.a.e0.a.b()) {
                throw new IllegalStateException("Attempt to block on a Scheduler " + Thread.currentThread().getName() + " that doesn't support blocking operators as they may lead to deadlock");
            }
        }
    }
}
