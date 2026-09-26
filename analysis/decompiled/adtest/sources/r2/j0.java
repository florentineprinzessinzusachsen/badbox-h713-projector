package r2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class j0 extends l0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final i f1991f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ n0 f1992g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j0(n0 n0Var, long j4, i iVar) {
        super(j4);
        this.f1992g = n0Var;
        this.f1991f = iVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f1991f.E(this.f1992g);
    }

    @Override // r2.l0
    public final String toString() {
        return super.toString() + this.f1991f;
    }
}
