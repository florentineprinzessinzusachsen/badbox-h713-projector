package r2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class h0 extends y0 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f1982h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f1983i;

    public /* synthetic */ h0(int i4, Object obj) {
        this.f1982h = i4;
        this.f1983i = obj;
    }

    @Override // r2.y0
    public final boolean k() {
        switch (this.f1982h) {
        }
        return false;
    }

    @Override // r2.y0
    public final void l(Throwable th) {
        switch (this.f1982h) {
            case 0:
                ((g0) this.f1983i).a();
                break;
            case 1:
                ((i2.l) this.f1983i).h(th);
                break;
            default:
                z0 z0Var = (z0) this.f1983i;
                Object obj = d1.f1971d.get(j());
                if (!(obj instanceof q)) {
                    z0Var.j(x.u(obj));
                } else {
                    z0Var.j(d0.l0.l(((q) obj).f2018a));
                }
                break;
        }
    }
}
