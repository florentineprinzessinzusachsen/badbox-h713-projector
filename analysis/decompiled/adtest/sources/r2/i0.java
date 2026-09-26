package r2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class i0 implements s0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f1989d;

    public i0(boolean z3) {
        this.f1989d = z3;
    }

    @Override // r2.s0
    public final boolean c() {
        return this.f1989d;
    }

    @Override // r2.s0
    public final f1 d() {
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Empty{");
        sb.append(this.f1989d ? "Active" : "New");
        sb.append('}');
        return sb.toString();
    }
}
