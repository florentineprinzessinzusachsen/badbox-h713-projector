package c.a.b0.g;

import c.a.t;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: NewThreadWorker.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends t.c implements c.a.y.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ScheduledExecutorService f3034a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    volatile boolean f3035b;

    public f(ThreadFactory threadFactory) {
        this.f3034a = m.a(threadFactory);
    }

    @Override // c.a.t.c
    public c.a.y.b a(Runnable runnable) {
        return a(runnable, 0L, null);
    }

    public c.a.y.b b(Runnable runnable, long j, TimeUnit timeUnit) {
        j jVar = new j(c.a.e0.a.a(runnable));
        try {
            jVar.a(j <= 0 ? this.f3034a.submit(jVar) : this.f3034a.schedule(jVar, j, timeUnit));
            return jVar;
        } catch (RejectedExecutionException e2) {
            c.a.e0.a.b(e2);
            return c.a.b0.a.d.INSTANCE;
        }
    }

    @Override // c.a.y.b
    public void dispose() {
        if (this.f3035b) {
            return;
        }
        this.f3035b = true;
        this.f3034a.shutdownNow();
    }

    @Override // c.a.t.c
    public c.a.y.b a(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.f3035b ? c.a.b0.a.d.INSTANCE : a(runnable, j, timeUnit, (c.a.b0.a.b) null);
    }

    public k a(Runnable runnable, long j, TimeUnit timeUnit, c.a.b0.a.b bVar) {
        Future<?> futureSchedule;
        k kVar = new k(c.a.e0.a.a(runnable), bVar);
        if (bVar != null && !bVar.c(kVar)) {
            return kVar;
        }
        try {
            if (j <= 0) {
                futureSchedule = this.f3034a.submit((Callable) kVar);
            } else {
                futureSchedule = this.f3034a.schedule((Callable) kVar, j, timeUnit);
            }
            kVar.a(futureSchedule);
        } catch (RejectedExecutionException e2) {
            if (bVar != null) {
                bVar.b(kVar);
            }
            c.a.e0.a.b(e2);
        }
        return kVar;
    }

    public c.a.y.b b(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        Future<?> futureSchedule;
        Runnable runnableA = c.a.e0.a.a(runnable);
        if (j2 <= 0) {
            c cVar = new c(runnableA, this.f3034a);
            try {
                if (j <= 0) {
                    futureSchedule = this.f3034a.submit(cVar);
                } else {
                    futureSchedule = this.f3034a.schedule(cVar, j, timeUnit);
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
            iVar.a(this.f3034a.scheduleAtFixedRate(iVar, j, j2, timeUnit));
            return iVar;
        } catch (RejectedExecutionException e3) {
            c.a.e0.a.b(e3);
            return c.a.b0.a.d.INSTANCE;
        }
    }

    public void a() {
        if (this.f3035b) {
            return;
        }
        this.f3035b = true;
        this.f3034a.shutdown();
    }
}
