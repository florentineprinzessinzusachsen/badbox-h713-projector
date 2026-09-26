package c.a.b0.d;

import java.util.NoSuchElementException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: FutureObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class l<T> extends CountDownLatch implements c.a.s<T>, Future<T>, c.a.y.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    T f1820a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Throwable f1821b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final AtomicReference<c.a.y.b> f1822c;

    public l() {
        super(1);
        this.f1822c = new AtomicReference<>();
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        c.a.y.b bVar;
        c.a.b0.a.c cVar;
        do {
            bVar = this.f1822c.get();
            if (bVar == this || bVar == (cVar = c.a.b0.a.c.DISPOSED)) {
                return false;
            }
        } while (!this.f1822c.compareAndSet(bVar, cVar));
        if (bVar != null) {
            bVar.dispose();
        }
        countDown();
        return true;
    }

    @Override // c.a.y.b
    public void dispose() {
    }

    @Override // java.util.concurrent.Future
    public T get() throws ExecutionException, InterruptedException {
        if (getCount() != 0) {
            c.a.b0.j.e.a();
            await();
        }
        if (isCancelled()) {
            throw new CancellationException();
        }
        Throwable th = this.f1821b;
        if (th == null) {
            return this.f1820a;
        }
        throw new ExecutionException(th);
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return c.a.b0.a.c.a(this.f1822c.get());
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return getCount() == 0;
    }

    @Override // c.a.s
    public void onComplete() {
        c.a.y.b bVar;
        if (this.f1820a == null) {
            onError(new NoSuchElementException("The source is empty"));
            return;
        }
        do {
            bVar = this.f1822c.get();
            if (bVar == this || bVar == c.a.b0.a.c.DISPOSED) {
                return;
            }
        } while (!this.f1822c.compareAndSet(bVar, this));
        countDown();
    }

    @Override // c.a.s
    public void onError(Throwable th) {
        c.a.y.b bVar;
        if (this.f1821b != null) {
            c.a.e0.a.b(th);
            return;
        }
        this.f1821b = th;
        do {
            bVar = this.f1822c.get();
            if (bVar == this || bVar == c.a.b0.a.c.DISPOSED) {
                c.a.e0.a.b(th);
                return;
            }
        } while (!this.f1822c.compareAndSet(bVar, this));
        countDown();
    }

    @Override // c.a.s
    public void onNext(T t) {
        if (this.f1820a == null) {
            this.f1820a = t;
        } else {
            this.f1822c.get().dispose();
            onError(new IndexOutOfBoundsException("More than one element received"));
        }
    }

    @Override // c.a.s
    public void onSubscribe(c.a.y.b bVar) {
        c.a.b0.a.c.c(this.f1822c, bVar);
    }

    @Override // java.util.concurrent.Future
    public T get(long j, TimeUnit timeUnit) throws ExecutionException, TimeoutException {
        if (getCount() != 0) {
            c.a.b0.j.e.a();
            if (!await(j, timeUnit)) {
                throw new TimeoutException();
            }
        }
        if (!isCancelled()) {
            Throwable th = this.f1821b;
            if (th == null) {
                return this.f1820a;
            }
            throw new ExecutionException(th);
        }
        throw new CancellationException();
    }
}
