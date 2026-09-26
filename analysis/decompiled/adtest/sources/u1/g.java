package u1;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class g implements Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Throwable f2296d;

    public g(Throwable th) {
        j2.i.e(th, "exception");
        this.f2296d = th;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof g) {
            return j2.i.a(this.f2296d, ((g) obj).f2296d);
        }
        return false;
    }

    public final int hashCode() {
        return this.f2296d.hashCode();
    }

    public final String toString() {
        return "Failure(" + this.f2296d + ')';
    }
}
