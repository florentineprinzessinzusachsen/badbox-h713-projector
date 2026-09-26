package c.a.b0.e.d;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableDebounceTimed.java */
/* JADX INFO: loaded from: classes.dex */
public final class d0<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f2035b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final TimeUnit f2036c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final c.a.t f2037d;

    /* JADX INFO: compiled from: ObservableDebounceTimed.java */
    static final class a<T> extends AtomicReference<c.a.y.b> implements Runnable, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final T f2038a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f2039b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final b<T> f2040c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final AtomicBoolean f2041d = new AtomicBoolean();

        a(T t, long j, b<T> bVar) {
            this.f2038a = t;
            this.f2039b = j;
            this.f2040c = bVar;
        }

        public void a(c.a.y.b bVar) {
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this, bVar);
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f2041d.compareAndSet(false, true)) {
                this.f2040c.a(this.f2039b, this.f2038a, this);
            }
        }
    }

    /* JADX INFO: compiled from: ObservableDebounceTimed.java */
    static final class b<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2042a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f2043b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final TimeUnit f2044c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.t.c f2045d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        c.a.y.b f2046e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        c.a.y.b f2047f;
        volatile long g;
        boolean h;

        b(c.a.s<? super T> sVar, long j, TimeUnit timeUnit, c.a.t.c cVar) {
            this.f2042a = sVar;
            this.f2043b = j;
            this.f2044c = timeUnit;
            this.f2045d = cVar;
        }

        void a(long j, T t, a<T> aVar) {
            if (j == this.g) {
                this.f2042a.onNext(t);
                aVar.dispose();
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2046e.dispose();
            this.f2045d.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.h) {
                return;
            }
            this.h = true;
            c.a.y.b bVar = this.f2047f;
            if (bVar != null) {
                bVar.dispose();
            }
            a aVar = (a) bVar;
            if (aVar != null) {
                aVar.run();
            }
            this.f2042a.onComplete();
            this.f2045d.dispose();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.h) {
                c.a.e0.a.b(th);
                return;
            }
            c.a.y.b bVar = this.f2047f;
            if (bVar != null) {
                bVar.dispose();
            }
            this.h = true;
            this.f2042a.onError(th);
            this.f2045d.dispose();
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.h) {
                return;
            }
            long j = this.g + 1;
            this.g = j;
            c.a.y.b bVar = this.f2047f;
            if (bVar != null) {
                bVar.dispose();
            }
            a aVar = new a(t, j, this);
            this.f2047f = aVar;
            aVar.a(this.f2045d.a(aVar, this.f2043b, this.f2044c));
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2046e, bVar)) {
                this.f2046e = bVar;
                this.f2042a.onSubscribe(this);
            }
        }
    }

    public d0(c.a.q<T> qVar, long j, TimeUnit timeUnit, c.a.t tVar) {
        super(qVar);
        this.f2035b = j;
        this.f2036c = timeUnit;
        this.f2037d = tVar;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new b(new c.a.d0.f(sVar), this.f2035b, this.f2036c, this.f2037d.a()));
    }
}
