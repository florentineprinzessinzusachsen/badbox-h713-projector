package com.android.umanalytics.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.mobstat.Config;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class PackageUtils {
    public static final int APP_INSTALL_AUTO = 0;
    public static final int APP_INSTALL_EXTERNAL = 2;
    public static final int APP_INSTALL_INTERNAL = 1;
    public static final int DELETE_FAILED_DEVICE_POLICY_MANAGER = -2;
    public static final int DELETE_FAILED_INTERNAL_ERROR = -1;
    public static final int DELETE_FAILED_INVALID_PACKAGE = -3;
    public static final int DELETE_FAILED_PERMISSION_DENIED = -4;
    public static final int DELETE_SUCCEEDED = 1;
    public static final int INSTALL_FAILED_ALREADY_EXISTS = -1;
    public static final int INSTALL_FAILED_CONFLICTING_PROVIDER = -13;
    public static final int INSTALL_FAILED_CONTAINER_ERROR = -18;
    public static final int INSTALL_FAILED_CPU_ABI_INCOMPATIBLE = -16;
    public static final int INSTALL_FAILED_DEXOPT = -11;
    public static final int INSTALL_FAILED_DUPLICATE_PACKAGE = -5;
    public static final int INSTALL_FAILED_INSUFFICIENT_STORAGE = -4;
    public static final int INSTALL_FAILED_INTERNAL_ERROR = -110;
    public static final int INSTALL_FAILED_INVALID_APK = -2;
    public static final int INSTALL_FAILED_INVALID_INSTALL_LOCATION = -19;
    public static final int INSTALL_FAILED_INVALID_URI = -3;
    public static final int INSTALL_FAILED_MEDIA_UNAVAILABLE = -20;
    public static final int INSTALL_FAILED_MISSING_FEATURE = -17;
    public static final int INSTALL_FAILED_MISSING_SHARED_LIBRARY = -9;
    public static final int INSTALL_FAILED_NEWER_SDK = -14;
    public static final int INSTALL_FAILED_NO_SHARED_USER = -6;
    public static final int INSTALL_FAILED_OLDER_SDK = -12;
    public static final int INSTALL_FAILED_OTHER = -1000000;
    public static final int INSTALL_FAILED_PACKAGE_CHANGED = -23;
    public static final int INSTALL_FAILED_REPLACE_COULDNT_DELETE = -10;
    public static final int INSTALL_FAILED_SHARED_USER_INCOMPATIBLE = -8;
    public static final int INSTALL_FAILED_TEST_ONLY = -15;
    public static final int INSTALL_FAILED_UID_CHANGED = -24;
    public static final int INSTALL_FAILED_UPDATE_INCOMPATIBLE = -7;
    public static final int INSTALL_FAILED_VERIFICATION_FAILURE = -22;
    public static final int INSTALL_FAILED_VERIFICATION_TIMEOUT = -21;
    public static final int INSTALL_PARSE_FAILED_BAD_MANIFEST = -101;
    public static final int INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME = -106;
    public static final int INSTALL_PARSE_FAILED_BAD_SHARED_USER_ID = -107;
    public static final int INSTALL_PARSE_FAILED_CERTIFICATE_ENCODING = -105;
    public static final int INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES = -104;
    public static final int INSTALL_PARSE_FAILED_MANIFEST_EMPTY = -109;
    public static final int INSTALL_PARSE_FAILED_MANIFEST_MALFORMED = -108;
    public static final int INSTALL_PARSE_FAILED_NOT_APK = -100;
    public static final int INSTALL_PARSE_FAILED_NO_CERTIFICATES = -103;
    public static final int INSTALL_PARSE_FAILED_UNEXPECTED_EXCEPTION = -102;
    public static final int INSTALL_SUCCEEDED = 1;
    public static final String TAG = "PackageUtils";

    private PackageUtils() {
        throw new AssertionError();
    }

    public static int getAppVersionCode(Context context) {
        PackageManager packageManager;
        if (context == null || (packageManager = context.getPackageManager()) == null) {
            return -1;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
            return -1;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public static int getInstallLocation() {
        String str;
        int i = 1;
        ShellUtils.CommandResult commandResultExecCommand = ShellUtils.execCommand("LD_LIBRARY_PATH=/vendor/lib:/system/lib pm get-install-location", false, true);
        if (commandResultExecCommand.result == 0 && (str = commandResultExecCommand.successMsg) != null && str.length() > 0) {
            try {
                int i2 = Integer.parseInt(commandResultExecCommand.successMsg.substring(0, 1));
                if (i2 != 1) {
                    i = 2;
                    if (i2 != 2) {
                    }
                }
                return i;
            } catch (NumberFormatException e2) {
                e2.printStackTrace();
                Log.e(TAG, "pm get-install-location error");
            }
        }
        return 0;
    }

    private static String getInstallLocationParams() {
        int installLocation = getInstallLocation();
        if (installLocation != 1) {
            return installLocation != 2 ? "" : "-s";
        }
        return "-f";
    }

    public static final int install(Context context, String str) {
        if (isSystemApplication(context) || ShellUtils.checkRootPermission()) {
            return installSilent(context, str);
        }
        return installNormal(context, str) ? 1 : -3;
    }

    public static boolean installNormal(Context context, String str) {
        if (!TextUtils.isEmpty(str) && str.startsWith(Environment.getDataDirectory().getAbsolutePath())) {
            try {
                Runtime.getRuntime().exec("chmod 777 " + Environment.getDataDirectory().getAbsolutePath() + "/data/" + context.getPackageName() + " \n");
                File file = new File(str);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                }
                Runtime.getRuntime().exec("chmod 777 " + file.getParentFile().getPath() + " \n");
                Runtime.getRuntime().exec("chmod 777 " + str + " \n");
            } catch (IOException e2) {
                Log.e("installNormal", "chmod fail!!!!");
                e2.printStackTrace();
            }
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        File file2 = new File(str);
        if (!file2.exists() || !file2.isFile() || file2.length() <= 0) {
            return false;
        }
        intent.setDataAndType(Uri.parse("file://" + str), "application/vnd.android.package-archive");
        intent.addFlags(268435456);
        context.startActivity(intent);
        return true;
    }

    public static int installSilent(Context context, String str) {
        return installSilent(context, str, " -r " + getInstallLocationParams());
    }

    public static boolean isSystemApplication(Context context) {
        if (context == null) {
            return false;
        }
        return isSystemApplication(context, context.getPackageName());
    }

    public static void startInstalledAppDetails(Context context, String str) {
        Intent intent = new Intent();
        int i = Build.VERSION.SDK_INT;
        if (i >= 9) {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", str, null));
        } else {
            intent.setAction("android.intent.action.VIEW");
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra(i == 8 ? Config.INPUT_DEF_PKG : "com.android.settings.ApplicationPkgName", str);
        }
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public static final int uninstall(Context context, String str) {
        if (isSystemApplication(context) || ShellUtils.checkRootPermission()) {
            return uninstallSilent(context, str);
        }
        return uninstallNormal(context, str) ? 1 : -3;
    }

    public static boolean uninstallNormal(Context context, String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append("package:");
        sb.append(str);
        Intent intent = new Intent("android.intent.action.DELETE", Uri.parse(sb.toString()));
        intent.addFlags(268435456);
        context.startActivity(intent);
        return true;
    }

    public static int uninstallSilent(Context context, String str) {
        return uninstallSilent(context, str, true);
    }

    public static int installSilent(Context context, String str, String str2) {
        if (str != null && str.length() != 0) {
            File file = new File(str);
            if (file.length() > 0 && file.exists() && file.isFile()) {
                StringBuilder sb = new StringBuilder();
                sb.append("LD_LIBRARY_PATH=/vendor/lib:/system/lib pm install ");
                if (str2 == null) {
                    str2 = "";
                }
                sb.append(str2);
                sb.append(" ");
                sb.append(str.replace(" ", "\\ "));
                ShellUtils.CommandResult commandResultExecCommand = ShellUtils.execCommand(sb.toString(), !isSystemApplication(context), true);
                String str3 = commandResultExecCommand.successMsg;
                if (str3 != null && (str3.contains("Success") || commandResultExecCommand.successMsg.contains("success"))) {
                    return 1;
                }
                Log.e(TAG, "installSilent successMsg:" + commandResultExecCommand.successMsg + ", ErrorMsg:" + commandResultExecCommand.errorMsg);
                String str4 = commandResultExecCommand.errorMsg;
                if (str4 == null) {
                    return INSTALL_FAILED_OTHER;
                }
                if (str4.contains("INSTALL_FAILED_ALREADY_EXISTS")) {
                    return -1;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_INVALID_APK")) {
                    return -2;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_INVALID_URI")) {
                    return -3;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_INSUFFICIENT_STORAGE")) {
                    return -4;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_DUPLICATE_PACKAGE")) {
                    return -5;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_NO_SHARED_USER")) {
                    return -6;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_UPDATE_INCOMPATIBLE")) {
                    return -7;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_SHARED_USER_INCOMPATIBLE")) {
                    return -8;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_MISSING_SHARED_LIBRARY")) {
                    return -9;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_REPLACE_COULDNT_DELETE")) {
                    return -10;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_DEXOPT")) {
                    return -11;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_OLDER_SDK")) {
                    return -12;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_CONFLICTING_PROVIDER")) {
                    return -13;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_NEWER_SDK")) {
                    return -14;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_TEST_ONLY")) {
                    return -15;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_CPU_ABI_INCOMPATIBLE")) {
                    return -16;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_MISSING_FEATURE")) {
                    return -17;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_CONTAINER_ERROR")) {
                    return -18;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_INVALID_INSTALL_LOCATION")) {
                    return -19;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_MEDIA_UNAVAILABLE")) {
                    return -20;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_VERIFICATION_TIMEOUT")) {
                    return -21;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_VERIFICATION_FAILURE")) {
                    return -22;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_PACKAGE_CHANGED")) {
                    return -23;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_UID_CHANGED")) {
                    return -24;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_PARSE_FAILED_NOT_APK")) {
                    return -100;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_PARSE_FAILED_BAD_MANIFEST")) {
                    return INSTALL_PARSE_FAILED_BAD_MANIFEST;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_PARSE_FAILED_UNEXPECTED_EXCEPTION")) {
                    return INSTALL_PARSE_FAILED_UNEXPECTED_EXCEPTION;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_PARSE_FAILED_NO_CERTIFICATES")) {
                    return INSTALL_PARSE_FAILED_NO_CERTIFICATES;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES")) {
                    return INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_PARSE_FAILED_CERTIFICATE_ENCODING")) {
                    return INSTALL_PARSE_FAILED_CERTIFICATE_ENCODING;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME")) {
                    return INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_PARSE_FAILED_BAD_SHARED_USER_ID")) {
                    return INSTALL_PARSE_FAILED_BAD_SHARED_USER_ID;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_PARSE_FAILED_MANIFEST_MALFORMED")) {
                    return INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
                }
                if (commandResultExecCommand.errorMsg.contains("INSTALL_PARSE_FAILED_MANIFEST_EMPTY")) {
                    return INSTALL_PARSE_FAILED_MANIFEST_EMPTY;
                }
                return commandResultExecCommand.errorMsg.contains("INSTALL_FAILED_INTERNAL_ERROR") ? INSTALL_FAILED_INTERNAL_ERROR : INSTALL_FAILED_OTHER;
            }
        }
        return -3;
    }

    public static boolean isSystemApplication(Context context, String str) {
        if (context == null) {
            return false;
        }
        return isSystemApplication(context.getPackageManager(), str);
    }

    public static int uninstallSilent(Context context, String str, boolean z) {
        if (str == null || str.length() == 0) {
            return -3;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("LD_LIBRARY_PATH=/vendor/lib:/system/lib pm uninstall");
        sb.append(z ? " -k " : " ");
        sb.append(str.replace(" ", "\\ "));
        ShellUtils.CommandResult commandResultExecCommand = ShellUtils.execCommand(sb.toString(), !isSystemApplication(context), true);
        String str2 = commandResultExecCommand.successMsg;
        if (str2 != null && (str2.contains("Success") || commandResultExecCommand.successMsg.contains("success"))) {
            return 1;
        }
        Log.e(TAG, "uninstallSilent successMsg:" + commandResultExecCommand.successMsg + ", ErrorMsg:" + commandResultExecCommand.errorMsg);
        String str3 = commandResultExecCommand.errorMsg;
        return (str3 != null && str3.contains("Permission denied")) ? -4 : -1;
    }

    public static boolean isSystemApplication(PackageManager packageManager, String str) {
        if (packageManager != null && str != null && str.length() != 0) {
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
                return applicationInfo != null && (applicationInfo.flags & 1) > 0;
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public static int getAppVersionCode(Context context, String str) {
        PackageManager packageManager;
        if (context == null || (packageManager = context.getPackageManager()) == null) {
            return -1;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
            return -1;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return -1;
        }
    }
}
