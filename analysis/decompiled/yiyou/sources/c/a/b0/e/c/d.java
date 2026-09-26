package c.a.b0.e.c;

import c.a.a0.n;
import c.a.b0.j.j;
import c.a.l;
import c.a.s;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableSwitchMapCompletable.java */
/* JADX INFO: loaded from: classes.dex */
public final class d<T> extends c.a.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final l<T> f1900a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final n<? super T, ? extends c.a.d> f1901b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f1902c;

    public d(l<T> lVar, n<? super T, ? extends c.a.d> nVar, boolean z) {
        this.f1900a = lVar;
        this.f1901b = nVar;
        this.f1902c = z;
    }

    @Override // c.a.b
    protected void b(c.a.c cVar) {
        if (g.a(this.f1900a, this.f1901b, cVar)) {
            return;
        }
        this.f1900a.subscribe(new a(cVar, this.f1901b, this.f1902c));
    }

    /* JADX INFO: compiled from: ObservableSwitchMapCompletable.java */
    static final class a<T> implements s<T>, c.a.y.b {
        static final C0050a h = new C0050a(null);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.c f1903a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final n<? super T, ? extends c.a.d> f1904b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final boolean f1905c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.b0.j.c f1906d = new c.a.b0.j.c();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final AtomicReference<C0050a> f1907e = new AtomicReference<>();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        volatile boolean f1908f;
        c.a.y.b g;

        /* JADX INFO: renamed from: c.a.b0.e.c.d$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableSwitchMapCompletable.java */
        static final class C0050a extends AtomicReference<c.a.y.b> implements c.a.c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final a<?> f1909a;

            C0050a(a<?> aVar) {
                this.f1909a = aVar;
            }

            void a() {
                c.a.b0.a.c.a(this);
            }

            @Override // c.a.c, c.a.i
            public void onComplete() {
                this.f1909a.a(this);
            }

            @Override // c.a.c, c.a.i
            public void onError(Throwable th) {
                this.f1909a.a(this, th);
            }

            @Override // c.a.c, c.a.i
            public void onSubscribe(c.a.y.b bVar) {
                c.a.b0.a.c.c(this, bVar);
            }
        }

        a(c.a.c cVar, n<? super T, ? extends c.a.d> nVar, boolean z) {
            this.f1903a = cVar;
            this.f1904b = nVar;
            this.f1905c = z;
        }

        void a() {
            C0050a andSet = this.f1907e.getAndSet(h);
            if (andSet == null || andSet == h) {
                return;
            }
            andSet.a();
        }

        @Override // c.a.y.b
        public void dispose() {
            this.g.dispose();
            a();
        }

        @Override // c.a.s
        public void onComplete() {
            this.f1908f = true;
            if (this.f1907e.get() == null) {
                Throwable thA = this.f1906d.a();
                if (thA == null) {
                    this.f1903a.onComplete();
                } else {
                    this.f1903a.onError(thA);
                }
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (!this.f1906d.a(th)) {
                c.a.e0.a.b(th);
                return;
            }
            if (this.f1905c) {
                onComplete();
                return;
            }
            a();
            Throwable thA = this.f1906d.a();
            if (thA != j.f3091a) {
                this.f1903a.onError(thA);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            C0050a c0050a;
            try {
                c.a.d dVarApply = this.f1904b.apply(t);
                c.a.b0.b.b.a(dVarApply, "The mapper returned a null CompletableSource");
                c.a.d dVar = dVarApply;
                C0050a c0050a2 = new C0050a(this);
                do {
                    c0050a = this.f1907e.get();
                    if (c0050a == h) {
                        return;
                    }
                } while (!this.f1907e.compareAndSet(c0050a, c0050a2));
                if (c0050a != null) {
                    c0050a.a();
                }
                dVar.a(c0050a2);
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.g.dispose();
                onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.g, bVar)) {
                this.g = bVar;
                this.f1903a.onSubscribe(this);
            }
        }

        void a(C0050a c0050a, Throwable th) {
            if (this.f1907e.compareAndSet(c0050a, null) && this.f1906d.a(th)) {
                if (this.f1905c) {
                    if (this.f1908f) {
                        this.f1903a.onError(this.f1906d.a());
                        return;
                    }
                    return;
                }
                dispose();
                Throwable thA = this.f1906d.a();
                if (thA != j.f3091a) {
                    this.f1903a.onError(thA);
                    return;
                }
                return;
            }
            c.a.e0.a.b(th);
        }

        void a(C0050a c0050a) {
            if (this.f1907e.compareAndSet(c0050a, null) && this.f1908f) {
                Throwable thA = this.f1906d.a();
                if (thA == null) {
                    this.f1903a.onComplete();
                } else {
                    this.f1903a.onError(thA);
                }
            }
        }
    }
}
