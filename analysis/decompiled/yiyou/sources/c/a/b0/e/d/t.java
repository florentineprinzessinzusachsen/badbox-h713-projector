package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableCombineLatest.java */
/* JADX INFO: loaded from: classes.dex */
public final class t<T, R> extends c.a.l<R> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<? extends T>[] f2697a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Iterable<? extends c.a.q<? extends T>> f2698b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.n<? super Object[], ? extends R> f2699c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f2700d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final boolean f2701e;

    /* JADX INFO: compiled from: ObservableCombineLatest.java */
    static final class a<T, R> extends AtomicReference<c.a.y.b> implements c.a.s<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final b<T, R> f2702a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f2703b;

        a(b<T, R> bVar, int i) {
            this.f2702a = bVar;
            this.f2703b = i;
        }

        public void a() {
            c.a.b0.a.c.a(this);
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2702a.a(this.f2703b);
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2702a.a(this.f2703b, th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2702a.a(this.f2703b, t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.c(this, bVar);
        }
    }

    public t(c.a.q<? extends T>[] qVarArr, Iterable<? extends c.a.q<? extends T>> iterable, c.a.a0.n<? super Object[], ? extends R> nVar, int i, boolean z) {
        this.f2697a = qVarArr;
        this.f2698b = iterable;
        this.f2699c = nVar;
        this.f2700d = i;
        this.f2701e = z;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super R> sVar) {
        int length;
        c.a.q<? extends T>[] qVarArr = this.f2697a;
        if (qVarArr == null) {
            qVarArr = new c.a.l[8];
            length = 0;
            for (c.a.q<? extends T> qVar : this.f2698b) {
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
        int i = length;
        if (i == 0) {
            c.a.b0.a.d.a(sVar);
        } else {
            new b(sVar, this.f2699c, i, this.f2700d, this.f2701e).a(qVarArr);
        }
    }

    /* JADX INFO: compiled from: ObservableCombineLatest.java */
    static final class b<T, R> extends AtomicInteger implements c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super R> f2704a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.n<? super Object[], ? extends R> f2705b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final a<T, R>[] f2706c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Object[] f2707d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final c.a.b0.f.c<Object[]> f2708e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final boolean f2709f;
        volatile boolean g;
        volatile boolean h;
        final c.a.b0.j.c i = new c.a.b0.j.c();
        int j;
        int k;

        b(c.a.s<? super R> sVar, c.a.a0.n<? super Object[], ? extends R> nVar, int i, int i2, boolean z) {
            this.f2704a = sVar;
            this.f2705b = nVar;
            this.f2709f = z;
            this.f2707d = new Object[i];
            a<T, R>[] aVarArr = new a[i];
            for (int i3 = 0; i3 < i; i3++) {
                aVarArr[i3] = new a<>(this, i3);
            }
            this.f2706c = aVarArr;
            this.f2708e = new c.a.b0.f.c<>(i2);
        }

        public void a(c.a.q<? extends T>[] qVarArr) {
            a<T, R>[] aVarArr = this.f2706c;
            int length = aVarArr.length;
            this.f2704a.onSubscribe(this);
            for (int i = 0; i < length && !this.h && !this.g; i++) {
                qVarArr[i].subscribe(aVarArr[i]);
            }
        }

        void b() {
            if (getAndIncrement() != 0) {
                return;
            }
            c.a.b0.f.c<Object[]> cVar = this.f2708e;
            c.a.s<? super R> sVar = this.f2704a;
            boolean z = this.f2709f;
            int iAddAndGet = 1;
            while (!this.g) {
                if (!z && this.i.get() != null) {
                    a();
                    a((c.a.b0.f.c<?>) cVar);
                    sVar.onError(this.i.a());
                    return;
                }
                boolean z2 = this.h;
                Object[] objArrPoll = cVar.poll();
                boolean z3 = objArrPoll == null;
                if (z2 && z3) {
                    a((c.a.b0.f.c<?>) cVar);
                    Throwable thA = this.i.a();
                    if (thA == null) {
                        sVar.onComplete();
                        return;
                    } else {
                        sVar.onError(thA);
                        return;
                    }
                }
                if (z3) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else {
                    try {
                        R rApply = this.f2705b.apply(objArrPoll);
                        c.a.b0.b.b.a(rApply, "The combiner returned a null value");
                        sVar.onNext(rApply);
                    } catch (Throwable th) {
                        c.a.z.b.b(th);
                        this.i.a(th);
                        a();
                        a((c.a.b0.f.c<?>) cVar);
                        sVar.onError(this.i.a());
                        return;
                    }
                }
            }
            a((c.a.b0.f.c<?>) cVar);
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.g) {
                return;
            }
            this.g = true;
            a();
            if (getAndIncrement() == 0) {
                a((c.a.b0.f.c<?>) this.f2708e);
            }
        }

        void a() {
            for (a<T, R> aVar : this.f2706c) {
                aVar.a();
            }
        }

        void a(c.a.b0.f.c<?> cVar) {
            synchronized (this) {
                this.f2707d = null;
            }
            cVar.clear();
        }

        void a(int i, T t) {
            boolean z;
            synchronized (this) {
                Object[] objArr = this.f2707d;
                if (objArr == null) {
                    return;
                }
                Object obj = objArr[i];
                int i2 = this.j;
                if (obj == null) {
                    i2++;
                    this.j = i2;
                }
                objArr[i] = t;
                if (i2 == objArr.length) {
                    this.f2708e.offer((Object[]) objArr.clone());
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    b();
                }
            }
        }

        /* JADX WARN: Code duplicated, block: B:18:0x0025 A[Catch: all -> 0x0029, TryCatch #0 {, blocks: (B:7:0x000e, B:9:0x0012, B:11:0x0014, B:16:0x001d, B:19:0x0027, B:18:0x0025), top: B:30:0x000e }] */
        void a(int i, Throwable th) {
            boolean z;
            if (this.i.a(th)) {
                if (this.f2709f) {
                    synchronized (this) {
                        Object[] objArr = this.f2707d;
                        if (objArr == null) {
                            return;
                        }
                        z = objArr[i] == null;
                        if (!z) {
                            int i2 = this.k + 1;
                            this.k = i2;
                            if (i2 == objArr.length) {
                                this.h = true;
                            }
                        } else {
                            this.h = true;
                        }
                    }
                } else {
                    z = true;
                }
                if (z) {
                    a();
                }
                b();
                return;
            }
            c.a.e0.a.b(th);
        }

        /* JADX WARN: Code duplicated, block: B:14:0x0019 A[Catch: all -> 0x0025, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x0007, B:12:0x0011, B:15:0x001b, B:14:0x0019), top: B:23:0x0001 }] */
        void a(int i) {
            synchronized (this) {
                Object[] objArr = this.f2707d;
                if (objArr == null) {
                    return;
                }
                boolean z = objArr[i] == null;
                if (!z) {
                    int i2 = this.k + 1;
                    this.k = i2;
                    if (i2 == objArr.length) {
                        this.h = true;
                    }
                } else {
                    this.h = true;
                }
                if (z) {
                    a();
                }
                b();
            }
        }
    }
}
