package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: ObservableRetryBiPredicate.java */
/* JADX INFO: loaded from: classes.dex */
public final class r2<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.d<? super Integer, ? super Throwable> f2661b;

    /* JADX INFO: compiled from: ObservableRetryBiPredicate.java */
    static final class a<T> extends AtomicInteger implements c.a.s<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2662a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.b0.a.f f2663b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.q<? extends T> f2664c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.a0.d<? super Integer, ? super Throwable> f2665d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f2666e;

        a(c.a.s<? super T> sVar, c.a.a0.d<? super Integer, ? super Throwable> dVar, c.a.b0.a.f fVar, c.a.q<? extends T> qVar) {
            this.f2662a = sVar;
            this.f2663b = fVar;
            this.f2664c = qVar;
            this.f2665d = dVar;
        }

        void a() {
            if (getAndIncrement() == 0) {
                int iAddAndGet = 1;
                while (!this.f2663b.a()) {
                    this.f2664c.subscribe(this);
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                }
            }
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2662a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            try {
                c.a.a0.d<? super Integer, ? super Throwable> dVar = this.f2665d;
                int i = this.f2666e + 1;
                this.f2666e = i;
                if (dVar.a(Integer.valueOf(i), th)) {
                    a();
                } else {
                    this.f2662a.onError(th);
                }
            } catch (Throwable th2) {
                c.a.z.b.b(th2);
                this.f2662a.onError(new c.a.z.a(th, th2));
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2662a.onNext(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            this.f2663b.b(bVar);
        }
    }

    public r2(c.a.l<T> lVar, c.a.a0.d<? super Integer, ? super Throwable> dVar) {
        super(lVar);
        this.f2661b = dVar;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        c.a.b0.a.f fVar = new c.a.b0.a.f();
        sVar.onSubscribe(fVar);
        new a(sVar, this.f2661b, fVar, this.f1932a).a();
    }
}
