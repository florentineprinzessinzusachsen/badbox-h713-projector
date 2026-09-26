package c.a.b0.e.c;

import c.a.a0.n;
import c.a.b0.c.j;
import c.a.b0.j.i;
import c.a.l;
import c.a.s;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableConcatMapCompletable.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> extends c.a.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final l<T> f1867a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final n<? super T, ? extends c.a.d> f1868b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final i f1869c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f1870d;

    public a(l<T> lVar, n<? super T, ? extends c.a.d> nVar, i iVar, int i) {
        this.f1867a = lVar;
        this.f1868b = nVar;
        this.f1869c = iVar;
        this.f1870d = i;
    }

    @Override // c.a.b
    protected void b(c.a.c cVar) {
        if (g.a(this.f1867a, this.f1868b, cVar)) {
            return;
        }
        this.f1867a.subscribe(new C0046a(cVar, this.f1868b, this.f1869c, this.f1870d));
    }

    /* JADX INFO: renamed from: c.a.b0.e.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ObservableConcatMapCompletable.java */
    static final class C0046a<T> extends AtomicInteger implements s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.c f1871a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final n<? super T, ? extends c.a.d> f1872b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final i f1873c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.b0.j.c f1874d = new c.a.b0.j.c();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final C0047a f1875e = new C0047a(this);

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final int f1876f;
        j<T> g;
        c.a.y.b h;
        volatile boolean i;
        volatile boolean j;
        volatile boolean k;

        /* JADX INFO: renamed from: c.a.b0.e.c.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableConcatMapCompletable.java */
        static final class C0047a extends AtomicReference<c.a.y.b> implements c.a.c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final C0046a<?> f1877a;

            C0047a(C0046a<?> c0046a) {
                this.f1877a = c0046a;
            }

            void a() {
                c.a.b0.a.c.a(this);
            }

            @Override // c.a.c, c.a.i
            public void onComplete() {
                this.f1877a.b();
            }

            @Override // c.a.c, c.a.i
            public void onError(Throwable th) {
                this.f1877a.a(th);
            }

            @Override // c.a.c, c.a.i
            public void onSubscribe(c.a.y.b bVar) {
                c.a.b0.a.c.a(this, bVar);
            }
        }

        C0046a(c.a.c cVar, n<? super T, ? extends c.a.d> nVar, i iVar, int i) {
            this.f1871a = cVar;
            this.f1872b = nVar;
            this.f1873c = iVar;
            this.f1876f = i;
        }

        void a(Throwable th) {
            if (!this.f1874d.a(th)) {
                c.a.e0.a.b(th);
                return;
            }
            if (this.f1873c != i.IMMEDIATE) {
                this.i = false;
                a();
                return;
            }
            this.k = true;
            this.h.dispose();
            Throwable thA = this.f1874d.a();
            if (thA != c.a.b0.j.j.f3091a) {
                this.f1871a.onError(thA);
            }
            if (getAndIncrement() == 0) {
                this.g.clear();
            }
        }

        void b() {
            this.i = false;
            a();
        }

        @Override // c.a.y.b
        public void dispose() {
            this.k = true;
            this.h.dispose();
            this.f1875e.a();
            if (getAndIncrement() == 0) {
                this.g.clear();
            }
        }

        @Override // c.a.s
        public void onComplete() {
            this.j = true;
            a();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (!this.f1874d.a(th)) {
                c.a.e0.a.b(th);
                return;
            }
            if (this.f1873c != i.IMMEDIATE) {
                this.j = true;
                a();
                return;
            }
            this.k = true;
            this.f1875e.a();
            Throwable thA = this.f1874d.a();
            if (thA != c.a.b0.j.j.f3091a) {
                this.f1871a.onError(thA);
            }
            if (getAndIncrement() == 0) {
                this.g.clear();
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (t != null) {
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
                        this.g = eVar;
                        this.j = true;
                        this.f1871a.onSubscribe(this);
                        a();
                        return;
                    }
                    if (iA == 2) {
                        this.g = eVar;
                        this.f1871a.onSubscribe(this);
                        return;
                    }
                }
                this.g = new c.a.b0.f.c(this.f1876f);
                this.f1871a.onSubscribe(this);
            }
        }

        void a() {
            boolean z;
            if (getAndIncrement() != 0) {
                return;
            }
            c.a.b0.j.c cVar = this.f1874d;
            i iVar = this.f1873c;
            while (!this.k) {
                if (!this.i) {
                    if (iVar == i.BOUNDARY && cVar.get() != null) {
                        this.k = true;
                        this.g.clear();
                        this.f1871a.onError(cVar.a());
                        return;
                    }
                    boolean z2 = this.j;
                    c.a.d dVar = null;
                    try {
                        T tPoll = this.g.poll();
                        if (tPoll != null) {
                            c.a.d dVarApply = this.f1872b.apply(tPoll);
                            c.a.b0.b.b.a(dVarApply, "The mapper returned a null CompletableSource");
                            dVar = dVarApply;
                            z = false;
                        } else {
                            z = true;
                        }
                        if (z2 && z) {
                            this.k = true;
                            Throwable thA = cVar.a();
                            if (thA != null) {
                                this.f1871a.onError(thA);
                                return;
                            } else {
                                this.f1871a.onComplete();
                                return;
                            }
                        }
                        if (!z) {
                            this.i = true;
                            dVar.a(this.f1875e);
                        }
                    } catch (Throwable th) {
                        c.a.z.b.b(th);
                        this.k = true;
                        this.g.clear();
                        this.h.dispose();
                        cVar.a(th);
                        this.f1871a.onError(cVar.a());
                        return;
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            this.g.clear();
        }
    }
}
