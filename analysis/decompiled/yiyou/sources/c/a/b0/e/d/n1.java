package c.a.b0.e.d;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: ObservableInternalHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class n1 {

    /* JADX INFO: compiled from: ObservableInternalHelper.java */
    static final class a<T> implements Callable<c.a.c0.a<T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c.a.l<T> f2475a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f2476b;

        a(c.a.l<T> lVar, int i) {
            this.f2475a = lVar;
            this.f2476b = i;
        }

        @Override // java.util.concurrent.Callable
        public c.a.c0.a<T> call() {
            return this.f2475a.replay(this.f2476b);
        }
    }

    /* JADX INFO: compiled from: ObservableInternalHelper.java */
    static final class b<T> implements Callable<c.a.c0.a<T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c.a.l<T> f2477a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f2478b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final long f2479c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final TimeUnit f2480d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final c.a.t f2481e;

        b(c.a.l<T> lVar, int i, long j, TimeUnit timeUnit, c.a.t tVar) {
            this.f2477a = lVar;
            this.f2478b = i;
            this.f2479c = j;
            this.f2480d = timeUnit;
            this.f2481e = tVar;
        }

        @Override // java.util.concurrent.Callable
        public c.a.c0.a<T> call() {
            return this.f2477a.replay(this.f2478b, this.f2479c, this.f2480d, this.f2481e);
        }
    }

    /* JADX INFO: compiled from: ObservableInternalHelper.java */
    static final class c<T, U> implements c.a.a0.n<T, c.a.q<U>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c.a.a0.n<? super T, ? extends Iterable<? extends U>> f2482a;

        c(c.a.a0.n<? super T, ? extends Iterable<? extends U>> nVar) {
            this.f2482a = nVar;
        }

        @Override // c.a.a0.n
        public c.a.q<U> apply(T t) {
            Iterable<? extends U> iterableApply = this.f2482a.apply(t);
            c.a.b0.b.b.a(iterableApply, "The mapper returned a null Iterable");
            return new e1(iterableApply);
        }
    }

    /* JADX INFO: compiled from: ObservableInternalHelper.java */
    static final class d<U, R, T> implements c.a.a0.n<U, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c.a.a0.c<? super T, ? super U, ? extends R> f2483a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final T f2484b;

        d(c.a.a0.c<? super T, ? super U, ? extends R> cVar, T t) {
            this.f2483a = cVar;
            this.f2484b = t;
        }

        @Override // c.a.a0.n
        public R apply(U u) {
            return this.f2483a.a(this.f2484b, u);
        }
    }

    /* JADX INFO: compiled from: ObservableInternalHelper.java */
    static final class e<T, R, U> implements c.a.a0.n<T, c.a.q<R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c.a.a0.c<? super T, ? super U, ? extends R> f2485a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final c.a.a0.n<? super T, ? extends c.a.q<? extends U>> f2486b;

        e(c.a.a0.c<? super T, ? super U, ? extends R> cVar, c.a.a0.n<? super T, ? extends c.a.q<? extends U>> nVar) {
            this.f2485a = cVar;
            this.f2486b = nVar;
        }

        @Override // c.a.a0.n
        public c.a.q<R> apply(T t) {
            c.a.q<? extends U> qVarApply = this.f2486b.apply(t);
            c.a.b0.b.b.a(qVarApply, "The mapper returned a null ObservableSource");
            return new v1(qVarApply, new d(this.f2485a, t));
        }
    }

    /* JADX INFO: compiled from: ObservableInternalHelper.java */
    static final class f<T, U> implements c.a.a0.n<T, c.a.q<T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.a0.n<? super T, ? extends c.a.q<U>> f2487a;

        f(c.a.a0.n<? super T, ? extends c.a.q<U>> nVar) {
            this.f2487a = nVar;
        }

        @Override // c.a.a0.n
        public c.a.q<T> apply(T t) {
            c.a.q<U> qVarApply = this.f2487a.apply(t);
            c.a.b0.b.b.a(qVarApply, "The itemDelay returned a null ObservableSource");
            return new m3(qVarApply, 1L).map(c.a.b0.b.a.c(t)).defaultIfEmpty(t);
        }
    }

    /* JADX INFO: compiled from: ObservableInternalHelper.java */
    static final class g<T> implements c.a.a0.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<T> f2488a;

        g(c.a.s<T> sVar) {
            this.f2488a = sVar;
        }

        @Override // c.a.a0.a
        public void run() {
            this.f2488a.onComplete();
        }
    }

    /* JADX INFO: compiled from: ObservableInternalHelper.java */
    static final class h<T> implements c.a.a0.f<Throwable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<T> f2489a;

        h(c.a.s<T> sVar) {
            this.f2489a = sVar;
        }

        @Override // c.a.a0.f
        public void a(Throwable th) {
            this.f2489a.onError(th);
        }
    }

    /* JADX INFO: compiled from: ObservableInternalHelper.java */
    static final class i<T> implements c.a.a0.f<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<T> f2490a;

        i(c.a.s<T> sVar) {
            this.f2490a = sVar;
        }

        @Override // c.a.a0.f
        public void a(T t) {
            this.f2490a.onNext(t);
        }
    }

    /* JADX INFO: compiled from: ObservableInternalHelper.java */
    static final class j<T> implements Callable<c.a.c0.a<T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c.a.l<T> f2491a;

        j(c.a.l<T> lVar) {
            this.f2491a = lVar;
        }

        @Override // java.util.concurrent.Callable
        public c.a.c0.a<T> call() {
            return this.f2491a.replay();
        }
    }

    /* JADX INFO: compiled from: ObservableInternalHelper.java */
    static final class k<T, R> implements c.a.a0.n<c.a.l<T>, c.a.q<R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c.a.a0.n<? super c.a.l<T>, ? extends c.a.q<R>> f2492a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final c.a.t f2493b;

        k(c.a.a0.n<? super c.a.l<T>, ? extends c.a.q<R>> nVar, c.a.t tVar) {
            this.f2492a = nVar;
            this.f2493b = tVar;
        }

        @Override // c.a.a0.n
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c.a.q<R> apply(c.a.l<T> lVar) {
            c.a.q<R> qVarApply = this.f2492a.apply(lVar);
            c.a.b0.b.b.a(qVarApply, "The selector returned a null ObservableSource");
            return c.a.l.wrap(qVarApply).observeOn(this.f2493b);
        }
    }

    /* JADX INFO: compiled from: ObservableInternalHelper.java */
    static final class l<T, S> implements c.a.a0.c<S, c.a.e<T>, S> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.a0.b<S, c.a.e<T>> f2494a;

        l(c.a.a0.b<S, c.a.e<T>> bVar) {
            this.f2494a = bVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // c.a.a0.c
        public /* bridge */ /* synthetic */ Object a(Object obj, Object obj2) {
            a(obj, (c.a.e) obj2);
            return obj;
        }

        public S a(S s, c.a.e<T> eVar) {
            this.f2494a.a(s, eVar);
            return s;
        }
    }

    /* JADX INFO: compiled from: ObservableInternalHelper.java */
    static final class m<T, S> implements c.a.a0.c<S, c.a.e<T>, S> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.a0.f<c.a.e<T>> f2495a;

        m(c.a.a0.f<c.a.e<T>> fVar) {
            this.f2495a = fVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // c.a.a0.c
        public /* bridge */ /* synthetic */ Object a(Object obj, Object obj2) {
            a(obj, (c.a.e) obj2);
            return obj;
        }

        public S a(S s, c.a.e<T> eVar) {
            this.f2495a.a(eVar);
            return s;
        }
    }

    /* JADX INFO: compiled from: ObservableInternalHelper.java */
    static final class n<T> implements Callable<c.a.c0.a<T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c.a.l<T> f2496a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final long f2497b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final TimeUnit f2498c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final c.a.t f2499d;

        n(c.a.l<T> lVar, long j, TimeUnit timeUnit, c.a.t tVar) {
            this.f2496a = lVar;
            this.f2497b = j;
            this.f2498c = timeUnit;
            this.f2499d = tVar;
        }

        @Override // java.util.concurrent.Callable
        public c.a.c0.a<T> call() {
            return this.f2496a.replay(this.f2497b, this.f2498c, this.f2499d);
        }
    }

    /* JADX INFO: compiled from: ObservableInternalHelper.java */
    static final class o<T, R> implements c.a.a0.n<List<c.a.q<? extends T>>, c.a.q<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c.a.a0.n<? super Object[], ? extends R> f2500a;

        o(c.a.a0.n<? super Object[], ? extends R> nVar) {
            this.f2500a = nVar;
        }

        @Override // c.a.a0.n
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c.a.q<? extends R> apply(List<c.a.q<? extends T>> list) {
            return c.a.l.zipIterable(list, this.f2500a, false, c.a.l.bufferSize());
        }
    }

    public static <T, S> c.a.a0.c<S, c.a.e<T>, S> a(c.a.a0.f<c.a.e<T>> fVar) {
        return new m(fVar);
    }

    public static <T, U> c.a.a0.n<T, c.a.q<T>> b(c.a.a0.n<? super T, ? extends c.a.q<U>> nVar) {
        return new f(nVar);
    }

    public static <T> c.a.a0.f<T> c(c.a.s<T> sVar) {
        return new i(sVar);
    }

    public static <T, S> c.a.a0.c<S, c.a.e<T>, S> a(c.a.a0.b<S, c.a.e<T>> bVar) {
        return new l(bVar);
    }

    public static <T> c.a.a0.f<Throwable> b(c.a.s<T> sVar) {
        return new h(sVar);
    }

    public static <T, R> c.a.a0.n<List<c.a.q<? extends T>>, c.a.q<? extends R>> c(c.a.a0.n<? super Object[], ? extends R> nVar) {
        return new o(nVar);
    }

    public static <T> c.a.a0.a a(c.a.s<T> sVar) {
        return new g(sVar);
    }

    public static <T, U, R> c.a.a0.n<T, c.a.q<R>> a(c.a.a0.n<? super T, ? extends c.a.q<? extends U>> nVar, c.a.a0.c<? super T, ? super U, ? extends R> cVar) {
        return new e(cVar, nVar);
    }

    public static <T, U> c.a.a0.n<T, c.a.q<U>> a(c.a.a0.n<? super T, ? extends Iterable<? extends U>> nVar) {
        return new c(nVar);
    }

    public static <T> Callable<c.a.c0.a<T>> a(c.a.l<T> lVar) {
        return new j(lVar);
    }

    public static <T> Callable<c.a.c0.a<T>> a(c.a.l<T> lVar, int i2) {
        return new a(lVar, i2);
    }

    public static <T> Callable<c.a.c0.a<T>> a(c.a.l<T> lVar, int i2, long j2, TimeUnit timeUnit, c.a.t tVar) {
        return new b(lVar, i2, j2, timeUnit, tVar);
    }

    public static <T> Callable<c.a.c0.a<T>> a(c.a.l<T> lVar, long j2, TimeUnit timeUnit, c.a.t tVar) {
        return new n(lVar, j2, timeUnit, tVar);
    }

    public static <T, R> c.a.a0.n<c.a.l<T>, c.a.q<R>> a(c.a.a0.n<? super c.a.l<T>, ? extends c.a.q<R>> nVar, c.a.t tVar) {
        return new k(nVar, tVar);
    }
}
