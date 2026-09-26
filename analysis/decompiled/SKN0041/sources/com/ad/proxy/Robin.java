package com.ad.proxy;

import android.content.Context;
import com.ad.proxy.a.A;
import com.ad.proxy.b.C;
import com.ad.proxy.b.D;
import com.ad.proxy.b.a0;
import com.ad.proxy.b.c0;
import com.ad.proxy.g.B;
import com.ad.proxy.g.F;
import com.ad.proxy.g.H;
import com.ad.proxy.g.I;
import com.ad.proxy.g.J;
import com.ad.proxy.g.K;
import com.ad.proxy.g.L;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/* JADX INFO: loaded from: classes.dex */
public class Robin {
    private static final String TAG = "Robin";
    private static C activeReporter = null;
    public static String appKey = null;
    public static String channel = null;
    private static boolean isDebug = false;
    private static boolean isProd = true;
    private static boolean isStarted = false;
    private static Context mContext = null;
    public static String version = "1";
    private static List<StatusListener> statusListeners = new ArrayList();
    private static boolean isForceWifi = true;
    private static H mNetworkStateListener = new A();

    public static Boolean hasStarted() {
        return Boolean.valueOf(isStarted);
    }

    private static void init() {
        if (!B.a()) {
            initFromJava();
        }
        try {
            K k = new K();
            TrustManager[] trustManagerArr = {new L()};
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            sSLContext.init(null, trustManagerArr, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sSLContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(k);
        } catch (Exception e) {
            F.a(TAG, e);
        }
        String str = "deviceId = " + com.ad.proxy.g.A.a();
        boolean z = F.a;
        if (isDebug()) {
            F.a("LogUtil", str);
        }
    }

    private static void initFromAndroid(Context context) {
        mContext = context.getApplicationContext();
        boolean z = J.a;
        J.b = context.getApplicationContext().getSharedPreferences("app_prefs", 0);
    }

    private static void initFromJava() {
        boolean z = J.a;
        J.c = Preferences.userRoot().node("app_prefs");
    }

    public static boolean isDebug() {
        return isDebug;
    }

    public static boolean isProd() {
        return isProd;
    }

    public static void notifyStatusChanged(Status status) {
        Iterator<StatusListener> it = statusListeners.iterator();
        while (it.hasNext()) {
            it.next().onClientStatusChanged(String.valueOf(status.ordinal()), status.getValue());
        }
    }

    public static void registerListener(StatusListener statusListener) {
        statusListeners.add(statusListener);
    }

    public static void setDebug(boolean z) {
        isDebug = z;
    }

    public static void setIsForceWifi(boolean z) {
        isForceWifi = z;
        if (isStarted) {
            if (!z) {
                startActiveReporter();
                return;
            }
            Context context = mContext;
            if (context == null) {
                startActiveReporter();
            } else if (I.a(context)) {
                startActiveReporter();
            } else {
                stopActiveReporter();
            }
        }
    }

    public static void setProd(boolean z) {
        isProd = z;
    }

    public static void start(String str, String str2) {
        if (isStarted) {
            return;
        }
        channel = str;
        appKey = str2;
        isStarted = true;
        init();
        F.a(TAG, "Robin has started!");
        Context context = mContext;
        if (context == null) {
            startActiveReporter();
            return;
        }
        if (!isForceWifi || I.a(context)) {
            startActiveReporter();
        } else {
            F.a(TAG, "WIFI not connected");
        }
        I.a(mContext, mNetworkStateListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startActiveReporter() {
        if (activeReporter != null) {
            return;
        }
        activeReporter = new C();
        notifyStatusChanged(Status.STARTING);
        C c = activeReporter;
        c.b = c.a.scheduleWithFixedDelay(new com.ad.proxy.b.B(c), 0L, C.c, TimeUnit.SECONDS);
        notifyStatusChanged(Status.STARTED);
    }

    public static void startFromAndroid(Context context, String str) {
        initFromAndroid(context);
        String[] strArrSplit = str.split("-");
        start(strArrSplit[0], strArrSplit[1]);
    }

    public static void stop() {
        notifyStatusChanged(Status.STOPPING);
        isStarted = false;
        stopActiveReporter();
        notifyStatusChanged(Status.STOPPED);
        Context context = mContext;
        if (context != null) {
            I.b(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void stopActiveReporter() {
        C c = activeReporter;
        if (c != null) {
            ScheduledFuture scheduledFuture = c.b;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            activeReporter = null;
        }
        for (a0 a0Var : c0.a().a.values()) {
            a0Var.getClass();
            F.a("SocketClient", "Closing socket");
            a0Var.h = false;
            Socket socket = a0Var.a;
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    F.a("E", e);
                }
            }
            try {
                a0Var.e.shutdownNow();
            } catch (Exception e2) {
                F.a("SocketClient", e2);
            }
        }
        c0.a().a.clear();
        Iterator it = com.ad.proxy.b.J.a().a.values().iterator();
        while (it.hasNext()) {
            ((D) it.next()).a();
        }
        com.ad.proxy.b.J jA = com.ad.proxy.b.J.a();
        jA.a.clear();
        synchronized (jA.c) {
            jA.b.clear();
        }
    }

    public static void unregisterListener(StatusListener statusListener) {
        statusListeners.remove(statusListener);
    }

    public static void startFromAndroid(Context context, String str, String str2) {
        initFromAndroid(context);
        start(str, str2);
    }
}
