package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableSampleWithObservable.java */
/* JADX INFO: loaded from: classes.dex */
public final class v2<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.q<?> f2803b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f2804c;

    /* JADX INFO: compiled from: ObservableSampleWithObservable.java */
    static final class a<T> extends c<T> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final AtomicInteger f2805e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        volatile boolean f2806f;

        a(c.a.s<? super T> sVar, c.a.q<?> qVar) {
            super(sVar, qVar);
            this.f2805e = new AtomicInteger();
        }

        @Override // c.a.b0.e.d.v2.c
        void b() {
            this.f2806f = true;
            if (this.f2805e.getAndIncrement() == 0) {
                d();
                this.f2807a.onComplete();
            }
        }

        @Override // c.a.b0.e.d.v2.c
        void c() {
            this.f2806f = true;
            if (this.f2805e.getAndIncrement() == 0) {
                d();
                this.f2807a.onComplete();
            }
        }

        @Override // c.a.b0.e.d.v2.c
        void e() {
            if (this.f2805e.getAndIncrement() == 0) {
                do {
                    boolean z = this.f2806f;
                    d();
                    if (z) {
                        this.f2807a.onComplete();
                        return;
                    }
                } while (this.f2805e.decrementAndGet() != 0);
            }
        }
    }

    /* JADX INFO: compiled from: ObservableSampleWithObservable.java */
    static final class b<T> extends c<T> {
        b(c.a.s<? super T> sVar, c.a.q<?> qVar) {
            super(sVar, qVar);
        }

        @Override // c.a.b0.e.d.v2.c
        void b() {
            this.f2807a.onComplete();
        }

        @Override // c.a.b0.e.d.v2.c
        void c() {
            this.f2807a.onComplete();
        }

        @Override // c.a.b0.e.d.v2.c
        void e() {
            d();
        }
    }

    /* JADX INFO: compiled from: ObservableSampleWithObservable.java */
    static abstract class c<T> extends AtomicReference<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2807a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.q<?> f2808b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final AtomicReference<c.a.y.b> f2809c = new AtomicReference<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        c.a.y.b f2810d;

        c(c.a.s<? super T> sVar, c.a.q<?> qVar) {
            this.f2807a = sVar;
            this.f2808b = qVar;
        }

        boolean a(c.a.y.b bVar) {
            return c.a.b0.a.c.c(this.f2809c, bVar);
        }

        abstract void b();

        abstract void c();

        void d() {
            T andSet = getAndSet(null);
            if (andSet != null) {
                this.f2807a.onNext(andSet);
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a(this.f2809c);
            this.f2810d.dispose();
        }

        abstract void e();

        @Override // c.a.s
        public void onComplete() {
            c.a.b0.a.c.a(this.f2809c);
            b();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            c.a.b0.a.c.a(this.f2809c);
            this.f2807a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            lazySet(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2810d, bVar)) {
                this.f2810d = bVar;
                this.f2807a.onSubscribe(this);
                if (this.f2809c.get() == null) {
                    this.f2808b.subscribe(new d(this));
                }
            }
        }

        public void a(Throwable th) {
            this.f2810d.dispose();
            this.f2807a.onError(th);
        }

        public void a() {
            this.f2810d.dispose();
            c();
        }
    }

    /* JADX INFO: compiled from: ObservableSampleWithObservable.java */
    static final class d<T> implements c.a.s<Object> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c<T> f2811a;

        d(c<T> cVar) {
            this.f2811a = cVar;
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2811a.a();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2811a.a(th);
        }

        @Override // c.a.s
        public void onNext(Object obj) {
            this.f2811a.e();
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            this.f2811a.a(bVar);
        }
    }

    public v2(c.a.q<T> qVar, c.a.q<?> qVar2, boolean z) {
        super(qVar);
        this.f2803b = qVar2;
        this.f2804c = z;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        c.a.d0.f fVar = new c.a.d0.f(sVar);
        if (this.f2804c) {
            this.f1932a.subscribe(new a(fVar, this.f2803b));
        } else {
            this.f1932a.subscribe(new b(fVar, this.f2803b));
        }
    }
}
