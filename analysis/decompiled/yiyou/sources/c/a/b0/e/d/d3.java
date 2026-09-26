package c.a.b0.e.d;

import java.util.NoSuchElementException;

/* JADX INFO: compiled from: ObservableSingleSingle.java */
/* JADX INFO: loaded from: classes.dex */
public final class d3<T> extends c.a.u<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<? extends T> f2059a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final T f2060b;

    /* JADX INFO: compiled from: ObservableSingleSingle.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.v<? super T> f2061a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final T f2062b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f2063c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        T f2064d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f2065e;

        a(c.a.v<? super T> vVar, T t) {
            this.f2061a = vVar;
            this.f2062b = t;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2063c.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2065e) {
                return;
            }
            this.f2065e = true;
            T t = this.f2064d;
            this.f2064d = null;
            if (t == null) {
                t = this.f2062b;
            }
            if (t != null) {
                this.f2061a.a(t);
            } else {
                this.f2061a.onError(new NoSuchElementException());
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2065e) {
                c.a.e0.a.b(th);
            } else {
                this.f2065e = true;
                this.f2061a.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2065e) {
                return;
            }
            if (this.f2064d == null) {
                this.f2064d = t;
                return;
            }
            this.f2065e = true;
            this.f2063c.dispose();
            this.f2061a.onError(new IllegalArgumentException("Sequence contains more than one element!"));
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2063c, bVar)) {
                this.f2063c = bVar;
                this.f2061a.onSubscribe(this);
            }
        }
    }

    public d3(c.a.q<? extends T> qVar, T t) {
        this.f2059a = qVar;
        this.f2060b = t;
    }

    @Override // c.a.u
    public void b(c.a.v<? super T> vVar) {
        this.f2059a.subscribe(new a(vVar, this.f2060b));
    }
}
