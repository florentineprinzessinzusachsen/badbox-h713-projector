package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkUtils {

    public static final class NetworkChangedReceiver extends BroadcastReceiver {
        private Set<OnNetworkStatusChangedListener> mListeners = new HashSet();
        private NetworkType mType;

        private static class LazyHolder {
            private static final NetworkChangedReceiver INSTANCE = new NetworkChangedReceiver();

            private LazyHolder() {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static NetworkChangedReceiver getInstance() {
            return LazyHolder.INSTANCE;
        }

        @Override // android.content.BroadcastReceiver
        @SuppressLint({"MissingPermission"})
        public void onReceive(Context context, Intent intent) {
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                Utils.runOnUiThreadDelayed(new Runnable() { // from class: com.blankj.utilcode.util.NetworkUtils.NetworkChangedReceiver.3
                    @Override // java.lang.Runnable
                    public void run() {
                        NetworkType networkType = NetworkUtils.getNetworkType();
                        if (NetworkChangedReceiver.this.mType == networkType) {
                            return;
                        }
                        LogUtils.e(networkType);
                        NetworkChangedReceiver.this.mType = networkType;
                        if (networkType == NetworkType.NETWORK_NO) {
                            Iterator it = NetworkChangedReceiver.this.mListeners.iterator();
                            while (it.hasNext()) {
                                ((OnNetworkStatusChangedListener) it.next()).onDisconnected();
                            }
                        } else {
                            Iterator it2 = NetworkChangedReceiver.this.mListeners.iterator();
                            while (it2.hasNext()) {
                                ((OnNetworkStatusChangedListener) it2.next()).onConnected(networkType);
                            }
                        }
                    }
                }, 1000L);
            }
        }

        void registerListener(final OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
            if (onNetworkStatusChangedListener == null) {
                return;
            }
            Utils.runOnUiThread(new Runnable() { // from class: com.blankj.utilcode.util.NetworkUtils.NetworkChangedReceiver.1
                @Override // java.lang.Runnable
                @SuppressLint({"MissingPermission"})
                public void run() {
                    int size = NetworkChangedReceiver.this.mListeners.size();
                    NetworkChangedReceiver.this.mListeners.add(onNetworkStatusChangedListener);
                    if (size == 0 && NetworkChangedReceiver.this.mListeners.size() == 1) {
                        NetworkChangedReceiver.this.mType = NetworkUtils.getNetworkType();
                        Utils.getApp().registerReceiver(NetworkChangedReceiver.getInstance(), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                    }
                }
            });
        }

        void unregisterListener(final OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
            if (onNetworkStatusChangedListener == null) {
                return;
            }
            Utils.runOnUiThread(new Runnable() { // from class: com.blankj.utilcode.util.NetworkUtils.NetworkChangedReceiver.2
                @Override // java.lang.Runnable
                public void run() {
                    int size = NetworkChangedReceiver.this.mListeners.size();
                    NetworkChangedReceiver.this.mListeners.remove(onNetworkStatusChangedListener);
                    if (size == 1 && NetworkChangedReceiver.this.mListeners.size() == 0) {
                        Utils.getApp().unregisterReceiver(NetworkChangedReceiver.getInstance());
                    }
                }
            });
        }
    }

    public enum NetworkType {
        NETWORK_ETHERNET,
        NETWORK_WIFI,
        NETWORK_4G,
        NETWORK_3G,
        NETWORK_2G,
        NETWORK_UNKNOWN,
        NETWORK_NO
    }

    public interface OnNetworkStatusChangedListener {
        void onConnected(NetworkType networkType);

        void onDisconnected();
    }

    private NetworkUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        return connectivityManager.getActiveNetworkInfo();
    }

    public static String getBroadcastIpAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            new LinkedList();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if (networkInterfaceNextElement.isUp() && !networkInterfaceNextElement.isLoopback()) {
                    List<InterfaceAddress> interfaceAddresses = networkInterfaceNextElement.getInterfaceAddresses();
                    int size = interfaceAddresses.size();
                    for (int i = 0; i < size; i++) {
                        InetAddress broadcast = interfaceAddresses.get(i).getBroadcast();
                        if (broadcast != null) {
                            return broadcast.getHostAddress();
                        }
                    }
                }
            }
            return "";
        } catch (SocketException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String getDomainAddress(String str) {
        try {
            return InetAddress.getByName(str).getHostAddress();
        } catch (UnknownHostException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static Utils.Task<String> getDomainAddressAsync(final String str, Utils.Callback<String> callback) {
        if (callback != null) {
            return Utils.doAsync(new Utils.Task<String>(callback) { // from class: com.blankj.utilcode.util.NetworkUtils.6
                @Override // com.blankj.utilcode.util.Utils.Task
                public String doInBackground() {
                    return NetworkUtils.getDomainAddress(str);
                }
            });
        }
        throw new NullPointerException("Argument 'callback' of type Utils.Callback<String> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static String getGatewayByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        return wifiManager == null ? "" : Formatter.formatIpAddress(wifiManager.getDhcpInfo().gateway);
    }

    public static String getIPAddress(boolean z) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            LinkedList<InetAddress> linkedList = new LinkedList();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if (networkInterfaceNextElement.isUp() && !networkInterfaceNextElement.isLoopback()) {
                    Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        linkedList.addFirst(inetAddresses.nextElement());
                    }
                }
            }
            for (InetAddress inetAddress : linkedList) {
                if (!inetAddress.isLoopbackAddress()) {
                    String hostAddress = inetAddress.getHostAddress();
                    boolean z2 = hostAddress.indexOf(58) < 0;
                    if (z) {
                        if (z2) {
                            return hostAddress;
                        }
                    } else if (!z2) {
                        int iIndexOf = hostAddress.indexOf(37);
                        return iIndexOf < 0 ? hostAddress.toUpperCase() : hostAddress.substring(0, iIndexOf).toUpperCase();
                    }
                }
            }
            return "";
        } catch (SocketException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static Utils.Task<String> getIPAddressAsync(final boolean z, Utils.Callback<String> callback) {
        if (callback != null) {
            return Utils.doAsync(new Utils.Task<String>(callback) { // from class: com.blankj.utilcode.util.NetworkUtils.5
                @Override // com.blankj.utilcode.util.Utils.Task
                public String doInBackground() {
                    return NetworkUtils.getIPAddress(z);
                }
            });
        }
        throw new NullPointerException("Argument 'callback' of type Utils.Callback<String> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static String getIpAddressByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        return wifiManager == null ? "" : Formatter.formatIpAddress(wifiManager.getDhcpInfo().ipAddress);
    }

    public static boolean getMobileDataEnabled() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) Utils.getApp().getSystemService("phone");
            if (telephonyManager == null) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 26) {
                return telephonyManager.isDataEnabled();
            }
            Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("getDataEnabled", new Class[0]);
            if (declaredMethod != null) {
                return ((Boolean) declaredMethod.invoke(telephonyManager, new Object[0])).booleanValue();
            }
            return false;
        } catch (Exception e2) {
            Log.e("NetworkUtils", "getMobileDataEnabled: ", e2);
        }
    }

    public static String getNetMaskByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        return wifiManager == null ? "" : Formatter.formatIpAddress(wifiManager.getDhcpInfo().netmask);
    }

    public static String getNetworkOperatorName() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.getApp().getSystemService("phone");
        return telephonyManager == null ? "" : telephonyManager.getNetworkOperatorName();
    }

    public static NetworkType getNetworkType() {
        if (isEthernet()) {
            return NetworkType.NETWORK_ETHERNET;
        }
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return NetworkType.NETWORK_NO;
        }
        if (activeNetworkInfo.getType() == 1) {
            return NetworkType.NETWORK_WIFI;
        }
        if (activeNetworkInfo.getType() != 0) {
            return NetworkType.NETWORK_UNKNOWN;
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return NetworkType.NETWORK_2G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return NetworkType.NETWORK_3G;
            case 13:
            case 18:
                return NetworkType.NETWORK_4G;
            default:
                String subtypeName = activeNetworkInfo.getSubtypeName();
                return (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) ? NetworkType.NETWORK_3G : NetworkType.NETWORK_UNKNOWN;
        }
    }

    public static String getServerAddressByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        return wifiManager == null ? "" : Formatter.formatIpAddress(wifiManager.getDhcpInfo().serverAddress);
    }

    public static boolean getWifiEnabled() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wifiManager == null) {
            return false;
        }
        return wifiManager.isWifiEnabled();
    }

    public static boolean is4G() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getSubtype() == 13;
    }

    public static boolean isAvailable() {
        return isAvailableByDns() || isAvailableByPing(null);
    }

    public static Utils.Task<Boolean> isAvailableAsync(Utils.Callback<Boolean> callback) {
        if (callback != null) {
            return Utils.doAsync(new Utils.Task<Boolean>(callback) { // from class: com.blankj.utilcode.util.NetworkUtils.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.blankj.utilcode.util.Utils.Task
                public Boolean doInBackground() {
                    return Boolean.valueOf(NetworkUtils.isAvailable());
                }
            });
        }
        throw new NullPointerException("Argument 'callback' of type Utils.Callback<Boolean> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean isAvailableByDns() {
        return isAvailableByDns("");
    }

    public static void isAvailableByDnsAsync(Utils.Callback<Boolean> callback) {
        isAvailableByDnsAsync("", callback);
    }

    public static boolean isAvailableByPing() {
        return isAvailableByPing("");
    }

    public static void isAvailableByPingAsync(Utils.Callback<Boolean> callback) {
        isAvailableByPingAsync("", callback);
    }

    public static boolean isConnected() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private static boolean isEthernet() {
        NetworkInfo networkInfo;
        NetworkInfo.State state;
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        if (connectivityManager == null || (networkInfo = connectivityManager.getNetworkInfo(9)) == null || (state = networkInfo.getState()) == null) {
            return false;
        }
        return state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING;
    }

    public static boolean isMobileData() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType() == 0;
    }

    public static boolean isWifiAvailable() {
        return getWifiEnabled() && isAvailable();
    }

    public static Utils.Task<Boolean> isWifiAvailableAsync(Utils.Callback<Boolean> callback) {
        if (callback != null) {
            return Utils.doAsync(new Utils.Task<Boolean>(callback) { // from class: com.blankj.utilcode.util.NetworkUtils.4
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.blankj.utilcode.util.Utils.Task
                public Boolean doInBackground() {
                    return Boolean.valueOf(NetworkUtils.isWifiAvailable());
                }
            });
        }
        throw new NullPointerException("Argument 'callback' of type Utils.Callback<Boolean> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean isWifiConnected() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || activeNetworkInfo.getType() != 1) ? false : true;
    }

    public static void openWirelessSettings() {
        Utils.getApp().startActivity(new Intent("android.settings.WIRELESS_SETTINGS").setFlags(268435456));
    }

    public static void registerNetworkStatusChangedListener(OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
        NetworkChangedReceiver.getInstance().registerListener(onNetworkStatusChangedListener);
    }

    public static void setWifiEnabled(boolean z) {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wifiManager == null || z == wifiManager.isWifiEnabled()) {
            return;
        }
        wifiManager.setWifiEnabled(z);
    }

    public static void unregisterNetworkStatusChangedListener(OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
        NetworkChangedReceiver.getInstance().unregisterListener(onNetworkStatusChangedListener);
    }

    public static boolean isAvailableByDns(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "www.baidu.com";
        }
        try {
            return InetAddress.getByName(str) != null;
        } catch (UnknownHostException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static Utils.Task isAvailableByDnsAsync(final String str, Utils.Callback<Boolean> callback) {
        if (callback != null) {
            return Utils.doAsync(new Utils.Task<Boolean>(callback) { // from class: com.blankj.utilcode.util.NetworkUtils.3
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.blankj.utilcode.util.Utils.Task
                public Boolean doInBackground() {
                    return Boolean.valueOf(NetworkUtils.isAvailableByDns(str));
                }
            });
        }
        throw new NullPointerException("Argument 'callback' of type Utils.Callback<Boolean> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean isAvailableByPing(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "223.5.5.5";
        }
        return ShellUtils.execCmd(String.format("ping -c 1 %s", str), false).result == 0;
    }

    public static Utils.Task<Boolean> isAvailableByPingAsync(final String str, Utils.Callback<Boolean> callback) {
        if (callback != null) {
            return Utils.doAsync(new Utils.Task<Boolean>(callback) { // from class: com.blankj.utilcode.util.NetworkUtils.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.blankj.utilcode.util.Utils.Task
                public Boolean doInBackground() {
                    return Boolean.valueOf(NetworkUtils.isAvailableByPing(str));
                }
            });
        }
        throw new NullPointerException("Argument 'callback' of type Utils.Callback<Boolean> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }
}
