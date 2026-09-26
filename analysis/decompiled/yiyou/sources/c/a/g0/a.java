package c.a.g0;

import c.a.s;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: PublishSubject.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> extends c<T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final C0076a[] f3146c = new C0076a[0];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final C0076a[] f3147d = new C0076a[0];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final AtomicReference<C0076a<T>[]> f3148a = new AtomicReference<>(f3147d);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Throwable f3149b;

    a() {
    }

    public static <T> a<T> b() {
        return new a<>();
    }

    boolean a(C0076a<T> c0076a) {
        C0076a<T>[] c0076aArr;
        C0076a<T>[] c0076aArr2;
        do {
            c0076aArr = this.f3148a.get();
            if (c0076aArr == f3146c) {
                return false;
            }
            int length = c0076aArr.length;
            c0076aArr2 = new C0076a[length + 1];
            System.arraycopy(c0076aArr, 0, c0076aArr2, 0, length);
            c0076aArr2[length] = c0076a;
        } while (!this.f3148a.compareAndSet(c0076aArr, c0076aArr2));
        return true;
    }

    @Override // c.a.s
    public void onComplete() {
        C0076a<T>[] c0076aArr = this.f3148a.get();
        C0076a<T>[] c0076aArr2 = f3146c;
        if (c0076aArr == c0076aArr2) {
            return;
        }
        for (C0076a<T> c0076a : this.f3148a.getAndSet(c0076aArr2)) {
            c0076a.b();
        }
    }

    @Override // c.a.s
    public void onError(Throwable th) {
        c.a.b0.b.b.a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        C0076a<T>[] c0076aArr = this.f3148a.get();
        C0076a<T>[] c0076aArr2 = f3146c;
        if (c0076aArr == c0076aArr2) {
            c.a.e0.a.b(th);
            return;
        }
        this.f3149b = th;
        for (C0076a<T> c0076a : this.f3148a.getAndSet(c0076aArr2)) {
            c0076a.a(th);
        }
    }

    @Override // c.a.s
    public void onNext(T t) {
        c.a.b0.b.b.a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        for (C0076a<T> c0076a : this.f3148a.get()) {
            c0076a.a(t);
        }
    }

    @Override // c.a.s
    public void onSubscribe(c.a.y.b bVar) {
        if (this.f3148a.get() == f3146c) {
            bVar.dispose();
        }
    }

    @Override // c.a.l
    protected void subscribeActual(s<? super T> sVar) {
        C0076a<T> c0076a = new C0076a<>(sVar, this);
        sVar.onSubscribe(c0076a);
        if (a(c0076a)) {
            if (c0076a.a()) {
                b(c0076a);
            }
        } else {
            Throwable th = this.f3149b;
            if (th != null) {
                sVar.onError(th);
            } else {
                sVar.onComplete();
            }
        }
    }

    /* JADX INFO: renamed from: c.a.g0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: PublishSubject.java */
    static final class C0076a<T> extends AtomicBoolean implements c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final s<? super T> f3150a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final a<T> f3151b;

        C0076a(s<? super T> sVar, a<T> aVar) {
            this.f3150a = sVar;
            this.f3151b = aVar;
        }

        public void a(T t) {
            if (get()) {
                return;
            }
            this.f3150a.onNext(t);
        }

        public void b() {
            if (get()) {
                return;
            }
            this.f3150a.onComplete();
        }

        @Override // c.a.y.b
        public void dispose() {
            if (compareAndSet(false, true)) {
                this.f3151b.b(this);
            }
        }

        public void a(Throwable th) {
            if (get()) {
                c.a.e0.a.b(th);
            } else {
                this.f3150a.onError(th);
            }
        }

        public boolean a() {
            return get();
        }
    }

    void b(C0076a<T> c0076a) {
        C0076a<T>[] c0076aArr;
        C0076a<T>[] c0076aArr2;
        do {
            c0076aArr = this.f3148a.get();
            if (c0076aArr == f3146c || c0076aArr == f3147d) {
                return;
            }
            int length = c0076aArr.length;
            int i = -1;
            for (int i2 = 0; i2 < length; i2++) {
                if (c0076aArr[i2] == c0076a) {
                    i = i2;
                    break;
                }
            }
            if (i < 0) {
                return;
            }
            if (length == 1) {
                c0076aArr2 = f3147d;
            } else {
                C0076a<T>[] c0076aArr3 = new C0076a[length - 1];
                System.arraycopy(c0076aArr, 0, c0076aArr3, 0, i);
                System.arraycopy(c0076aArr, i + 1, c0076aArr3, i, (length - i) - 1);
                c0076aArr2 = c0076aArr3;
            }
        } while (!this.f3148a.compareAndSet(c0076aArr, c0076aArr2));
    }
}
