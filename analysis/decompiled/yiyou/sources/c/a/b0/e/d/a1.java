package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableFlattenIterable.java */
/* JADX INFO: loaded from: classes.dex */
public final class a1<T, R> extends c.a.b0.e.d.a<T, R> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.n<? super T, ? extends Iterable<? extends R>> f1937b;

    /* JADX INFO: compiled from: ObservableFlattenIterable.java */
    static final class a<T, R> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super R> f1938a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.n<? super T, ? extends Iterable<? extends R>> f1939b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f1940c;

        a(c.a.s<? super R> sVar, c.a.a0.n<? super T, ? extends Iterable<? extends R>> nVar) {
            this.f1938a = sVar;
            this.f1939b = nVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f1940c.dispose();
            this.f1940c = c.a.b0.a.c.DISPOSED;
        }

        @Override // c.a.s
        public void onComplete() {
            c.a.y.b bVar = this.f1940c;
            c.a.b0.a.c cVar = c.a.b0.a.c.DISPOSED;
            if (bVar == cVar) {
                return;
            }
            this.f1940c = cVar;
            this.f1938a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            c.a.y.b bVar = this.f1940c;
            c.a.b0.a.c cVar = c.a.b0.a.c.DISPOSED;
            if (bVar == cVar) {
                c.a.e0.a.b(th);
            } else {
                this.f1940c = cVar;
                this.f1938a.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f1940c == c.a.b0.a.c.DISPOSED) {
                return;
            }
            try {
                c.a.s<? super R> sVar = this.f1938a;
                for (R r : this.f1939b.apply(t)) {
                    try {
                        try {
                            c.a.b0.b.b.a(r, "The iterator returned a null value");
                            sVar.onNext(r);
                        } catch (Throwable th) {
                            c.a.z.b.b(th);
                            this.f1940c.dispose();
                            onError(th);
                            return;
                        }
                    } catch (Throwable th2) {
                        c.a.z.b.b(th2);
                        this.f1940c.dispose();
                        onError(th2);
                        return;
                    }
                }
            } catch (Throwable th3) {
                c.a.z.b.b(th3);
                this.f1940c.dispose();
                onError(th3);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f1940c, bVar)) {
                this.f1940c = bVar;
                this.f1938a.onSubscribe(this);
            }
        }
    }

    public a1(c.a.q<T> qVar, c.a.a0.n<? super T, ? extends Iterable<? extends R>> nVar) {
        super(qVar);
        this.f1937b = nVar;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super R> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f1937b));
    }
}
