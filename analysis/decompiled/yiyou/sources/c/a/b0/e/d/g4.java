package c.a.b0.e.d;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableWindowBoundarySupplier.java */
/* JADX INFO: loaded from: classes.dex */
public final class g4<T, B> extends c.a.b0.e.d.a<T, c.a.l<T>> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Callable<? extends c.a.q<B>> f2193b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final int f2194c;

    /* JADX INFO: compiled from: ObservableWindowBoundarySupplier.java */
    static final class a<T, B> extends c.a.d0.c<B> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final b<T, B> f2195b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f2196c;

        a(b<T, B> bVar) {
            this.f2195b = bVar;
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2196c) {
                return;
            }
            this.f2196c = true;
            this.f2195b.c();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2196c) {
                c.a.e0.a.b(th);
            } else {
                this.f2196c = true;
                this.f2195b.a(th);
            }
        }

        @Override // c.a.s
        public void onNext(B b2) {
            if (this.f2196c) {
                return;
            }
            this.f2196c = true;
            dispose();
            this.f2195b.a(this);
        }
    }

    public g4(c.a.q<T> qVar, Callable<? extends c.a.q<B>> callable, int i) {
        super(qVar);
        this.f2193b = callable;
        this.f2194c = i;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super c.a.l<T>> sVar) {
        this.f1932a.subscribe(new b(sVar, this.f2194c, this.f2193b));
    }

    /* JADX INFO: compiled from: ObservableWindowBoundarySupplier.java */
    static final class b<T, B> extends AtomicInteger implements c.a.s<T>, c.a.y.b, Runnable {
        static final a<Object, Object> l = new a<>(null);
        static final Object m = new Object();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super c.a.l<T>> f2197a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f2198b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final AtomicReference<a<T, B>> f2199c = new AtomicReference<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final AtomicInteger f2200d = new AtomicInteger(1);

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final c.a.b0.f.a<Object> f2201e = new c.a.b0.f.a<>();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final c.a.b0.j.c f2202f = new c.a.b0.j.c();
        final AtomicBoolean g = new AtomicBoolean();
        final Callable<? extends c.a.q<B>> h;
        c.a.y.b i;
        volatile boolean j;
        c.a.g0.d<T> k;

        b(c.a.s<? super c.a.l<T>> sVar, int i, Callable<? extends c.a.q<B>> callable) {
            this.f2197a = sVar;
            this.f2198b = i;
            this.h = callable;
        }

        void a() {
            a<T, B> andSet = this.f2199c.getAndSet((a<T, B>) l);
            if (andSet == null || andSet == l) {
                return;
            }
            andSet.dispose();
        }

        void b() {
            if (getAndIncrement() != 0) {
                return;
            }
            c.a.s<? super c.a.l<T>> sVar = this.f2197a;
            c.a.b0.f.a<Object> aVar = this.f2201e;
            c.a.b0.j.c cVar = this.f2202f;
            int iAddAndGet = 1;
            while (this.f2200d.get() != 0) {
                c.a.g0.d<T> dVar = this.k;
                boolean z = this.j;
                if (z && cVar.get() != null) {
                    aVar.clear();
                    Throwable thA = cVar.a();
                    if (dVar != null) {
                        this.k = null;
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
                            this.k = null;
                            dVar.onComplete();
                        }
                        sVar.onComplete();
                        return;
                    }
                    if (dVar != null) {
                        this.k = null;
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
                } else if (objPoll != m) {
                    dVar.onNext((T) objPoll);
                } else {
                    if (dVar != null) {
                        this.k = null;
                        dVar.onComplete();
                    }
                    if (!this.g.get()) {
                        c.a.g0.d<T> dVarA = c.a.g0.d.a(this.f2198b, this);
                        this.k = dVarA;
                        this.f2200d.getAndIncrement();
                        try {
                            c.a.q<B> qVarCall = this.h.call();
                            c.a.b0.b.b.a(qVarCall, "The other Callable returned a null ObservableSource");
                            c.a.q<B> qVar = qVarCall;
                            a<T, B> aVar2 = new a<>(this);
                            if (this.f2199c.compareAndSet(null, aVar2)) {
                                qVar.subscribe(aVar2);
                                sVar.onNext(dVarA);
                            }
                        } catch (Throwable th) {
                            c.a.z.b.b(th);
                            cVar.a(th);
                            this.j = true;
                        }
                    }
                }
            }
            aVar.clear();
            this.k = null;
        }

        void c() {
            this.i.dispose();
            this.j = true;
            b();
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.g.compareAndSet(false, true)) {
                a();
                if (this.f2200d.decrementAndGet() == 0) {
                    this.i.dispose();
                }
            }
        }

        @Override // c.a.s
        public void onComplete() {
            a();
            this.j = true;
            b();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            a();
            if (!this.f2202f.a(th)) {
                c.a.e0.a.b(th);
            } else {
                this.j = true;
                b();
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2201e.offer(t);
            b();
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.i, bVar)) {
                this.i = bVar;
                this.f2197a.onSubscribe(this);
                this.f2201e.offer(m);
                b();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f2200d.decrementAndGet() == 0) {
                this.i.dispose();
            }
        }

        void a(a<T, B> aVar) {
            this.f2199c.compareAndSet(aVar, null);
            this.f2201e.offer(m);
            b();
        }

        void a(Throwable th) {
            this.i.dispose();
            if (this.f2202f.a(th)) {
                this.j = true;
                b();
            } else {
                c.a.e0.a.b(th);
            }
        }
    }
}
