package c.a.b0.e.c;

import c.a.a0.n;
import c.a.i;
import c.a.j;
import c.a.l;
import c.a.s;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableSwitchMapMaybe.java */
/* JADX INFO: loaded from: classes.dex */
public final class e<T, R> extends l<R> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final l<T> f1910a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final n<? super T, ? extends j<? extends R>> f1911b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f1912c;

    /* JADX INFO: compiled from: ObservableSwitchMapMaybe.java */
    static final class a<T, R> extends AtomicInteger implements s<T>, c.a.y.b {
        static final C0051a<Object> i = new C0051a<>(null);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final s<? super R> f1913a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final n<? super T, ? extends j<? extends R>> f1914b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final boolean f1915c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.b0.j.c f1916d = new c.a.b0.j.c();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final AtomicReference<C0051a<R>> f1917e = new AtomicReference<>();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        c.a.y.b f1918f;
        volatile boolean g;
        volatile boolean h;

        a(s<? super R> sVar, n<? super T, ? extends j<? extends R>> nVar, boolean z) {
            this.f1913a = sVar;
            this.f1914b = nVar;
            this.f1915c = z;
        }

        void a() {
            C0051a<R> andSet = this.f1917e.getAndSet((C0051a<R>) i);
            if (andSet == null || andSet == i) {
                return;
            }
            andSet.a();
        }

        void b() {
            if (getAndIncrement() != 0) {
                return;
            }
            s<? super R> sVar = this.f1913a;
            c.a.b0.j.c cVar = this.f1916d;
            AtomicReference<C0051a<R>> atomicReference = this.f1917e;
            int iAddAndGet = 1;
            while (!this.h) {
                if (cVar.get() != null && !this.f1915c) {
                    sVar.onError(cVar.a());
                    return;
                }
                boolean z = this.g;
                C0051a<R> c0051a = atomicReference.get();
                boolean z2 = c0051a == null;
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
                if (z2 || c0051a.f1920b == null) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else {
                    atomicReference.compareAndSet(c0051a, null);
                    sVar.onNext(c0051a.f1920b);
                }
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            this.h = true;
            this.f1918f.dispose();
            a();
        }

        @Override // c.a.s
        public void onComplete() {
            this.g = true;
            b();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (!this.f1916d.a(th)) {
                c.a.e0.a.b(th);
                return;
            }
            if (!this.f1915c) {
                a();
            }
            this.g = true;
            b();
        }

        @Override // c.a.s
        public void onNext(T t) {
            C0051a<R> c0051a;
            C0051a<R> c0051a2 = this.f1917e.get();
            if (c0051a2 != null) {
                c0051a2.a();
            }
            try {
                j<? extends R> jVarApply = this.f1914b.apply(t);
                c.a.b0.b.b.a(jVarApply, "The mapper returned a null MaybeSource");
                j<? extends R> jVar = jVarApply;
                C0051a<R> c0051a3 = new C0051a<>(this);
                do {
                    c0051a = this.f1917e.get();
                    if (c0051a == i) {
                        return;
                    }
                } while (!this.f1917e.compareAndSet(c0051a, c0051a3));
                jVar.a(c0051a3);
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f1918f.dispose();
                this.f1917e.getAndSet((C0051a<R>) i);
                onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f1918f, bVar)) {
                this.f1918f = bVar;
                this.f1913a.onSubscribe(this);
            }
        }

        /* JADX INFO: renamed from: c.a.b0.e.c.e$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableSwitchMapMaybe.java */
        static final class C0051a<R> extends AtomicReference<c.a.y.b> implements i<R> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final a<?, R> f1919a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            volatile R f1920b;

            C0051a(a<?, R> aVar) {
                this.f1919a = aVar;
            }

            @Override // c.a.i
            public void a(R r) {
                this.f1920b = r;
                this.f1919a.b();
            }

            @Override // c.a.i
            public void onComplete() {
                this.f1919a.a(this);
            }

            @Override // c.a.i
            public void onError(Throwable th) {
                this.f1919a.a(this, th);
            }

            @Override // c.a.i
            public void onSubscribe(c.a.y.b bVar) {
                c.a.b0.a.c.c(this, bVar);
            }

            void a() {
                c.a.b0.a.c.a(this);
            }
        }

        void a(C0051a<R> c0051a, Throwable th) {
            if (this.f1917e.compareAndSet(c0051a, null) && this.f1916d.a(th)) {
                if (!this.f1915c) {
                    this.f1918f.dispose();
                    a();
                }
                b();
                return;
            }
            c.a.e0.a.b(th);
        }

        void a(C0051a<R> c0051a) {
            if (this.f1917e.compareAndSet(c0051a, null)) {
                b();
            }
        }
    }

    public e(l<T> lVar, n<? super T, ? extends j<? extends R>> nVar, boolean z) {
        this.f1910a = lVar;
        this.f1911b = nVar;
        this.f1912c = z;
    }

    @Override // c.a.l
    protected void subscribeActual(s<? super R> sVar) {
        if (g.a(this.f1910a, this.f1911b, sVar)) {
            return;
        }
        this.f1910a.subscribe(new a(sVar, this.f1911b, this.f1912c));
    }
}
