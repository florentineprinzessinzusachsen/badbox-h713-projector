package m0;

import android.content.Context;
import android.os.PowerManager;
import d0.a0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class l {
    static {
        j2.i.d(a0.g("WakeLocks"), "tagWithPrefix(...)");
    }

    public static final PowerManager.WakeLock a(Context context) {
        j2.i.e(context, "context");
        Object systemService = context.getApplicationContext().getSystemService("power");
        j2.i.c(systemService, "null cannot be cast to non-null type android.os.PowerManager");
        String strConcat = "WorkManager: ".concat("ProcessorForegroundLck");
        PowerManager.WakeLock wakeLockNewWakeLock = ((PowerManager) systemService).newWakeLock(1, strConcat);
        synchronized (m.f1419a) {
        }
        j2.i.b(wakeLockNewWakeLock);
        return wakeLockNewWakeLock;
    }
}
