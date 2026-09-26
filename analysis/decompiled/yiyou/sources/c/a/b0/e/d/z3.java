package c.a.b0.e.d;

import java.util.Collection;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: ObservableToList.java */
/* JADX INFO: loaded from: classes.dex */
public final class z3<T, U extends Collection<? super T>> extends c.a.b0.e.d.a<T, U> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Callable<U> f2968b;

    /* JADX INFO: compiled from: ObservableToList.java */
    static final class a<T, U extends Collection<? super T>> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        U f2969a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.s<? super U> f2970b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f2971c;

        a(c.a.s<? super U> sVar, U u) {
            this.f2970b = sVar;
            this.f2969a = u;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2971c.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            U u = this.f2969a;
            this.f2969a = null;
            this.f2970b.onNext(u);
            this.f2970b.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2969a = null;
            this.f2970b.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2969a.add(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2971c, bVar)) {
                this.f2971c = bVar;
                this.f2970b.onSubscribe(this);
            }
        }
    }

    public z3(c.a.q<T> qVar, int i) {
        super(qVar);
        this.f2968b = c.a.b0.b.a.a(i);
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super U> sVar) {
        try {
            U uCall = this.f2968b.call();
            c.a.b0.b.b.a(uCall, "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.");
            this.f1932a.subscribe(new a(sVar, uCall));
        } catch (Throwable th) {
            c.a.z.b.b(th);
            c.a.b0.a.d.a(th, sVar);
        }
    }

    public z3(c.a.q<T> qVar, Callable<U> callable) {
        super(qVar);
        this.f2968b = callable;
    }
}
