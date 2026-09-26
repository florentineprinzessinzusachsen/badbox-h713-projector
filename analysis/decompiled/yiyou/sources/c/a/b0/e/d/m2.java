package c.a.b0.e.d;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableRefCount.java */
/* JADX INFO: loaded from: classes.dex */
public final class m2<T> extends c.a.l<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.c0.a<T> f2440a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final int f2441b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final long f2442c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final TimeUnit f2443d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final c.a.t f2444e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    a f2445f;

    /* JADX INFO: compiled from: ObservableRefCount.java */
    static final class a extends AtomicReference<c.a.y.b> implements Runnable, c.a.a0.f<c.a.y.b> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final m2<?> f2446a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c.a.y.b f2447b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f2448c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f2449d;

        a(m2<?> m2Var) {
            this.f2446a = m2Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f2446a.c(this);
        }

        @Override // c.a.a0.f
        public void a(c.a.y.b bVar) {
            c.a.b0.a.c.a(this, bVar);
        }
    }

    /* JADX INFO: compiled from: ObservableRefCount.java */
    static final class b<T> extends AtomicBoolean implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2450a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final m2<T> f2451b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final a f2452c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        c.a.y.b f2453d;

        b(c.a.s<? super T> sVar, m2<T> m2Var, a aVar) {
            this.f2450a = sVar;
            this.f2451b = m2Var;
            this.f2452c = aVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2453d.dispose();
            if (compareAndSet(false, true)) {
                this.f2451b.a(this.f2452c);
            }
        }

        @Override // c.a.s
        public void onComplete() {
            if (compareAndSet(false, true)) {
                this.f2451b.b(this.f2452c);
                this.f2450a.onComplete();
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (!compareAndSet(false, true)) {
                c.a.e0.a.b(th);
            } else {
                this.f2451b.b(this.f2452c);
                this.f2450a.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2450a.onNext(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2453d, bVar)) {
                this.f2453d = bVar;
                this.f2450a.onSubscribe(this);
            }
        }
    }

    public m2(c.a.c0.a<T> aVar) {
        this(aVar, 1, 0L, TimeUnit.NANOSECONDS, c.a.f0.b.d());
    }

    void a(a aVar) {
        synchronized (this) {
            if (this.f2445f == null) {
                return;
            }
            long j = aVar.f2448c - 1;
            aVar.f2448c = j;
            if (j == 0 && aVar.f2449d) {
                if (this.f2442c == 0) {
                    c(aVar);
                    return;
                }
                c.a.b0.a.f fVar = new c.a.b0.a.f();
                aVar.f2447b = fVar;
                fVar.a(this.f2444e.a(aVar, this.f2442c, this.f2443d));
            }
        }
    }

    void b(a aVar) {
        synchronized (this) {
            if (this.f2445f != null) {
                this.f2445f = null;
                if (aVar.f2447b != null) {
                    aVar.f2447b.dispose();
                }
                if (this.f2440a instanceof c.a.y.b) {
                    ((c.a.y.b) this.f2440a).dispose();
                }
            }
        }
    }

    void c(a aVar) {
        synchronized (this) {
            if (aVar.f2448c == 0 && aVar == this.f2445f) {
                this.f2445f = null;
                c.a.b0.a.c.a(aVar);
                if (this.f2440a instanceof c.a.y.b) {
                    ((c.a.y.b) this.f2440a).dispose();
                }
            }
        }
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        a aVar;
        boolean z;
        synchronized (this) {
            aVar = this.f2445f;
            if (aVar == null) {
                aVar = new a(this);
                this.f2445f = aVar;
            }
            long j = aVar.f2448c;
            if (j == 0 && aVar.f2447b != null) {
                aVar.f2447b.dispose();
            }
            long j2 = j + 1;
            aVar.f2448c = j2;
            z = true;
            if (aVar.f2449d || j2 != this.f2441b) {
                z = false;
            } else {
                aVar.f2449d = true;
            }
        }
        this.f2440a.subscribe(new b(sVar, this, aVar));
        if (z) {
            this.f2440a.a(aVar);
        }
    }

    public m2(c.a.c0.a<T> aVar, int i, long j, TimeUnit timeUnit, c.a.t tVar) {
        this.f2440a = aVar;
        this.f2441b = i;
        this.f2442c = j;
        this.f2443d = timeUnit;
        this.f2444e = tVar;
    }
}
