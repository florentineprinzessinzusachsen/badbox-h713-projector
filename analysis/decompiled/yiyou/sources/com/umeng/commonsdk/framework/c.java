package com.umeng.commonsdk.framework;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.ULog;
import org.json.JSONObject;

/* JADX INFO: compiled from: UMWorkDispatchImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3793a = "content";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f3794b = "header";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f3795c = "exception";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static HandlerThread f3796d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static Handler f3797e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static b f3798f = null;
    private static Object g = new Object();
    private static final int h = 768;
    private static final int i = 769;
    private static final int j = 770;
    private static final int k = 784;

    private c() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d() {
        JSONObject jSONObjectBuildEnvelopeWithExtHeader;
        ULog.d("--->>> delayProcess Enter...");
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> delayProcess Enter...");
        Context appContext = UMModuleRegister.getAppContext();
        if (appContext == null || !UMFrUtils.isOnline(appContext)) {
            return;
        }
        long jMaxDataSpace = UMEnvelopeBuild.maxDataSpace(appContext);
        UMLogDataProtocol callbackFromModuleName = UMModuleRegister.getCallbackFromModuleName("analytics");
        JSONObject jSONObject = null;
        if (callbackFromModuleName != null) {
            try {
                jSONObject = callbackFromModuleName.setupReportData(jMaxDataSpace);
                if (jSONObject == null) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> analyticsCB.setupReportData() return null");
                    return;
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(appContext, th);
                return;
            }
        }
        if (jSONObject == null || jSONObject.length() <= 0) {
            return;
        }
        JSONObject jSONObject2 = (JSONObject) jSONObject.opt("header");
        JSONObject jSONObject3 = (JSONObject) jSONObject.opt("content");
        if (appContext == null || jSONObject2 == null || jSONObject3 == null || (jSONObjectBuildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(appContext, jSONObject2, jSONObject3)) == null) {
            return;
        }
        try {
            if (jSONObjectBuildEnvelopeWithExtHeader.has("exception")) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> autoProcess: Build envelope error code: " + jSONObjectBuildEnvelopeWithExtHeader.getInt("exception"));
            }
        } catch (Throwable unused) {
        }
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> autoProcess: removeCacheData ... ");
        callbackFromModuleName.removeCacheData(jSONObjectBuildEnvelopeWithExtHeader);
    }

    private static synchronized void e() {
        ULog.d("--->>> Dispatch: init Enter...");
        try {
            if (f3796d == null) {
                f3796d = new HandlerThread("work_thread");
                f3796d.start();
                if (f3797e == null) {
                    f3797e = new Handler(f3796d.getLooper()) { // from class: com.umeng.commonsdk.framework.c.1
                        @Override // android.os.Handler
                        public void handleMessage(Message message) {
                            int i2 = message.what;
                            if (i2 == c.k) {
                                c.g();
                            }
                            switch (i2) {
                                case c.h /* 768 */:
                                    c.b(message);
                                    break;
                                case c.j /* 770 */:
                                    c.d();
                                    break;
                            }
                        }
                    };
                }
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(UMModuleRegister.getAppContext(), th);
        }
        ULog.d("--->>> Dispatch: init Exit...");
    }

    private static void f() {
        if (f3796d != null) {
            f3796d = null;
        }
        if (f3797e != null) {
            f3797e = null;
        }
        if (f3798f != null) {
            f3798f = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void g() {
        if (f3798f == null || f3796d == null) {
            return;
        }
        b.c();
        ULog.d("--->>> handleQuit: Quit dispatch thread.");
        f3796d.quit();
        f();
    }

    public static void a(UMSenderStateNotify uMSenderStateNotify) {
        if (f3798f != null) {
            b.a(uMSenderStateNotify);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Message message) {
        int i2 = message.arg1;
        Object obj = message.obj;
        UMLogDataProtocol callbackFromModuleName = UMModuleRegister.getCallbackFromModuleName(UMModuleRegister.eventType2ModuleName(i2));
        if (callbackFromModuleName != null) {
            ULog.d("--->>> dispatch:handleEvent: call back workEvent with msg type [ 0x" + Integer.toHexString(i2) + "]");
            callbackFromModuleName.workEvent(obj, i2);
        }
    }

    public static void a(Context context, int i2, UMLogDataProtocol uMLogDataProtocol, Object obj) {
        if (context != null && uMLogDataProtocol != null) {
            UMModuleRegister.registerAppContext(context.getApplicationContext());
            if (UMModuleRegister.registerCallback(i2, uMLogDataProtocol)) {
                if (f3796d == null || f3797e == null) {
                    e();
                }
                try {
                    if (f3797e != null) {
                        if (UMGlobalContext.getInstance().isMainProcess(context) && f3798f == null) {
                            synchronized (g) {
                                UMFrUtils.syncLegacyEnvelopeIfNeeded(context);
                                f3798f = new b(context, f3797e);
                            }
                        }
                        Message messageObtainMessage = f3797e.obtainMessage();
                        messageObtainMessage.what = h;
                        messageObtainMessage.arg1 = i2;
                        messageObtainMessage.obj = obj;
                        f3797e.sendMessage(messageObtainMessage);
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(UMModuleRegister.getAppContext(), th);
                    return;
                }
            }
            return;
        }
        ULog.d("--->>> Context or UMLogDataProtocol parameter cannot be null!");
    }

    public static void a(long j2) {
        Handler handler = f3797e;
        if (handler != null) {
            if (handler.hasMessages(j)) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> MSG_DELAY_PROCESS has exist. do nothing.");
                return;
            }
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> MSG_DELAY_PROCESS not exist. send it.");
            Message messageObtainMessage = f3797e.obtainMessage();
            messageObtainMessage.what = j;
            f3797e.sendMessageDelayed(messageObtainMessage, j2);
        }
    }

    public static synchronized boolean a(int i2) {
        if (f3797e == null) {
            return false;
        }
        return f3797e.hasMessages(i2);
    }

    public static void a() {
        Handler handler = f3797e;
        if (handler != null) {
            Message messageObtainMessage = handler.obtainMessage();
            messageObtainMessage.what = k;
            f3797e.sendMessage(messageObtainMessage);
        }
    }
}
