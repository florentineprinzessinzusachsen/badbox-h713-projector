package v;

import java.util.AbstractSet;
import java.util.Map;
import java.util.Set;
import u0.l;
import v1.p;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2416a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f2417b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Set f2418c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Set f2419d;

    public k(String str, Map map, AbstractSet abstractSet, AbstractSet abstractSet2) {
        j2.i.e(abstractSet, "foreignKeys");
        this.f2416a = str;
        this.f2417b = map;
        this.f2418c = abstractSet;
        this.f2419d = abstractSet2;
    }

    public final boolean equals(Object obj) {
        Set set;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        if (!this.f2416a.equals(kVar.f2416a) || !this.f2417b.equals(kVar.f2417b) || !j2.i.a(this.f2418c, kVar.f2418c)) {
            return false;
        }
        Set set2 = this.f2419d;
        if (set2 == null || (set = kVar.f2419d) == null) {
            return true;
        }
        return set2.equals(set);
    }

    public final int hashCode() {
        return this.f2418c.hashCode() + ((this.f2417b.hashCode() + (this.f2416a.hashCode() * 31)) * 31);
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Object, java.util.Map] */
    public final String toString() {
        StringBuilder sb = new StringBuilder("\n            |TableInfo {\n            |    name = '");
        sb.append(this.f2416a);
        sb.append("',\n            |    columns = {");
        sb.append(l3.h.z(v1.j.D0(this.f2417b.values(), new l(3))));
        sb.append("\n            |    foreignKeys = {");
        sb.append(l3.h.z(this.f2418c));
        sb.append("\n            |    indices = {");
        Set set = this.f2419d;
        sb.append(l3.h.z(set != null ? v1.j.D0(set, new l(4)) : p.f2517d));
        sb.append("\n            |}\n        ");
        return p2.j.t0(sb.toString());
    }
}
