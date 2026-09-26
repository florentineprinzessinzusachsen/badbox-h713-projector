package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObserverResourceWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public final class m4<T> extends AtomicReference<c.a.y.b> implements c.a.s<T>, c.a.y.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.s<? super T> f2459a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final AtomicReference<c.a.y.b> f2460b = new AtomicReference<>();

    public m4(c.a.s<? super T> sVar) {
        this.f2459a = sVar;
    }

    public void a(c.a.y.b bVar) {
        c.a.b0.a.c.b(this, bVar);
    }

    @Override // c.a.y.b
    public void dispose() {
        c.a.b0.a.c.a(this.f2460b);
        c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
    }

    @Override // c.a.s
    public void onComplete() {
        dispose();
        this.f2459a.onComplete();
    }

    @Override // c.a.s
    public void onError(Throwable th) {
        dispose();
        this.f2459a.onError(th);
    }

    @Override // c.a.s
    public void onNext(T t) {
        this.f2459a.onNext(t);
    }

    @Override // c.a.s
    public void onSubscribe(c.a.y.b bVar) {
        if (c.a.b0.a.c.c(this.f2460b, bVar)) {
            this.f2459a.onSubscribe(this);
        }
    }
}
