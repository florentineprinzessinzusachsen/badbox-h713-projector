package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableFlatMapSingle.java */
/* JADX INFO: loaded from: classes.dex */
public final class z0<T, R> extends c.a.b0.e.d.a<T, R> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.n<? super T, ? extends c.a.w<? extends R>> f2936b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f2937c;

    public z0(c.a.q<T> qVar, c.a.a0.n<? super T, ? extends c.a.w<? extends R>> nVar, boolean z) {
        super(qVar);
        this.f2936b = nVar;
        this.f2937c = z;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super R> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2936b, this.f2937c));
    }

    /* JADX INFO: compiled from: ObservableFlatMapSingle.java */
    static final class a<T, R> extends AtomicInteger implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super R> f2938a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean f2939b;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final c.a.a0.n<? super T, ? extends c.a.w<? extends R>> f2943f;
        c.a.y.b h;
        volatile boolean i;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.y.a f2940c = new c.a.y.a();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final c.a.b0.j.c f2942e = new c.a.b0.j.c();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final AtomicInteger f2941d = new AtomicInteger(1);
        final AtomicReference<c.a.b0.f.c<R>> g = new AtomicReference<>();

        /* JADX INFO: renamed from: c.a.b0.e.d.z0$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableFlatMapSingle.java */
        final class C0069a extends AtomicReference<c.a.y.b> implements c.a.v<R>, c.a.y.b {
            C0069a() {
            }

            @Override // c.a.v, c.a.i
            public void a(R r) {
                a.this.a(this, r);
            }

            @Override // c.a.y.b
            public void dispose() {
                c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
            }

            @Override // c.a.v, c.a.c, c.a.i
            public void onError(Throwable th) {
                a.this.a(this, th);
            }

            @Override // c.a.v, c.a.c, c.a.i
            public void onSubscribe(c.a.y.b bVar) {
                c.a.b0.a.c.c(this, bVar);
            }
        }

        a(c.a.s<? super R> sVar, c.a.a0.n<? super T, ? extends c.a.w<? extends R>> nVar, boolean z) {
            this.f2938a = sVar;
            this.f2943f = nVar;
            this.f2939b = z;
        }

        /* JADX WARN: Code duplicated, block: B:23:0x004f  */
        /* JADX WARN: Code duplicated, block: B:29:0x0063 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:35:0x0054 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        void a(a<T, R>.C0069a c0069a, R r) {
            c.a.b0.f.c<R> cVarC;
            this.f2940c.a(c0069a);
            if (get() == 0) {
                if (compareAndSet(0, 1)) {
                    this.f2938a.onNext(r);
                    boolean z = this.f2941d.decrementAndGet() == 0;
                    c.a.b0.f.c<R> cVar = this.g.get();
                    if (z && (cVar == null || cVar.isEmpty())) {
                        Throwable thA = this.f2942e.a();
                        if (thA != null) {
                            this.f2938a.onError(thA);
                            return;
                        } else {
                            this.f2938a.onComplete();
                            return;
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                } else {
                    cVarC = c();
                    synchronized (cVarC) {
                        cVarC.offer(r);
                    }
                    this.f2941d.decrementAndGet();
                    if (getAndIncrement() != 0) {
                        return;
                    }
                }
            } else {
                cVarC = c();
                synchronized (cVarC) {
                    cVarC.offer(r);
                    this.f2941d.decrementAndGet();
                    if (getAndIncrement() != 0) {
                        return;
                    }
                }
            }
            b();
        }

        void b() {
            c.a.s<? super R> sVar = this.f2938a;
            AtomicInteger atomicInteger = this.f2941d;
            AtomicReference<c.a.b0.f.c<R>> atomicReference = this.g;
            int iAddAndGet = 1;
            while (!this.i) {
                if (!this.f2939b && this.f2942e.get() != null) {
                    Throwable thA = this.f2942e.a();
                    clear();
                    sVar.onError(thA);
                    return;
                }
                boolean z = atomicInteger.get() == 0;
                c.a.b0.f.c<R> cVar = atomicReference.get();
                a.a.a.b.b.C0001b c0001bPoll = cVar != null ? cVar.poll() : null;
                boolean z2 = c0001bPoll == null;
                if (z && z2) {
                    Throwable thA2 = this.f2942e.a();
                    if (thA2 != null) {
                        sVar.onError(thA2);
                        return;
                    } else {
                        sVar.onComplete();
                        return;
                    }
                }
                if (z2) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else {
                    sVar.onNext(c0001bPoll);
                }
            }
            clear();
        }

        c.a.b0.f.c<R> c() {
            c.a.b0.f.c<R> cVar;
            do {
                c.a.b0.f.c<R> cVar2 = this.g.get();
                if (cVar2 != null) {
                    return cVar2;
                }
                cVar = new c.a.b0.f.c<>(c.a.l.bufferSize());
            } while (!this.g.compareAndSet(null, cVar));
            return cVar;
        }

        void clear() {
            c.a.b0.f.c<R> cVar = this.g.get();
            if (cVar != null) {
                cVar.clear();
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            this.i = true;
            this.h.dispose();
            this.f2940c.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2941d.decrementAndGet();
            a();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2941d.decrementAndGet();
            if (!this.f2942e.a(th)) {
                c.a.e0.a.b(th);
                return;
            }
            if (!this.f2939b) {
                this.f2940c.dispose();
            }
            a();
        }

        @Override // c.a.s
        public void onNext(T t) {
            try {
                c.a.w<? extends R> wVarApply = this.f2943f.apply(t);
                c.a.b0.b.b.a(wVarApply, "The mapper returned a null SingleSource");
                c.a.w<? extends R> wVar = wVarApply;
                this.f2941d.getAndIncrement();
                C0069a c0069a = new C0069a();
                if (this.i || !this.f2940c.c(c0069a)) {
                    return;
                }
                wVar.a(c0069a);
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.h.dispose();
                onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.h, bVar)) {
                this.h = bVar;
                this.f2938a.onSubscribe(this);
            }
        }

        void a(a<T, R>.C0069a c0069a, Throwable th) {
            this.f2940c.a(c0069a);
            if (this.f2942e.a(th)) {
                if (!this.f2939b) {
                    this.h.dispose();
                    this.f2940c.dispose();
                }
                this.f2941d.decrementAndGet();
                a();
                return;
            }
            c.a.e0.a.b(th);
        }

        void a() {
            if (getAndIncrement() == 0) {
                b();
            }
        }
    }
}
