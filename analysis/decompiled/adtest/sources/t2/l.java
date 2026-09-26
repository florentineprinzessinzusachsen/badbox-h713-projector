package t2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final k f2221b = new k();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f2222a;

    public final boolean equals(Object obj) {
        if (obj instanceof l) {
            return j2.i.a(this.f2222a, ((l) obj).f2222a);
        }
        return false;
    }

    public final int hashCode() {
        Object obj = this.f2222a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final String toString() {
        Object obj = this.f2222a;
        if (obj instanceof j) {
            return ((j) obj).toString();
        }
        return "Value(" + obj + ')';
    }
}
