package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableMergeWithSingle.java */
/* JADX INFO: loaded from: classes.dex */
public final class a2<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.w<? extends T> f1941b;

    public a2(c.a.l<T> lVar, c.a.w<? extends T> wVar) {
        super(lVar);
        this.f1941b = wVar;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        a aVar = new a(sVar);
        sVar.onSubscribe(aVar);
        this.f1932a.subscribe(aVar);
        this.f1941b.a(aVar.f1944c);
    }

    /* JADX INFO: compiled from: ObservableMergeWithSingle.java */
    static final class a<T> extends AtomicInteger implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f1942a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final AtomicReference<c.a.y.b> f1943b = new AtomicReference<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final C0053a<T> f1944c = new C0053a<>(this);

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.b0.j.c f1945d = new c.a.b0.j.c();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        volatile c.a.b0.c.i<T> f1946e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        T f1947f;
        volatile boolean g;
        volatile boolean h;
        volatile int i;

        /* JADX INFO: renamed from: c.a.b0.e.d.a2$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableMergeWithSingle.java */
        static final class C0053a<T> extends AtomicReference<c.a.y.b> implements c.a.v<T> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final a<T> f1948a;

            C0053a(a<T> aVar) {
                this.f1948a = aVar;
            }

            @Override // c.a.v, c.a.i
            public void a(T t) {
                this.f1948a.a(t);
            }

            @Override // c.a.v, c.a.c, c.a.i
            public void onError(Throwable th) {
                this.f1948a.a(th);
            }

            @Override // c.a.v, c.a.c, c.a.i
            public void onSubscribe(c.a.y.b bVar) {
                c.a.b0.a.c.c(this, bVar);
            }
        }

        a(c.a.s<? super T> sVar) {
            this.f1942a = sVar;
        }

        void a(T t) {
            if (compareAndSet(0, 1)) {
                this.f1942a.onNext(t);
                this.i = 2;
            } else {
                this.f1947f = t;
                this.i = 1;
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            b();
        }

        void b() {
            c.a.s<? super T> sVar = this.f1942a;
            int iAddAndGet = 1;
            while (!this.g) {
                if (this.f1945d.get() != null) {
                    this.f1947f = null;
                    this.f1946e = null;
                    sVar.onError(this.f1945d.a());
                    return;
                }
                int i = this.i;
                if (i == 1) {
                    T t = this.f1947f;
                    this.f1947f = null;
                    this.i = 2;
                    sVar.onNext(t);
                    i = 2;
                }
                boolean z = this.h;
                c.a.b0.c.i<T> iVar = this.f1946e;
                a.a.a.b.b.C0001b c0001bPoll = iVar != null ? iVar.poll() : null;
                boolean z2 = c0001bPoll == null;
                if (z && z2 && i == 2) {
                    this.f1946e = null;
                    sVar.onComplete();
                    return;
                } else if (z2) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else {
                    sVar.onNext(c0001bPoll);
                }
            }
            this.f1947f = null;
            this.f1946e = null;
        }

        c.a.b0.c.i<T> c() {
            c.a.b0.c.i<T> iVar = this.f1946e;
            if (iVar != null) {
                return iVar;
            }
            c.a.b0.f.c cVar = new c.a.b0.f.c(c.a.l.bufferSize());
            this.f1946e = cVar;
            return cVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.g = true;
            c.a.b0.a.c.a(this.f1943b);
            c.a.b0.a.c.a(this.f1944c);
            if (getAndIncrement() == 0) {
                this.f1946e = null;
                this.f1947f = null;
            }
        }

        @Override // c.a.s
        public void onComplete() {
            this.h = true;
            a();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (!this.f1945d.a(th)) {
                c.a.e0.a.b(th);
            } else {
                c.a.b0.a.c.a(this.f1943b);
                a();
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (compareAndSet(0, 1)) {
                this.f1942a.onNext(t);
                if (decrementAndGet() == 0) {
                    return;
                }
            } else {
                c().offer(t);
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            b();
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.c(this.f1943b, bVar);
        }

        void a(Throwable th) {
            if (this.f1945d.a(th)) {
                c.a.b0.a.c.a(this.f1943b);
                a();
            } else {
                c.a.e0.a.b(th);
            }
        }

        void a() {
            if (getAndIncrement() == 0) {
                b();
            }
        }
    }
}
