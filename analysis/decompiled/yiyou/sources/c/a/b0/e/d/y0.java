package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableFlatMapMaybe.java */
/* JADX INFO: loaded from: classes.dex */
public final class y0<T, R> extends c.a.b0.e.d.a<T, R> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.n<? super T, ? extends c.a.j<? extends R>> f2905b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f2906c;

    public y0(c.a.q<T> qVar, c.a.a0.n<? super T, ? extends c.a.j<? extends R>> nVar, boolean z) {
        super(qVar);
        this.f2905b = nVar;
        this.f2906c = z;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super R> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2905b, this.f2906c));
    }

    /* JADX INFO: compiled from: ObservableFlatMapMaybe.java */
    static final class a<T, R> extends AtomicInteger implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super R> f2907a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean f2908b;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final c.a.a0.n<? super T, ? extends c.a.j<? extends R>> f2912f;
        c.a.y.b h;
        volatile boolean i;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.y.a f2909c = new c.a.y.a();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final c.a.b0.j.c f2911e = new c.a.b0.j.c();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final AtomicInteger f2910d = new AtomicInteger(1);
        final AtomicReference<c.a.b0.f.c<R>> g = new AtomicReference<>();

        /* JADX INFO: renamed from: c.a.b0.e.d.y0$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableFlatMapMaybe.java */
        final class C0067a extends AtomicReference<c.a.y.b> implements c.a.i<R>, c.a.y.b {
            C0067a() {
            }

            @Override // c.a.i
            public void a(R r) {
                a.this.a(this, r);
            }

            @Override // c.a.y.b
            public void dispose() {
                c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
            }

            @Override // c.a.i
            public void onComplete() {
                a.this.a(this);
            }

            @Override // c.a.i
            public void onError(Throwable th) {
                a.this.a(this, th);
            }

            @Override // c.a.i
            public void onSubscribe(c.a.y.b bVar) {
                c.a.b0.a.c.c(this, bVar);
            }
        }

        a(c.a.s<? super R> sVar, c.a.a0.n<? super T, ? extends c.a.j<? extends R>> nVar, boolean z) {
            this.f2907a = sVar;
            this.f2912f = nVar;
            this.f2908b = z;
        }

        /* JADX WARN: Code duplicated, block: B:23:0x004f  */
        /* JADX WARN: Code duplicated, block: B:29:0x0063 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:35:0x0054 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        void a(a<T, R>.C0067a c0067a, R r) {
            c.a.b0.f.c<R> cVarC;
            this.f2909c.a(c0067a);
            if (get() == 0) {
                if (compareAndSet(0, 1)) {
                    this.f2907a.onNext(r);
                    boolean z = this.f2910d.decrementAndGet() == 0;
                    c.a.b0.f.c<R> cVar = this.g.get();
                    if (z && (cVar == null || cVar.isEmpty())) {
                        Throwable thA = this.f2911e.a();
                        if (thA != null) {
                            this.f2907a.onError(thA);
                            return;
                        } else {
                            this.f2907a.onComplete();
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
                    this.f2910d.decrementAndGet();
                    if (getAndIncrement() != 0) {
                        return;
                    }
                }
            } else {
                cVarC = c();
                synchronized (cVarC) {
                    cVarC.offer(r);
                    this.f2910d.decrementAndGet();
                    if (getAndIncrement() != 0) {
                        return;
                    }
                }
            }
            b();
        }

        void b() {
            c.a.s<? super R> sVar = this.f2907a;
            AtomicInteger atomicInteger = this.f2910d;
            AtomicReference<c.a.b0.f.c<R>> atomicReference = this.g;
            int iAddAndGet = 1;
            while (!this.i) {
                if (!this.f2908b && this.f2911e.get() != null) {
                    Throwable thA = this.f2911e.a();
                    clear();
                    sVar.onError(thA);
                    return;
                }
                boolean z = atomicInteger.get() == 0;
                c.a.b0.f.c<R> cVar = atomicReference.get();
                a.a.a.b.b.C0001b c0001bPoll = cVar != null ? cVar.poll() : null;
                boolean z2 = c0001bPoll == null;
                if (z && z2) {
                    Throwable thA2 = this.f2911e.a();
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
            this.f2909c.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2910d.decrementAndGet();
            a();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2910d.decrementAndGet();
            if (!this.f2911e.a(th)) {
                c.a.e0.a.b(th);
                return;
            }
            if (!this.f2908b) {
                this.f2909c.dispose();
            }
            a();
        }

        @Override // c.a.s
        public void onNext(T t) {
            try {
                c.a.j<? extends R> jVarApply = this.f2912f.apply(t);
                c.a.b0.b.b.a(jVarApply, "The mapper returned a null MaybeSource");
                c.a.j<? extends R> jVar = jVarApply;
                this.f2910d.getAndIncrement();
                C0067a c0067a = new C0067a();
                if (this.i || !this.f2909c.c(c0067a)) {
                    return;
                }
                jVar.a(c0067a);
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
                this.f2907a.onSubscribe(this);
            }
        }

        void a(a<T, R>.C0067a c0067a, Throwable th) {
            this.f2909c.a(c0067a);
            if (this.f2911e.a(th)) {
                if (!this.f2908b) {
                    this.h.dispose();
                    this.f2909c.dispose();
                }
                this.f2910d.decrementAndGet();
                a();
                return;
            }
            c.a.e0.a.b(th);
        }

        void a(a<T, R>.C0067a c0067a) {
            this.f2909c.a(c0067a);
            if (get() == 0) {
                if (compareAndSet(0, 1)) {
                    boolean z = this.f2910d.decrementAndGet() == 0;
                    c.a.b0.f.c<R> cVar = this.g.get();
                    if (z && (cVar == null || cVar.isEmpty())) {
                        Throwable thA = this.f2911e.a();
                        if (thA != null) {
                            this.f2907a.onError(thA);
                            return;
                        } else {
                            this.f2907a.onComplete();
                            return;
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                    b();
                    return;
                }
            }
            this.f2910d.decrementAndGet();
            a();
        }

        void a() {
            if (getAndIncrement() == 0) {
                b();
            }
        }
    }
}
