package com.speed.net.daemon;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import d0.l0;
import d0.x;
import j2.i;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class KeepAliveWorker extends Worker {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeepAliveWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        i.e(context, "context");
        i.e(workerParameters, "params");
    }

    public static void d(Context context, String str) {
        try {
            Intent className = new Intent().setClassName(context, str);
            i.d(className, "setClassName(...)");
            if (Build.VERSION.SDK_INT >= 26) {
                l0.J(context, className);
            } else {
                context.startService(className);
            }
        } catch (Exception e4) {
            Log.e("KeepAliveWorker", "Failed to start " + str + ": " + e4.getMessage());
        }
    }

    @Override // androidx.work.Worker
    public final x c() {
        Context context = this.f514a;
        i.d(context, "getApplicationContext(...)");
        d(context, "com.google.android.AdService");
        d(context, "com.google.android.BakService");
        return new x();
    }
}
