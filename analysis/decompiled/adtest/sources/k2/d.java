package k2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f1291d;

    static {
        Integer num = e2.a.f704a;
        f1291d = (num == null || num.intValue() >= 34) ? new l2.a() : new b();
    }

    public abstract int a(int i4);

    public abstract int b();

    public abstract long c();

    public long d(long j4, long j5) {
        long jC;
        long j6;
        long jA;
        int iB;
        if (j5 <= j4) {
            throw new IllegalArgumentException(("Random range is empty: [" + Long.valueOf(j4) + ", " + Long.valueOf(j5) + ").").toString());
        }
        long j7 = j5 - j4;
        if (j7 > 0) {
            if (((-j7) & j7) == j7) {
                int i4 = (int) j7;
                int i5 = (int) (j7 >>> 32);
                if (i4 != 0) {
                    iB = a(31 - Integer.numberOfLeadingZeros(i4));
                } else if (i5 == 1) {
                    iB = b();
                } else {
                    jA = (((long) a(31 - Integer.numberOfLeadingZeros(i5))) << 32) + (4294967295L & ((long) b()));
                }
                jA = ((long) iB) & 4294967295L;
            } else {
                do {
                    jC = c() >>> 1;
                    j6 = jC % j7;
                } while ((j7 - 1) + (jC - j6) < 0);
                jA = j6;
            }
            return j4 + jA;
        }
        while (true) {
            long jC2 = c();
            if (j4 <= jC2 && jC2 < j5) {
                return jC2;
            }
        }
    }
}
