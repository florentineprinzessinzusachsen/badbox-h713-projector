package c.a.b0.g;

import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: SchedulerPoolFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f3044a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f3045b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final AtomicReference<ScheduledExecutorService> f3046c = new AtomicReference<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final Map<ScheduledThreadPoolExecutor, Object> f3047d = new ConcurrentHashMap();

    /* JADX INFO: compiled from: SchedulerPoolFactory.java */
    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f3048a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f3049b;

        a() {
        }

        void a(Properties properties) {
            if (properties.containsKey("rx2.purge-enabled")) {
                this.f3048a = Boolean.parseBoolean(properties.getProperty("rx2.purge-enabled"));
            } else {
                this.f3048a = true;
            }
            if (!this.f3048a || !properties.containsKey("rx2.purge-period-seconds")) {
                this.f3049b = 1;
                return;
            }
            try {
                this.f3049b = Integer.parseInt(properties.getProperty("rx2.purge-period-seconds"));
            } catch (NumberFormatException unused) {
                this.f3049b = 1;
            }
        }
    }

    /* JADX INFO: compiled from: SchedulerPoolFactory.java */
    static final class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            for (ScheduledThreadPoolExecutor scheduledThreadPoolExecutor : new ArrayList(m.f3047d.keySet())) {
                if (scheduledThreadPoolExecutor.isShutdown()) {
                    m.f3047d.remove(scheduledThreadPoolExecutor);
                } else {
                    scheduledThreadPoolExecutor.purge();
                }
            }
        }
    }

    static {
        Properties properties = System.getProperties();
        a aVar = new a();
        aVar.a(properties);
        f3044a = aVar.f3048a;
        f3045b = aVar.f3049b;
        a();
    }

    public static void a() {
        a(f3044a);
    }

    static void a(boolean z) {
        if (!z) {
            return;
        }
        while (true) {
            ScheduledExecutorService scheduledExecutorService = f3046c.get();
            if (scheduledExecutorService != null) {
                return;
            }
            ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, new h("RxSchedulerPurge"));
            if (f3046c.compareAndSet(scheduledExecutorService, scheduledExecutorServiceNewScheduledThreadPool)) {
                b bVar = new b();
                int i = f3045b;
                scheduledExecutorServiceNewScheduledThreadPool.scheduleAtFixedRate(bVar, i, i, TimeUnit.SECONDS);
                return;
            }
            scheduledExecutorServiceNewScheduledThreadPool.shutdownNow();
        }
    }

    public static ScheduledExecutorService a(ThreadFactory threadFactory) {
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, threadFactory);
        a(f3044a, scheduledExecutorServiceNewScheduledThreadPool);
        return scheduledExecutorServiceNewScheduledThreadPool;
    }

    static void a(boolean z, ScheduledExecutorService scheduledExecutorService) {
        if (z && (scheduledExecutorService instanceof ScheduledThreadPoolExecutor)) {
            f3047d.put((ScheduledThreadPoolExecutor) scheduledExecutorService, scheduledExecutorService);
        }
    }
}
