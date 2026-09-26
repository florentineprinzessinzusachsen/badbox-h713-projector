package c.a.b0.e.d;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableGroupJoin.java */
/* JADX INFO: loaded from: classes.dex */
public final class j1<TLeft, TRight, TLeftEnd, TRightEnd, R> extends c.a.b0.e.d.a<TLeft, R> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.q<? extends TRight> f2302b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.n<? super TLeft, ? extends c.a.q<TLeftEnd>> f2303c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final c.a.a0.n<? super TRight, ? extends c.a.q<TRightEnd>> f2304d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final c.a.a0.c<? super TLeft, ? super c.a.l<TRight>, ? extends R> f2305e;

    /* JADX INFO: compiled from: ObservableGroupJoin.java */
    static final class a<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements c.a.y.b, b {
        static final Integer n = 1;
        static final Integer o = 2;
        static final Integer p = 3;
        static final Integer q = 4;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super R> f2306a;
        final c.a.a0.n<? super TLeft, ? extends c.a.q<TLeftEnd>> g;
        final c.a.a0.n<? super TRight, ? extends c.a.q<TRightEnd>> h;
        final c.a.a0.c<? super TLeft, ? super c.a.l<TRight>, ? extends R> i;
        int k;
        int l;
        volatile boolean m;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.y.a f2308c = new c.a.y.a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.b0.f.c<Object> f2307b = new c.a.b0.f.c<>(c.a.l.bufferSize());

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final Map<Integer, c.a.g0.d<TRight>> f2309d = new LinkedHashMap();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final Map<Integer, TRight> f2310e = new LinkedHashMap();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final AtomicReference<Throwable> f2311f = new AtomicReference<>();
        final AtomicInteger j = new AtomicInteger(2);

        a(c.a.s<? super R> sVar, c.a.a0.n<? super TLeft, ? extends c.a.q<TLeftEnd>> nVar, c.a.a0.n<? super TRight, ? extends c.a.q<TRightEnd>> nVar2, c.a.a0.c<? super TLeft, ? super c.a.l<TRight>, ? extends R> cVar) {
            this.f2306a = sVar;
            this.g = nVar;
            this.h = nVar2;
            this.i = cVar;
        }

        void a() {
            this.f2308c.dispose();
        }

        void b() {
            if (getAndIncrement() != 0) {
                return;
            }
            c.a.b0.f.c<?> cVar = this.f2307b;
            c.a.s<? super R> sVar = this.f2306a;
            int iAddAndGet = 1;
            while (!this.m) {
                if (this.f2311f.get() != null) {
                    cVar.clear();
                    a();
                    a(sVar);
                    return;
                }
                boolean z = this.j.get() == 0;
                Integer num = (Integer) cVar.poll();
                boolean z2 = num == null;
                if (z && z2) {
                    Iterator<c.a.g0.d<TRight>> it = this.f2309d.values().iterator();
                    while (it.hasNext()) {
                        it.next().onComplete();
                    }
                    this.f2309d.clear();
                    this.f2310e.clear();
                    this.f2308c.dispose();
                    sVar.onComplete();
                    return;
                }
                if (z2) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else {
                    Object objPoll = cVar.poll();
                    if (num == n) {
                        c.a.g0.d dVarD = c.a.g0.d.d();
                        int i = this.k;
                        this.k = i + 1;
                        this.f2309d.put(Integer.valueOf(i), (c.a.g0.d<TRight>) dVarD);
                        try {
                            c.a.q qVarApply = this.g.apply(objPoll);
                            c.a.b0.b.b.a(qVarApply, "The leftEnd returned a null ObservableSource");
                            c.a.q qVar = qVarApply;
                            c cVar2 = new c(this, true, i);
                            this.f2308c.c(cVar2);
                            qVar.subscribe(cVar2);
                            if (this.f2311f.get() != null) {
                                cVar.clear();
                                a();
                                a(sVar);
                                return;
                            }
                            try {
                                R rA = this.i.a(objPoll, dVarD);
                                c.a.b0.b.b.a(rA, "The resultSelector returned a null value");
                                sVar.onNext(rA);
                                Iterator<TRight> it2 = this.f2310e.values().iterator();
                                while (it2.hasNext()) {
                                    dVarD.onNext(it2.next());
                                }
                            } catch (Throwable th) {
                                a(th, sVar, cVar);
                                return;
                            }
                        } catch (Throwable th2) {
                            a(th2, sVar, cVar);
                            return;
                        }
                    } else if (num == o) {
                        int i2 = this.l;
                        this.l = i2 + 1;
                        this.f2310e.put(Integer.valueOf(i2), (TRight) objPoll);
                        try {
                            c.a.q qVarApply2 = this.h.apply(objPoll);
                            c.a.b0.b.b.a(qVarApply2, "The rightEnd returned a null ObservableSource");
                            c.a.q qVar2 = qVarApply2;
                            c cVar3 = new c(this, false, i2);
                            this.f2308c.c(cVar3);
                            qVar2.subscribe(cVar3);
                            if (this.f2311f.get() != null) {
                                cVar.clear();
                                a();
                                a(sVar);
                                return;
                            } else {
                                Iterator<c.a.g0.d<TRight>> it3 = this.f2309d.values().iterator();
                                while (it3.hasNext()) {
                                    it3.next().onNext(objPoll);
                                }
                            }
                        } catch (Throwable th3) {
                            a(th3, sVar, cVar);
                            return;
                        }
                    } else if (num == p) {
                        c cVar4 = (c) objPoll;
                        c.a.g0.d<TRight> dVarRemove = this.f2309d.remove(Integer.valueOf(cVar4.f2314c));
                        this.f2308c.b(cVar4);
                        if (dVarRemove != null) {
                            dVarRemove.onComplete();
                        }
                    } else if (num == q) {
                        c cVar5 = (c) objPoll;
                        this.f2310e.remove(Integer.valueOf(cVar5.f2314c));
                        this.f2308c.b(cVar5);
                    }
                }
            }
            cVar.clear();
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.m) {
                return;
            }
            this.m = true;
            a();
            if (getAndIncrement() == 0) {
                this.f2307b.clear();
            }
        }

        void a(c.a.s<?> sVar) {
            Throwable thA = c.a.b0.j.j.a(this.f2311f);
            Iterator<c.a.g0.d<TRight>> it = this.f2309d.values().iterator();
            while (it.hasNext()) {
                it.next().onError(thA);
            }
            this.f2309d.clear();
            this.f2310e.clear();
            sVar.onError(thA);
        }

        void a(Throwable th, c.a.s<?> sVar, c.a.b0.f.c<?> cVar) {
            c.a.z.b.b(th);
            c.a.b0.j.j.a(this.f2311f, th);
            cVar.clear();
            a();
            a(sVar);
        }

        @Override // c.a.b0.e.d.j1.b
        public void a(d dVar) {
            this.f2308c.a(dVar);
            this.j.decrementAndGet();
            b();
        }

        @Override // c.a.b0.e.d.j1.b
        public void a(boolean z, Object obj) {
            synchronized (this) {
                this.f2307b.a(z ? n : o, obj);
            }
            b();
        }

        @Override // c.a.b0.e.d.j1.b
        public void a(boolean z, c cVar) {
            synchronized (this) {
                this.f2307b.a((c) (z ? p : q), cVar);
            }
            b();
        }

        @Override // c.a.b0.e.d.j1.b
        public void a(Throwable th) {
            if (c.a.b0.j.j.a(this.f2311f, th)) {
                b();
            } else {
                c.a.e0.a.b(th);
            }
        }

        @Override // c.a.b0.e.d.j1.b
        public void b(Throwable th) {
            if (c.a.b0.j.j.a(this.f2311f, th)) {
                this.j.decrementAndGet();
                b();
            } else {
                c.a.e0.a.b(th);
            }
        }
    }

    /* JADX INFO: compiled from: ObservableGroupJoin.java */
    interface b {
        void a(d dVar);

        void a(Throwable th);

        void a(boolean z, c cVar);

        void a(boolean z, Object obj);

        void b(Throwable th);
    }

    /* JADX INFO: compiled from: ObservableGroupJoin.java */
    static final class c extends AtomicReference<c.a.y.b> implements c.a.s<Object>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final b f2312a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean f2313b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int f2314c;

        c(b bVar, boolean z, int i) {
            this.f2312a = bVar;
            this.f2313b = z;
            this.f2314c = i;
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2312a.a(this.f2313b, this);
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2312a.a(th);
        }

        @Override // c.a.s
        public void onNext(Object obj) {
            if (c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this)) {
                this.f2312a.a(this.f2313b, this);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.c(this, bVar);
        }
    }

    /* JADX INFO: compiled from: ObservableGroupJoin.java */
    static final class d extends AtomicReference<c.a.y.b> implements c.a.s<Object>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final b f2315a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean f2316b;

        d(b bVar, boolean z) {
            this.f2315a = bVar;
            this.f2316b = z;
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2315a.a(this);
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2315a.b(th);
        }

        @Override // c.a.s
        public void onNext(Object obj) {
            this.f2315a.a(this.f2316b, obj);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.c(this, bVar);
        }
    }

    public j1(c.a.q<TLeft> qVar, c.a.q<? extends TRight> qVar2, c.a.a0.n<? super TLeft, ? extends c.a.q<TLeftEnd>> nVar, c.a.a0.n<? super TRight, ? extends c.a.q<TRightEnd>> nVar2, c.a.a0.c<? super TLeft, ? super c.a.l<TRight>, ? extends R> cVar) {
        super(qVar);
        this.f2302b = qVar2;
        this.f2303c = nVar;
        this.f2304d = nVar2;
        this.f2305e = cVar;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super R> sVar) {
        a aVar = new a(sVar, this.f2303c, this.f2304d, this.f2305e);
        sVar.onSubscribe(aVar);
        d dVar = new d(aVar, true);
        aVar.f2308c.c(dVar);
        d dVar2 = new d(aVar, false);
        aVar.f2308c.c(dVar2);
        this.f1932a.subscribe(dVar);
        this.f2302b.subscribe(dVar2);
    }
}
