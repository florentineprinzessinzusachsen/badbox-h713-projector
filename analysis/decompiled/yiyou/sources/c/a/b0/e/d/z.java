package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableCount.java */
/* JADX INFO: loaded from: classes.dex */
public final class z<T> extends c.a.b0.e.d.a<T, Long> {

    /* JADX INFO: compiled from: ObservableCount.java */
    static final class a implements c.a.s<Object>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super Long> f2933a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c.a.y.b f2934b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f2935c;

        a(c.a.s<? super Long> sVar) {
            this.f2933a = sVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2934b.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2933a.onNext(Long.valueOf(this.f2935c));
            this.f2933a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2933a.onError(th);
        }

        @Override // c.a.s
        public void onNext(Object obj) {
            this.f2935c++;
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2934b, bVar)) {
                this.f2934b = bVar;
                this.f2933a.onSubscribe(this);
            }
        }
    }

    public z(c.a.q<T> qVar) {
        super(qVar);
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super Long> sVar) {
        this.f1932a.subscribe(new a(sVar));
    }
}
