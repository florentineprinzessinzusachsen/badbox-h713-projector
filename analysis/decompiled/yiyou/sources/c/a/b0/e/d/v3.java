package c.a.b0.e.d;

import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: ObservableTimeInterval.java */
/* JADX INFO: loaded from: classes.dex */
public final class v3<T> extends c.a.b0.e.d.a<T, c.a.f0.c<T>> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.t f2812b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final TimeUnit f2813c;

    /* JADX INFO: compiled from: ObservableTimeInterval.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super c.a.f0.c<T>> f2814a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final TimeUnit f2815b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.t f2816c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        long f2817d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        c.a.y.b f2818e;

        a(c.a.s<? super c.a.f0.c<T>> sVar, TimeUnit timeUnit, c.a.t tVar) {
            this.f2814a = sVar;
            this.f2816c = tVar;
            this.f2815b = timeUnit;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2818e.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2814a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2814a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            long jA = this.f2816c.a(this.f2815b);
            long j = this.f2817d;
            this.f2817d = jA;
            this.f2814a.onNext(new c.a.f0.c(t, jA - j, this.f2815b));
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2818e, bVar)) {
                this.f2818e = bVar;
                this.f2817d = this.f2816c.a(this.f2815b);
                this.f2814a.onSubscribe(this);
            }
        }
    }

    public v3(c.a.q<T> qVar, TimeUnit timeUnit, c.a.t tVar) {
        super(qVar);
        this.f2812b = tVar;
        this.f2813c = timeUnit;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super c.a.f0.c<T>> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2813c, this.f2812b));
    }
}
