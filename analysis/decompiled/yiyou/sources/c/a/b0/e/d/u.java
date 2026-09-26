package c.a.b0.e.d;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableConcatMap.java */
/* JADX INFO: loaded from: classes.dex */
public final class u<T, U> extends c.a.b0.e.d.a<T, U> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.n<? super T, ? extends c.a.q<? extends U>> f2734b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final int f2735c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final c.a.b0.j.i f2736d;

    /* JADX INFO: compiled from: ObservableConcatMap.java */
    static final class a<T, R> extends AtomicInteger implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super R> f2737a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.n<? super T, ? extends c.a.q<? extends R>> f2738b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int f2739c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.b0.j.c f2740d = new c.a.b0.j.c();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final C0064a<R> f2741e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final boolean f2742f;
        c.a.b0.c.j<T> g;
        c.a.y.b h;
        volatile boolean i;
        volatile boolean j;
        volatile boolean k;
        int l;

        /* JADX INFO: renamed from: c.a.b0.e.d.u$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableConcatMap.java */
        static final class C0064a<R> extends AtomicReference<c.a.y.b> implements c.a.s<R> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final c.a.s<? super R> f2743a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final a<?, R> f2744b;

            C0064a(c.a.s<? super R> sVar, a<?, R> aVar) {
                this.f2743a = sVar;
                this.f2744b = aVar;
            }

            void a() {
                c.a.b0.a.c.a(this);
            }

            @Override // c.a.s
            public void onComplete() {
                a<?, R> aVar = this.f2744b;
                aVar.i = false;
                aVar.a();
            }

            @Override // c.a.s
            public void onError(Throwable th) {
                a<?, R> aVar = this.f2744b;
                if (!aVar.f2740d.a(th)) {
                    c.a.e0.a.b(th);
                    return;
                }
                if (!aVar.f2742f) {
                    aVar.h.dispose();
                }
                aVar.i = false;
                aVar.a();
            }

            @Override // c.a.s
            public void onNext(R r) {
                this.f2743a.onNext(r);
            }

            @Override // c.a.s
            public void onSubscribe(c.a.y.b bVar) {
                c.a.b0.a.c.a(this, bVar);
            }
        }

        a(c.a.s<? super R> sVar, c.a.a0.n<? super T, ? extends c.a.q<? extends R>> nVar, int i, boolean z) {
            this.f2737a = sVar;
            this.f2738b = nVar;
            this.f2739c = i;
            this.f2742f = z;
            this.f2741e = new C0064a<>(sVar, this);
        }

        void a() {
            if (getAndIncrement() != 0) {
                return;
            }
            c.a.s<? super R> sVar = this.f2737a;
            c.a.b0.c.j<T> jVar = this.g;
            c.a.b0.j.c cVar = this.f2740d;
            while (true) {
                if (!this.i) {
                    if (this.k) {
                        jVar.clear();
                        return;
                    }
                    if (!this.f2742f && cVar.get() != null) {
                        jVar.clear();
                        this.k = true;
                        sVar.onError(cVar.a());
                        return;
                    }
                    boolean z = this.j;
                    try {
                        T tPoll = jVar.poll();
                        boolean z2 = tPoll == null;
                        if (z && z2) {
                            this.k = true;
                            Throwable thA = cVar.a();
                            if (thA != null) {
                                sVar.onError(thA);
                                return;
                            } else {
                                sVar.onComplete();
                                return;
                            }
                        }
                        if (!z2) {
                            try {
                                c.a.q<? extends R> qVarApply = this.f2738b.apply(tPoll);
                                c.a.b0.b.b.a(qVarApply, "The mapper returned a null ObservableSource");
                                c.a.q<? extends R> qVar = qVarApply;
                                if (qVar instanceof Callable) {
                                    try {
                                        a.a.a.b.b.a aVar = (Object) ((Callable) qVar).call();
                                        if (aVar != null && !this.k) {
                                            sVar.onNext(aVar);
                                        }
                                    } catch (Throwable th) {
                                        c.a.z.b.b(th);
                                        cVar.a(th);
                                    }
                                } else {
                                    this.i = true;
                                    qVar.subscribe(this.f2741e);
                                }
                            } catch (Throwable th2) {
                                c.a.z.b.b(th2);
                                this.k = true;
                                this.h.dispose();
                                jVar.clear();
                                cVar.a(th2);
                                sVar.onError(cVar.a());
                                return;
                            }
                        }
                    } catch (Throwable th3) {
                        c.a.z.b.b(th3);
                        this.k = true;
                        this.h.dispose();
                        cVar.a(th3);
                        sVar.onError(cVar.a());
                        return;
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            this.k = true;
            this.h.dispose();
            this.f2741e.a();
        }

        @Override // c.a.s
        public void onComplete() {
            this.j = true;
            a();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (!this.f2740d.a(th)) {
                c.a.e0.a.b(th);
            } else {
                this.j = true;
                a();
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.l == 0) {
                this.g.offer(t);
            }
            a();
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.h, bVar)) {
                this.h = bVar;
                if (bVar instanceof c.a.b0.c.e) {
                    c.a.b0.c.e eVar = (c.a.b0.c.e) bVar;
                    int iA = eVar.a(3);
                    if (iA == 1) {
                        this.l = iA;
                        this.g = eVar;
                        this.j = true;
                        this.f2737a.onSubscribe(this);
                        a();
                        return;
                    }
                    if (iA == 2) {
                        this.l = iA;
                        this.g = eVar;
                        this.f2737a.onSubscribe(this);
                        return;
                    }
                }
                this.g = new c.a.b0.f.c(this.f2739c);
                this.f2737a.onSubscribe(this);
            }
        }
    }

    /* JADX INFO: compiled from: ObservableConcatMap.java */
    static final class b<T, U> extends AtomicInteger implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super U> f2745a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.n<? super T, ? extends c.a.q<? extends U>> f2746b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final a<U> f2747c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final int f2748d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        c.a.b0.c.j<T> f2749e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        c.a.y.b f2750f;
        volatile boolean g;
        volatile boolean h;
        volatile boolean i;
        int j;

        /* JADX INFO: compiled from: ObservableConcatMap.java */
        static final class a<U> extends AtomicReference<c.a.y.b> implements c.a.s<U> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final c.a.s<? super U> f2751a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final b<?, ?> f2752b;

            a(c.a.s<? super U> sVar, b<?, ?> bVar) {
                this.f2751a = sVar;
                this.f2752b = bVar;
            }

            void a() {
                c.a.b0.a.c.a(this);
            }

            @Override // c.a.s
            public void onComplete() {
                this.f2752b.b();
            }

            @Override // c.a.s
            public void onError(Throwable th) {
                this.f2752b.dispose();
                this.f2751a.onError(th);
            }

            @Override // c.a.s
            public void onNext(U u) {
                this.f2751a.onNext(u);
            }

            @Override // c.a.s
            public void onSubscribe(c.a.y.b bVar) {
                c.a.b0.a.c.b(this, bVar);
            }
        }

        b(c.a.s<? super U> sVar, c.a.a0.n<? super T, ? extends c.a.q<? extends U>> nVar, int i) {
            this.f2745a = sVar;
            this.f2746b = nVar;
            this.f2748d = i;
            this.f2747c = new a<>(sVar, this);
        }

        void a() {
            if (getAndIncrement() != 0) {
                return;
            }
            while (!this.h) {
                if (!this.g) {
                    boolean z = this.i;
                    try {
                        T tPoll = this.f2749e.poll();
                        boolean z2 = tPoll == null;
                        if (z && z2) {
                            this.h = true;
                            this.f2745a.onComplete();
                            return;
                        }
                        if (!z2) {
                            try {
                                c.a.q<? extends U> qVarApply = this.f2746b.apply(tPoll);
                                c.a.b0.b.b.a(qVarApply, "The mapper returned a null ObservableSource");
                                c.a.q<? extends U> qVar = qVarApply;
                                this.g = true;
                                qVar.subscribe(this.f2747c);
                            } catch (Throwable th) {
                                c.a.z.b.b(th);
                                dispose();
                                this.f2749e.clear();
                                this.f2745a.onError(th);
                                return;
                            }
                        }
                    } catch (Throwable th2) {
                        c.a.z.b.b(th2);
                        dispose();
                        this.f2749e.clear();
                        this.f2745a.onError(th2);
                        return;
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            this.f2749e.clear();
        }

        void b() {
            this.g = false;
            a();
        }

        @Override // c.a.y.b
        public void dispose() {
            this.h = true;
            this.f2747c.a();
            this.f2750f.dispose();
            if (getAndIncrement() == 0) {
                this.f2749e.clear();
            }
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.i) {
                return;
            }
            this.i = true;
            a();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.i) {
                c.a.e0.a.b(th);
                return;
            }
            this.i = true;
            dispose();
            this.f2745a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.i) {
                return;
            }
            if (this.j == 0) {
                this.f2749e.offer(t);
            }
            a();
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2750f, bVar)) {
                this.f2750f = bVar;
                if (bVar instanceof c.a.b0.c.e) {
                    c.a.b0.c.e eVar = (c.a.b0.c.e) bVar;
                    int iA = eVar.a(3);
                    if (iA == 1) {
                        this.j = iA;
                        this.f2749e = eVar;
                        this.i = true;
                        this.f2745a.onSubscribe(this);
                        a();
                        return;
                    }
                    if (iA == 2) {
                        this.j = iA;
                        this.f2749e = eVar;
                        this.f2745a.onSubscribe(this);
                        return;
                    }
                }
                this.f2749e = new c.a.b0.f.c(this.f2748d);
                this.f2745a.onSubscribe(this);
            }
        }
    }

    public u(c.a.q<T> qVar, c.a.a0.n<? super T, ? extends c.a.q<? extends U>> nVar, int i, c.a.b0.j.i iVar) {
        super(qVar);
        this.f2734b = nVar;
        this.f2736d = iVar;
        this.f2735c = Math.max(8, i);
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super U> sVar) {
        if (w2.a(this.f1932a, sVar, this.f2734b)) {
            return;
        }
        c.a.b0.j.i iVar = this.f2736d;
        if (iVar == c.a.b0.j.i.IMMEDIATE) {
            this.f1932a.subscribe(new b(new c.a.d0.f(sVar), this.f2734b, this.f2735c));
        } else {
            this.f1932a.subscribe(new a(sVar, this.f2734b, this.f2735c, iVar == c.a.b0.j.i.END));
        }
    }
}
