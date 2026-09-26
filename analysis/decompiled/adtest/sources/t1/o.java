package t1;

import a3.a0;
import a3.b0;
import a3.v;
import a3.x;
import a3.z;
import android.content.Context;
import d0.l0;
import h1.c0;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f2159b = "";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final o f2158a = new o();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final SimpleDateFormat f2160c = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    public final void a(String str, String str2) {
        j2.i.e(str2, "message");
        synchronized (this) {
            try {
                String str3 = str2 + " " + f2160c.format(new Date());
                c0.f1036a.getClass();
                Map mapE = c0.e();
                mapE.put(str, str3);
                if (mapE.size() > 150) {
                    Iterator it = mapE.entrySet().iterator();
                    for (int i4 = 0; i4 < 15; i4++) {
                        if (it.hasNext()) {
                            it.next();
                            it.remove();
                        }
                    }
                    l3.h.a0("Removed 15 oldest logs to maintain size limit (150)");
                }
                c0.f1036a.getClass();
                c0.o(mapE);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void b(i2.l lVar) {
        synchronized (this) {
            c0 c0Var = c0.f1036a;
            c0Var.getClass();
            Map mapE = c0.e();
            if (!mapE.isEmpty() && a.a.i()) {
                l3.h.a0("准备开始上传日志");
                try {
                    Context contextA = b1.a.f336a.a();
                    o1.a aVar = e1.a.f703a;
                    o1.a aVar2 = o1.a.f1558d;
                    String str = aVar == aVar2 ? "" : "/v2";
                    f2159b = c0.d().get(0) + "appapi/debug/reportInfo" + str;
                    String strG = l3.h.G();
                    if (strG == null) {
                        strG = "";
                    }
                    Map mapK = v1.t.K(new u1.f("mac", strG), new u1.f("channel", c0.c()), new u1.f("logs", v1.t.K(new u1.f("logs", mapE), new u1.f("device_id", c0Var.f()), new u1.f("app_version", l3.h.b(contextA)))));
                    x xVar = l1.f.f1368a;
                    String str2 = f2159b;
                    o1.a aVar3 = e1.a.f703a;
                    v1.q qVar = v1.q.f2518d;
                    Type type = new j().f2779b;
                    j2.i.d(type, "getType(...)");
                    x xVar2 = l1.f.f1368a;
                    String strA = aVar3 == aVar2 ? l1.f.a(str2, mapK) : l1.f.a(str2, qVar);
                    z zVar = new z();
                    zVar.d(strA);
                    if (aVar3 == o1.a.f1559e) {
                        String strE = l1.f.f1369b.e(mapK);
                        j2.i.b(strE);
                        String strX = l3.h.x(strE);
                        int i4 = b0.f70d;
                        p2.h hVar = v.f216c;
                        zVar.c("POST", l3.h.s(l0.t("application/json; charset=utf-8"), strX));
                        zVar.a("Content-Type", "application/json; charset=utf-8");
                        zVar.a("X-Encrypted", "AES-GCM");
                    }
                    a0 a0Var = new a0(zVar);
                    xVar2.getClass();
                    new e3.p(xVar2, a0Var).e(new a2.f(aVar3, type, lVar, 4));
                    return;
                } catch (Exception e4) {
                    a1.c.f("Log upload exception: ", e4.getMessage());
                    lVar.h(Boolean.FALSE);
                    return;
                }
            }
            l3.h.a0("日志为空或不允许日志上传");
            lVar.h(Boolean.TRUE);
        }
    }
}
