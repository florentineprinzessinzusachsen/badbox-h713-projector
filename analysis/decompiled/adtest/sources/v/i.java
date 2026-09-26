package v;

import java.util.List;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2407a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f2408b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f2409c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f2410d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f2411e;

    public i(String str, String str2, String str3, List list, List list2) {
        j2.i.e(str, "referenceTable");
        j2.i.e(str2, "onDelete");
        j2.i.e(str3, "onUpdate");
        this.f2407a = str;
        this.f2408b = str2;
        this.f2409c = str3;
        this.f2410d = list;
        this.f2411e = list2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        if (j2.i.a(this.f2407a, iVar.f2407a) && j2.i.a(this.f2408b, iVar.f2408b) && j2.i.a(this.f2409c, iVar.f2409c) && j2.i.a(this.f2410d, iVar.f2410d)) {
            return j2.i.a(this.f2411e, iVar.f2411e);
        }
        return false;
    }

    public final int hashCode() {
        return this.f2411e.hashCode() + ((this.f2410d.hashCode() + ((this.f2409c.hashCode() + ((this.f2408b.hashCode() + (this.f2407a.hashCode() * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("\n            |ForeignKey {\n            |   referenceTable = '");
        sb.append(this.f2407a);
        sb.append("',\n            |   onDelete = '");
        sb.append(this.f2408b);
        sb.append("',\n            |   onUpdate = '");
        sb.append(this.f2409c);
        sb.append("',\n            |   columnNames = {");
        p2.j.r0(v1.j.y0(v1.j.C0(this.f2410d), ",", null, null, null, 62));
        p2.j.r0("},");
        u1.k kVar = u1.k.f2301a;
        sb.append(kVar);
        sb.append("\n            |   referenceColumnNames = {");
        p2.j.r0(v1.j.y0(v1.j.C0(this.f2411e), ",", null, null, null, 62));
        p2.j.r0(" }");
        sb.append(kVar);
        sb.append("\n            |}\n        ");
        return p2.j.r0(p2.j.t0(sb.toString()));
    }
}
