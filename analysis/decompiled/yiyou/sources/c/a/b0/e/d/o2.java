package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: ObservableRepeatUntil.java */
/* JADX INFO: loaded from: classes.dex */
public final class o2<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.e f2522b;

    /* JADX INFO: compiled from: ObservableRepeatUntil.java */
    static final class a<T> extends AtomicInteger implements c.a.s<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2523a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.b0.a.f f2524b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.q<? extends T> f2525c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.a0.e f2526d;

        a(c.a.s<? super T> sVar, c.a.a0.e eVar, c.a.b0.a.f fVar, c.a.q<? extends T> qVar) {
            this.f2523a = sVar;
            this.f2524b = fVar;
            this.f2525c = qVar;
            this.f2526d = eVar;
        }

        void a() {
            if (getAndIncrement() == 0) {
                int iAddAndGet = 1;
                do {
                    this.f2525c.subscribe(this);
                    iAddAndGet = addAndGet(-iAddAndGet);
                } while (iAddAndGet != 0);
            }
        }

        @Override // c.a.s
        public void onComplete() {
            try {
                if (this.f2526d.a()) {
                    this.f2523a.onComplete();
                } else {
                    a();
                }
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f2523a.onError(th);
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2523a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2523a.onNext(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            this.f2524b.a(bVar);
        }
    }

    public o2(c.a.l<T> lVar, c.a.a0.e eVar) {
        super(lVar);
        this.f2522b = eVar;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        c.a.b0.a.f fVar = new c.a.b0.a.f();
        sVar.onSubscribe(fVar);
        new a(sVar, this.f2522b, fVar, this.f1932a).a();
    }
}
