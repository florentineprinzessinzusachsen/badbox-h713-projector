package k0;

import android.app.ForegroundServiceStartNotAllowedException;
import android.app.Notification;
import android.util.Log;
import androidx.work.impl.foreground.SystemForegroundService;
import d0.a0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {
    public static void a(SystemForegroundService systemForegroundService, int i4, Notification notification, int i5) {
        systemForegroundService.startForeground(i4, notification, i5);
    }

    public static void b(SystemForegroundService systemForegroundService, int i4, Notification notification, int i5) {
        try {
            systemForegroundService.startForeground(i4, notification, i5);
        } catch (ForegroundServiceStartNotAllowedException e4) {
            a0 a0VarE = a0.e();
            String str = SystemForegroundService.f330e;
            if (a0VarE.f403a <= 5) {
                Log.w(str, "Unable to start foreground service", e4);
            }
        } catch (SecurityException e5) {
            a0 a0VarE2 = a0.e();
            String str2 = SystemForegroundService.f330e;
            if (a0VarE2.f403a <= 5) {
                Log.w(str2, "Unable to start foreground service", e5);
            }
        }
    }
}
