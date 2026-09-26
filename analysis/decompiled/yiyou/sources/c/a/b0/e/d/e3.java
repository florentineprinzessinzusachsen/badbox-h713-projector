package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableSkip.java */
/* JADX INFO: loaded from: classes.dex */
public final class e3<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f2102b;

    /* JADX INFO: compiled from: ObservableSkip.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2103a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        long f2104b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f2105c;

        a(c.a.s<? super T> sVar, long j) {
            this.f2103a = sVar;
            this.f2104b = j;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2105c.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2103a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2103a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            long j = this.f2104b;
            if (j != 0) {
                this.f2104b = j - 1;
            } else {
                this.f2103a.onNext(t);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2105c, bVar)) {
                this.f2105c = bVar;
                this.f2103a.onSubscribe(this);
            }
        }
    }

    public e3(c.a.q<T> qVar, long j) {
        super(qVar);
        this.f2102b = j;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2102b));
    }
}
