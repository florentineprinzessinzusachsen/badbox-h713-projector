package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableIgnoreElementsCompletable.java */
/* JADX INFO: loaded from: classes.dex */
public final class m1<T> extends c.a.b implements c.a.b0.c.a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<T> f2437a;

    /* JADX INFO: compiled from: ObservableIgnoreElementsCompletable.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.c f2438a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c.a.y.b f2439b;

        a(c.a.c cVar) {
            this.f2438a = cVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2439b.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2438a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2438a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            this.f2439b = bVar;
            this.f2438a.onSubscribe(this);
        }
    }

    public m1(c.a.q<T> qVar) {
        this.f2437a = qVar;
    }

    @Override // c.a.b0.c.a
    public c.a.l<T> a() {
        return c.a.e0.a.a(new l1(this.f2437a));
    }

    @Override // c.a.b
    public void b(c.a.c cVar) {
        this.f2437a.subscribe(new a(cVar));
    }
}
