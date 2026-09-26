package h;

import android.app.Notification;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class d {
    public static Notification a(Notification.Builder builder) {
        return builder.build();
    }

    public static Notification.Builder b(Notification.Builder builder, int i4) {
        return builder.setPriority(i4);
    }

    public static Notification.Builder c(Notification.Builder builder, CharSequence charSequence) {
        return builder.setSubText(charSequence);
    }

    public static Notification.Builder d(Notification.Builder builder, boolean z3) {
        return builder.setUsesChronometer(z3);
    }
}
