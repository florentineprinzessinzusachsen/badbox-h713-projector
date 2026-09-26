package f1;

import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import com.speed.adv.AdService;
import d0.l0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final t f890a = new t();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile boolean f891b;

    public static final void a(Application application) {
        Object objL;
        b1.a.f336a.b(application);
        Intent intent = new Intent(application, (Class<?>) AdService.class);
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                l0.J(application, intent);
            } else {
                application.startService(intent);
            }
            l3.h.a0("[AdvRuntime] 已发送运行时服务启动请求 -> ".concat(AdService.class.getName()));
            objL = u1.k.f2301a;
        } catch (Throwable th) {
            objL = l0.l(th);
        }
        Throwable thA = u1.h.a(objL);
        if (thA != null) {
            Log.e("AdvRuntime", "start runtime service failed", thA);
            a1.c.f("[AdvRuntime] 运行时服务启动失败 -> ", thA.getMessage());
        }
    }

    public static void b(Application application, Class cls) {
        Object objL;
        Intent intent = new Intent(application, (Class<?>) cls);
        l3.h.a0("[AdvRuntime] startKeepAlive -> ".concat(cls.getName()));
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                l0.J(application, intent);
            } else {
                application.startService(intent);
            }
            l3.h.a0("[AdvRuntime] startKeepAlive 已发送启动请求 -> ".concat(cls.getName()));
            objL = u1.k.f2301a;
        } catch (Throwable th) {
            objL = l0.l(th);
        }
        Throwable thA = u1.h.a(objL);
        if (thA != null) {
            Log.e("AdvRuntime", "start keepalive service failed: ".concat(cls.getName()), thA);
            l3.h.a0("[AdvRuntime] startKeepAlive 失败 -> " + cls.getName() + ", error=" + thA.getMessage());
        }
    }
}
