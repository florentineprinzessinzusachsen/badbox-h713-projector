package j2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class k implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f1273a;

    public k(Class cls) {
        this.f1273a = cls;
    }

    @Override // j2.d
    public final Class a() {
        return this.f1273a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof k) {
            return i.a(this.f1273a, ((k) obj).f1273a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f1273a.hashCode();
    }

    public final String toString() {
        return this.f1273a.toString() + " (Kotlin reflection is not available)";
    }
}
