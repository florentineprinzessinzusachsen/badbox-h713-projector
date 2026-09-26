package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableTakeWhile.java */
/* JADX INFO: loaded from: classes.dex */
public final class s3<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.p<? super T> f2692b;

    /* JADX INFO: compiled from: ObservableTakeWhile.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2693a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.p<? super T> f2694b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f2695c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f2696d;

        a(c.a.s<? super T> sVar, c.a.a0.p<? super T> pVar) {
            this.f2693a = sVar;
            this.f2694b = pVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2695c.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2696d) {
                return;
            }
            this.f2696d = true;
            this.f2693a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2696d) {
                c.a.e0.a.b(th);
            } else {
                this.f2696d = true;
                this.f2693a.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2696d) {
                return;
            }
            try {
                if (this.f2694b.a(t)) {
                    this.f2693a.onNext(t);
                    return;
                }
                this.f2696d = true;
                this.f2695c.dispose();
                this.f2693a.onComplete();
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f2695c.dispose();
                onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2695c, bVar)) {
                this.f2695c = bVar;
                this.f2693a.onSubscribe(this);
            }
        }
    }

    public s3(c.a.q<T> qVar, c.a.a0.p<? super T> pVar) {
        super(qVar);
        this.f2692b = pVar;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2692b));
    }
}
