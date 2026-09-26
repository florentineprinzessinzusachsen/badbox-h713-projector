package r2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a1 extends y0 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final d1 f1949h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final b1 f1950i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final m f1951j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Object f1952k;

    public a1(d1 d1Var, b1 b1Var, m mVar, Object obj) {
        this.f1949h = d1Var;
        this.f1950i = b1Var;
        this.f1951j = mVar;
        this.f1952k = obj;
    }

    @Override // r2.y0
    public final boolean k() {
        return false;
    }

    @Override // r2.y0
    public final void l(Throwable th) {
        m mVar = this.f1951j;
        m mVarP = d1.P(mVar);
        d1 d1Var = this.f1949h;
        b1 b1Var = this.f1950i;
        Object obj = this.f1952k;
        if (mVarP == null || !d1Var.Y(b1Var, mVarP, obj)) {
            b1Var.f1958d.e(new w2.h(2), 2);
            m mVarP2 = d1.P(mVar);
            if (mVarP2 == null || !d1Var.Y(b1Var, mVarP2, obj)) {
                d1Var.p(d1Var.y(b1Var, obj));
            }
        }
    }
}
