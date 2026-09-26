package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableReduceSeedSingle.java */
/* JADX INFO: loaded from: classes.dex */
public final class k2<T, R> extends c.a.u<R> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<T> f2347a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final R f2348b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.c<R, ? super T, R> f2349c;

    /* JADX INFO: compiled from: ObservableReduceSeedSingle.java */
    static final class a<T, R> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.v<? super R> f2350a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.c<R, ? super T, R> f2351b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        R f2352c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        c.a.y.b f2353d;

        a(c.a.v<? super R> vVar, c.a.a0.c<R, ? super T, R> cVar, R r) {
            this.f2350a = vVar;
            this.f2352c = r;
            this.f2351b = cVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2353d.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            R r = this.f2352c;
            if (r != null) {
                this.f2352c = null;
                this.f2350a.a(r);
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2352c == null) {
                c.a.e0.a.b(th);
            } else {
                this.f2352c = null;
                this.f2350a.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            R r = this.f2352c;
            if (r != null) {
                try {
                    R rA = this.f2351b.a(r, t);
                    c.a.b0.b.b.a(rA, "The reducer returned a null value");
                    this.f2352c = rA;
                } catch (Throwable th) {
                    c.a.z.b.b(th);
                    this.f2353d.dispose();
                    onError(th);
                }
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2353d, bVar)) {
                this.f2353d = bVar;
                this.f2350a.onSubscribe(this);
            }
        }
    }

    public k2(c.a.q<T> qVar, R r, c.a.a0.c<R, ? super T, R> cVar) {
        this.f2347a = qVar;
        this.f2348b = r;
        this.f2349c = cVar;
    }

    @Override // c.a.u
    protected void b(c.a.v<? super R> vVar) {
        this.f2347a.subscribe(new a(vVar, this.f2349c, this.f2348b));
    }
}
