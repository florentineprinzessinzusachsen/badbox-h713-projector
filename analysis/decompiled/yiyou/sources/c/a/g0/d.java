package c.a.g0;

import c.a.b0.c.j;
import c.a.l;
import c.a.s;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: UnicastSubject.java */
/* JADX INFO: loaded from: classes.dex */
public final class d<T> extends c<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.b0.f.c<T> f3156a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final AtomicReference<s<? super T>> f3157b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final AtomicReference<Runnable> f3158c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final boolean f3159d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    volatile boolean f3160e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    volatile boolean f3161f;
    Throwable g;
    final AtomicBoolean h;
    final c.a.b0.d.b<T> i;
    boolean j;

    /* JADX INFO: compiled from: UnicastSubject.java */
    final class a extends c.a.b0.d.b<T> {
        a() {
        }

        @Override // c.a.b0.c.f
        public int a(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            d.this.j = true;
            return 2;
        }

        @Override // c.a.b0.c.j
        public void clear() {
            d.this.f3156a.clear();
        }

        @Override // c.a.y.b
        public void dispose() {
            if (d.this.f3160e) {
                return;
            }
            d dVar = d.this;
            dVar.f3160e = true;
            dVar.b();
            d.this.f3157b.lazySet(null);
            if (d.this.i.getAndIncrement() == 0) {
                d.this.f3157b.lazySet(null);
                d.this.f3156a.clear();
            }
        }

        @Override // c.a.b0.c.j
        public boolean isEmpty() {
            return d.this.f3156a.isEmpty();
        }

        @Override // c.a.b0.c.j
        public T poll() {
            return d.this.f3156a.poll();
        }
    }

    d(int i, boolean z) {
        c.a.b0.b.b.a(i, "capacityHint");
        this.f3156a = new c.a.b0.f.c<>(i);
        this.f3158c = new AtomicReference<>();
        this.f3159d = z;
        this.f3157b = new AtomicReference<>();
        this.h = new AtomicBoolean();
        this.i = new a();
    }

    public static <T> d<T> a(int i) {
        return new d<>(i, true);
    }

    public static <T> d<T> d() {
        return new d<>(l.bufferSize(), true);
    }

    void b() {
        Runnable runnable = this.f3158c.get();
        if (runnable == null || !this.f3158c.compareAndSet(runnable, null)) {
            return;
        }
        runnable.run();
    }

    void c(s<? super T> sVar) {
        this.f3157b.lazySet(null);
        Throwable th = this.g;
        if (th != null) {
            sVar.onError(th);
        } else {
            sVar.onComplete();
        }
    }

    @Override // c.a.s
    public void onComplete() {
        if (this.f3161f || this.f3160e) {
            return;
        }
        this.f3161f = true;
        b();
        c();
    }

    @Override // c.a.s
    public void onError(Throwable th) {
        c.a.b0.b.b.a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f3161f || this.f3160e) {
            c.a.e0.a.b(th);
            return;
        }
        this.g = th;
        this.f3161f = true;
        b();
        c();
    }

    @Override // c.a.s
    public void onNext(T t) {
        c.a.b0.b.b.a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f3161f || this.f3160e) {
            return;
        }
        this.f3156a.offer(t);
        c();
    }

    @Override // c.a.s
    public void onSubscribe(c.a.y.b bVar) {
        if (this.f3161f || this.f3160e) {
            bVar.dispose();
        }
    }

    @Override // c.a.l
    protected void subscribeActual(s<? super T> sVar) {
        if (this.h.get() || !this.h.compareAndSet(false, true)) {
            c.a.b0.a.d.a(new IllegalStateException("Only a single observer allowed."), sVar);
            return;
        }
        sVar.onSubscribe(this.i);
        this.f3157b.lazySet(sVar);
        if (this.f3160e) {
            this.f3157b.lazySet(null);
        } else {
            c();
        }
    }

    public static <T> d<T> a(int i, Runnable runnable) {
        return new d<>(i, runnable, true);
    }

    void a(s<? super T> sVar) {
        c.a.b0.f.c<T> cVar = this.f3156a;
        int iAddAndGet = 1;
        boolean z = !this.f3159d;
        while (!this.f3160e) {
            boolean z2 = this.f3161f;
            if (z && z2 && a(cVar, sVar)) {
                return;
            }
            sVar.onNext(null);
            if (z2) {
                c(sVar);
                return;
            } else {
                iAddAndGet = this.i.addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            }
        }
        this.f3157b.lazySet(null);
        cVar.clear();
    }

    void b(s<? super T> sVar) {
        c.a.b0.f.c<T> cVar = this.f3156a;
        boolean z = !this.f3159d;
        boolean z2 = true;
        int iAddAndGet = 1;
        while (!this.f3160e) {
            boolean z3 = this.f3161f;
            T tPoll = this.f3156a.poll();
            boolean z4 = tPoll == null;
            if (z3) {
                if (z && z2) {
                    if (a(cVar, sVar)) {
                        return;
                    } else {
                        z2 = false;
                    }
                }
                if (z4) {
                    c(sVar);
                    return;
                }
            }
            if (z4) {
                iAddAndGet = this.i.addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            } else {
                sVar.onNext(tPoll);
            }
        }
        this.f3157b.lazySet(null);
        cVar.clear();
    }

    void c() {
        if (this.i.getAndIncrement() != 0) {
            return;
        }
        s<? super T> sVar = this.f3157b.get();
        int iAddAndGet = 1;
        while (sVar == null) {
            iAddAndGet = this.i.addAndGet(-iAddAndGet);
            if (iAddAndGet == 0) {
                return;
            } else {
                sVar = this.f3157b.get();
            }
        }
        if (this.j) {
            a(sVar);
        } else {
            b(sVar);
        }
    }

    d(int i, Runnable runnable, boolean z) {
        c.a.b0.b.b.a(i, "capacityHint");
        this.f3156a = new c.a.b0.f.c<>(i);
        c.a.b0.b.b.a(runnable, "onTerminate");
        this.f3158c = new AtomicReference<>(runnable);
        this.f3159d = z;
        this.f3157b = new AtomicReference<>();
        this.h = new AtomicBoolean();
        this.i = new a();
    }

    boolean a(j<T> jVar, s<? super T> sVar) {
        Throwable th = this.g;
        if (th == null) {
            return false;
        }
        this.f3157b.lazySet(null);
        jVar.clear();
        sVar.onError(th);
        return true;
    }
}
