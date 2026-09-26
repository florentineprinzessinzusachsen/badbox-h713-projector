package c.a.b0.e.d;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: BlockingObservableNext.java */
/* JADX INFO: loaded from: classes.dex */
public final class e<T> implements Iterable<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<T> f2081a;

    /* JADX INFO: compiled from: BlockingObservableNext.java */
    static final class a<T> implements Iterator<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b<T> f2082a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final c.a.q<T> f2083b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private T f2084c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f2085d = true;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f2086e = true;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private Throwable f2087f;
        private boolean g;

        a(c.a.q<T> qVar, b<T> bVar) {
            this.f2083b = qVar;
            this.f2082a = bVar;
        }

        private boolean a() {
            if (!this.g) {
                this.g = true;
                this.f2082a.b();
                new x1(this.f2083b).subscribe(this.f2082a);
            }
            try {
                c.a.k<T> kVarC = this.f2082a.c();
                if (kVarC.e()) {
                    this.f2086e = false;
                    this.f2084c = kVarC.b();
                    return true;
                }
                this.f2085d = false;
                if (kVarC.c()) {
                    return false;
                }
                this.f2087f = kVarC.a();
                throw c.a.b0.j.j.a(this.f2087f);
            } catch (InterruptedException e2) {
                this.f2082a.dispose();
                this.f2087f = e2;
                throw c.a.b0.j.j.a(e2);
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            Throwable th = this.f2087f;
            if (th != null) {
                throw c.a.b0.j.j.a(th);
            }
            if (this.f2085d) {
                return !this.f2086e || a();
            }
            return false;
        }

        @Override // java.util.Iterator
        public T next() {
            Throwable th = this.f2087f;
            if (th != null) {
                throw c.a.b0.j.j.a(th);
            }
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements");
            }
            this.f2086e = true;
            return this.f2084c;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Read only iterator");
        }
    }

    /* JADX INFO: compiled from: BlockingObservableNext.java */
    static final class b<T> extends c.a.d0.c<c.a.k<T>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final BlockingQueue<c.a.k<T>> f2088b = new ArrayBlockingQueue(1);

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final AtomicInteger f2089c = new AtomicInteger();

        b() {
        }

        @Override // c.a.s
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onNext(c.a.k<T> kVar) {
            if (this.f2089c.getAndSet(0) == 1 || !kVar.e()) {
                while (!this.f2088b.offer(kVar)) {
                    c.a.k<T> kVarPoll = this.f2088b.poll();
                    if (kVarPoll != null && !kVarPoll.e()) {
                        kVar = kVarPoll;
                    }
                }
            }
        }

        void b() {
            this.f2089c.set(1);
        }

        public c.a.k<T> c() {
            b();
            c.a.b0.j.e.a();
            return this.f2088b.take();
        }

        @Override // c.a.s
        public void onComplete() {
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            c.a.e0.a.b(th);
        }
    }

    public e(c.a.q<T> qVar) {
        this.f2081a = qVar;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return new a(this.f2081a, new b());
    }
}
