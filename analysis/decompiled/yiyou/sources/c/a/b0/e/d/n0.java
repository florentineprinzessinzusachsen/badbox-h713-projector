package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableDoOnEach.java */
/* JADX INFO: loaded from: classes.dex */
public final class n0<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.f<? super T> f2465b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.f<? super Throwable> f2466c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final c.a.a0.a f2467d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final c.a.a0.a f2468e;

    /* JADX INFO: compiled from: ObservableDoOnEach.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2469a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.f<? super T> f2470b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.a0.f<? super Throwable> f2471c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.a0.a f2472d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final c.a.a0.a f2473e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        c.a.y.b f2474f;
        boolean g;

        a(c.a.s<? super T> sVar, c.a.a0.f<? super T> fVar, c.a.a0.f<? super Throwable> fVar2, c.a.a0.a aVar, c.a.a0.a aVar2) {
            this.f2469a = sVar;
            this.f2470b = fVar;
            this.f2471c = fVar2;
            this.f2472d = aVar;
            this.f2473e = aVar2;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2474f.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.g) {
                return;
            }
            try {
                this.f2472d.run();
                this.g = true;
                this.f2469a.onComplete();
                try {
                    this.f2473e.run();
                } catch (Throwable th) {
                    c.a.z.b.b(th);
                    c.a.e0.a.b(th);
                }
            } catch (Throwable th2) {
                c.a.z.b.b(th2);
                onError(th2);
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.g) {
                c.a.e0.a.b(th);
                return;
            }
            this.g = true;
            try {
                this.f2471c.a(th);
            } catch (Throwable th2) {
                c.a.z.b.b(th2);
                th = new c.a.z.a(th, th2);
            }
            this.f2469a.onError(th);
            try {
                this.f2473e.run();
            } catch (Throwable th3) {
                c.a.z.b.b(th3);
                c.a.e0.a.b(th3);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.g) {
                return;
            }
            try {
                this.f2470b.a(t);
                this.f2469a.onNext(t);
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f2474f.dispose();
                onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2474f, bVar)) {
                this.f2474f = bVar;
                this.f2469a.onSubscribe(this);
            }
        }
    }

    public n0(c.a.q<T> qVar, c.a.a0.f<? super T> fVar, c.a.a0.f<? super Throwable> fVar2, c.a.a0.a aVar, c.a.a0.a aVar2) {
        super(qVar);
        this.f2465b = fVar;
        this.f2466c = fVar2;
        this.f2467d = aVar;
        this.f2468e = aVar2;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2465b, this.f2466c, this.f2467d, this.f2468e));
    }
}
