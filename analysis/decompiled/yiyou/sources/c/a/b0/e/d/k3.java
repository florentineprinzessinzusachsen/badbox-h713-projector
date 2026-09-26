package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableSwitchIfEmpty.java */
/* JADX INFO: loaded from: classes.dex */
public final class k3<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.q<? extends T> f2354b;

    /* JADX INFO: compiled from: ObservableSwitchIfEmpty.java */
    static final class a<T> implements c.a.s<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2355a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.q<? extends T> f2356b;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f2358d = true;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.b0.a.f f2357c = new c.a.b0.a.f();

        a(c.a.s<? super T> sVar, c.a.q<? extends T> qVar) {
            this.f2355a = sVar;
            this.f2356b = qVar;
        }

        @Override // c.a.s
        public void onComplete() {
            if (!this.f2358d) {
                this.f2355a.onComplete();
            } else {
                this.f2358d = false;
                this.f2356b.subscribe(this);
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2355a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2358d) {
                this.f2358d = false;
            }
            this.f2355a.onNext(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            this.f2357c.b(bVar);
        }
    }

    public k3(c.a.q<T> qVar, c.a.q<? extends T> qVar2) {
        super(qVar);
        this.f2354b = qVar2;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        a aVar = new a(sVar, this.f2354b);
        sVar.onSubscribe(aVar.f2357c);
        this.f1932a.subscribe(aVar);
    }
}
