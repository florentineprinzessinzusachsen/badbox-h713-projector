package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import d0.a0;
import e0.y;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class RescheduleReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f323a = a0.g("RescheduleReceiver");

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        a0.e().a(f323a, "Received intent " + intent);
        try {
            y yVarS = y.S(context);
            BroadcastReceiver.PendingResult pendingResultGoAsync = goAsync();
            synchronized (y.f691m) {
                try {
                    BroadcastReceiver.PendingResult pendingResult = yVarS.f700i;
                    if (pendingResult != null) {
                        pendingResult.finish();
                    }
                    yVarS.f700i = pendingResultGoAsync;
                    if (yVarS.f699h) {
                        pendingResultGoAsync.finish();
                        yVarS.f700i = null;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (IllegalStateException e4) {
            a0.e().d(f323a, "Cannot reschedule jobs. WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().", e4);
        }
    }
}
