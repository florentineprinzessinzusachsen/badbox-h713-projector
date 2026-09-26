package com.hs.p.basic;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import com.hs.p.common.PROP;
import com.hs.p.common.utils.IoUtils;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class Porting {
    private static final String TAG = "Porting";

    private static int asInt(String str, int i) {
        try {
            if (!TextUtils.empty(str)) {
                return Integer.parseInt(str);
            }
        } catch (Throwable unused) {
        }
        return i;
    }

    private static boolean checkRootFile() {
        String[] strArr = {"/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su"};
        for (int i = 0; i < 8; i++) {
            try {
                if (new File(strArr[i]).exists()) {
                    return true;
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    private static boolean checkSuFile() {
        Process processExec;
        BufferedReader bufferedReader = null;
        try {
            processExec = Runtime.getRuntime().exec(new String[]{"which", "su"});
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
                try {
                    boolean z = bufferedReader2.readLine() != null;
                    IoUtils.close(bufferedReader2);
                    destroy(processExec);
                    return z;
                } catch (Throwable unused) {
                    bufferedReader = bufferedReader2;
                    IoUtils.close(bufferedReader);
                    destroy(processExec);
                    return false;
                }
            } catch (Throwable unused2) {
            }
        } catch (Throwable unused3) {
            processExec = null;
        }
    }

    private static void destroy(Process process) {
        try {
            if (process != null) {
                try {
                    process.exitValue();
                } catch (IllegalThreadStateException unused) {
                    process.destroy();
                    process.waitFor();
                }
            }
        } catch (Throwable unused2) {
        }
    }

    private static boolean getPropertyBoolean(String str, boolean z) {
        try {
            Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("getBoolean", String.class, Boolean.TYPE);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(null, str, Boolean.valueOf(z))).booleanValue();
        } catch (Throwable unused) {
            return z;
        }
    }

    public static boolean isAdbEnabled(Context context) {
        try {
            return android.provider.Settings.Global.getInt(context.getContentResolver(), "adb_enabled", 0) > 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isCTAEnabled() {
        try {
            Class<?> cls = Class.forName("android.os.BuildExt");
            Field declaredField = cls.getDeclaredField("IS_CTA");
            declaredField.setAccessible(true);
            return ((Boolean) declaredField.get(cls)).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isCTSEnabled() {
        try {
            return getPropertyBoolean("persist.sys.cts_state", false);
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isDevUserType() {
        String property = PROP.readProperty("ro.build.type", "eng");
        boolean z = !PROP.empty(property) && property.equals("user");
        LOG.i(TAG, "The device type is := " + z);
        return z;
    }

    public static boolean isDeviceRoot() {
        return checkSuFile() || checkRootFile();
    }

    public static boolean isProxyEnabled() {
        try {
            return !TextUtils.empty(System.getProperty("http.proxyHost")) && asInt(System.getProperty("http.proxyPort"), -1) > 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isUsbConnected(Context context) {
        Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentRegisterReceiver == null) {
            return false;
        }
        int intExtra = intentRegisterReceiver.getIntExtra("status", -1);
        return (intExtra == 2 || intExtra == 5) && (intentRegisterReceiver.getIntExtra("plugged", -1) == 2);
    }

    public static boolean isVpnEnabled() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces == null) {
                return false;
            }
            for (NetworkInterface networkInterface : Collections.list(networkInterfaces)) {
                if (networkInterface.isUp() && networkInterface.getInterfaceAddresses().size() != 0 && (TextUtils.equalsIgnoreCase("tun0", networkInterface.getName()) || TextUtils.equalsIgnoreCase("ppp0", networkInterface.getName()))) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isWifiAdbEnabled(Context context) {
        try {
            long j = Long.parseLong(PROP.readProperty("service.adb.tcp.port", "0"));
            if (Build.VERSION.SDK_INT >= 30) {
                if (android.provider.Settings.Global.getInt(context.getContentResolver(), "adb_wifi_enabled", 0) <= 0 && j <= 0) {
                    return false;
                }
            } else if (j <= 0) {
                return false;
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
