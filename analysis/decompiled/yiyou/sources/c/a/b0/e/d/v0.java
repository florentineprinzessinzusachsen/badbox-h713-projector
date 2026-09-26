package c.a.b0.e.d;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableFlatMap.java */
/* JADX INFO: loaded from: classes.dex */
public final class v0<T, U> extends c.a.b0.e.d.a<T, U> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.n<? super T, ? extends c.a.q<? extends U>> f2786b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f2787c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f2788d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final int f2789e;

    /* JADX INFO: compiled from: ObservableFlatMap.java */
    static final class a<T, U> extends AtomicReference<c.a.y.b> implements c.a.s<U> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final long f2790a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final b<T, U> f2791b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        volatile boolean f2792c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        volatile c.a.b0.c.j<U> f2793d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f2794e;

        a(b<T, U> bVar, long j) {
            this.f2790a = j;
            this.f2791b = bVar;
        }

        public void a() {
            c.a.b0.a.c.a(this);
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2792c = true;
            this.f2791b.c();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (!this.f2791b.h.a(th)) {
                c.a.e0.a.b(th);
                return;
            }
            b<T, U> bVar = this.f2791b;
            if (!bVar.f2797c) {
                bVar.b();
            }
            this.f2792c = true;
            this.f2791b.c();
        }

        @Override // c.a.s
        public void onNext(U u) {
            if (this.f2794e == 0) {
                this.f2791b.a(u, this);
            } else {
                this.f2791b.c();
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.c(this, bVar) && (bVar instanceof c.a.b0.c.e)) {
                c.a.b0.c.e eVar = (c.a.b0.c.e) bVar;
                int iA = eVar.a(7);
                if (iA == 1) {
                    this.f2794e = iA;
                    this.f2793d = eVar;
                    this.f2792c = true;
                    this.f2791b.c();
                    return;
                }
                if (iA == 2) {
                    this.f2794e = iA;
                    this.f2793d = eVar;
                }
            }
        }
    }

    public v0(c.a.q<T> qVar, c.a.a0.n<? super T, ? extends c.a.q<? extends U>> nVar, boolean z, int i, int i2) {
        super(qVar);
        this.f2786b = nVar;
        this.f2787c = z;
        this.f2788d = i;
        this.f2789e = i2;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super U> sVar) {
        if (w2.a(this.f1932a, sVar, this.f2786b)) {
            return;
        }
        this.f1932a.subscribe(new b(sVar, this.f2786b, this.f2787c, this.f2788d, this.f2789e));
    }

    /* JADX INFO: compiled from: ObservableFlatMap.java */
    static final class b<T, U> extends AtomicInteger implements c.a.y.b, c.a.s<T> {
        static final a<?, ?>[] q = new a[0];
        static final a<?, ?>[] r = new a[0];

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super U> f2795a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.n<? super T, ? extends c.a.q<? extends U>> f2796b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final boolean f2797c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final int f2798d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final int f2799e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        volatile c.a.b0.c.i<U> f2800f;
        volatile boolean g;
        final c.a.b0.j.c h = new c.a.b0.j.c();
        volatile boolean i;
        final AtomicReference<a<?, ?>[]> j;
        c.a.y.b k;
        long l;
        long m;
        int n;
        Queue<c.a.q<? extends U>> o;
        int p;

        b(c.a.s<? super U> sVar, c.a.a0.n<? super T, ? extends c.a.q<? extends U>> nVar, boolean z, int i, int i2) {
            this.f2795a = sVar;
            this.f2796b = nVar;
            this.f2797c = z;
            this.f2798d = i;
            this.f2799e = i2;
            if (i != Integer.MAX_VALUE) {
                this.o = new ArrayDeque(i);
            }
            this.j = new AtomicReference<>(q);
        }

        void a(c.a.q<? extends U> qVar) {
            c.a.q<? extends U> qVarPoll;
            while (qVar instanceof Callable) {
                if (!a((Callable) qVar) || this.f2798d == Integer.MAX_VALUE) {
                    return;
                }
                boolean z = false;
                synchronized (this) {
                    qVarPoll = this.o.poll();
                    if (qVarPoll == null) {
                        this.p--;
                        z = true;
                    }
                }
                if (z) {
                    c();
                    return;
                }
                qVar = qVarPoll;
            }
            long j = this.l;
            this.l = 1 + j;
            a<T, U> aVar = new a<>(this, j);
            if (a(aVar)) {
                qVar.subscribe(aVar);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        void b(a<T, U> aVar) {
            a<?, ?>[] aVarArr;
            a<?, ?>[] aVarArr2;
            do {
                aVarArr = this.j.get();
                int length = aVarArr.length;
                if (length == 0) {
                    return;
                }
                int i = -1;
                for (int i2 = 0; i2 < length; i2++) {
                    if (aVarArr[i2] == aVar) {
                        i = i2;
                        break;
                    }
                }
                if (i < 0) {
                    return;
                }
                if (length == 1) {
                    aVarArr2 = q;
                } else {
                    a<?, ?>[] aVarArr3 = new a[length - 1];
                    System.arraycopy(aVarArr, 0, aVarArr3, 0, i);
                    System.arraycopy(aVarArr, i + 1, aVarArr3, i, (length - i) - 1);
                    aVarArr2 = aVarArr3;
                }
            } while (!this.j.compareAndSet(aVarArr, aVarArr2));
        }

        void c() {
            if (getAndIncrement() == 0) {
                d();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        void d() {
            int size;
            boolean z;
            c.a.s<? super U> sVar = this.f2795a;
            int iAddAndGet = 1;
            while (!a()) {
                c.a.b0.c.i<U> iVar = this.f2800f;
                if (iVar != null) {
                    while (!a()) {
                        U uPoll = iVar.poll();
                        if (uPoll != null) {
                            sVar.onNext(uPoll);
                        } else if (uPoll == null) {
                        }
                    }
                    return;
                }
                boolean z2 = this.g;
                c.a.b0.c.i<U> iVar2 = this.f2800f;
                a<?, ?>[] aVarArr = this.j.get();
                int length = aVarArr.length;
                if (this.f2798d != Integer.MAX_VALUE) {
                    synchronized (this) {
                        size = this.o.size();
                    }
                } else {
                    size = 0;
                }
                if (z2 && ((iVar2 == null || iVar2.isEmpty()) && length == 0 && size == 0)) {
                    Throwable thA = this.h.a();
                    if (thA != c.a.b0.j.j.f3091a) {
                        if (thA == null) {
                            sVar.onComplete();
                            return;
                        } else {
                            sVar.onError(thA);
                            return;
                        }
                    }
                    return;
                }
                if (length != 0) {
                    long j = this.m;
                    int i = this.n;
                    if (length <= i || aVarArr[i].f2790a != j) {
                        if (length <= i) {
                            i = 0;
                        }
                        int i2 = i;
                        for (int i3 = 0; i3 < length && aVarArr[i2].f2790a != j; i3++) {
                            i2++;
                            if (i2 == length) {
                                i2 = 0;
                            }
                        }
                        this.n = i2;
                        this.m = aVarArr[i2].f2790a;
                        i = i2;
                    }
                    int i4 = 0;
                    z = false;
                    while (i4 < length) {
                        if (a()) {
                            return;
                        }
                        a<T, U> aVar = aVarArr[i];
                        while (!a()) {
                            c.a.b0.c.j<U> jVar = aVar.f2793d;
                            if (jVar != null) {
                                do {
                                    try {
                                        U uPoll2 = jVar.poll();
                                        if (uPoll2 != null) {
                                            sVar.onNext(uPoll2);
                                        } else if (uPoll2 == null) {
                                        }
                                    } catch (Throwable th) {
                                        c.a.z.b.b(th);
                                        aVar.a();
                                        this.h.a(th);
                                        if (a()) {
                                            return;
                                        }
                                        b(aVar);
                                        i4++;
                                        z = true;
                                    }
                                } while (!a());
                                return;
                            }
                            boolean z3 = aVar.f2792c;
                            c.a.b0.c.j<U> jVar2 = aVar.f2793d;
                            if (z3 && (jVar2 == null || jVar2.isEmpty())) {
                                b(aVar);
                                if (a()) {
                                    return;
                                } else {
                                    z = true;
                                }
                            }
                            i++;
                            if (i == length) {
                                i = 0;
                            }
                            i4++;
                        }
                        return;
                    }
                    this.n = i;
                    this.m = aVarArr[i].f2790a;
                } else {
                    z = false;
                }
                if (!z) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else if (this.f2798d != Integer.MAX_VALUE) {
                    synchronized (this) {
                        c.a.q<? extends U> qVarPoll = this.o.poll();
                        if (qVarPoll == null) {
                            this.p--;
                        } else {
                            a(qVarPoll);
                        }
                    }
                } else {
                    continue;
                }
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            Throwable thA;
            if (this.i) {
                return;
            }
            this.i = true;
            if (!b() || (thA = this.h.a()) == null || thA == c.a.b0.j.j.f3091a) {
                return;
            }
            c.a.e0.a.b(thA);
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.g) {
                return;
            }
            this.g = true;
            c();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.g) {
                c.a.e0.a.b(th);
            } else if (!this.h.a(th)) {
                c.a.e0.a.b(th);
            } else {
                this.g = true;
                c();
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.g) {
                return;
            }
            try {
                c.a.q<? extends U> qVarApply = this.f2796b.apply(t);
                c.a.b0.b.b.a(qVarApply, "The mapper returned a null ObservableSource");
                c.a.q<? extends U> qVar = qVarApply;
                if (this.f2798d != Integer.MAX_VALUE) {
                    synchronized (this) {
                        if (this.p == this.f2798d) {
                            this.o.offer(qVar);
                            return;
                        }
                        this.p++;
                    }
                }
                a(qVar);
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.k.dispose();
                onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.k, bVar)) {
                this.k = bVar;
                this.f2795a.onSubscribe(this);
            }
        }

        boolean b() {
            a<?, ?>[] andSet;
            this.k.dispose();
            a<?, ?>[] aVarArr = this.j.get();
            a<?, ?>[] aVarArr2 = r;
            if (aVarArr == aVarArr2 || (andSet = this.j.getAndSet(aVarArr2)) == r) {
                return false;
            }
            for (a<?, ?> aVar : andSet) {
                aVar.a();
            }
            return true;
        }

        boolean a(a<T, U> aVar) {
            a<?, ?>[] aVarArr;
            a[] aVarArr2;
            do {
                aVarArr = this.j.get();
                if (aVarArr == r) {
                    aVar.a();
                    return false;
                }
                int length = aVarArr.length;
                aVarArr2 = new a[length + 1];
                System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
                aVarArr2[length] = aVar;
            } while (!this.j.compareAndSet(aVarArr, (a<?, ?>[]) aVarArr2));
            return true;
        }

        boolean a(Callable<? extends U> callable) {
            try {
                U uCall = callable.call();
                if (uCall == null) {
                    return true;
                }
                if (get() == 0 && compareAndSet(0, 1)) {
                    this.f2795a.onNext(uCall);
                    if (decrementAndGet() == 0) {
                        return true;
                    }
                } else {
                    c.a.b0.c.i<U> bVar = this.f2800f;
                    if (bVar == null) {
                        int i = this.f2798d;
                        if (i == Integer.MAX_VALUE) {
                            bVar = new c.a.b0.f.c<>(this.f2799e);
                        } else {
                            bVar = new c.a.b0.f.b(i);
                        }
                        this.f2800f = bVar;
                    }
                    if (!bVar.offer(uCall)) {
                        onError(new IllegalStateException("Scalar queue full?!"));
                        return true;
                    }
                    if (getAndIncrement() != 0) {
                        return false;
                    }
                }
                d();
                return true;
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.h.a(th);
                c();
                return true;
            }
        }

        void a(U u, a<T, U> aVar) {
            if (get() == 0 && compareAndSet(0, 1)) {
                this.f2795a.onNext(u);
                if (decrementAndGet() == 0) {
                    return;
                }
            } else {
                c.a.b0.c.j cVar = aVar.f2793d;
                if (cVar == null) {
                    cVar = new c.a.b0.f.c(this.f2799e);
                    aVar.f2793d = cVar;
                }
                cVar.offer(u);
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            d();
        }

        boolean a() {
            if (this.i) {
                return true;
            }
            Throwable th = this.h.get();
            if (this.f2797c || th == null) {
                return false;
            }
            b();
            Throwable thA = this.h.a();
            if (thA != c.a.b0.j.j.f3091a) {
                this.f2795a.onError(thA);
            }
            return true;
        }
    }
}
