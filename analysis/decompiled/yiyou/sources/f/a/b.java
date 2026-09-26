package f.a;

/* JADX INFO: compiled from: Subscriber.java */
/* JADX INFO: loaded from: classes.dex */
public interface b<T> {
    void a(c cVar);

    void onComplete();

    void onError(Throwable th);

    void onNext(T t);
}
