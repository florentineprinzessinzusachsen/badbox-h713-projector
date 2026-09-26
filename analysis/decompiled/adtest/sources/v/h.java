package v;

import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2400a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f2401b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f2402c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2403d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f2404e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f2405f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f2406g;

    public h(String str, String str2, boolean z3, int i4, String str3, int i5) {
        j2.i.e(str, "name");
        j2.i.e(str2, "type");
        this.f2400a = str;
        this.f2401b = str2;
        this.f2402c = z3;
        this.f2403d = i4;
        this.f2404e = str3;
        this.f2405f = i5;
        String upperCase = str2.toUpperCase(Locale.ROOT);
        j2.i.d(upperCase, "toUpperCase(...)");
        this.f2406g = p2.i.B0(upperCase, "INT") ? 3 : (p2.i.B0(upperCase, "CHAR") || p2.i.B0(upperCase, "CLOB") || p2.i.B0(upperCase, "TEXT")) ? 2 : p2.i.B0(upperCase, "BLOB") ? 5 : (p2.i.B0(upperCase, "REAL") || p2.i.B0(upperCase, "FLOA") || p2.i.B0(upperCase, "DOUB")) ? 4 : 1;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof h) {
                boolean z3 = this.f2403d > 0;
                h hVar = (h) obj;
                int i4 = hVar.f2405f;
                if (z3 == (hVar.f2403d > 0) && j2.i.a(this.f2400a, hVar.f2400a) && this.f2402c == hVar.f2402c) {
                    String str = hVar.f2404e;
                    int i5 = this.f2405f;
                    String str2 = this.f2404e;
                    if ((i5 != 1 || i4 != 2 || str2 == null || l3.h.v(str2, str)) && ((i5 != 2 || i4 != 1 || str == null || l3.h.v(str, str2)) && ((i5 == 0 || i5 != i4 || (str2 == null ? str == null : l3.h.v(str2, str))) && this.f2406g == hVar.f2406g))) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return (((((this.f2400a.hashCode() * 31) + this.f2406g) * 31) + (this.f2402c ? 1231 : 1237)) * 31) + this.f2403d;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("\n            |Column {\n            |   name = '");
        sb.append(this.f2400a);
        sb.append("',\n            |   type = '");
        sb.append(this.f2401b);
        sb.append("',\n            |   affinity = '");
        sb.append(this.f2406g);
        sb.append("',\n            |   notNull = '");
        sb.append(this.f2402c);
        sb.append("',\n            |   primaryKeyPosition = '");
        sb.append(this.f2403d);
        sb.append("',\n            |   defaultValue = '");
        String str = this.f2404e;
        if (str == null) {
            str = "undefined";
        }
        sb.append(str);
        sb.append("'\n            |}\n        ");
        return p2.j.r0(p2.j.t0(sb.toString()));
    }
}
