package c.a.b0.d;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: LambdaObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class o<T> extends AtomicReference<c.a.y.b> implements c.a.s<T>, c.a.y.b, c.a.d0.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.a0.f<? super T> f1828a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.f<? super Throwable> f1829b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.a f1830c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final c.a.a0.f<? super c.a.y.b> f1831d;

    public o(c.a.a0.f<? super T> fVar, c.a.a0.f<? super Throwable> fVar2, c.a.a0.a aVar, c.a.a0.f<? super c.a.y.b> fVar3) {
        this.f1828a = fVar;
        this.f1829b = fVar2;
        this.f1830c = aVar;
        this.f1831d = fVar3;
    }

    public boolean a() {
        return get() == c.a.b0.a.c.DISPOSED;
    }

    @Override // c.a.y.b
    public void dispose() {
        c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
    }

    @Override // c.a.s
    public void onComplete() {
        if (a()) {
            return;
        }
        lazySet(c.a.b0.a.c.DISPOSED);
        try {
            this.f1830c.run();
        } catch (Throwable th) {
            c.a.z.b.b(th);
            c.a.e0.a.b(th);
        }
    }

    @Override // c.a.s
    public void onError(Throwable th) {
        if (a()) {
            c.a.e0.a.b(th);
            return;
        }
        lazySet(c.a.b0.a.c.DISPOSED);
        try {
            this.f1829b.a(th);
        } catch (Throwable th2) {
            c.a.z.b.b(th2);
            c.a.e0.a.b(new c.a.z.a(th, th2));
        }
    }

    @Override // c.a.s
    public void onNext(T t) {
        if (a()) {
            return;
        }
        try {
            this.f1828a.a(t);
        } catch (Throwable th) {
            c.a.z.b.b(th);
            get().dispose();
            onError(th);
        }
    }

    @Override // c.a.s
    public void onSubscribe(c.a.y.b bVar) {
        if (c.a.b0.a.c.c(this, bVar)) {
            try {
                this.f1831d.a(this);
            } catch (Throwable th) {
                c.a.z.b.b(th);
                bVar.dispose();
                onError(th);
            }
        }
    }
}
