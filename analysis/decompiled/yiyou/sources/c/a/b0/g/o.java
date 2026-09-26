package c.a.b0.g;

import c.a.t;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: TrampolineScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class o extends t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final o f3056b = new o();

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    static final class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Runnable f3057a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final c f3058b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final long f3059c;

        a(Runnable runnable, c cVar, long j) {
            this.f3057a = runnable;
            this.f3058b = cVar;
            this.f3059c = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f3058b.f3067d) {
                return;
            }
            long jA = this.f3058b.a(TimeUnit.MILLISECONDS);
            long j = this.f3059c;
            if (j > jA) {
                try {
                    Thread.sleep(j - jA);
                } catch (InterruptedException e2) {
                    Thread.currentThread().interrupt();
                    c.a.e0.a.b(e2);
                    return;
                }
            }
            if (this.f3058b.f3067d) {
                return;
            }
            this.f3057a.run();
        }
    }

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    static final class b implements Comparable<b> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Runnable f3060a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f3061b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int f3062c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        volatile boolean f3063d;

        b(Runnable runnable, Long l, int i) {
            this.f3060a = runnable;
            this.f3061b = l.longValue();
            this.f3062c = i;
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(b bVar) {
            int iA = c.a.b0.b.b.a(this.f3061b, bVar.f3061b);
            return iA == 0 ? c.a.b0.b.b.a(this.f3062c, bVar.f3062c) : iA;
        }
    }

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    static final class c extends t.c implements c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final PriorityBlockingQueue<b> f3064a = new PriorityBlockingQueue<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final AtomicInteger f3065b = new AtomicInteger();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final AtomicInteger f3066c = new AtomicInteger();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        volatile boolean f3067d;

        /* JADX INFO: compiled from: TrampolineScheduler.java */
        final class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final b f3068a;

            a(b bVar) {
                this.f3068a = bVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                b bVar = this.f3068a;
                bVar.f3063d = true;
                c.this.f3064a.remove(bVar);
            }
        }

        c() {
        }

        @Override // c.a.t.c
        public c.a.y.b a(Runnable runnable) {
            return a(runnable, a(TimeUnit.MILLISECONDS));
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f3067d = true;
        }

        @Override // c.a.t.c
        public c.a.y.b a(Runnable runnable, long j, TimeUnit timeUnit) {
            long jA = a(TimeUnit.MILLISECONDS) + timeUnit.toMillis(j);
            return a(new a(runnable, this, jA), jA);
        }

        c.a.y.b a(Runnable runnable, long j) {
            if (this.f3067d) {
                return c.a.b0.a.d.INSTANCE;
            }
            b bVar = new b(runnable, Long.valueOf(j), this.f3066c.incrementAndGet());
            this.f3064a.add(bVar);
            if (this.f3065b.getAndIncrement() == 0) {
                int iAddAndGet = 1;
                while (!this.f3067d) {
                    b bVarPoll = this.f3064a.poll();
                    if (bVarPoll == null) {
                        iAddAndGet = this.f3065b.addAndGet(-iAddAndGet);
                        if (iAddAndGet == 0) {
                            return c.a.b0.a.d.INSTANCE;
                        }
                    } else if (!bVarPoll.f3063d) {
                        bVarPoll.f3060a.run();
                    }
                }
                this.f3064a.clear();
                return c.a.b0.a.d.INSTANCE;
            }
            return c.a.y.c.a(new a(bVar));
        }
    }

    o() {
    }

    public static o b() {
        return f3056b;
    }

    @Override // c.a.t
    public t.c a() {
        return new c();
    }

    @Override // c.a.t
    public c.a.y.b a(Runnable runnable) {
        c.a.e0.a.a(runnable).run();
        return c.a.b0.a.d.INSTANCE;
    }

    @Override // c.a.t
    public c.a.y.b a(Runnable runnable, long j, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(j);
            c.a.e0.a.a(runnable).run();
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            c.a.e0.a.b(e2);
        }
        return c.a.b0.a.d.INSTANCE;
    }
}
