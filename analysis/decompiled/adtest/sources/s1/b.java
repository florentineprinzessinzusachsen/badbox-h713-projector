package s1;

import a3.a0;
import d0.l0;
import h1.c0;
import l3.h;
import p2.i;
import r2.e0;
import r2.j1;
import r2.k1;
import r2.x;
import t1.o;
import w2.c;
import y2.d;
import y2.e;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c f2142a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static j1 f2143b;

    static {
        k1 k1VarC = x.c();
        e eVar = e0.f1974a;
        f2142a = x.a(h.Y(k1VarC, d.f2753f));
    }

    public static void a() {
        c0 c0Var = c0.f1036a;
        c0Var.getClass();
        if (i.B0(c0.i(), "random")) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            a0 a0Var = c0.f1057v;
            n2.c[] cVarArr = c0.f1037b;
            long jLongValue = jCurrentTimeMillis - ((Number) a0Var.a(cVarArr[16])).longValue();
            c0Var.getClass();
            if (jLongValue >= a.a.F("persist.autorun.ping_rd_time", c0.f1041f)) {
                j1 j1Var = f2143b;
                y1.c cVar = null;
                if (j1Var != null) {
                    j1Var.b(null);
                }
                f2143b = x.p(f2142a, null, null, new a(2, cVar, 0), 3);
                return;
            }
            long jLongValue2 = jCurrentTimeMillis - ((Number) a0Var.a(cVarArr[16])).longValue();
            c0Var.getClass();
            String str = "获取随机域名跳过：未达到阈值" + l0.P(a.a.F("persist.autorun.ping_rd_time", c0.f1041f)) + "，当前间隔" + l0.P(jLongValue2);
            h.a0(str);
            o.f2158a.a("domain_ping_skip", str);
        }
    }
}
