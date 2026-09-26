package g1;

import j2.i;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f957a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f958b;

    public b(int i4, String str) {
        this.f957a = i4;
        this.f958b = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f957a == bVar.f957a && i.a(this.f958b, bVar.f958b);
    }

    public final int hashCode() {
        int i4 = this.f957a * 31;
        String str = this.f958b;
        return i4 + (str == null ? 0 : str.hashCode());
    }

    public final String toString() {
        return "InstallResult(status=" + this.f957a + ", message=" + this.f958b + ")";
    }
}
