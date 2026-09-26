package c.a.b0.e.d;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableReplay.java */
/* JADX INFO: loaded from: classes.dex */
public final class q2<T> extends c.a.c0.a<T> implements c.a.b0.c.c<T>, c.a.y.b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final b f2602e = new o();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<T> f2603a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final AtomicReference<j<T>> f2604b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final b<T> f2605c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final c.a.q<T> f2606d;

    /* JADX INFO: compiled from: ObservableReplay.java */
    static abstract class a<T> extends AtomicReference<f> implements h<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        f f2607a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f2608b;

        a() {
            f fVar = new f(null);
            this.f2607a = fVar;
            set(fVar);
        }

        final void a(f fVar) {
            this.f2607a.set(fVar);
            this.f2607a = fVar;
            this.f2608b++;
        }

        Object b(Object obj) {
            return obj;
        }

        final void b(f fVar) {
            set(fVar);
        }

        Object c(Object obj) {
            return obj;
        }

        final void c() {
            f fVar = get().get();
            this.f2608b--;
            b(fVar);
        }

        final void d() {
            f fVar = get();
            if (fVar.f2616a != null) {
                f fVar2 = new f(null);
                fVar2.lazySet(fVar.get());
                set(fVar2);
            }
        }

        abstract void e();

        void f() {
            d();
        }

        f b() {
            return get();
        }

        @Override // c.a.b0.e.d.q2.h
        public final void a(T t) {
            c.a.b0.j.n.e(t);
            a(new f(b(t)));
            e();
        }

        @Override // c.a.b0.e.d.q2.h
        public final void a(Throwable th) {
            a(new f(b(c.a.b0.j.n.a(th))));
            f();
        }

        @Override // c.a.b0.e.d.q2.h
        public final void a() {
            a(new f(b(c.a.b0.j.n.a())));
            f();
        }

        @Override // c.a.b0.e.d.q2.h
        public final void a(d<T> dVar) {
            if (dVar.getAndIncrement() != 0) {
                return;
            }
            int iAddAndGet = 1;
            do {
                f fVarB = (f) dVar.a();
                if (fVarB == null) {
                    fVarB = b();
                    dVar.f2612c = fVarB;
                }
                while (!dVar.b()) {
                    f fVar = fVarB.get();
                    if (fVar != null) {
                        if (c.a.b0.j.n.a(c(fVar.f2616a), dVar.f2611b)) {
                            dVar.f2612c = null;
                            return;
                        }
                        fVarB = fVar;
                    } else {
                        dVar.f2612c = fVarB;
                        iAddAndGet = dVar.addAndGet(-iAddAndGet);
                    }
                }
                return;
            } while (iAddAndGet != 0);
        }
    }

    /* JADX INFO: compiled from: ObservableReplay.java */
    interface b<T> {
        h<T> call();
    }

    /* JADX INFO: compiled from: ObservableReplay.java */
    static final class c<R> implements c.a.a0.f<c.a.y.b> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final m4<R> f2609a;

        c(m4<R> m4Var) {
            this.f2609a = m4Var;
        }

        @Override // c.a.a0.f
        public void a(c.a.y.b bVar) {
            this.f2609a.a(bVar);
        }
    }

    /* JADX INFO: compiled from: ObservableReplay.java */
    static final class d<T> extends AtomicInteger implements c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final j<T> f2610a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.s<? super T> f2611b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        Object f2612c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        volatile boolean f2613d;

        d(j<T> jVar, c.a.s<? super T> sVar) {
            this.f2610a = jVar;
            this.f2611b = sVar;
        }

        <U> U a() {
            return (U) this.f2612c;
        }

        public boolean b() {
            return this.f2613d;
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.f2613d) {
                return;
            }
            this.f2613d = true;
            this.f2610a.b(this);
        }
    }

    /* JADX INFO: compiled from: ObservableReplay.java */
    static final class e<R, U> extends c.a.l<R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Callable<? extends c.a.c0.a<U>> f2614a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final c.a.a0.n<? super c.a.l<U>, ? extends c.a.q<R>> f2615b;

        e(Callable<? extends c.a.c0.a<U>> callable, c.a.a0.n<? super c.a.l<U>, ? extends c.a.q<R>> nVar) {
            this.f2614a = callable;
            this.f2615b = nVar;
        }

        @Override // c.a.l
        protected void subscribeActual(c.a.s<? super R> sVar) {
            try {
                c.a.c0.a<U> aVarCall = this.f2614a.call();
                c.a.b0.b.b.a(aVarCall, "The connectableFactory returned a null ConnectableObservable");
                c.a.c0.a<U> aVar = aVarCall;
                c.a.q<R> qVarApply = this.f2615b.apply(aVar);
                c.a.b0.b.b.a(qVarApply, "The selector returned a null ObservableSource");
                c.a.q<R> qVar = qVarApply;
                m4 m4Var = new m4(sVar);
                qVar.subscribe(m4Var);
                aVar.a(new c(m4Var));
            } catch (Throwable th) {
                c.a.z.b.b(th);
                c.a.b0.a.d.a(th, sVar);
            }
        }
    }

    /* JADX INFO: compiled from: ObservableReplay.java */
    static final class f extends AtomicReference<f> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Object f2616a;

        f(Object obj) {
            this.f2616a = obj;
        }
    }

    /* JADX INFO: compiled from: ObservableReplay.java */
    static final class g<T> extends c.a.c0.a<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c.a.c0.a<T> f2617a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final c.a.l<T> f2618b;

        g(c.a.c0.a<T> aVar, c.a.l<T> lVar) {
            this.f2617a = aVar;
            this.f2618b = lVar;
        }

        @Override // c.a.c0.a
        public void a(c.a.a0.f<? super c.a.y.b> fVar) {
            this.f2617a.a(fVar);
        }

        @Override // c.a.l
        protected void subscribeActual(c.a.s<? super T> sVar) {
            this.f2618b.subscribe(sVar);
        }
    }

    /* JADX INFO: compiled from: ObservableReplay.java */
    interface h<T> {
        void a();

        void a(d<T> dVar);

        void a(T t);

        void a(Throwable th);
    }

    /* JADX INFO: compiled from: ObservableReplay.java */
    static final class i<T> implements b<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f2619a;

        i(int i) {
            this.f2619a = i;
        }

        @Override // c.a.b0.e.d.q2.b
        public h<T> call() {
            return new n(this.f2619a);
        }
    }

    /* JADX INFO: compiled from: ObservableReplay.java */
    static final class j<T> extends AtomicReference<c.a.y.b> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        static final d[] f2620e = new d[0];

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        static final d[] f2621f = new d[0];

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final h<T> f2622a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f2623b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final AtomicReference<d[]> f2624c = new AtomicReference<>(f2620e);

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final AtomicBoolean f2625d = new AtomicBoolean();

        j(h<T> hVar) {
            this.f2622a = hVar;
        }

        public boolean a() {
            return this.f2624c.get() == f2621f;
        }

        void b(d<T> dVar) {
            d[] dVarArr;
            d[] dVarArr2;
            do {
                dVarArr = this.f2624c.get();
                int length = dVarArr.length;
                if (length == 0) {
                    return;
                }
                int i = -1;
                for (int i2 = 0; i2 < length; i2++) {
                    if (dVarArr[i2].equals(dVar)) {
                        i = i2;
                        break;
                    }
                }
                if (i < 0) {
                    return;
                }
                if (length == 1) {
                    dVarArr2 = f2620e;
                } else {
                    d[] dVarArr3 = new d[length - 1];
                    System.arraycopy(dVarArr, 0, dVarArr3, 0, i);
                    System.arraycopy(dVarArr, i + 1, dVarArr3, i, (length - i) - 1);
                    dVarArr2 = dVarArr3;
                }
            } while (!this.f2624c.compareAndSet(dVarArr, dVarArr2));
        }

        void c() {
            for (d<T> dVar : this.f2624c.getAndSet(f2621f)) {
                this.f2622a.a((d) dVar);
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2624c.set(f2621f);
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2623b) {
                return;
            }
            this.f2623b = true;
            this.f2622a.a();
            c();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2623b) {
                c.a.e0.a.b(th);
                return;
            }
            this.f2623b = true;
            this.f2622a.a(th);
            c();
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2623b) {
                return;
            }
            this.f2622a.a(t);
            b();
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.c(this, bVar)) {
                b();
            }
        }

        boolean a(d<T> dVar) {
            d[] dVarArr;
            d[] dVarArr2;
            do {
                dVarArr = this.f2624c.get();
                if (dVarArr == f2621f) {
                    return false;
                }
                int length = dVarArr.length;
                dVarArr2 = new d[length + 1];
                System.arraycopy(dVarArr, 0, dVarArr2, 0, length);
                dVarArr2[length] = dVar;
            } while (!this.f2624c.compareAndSet(dVarArr, dVarArr2));
            return true;
        }

        void b() {
            for (d<T> dVar : this.f2624c.get()) {
                this.f2622a.a((d) dVar);
            }
        }
    }

    /* JADX INFO: compiled from: ObservableReplay.java */
    static final class k<T> implements c.a.q<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AtomicReference<j<T>> f2626a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final b<T> f2627b;

        k(AtomicReference<j<T>> atomicReference, b<T> bVar) {
            this.f2626a = atomicReference;
            this.f2627b = bVar;
        }

        @Override // c.a.q
        public void subscribe(c.a.s<? super T> sVar) {
            j<T> jVar;
            while (true) {
                jVar = this.f2626a.get();
                if (jVar != null) {
                    break;
                }
                j<T> jVar2 = new j<>(this.f2627b.call());
                if (this.f2626a.compareAndSet(null, jVar2)) {
                    jVar = jVar2;
                    break;
                }
            }
            d<T> dVar = new d<>(jVar, sVar);
            sVar.onSubscribe(dVar);
            jVar.a(dVar);
            if (dVar.b()) {
                jVar.b(dVar);
            } else {
                jVar.f2622a.a((d) dVar);
            }
        }
    }

    /* JADX INFO: compiled from: ObservableReplay.java */
    static final class l<T> implements b<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f2628a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final long f2629b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final TimeUnit f2630c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final c.a.t f2631d;

        l(int i, long j, TimeUnit timeUnit, c.a.t tVar) {
            this.f2628a = i;
            this.f2629b = j;
            this.f2630c = timeUnit;
            this.f2631d = tVar;
        }

        @Override // c.a.b0.e.d.q2.b
        public h<T> call() {
            return new m(this.f2628a, this.f2629b, this.f2630c, this.f2631d);
        }
    }

    /* JADX INFO: compiled from: ObservableReplay.java */
    static final class m<T> extends a<T> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.t f2632c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final long f2633d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final TimeUnit f2634e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final int f2635f;

        m(int i, long j, TimeUnit timeUnit, c.a.t tVar) {
            this.f2632c = tVar;
            this.f2635f = i;
            this.f2633d = j;
            this.f2634e = timeUnit;
        }

        @Override // c.a.b0.e.d.q2.a
        Object b(Object obj) {
            return new c.a.f0.c(obj, this.f2632c.a(this.f2634e), this.f2634e);
        }

        @Override // c.a.b0.e.d.q2.a
        Object c(Object obj) {
            return ((c.a.f0.c) obj).b();
        }

        @Override // c.a.b0.e.d.q2.a
        void e() {
            f fVar;
            long jA = this.f2632c.a(this.f2634e) - this.f2633d;
            f fVar2 = get();
            f fVar3 = fVar2.get();
            int i = 0;
            while (true) {
                f fVar4 = fVar3;
                fVar = fVar2;
                fVar2 = fVar4;
                if (fVar2 != null) {
                    int i2 = this.f2608b;
                    if (i2 <= this.f2635f) {
                        if (((c.a.f0.c) fVar2.f2616a).a() > jA) {
                            break;
                        }
                        i++;
                        this.f2608b--;
                        fVar3 = fVar2.get();
                    } else {
                        i++;
                        this.f2608b = i2 - 1;
                        fVar3 = fVar2.get();
                    }
                } else {
                    break;
                }
            }
            if (i != 0) {
                b(fVar);
            }
        }

        @Override // c.a.b0.e.d.q2.a
        void f() {
            f fVar;
            long jA = this.f2632c.a(this.f2634e) - this.f2633d;
            f fVar2 = get();
            f fVar3 = fVar2.get();
            int i = 0;
            while (true) {
                f fVar4 = fVar3;
                fVar = fVar2;
                fVar2 = fVar4;
                if (fVar2 == null || this.f2608b <= 1 || ((c.a.f0.c) fVar2.f2616a).a() > jA) {
                    break;
                }
                i++;
                this.f2608b--;
                fVar3 = fVar2.get();
            }
            if (i != 0) {
                b(fVar);
            }
        }

        @Override // c.a.b0.e.d.q2.a
        f b() {
            f fVar;
            long jA = this.f2632c.a(this.f2634e) - this.f2633d;
            f fVar2 = get();
            f fVar3 = fVar2.get();
            while (true) {
                f fVar4 = fVar3;
                fVar = fVar2;
                fVar2 = fVar4;
                if (fVar2 == null) {
                    break;
                }
                c.a.f0.c cVar = (c.a.f0.c) fVar2.f2616a;
                if (c.a.b0.j.n.c(cVar.b()) || c.a.b0.j.n.d(cVar.b()) || cVar.a() > jA) {
                    break;
                }
                fVar3 = fVar2.get();
            }
            return fVar;
        }
    }

    /* JADX INFO: compiled from: ObservableReplay.java */
    static final class n<T> extends a<T> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int f2636c;

        n(int i) {
            this.f2636c = i;
        }

        @Override // c.a.b0.e.d.q2.a
        void e() {
            if (this.f2608b > this.f2636c) {
                c();
            }
        }
    }

    /* JADX INFO: compiled from: ObservableReplay.java */
    static final class o implements b<Object> {
        o() {
        }

        @Override // c.a.b0.e.d.q2.b
        public h<Object> call() {
            return new p(16);
        }
    }

    private q2(c.a.q<T> qVar, c.a.q<T> qVar2, AtomicReference<j<T>> atomicReference, b<T> bVar) {
        this.f2606d = qVar;
        this.f2603a = qVar2;
        this.f2604b = atomicReference;
        this.f2605c = bVar;
    }

    public static <U, R> c.a.l<R> a(Callable<? extends c.a.c0.a<U>> callable, c.a.a0.n<? super c.a.l<U>, ? extends c.a.q<R>> nVar) {
        return c.a.e0.a.a(new e(callable, nVar));
    }

    @Override // c.a.y.b
    public void dispose() {
        this.f2604b.lazySet(null);
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        this.f2606d.subscribe(sVar);
    }

    /* JADX INFO: compiled from: ObservableReplay.java */
    static final class p<T> extends ArrayList<Object> implements h<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        volatile int f2637a;

        p(int i) {
            super(i);
        }

        @Override // c.a.b0.e.d.q2.h
        public void a(T t) {
            c.a.b0.j.n.e(t);
            add(t);
            this.f2637a++;
        }

        @Override // c.a.b0.e.d.q2.h
        public void a(Throwable th) {
            add(c.a.b0.j.n.a(th));
            this.f2637a++;
        }

        @Override // c.a.b0.e.d.q2.h
        public void a() {
            add(c.a.b0.j.n.a());
            this.f2637a++;
        }

        @Override // c.a.b0.e.d.q2.h
        public void a(d<T> dVar) {
            if (dVar.getAndIncrement() != 0) {
                return;
            }
            c.a.s<? super T> sVar = dVar.f2611b;
            int iAddAndGet = 1;
            while (!dVar.b()) {
                int i = this.f2637a;
                Integer num = (Integer) dVar.a();
                int iIntValue = num != null ? num.intValue() : 0;
                while (iIntValue < i) {
                    if (c.a.b0.j.n.a(get(iIntValue), sVar) || dVar.b()) {
                        return;
                    } else {
                        iIntValue++;
                    }
                }
                dVar.f2612c = Integer.valueOf(iIntValue);
                iAddAndGet = dVar.addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            }
        }
    }

    public static <T> c.a.c0.a<T> a(c.a.c0.a<T> aVar, c.a.t tVar) {
        return c.a.e0.a.a((c.a.c0.a) new g(aVar, aVar.observeOn(tVar)));
    }

    public static <T> c.a.c0.a<T> a(c.a.q<? extends T> qVar) {
        return a(qVar, f2602e);
    }

    public static <T> c.a.c0.a<T> a(c.a.q<T> qVar, int i2) {
        if (i2 == Integer.MAX_VALUE) {
            return a(qVar);
        }
        return a(qVar, new i(i2));
    }

    public static <T> c.a.c0.a<T> a(c.a.q<T> qVar, long j2, TimeUnit timeUnit, c.a.t tVar) {
        return a(qVar, j2, timeUnit, tVar, Integer.MAX_VALUE);
    }

    public static <T> c.a.c0.a<T> a(c.a.q<T> qVar, long j2, TimeUnit timeUnit, c.a.t tVar, int i2) {
        return a(qVar, new l(i2, j2, timeUnit, tVar));
    }

    static <T> c.a.c0.a<T> a(c.a.q<T> qVar, b<T> bVar) {
        AtomicReference atomicReference = new AtomicReference();
        return c.a.e0.a.a((c.a.c0.a) new q2(new k(atomicReference, bVar), qVar, atomicReference, bVar));
    }

    @Override // c.a.c0.a
    public void a(c.a.a0.f<? super c.a.y.b> fVar) {
        j<T> jVar;
        while (true) {
            jVar = this.f2604b.get();
            if (jVar != null && !jVar.a()) {
                break;
            }
            j<T> jVar2 = new j<>(this.f2605c.call());
            if (this.f2604b.compareAndSet(jVar, jVar2)) {
                jVar = jVar2;
                break;
            }
        }
        boolean z = !jVar.f2625d.get() && jVar.f2625d.compareAndSet(false, true);
        try {
            fVar.a(jVar);
            if (z) {
                this.f2603a.subscribe(jVar);
            }
        } catch (Throwable th) {
            if (z) {
                jVar.f2625d.compareAndSet(true, false);
            }
            c.a.z.b.b(th);
            throw c.a.b0.j.j.a(th);
        }
    }
}
