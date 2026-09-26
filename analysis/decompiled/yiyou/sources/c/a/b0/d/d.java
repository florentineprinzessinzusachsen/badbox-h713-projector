package c.a.b0.d;

import java.util.concurrent.CountDownLatch;

/* JADX INFO: compiled from: BlockingBaseObserver.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class d<T> extends CountDownLatch implements c.a.s<T>, c.a.y.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    T f1800a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Throwable f1801b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    c.a.y.b f1802c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    volatile boolean f1803d;

    public d() {
        super(1);
    }

    public final T a() {
        if (getCount() != 0) {
            try {
                c.a.b0.j.e.a();
                await();
            } catch (InterruptedException e2) {
                dispose();
                throw c.a.b0.j.j.a(e2);
            }
        }
        Throwable th = this.f1801b;
        if (th == null) {
            return this.f1800a;
        }
        throw c.a.b0.j.j.a(th);
    }

    @Override // c.a.y.b
    public final void dispose() {
        this.f1803d = true;
        c.a.y.b bVar = this.f1802c;
        if (bVar != null) {
            bVar.dispose();
        }
    }

    @Override // c.a.s
    public final void onComplete() {
        countDown();
    }

    @Override // c.a.s
    public final void onSubscribe(c.a.y.b bVar) {
        this.f1802c = bVar;
        if (this.f1803d) {
            bVar.dispose();
        }
    }
}
