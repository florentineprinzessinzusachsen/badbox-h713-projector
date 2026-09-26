package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableCountSingle.java */
/* JADX INFO: loaded from: classes.dex */
public final class a0<T> extends c.a.u<Long> implements c.a.b0.c.a<Long> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<T> f1933a;

    /* JADX INFO: compiled from: ObservableCountSingle.java */
    static final class a implements c.a.s<Object>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.v<? super Long> f1934a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c.a.y.b f1935b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f1936c;

        a(c.a.v<? super Long> vVar) {
            this.f1934a = vVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f1935b.dispose();
            this.f1935b = c.a.b0.a.c.DISPOSED;
        }

        @Override // c.a.s
        public void onComplete() {
            this.f1935b = c.a.b0.a.c.DISPOSED;
            this.f1934a.a(Long.valueOf(this.f1936c));
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f1935b = c.a.b0.a.c.DISPOSED;
            this.f1934a.onError(th);
        }

        @Override // c.a.s
        public void onNext(Object obj) {
            this.f1936c++;
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f1935b, bVar)) {
                this.f1935b = bVar;
                this.f1934a.onSubscribe(this);
            }
        }
    }

    public a0(c.a.q<T> qVar) {
        this.f1933a = qVar;
    }

    @Override // c.a.b0.c.a
    public c.a.l<Long> a() {
        return c.a.e0.a.a(new z(this.f1933a));
    }

    @Override // c.a.u
    public void b(c.a.v<? super Long> vVar) {
        this.f1933a.subscribe(new a(vVar));
    }
}
