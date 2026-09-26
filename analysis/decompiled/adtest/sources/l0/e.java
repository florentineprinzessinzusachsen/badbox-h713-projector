package l0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1309a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Long f1310b;

    public e(String str, Long l4) {
        this.f1309a = str;
        this.f1310b = l4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return j2.i.a(this.f1309a, eVar.f1309a) && j2.i.a(this.f1310b, eVar.f1310b);
    }

    public final int hashCode() {
        int iHashCode = this.f1309a.hashCode() * 31;
        Long l4 = this.f1310b;
        return iHashCode + (l4 == null ? 0 : l4.hashCode());
    }

    public final String toString() {
        return "Preference(key=" + this.f1309a + ", value=" + this.f1310b + ')';
    }
}
