package r2;

import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class q0 extends p0 implements a0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Executor f2019f;

    public q0(Executor executor) {
        this.f2019f = executor;
        if (executor instanceof ScheduledThreadPoolExecutor) {
            ((ScheduledThreadPoolExecutor) executor).setRemoveOnCancelPolicy(true);
        }
    }

    public static void W(y1.h hVar, RejectedExecutionException rejectedExecutionException) {
        CancellationException cancellationException = new CancellationException("The task was rejected");
        cancellationException.initCause(rejectedExecutionException);
        x.d(hVar, cancellationException);
    }

    @Override // r2.a0
    public final g0 A(long j4, n1 n1Var, y1.h hVar) {
        Executor executor = this.f2019f;
        ScheduledFuture<?> scheduledFutureSchedule = null;
        ScheduledExecutorService scheduledExecutorService = executor instanceof ScheduledExecutorService ? (ScheduledExecutorService) executor : null;
        if (scheduledExecutorService != null) {
            try {
                scheduledFutureSchedule = scheduledExecutorService.schedule(n1Var, j4, TimeUnit.MILLISECONDS);
            } catch (RejectedExecutionException e4) {
                W(hVar, e4);
            }
        }
        return scheduledFutureSchedule != null ? new f0(scheduledFutureSchedule) : y.f2050m.A(j4, n1Var, hVar);
    }

    @Override // r2.a0
    public final void J(long j4, i iVar) {
        Executor executor = this.f2019f;
        ScheduledFuture<?> scheduledFutureSchedule = null;
        ScheduledExecutorService scheduledExecutorService = executor instanceof ScheduledExecutorService ? (ScheduledExecutorService) executor : null;
        if (scheduledExecutorService != null) {
            f0.a aVar = new f0.a(this, iVar, 3, false);
            y1.h hVar = iVar.f1988h;
            try {
                scheduledFutureSchedule = scheduledExecutorService.schedule(aVar, j4, TimeUnit.MILLISECONDS);
            } catch (RejectedExecutionException e4) {
                W(hVar, e4);
            }
        }
        if (scheduledFutureSchedule != null) {
            iVar.y(new f(0, scheduledFutureSchedule));
        } else {
            y.f2050m.J(j4, iVar);
        }
    }

    @Override // r2.s
    public final void S(y1.h hVar, Runnable runnable) {
        try {
            this.f2019f.execute(runnable);
        } catch (RejectedExecutionException e4) {
            CancellationException cancellationException = new CancellationException("The task was rejected");
            cancellationException.initCause(e4);
            x.d(hVar, cancellationException);
            y2.e eVar = e0.f1974a;
            y2.d.f2753f.S(hVar, runnable);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Executor executor = this.f2019f;
        ExecutorService executorService = executor instanceof ExecutorService ? (ExecutorService) executor : null;
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof q0) && ((q0) obj).f2019f == this.f2019f;
    }

    public final int hashCode() {
        return System.identityHashCode(this.f2019f);
    }

    @Override // r2.s
    public final String toString() {
        return this.f2019f.toString();
    }
}
