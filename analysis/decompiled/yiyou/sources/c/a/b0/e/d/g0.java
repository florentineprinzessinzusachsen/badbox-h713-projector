package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableDelaySubscriptionOther.java */
/* JADX INFO: loaded from: classes.dex */
public final class g0<T, U> extends c.a.l<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<? extends T> f2169a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.q<U> f2170b;

    /* JADX INFO: compiled from: ObservableDelaySubscriptionOther.java */
    final class a implements c.a.s<U> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.b0.a.f f2171a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.s<? super T> f2172b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f2173c;

        /* JADX INFO: renamed from: c.a.b0.e.d.g0$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableDelaySubscriptionOther.java */
        final class C0058a implements c.a.s<T> {
            C0058a() {
            }

            @Override // c.a.s
            public void onComplete() {
                a.this.f2172b.onComplete();
            }

            @Override // c.a.s
            public void onError(Throwable th) {
                a.this.f2172b.onError(th);
            }

            @Override // c.a.s
            public void onNext(T t) {
                a.this.f2172b.onNext(t);
            }

            @Override // c.a.s
            public void onSubscribe(c.a.y.b bVar) {
                a.this.f2171a.b(bVar);
            }
        }

        a(c.a.b0.a.f fVar, c.a.s<? super T> sVar) {
            this.f2171a = fVar;
            this.f2172b = sVar;
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2173c) {
                return;
            }
            this.f2173c = true;
            g0.this.f2169a.subscribe(new C0058a());
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2173c) {
                c.a.e0.a.b(th);
            } else {
                this.f2173c = true;
                this.f2172b.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(U u) {
            onComplete();
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            this.f2171a.b(bVar);
        }
    }

    public g0(c.a.q<? extends T> qVar, c.a.q<U> qVar2) {
        this.f2169a = qVar;
        this.f2170b = qVar2;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        c.a.b0.a.f fVar = new c.a.b0.a.f();
        sVar.onSubscribe(fVar);
        this.f2170b.subscribe(new a(fVar, sVar));
    }
}
