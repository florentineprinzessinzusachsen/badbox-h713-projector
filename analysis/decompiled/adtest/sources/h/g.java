package h;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.os.Bundle;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class g {
    public static Notification.Builder a(Notification.Builder builder, Notification.Action action) {
        return builder.addAction(action);
    }

    public static Notification.Action.Builder b(Notification.Action.Builder builder, Bundle bundle) {
        return builder.addExtras(bundle);
    }

    public static Notification.Action.Builder c(Notification.Action.Builder builder, RemoteInput remoteInput) {
        return builder.addRemoteInput(remoteInput);
    }

    public static Notification.Action d(Notification.Action.Builder builder) {
        return builder.build();
    }

    public static Notification.Action.Builder e(int i4, CharSequence charSequence, PendingIntent pendingIntent) {
        return new Notification.Action.Builder(i4, charSequence, pendingIntent);
    }

    public static String f(Notification notification) {
        return notification.getGroup();
    }

    public static Notification.Builder g(Notification.Builder builder, String str) {
        return builder.setGroup(str);
    }

    public static Notification.Builder h(Notification.Builder builder, boolean z3) {
        return builder.setGroupSummary(z3);
    }

    public static Notification.Builder i(Notification.Builder builder, boolean z3) {
        return builder.setLocalOnly(z3);
    }

    public static Notification.Builder j(Notification.Builder builder, String str) {
        return builder.setSortKey(str);
    }
}
