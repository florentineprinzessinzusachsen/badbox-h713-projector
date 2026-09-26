package c.a.b0.d;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ForEachWhileObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class k<T> extends AtomicReference<c.a.y.b> implements c.a.s<T>, c.a.y.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.a0.p<? super T> f1816a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.f<? super Throwable> f1817b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.a f1818c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f1819d;

    public k(c.a.a0.p<? super T> pVar, c.a.a0.f<? super Throwable> fVar, c.a.a0.a aVar) {
        this.f1816a = pVar;
        this.f1817b = fVar;
        this.f1818c = aVar;
    }

    @Override // c.a.y.b
    public void dispose() {
        c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
    }

    @Override // c.a.s
    public void onComplete() {
        if (this.f1819d) {
            return;
        }
        this.f1819d = true;
        try {
            this.f1818c.run();
        } catch (Throwable th) {
            c.a.z.b.b(th);
            c.a.e0.a.b(th);
        }
    }

    @Override // c.a.s
    public void onError(Throwable th) {
        if (this.f1819d) {
            c.a.e0.a.b(th);
            return;
        }
        this.f1819d = true;
        try {
            this.f1817b.a(th);
        } catch (Throwable th2) {
            c.a.z.b.b(th2);
            c.a.e0.a.b(new c.a.z.a(th, th2));
        }
    }

    @Override // c.a.s
    public void onNext(T t) {
        if (this.f1819d) {
            return;
        }
        try {
            if (this.f1816a.a(t)) {
                return;
            }
            dispose();
            onComplete();
        } catch (Throwable th) {
            c.a.z.b.b(th);
            dispose();
            onError(th);
        }
    }

    @Override // c.a.s
    public void onSubscribe(c.a.y.b bVar) {
        c.a.b0.a.c.c(this, bVar);
    }
}
