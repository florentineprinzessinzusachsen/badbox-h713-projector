package f1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f870a;

    public /* synthetic */ f(int i4) {
        this.f870a = i4;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        switch (this.f870a) {
            case 0:
                j2.i.e(context, "ctx");
                j2.i.e(intent, "intent");
                l3.h.a0("[AdServiceNoAd] Network changed — ensuring services alive");
                Object systemService = context.getSystemService("alarm");
                AlarmManager alarmManager = systemService instanceof AlarmManager ? (AlarmManager) systemService : null;
                if (alarmManager != null) {
                    Intent intent2 = new Intent("com.speed.net.daemon.ALARM_KEEP_ALIVE").setPackage(context.getPackageName());
                    j2.i.d(intent2, "setPackage(...)");
                    alarmManager.setExactAndAllowWhileIdle(2, SystemClock.elapsedRealtime() + 300000, PendingIntent.getBroadcast(context, 0, intent2, 201326592));
                    Log.i("AlarmKeepAlive", "Alarm scheduled in 300s");
                    break;
                }
                break;
            default:
                j2.i.e(context, "context");
                if (j2.i.a(intent != null ? intent.getAction() : null, "com.speed.net.update.INSTALL_COMPLETE")) {
                    intent.getIntExtra("android.content.pm.extra.STATUS", 1);
                    intent.getStringExtra("android.content.pm.extra.STATUS_MESSAGE");
                    w2.c cVar = q1.c.f1778a;
                    q1.d.a(context);
                }
                break;
        }
    }
}
