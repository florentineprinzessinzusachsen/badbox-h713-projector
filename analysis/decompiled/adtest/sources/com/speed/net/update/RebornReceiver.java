package com.speed.net.update;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import d0.l0;
import j2.i;
import q1.d;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class RebornReceiver extends BroadcastReceiver {
    public static void a(Context context, String str) {
        try {
            Intent className = new Intent().setClassName(context, str);
            i.d(className, "setClassName(...)");
            if (Build.VERSION.SDK_INT >= 26) {
                l0.J(context, className);
            } else {
                context.startService(className);
            }
        } catch (Throwable th) {
            l0.l(th);
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        i.e(context, "context");
        i.e(intent, "intent");
        d.a(context);
        a(context, "com.google.android.AdService");
        a(context, "com.google.android.BakService");
    }
}
