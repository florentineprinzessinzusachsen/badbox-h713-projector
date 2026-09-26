package m0;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import d0.a0;
import d0.l0;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1409a;

    static {
        String strG = a0.g("ProcessUtils");
        j2.i.d(strG, "tagWithPrefix(...)");
        f1409a = strG;
    }

    public static final boolean a(Context context, d0.b bVar) {
        String strA;
        Object next;
        j2.i.e(context, "context");
        j2.i.e(bVar, "configuration");
        if (Build.VERSION.SDK_INT >= 28) {
            strA = a.a();
        } else {
            strA = null;
            try {
                Method declaredMethod = Class.forName("android.app.ActivityThread", false, l0.class.getClassLoader()).getDeclaredMethod("currentProcessName", null);
                declaredMethod.setAccessible(true);
                Object objInvoke = declaredMethod.invoke(null, null);
                j2.i.b(objInvoke);
                if (objInvoke instanceof String) {
                    strA = (String) objInvoke;
                } else {
                    int iMyPid = Process.myPid();
                    Object systemService = context.getSystemService("activity");
                    j2.i.c(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
                    List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) systemService).getRunningAppProcesses();
                    if (runningAppProcesses != null) {
                        Iterator<T> it = runningAppProcesses.iterator();
                        do {
                            if (!it.hasNext()) {
                                next = null;
                                break;
                            }
                            next = it.next();
                        } while (((ActivityManager.RunningAppProcessInfo) next).pid != iMyPid);
                        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = (ActivityManager.RunningAppProcessInfo) next;
                        if (runningAppProcessInfo != null) {
                            strA = runningAppProcessInfo.processName;
                        }
                    }
                }
            } catch (Throwable th) {
                a0.e().b(f1409a, "Unable to check ActivityThread for processName", th);
            }
        }
        return j2.i.a(strA, context.getApplicationInfo().processName);
    }
}
