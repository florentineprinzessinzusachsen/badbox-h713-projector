package c.a;

/* JADX INFO: compiled from: MaybeObserver.java */
/* JADX INFO: loaded from: classes.dex */
public interface i<T> {
    void a(T t);

    void onComplete();

    void onError(Throwable th);

    void onSubscribe(c.a.y.b bVar);
}
