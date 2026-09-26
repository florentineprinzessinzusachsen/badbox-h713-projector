package c.a.b0.e.c;

import c.a.a0.n;
import c.a.l;
import c.a.s;
import c.a.v;
import c.a.w;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableSwitchMapSingle.java */
/* JADX INFO: loaded from: classes.dex */
public final class f<T, R> extends l<R> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final l<T> f1921a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final n<? super T, ? extends w<? extends R>> f1922b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f1923c;

    /* JADX INFO: compiled from: ObservableSwitchMapSingle.java */
    static final class a<T, R> extends AtomicInteger implements s<T>, c.a.y.b {
        static final C0052a<Object> i = new C0052a<>(null);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final s<? super R> f1924a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final n<? super T, ? extends w<? extends R>> f1925b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final boolean f1926c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.b0.j.c f1927d = new c.a.b0.j.c();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final AtomicReference<C0052a<R>> f1928e = new AtomicReference<>();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        c.a.y.b f1929f;
        volatile boolean g;
        volatile boolean h;

        a(s<? super R> sVar, n<? super T, ? extends w<? extends R>> nVar, boolean z) {
            this.f1924a = sVar;
            this.f1925b = nVar;
            this.f1926c = z;
        }

        void a() {
            C0052a<R> andSet = this.f1928e.getAndSet((C0052a<R>) i);
            if (andSet == null || andSet == i) {
                return;
            }
            andSet.a();
        }

        void b() {
            if (getAndIncrement() != 0) {
                return;
            }
            s<? super R> sVar = this.f1924a;
            c.a.b0.j.c cVar = this.f1927d;
            AtomicReference<C0052a<R>> atomicReference = this.f1928e;
            int iAddAndGet = 1;
            while (!this.h) {
                if (cVar.get() != null && !this.f1926c) {
                    sVar.onError(cVar.a());
                    return;
                }
                boolean z = this.g;
                C0052a<R> c0052a = atomicReference.get();
                boolean z2 = c0052a == null;
                if (z && z2) {
                    Throwable thA = cVar.a();
                    if (thA != null) {
                        sVar.onError(thA);
                        return;
                    } else {
                        sVar.onComplete();
                        return;
                    }
                }
                if (z2 || c0052a.f1931b == null) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else {
                    atomicReference.compareAndSet(c0052a, null);
                    sVar.onNext(c0052a.f1931b);
                }
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            this.h = true;
            this.f1929f.dispose();
            a();
        }

        @Override // c.a.s
        public void onComplete() {
            this.g = true;
            b();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (!this.f1927d.a(th)) {
                c.a.e0.a.b(th);
                return;
            }
            if (!this.f1926c) {
                a();
            }
            this.g = true;
            b();
        }

        @Override // c.a.s
        public void onNext(T t) {
            C0052a<R> c0052a;
            C0052a<R> c0052a2 = this.f1928e.get();
            if (c0052a2 != null) {
                c0052a2.a();
            }
            try {
                w<? extends R> wVarApply = this.f1925b.apply(t);
                c.a.b0.b.b.a(wVarApply, "The mapper returned a null SingleSource");
                w<? extends R> wVar = wVarApply;
                C0052a<R> c0052a3 = new C0052a<>(this);
                do {
                    c0052a = this.f1928e.get();
                    if (c0052a == i) {
                        return;
                    }
                } while (!this.f1928e.compareAndSet(c0052a, c0052a3));
                wVar.a(c0052a3);
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f1929f.dispose();
                this.f1928e.getAndSet((C0052a<R>) i);
                onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f1929f, bVar)) {
                this.f1929f = bVar;
                this.f1924a.onSubscribe(this);
            }
        }

        /* JADX INFO: renamed from: c.a.b0.e.c.f$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableSwitchMapSingle.java */
        static final class C0052a<R> extends AtomicReference<c.a.y.b> implements v<R> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final a<?, R> f1930a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            volatile R f1931b;

            C0052a(a<?, R> aVar) {
                this.f1930a = aVar;
            }

            @Override // c.a.v, c.a.i
            public void a(R r) {
                this.f1931b = r;
                this.f1930a.b();
            }

            @Override // c.a.v, c.a.c, c.a.i
            public void onError(Throwable th) {
                this.f1930a.a(this, th);
            }

            @Override // c.a.v, c.a.c, c.a.i
            public void onSubscribe(c.a.y.b bVar) {
                c.a.b0.a.c.c(this, bVar);
            }

            void a() {
                c.a.b0.a.c.a(this);
            }
        }

        void a(C0052a<R> c0052a, Throwable th) {
            if (this.f1928e.compareAndSet(c0052a, null) && this.f1927d.a(th)) {
                if (!this.f1926c) {
                    this.f1929f.dispose();
                    a();
                }
                b();
                return;
            }
            c.a.e0.a.b(th);
        }
    }

    public f(l<T> lVar, n<? super T, ? extends w<? extends R>> nVar, boolean z) {
        this.f1921a = lVar;
        this.f1922b = nVar;
        this.f1923c = z;
    }

    @Override // c.a.l
    protected void subscribeActual(s<? super R> sVar) {
        if (g.b(this.f1921a, this.f1922b, sVar)) {
            return;
        }
        this.f1921a.subscribe(new a(sVar, this.f1922b, this.f1923c));
    }
}
