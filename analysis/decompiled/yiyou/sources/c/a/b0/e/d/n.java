package c.a.b0.e.d;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableBufferBoundarySupplier.java */
/* JADX INFO: loaded from: classes.dex */
public final class n<T, U extends Collection<? super T>, B> extends c.a.b0.e.d.a<T, U> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Callable<? extends c.a.q<B>> f2461b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Callable<U> f2462c;

    /* JADX INFO: compiled from: ObservableBufferBoundarySupplier.java */
    static final class a<T, U extends Collection<? super T>, B> extends c.a.d0.c<B> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final b<T, U, B> f2463b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f2464c;

        a(b<T, U, B> bVar) {
            this.f2463b = bVar;
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2464c) {
                return;
            }
            this.f2464c = true;
            this.f2463b.g();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2464c) {
                c.a.e0.a.b(th);
            } else {
                this.f2464c = true;
                this.f2463b.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(B b2) {
            if (this.f2464c) {
                return;
            }
            this.f2464c = true;
            dispose();
            this.f2463b.g();
        }
    }

    /* JADX INFO: compiled from: ObservableBufferBoundarySupplier.java */
    static final class b<T, U extends Collection<? super T>, B> extends c.a.b0.d.p<T, U, U> implements c.a.s<T>, c.a.y.b {
        final Callable<U> g;
        final Callable<? extends c.a.q<B>> h;
        c.a.y.b i;
        final AtomicReference<c.a.y.b> j;
        U k;

        b(c.a.s<? super U> sVar, Callable<U> callable, Callable<? extends c.a.q<B>> callable2) {
            super(sVar, new c.a.b0.f.a());
            this.j = new AtomicReference<>();
            this.g = callable;
            this.h = callable2;
        }

        @Override // c.a.b0.d.p, c.a.b0.j.o
        public /* bridge */ /* synthetic */ void a(c.a.s sVar, Object obj) {
            a((c.a.s<? super Collection>) sVar, (Collection) obj);
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.f1834d) {
                return;
            }
            this.f1834d = true;
            this.i.dispose();
            f();
            if (d()) {
                this.f1833c.clear();
            }
        }

        void f() {
            c.a.b0.a.c.a(this.j);
        }

        void g() {
            try {
                U uCall = this.g.call();
                c.a.b0.b.b.a(uCall, "The buffer supplied is null");
                U u = uCall;
                try {
                    c.a.q<B> qVarCall = this.h.call();
                    c.a.b0.b.b.a(qVarCall, "The boundary ObservableSource supplied is null");
                    c.a.q<B> qVar = qVarCall;
                    a aVar = new a(this);
                    if (c.a.b0.a.c.a(this.j, aVar)) {
                        synchronized (this) {
                            U u2 = this.k;
                            if (u2 == null) {
                                return;
                            }
                            this.k = u;
                            qVar.subscribe(aVar);
                            a(u2, false, this);
                        }
                    }
                } catch (Throwable th) {
                    c.a.z.b.b(th);
                    this.f1834d = true;
                    this.i.dispose();
                    this.f1832b.onError(th);
                }
            } catch (Throwable th2) {
                c.a.z.b.b(th2);
                dispose();
                this.f1832b.onError(th2);
            }
        }

        @Override // c.a.s
        public void onComplete() {
            synchronized (this) {
                U u = this.k;
                if (u == null) {
                    return;
                }
                this.k = null;
                this.f1833c.offer(u);
                this.f1835e = true;
                if (d()) {
                    c.a.b0.j.r.a(this.f1833c, this.f1832b, false, this, this);
                }
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            dispose();
            this.f1832b.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            synchronized (this) {
                U u = this.k;
                if (u == null) {
                    return;
                }
                u.add(t);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.i, bVar)) {
                this.i = bVar;
                c.a.s<? super V> sVar = this.f1832b;
                try {
                    U uCall = this.g.call();
                    c.a.b0.b.b.a(uCall, "The buffer supplied is null");
                    this.k = uCall;
                    try {
                        c.a.q<B> qVarCall = this.h.call();
                        c.a.b0.b.b.a(qVarCall, "The boundary ObservableSource supplied is null");
                        c.a.q<B> qVar = qVarCall;
                        a aVar = new a(this);
                        this.j.set(aVar);
                        sVar.onSubscribe(this);
                        if (this.f1834d) {
                            return;
                        }
                        qVar.subscribe(aVar);
                    } catch (Throwable th) {
                        c.a.z.b.b(th);
                        this.f1834d = true;
                        bVar.dispose();
                        c.a.b0.a.d.a(th, sVar);
                    }
                } catch (Throwable th2) {
                    c.a.z.b.b(th2);
                    this.f1834d = true;
                    bVar.dispose();
                    c.a.b0.a.d.a(th2, sVar);
                }
            }
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public void a(c.a.s<? super U> sVar, U u) {
            this.f1832b.onNext((Object) u);
        }
    }

    public n(c.a.q<T> qVar, Callable<? extends c.a.q<B>> callable, Callable<U> callable2) {
        super(qVar);
        this.f2461b = callable;
        this.f2462c = callable2;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super U> sVar) {
        this.f1932a.subscribe(new b(new c.a.d0.f(sVar), this.f2462c, this.f2461b));
    }
}
