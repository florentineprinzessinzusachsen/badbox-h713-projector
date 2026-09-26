package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableAllSingle.java */
/* JADX INFO: loaded from: classes.dex */
public final class g<T> extends c.a.u<Boolean> implements c.a.b0.c.a<Boolean> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<T> f2163a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.p<? super T> f2164b;

    /* JADX INFO: compiled from: ObservableAllSingle.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.v<? super Boolean> f2165a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.p<? super T> f2166b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f2167c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f2168d;

        a(c.a.v<? super Boolean> vVar, c.a.a0.p<? super T> pVar) {
            this.f2165a = vVar;
            this.f2166b = pVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2167c.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2168d) {
                return;
            }
            this.f2168d = true;
            this.f2165a.a(true);
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2168d) {
                c.a.e0.a.b(th);
            } else {
                this.f2168d = true;
                this.f2165a.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2168d) {
                return;
            }
            try {
                if (this.f2166b.a(t)) {
                    return;
                }
                this.f2168d = true;
                this.f2167c.dispose();
                this.f2165a.a(false);
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f2167c.dispose();
                onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2167c, bVar)) {
                this.f2167c = bVar;
                this.f2165a.onSubscribe(this);
            }
        }
    }

    public g(c.a.q<T> qVar, c.a.a0.p<? super T> pVar) {
        this.f2163a = qVar;
        this.f2164b = pVar;
    }

    @Override // c.a.b0.c.a
    public c.a.l<Boolean> a() {
        return c.a.e0.a.a(new f(this.f2163a, this.f2164b));
    }

    @Override // c.a.u
    protected void b(c.a.v<? super Boolean> vVar) {
        this.f2163a.subscribe(new a(vVar, this.f2164b));
    }
}
