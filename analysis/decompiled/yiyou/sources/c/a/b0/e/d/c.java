package c.a.b0.e.d;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: BlockingObservableLatest.java */
/* JADX INFO: loaded from: classes.dex */
public final class c<T> implements Iterable<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<T> f1990a;

    /* JADX INFO: compiled from: BlockingObservableLatest.java */
    static final class a<T> extends c.a.d0.c<c.a.k<T>> implements Iterator<T> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c.a.k<T> f1991b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final Semaphore f1992c = new Semaphore(0);

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final AtomicReference<c.a.k<T>> f1993d = new AtomicReference<>();

        a() {
        }

        @Override // c.a.s
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onNext(c.a.k<T> kVar) {
            if (this.f1993d.getAndSet(kVar) == null) {
                this.f1992c.release();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            c.a.k<T> kVar = this.f1991b;
            if (kVar != null && kVar.d()) {
                throw c.a.b0.j.j.a(this.f1991b.a());
            }
            if (this.f1991b == null) {
                try {
                    c.a.b0.j.e.a();
                    this.f1992c.acquire();
                    c.a.k<T> andSet = this.f1993d.getAndSet(null);
                    this.f1991b = andSet;
                    if (andSet.d()) {
                        throw c.a.b0.j.j.a(andSet.a());
                    }
                } catch (InterruptedException e2) {
                    dispose();
                    this.f1991b = c.a.k.a((Throwable) e2);
                    throw c.a.b0.j.j.a(e2);
                }
            }
            return this.f1991b.e();
        }

        @Override // java.util.Iterator
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T tB = this.f1991b.b();
            this.f1991b = null;
            return tB;
        }

        @Override // c.a.s
        public void onComplete() {
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            c.a.e0.a.b(th);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Read-only iterator.");
        }
    }

    public c(c.a.q<T> qVar) {
        this.f1990a = qVar;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        a aVar = new a();
        c.a.l.wrap(this.f1990a).materialize().subscribe(aVar);
        return aVar;
    }
}
