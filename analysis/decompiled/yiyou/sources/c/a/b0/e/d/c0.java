package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableDebounce.java */
/* JADX INFO: loaded from: classes.dex */
public final class c0<T, U> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.n<? super T, ? extends c.a.q<U>> f1994b;

    /* JADX INFO: compiled from: ObservableDebounce.java */
    static final class a<T, U> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f1995a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.n<? super T, ? extends c.a.q<U>> f1996b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f1997c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final AtomicReference<c.a.y.b> f1998d = new AtomicReference<>();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        volatile long f1999e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        boolean f2000f;

        /* JADX INFO: renamed from: c.a.b0.e.d.c0$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableDebounce.java */
        static final class C0055a<T, U> extends c.a.d0.c<U> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final a<T, U> f2001b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final long f2002c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final T f2003d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            boolean f2004e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            final AtomicBoolean f2005f = new AtomicBoolean();

            C0055a(a<T, U> aVar, long j, T t) {
                this.f2001b = aVar;
                this.f2002c = j;
                this.f2003d = t;
            }

            void b() {
                if (this.f2005f.compareAndSet(false, true)) {
                    this.f2001b.a(this.f2002c, this.f2003d);
                }
            }

            @Override // c.a.s
            public void onComplete() {
                if (this.f2004e) {
                    return;
                }
                this.f2004e = true;
                b();
            }

            @Override // c.a.s
            public void onError(Throwable th) {
                if (this.f2004e) {
                    c.a.e0.a.b(th);
                } else {
                    this.f2004e = true;
                    this.f2001b.onError(th);
                }
            }

            @Override // c.a.s
            public void onNext(U u) {
                if (this.f2004e) {
                    return;
                }
                this.f2004e = true;
                dispose();
                b();
            }
        }

        a(c.a.s<? super T> sVar, c.a.a0.n<? super T, ? extends c.a.q<U>> nVar) {
            this.f1995a = sVar;
            this.f1996b = nVar;
        }

        void a(long j, T t) {
            if (j == this.f1999e) {
                this.f1995a.onNext(t);
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f1997c.dispose();
            c.a.b0.a.c.a(this.f1998d);
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2000f) {
                return;
            }
            this.f2000f = true;
            c.a.y.b bVar = this.f1998d.get();
            if (bVar != c.a.b0.a.c.DISPOSED) {
                ((C0055a) bVar).b();
                c.a.b0.a.c.a(this.f1998d);
                this.f1995a.onComplete();
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            c.a.b0.a.c.a(this.f1998d);
            this.f1995a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2000f) {
                return;
            }
            long j = this.f1999e + 1;
            this.f1999e = j;
            c.a.y.b bVar = this.f1998d.get();
            if (bVar != null) {
                bVar.dispose();
            }
            try {
                c.a.q<U> qVarApply = this.f1996b.apply(t);
                c.a.b0.b.b.a(qVarApply, "The ObservableSource supplied is null");
                c.a.q<U> qVar = qVarApply;
                C0055a c0055a = new C0055a(this, j, t);
                if (this.f1998d.compareAndSet(bVar, c0055a)) {
                    qVar.subscribe(c0055a);
                }
            } catch (Throwable th) {
                c.a.z.b.b(th);
                dispose();
                this.f1995a.onError(th);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f1997c, bVar)) {
                this.f1997c = bVar;
                this.f1995a.onSubscribe(this);
            }
        }
    }

    public c0(c.a.q<T> qVar, c.a.a0.n<? super T, ? extends c.a.q<U>> nVar) {
        super(qVar);
        this.f1994b = nVar;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(new c.a.d0.f(sVar), this.f1994b));
    }
}
