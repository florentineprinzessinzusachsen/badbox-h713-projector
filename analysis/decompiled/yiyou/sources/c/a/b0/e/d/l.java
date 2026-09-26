package c.a.b0.e.d;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: ObservableBuffer.java */
/* JADX INFO: loaded from: classes.dex */
public final class l<T, U extends Collection<? super T>> extends c.a.b0.e.d.a<T, U> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final int f2375b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final int f2376c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final Callable<U> f2377d;

    /* JADX INFO: compiled from: ObservableBuffer.java */
    static final class a<T, U extends Collection<? super T>> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super U> f2378a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f2379b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final Callable<U> f2380c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        U f2381d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f2382e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        c.a.y.b f2383f;

        a(c.a.s<? super U> sVar, int i, Callable<U> callable) {
            this.f2378a = sVar;
            this.f2379b = i;
            this.f2380c = callable;
        }

        boolean a() {
            try {
                U uCall = this.f2380c.call();
                c.a.b0.b.b.a(uCall, "Empty buffer supplied");
                this.f2381d = uCall;
                return true;
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f2381d = null;
                c.a.y.b bVar = this.f2383f;
                if (bVar == null) {
                    c.a.b0.a.d.a(th, this.f2378a);
                    return false;
                }
                bVar.dispose();
                this.f2378a.onError(th);
                return false;
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2383f.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            U u = this.f2381d;
            if (u != null) {
                this.f2381d = null;
                if (!u.isEmpty()) {
                    this.f2378a.onNext(u);
                }
                this.f2378a.onComplete();
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2381d = null;
            this.f2378a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            U u = this.f2381d;
            if (u != null) {
                u.add(t);
                int i = this.f2382e + 1;
                this.f2382e = i;
                if (i >= this.f2379b) {
                    this.f2378a.onNext(u);
                    this.f2382e = 0;
                    a();
                }
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2383f, bVar)) {
                this.f2383f = bVar;
                this.f2378a.onSubscribe(this);
            }
        }
    }

    /* JADX INFO: compiled from: ObservableBuffer.java */
    static final class b<T, U extends Collection<? super T>> extends AtomicBoolean implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super U> f2384a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f2385b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int f2386c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final Callable<U> f2387d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        c.a.y.b f2388e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final ArrayDeque<U> f2389f = new ArrayDeque<>();
        long g;

        b(c.a.s<? super U> sVar, int i, int i2, Callable<U> callable) {
            this.f2384a = sVar;
            this.f2385b = i;
            this.f2386c = i2;
            this.f2387d = callable;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2388e.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            while (!this.f2389f.isEmpty()) {
                this.f2384a.onNext(this.f2389f.poll());
            }
            this.f2384a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2389f.clear();
            this.f2384a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            long j = this.g;
            this.g = 1 + j;
            if (j % ((long) this.f2386c) == 0) {
                try {
                    U uCall = this.f2387d.call();
                    c.a.b0.b.b.a(uCall, "The bufferSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.");
                    this.f2389f.offer(uCall);
                } catch (Throwable th) {
                    this.f2389f.clear();
                    this.f2388e.dispose();
                    this.f2384a.onError(th);
                    return;
                }
            }
            Iterator<U> it = this.f2389f.iterator();
            while (it.hasNext()) {
                U next = it.next();
                next.add(t);
                if (this.f2385b <= next.size()) {
                    it.remove();
                    this.f2384a.onNext(next);
                }
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2388e, bVar)) {
                this.f2388e = bVar;
                this.f2384a.onSubscribe(this);
            }
        }
    }

    public l(c.a.q<T> qVar, int i, int i2, Callable<U> callable) {
        super(qVar);
        this.f2375b = i;
        this.f2376c = i2;
        this.f2377d = callable;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super U> sVar) {
        int i = this.f2376c;
        int i2 = this.f2375b;
        if (i != i2) {
            this.f1932a.subscribe(new b(sVar, i2, i, this.f2377d));
            return;
        }
        a aVar = new a(sVar, i2, this.f2377d);
        if (aVar.a()) {
            this.f1932a.subscribe(aVar);
        }
    }
}
