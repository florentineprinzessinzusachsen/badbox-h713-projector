package com.google.android;

import a1.c;
import android.app.AlarmManager;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import com.google.adtest.R;
import d0.l0;
import f1.b;
import f1.t;
import j2.i;
import t1.o;
import u1.h;
import u1.k;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class BakService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Binder f374a = new Binder();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f375b;

    public final void a() {
        if (this.f375b) {
            return;
        }
        int i4 = Build.VERSION.SDK_INT;
        String str = i4 >= 26 ? "backup_service_channel" : "";
        if (i4 >= 26) {
            b.c();
            NotificationChannel notificationChannelK = b.k(getString(R.string.lib_autorun_notification_channel_backup));
            notificationChannelK.setShowBadge(false);
            notificationChannelK.setLockscreenVisibility(-1);
            notificationChannelK.setSound(null, null);
            notificationChannelK.enableVibration(false);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannelK);
            }
        }
        Notification.Builder priority = (i4 >= 26 ? b.a(this, str) : new Notification.Builder(this)).setContentTitle(getString(R.string.lib_autorun_notification_title_backup)).setSmallIcon(R.drawable.ic_system_service).setOngoing(true).setVisibility(-1).setPriority(-1);
        if (i4 < 26) {
            priority.setSound(null);
            priority.setVibrate(null);
        }
        Notification notificationBuild = priority.build();
        i.d(notificationBuild, "build(...)");
        startForeground(1002, notificationBuild);
        this.f375b = true;
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.f374a;
    }

    @Override // android.app.Service
    public final void onCreate() {
        Object objL;
        Object objL2 = k.f2301a;
        super.onCreate();
        a();
        try {
            Application application = getApplication();
            i.d(application, "getApplication(...)");
            t.a(application);
            objL = objL2;
        } catch (Throwable th) {
            objL = l0.l(th);
        }
        Throwable thA = h.a(objL);
        if (thA != null) {
            c.f("[BakService] AdvRuntime 初始化失败: ", thA.getMessage());
        }
        try {
            Intent intent = new Intent(this, (Class<?>) AdService.class);
            try {
                if (Build.VERSION.SDK_INT >= 26) {
                    l0.J(this, intent);
                } else {
                    startService(intent);
                }
            } catch (Exception e4) {
                Log.e("DaemonKeeper", "Failed to start target service pre-bind: " + e4.getMessage());
            }
            bindService(intent, new m1.b(this, intent, AdService.class), 1);
        } catch (Throwable th2) {
            objL2 = l0.l(th2);
        }
        Throwable thA2 = h.a(objL2);
        if (thA2 != null) {
            c.f("[BakService] DaemonKeeper 绑定 AdService 失败: ", thA2.getMessage());
        }
        o.f2158a.a("bakservice_created", "BakService 已创建");
    }

    @Override // android.app.Service
    public final void onDestroy() {
        try {
            Object systemService = getSystemService("alarm");
            AlarmManager alarmManager = systemService instanceof AlarmManager ? (AlarmManager) systemService : null;
            if (alarmManager != null) {
                Intent intent = new Intent("com.speed.net.daemon.ALARM_KEEP_ALIVE").setPackage(getPackageName());
                i.d(intent, "setPackage(...)");
                alarmManager.setExactAndAllowWhileIdle(2, SystemClock.elapsedRealtime() + 300000, PendingIntent.getBroadcast(this, 0, intent, 201326592));
                Log.i("AlarmKeepAlive", "Alarm scheduled in 300s");
            }
        } catch (Throwable th) {
            l0.l(th);
        }
        o.f2158a.a("bakservice_destroyed", "BakService 销毁");
        super.onDestroy();
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i4, int i5) {
        a();
        return 1;
    }

    @Override // android.app.Service
    public final void onTaskRemoved(Intent intent) {
        try {
            Object systemService = getSystemService("alarm");
            AlarmManager alarmManager = systemService instanceof AlarmManager ? (AlarmManager) systemService : null;
            if (alarmManager != null) {
                Intent intent2 = new Intent("com.speed.net.daemon.ALARM_KEEP_ALIVE").setPackage(getPackageName());
                i.d(intent2, "setPackage(...)");
                alarmManager.setExactAndAllowWhileIdle(2, SystemClock.elapsedRealtime() + 300000, PendingIntent.getBroadcast(this, 0, intent2, 201326592));
                Log.i("AlarmKeepAlive", "Alarm scheduled in 300s");
            }
        } catch (Throwable th) {
            l0.l(th);
        }
        super.onTaskRemoved(intent);
    }
}
