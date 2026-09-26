package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableAnySingle.java */
/* JADX INFO: loaded from: classes.dex */
public final class j<T> extends c.a.u<Boolean> implements c.a.b0.c.a<Boolean> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<T> f2293a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.p<? super T> f2294b;

    /* JADX INFO: compiled from: ObservableAnySingle.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.v<? super Boolean> f2295a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.p<? super T> f2296b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f2297c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f2298d;

        a(c.a.v<? super Boolean> vVar, c.a.a0.p<? super T> pVar) {
            this.f2295a = vVar;
            this.f2296b = pVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2297c.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2298d) {
                return;
            }
            this.f2298d = true;
            this.f2295a.a(false);
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2298d) {
                c.a.e0.a.b(th);
            } else {
                this.f2298d = true;
                this.f2295a.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2298d) {
                return;
            }
            try {
                if (this.f2296b.a(t)) {
                    this.f2298d = true;
                    this.f2297c.dispose();
                    this.f2295a.a(true);
                }
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f2297c.dispose();
                onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2297c, bVar)) {
                this.f2297c = bVar;
                this.f2295a.onSubscribe(this);
            }
        }
    }

    public j(c.a.q<T> qVar, c.a.a0.p<? super T> pVar) {
        this.f2293a = qVar;
        this.f2294b = pVar;
    }

    @Override // c.a.b0.c.a
    public c.a.l<Boolean> a() {
        return c.a.e0.a.a(new i(this.f2293a, this.f2294b));
    }

    @Override // c.a.u
    protected void b(c.a.v<? super Boolean> vVar) {
        this.f2293a.subscribe(new a(vVar, this.f2294b));
    }
}
