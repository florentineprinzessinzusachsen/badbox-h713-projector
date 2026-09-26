package r2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class k0 extends l0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final n1 f1995f;

    public k0(long j4, n1 n1Var) {
        super(j4);
        this.f1995f = n1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f1995f.run();
    }

    @Override // r2.l0
    public final String toString() {
        return super.toString() + this.f1995f;
    }
}
