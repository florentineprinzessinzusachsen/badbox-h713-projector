package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: ObservableSequenceEqualSingle.java */
/* JADX INFO: loaded from: classes.dex */
public final class a3<T> extends c.a.u<Boolean> implements c.a.b0.c.a<Boolean> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<? extends T> f1949a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.q<? extends T> f1950b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.d<? super T, ? super T> f1951c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f1952d;

    /* JADX INFO: compiled from: ObservableSequenceEqualSingle.java */
    static final class a<T> extends AtomicInteger implements c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.v<? super Boolean> f1953a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.d<? super T, ? super T> f1954b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.b0.a.a f1955c = new c.a.b0.a.a(2);

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.q<? extends T> f1956d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final c.a.q<? extends T> f1957e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final b<T>[] f1958f;
        volatile boolean g;
        T h;
        T i;

        a(c.a.v<? super Boolean> vVar, int i, c.a.q<? extends T> qVar, c.a.q<? extends T> qVar2, c.a.a0.d<? super T, ? super T> dVar) {
            this.f1953a = vVar;
            this.f1956d = qVar;
            this.f1957e = qVar2;
            this.f1954b = dVar;
            this.f1958f = new b[]{new b<>(this, 0, i), new b<>(this, 1, i)};
        }

        boolean a(c.a.y.b bVar, int i) {
            return this.f1955c.a(i, bVar);
        }

        void b() {
            b<T>[] bVarArr = this.f1958f;
            this.f1956d.subscribe(bVarArr[0]);
            this.f1957e.subscribe(bVarArr[1]);
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.g) {
                return;
            }
            this.g = true;
            this.f1955c.dispose();
            if (getAndIncrement() == 0) {
                b<T>[] bVarArr = this.f1958f;
                bVarArr[0].f1960b.clear();
                bVarArr[1].f1960b.clear();
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
            b<T>[] bVarArr = this.f1958f;
            b<T> bVar = bVarArr[0];
            c.a.b0.f.c<T> cVar = bVar.f1960b;
            b<T> bVar2 = bVarArr[1];
            c.a.b0.f.c<T> cVar2 = bVar2.f1960b;
            int iAddAndGet = 1;
            while (!this.g) {
                boolean z = bVar.f1962d;
                if (z && (th2 = bVar.f1963e) != null) {
                    a(cVar, cVar2);
                    this.f1953a.onError(th2);
                    return;
                }
                boolean z2 = bVar2.f1962d;
                if (z2 && (th = bVar2.f1963e) != null) {
                    a(cVar, cVar2);
                    this.f1953a.onError(th);
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
                    this.f1953a.a(true);
                    return;
                }
                if (z && z2 && z3 != z4) {
                    a(cVar, cVar2);
                    this.f1953a.a(false);
                    return;
                }
                if (!z3 && !z4) {
                    try {
                        if (!this.f1954b.a(this.h, this.i)) {
                            a(cVar, cVar2);
                            this.f1953a.a(false);
                            return;
                        } else {
                            this.h = null;
                            this.i = null;
                        }
                    } catch (Throwable th3) {
                        c.a.z.b.b(th3);
                        a(cVar, cVar2);
                        this.f1953a.onError(th3);
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

    /* JADX INFO: compiled from: ObservableSequenceEqualSingle.java */
    static final class b<T> implements c.a.s<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final a<T> f1959a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.b0.f.c<T> f1960b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int f1961c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        volatile boolean f1962d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        Throwable f1963e;

        b(a<T> aVar, int i, int i2) {
            this.f1959a = aVar;
            this.f1961c = i;
            this.f1960b = new c.a.b0.f.c<>(i2);
        }

        @Override // c.a.s
        public void onComplete() {
            this.f1962d = true;
            this.f1959a.a();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f1963e = th;
            this.f1962d = true;
            this.f1959a.a();
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f1960b.offer(t);
            this.f1959a.a();
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            this.f1959a.a(bVar, this.f1961c);
        }
    }

    public a3(c.a.q<? extends T> qVar, c.a.q<? extends T> qVar2, c.a.a0.d<? super T, ? super T> dVar, int i) {
        this.f1949a = qVar;
        this.f1950b = qVar2;
        this.f1951c = dVar;
        this.f1952d = i;
    }

    @Override // c.a.b0.c.a
    public c.a.l<Boolean> a() {
        return c.a.e0.a.a(new z2(this.f1949a, this.f1950b, this.f1951c, this.f1952d));
    }

    @Override // c.a.u
    public void b(c.a.v<? super Boolean> vVar) {
        a aVar = new a(vVar, this.f1952d, this.f1949a, this.f1950b, this.f1951c);
        vVar.onSubscribe(aVar);
        aVar.b();
    }
}
