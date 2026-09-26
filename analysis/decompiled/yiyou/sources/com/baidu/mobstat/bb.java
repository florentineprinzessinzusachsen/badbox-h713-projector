package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Process;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class bb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f3483a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f3484b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static String f3485c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final Pattern f3486d = Pattern.compile("\\s*|\t|\r|\n");

    public static String a(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null) {
                return "";
            }
            Object obj = applicationInfo.metaData != null ? applicationInfo.metaData.get(str) : null;
            if (obj != null) {
                return obj.toString();
            }
            am.c().a("can't find information in AndroidManifest.xml for key " + str);
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b(Context context) {
        return ay.a.a(a(context).getBytes()).toUpperCase(Locale.US);
    }

    public static int c(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            displayMetrics = e(context);
        } catch (Exception unused) {
        }
        return displayMetrics.widthPixels;
    }

    public static int d(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            displayMetrics = e(context);
        } catch (Exception unused) {
        }
        return displayMetrics.heightPixels;
    }

    public static DisplayMetrics e(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static int f(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception unused) {
            return 1;
        }
    }

    public static String g(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String h(Context context) {
        CellLocation cellLocation;
        String str = String.format("%s_%s_%s", 0, 0, 0);
        try {
            if ((!at.e(context, "android.permission.ACCESS_FINE_LOCATION") && !at.e(context, "android.permission.ACCESS_COARSE_LOCATION")) || (cellLocation = ((TelephonyManager) context.getSystemService("phone")).getCellLocation()) == null) {
                return str;
            }
            if (cellLocation instanceof GsmCellLocation) {
                GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                return String.format("%s_%s_%s", String.format("%d", Integer.valueOf(gsmCellLocation.getCid())), String.format("%d", Integer.valueOf(gsmCellLocation.getLac())), 0);
            }
            String[] strArrSplit = cellLocation.toString().replace("[", "").replace("]", "").split(",");
            return String.format("%s_%s_%s", strArrSplit[0], strArrSplit[3], strArrSplit[4]);
        } catch (Exception unused) {
        }
        return str;
    }

    public static String i(Context context) {
        Location lastKnownLocation;
        try {
            return (!at.e(context, "android.permission.ACCESS_FINE_LOCATION") || (lastKnownLocation = ((LocationManager) context.getSystemService("location")).getLastKnownLocation("gps")) == null) ? "" : String.format("%s_%s_%s", Long.valueOf(lastKnownLocation.getTime()), Double.valueOf(lastKnownLocation.getLongitude()), Double.valueOf(lastKnownLocation.getLatitude()));
        } catch (Exception unused) {
            return "";
        }
    }

    public static String j(Context context) {
        return android.os.Build.VERSION.SDK_INT < 23 ? k(context) : d();
    }

    public static String k(Context context) {
        WifiInfo connectionInfo;
        try {
            if (!at.e(context, "android.permission.ACCESS_WIFI_STATE") || (connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo()) == null) {
                return "";
            }
            String macAddress = connectionInfo.getMacAddress();
            return !TextUtils.isEmpty(macAddress) ? macAddress : "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static String l(Context context) {
        String name;
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            return (defaultAdapter == null || (name = defaultAdapter.getName()) == null) ? "" : name;
        } catch (Exception unused) {
            return "";
        }
    }

    @SuppressLint({"NewApi"})
    public static String m(Context context) {
        BluetoothAdapter defaultAdapter;
        String address;
        String str = android.os.Build.BRAND;
        if ("4.1.1".equals(android.os.Build.VERSION.RELEASE) && "TCT".equals(str)) {
            return "";
        }
        try {
            return (!at.e(context, "android.permission.BLUETOOTH") || (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) == null || (address = defaultAdapter.getAddress()) == null) ? "" : address;
        } catch (Exception unused) {
        }
    }

    public static String n(Context context) {
        String strO = o(context);
        return TextUtils.isEmpty(strO) ? "" : ar.a.a(strO.getBytes());
    }

    public static String o(Context context) {
        boolean zIsProviderEnabled;
        WifiInfo connectionInfo;
        List<ScanResult> scanResults;
        JSONArray jSONArray;
        int i;
        WifiManager wifiManager;
        if (context == null || !at.e(context, "android.permission.ACCESS_WIFI_STATE")) {
            return "";
        }
        try {
            try {
                try {
                    zIsProviderEnabled = at.e(context, "android.permission.ACCESS_FINE_LOCATION") ? ((LocationManager) context.getSystemService("location")).isProviderEnabled("gps") : false;
                    while (true) {
                        int i2 = 1;
                        if (scanResults == null || i >= scanResults.size() || i >= 30) {
                            break;
                        }
                        try {
                            ScanResult scanResult = scanResults.get(i);
                            StringBuilder sb = new StringBuilder();
                            sb.append(scanResult.BSSID);
                            sb.append("|");
                            String strReplaceAll = scanResult.SSID.replaceAll("\\|", "");
                            if (strReplaceAll.length() > 30) {
                                strReplaceAll = strReplaceAll.substring(0, 30);
                            }
                            sb.append(strReplaceAll);
                            sb.append("|");
                            sb.append(scanResult.level);
                            sb.append("|");
                            if (connectionInfo == null || !scanResult.BSSID.equals(connectionInfo.getBSSID())) {
                                i2 = 0;
                            }
                            sb.append(i2);
                            jSONArray.put(sb.toString());
                        } catch (Exception unused) {
                        }
                        i++;
                    }
                } catch (Exception unused2) {
                }
                scanResults = wifiManager.getScanResults();
            } catch (Throwable unused3) {
                scanResults = null;
            }
            wifiManager = (WifiManager) context.getSystemService("wifi");
            connectionInfo = wifiManager.getConnectionInfo();
        } catch (Throwable unused4) {
            connectionInfo = null;
        }
        if (scanResults != null && scanResults.size() != 0) {
            Collections.sort(scanResults, new Comparator<ScanResult>() { // from class: com.baidu.mobstat.bb.1
                @Override // java.util.Comparator
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public int compare(ScanResult scanResult2, ScanResult scanResult3) {
                    return scanResult3.level - scanResult2.level;
                }
            });
        }
        jSONArray = new JSONArray();
        i = 0;
        if (jSONArray.length() == 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(System.currentTimeMillis());
            sb2.append("|");
            sb2.append(zIsProviderEnabled ? 1 : 0);
            sb2.append("|");
            sb2.append(i(context));
            jSONObject.put("ap-list", jSONArray);
            jSONObject.put("meta-data", sb2.toString());
            return jSONObject.toString();
        } catch (Exception unused5) {
            return "";
        }
    }

    public static boolean p(Context context) {
        if (context == null) {
            return false;
        }
        try {
            NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
            return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
        } catch (Exception unused) {
            return false;
        }
    }

    public static String q(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return "";
            }
            String typeName = activeNetworkInfo.getTypeName();
            return (typeName.equals("WIFI") || activeNetworkInfo.getSubtypeName() == null) ? typeName : activeNetworkInfo.getSubtypeName();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String r(Context context) {
        return context != null ? context.getPackageName() : "";
    }

    public static String s(Context context) {
        String str = f3484b;
        if (str == null) {
            String strW = w(context);
            String strB = b(context, strW);
            if (TextUtils.isEmpty(strB)) {
                strB = c(context, strW);
            }
            str = strB == null ? "" : strB;
            f3484b = str;
        }
        return str;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0038  */
    /* JADX WARN: Code duplicated, block: B:28:? A[RETURN, SYNTHETIC] */
    public static String t(Context context) {
        ServiceInfo[] serviceInfoArr;
        String str;
        String strW = w(context);
        if (strW == null) {
            return "";
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4);
        } catch (Exception unused) {
        }
        if (packageInfo == null || (serviceInfoArr = packageInfo.services) == null) {
            return "";
        }
        for (ServiceInfo serviceInfo : serviceInfoArr) {
            if (strW.equals(serviceInfo.processName)) {
                str = serviceInfo.name;
                if (str == null) {
                    return "";
                }
                return str;
            }
        }
        str = "";
        if (str == null) {
            return "";
        }
        return str;
    }

    public static boolean u(Context context) {
        if (context == null) {
            return false;
        }
        try {
            return context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
        } catch (Exception unused) {
            return false;
        }
    }

    public static String v(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Config.MODEL, memoryInfo.availMem);
            jSONObject.put("l", memoryInfo.lowMemory);
            jSONObject.put("t", memoryInfo.threshold);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("app_mem", jSONArray);
            jSONObject2.put("meta-data", sb.toString());
            return ar.a.a(jSONObject2.toString().getBytes());
        } catch (Exception unused) {
            return "";
        }
    }

    private static String w(Context context) {
        String str = f3483a;
        if (str == null) {
            try {
                List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
                for (int i = 0; runningAppProcesses != null && i < runningAppProcesses.size(); i++) {
                    ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningAppProcesses.get(i);
                    if (runningAppProcessInfo != null && runningAppProcessInfo.pid == Process.myPid()) {
                        str = runningAppProcessInfo.processName;
                        break;
                    }
                }
            } catch (Exception unused) {
            }
            if (str == null) {
                str = "";
            }
            f3483a = str;
        }
        return str;
    }

    public static String b(int i, Context context) {
        String strK = k(context);
        return TextUtils.isEmpty(strK) ? "" : ar.b.c(i, strK.getBytes());
    }

    public static String c(int i, Context context) {
        String strD = d(i, context);
        String strC = !TextUtils.isEmpty(strD) ? ar.b.c(i, strD.getBytes()) : null;
        return TextUtils.isEmpty(strC) ? "" : strC;
    }

    @TargetApi(9)
    private static String d() {
        if (android.os.Build.VERSION.SDK_INT < 9) {
            return "";
        }
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b2 : hardwareAddress) {
                        sb.append(String.format("%02x:", Byte.valueOf(b2)));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
        } catch (Throwable unused) {
        }
        return "";
    }

    @SuppressLint({"NewApi"})
    public static String e(int i, Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        byte[] hardwareAddress = null;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterfaceNextElement.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isAnyLocalAddress() && (inetAddressNextElement instanceof Inet4Address) && !inetAddressNextElement.isLoopbackAddress()) {
                        if (inetAddressNextElement.isSiteLocalAddress()) {
                            hardwareAddress = networkInterfaceNextElement.getHardwareAddress();
                        } else if (!inetAddressNextElement.isLinkLocalAddress()) {
                            hardwareAddress = networkInterfaceNextElement.getHardwareAddress();
                            break;
                        }
                    }
                }
            }
        } catch (Exception unused) {
        }
        if (hardwareAddress != null) {
            for (byte b2 : hardwareAddress) {
                stringBuffer.append(a(b2));
            }
            return stringBuffer.substring(0, stringBuffer.length() - 1).replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "");
        }
        String strB = b(i, context);
        return strB != null ? strB.replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "") : strB;
    }

    private static String b(Context context, String str) {
        int iLastIndexOf;
        int i;
        if (str != null && (iLastIndexOf = str.lastIndexOf(58)) > 0 && (i = iLastIndexOf + 1) < str.length()) {
            return str.substring(i);
        }
        return null;
    }

    public static String f(int i, Context context) {
        String strM = m(context);
        return TextUtils.isEmpty(strM) ? "" : ar.b.c(i, strM.getBytes());
    }

    public static String g(int i, Context context) {
        String strO = o(context);
        return TextUtils.isEmpty(strO) ? "" : ar.b.d(i, strO.getBytes());
    }

    public static String a(int i, Context context) {
        try {
            return ar.b.c(i, a(context).getBytes());
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b() throws Throwable {
        String str;
        String str2 = f3485c;
        if (str2 != null) {
            return str2;
        }
        if (!TextUtils.isEmpty(a("ro.miui.ui.version.name"))) {
            str = "miui";
        } else if (!TextUtils.isEmpty(a("ro.build.version.opporom"))) {
            str = "coloros";
        } else if (!TextUtils.isEmpty(a("ro.build.version.emui"))) {
            str = "emui";
        } else if (TextUtils.isEmpty(a("ro.vivo.os.version"))) {
            str = !TextUtils.isEmpty(a("ro.smartisan.version")) ? "smartisan" : "";
        } else {
            str = "funtouch";
        }
        if (TextUtils.isEmpty(str)) {
            String strA = a("ro.build.display.id");
            if (!TextUtils.isEmpty(strA) && strA.contains("Flyme")) {
                str = "flyme";
            }
        }
        f3485c = str;
        return f3485c;
    }

    private static String c(Context context, String str) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return null;
        }
        String str2 = applicationInfo.processName;
        if (str2 == null || str2.equals(str)) {
            return null;
        }
        return str;
    }

    public static String a(Context context) {
        return f3486d.matcher(bc.a(context)).replaceAll("");
    }

    public static Boolean c() {
        Object objInvoke;
        try {
            Class<?> cls = Class.forName("com.baidu.disasterrecovery.MtjAdapter");
            if (cls == null || (objInvoke = cls.getDeclaredMethod("shouldUploadOther", new Class[0]).invoke(null, new Object[0])) == null || !(objInvoke instanceof Boolean)) {
                return true;
            }
            return (Boolean) objInvoke;
        } catch (Exception unused) {
            return true;
        }
    }

    private static String a(byte b2) {
        String str = "00" + Integer.toHexString(b2) + Config.TRACE_TODAY_VISIT_SPLIT;
        return str.substring(str.length() - 3);
    }

    public static String h(int i, Context context) {
        String strR = r(context);
        if (TextUtils.isEmpty(strR)) {
            return "";
        }
        try {
            return ar.b.c(i, strR.getBytes());
        } catch (Exception unused) {
            return "";
        }
    }

    public static String a() throws Throwable {
        InputStreamReader inputStreamReader;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            char[] cArr = new char[20];
            inputStreamReader = new InputStreamReader(new FileInputStream("/sys/class/net/eth0/address"));
            while (true) {
                try {
                    int i = inputStreamReader.read(cArr);
                    if (i == -1) {
                        break;
                    }
                    if (i != cArr.length || cArr[cArr.length - 1] == '\r') {
                        for (int i2 = 0; i2 < i; i2++) {
                            if (cArr[i2] != '\r') {
                                stringBuffer.append(cArr[i2]);
                            }
                        }
                    } else {
                        System.out.print(cArr);
                    }
                } catch (Exception unused) {
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (Exception unused2) {
                        }
                    }
                    return null;
                } catch (Throwable th) {
                    th = th;
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (Exception unused3) {
                        }
                    }
                    throw th;
                }
            }
            String strReplaceAll = stringBuffer.toString().trim().replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "");
            try {
                inputStreamReader.close();
            } catch (Exception unused4) {
            }
            return strReplaceAll;
        } catch (Exception unused5) {
            inputStreamReader = null;
        } catch (Throwable th2) {
            th = th2;
            inputStreamReader = null;
        }
    }

    public static String d(int i, Context context) throws Throwable {
        String strA = a();
        if (TextUtils.isEmpty(strA)) {
            strA = e(i, context);
        }
        return TextUtils.isEmpty(strA) ? "" : strA;
    }

    public static String a(Context context, int i) {
        String strL = l(context);
        return TextUtils.isEmpty(strL) ? "" : ar.b.c(i, strL.getBytes());
    }

    private static String a(String str) throws Throwable {
        Process process;
        Process processExec;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        line = null;
        String line = null;
        bufferedReader2 = null;
        try {
            processExec = Runtime.getRuntime().exec("getprop " + str);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()), 1024);
                try {
                    line = bufferedReader.readLine();
                    try {
                        bufferedReader.close();
                    } catch (Exception unused) {
                    }
                    if (processExec != null) {
                        processExec.destroy();
                    }
                } catch (Exception unused2) {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception unused3) {
                        }
                    }
                    if (processExec != null) {
                    }
                    return line;
                } catch (Throwable th) {
                    process = processExec;
                    th = th;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Exception unused4) {
                        }
                    }
                    if (process == null) {
                        throw th;
                    }
                    process.destroy();
                    throw th;
                }
            } catch (Exception unused5) {
                bufferedReader = null;
            } catch (Throwable th2) {
                process = processExec;
                th = th2;
            }
        } catch (Exception unused6) {
            processExec = null;
            bufferedReader = null;
        } catch (Throwable th3) {
            th = th3;
            process = null;
        }
        return line;
    }
}
