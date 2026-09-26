package h0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f1009a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f1010b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f1011c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f1012d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f1013e;

    public h(boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        this.f1009a = z3;
        this.f1010b = z4;
        this.f1011c = z5;
        this.f1012d = z6;
        this.f1013e = z7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return this.f1009a == hVar.f1009a && this.f1010b == hVar.f1010b && this.f1011c == hVar.f1011c && this.f1012d == hVar.f1012d && this.f1013e == hVar.f1013e;
    }

    public final int hashCode() {
        return ((((((((this.f1009a ? 1231 : 1237) * 31) + (this.f1010b ? 1231 : 1237)) * 31) + (this.f1011c ? 1231 : 1237)) * 31) + (this.f1012d ? 1231 : 1237)) * 31) + (this.f1013e ? 1231 : 1237);
    }

    public final String toString() {
        return "NetworkState(isConnected=" + this.f1009a + ", isValidated=" + this.f1010b + ", isMetered=" + this.f1011c + ", isNotRoaming=" + this.f1012d + ", isBlocked=" + this.f1013e + ')';
    }
}
