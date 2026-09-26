package com.blankj.utilcode.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import androidx.core.content.FileProvider;
import com.baidu.mobstat.Config;
import com.umeng.commonsdk.proguard.ap;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class AppUtils {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static class AppInfo {
        private Drawable icon;
        private boolean isSystem;
        private String name;
        private String packageName;
        private String packagePath;
        private int versionCode;
        private String versionName;

        public AppInfo(String str, String str2, Drawable drawable, String str3, String str4, int i, boolean z) {
            setName(str2);
            setIcon(drawable);
            setPackageName(str);
            setPackagePath(str3);
            setVersionName(str4);
            setVersionCode(i);
            setSystem(z);
        }

        public Drawable getIcon() {
            return this.icon;
        }

        public String getName() {
            return this.name;
        }

        public String getPackageName() {
            return this.packageName;
        }

        public String getPackagePath() {
            return this.packagePath;
        }

        public int getVersionCode() {
            return this.versionCode;
        }

        public String getVersionName() {
            return this.versionName;
        }

        public boolean isSystem() {
            return this.isSystem;
        }

        public void setIcon(Drawable drawable) {
            this.icon = drawable;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setPackageName(String str) {
            this.packageName = str;
        }

        public void setPackagePath(String str) {
            this.packagePath = str;
        }

        public void setSystem(boolean z) {
            this.isSystem = z;
        }

        public void setVersionCode(int i) {
            this.versionCode = i;
        }

        public void setVersionName(String str) {
            this.versionName = str;
        }

        public String toString() {
            return "{\n  pkg name: " + getPackageName() + "\n  app icon: " + getIcon() + "\n  app name: " + getName() + "\n  app path: " + getPackagePath() + "\n  app v name: " + getVersionName() + "\n  app v code: " + getVersionCode() + "\n  is system: " + isSystem() + "}";
        }
    }

    private AppUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static String bytes2HexString(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return "";
        }
        char[] cArr = new char[length << 1];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            char[] cArr2 = HEX_DIGITS;
            cArr[i] = cArr2[(bArr[i2] >> 4) & 15];
            i = i3 + 1;
            cArr[i3] = cArr2[bArr[i2] & ap.m];
        }
        return new String(cArr);
    }

    public static void exitApp() {
        LinkedList<Activity> activityList = Utils.getActivityList();
        for (int size = activityList.size() - 1; size >= 0; size--) {
            activityList.get(size).finish();
        }
        System.exit(0);
    }

    public static AppInfo getApkInfo(File file) {
        if (file != null && file.isFile() && file.exists()) {
            return getApkInfo(file.getAbsolutePath());
        }
        return null;
    }

    public static Drawable getAppIcon() {
        return getAppIcon(Utils.getApp().getPackageName());
    }

    public static AppInfo getAppInfo() {
        return getAppInfo(Utils.getApp().getPackageName());
    }

    public static String getAppName() {
        return getAppName(Utils.getApp().getPackageName());
    }

    public static String getAppPackageName() {
        return Utils.getApp().getPackageName();
    }

    public static String getAppPath() {
        return getAppPath(Utils.getApp().getPackageName());
    }

    public static Signature[] getAppSignature() {
        return getAppSignature(Utils.getApp().getPackageName());
    }

    private static String getAppSignatureHash(String str, String str2) {
        Signature[] appSignature;
        return (isSpace(str) || (appSignature = getAppSignature(str)) == null || appSignature.length <= 0) ? "" : bytes2HexString(hashTemplate(appSignature[0].toByteArray(), str2)).replaceAll("(?<=[0-9A-F]{2})[0-9A-F]{2}", ":$0");
    }

    public static String getAppSignatureMD5() {
        return getAppSignatureMD5(Utils.getApp().getPackageName());
    }

    public static String getAppSignatureSHA1() {
        return getAppSignatureSHA1(Utils.getApp().getPackageName());
    }

    public static String getAppSignatureSHA256() {
        return getAppSignatureSHA256(Utils.getApp().getPackageName());
    }

    public static int getAppUid() {
        return getAppUid(Utils.getApp().getPackageName());
    }

    public static int getAppVersionCode() {
        return getAppVersionCode(Utils.getApp().getPackageName());
    }

    public static String getAppVersionName() {
        return getAppVersionName(Utils.getApp().getPackageName());
    }

    public static List<AppInfo> getAppsInfo() {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = Utils.getApp().getPackageManager();
        if (packageManager == null) {
            return arrayList;
        }
        Iterator<PackageInfo> it = packageManager.getInstalledPackages(0).iterator();
        while (it.hasNext()) {
            AppInfo bean = getBean(packageManager, it.next());
            if (bean != null) {
                arrayList.add(bean);
            }
        }
        return arrayList;
    }

    private static AppInfo getBean(PackageManager packageManager, PackageInfo packageInfo) {
        if (packageInfo == null) {
            return null;
        }
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        return new AppInfo(packageInfo.packageName, applicationInfo.loadLabel(packageManager).toString(), applicationInfo.loadIcon(packageManager), applicationInfo.sourceDir, packageInfo.versionName, packageInfo.versionCode, (applicationInfo.flags & 1) != 0);
    }

    private static File getFileByPath(String str) {
        if (isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    private static String getForegroundProcessName() {
        List<UsageStats> listQueryUsageStats;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) Utils.getApp().getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null && runningAppProcesses.size() > 0) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.importance == 100) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        if (Build.VERSION.SDK_INT > 21) {
            PackageManager packageManager = Utils.getApp().getPackageManager();
            Intent intent = new Intent("android.settings.USAGE_ACCESS_SETTINGS");
            List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
            Log.i("ProcessUtils", listQueryIntentActivities.toString());
            if (listQueryIntentActivities.size() <= 0) {
                Log.i("ProcessUtils", "getForegroundProcessName: noun of access to usage information.");
                return "";
            }
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(Utils.getApp().getPackageName(), 0);
                AppOpsManager appOpsManager = (AppOpsManager) Utils.getApp().getSystemService("appops");
                if (appOpsManager.checkOpNoThrow("android:get_usage_stats", applicationInfo.uid, applicationInfo.packageName) != 0) {
                    intent.addFlags(268435456);
                    Utils.getApp().startActivity(intent);
                }
                if (appOpsManager.checkOpNoThrow("android:get_usage_stats", applicationInfo.uid, applicationInfo.packageName) != 0) {
                    Log.i("ProcessUtils", "getForegroundProcessName: refuse to device usage stats.");
                    return "";
                }
                UsageStatsManager usageStatsManager = (UsageStatsManager) Utils.getApp().getSystemService("usagestats");
                if (usageStatsManager != null) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    listQueryUsageStats = usageStatsManager.queryUsageStats(4, jCurrentTimeMillis - Config.MAX_LOG_DATA_EXSIT_TIME, jCurrentTimeMillis);
                } else {
                    listQueryUsageStats = null;
                }
                if (listQueryUsageStats != null && !listQueryUsageStats.isEmpty()) {
                    UsageStats usageStats = null;
                    for (UsageStats usageStats2 : listQueryUsageStats) {
                        if (usageStats == null || usageStats2.getLastTimeUsed() > usageStats.getLastTimeUsed()) {
                            usageStats = usageStats2;
                        }
                    }
                    if (usageStats == null) {
                        return null;
                    }
                    return usageStats.getPackageName();
                }
                return null;
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return "";
    }

    private static Intent getInstallAppIntent(File file) {
        return getInstallAppIntent(file, false);
    }

    private static Intent getLaunchAppIntent(String str) {
        return getLaunchAppIntent(str, false);
    }

    private static String getLauncherActivity(String str) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(str);
        List<ResolveInfo> listQueryIntentActivities = Utils.getApp().getPackageManager().queryIntentActivities(intent, 0);
        int size = listQueryIntentActivities.size();
        if (size == 0) {
            return "";
        }
        for (int i = 0; i < size; i++) {
            ResolveInfo resolveInfo = listQueryIntentActivities.get(i);
            if (resolveInfo.activityInfo.processName.equals(str)) {
                return resolveInfo.activityInfo.name;
            }
        }
        return listQueryIntentActivities.get(0).activityInfo.name;
    }

    private static Intent getUninstallAppIntent(String str) {
        return getUninstallAppIntent(str, false);
    }

    private static byte[] hashTemplate(byte[] bArr, String str) {
        if (bArr != null && bArr.length > 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(str);
                messageDigest.update(bArr);
                return messageDigest.digest();
            } catch (NoSuchAlgorithmException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static void installApp(String str) {
        installApp(getFileByPath(str));
    }

    public static boolean isAppDebug() {
        return isAppDebug(Utils.getApp().getPackageName());
    }

    public static boolean isAppForeground() {
        return Utils.isAppForeground();
    }

    public static boolean isAppInstalled(String str) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkgName' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        try {
            return Utils.getApp().getPackageManager().getApplicationInfo(str, 0) != null;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean isAppRoot() {
        ShellUtils.CommandResult commandResultExecCmd = ShellUtils.execCmd("echo root", true);
        if (commandResultExecCmd.result == 0) {
            return true;
        }
        if (commandResultExecCmd.errorMsg == null) {
            return false;
        }
        Log.d("AppUtils", "isAppRoot() called" + commandResultExecCmd.errorMsg);
        return false;
    }

    public static boolean isAppRunning(String str) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkgName' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        try {
            ApplicationInfo applicationInfo = Utils.getApp().getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo == null) {
                return false;
            }
            int i = applicationInfo.uid;
            ActivityManager activityManager = (ActivityManager) Utils.getApp().getSystemService("activity");
            if (activityManager != null) {
                List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
                if (runningTasks != null && runningTasks.size() > 0) {
                    Iterator<ActivityManager.RunningTaskInfo> it = runningTasks.iterator();
                    while (it.hasNext()) {
                        if (str.equals(it.next().baseActivity.getPackageName())) {
                            return true;
                        }
                    }
                }
                List<ActivityManager.RunningServiceInfo> runningServices = activityManager.getRunningServices(Integer.MAX_VALUE);
                if (runningServices != null && runningServices.size() > 0) {
                    Iterator<ActivityManager.RunningServiceInfo> it2 = runningServices.iterator();
                    while (it2.hasNext()) {
                        if (i == it2.next().uid) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean isAppSystem() {
        return isAppSystem(Utils.getApp().getPackageName());
    }

    private static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void launchApp(String str) {
        if (isSpace(str)) {
            return;
        }
        Intent launchAppIntent = getLaunchAppIntent(str, true);
        if (launchAppIntent == null) {
            Log.e("AppUtils", "Didn't exist launcher activity.");
        } else {
            Utils.getApp().startActivity(launchAppIntent);
        }
    }

    public static void launchAppDetailsSettings() {
        launchAppDetailsSettings(Utils.getApp().getPackageName());
    }

    public static void registerAppStatusChangedListener(Object obj, Utils.OnAppStatusChangedListener onAppStatusChangedListener) {
        if (obj == null) {
            throw new NullPointerException("Argument 'obj' of type Object (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (onAppStatusChangedListener == null) {
            throw new NullPointerException("Argument 'listener' of type Utils.OnAppStatusChangedListener (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        Utils.getActivityLifecycle().addOnAppStatusChangedListener(obj, onAppStatusChangedListener);
    }

    public static void relaunchApp() {
        relaunchApp(false);
    }

    public static void uninstallApp(String str) {
        if (isSpace(str)) {
            return;
        }
        Utils.getApp().startActivity(getUninstallAppIntent(str, true));
    }

    public static void unregisterAppStatusChangedListener(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Argument 'obj' of type Object (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        Utils.getActivityLifecycle().removeOnAppStatusChangedListener(obj);
    }

    public static Drawable getAppIcon(String str) {
        if (isSpace(str)) {
            return null;
        }
        try {
            PackageManager packageManager = Utils.getApp().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.applicationInfo.loadIcon(packageManager);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static AppInfo getAppInfo(String str) {
        try {
            PackageManager packageManager = Utils.getApp().getPackageManager();
            if (packageManager == null) {
                return null;
            }
            return getBean(packageManager, packageManager.getPackageInfo(str, 0));
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getAppName(String str) {
        if (isSpace(str)) {
            return "";
        }
        try {
            PackageManager packageManager = Utils.getApp().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.applicationInfo.loadLabel(packageManager).toString();
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String getAppPath(String str) {
        if (isSpace(str)) {
            return "";
        }
        try {
            PackageInfo packageInfo = Utils.getApp().getPackageManager().getPackageInfo(str, 0);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.applicationInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static Signature[] getAppSignature(String str) {
        if (isSpace(str)) {
            return null;
        }
        try {
            PackageInfo packageInfo = Utils.getApp().getPackageManager().getPackageInfo(str, 64);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.signatures;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getAppSignatureMD5(String str) {
        return getAppSignatureHash(str, "MD5");
    }

    public static String getAppSignatureSHA1(String str) {
        return getAppSignatureHash(str, "SHA1");
    }

    public static String getAppSignatureSHA256(String str) {
        return getAppSignatureHash(str, "SHA256");
    }

    public static int getAppUid(String str) {
        try {
            ApplicationInfo applicationInfo = Utils.getApp().getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                return applicationInfo.uid;
            }
            return -1;
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public static int getAppVersionCode(String str) {
        if (isSpace(str)) {
            return -1;
        }
        try {
            PackageInfo packageInfo = Utils.getApp().getPackageManager().getPackageInfo(str, 0);
            if (packageInfo == null) {
                return -1;
            }
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public static String getAppVersionName(String str) {
        if (isSpace(str)) {
            return "";
        }
        try {
            PackageInfo packageInfo = Utils.getApp().getPackageManager().getPackageInfo(str, 0);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static Intent getInstallAppIntent(File file, boolean z) {
        Uri uriForFile;
        Intent intent = new Intent("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT < 24) {
            uriForFile = Uri.fromFile(file);
        } else {
            uriForFile = FileProvider.getUriForFile(Utils.getApp(), Utils.getApp().getPackageName() + ".utilcode.provider", file);
            intent.setFlags(1);
        }
        Utils.getApp().grantUriPermission(Utils.getApp().getPackageName(), uriForFile, 1);
        intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
        return z ? intent.addFlags(268435456) : intent;
    }

    private static Intent getLaunchAppIntent(String str, boolean z) {
        String launcherActivity = getLauncherActivity(str);
        if (launcherActivity.isEmpty()) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName(str, launcherActivity));
        return z ? intent.addFlags(268435456) : intent;
    }

    private static Intent getUninstallAppIntent(String str, boolean z) {
        Intent intent = new Intent("android.intent.action.DELETE");
        intent.setData(Uri.parse("package:" + str));
        return z ? intent.addFlags(268435456) : intent;
    }

    public static void installApp(File file) {
        if (isFileExists(file)) {
            Utils.getApp().startActivity(getInstallAppIntent(file, true));
        }
    }

    public static boolean isAppDebug(String str) {
        if (isSpace(str)) {
            return false;
        }
        try {
            ApplicationInfo applicationInfo = Utils.getApp().getPackageManager().getApplicationInfo(str, 0);
            return (applicationInfo == null || (applicationInfo.flags & 2) == 0) ? false : true;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean isAppForeground(String str) {
        if (str != null) {
            return !isSpace(str) && str.equals(getForegroundProcessName());
        }
        throw new NullPointerException("Argument 'packageName' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean isAppSystem(String str) {
        if (isSpace(str)) {
            return false;
        }
        try {
            ApplicationInfo applicationInfo = Utils.getApp().getPackageManager().getApplicationInfo(str, 0);
            return (applicationInfo == null || (applicationInfo.flags & 1) == 0) ? false : true;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static void launchAppDetailsSettings(String str) {
        if (isSpace(str)) {
            return;
        }
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + str));
        Utils.getApp().startActivity(intent.addFlags(268435456));
    }

    public static void relaunchApp(boolean z) {
        Intent launchAppIntent = getLaunchAppIntent(Utils.getApp().getPackageName(), true);
        if (launchAppIntent == null) {
            Log.e("AppUtils", "Didn't exist launcher activity.");
            return;
        }
        launchAppIntent.addFlags(335577088);
        Utils.getApp().startActivity(launchAppIntent);
        if (z) {
            Process.killProcess(Process.myPid());
            System.exit(0);
        }
    }

    public static AppInfo getApkInfo(String str) {
        PackageManager packageManager;
        PackageInfo packageArchiveInfo;
        if (isSpace(str) || (packageManager = Utils.getApp().getPackageManager()) == null || (packageArchiveInfo = packageManager.getPackageArchiveInfo(str, 0)) == null) {
            return null;
        }
        ApplicationInfo applicationInfo = packageArchiveInfo.applicationInfo;
        applicationInfo.sourceDir = str;
        applicationInfo.publicSourceDir = str;
        return getBean(packageManager, packageArchiveInfo);
    }

    public static void uninstallApp(Activity activity, String str, int i) {
        if (isSpace(str)) {
            return;
        }
        activity.startActivityForResult(getUninstallAppIntent(str), i);
    }

    public static void installApp(Activity activity, String str, int i) {
        installApp(activity, getFileByPath(str), i);
    }

    public static void installApp(Activity activity, File file, int i) {
        if (isFileExists(file)) {
            activity.startActivityForResult(getInstallAppIntent(file), i);
        }
    }

    public static void launchApp(Activity activity, String str, int i) {
        if (isSpace(str)) {
            return;
        }
        Intent launchAppIntent = getLaunchAppIntent(str);
        if (launchAppIntent == null) {
            Log.e("AppUtils", "Didn't exist launcher activity.");
        } else {
            activity.startActivityForResult(launchAppIntent, i);
        }
    }
}
