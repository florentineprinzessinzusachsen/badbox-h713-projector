package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableFlatMapCompletable.java */
/* JADX INFO: loaded from: classes.dex */
public final class w0<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.n<? super T, ? extends c.a.d> f2823b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f2824c;

    public w0(c.a.q<T> qVar, c.a.a0.n<? super T, ? extends c.a.d> nVar, boolean z) {
        super(qVar);
        this.f2823b = nVar;
        this.f2824c = z;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2823b, this.f2824c));
    }

    /* JADX INFO: compiled from: ObservableFlatMapCompletable.java */
    static final class a<T> extends c.a.b0.d.b<T> implements c.a.s<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2825a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.a0.n<? super T, ? extends c.a.d> f2827c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final boolean f2828d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        c.a.y.b f2830f;
        volatile boolean g;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.b0.j.c f2826b = new c.a.b0.j.c();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final c.a.y.a f2829e = new c.a.y.a();

        /* JADX INFO: renamed from: c.a.b0.e.d.w0$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableFlatMapCompletable.java */
        final class C0065a extends AtomicReference<c.a.y.b> implements c.a.c, c.a.y.b {
            C0065a() {
            }

            @Override // c.a.y.b
            public void dispose() {
                c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
            }

            @Override // c.a.c, c.a.i
            public void onComplete() {
                a.this.a(this);
            }

            @Override // c.a.c, c.a.i
            public void onError(Throwable th) {
                a.this.a(this, th);
            }

            @Override // c.a.c, c.a.i
            public void onSubscribe(c.a.y.b bVar) {
                c.a.b0.a.c.c(this, bVar);
            }
        }

        a(c.a.s<? super T> sVar, c.a.a0.n<? super T, ? extends c.a.d> nVar, boolean z) {
            this.f2825a = sVar;
            this.f2827c = nVar;
            this.f2828d = z;
            lazySet(1);
        }

        @Override // c.a.b0.c.f
        public int a(int i) {
            return i & 2;
        }

        void a(a<T>.C0065a c0065a) {
            this.f2829e.a(c0065a);
            onComplete();
        }

        @Override // c.a.b0.c.j
        public void clear() {
        }

        @Override // c.a.y.b
        public void dispose() {
            this.g = true;
            this.f2830f.dispose();
            this.f2829e.dispose();
        }

        @Override // c.a.b0.c.j
        public boolean isEmpty() {
            return true;
        }

        @Override // c.a.s
        public void onComplete() {
            if (decrementAndGet() == 0) {
                Throwable thA = this.f2826b.a();
                if (thA != null) {
                    this.f2825a.onError(thA);
                } else {
                    this.f2825a.onComplete();
                }
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (!this.f2826b.a(th)) {
                c.a.e0.a.b(th);
                return;
            }
            if (this.f2828d) {
                if (decrementAndGet() == 0) {
                    this.f2825a.onError(this.f2826b.a());
                    return;
                }
                return;
            }
            dispose();
            if (getAndSet(0) > 0) {
                this.f2825a.onError(this.f2826b.a());
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            try {
                c.a.d dVarApply = this.f2827c.apply(t);
                c.a.b0.b.b.a(dVarApply, "The mapper returned a null CompletableSource");
                c.a.d dVar = dVarApply;
                getAndIncrement();
                C0065a c0065a = new C0065a();
                if (this.g || !this.f2829e.c(c0065a)) {
                    return;
                }
                dVar.a(c0065a);
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f2830f.dispose();
                onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2830f, bVar)) {
                this.f2830f = bVar;
                this.f2825a.onSubscribe(this);
            }
        }

        @Override // c.a.b0.c.j
        public T poll() {
            return null;
        }

        void a(a<T>.C0065a c0065a, Throwable th) {
            this.f2829e.a(c0065a);
            onError(th);
        }
    }
}
