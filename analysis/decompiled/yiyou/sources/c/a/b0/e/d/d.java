package c.a.b0.e.d;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: BlockingObservableMostRecent.java */
/* JADX INFO: loaded from: classes.dex */
public final class d<T> implements Iterable<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<T> f2030a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final T f2031b;

    /* JADX INFO: compiled from: BlockingObservableMostRecent.java */
    static final class a<T> extends c.a.d0.b<T> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        volatile Object f2032b;

        /* JADX INFO: renamed from: c.a.b0.e.d.d$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: BlockingObservableMostRecent.java */
        final class C0056a implements Iterator<T> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private Object f2033a;

            C0056a() {
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                this.f2033a = a.this.f2032b;
                return !c.a.b0.j.n.c(this.f2033a);
            }

            @Override // java.util.Iterator
            public T next() {
                try {
                    if (this.f2033a == null) {
                        this.f2033a = a.this.f2032b;
                    }
                    if (c.a.b0.j.n.c(this.f2033a)) {
                        throw new NoSuchElementException();
                    }
                    if (c.a.b0.j.n.d(this.f2033a)) {
                        throw c.a.b0.j.j.a(c.a.b0.j.n.a(this.f2033a));
                    }
                    T t = (T) this.f2033a;
                    c.a.b0.j.n.b(t);
                    this.f2033a = null;
                    return t;
                } catch (Throwable th) {
                    this.f2033a = null;
                    throw th;
                }
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("Read only iterator");
            }
        }

        a(T t) {
            c.a.b0.j.n.e(t);
            this.f2032b = t;
        }

        public a<T>.C0056a b() {
            return new C0056a();
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2032b = c.a.b0.j.n.a();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2032b = c.a.b0.j.n.a(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            c.a.b0.j.n.e(t);
            this.f2032b = t;
        }
    }

    public d(c.a.q<T> qVar, T t) {
        this.f2030a = qVar;
        this.f2031b = t;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        a aVar = new a(this.f2031b);
        this.f2030a.subscribe(aVar);
        return aVar.b();
    }
}
