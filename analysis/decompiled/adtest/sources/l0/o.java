package l0;

import d0.k0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1328a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public k0 f1329b;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof o)) {
            return false;
        }
        o oVar = (o) obj;
        return j2.i.a(this.f1328a, oVar.f1328a) && this.f1329b == oVar.f1329b;
    }

    public final int hashCode() {
        return this.f1329b.hashCode() + (this.f1328a.hashCode() * 31);
    }

    public final String toString() {
        return "IdAndState(id=" + this.f1328a + ", state=" + this.f1329b + ')';
    }
}
