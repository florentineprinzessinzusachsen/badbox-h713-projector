package c.a.b0.e.d;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: ObservableGenerate.java */
/* JADX INFO: loaded from: classes.dex */
public final class h1<T, S> extends c.a.l<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Callable<S> f2215a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.c<S, c.a.e<T>, S> f2216b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.f<? super S> f2217c;

    public h1(Callable<S> callable, c.a.a0.c<S, c.a.e<T>, S> cVar, c.a.a0.f<? super S> fVar) {
        this.f2215a = callable;
        this.f2216b = cVar;
        this.f2217c = fVar;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        try {
            a aVar = new a(sVar, this.f2216b, this.f2217c, this.f2215a.call());
            sVar.onSubscribe(aVar);
            aVar.a();
        } catch (Throwable th) {
            c.a.z.b.b(th);
            c.a.b0.a.d.a(th, sVar);
        }
    }

    /* JADX INFO: compiled from: ObservableGenerate.java */
    static final class a<T, S> implements c.a.e<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2218a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.c<S, ? super c.a.e<T>, S> f2219b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.a0.f<? super S> f2220c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        S f2221d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        volatile boolean f2222e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        boolean f2223f;

        a(c.a.s<? super T> sVar, c.a.a0.c<S, ? super c.a.e<T>, S> cVar, c.a.a0.f<? super S> fVar, S s) {
            this.f2218a = sVar;
            this.f2219b = cVar;
            this.f2220c = fVar;
            this.f2221d = s;
        }

        public void a() {
            S sA = this.f2221d;
            if (this.f2222e) {
                this.f2221d = null;
                a(sA);
                return;
            }
            c.a.a0.c<S, ? super c.a.e<T>, S> cVar = this.f2219b;
            while (!this.f2222e) {
                try {
                    sA = cVar.a(sA, this);
                    if (this.f2223f) {
                        this.f2222e = true;
                        this.f2221d = null;
                        a(sA);
                        return;
                    }
                } catch (Throwable th) {
                    c.a.z.b.b(th);
                    this.f2221d = null;
                    this.f2222e = true;
                    a(th);
                    a(sA);
                    return;
                }
            }
            this.f2221d = null;
            a(sA);
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2222e = true;
        }

        private void a(S s) {
            try {
                this.f2220c.a(s);
            } catch (Throwable th) {
                c.a.z.b.b(th);
                c.a.e0.a.b(th);
            }
        }

        public void a(Throwable th) {
            if (this.f2223f) {
                c.a.e0.a.b(th);
                return;
            }
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            this.f2223f = true;
            this.f2218a.onError(th);
        }
    }
}
