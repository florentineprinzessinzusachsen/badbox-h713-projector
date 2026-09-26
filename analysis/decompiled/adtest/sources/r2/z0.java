package r2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class z0 extends i {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final o f2054l;

    public z0(y1.c cVar, o oVar) {
        super(1, cVar);
        this.f2054l = oVar;
    }

    @Override // r2.i
    public final String B() {
        return "AwaitContinuation";
    }

    @Override // r2.i
    public final Throwable t(d1 d1Var) {
        Throwable thB;
        o oVar = this.f2054l;
        oVar.getClass();
        Object obj = d1.f1971d.get(oVar);
        if (!(obj instanceof b1) || (thB = ((b1) obj).b()) == null) {
            return obj instanceof q ? ((q) obj).f2018a : d1Var.z();
        }
        return thB;
    }
}
