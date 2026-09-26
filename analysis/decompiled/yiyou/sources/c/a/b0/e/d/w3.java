package c.a.b0.e.d;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableTimeout.java */
/* JADX INFO: loaded from: classes.dex */
public final class w3<T, U, V> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.q<U> f2844b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.n<? super T, ? extends c.a.q<V>> f2845c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final c.a.q<? extends T> f2846d;

    /* JADX INFO: compiled from: ObservableTimeout.java */
    static final class a extends AtomicReference<c.a.y.b> implements c.a.s<Object>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final d f2847a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f2848b;

        a(long j, d dVar) {
            this.f2848b = j;
            this.f2847a = dVar;
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
        }

        @Override // c.a.s
        public void onComplete() {
            Object obj = get();
            c.a.b0.a.c cVar = c.a.b0.a.c.DISPOSED;
            if (obj != cVar) {
                lazySet(cVar);
                this.f2847a.a(this.f2848b);
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            Object obj = get();
            c.a.b0.a.c cVar = c.a.b0.a.c.DISPOSED;
            if (obj == cVar) {
                c.a.e0.a.b(th);
            } else {
                lazySet(cVar);
                this.f2847a.a(this.f2848b, th);
            }
        }

        @Override // c.a.s
        public void onNext(Object obj) {
            c.a.y.b bVar = (c.a.y.b) get();
            if (bVar != c.a.b0.a.c.DISPOSED) {
                bVar.dispose();
                lazySet(c.a.b0.a.c.DISPOSED);
                this.f2847a.a(this.f2848b);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.c(this, bVar);
        }
    }

    /* JADX INFO: compiled from: ObservableTimeout.java */
    interface d extends x3.d {
        void a(long j, Throwable th);
    }

    public w3(c.a.l<T> lVar, c.a.q<U> qVar, c.a.a0.n<? super T, ? extends c.a.q<V>> nVar, c.a.q<? extends T> qVar2) {
        super(lVar);
        this.f2844b = qVar;
        this.f2845c = nVar;
        this.f2846d = qVar2;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        c.a.q<? extends T> qVar = this.f2846d;
        if (qVar == null) {
            c cVar = new c(sVar, this.f2845c);
            sVar.onSubscribe(cVar);
            cVar.a((c.a.q<?>) this.f2844b);
            this.f1932a.subscribe(cVar);
            return;
        }
        b bVar = new b(sVar, this.f2845c, qVar);
        sVar.onSubscribe(bVar);
        bVar.a((c.a.q<?>) this.f2844b);
        this.f1932a.subscribe(bVar);
    }

    /* JADX INFO: compiled from: ObservableTimeout.java */
    static final class b<T> extends AtomicReference<c.a.y.b> implements c.a.s<T>, c.a.y.b, d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2849a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.n<? super T, ? extends c.a.q<?>> f2850b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.b0.a.f f2851c = new c.a.b0.a.f();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final AtomicLong f2852d = new AtomicLong();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final AtomicReference<c.a.y.b> f2853e = new AtomicReference<>();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        c.a.q<? extends T> f2854f;

        b(c.a.s<? super T> sVar, c.a.a0.n<? super T, ? extends c.a.q<?>> nVar, c.a.q<? extends T> qVar) {
            this.f2849a = sVar;
            this.f2850b = nVar;
            this.f2854f = qVar;
        }

        void a(c.a.q<?> qVar) {
            if (qVar != null) {
                a aVar = new a(0L, this);
                if (this.f2851c.a(aVar)) {
                    qVar.subscribe(aVar);
                }
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a(this.f2853e);
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
            this.f2851c.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2852d.getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.f2851c.dispose();
                this.f2849a.onComplete();
                this.f2851c.dispose();
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2852d.getAndSet(Long.MAX_VALUE) == Long.MAX_VALUE) {
                c.a.e0.a.b(th);
                return;
            }
            this.f2851c.dispose();
            this.f2849a.onError(th);
            this.f2851c.dispose();
        }

        @Override // c.a.s
        public void onNext(T t) {
            long j = this.f2852d.get();
            if (j != Long.MAX_VALUE) {
                long j2 = 1 + j;
                if (this.f2852d.compareAndSet(j, j2)) {
                    c.a.y.b bVar = this.f2851c.get();
                    if (bVar != null) {
                        bVar.dispose();
                    }
                    this.f2849a.onNext(t);
                    try {
                        c.a.q<?> qVarApply = this.f2850b.apply(t);
                        c.a.b0.b.b.a(qVarApply, "The itemTimeoutIndicator returned a null ObservableSource.");
                        c.a.q<?> qVar = qVarApply;
                        a aVar = new a(j2, this);
                        if (this.f2851c.a(aVar)) {
                            qVar.subscribe(aVar);
                        }
                    } catch (Throwable th) {
                        c.a.z.b.b(th);
                        this.f2853e.get().dispose();
                        this.f2852d.getAndSet(Long.MAX_VALUE);
                        this.f2849a.onError(th);
                    }
                }
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.c(this.f2853e, bVar);
        }

        @Override // c.a.b0.e.d.x3.d
        public void a(long j) {
            if (this.f2852d.compareAndSet(j, Long.MAX_VALUE)) {
                c.a.b0.a.c.a(this.f2853e);
                c.a.q<? extends T> qVar = this.f2854f;
                this.f2854f = null;
                qVar.subscribe(new x3.a(this.f2849a, this));
            }
        }

        @Override // c.a.b0.e.d.w3.d
        public void a(long j, Throwable th) {
            if (this.f2852d.compareAndSet(j, Long.MAX_VALUE)) {
                c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
                this.f2849a.onError(th);
            } else {
                c.a.e0.a.b(th);
            }
        }
    }

    /* JADX INFO: compiled from: ObservableTimeout.java */
    static final class c<T> extends AtomicLong implements c.a.s<T>, c.a.y.b, d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2855a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.n<? super T, ? extends c.a.q<?>> f2856b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.b0.a.f f2857c = new c.a.b0.a.f();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final AtomicReference<c.a.y.b> f2858d = new AtomicReference<>();

        c(c.a.s<? super T> sVar, c.a.a0.n<? super T, ? extends c.a.q<?>> nVar) {
            this.f2855a = sVar;
            this.f2856b = nVar;
        }

        void a(c.a.q<?> qVar) {
            if (qVar != null) {
                a aVar = new a(0L, this);
                if (this.f2857c.a(aVar)) {
                    qVar.subscribe(aVar);
                }
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a(this.f2858d);
            this.f2857c.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.f2857c.dispose();
                this.f2855a.onComplete();
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (getAndSet(Long.MAX_VALUE) == Long.MAX_VALUE) {
                c.a.e0.a.b(th);
            } else {
                this.f2857c.dispose();
                this.f2855a.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            long j = get();
            if (j != Long.MAX_VALUE) {
                long j2 = 1 + j;
                if (compareAndSet(j, j2)) {
                    c.a.y.b bVar = this.f2857c.get();
                    if (bVar != null) {
                        bVar.dispose();
                    }
                    this.f2855a.onNext(t);
                    try {
                        c.a.q<?> qVarApply = this.f2856b.apply(t);
                        c.a.b0.b.b.a(qVarApply, "The itemTimeoutIndicator returned a null ObservableSource.");
                        c.a.q<?> qVar = qVarApply;
                        a aVar = new a(j2, this);
                        if (this.f2857c.a(aVar)) {
                            qVar.subscribe(aVar);
                        }
                    } catch (Throwable th) {
                        c.a.z.b.b(th);
                        this.f2858d.get().dispose();
                        getAndSet(Long.MAX_VALUE);
                        this.f2855a.onError(th);
                    }
                }
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.c(this.f2858d, bVar);
        }

        @Override // c.a.b0.e.d.x3.d
        public void a(long j) {
            if (compareAndSet(j, Long.MAX_VALUE)) {
                c.a.b0.a.c.a(this.f2858d);
                this.f2855a.onError(new TimeoutException());
            }
        }

        @Override // c.a.b0.e.d.w3.d
        public void a(long j, Throwable th) {
            if (compareAndSet(j, Long.MAX_VALUE)) {
                c.a.b0.a.c.a(this.f2858d);
                this.f2855a.onError(th);
            } else {
                c.a.e0.a.b(th);
            }
        }
    }
}
