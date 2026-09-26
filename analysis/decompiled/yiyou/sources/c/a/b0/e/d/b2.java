package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableNever.java */
/* JADX INFO: loaded from: classes.dex */
public final class b2 extends c.a.l<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c.a.l<Object> f1984a = new b2();

    private b2() {
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super Object> sVar) {
        sVar.onSubscribe(c.a.b0.a.d.NEVER);
    }
}
