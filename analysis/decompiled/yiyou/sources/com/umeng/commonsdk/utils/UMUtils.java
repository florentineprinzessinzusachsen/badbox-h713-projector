package com.umeng.commonsdk.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.baidu.mobstat.Config;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.e;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.MLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes.dex */
public class UMUtils {
    public static final int DEFAULT_TIMEZONE = 8;
    private static final String KEY_APP_KEY = "appkey";
    private static final String KEY_CHANNEL = "channel";
    private static final String KEY_LAST_APP_KEY = "last_appkey";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_SHARED_PREFERENCES_NAME = "umeng_common_config";
    public static final String MOBILE_NETWORK = "2G/3G";
    private static final String SD_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final String TAG = "UMUtils";
    public static final String UNKNOW = "";
    public static final String WIFI = "Wi-Fi";
    private static final Pattern pattern = Pattern.compile("UTDID\">([^<]+)");
    private static Object spLock = new Object();

    public static String MD5(String str) {
        try {
            if (str == null) {
                return null;
            }
            try {
                try {
                    byte[] bytes = str.getBytes();
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    messageDigest.reset();
                    messageDigest.update(bytes);
                    byte[] bArrDigest = messageDigest.digest();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (byte b2 : bArrDigest) {
                        stringBuffer.append(String.format("%02X", Byte.valueOf(b2)));
                    }
                    return stringBuffer.toString();
                } catch (Exception e2) {
                    if (AnalyticsConstants.UM_DEBUG) {
                        Log.e(TAG, "MD5 e is " + e2);
                    }
                    return null;
                }
            } catch (Exception unused) {
                return str.replaceAll("[^[a-z][A-Z][0-9][.][_]]", "");
            }
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "MD5 e is " + th);
            }
            return null;
        }
    }

    private static String byte2HexFormatted(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase());
            if (i < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    private static String bytes2Hex(byte[] bArr) {
        String str = "";
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                str = str + "0";
            }
            str = str + hexString;
        }
        return str;
    }

    public static boolean checkAndroidManifest(Context context, String str) {
        try {
            context.getApplicationContext().getPackageManager().getActivityInfo(new ComponentName(context.getApplicationContext().getPackageName(), str), 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean checkIntentFilterData(Context context, String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setData(Uri.parse("tencent" + str + Config.TRACE_TODAY_VISIT_SPLIT));
        List<ResolveInfo> listQueryIntentActivities = context.getApplicationContext().getPackageManager().queryIntentActivities(intent, 64);
        if (listQueryIntentActivities.size() > 0) {
            Iterator<ResolveInfo> it = listQueryIntentActivities.iterator();
            while (it.hasNext()) {
                ActivityInfo activityInfo = it.next().activityInfo;
                if (activityInfo != null && activityInfo.packageName.equals(context.getApplicationContext().getPackageName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkMetaData(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = context.getApplicationContext().getPackageManager().getApplicationInfo(context.getApplicationContext().getPackageName(), 128);
            return (applicationInfo == null || applicationInfo.metaData.get(str) == null) ? false : true;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    public static boolean checkPath(String str) {
        try {
            return Class.forName(str) != null;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static boolean checkPermission(Context context, String str) {
        if (context == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                if (((Integer) Class.forName("android.content.Context").getMethod("checkSelfPermission", String.class).invoke(context, str)).intValue() != 0) {
                    return false;
                }
            } catch (Exception e2) {
                UMCrashManager.reportCrash(context, e2);
                return false;
            }
        } else if (context.getPackageManager().checkPermission(str, context.getPackageName()) != 0) {
            return false;
        }
        return true;
    }

    public static boolean checkResource(Context context, String str, String str2) {
        return context.getApplicationContext().getResources().getIdentifier(str, str2, context.getApplicationContext().getPackageName()) > 0;
    }

    public static String encryptBySHA1(String str) {
        try {
            try {
                byte[] bytes = str.getBytes();
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
                    messageDigest.update(bytes);
                    return bytes2Hex(messageDigest.digest());
                } catch (Exception unused) {
                    return null;
                }
            } catch (Exception e2) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "encrypt by SHA1 e is " + e2);
                }
                return null;
            }
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "encrypt by SHA1 e is " + th);
            }
            return null;
        }
    }

    public static String getAppHashKey(Context context) {
        return DeviceConfig.getAppHashKey(context);
    }

    public static String getAppMD5Signature(Context context) {
        String appMD5Signature = DeviceConfig.getAppMD5Signature(context);
        return !TextUtils.isEmpty(appMD5Signature) ? appMD5Signature.replace(Config.TRACE_TODAY_VISIT_SPLIT, "").toLowerCase() : appMD5Signature;
    }

    public static String getAppName(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (Exception e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app name e is " + e2);
            }
            UMCrashManager.reportCrash(context, e2);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app name e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static String getAppSHA1Key(Context context) {
        return DeviceConfig.getAppSHA1Key(context);
    }

    public static String getAppVersinoCode(Context context, String str) {
        if (context != null && str != null) {
            try {
                return String.valueOf(context.getPackageManager().getPackageInfo(str, 0).versionCode);
            } catch (Exception e2) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "get app version code e is " + e2);
                }
            } catch (Throwable th) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "get app version code e is " + th);
                }
                return "";
            }
        }
        return "";
    }

    public static String getAppVersionCode(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (Exception e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version code e is " + e2);
            }
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version code e is " + th);
            }
            return "";
        }
    }

    public static String getAppVersionName(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version name e is " + e2);
            }
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version name e is " + th);
            }
            return "";
        }
    }

    public static String getAppkey(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return getMultiProcessSP(context, "appkey");
        } catch (Exception e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app key e is " + e2);
            }
            UMCrashManager.reportCrash(context, e2);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app key e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static String getAppkeyByXML(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null) {
                return null;
            }
            String string = applicationInfo.metaData.getString("UMENG_APPKEY");
            if (string != null) {
                return string.trim();
            }
            if (!AnalyticsConstants.UM_DEBUG) {
                return null;
            }
            MLog.i(AnalyticsConstants.LOG_TAG, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.");
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Properties getBuildProp() throws Throwable {
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
                try {
                    properties.load(fileInputStream2);
                    fileInputStream2.close();
                } catch (IOException unused) {
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return properties;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            } catch (IOException unused3) {
            }
        } catch (IOException unused4) {
        } catch (Throwable th2) {
            th = th2;
        }
        return properties;
    }

    public static String getCPU() {
        String line = null;
        try {
            try {
                FileReader fileReader = new FileReader("/proc/cpuinfo");
                try {
                    BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
                    line = bufferedReader.readLine();
                    bufferedReader.close();
                    fileReader.close();
                } catch (IOException e2) {
                    if (AnalyticsConstants.UM_DEBUG) {
                        Log.e(TAG, "Could not read from file /proc/cpuinfo, e is " + e2);
                    }
                }
            } catch (FileNotFoundException e3) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "Could not read from file /proc/cpuinfo, e is " + e3);
                }
            }
            return line != null ? line.substring(line.indexOf(58) + 1).trim() : "";
        } catch (Exception e4) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get cpu e is " + e4);
            }
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get cpu e is " + th);
            }
            return "";
        }
    }

    public static String getChannel(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return getMultiProcessSP(context, "channel");
        } catch (Exception e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get channel e is " + e2);
            }
            UMCrashManager.reportCrash(context, e2);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get channel e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static String getChannelByXML(Context context) {
        Object obj;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null || (obj = applicationInfo.metaData.get("UMENG_CHANNEL")) == null) {
                return null;
            }
            String string = obj.toString();
            if (string != null) {
                return string.trim();
            }
            if (!AnalyticsConstants.UM_DEBUG) {
                return null;
            }
            MLog.i(AnalyticsConstants.LOG_TAG, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String getDeviceToken(Context context) {
        Method method;
        Object objInvoke;
        Method method2;
        Object objInvoke2;
        if (context == null) {
            return null;
        }
        Context applicationContext = context.getApplicationContext();
        try {
            Class<?> cls = Class.forName("com.umeng.message.MessageSharedPrefs");
            if (cls == null || (method = cls.getMethod("getInstance", Context.class)) == null || (objInvoke = method.invoke(cls, applicationContext)) == null || (method2 = cls.getMethod("getDeviceToken", new Class[0])) == null || (objInvoke2 = method2.invoke(objInvoke, new Object[0])) == null || !(objInvoke2 instanceof String)) {
                return null;
            }
            return (String) objInvoke2;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String getDeviceType(Context context) {
        if (context == null) {
            return "Phone";
        }
        try {
            return (context.getResources().getConfiguration().screenLayout & 15) >= 3 ? "Tablet" : "Phone";
        } catch (Exception e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get device type e is " + e2);
            }
            UMCrashManager.reportCrash(context, e2);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get device type e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static String getDisplayResolution(Context context) {
        if (context == null) {
            return "";
        }
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager == null) {
                return "";
            }
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            return String.valueOf(displayMetrics.heightPixels) + "*" + String.valueOf(i);
        } catch (Exception e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get display resolution e is " + e2);
            }
            UMCrashManager.reportCrash(context, e2);
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get display resolution e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return "";
        }
    }

    private static File getFile(Context context) {
        if (context != null && checkPermission(context, SD_PERMISSION) && Environment.getExternalStorageState().equals("mounted")) {
            try {
                return new File(Environment.getExternalStorageDirectory().getCanonicalPath(), ".UTSystemConfig/Global/Alvin2.xml");
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static String getFileMD5(File file) {
        try {
            try {
                byte[] bArr = new byte[1024];
                try {
                    if (!file.isFile()) {
                        return "";
                    }
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    FileInputStream fileInputStream = new FileInputStream(file);
                    while (true) {
                        int i = fileInputStream.read(bArr, 0, 1024);
                        if (i == -1) {
                            fileInputStream.close();
                            return String.format("%1$032x", new BigInteger(1, messageDigest.digest()));
                        }
                        messageDigest.update(bArr, 0, i);
                    }
                } catch (Exception unused) {
                    return null;
                }
            } catch (Throwable th) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "get file MD5 e is " + th);
                }
                return null;
            }
        } catch (Exception e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get file MD5 e is " + e2);
            }
            return null;
        }
    }

    private static String getFlymeVersion(Properties properties) {
        try {
            String lowerCase = properties.getProperty("ro.build.display.id").toLowerCase(Locale.getDefault());
            if (lowerCase.contains("flyme os")) {
                return lowerCase.split(" ")[2];
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String[] getGPU(GL10 gl10) {
        try {
            return new String[]{gl10.glGetString(7936), gl10.glGetString(7937)};
        } catch (Exception e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "Could not read gpu infor, e is " + e2);
            }
            return new String[0];
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "Could not read gpu infor, e is " + th);
            }
            return new String[0];
        }
    }

    public static String getImsi(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (!checkPermission(context, "android.permission.READ_PHONE_STATE") || telephonyManager == null) {
                return null;
            }
            return telephonyManager.getSubscriberId();
        } catch (Exception e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get imei e is " + e2);
            }
            UMCrashManager.reportCrash(context, e2);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get imei e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static String getLastAppkey(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return getMultiProcessSP(context, KEY_LAST_APP_KEY);
        } catch (Exception e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get last app key e is " + e2);
            }
            UMCrashManager.reportCrash(context, e2);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get last app key e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static Locale getLocale(Context context) {
        Locale locale;
        if (context == null) {
            return null;
        }
        try {
            try {
                Configuration configuration = new Configuration();
                configuration.setToDefaults();
                Settings.System.getConfiguration(context.getContentResolver(), configuration);
                locale = configuration.locale;
            } catch (Exception e2) {
                try {
                    if (AnalyticsConstants.UM_DEBUG) {
                        Log.e(TAG, "fail to read user config locale, e is " + e2);
                    }
                    locale = null;
                } catch (Exception e3) {
                    if (AnalyticsConstants.UM_DEBUG) {
                        Log.e(TAG, "get locale e is " + e3);
                    }
                    UMCrashManager.reportCrash(context, e3);
                    return null;
                }
            }
            return locale == null ? Locale.getDefault() : locale;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get locale e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static String getMac(Context context) {
        if (context == null) {
            return null;
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager == null) {
                return null;
            }
            if (checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
                return wifiManager.getConnectionInfo().getMacAddress();
            }
            if (!AnalyticsConstants.UM_DEBUG) {
                return "";
            }
            Log.e(TAG, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
            return "";
        } catch (Exception e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get mac e is " + e2);
            }
            UMCrashManager.reportCrash(context, e2);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get mac e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static String getMultiProcessSP(Context context, String str) {
        SharedPreferences sharedPreferences;
        try {
            synchronized (spLock) {
                if (context != null) {
                    if (!TextUtils.isEmpty(str)) {
                        if (isMainProgress(context)) {
                            sharedPreferences = context.getApplicationContext().getSharedPreferences("umeng_common_config", 0);
                        } else {
                            String subProcessName = UMFrUtils.getSubProcessName(context);
                            sharedPreferences = context.getApplicationContext().getSharedPreferences(subProcessName + "_umeng_common_config", 0);
                        }
                        if (sharedPreferences == null) {
                            return null;
                        }
                        return sharedPreferences.getString(str, null);
                    }
                }
                return null;
            }
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String[] getNetworkAccessMode(Context context) {
        String[] strArr = {"", ""};
        if (context == null) {
            return strArr;
        }
        try {
            if (!checkPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
                strArr[0] = "";
                return strArr;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                strArr[0] = "";
                return strArr;
            }
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                strArr[0] = "Wi-Fi";
                return strArr;
            }
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
            if (networkInfo2 != null && networkInfo2.getState() == NetworkInfo.State.CONNECTED) {
                strArr[0] = "2G/3G";
                strArr[1] = networkInfo2.getSubtypeName();
                return strArr;
            }
            return strArr;
        } catch (Exception e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get network access mode e is " + e2);
            }
            UMCrashManager.reportCrash(context, e2);
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get network access mode e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
        }
    }

    public static String getNetworkOperatorName(Context context) {
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return (checkPermission(context, "android.permission.READ_PHONE_STATE") && telephonyManager != null) ? telephonyManager.getNetworkOperatorName() : "";
        } catch (Exception e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get network operator e is " + e2);
            }
            UMCrashManager.reportCrash(context, e2);
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get network operator e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return "";
        }
    }

    public static String getOperator(Context context) {
        if (context == null) {
            return "Unknown";
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return telephonyManager == null ? "Unknown" : telephonyManager.getNetworkOperator();
        } catch (Exception e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get get operator e is " + e2);
            }
            UMCrashManager.reportCrash(context, e2);
            return "Unknown";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get get operator e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return "Unknown";
        }
    }

    public static String getRegisteredOperator(Context context) {
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null && checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                return telephonyManager.getNetworkOperator();
            }
            return null;
        } catch (Exception e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get registered operator e is " + e2);
            }
            UMCrashManager.reportCrash(context, e2);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get registered operator e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static String getSubOSName(Context context) {
        String str;
        if (context == null) {
            return null;
        }
        try {
            try {
                Properties buildProp = getBuildProp();
                try {
                    String property = buildProp.getProperty(KEY_MIUI_VERSION_NAME);
                    if (!TextUtils.isEmpty(property)) {
                        str = "MIUI";
                    } else if (isFlyMe()) {
                        str = "Flyme";
                    } else {
                        if (TextUtils.isEmpty(getYunOSVersion(buildProp))) {
                            return property;
                        }
                        str = "YunOS";
                    }
                    return str;
                } catch (Exception e2) {
                    UMCrashManager.reportCrash(context, e2);
                    return null;
                }
            } catch (Throwable th) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "get sub os name e is " + th);
                }
                UMCrashManager.reportCrash(context, th);
                return null;
            }
        } catch (Exception e3) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get sub os name e is " + e3);
            }
            UMCrashManager.reportCrash(context, e3);
            return null;
        }
    }

    public static String getSubOSVersion(Context context) {
        if (context == null) {
            return null;
        }
        try {
            try {
                Properties buildProp = getBuildProp();
                try {
                    String property = buildProp.getProperty(KEY_MIUI_VERSION_NAME);
                    if (TextUtils.isEmpty(property)) {
                        try {
                            return isFlyMe() ? getFlymeVersion(buildProp) : getYunOSVersion(buildProp);
                        } catch (Exception unused) {
                        }
                    }
                    return property;
                } catch (Exception e2) {
                    UMCrashManager.reportCrash(context, e2);
                    return null;
                }
            } catch (Throwable th) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "get sub os version e is " + th);
                }
                UMCrashManager.reportCrash(context, th);
                return null;
            }
        } catch (Exception e3) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get sub os version e is " + e3);
            }
            UMCrashManager.reportCrash(context, e3);
            return null;
        }
    }

    public static int getTargetSdkVersion(Context context) {
        return context.getApplicationInfo().targetSdkVersion;
    }

    public static String getUMId(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return UMEnvelopeBuild.imprintProperty(context.getApplicationContext(), e.f3967f, null);
        } catch (Exception e2) {
            UMCrashManager.reportCrash(context, e2);
            return null;
        }
    }

    public static String getUTDID(Context context) {
        if (context == null) {
            return null;
        }
        try {
            try {
                try {
                    return (String) Class.forName("com.ut.device.UTDevice").getMethod("getUtdid", Context.class).invoke(null, context.getApplicationContext());
                } catch (Exception unused) {
                    return readUTDId(context);
                }
            } catch (Exception e2) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "get utiid e is " + e2);
                }
                return null;
            }
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get utiid e is " + th);
            }
            return null;
        }
    }

    private static String getYunOSVersion(Properties properties) {
        String property = properties.getProperty("ro.yunos.version");
        if (TextUtils.isEmpty(property)) {
            return null;
        }
        return property;
    }

    public static boolean isApplication(Context context) {
        try {
            String name = context.getApplicationContext().getClass().getSuperclass().getName();
            return !TextUtils.isEmpty(name) && name.equals("android.app.Application");
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isDebug(Context context) {
        if (context == null) {
            return false;
        }
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception e2) {
            UMCrashManager.reportCrash(context, e2);
            return false;
        }
    }

    private static boolean isFlyMe() {
        try {
            Build.class.getMethod("hasSmartBar", new Class[0]);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isMainProgress(Context context) {
        try {
            String currentProcessName = UMFrUtils.getCurrentProcessName(context);
            String packageName = context.getApplicationContext().getPackageName();
            return (TextUtils.isEmpty(currentProcessName) || TextUtils.isEmpty(packageName) || !currentProcessName.equals(packageName)) ? false : true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isSdCardWrittenable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    private static String parseId(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private static String readStreamToString(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        char[] cArr = new char[1024];
        StringWriter stringWriter = new StringWriter();
        while (true) {
            int i = inputStreamReader.read(cArr);
            if (-1 == i) {
                return stringWriter.toString();
            }
            stringWriter.write(cArr, 0, i);
        }
    }

    private static String readUTDId(Context context) {
        File file;
        if (context != null && (file = getFile(context)) != null && file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    return parseId(readStreamToString(fileInputStream));
                } finally {
                    safeClose(fileInputStream);
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private static void safeClose(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    public static void setAppkey(Context context, String str) {
        if (context == null || str == null) {
            return;
        }
        try {
            setMultiProcessSP(context, "appkey", str);
        } catch (Exception e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "set app key e is " + e2);
            }
            UMCrashManager.reportCrash(context, e2);
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "set app key e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
        }
    }

    public static void setChannel(Context context, String str) {
        if (context == null || str == null) {
            return;
        }
        try {
            setMultiProcessSP(context, "channel", str);
        } catch (Exception e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "set channel e is " + e2);
            }
            UMCrashManager.reportCrash(context, e2);
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "set channel e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
        }
    }

    public static void setLastAppkey(Context context, String str) {
        if (context == null || str == null) {
            return;
        }
        try {
            setMultiProcessSP(context, KEY_LAST_APP_KEY, str);
        } catch (Exception e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "set last app key e is " + e2);
            }
            UMCrashManager.reportCrash(context, e2);
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "set last app key e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
        }
    }

    public static void setMultiProcessSP(Context context, String str, String str2) {
        SharedPreferences sharedPreferences;
        try {
            synchronized (spLock) {
                if (context != null) {
                    if (!TextUtils.isEmpty(str) && str2 != null) {
                        if (isMainProgress(context)) {
                            sharedPreferences = context.getApplicationContext().getSharedPreferences("umeng_common_config", 0);
                        } else {
                            String subProcessName = UMFrUtils.getSubProcessName(context);
                            sharedPreferences = context.getApplicationContext().getSharedPreferences(subProcessName + "_umeng_common_config", 0);
                        }
                        if (sharedPreferences != null) {
                            sharedPreferences.edit().putString(str, str2).commit();
                        }
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static String getAppVersionName(Context context, String str) {
        if (context != null && str != null) {
            try {
                return context.getPackageManager().getPackageInfo(str, 0).versionName;
            } catch (PackageManager.NameNotFoundException e2) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "get app version name e is " + e2);
                }
                UMCrashManager.reportCrash(context, e2);
            } catch (Throwable th) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "get app version name e is " + th);
                }
                UMCrashManager.reportCrash(context, th);
                return "";
            }
        }
        return "";
    }
}
