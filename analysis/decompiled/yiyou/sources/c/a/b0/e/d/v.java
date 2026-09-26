package c.a.b0.e.d;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: ObservableConcatMapEager.java */
/* JADX INFO: loaded from: classes.dex */
public final class v<T, R> extends c.a.b0.e.d.a<T, R> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.n<? super T, ? extends c.a.q<? extends R>> f2776b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.b0.j.i f2777c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f2778d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final int f2779e;

    public v(c.a.q<T> qVar, c.a.a0.n<? super T, ? extends c.a.q<? extends R>> nVar, c.a.b0.j.i iVar, int i, int i2) {
        super(qVar);
        this.f2776b = nVar;
        this.f2777c = iVar;
        this.f2778d = i;
        this.f2779e = i2;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super R> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2776b, this.f2778d, this.f2779e, this.f2777c));
    }

    /* JADX INFO: compiled from: ObservableConcatMapEager.java */
    static final class a<T, R> extends AtomicInteger implements c.a.s<T>, c.a.y.b, c.a.b0.d.n<R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super R> f2780a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.n<? super T, ? extends c.a.q<? extends R>> f2781b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int f2782c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final int f2783d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final c.a.b0.j.i f2784e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final c.a.b0.j.c f2785f = new c.a.b0.j.c();
        final ArrayDeque<c.a.b0.d.m<R>> g = new ArrayDeque<>();
        c.a.b0.c.j<T> h;
        c.a.y.b i;
        volatile boolean j;
        int k;
        volatile boolean l;
        c.a.b0.d.m<R> m;
        int n;

        a(c.a.s<? super R> sVar, c.a.a0.n<? super T, ? extends c.a.q<? extends R>> nVar, int i, int i2, c.a.b0.j.i iVar) {
            this.f2780a = sVar;
            this.f2781b = nVar;
            this.f2782c = i;
            this.f2783d = i2;
            this.f2784e = iVar;
        }

        @Override // c.a.b0.d.n
        public void a(c.a.b0.d.m<R> mVar, R r) {
            mVar.b().offer(r);
            a();
        }

        void b() {
            c.a.b0.d.m<R> mVar = this.m;
            if (mVar != null) {
                mVar.dispose();
            }
            while (true) {
                c.a.b0.d.m<R> mVarPoll = this.g.poll();
                if (mVarPoll == null) {
                    return;
                } else {
                    mVarPoll.dispose();
                }
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            this.l = true;
            if (getAndIncrement() == 0) {
                this.h.clear();
                b();
            }
        }

        @Override // c.a.s
        public void onComplete() {
            this.j = true;
            a();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (!this.f2785f.a(th)) {
                c.a.e0.a.b(th);
            } else {
                this.j = true;
                a();
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.k == 0) {
                this.h.offer(t);
            }
            a();
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.i, bVar)) {
                this.i = bVar;
                if (bVar instanceof c.a.b0.c.e) {
                    c.a.b0.c.e eVar = (c.a.b0.c.e) bVar;
                    int iA = eVar.a(3);
                    if (iA == 1) {
                        this.k = iA;
                        this.h = eVar;
                        this.j = true;
                        this.f2780a.onSubscribe(this);
                        a();
                        return;
                    }
                    if (iA == 2) {
                        this.k = iA;
                        this.h = eVar;
                        this.f2780a.onSubscribe(this);
                        return;
                    }
                }
                this.h = new c.a.b0.f.c(this.f2783d);
                this.f2780a.onSubscribe(this);
            }
        }

        @Override // c.a.b0.d.n
        public void a(c.a.b0.d.m<R> mVar, Throwable th) {
            if (this.f2785f.a(th)) {
                if (this.f2784e == c.a.b0.j.i.IMMEDIATE) {
                    this.i.dispose();
                }
                mVar.c();
                a();
                return;
            }
            c.a.e0.a.b(th);
        }

        @Override // c.a.b0.d.n
        public void a(c.a.b0.d.m<R> mVar) {
            mVar.c();
            a();
        }

        @Override // c.a.b0.d.n
        public void a() {
            if (getAndIncrement() != 0) {
                return;
            }
            c.a.b0.c.j<T> jVar = this.h;
            ArrayDeque<c.a.b0.d.m<R>> arrayDeque = this.g;
            c.a.s<? super R> sVar = this.f2780a;
            c.a.b0.j.i iVar = this.f2784e;
            int iAddAndGet = 1;
            while (true) {
                int i = this.n;
                while (i != this.f2782c) {
                    if (this.l) {
                        jVar.clear();
                        b();
                        return;
                    }
                    if (iVar == c.a.b0.j.i.IMMEDIATE && this.f2785f.get() != null) {
                        jVar.clear();
                        b();
                        sVar.onError(this.f2785f.a());
                        return;
                    }
                    try {
                        T tPoll = jVar.poll();
                        if (tPoll == null) {
                            break;
                        }
                        c.a.q<? extends R> qVarApply = this.f2781b.apply(tPoll);
                        c.a.b0.b.b.a(qVarApply, "The mapper returned a null ObservableSource");
                        c.a.q<? extends R> qVar = qVarApply;
                        c.a.b0.d.m<R> mVar = new c.a.b0.d.m<>(this, this.f2783d);
                        arrayDeque.offer(mVar);
                        qVar.subscribe(mVar);
                        i++;
                    } catch (Throwable th) {
                        c.a.z.b.b(th);
                        this.i.dispose();
                        jVar.clear();
                        b();
                        this.f2785f.a(th);
                        sVar.onError(this.f2785f.a());
                        return;
                    }
                }
                this.n = i;
                if (this.l) {
                    jVar.clear();
                    b();
                    return;
                }
                if (iVar == c.a.b0.j.i.IMMEDIATE && this.f2785f.get() != null) {
                    jVar.clear();
                    b();
                    sVar.onError(this.f2785f.a());
                    return;
                }
                c.a.b0.d.m<R> mVar2 = this.m;
                if (mVar2 == null) {
                    if (iVar == c.a.b0.j.i.BOUNDARY && this.f2785f.get() != null) {
                        jVar.clear();
                        b();
                        sVar.onError(this.f2785f.a());
                        return;
                    }
                    boolean z = this.j;
                    c.a.b0.d.m<R> mVarPoll = arrayDeque.poll();
                    boolean z2 = mVarPoll == null;
                    if (z && z2) {
                        if (this.f2785f.get() != null) {
                            jVar.clear();
                            b();
                            sVar.onError(this.f2785f.a());
                            return;
                        }
                        sVar.onComplete();
                        return;
                    }
                    if (!z2) {
                        this.m = mVarPoll;
                    }
                    mVar2 = mVarPoll;
                }
                if (mVar2 != null) {
                    c.a.b0.c.j<R> jVarB = mVar2.b();
                    while (true) {
                        if (this.l) {
                            jVar.clear();
                            b();
                            return;
                        }
                        boolean zA = mVar2.a();
                        if (iVar == c.a.b0.j.i.IMMEDIATE && this.f2785f.get() != null) {
                            jVar.clear();
                            b();
                            sVar.onError(this.f2785f.a());
                            return;
                        }
                        try {
                            R rPoll = jVarB.poll();
                            boolean z3 = rPoll == null;
                            if (zA && z3) {
                                this.m = null;
                                this.n--;
                            } else if (!z3) {
                                sVar.onNext(rPoll);
                            }
                        } catch (Throwable th2) {
                            c.a.z.b.b(th2);
                            this.f2785f.a(th2);
                            this.m = null;
                            this.n--;
                        }
                    }
                }
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            }
        }
    }
}
