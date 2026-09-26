package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableWithLatestFrom.java */
/* JADX INFO: loaded from: classes.dex */
public final class i4<T, U, R> extends c.a.b0.e.d.a<T, R> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.c<? super T, ? super U, ? extends R> f2286b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.q<? extends U> f2287c;

    /* JADX INFO: compiled from: ObservableWithLatestFrom.java */
    final class a implements c.a.s<U> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b<T, U, R> f2288a;

        a(i4 i4Var, b<T, U, R> bVar) {
            this.f2288a = bVar;
        }

        @Override // c.a.s
        public void onComplete() {
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2288a.a(th);
        }

        @Override // c.a.s
        public void onNext(U u) {
            this.f2288a.lazySet(u);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            this.f2288a.a(bVar);
        }
    }

    /* JADX INFO: compiled from: ObservableWithLatestFrom.java */
    static final class b<T, U, R> extends AtomicReference<U> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super R> f2289a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.c<? super T, ? super U, ? extends R> f2290b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final AtomicReference<c.a.y.b> f2291c = new AtomicReference<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final AtomicReference<c.a.y.b> f2292d = new AtomicReference<>();

        b(c.a.s<? super R> sVar, c.a.a0.c<? super T, ? super U, ? extends R> cVar) {
            this.f2289a = sVar;
            this.f2290b = cVar;
        }

        public boolean a(c.a.y.b bVar) {
            return c.a.b0.a.c.c(this.f2292d, bVar);
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a(this.f2291c);
            c.a.b0.a.c.a(this.f2292d);
        }

        @Override // c.a.s
        public void onComplete() {
            c.a.b0.a.c.a(this.f2292d);
            this.f2289a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            c.a.b0.a.c.a(this.f2292d);
            this.f2289a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            U u = get();
            if (u != null) {
                try {
                    R rA = this.f2290b.a(t, u);
                    c.a.b0.b.b.a(rA, "The combiner returned a null value");
                    this.f2289a.onNext(rA);
                } catch (Throwable th) {
                    c.a.z.b.b(th);
                    dispose();
                    this.f2289a.onError(th);
                }
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.c(this.f2291c, bVar);
        }

        public void a(Throwable th) {
            c.a.b0.a.c.a(this.f2291c);
            this.f2289a.onError(th);
        }
    }

    public i4(c.a.q<T> qVar, c.a.a0.c<? super T, ? super U, ? extends R> cVar, c.a.q<? extends U> qVar2) {
        super(qVar);
        this.f2286b = cVar;
        this.f2287c = qVar2;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super R> sVar) {
        c.a.d0.f fVar = new c.a.d0.f(sVar);
        b bVar = new b(fVar, this.f2286b);
        fVar.onSubscribe(bVar);
        this.f2287c.subscribe(new a(this, bVar));
        this.f1932a.subscribe(bVar);
    }
}
