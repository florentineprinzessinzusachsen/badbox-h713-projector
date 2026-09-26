package c.a.b0.e.d;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableZip.java */
/* JADX INFO: loaded from: classes.dex */
public final class k4<T, R> extends c.a.l<R> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<? extends T>[] f2359a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Iterable<? extends c.a.q<? extends T>> f2360b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.n<? super Object[], ? extends R> f2361c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f2362d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final boolean f2363e;

    /* JADX INFO: compiled from: ObservableZip.java */
    static final class b<T, R> implements c.a.s<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final a<T, R> f2370a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.b0.f.c<T> f2371b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        volatile boolean f2372c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Throwable f2373d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final AtomicReference<c.a.y.b> f2374e = new AtomicReference<>();

        b(a<T, R> aVar, int i) {
            this.f2370a = aVar;
            this.f2371b = new c.a.b0.f.c<>(i);
        }

        public void a() {
            c.a.b0.a.c.a(this.f2374e);
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2372c = true;
            this.f2370a.c();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2373d = th;
            this.f2372c = true;
            this.f2370a.c();
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2371b.offer(t);
            this.f2370a.c();
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.c(this.f2374e, bVar);
        }
    }

    public k4(c.a.q<? extends T>[] qVarArr, Iterable<? extends c.a.q<? extends T>> iterable, c.a.a0.n<? super Object[], ? extends R> nVar, int i, boolean z) {
        this.f2359a = qVarArr;
        this.f2360b = iterable;
        this.f2361c = nVar;
        this.f2362d = i;
        this.f2363e = z;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super R> sVar) {
        int length;
        c.a.q<? extends T>[] qVarArr = this.f2359a;
        if (qVarArr == null) {
            qVarArr = new c.a.l[8];
            length = 0;
            for (c.a.q<? extends T> qVar : this.f2360b) {
                if (length == qVarArr.length) {
                    c.a.q<? extends T>[] qVarArr2 = new c.a.q[(length >> 2) + length];
                    System.arraycopy(qVarArr, 0, qVarArr2, 0, length);
                    qVarArr = qVarArr2;
                }
                qVarArr[length] = qVar;
                length++;
            }
        } else {
            length = qVarArr.length;
        }
        if (length == 0) {
            c.a.b0.a.d.a(sVar);
        } else {
            new a(sVar, this.f2361c, length, this.f2363e).a(qVarArr, this.f2362d);
        }
    }

    /* JADX INFO: compiled from: ObservableZip.java */
    static final class a<T, R> extends AtomicInteger implements c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super R> f2364a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.n<? super Object[], ? extends R> f2365b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final b<T, R>[] f2366c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final T[] f2367d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final boolean f2368e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        volatile boolean f2369f;

        a(c.a.s<? super R> sVar, c.a.a0.n<? super Object[], ? extends R> nVar, int i, boolean z) {
            this.f2364a = sVar;
            this.f2365b = nVar;
            this.f2366c = new b[i];
            this.f2367d = (T[]) new Object[i];
            this.f2368e = z;
        }

        public void a(c.a.q<? extends T>[] qVarArr, int i) {
            b<T, R>[] bVarArr = this.f2366c;
            int length = bVarArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                bVarArr[i2] = new b<>(this, i);
            }
            lazySet(0);
            this.f2364a.onSubscribe(this);
            for (int i3 = 0; i3 < length && !this.f2369f; i3++) {
                qVarArr[i3].subscribe(bVarArr[i3]);
            }
        }

        void b() {
            for (b<T, R> bVar : this.f2366c) {
                bVar.a();
            }
        }

        public void c() {
            Throwable th;
            if (getAndIncrement() != 0) {
                return;
            }
            b<T, R>[] bVarArr = this.f2366c;
            c.a.s<? super R> sVar = this.f2364a;
            T[] tArr = this.f2367d;
            boolean z = this.f2368e;
            int iAddAndGet = 1;
            while (true) {
                int i = 0;
                int i2 = 0;
                for (b<T, R> bVar : bVarArr) {
                    if (tArr[i2] == null) {
                        boolean z2 = bVar.f2372c;
                        T tPoll = bVar.f2371b.poll();
                        boolean z3 = tPoll == null;
                        if (a(z2, z3, sVar, z, bVar)) {
                            return;
                        }
                        if (z3) {
                            i++;
                        } else {
                            tArr[i2] = tPoll;
                        }
                    } else if (bVar.f2372c && !z && (th = bVar.f2373d) != null) {
                        a();
                        sVar.onError(th);
                        return;
                    }
                    i2++;
                }
                if (i != 0) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else {
                    try {
                        R rApply = this.f2365b.apply(tArr.clone());
                        c.a.b0.b.b.a(rApply, "The zipper returned a null value");
                        sVar.onNext(rApply);
                        Arrays.fill(tArr, (Object) null);
                    } catch (Throwable th2) {
                        c.a.z.b.b(th2);
                        a();
                        sVar.onError(th2);
                        return;
                    }
                }
            }
        }

        void clear() {
            for (b<T, R> bVar : this.f2366c) {
                bVar.f2371b.clear();
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.f2369f) {
                return;
            }
            this.f2369f = true;
            b();
            if (getAndIncrement() == 0) {
                clear();
            }
        }

        void a() {
            clear();
            b();
        }

        boolean a(boolean z, boolean z2, c.a.s<? super R> sVar, boolean z3, b<?, ?> bVar) {
            if (this.f2369f) {
                a();
                return true;
            }
            if (!z) {
                return false;
            }
            if (z3) {
                if (!z2) {
                    return false;
                }
                Throwable th = bVar.f2373d;
                a();
                if (th != null) {
                    sVar.onError(th);
                } else {
                    sVar.onComplete();
                }
                return true;
            }
            Throwable th2 = bVar.f2373d;
            if (th2 != null) {
                a();
                sVar.onError(th2);
                return true;
            }
            if (!z2) {
                return false;
            }
            a();
            sVar.onComplete();
            return true;
        }
    }
}
