package c.a.b0.g;

import c.a.t;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ComputationScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends t implements l {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final C0073b f2997d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final h f2998e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static final int f2999f = a(Runtime.getRuntime().availableProcessors(), Integer.getInteger("rx2.computation-threads", 0).intValue());
    static final c g = new c(new h("RxComputationShutdown"));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ThreadFactory f3000b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final AtomicReference<C0073b> f3001c;

    /* JADX INFO: renamed from: c.a.b0.g.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ComputationScheduler.java */
    static final class C0073b implements l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int f3007a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c[] f3008b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f3009c;

        C0073b(int i, ThreadFactory threadFactory) {
            this.f3007a = i;
            this.f3008b = new c[i];
            for (int i2 = 0; i2 < i; i2++) {
                this.f3008b[i2] = new c(threadFactory);
            }
        }

        public c a() {
            int i = this.f3007a;
            if (i == 0) {
                return b.g;
            }
            c[] cVarArr = this.f3008b;
            long j = this.f3009c;
            this.f3009c = 1 + j;
            return cVarArr[(int) (j % ((long) i))];
        }

        public void b() {
            for (c cVar : this.f3008b) {
                cVar.dispose();
            }
        }
    }

    /* JADX INFO: compiled from: ComputationScheduler.java */
    static final class c extends f {
        c(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }

    static {
        g.dispose();
        f2998e = new h("RxComputationThreadPool", Math.max(1, Math.min(10, Integer.getInteger("rx2.computation-priority", 5).intValue())), true);
        f2997d = new C0073b(0, f2998e);
        f2997d.b();
    }

    public b() {
        this(f2998e);
    }

    static int a(int i, int i2) {
        return (i2 <= 0 || i2 > i) ? i : i2;
    }

    @Override // c.a.t
    public t.c a() {
        return new a(this.f3001c.get().a());
    }

    public void b() {
        C0073b c0073b = new C0073b(f2999f, this.f3000b);
        if (this.f3001c.compareAndSet(f2997d, c0073b)) {
            return;
        }
        c0073b.b();
    }

    public b(ThreadFactory threadFactory) {
        this.f3000b = threadFactory;
        this.f3001c = new AtomicReference<>(f2997d);
        b();
    }

    @Override // c.a.t
    public c.a.y.b a(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.f3001c.get().a().b(runnable, j, timeUnit);
    }

    /* JADX INFO: compiled from: ComputationScheduler.java */
    static final class a extends t.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c.a.b0.a.e f3002a = new c.a.b0.a.e();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final c.a.y.a f3003b = new c.a.y.a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final c.a.b0.a.e f3004c = new c.a.b0.a.e();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final c f3005d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        volatile boolean f3006e;

        a(c cVar) {
            this.f3005d = cVar;
            this.f3004c.c(this.f3002a);
            this.f3004c.c(this.f3003b);
        }

        @Override // c.a.t.c
        public c.a.y.b a(Runnable runnable) {
            return this.f3006e ? c.a.b0.a.d.INSTANCE : this.f3005d.a(runnable, 0L, TimeUnit.MILLISECONDS, this.f3002a);
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.f3006e) {
                return;
            }
            this.f3006e = true;
            this.f3004c.dispose();
        }

        @Override // c.a.t.c
        public c.a.y.b a(Runnable runnable, long j, TimeUnit timeUnit) {
            if (this.f3006e) {
                return c.a.b0.a.d.INSTANCE;
            }
            return this.f3005d.a(runnable, j, timeUnit, this.f3003b);
        }
    }

    @Override // c.a.t
    public c.a.y.b a(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return this.f3001c.get().a().b(runnable, j, j2, timeUnit);
    }
}
