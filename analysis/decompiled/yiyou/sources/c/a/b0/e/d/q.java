package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableCache.java */
/* JADX INFO: loaded from: classes.dex */
public final class q<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final a<T> f2576b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final AtomicBoolean f2577c;

    /* JADX INFO: compiled from: ObservableCache.java */
    static final class a<T> extends c.a.b0.j.m implements c.a.s<T> {
        static final b[] j = new b[0];
        static final b[] k = new b[0];

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final c.a.l<? extends T> f2578f;
        final c.a.b0.a.f g;
        final AtomicReference<b<T>[]> h;
        boolean i;

        a(c.a.l<? extends T> lVar, int i) {
            super(i);
            this.f2578f = lVar;
            this.h = new AtomicReference<>(j);
            this.g = new c.a.b0.a.f();
        }

        public boolean a(b<T> bVar) {
            b<T>[] bVarArr;
            b<T>[] bVarArr2;
            do {
                bVarArr = this.h.get();
                if (bVarArr == k) {
                    return false;
                }
                int length = bVarArr.length;
                bVarArr2 = new b[length + 1];
                System.arraycopy(bVarArr, 0, bVarArr2, 0, length);
                bVarArr2[length] = bVar;
            } while (!this.h.compareAndSet(bVarArr, bVarArr2));
            return true;
        }

        public void b(b<T> bVar) {
            b<T>[] bVarArr;
            b<T>[] bVarArr2;
            do {
                bVarArr = this.h.get();
                int length = bVarArr.length;
                if (length == 0) {
                    return;
                }
                int i = -1;
                for (int i2 = 0; i2 < length; i2++) {
                    if (bVarArr[i2].equals(bVar)) {
                        i = i2;
                        break;
                    }
                }
                if (i < 0) {
                    return;
                }
                if (length == 1) {
                    bVarArr2 = j;
                } else {
                    b<T>[] bVarArr3 = new b[length - 1];
                    System.arraycopy(bVarArr, 0, bVarArr3, 0, i);
                    System.arraycopy(bVarArr, i + 1, bVarArr3, i, (length - i) - 1);
                    bVarArr2 = bVarArr3;
                }
            } while (!this.h.compareAndSet(bVarArr, bVarArr2));
        }

        public void c() {
            this.f2578f.subscribe(this);
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.i) {
                return;
            }
            this.i = true;
            a(c.a.b0.j.n.a());
            this.g.dispose();
            for (b<T> bVar : this.h.getAndSet(k)) {
                bVar.a();
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.i) {
                return;
            }
            this.i = true;
            a(c.a.b0.j.n.a(th));
            this.g.dispose();
            for (b<T> bVar : this.h.getAndSet(k)) {
                bVar.a();
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.i) {
                return;
            }
            c.a.b0.j.n.e(t);
            a(t);
            for (b<T> bVar : this.h.get()) {
                bVar.a();
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            this.g.b(bVar);
        }
    }

    /* JADX INFO: compiled from: ObservableCache.java */
    static final class b<T> extends AtomicInteger implements c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2579a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final a<T> f2580b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        Object[] f2581c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f2582d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f2583e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        volatile boolean f2584f;

        b(c.a.s<? super T> sVar, a<T> aVar) {
            this.f2579a = sVar;
            this.f2580b = aVar;
        }

        public void a() {
            if (getAndIncrement() != 0) {
                return;
            }
            c.a.s<? super T> sVar = this.f2579a;
            int iAddAndGet = 1;
            while (!this.f2584f) {
                int iB = this.f2580b.b();
                if (iB != 0) {
                    Object[] objArrA = this.f2581c;
                    if (objArrA == null) {
                        objArrA = this.f2580b.a();
                        this.f2581c = objArrA;
                    }
                    int length = objArrA.length - 1;
                    int i = this.f2583e;
                    int i2 = this.f2582d;
                    while (i < iB) {
                        if (this.f2584f) {
                            return;
                        }
                        if (i2 == length) {
                            objArrA = (Object[]) objArrA[length];
                            i2 = 0;
                        }
                        if (c.a.b0.j.n.a(objArrA[i2], sVar)) {
                            return;
                        }
                        i2++;
                        i++;
                    }
                    if (this.f2584f) {
                        return;
                    }
                    this.f2583e = i;
                    this.f2582d = i2;
                    this.f2581c = objArrA;
                }
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.f2584f) {
                return;
            }
            this.f2584f = true;
            this.f2580b.b(this);
        }
    }

    private q(c.a.l<T> lVar, a<T> aVar) {
        super(lVar);
        this.f2576b = aVar;
        this.f2577c = new AtomicBoolean();
    }

    public static <T> c.a.l<T> a(c.a.l<T> lVar) {
        return a(lVar, 16);
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        b<T> bVar = new b<>(sVar, this.f2576b);
        sVar.onSubscribe(bVar);
        this.f2576b.a((b) bVar);
        if (!this.f2577c.get() && this.f2577c.compareAndSet(false, true)) {
            this.f2576b.c();
        }
        bVar.a();
    }

    public static <T> c.a.l<T> a(c.a.l<T> lVar, int i) {
        c.a.b0.b.b.a(i, "capacityHint");
        return c.a.e0.a.a(new q(lVar, new a(lVar, i)));
    }
}
