package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableJust.java */
/* JADX INFO: loaded from: classes.dex */
public final class r1<T> extends c.a.l<T> implements c.a.b0.c.h<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final T f2660a;

    public r1(T t) {
        this.f2660a = t;
    }

    @Override // c.a.b0.c.h, java.util.concurrent.Callable
    public T call() {
        return this.f2660a;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        w2.a aVar = new w2.a(sVar, this.f2660a);
        sVar.onSubscribe(aVar);
        aVar.run();
    }
}
