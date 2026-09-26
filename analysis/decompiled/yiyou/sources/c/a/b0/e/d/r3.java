package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableTakeUntilPredicate.java */
/* JADX INFO: loaded from: classes.dex */
public final class r3<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.p<? super T> f2667b;

    /* JADX INFO: compiled from: ObservableTakeUntilPredicate.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2668a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.p<? super T> f2669b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f2670c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f2671d;

        a(c.a.s<? super T> sVar, c.a.a0.p<? super T> pVar) {
            this.f2668a = sVar;
            this.f2669b = pVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2670c.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2671d) {
                return;
            }
            this.f2671d = true;
            this.f2668a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2671d) {
                c.a.e0.a.b(th);
            } else {
                this.f2671d = true;
                this.f2668a.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2671d) {
                return;
            }
            this.f2668a.onNext(t);
            try {
                if (this.f2669b.a(t)) {
                    this.f2671d = true;
                    this.f2670c.dispose();
                    this.f2668a.onComplete();
                }
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f2670c.dispose();
                onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2670c, bVar)) {
                this.f2670c = bVar;
                this.f2668a.onSubscribe(this);
            }
        }
    }

    public r3(c.a.q<T> qVar, c.a.a0.p<? super T> pVar) {
        super(qVar);
        this.f2667b = pVar;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2667b));
    }
}
