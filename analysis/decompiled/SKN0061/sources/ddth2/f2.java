package ddth2;

import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class f2 {
    public static volatile int a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public static volatile Semaphore f81a;

    public static int a() {
        Semaphore semaphore = f81a;
        if (semaphore == null || a <= 0) {
            return 0;
        }
        int iAvailablePermits = a - semaphore.availablePermits();
        if (iAvailablePermits < 0) {
            return 0;
        }
        return iAvailablePermits;
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public static void m58a() {
        Semaphore semaphore = f81a;
        if (semaphore != null) {
            semaphore.release();
        }
    }

    public static void a(e eVar) throws IOException {
        Semaphore semaphore = f81a;
        if (semaphore == null) {
            return;
        }
        long jM27a = eVar != null ? eVar.m27a() : 30000L;
        try {
            if (semaphore.tryAcquire(jM27a > 0 ? jM27a : 30000L, TimeUnit.MILLISECONDS)) {
                return;
            }
            throw new IOException("max concurrent HTTP connections (" + a + ")");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("interrupted waiting for HTTP slot", e);
        }
    }

    public static void b(e eVar) {
        if (eVar == null) {
            return;
        }
        a = Math.max(1, eVar.m36c());
        f81a = new Semaphore(a, true);
    }
}
