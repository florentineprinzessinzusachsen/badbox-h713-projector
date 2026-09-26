package r2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class u extends y1.a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final t f2029f = new t();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f2030e;

    public u() {
        super(f2029f);
        this.f2030e = "Room Invalidation Tracker Refresh";
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof u) && j2.i.a(this.f2030e, ((u) obj).f2030e);
    }

    public final int hashCode() {
        return this.f2030e.hashCode();
    }

    public final String toString() {
        return "CoroutineName(" + this.f2030e + ')';
    }
}
