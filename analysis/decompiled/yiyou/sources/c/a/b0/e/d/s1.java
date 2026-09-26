package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableLastMaybe.java */
/* JADX INFO: loaded from: classes.dex */
public final class s1<T> extends c.a.h<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<T> f2681a;

    /* JADX INFO: compiled from: ObservableLastMaybe.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.i<? super T> f2682a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c.a.y.b f2683b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        T f2684c;

        a(c.a.i<? super T> iVar) {
            this.f2682a = iVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2683b.dispose();
            this.f2683b = c.a.b0.a.c.DISPOSED;
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2683b = c.a.b0.a.c.DISPOSED;
            T t = this.f2684c;
            if (t == null) {
                this.f2682a.onComplete();
            } else {
                this.f2684c = null;
                this.f2682a.a(t);
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2683b = c.a.b0.a.c.DISPOSED;
            this.f2684c = null;
            this.f2682a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2684c = t;
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2683b, bVar)) {
                this.f2683b = bVar;
                this.f2682a.onSubscribe(this);
            }
        }
    }

    public s1(c.a.q<T> qVar) {
        this.f2681a = qVar;
    }

    @Override // c.a.h
    protected void b(c.a.i<? super T> iVar) {
        this.f2681a.subscribe(new a(iVar));
    }
}
