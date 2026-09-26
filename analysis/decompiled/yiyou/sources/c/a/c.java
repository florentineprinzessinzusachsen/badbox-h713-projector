package c.a;

/* JADX INFO: compiled from: CompletableObserver.java */
/* JADX INFO: loaded from: classes.dex */
public interface c {
    void onComplete();

    void onError(Throwable th);

    void onSubscribe(c.a.y.b bVar);
}
