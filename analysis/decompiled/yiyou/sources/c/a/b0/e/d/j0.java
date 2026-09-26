package c.a.b0.e.d;

import java.util.Collection;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: ObservableDistinct.java */
/* JADX INFO: loaded from: classes.dex */
public final class j0<T, K> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.n<? super T, K> f2299b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Callable<? extends Collection<? super K>> f2300c;

    /* JADX INFO: compiled from: ObservableDistinct.java */
    static final class a<T, K> extends c.a.b0.d.a<T, T> {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final Collection<? super K> f2301f;
        final c.a.a0.n<? super T, K> g;

        a(c.a.s<? super T> sVar, c.a.a0.n<? super T, K> nVar, Collection<? super K> collection) {
            super(sVar);
            this.g = nVar;
            this.f2301f = collection;
        }

        @Override // c.a.b0.c.f
        public int a(int i) {
            return b(i);
        }

        @Override // c.a.b0.d.a, c.a.b0.c.j
        public void clear() {
            this.f2301f.clear();
            super.clear();
        }

        @Override // c.a.b0.d.a, c.a.s
        public void onComplete() {
            if (this.f1798d) {
                return;
            }
            this.f1798d = true;
            this.f2301f.clear();
            this.f1795a.onComplete();
        }

        @Override // c.a.b0.d.a, c.a.s
        public void onError(Throwable th) {
            if (this.f1798d) {
                c.a.e0.a.b(th);
                return;
            }
            this.f1798d = true;
            this.f2301f.clear();
            this.f1795a.onError(th);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // c.a.s
        public void onNext(T t) {
            if (this.f1798d) {
                return;
            }
            if (this.f1799e != 0) {
                this.f1795a.onNext(null);
                return;
            }
            try {
                K kApply = this.g.apply(t);
                c.a.b0.b.b.a(kApply, "The keySelector returned a null key");
                if (this.f2301f.add(kApply)) {
                    this.f1795a.onNext((Object) t);
                }
            } catch (Throwable th) {
                a(th);
            }
        }

        @Override // c.a.b0.c.j
        public T poll() {
            T tPoll;
            Collection<? super K> collection;
            K kApply;
            do {
                tPoll = this.f1797c.poll();
                if (tPoll == null) {
                    break;
                }
                collection = this.f2301f;
                kApply = this.g.apply(tPoll);
                c.a.b0.b.b.a(kApply, "The keySelector returned a null key");
            } while (!collection.add(kApply));
            return tPoll;
        }
    }

    public j0(c.a.q<T> qVar, c.a.a0.n<? super T, K> nVar, Callable<? extends Collection<? super K>> callable) {
        super(qVar);
        this.f2299b = nVar;
        this.f2300c = callable;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        try {
            Collection<? super K> collectionCall = this.f2300c.call();
            c.a.b0.b.b.a(collectionCall, "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.");
            this.f1932a.subscribe(new a(sVar, this.f2299b, collectionCall));
        } catch (Throwable th) {
            c.a.z.b.b(th);
            c.a.b0.a.d.a(th, sVar);
        }
    }
}
