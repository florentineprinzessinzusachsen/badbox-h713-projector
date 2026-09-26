package c.a.b0.e.d;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: ObservableCollect.java */
/* JADX INFO: loaded from: classes.dex */
public final class r<T, U> extends c.a.b0.e.d.a<T, U> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Callable<? extends U> f2644b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.b<? super U, ? super T> f2645c;

    /* JADX INFO: compiled from: ObservableCollect.java */
    static final class a<T, U> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super U> f2646a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.b<? super U, ? super T> f2647b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final U f2648c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        c.a.y.b f2649d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f2650e;

        a(c.a.s<? super U> sVar, U u, c.a.a0.b<? super U, ? super T> bVar) {
            this.f2646a = sVar;
            this.f2647b = bVar;
            this.f2648c = u;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2649d.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2650e) {
                return;
            }
            this.f2650e = true;
            this.f2646a.onNext(this.f2648c);
            this.f2646a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2650e) {
                c.a.e0.a.b(th);
            } else {
                this.f2650e = true;
                this.f2646a.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2650e) {
                return;
            }
            try {
                this.f2647b.a(this.f2648c, t);
            } catch (Throwable th) {
                this.f2649d.dispose();
                onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2649d, bVar)) {
                this.f2649d = bVar;
                this.f2646a.onSubscribe(this);
            }
        }
    }

    public r(c.a.q<T> qVar, Callable<? extends U> callable, c.a.a0.b<? super U, ? super T> bVar) {
        super(qVar);
        this.f2644b = callable;
        this.f2645c = bVar;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super U> sVar) {
        try {
            U uCall = this.f2644b.call();
            c.a.b0.b.b.a(uCall, "The initialSupplier returned a null value");
            this.f1932a.subscribe(new a(sVar, uCall, this.f2645c));
        } catch (Throwable th) {
            c.a.b0.a.d.a(th, sVar);
        }
    }
}
