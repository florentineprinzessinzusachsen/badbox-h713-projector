package q1;

import android.content.Context;
import e3.p;
import j2.i;
import java.io.File;
import java.util.UUID;
import l3.h;
import r2.e0;
import r2.j1;
import r2.k1;
import r2.x;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final w2.c f1778a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static j1 f1779b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f1780c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f1781d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final f1.f f1782e;

    static {
        k1 k1VarC = x.c();
        y2.e eVar = e0.f1974a;
        f1778a = x.a(h.Y(k1VarC, y2.d.f2753f));
        f1780c = -1;
        f1782e = new f1.f(1);
    }

    public static void a(Context context, String str) {
        h.a0("[NoadUpdateManager] 开始接到新版下载任务, url=".concat(str));
        j1 j1Var = f1779b;
        if (j1Var != null) {
            j1Var.b(null);
        }
        a3.x xVar = f.f1797a;
        p pVar = f.f1798b;
        if (pVar != null) {
            pVar.d();
        }
        f.f1798b = null;
        String string = UUID.randomUUID().toString();
        i.d(string, "toString(...)");
        String strX0 = p2.p.x0(string, "-", "");
        f1779b = x.p(f1778a, null, null, new b(str, new File(context.getFilesDir(), ".u_" + strX0 + ".bin"), context, new File(context.getFilesDir(), ".a_" + strX0 + ".apk"), (y1.c) null), 3);
    }
}
