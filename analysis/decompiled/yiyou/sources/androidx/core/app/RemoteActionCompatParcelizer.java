package androidx.core.app;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;

/* JADX INFO: loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(androidx.versionedparcelable.a aVar) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.f899a = (IconCompat) aVar.a(remoteActionCompat.f899a, 1);
        remoteActionCompat.f900b = aVar.a(remoteActionCompat.f900b, 2);
        remoteActionCompat.f901c = aVar.a(remoteActionCompat.f901c, 3);
        remoteActionCompat.f902d = (PendingIntent) aVar.a(remoteActionCompat.f902d, 4);
        remoteActionCompat.f903e = aVar.a(remoteActionCompat.f903e, 5);
        remoteActionCompat.f904f = aVar.a(remoteActionCompat.f904f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, androidx.versionedparcelable.a aVar) {
        aVar.a(false, false);
        aVar.b(remoteActionCompat.f899a, 1);
        aVar.b(remoteActionCompat.f900b, 2);
        aVar.b(remoteActionCompat.f901c, 3);
        aVar.b(remoteActionCompat.f902d, 4);
        aVar.b(remoteActionCompat.f903e, 5);
        aVar.b(remoteActionCompat.f904f, 6);
    }
}
