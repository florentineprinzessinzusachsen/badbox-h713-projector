package ddth2;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public final class j2 {
    public static volatile e a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public static volatile ThreadPoolExecutor f82a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public static final AtomicLong f83a = new AtomicLong(0);
    public static final AtomicLong b = new AtomicLong(0);
    public static final AtomicLong c = new AtomicLong(0);

    public static MetricsSnapshot a(int i, int i2, int i3) {
        e eVar = a;
        ThreadPoolExecutor threadPoolExecutor = f82a;
        return new MetricsSnapshot(threadPoolExecutor != null ? threadPoolExecutor.getPoolSize() : 0, eVar != null ? eVar.m() : 0, threadPoolExecutor != null ? threadPoolExecutor.getActiveCount() : 0, threadPoolExecutor != null ? threadPoolExecutor.getQueue().size() : 0L, i, i2, eVar != null ? eVar.e() : 0, i2 + i3, b.get(), f83a.get(), f2.a(), eVar != null ? eVar.m36c() : 0, d2.m20a(), d2.m24b(), d2.a(), d2.b());
    }

    public static void a() {
        f83a.incrementAndGet();
    }

    public static void a(long j) {
        AtomicLong atomicLong;
        long j2;
        do {
            atomicLong = c;
            j2 = atomicLong.get();
            if (j <= j2) {
                return;
            }
        } while (!atomicLong.compareAndSet(j2, j));
    }

    public static void a(e eVar) {
        a = eVar;
    }

    public static void a(ThreadPoolExecutor threadPoolExecutor) {
        f82a = threadPoolExecutor;
    }

    public static void b(long j) {
        AtomicLong atomicLong;
        long j2;
        do {
            atomicLong = b;
            j2 = atomicLong.get();
            if (j <= j2) {
                return;
            }
        } while (!atomicLong.compareAndSet(j2, j));
    }
}
