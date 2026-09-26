package com.szns.sdk;

import android.os.Build;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class n {
    private static final n a = new n();
    private final Object b = new Object();
    private boolean c;
    private ScheduledThreadPoolExecutor d;
    private ThreadPoolExecutor e;

    private n() {
    }

    public static n a() {
        return a;
    }

    private ScheduledThreadPoolExecutor d() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
        synchronized (this.b) {
            if (this.c && (scheduledThreadPoolExecutor = this.d) != null && !scheduledThreadPoolExecutor.isShutdown()) {
                return this.d;
            }
            return null;
        }
    }

    private ThreadPoolExecutor e() {
        ThreadPoolExecutor threadPoolExecutor;
        synchronized (this.b) {
            if (this.c && (threadPoolExecutor = this.e) != null && !threadPoolExecutor.isShutdown()) {
                return this.e;
            }
            return null;
        }
    }

    private void f() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = this.d;
        if (scheduledThreadPoolExecutor != null) {
            scheduledThreadPoolExecutor.shutdownNow();
            this.d = null;
        }
        ThreadPoolExecutor threadPoolExecutor = this.e;
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdownNow();
            this.e = null;
        }
    }

    public final ScheduledFuture a(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutorD = d();
        if (scheduledThreadPoolExecutorD == null) {
            return null;
        }
        try {
            return scheduledThreadPoolExecutorD.scheduleWithFixedDelay(runnable, j, j2, timeUnit);
        } catch (RejectedExecutionException unused) {
            return null;
        }
    }

    public final ScheduledFuture a(Runnable runnable, long j, TimeUnit timeUnit) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutorD = d();
        if (scheduledThreadPoolExecutorD == null) {
            return null;
        }
        try {
            return scheduledThreadPoolExecutorD.schedule(runnable, j, timeUnit);
        } catch (RejectedExecutionException unused) {
            return null;
        }
    }

    public final void a(int i) {
        synchronized (this.b) {
            int iMax = Math.max(2, Math.min(i, 1000));
            ThreadPoolExecutor threadPoolExecutor = this.e;
            if (threadPoolExecutor == null || threadPoolExecutor.isShutdown() || this.e.getCorePoolSize() != iMax || this.e.getMaximumPoolSize() != iMax) {
                ThreadPoolExecutor threadPoolExecutor2 = this.e;
                ThreadPoolExecutor threadPoolExecutor3 = new ThreadPoolExecutor(iMax, iMax, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue(Math.max(8, iMax * 8)), new o("worker", (byte) 0), new ThreadPoolExecutor.AbortPolicy());
                this.e = threadPoolExecutor3;
                threadPoolExecutor3.allowCoreThreadTimeOut(true);
                if (threadPoolExecutor2 != null) {
                    threadPoolExecutor2.shutdown();
                }
            }
        }
    }

    public final boolean a(Runnable runnable) {
        ThreadPoolExecutor threadPoolExecutorE = e();
        if (threadPoolExecutorE == null) {
            return false;
        }
        try {
            threadPoolExecutorE.execute(runnable);
            return true;
        } catch (RejectedExecutionException unused) {
            return false;
        }
    }

    public final void b() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
        synchronized (this.b) {
            if (!this.c || (scheduledThreadPoolExecutor = this.d) == null || scheduledThreadPoolExecutor.isShutdown()) {
                f();
                this.d = new ScheduledThreadPoolExecutor(8, new o("sched", (byte) 0));
                if (Build.VERSION.SDK_INT >= 21) {
                    this.d.setRemoveOnCancelPolicy(true);
                }
                this.d.allowCoreThreadTimeOut(true);
                this.c = true;
            }
        }
    }

    public final boolean b(Runnable runnable) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutorD = d();
        if (scheduledThreadPoolExecutorD == null) {
            return false;
        }
        try {
            scheduledThreadPoolExecutorD.execute(runnable);
            return true;
        } catch (RejectedExecutionException unused) {
            return false;
        }
    }

    public final ScheduledFuture c(Runnable runnable) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutorD = d();
        if (scheduledThreadPoolExecutorD == null) {
            return null;
        }
        try {
            return scheduledThreadPoolExecutorD.schedule(runnable, 0L, TimeUnit.MILLISECONDS);
        } catch (RejectedExecutionException unused) {
            return null;
        }
    }

    public final void c() {
        synchronized (this.b) {
            this.c = false;
            f();
        }
    }
}
