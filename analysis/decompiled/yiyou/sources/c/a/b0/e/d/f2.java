package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservablePublish.java */
/* JADX INFO: loaded from: classes.dex */
public final class f2<T> extends c.a.c0.a<T> implements c.a.b0.c.c<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<T> f2139a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final AtomicReference<b<T>> f2140b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.q<T> f2141c;

    /* JADX INFO: compiled from: ObservablePublish.java */
    static final class a<T> extends AtomicReference<Object> implements c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2142a;

        a(c.a.s<? super T> sVar) {
            this.f2142a = sVar;
        }

        void a(b<T> bVar) {
            if (compareAndSet(null, bVar)) {
                return;
            }
            bVar.b(this);
        }

        @Override // c.a.y.b
        public void dispose() {
            Object andSet = getAndSet(this);
            if (andSet == null || andSet == this) {
                return;
            }
            ((b) andSet).b(this);
        }
    }

    /* JADX INFO: compiled from: ObservablePublish.java */
    static final class b<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        static final a[] f2143e = new a[0];

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        static final a[] f2144f = new a[0];

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final AtomicReference<b<T>> f2145a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final AtomicReference<c.a.y.b> f2148d = new AtomicReference<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final AtomicReference<a<T>[]> f2146b = new AtomicReference<>(f2143e);

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final AtomicBoolean f2147c = new AtomicBoolean();

        b(AtomicReference<b<T>> atomicReference) {
            this.f2145a = atomicReference;
        }

        public boolean a() {
            return this.f2146b.get() == f2144f;
        }

        void b(a<T> aVar) {
            a<T>[] aVarArr;
            a<T>[] aVarArr2;
            do {
                aVarArr = this.f2146b.get();
                int length = aVarArr.length;
                if (length == 0) {
                    return;
                }
                int i = -1;
                for (int i2 = 0; i2 < length; i2++) {
                    if (aVarArr[i2].equals(aVar)) {
                        i = i2;
                        break;
                    }
                }
                if (i < 0) {
                    return;
                }
                if (length == 1) {
                    aVarArr2 = f2143e;
                } else {
                    a<T>[] aVarArr3 = new a[length - 1];
                    System.arraycopy(aVarArr, 0, aVarArr3, 0, i);
                    System.arraycopy(aVarArr, i + 1, aVarArr3, i, (length - i) - 1);
                    aVarArr2 = aVarArr3;
                }
            } while (!this.f2146b.compareAndSet(aVarArr, aVarArr2));
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.f2146b.getAndSet(f2144f) != f2144f) {
                this.f2145a.compareAndSet(this, null);
                c.a.b0.a.c.a(this.f2148d);
            }
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2145a.compareAndSet(this, null);
            for (a<T> aVar : this.f2146b.getAndSet(f2144f)) {
                aVar.f2142a.onComplete();
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2145a.compareAndSet(this, null);
            a<T>[] andSet = this.f2146b.getAndSet(f2144f);
            if (andSet.length == 0) {
                c.a.e0.a.b(th);
                return;
            }
            for (a<T> aVar : andSet) {
                aVar.f2142a.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            for (a<T> aVar : this.f2146b.get()) {
                aVar.f2142a.onNext(t);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.c(this.f2148d, bVar);
        }

        boolean a(a<T> aVar) {
            a<T>[] aVarArr;
            a<T>[] aVarArr2;
            do {
                aVarArr = this.f2146b.get();
                if (aVarArr == f2144f) {
                    return false;
                }
                int length = aVarArr.length;
                aVarArr2 = new a[length + 1];
                System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
                aVarArr2[length] = aVar;
            } while (!this.f2146b.compareAndSet(aVarArr, aVarArr2));
            return true;
        }
    }

    /* JADX INFO: compiled from: ObservablePublish.java */
    static final class c<T> implements c.a.q<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AtomicReference<b<T>> f2149a;

        c(AtomicReference<b<T>> atomicReference) {
            this.f2149a = atomicReference;
        }

        @Override // c.a.q
        public void subscribe(c.a.s<? super T> sVar) {
            a aVar = new a(sVar);
            sVar.onSubscribe(aVar);
            while (true) {
                b<T> bVar = this.f2149a.get();
                if (bVar == null || bVar.a()) {
                    b<T> bVar2 = new b<>(this.f2149a);
                    if (this.f2149a.compareAndSet(bVar, bVar2)) {
                        bVar = bVar2;
                    } else {
                        continue;
                    }
                }
                if (bVar.a(aVar)) {
                    aVar.a(bVar);
                    return;
                }
            }
        }
    }

    private f2(c.a.q<T> qVar, c.a.q<T> qVar2, AtomicReference<b<T>> atomicReference) {
        this.f2141c = qVar;
        this.f2139a = qVar2;
        this.f2140b = atomicReference;
    }

    public static <T> c.a.c0.a<T> a(c.a.q<T> qVar) {
        AtomicReference atomicReference = new AtomicReference();
        return c.a.e0.a.a((c.a.c0.a) new f2(new c(atomicReference), qVar, atomicReference));
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        this.f2141c.subscribe(sVar);
    }

    @Override // c.a.c0.a
    public void a(c.a.a0.f<? super c.a.y.b> fVar) {
        b<T> bVar;
        while (true) {
            bVar = this.f2140b.get();
            if (bVar != null && !bVar.a()) {
                break;
            }
            b<T> bVar2 = new b<>(this.f2140b);
            if (this.f2140b.compareAndSet(bVar, bVar2)) {
                bVar = bVar2;
                break;
            }
        }
        boolean z = !bVar.f2147c.get() && bVar.f2147c.compareAndSet(false, true);
        try {
            fVar.a(bVar);
            if (z) {
                this.f2139a.subscribe(bVar);
            }
        } catch (Throwable th) {
            c.a.z.b.b(th);
            throw c.a.b0.j.j.a(th);
        }
    }
}
