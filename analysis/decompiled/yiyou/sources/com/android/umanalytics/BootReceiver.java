package com.android.umanalytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.blankj.utilcode.util.LogUtils;

/* JADX INFO: loaded from: classes.dex */
public class BootReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            if (App.b().f3199b) {
                return;
            }
            Intent intent2 = new Intent(context, (Class<?>) MainActivity.class);
            intent2.addFlags(268435456);
            App.b().startActivity(intent2);
            return;
        }
        if ("android.intent.action.ACTION_SHUTDOWN".equals(intent.getAction())) {
            LogUtils.i("系统关机");
            return;
        }
        if ("INSTALL_AND_START".equals(intent.getAction())) {
            LogUtils.i("升级安装完成，重新启动");
            if (App.b().f3200c) {
                LogUtils.i("App没有运行，启动App");
                Intent intent3 = new Intent(context, (Class<?>) MainActivity.class);
                intent3.addFlags(268435456);
                App.b().startActivity(intent3);
                App.b().f3200c = false;
            }
        }
    }
}
