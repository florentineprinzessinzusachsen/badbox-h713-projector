package d;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: ConnectionPool.java */
/* JADX INFO: loaded from: classes.dex */
public final class j {
    private static final Executor g = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), d.h0.c.a("OkHttp ConnectionPool", true));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f4619a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final long f4620b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Runnable f4621c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Deque<d.h0.f.c> f4622d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final d.h0.f.d f4623e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    boolean f4624f;

    /* JADX INFO: compiled from: ConnectionPool.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            while (true) {
                long jA = j.this.a(System.nanoTime());
                if (jA == -1) {
                    return;
                }
                if (jA > 0) {
                    long j = jA / 1000000;
                    long j2 = jA - (1000000 * j);
                    synchronized (j.this) {
                        try {
                            j.this.wait(j, (int) j2);
                        } catch (InterruptedException unused) {
                        }
                    }
                }
            }
        }
    }

    public j() {
        this(5, 5L, TimeUnit.MINUTES);
    }

    d.h0.f.c a(d.a aVar, d.h0.f.g gVar, e0 e0Var) {
        for (d.h0.f.c cVar : this.f4622d) {
            if (cVar.a(aVar, e0Var)) {
                gVar.a(cVar, true);
                return cVar;
            }
        }
        return null;
    }

    void b(d.h0.f.c cVar) {
        if (!this.f4624f) {
            this.f4624f = true;
            g.execute(this.f4621c);
        }
        this.f4622d.add(cVar);
    }

    public j(int i, long j, TimeUnit timeUnit) {
        this.f4621c = new a();
        this.f4622d = new ArrayDeque();
        this.f4623e = new d.h0.f.d();
        this.f4619a = i;
        this.f4620b = timeUnit.toNanos(j);
        if (j > 0) {
            return;
        }
        throw new IllegalArgumentException("keepAliveDuration <= 0: " + j);
    }

    Socket a(d.a aVar, d.h0.f.g gVar) {
        for (d.h0.f.c cVar : this.f4622d) {
            if (cVar.a(aVar, null) && cVar.d() && cVar != gVar.c()) {
                return gVar.a(cVar);
            }
        }
        return null;
    }

    boolean a(d.h0.f.c cVar) {
        if (!cVar.k && this.f4619a != 0) {
            notifyAll();
            return false;
        }
        this.f4622d.remove(cVar);
        return true;
    }

    long a(long j) {
        synchronized (this) {
            long j2 = Long.MIN_VALUE;
            d.h0.f.c cVar = null;
            int i = 0;
            int i2 = 0;
            for (d.h0.f.c cVar2 : this.f4622d) {
                if (a(cVar2, j) > 0) {
                    i2++;
                } else {
                    i++;
                    long j3 = j - cVar2.o;
                    if (j3 > j2) {
                        cVar = cVar2;
                        j2 = j3;
                    }
                }
            }
            if (j2 < this.f4620b && i <= this.f4619a) {
                if (i > 0) {
                    return this.f4620b - j2;
                }
                if (i2 > 0) {
                    return this.f4620b;
                }
                this.f4624f = false;
                return -1L;
            }
            this.f4622d.remove(cVar);
            d.h0.c.a(cVar.f());
            return 0L;
        }
    }

    private int a(d.h0.f.c cVar, long j) {
        List<Reference<d.h0.f.g>> list = cVar.n;
        int i = 0;
        while (i < list.size()) {
            Reference<d.h0.f.g> reference = list.get(i);
            if (reference.get() != null) {
                i++;
            } else {
                d.h0.k.f.d().a("A connection to " + cVar.e().a().k() + " was leaked. Did you forget to close a response body?", ((d.h0.f.g.a) reference).f4409a);
                list.remove(i);
                cVar.k = true;
                if (list.isEmpty()) {
                    cVar.o = j - this.f4620b;
                    return 0;
                }
            }
        }
        return list.size();
    }
}
