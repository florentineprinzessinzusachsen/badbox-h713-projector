package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableSubscribeOn.java */
/* JADX INFO: loaded from: classes.dex */
public final class j3<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.t f2324b;

    /* JADX INFO: compiled from: ObservableSubscribeOn.java */
    static final class a<T> extends AtomicReference<c.a.y.b> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2325a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final AtomicReference<c.a.y.b> f2326b = new AtomicReference<>();

        a(c.a.s<? super T> sVar) {
            this.f2325a = sVar;
        }

        void a(c.a.y.b bVar) {
            c.a.b0.a.c.c(this, bVar);
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a(this.f2326b);
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2325a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2325a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2325a.onNext(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.c(this.f2326b, bVar);
        }
    }

    /* JADX INFO: compiled from: ObservableSubscribeOn.java */
    final class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final a<T> f2327a;

        b(a<T> aVar) {
            this.f2327a = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            j3.this.f1932a.subscribe(this.f2327a);
        }
    }

    public j3(c.a.q<T> qVar, c.a.t tVar) {
        super(qVar);
        this.f2324b = tVar;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        a aVar = new a(sVar);
        sVar.onSubscribe(aVar);
        aVar.a(this.f2324b.a(new b(aVar)));
    }
}
