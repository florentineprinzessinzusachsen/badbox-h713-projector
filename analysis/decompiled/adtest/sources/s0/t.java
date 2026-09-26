package s0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class t extends q {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u0.p f2135d = new u0.p(false);

    public final boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof t) && ((t) obj).f2135d.equals(this.f2135d);
        }
        return true;
    }

    public final int hashCode() {
        return this.f2135d.hashCode();
    }
}
