package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableDematerialize.java */
/* JADX INFO: loaded from: classes.dex */
public final class h0<T> extends c.a.b0.e.d.a<c.a.k<T>, T> {

    /* JADX INFO: compiled from: ObservableDematerialize.java */
    static final class a<T> implements c.a.s<c.a.k<T>>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2212a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f2213b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f2214c;

        a(c.a.s<? super T> sVar) {
            this.f2212a = sVar;
        }

        @Override // c.a.s
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onNext(c.a.k<T> kVar) {
            if (this.f2213b) {
                if (kVar.d()) {
                    c.a.e0.a.b(kVar.a());
                }
            } else if (kVar.d()) {
                this.f2214c.dispose();
                onError(kVar.a());
            } else if (!kVar.c()) {
                this.f2212a.onNext(kVar.b());
            } else {
                this.f2214c.dispose();
                onComplete();
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2214c.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2213b) {
                return;
            }
            this.f2213b = true;
            this.f2212a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2213b) {
                c.a.e0.a.b(th);
            } else {
                this.f2213b = true;
                this.f2212a.onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2214c, bVar)) {
                this.f2214c = bVar;
                this.f2212a.onSubscribe(this);
            }
        }
    }

    public h0(c.a.q<c.a.k<T>> qVar) {
        super(qVar);
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar));
    }
}
