package c.a.b0.e.d;

import java.util.Collection;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: ObservableBufferExactBoundary.java */
/* JADX INFO: loaded from: classes.dex */
public final class o<T, U extends Collection<? super T>, B> extends c.a.b0.e.d.a<T, U> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.q<B> f2511b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Callable<U> f2512c;

    /* JADX INFO: compiled from: ObservableBufferExactBoundary.java */
    static final class a<T, U extends Collection<? super T>, B> extends c.a.d0.c<B> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final b<T, U, B> f2513b;

        a(b<T, U, B> bVar) {
            this.f2513b = bVar;
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2513b.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2513b.onError(th);
        }

        @Override // c.a.s
        public void onNext(B b2) {
            this.f2513b.f();
        }
    }

    /* JADX INFO: compiled from: ObservableBufferExactBoundary.java */
    static final class b<T, U extends Collection<? super T>, B> extends c.a.b0.d.p<T, U, U> implements c.a.s<T>, c.a.y.b {
        final Callable<U> g;
        final c.a.q<B> h;
        c.a.y.b i;
        c.a.y.b j;
        U k;

        b(c.a.s<? super U> sVar, Callable<U> callable, c.a.q<B> qVar) {
            super(sVar, new c.a.b0.f.a());
            this.g = callable;
            this.h = qVar;
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
            this.j.dispose();
            this.i.dispose();
            if (d()) {
                this.f1833c.clear();
            }
        }

        void f() {
            try {
                U uCall = this.g.call();
                c.a.b0.b.b.a(uCall, "The buffer supplied is null");
                U u = uCall;
                synchronized (this) {
                    U u2 = this.k;
                    if (u2 == null) {
                        return;
                    }
                    this.k = u;
                    a(u2, false, this);
                }
            } catch (Throwable th) {
                c.a.z.b.b(th);
                dispose();
                this.f1832b.onError(th);
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
                try {
                    U uCall = this.g.call();
                    c.a.b0.b.b.a(uCall, "The buffer supplied is null");
                    this.k = uCall;
                    a aVar = new a(this);
                    this.j = aVar;
                    this.f1832b.onSubscribe(this);
                    if (this.f1834d) {
                        return;
                    }
                    this.h.subscribe(aVar);
                } catch (Throwable th) {
                    c.a.z.b.b(th);
                    this.f1834d = true;
                    bVar.dispose();
                    c.a.b0.a.d.a(th, this.f1832b);
                }
            }
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public void a(c.a.s<? super U> sVar, U u) {
            this.f1832b.onNext((Object) u);
        }
    }

    public o(c.a.q<T> qVar, c.a.q<B> qVar2, Callable<U> callable) {
        super(qVar);
        this.f2511b = qVar2;
        this.f2512c = callable;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super U> sVar) {
        this.f1932a.subscribe(new b(new c.a.d0.f(sVar), this.f2512c, this.f2511b));
    }
}
