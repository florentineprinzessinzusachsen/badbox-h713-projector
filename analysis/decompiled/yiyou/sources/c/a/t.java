package c.a;

import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Scheduler.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final long f3166a = TimeUnit.MINUTES.toNanos(Long.getLong("rx2.scheduler.drift-tolerance", 15).longValue());

    /* JADX INFO: compiled from: Scheduler.java */
    static final class a implements c.a.y.b, Runnable, c.a.f0.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Runnable f3167a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c f3168b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        Thread f3169c;

        a(Runnable runnable, c cVar) {
            this.f3167a = runnable;
            this.f3168b = cVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.f3169c == Thread.currentThread()) {
                c cVar = this.f3168b;
                if (cVar instanceof c.a.b0.g.f) {
                    ((c.a.b0.g.f) cVar).a();
                    return;
                }
            }
            this.f3168b.dispose();
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f3169c = Thread.currentThread();
            try {
                this.f3167a.run();
            } finally {
                dispose();
                this.f3169c = null;
            }
        }
    }

    /* JADX INFO: compiled from: Scheduler.java */
    static final class b implements c.a.y.b, Runnable, c.a.f0.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Runnable f3170a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c f3171b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        volatile boolean f3172c;

        b(Runnable runnable, c cVar) {
            this.f3170a = runnable;
            this.f3171b = cVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f3172c = true;
            this.f3171b.dispose();
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f3172c) {
                return;
            }
            try {
                this.f3170a.run();
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f3171b.dispose();
                throw c.a.b0.j.j.a(th);
            }
        }
    }

    /* JADX INFO: compiled from: Scheduler.java */
    public static abstract class c implements c.a.y.b {

        /* JADX INFO: compiled from: Scheduler.java */
        final class a implements Runnable, c.a.f0.a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final Runnable f3173a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final c.a.b0.a.f f3174b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final long f3175c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            long f3176d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            long f3177e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            long f3178f;

            a(long j, Runnable runnable, long j2, c.a.b0.a.f fVar, long j3) {
                this.f3173a = runnable;
                this.f3174b = fVar;
                this.f3175c = j3;
                this.f3177e = j2;
                this.f3178f = j;
            }

            /* JADX WARN: Code duplicated, block: B:10:0x0035  */
            @Override // java.lang.Runnable
            public void run() {
                long j;
                this.f3173a.run();
                if (this.f3174b.a()) {
                    return;
                }
                long jA = c.this.a(TimeUnit.NANOSECONDS);
                long j2 = t.f3166a;
                long j3 = jA + j2;
                long j4 = this.f3177e;
                if (j3 >= j4) {
                    long j5 = this.f3175c;
                    if (jA >= j4 + j5 + j2) {
                        long j6 = this.f3175c;
                        long j7 = jA + j6;
                        long j8 = this.f3176d + 1;
                        this.f3176d = j8;
                        this.f3178f = j7 - (j6 * j8);
                        j = j7;
                    } else {
                        long j9 = this.f3178f;
                        long j10 = this.f3176d + 1;
                        this.f3176d = j10;
                        j = j9 + (j10 * j5);
                    }
                } else {
                    long j11 = this.f3175c;
                    long j12 = jA + j11;
                    long j13 = this.f3176d + 1;
                    this.f3176d = j13;
                    this.f3178f = j12 - (j11 * j13);
                    j = j12;
                }
                this.f3177e = jA;
                this.f3174b.a(c.this.a(this, j - jA, TimeUnit.NANOSECONDS));
            }
        }

        public c.a.y.b a(Runnable runnable) {
            return a(runnable, 0L, TimeUnit.NANOSECONDS);
        }

        public abstract c.a.y.b a(Runnable runnable, long j, TimeUnit timeUnit);

        public c.a.y.b a(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            c.a.b0.a.f fVar = new c.a.b0.a.f();
            c.a.b0.a.f fVar2 = new c.a.b0.a.f(fVar);
            Runnable runnableA = c.a.e0.a.a(runnable);
            long nanos = timeUnit.toNanos(j2);
            long jA = a(TimeUnit.NANOSECONDS);
            c.a.y.b bVarA = a(new a(jA + timeUnit.toNanos(j), runnableA, jA, fVar2, nanos), j, timeUnit);
            if (bVarA == c.a.b0.a.d.INSTANCE) {
                return bVarA;
            }
            fVar.a(bVarA);
            return fVar2;
        }

        public long a(TimeUnit timeUnit) {
            return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }
    }

    public long a(TimeUnit timeUnit) {
        return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    public abstract c a();

    public c.a.y.b a(Runnable runnable) {
        return a(runnable, 0L, TimeUnit.NANOSECONDS);
    }

    public c.a.y.b a(Runnable runnable, long j, TimeUnit timeUnit) {
        c cVarA = a();
        a aVar = new a(c.a.e0.a.a(runnable), cVarA);
        cVarA.a(aVar, j, timeUnit);
        return aVar;
    }

    public c.a.y.b a(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        c cVarA = a();
        b bVar = new b(c.a.e0.a.a(runnable), cVarA);
        c.a.y.b bVarA = cVarA.a(bVar, j, j2, timeUnit);
        return bVarA == c.a.b0.a.d.INSTANCE ? bVarA : bVar;
    }
}
