package c.a.b0.e.a;

import c.a.l;
import c.a.s;

/* JADX INFO: compiled from: FlowableFromObservable.java */
/* JADX INFO: loaded from: classes.dex */
public final class b<T> extends c.a.f<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final l<T> f1839b;

    /* JADX INFO: compiled from: FlowableFromObservable.java */
    static class a<T> implements s<T>, f.a.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final f.a.b<? super T> f1840a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private c.a.y.b f1841b;

        a(f.a.b<? super T> bVar) {
            this.f1840a = bVar;
        }

        @Override // f.a.c
        public void c(long j) {
        }

        @Override // f.a.c
        public void cancel() {
            this.f1841b.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            this.f1840a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f1840a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f1840a.onNext(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            this.f1841b = bVar;
            this.f1840a.a(this);
        }
    }

    public b(l<T> lVar) {
        this.f1839b = lVar;
    }

    @Override // c.a.f
    protected void b(f.a.b<? super T> bVar) {
        this.f1839b.subscribe(new a(bVar));
    }
}
