package t1;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.speed.service.DexLoaderService;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import r2.e0;
import r2.x;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static long f2172a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f2173b = 0;

    static {
        TimeZone.getTimeZone("Asia/Shanghai");
        new Handler(Looper.getMainLooper());
    }

    public static void a(Context context) {
        Object systemService = context.getSystemService("activity");
        j2.i.c(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        String str = context.getPackageName() + ":dex_loader";
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) systemService).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            l3.h.a0("No running processes found");
            return;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (j2.i.a(runningAppProcessInfo.processName, str)) {
                try {
                    context.stopService(new Intent(context, (Class<?>) DexLoaderService.class));
                    Process.killProcess(runningAppProcessInfo.pid);
                    l3.h.a0("Killed process: " + runningAppProcessInfo.processName + " (PID: " + runningAppProcessInfo.pid + ")");
                } catch (Exception e4) {
                    l3.h.a0("Failed to kill process " + runningAppProcessInfo.processName + ": " + e4.getMessage());
                }
            }
        }
    }

    public static void b() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - f2172a < 600000) {
            l3.h.a0("loadLocalPlugin: 距上次执行不足10分钟，跳过");
            return;
        }
        f2172a = jCurrentTimeMillis;
        y2.e eVar = e0.f1974a;
        x.p(x.a(y2.d.f2753f), null, null, new s1.a(2, null, 1), 3);
    }

    public static void c(String str, boolean z3, String str2, String str3, String str4, a3.o oVar, int i4) {
        boolean z4 = (i4 & 32) != 0;
        a3.o oVar2 = (i4 & 64) != 0 ? null : oVar;
        Context contextA = b1.a.f336a.a();
        if (z3) {
            y2.e eVar = e0.f1974a;
            x.p(x.a(w2.n.f2645a), null, null, new t(contextA, str3, str, str2, str4, z4, null, 0), 3);
            return;
        }
        Object systemService = contextA.getSystemService("activity");
        j2.i.c(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        Iterator<ActivityManager.RunningServiceInfo> it = ((ActivityManager) systemService).getRunningServices(Integer.MAX_VALUE).iterator();
        while (it.hasNext()) {
            if (DexLoaderService.class.getName().equals(it.next().service.getClassName())) {
                l3.h.a0("插件运行中，无需重新加载");
                o.f2158a.a("plugin_running", "插件运行中，无需重新加载");
                if (oVar2 != null) {
                    oVar2.a();
                    return;
                }
                return;
            }
        }
        y2.e eVar2 = e0.f1974a;
        x.p(x.a(w2.n.f2645a), null, null, new t(contextA, str3, str, str2, str4, z4, null, 1), 3);
    }
}
