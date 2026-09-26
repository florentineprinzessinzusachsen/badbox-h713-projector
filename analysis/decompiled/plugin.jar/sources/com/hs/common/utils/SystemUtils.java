package com.hs.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.PowerManager;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.WebSettings;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class SystemUtils {
    private static boolean empty(String str) {
        return str == null || str.length() <= 0;
    }

    public static String getAppName(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getApplicationLabel(context.getApplicationInfo()));
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String getCellId(Context context) {
        CellLocation cellLocation;
        try {
            if (hasPermission(context, "android.permission.ACCESS_FINE_LOCATION") || hasPermission(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (5 == telephonyManager.getSimState() && (cellLocation = telephonyManager.getCellLocation()) != null) {
                    if (cellLocation instanceof GsmCellLocation) {
                        return "" + ((GsmCellLocation) cellLocation).getCid();
                    }
                    if (cellLocation instanceof CdmaCellLocation) {
                        return "" + ((CdmaCellLocation) cellLocation).getBaseStationId();
                    }
                }
            }
        } catch (Exception unused) {
        }
        return "";
    }

    public static String getDefaultIMSI(Context context) {
        TelephonyManager telephonyManager;
        try {
            if (!hasPermission(context, "android.permission.READ_PHONE_STATE") || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
                return "";
            }
            String subscriberId = telephonyManager.getSubscriberId();
            return empty(subscriberId) ? "" : subscriberId;
        } catch (Exception unused) {
            return "";
        }
    }

    public static float getDensity(Context context) {
        try {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager == null) {
                return 0.0f;
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.density;
        } catch (Throwable unused) {
            return 0.0f;
        }
    }

    public static int getDensityDpi(Context context) {
        try {
            return context.getResources().getDisplayMetrics().densityDpi;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static Set<String> getEnabledApps(Context context) {
        HashSet hashSet = new HashSet();
        try {
            List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
            if (installedPackages != null && !installedPackages.isEmpty()) {
                for (PackageInfo packageInfo : installedPackages) {
                    if (packageInfo.applicationInfo.enabled) {
                        hashSet.add(packageInfo.packageName);
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return hashSet;
    }

    private static String getIPAddrOnMobile() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
            if (inetAddresses != null) {
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (inetAddressNextElement != null && !inetAddressNextElement.isLoopbackAddress() && (inetAddressNextElement instanceof Inet4Address)) {
                        return inetAddressNextElement.getHostAddress();
                    }
                }
            }
        }
        return "";
    }

    private static String getIPAddrOnWIFI(Context context) {
        return ip2str(((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getIpAddress());
    }

    public static String getIPAddress(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) {
                return "";
            }
            return 1 == activeNetworkInfo.getType() ? getIPAddrOnWIFI(context) : getIPAddrOnMobile();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static Set<String> getInstallApps(Context context) {
        HashSet hashSet = new HashSet();
        try {
            List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
            if (installedPackages != null && !installedPackages.isEmpty()) {
                Iterator<PackageInfo> it = installedPackages.iterator();
                while (it.hasNext()) {
                    hashSet.add(it.next().packageName);
                }
            }
        } catch (Throwable unused) {
        }
        return hashSet;
    }

    public static String getLang() {
        try {
            return readPropertyString("ro.product.locale.language", "");
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String getNetworkDescriptor(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return "type=" + activeNetworkInfo.getType() + "/" + activeNetworkInfo.getTypeName() + "; subtype=" + activeNetworkInfo.getSubtype() + "/" + activeNetworkInfo.getSubtypeName() + "; ext=" + activeNetworkInfo.getExtraInfo() + "; conn=" + activeNetworkInfo.isConnected() + "; available=" + activeNetworkInfo.isAvailable();
            }
        } catch (Throwable unused) {
        }
        return "";
    }

    public static String getNetworkInfo(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable()) {
                return TextUtils.tidy(activeNetworkInfo.getTypeName() + "(" + activeNetworkInfo.getType() + ")");
            }
        } catch (Throwable unused) {
        }
        return "";
    }

    public static int getOSDKInt() {
        return Build.VERSION.SDK_INT;
    }

    public static String getOSRelease() {
        String str = Build.VERSION.RELEASE;
        return empty(str) ? "" : str;
    }

    public static String getOSType() {
        return "android";
    }

    public static String getROMRelease() {
        return empty(Build.VERSION.INCREMENTAL) ? "" : Build.VERSION.INCREMENTAL;
    }

    public static Point getScreenXY(Context context) {
        Point point = new Point();
        try {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager != null) {
                windowManager.getDefaultDisplay().getSize(point);
            }
        } catch (Exception unused) {
        }
        return point;
    }

    @SuppressLint({"NewApi"})
    public static String getUserAgent(Context context) {
        try {
            if (Build.VERSION.SDK_INT < 17) {
                return System.getProperty("http.agent");
            }
            try {
                if (context.getApplicationInfo().uid > 1000) {
                    return WebSettings.getDefaultUserAgent(context);
                }
            } catch (Throwable unused) {
            }
            return System.getProperty("http.agent");
        } catch (Throwable unused2) {
            return "";
        }
    }

    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Throwable unused) {
            return "";
        }
    }

    private static boolean hasPermission(Context context, String str) {
        return Build.VERSION.SDK_INT <= 22 || context.checkSelfPermission(str) == 0;
    }

    private static String ip2str(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    public static boolean isNetworkAvailable(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isScreenOn(Context context) {
        try {
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            return Build.VERSION.SDK_INT >= 20 ? powerManager.isInteractive() : powerManager.isScreenOn();
        } catch (Throwable unused) {
            return true;
        }
    }

    public static boolean isWIFIAvailable(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType() == 1;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static String readPropertyString(String str, String str2) {
        try {
            Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class);
            declaredMethod.setAccessible(true);
            return (String) declaredMethod.invoke(null, str);
        } catch (Throwable unused) {
            return str2;
        }
    }

    public static int getVersionCode(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static String getVersionName(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (Throwable unused) {
            return "";
        }
    }
}
