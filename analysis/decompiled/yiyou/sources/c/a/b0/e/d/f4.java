package c.a.b0.e.d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableWindowBoundarySelector.java */
/* JADX INFO: loaded from: classes.dex */
public final class f4<T, B, V> extends c.a.b0.e.d.a<T, c.a.l<T>> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.q<B> f2154b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.n<? super B, ? extends c.a.q<V>> f2155c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f2156d;

    /* JADX INFO: compiled from: ObservableWindowBoundarySelector.java */
    static final class a<T, V> extends c.a.d0.c<V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c<T, ?, V> f2157b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.g0.d<T> f2158c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f2159d;

        a(c<T, ?, V> cVar, c.a.g0.d<T> dVar) {
            this.f2157b = cVar;
            this.f2158c = dVar;
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2159d) {
                return;
            }
            this.f2159d = true;
            this.f2157b.a((a) this);
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2159d) {
                c.a.e0.a.b(th);
            } else {
                this.f2159d = true;
                this.f2157b.a(th);
            }
        }

        @Override // c.a.s
        public void onNext(V v) {
            dispose();
            onComplete();
        }
    }

    /* JADX INFO: compiled from: ObservableWindowBoundarySelector.java */
    static final class b<T, B> extends c.a.d0.c<B> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c<T, B, ?> f2160b;

        b(c<T, B, ?> cVar) {
            this.f2160b = cVar;
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2160b.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2160b.a(th);
        }

        @Override // c.a.s
        public void onNext(B b2) {
            this.f2160b.a(b2);
        }
    }

    /* JADX INFO: compiled from: ObservableWindowBoundarySelector.java */
    static final class d<T, B> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.g0.d<T> f2161a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final B f2162b;

        d(c.a.g0.d<T> dVar, B b2) {
            this.f2161a = dVar;
            this.f2162b = b2;
        }
    }

    public f4(c.a.q<T> qVar, c.a.q<B> qVar2, c.a.a0.n<? super B, ? extends c.a.q<V>> nVar, int i) {
        super(qVar);
        this.f2154b = qVar2;
        this.f2155c = nVar;
        this.f2156d = i;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super c.a.l<T>> sVar) {
        this.f1932a.subscribe(new c(new c.a.d0.f(sVar), this.f2154b, this.f2155c, this.f2156d));
    }

    /* JADX INFO: compiled from: ObservableWindowBoundarySelector.java */
    static final class c<T, B, V> extends c.a.b0.d.p<T, Object, c.a.l<T>> implements c.a.y.b {
        final c.a.q<B> g;
        final c.a.a0.n<? super B, ? extends c.a.q<V>> h;
        final int i;
        final c.a.y.a j;
        c.a.y.b k;
        final AtomicReference<c.a.y.b> l;
        final List<c.a.g0.d<T>> m;
        final AtomicLong n;

        c(c.a.s<? super c.a.l<T>> sVar, c.a.q<B> qVar, c.a.a0.n<? super B, ? extends c.a.q<V>> nVar, int i) {
            super(sVar, new c.a.b0.f.a());
            this.l = new AtomicReference<>();
            this.n = new AtomicLong();
            this.g = qVar;
            this.h = nVar;
            this.i = i;
            this.j = new c.a.y.a();
            this.m = new ArrayList();
            this.n.lazySet(1L);
        }

        @Override // c.a.b0.d.p, c.a.b0.j.o
        public void a(c.a.s<? super c.a.l<T>> sVar, Object obj) {
        }

        void a(Throwable th) {
            this.k.dispose();
            this.j.dispose();
            onError(th);
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f1834d = true;
        }

        void f() {
            this.j.dispose();
            c.a.b0.a.c.a(this.l);
        }

        /* JADX WARN: Multi-variable type inference failed */
        void g() {
            c.a.b0.f.a aVar = (c.a.b0.f.a) this.f1833c;
            c.a.s<? super V> sVar = this.f1832b;
            List<c.a.g0.d<T>> list = this.m;
            int iA = 1;
            while (true) {
                boolean z = this.f1835e;
                Object objPoll = aVar.poll();
                boolean z2 = objPoll == null;
                if (z && z2) {
                    f();
                    Throwable th = this.f1836f;
                    if (th != null) {
                        Iterator<c.a.g0.d<T>> it = list.iterator();
                        while (it.hasNext()) {
                            it.next().onError(th);
                        }
                    } else {
                        Iterator<c.a.g0.d<T>> it2 = list.iterator();
                        while (it2.hasNext()) {
                            it2.next().onComplete();
                        }
                    }
                    list.clear();
                    return;
                }
                if (z2) {
                    iA = a(-iA);
                    if (iA == 0) {
                        return;
                    }
                } else if (objPoll instanceof d) {
                    d dVar = (d) objPoll;
                    c.a.g0.d<T> dVar2 = dVar.f2161a;
                    if (dVar2 != null) {
                        if (list.remove(dVar2)) {
                            dVar.f2161a.onComplete();
                            if (this.n.decrementAndGet() == 0) {
                                f();
                                return;
                            }
                        } else {
                            continue;
                        }
                    } else if (!this.f1834d) {
                        c.a.g0.d<T> dVarA = c.a.g0.d.a(this.i);
                        list.add(dVarA);
                        sVar.onNext(dVarA);
                        try {
                            c.a.q<V> qVarApply = this.h.apply(dVar.f2162b);
                            c.a.b0.b.b.a(qVarApply, "The ObservableSource supplied is null");
                            c.a.q<V> qVar = qVarApply;
                            a aVar2 = new a(this, dVarA);
                            if (this.j.c(aVar2)) {
                                this.n.getAndIncrement();
                                qVar.subscribe(aVar2);
                            }
                        } catch (Throwable th2) {
                            c.a.z.b.b(th2);
                            this.f1834d = true;
                            sVar.onError(th2);
                        }
                    }
                } else {
                    for (c.a.g0.d<T> dVar3 : list) {
                        c.a.b0.j.n.b(objPoll);
                        dVar3.onNext(objPoll);
                    }
                }
            }
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f1835e) {
                return;
            }
            this.f1835e = true;
            if (d()) {
                g();
            }
            if (this.n.decrementAndGet() == 0) {
                this.j.dispose();
            }
            this.f1832b.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f1835e) {
                c.a.e0.a.b(th);
                return;
            }
            this.f1836f = th;
            this.f1835e = true;
            if (d()) {
                g();
            }
            if (this.n.decrementAndGet() == 0) {
                this.j.dispose();
            }
            this.f1832b.onError(th);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // c.a.s
        public void onNext(T t) {
            if (e()) {
                Iterator<c.a.g0.d<T>> it = this.m.iterator();
                while (it.hasNext()) {
                    it.next().onNext(t);
                }
                if (a(-1) == 0) {
                    return;
                }
            } else {
                c.a.b0.c.i<U> iVar = this.f1833c;
                c.a.b0.j.n.e(t);
                iVar.offer((U) t);
                if (!d()) {
                    return;
                }
            }
            g();
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.k, bVar)) {
                this.k = bVar;
                this.f1832b.onSubscribe(this);
                if (this.f1834d) {
                    return;
                }
                b bVar2 = new b(this);
                if (this.l.compareAndSet(null, bVar2)) {
                    this.n.getAndIncrement();
                    this.g.subscribe(bVar2);
                }
            }
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        void a(B b2) {
            this.f1833c.offer((U) new d(null, b2));
            if (d()) {
                g();
            }
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        void a(a<T, V> aVar) {
            this.j.a(aVar);
            this.f1833c.offer((U) new d(aVar.f2158c, null));
            if (d()) {
                g();
            }
        }
    }
}
