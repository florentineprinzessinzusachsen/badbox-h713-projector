package c.a.b0.e.d;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: BlockingObservableIterable.java */
/* JADX INFO: loaded from: classes.dex */
public final class b<T> implements Iterable<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<? extends T> f1969a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final int f1970b;

    /* JADX INFO: compiled from: BlockingObservableIterable.java */
    static final class a<T> extends AtomicReference<c.a.y.b> implements c.a.s<T>, Iterator<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.b0.f.c<T> f1971a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Lock f1972b = new ReentrantLock();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final Condition f1973c = this.f1972b.newCondition();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        volatile boolean f1974d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        Throwable f1975e;

        a(int i) {
            this.f1971a = new c.a.b0.f.c<>(i);
        }

        void a() {
            this.f1972b.lock();
            try {
                this.f1973c.signalAll();
            } finally {
                this.f1972b.unlock();
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            while (true) {
                boolean z = this.f1974d;
                boolean zIsEmpty = this.f1971a.isEmpty();
                if (z) {
                    Throwable th = this.f1975e;
                    if (th != null) {
                        throw c.a.b0.j.j.a(th);
                    }
                    if (zIsEmpty) {
                        return false;
                    }
                }
                if (!zIsEmpty) {
                    return true;
                }
                try {
                    c.a.b0.j.e.a();
                    this.f1972b.lock();
                    while (!this.f1974d && this.f1971a.isEmpty()) {
                        try {
                            this.f1973c.await();
                        } catch (Throwable th2) {
                            this.f1972b.unlock();
                            throw th2;
                        }
                    }
                    this.f1972b.unlock();
                } catch (InterruptedException e2) {
                    c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
                    a();
                    throw c.a.b0.j.j.a(e2);
                }
            }
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                return this.f1971a.poll();
            }
            throw new NoSuchElementException();
        }

        @Override // c.a.s
        public void onComplete() {
            this.f1974d = true;
            a();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f1975e = th;
            this.f1974d = true;
            a();
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f1971a.offer(t);
            a();
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.b0.a.c.c(this, bVar);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }

    public b(c.a.q<? extends T> qVar, int i) {
        this.f1969a = qVar;
        this.f1970b = i;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        a aVar = new a(this.f1970b);
        this.f1969a.subscribe(aVar);
        return aVar;
    }
}
