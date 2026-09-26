package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableAmb.java */
/* JADX INFO: loaded from: classes.dex */
public final class h<T> extends c.a.l<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<? extends T>[] f2203a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Iterable<? extends c.a.q<? extends T>> f2204b;

    /* JADX INFO: compiled from: ObservableAmb.java */
    static final class b<T> extends AtomicReference<c.a.y.b> implements c.a.s<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final a<T> f2208a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f2209b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.s<? super T> f2210c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f2211d;

        b(a<T> aVar, int i, c.a.s<? super T> sVar) {
            this.f2208a = aVar;
            this.f2209b = i;
            this.f2210c = sVar;
        }

        public void a() {
            c.a.b0.a.c.a(this);
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2211d) {
                this.f2210c.onComplete();
            } else if (this.f2208a.a(this.f2209b)) {
                this.f2211d = true;
                this.f2210c.onComplete();
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2211d) {
                this.f2210c.onError(th);
            } else if (!this.f2208a.a(this.f2209b)) {
                c.a.e0.a.b(th);
            } else {
                this.f2211d = true;
                this.f2210c.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2211d) {
                this.f2210c.onNext(t);
            } else if (!this.f2208a.a(this.f2209b)) {
                get().dispose();
            } else {
                this.f2211d = true;
                this.f2210c.onNext(t);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.c(this, bVar);
        }
    }

    public h(c.a.q<? extends T>[] qVarArr, Iterable<? extends c.a.q<? extends T>> iterable) {
        this.f2203a = qVarArr;
        this.f2204b = iterable;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        int length;
        c.a.q<? extends T>[] qVarArr = this.f2203a;
        if (qVarArr == null) {
            qVarArr = new c.a.l[8];
            try {
                length = 0;
                for (c.a.q<? extends T> qVar : this.f2204b) {
                    if (qVar == null) {
                        c.a.b0.a.d.a(new NullPointerException("One of the sources is null"), sVar);
                        return;
                    }
                    if (length == qVarArr.length) {
                        c.a.q<? extends T>[] qVarArr2 = new c.a.q[(length >> 2) + length];
                        System.arraycopy(qVarArr, 0, qVarArr2, 0, length);
                        qVarArr = qVarArr2;
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
            c.a.b0.a.d.a(sVar);
        } else if (length == 1) {
            qVarArr[0].subscribe(sVar);
        } else {
            new a(sVar, length).a(qVarArr);
        }
    }

    /* JADX INFO: compiled from: ObservableAmb.java */
    static final class a<T> implements c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2205a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final b<T>[] f2206b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final AtomicInteger f2207c = new AtomicInteger();

        a(c.a.s<? super T> sVar, int i) {
            this.f2205a = sVar;
            this.f2206b = new b[i];
        }

        public void a(c.a.q<? extends T>[] qVarArr) {
            b<T>[] bVarArr = this.f2206b;
            int length = bVarArr.length;
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                bVarArr[i] = new b<>(this, i2, this.f2205a);
                i = i2;
            }
            this.f2207c.lazySet(0);
            this.f2205a.onSubscribe(this);
            for (int i3 = 0; i3 < length && this.f2207c.get() == 0; i3++) {
                qVarArr[i3].subscribe(bVarArr[i3]);
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.f2207c.get() != -1) {
                this.f2207c.lazySet(-1);
                for (b<T> bVar : this.f2206b) {
                    bVar.a();
                }
            }
        }

        public boolean a(int i) {
            int i2 = this.f2207c.get();
            int i3 = 0;
            if (i2 != 0) {
                return i2 == i;
            }
            if (!this.f2207c.compareAndSet(0, i)) {
                return false;
            }
            b<T>[] bVarArr = this.f2206b;
            int length = bVarArr.length;
            while (i3 < length) {
                int i4 = i3 + 1;
                if (i4 != i) {
                    bVarArr[i3].a();
                }
                i3 = i4;
            }
            return true;
        }
    }
}
