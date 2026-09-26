package c.a.b0.e.d;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableBufferBoundary.java */
/* JADX INFO: loaded from: classes.dex */
public final class m<T, U extends Collection<? super T>, Open, Close> extends c.a.b0.e.d.a<T, U> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Callable<U> f2419b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.q<? extends Open> f2420c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final c.a.a0.n<? super Open, ? extends c.a.q<? extends Close>> f2421d;

    /* JADX INFO: compiled from: ObservableBufferBoundary.java */
    static final class b<T, C extends Collection<? super T>> extends AtomicReference<c.a.y.b> implements c.a.s<Object>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final a<T, C, ?, ?> f2429a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f2430b;

        b(a<T, C, ?, ?> aVar, long j) {
            this.f2429a = aVar;
            this.f2430b = j;
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
        }

        @Override // c.a.s
        public void onComplete() {
            c.a.y.b bVar = get();
            c.a.b0.a.c cVar = c.a.b0.a.c.DISPOSED;
            if (bVar != cVar) {
                lazySet(cVar);
                this.f2429a.a(this, this.f2430b);
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            c.a.y.b bVar = get();
            c.a.b0.a.c cVar = c.a.b0.a.c.DISPOSED;
            if (bVar == cVar) {
                c.a.e0.a.b(th);
            } else {
                lazySet(cVar);
                this.f2429a.a(this, th);
            }
        }

        @Override // c.a.s
        public void onNext(Object obj) {
            c.a.y.b bVar = get();
            c.a.b0.a.c cVar = c.a.b0.a.c.DISPOSED;
            if (bVar != cVar) {
                lazySet(cVar);
                bVar.dispose();
                this.f2429a.a(this, this.f2430b);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.c(this, bVar);
        }
    }

    public m(c.a.q<T> qVar, c.a.q<? extends Open> qVar2, c.a.a0.n<? super Open, ? extends c.a.q<? extends Close>> nVar, Callable<U> callable) {
        super(qVar);
        this.f2420c = qVar2;
        this.f2421d = nVar;
        this.f2419b = callable;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super U> sVar) {
        a aVar = new a(sVar, this.f2420c, this.f2421d, this.f2419b);
        sVar.onSubscribe(aVar);
        this.f1932a.subscribe(aVar);
    }

    /* JADX INFO: compiled from: ObservableBufferBoundary.java */
    static final class a<T, C extends Collection<? super T>, Open, Close> extends AtomicInteger implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super C> f2422a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Callable<C> f2423b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.q<? extends Open> f2424c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.a0.n<? super Open, ? extends c.a.q<? extends Close>> f2425d;
        volatile boolean h;
        volatile boolean j;
        long k;
        final c.a.b0.f.c<C> i = new c.a.b0.f.c<>(c.a.l.bufferSize());

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final c.a.y.a f2426e = new c.a.y.a();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final AtomicReference<c.a.y.b> f2427f = new AtomicReference<>();
        Map<Long, C> l = new LinkedHashMap();
        final c.a.b0.j.c g = new c.a.b0.j.c();

        /* JADX INFO: renamed from: c.a.b0.e.d.m$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableBufferBoundary.java */
        static final class C0060a<Open> extends AtomicReference<c.a.y.b> implements c.a.s<Open>, c.a.y.b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final a<?, ?, Open, ?> f2428a;

            C0060a(a<?, ?, Open, ?> aVar) {
                this.f2428a = aVar;
            }

            @Override // c.a.y.b
            public void dispose() {
                c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
            }

            @Override // c.a.s
            public void onComplete() {
                lazySet(c.a.b0.a.c.DISPOSED);
                this.f2428a.a((C0060a) this);
            }

            @Override // c.a.s
            public void onError(Throwable th) {
                lazySet(c.a.b0.a.c.DISPOSED);
                this.f2428a.a(this, th);
            }

            @Override // c.a.s
            public void onNext(Open open) {
                this.f2428a.a(open);
            }

            @Override // c.a.s
            public void onSubscribe(c.a.y.b bVar) {
                c.a.b0.a.c.c(this, bVar);
            }
        }

        a(c.a.s<? super C> sVar, c.a.q<? extends Open> qVar, c.a.a0.n<? super Open, ? extends c.a.q<? extends Close>> nVar, Callable<C> callable) {
            this.f2422a = sVar;
            this.f2423b = callable;
            this.f2424c = qVar;
            this.f2425d = nVar;
        }

        void a(Open open) {
            try {
                C cCall = this.f2423b.call();
                c.a.b0.b.b.a(cCall, "The bufferSupplier returned a null Collection");
                C c2 = cCall;
                c.a.q<? extends Close> qVarApply = this.f2425d.apply(open);
                c.a.b0.b.b.a(qVarApply, "The bufferClose returned a null ObservableSource");
                c.a.q<? extends Close> qVar = qVarApply;
                long j = this.k;
                this.k = 1 + j;
                synchronized (this) {
                    Map<Long, C> map = this.l;
                    if (map == null) {
                        return;
                    }
                    map.put(Long.valueOf(j), c2);
                    b bVar = new b(this, j);
                    this.f2426e.c(bVar);
                    qVar.subscribe(bVar);
                }
            } catch (Throwable th) {
                c.a.z.b.b(th);
                c.a.b0.a.c.a(this.f2427f);
                onError(th);
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            if (c.a.b0.a.c.a(this.f2427f)) {
                this.j = true;
                this.f2426e.dispose();
                synchronized (this) {
                    this.l = null;
                }
                if (getAndIncrement() != 0) {
                    this.i.clear();
                }
            }
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2426e.dispose();
            synchronized (this) {
                Map<Long, C> map = this.l;
                if (map == null) {
                    return;
                }
                Iterator<C> it = map.values().iterator();
                while (it.hasNext()) {
                    this.i.offer(it.next());
                }
                this.l = null;
                this.h = true;
                a();
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (!this.g.a(th)) {
                c.a.e0.a.b(th);
                return;
            }
            this.f2426e.dispose();
            synchronized (this) {
                this.l = null;
            }
            this.h = true;
            a();
        }

        @Override // c.a.s
        public void onNext(T t) {
            synchronized (this) {
                Map<Long, C> map = this.l;
                if (map == null) {
                    return;
                }
                Iterator<C> it = map.values().iterator();
                while (it.hasNext()) {
                    it.next().add(t);
                }
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.c(this.f2427f, bVar)) {
                C0060a c0060a = new C0060a(this);
                this.f2426e.c(c0060a);
                this.f2424c.subscribe(c0060a);
            }
        }

        void a(C0060a<Open> c0060a) {
            this.f2426e.a(c0060a);
            if (this.f2426e.b() == 0) {
                c.a.b0.a.c.a(this.f2427f);
                this.h = true;
                a();
            }
        }

        void a(b<T, C> bVar, long j) {
            boolean z;
            this.f2426e.a(bVar);
            if (this.f2426e.b() == 0) {
                c.a.b0.a.c.a(this.f2427f);
                z = true;
            } else {
                z = false;
            }
            synchronized (this) {
                if (this.l == null) {
                    return;
                }
                this.i.offer(this.l.remove(Long.valueOf(j)));
                if (z) {
                    this.h = true;
                }
                a();
            }
        }

        void a(c.a.y.b bVar, Throwable th) {
            c.a.b0.a.c.a(this.f2427f);
            this.f2426e.a(bVar);
            onError(th);
        }

        void a() {
            if (getAndIncrement() != 0) {
                return;
            }
            c.a.s<? super C> sVar = this.f2422a;
            c.a.b0.f.c<C> cVar = this.i;
            int iAddAndGet = 1;
            while (!this.j) {
                boolean z = this.h;
                if (z && this.g.get() != null) {
                    cVar.clear();
                    sVar.onError(this.g.a());
                    return;
                }
                C cPoll = cVar.poll();
                boolean z2 = cPoll == null;
                if (z && z2) {
                    sVar.onComplete();
                    return;
                } else if (z2) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else {
                    sVar.onNext(cPoll);
                }
            }
            cVar.clear();
        }
    }
}
