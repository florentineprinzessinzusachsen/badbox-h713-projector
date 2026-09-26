package c.a.b0.e.d;

import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: ObservableDelay.java */
/* JADX INFO: loaded from: classes.dex */
public final class f0<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f2121b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final TimeUnit f2122c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final c.a.t f2123d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final boolean f2124e;

    /* JADX INFO: compiled from: ObservableDelay.java */
    static final class a<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2125a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f2126b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final TimeUnit f2127c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.t.c f2128d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final boolean f2129e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        c.a.y.b f2130f;

        /* JADX INFO: renamed from: c.a.b0.e.d.f0$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableDelay.java */
        final class RunnableC0057a implements Runnable {
            RunnableC0057a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    a.this.f2125a.onComplete();
                } finally {
                    a.this.f2128d.dispose();
                }
            }
        }

        /* JADX INFO: compiled from: ObservableDelay.java */
        final class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final Throwable f2132a;

            b(Throwable th) {
                this.f2132a = th;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    a.this.f2125a.onError(this.f2132a);
                } finally {
                    a.this.f2128d.dispose();
                }
            }
        }

        /* JADX INFO: compiled from: ObservableDelay.java */
        final class c implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final T f2134a;

            c(T t) {
                this.f2134a = t;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.f2125a.onNext(this.f2134a);
            }
        }

        a(c.a.s<? super T> sVar, long j, TimeUnit timeUnit, c.a.t.c cVar, boolean z) {
            this.f2125a = sVar;
            this.f2126b = j;
            this.f2127c = timeUnit;
            this.f2128d = cVar;
            this.f2129e = z;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2130f.dispose();
            this.f2128d.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2128d.a(new RunnableC0057a(), this.f2126b, this.f2127c);
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2128d.a(new b(th), this.f2129e ? this.f2126b : 0L, this.f2127c);
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2128d.a(new c(t), this.f2126b, this.f2127c);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2130f, bVar)) {
                this.f2130f = bVar;
                this.f2125a.onSubscribe(this);
            }
        }
    }

    public f0(c.a.q<T> qVar, long j, TimeUnit timeUnit, c.a.t tVar, boolean z) {
        super(qVar);
        this.f2121b = j;
        this.f2122c = timeUnit;
        this.f2123d = tVar;
        this.f2124e = z;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(this.f2124e ? sVar : new c.a.d0.f(sVar), this.f2121b, this.f2122c, this.f2123d.a(), this.f2124e));
    }
}
