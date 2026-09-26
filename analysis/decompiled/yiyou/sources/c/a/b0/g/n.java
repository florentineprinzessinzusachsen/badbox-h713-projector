package c.a.b0.g;

import c.a.t;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: SingleScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class n extends t {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final h f3050c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final ScheduledExecutorService f3051d = Executors.newScheduledThreadPool(0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final AtomicReference<ScheduledExecutorService> f3052b;

    /* JADX INFO: compiled from: SingleScheduler.java */
    static final class a extends t.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ScheduledExecutorService f3053a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.y.a f3054b = new c.a.y.a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        volatile boolean f3055c;

        a(ScheduledExecutorService scheduledExecutorService) {
            this.f3053a = scheduledExecutorService;
        }

        @Override // c.a.t.c
        public c.a.y.b a(Runnable runnable, long j, TimeUnit timeUnit) {
            if (this.f3055c) {
                return c.a.b0.a.d.INSTANCE;
            }
            k kVar = new k(c.a.e0.a.a(runnable), this.f3054b);
            this.f3054b.c(kVar);
            try {
                kVar.a(j <= 0 ? this.f3053a.submit((Callable) kVar) : this.f3053a.schedule((Callable) kVar, j, timeUnit));
                return kVar;
            } catch (RejectedExecutionException e2) {
                dispose();
                c.a.e0.a.b(e2);
                return c.a.b0.a.d.INSTANCE;
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.f3055c) {
                return;
            }
            this.f3055c = true;
            this.f3054b.dispose();
        }
    }

    static {
        f3051d.shutdown();
        f3050c = new h("RxSingleScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.single-priority", 5).intValue())), true);
    }

    public n() {
        this(f3050c);
    }

    static ScheduledExecutorService a(ThreadFactory threadFactory) {
        return m.a(threadFactory);
    }

    public n(ThreadFactory threadFactory) {
        this.f3052b = new AtomicReference<>();
        this.f3052b.lazySet(a(threadFactory));
    }

    @Override // c.a.t
    public t.c a() {
        return new a(this.f3052b.get());
    }

    @Override // c.a.t
    public c.a.y.b a(Runnable runnable, long j, TimeUnit timeUnit) {
        Future<?> futureSchedule;
        j jVar = new j(c.a.e0.a.a(runnable));
        try {
            if (j <= 0) {
                futureSchedule = this.f3052b.get().submit(jVar);
            } else {
                futureSchedule = this.f3052b.get().schedule(jVar, j, timeUnit);
            }
            jVar.a(futureSchedule);
            return jVar;
        } catch (RejectedExecutionException e2) {
            c.a.e0.a.b(e2);
            return c.a.b0.a.d.INSTANCE;
        }
    }

    @Override // c.a.t
    public c.a.y.b a(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        Future<?> futureSchedule;
        Runnable runnableA = c.a.e0.a.a(runnable);
        if (j2 <= 0) {
            ScheduledExecutorService scheduledExecutorService = this.f3052b.get();
            c cVar = new c(runnableA, scheduledExecutorService);
            try {
                if (j <= 0) {
                    futureSchedule = scheduledExecutorService.submit(cVar);
                } else {
                    futureSchedule = scheduledExecutorService.schedule(cVar, j, timeUnit);
                }
                cVar.a(futureSchedule);
                return cVar;
            } catch (RejectedExecutionException e2) {
                c.a.e0.a.b(e2);
                return c.a.b0.a.d.INSTANCE;
            }
        }
        i iVar = new i(runnableA);
        try {
            iVar.a(this.f3052b.get().scheduleAtFixedRate(iVar, j, j2, timeUnit));
            return iVar;
        } catch (RejectedExecutionException e3) {
            c.a.e0.a.b(e3);
            return c.a.b0.a.d.INSTANCE;
        }
    }
}
