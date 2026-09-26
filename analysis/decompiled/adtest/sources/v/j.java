package v;

import java.util.ArrayList;
import java.util.List;
import p2.p;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2412a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f2413b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f2414c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f2415d;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.util.Collection, java.util.List] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.util.ArrayList] */
    public j(String str, boolean z3, List list, List list2) {
        j2.i.e(str, "name");
        this.f2412a = str;
        this.f2413b = z3;
        this.f2414c = list;
        this.f2415d = list2;
        if (list2.isEmpty()) {
            int size = list.size();
            list2 = new ArrayList(size);
            for (int i4 = 0; i4 < size; i4++) {
                list2.add("ASC");
            }
        }
        this.f2415d = list2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof j) {
            j jVar = (j) obj;
            String str = jVar.f2412a;
            if (this.f2413b == jVar.f2413b && this.f2414c.equals(jVar.f2414c) && j2.i.a(this.f2415d, jVar.f2415d)) {
                String str2 = this.f2412a;
                return p.z0(str2, "index_", false) ? p.z0(str, "index_", false) : str2.equals(str);
            }
        }
        return false;
    }

    public final int hashCode() {
        String str = this.f2412a;
        return this.f2415d.hashCode() + ((this.f2414c.hashCode() + ((((p.z0(str, "index_", false) ? -1184239155 : str.hashCode()) * 31) + (this.f2413b ? 1 : 0)) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("\n            |Index {\n            |   name = '");
        sb.append(this.f2412a);
        sb.append("',\n            |   unique = '");
        sb.append(this.f2413b);
        sb.append("',\n            |   columns = {");
        p2.j.r0(v1.j.y0(this.f2414c, ",", null, null, null, 62));
        p2.j.r0("},");
        u1.k kVar = u1.k.f2301a;
        sb.append(kVar);
        sb.append("\n            |   orders = {");
        p2.j.r0(v1.j.y0(this.f2415d, ",", null, null, null, 62));
        p2.j.r0(" }");
        sb.append(kVar);
        sb.append("\n            |}\n        ");
        return p2.j.r0(p2.j.t0(sb.toString()));
    }
}
