package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableSwitchMap.java */
/* JADX INFO: loaded from: classes.dex */
public final class l3<T, R> extends c.a.b0.e.d.a<T, R> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.n<? super T, ? extends c.a.q<? extends R>> f2397b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final int f2398c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final boolean f2399d;

    /* JADX INFO: compiled from: ObservableSwitchMap.java */
    static final class a<T, R> extends AtomicReference<c.a.y.b> implements c.a.s<R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final b<T, R> f2400a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f2401b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int f2402c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        volatile c.a.b0.c.j<R> f2403d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        volatile boolean f2404e;

        a(b<T, R> bVar, long j, int i) {
            this.f2400a = bVar;
            this.f2401b = j;
            this.f2402c = i;
        }

        public void a() {
            c.a.b0.a.c.a(this);
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2401b == this.f2400a.j) {
                this.f2404e = true;
                this.f2400a.b();
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2400a.a(this, th);
        }

        @Override // c.a.s
        public void onNext(R r) {
            if (this.f2401b == this.f2400a.j) {
                if (r != null) {
                    this.f2403d.offer(r);
                }
                this.f2400a.b();
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.c(this, bVar)) {
                if (bVar instanceof c.a.b0.c.e) {
                    c.a.b0.c.e eVar = (c.a.b0.c.e) bVar;
                    int iA = eVar.a(7);
                    if (iA == 1) {
                        this.f2403d = eVar;
                        this.f2404e = true;
                        this.f2400a.b();
                        return;
                    } else if (iA == 2) {
                        this.f2403d = eVar;
                        return;
                    }
                }
                this.f2403d = new c.a.b0.f.c(this.f2402c);
            }
        }
    }

    public l3(c.a.q<T> qVar, c.a.a0.n<? super T, ? extends c.a.q<? extends R>> nVar, int i, boolean z) {
        super(qVar);
        this.f2397b = nVar;
        this.f2398c = i;
        this.f2399d = z;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super R> sVar) {
        if (w2.a(this.f1932a, sVar, this.f2397b)) {
            return;
        }
        this.f1932a.subscribe(new b(sVar, this.f2397b, this.f2398c, this.f2399d));
    }

    /* JADX INFO: compiled from: ObservableSwitchMap.java */
    static final class b<T, R> extends AtomicInteger implements c.a.s<T>, c.a.y.b {
        static final a<Object, Object> k = new a<>(null, -1, 1);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super R> f2405a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.n<? super T, ? extends c.a.q<? extends R>> f2406b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int f2407c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final boolean f2408d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        volatile boolean f2410f;
        volatile boolean g;
        c.a.y.b h;
        volatile long j;
        final AtomicReference<a<T, R>> i = new AtomicReference<>();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final c.a.b0.j.c f2409e = new c.a.b0.j.c();

        static {
            k.a();
        }

        b(c.a.s<? super R> sVar, c.a.a0.n<? super T, ? extends c.a.q<? extends R>> nVar, int i, boolean z) {
            this.f2405a = sVar;
            this.f2406b = nVar;
            this.f2407c = i;
            this.f2408d = z;
        }

        void a() {
            a<T, R> andSet;
            a<T, R> aVar = this.i.get();
            a<Object, Object> aVar2 = k;
            if (aVar == aVar2 || (andSet = this.i.getAndSet((a<T, R>) aVar2)) == k || andSet == null) {
                return;
            }
            andSet.a();
        }

        /* JADX WARN: Code duplicated, block: B:101:0x000f A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:95:0x00e9 A[SYNTHETIC] */
        void b() {
            c.a.b0.c.j<R> jVar;
            a.a.a.b.b.C0001b c0001bPoll;
            if (getAndIncrement() != 0) {
                return;
            }
            c.a.s<? super R> sVar = this.f2405a;
            AtomicReference<a<T, R>> atomicReference = this.i;
            boolean z = this.f2408d;
            int iAddAndGet = 1;
            while (!this.g) {
                if (this.f2410f) {
                    boolean z2 = atomicReference.get() == null;
                    if (z) {
                        if (z2) {
                            Throwable th = this.f2409e.get();
                            if (th != null) {
                                sVar.onError(th);
                                return;
                            } else {
                                sVar.onComplete();
                                return;
                            }
                        }
                    } else if (this.f2409e.get() != null) {
                        sVar.onError(this.f2409e.a());
                        return;
                    } else if (z2) {
                        sVar.onComplete();
                        return;
                    }
                }
                a<T, R> aVar = atomicReference.get();
                if (aVar != null && (jVar = aVar.f2403d) != null) {
                    if (aVar.f2404e) {
                        boolean zIsEmpty = jVar.isEmpty();
                        if (z) {
                            if (zIsEmpty) {
                                atomicReference.compareAndSet(aVar, null);
                            }
                        } else if (this.f2409e.get() != null) {
                            sVar.onError(this.f2409e.a());
                            return;
                        } else if (zIsEmpty) {
                            atomicReference.compareAndSet(aVar, null);
                        }
                    }
                    boolean z3 = false;
                    while (!this.g) {
                        if (aVar == atomicReference.get()) {
                            if (!z && this.f2409e.get() != null) {
                                sVar.onError(this.f2409e.a());
                                return;
                            }
                            boolean z4 = aVar.f2404e;
                            try {
                                c0001bPoll = jVar.poll();
                            } catch (Throwable th2) {
                                c.a.z.b.b(th2);
                                this.f2409e.a(th2);
                                atomicReference.compareAndSet(aVar, null);
                                if (z) {
                                    aVar.a();
                                } else {
                                    a();
                                    this.h.dispose();
                                    this.f2410f = true;
                                }
                                c0001bPoll = null;
                                z3 = true;
                            }
                            boolean z5 = c0001bPoll == null;
                            if (z4 && z5) {
                                atomicReference.compareAndSet(aVar, null);
                            } else if (!z5) {
                                sVar.onNext(c0001bPoll);
                            }
                            if (z3) {
                                continue;
                            }
                        }
                        z3 = true;
                        if (z3) {
                            continue;
                        }
                    }
                    return;
                }
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.g) {
                return;
            }
            this.g = true;
            this.h.dispose();
            a();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2410f) {
                return;
            }
            this.f2410f = true;
            b();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2410f || !this.f2409e.a(th)) {
                c.a.e0.a.b(th);
                return;
            }
            if (!this.f2408d) {
                a();
            }
            this.f2410f = true;
            b();
        }

        @Override // c.a.s
        public void onNext(T t) {
            a<T, R> aVar;
            long j = this.j + 1;
            this.j = j;
            a<T, R> aVar2 = this.i.get();
            if (aVar2 != null) {
                aVar2.a();
            }
            try {
                c.a.q<? extends R> qVarApply = this.f2406b.apply(t);
                c.a.b0.b.b.a(qVarApply, "The ObservableSource returned is null");
                c.a.q<? extends R> qVar = qVarApply;
                a<T, R> aVar3 = new a<>(this, j, this.f2407c);
                do {
                    aVar = this.i.get();
                    if (aVar == k) {
                        return;
                    }
                } while (!this.i.compareAndSet(aVar, aVar3));
                qVar.subscribe(aVar3);
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
                this.f2405a.onSubscribe(this);
            }
        }

        void a(a<T, R> aVar, Throwable th) {
            if (aVar.f2401b == this.j && this.f2409e.a(th)) {
                if (!this.f2408d) {
                    this.h.dispose();
                }
                aVar.f2404e = true;
                b();
                return;
            }
            c.a.e0.a.b(th);
        }
    }
}
