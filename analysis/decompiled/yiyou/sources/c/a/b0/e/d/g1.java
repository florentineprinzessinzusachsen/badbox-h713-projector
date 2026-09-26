package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableFromUnsafeSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class g1<T> extends c.a.l<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.q<T> f2176a;

    public g1(c.a.q<T> qVar) {
        this.f2176a = qVar;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        this.f2176a.subscribe(sVar);
    }
}
