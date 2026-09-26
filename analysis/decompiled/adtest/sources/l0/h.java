package l0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1313a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1314b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1315c;

    public h(String str, int i4, int i5) {
        j2.i.e(str, "workSpecId");
        this.f1313a = str;
        this.f1314b = i4;
        this.f1315c = i5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return j2.i.a(this.f1313a, hVar.f1313a) && this.f1314b == hVar.f1314b && this.f1315c == hVar.f1315c;
    }

    public final int hashCode() {
        return (((this.f1313a.hashCode() * 31) + this.f1314b) * 31) + this.f1315c;
    }

    public final String toString() {
        return "SystemIdInfo(workSpecId=" + this.f1313a + ", generation=" + this.f1314b + ", systemId=" + this.f1315c + ')';
    }
}
