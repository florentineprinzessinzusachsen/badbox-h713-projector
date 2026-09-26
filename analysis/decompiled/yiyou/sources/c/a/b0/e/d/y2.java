package c.a.b0.e.d;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: ObservableScanSeed.java */
/* JADX INFO: loaded from: classes.dex */
public final class y2<T, R> extends c.a.b0.e.d.a<T, R> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.c<R, ? super T, R> f2922b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Callable<R> f2923c;

    /* JADX INFO: compiled from: ObservableScanSeed.java */
    static final class a<T, R> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super R> f2924a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.c<R, ? super T, R> f2925b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        R f2926c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        c.a.y.b f2927d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f2928e;

        a(c.a.s<? super R> sVar, c.a.a0.c<R, ? super T, R> cVar, R r) {
            this.f2924a = sVar;
            this.f2925b = cVar;
            this.f2926c = r;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2927d.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2928e) {
                return;
            }
            this.f2928e = true;
            this.f2924a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2928e) {
                c.a.e0.a.b(th);
            } else {
                this.f2928e = true;
                this.f2924a.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2928e) {
                return;
            }
            try {
                R rA = this.f2925b.a(this.f2926c, t);
                c.a.b0.b.b.a(rA, "The accumulator returned a null value");
                this.f2926c = rA;
                this.f2924a.onNext(rA);
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f2927d.dispose();
                onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2927d, bVar)) {
                this.f2927d = bVar;
                this.f2924a.onSubscribe(this);
                this.f2924a.onNext(this.f2926c);
            }
        }
    }

    public y2(c.a.q<T> qVar, Callable<R> callable, c.a.a0.c<R, ? super T, R> cVar) {
        super(qVar);
        this.f2922b = cVar;
        this.f2923c = callable;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super R> sVar) {
        try {
            R rCall = this.f2923c.call();
            c.a.b0.b.b.a(rCall, "The seed supplied is null");
            this.f1932a.subscribe(new a(sVar, this.f2922b, rCall));
        } catch (Throwable th) {
            c.a.z.b.b(th);
            c.a.b0.a.d.a(th, sVar);
        }
    }
}
