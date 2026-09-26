package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: ObservableSequenceEqual.java */
/* JADX INFO: loaded from: classes.dex */
public final class z2<T> extends c.a.l<Boolean> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<? extends T> f2953a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.q<? extends T> f2954b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.d<? super T, ? super T> f2955c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f2956d;

    /* JADX INFO: compiled from: ObservableSequenceEqual.java */
    static final class a<T> extends AtomicInteger implements c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super Boolean> f2957a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.d<? super T, ? super T> f2958b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.b0.a.a f2959c = new c.a.b0.a.a(2);

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.q<? extends T> f2960d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final c.a.q<? extends T> f2961e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final b<T>[] f2962f;
        volatile boolean g;
        T h;
        T i;

        a(c.a.s<? super Boolean> sVar, int i, c.a.q<? extends T> qVar, c.a.q<? extends T> qVar2, c.a.a0.d<? super T, ? super T> dVar) {
            this.f2957a = sVar;
            this.f2960d = qVar;
            this.f2961e = qVar2;
            this.f2958b = dVar;
            this.f2962f = new b[]{new b<>(this, 0, i), new b<>(this, 1, i)};
        }

        boolean a(c.a.y.b bVar, int i) {
            return this.f2959c.a(i, bVar);
        }

        void b() {
            b<T>[] bVarArr = this.f2962f;
            this.f2960d.subscribe(bVarArr[0]);
            this.f2961e.subscribe(bVarArr[1]);
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.g) {
                return;
            }
            this.g = true;
            this.f2959c.dispose();
            if (getAndIncrement() == 0) {
                b<T>[] bVarArr = this.f2962f;
                bVarArr[0].f2964b.clear();
                bVarArr[1].f2964b.clear();
            }
        }

        void a(c.a.b0.f.c<T> cVar, c.a.b0.f.c<T> cVar2) {
            this.g = true;
            cVar.clear();
            cVar2.clear();
        }

        void a() {
            Throwable th;
            Throwable th2;
            if (getAndIncrement() != 0) {
                return;
            }
            b<T>[] bVarArr = this.f2962f;
            b<T> bVar = bVarArr[0];
            c.a.b0.f.c<T> cVar = bVar.f2964b;
            b<T> bVar2 = bVarArr[1];
            c.a.b0.f.c<T> cVar2 = bVar2.f2964b;
            int iAddAndGet = 1;
            while (!this.g) {
                boolean z = bVar.f2966d;
                if (z && (th2 = bVar.f2967e) != null) {
                    a(cVar, cVar2);
                    this.f2957a.onError(th2);
                    return;
                }
                boolean z2 = bVar2.f2966d;
                if (z2 && (th = bVar2.f2967e) != null) {
                    a(cVar, cVar2);
                    this.f2957a.onError(th);
                    return;
                }
                if (this.h == null) {
                    this.h = cVar.poll();
                }
                boolean z3 = this.h == null;
                if (this.i == null) {
                    this.i = cVar2.poll();
                }
                boolean z4 = this.i == null;
                if (z && z2 && z3 && z4) {
                    this.f2957a.onNext(true);
                    this.f2957a.onComplete();
                    return;
                }
                if (z && z2 && z3 != z4) {
                    a(cVar, cVar2);
                    this.f2957a.onNext(false);
                    this.f2957a.onComplete();
                    return;
                }
                if (!z3 && !z4) {
                    try {
                        if (!this.f2958b.a(this.h, this.i)) {
                            a(cVar, cVar2);
                            this.f2957a.onNext(false);
                            this.f2957a.onComplete();
                            return;
                        }
                        this.h = null;
                        this.i = null;
                    } catch (Throwable th3) {
                        c.a.z.b.b(th3);
                        a(cVar, cVar2);
                        this.f2957a.onError(th3);
                        return;
                    }
                }
                if (z3 || z4) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                }
            }
            cVar.clear();
            cVar2.clear();
        }
    }

    /* JADX INFO: compiled from: ObservableSequenceEqual.java */
    static final class b<T> implements c.a.s<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final a<T> f2963a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.b0.f.c<T> f2964b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int f2965c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        volatile boolean f2966d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        Throwable f2967e;

        b(a<T> aVar, int i, int i2) {
            this.f2963a = aVar;
            this.f2965c = i;
            this.f2964b = new c.a.b0.f.c<>(i2);
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2966d = true;
            this.f2963a.a();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2967e = th;
            this.f2966d = true;
            this.f2963a.a();
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2964b.offer(t);
            this.f2963a.a();
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            this.f2963a.a(bVar, this.f2965c);
        }
    }

    public z2(c.a.q<? extends T> qVar, c.a.q<? extends T> qVar2, c.a.a0.d<? super T, ? super T> dVar, int i) {
        this.f2953a = qVar;
        this.f2954b = qVar2;
        this.f2955c = dVar;
        this.f2956d = i;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super Boolean> sVar) {
        a aVar = new a(sVar, this.f2956d, this.f2953a, this.f2954b, this.f2955c);
        sVar.onSubscribe(aVar);
        aVar.b();
    }
}
