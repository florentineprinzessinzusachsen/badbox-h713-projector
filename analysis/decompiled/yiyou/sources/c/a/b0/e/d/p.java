package c.a.b0.e.d;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableBufferTimed.java */
/* JADX INFO: loaded from: classes.dex */
public final class p<T, U extends Collection<? super T>> extends c.a.b0.e.d.a<T, U> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f2530b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final long f2531c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final TimeUnit f2532d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final c.a.t f2533e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final Callable<U> f2534f;
    final int g;
    final boolean h;

    /* JADX INFO: compiled from: ObservableBufferTimed.java */
    static final class a<T, U extends Collection<? super T>> extends c.a.b0.d.p<T, U, U> implements Runnable, c.a.y.b {
        final Callable<U> g;
        final long h;
        final TimeUnit i;
        final int j;
        final boolean k;
        final c.a.t.c l;
        U m;
        c.a.y.b n;
        c.a.y.b o;
        long p;
        long q;

        a(c.a.s<? super U> sVar, Callable<U> callable, long j, TimeUnit timeUnit, int i, boolean z, c.a.t.c cVar) {
            super(sVar, new c.a.b0.f.a());
            this.g = callable;
            this.h = j;
            this.i = timeUnit;
            this.j = i;
            this.k = z;
            this.l = cVar;
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
            this.o.dispose();
            this.l.dispose();
            synchronized (this) {
                this.m = null;
            }
        }

        @Override // c.a.s
        public void onComplete() {
            U u;
            this.l.dispose();
            synchronized (this) {
                u = this.m;
                this.m = null;
            }
            this.f1833c.offer(u);
            this.f1835e = true;
            if (d()) {
                c.a.b0.j.r.a(this.f1833c, this.f1832b, false, this, this);
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            synchronized (this) {
                this.m = null;
            }
            this.f1832b.onError(th);
            this.l.dispose();
        }

        @Override // c.a.s
        public void onNext(T t) {
            synchronized (this) {
                U u = this.m;
                if (u == null) {
                    return;
                }
                u.add(t);
                if (u.size() < this.j) {
                    return;
                }
                this.m = null;
                this.p++;
                if (this.k) {
                    this.n.dispose();
                }
                b(u, false, this);
                try {
                    U uCall = this.g.call();
                    c.a.b0.b.b.a(uCall, "The buffer supplied is null");
                    U u2 = uCall;
                    synchronized (this) {
                        this.m = u2;
                        this.q++;
                    }
                    if (this.k) {
                        c.a.t.c cVar = this.l;
                        long j = this.h;
                        this.n = cVar.a(this, j, j, this.i);
                    }
                } catch (Throwable th) {
                    c.a.z.b.b(th);
                    this.f1832b.onError(th);
                    dispose();
                }
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.o, bVar)) {
                this.o = bVar;
                try {
                    U uCall = this.g.call();
                    c.a.b0.b.b.a(uCall, "The buffer supplied is null");
                    this.m = uCall;
                    this.f1832b.onSubscribe(this);
                    c.a.t.c cVar = this.l;
                    long j = this.h;
                    this.n = cVar.a(this, j, j, this.i);
                } catch (Throwable th) {
                    c.a.z.b.b(th);
                    bVar.dispose();
                    c.a.b0.a.d.a(th, this.f1832b);
                    this.l.dispose();
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                U uCall = this.g.call();
                c.a.b0.b.b.a(uCall, "The bufferSupplier returned a null buffer");
                U u = uCall;
                synchronized (this) {
                    U u2 = this.m;
                    if (u2 != null && this.p == this.q) {
                        this.m = u;
                        b(u2, false, this);
                    }
                }
            } catch (Throwable th) {
                c.a.z.b.b(th);
                dispose();
                this.f1832b.onError(th);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void a(c.a.s<? super U> sVar, U u) {
            sVar.onNext(u);
        }
    }

    /* JADX INFO: compiled from: ObservableBufferTimed.java */
    static final class b<T, U extends Collection<? super T>> extends c.a.b0.d.p<T, U, U> implements Runnable, c.a.y.b {
        final Callable<U> g;
        final long h;
        final TimeUnit i;
        final c.a.t j;
        c.a.y.b k;
        U l;
        final AtomicReference<c.a.y.b> m;

        b(c.a.s<? super U> sVar, Callable<U> callable, long j, TimeUnit timeUnit, c.a.t tVar) {
            super(sVar, new c.a.b0.f.a());
            this.m = new AtomicReference<>();
            this.g = callable;
            this.h = j;
            this.i = timeUnit;
            this.j = tVar;
        }

        @Override // c.a.b0.d.p, c.a.b0.j.o
        public /* bridge */ /* synthetic */ void a(c.a.s sVar, Object obj) {
            a((c.a.s<? super Collection>) sVar, (Collection) obj);
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a(this.m);
            this.k.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            U u;
            synchronized (this) {
                u = this.l;
                this.l = null;
            }
            if (u != null) {
                this.f1833c.offer(u);
                this.f1835e = true;
                if (d()) {
                    c.a.b0.j.r.a(this.f1833c, this.f1832b, false, null, this);
                }
            }
            c.a.b0.a.c.a(this.m);
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            synchronized (this) {
                this.l = null;
            }
            this.f1832b.onError(th);
            c.a.b0.a.c.a(this.m);
        }

        @Override // c.a.s
        public void onNext(T t) {
            synchronized (this) {
                U u = this.l;
                if (u == null) {
                    return;
                }
                u.add(t);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.k, bVar)) {
                this.k = bVar;
                try {
                    U uCall = this.g.call();
                    c.a.b0.b.b.a(uCall, "The buffer supplied is null");
                    this.l = uCall;
                    this.f1832b.onSubscribe(this);
                    if (this.f1834d) {
                        return;
                    }
                    c.a.t tVar = this.j;
                    long j = this.h;
                    c.a.y.b bVarA = tVar.a(this, j, j, this.i);
                    if (this.m.compareAndSet(null, bVarA)) {
                        return;
                    }
                    bVarA.dispose();
                } catch (Throwable th) {
                    c.a.z.b.b(th);
                    dispose();
                    c.a.b0.a.d.a(th, this.f1832b);
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            U u;
            try {
                U uCall = this.g.call();
                c.a.b0.b.b.a(uCall, "The bufferSupplier returned a null buffer");
                U u2 = uCall;
                synchronized (this) {
                    u = this.l;
                    if (u != null) {
                        this.l = u2;
                    }
                }
                if (u == null) {
                    c.a.b0.a.c.a(this.m);
                } else {
                    a(u, false, this);
                }
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f1832b.onError(th);
                dispose();
            }
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public void a(c.a.s<? super U> sVar, U u) {
            this.f1832b.onNext((Object) u);
        }
    }

    /* JADX INFO: compiled from: ObservableBufferTimed.java */
    static final class c<T, U extends Collection<? super T>> extends c.a.b0.d.p<T, U, U> implements Runnable, c.a.y.b {
        final Callable<U> g;
        final long h;
        final long i;
        final TimeUnit j;
        final c.a.t.c k;
        final List<U> l;
        c.a.y.b m;

        /* JADX INFO: compiled from: ObservableBufferTimed.java */
        final class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final U f2535a;

            a(U u) {
                this.f2535a = u;
            }

            @Override // java.lang.Runnable
            public void run() {
                synchronized (c.this) {
                    c.this.l.remove(this.f2535a);
                }
                c cVar = c.this;
                cVar.b(this.f2535a, false, cVar.k);
            }
        }

        /* JADX INFO: compiled from: ObservableBufferTimed.java */
        final class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final U f2537a;

            b(U u) {
                this.f2537a = u;
            }

            @Override // java.lang.Runnable
            public void run() {
                synchronized (c.this) {
                    c.this.l.remove(this.f2537a);
                }
                c cVar = c.this;
                cVar.b(this.f2537a, false, cVar.k);
            }
        }

        c(c.a.s<? super U> sVar, Callable<U> callable, long j, long j2, TimeUnit timeUnit, c.a.t.c cVar) {
            super(sVar, new c.a.b0.f.a());
            this.g = callable;
            this.h = j;
            this.i = j2;
            this.j = timeUnit;
            this.k = cVar;
            this.l = new LinkedList();
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
            f();
            this.m.dispose();
            this.k.dispose();
        }

        void f() {
            synchronized (this) {
                this.l.clear();
            }
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // c.a.s
        public void onComplete() {
            ArrayList arrayList;
            synchronized (this) {
                arrayList = new ArrayList(this.l);
                this.l.clear();
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.f1833c.offer((U) ((Collection) it.next()));
            }
            this.f1835e = true;
            if (d()) {
                c.a.b0.j.r.a(this.f1833c, this.f1832b, false, this.k, this);
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f1835e = true;
            f();
            this.f1832b.onError(th);
            this.k.dispose();
        }

        @Override // c.a.s
        public void onNext(T t) {
            synchronized (this) {
                Iterator<U> it = this.l.iterator();
                while (it.hasNext()) {
                    it.next().add(t);
                }
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.m, bVar)) {
                this.m = bVar;
                try {
                    U uCall = this.g.call();
                    c.a.b0.b.b.a(uCall, "The buffer supplied is null");
                    U u = uCall;
                    this.l.add(u);
                    this.f1832b.onSubscribe(this);
                    c.a.t.c cVar = this.k;
                    long j = this.i;
                    cVar.a(this, j, j, this.j);
                    this.k.a(new b(u), this.h, this.j);
                } catch (Throwable th) {
                    c.a.z.b.b(th);
                    bVar.dispose();
                    c.a.b0.a.d.a(th, this.f1832b);
                    this.k.dispose();
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f1834d) {
                return;
            }
            try {
                U uCall = this.g.call();
                c.a.b0.b.b.a(uCall, "The bufferSupplier returned a null buffer");
                U u = uCall;
                synchronized (this) {
                    if (this.f1834d) {
                        return;
                    }
                    this.l.add(u);
                    this.k.a(new a(u), this.h, this.j);
                }
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f1832b.onError(th);
                dispose();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void a(c.a.s<? super U> sVar, U u) {
            sVar.onNext(u);
        }
    }

    public p(c.a.q<T> qVar, long j, long j2, TimeUnit timeUnit, c.a.t tVar, Callable<U> callable, int i, boolean z) {
        super(qVar);
        this.f2530b = j;
        this.f2531c = j2;
        this.f2532d = timeUnit;
        this.f2533e = tVar;
        this.f2534f = callable;
        this.g = i;
        this.h = z;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super U> sVar) {
        if (this.f2530b == this.f2531c && this.g == Integer.MAX_VALUE) {
            this.f1932a.subscribe(new b(new c.a.d0.f(sVar), this.f2534f, this.f2530b, this.f2532d, this.f2533e));
            return;
        }
        c.a.t.c cVarA = this.f2533e.a();
        if (this.f2530b == this.f2531c) {
            this.f1932a.subscribe(new a(new c.a.d0.f(sVar), this.f2534f, this.f2530b, this.f2532d, this.g, this.h, cVarA));
        } else {
            this.f1932a.subscribe(new c(new c.a.d0.f(sVar), this.f2534f, this.f2530b, this.f2531c, this.f2532d, cVarA));
        }
    }
}
