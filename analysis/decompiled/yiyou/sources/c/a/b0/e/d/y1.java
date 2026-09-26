package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableMergeWithCompletable.java */
/* JADX INFO: loaded from: classes.dex */
public final class y1<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.d f2914b;

    public y1(c.a.l<T> lVar, c.a.d dVar) {
        super(lVar);
        this.f2914b = dVar;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        a aVar = new a(sVar);
        sVar.onSubscribe(aVar);
        this.f1932a.subscribe(aVar);
        this.f2914b.a(aVar.f2917c);
    }

    /* JADX INFO: compiled from: ObservableMergeWithCompletable.java */
    static final class a<T> extends AtomicInteger implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2915a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final AtomicReference<c.a.y.b> f2916b = new AtomicReference<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final C0068a f2917c = new C0068a(this);

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.b0.j.c f2918d = new c.a.b0.j.c();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        volatile boolean f2919e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        volatile boolean f2920f;

        /* JADX INFO: renamed from: c.a.b0.e.d.y1$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableMergeWithCompletable.java */
        static final class C0068a extends AtomicReference<c.a.y.b> implements c.a.c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final a<?> f2921a;

            C0068a(a<?> aVar) {
                this.f2921a = aVar;
            }

            @Override // c.a.c, c.a.i
            public void onComplete() {
                this.f2921a.a();
            }

            @Override // c.a.c, c.a.i
            public void onError(Throwable th) {
                this.f2921a.a(th);
            }

            @Override // c.a.c, c.a.i
            public void onSubscribe(c.a.y.b bVar) {
                c.a.b0.a.c.c(this, bVar);
            }
        }

        a(c.a.s<? super T> sVar) {
            this.f2915a = sVar;
        }

        void a(Throwable th) {
            c.a.b0.a.c.a(this.f2916b);
            c.a.b0.j.k.a((c.a.s<?>) this.f2915a, th, (AtomicInteger) this, this.f2918d);
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a(this.f2916b);
            c.a.b0.a.c.a(this.f2917c);
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2919e = true;
            if (this.f2920f) {
                c.a.b0.j.k.a(this.f2915a, this, this.f2918d);
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            c.a.b0.a.c.a(this.f2916b);
            c.a.b0.j.k.a((c.a.s<?>) this.f2915a, th, (AtomicInteger) this, this.f2918d);
        }

        @Override // c.a.s
        public void onNext(T t) {
            c.a.b0.j.k.a(this.f2915a, t, this, this.f2918d);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.c(this.f2916b, bVar);
        }

        void a() {
            this.f2920f = true;
            if (this.f2919e) {
                c.a.b0.j.k.a(this.f2915a, this, this.f2918d);
            }
        }
    }
}
