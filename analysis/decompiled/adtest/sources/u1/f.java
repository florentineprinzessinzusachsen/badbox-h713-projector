package u1;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f implements Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f2294d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f2295e;

    public f(Object obj, Object obj2) {
        this.f2294d = obj;
        this.f2295e = obj2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return j2.i.a(this.f2294d, fVar.f2294d) && j2.i.a(this.f2295e, fVar.f2295e);
    }

    public final int hashCode() {
        Object obj = this.f2294d;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.f2295e;
        return iHashCode + (obj2 != null ? obj2.hashCode() : 0);
    }

    public final String toString() {
        return "(" + this.f2294d + ", " + this.f2295e + ')';
    }
}
