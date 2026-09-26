package i3;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1211a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f1212b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f1213c;

    public a(int i4) {
        this.f1211a = i4;
    }

    public static void b(a aVar, long j4, long j5, int i4) {
        if ((i4 & 1) != 0) {
            j4 = 0;
        }
        if ((i4 & 2) != 0) {
            j5 = 0;
        }
        synchronized (aVar) {
            try {
                if (j4 < 0) {
                    throw new IllegalStateException("Check failed.");
                }
                if (j5 < 0) {
                    throw new IllegalStateException("Check failed.");
                }
                long j6 = aVar.f1212b + j4;
                aVar.f1212b = j6;
                long j7 = aVar.f1213c + j5;
                aVar.f1213c = j7;
                if (j7 > j6) {
                    throw new IllegalStateException("Check failed.");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final synchronized long a() {
        return this.f1212b - this.f1213c;
    }

    public final String toString() {
        return "WindowCounter(streamId=" + this.f1211a + ", total=" + this.f1212b + ", acknowledged=" + this.f1213c + ", unacknowledged=" + a() + ')';
    }
}
