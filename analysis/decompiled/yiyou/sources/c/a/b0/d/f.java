package c.a.b0.d;

/* JADX INFO: compiled from: BlockingLastObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class f<T> extends d<T> {
    @Override // c.a.s
    public void onError(Throwable th) {
        this.f1800a = null;
        this.f1801b = th;
        countDown();
    }

    @Override // c.a.s
    public void onNext(T t) {
        this.f1800a = t;
    }
}
