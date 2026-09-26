package r2;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class y extends n0 implements Runnable {
    private static volatile Thread _thread;
    private static volatile int debugStatus;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final y f2050m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final long f2051n;

    static {
        Long l4;
        y yVar = new y();
        f2050m = yVar;
        yVar.Z(false);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        try {
            l4 = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000L);
        } catch (SecurityException unused) {
            l4 = 1000L;
        }
        f2051n = timeUnit.toNanos(l4.longValue());
    }

    @Override // r2.n0, r2.a0
    public final g0 A(long j4, n1 n1Var, y1.h hVar) {
        long j5 = 0;
        if (j4 > 0) {
            j5 = j4 >= 9223372036854L ? Long.MAX_VALUE : 1000000 * j4;
        }
        if (j5 >= 4611686018427387903L) {
            return g1.f1979d;
        }
        long jNanoTime = System.nanoTime();
        k0 k0Var = new k0(j5 + jNanoTime, n1Var);
        h0(jNanoTime, k0Var);
        return k0Var;
    }

    @Override // r2.o0
    public final Thread Y() {
        Thread thread;
        Thread thread2 = _thread;
        if (thread2 != null) {
            return thread2;
        }
        synchronized (this) {
            thread = _thread;
            if (thread == null) {
                thread = new Thread(this, "kotlinx.coroutines.DefaultExecutor");
                _thread = thread;
                thread.setContextClassLoader(f2050m.getClass().getClassLoader());
                thread.setDaemon(true);
                thread.start();
            }
        }
        return thread;
    }

    @Override // r2.o0
    public final void c0(long j4, l0 l0Var) {
        throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
    }

    @Override // r2.n0
    public final void d0(Runnable runnable) {
        if (debugStatus == 4) {
            throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
        }
        super.d0(runnable);
    }

    public final synchronized void i0() {
        int i4 = debugStatus;
        if (i4 == 2 || i4 == 3) {
            debugStatus = 3;
            n0.f2002j.set(this, null);
            n0.f2003k.set(this, null);
            notifyAll();
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        l1.f1998a.set(this);
        try {
            synchronized (this) {
                int i4 = debugStatus;
                if (i4 == 2 || i4 == 3) {
                    _thread = null;
                    i0();
                    if (g0()) {
                        return;
                    }
                    Y();
                    return;
                }
                debugStatus = 1;
                notifyAll();
                long j4 = Long.MAX_VALUE;
                while (true) {
                    Thread.interrupted();
                    long jA0 = a0();
                    if (jA0 == Long.MAX_VALUE) {
                        long jNanoTime = System.nanoTime();
                        if (j4 == Long.MAX_VALUE) {
                            j4 = f2051n + jNanoTime;
                        }
                        long j5 = j4 - jNanoTime;
                        if (j5 <= 0) {
                            _thread = null;
                            i0();
                            if (g0()) {
                                return;
                            }
                            Y();
                            return;
                        }
                        if (jA0 > j5) {
                            jA0 = j5;
                        }
                    } else {
                        j4 = Long.MAX_VALUE;
                    }
                    if (jA0 > 0) {
                        int i5 = debugStatus;
                        if (i5 == 2 || i5 == 3) {
                            _thread = null;
                            i0();
                            if (g0()) {
                                return;
                            }
                            Y();
                            return;
                        }
                        LockSupport.parkNanos(this, jA0);
                    }
                }
            }
        } catch (Throwable th) {
            _thread = null;
            i0();
            if (!g0()) {
                Y();
            }
            throw th;
        }
    }

    @Override // r2.n0, r2.o0
    public final void shutdown() {
        debugStatus = 4;
        super.shutdown();
    }

    @Override // r2.s
    public final String toString() {
        return "DefaultExecutor";
    }
}
