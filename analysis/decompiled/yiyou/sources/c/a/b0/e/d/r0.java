package c.a.b0.e.d;

import java.util.NoSuchElementException;

/* JADX INFO: compiled from: ObservableElementAtSingle.java */
/* JADX INFO: loaded from: classes.dex */
public final class r0<T> extends c.a.u<T> implements c.a.b0.c.a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<T> f2651a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f2652b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final T f2653c;

    /* JADX INFO: compiled from: ObservableElementAtSingle.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.v<? super T> f2654a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f2655b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final T f2656c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        c.a.y.b f2657d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        long f2658e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        boolean f2659f;

        a(c.a.v<? super T> vVar, long j, T t) {
            this.f2654a = vVar;
            this.f2655b = j;
            this.f2656c = t;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2657d.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2659f) {
                return;
            }
            this.f2659f = true;
            T t = this.f2656c;
            if (t != null) {
                this.f2654a.a(t);
            } else {
                this.f2654a.onError(new NoSuchElementException());
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2659f) {
                c.a.e0.a.b(th);
            } else {
                this.f2659f = true;
                this.f2654a.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2659f) {
                return;
            }
            long j = this.f2658e;
            if (j != this.f2655b) {
                this.f2658e = j + 1;
                return;
            }
            this.f2659f = true;
            this.f2657d.dispose();
            this.f2654a.a(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2657d, bVar)) {
                this.f2657d = bVar;
                this.f2654a.onSubscribe(this);
            }
        }
    }

    public r0(c.a.q<T> qVar, long j, T t) {
        this.f2651a = qVar;
        this.f2652b = j;
        this.f2653c = t;
    }

    @Override // c.a.b0.c.a
    public c.a.l<T> a() {
        return c.a.e0.a.a(new p0(this.f2651a, this.f2652b, this.f2653c, true));
    }

    @Override // c.a.u
    public void b(c.a.v<? super T> vVar) {
        this.f2651a.subscribe(new a(vVar, this.f2652b, this.f2653c));
    }
}
