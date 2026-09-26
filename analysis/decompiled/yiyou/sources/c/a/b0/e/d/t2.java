package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableRetryWhen.java */
/* JADX INFO: loaded from: classes.dex */
public final class t2<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.n<? super c.a.l<Throwable>, ? extends c.a.q<?>> f2717b;

    public t2(c.a.q<T> qVar, c.a.a0.n<? super c.a.l<Throwable>, ? extends c.a.q<?>> nVar) {
        super(qVar);
        this.f2717b = nVar;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        c.a.g0.c<T> cVarA = c.a.g0.a.b().a();
        try {
            c.a.q<?> qVarApply = this.f2717b.apply(cVarA);
            c.a.b0.b.b.a(qVarApply, "The handler returned a null ObservableSource");
            c.a.q<?> qVar = qVarApply;
            a aVar = new a(sVar, cVarA, this.f1932a);
            sVar.onSubscribe(aVar);
            qVar.subscribe(aVar.f2722e);
            aVar.d();
        } catch (Throwable th) {
            c.a.z.b.b(th);
            c.a.b0.a.d.a(th, sVar);
        }
    }

    /* JADX INFO: compiled from: ObservableRetryWhen.java */
    static final class a<T> extends AtomicInteger implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2718a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.g0.c<Throwable> f2721d;
        final c.a.q<T> g;
        volatile boolean h;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final AtomicInteger f2719b = new AtomicInteger();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.b0.j.c f2720c = new c.a.b0.j.c();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final a<T>.C0063a f2722e = new C0063a();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final AtomicReference<c.a.y.b> f2723f = new AtomicReference<>();

        /* JADX INFO: renamed from: c.a.b0.e.d.t2$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableRetryWhen.java */
        final class C0063a extends AtomicReference<c.a.y.b> implements c.a.s<Object> {
            C0063a() {
            }

            @Override // c.a.s
            public void onComplete() {
                a.this.a();
            }

            @Override // c.a.s
            public void onError(Throwable th) {
                a.this.a(th);
            }

            @Override // c.a.s
            public void onNext(Object obj) {
                a.this.b();
            }

            @Override // c.a.s
            public void onSubscribe(c.a.y.b bVar) {
                c.a.b0.a.c.c(this, bVar);
            }
        }

        a(c.a.s<? super T> sVar, c.a.g0.c<Throwable> cVar, c.a.q<T> qVar) {
            this.f2718a = sVar;
            this.f2721d = cVar;
            this.g = qVar;
        }

        void a(Throwable th) {
            c.a.b0.a.c.a(this.f2723f);
            c.a.b0.j.k.a((c.a.s<?>) this.f2718a, th, (AtomicInteger) this, this.f2720c);
        }

        void b() {
            d();
        }

        public boolean c() {
            return c.a.b0.a.c.a(this.f2723f.get());
        }

        void d() {
            if (this.f2719b.getAndIncrement() == 0) {
                while (!c()) {
                    if (!this.h) {
                        this.h = true;
                        this.g.subscribe(this);
                    }
                    if (this.f2719b.decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a(this.f2723f);
            c.a.b0.a.c.a(this.f2722e);
        }

        @Override // c.a.s
        public void onComplete() {
            c.a.b0.a.c.a(this.f2722e);
            c.a.b0.j.k.a(this.f2718a, this, this.f2720c);
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.h = false;
            this.f2721d.onNext(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            c.a.b0.j.k.a(this.f2718a, t, this, this.f2720c);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.a(this.f2723f, bVar);
        }

        void a() {
            c.a.b0.a.c.a(this.f2723f);
            c.a.b0.j.k.a(this.f2718a, this, this.f2720c);
        }
    }
}
