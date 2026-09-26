package c.a.b0.e.d;

import java.util.Iterator;

/* JADX INFO: compiled from: ObservableFromIterable.java */
/* JADX INFO: loaded from: classes.dex */
public final class e1<T> extends c.a.l<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Iterable<? extends T> f2091a;

    /* JADX INFO: compiled from: ObservableFromIterable.java */
    static final class a<T> extends c.a.b0.d.c<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2092a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Iterator<? extends T> f2093b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        volatile boolean f2094c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f2095d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f2096e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        boolean f2097f;

        a(c.a.s<? super T> sVar, Iterator<? extends T> it) {
            this.f2092a = sVar;
            this.f2093b = it;
        }

        @Override // c.a.b0.c.f
        public int a(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            this.f2095d = true;
            return 1;
        }

        void b() {
            while (!a()) {
                try {
                    T next = this.f2093b.next();
                    c.a.b0.b.b.a((Object) next, "The iterator returned a null value");
                    this.f2092a.onNext(next);
                    if (a()) {
                        return;
                    }
                    try {
                        if (!this.f2093b.hasNext()) {
                            if (a()) {
                                return;
                            }
                            this.f2092a.onComplete();
                            return;
                        }
                    } catch (Throwable th) {
                        c.a.z.b.b(th);
                        this.f2092a.onError(th);
                        return;
                    }
                } catch (Throwable th2) {
                    c.a.z.b.b(th2);
                    this.f2092a.onError(th2);
                    return;
                }
            }
        }

        @Override // c.a.b0.c.j
        public void clear() {
            this.f2096e = true;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2094c = true;
        }

        @Override // c.a.b0.c.j
        public boolean isEmpty() {
            return this.f2096e;
        }

        @Override // c.a.b0.c.j
        public T poll() {
            if (this.f2096e) {
                return null;
            }
            if (!this.f2097f) {
                this.f2097f = true;
            } else if (!this.f2093b.hasNext()) {
                this.f2096e = true;
                return null;
            }
            T next = this.f2093b.next();
            c.a.b0.b.b.a((Object) next, "The iterator returned a null value");
            return next;
        }

        public boolean a() {
            return this.f2094c;
        }
    }

    public e1(Iterable<? extends T> iterable) {
        this.f2091a = iterable;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        try {
            Iterator<? extends T> it = this.f2091a.iterator();
            try {
                if (!it.hasNext()) {
                    c.a.b0.a.d.a(sVar);
                    return;
                }
                a aVar = new a(sVar, it);
                sVar.onSubscribe(aVar);
                if (aVar.f2095d) {
                    return;
                }
                aVar.b();
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
