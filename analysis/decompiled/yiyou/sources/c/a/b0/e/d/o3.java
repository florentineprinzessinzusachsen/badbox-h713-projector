package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableTakeLastOne.java */
/* JADX INFO: loaded from: classes.dex */
public final class o3<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: compiled from: ObservableTakeLastOne.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2527a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c.a.y.b f2528b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        T f2529c;

        a(c.a.s<? super T> sVar) {
            this.f2527a = sVar;
        }

        void a() {
            T t = this.f2529c;
            if (t != null) {
                this.f2529c = null;
                this.f2527a.onNext(t);
            }
            this.f2527a.onComplete();
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2529c = null;
            this.f2528b.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            a();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2529c = null;
            this.f2527a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2529c = t;
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2528b, bVar)) {
                this.f2528b = bVar;
                this.f2527a.onSubscribe(this);
            }
        }
    }

    public o3(c.a.q<T> qVar) {
        super(qVar);
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar));
    }
}
