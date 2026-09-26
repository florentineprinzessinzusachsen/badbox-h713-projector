package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableMergeWithMaybe.java */
/* JADX INFO: loaded from: classes.dex */
public final class z1<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.j<? extends T> f2945b;

    public z1(c.a.l<T> lVar, c.a.j<? extends T> jVar) {
        super(lVar);
        this.f2945b = jVar;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        a aVar = new a(sVar);
        sVar.onSubscribe(aVar);
        this.f1932a.subscribe(aVar);
        this.f2945b.a(aVar.f2948c);
    }

    /* JADX INFO: compiled from: ObservableMergeWithMaybe.java */
    static final class a<T> extends AtomicInteger implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2946a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final AtomicReference<c.a.y.b> f2947b = new AtomicReference<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final C0070a<T> f2948c = new C0070a<>(this);

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.b0.j.c f2949d = new c.a.b0.j.c();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        volatile c.a.b0.c.i<T> f2950e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        T f2951f;
        volatile boolean g;
        volatile boolean h;
        volatile int i;

        /* JADX INFO: renamed from: c.a.b0.e.d.z1$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableMergeWithMaybe.java */
        static final class C0070a<T> extends AtomicReference<c.a.y.b> implements c.a.i<T> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final a<T> f2952a;

            C0070a(a<T> aVar) {
                this.f2952a = aVar;
            }

            @Override // c.a.i
            public void a(T t) {
                this.f2952a.a(t);
            }

            @Override // c.a.i
            public void onComplete() {
                this.f2952a.d();
            }

            @Override // c.a.i
            public void onError(Throwable th) {
                this.f2952a.a(th);
            }

            @Override // c.a.i
            public void onSubscribe(c.a.y.b bVar) {
                c.a.b0.a.c.c(this, bVar);
            }
        }

        a(c.a.s<? super T> sVar) {
            this.f2946a = sVar;
        }

        void a(T t) {
            if (compareAndSet(0, 1)) {
                this.f2946a.onNext(t);
                this.i = 2;
            } else {
                this.f2951f = t;
                this.i = 1;
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            b();
        }

        void b() {
            c.a.s<? super T> sVar = this.f2946a;
            int iAddAndGet = 1;
            while (!this.g) {
                if (this.f2949d.get() != null) {
                    this.f2951f = null;
                    this.f2950e = null;
                    sVar.onError(this.f2949d.a());
                    return;
                }
                int i = this.i;
                if (i == 1) {
                    T t = this.f2951f;
                    this.f2951f = null;
                    this.i = 2;
                    sVar.onNext(t);
                    i = 2;
                }
                boolean z = this.h;
                c.a.b0.c.i<T> iVar = this.f2950e;
                a.a.a.b.b.C0001b c0001bPoll = iVar != null ? iVar.poll() : null;
                boolean z2 = c0001bPoll == null;
                if (z && z2 && i == 2) {
                    this.f2950e = null;
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
            this.f2951f = null;
            this.f2950e = null;
        }

        c.a.b0.c.i<T> c() {
            c.a.b0.c.i<T> iVar = this.f2950e;
            if (iVar != null) {
                return iVar;
            }
            c.a.b0.f.c cVar = new c.a.b0.f.c(c.a.l.bufferSize());
            this.f2950e = cVar;
            return cVar;
        }

        void d() {
            this.i = 2;
            a();
        }

        @Override // c.a.y.b
        public void dispose() {
            this.g = true;
            c.a.b0.a.c.a(this.f2947b);
            c.a.b0.a.c.a(this.f2948c);
            if (getAndIncrement() == 0) {
                this.f2950e = null;
                this.f2951f = null;
            }
        }

        @Override // c.a.s
        public void onComplete() {
            this.h = true;
            a();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (!this.f2949d.a(th)) {
                c.a.e0.a.b(th);
            } else {
                c.a.b0.a.c.a(this.f2947b);
                a();
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (compareAndSet(0, 1)) {
                this.f2946a.onNext(t);
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
            c.a.b0.a.c.c(this.f2947b, bVar);
        }

        void a(Throwable th) {
            if (this.f2949d.a(th)) {
                c.a.b0.a.c.a(this.f2947b);
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
