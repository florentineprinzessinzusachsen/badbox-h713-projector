package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableOnErrorNext.java */
/* JADX INFO: loaded from: classes.dex */
public final class d2<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.n<? super Throwable, ? extends c.a.q<? extends T>> f2051b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f2052c;

    /* JADX INFO: compiled from: ObservableOnErrorNext.java */
    static final class a<T> implements c.a.s<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2053a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.n<? super Throwable, ? extends c.a.q<? extends T>> f2054b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final boolean f2055c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.b0.a.f f2056d = new c.a.b0.a.f();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f2057e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        boolean f2058f;

        a(c.a.s<? super T> sVar, c.a.a0.n<? super Throwable, ? extends c.a.q<? extends T>> nVar, boolean z) {
            this.f2053a = sVar;
            this.f2054b = nVar;
            this.f2055c = z;
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2058f) {
                return;
            }
            this.f2058f = true;
            this.f2057e = true;
            this.f2053a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2057e) {
                if (this.f2058f) {
                    c.a.e0.a.b(th);
                    return;
                } else {
                    this.f2053a.onError(th);
                    return;
                }
            }
            this.f2057e = true;
            if (this.f2055c && !(th instanceof Exception)) {
                this.f2053a.onError(th);
                return;
            }
            try {
                c.a.q<? extends T> qVarApply = this.f2054b.apply(th);
                if (qVarApply != null) {
                    qVarApply.subscribe(this);
                    return;
                }
                NullPointerException nullPointerException = new NullPointerException("Observable is null");
                nullPointerException.initCause(th);
                this.f2053a.onError(nullPointerException);
            } catch (Throwable th2) {
                c.a.z.b.b(th2);
                this.f2053a.onError(new c.a.z.a(th, th2));
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2058f) {
                return;
            }
            this.f2053a.onNext(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            this.f2056d.a(bVar);
        }
    }

    public d2(c.a.q<T> qVar, c.a.a0.n<? super Throwable, ? extends c.a.q<? extends T>> nVar, boolean z) {
        super(qVar);
        this.f2051b = nVar;
        this.f2052c = z;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        a aVar = new a(sVar, this.f2051b, this.f2052c);
        sVar.onSubscribe(aVar.f2056d);
        this.f1932a.subscribe(aVar);
    }
}
