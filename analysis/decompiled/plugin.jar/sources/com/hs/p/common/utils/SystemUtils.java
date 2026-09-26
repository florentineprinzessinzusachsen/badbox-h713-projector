package com.hs.p.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.webkit.WebSettings;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class SystemUtils {
    private static String byte2String(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr != null) {
            for (byte b2 : bArr) {
                sb.append(String.format("%02x", Integer.valueOf(b2 & 255)));
            }
        }
        return sb.toString();
    }

    private static boolean empty(String str) {
        return str == null || str.length() <= 0;
    }

    public static List<String> getEnabledInstallApps(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
            if (!ObjUtils.empty(installedPackages)) {
                for (PackageInfo packageInfo : installedPackages) {
                    if (packageInfo.applicationInfo.enabled) {
                        arrayList.add(packageInfo.packageName);
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return arrayList;
    }

    public static String getIPAddress(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) {
                return "";
            }
            return 1 == activeNetworkInfo.getType() ? getWifiIp(context) : getMobileIp();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static List<String> getInstallApps(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
            if (!ObjUtils.empty(installedPackages)) {
                Iterator<PackageInfo> it = installedPackages.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().packageName);
                }
            }
        } catch (Throwable unused) {
        }
        return arrayList;
    }

    public static String getLang() {
        try {
            return readPropertyString("ro.product.locale.language", "");
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String getMACAddress(Context context) {
        NetworkInterface byName;
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager != null) {
                String macAddress = wifiManager.getConnectionInfo().getMacAddress();
                String strReplace = (macAddress == null || macAddress.equals("02:00:00:00:00:00")) ? "" : replace(macAddress, ":", "");
                return (valid(strReplace) || (byName = NetworkInterface.getByName("wlan0")) == null) ? strReplace : byte2String(byName.getHardwareAddress());
            }
        } catch (Throwable unused) {
        }
        return "";
    }

    private static String getMobileIp() throws SocketException {
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

    public static String getNetworkInfo(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) {
                return "";
            }
            return TextUtils.tidy(activeNetworkInfo.getTypeName() + "(" + activeNetworkInfo.getType() + ")");
        } catch (Throwable unused) {
            return "";
        }
    }

    public static int getOSApiInt() {
        return Build.VERSION.SDK_INT;
    }

    public static String getOSBuildDisplay() {
        String str = Build.DISPLAY;
        return empty(str) ? "" : str;
    }

    public static String getOSBuildIncremental() {
        return empty(Build.VERSION.INCREMENTAL) ? "" : Build.VERSION.INCREMENTAL;
    }

    public static String getOSRelease() {
        String str = Build.VERSION.RELEASE;
        return empty(str) ? "" : str;
    }

    public static String getSelfPackageName(Context context) {
        try {
            return context.getPackageName();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static int getSelfVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static String getSelfVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Throwable unused) {
            return "";
        }
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

    private static String getWifiIp(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        return wifiManager != null ? ip2s(wifiManager.getConnectionInfo().getIpAddress()) : "";
    }

    private static String ip2s(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    public static boolean isDevModeEnabled(Context context) {
        try {
            return Settings.Global.getInt(context.getContentResolver(), "adb_enabled", 0) > 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager;
        try {
            if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0 || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null) {
                return true;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
        } catch (Throwable unused) {
            return true;
        }
    }

    public static boolean isScreenOn(Context context) {
        try {
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            if (powerManager != null) {
                return Build.VERSION.SDK_INT >= 20 ? powerManager.isInteractive() : powerManager.isScreenOn();
            }
            return true;
        } catch (Throwable unused) {
            return true;
        }
    }

    public static boolean isSecurityMode(Context context) {
        return !(isDevModeEnabled(context) && isUsbConnected(context));
    }

    private static boolean isUsbConnected(Context context) {
        try {
            Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.hardware.usb.action.USB_STATE"));
            if (intentRegisterReceiver != null && intentRegisterReceiver.hasExtra("connected")) {
                return intentRegisterReceiver.getBooleanExtra("connected", false);
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public static boolean isWIFIAvailable(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            return connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType() == 1;
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

    private static String replace(String str, String str2, String str3) {
        return !empty(str) ? str.replaceAll(str2, str3) : str;
    }

    private static boolean valid(String str) {
        return (empty(str) || str.matches("[0]+")) ? false : true;
    }
}
