package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableHide.java */
/* JADX INFO: loaded from: classes.dex */
public final class k1<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: compiled from: ObservableHide.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2345a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c.a.y.b f2346b;

        a(c.a.s<? super T> sVar) {
            this.f2345a = sVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2346b.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2345a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2345a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2345a.onNext(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2346b, bVar)) {
                this.f2346b = bVar;
                this.f2345a.onSubscribe(this);
            }
        }
    }

    public k1(c.a.q<T> qVar) {
        super(qVar);
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar));
    }
}
