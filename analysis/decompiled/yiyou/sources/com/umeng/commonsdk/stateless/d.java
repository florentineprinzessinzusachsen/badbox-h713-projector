package com.umeng.commonsdk.stateless;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.File;

/* JADX INFO: compiled from: UMSLNetWorkSender.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f4045a = 273;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Context f4046b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static HandlerThread f4047c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Handler f4048d = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final int f4050f = 512;
    private static IntentFilter g = null;
    private static boolean h = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static Object f4049e = new Object();
    private static BroadcastReceiver i = new BroadcastReceiver() { // from class: com.umeng.commonsdk.stateless.d.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager;
            if (context == null || intent == null) {
                return;
            }
            try {
                if (intent.getAction() != null && intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    Context unused = d.f4046b = context.getApplicationContext();
                    if (d.f4046b != null && (connectivityManager = (ConnectivityManager) d.f4046b.getSystemService("connectivity")) != null) {
                        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                            ULog.i("walle", "[stateless] net reveiver disconnected --->>>");
                            boolean unused2 = d.h = false;
                        } else {
                            boolean unused3 = d.h = true;
                            ULog.i("walle", "[stateless] net reveiver ok --->>>");
                            d.b(d.f4045a);
                        }
                    }
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
            }
        }
    };

    public d(Context context) {
        synchronized (f4049e) {
            if (context != null) {
                try {
                    f4046b = context.getApplicationContext();
                    if (f4046b != null && f4047c == null) {
                        f4047c = new HandlerThread("SL-NetWorkSender");
                        f4047c.start();
                        if (f4048d == null) {
                            f4048d = new Handler(f4047c.getLooper()) { // from class: com.umeng.commonsdk.stateless.d.2
                                @Override // android.os.Handler
                                public void handleMessage(Message message) {
                                    int i2 = message.what;
                                    if (i2 == 273) {
                                        d.e();
                                    } else {
                                        if (i2 != d.f4050f) {
                                            return;
                                        }
                                        d.f();
                                    }
                                }
                            };
                        }
                        if (DeviceConfig.checkPermission(f4046b, "android.permission.ACCESS_NETWORK_STATE")) {
                            ULog.i("walle", "[stateless] begin register receiver");
                            if (g == null) {
                                g = new IntentFilter();
                                g.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                                if (i != null) {
                                    ULog.i("walle", "[stateless] register receiver ok");
                                    f4046b.registerReceiver(i, g);
                                }
                            }
                        }
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(context, th);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e() {
        Context context;
        if (!h || (context = f4046b) == null) {
            return;
        }
        try {
            File fileA = f.a(context);
            if (fileA == null || fileA.getParentFile() == null || TextUtils.isEmpty(fileA.getParentFile().getName())) {
                return;
            }
            e eVar = new e(f4046b);
            String str = new String(Base64.decode(fileA.getParentFile().getName(), 0));
            ULog.i("walle", "[stateless] handleProcessNext, pathUrl is " + str);
            byte[] bArrA = null;
            try {
                bArrA = f.a(fileA.getAbsolutePath());
            } catch (Exception unused) {
            }
            if (!eVar.a(bArrA, str)) {
                ULog.i("walle", "[stateless] Send envelope file failed, abandon and wait next trigger!");
                return;
            }
            ULog.i("walle", "[stateless] Send envelope file success, delete it.");
            File file = new File(fileA.getAbsolutePath());
            if (!file.delete()) {
                ULog.i("walle", "[stateless] Failed to delete already processed file. We try again after delete failed.");
                file.delete();
            }
            b(f4045a);
        } catch (Throwable th) {
            UMCrashManager.reportCrash(f4046b, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f() {
        if (g != null) {
            BroadcastReceiver broadcastReceiver = i;
            if (broadcastReceiver != null) {
                Context context = f4046b;
                if (context != null) {
                    context.unregisterReceiver(broadcastReceiver);
                }
                i = null;
            }
            g = null;
        }
        HandlerThread handlerThread = f4047c;
        if (handlerThread != null) {
            handlerThread.quit();
            if (f4047c != null) {
                f4047c = null;
            }
            if (f4048d != null) {
                f4048d = null;
            }
        }
    }

    public static void b(int i2) {
        try {
            if (!h || f4048d == null || f4048d.hasMessages(i2)) {
                return;
            }
            ULog.i("walle", "[stateless] sendMsgOnce !!!!");
            Message messageObtainMessage = f4048d.obtainMessage();
            messageObtainMessage.what = i2;
            f4048d.sendMessage(messageObtainMessage);
        } catch (Throwable th) {
            UMCrashManager.reportCrash(f4046b, th);
        }
    }

    public static void a(int i2) {
        Handler handler;
        if (!h || (handler = f4048d) == null) {
            return;
        }
        Message messageObtainMessage = handler.obtainMessage();
        messageObtainMessage.what = i2;
        f4048d.sendMessage(messageObtainMessage);
    }

    public static void a() {
        b(f4050f);
    }
}
