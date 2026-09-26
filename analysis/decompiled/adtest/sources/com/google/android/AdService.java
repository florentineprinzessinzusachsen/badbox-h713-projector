package com.google.android;

import a1.c;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import com.google.adtest.R;
import d0.l0;
import f1.b;
import f1.t;
import j2.i;
import l3.h;
import u1.k;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class AdService extends Service {
    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        Object objL;
        NotificationManager notificationManager;
        super.onCreate();
        h.a0("[GoogleAdService] onCreate");
        Log.d("AdService", "start");
        try {
            Application application = getApplication();
            i.d(application, "getApplication(...)");
            t.a(application);
            objL = k.f2301a;
        } catch (Throwable th) {
            objL = l0.l(th);
        }
        Throwable thA = u1.h.a(objL);
        if (thA != null) {
            Log.e("AdService", "bootstrap init failed", thA);
            c.f("[GoogleAdService] bootstrap init failed: ", thA.getMessage());
        }
        h.a0("[GoogleAdService] setupForeground");
        if (Build.VERSION.SDK_INT >= 26 && (notificationManager = (NotificationManager) getSystemService(NotificationManager.class)) != null) {
            b.c();
            notificationManager.createNotificationChannel(b.h(getString(R.string.lib_autorun_keepalive_notification_channel)));
        }
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if (launchIntentForPackage == null) {
            launchIntentForPackage = new Intent();
        }
        PendingIntent activity = PendingIntent.getActivity(this, 0, launchIntentForPackage, 201326592);
        h.c cVar = new h.c(this, "com.google.android.AdService");
        cVar.f994l.icon = android.R.drawable.stat_notify_sync;
        cVar.f987e = h.c.b(getString(R.string.lib_autorun_keepalive_notification_title));
        cVar.f988f = h.c.b(getString(R.string.lib_autorun_keepalive_notification_text));
        cVar.f989g = activity;
        cVar.f994l.flags |= 2;
        Notification notificationA = cVar.a();
        i.d(notificationA, "build(...)");
        startForeground(10000, notificationA);
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i4, int i5) {
        h.a0("[GoogleAdService] onStartCommand startId=" + i5 + " flags=" + i4);
        Log.d("AdService", "onStartCommand");
        return 1;
    }
}
