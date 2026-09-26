package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableDoOnLifecycle.java */
/* JADX INFO: loaded from: classes.dex */
public final class o0<T> extends a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.a.a0.f<? super c.a.y.b> f2514b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.a.a0.a f2515c;

    public o0(c.a.l<T> lVar, c.a.a0.f<? super c.a.y.b> fVar, c.a.a0.a aVar) {
        super(lVar);
        this.f2514b = fVar;
        this.f2515c = aVar;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new c.a.b0.d.j(sVar, this.f2514b, this.f2515c));
    }
}
