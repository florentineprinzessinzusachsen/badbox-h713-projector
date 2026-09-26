package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableConcatWithCompletable.java */
/* JADX INFO: loaded from: classes.dex */
public final class w<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.d f2819b;

    /* JADX INFO: compiled from: ObservableConcatWithCompletable.java */
    static final class a<T> extends AtomicReference<c.a.y.b> implements c.a.s<T>, c.a.c, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2820a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c.a.d f2821b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f2822c;

        a(c.a.s<? super T> sVar, c.a.d dVar) {
            this.f2820a = sVar;
            this.f2821b = dVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2822c) {
                this.f2820a.onComplete();
                return;
            }
            this.f2822c = true;
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this, (c.a.y.b) null);
            c.a.d dVar = this.f2821b;
            this.f2821b = null;
            dVar.a(this);
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2820a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2820a.onNext(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (!c.a.b0.a.c.c(this, bVar) || this.f2822c) {
                return;
            }
            this.f2820a.onSubscribe(this);
        }
    }

    public w(c.a.l<T> lVar, c.a.d dVar) {
        super(lVar);
        this.f2819b = dVar;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2819b));
    }
}
