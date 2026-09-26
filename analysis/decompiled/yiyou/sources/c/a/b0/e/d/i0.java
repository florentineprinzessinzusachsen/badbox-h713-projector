package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableDetach.java */
/* JADX INFO: loaded from: classes.dex */
public final class i0<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: compiled from: ObservableDetach.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        c.a.s<? super T> f2256a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c.a.y.b f2257b;

        a(c.a.s<? super T> sVar) {
            this.f2256a = sVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.y.b bVar = this.f2257b;
            this.f2257b = c.a.b0.j.g.INSTANCE;
            this.f2256a = c.a.b0.j.g.a();
            bVar.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            c.a.s<? super T> sVar = this.f2256a;
            this.f2257b = c.a.b0.j.g.INSTANCE;
            this.f2256a = c.a.b0.j.g.a();
            sVar.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            c.a.s<? super T> sVar = this.f2256a;
            this.f2257b = c.a.b0.j.g.INSTANCE;
            this.f2256a = c.a.b0.j.g.a();
            sVar.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2256a.onNext(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2257b, bVar)) {
                this.f2257b = bVar;
                this.f2256a.onSubscribe(this);
            }
        }
    }

    public i0(c.a.q<T> qVar) {
        super(qVar);
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar));
    }
}
