package e;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: AsyncTimeout.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends t {
    private static final long h = TimeUnit.SECONDS.toMillis(60);
    private static final long i = TimeUnit.MILLISECONDS.toNanos(h);
    static a j;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f4719e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private a f4720f;
    private long g;

    /* JADX INFO: renamed from: e.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AsyncTimeout.java */
    class C0105a implements r {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ r f4721a;

        C0105a(r rVar) {
            this.f4721a = rVar;
        }

        @Override // e.r
        public void a(e.c cVar, long j) throws IOException {
            u.a(cVar.f4728b, 0L, j);
            while (true) {
                long j2 = 0;
                if (j <= 0) {
                    return;
                }
                o oVar = cVar.f4727a;
                while (j2 < 65536) {
                    j2 += (long) (oVar.f4761c - oVar.f4760b);
                    if (j2 >= j) {
                        j2 = j;
                        break;
                    }
                    oVar = oVar.f4764f;
                }
                a.this.g();
                try {
                    try {
                        this.f4721a.a(cVar, j2);
                        j -= j2;
                        a.this.a(true);
                    } catch (IOException e2) {
                        throw a.this.a(e2);
                    }
                } catch (Throwable th) {
                    a.this.a(false);
                    throw th;
                }
            }
        }

        @Override // e.r, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            a.this.g();
            try {
                try {
                    this.f4721a.close();
                    a.this.a(true);
                } catch (IOException e2) {
                    throw a.this.a(e2);
                }
            } catch (Throwable th) {
                a.this.a(false);
                throw th;
            }
        }

        @Override // e.r, java.io.Flushable
        public void flush() throws IOException {
            a.this.g();
            try {
                try {
                    this.f4721a.flush();
                    a.this.a(true);
                } catch (IOException e2) {
                    throw a.this.a(e2);
                }
            } catch (Throwable th) {
                a.this.a(false);
                throw th;
            }
        }

        @Override // e.r
        public t timeout() {
            return a.this;
        }

        public String toString() {
            return "AsyncTimeout.sink(" + this.f4721a + ")";
        }
    }

    /* JADX INFO: compiled from: AsyncTimeout.java */
    class b implements s {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ s f4723a;

        b(s sVar) {
            this.f4723a = sVar;
        }

        @Override // e.s, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            try {
                try {
                    this.f4723a.close();
                    a.this.a(true);
                } catch (IOException e2) {
                    throw a.this.a(e2);
                }
            } catch (Throwable th) {
                a.this.a(false);
                throw th;
            }
        }

        @Override // e.s
        public long read(e.c cVar, long j) throws IOException {
            a.this.g();
            try {
                try {
                    long j2 = this.f4723a.read(cVar, j);
                    a.this.a(true);
                    return j2;
                } catch (IOException e2) {
                    throw a.this.a(e2);
                }
            } catch (Throwable th) {
                a.this.a(false);
                throw th;
            }
        }

        @Override // e.s
        public t timeout() {
            return a.this;
        }

        public String toString() {
            return "AsyncTimeout.source(" + this.f4723a + ")";
        }
    }

    /* JADX INFO: compiled from: AsyncTimeout.java */
    private static final class c extends Thread {
        c() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0015, code lost:
        
            r1.i();
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r3 = this;
            L0:
                java.lang.Class<e.a> r0 = e.a.class
                monitor-enter(r0)     // Catch: java.lang.InterruptedException -> L0
                e.a r1 = e.a.j()     // Catch: java.lang.Throwable -> L19
                if (r1 != 0) goto Lb
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                goto L0
            Lb:
                e.a r2 = e.a.j     // Catch: java.lang.Throwable -> L19
                if (r1 != r2) goto L14
                r1 = 0
                e.a.j = r1     // Catch: java.lang.Throwable -> L19
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                return
            L14:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                r1.i()     // Catch: java.lang.InterruptedException -> L0
                goto L0
            L19:
                r1 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                goto L1d
            L1c:
                throw r1
            L1d:
                goto L1c
            */
            throw new UnsupportedOperationException("Method not decompiled: e.a.c.run():void");
        }
    }

    private static synchronized void a(a aVar, long j2, boolean z) {
        if (j == null) {
            j = new a();
            new c().start();
        }
        long jNanoTime = System.nanoTime();
        if (j2 != 0 && z) {
            aVar.g = Math.min(j2, aVar.c() - jNanoTime) + jNanoTime;
        } else if (j2 != 0) {
            aVar.g = j2 + jNanoTime;
        } else {
            if (!z) {
                throw new AssertionError();
            }
            aVar.g = aVar.c();
        }
        long jB = aVar.b(jNanoTime);
        a aVar2 = j;
        while (aVar2.f4720f != null && jB >= aVar2.f4720f.b(jNanoTime)) {
            aVar2 = aVar2.f4720f;
        }
        aVar.f4720f = aVar2.f4720f;
        aVar2.f4720f = aVar;
        if (aVar2 == j) {
            a.class.notify();
        }
    }

    private long b(long j2) {
        return this.g - j2;
    }

    static a j() throws InterruptedException {
        a aVar = j.f4720f;
        if (aVar == null) {
            long jNanoTime = System.nanoTime();
            a.class.wait(h);
            if (j.f4720f != null || System.nanoTime() - jNanoTime < i) {
                return null;
            }
            return j;
        }
        long jB = aVar.b(System.nanoTime());
        if (jB > 0) {
            long j2 = jB / 1000000;
            a.class.wait(j2, (int) (jB - (1000000 * j2)));
            return null;
        }
        j.f4720f = aVar.f4720f;
        aVar.f4720f = null;
        return aVar;
    }

    public final void g() {
        if (this.f4719e) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long jF = f();
        boolean zD = d();
        if (jF != 0 || zD) {
            this.f4719e = true;
            a(this, jF, zD);
        }
    }

    public final boolean h() {
        if (!this.f4719e) {
            return false;
        }
        this.f4719e = false;
        return a(this);
    }

    protected void i() {
    }

    protected IOException b(IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    private static synchronized boolean a(a aVar) {
        for (a aVar2 = j; aVar2 != null; aVar2 = aVar2.f4720f) {
            if (aVar2.f4720f == aVar) {
                aVar2.f4720f = aVar.f4720f;
                aVar.f4720f = null;
                return false;
            }
        }
        return true;
    }

    public final r a(r rVar) {
        return new C0105a(rVar);
    }

    public final s a(s sVar) {
        return new b(sVar);
    }

    final void a(boolean z) throws IOException {
        if (h() && z) {
            throw b((IOException) null);
        }
    }

    final IOException a(IOException iOException) {
        return !h() ? iOException : b(iOException);
    }
}
