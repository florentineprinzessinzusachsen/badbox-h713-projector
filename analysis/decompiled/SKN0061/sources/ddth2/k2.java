package ddth2;

import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class k2 {
    public static final Object a = new Object();

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public static volatile ThreadPoolExecutor f84a;

    public static class a implements ThreadFactory {
        public final AtomicInteger a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "vps-sdk-" + this.a.getAndIncrement());
            thread.setDaemon(true);
            return thread;
        }
    }

    public static Future<?> a(Runnable runnable) {
        ThreadPoolExecutor threadPoolExecutor = f84a;
        if (threadPoolExecutor != null) {
            return threadPoolExecutor.submit(runnable);
        }
        throw new IllegalStateException("VpsSdkThreadPool not initialized");
    }

    public static RejectedExecutionHandler a(String str) {
        if (str == null) {
            return new ThreadPoolExecutor.CallerRunsPolicy();
        }
        String upperCase = str.trim().toUpperCase();
        if ("ABORT".equals(upperCase)) {
            return new ThreadPoolExecutor.AbortPolicy();
        }
        if ("DISCARD".equals(upperCase)) {
            return new ThreadPoolExecutor.DiscardPolicy();
        }
        return "DISCARD_OLDEST".equals(upperCase) ? new ThreadPoolExecutor.DiscardOldestPolicy() : new ThreadPoolExecutor.CallerRunsPolicy();
    }

    public static ThreadPoolExecutor a() {
        return f84a;
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public static void m60a() {
        synchronized (a) {
            if (f84a != null) {
                f84a.shutdownNow();
                f84a = null;
            }
        }
    }

    public static void a(e eVar) {
        synchronized (a) {
            if (f84a != null) {
                return;
            }
            int iMax = Math.max(1, eVar.k());
            f84a = new ThreadPoolExecutor(iMax, Math.max(iMax, eVar.m()), Math.max(1L, eVar.l()), TimeUnit.SECONDS, new SynchronousQueue(), new a(), a(eVar.m43e()));
            j2.a(f84a);
            j2.a(eVar);
        }
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public static void m61a(Runnable runnable) {
        ThreadPoolExecutor threadPoolExecutor = f84a;
        if (threadPoolExecutor == null) {
            throw new IllegalStateException("VpsSdkThreadPool not initialized");
        }
        threadPoolExecutor.execute(runnable);
    }
}
