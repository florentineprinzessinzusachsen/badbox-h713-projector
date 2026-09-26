package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableRepeatWhen.java */
/* JADX INFO: loaded from: classes.dex */
public final class p2<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.n<? super c.a.l<Object>, ? extends c.a.q<?>> f2557b;

    public p2(c.a.q<T> qVar, c.a.a0.n<? super c.a.l<Object>, ? extends c.a.q<?>> nVar) {
        super(qVar);
        this.f2557b = nVar;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        c.a.g0.c<T> cVarA = c.a.g0.a.b().a();
        try {
            c.a.q<?> qVarApply = this.f2557b.apply(cVarA);
            c.a.b0.b.b.a(qVarApply, "The handler returned a null ObservableSource");
            c.a.q<?> qVar = qVarApply;
            a aVar = new a(sVar, cVarA, this.f1932a);
            sVar.onSubscribe(aVar);
            qVar.subscribe(aVar.f2562e);
            aVar.d();
        } catch (Throwable th) {
            c.a.z.b.b(th);
            c.a.b0.a.d.a(th, sVar);
        }
    }

    /* JADX INFO: compiled from: ObservableRepeatWhen.java */
    static final class a<T> extends AtomicInteger implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2558a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.g0.c<Object> f2561d;
        final c.a.q<T> g;
        volatile boolean h;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final AtomicInteger f2559b = new AtomicInteger();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.b0.j.c f2560c = new c.a.b0.j.c();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final a<T>.C0061a f2562e = new C0061a();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final AtomicReference<c.a.y.b> f2563f = new AtomicReference<>();

        /* JADX INFO: renamed from: c.a.b0.e.d.p2$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableRepeatWhen.java */
        final class C0061a extends AtomicReference<c.a.y.b> implements c.a.s<Object> {
            C0061a() {
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

        a(c.a.s<? super T> sVar, c.a.g0.c<Object> cVar, c.a.q<T> qVar) {
            this.f2558a = sVar;
            this.f2561d = cVar;
            this.g = qVar;
        }

        void a(Throwable th) {
            c.a.b0.a.c.a(this.f2563f);
            c.a.b0.j.k.a((c.a.s<?>) this.f2558a, th, (AtomicInteger) this, this.f2560c);
        }

        void b() {
            d();
        }

        public boolean c() {
            return c.a.b0.a.c.a(this.f2563f.get());
        }

        void d() {
            if (this.f2559b.getAndIncrement() == 0) {
                while (!c()) {
                    if (!this.h) {
                        this.h = true;
                        this.g.subscribe(this);
                    }
                    if (this.f2559b.decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a(this.f2563f);
            c.a.b0.a.c.a(this.f2562e);
        }

        @Override // c.a.s
        public void onComplete() {
            this.h = false;
            this.f2561d.onNext(0);
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            c.a.b0.a.c.a(this.f2562e);
            c.a.b0.j.k.a((c.a.s<?>) this.f2558a, th, (AtomicInteger) this, this.f2560c);
        }

        @Override // c.a.s
        public void onNext(T t) {
            c.a.b0.j.k.a(this.f2558a, t, this, this.f2560c);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.a(this.f2563f, bVar);
        }

        void a() {
            c.a.b0.a.c.a(this.f2563f);
            c.a.b0.j.k.a(this.f2558a, this, this.f2560c);
        }
    }
}
