package c.a.b0.e.d;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: ObservableCollectSingle.java */
/* JADX INFO: loaded from: classes.dex */
public final class s<T, U> extends c.a.u<U> implements c.a.b0.c.a<U> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<T> f2672a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Callable<? extends U> f2673b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.b<? super U, ? super T> f2674c;

    /* JADX INFO: compiled from: ObservableCollectSingle.java */
    static final class a<T, U> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.v<? super U> f2675a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.b<? super U, ? super T> f2676b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final U f2677c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        c.a.y.b f2678d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f2679e;

        a(c.a.v<? super U> vVar, U u, c.a.a0.b<? super U, ? super T> bVar) {
            this.f2675a = vVar;
            this.f2676b = bVar;
            this.f2677c = u;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2678d.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2679e) {
                return;
            }
            this.f2679e = true;
            this.f2675a.a(this.f2677c);
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2679e) {
                c.a.e0.a.b(th);
            } else {
                this.f2679e = true;
                this.f2675a.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2679e) {
                return;
            }
            try {
                this.f2676b.a(this.f2677c, t);
            } catch (Throwable th) {
                this.f2678d.dispose();
                onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2678d, bVar)) {
                this.f2678d = bVar;
                this.f2675a.onSubscribe(this);
            }
        }
    }

    public s(c.a.q<T> qVar, Callable<? extends U> callable, c.a.a0.b<? super U, ? super T> bVar) {
        this.f2672a = qVar;
        this.f2673b = callable;
        this.f2674c = bVar;
    }

    @Override // c.a.b0.c.a
    public c.a.l<U> a() {
        return c.a.e0.a.a(new r(this.f2672a, this.f2673b, this.f2674c));
    }

    @Override // c.a.u
    protected void b(c.a.v<? super U> vVar) {
        try {
            U uCall = this.f2673b.call();
            c.a.b0.b.b.a(uCall, "The initialSupplier returned a null value");
            this.f2672a.subscribe(new a(vVar, uCall, this.f2674c));
        } catch (Throwable th) {
            c.a.b0.a.d.a(th, vVar);
        }
    }
}
