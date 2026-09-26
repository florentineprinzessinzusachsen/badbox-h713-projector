package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableAny.java */
/* JADX INFO: loaded from: classes.dex */
public final class i<T> extends c.a.b0.e.d.a<T, Boolean> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.p<? super T> f2251b;

    /* JADX INFO: compiled from: ObservableAny.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super Boolean> f2252a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.p<? super T> f2253b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f2254c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f2255d;

        a(c.a.s<? super Boolean> sVar, c.a.a0.p<? super T> pVar) {
            this.f2252a = sVar;
            this.f2253b = pVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2254c.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2255d) {
                return;
            }
            this.f2255d = true;
            this.f2252a.onNext(false);
            this.f2252a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2255d) {
                c.a.e0.a.b(th);
            } else {
                this.f2255d = true;
                this.f2252a.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2255d) {
                return;
            }
            try {
                if (this.f2253b.a(t)) {
                    this.f2255d = true;
                    this.f2254c.dispose();
                    this.f2252a.onNext(true);
                    this.f2252a.onComplete();
                }
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f2254c.dispose();
                onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2254c, bVar)) {
                this.f2254c = bVar;
                this.f2252a.onSubscribe(this);
            }
        }
    }

    public i(c.a.q<T> qVar, c.a.a0.p<? super T> pVar) {
        super(qVar);
        this.f2251b = pVar;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super Boolean> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2251b));
    }
}
