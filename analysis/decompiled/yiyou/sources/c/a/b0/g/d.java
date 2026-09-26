package c.a.b0.g;

import c.a.t;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: IoScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class d extends t {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final h f3016d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final h f3017e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final TimeUnit f3018f = TimeUnit.SECONDS;
    static final c g = new c(new h("RxCachedThreadSchedulerShutdown"));
    static final a h;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ThreadFactory f3019b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final AtomicReference<a> f3020c;

    /* JADX INFO: compiled from: IoScheduler.java */
    static final class b extends t.c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final a f3028b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final c f3029c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final AtomicBoolean f3030d = new AtomicBoolean();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c.a.y.a f3027a = new c.a.y.a();

        b(a aVar) {
            this.f3028b = aVar;
            this.f3029c = aVar.b();
        }

        @Override // c.a.t.c
        public c.a.y.b a(Runnable runnable, long j, TimeUnit timeUnit) {
            return this.f3027a.a() ? c.a.b0.a.d.INSTANCE : this.f3029c.a(runnable, j, timeUnit, this.f3027a);
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.f3030d.compareAndSet(false, true)) {
                this.f3027a.dispose();
                this.f3028b.a(this.f3029c);
            }
        }
    }

    /* JADX INFO: compiled from: IoScheduler.java */
    static final class c extends f {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private long f3031c;

        c(ThreadFactory threadFactory) {
            super(threadFactory);
            this.f3031c = 0L;
        }

        public void a(long j) {
            this.f3031c = j;
        }

        public long b() {
            return this.f3031c;
        }
    }

    static {
        g.dispose();
        int iMax = Math.max(1, Math.min(10, Integer.getInteger("rx2.io-priority", 5).intValue()));
        f3016d = new h("RxCachedThreadScheduler", iMax);
        f3017e = new h("RxCachedWorkerPoolEvictor", iMax);
        h = new a(0L, null, f3016d);
        h.d();
    }

    public d() {
        this(f3016d);
    }

    @Override // c.a.t
    public t.c a() {
        return new b(this.f3020c.get());
    }

    public void b() {
        a aVar = new a(60L, f3018f, this.f3019b);
        if (this.f3020c.compareAndSet(h, aVar)) {
            return;
        }
        aVar.d();
    }

    /* JADX INFO: compiled from: IoScheduler.java */
    static final class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final long f3021a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final ConcurrentLinkedQueue<c> f3022b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.y.a f3023c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final ScheduledExecutorService f3024d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Future<?> f3025e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final ThreadFactory f3026f;

        a(long j, TimeUnit timeUnit, ThreadFactory threadFactory) {
            ScheduledFuture<?> scheduledFutureScheduleWithFixedDelay;
            this.f3021a = timeUnit != null ? timeUnit.toNanos(j) : 0L;
            this.f3022b = new ConcurrentLinkedQueue<>();
            this.f3023c = new c.a.y.a();
            this.f3026f = threadFactory;
            ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = null;
            if (timeUnit != null) {
                scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1, d.f3017e);
                long j2 = this.f3021a;
                scheduledFutureScheduleWithFixedDelay = scheduledExecutorServiceNewScheduledThreadPool.scheduleWithFixedDelay(this, j2, j2, TimeUnit.NANOSECONDS);
            } else {
                scheduledFutureScheduleWithFixedDelay = null;
            }
            this.f3024d = scheduledExecutorServiceNewScheduledThreadPool;
            this.f3025e = scheduledFutureScheduleWithFixedDelay;
        }

        void a(c cVar) {
            cVar.a(c() + this.f3021a);
            this.f3022b.offer(cVar);
        }

        c b() {
            if (this.f3023c.a()) {
                return d.g;
            }
            while (!this.f3022b.isEmpty()) {
                c cVarPoll = this.f3022b.poll();
                if (cVarPoll != null) {
                    return cVarPoll;
                }
            }
            c cVar = new c(this.f3026f);
            this.f3023c.c(cVar);
            return cVar;
        }

        long c() {
            return System.nanoTime();
        }

        void d() {
            this.f3023c.dispose();
            Future<?> future = this.f3025e;
            if (future != null) {
                future.cancel(true);
            }
            ScheduledExecutorService scheduledExecutorService = this.f3024d;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdownNow();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            a();
        }

        void a() {
            if (this.f3022b.isEmpty()) {
                return;
            }
            long jC = c();
            for (c cVar : this.f3022b) {
                if (cVar.b() > jC) {
                    return;
                }
                if (this.f3022b.remove(cVar)) {
                    this.f3023c.b(cVar);
                }
            }
        }
    }

    public d(ThreadFactory threadFactory) {
        this.f3019b = threadFactory;
        this.f3020c = new AtomicReference<>(h);
        b();
    }
}
