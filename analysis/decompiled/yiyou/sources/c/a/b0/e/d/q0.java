package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableElementAtMaybe.java */
/* JADX INFO: loaded from: classes.dex */
public final class q0<T> extends c.a.h<T> implements c.a.b0.c.a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<T> f2585a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f2586b;

    /* JADX INFO: compiled from: ObservableElementAtMaybe.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.i<? super T> f2587a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f2588b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f2589c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        long f2590d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f2591e;

        a(c.a.i<? super T> iVar, long j) {
            this.f2587a = iVar;
            this.f2588b = j;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2589c.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2591e) {
                return;
            }
            this.f2591e = true;
            this.f2587a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2591e) {
                c.a.e0.a.b(th);
            } else {
                this.f2591e = true;
                this.f2587a.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2591e) {
                return;
            }
            long j = this.f2590d;
            if (j != this.f2588b) {
                this.f2590d = j + 1;
                return;
            }
            this.f2591e = true;
            this.f2589c.dispose();
            this.f2587a.a(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2589c, bVar)) {
                this.f2589c = bVar;
                this.f2587a.onSubscribe(this);
            }
        }
    }

    public q0(c.a.q<T> qVar, long j) {
        this.f2585a = qVar;
        this.f2586b = j;
    }

    @Override // c.a.b0.c.a
    public c.a.l<T> a() {
        return c.a.e0.a.a(new p0(this.f2585a, this.f2586b, null, false));
    }

    @Override // c.a.h
    public void b(c.a.i<? super T> iVar) {
        this.f2585a.subscribe(new a(iVar, this.f2586b));
    }
}
