package c.a.b0.d;

import c.a.v;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: compiled from: BlockingMultiObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class g<T> extends CountDownLatch implements v<T>, c.a.c, c.a.i<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    T f1804a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Throwable f1805b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    c.a.y.b f1806c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    volatile boolean f1807d;

    public g() {
        super(1);
    }

    @Override // c.a.v, c.a.i
    public void a(T t) {
        this.f1804a = t;
        countDown();
    }

    void b() {
        this.f1807d = true;
        c.a.y.b bVar = this.f1806c;
        if (bVar != null) {
            bVar.dispose();
        }
    }

    @Override // c.a.c, c.a.i
    public void onComplete() {
        countDown();
    }

    @Override // c.a.v, c.a.c, c.a.i
    public void onError(Throwable th) {
        this.f1805b = th;
        countDown();
    }

    @Override // c.a.v, c.a.c, c.a.i
    public void onSubscribe(c.a.y.b bVar) {
        this.f1806c = bVar;
        if (this.f1807d) {
            bVar.dispose();
        }
    }

    public T a() {
        if (getCount() != 0) {
            try {
                c.a.b0.j.e.a();
                await();
            } catch (InterruptedException e2) {
                b();
                throw c.a.b0.j.j.a(e2);
            }
        }
        Throwable th = this.f1805b;
        if (th == null) {
            return this.f1804a;
        }
        throw c.a.b0.j.j.a(th);
    }
}
