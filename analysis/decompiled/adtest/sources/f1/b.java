package f1;

import android.app.Notification;
import android.app.NotificationChannel;
import com.google.android.BakService;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class b {
    public static /* synthetic */ Notification.Builder a(BakService bakService, String str) {
        return new Notification.Builder(bakService, str);
    }

    public static /* synthetic */ NotificationChannel b(String str) {
        return new NotificationChannel("AdServiceChannel", str, 3);
    }

    public static /* synthetic */ void c() {
    }

    public static /* synthetic */ NotificationChannel h(String str) {
        return new NotificationChannel("com.google.android.AdService", str, 2);
    }

    public static /* synthetic */ NotificationChannel k(String str) {
        return new NotificationChannel("backup_service_channel", str, 2);
    }
}
