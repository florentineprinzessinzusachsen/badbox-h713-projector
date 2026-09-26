package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservablePublishSelector.java */
/* JADX INFO: loaded from: classes.dex */
public final class g2<T, R> extends c.a.b0.e.d.a<T, R> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.n<? super c.a.l<T>, ? extends c.a.q<R>> f2177b;

    /* JADX INFO: compiled from: ObservablePublishSelector.java */
    static final class a<T, R> implements c.a.s<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.g0.a<T> f2178a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final AtomicReference<c.a.y.b> f2179b;

        a(c.a.g0.a<T> aVar, AtomicReference<c.a.y.b> atomicReference) {
            this.f2178a = aVar;
            this.f2179b = atomicReference;
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2178a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2178a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2178a.onNext(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.c(this.f2179b, bVar);
        }
    }

    /* JADX INFO: compiled from: ObservablePublishSelector.java */
    static final class b<T, R> extends AtomicReference<c.a.y.b> implements c.a.s<R>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super R> f2180a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c.a.y.b f2181b;

        b(c.a.s<? super R> sVar) {
            this.f2180a = sVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2181b.dispose();
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
        }

        @Override // c.a.s
        public void onComplete() {
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
            this.f2180a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
            this.f2180a.onError(th);
        }

        @Override // c.a.s
        public void onNext(R r) {
            this.f2180a.onNext(r);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2181b, bVar)) {
                this.f2181b = bVar;
                this.f2180a.onSubscribe(this);
            }
        }
    }

    public g2(c.a.q<T> qVar, c.a.a0.n<? super c.a.l<T>, ? extends c.a.q<R>> nVar) {
        super(qVar);
        this.f2177b = nVar;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super R> sVar) {
        c.a.g0.a aVarB = c.a.g0.a.b();
        try {
            c.a.q<R> qVarApply = this.f2177b.apply(aVarB);
            c.a.b0.b.b.a(qVarApply, "The selector returned a null ObservableSource");
            c.a.q<R> qVar = qVarApply;
            b bVar = new b(sVar);
            qVar.subscribe(bVar);
            this.f1932a.subscribe(new a(aVarB, bVar));
        } catch (Throwable th) {
            c.a.z.b.b(th);
            c.a.b0.a.d.a(th, sVar);
        }
    }
}
