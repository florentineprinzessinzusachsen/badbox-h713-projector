package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: ObservableRetryPredicate.java */
/* JADX INFO: loaded from: classes.dex */
public final class s2<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.p<? super Throwable> f2685b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final long f2686c;

    /* JADX INFO: compiled from: ObservableRetryPredicate.java */
    static final class a<T> extends AtomicInteger implements c.a.s<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2687a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.b0.a.f f2688b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.q<? extends T> f2689c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.a0.p<? super Throwable> f2690d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        long f2691e;

        a(c.a.s<? super T> sVar, long j, c.a.a0.p<? super Throwable> pVar, c.a.b0.a.f fVar, c.a.q<? extends T> qVar) {
            this.f2687a = sVar;
            this.f2688b = fVar;
            this.f2689c = qVar;
            this.f2690d = pVar;
            this.f2691e = j;
        }

        void a() {
            if (getAndIncrement() == 0) {
                int iAddAndGet = 1;
                while (!this.f2688b.a()) {
                    this.f2689c.subscribe(this);
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                }
            }
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2687a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            long j = this.f2691e;
            if (j != Long.MAX_VALUE) {
                this.f2691e = j - 1;
            }
            if (j == 0) {
                this.f2687a.onError(th);
                return;
            }
            try {
                if (this.f2690d.a(th)) {
                    a();
                } else {
                    this.f2687a.onError(th);
                }
            } catch (Throwable th2) {
                c.a.z.b.b(th2);
                this.f2687a.onError(new c.a.z.a(th, th2));
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2687a.onNext(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            this.f2688b.b(bVar);
        }
    }

    public s2(c.a.l<T> lVar, long j, c.a.a0.p<? super Throwable> pVar) {
        super(lVar);
        this.f2685b = pVar;
        this.f2686c = j;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        c.a.b0.a.f fVar = new c.a.b0.a.f();
        sVar.onSubscribe(fVar);
        new a(sVar, this.f2686c, this.f2685b, fVar, this.f1932a).a();
    }
}
