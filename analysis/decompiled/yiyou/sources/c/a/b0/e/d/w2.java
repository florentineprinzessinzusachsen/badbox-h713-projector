package c.a.b0.e.d;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: ObservableScalarXMap.java */
/* JADX INFO: loaded from: classes.dex */
public final class w2 {

    /* JADX INFO: compiled from: ObservableScalarXMap.java */
    public static final class a<T> extends AtomicInteger implements c.a.b0.c.e<T>, Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2840a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final T f2841b;

        public a(c.a.s<? super T> sVar, T t) {
            this.f2840a = sVar;
            this.f2841b = t;
        }

        @Override // c.a.b0.c.f
        public int a(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            lazySet(1);
            return 1;
        }

        @Override // c.a.b0.c.j
        public void clear() {
            lazySet(3);
        }

        @Override // c.a.y.b
        public void dispose() {
            set(3);
        }

        @Override // c.a.b0.c.j
        public boolean isEmpty() {
            return get() != 1;
        }

        @Override // c.a.b0.c.j
        public boolean offer(T t) {
            throw new UnsupportedOperationException("Should not be called!");
        }

        @Override // c.a.b0.c.j
        public T poll() {
            if (get() != 1) {
                return null;
            }
            lazySet(3);
            return this.f2841b;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (get() == 0 && compareAndSet(0, 2)) {
                this.f2840a.onNext(this.f2841b);
                if (get() == 2) {
                    lazySet(3);
                    this.f2840a.onComplete();
                }
            }
        }
    }

    /* JADX INFO: compiled from: ObservableScalarXMap.java */
    static final class b<T, R> extends c.a.l<R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final T f2842a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.n<? super T, ? extends c.a.q<? extends R>> f2843b;

        b(T t, c.a.a0.n<? super T, ? extends c.a.q<? extends R>> nVar) {
            this.f2842a = t;
            this.f2843b = nVar;
        }

        @Override // c.a.l
        public void subscribeActual(c.a.s<? super R> sVar) {
            try {
                c.a.q<? extends R> qVarApply = this.f2843b.apply(this.f2842a);
                c.a.b0.b.b.a(qVarApply, "The mapper returned a null ObservableSource");
                c.a.q<? extends R> qVar = qVarApply;
                if (!(qVar instanceof Callable)) {
                    qVar.subscribe(sVar);
                    return;
                }
                try {
                    Object objCall = ((Callable) qVar).call();
                    if (objCall == null) {
                        c.a.b0.a.d.a(sVar);
                        return;
                    }
                    a aVar = new a(sVar, objCall);
                    sVar.onSubscribe(aVar);
                    aVar.run();
                } catch (Throwable th) {
                    c.a.z.b.b(th);
                    c.a.b0.a.d.a(th, sVar);
                }
            } catch (Throwable th2) {
                c.a.b0.a.d.a(th2, sVar);
            }
        }
    }

    public static <T, R> boolean a(c.a.q<T> qVar, c.a.s<? super R> sVar, c.a.a0.n<? super T, ? extends c.a.q<? extends R>> nVar) {
        if (!(qVar instanceof Callable)) {
            return false;
        }
        try {
            a.a.a.b.b.a aVar = (Object) ((Callable) qVar).call();
            if (aVar == null) {
                c.a.b0.a.d.a(sVar);
                return true;
            }
            try {
                c.a.q<? extends R> qVarApply = nVar.apply(aVar);
                c.a.b0.b.b.a(qVarApply, "The mapper returned a null ObservableSource");
                c.a.q<? extends R> qVar2 = qVarApply;
                if (qVar2 instanceof Callable) {
                    try {
                        Object objCall = ((Callable) qVar2).call();
                        if (objCall == null) {
                            c.a.b0.a.d.a(sVar);
                            return true;
                        }
                        a aVar2 = new a(sVar, objCall);
                        sVar.onSubscribe(aVar2);
                        aVar2.run();
                    } catch (Throwable th) {
                        c.a.z.b.b(th);
                        c.a.b0.a.d.a(th, sVar);
                        return true;
                    }
                } else {
                    qVar2.subscribe(sVar);
                }
                return true;
            } catch (Throwable th2) {
                c.a.z.b.b(th2);
                c.a.b0.a.d.a(th2, sVar);
                return true;
            }
        } catch (Throwable th3) {
            c.a.z.b.b(th3);
            c.a.b0.a.d.a(th3, sVar);
            return true;
        }
    }

    public static <T, U> c.a.l<U> a(T t, c.a.a0.n<? super T, ? extends c.a.q<? extends U>> nVar) {
        return c.a.e0.a.a(new b(t, nVar));
    }
}
