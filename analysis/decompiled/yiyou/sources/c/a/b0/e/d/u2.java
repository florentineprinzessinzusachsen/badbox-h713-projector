package c.a.b0.e.d;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableSampleTimed.java */
/* JADX INFO: loaded from: classes.dex */
public final class u2<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f2756b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final TimeUnit f2757c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final c.a.t f2758d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final boolean f2759e;

    /* JADX INFO: compiled from: ObservableSampleTimed.java */
    static final class a<T> extends c<T> {
        final AtomicInteger g;

        a(c.a.s<? super T> sVar, long j, TimeUnit timeUnit, c.a.t tVar) {
            super(sVar, j, timeUnit, tVar);
            this.g = new AtomicInteger(1);
        }

        @Override // c.a.b0.e.d.u2.c
        void b() {
            c();
            if (this.g.decrementAndGet() == 0) {
                this.f2760a.onComplete();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.g.incrementAndGet() == 2) {
                c();
                if (this.g.decrementAndGet() == 0) {
                    this.f2760a.onComplete();
                }
            }
        }
    }

    /* JADX INFO: compiled from: ObservableSampleTimed.java */
    static final class b<T> extends c<T> {
        b(c.a.s<? super T> sVar, long j, TimeUnit timeUnit, c.a.t tVar) {
            super(sVar, j, timeUnit, tVar);
        }

        @Override // c.a.b0.e.d.u2.c
        void b() {
            this.f2760a.onComplete();
        }

        @Override // java.lang.Runnable
        public void run() {
            c();
        }
    }

    /* JADX INFO: compiled from: ObservableSampleTimed.java */
    static abstract class c<T> extends AtomicReference<T> implements c.a.s<T>, c.a.y.b, Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2760a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f2761b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final TimeUnit f2762c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.t f2763d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final AtomicReference<c.a.y.b> f2764e = new AtomicReference<>();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        c.a.y.b f2765f;

        c(c.a.s<? super T> sVar, long j, TimeUnit timeUnit, c.a.t tVar) {
            this.f2760a = sVar;
            this.f2761b = j;
            this.f2762c = timeUnit;
            this.f2763d = tVar;
        }

        void a() {
            c.a.b0.a.c.a(this.f2764e);
        }

        abstract void b();

        void c() {
            T andSet = getAndSet(null);
            if (andSet != null) {
                this.f2760a.onNext(andSet);
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            a();
            this.f2765f.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            a();
            b();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            a();
            this.f2760a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            lazySet(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2765f, bVar)) {
                this.f2765f = bVar;
                this.f2760a.onSubscribe(this);
                c.a.t tVar = this.f2763d;
                long j = this.f2761b;
                c.a.b0.a.c.a(this.f2764e, tVar.a(this, j, j, this.f2762c));
            }
        }
    }

    public u2(c.a.q<T> qVar, long j, TimeUnit timeUnit, c.a.t tVar, boolean z) {
        super(qVar);
        this.f2756b = j;
        this.f2757c = timeUnit;
        this.f2758d = tVar;
        this.f2759e = z;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        c.a.d0.f fVar = new c.a.d0.f(sVar);
        if (this.f2759e) {
            this.f1932a.subscribe(new a(fVar, this.f2756b, this.f2757c, this.f2758d));
        } else {
            this.f1932a.subscribe(new b(fVar, this.f2756b, this.f2757c, this.f2758d));
        }
    }
}
