package r2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class m extends y0 implements l {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final d1 f1999h;

    public m(d1 d1Var) {
        this.f1999h = d1Var;
    }

    @Override // r2.l
    public final boolean b(Throwable th) {
        return j().v(th);
    }

    @Override // r2.y0
    public final boolean k() {
        return true;
    }

    @Override // r2.y0
    public final void l(Throwable th) {
        this.f1999h.r(j());
    }
}
