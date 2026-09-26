package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableSerialized.java */
/* JADX INFO: loaded from: classes.dex */
public final class b3<T> extends a<T, T> {
    public b3(c.a.l<T> lVar) {
        super(lVar);
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new c.a.d0.f(sVar));
    }
}
