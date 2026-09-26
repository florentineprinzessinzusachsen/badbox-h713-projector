package g;

import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class h implements r0.a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final boolean f929g = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Logger f930h = Logger.getLogger(h.class.getName());

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final l3.h f931i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Object f932j;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile Object f933d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile d f934e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile g f935f;

    static {
        l3.h fVar;
        try {
            fVar = new e(AtomicReferenceFieldUpdater.newUpdater(g.class, Thread.class, "a"), AtomicReferenceFieldUpdater.newUpdater(g.class, g.class, "b"), AtomicReferenceFieldUpdater.newUpdater(h.class, g.class, "f"), AtomicReferenceFieldUpdater.newUpdater(h.class, d.class, "e"), AtomicReferenceFieldUpdater.newUpdater(h.class, Object.class, "d"));
            th = null;
        } catch (Throwable th) {
            th = th;
            fVar = new f();
        }
        f931i = fVar;
        if (th != null) {
            f930h.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
        f932j = new Object();
    }

    public static void c(h hVar) {
        g gVar;
        d dVar;
        d dVar2;
        d dVar3;
        do {
            gVar = hVar.f935f;
        } while (!f931i.i(hVar, gVar, g.f926c));
        while (true) {
            dVar = null;
            if (gVar == null) {
                break;
            }
            Thread thread = gVar.f927a;
            if (thread != null) {
                gVar.f927a = null;
                LockSupport.unpark(thread);
            }
            gVar = gVar.f928b;
        }
        do {
            dVar2 = hVar.f934e;
        } while (!f931i.g(hVar, dVar2, d.f917d));
        while (true) {
            dVar3 = dVar;
            dVar = dVar2;
            if (dVar == null) {
                break;
            }
            dVar2 = dVar.f920c;
            dVar.f920c = dVar3;
        }
        while (dVar3 != null) {
            d dVar4 = dVar3.f920c;
            d(dVar3.f918a, dVar3.f919b);
            dVar3 = dVar4;
        }
    }

    public static void d(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e4) {
            f930h.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e4);
        }
    }

    public static Object e(Object obj) throws ExecutionException {
        if (obj instanceof a) {
            Throwable th = ((a) obj).f914a;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        }
        if (obj instanceof c) {
            throw new ExecutionException(((c) obj).f916a);
        }
        if (obj == f932j) {
            return null;
        }
        return obj;
    }

    public static Object f(Future future) {
        Object obj;
        boolean z3 = false;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException unused) {
                z3 = true;
            } catch (Throwable th) {
                if (z3) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z3) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    @Override // r0.a
    public final void a(Runnable runnable, Executor executor) {
        executor.getClass();
        d dVar = this.f934e;
        d dVar2 = d.f917d;
        if (dVar != dVar2) {
            d dVar3 = new d(runnable, executor);
            do {
                dVar3.f920c = dVar;
                if (f931i.g(this, dVar, dVar3)) {
                    return;
                } else {
                    dVar = this.f934e;
                }
            } while (dVar != dVar2);
        }
        d(runnable, executor);
    }

    public final void b(StringBuilder sb) {
        try {
            Object objF = f(this);
            sb.append("SUCCESS, result=[");
            sb.append(objF == this ? "this future" : String.valueOf(objF));
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (RuntimeException e4) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e4.getClass());
            sb.append(" thrown from get()]");
        } catch (ExecutionException e5) {
            sb.append("FAILURE, cause=[");
            sb.append(e5.getCause());
            sb.append("]");
        }
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z3) {
        a aVar;
        Object obj = this.f933d;
        if (obj != null) {
            return false;
        }
        if (f929g) {
            aVar = new a(new CancellationException("Future.cancel() was called."), z3);
        } else {
            aVar = z3 ? a.f912b : a.f913c;
        }
        if (!f931i.h(this, obj, aVar)) {
            return false;
        }
        c(this);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String g() {
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j4, TimeUnit timeUnit) throws InterruptedException, TimeoutException {
        g gVar = g.f926c;
        long nanos = timeUnit.toNanos(j4);
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj = this.f933d;
        if (obj != null) {
            return e(obj);
        }
        long jNanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
        if (nanos >= 1000) {
            g gVar2 = this.f935f;
            if (gVar2 != gVar) {
                g gVar3 = new g();
                while (true) {
                    l3.h hVar = f931i;
                    hVar.f0(gVar3, gVar2);
                    if (hVar.i(this, gVar2, gVar3)) {
                        while (true) {
                            LockSupport.parkNanos(this, nanos);
                            if (Thread.interrupted()) {
                                h(gVar3);
                                throw new InterruptedException();
                            }
                            Object obj2 = this.f933d;
                            if (obj2 != null) {
                                return e(obj2);
                            }
                            long jNanoTime2 = jNanoTime - System.nanoTime();
                            if (jNanoTime2 < 1000) {
                                h(gVar3);
                                nanos = jNanoTime2;
                                break;
                            }
                            nanos = jNanoTime2;
                        }
                    } else {
                        gVar2 = this.f935f;
                        if (gVar2 == gVar) {
                        }
                    }
                }
            }
            return e(this.f933d);
        }
        while (nanos > 0) {
            Object obj3 = this.f933d;
            if (obj3 != null) {
                return e(obj3);
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            nanos = jNanoTime - System.nanoTime();
        }
        String string = toString();
        String string2 = timeUnit.toString();
        Locale locale = Locale.ROOT;
        String lowerCase = string2.toLowerCase(locale);
        String str = "Waited " + j4 + " " + timeUnit.toString().toLowerCase(locale);
        if (nanos + 1000 < 0) {
            String str2 = str + " (plus ";
            long j5 = -nanos;
            long jConvert = timeUnit.convert(j5, TimeUnit.NANOSECONDS);
            long nanos2 = j5 - timeUnit.toNanos(jConvert);
            boolean z3 = jConvert == 0 || nanos2 > 1000;
            if (jConvert > 0) {
                String str3 = str2 + jConvert + " " + lowerCase;
                if (z3) {
                    str3 = str3 + ",";
                }
                str2 = str3 + " ";
            }
            if (z3) {
                str2 = str2 + nanos2 + " nanoseconds ";
            }
            str = str2 + "delay)";
        }
        if (isDone()) {
            throw new TimeoutException(str + " but future completed as timeout expired");
        }
        throw new TimeoutException(str + " for " + string);
    }

    public final void h(g gVar) {
        gVar.f927a = null;
        while (true) {
            g gVar2 = this.f935f;
            if (gVar2 == g.f926c) {
                return;
            }
            g gVar3 = null;
            while (gVar2 != null) {
                g gVar4 = gVar2.f928b;
                if (gVar2.f927a != null) {
                    gVar3 = gVar2;
                } else if (gVar3 != null) {
                    gVar3.f928b = gVar4;
                    if (gVar3.f927a == null) {
                    }
                } else if (!f931i.i(this, gVar2, gVar4)) {
                }
                gVar2 = gVar4;
            }
            return;
        }
    }

    public boolean i(Throwable th) {
        if (!f931i.h(this, null, new c(th))) {
            return false;
        }
        c(this);
        return true;
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.f933d instanceof a;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.f933d != null;
    }

    public final String toString() {
        String strG;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (this.f933d instanceof a) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            b(sb);
        } else {
            try {
                strG = g();
            } catch (RuntimeException e4) {
                strG = "Exception thrown from implementation: " + e4.getClass();
            }
            if (strG != null && !strG.isEmpty()) {
                sb.append("PENDING, info=[");
                sb.append(strG);
                sb.append("]");
            } else if (isDone()) {
                b(sb);
            } else {
                sb.append("PENDING");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // java.util.concurrent.Future
    public final Object get() throws InterruptedException {
        Object obj;
        g gVar = g.f926c;
        if (!Thread.interrupted()) {
            Object obj2 = this.f933d;
            if (obj2 != null) {
                return e(obj2);
            }
            g gVar2 = this.f935f;
            if (gVar2 != gVar) {
                g gVar3 = new g();
                do {
                    l3.h hVar = f931i;
                    hVar.f0(gVar3, gVar2);
                    if (hVar.i(this, gVar2, gVar3)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.f933d;
                            } else {
                                h(gVar3);
                                throw new InterruptedException();
                            }
                        } while (obj == null);
                        return e(obj);
                    }
                    gVar2 = this.f935f;
                } while (gVar2 != gVar);
            }
            return e(this.f933d);
        }
        throw new InterruptedException();
    }
}
