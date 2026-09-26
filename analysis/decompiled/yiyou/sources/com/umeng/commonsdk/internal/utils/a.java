package com.umeng.commonsdk.internal.utils;

import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: ApplicationLayerUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: com.umeng.commonsdk.internal.utils.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ApplicationLayerUtil.java */
    public static class C0086a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f3813a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f3814b;
    }

    /* JADX INFO: compiled from: ApplicationLayerUtil.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f3815a = null;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3816b = -1;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f3817c = null;
    }

    /* JADX INFO: compiled from: ApplicationLayerUtil.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3818a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f3819b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f3820c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3821d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f3822e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f3823f;
        public int g;
        public String h;
        public int i;
        public int j;
        public int k;
        public long l;
    }

    public static long a(Context context, String str) {
        if (context == null) {
            return 0L;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0).firstInstallTime;
        } catch (PackageManager.NameNotFoundException e2) {
            UMCrashManager.reportCrash(context, e2);
            ULog.e("getAppFirstInstallTime" + e2.getMessage());
            return 0L;
        }
    }

    public static long b(Context context, String str) {
        if (context == null) {
            return 0L;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0).lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e2) {
            UMCrashManager.reportCrash(context, e2);
            ULog.e("getAppLastUpdateTime:" + e2.getMessage());
            return 0L;
        }
    }

    public static String c(Context context, String str) {
        try {
            return context.getPackageManager().getInstallerPackageName(str);
        } catch (Exception e2) {
            UMCrashManager.reportCrash(context, e2);
            ULog.e("getAppInstaller:" + e2.getMessage());
            return null;
        }
    }

    public static int d(Context context, String str) {
        if (context == null) {
            return 0;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getPackageInfo(str, 0).applicationInfo;
            if (applicationInfo != null) {
                return applicationInfo.uid;
            }
            return 0;
        } catch (PackageManager.NameNotFoundException e2) {
            UMCrashManager.reportCrash(context, e2);
            ULog.e("getAppUid:" + e2.getMessage());
            return 0;
        }
    }

    public static JSONArray e(Context context) {
        if (context == null) {
            return null;
        }
        return f.b(context);
    }

    public static void f(Context context) {
        if (context == null) {
            return;
        }
        f.c(context);
    }

    public static int g(Context context) {
        WifiManager wifiManager;
        if (context == null || !DeviceConfig.checkPermission(context, "android.permission.ACCESS_WIFI_STATE") || (wifiManager = (WifiManager) context.getSystemService("wifi")) == null) {
            return -1;
        }
        return wifiManager.getWifiState();
    }

    public static int h(Context context) {
        if (context == null) {
            return 0;
        }
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }

    public static int i(Context context) {
        if (context == null) {
            return 0;
        }
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
    }

    public static DisplayMetrics j(Context context) {
        if (context == null) {
            return null;
        }
        return context.getResources().getDisplayMetrics();
    }

    public static String k(Context context) {
        TelephonyManager telephonyManager;
        if (context == null || !DeviceConfig.checkPermission(context, "android.permission.READ_PHONE_STATE") || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
            return null;
        }
        return telephonyManager.getSubscriberId();
    }

    public static String l(Context context) {
        TelephonyManager telephonyManager;
        if (context == null || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null || !DeviceConfig.checkPermission(context, "android.permission.READ_PHONE_STATE") || telephonyManager == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 26) {
            return telephonyManager.getDeviceId();
        }
        try {
            String strT = t(context);
            try {
                if (TextUtils.isEmpty(strT)) {
                    strT = telephonyManager.getDeviceId();
                }
            } catch (Throwable unused) {
            }
            return strT;
        } catch (Throwable unused2) {
            return null;
        }
    }

    public static List<InputMethodInfo> m(Context context) {
        InputMethodManager inputMethodManager;
        if (context == null || (inputMethodManager = (InputMethodManager) context.getSystemService("input_method")) == null) {
            return null;
        }
        return inputMethodManager.getInputMethodList();
    }

    public static void n(Context context) {
        if (context == null) {
            return;
        }
        try {
            if (DeviceConfig.checkPermission(context, "android.permission.BLUETOOTH")) {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                b bVar = new b();
                if (defaultAdapter.isEnabled()) {
                    bVar.f3816b = defaultAdapter.getState();
                    if (Build.VERSION.SDK_INT < 23) {
                        bVar.f3815a = defaultAdapter.getAddress();
                    } else {
                        bVar.f3815a = a(defaultAdapter);
                    }
                    bVar.f3817c = defaultAdapter.getName();
                    UMWorkDispatch.sendEvent(context, com.umeng.commonsdk.internal.a.i, com.umeng.commonsdk.internal.b.a(context).a(), bVar);
                }
            }
        } catch (Exception unused) {
        }
    }

    public static JSONObject o(Context context) {
        if (context == null) {
            return null;
        }
        return f.a(context);
    }

    public static List<C0086a> p(Context context) {
        String[] list;
        if (context == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            File file = new File(Environment.getExternalStorageDirectory() + "/Android/data/");
            if (file.isDirectory() && (list = file.list()) != null && list.length > 0) {
                for (String str : list) {
                    if (str != null && !str.startsWith(".")) {
                        C0086a c0086a = new C0086a();
                        c0086a.f3813a = str;
                        c0086a.f3814b = e(context, str);
                        arrayList.add(c0086a);
                    }
                }
            }
        } catch (Exception e2) {
            ULog.e("getAppList:" + e2.getMessage());
        }
        return arrayList;
    }

    public static ActivityManager.MemoryInfo q(Context context) {
        ActivityManager activityManager;
        if (context == null || (activityManager = (ActivityManager) context.getSystemService("activity")) == null) {
            return null;
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo;
    }

    public static String r(Context context) {
        if (context == null) {
        }
        return null;
    }

    public static String s(Context context) {
        return null;
    }

    private static String t(Context context) {
        if (context == null) {
            return null;
        }
        try {
            Object objInvoke = Class.forName("android.telephony.TelephonyManager").getMethod("getMeid", new Class[0]).invoke(null, new Object[0]);
            if (objInvoke == null || !(objInvoke instanceof String)) {
                return null;
            }
            return (String) objInvoke;
        } catch (Exception e2) {
            ULog.e("meid:" + e2.getMessage());
            return null;
        }
    }

    public static String e() {
        return g.a("df");
    }

    public static long f() {
        return System.currentTimeMillis() - SystemClock.elapsedRealtime();
    }

    private static boolean j() {
        String externalStorageState = Environment.getExternalStorageState();
        return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
    }

    private static String e(Context context, String str) {
        if (context == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
            if (applicationInfo != null) {
                return (String) applicationInfo.loadLabel(context.getPackageManager());
            }
            return null;
        } catch (Exception e2) {
            ULog.e("getLabel:" + e2.getMessage());
            return null;
        }
    }

    public static WifiInfo c(Context context) {
        WifiManager wifiManager;
        if (context == null || !DeviceConfig.checkPermission(context, "android.permission.ACCESS_WIFI_STATE") || (wifiManager = (WifiManager) context.getSystemService("wifi")) == null) {
            return null;
        }
        return wifiManager.getConnectionInfo();
    }

    public static String g() {
        try {
            Method declaredMethod = Build.class.getDeclaredMethod("getString", String.class);
            declaredMethod.setAccessible(true);
            String string = declaredMethod.invoke(null, "net.hostname").toString();
            return (string == null || string.equalsIgnoreCase("")) ? string : HelperUtils.getUmengMD5(string);
        } catch (Exception e2) {
            ULog.e("getHostName:" + e2.getMessage());
            return null;
        }
    }

    public static long h() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static long i() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static boolean a() {
        return h.a();
    }

    public static String b() {
        return new SimpleDateFormat().format(new Date());
    }

    public static float a(Context context) {
        if (context == null) {
            return 0.0f;
        }
        Configuration configuration = new Configuration();
        try {
            configuration.updateFrom(context.getResources().getConfiguration());
            return configuration.fontScale;
        } catch (Exception e2) {
            ULog.e("getFontSize:" + e2.getMessage());
            return 0.0f;
        }
    }

    public static void d(Context context) {
        WifiInfo wifiInfoC;
        if (context == null || (wifiInfoC = c(context)) == null) {
            return;
        }
        c cVar = new c();
        cVar.f3818a = wifiInfoC.describeContents();
        cVar.f3819b = wifiInfoC.getBSSID();
        cVar.f3820c = wifiInfoC.getSSID();
        if (Build.VERSION.SDK_INT >= 21) {
            cVar.f3821d = wifiInfoC.getFrequency();
        } else {
            cVar.f3821d = -1;
        }
        boolean z = true;
        if (wifiInfoC.getHiddenSSID()) {
            cVar.f3822e = 1;
        } else {
            cVar.f3822e = 0;
        }
        cVar.f3823f = wifiInfoC.getIpAddress();
        cVar.g = wifiInfoC.getLinkSpeed();
        cVar.h = DeviceConfig.getMac(context);
        cVar.i = wifiInfoC.getNetworkId();
        cVar.j = wifiInfoC.getRssi();
        cVar.k = g(context);
        cVar.l = System.currentTimeMillis();
        if (wifiInfoC != null) {
            try {
                JSONArray jSONArrayB = f.b(context);
                if (jSONArrayB != null && jSONArrayB.length() > 0) {
                    int i = 0;
                    while (true) {
                        if (i >= jSONArrayB.length()) {
                            z = false;
                            break;
                        }
                        String strOptString = jSONArrayB.optJSONObject(i).optString("ssid", null);
                        if (strOptString != null && strOptString.equals(cVar.f3820c)) {
                            break;
                        } else {
                            i++;
                        }
                    }
                } else {
                    z = false;
                    break;
                }
                if (z) {
                    return;
                }
                f.a(context, cVar);
            } catch (Exception e2) {
                ULog.e("wifiChange:" + e2.getMessage());
            }
        }
    }

    public static List<ScanResult> b(Context context) {
        WifiManager wifiManager;
        List<ScanResult> scanResults = null;
        if (context == null || (wifiManager = (WifiManager) context.getSystemService("wifi")) == null) {
            return null;
        }
        if (!DeviceConfig.checkPermission(context, "android.permission.ACCESS_WIFI_STATE") || ((!DeviceConfig.checkPermission(context, "android.permission.ACCESS_COARSE_LOCATION") && !DeviceConfig.checkPermission(context, "android.permission.ACCESS_FINE_LOCATION")) || (scanResults = wifiManager.getScanResults()) == null || scanResults.size() != 0)) {
        }
        return scanResults;
    }

    public static long c() {
        if (!j() || Build.VERSION.SDK_INT < 9) {
            return 0L;
        }
        return Environment.getExternalStorageDirectory().getFreeSpace();
    }

    private static String a(BluetoothAdapter bluetoothAdapter) {
        if (bluetoothAdapter == null) {
            return null;
        }
        Class<?> cls = bluetoothAdapter.getClass();
        try {
            Class<?> cls2 = Class.forName("android.bluetooth.IBluetooth");
            Field declaredField = cls.getDeclaredField("mService");
            declaredField.setAccessible(true);
            Method method = cls2.getMethod("getAddress", new Class[0]);
            method.setAccessible(true);
            return (String) method.invoke(declaredField.get(bluetoothAdapter), new Object[0]);
        } catch (Exception unused) {
            return bluetoothAdapter.getAddress();
        }
    }

    public static long d() {
        if (!j() || Build.VERSION.SDK_INT < 9) {
            return 0L;
        }
        return Environment.getExternalStorageDirectory().getTotalSpace();
    }
}
