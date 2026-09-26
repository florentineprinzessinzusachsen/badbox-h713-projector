package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableFlatMapCompletableCompletable.java */
/* JADX INFO: loaded from: classes.dex */
public final class x0<T> extends c.a.b implements c.a.b0.c.a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<T> f2863a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.n<? super T, ? extends c.a.d> f2864b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f2865c;

    public x0(c.a.q<T> qVar, c.a.a0.n<? super T, ? extends c.a.d> nVar, boolean z) {
        this.f2863a = qVar;
        this.f2864b = nVar;
        this.f2865c = z;
    }

    @Override // c.a.b0.c.a
    public c.a.l<T> a() {
        return c.a.e0.a.a(new w0(this.f2863a, this.f2864b, this.f2865c));
    }

    @Override // c.a.b
    protected void b(c.a.c cVar) {
        this.f2863a.subscribe(new a(cVar, this.f2864b, this.f2865c));
    }

    /* JADX INFO: compiled from: ObservableFlatMapCompletableCompletable.java */
    static final class a<T> extends AtomicInteger implements c.a.y.b, c.a.s<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.c f2866a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.a0.n<? super T, ? extends c.a.d> f2868c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final boolean f2869d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        c.a.y.b f2871f;
        volatile boolean g;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.b0.j.c f2867b = new c.a.b0.j.c();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final c.a.y.a f2870e = new c.a.y.a();

        /* JADX INFO: renamed from: c.a.b0.e.d.x0$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableFlatMapCompletableCompletable.java */
        final class C0066a extends AtomicReference<c.a.y.b> implements c.a.c, c.a.y.b {
            C0066a() {
            }

            @Override // c.a.y.b
            public void dispose() {
                c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
            }

            @Override // c.a.c, c.a.i
            public void onComplete() {
                a.this.a(this);
            }

            @Override // c.a.c, c.a.i
            public void onError(Throwable th) {
                a.this.a(this, th);
            }

            @Override // c.a.c, c.a.i
            public void onSubscribe(c.a.y.b bVar) {
                c.a.b0.a.c.c(this, bVar);
            }
        }

        a(c.a.c cVar, c.a.a0.n<? super T, ? extends c.a.d> nVar, boolean z) {
            this.f2866a = cVar;
            this.f2868c = nVar;
            this.f2869d = z;
            lazySet(1);
        }

        void a(a<T>.C0066a c0066a) {
            this.f2870e.a(c0066a);
            onComplete();
        }

        @Override // c.a.y.b
        public void dispose() {
            this.g = true;
            this.f2871f.dispose();
            this.f2870e.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (decrementAndGet() == 0) {
                Throwable thA = this.f2867b.a();
                if (thA != null) {
                    this.f2866a.onError(thA);
                } else {
                    this.f2866a.onComplete();
                }
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (!this.f2867b.a(th)) {
                c.a.e0.a.b(th);
                return;
            }
            if (this.f2869d) {
                if (decrementAndGet() == 0) {
                    this.f2866a.onError(this.f2867b.a());
                    return;
                }
                return;
            }
            dispose();
            if (getAndSet(0) > 0) {
                this.f2866a.onError(this.f2867b.a());
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            try {
                c.a.d dVarApply = this.f2868c.apply(t);
                c.a.b0.b.b.a(dVarApply, "The mapper returned a null CompletableSource");
                c.a.d dVar = dVarApply;
                getAndIncrement();
                C0066a c0066a = new C0066a();
                if (this.g || !this.f2870e.c(c0066a)) {
                    return;
                }
                dVar.a(c0066a);
            } catch (Throwable th) {
                c.a.z.b.b(th);
                this.f2871f.dispose();
                onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2871f, bVar)) {
                this.f2871f = bVar;
                this.f2866a.onSubscribe(this);
            }
        }

        void a(a<T>.C0066a c0066a, Throwable th) {
            this.f2870e.a(c0066a);
            onError(th);
        }
    }
}
