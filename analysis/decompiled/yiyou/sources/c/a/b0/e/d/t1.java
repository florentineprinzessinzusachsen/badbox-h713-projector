package c.a.b0.e.d;

import java.util.NoSuchElementException;

/* JADX INFO: compiled from: ObservableLastSingle.java */
/* JADX INFO: loaded from: classes.dex */
public final class t1<T> extends c.a.u<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<T> f2711a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final T f2712b;

    /* JADX INFO: compiled from: ObservableLastSingle.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.v<? super T> f2713a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final T f2714b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f2715c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        T f2716d;

        a(c.a.v<? super T> vVar, T t) {
            this.f2713a = vVar;
            this.f2714b = t;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2715c.dispose();
            this.f2715c = c.a.b0.a.c.DISPOSED;
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2715c = c.a.b0.a.c.DISPOSED;
            T t = this.f2716d;
            if (t != null) {
                this.f2716d = null;
                this.f2713a.a(t);
                return;
            }
            T t2 = this.f2714b;
            if (t2 != null) {
                this.f2713a.a(t2);
            } else {
                this.f2713a.onError(new NoSuchElementException());
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2715c = c.a.b0.a.c.DISPOSED;
            this.f2716d = null;
            this.f2713a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2716d = t;
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2715c, bVar)) {
                this.f2715c = bVar;
                this.f2713a.onSubscribe(this);
            }
        }
    }

    public t1(c.a.q<T> qVar, T t) {
        this.f2711a = qVar;
        this.f2712b = t;
    }

    @Override // c.a.u
    protected void b(c.a.v<? super T> vVar) {
        this.f2711a.subscribe(new a(vVar, this.f2712b));
    }
}
