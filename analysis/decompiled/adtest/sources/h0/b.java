package h0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f997a;

    public b(int i4) {
        this.f997a = i4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof b) && this.f997a == ((b) obj).f997a;
    }

    public final int hashCode() {
        return this.f997a;
    }

    public final String toString() {
        return "ConstraintsNotMet(reason=" + this.f997a + ')';
    }
}
