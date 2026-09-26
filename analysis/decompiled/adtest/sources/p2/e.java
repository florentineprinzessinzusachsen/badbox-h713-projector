package p2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1757a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m2.c f1758b;

    public e(String str, m2.c cVar) {
        this.f1757a = str;
        this.f1758b = cVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return j2.i.a(this.f1757a, eVar.f1757a) && j2.i.a(this.f1758b, eVar.f1758b);
    }

    public final int hashCode() {
        return this.f1758b.hashCode() + (this.f1757a.hashCode() * 31);
    }

    public final String toString() {
        return "MatchGroup(value=" + this.f1757a + ", range=" + this.f1758b + ')';
    }
}
