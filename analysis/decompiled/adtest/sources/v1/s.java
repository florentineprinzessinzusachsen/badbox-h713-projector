package v1;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2520a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f2521b;

    public s(int i4, Object obj) {
        this.f2520a = i4;
        this.f2521b = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        return this.f2520a == sVar.f2520a && j2.i.a(this.f2521b, sVar.f2521b);
    }

    public final int hashCode() {
        int i4 = this.f2520a * 31;
        Object obj = this.f2521b;
        return i4 + (obj == null ? 0 : obj.hashCode());
    }

    public final String toString() {
        return "IndexedValue(index=" + this.f2520a + ", value=" + this.f2521b + ')';
    }
}
