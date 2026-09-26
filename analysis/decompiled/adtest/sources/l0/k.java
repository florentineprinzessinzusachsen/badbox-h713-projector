package l0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1321a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1322b;

    public k(int i4, String str) {
        j2.i.e(str, "workSpecId");
        this.f1321a = str;
        this.f1322b = i4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        return j2.i.a(this.f1321a, kVar.f1321a) && this.f1322b == kVar.f1322b;
    }

    public final int hashCode() {
        return (this.f1321a.hashCode() * 31) + this.f1322b;
    }

    public final String toString() {
        return "WorkGenerationalId(workSpecId=" + this.f1321a + ", generation=" + this.f1322b + ')';
    }
}
