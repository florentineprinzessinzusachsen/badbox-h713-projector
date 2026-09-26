package c.a.b0.d;

/* JADX INFO: compiled from: BlockingFirstObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class e<T> extends d<T> {
    @Override // c.a.s
    public void onError(Throwable th) {
        if (this.f1800a == null) {
            this.f1801b = th;
        }
        countDown();
    }

    @Override // c.a.s
    public void onNext(T t) {
        if (this.f1800a == null) {
            this.f1800a = t;
            this.f1802c.dispose();
            countDown();
        }
    }
}
