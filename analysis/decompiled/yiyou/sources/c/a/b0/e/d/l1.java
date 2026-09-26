package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableIgnoreElements.java */
/* JADX INFO: loaded from: classes.dex */
public final class l1<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: compiled from: ObservableIgnoreElements.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2392a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c.a.y.b f2393b;

        a(c.a.s<? super T> sVar) {
            this.f2392a = sVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2393b.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2392a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2392a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            this.f2393b = bVar;
            this.f2392a.onSubscribe(this);
        }
    }

    public l1(c.a.q<T> qVar) {
        super(qVar);
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar));
    }
}
