package d0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class v extends y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j f512a = j.f464b;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || v.class != obj.getClass()) {
            return false;
        }
        return this.f512a.equals(((v) obj).f512a);
    }

    public final int hashCode() {
        return this.f512a.hashCode() + (v.class.getName().hashCode() * 31);
    }

    public final String toString() {
        return "Failure {mOutputData=" + this.f512a + '}';
    }
}
