package q2;

import d0.l0;
import j2.i;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements Comparable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a1.a f1800d = new a1.a(17);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f1801e = l0.p(4611686018427387903L);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final long f1802f = l0.p(-4611686018427387903L);

    public static final long a(long j4, long j5) {
        long j6 = 1000000;
        long j7 = j5 / j6;
        long jA = l0.a(j4, j7);
        if (-4611686018426L > jA || jA >= 4611686018427L) {
            return l0.p(jA);
        }
        long j8 = ((jA * j6) + (j5 - (j7 * j6))) << 1;
        int i4 = b.f1803a;
        return j8;
    }

    public static final long b(long j4, c cVar) {
        i.e(cVar, "unit");
        if (j4 == f1801e) {
            return Long.MAX_VALUE;
        }
        if (j4 == f1802f) {
            return Long.MIN_VALUE;
        }
        long j5 = j4 >> 1;
        c cVar2 = (((int) j4) & 1) == 0 ? c.NANOSECONDS : c.MILLISECONDS;
        i.e(cVar2, "sourceUnit");
        return cVar.f1808d.convert(j5, cVar2.f1808d);
    }
}
