package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: ObservableRepeat.java */
/* JADX INFO: loaded from: classes.dex */
public final class n2<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f2501b;

    /* JADX INFO: compiled from: ObservableRepeat.java */
    static final class a<T> extends AtomicInteger implements c.a.s<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2502a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.b0.a.f f2503b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.q<? extends T> f2504c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        long f2505d;

        a(c.a.s<? super T> sVar, long j, c.a.b0.a.f fVar, c.a.q<? extends T> qVar) {
            this.f2502a = sVar;
            this.f2503b = fVar;
            this.f2504c = qVar;
            this.f2505d = j;
        }

        void a() {
            if (getAndIncrement() == 0) {
                int iAddAndGet = 1;
                while (!this.f2503b.a()) {
                    this.f2504c.subscribe(this);
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                }
            }
        }

        @Override // c.a.s
        public void onComplete() {
            long j = this.f2505d;
            if (j != Long.MAX_VALUE) {
                this.f2505d = j - 1;
            }
            if (j != 0) {
                a();
            } else {
                this.f2502a.onComplete();
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2502a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2502a.onNext(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            this.f2503b.a(bVar);
        }
    }

    public n2(c.a.l<T> lVar, long j) {
        super(lVar);
        this.f2501b = j;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        c.a.b0.a.f fVar = new c.a.b0.a.f();
        sVar.onSubscribe(fVar);
        long j = this.f2501b;
        new a(sVar, j != Long.MAX_VALUE ? j - 1 : Long.MAX_VALUE, fVar, this.f1932a).a();
    }
}
