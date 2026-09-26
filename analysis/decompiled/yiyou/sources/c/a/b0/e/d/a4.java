package c.a.b0.e.d;

import java.util.Collection;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: ObservableToListSingle.java */
/* JADX INFO: loaded from: classes.dex */
public final class a4<T, U extends Collection<? super T>> extends c.a.u<U> implements c.a.b0.c.a<U> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<T> f1964a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Callable<U> f1965b;

    /* JADX INFO: compiled from: ObservableToListSingle.java */
    static final class a<T, U extends Collection<? super T>> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.v<? super U> f1966a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        U f1967b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f1968c;

        a(c.a.v<? super U> vVar, U u) {
            this.f1966a = vVar;
            this.f1967b = u;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f1968c.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            U u = this.f1967b;
            this.f1967b = null;
            this.f1966a.a(u);
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f1967b = null;
            this.f1966a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f1967b.add(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f1968c, bVar)) {
                this.f1968c = bVar;
                this.f1966a.onSubscribe(this);
            }
        }
    }

    public a4(c.a.q<T> qVar, int i) {
        this.f1964a = qVar;
        this.f1965b = c.a.b0.b.a.a(i);
    }

    @Override // c.a.b0.c.a
    public c.a.l<U> a() {
        return c.a.e0.a.a(new z3(this.f1964a, this.f1965b));
    }

    @Override // c.a.u
    public void b(c.a.v<? super U> vVar) {
        try {
            U uCall = this.f1965b.call();
            c.a.b0.b.b.a(uCall, "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.");
            this.f1964a.subscribe(new a(vVar, uCall));
        } catch (Throwable th) {
            c.a.z.b.b(th);
            c.a.b0.a.d.a(th, vVar);
        }
    }

    public a4(c.a.q<T> qVar, Callable<U> callable) {
        this.f1964a = qVar;
        this.f1965b = callable;
    }
}
