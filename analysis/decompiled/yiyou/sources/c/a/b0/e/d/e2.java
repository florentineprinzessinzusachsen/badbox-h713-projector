package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableOnErrorReturn.java */
/* JADX INFO: loaded from: classes.dex */
public final class e2<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.n<? super Throwable, ? extends T> f2098b;

    /* JADX INFO: compiled from: ObservableOnErrorReturn.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2099a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.n<? super Throwable, ? extends T> f2100b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f2101c;

        a(c.a.s<? super T> sVar, c.a.a0.n<? super Throwable, ? extends T> nVar) {
            this.f2099a = sVar;
            this.f2100b = nVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2101c.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2099a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            try {
                T tApply = this.f2100b.apply(th);
                if (tApply != null) {
                    this.f2099a.onNext(tApply);
                    this.f2099a.onComplete();
                } else {
                    NullPointerException nullPointerException = new NullPointerException("The supplied value is null");
                    nullPointerException.initCause(th);
                    this.f2099a.onError(nullPointerException);
                }
            } catch (Throwable th2) {
                c.a.z.b.b(th2);
                this.f2099a.onError(new c.a.z.a(th, th2));
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2099a.onNext(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2101c, bVar)) {
                this.f2101c = bVar;
                this.f2099a.onSubscribe(this);
            }
        }
    }

    public e2(c.a.q<T> qVar, c.a.a0.n<? super Throwable, ? extends T> nVar) {
        super(qVar);
        this.f2098b = nVar;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2098b));
    }
}
