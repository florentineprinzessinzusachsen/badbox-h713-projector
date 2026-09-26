package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableConcatWithSingle.java */
/* JADX INFO: loaded from: classes.dex */
public final class y<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.w<? extends T> f2901b;

    /* JADX INFO: compiled from: ObservableConcatWithSingle.java */
    static final class a<T> extends AtomicReference<c.a.y.b> implements c.a.s<T>, c.a.v<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2902a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c.a.w<? extends T> f2903b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f2904c;

        a(c.a.s<? super T> sVar, c.a.w<? extends T> wVar) {
            this.f2902a = sVar;
            this.f2903b = wVar;
        }

        @Override // c.a.v, c.a.i
        public void a(T t) {
            this.f2902a.onNext(t);
            this.f2902a.onComplete();
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2904c = true;
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this, (c.a.y.b) null);
            c.a.w<? extends T> wVar = this.f2903b;
            this.f2903b = null;
            wVar.a(this);
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2902a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2902a.onNext(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (!c.a.b0.a.c.c(this, bVar) || this.f2904c) {
                return;
            }
            this.f2902a.onSubscribe(this);
        }
    }

    public y(c.a.l<T> lVar, c.a.w<? extends T> wVar) {
        super(lVar);
        this.f2901b = wVar;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2901b));
    }
}
