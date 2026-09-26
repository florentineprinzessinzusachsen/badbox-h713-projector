package d0;

import android.app.Notification;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f490a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f491b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Notification f492c;

    public o(int i4, Notification notification, int i5) {
        this.f490a = i4;
        this.f492c = notification;
        this.f491b = i5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || o.class != obj.getClass()) {
            return false;
        }
        o oVar = (o) obj;
        if (this.f490a == oVar.f490a && this.f491b == oVar.f491b) {
            return this.f492c.equals(oVar.f492c);
        }
        return false;
    }

    public final int hashCode() {
        return this.f492c.hashCode() + (((this.f490a * 31) + this.f491b) * 31);
    }

    public final String toString() {
        return "ForegroundInfo{mNotificationId=" + this.f490a + ", mForegroundServiceType=" + this.f491b + ", mNotification=" + this.f492c + '}';
    }
}
