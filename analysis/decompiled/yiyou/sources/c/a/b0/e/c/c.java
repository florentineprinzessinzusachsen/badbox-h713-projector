package c.a.b0.e.c;

import c.a.a0.n;
import c.a.b0.j.i;
import c.a.l;
import c.a.s;
import c.a.v;
import c.a.w;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableConcatMapSingle.java */
/* JADX INFO: loaded from: classes.dex */
public final class c<T, R> extends l<R> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final l<T> f1889a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final n<? super T, ? extends w<? extends R>> f1890b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final i f1891c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f1892d;

    public c(l<T> lVar, n<? super T, ? extends w<? extends R>> nVar, i iVar, int i) {
        this.f1889a = lVar;
        this.f1890b = nVar;
        this.f1891c = iVar;
        this.f1892d = i;
    }

    @Override // c.a.l
    protected void subscribeActual(s<? super R> sVar) {
        if (g.b(this.f1889a, this.f1890b, sVar)) {
            return;
        }
        this.f1889a.subscribe(new a(sVar, this.f1890b, this.f1892d, this.f1891c));
    }

    /* JADX INFO: compiled from: ObservableConcatMapSingle.java */
    static final class a<T, R> extends AtomicInteger implements s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final s<? super R> f1893a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final n<? super T, ? extends w<? extends R>> f1894b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.b0.j.c f1895c = new c.a.b0.j.c();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final C0049a<R> f1896d = new C0049a<>(this);

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final c.a.b0.c.i<T> f1897e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final i f1898f;
        c.a.y.b g;
        volatile boolean h;
        volatile boolean i;
        R j;
        volatile int k;

        /* JADX INFO: renamed from: c.a.b0.e.c.c$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableConcatMapSingle.java */
        static final class C0049a<R> extends AtomicReference<c.a.y.b> implements v<R> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final a<?, R> f1899a;

            C0049a(a<?, R> aVar) {
                this.f1899a = aVar;
            }

            @Override // c.a.v, c.a.i
            public void a(R r) {
                this.f1899a.a(r);
            }

            @Override // c.a.v, c.a.c, c.a.i
            public void onError(Throwable th) {
                this.f1899a.a(th);
            }

            @Override // c.a.v, c.a.c, c.a.i
            public void onSubscribe(c.a.y.b bVar) {
                c.a.b0.a.c.a(this, bVar);
            }

            void a() {
                c.a.b0.a.c.a(this);
            }
        }

        a(s<? super R> sVar, n<? super T, ? extends w<? extends R>> nVar, int i, i iVar) {
            this.f1893a = sVar;
            this.f1894b = nVar;
            this.f1898f = iVar;
            this.f1897e = new c.a.b0.f.c(i);
        }

        void a(R r) {
            this.j = r;
            this.k = 2;
            a();
        }

        @Override // c.a.y.b
        public void dispose() {
            this.i = true;
            this.g.dispose();
            this.f1896d.a();
            if (getAndIncrement() == 0) {
                this.f1897e.clear();
                this.j = null;
            }
        }

        @Override // c.a.s
        public void onComplete() {
            this.h = true;
            a();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (!this.f1895c.a(th)) {
                c.a.e0.a.b(th);
                return;
            }
            if (this.f1898f == i.IMMEDIATE) {
                this.f1896d.a();
            }
            this.h = true;
            a();
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f1897e.offer(t);
            a();
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.g, bVar)) {
                this.g = bVar;
                this.f1893a.onSubscribe(this);
            }
        }

        void a(Throwable th) {
            if (this.f1895c.a(th)) {
                if (this.f1898f != i.END) {
                    this.g.dispose();
                }
                this.k = 0;
                a();
                return;
            }
            c.a.e0.a.b(th);
        }

        void a() {
            if (getAndIncrement() != 0) {
                return;
            }
            s<? super R> sVar = this.f1893a;
            i iVar = this.f1898f;
            c.a.b0.c.i<T> iVar2 = this.f1897e;
            c.a.b0.j.c cVar = this.f1895c;
            int iAddAndGet = 1;
            while (true) {
                if (this.i) {
                    iVar2.clear();
                    this.j = null;
                } else {
                    int i = this.k;
                    if (cVar.get() != null && (iVar == i.IMMEDIATE || (iVar == i.BOUNDARY && i == 0))) {
                        break;
                    }
                    if (i == 0) {
                        boolean z = this.h;
                        T tPoll = iVar2.poll();
                        boolean z2 = tPoll == null;
                        if (z && z2) {
                            Throwable thA = cVar.a();
                            if (thA == null) {
                                sVar.onComplete();
                                return;
                            } else {
                                sVar.onError(thA);
                                return;
                            }
                        }
                        if (!z2) {
                            try {
                                w<? extends R> wVarApply = this.f1894b.apply(tPoll);
                                c.a.b0.b.b.a(wVarApply, "The mapper returned a null SingleSource");
                                w<? extends R> wVar = wVarApply;
                                this.k = 1;
                                wVar.a(this.f1896d);
                            } catch (Throwable th) {
                                c.a.z.b.b(th);
                                this.g.dispose();
                                iVar2.clear();
                                cVar.a(th);
                                sVar.onError(cVar.a());
                                return;
                            }
                        }
                    } else if (i == 2) {
                        R r = this.j;
                        this.j = null;
                        sVar.onNext(r);
                        this.k = 0;
                    }
                }
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            }
            iVar2.clear();
            this.j = null;
            sVar.onError(cVar.a());
        }
    }
}
