package c.a;

/* JADX INFO: compiled from: Observer.java */
/* JADX INFO: loaded from: classes.dex */
public interface s<T> {
    void onComplete();

    void onError(Throwable th);

    void onNext(T t);

    void onSubscribe(c.a.y.b bVar);
}
