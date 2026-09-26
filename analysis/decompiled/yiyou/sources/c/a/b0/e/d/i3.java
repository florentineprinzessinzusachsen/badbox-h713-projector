package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableSkipWhile.java */
/* JADX INFO: loaded from: classes.dex */
public final class i3<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.p<? super T> f2281b;

    /* JADX INFO: compiled from: ObservableSkipWhile.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2282a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.p<? super T> f2283b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f2284c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f2285d;

        a(c.a.s<? super T> sVar, c.a.a0.p<? super T> pVar) {
            this.f2282a = sVar;
            this.f2283b = pVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2284c.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2282a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2282a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2285d) {
                this.f2282a.onNext(t);
                return;
            }
            try {
                if (this.f2283b.a(t)) {
                    return;
                }
                this.f2285d = true;
                this.f2282a.onNext(t);
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f2284c.dispose();
                this.f2282a.onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2284c, bVar)) {
                this.f2284c = bVar;
                this.f2282a.onSubscribe(this);
            }
        }
    }

    public i3(c.a.q<T> qVar, c.a.a0.p<? super T> pVar) {
        super(qVar);
        this.f2281b = pVar;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2281b));
    }
}
