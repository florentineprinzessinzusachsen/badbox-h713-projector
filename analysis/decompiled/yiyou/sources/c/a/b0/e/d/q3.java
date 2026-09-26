package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableTakeUntil.java */
/* JADX INFO: loaded from: classes.dex */
public final class q3<T, U> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.q<? extends U> f2638b;

    public q3(c.a.q<T> qVar, c.a.q<? extends U> qVar2) {
        super(qVar);
        this.f2638b = qVar2;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        a aVar = new a(sVar);
        sVar.onSubscribe(aVar);
        this.f2638b.subscribe(aVar.f2641c);
        this.f1932a.subscribe(aVar);
    }

    /* JADX INFO: compiled from: ObservableTakeUntil.java */
    static final class a<T, U> extends AtomicInteger implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2639a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final AtomicReference<c.a.y.b> f2640b = new AtomicReference<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final a<T, U>.C0062a f2641c = new C0062a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.b0.j.c f2642d = new c.a.b0.j.c();

        /* JADX INFO: renamed from: c.a.b0.e.d.q3$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableTakeUntil.java */
        final class C0062a extends AtomicReference<c.a.y.b> implements c.a.s<U> {
            C0062a() {
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
            public void onNext(U u) {
                c.a.b0.a.c.a(this);
                a.this.a();
            }

            @Override // c.a.s
            public void onSubscribe(c.a.y.b bVar) {
                c.a.b0.a.c.c(this, bVar);
            }
        }

        a(c.a.s<? super T> sVar) {
            this.f2639a = sVar;
        }

        void a(Throwable th) {
            c.a.b0.a.c.a(this.f2640b);
            c.a.b0.j.k.a((c.a.s<?>) this.f2639a, th, (AtomicInteger) this, this.f2642d);
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a(this.f2640b);
            c.a.b0.a.c.a(this.f2641c);
        }

        @Override // c.a.s
        public void onComplete() {
            c.a.b0.a.c.a(this.f2641c);
            c.a.b0.j.k.a(this.f2639a, this, this.f2642d);
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            c.a.b0.a.c.a(this.f2641c);
            c.a.b0.j.k.a((c.a.s<?>) this.f2639a, th, (AtomicInteger) this, this.f2642d);
        }

        @Override // c.a.s
        public void onNext(T t) {
            c.a.b0.j.k.a(this.f2639a, t, this, this.f2642d);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.c(this.f2640b, bVar);
        }

        void a() {
            c.a.b0.a.c.a(this.f2640b);
            c.a.b0.j.k.a(this.f2639a, this, this.f2642d);
        }
    }
}
