package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class StatService {
    public static final int EXCEPTION_LOG = 1;
    public static final int JAVA_EXCEPTION_LOG = 16;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f3421a = false;

    public interface OnZidReceiveListener {
        String getZid();
    }

    public interface WearListener {
        boolean onSendLogData(String str);
    }

    private static boolean a(Class<?> cls, String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        boolean z = false;
        for (int i = 2; i < stackTrace.length; i++) {
            StackTraceElement stackTraceElement = stackTrace[i];
            if (stackTraceElement.getMethodName().equals(str)) {
                try {
                    for (Class<?> cls2 = Class.forName(stackTraceElement.getClassName()); cls2.getSuperclass() != null && cls2.getSuperclass() != cls; cls2 = cls2.getSuperclass()) {
                    }
                    z = true;
                } catch (Exception unused) {
                }
            }
        }
        return z;
    }

    public static void bindJSInterface(Context context, WebView webView) {
        bindJSInterface(context, webView, null);
    }

    public static void enableDeviceMac(Context context, boolean z) {
        CooperService.instance().enableDeviceMac(context, z);
        BDStatCore.instance().init(context);
    }

    public static String getAppKey(Context context) {
        return PrefOperate.getAppKey(context);
    }

    public static String getSdkVersion() {
        return CooperService.instance().getMTJSDKVersion();
    }

    public static String getTestDeviceId(Context context) {
        return bb.b(context);
    }

    public static void onErised(Context context, String str, String str2, String str3) {
        if (a(context, "onErised(...)")) {
            if (str == null || "".equals(str)) {
                am.c().c("[WARNING] AppKey is invalid");
            } else {
                BDStatCore.instance().onErised(context, str, str2, str3);
            }
        }
    }

    public static void onEvent(Context context, String str, String str2, int i, Map<String, String> map) {
        a(context, str, str2, i, (ExtraInfo) null, map);
    }

    public static void onEventDuration(Context context, String str, String str2, long j, Map<String, String> map) {
        a(context, str, str2, j, (ExtraInfo) null, map);
    }

    public static void onEventEnd(Context context, String str, String str2) {
        a(context, str, str2, (ExtraInfo) null, (Map<String, String>) null);
    }

    public static void onEventStart(Context context, String str, String str2) {
        if (a(context, "onEventStart(...)") && !TextUtils.isEmpty(str)) {
            BDStatCore.instance().onEventStart(context.getApplicationContext(), str, str2, false);
        }
    }

    public static synchronized void onPageEnd(Context context, String str) {
        a(context, str, null);
    }

    public static synchronized void onPageStart(Context context, String str) {
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                BDStatCore.instance().onPageStart(context, str);
                return;
            }
        }
        am.c().c("[WARNING] onPageStart parameter invalid");
    }

    public static synchronized void onPause(Activity activity) {
        onPause(activity, null);
    }

    public static synchronized void onResume(Activity activity) {
        if (a(activity, "onResume(...)")) {
            if (a((Class<?>) Activity.class, "onResume")) {
                BDStatCore.instance().onResume(activity, false);
            } else {
                am.c().c("[WARNING] onResume must be called in Activity.onResume()");
            }
        }
    }

    public static void recordException(Context context, Throwable th) {
        if (context == null || th == null) {
            return;
        }
        ExceptionAnalysis.getInstance().saveCrashInfo(context, th, false);
    }

    @Deprecated
    public static void setAppChannel(String str) {
        PrefOperate.setAppChannel(str);
    }

    public static void setAppKey(String str) {
        PrefOperate.setAppKey(str);
    }

    public static void setAppVersionName(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.length() > 256) {
            str = str.substring(0, 256);
        }
        CooperService.instance().setAppVersionName(context, str);
    }

    public static void setCrashExtraInfo(String str) {
        ExceptionAnalysis.getInstance().setCrashExtraInfo(str);
    }

    public static void setDebugOn(boolean z) {
        am.c().a(z);
    }

    public static void setEnableBackgroundSendLog(Context context, boolean z) {
        BDStatCore.instance().setAutoSendLog(context, z);
    }

    public static void setForTv(Context context, boolean z) {
        av.a().c(context, z);
        BDStatCore.instance().init(context);
    }

    public static synchronized void setGlobalExtraInfo(Context context, ExtraInfo extraInfo) {
        if (context == null) {
            return;
        }
        CooperService.instance().setHeaderExt(context, extraInfo);
        BDStatCore.instance().init(context);
    }

    public static void setLogSenderDelayed(int i) {
        LogSender.instance().setLogSenderDelayed(i);
    }

    public static void setOn(Context context, int i) {
        if (a(context, "setOn(...)") && !f3421a) {
            f3421a = true;
            if ((i & 1) != 0) {
                a(context, false);
            } else if ((i & 16) != 0) {
                a(context, true);
            }
            BDStatCore.instance().init(context);
        }
    }

    public static synchronized void setPushId(Context context, MtjConfig.PushPlatform pushPlatform, String str) {
        if (context == null) {
            return;
        }
        if (pushPlatform == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (str.length() > 1024) {
            str = str.substring(0, 1024);
        }
        CooperService.instance().setPushId(context, pushPlatform.value(), pushPlatform.showName(), str);
        BDStatCore.instance().init(context);
    }

    @Deprecated
    public static void setSendLogStrategy(Context context, SendStrategyEnum sendStrategyEnum, int i, boolean z) {
        if (a(context, "setSendLogStrategy(...)")) {
            boolean zA = bc.a((Class<?>) Application.class, "onCreate");
            if (zA) {
                am.c().c("[WARNING] setSendLogStrategy 方法被 Application.onCreate()调用，not a good practice; 可能由于多进程反复重启等原因造成Application.onCreate() 方法多次被执行，导致启动次数高；建议埋点在统计路径触发的第一个页面中，比如APP主页面中");
            }
            BDStatCore.instance().onSessionStart(context, zA);
            LogSender.instance().setSendLogStrategy(context.getApplicationContext(), sendStrategyEnum, i, z);
        }
    }

    public static void setSessionTimeOut(int i) {
        BDStatCore.instance().setSessionTimeOut(i);
    }

    public static synchronized void setStartType(boolean z) {
        CooperService.instance().setStartType(z);
    }

    public static void setUserId(Context context, String str) {
        if (context == null) {
            return;
        }
        CooperService.instance().setUserId(context, str);
        BDStatCore.instance().init(context);
    }

    public static void setUserProperty(Context context, Map<String, String> map) {
        if (context == null) {
            return;
        }
        CooperService.instance().setUserProperty(context, bc.a(map));
        BDStatCore.instance().init(context);
    }

    public static void start(Context context) {
        if (a(context, "start(...)")) {
            boolean zA = bc.a((Class<?>) Application.class, "onCreate");
            if (zA) {
                am.c().c("[WARNING] start 方法被 Application.onCreate()调用，not a good practice; 可能由于多进程反复重启等原因造成Application.onCreate() 方法多次被执行，导致启动次数高；建议埋点在统计路径触发的第一个页面中，比如APP主页面中");
            }
            BDStatCore.instance().onSessionStart(context, zA);
        }
    }

    public static void trackWebView(Context context, WebView webView, WebChromeClient webChromeClient) {
    }

    @SuppressLint({"NewApi", "SetJavaScriptEnabled"})
    public static void bindJSInterface(Context context, WebView webView, WebViewClient webViewClient) {
        a(context, webView, webViewClient, (WebChromeClient) null, false);
    }

    public static void onEvent(Context context, String str, String str2, int i) {
        a(context, str, str2, i, (ExtraInfo) null, (Map<String, String>) null);
    }

    public static void onEventDuration(Context context, String str, String str2, long j) {
        a(context, str, str2, j, (ExtraInfo) null, (Map<String, String>) null);
    }

    public static void onEventEnd(Context context, String str, String str2, Map<String, String> map) {
        a(context.getApplicationContext(), str, str2, (ExtraInfo) null, map);
    }

    public static void setAppChannel(Context context, String str, boolean z) {
        PrefOperate.setAppChannel(context, str, z);
        BDStatCore.instance().init(context);
    }

    public static void onEvent(Context context, String str, String str2) {
        a(context, str, str2, null);
    }

    public static synchronized void onPause(Activity activity, ExtraInfo extraInfo) {
        if (a(activity, "onPause(...)")) {
            if (!a((Class<?>) Activity.class, "onPause")) {
                am.c().c("[WARNING] onPause must be called in Activity.onPause");
            } else {
                BDStatCore.instance().onPause(activity, false, extraInfo);
            }
        }
    }

    @Deprecated
    public static void setSendLogStrategy(Context context, SendStrategyEnum sendStrategyEnum, int i) {
        setSendLogStrategy(context, sendStrategyEnum, i, false);
    }

    private static synchronized void a(Context context, String str, ExtraInfo extraInfo) {
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                BDStatCore.instance().onPageEnd(context, str, extraInfo);
                return;
            }
        }
        am.c().c("[WARNING] onPageEnd parameter invalid");
    }

    private static void a(Context context, boolean z) {
        if (a(context, "onError(...)")) {
            BDStatCore.instance().init(context);
            ExceptionAnalysis.getInstance().openExceptionAnalysis(context.getApplicationContext(), z);
        }
    }

    private static void a(Context context, String str, String str2, int i, ExtraInfo extraInfo, Map<String, String> map) {
        if (a(context, "onEvent(...)") && !TextUtils.isEmpty(str)) {
            boolean zA = bc.a((Class<?>) Application.class, "onCreate");
            if (zA) {
                am.c().c("[WARNING] onEvent 方法被 Application.onCreate()调用，not a good practice; 可能由于多进程反复重启等原因造成Application.onCreate() 方法多次被执行，导致启动次数高；建议埋点在统计路径触发的第一个页面中，比如APP主页面中");
            }
            BDStatCore.instance().onEvent(context.getApplicationContext(), str, str2, i, extraInfo, bc.a(map), zA);
        }
    }

    private static void a(Context context, String str, String str2, ExtraInfo extraInfo) {
        a(context, str, str2, 1, extraInfo, (Map<String, String>) null);
    }

    private static void a(Context context, String str, String str2, ExtraInfo extraInfo, Map<String, String> map) {
        if (a(context, "onEventEnd(...)") && !TextUtils.isEmpty(str)) {
            BDStatCore.instance().onEventEnd(context.getApplicationContext(), str, str2, extraInfo, bc.a(map));
        }
    }

    private static void a(Context context, String str, String str2, long j, ExtraInfo extraInfo, Map<String, String> map) {
        if (a(context, "onEventDuration(...)") && !TextUtils.isEmpty(str)) {
            if (j <= 0) {
                am.c().b("[WARNING] onEventDuration duration must be greater than zero");
                return;
            }
            boolean zA = bc.a((Class<?>) Application.class, "onCreate");
            if (zA) {
                am.c().c("[WARNING] onEventDuration 方法被 Application.onCreate()调用，not a good practice; 可能由于多进程反复重启等原因造成Application.onCreate() 方法多次被执行，导致启动次数高；建议埋点在统计路径触发的第一个页面中，比如APP主页面中");
            }
            BDStatCore.instance().onEventDuration(context.getApplicationContext(), str, str2, j, extraInfo, bc.a(map), zA);
        }
    }

    private static boolean a(Context context, String str) {
        if (context != null) {
            return true;
        }
        am.c().b("[WARNING] " + str + ", context is null, invalid");
        return false;
    }

    @SuppressLint({"NewApi"})
    private static void a(WebView webView) {
        int i = android.os.Build.VERSION.SDK_INT;
        if (i < 11 || i > 18) {
            return;
        }
        webView.removeJavascriptInterface("searchBoxJavaBridge_");
        webView.removeJavascriptInterface("accessibility");
        webView.removeJavascriptInterface("accessibilityTraversal");
    }

    @SuppressLint({"NewApi", "SetJavaScriptEnabled"})
    private static void a(Context context, WebView webView, WebViewClient webViewClient, WebChromeClient webChromeClient, boolean z) {
        if (context == null) {
            am.c().c("[WARNING] context is null, invalid");
            return;
        }
        if (webView == null) {
            am.c().c("[WARNING] webview is null, invalid");
            return;
        }
        a(webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (!z) {
            webView.setWebViewClient(new BaiduStatJSInterface.CustomWebViewClient(context, webViewClient, null, null));
        } else {
            aq aqVar = new aq();
            webView.addJavascriptInterface(aqVar, "WebViewInterface");
            WebChromeClient customWebChromeViewClient = new BaiduStatJSInterface.CustomWebChromeViewClient(context, webChromeClient, new ArrayList(), aqVar);
            webView.setWebChromeClient(customWebChromeViewClient);
            webView.setTag(-96001, customWebChromeViewClient);
        }
        BDStatCore.instance().init(context);
    }
}
