package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableReduceMaybe.java */
/* JADX INFO: loaded from: classes.dex */
public final class j2<T> extends c.a.h<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<T> f2317a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.c<T, T, T> f2318b;

    /* JADX INFO: compiled from: ObservableReduceMaybe.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.i<? super T> f2319a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.c<T, T, T> f2320b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f2321c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        T f2322d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        c.a.y.b f2323e;

        a(c.a.i<? super T> iVar, c.a.a0.c<T, T, T> cVar) {
            this.f2319a = iVar;
            this.f2320b = cVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2323e.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2321c) {
                return;
            }
            this.f2321c = true;
            T t = this.f2322d;
            this.f2322d = null;
            if (t != null) {
                this.f2319a.a(t);
            } else {
                this.f2319a.onComplete();
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2321c) {
                c.a.e0.a.b(th);
                return;
            }
            this.f2321c = true;
            this.f2322d = null;
            this.f2319a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2321c) {
                return;
            }
            T t2 = this.f2322d;
            if (t2 == null) {
                this.f2322d = t;
                return;
            }
            try {
                T tA = this.f2320b.a(t2, t);
                c.a.b0.b.b.a((Object) tA, "The reducer returned a null value");
                this.f2322d = tA;
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f2323e.dispose();
                onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2323e, bVar)) {
                this.f2323e = bVar;
                this.f2319a.onSubscribe(this);
            }
        }
    }

    public j2(c.a.q<T> qVar, c.a.a0.c<T, T, T> cVar) {
        this.f2317a = qVar;
        this.f2318b = cVar;
    }

    @Override // c.a.h
    protected void b(c.a.i<? super T> iVar) {
        this.f2317a.subscribe(new a(iVar, this.f2318b));
    }
}
