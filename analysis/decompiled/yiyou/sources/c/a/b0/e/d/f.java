package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableAll.java */
/* JADX INFO: loaded from: classes.dex */
public final class f<T> extends c.a.b0.e.d.a<T, Boolean> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.p<? super T> f2116b;

    /* JADX INFO: compiled from: ObservableAll.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super Boolean> f2117a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.p<? super T> f2118b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f2119c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f2120d;

        a(c.a.s<? super Boolean> sVar, c.a.a0.p<? super T> pVar) {
            this.f2117a = sVar;
            this.f2118b = pVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2119c.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2120d) {
                return;
            }
            this.f2120d = true;
            this.f2117a.onNext(true);
            this.f2117a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2120d) {
                c.a.e0.a.b(th);
            } else {
                this.f2120d = true;
                this.f2117a.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2120d) {
                return;
            }
            try {
                if (this.f2118b.a(t)) {
                    return;
                }
                this.f2120d = true;
                this.f2119c.dispose();
                this.f2117a.onNext(false);
                this.f2117a.onComplete();
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f2119c.dispose();
                onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2119c, bVar)) {
                this.f2119c = bVar;
                this.f2117a.onSubscribe(this);
            }
        }
    }

    public f(c.a.q<T> qVar, c.a.a0.p<? super T> pVar) {
        super(qVar);
        this.f2116b = pVar;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super Boolean> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2116b));
    }
}
