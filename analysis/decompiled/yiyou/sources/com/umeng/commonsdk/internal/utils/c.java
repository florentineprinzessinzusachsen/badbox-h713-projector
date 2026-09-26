package com.umeng.commonsdk.internal.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.baidu.mobstat.Config;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.ULog;
import org.json.JSONObject;

/* JADX INFO: compiled from: BatteryUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3831a = "BatteryUtils";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f3832b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Context f3833c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private BroadcastReceiver f3834d;

    /* JADX INFO: compiled from: BatteryUtils.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final c f3836a = new c();

        private a() {
        }
    }

    public static c a(Context context) {
        if (f3833c == null && context != null) {
            f3833c = context.getApplicationContext();
        }
        return a.f3836a;
    }

    public synchronized void b() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
            f3833c.registerReceiver(this.f3834d, intentFilter);
            f3832b = true;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(f3833c, th);
        }
    }

    public synchronized void c() {
        try {
            f3833c.unregisterReceiver(this.f3834d);
            f3832b = false;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(f3833c, th);
        }
    }

    private c() {
        this.f3834d = new BroadcastReceiver() { // from class: com.umeng.commonsdk.internal.utils.c.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                try {
                    if (intent.getAction().equals("android.intent.action.BATTERY_CHANGED")) {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("le", intent.getIntExtra("level", 0));
                        } catch (Exception unused) {
                        }
                        try {
                            jSONObject.put("vol", intent.getIntExtra("voltage", 0));
                        } catch (Exception unused2) {
                        }
                        try {
                            jSONObject.put("temp", intent.getIntExtra("temperature", 0));
                            jSONObject.put("ts", System.currentTimeMillis());
                        } catch (Exception unused3) {
                        }
                        int intExtra = intent.getIntExtra("status", 0);
                        int i = -1;
                        int i2 = 2;
                        if (intExtra != 1) {
                            if (intExtra == 2) {
                                i = 1;
                            } else if (intExtra != 3) {
                                if (intExtra == 4) {
                                    i = 0;
                                } else if (intExtra == 5) {
                                    i = 2;
                                }
                            }
                        }
                        try {
                            jSONObject.put("st", i);
                        } catch (Exception unused4) {
                        }
                        int intExtra2 = intent.getIntExtra("plugged", 0);
                        if (intExtra2 == 1) {
                            i2 = 1;
                        } else if (intExtra2 != 2) {
                            i2 = 0;
                        }
                        try {
                            jSONObject.put(Config.EXCEPTION_CRASH_TYPE, i2);
                            jSONObject.put("ts", System.currentTimeMillis());
                        } catch (Exception unused5) {
                        }
                        ULog.i(c.f3831a, jSONObject.toString());
                        UMWorkDispatch.sendEvent(context, com.umeng.commonsdk.internal.a.g, com.umeng.commonsdk.internal.b.a(c.f3833c).a(), jSONObject.toString());
                        c.this.c();
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(c.f3833c, th);
                }
            }
        };
    }

    public synchronized boolean a() {
        return f3832b;
    }
}
