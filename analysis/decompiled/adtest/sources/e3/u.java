package e3;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v f822a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final v f823b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Throwable f824c;

    public u(v vVar, d dVar, Throwable th) {
        this.f822a = vVar;
        this.f823b = dVar;
        this.f824c = th;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof u)) {
            return false;
        }
        u uVar = (u) obj;
        return j2.i.a(this.f822a, uVar.f822a) && j2.i.a(this.f823b, uVar.f823b) && j2.i.a(this.f824c, uVar.f824c);
    }

    public final int hashCode() {
        int iHashCode = this.f822a.hashCode() * 31;
        v vVar = this.f823b;
        int iHashCode2 = (iHashCode + (vVar == null ? 0 : vVar.hashCode())) * 31;
        Throwable th = this.f824c;
        return iHashCode2 + (th != null ? th.hashCode() : 0);
    }

    public final String toString() {
        return "ConnectResult(plan=" + this.f822a + ", nextPlan=" + this.f823b + ", throwable=" + this.f824c + ')';
    }

    public /* synthetic */ u(v vVar, Throwable th, int i4) {
        this(vVar, (d) null, (i4 & 4) != 0 ? null : th);
    }
}
