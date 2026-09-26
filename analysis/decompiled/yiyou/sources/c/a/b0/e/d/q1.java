package c.a.b0.e.d;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableJoin.java */
/* JADX INFO: loaded from: classes.dex */
public final class q1<TLeft, TRight, TLeftEnd, TRightEnd, R> extends c.a.b0.e.d.a<TLeft, R> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.q<? extends TRight> f2592b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.n<? super TLeft, ? extends c.a.q<TLeftEnd>> f2593c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final c.a.a0.n<? super TRight, ? extends c.a.q<TRightEnd>> f2594d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final c.a.a0.c<? super TLeft, ? super TRight, ? extends R> f2595e;

    /* JADX INFO: compiled from: ObservableJoin.java */
    static final class a<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements c.a.y.b, j1.b {
        static final Integer n = 1;
        static final Integer o = 2;
        static final Integer p = 3;
        static final Integer q = 4;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super R> f2596a;
        final c.a.a0.n<? super TLeft, ? extends c.a.q<TLeftEnd>> g;
        final c.a.a0.n<? super TRight, ? extends c.a.q<TRightEnd>> h;
        final c.a.a0.c<? super TLeft, ? super TRight, ? extends R> i;
        int k;
        int l;
        volatile boolean m;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.y.a f2598c = new c.a.y.a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.b0.f.c<Object> f2597b = new c.a.b0.f.c<>(c.a.l.bufferSize());

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final Map<Integer, TLeft> f2599d = new LinkedHashMap();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final Map<Integer, TRight> f2600e = new LinkedHashMap();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final AtomicReference<Throwable> f2601f = new AtomicReference<>();
        final AtomicInteger j = new AtomicInteger(2);

        a(c.a.s<? super R> sVar, c.a.a0.n<? super TLeft, ? extends c.a.q<TLeftEnd>> nVar, c.a.a0.n<? super TRight, ? extends c.a.q<TRightEnd>> nVar2, c.a.a0.c<? super TLeft, ? super TRight, ? extends R> cVar) {
            this.f2596a = sVar;
            this.g = nVar;
            this.h = nVar2;
            this.i = cVar;
        }

        void a() {
            this.f2598c.dispose();
        }

        void b() {
            if (getAndIncrement() != 0) {
                return;
            }
            c.a.b0.f.c<?> cVar = this.f2597b;
            c.a.s<? super R> sVar = this.f2596a;
            int iAddAndGet = 1;
            while (!this.m) {
                if (this.f2601f.get() != null) {
                    cVar.clear();
                    a();
                    a(sVar);
                    return;
                }
                boolean z = this.j.get() == 0;
                Integer num = (Integer) cVar.poll();
                boolean z2 = num == null;
                if (z && z2) {
                    this.f2599d.clear();
                    this.f2600e.clear();
                    this.f2598c.dispose();
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
                        int i = this.k;
                        this.k = i + 1;
                        this.f2599d.put(Integer.valueOf(i), (TLeft) objPoll);
                        try {
                            c.a.q qVarApply = this.g.apply(objPoll);
                            c.a.b0.b.b.a(qVarApply, "The leftEnd returned a null ObservableSource");
                            c.a.q qVar = qVarApply;
                            j1.c cVar2 = new j1.c(this, true, i);
                            this.f2598c.c(cVar2);
                            qVar.subscribe(cVar2);
                            if (this.f2601f.get() != null) {
                                cVar.clear();
                                a();
                                a(sVar);
                                return;
                            }
                            Iterator<TRight> it = this.f2600e.values().iterator();
                            while (it.hasNext()) {
                                try {
                                    R rA = this.i.a(objPoll, it.next());
                                    c.a.b0.b.b.a(rA, "The resultSelector returned a null value");
                                    sVar.onNext(rA);
                                } catch (Throwable th) {
                                    a(th, sVar, cVar);
                                    return;
                                }
                            }
                        } catch (Throwable th2) {
                            a(th2, sVar, cVar);
                            return;
                        }
                    } else if (num == o) {
                        int i2 = this.l;
                        this.l = i2 + 1;
                        this.f2600e.put(Integer.valueOf(i2), (TRight) objPoll);
                        try {
                            c.a.q qVarApply2 = this.h.apply(objPoll);
                            c.a.b0.b.b.a(qVarApply2, "The rightEnd returned a null ObservableSource");
                            c.a.q qVar2 = qVarApply2;
                            j1.c cVar3 = new j1.c(this, false, i2);
                            this.f2598c.c(cVar3);
                            qVar2.subscribe(cVar3);
                            if (this.f2601f.get() != null) {
                                cVar.clear();
                                a();
                                a(sVar);
                                return;
                            }
                            Iterator<TLeft> it2 = this.f2599d.values().iterator();
                            while (it2.hasNext()) {
                                try {
                                    R rA2 = this.i.a(it2.next(), objPoll);
                                    c.a.b0.b.b.a(rA2, "The resultSelector returned a null value");
                                    sVar.onNext(rA2);
                                } catch (Throwable th3) {
                                    a(th3, sVar, cVar);
                                    return;
                                }
                            }
                        } catch (Throwable th4) {
                            a(th4, sVar, cVar);
                            return;
                        }
                    } else if (num == p) {
                        j1.c cVar4 = (j1.c) objPoll;
                        this.f2599d.remove(Integer.valueOf(cVar4.f2314c));
                        this.f2598c.b(cVar4);
                    } else {
                        j1.c cVar5 = (j1.c) objPoll;
                        this.f2600e.remove(Integer.valueOf(cVar5.f2314c));
                        this.f2598c.b(cVar5);
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
                this.f2597b.clear();
            }
        }

        void a(c.a.s<?> sVar) {
            Throwable thA = c.a.b0.j.j.a(this.f2601f);
            this.f2599d.clear();
            this.f2600e.clear();
            sVar.onError(thA);
        }

        void a(Throwable th, c.a.s<?> sVar, c.a.b0.f.c<?> cVar) {
            c.a.z.b.b(th);
            c.a.b0.j.j.a(this.f2601f, th);
            cVar.clear();
            a();
            a(sVar);
        }

        @Override // c.a.b0.e.d.j1.b
        public void a(j1.d dVar) {
            this.f2598c.a(dVar);
            this.j.decrementAndGet();
            b();
        }

        @Override // c.a.b0.e.d.j1.b
        public void a(boolean z, Object obj) {
            synchronized (this) {
                this.f2597b.a(z ? n : o, obj);
            }
            b();
        }

        @Override // c.a.b0.e.d.j1.b
        public void a(boolean z, j1.c cVar) {
            synchronized (this) {
                this.f2597b.a((j1.c) (z ? p : q), cVar);
            }
            b();
        }

        @Override // c.a.b0.e.d.j1.b
        public void a(Throwable th) {
            if (c.a.b0.j.j.a(this.f2601f, th)) {
                b();
            } else {
                c.a.e0.a.b(th);
            }
        }

        @Override // c.a.b0.e.d.j1.b
        public void b(Throwable th) {
            if (c.a.b0.j.j.a(this.f2601f, th)) {
                this.j.decrementAndGet();
                b();
            } else {
                c.a.e0.a.b(th);
            }
        }
    }

    public q1(c.a.q<TLeft> qVar, c.a.q<? extends TRight> qVar2, c.a.a0.n<? super TLeft, ? extends c.a.q<TLeftEnd>> nVar, c.a.a0.n<? super TRight, ? extends c.a.q<TRightEnd>> nVar2, c.a.a0.c<? super TLeft, ? super TRight, ? extends R> cVar) {
        super(qVar);
        this.f2592b = qVar2;
        this.f2593c = nVar;
        this.f2594d = nVar2;
        this.f2595e = cVar;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super R> sVar) {
        a aVar = new a(sVar, this.f2593c, this.f2594d, this.f2595e);
        sVar.onSubscribe(aVar);
        j1.d dVar = new j1.d(aVar, true);
        aVar.f2598c.c(dVar);
        j1.d dVar2 = new j1.d(aVar, false);
        aVar.f2598c.c(dVar2);
        this.f1932a.subscribe(dVar);
        this.f2592b.subscribe(dVar2);
    }
}
