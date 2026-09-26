package n1;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f1487a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f1488b = System.currentTimeMillis();

    public b(ArrayList arrayList) {
        this.f1487a = arrayList;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof b) && this.f1487a.equals(((b) obj).f1487a);
    }

    public final int hashCode() {
        return this.f1487a.hashCode();
    }

    public final String toString() {
        return "DNSRecord(ips=" + this.f1487a + ")";
    }
}
