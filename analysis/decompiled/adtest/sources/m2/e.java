package m2;

import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e implements Iterable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f1455d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f1456e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f1457f;

    public e(long j4, long j5) {
        this.f1455d = j4;
        if (j4 < j5) {
            long j6 = j5 % 1;
            long j7 = j4 % 1;
            long j8 = ((j6 < 0 ? j6 + 1 : j6) - (j7 < 0 ? j7 + 1 : j7)) % 1;
            j5 -= j8 < 0 ? j8 + 1 : j8;
        }
        this.f1456e = j5;
        this.f1457f = 1L;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof e)) {
            return false;
        }
        long j4 = this.f1455d;
        long j5 = this.f1456e;
        if (j4 > j5) {
            e eVar = (e) obj;
            if (eVar.f1455d > eVar.f1456e) {
                return true;
            }
        }
        e eVar2 = (e) obj;
        return j4 == eVar2.f1455d && j5 == eVar2.f1456e;
    }

    public final int hashCode() {
        long j4 = this.f1455d;
        long j5 = this.f1456e;
        if (j4 > j5) {
            return -1;
        }
        return (int) ((((long) 31) * (j4 ^ (j4 >>> 32))) + ((j5 >>> 32) ^ j5));
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new d(this.f1455d, this.f1456e, this.f1457f);
    }

    public final String toString() {
        return this.f1455d + ".." + this.f1456e;
    }
}
