package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableWindowBoundary.java */
/* JADX INFO: loaded from: classes.dex */
public final class e4<T, B> extends c.a.b0.e.d.a<T, c.a.l<T>> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.q<B> f2106b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final int f2107c;

    /* JADX INFO: compiled from: ObservableWindowBoundary.java */
    static final class a<T, B> extends c.a.d0.c<B> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final b<T, B> f2108b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f2109c;

        a(b<T, B> bVar) {
            this.f2108b = bVar;
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2109c) {
                return;
            }
            this.f2109c = true;
            this.f2108b.b();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2109c) {
                c.a.e0.a.b(th);
            } else {
                this.f2109c = true;
                this.f2108b.a(th);
            }
        }

        @Override // c.a.s
        public void onNext(B b2) {
            if (this.f2109c) {
                return;
            }
            this.f2108b.c();
        }
    }

    public e4(c.a.q<T> qVar, c.a.q<B> qVar2, int i) {
        super(qVar);
        this.f2106b = qVar2;
        this.f2107c = i;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super c.a.l<T>> sVar) {
        b bVar = new b(sVar, this.f2107c);
        sVar.onSubscribe(bVar);
        this.f2106b.subscribe(bVar.f2112c);
        this.f1932a.subscribe(bVar);
    }

    /* JADX INFO: compiled from: ObservableWindowBoundary.java */
    static final class b<T, B> extends AtomicInteger implements c.a.s<T>, c.a.y.b, Runnable {
        static final Object k = new Object();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super c.a.l<T>> f2110a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f2111b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final a<T, B> f2112c = new a<>(this);

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final AtomicReference<c.a.y.b> f2113d = new AtomicReference<>();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final AtomicInteger f2114e = new AtomicInteger(1);

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final c.a.b0.f.a<Object> f2115f = new c.a.b0.f.a<>();
        final c.a.b0.j.c g = new c.a.b0.j.c();
        final AtomicBoolean h = new AtomicBoolean();
        volatile boolean i;
        c.a.g0.d<T> j;

        b(c.a.s<? super c.a.l<T>> sVar, int i) {
            this.f2110a = sVar;
            this.f2111b = i;
        }

        void a(Throwable th) {
            c.a.b0.a.c.a(this.f2113d);
            if (!this.g.a(th)) {
                c.a.e0.a.b(th);
            } else {
                this.i = true;
                a();
            }
        }

        void b() {
            c.a.b0.a.c.a(this.f2113d);
            this.i = true;
            a();
        }

        void c() {
            this.f2115f.offer(k);
            a();
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.h.compareAndSet(false, true)) {
                this.f2112c.dispose();
                if (this.f2114e.decrementAndGet() == 0) {
                    c.a.b0.a.c.a(this.f2113d);
                }
            }
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2112c.dispose();
            this.i = true;
            a();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2112c.dispose();
            if (!this.g.a(th)) {
                c.a.e0.a.b(th);
            } else {
                this.i = true;
                a();
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2115f.offer(t);
            a();
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.c(this.f2113d, bVar)) {
                c();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f2114e.decrementAndGet() == 0) {
                c.a.b0.a.c.a(this.f2113d);
            }
        }

        void a() {
            if (getAndIncrement() != 0) {
                return;
            }
            c.a.s<? super c.a.l<T>> sVar = this.f2110a;
            c.a.b0.f.a<Object> aVar = this.f2115f;
            c.a.b0.j.c cVar = this.g;
            int iAddAndGet = 1;
            while (this.f2114e.get() != 0) {
                c.a.g0.d<T> dVar = this.j;
                boolean z = this.i;
                if (z && cVar.get() != null) {
                    aVar.clear();
                    Throwable thA = cVar.a();
                    if (dVar != null) {
                        this.j = null;
                        dVar.onError(thA);
                    }
                    sVar.onError(thA);
                    return;
                }
                Object objPoll = aVar.poll();
                boolean z2 = objPoll == null;
                if (z && z2) {
                    Throwable thA2 = cVar.a();
                    if (thA2 == null) {
                        if (dVar != null) {
                            this.j = null;
                            dVar.onComplete();
                        }
                        sVar.onComplete();
                        return;
                    }
                    if (dVar != null) {
                        this.j = null;
                        dVar.onError(thA2);
                    }
                    sVar.onError(thA2);
                    return;
                }
                if (z2) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else if (objPoll != k) {
                    dVar.onNext((T) objPoll);
                } else {
                    if (dVar != null) {
                        this.j = null;
                        dVar.onComplete();
                    }
                    if (!this.h.get()) {
                        c.a.g0.d<T> dVarA = c.a.g0.d.a(this.f2111b, this);
                        this.j = dVarA;
                        this.f2114e.getAndIncrement();
                        sVar.onNext(dVarA);
                    }
                }
            }
            aVar.clear();
            this.j = null;
        }
    }
}
