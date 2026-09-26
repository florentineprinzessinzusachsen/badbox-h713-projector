package c.a.b0.e.d;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableThrottleFirstTimed.java */
/* JADX INFO: loaded from: classes.dex */
public final class t3<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f2725b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final TimeUnit f2726c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final c.a.t f2727d;

    /* JADX INFO: compiled from: ObservableThrottleFirstTimed.java */
    static final class a<T> extends AtomicReference<c.a.y.b> implements c.a.s<T>, c.a.y.b, Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2728a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f2729b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final TimeUnit f2730c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.t.c f2731d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        c.a.y.b f2732e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        volatile boolean f2733f;
        boolean g;

        a(c.a.s<? super T> sVar, long j, TimeUnit timeUnit, c.a.t.c cVar) {
            this.f2728a = sVar;
            this.f2729b = j;
            this.f2730c = timeUnit;
            this.f2731d = cVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2732e.dispose();
            this.f2731d.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.g) {
                return;
            }
            this.g = true;
            this.f2728a.onComplete();
            this.f2731d.dispose();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.g) {
                c.a.e0.a.b(th);
                return;
            }
            this.g = true;
            this.f2728a.onError(th);
            this.f2731d.dispose();
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2733f || this.g) {
                return;
            }
            this.f2733f = true;
            this.f2728a.onNext(t);
            c.a.y.b bVar = get();
            if (bVar != null) {
                bVar.dispose();
            }
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this, this.f2731d.a(this, this.f2729b, this.f2730c));
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2732e, bVar)) {
                this.f2732e = bVar;
                this.f2728a.onSubscribe(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f2733f = false;
        }
    }

    public t3(c.a.q<T> qVar, long j, TimeUnit timeUnit, c.a.t tVar) {
        super(qVar);
        this.f2725b = j;
        this.f2726c = timeUnit;
        this.f2727d = tVar;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(new c.a.d0.f(sVar), this.f2725b, this.f2726c, this.f2727d.a()));
    }
}
