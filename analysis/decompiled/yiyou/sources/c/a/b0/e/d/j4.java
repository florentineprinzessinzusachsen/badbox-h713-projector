package c.a.b0.e.d;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: compiled from: ObservableWithLatestFromMany.java */
/* JADX INFO: loaded from: classes.dex */
public final class j4<T, R> extends c.a.b0.e.d.a<T, R> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.q<?>[] f2329b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Iterable<? extends c.a.q<?>> f2330c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final c.a.a0.n<? super Object[], R> f2331d;

    /* JADX INFO: compiled from: ObservableWithLatestFromMany.java */
    final class a implements c.a.a0.n<T, R> {
        a() {
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, java.lang.Object[]] */
        @Override // c.a.a0.n
        public R apply(T t) {
            R rApply = j4.this.f2331d.apply(new Object[]{t});
            c.a.b0.b.b.a(rApply, "The combiner returned a null value");
            return rApply;
        }
    }

    /* JADX INFO: compiled from: ObservableWithLatestFromMany.java */
    static final class c extends AtomicReference<c.a.y.b> implements c.a.s<Object> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final b<?, ?> f2339a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f2340b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f2341c;

        c(b<?, ?> bVar, int i) {
            this.f2339a = bVar;
            this.f2340b = i;
        }

        public void a() {
            c.a.b0.a.c.a(this);
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2339a.a(this.f2340b, this.f2341c);
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2339a.a(this.f2340b, th);
        }

        @Override // c.a.s
        public void onNext(Object obj) {
            if (!this.f2341c) {
                this.f2341c = true;
            }
            this.f2339a.a(this.f2340b, obj);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.c(this, bVar);
        }
    }

    public j4(c.a.q<T> qVar, c.a.q<?>[] qVarArr, c.a.a0.n<? super Object[], R> nVar) {
        super(qVar);
        this.f2329b = qVarArr;
        this.f2330c = null;
        this.f2331d = nVar;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super R> sVar) {
        int length;
        c.a.q<?>[] qVarArr = this.f2329b;
        if (qVarArr == null) {
            qVarArr = new c.a.q[8];
            try {
                length = 0;
                for (c.a.q<?> qVar : this.f2330c) {
                    if (length == qVarArr.length) {
                        qVarArr = (c.a.q[]) Arrays.copyOf(qVarArr, (length >> 1) + length);
                    }
                    int i = length + 1;
                    qVarArr[length] = qVar;
                    length = i;
                }
            } catch (Throwable th) {
                c.a.z.b.b(th);
                c.a.b0.a.d.a(th, sVar);
                return;
            }
        } else {
            length = qVarArr.length;
        }
        if (length == 0) {
            new v1(this.f1932a, new a()).subscribeActual(sVar);
            return;
        }
        b bVar = new b(sVar, this.f2331d, length);
        sVar.onSubscribe(bVar);
        bVar.a(qVarArr, length);
        this.f1932a.subscribe(bVar);
    }

    /* JADX INFO: compiled from: ObservableWithLatestFromMany.java */
    static final class b<T, R> extends AtomicInteger implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super R> f2333a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.n<? super Object[], R> f2334b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c[] f2335c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final AtomicReferenceArray<Object> f2336d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final AtomicReference<c.a.y.b> f2337e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final c.a.b0.j.c f2338f;
        volatile boolean g;

        b(c.a.s<? super R> sVar, c.a.a0.n<? super Object[], R> nVar, int i) {
            this.f2333a = sVar;
            this.f2334b = nVar;
            c[] cVarArr = new c[i];
            for (int i2 = 0; i2 < i; i2++) {
                cVarArr[i2] = new c(this, i2);
            }
            this.f2335c = cVarArr;
            this.f2336d = new AtomicReferenceArray<>(i);
            this.f2337e = new AtomicReference<>();
            this.f2338f = new c.a.b0.j.c();
        }

        void a(c.a.q<?>[] qVarArr, int i) {
            c[] cVarArr = this.f2335c;
            AtomicReference<c.a.y.b> atomicReference = this.f2337e;
            for (int i2 = 0; i2 < i && !c.a.b0.a.c.a(atomicReference.get()) && !this.g; i2++) {
                qVarArr[i2].subscribe(cVarArr[i2]);
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a(this.f2337e);
            for (c cVar : this.f2335c) {
                cVar.a();
            }
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.g) {
                return;
            }
            this.g = true;
            a(-1);
            c.a.b0.j.k.a(this.f2333a, this, this.f2338f);
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.g) {
                c.a.e0.a.b(th);
                return;
            }
            this.g = true;
            a(-1);
            c.a.b0.j.k.a((c.a.s<?>) this.f2333a, th, (AtomicInteger) this, this.f2338f);
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.g) {
                return;
            }
            AtomicReferenceArray<Object> atomicReferenceArray = this.f2336d;
            int length = atomicReferenceArray.length();
            Object[] objArr = new Object[length + 1];
            int i = 0;
            objArr[0] = t;
            while (i < length) {
                Object obj = atomicReferenceArray.get(i);
                if (obj == null) {
                    return;
                }
                i++;
                objArr[i] = obj;
            }
            try {
                R rApply = this.f2334b.apply(objArr);
                c.a.b0.b.b.a(rApply, "combiner returned a null value");
                c.a.b0.j.k.a(this.f2333a, rApply, this, this.f2338f);
            } catch (Throwable th) {
                c.a.z.b.b(th);
                dispose();
                onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.c(this.f2337e, bVar);
        }

        void a(int i, Object obj) {
            this.f2336d.set(i, obj);
        }

        void a(int i, Throwable th) {
            this.g = true;
            c.a.b0.a.c.a(this.f2337e);
            a(i);
            c.a.b0.j.k.a((c.a.s<?>) this.f2333a, th, (AtomicInteger) this, this.f2338f);
        }

        void a(int i, boolean z) {
            if (z) {
                return;
            }
            this.g = true;
            a(i);
            c.a.b0.j.k.a(this.f2333a, this, this.f2338f);
        }

        void a(int i) {
            c[] cVarArr = this.f2335c;
            for (int i2 = 0; i2 < cVarArr.length; i2++) {
                if (i2 != i) {
                    cVarArr[i2].a();
                }
            }
        }
    }

    public j4(c.a.q<T> qVar, Iterable<? extends c.a.q<?>> iterable, c.a.a0.n<? super Object[], R> nVar) {
        super(qVar);
        this.f2329b = null;
        this.f2330c = iterable;
        this.f2331d = nVar;
    }
}
