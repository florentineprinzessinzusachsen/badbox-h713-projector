package com.hs.p.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.cloudmedia.tv.server.a;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class DeviceUtils {
    private static String byte2String(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr != null) {
            for (byte b2 : bArr) {
                sb.append(String.format("%02x", Integer.valueOf(b2 & 255)));
            }
        }
        return sb.toString();
    }

    public static String getAndroidId(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String getBrand() {
        return Build.BRAND;
    }

    @SuppressLint({"MissingPermission"})
    private static String getDeviceId(TelephonyManager telephonyManager) {
        if (telephonyManager == null) {
            return "";
        }
        try {
            return telephonyManager.getDeviceId();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String getImei(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return "";
            }
            String strInvoke = invoke(telephonyManager, "getImei", 0);
            if (isValid(strInvoke)) {
                return strInvoke;
            }
            String strInvoke2 = invoke(telephonyManager, "getImei", 1);
            if (isValid(strInvoke2)) {
                return strInvoke2;
            }
            String strInvoke3 = invoke(telephonyManager, "getDeviceId", 0);
            if (isValid(strInvoke3)) {
                return strInvoke3;
            }
            String strInvoke4 = invoke(telephonyManager, "getDeviceId", 1);
            if (isValid(strInvoke4)) {
                return strInvoke4;
            }
            String deviceId = getDeviceId(telephonyManager);
            return isValid(deviceId) ? deviceId : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static Set<String> getImeis(Context context) {
        HashSet hashSet = new HashSet();
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                String strInvoke = invoke(telephonyManager, "getImei", 0);
                if (isValid(strInvoke)) {
                    hashSet.add(strInvoke);
                }
                String strInvoke2 = invoke(telephonyManager, "getImei", 1);
                if (isValid(strInvoke2)) {
                    hashSet.add(strInvoke2);
                }
                String strInvoke3 = invoke(telephonyManager, "getDeviceId", 0);
                if (isValid(strInvoke3)) {
                    hashSet.add(strInvoke3);
                }
                String strInvoke4 = invoke(telephonyManager, "getDeviceId", 1);
                if (isValid(strInvoke4)) {
                    hashSet.add(strInvoke4);
                }
                String deviceId = getDeviceId(telephonyManager);
                if (isValid(deviceId)) {
                    hashSet.add(deviceId);
                }
            }
        } catch (Throwable unused) {
        }
        return hashSet;
    }

    public static String getMACAddress(Context context) {
        WifiInfo connectionInfo;
        byte[] hardwareAddress;
        try {
            File file = new File("/sys/class/net/eth0/address");
            if (file.exists()) {
                String upperCase = loadFileAsString(file).trim().toUpperCase();
                if (upperCase.length() >= 17) {
                    String strSubstring = upperCase.substring(0, 17);
                    if (isValidMacAddress(strSubstring)) {
                        return strSubstring.replaceAll(":", "");
                    }
                }
            }
        } catch (IOException unused) {
        }
        try {
            File file2 = new File("/sys/class/net/wlan0/address");
            if (file2.exists()) {
                String upperCase2 = loadFileAsString(file2).trim().toUpperCase();
                if (upperCase2.length() >= 17) {
                    String strSubstring2 = upperCase2.substring(0, 17);
                    if (isValidMacAddress(strSubstring2)) {
                        return strSubstring2.replaceAll(":", "");
                    }
                }
            }
        } catch (IOException unused2) {
        }
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if ("wlan0".equalsIgnoreCase(networkInterface.getName()) && (hardwareAddress = networkInterface.getHardwareAddress()) != null) {
                    String strByte2String = byte2String(hardwareAddress);
                    StringBuilder sb = new StringBuilder();
                    int i = 0;
                    while (i < strByte2String.length()) {
                        if (sb.length() > 0) {
                            sb.append(":");
                        }
                        int i2 = i + 2;
                        sb.append(strByte2String.substring(i, i2).toUpperCase());
                        i = i2;
                    }
                    if (isValidMacAddress(sb.toString())) {
                        return strByte2String;
                    }
                }
            }
        } catch (Exception unused3) {
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager != null && (connectionInfo = wifiManager.getConnectionInfo()) != null) {
                String macAddress = connectionInfo.getMacAddress();
                if (isValidMacAddress(macAddress)) {
                    return macAddress.toUpperCase().replaceAll(":", "");
                }
            }
        } catch (Exception unused4) {
        }
        return "";
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getModel() {
        String lowerCase = toLowerCase(getPropertyString("ro.x.product.model", ""));
        if (isEmpty(lowerCase)) {
            return Build.MODEL;
        }
        return Build.MODEL + "_" + lowerCase;
    }

    private static String getPropertyString(String str, String str2) {
        try {
            Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class);
            declaredMethod.setAccessible(true);
            return (String) declaredMethod.invoke(null, str);
        } catch (Throwable unused) {
            return str2;
        }
    }

    private static String getSerial() {
        return Build.SERIAL;
    }

    private static String invoke(TelephonyManager telephonyManager, String str, int i) {
        if (telephonyManager == null) {
            return "";
        }
        try {
            return (String) telephonyManager.getClass().getMethod(str, Integer.TYPE).invoke(telephonyManager, Integer.valueOf(i));
        } catch (Throwable unused) {
            return "";
        }
    }

    private static boolean isEmpty(String str) {
        return str == null || str.length() <= 0;
    }

    private static boolean isValid(String str) {
        return (isEmpty(str) || str.matches("[0]+")) ? false : true;
    }

    private static boolean isValidMacAddress(String str) {
        if (isEmpty(str) || Arrays.asList("02:00:00:00:00:00", "00:00:00:00:00:00", "FF:FF:FF:FF:FF:FF").contains(str.toUpperCase())) {
            return false;
        }
        return str.matches("([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})");
    }

    private static String loadFileAsString(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        try {
            char[] cArr = new char[a.l.s];
            while (true) {
                int i = bufferedReader.read(cArr);
                if (i == -1) {
                    bufferedReader.close();
                    return sb.toString();
                }
                sb.append(cArr, 0, i);
            }
        } catch (Throwable th) {
            bufferedReader.close();
            throw th;
        }
    }

    private static String replace(String str, String str2, String str3) {
        return !isEmpty(str) ? str.replaceAll(str2, str3) : str;
    }

    private static String toLowerCase(String str) {
        return str != null ? str.toLowerCase(Locale.getDefault()) : "";
    }
}
