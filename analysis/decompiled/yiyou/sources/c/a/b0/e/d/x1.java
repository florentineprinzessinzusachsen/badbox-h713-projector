package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableMaterialize.java */
/* JADX INFO: loaded from: classes.dex */
public final class x1<T> extends c.a.b0.e.d.a<T, c.a.k<T>> {

    /* JADX INFO: compiled from: ObservableMaterialize.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super c.a.k<T>> f2873a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c.a.y.b f2874b;

        a(c.a.s<? super c.a.k<T>> sVar) {
            this.f2873a = sVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2874b.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2873a.onNext(c.a.k.f());
            this.f2873a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2873a.onNext(c.a.k.a(th));
            this.f2873a.onComplete();
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2873a.onNext(c.a.k.a(t));
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2874b, bVar)) {
                this.f2874b = bVar;
                this.f2873a.onSubscribe(this);
            }
        }
    }

    public x1(c.a.q<T> qVar) {
        super(qVar);
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super c.a.k<T>> sVar) {
        this.f1932a.subscribe(new a(sVar));
    }
}
