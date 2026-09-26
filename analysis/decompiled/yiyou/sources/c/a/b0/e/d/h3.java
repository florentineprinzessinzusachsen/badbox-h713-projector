package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableSkipUntil.java */
/* JADX INFO: loaded from: classes.dex */
public final class h3<T, U> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.q<U> f2230b;

    /* JADX INFO: compiled from: ObservableSkipUntil.java */
    final class a implements c.a.s<U> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c.a.b0.a.a f2231a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final b<T> f2232b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final c.a.d0.f<T> f2233c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        c.a.y.b f2234d;

        a(h3 h3Var, c.a.b0.a.a aVar, b<T> bVar, c.a.d0.f<T> fVar) {
            this.f2231a = aVar;
            this.f2232b = bVar;
            this.f2233c = fVar;
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2232b.f2238d = true;
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2231a.dispose();
            this.f2233c.onError(th);
        }

        @Override // c.a.s
        public void onNext(U u) {
            this.f2234d.dispose();
            this.f2232b.f2238d = true;
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2234d, bVar)) {
                this.f2234d = bVar;
                this.f2231a.a(1, bVar);
            }
        }
    }

    /* JADX INFO: compiled from: ObservableSkipUntil.java */
    static final class b<T> implements c.a.s<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2235a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.b0.a.a f2236b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f2237c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        volatile boolean f2238d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f2239e;

        b(c.a.s<? super T> sVar, c.a.b0.a.a aVar) {
            this.f2235a = sVar;
            this.f2236b = aVar;
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2236b.dispose();
            this.f2235a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2236b.dispose();
            this.f2235a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2239e) {
                this.f2235a.onNext(t);
            } else if (this.f2238d) {
                this.f2239e = true;
                this.f2235a.onNext(t);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2237c, bVar)) {
                this.f2237c = bVar;
                this.f2236b.a(0, bVar);
            }
        }
    }

    public h3(c.a.q<T> qVar, c.a.q<U> qVar2) {
        super(qVar);
        this.f2230b = qVar2;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        c.a.d0.f fVar = new c.a.d0.f(sVar);
        c.a.b0.a.a aVar = new c.a.b0.a.a(2);
        fVar.onSubscribe(aVar);
        b bVar = new b(fVar, aVar);
        this.f2230b.subscribe(new a(this, aVar, bVar, fVar));
        this.f1932a.subscribe(bVar);
    }
}
