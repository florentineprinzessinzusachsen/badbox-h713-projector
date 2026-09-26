package c.a.b0.d;

/* JADX INFO: compiled from: DisposableLambdaObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class j<T> implements c.a.s<T>, c.a.y.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.s<? super T> f1812a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.f<? super c.a.y.b> f1813b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.a f1814c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    c.a.y.b f1815d;

    public j(c.a.s<? super T> sVar, c.a.a0.f<? super c.a.y.b> fVar, c.a.a0.a aVar) {
        this.f1812a = sVar;
        this.f1813b = fVar;
        this.f1814c = aVar;
    }

    @Override // c.a.y.b
    public void dispose() {
        try {
            this.f1814c.run();
        } catch (Throwable th) {
            c.a.z.b.b(th);
            c.a.e0.a.b(th);
        }
        this.f1815d.dispose();
    }

    @Override // c.a.s
    public void onComplete() {
        if (this.f1815d != c.a.b0.a.c.DISPOSED) {
            this.f1812a.onComplete();
        }
    }

    @Override // c.a.s
    public void onError(Throwable th) {
        if (this.f1815d != c.a.b0.a.c.DISPOSED) {
            this.f1812a.onError(th);
        } else {
            c.a.e0.a.b(th);
        }
    }

    @Override // c.a.s
    public void onNext(T t) {
        this.f1812a.onNext(t);
    }

    @Override // c.a.s
    public void onSubscribe(c.a.y.b bVar) {
        try {
            this.f1813b.a(bVar);
            if (c.a.b0.a.c.a(this.f1815d, bVar)) {
                this.f1815d = bVar;
                this.f1812a.onSubscribe(this);
            }
        } catch (Throwable th) {
            c.a.z.b.b(th);
            bVar.dispose();
            this.f1815d = c.a.b0.a.c.DISPOSED;
            c.a.b0.a.d.a(th, this.f1812a);
        }
    }
}
