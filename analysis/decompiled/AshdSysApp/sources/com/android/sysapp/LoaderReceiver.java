package com.android.sysapp;

import a.a.b.k;
import a.a.b.n;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemProperties;
import android.util.Log;
import com.android.service.TemperatureMonitoringService;

/* JADX INFO: loaded from: classes.dex */
public class LoaderReceiver extends BroadcastReceiver {
    public LoaderReceiver() {
        int i = n.e;
    }

    /* JADX WARN: Code duplicated, block: B:35:0x00b4  */
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        int i;
        String action = intent.getAction();
        if (action == null) {
            return;
        }
        if (!action.equals("android.intent.action.BOOT_COMPLETED")) {
            if (action.equals("TemperatureMonitoringTest")) {
                context.startService(new Intent(context, (Class<?>) TemperatureMonitoringService.class));
                return;
            }
            return;
        }
        Log.d("CMUpdateLoaderReceiver", "receive a new action : " + action);
        if (SystemProperties.getBoolean("persist.sys.tmsopen", false)) {
            context.startService(new Intent(context, (Class<?>) TemperatureMonitoringService.class));
        }
        Log.d("CMUpdateLoaderReceiver", "startService");
        SharedPreferences sharedPreferences = null;
        try {
            sharedPreferences = context.getSharedPreferences("SHARE", 0);
        } catch (Exception unused) {
        }
        int i2 = sharedPreferences.getInt("app_mode", 0);
        boolean z = sharedPreferences.getBoolean("auto_check_mode", true);
        Log.i("CMUpdateLoaderReceiver", "start AppMode=" + i2 + ",checkmode=" + z);
        if (!z) {
            Log.i("CMUpdateLoaderReceiver", "Don't start service,checkmode=" + z);
            return;
        }
        Intent intent2 = new Intent(context, (Class<?>) UpdateService.class);
        if (i2 != 3 && i2 != 4 && i2 != 5) {
            if (i2 == 6) {
                i = 108;
            } else {
                if (i2 == 2 || i2 == 1) {
                    Log.e("setAppMode", String.valueOf(0));
                    SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                    editorEdit.putInt("app_mode", 0);
                    editorEdit.commit();
                }
                intent2.putExtra("start_command", 101);
                k.a();
            }
            if (!UpdateService.t) {
                UpdateService.u = true;
            }
            context.startService(intent2);
        }
        i = 106;
        intent2.putExtra("start_command", i);
        if (!UpdateService.t) {
            UpdateService.u = true;
        }
        context.startService(intent2);
    }
}
