package l0;

import androidx.work.OverwritingInputMerger;
import d0.a0;
import d0.i0;
import d0.k0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final String f1330z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1331a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public k0 f1332b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f1333c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f1334d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d0.j f1335e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final d0.j f1336f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f1337g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f1338h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f1339i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final d0.e f1340j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f1341k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final d0.a f1342l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f1343m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f1344n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final long f1345o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final long f1346p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1347q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final i0 f1348r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f1349s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int f1350t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final long f1351u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f1352v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f1353w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public String f1354x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final Boolean f1355y;

    static {
        String strG = a0.g("WorkSpec");
        j2.i.d(strG, "tagWithPrefix(...)");
        f1330z = strG;
    }

    public p(String str, k0 k0Var, String str2, String str3, d0.j jVar, d0.j jVar2, long j4, long j5, long j6, d0.e eVar, int i4, d0.a aVar, long j7, long j8, long j9, long j10, boolean z3, i0 i0Var, int i5, int i6, long j11, int i7, int i8, String str4, Boolean bool) {
        j2.i.e(str, "id");
        j2.i.e(k0Var, "state");
        j2.i.e(str2, "workerClassName");
        j2.i.e(str3, "inputMergerClassName");
        j2.i.e(jVar, "input");
        j2.i.e(jVar2, "output");
        j2.i.e(eVar, "constraints");
        j2.i.e(aVar, "backoffPolicy");
        j2.i.e(i0Var, "outOfQuotaPolicy");
        this.f1331a = str;
        this.f1332b = k0Var;
        this.f1333c = str2;
        this.f1334d = str3;
        this.f1335e = jVar;
        this.f1336f = jVar2;
        this.f1337g = j4;
        this.f1338h = j5;
        this.f1339i = j6;
        this.f1340j = eVar;
        this.f1341k = i4;
        this.f1342l = aVar;
        this.f1343m = j7;
        this.f1344n = j8;
        this.f1345o = j9;
        this.f1346p = j10;
        this.f1347q = z3;
        this.f1348r = i0Var;
        this.f1349s = i5;
        this.f1350t = i6;
        this.f1351u = j11;
        this.f1352v = i7;
        this.f1353w = i8;
        this.f1354x = str4;
        this.f1355y = bool;
    }

    public static p b(p pVar, String str, k0 k0Var, String str2, d0.j jVar, int i4, long j4, int i5, int i6, long j5, int i7, int i8) {
        String str3 = (i8 & 1) != 0 ? pVar.f1331a : str;
        k0 k0Var2 = (i8 & 2) != 0 ? pVar.f1332b : k0Var;
        String str4 = (i8 & 4) != 0 ? pVar.f1333c : str2;
        String str5 = pVar.f1334d;
        d0.j jVar2 = (i8 & 16) != 0 ? pVar.f1335e : jVar;
        d0.j jVar3 = pVar.f1336f;
        long j6 = pVar.f1337g;
        long j7 = pVar.f1338h;
        long j8 = pVar.f1339i;
        d0.e eVar = pVar.f1340j;
        int i9 = (i8 & 1024) != 0 ? pVar.f1341k : i4;
        d0.a aVar = pVar.f1342l;
        long j9 = pVar.f1343m;
        long j10 = (i8 & 8192) != 0 ? pVar.f1344n : j4;
        long j11 = pVar.f1345o;
        long j12 = pVar.f1346p;
        boolean z3 = pVar.f1347q;
        i0 i0Var = pVar.f1348r;
        int i10 = (i8 & 262144) != 0 ? pVar.f1349s : i5;
        int i11 = (i8 & 524288) != 0 ? pVar.f1350t : i6;
        long j13 = (i8 & 1048576) != 0 ? pVar.f1351u : j5;
        int i12 = (i8 & 2097152) != 0 ? pVar.f1352v : i7;
        int i13 = pVar.f1353w;
        String str6 = pVar.f1354x;
        Boolean bool = pVar.f1355y;
        pVar.getClass();
        j2.i.e(str3, "id");
        j2.i.e(k0Var2, "state");
        j2.i.e(str4, "workerClassName");
        j2.i.e(str5, "inputMergerClassName");
        j2.i.e(jVar2, "input");
        j2.i.e(jVar3, "output");
        j2.i.e(eVar, "constraints");
        j2.i.e(aVar, "backoffPolicy");
        j2.i.e(i0Var, "outOfQuotaPolicy");
        return new p(str3, k0Var2, str4, str5, jVar2, jVar3, j6, j7, j8, eVar, i9, aVar, j9, j10, j11, j12, z3, i0Var, i10, i11, j13, i12, i13, str6, bool);
    }

    public final long a() {
        k0 k0Var = this.f1332b;
        k0 k0Var2 = k0.f467d;
        int i4 = this.f1341k;
        boolean z3 = k0Var == k0Var2 && i4 > 0;
        long j4 = this.f1344n;
        boolean zC = c();
        long j5 = this.f1339i;
        long j6 = this.f1338h;
        d0.a aVar = this.f1342l;
        j2.i.e(aVar, "backoffPolicy");
        long j7 = this.f1351u;
        int i5 = this.f1349s;
        if (j7 != Long.MAX_VALUE && zC) {
            if (i5 != 0) {
                long j8 = j4 + 900000;
                if (j7 < j8) {
                    return j8;
                }
            }
            return j7;
        }
        if (z3) {
            d0.a aVar2 = d0.a.f399e;
            long j9 = this.f1343m;
            long jScalb = aVar == aVar2 ? j9 * ((long) i4) : (long) Math.scalb(j9, i4 - 1);
            if (jScalb > 18000000) {
                jScalb = 18000000;
            }
            return j4 + jScalb;
        }
        long j10 = this.f1337g;
        if (zC) {
            long j11 = i5 == 0 ? j4 + j10 : j4 + j6;
            return (j5 == j6 || i5 != 0) ? j11 : (j6 - j5) + j11;
        }
        if (j4 == -1) {
            return Long.MAX_VALUE;
        }
        return j4 + j10;
    }

    public final boolean c() {
        return this.f1338h != 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return j2.i.a(this.f1331a, pVar.f1331a) && this.f1332b == pVar.f1332b && j2.i.a(this.f1333c, pVar.f1333c) && j2.i.a(this.f1334d, pVar.f1334d) && j2.i.a(this.f1335e, pVar.f1335e) && j2.i.a(this.f1336f, pVar.f1336f) && this.f1337g == pVar.f1337g && this.f1338h == pVar.f1338h && this.f1339i == pVar.f1339i && j2.i.a(this.f1340j, pVar.f1340j) && this.f1341k == pVar.f1341k && this.f1342l == pVar.f1342l && this.f1343m == pVar.f1343m && this.f1344n == pVar.f1344n && this.f1345o == pVar.f1345o && this.f1346p == pVar.f1346p && this.f1347q == pVar.f1347q && this.f1348r == pVar.f1348r && this.f1349s == pVar.f1349s && this.f1350t == pVar.f1350t && this.f1351u == pVar.f1351u && this.f1352v == pVar.f1352v && this.f1353w == pVar.f1353w && j2.i.a(this.f1354x, pVar.f1354x) && j2.i.a(this.f1355y, pVar.f1355y);
    }

    public final int hashCode() {
        int iHashCode = (this.f1336f.hashCode() + ((this.f1335e.hashCode() + ((this.f1334d.hashCode() + ((this.f1333c.hashCode() + ((this.f1332b.hashCode() + (this.f1331a.hashCode() * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        long j4 = this.f1337g;
        int i4 = (iHashCode + ((int) (j4 ^ (j4 >>> 32)))) * 31;
        long j5 = this.f1338h;
        int i5 = (i4 + ((int) (j5 ^ (j5 >>> 32)))) * 31;
        long j6 = this.f1339i;
        int iHashCode2 = (this.f1342l.hashCode() + ((((this.f1340j.hashCode() + ((i5 + ((int) (j6 ^ (j6 >>> 32)))) * 31)) * 31) + this.f1341k) * 31)) * 31;
        long j7 = this.f1343m;
        int i6 = (iHashCode2 + ((int) (j7 ^ (j7 >>> 32)))) * 31;
        long j8 = this.f1344n;
        int i7 = (i6 + ((int) (j8 ^ (j8 >>> 32)))) * 31;
        long j9 = this.f1345o;
        int i8 = (i7 + ((int) (j9 ^ (j9 >>> 32)))) * 31;
        long j10 = this.f1346p;
        int iHashCode3 = (((((this.f1348r.hashCode() + ((((i8 + ((int) (j10 ^ (j10 >>> 32)))) * 31) + (this.f1347q ? 1231 : 1237)) * 31)) * 31) + this.f1349s) * 31) + this.f1350t) * 31;
        long j11 = this.f1351u;
        int i9 = (((((iHashCode3 + ((int) ((j11 >>> 32) ^ j11))) * 31) + this.f1352v) * 31) + this.f1353w) * 31;
        String str = this.f1354x;
        int iHashCode4 = (i9 + (str == null ? 0 : str.hashCode())) * 31;
        Boolean bool = this.f1355y;
        return iHashCode4 + (bool != null ? bool.hashCode() : 0);
    }

    public final String toString() {
        return "{WorkSpec: " + this.f1331a + '}';
    }

    public /* synthetic */ p(String str, k0 k0Var, String str2, String str3, d0.j jVar, d0.j jVar2, long j4, long j5, long j6, d0.e eVar, int i4, d0.a aVar, long j7, long j8, long j9, long j10, boolean z3, i0 i0Var, int i5, long j11, int i6, int i7, String str4, Boolean bool, int i8) {
        this(str, (i8 & 2) != 0 ? k0.f467d : k0Var, str2, (i8 & 8) != 0 ? OverwritingInputMerger.class.getName() : str3, (i8 & 16) != 0 ? d0.j.f464b : jVar, (i8 & 32) != 0 ? d0.j.f464b : jVar2, (i8 & 64) != 0 ? 0L : j4, (i8 & 128) != 0 ? 0L : j5, (i8 & 256) != 0 ? 0L : j6, (i8 & 512) != 0 ? d0.e.f432j : eVar, (i8 & 1024) != 0 ? 0 : i4, (i8 & 2048) != 0 ? d0.a.f398d : aVar, (i8 & 4096) != 0 ? 30000L : j7, (i8 & 8192) != 0 ? -1L : j8, (i8 & 16384) == 0 ? j9 : 0L, (32768 & i8) != 0 ? -1L : j10, (65536 & i8) != 0 ? false : z3, (131072 & i8) != 0 ? i0.f461d : i0Var, (262144 & i8) != 0 ? 0 : i5, 0, (1048576 & i8) != 0 ? Long.MAX_VALUE : j11, (2097152 & i8) != 0 ? 0 : i6, (4194304 & i8) != 0 ? -256 : i7, (8388608 & i8) != 0 ? null : str4, (i8 & 16777216) != 0 ? Boolean.FALSE : bool);
    }
}
