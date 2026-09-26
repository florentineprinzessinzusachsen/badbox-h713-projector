package c.a.b0.e.d;

import java.util.Iterator;

/* JADX INFO: compiled from: ObservableZipIterable.java */
/* JADX INFO: loaded from: classes.dex */
public final class l4<T, U, V> extends c.a.l<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.l<? extends T> f2411a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Iterable<U> f2412b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.c<? super T, ? super U, ? extends V> f2413c;

    /* JADX INFO: compiled from: ObservableZipIterable.java */
    static final class a<T, U, V> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super V> f2414a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Iterator<U> f2415b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.a0.c<? super T, ? super U, ? extends V> f2416c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        c.a.y.b f2417d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f2418e;

        a(c.a.s<? super V> sVar, Iterator<U> it, c.a.a0.c<? super T, ? super U, ? extends V> cVar) {
            this.f2414a = sVar;
            this.f2415b = it;
            this.f2416c = cVar;
        }

        void a(Throwable th) {
            this.f2418e = true;
            this.f2417d.dispose();
            this.f2414a.onError(th);
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2417d.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (this.f2418e) {
                return;
            }
            this.f2418e = true;
            this.f2414a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (this.f2418e) {
                c.a.e0.a.b(th);
            } else {
                this.f2418e = true;
                this.f2414a.onError(th);
            }
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2418e) {
                return;
            }
            try {
                U next = this.f2415b.next();
                c.a.b0.b.b.a(next, "The iterator returned a null value");
                try {
                    V vA = this.f2416c.a(t, next);
                    c.a.b0.b.b.a(vA, "The zipper function returned a null value");
                    this.f2414a.onNext(vA);
                    try {
                        if (this.f2415b.hasNext()) {
                            return;
                        }
                        this.f2418e = true;
                        this.f2417d.dispose();
                        this.f2414a.onComplete();
                    } catch (Throwable th) {
                        c.a.z.b.b(th);
                        a(th);
                    }
                } catch (Throwable th2) {
                    c.a.z.b.b(th2);
                    a(th2);
                }
            } catch (Throwable th3) {
                c.a.z.b.b(th3);
                a(th3);
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2417d, bVar)) {
                this.f2417d = bVar;
                this.f2414a.onSubscribe(this);
            }
        }
    }

    public l4(c.a.l<? extends T> lVar, Iterable<U> iterable, c.a.a0.c<? super T, ? super U, ? extends V> cVar) {
        this.f2411a = lVar;
        this.f2412b = iterable;
        this.f2413c = cVar;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super V> sVar) {
        try {
            Iterator<U> it = this.f2412b.iterator();
            c.a.b0.b.b.a(it, "The iterator returned by other is null");
            Iterator<U> it2 = it;
            try {
                if (it2.hasNext()) {
                    this.f2411a.subscribe(new a(sVar, it2, this.f2413c));
                } else {
                    c.a.b0.a.d.a(sVar);
                }
            } catch (Throwable th) {
                c.a.z.b.b(th);
                c.a.b0.a.d.a(th, sVar);
            }
        } catch (Throwable th2) {
            c.a.z.b.b(th2);
            c.a.b0.a.d.a(th2, sVar);
        }
    }
}
