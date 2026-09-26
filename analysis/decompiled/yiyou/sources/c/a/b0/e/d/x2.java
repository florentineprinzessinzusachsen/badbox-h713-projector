package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableScan.java */
/* JADX INFO: loaded from: classes.dex */
public final class x2<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.c<T, T, T> f2875b;

    /* JADX INFO: compiled from: ObservableScan.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2876a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.c<T, T, T> f2877b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f2878c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        T f2879d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f2880e;

        a(c.a.s<? super T> sVar, c.a.a0.c<T, T, T> cVar) {
            this.f2876a = sVar;
            this.f2877b = cVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2878c.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2880e) {
                return;
            }
            this.f2880e = true;
            this.f2876a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2880e) {
                c.a.e0.a.b(th);
            } else {
                this.f2880e = true;
                this.f2876a.onError(th);
            }
        }

        /* JADX WARN: Type inference failed for: r4v2, types: [T, java.lang.Object] */
        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2880e) {
                return;
            }
            c.a.s<? super T> sVar = this.f2876a;
            T t2 = this.f2879d;
            if (t2 == null) {
                this.f2879d = t;
                sVar.onNext(t);
                return;
            }
            try {
                T tA = this.f2877b.a(t2, t);
                c.a.b0.b.b.a((Object) tA, "The value returned by the accumulator is null");
                this.f2879d = tA;
                sVar.onNext(tA);
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f2878c.dispose();
                onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2878c, bVar)) {
                this.f2878c = bVar;
                this.f2876a.onSubscribe(this);
            }
        }
    }

    public x2(c.a.q<T> qVar, c.a.a0.c<T, T, T> cVar) {
        super(qVar);
        this.f2875b = cVar;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2875b));
    }
}
