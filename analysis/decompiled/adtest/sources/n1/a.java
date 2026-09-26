package n1;

import j2.i;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f1485a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f1486b;

    public a(ArrayList arrayList, String str) {
        i.e(str, "dnsServer");
        this.f1485a = arrayList;
        this.f1486b = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f1485a.equals(aVar.f1485a) && i.a(this.f1486b, aVar.f1486b);
    }

    public final int hashCode() {
        return this.f1486b.hashCode() + (this.f1485a.hashCode() * 31);
    }

    public final String toString() {
        return "DNSQueryResult(ips=" + this.f1485a + ", dnsServer=" + this.f1486b + ")";
    }
}
