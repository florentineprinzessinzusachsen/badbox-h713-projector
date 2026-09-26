package c.a.b0.e.d;

import java.util.NoSuchElementException;

/* JADX INFO: compiled from: ObservableElementAt.java */
/* JADX INFO: loaded from: classes.dex */
public final class p0<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f2539b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final T f2540c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final boolean f2541d;

    /* JADX INFO: compiled from: ObservableElementAt.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2542a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f2543b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final T f2544c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final boolean f2545d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        c.a.y.b f2546e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        long f2547f;
        boolean g;

        a(c.a.s<? super T> sVar, long j, T t, boolean z) {
            this.f2542a = sVar;
            this.f2543b = j;
            this.f2544c = t;
            this.f2545d = z;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2546e.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.g) {
                return;
            }
            this.g = true;
            T t = this.f2544c;
            if (t == null && this.f2545d) {
                this.f2542a.onError(new NoSuchElementException());
                return;
            }
            if (t != null) {
                this.f2542a.onNext(t);
            }
            this.f2542a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.g) {
                c.a.e0.a.b(th);
            } else {
                this.g = true;
                this.f2542a.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.g) {
                return;
            }
            long j = this.f2547f;
            if (j != this.f2543b) {
                this.f2547f = j + 1;
                return;
            }
            this.g = true;
            this.f2546e.dispose();
            this.f2542a.onNext(t);
            this.f2542a.onComplete();
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2546e, bVar)) {
                this.f2546e = bVar;
                this.f2542a.onSubscribe(this);
            }
        }
    }

    public p0(c.a.q<T> qVar, long j, T t, boolean z) {
        super(qVar);
        this.f2539b = j;
        this.f2540c = t;
        this.f2541d = z;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2539b, this.f2540c, this.f2541d));
    }
}
