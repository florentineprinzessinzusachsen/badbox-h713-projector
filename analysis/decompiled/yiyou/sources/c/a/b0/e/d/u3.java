package c.a.b0.e.d;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableThrottleLatest.java */
/* JADX INFO: loaded from: classes.dex */
public final class u3<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f2766b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final TimeUnit f2767c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final c.a.t f2768d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final boolean f2769e;

    /* JADX INFO: compiled from: ObservableThrottleLatest.java */
    static final class a<T> extends AtomicInteger implements c.a.s<T>, c.a.y.b, Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2770a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f2771b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final TimeUnit f2772c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.t.c f2773d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final boolean f2774e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final AtomicReference<T> f2775f = new AtomicReference<>();
        c.a.y.b g;
        volatile boolean h;
        Throwable i;
        volatile boolean j;
        volatile boolean k;
        boolean l;

        a(c.a.s<? super T> sVar, long j, TimeUnit timeUnit, c.a.t.c cVar, boolean z) {
            this.f2770a = sVar;
            this.f2771b = j;
            this.f2772c = timeUnit;
            this.f2773d = cVar;
            this.f2774e = z;
        }

        void a() {
            if (getAndIncrement() != 0) {
                return;
            }
            AtomicReference<T> atomicReference = this.f2775f;
            c.a.s<? super T> sVar = this.f2770a;
            int iAddAndGet = 1;
            while (!this.j) {
                boolean z = this.h;
                if (z && this.i != null) {
                    atomicReference.lazySet(null);
                    sVar.onError(this.i);
                    this.f2773d.dispose();
                    return;
                }
                boolean z2 = atomicReference.get() == null;
                if (z) {
                    T andSet = atomicReference.getAndSet(null);
                    if (!z2 && this.f2774e) {
                        sVar.onNext(andSet);
                    }
                    sVar.onComplete();
                    this.f2773d.dispose();
                    return;
                }
                if (z2) {
                    if (this.k) {
                        this.l = false;
                        this.k = false;
                    }
                } else if (!this.l || this.k) {
                    sVar.onNext(atomicReference.getAndSet(null));
                    this.k = false;
                    this.l = true;
                    this.f2773d.a(this, this.f2771b, this.f2772c);
                }
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            }
            atomicReference.lazySet(null);
        }

        @Override // c.a.y.b
        public void dispose() {
            this.j = true;
            this.g.dispose();
            this.f2773d.dispose();
            if (getAndIncrement() == 0) {
                this.f2775f.lazySet(null);
            }
        }

        @Override // c.a.s
        public void onComplete() {
            this.h = true;
            a();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.i = th;
            this.h = true;
            a();
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2775f.set(t);
            a();
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.g, bVar)) {
                this.g = bVar;
                this.f2770a.onSubscribe(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.k = true;
            a();
        }
    }

    public u3(c.a.l<T> lVar, long j, TimeUnit timeUnit, c.a.t tVar, boolean z) {
        super(lVar);
        this.f2766b = j;
        this.f2767c = timeUnit;
        this.f2768d = tVar;
        this.f2769e = z;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2766b, this.f2767c, this.f2768d.a(), this.f2769e));
    }
}
