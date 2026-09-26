package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceUtils {
    private static final String KEY_UDID = "KEY_UDID";
    private static volatile String udid;

    private DeviceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String[] getABIs() {
        if (Build.VERSION.SDK_INT >= 21) {
            return Build.SUPPORTED_ABIS;
        }
        return !TextUtils.isEmpty(Build.CPU_ABI2) ? new String[]{Build.CPU_ABI, Build.CPU_ABI2} : new String[]{Build.CPU_ABI};
    }

    @SuppressLint({"HardwareIds"})
    public static String getAndroidID() {
        String string = Settings.Secure.getString(Utils.getApp().getContentResolver(), "android_id");
        return ("9774d56d682e549c".equals(string) || string == null) ? "" : string;
    }

    private static InetAddress getInetAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if (networkInterfaceNextElement.isUp()) {
                    Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddressNextElement = inetAddresses.nextElement();
                        if (!inetAddressNextElement.isLoopbackAddress() && inetAddressNextElement.getHostAddress().indexOf(58) < 0) {
                            return inetAddressNextElement;
                        }
                    }
                }
            }
            return null;
        } catch (SocketException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getMacAddress() {
        String macAddress = getMacAddress(null);
        if (!macAddress.equals("") || getWifiEnabled()) {
            return macAddress;
        }
        setWifiEnabled(true);
        setWifiEnabled(false);
        return getMacAddress(null);
    }

    private static String getMacAddressByFile() {
        String str;
        String str2;
        ShellUtils.CommandResult commandResultExecCmd = ShellUtils.execCmd("getprop wifi.interface", false);
        if (commandResultExecCmd.result != 0 || (str = commandResultExecCmd.successMsg) == null) {
            return Config.DEF_MAC_ID;
        }
        ShellUtils.CommandResult commandResultExecCmd2 = ShellUtils.execCmd("cat /sys/class/net/" + str + "/address", false);
        return (commandResultExecCmd2.result != 0 || (str2 = commandResultExecCmd2.successMsg) == null || str2.length() <= 0) ? Config.DEF_MAC_ID : str2;
    }

    private static String getMacAddressByInetAddress() {
        NetworkInterface byInetAddress;
        byte[] hardwareAddress;
        try {
            InetAddress inetAddress = getInetAddress();
            if (inetAddress == null || (byInetAddress = NetworkInterface.getByInetAddress(inetAddress)) == null || (hardwareAddress = byInetAddress.getHardwareAddress()) == null || hardwareAddress.length <= 0) {
                return Config.DEF_MAC_ID;
            }
            StringBuilder sb = new StringBuilder();
            for (byte b2 : hardwareAddress) {
                sb.append(String.format("%02x:", Byte.valueOf(b2)));
            }
            return sb.substring(0, sb.length() - 1);
        } catch (Exception e2) {
            e2.printStackTrace();
            return Config.DEF_MAC_ID;
        }
    }

    private static String getMacAddressByNetworkInterface() {
        byte[] hardwareAddress;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if (networkInterfaceNextElement != null && networkInterfaceNextElement.getName().equalsIgnoreCase("wlan0") && (hardwareAddress = networkInterfaceNextElement.getHardwareAddress()) != null && hardwareAddress.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (byte b2 : hardwareAddress) {
                        sb.append(String.format("%02x:", Byte.valueOf(b2)));
                    }
                    return sb.substring(0, sb.length() - 1);
                }
            }
            return Config.DEF_MAC_ID;
        } catch (Exception e2) {
            e2.printStackTrace();
            return Config.DEF_MAC_ID;
        }
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    private static String getMacAddressByWifiInfo() {
        WifiInfo connectionInfo;
        try {
            WifiManager wifiManager = (WifiManager) Utils.getApp().getApplicationContext().getSystemService("wifi");
            return (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) ? Config.DEF_MAC_ID : connectionInfo.getMacAddress();
        } catch (Exception e2) {
            e2.printStackTrace();
            return Config.DEF_MAC_ID;
        }
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getModel() {
        String str = Build.MODEL;
        return str != null ? str.trim().replaceAll("\\s*", "") : "";
    }

    public static int getSDKVersionCode() {
        return Build.VERSION.SDK_INT;
    }

    public static String getSDKVersionName() {
        return Build.VERSION.RELEASE;
    }

    private static String getUdid(String str, String str2) {
        if (str2.equals("")) {
            return str + UUID.randomUUID().toString().replace("-", "");
        }
        return str + UUID.nameUUIDFromBytes(str2.getBytes()).toString().replace("-", "");
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getUniqueDeviceId() {
        return getUniqueDeviceId("");
    }

    private static boolean getWifiEnabled() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wifiManager == null) {
            return false;
        }
        return wifiManager.isWifiEnabled();
    }

    public static boolean isAdbEnabled() {
        return Settings.Secure.getInt(Utils.getApp().getContentResolver(), "adb_enabled", 0) > 0;
    }

    private static boolean isAddressNotInExcepts(String str, String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return !Config.DEF_MAC_ID.equals(str);
        }
        for (String str2 : strArr) {
            if (str.equals(str2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDeviceRooted() {
        for (String str : new String[]{"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/", "/system/sbin/", "/usr/bin/", "/vendor/bin/"}) {
            if (new File(str + com.android.umanalytics.utils.ShellUtils.COMMAND_SU).exists()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmulator() {
        String networkOperatorName;
        if (Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.toLowerCase().contains("vbox") || Build.FINGERPRINT.toLowerCase().contains("test-keys") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT)) {
            return true;
        }
        TelephonyManager telephonyManager = (TelephonyManager) Utils.getApp().getSystemService("phone");
        if (telephonyManager == null || (networkOperatorName = telephonyManager.getNetworkOperatorName()) == null) {
            networkOperatorName = "";
        }
        if (networkOperatorName.toLowerCase().equals("android")) {
            return true;
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse("tel:123456"));
        intent.setAction("android.intent.action.DIAL");
        return intent.resolveActivity(Utils.getApp().getPackageManager()) == null;
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static boolean isSameDevice(String str) {
        if (TextUtils.isEmpty(str) && str.length() < 33) {
            return false;
        }
        if (str.equals(udid) || str.equals(Utils.getSpUtils4Utils().getString(KEY_UDID, null))) {
            return true;
        }
        int length = str.length() - 33;
        int i = length + 1;
        String strSubstring = str.substring(length, i);
        if (strSubstring.startsWith("1")) {
            String macAddress = getMacAddress();
            if (macAddress.equals("")) {
                return false;
            }
            return str.substring(i).equals(getUdid("", macAddress));
        }
        if (strSubstring.startsWith("2")) {
            String androidID = getAndroidID();
            if (TextUtils.isEmpty(androidID)) {
                return false;
            }
            return str.substring(i).equals(getUdid("", androidID));
        }
        if (!strSubstring.startsWith("3")) {
            return false;
        }
        String deviceId = ((TelephonyManager) Utils.getApp().getSystemService("phone")).getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            return false;
        }
        return str.substring(i).equals(getUdid("", deviceId));
    }

    public static boolean isTablet() {
        return (Utils.getApp().getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    private static String saveUdid(String str, String str2) {
        udid = getUdid(str, str2);
        SPUtils.getInstance().put(KEY_UDID, udid);
        return udid;
    }

    private static void setWifiEnabled(boolean z) {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wifiManager == null) {
            return;
        }
        wifiManager.setWifiEnabled(z);
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getUniqueDeviceId(String str) {
        if (udid == null) {
            synchronized (DeviceUtils.class) {
                if (udid == null) {
                    String string = Utils.getSpUtils4Utils().getString(KEY_UDID, null);
                    if (string != null) {
                        udid = string;
                        return udid;
                    }
                    try {
                        String macAddress = getMacAddress();
                        if (!macAddress.equals("")) {
                            return saveUdid(str + 1, macAddress);
                        }
                        String androidID = getAndroidID();
                        if (!TextUtils.isEmpty(androidID)) {
                            return saveUdid(str + 2, androidID);
                        }
                        String deviceId = ((TelephonyManager) Utils.getApp().getSystemService("phone")).getDeviceId();
                        if (!TextUtils.isEmpty(deviceId)) {
                            return saveUdid(str + 3, deviceId);
                        }
                        return saveUdid(str + 4, "");
                    } catch (Exception unused) {
                    }
                }
            }
        }
        return udid;
    }

    public static String getMacAddress(String... strArr) {
        String macAddressByNetworkInterface = getMacAddressByNetworkInterface();
        if (isAddressNotInExcepts(macAddressByNetworkInterface, strArr)) {
            return macAddressByNetworkInterface;
        }
        String macAddressByInetAddress = getMacAddressByInetAddress();
        if (isAddressNotInExcepts(macAddressByInetAddress, strArr)) {
            return macAddressByInetAddress;
        }
        String macAddressByWifiInfo = getMacAddressByWifiInfo();
        if (isAddressNotInExcepts(macAddressByWifiInfo, strArr)) {
            return macAddressByWifiInfo;
        }
        String macAddressByFile = getMacAddressByFile();
        return isAddressNotInExcepts(macAddressByFile, strArr) ? macAddressByFile : "";
    }
}
