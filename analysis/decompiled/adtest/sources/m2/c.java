package m2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c extends a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final c f1450g = new c(1, 0, 1);

    @Override // m2.a
    public final boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        if (isEmpty() && ((c) obj).isEmpty()) {
            return true;
        }
        c cVar = (c) obj;
        return this.f1443d == cVar.f1443d && this.f1444e == cVar.f1444e;
    }

    @Override // m2.a
    public final int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (this.f1443d * 31) + this.f1444e;
    }

    @Override // m2.a
    public final boolean isEmpty() {
        return this.f1443d > this.f1444e;
    }

    @Override // m2.a
    public final String toString() {
        return this.f1443d + ".." + this.f1444e;
    }
}
