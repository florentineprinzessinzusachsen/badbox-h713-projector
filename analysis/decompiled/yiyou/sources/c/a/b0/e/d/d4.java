package c.a.b0.e.d;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: ObservableWindow.java */
/* JADX INFO: loaded from: classes.dex */
public final class d4<T> extends c.a.b0.e.d.a<T, c.a.l<T>> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f2066b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final long f2067c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f2068d;

    /* JADX INFO: compiled from: ObservableWindow.java */
    static final class a<T> extends AtomicInteger implements c.a.s<T>, c.a.y.b, Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super c.a.l<T>> f2069a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f2070b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int f2071c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        long f2072d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        c.a.y.b f2073e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        c.a.g0.d<T> f2074f;
        volatile boolean g;

        a(c.a.s<? super c.a.l<T>> sVar, long j, int i) {
            this.f2069a = sVar;
            this.f2070b = j;
            this.f2071c = i;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.g = true;
        }

        @Override // c.a.s
        public void onComplete() {
            c.a.g0.d<T> dVar = this.f2074f;
            if (dVar != null) {
                this.f2074f = null;
                dVar.onComplete();
            }
            this.f2069a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            c.a.g0.d<T> dVar = this.f2074f;
            if (dVar != null) {
                this.f2074f = null;
                dVar.onError(th);
            }
            this.f2069a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            c.a.g0.d<T> dVarA = this.f2074f;
            if (dVarA == null && !this.g) {
                dVarA = c.a.g0.d.a(this.f2071c, this);
                this.f2074f = dVarA;
                this.f2069a.onNext(dVarA);
            }
            if (dVarA != null) {
                dVarA.onNext(t);
                long j = this.f2072d + 1;
                this.f2072d = j;
                if (j >= this.f2070b) {
                    this.f2072d = 0L;
                    this.f2074f = null;
                    dVarA.onComplete();
                    if (this.g) {
                        this.f2073e.dispose();
                    }
                }
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2073e, bVar)) {
                this.f2073e = bVar;
                this.f2069a.onSubscribe(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.g) {
                this.f2073e.dispose();
            }
        }
    }

    /* JADX INFO: compiled from: ObservableWindow.java */
    static final class b<T> extends AtomicBoolean implements c.a.s<T>, c.a.y.b, Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super c.a.l<T>> f2075a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f2076b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final long f2077c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final int f2078d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        long f2080f;
        volatile boolean g;
        long h;
        c.a.y.b i;
        final AtomicInteger j = new AtomicInteger();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final ArrayDeque<c.a.g0.d<T>> f2079e = new ArrayDeque<>();

        b(c.a.s<? super c.a.l<T>> sVar, long j, long j2, int i) {
            this.f2075a = sVar;
            this.f2076b = j;
            this.f2077c = j2;
            this.f2078d = i;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.g = true;
        }

        @Override // c.a.s
        public void onComplete() {
            ArrayDeque<c.a.g0.d<T>> arrayDeque = this.f2079e;
            while (!arrayDeque.isEmpty()) {
                arrayDeque.poll().onComplete();
            }
            this.f2075a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            ArrayDeque<c.a.g0.d<T>> arrayDeque = this.f2079e;
            while (!arrayDeque.isEmpty()) {
                arrayDeque.poll().onError(th);
            }
            this.f2075a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            ArrayDeque<c.a.g0.d<T>> arrayDeque = this.f2079e;
            long j = this.f2080f;
            long j2 = this.f2077c;
            if (j % j2 == 0 && !this.g) {
                this.j.getAndIncrement();
                c.a.g0.d<T> dVarA = c.a.g0.d.a(this.f2078d, this);
                arrayDeque.offer(dVarA);
                this.f2075a.onNext(dVarA);
            }
            long j3 = this.h + 1;
            Iterator<c.a.g0.d<T>> it = arrayDeque.iterator();
            while (it.hasNext()) {
                it.next().onNext(t);
            }
            if (j3 >= this.f2076b) {
                arrayDeque.poll().onComplete();
                if (arrayDeque.isEmpty() && this.g) {
                    this.i.dispose();
                    return;
                }
                this.h = j3 - j2;
            } else {
                this.h = j3;
            }
            this.f2080f = j + 1;
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.i, bVar)) {
                this.i = bVar;
                this.f2075a.onSubscribe(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.j.decrementAndGet() == 0 && this.g) {
                this.i.dispose();
            }
        }
    }

    public d4(c.a.q<T> qVar, long j, long j2, int i) {
        super(qVar);
        this.f2066b = j;
        this.f2067c = j2;
        this.f2068d = i;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super c.a.l<T>> sVar) {
        long j = this.f2066b;
        long j2 = this.f2067c;
        if (j == j2) {
            this.f1932a.subscribe(new a(sVar, j, this.f2068d));
        } else {
            this.f1932a.subscribe(new b(sVar, j, j2, this.f2068d));
        }
    }
}
