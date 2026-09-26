package com.android.settingslib;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.telephony.PhoneNumberUtils;
import android.telephony.SubscriptionInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class DeviceInfoUtils {
    private static final String FILENAME_MSV = "/sys/board_properties/soc/msv";
    private static final String FILENAME_PROC_VERSION = "/proc/version";
    private static final String TAG = "DeviceInfoUtils";

    private static String readLine(String str) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(str), 256);
        try {
            return bufferedReader.readLine();
        } finally {
            bufferedReader.close();
        }
    }

    public static String getFormattedKernelVersion() {
        try {
            return formatKernelVersion(readLine(FILENAME_PROC_VERSION));
        } catch (IOException e) {
            Log.e(TAG, "IO Exception when getting kernel version for Device Info screen", e);
            return "Unavailable";
        }
    }

    public static String formatKernelVersion(String str) {
        Matcher matcher = Pattern.compile("Linux version (\\S+) \\((\\S+?)\\) (?:\\(gcc.+? \\)) (#\\d+) (?:.*?)?((Sun|Mon|Tue|Wed|Thu|Fri|Sat).+)").matcher(str);
        if (!matcher.matches()) {
            Log.e(TAG, "Regex did not match on /proc/version: " + str);
            return "Unavailable";
        }
        if (matcher.groupCount() < 4) {
            Log.e(TAG, "Regex match on /proc/version only returned " + matcher.groupCount() + " groups");
            return "Unavailable";
        }
        return matcher.group(1) + "\n" + matcher.group(2) + " " + matcher.group(3) + "\n" + matcher.group(4);
    }

    public static String getMsvSuffix() {
        try {
            return Long.parseLong(readLine(FILENAME_MSV), 16) == 0 ? " (ENGINEERING)" : "";
        } catch (IOException | NumberFormatException unused) {
            return "";
        }
    }

    public static String getFeedbackReporterPackage(Context context) {
        String string = context.getResources().getString(R.string.oem_preferred_feedback_reporter);
        if (TextUtils.isEmpty(string)) {
            return string;
        }
        Intent intent = new Intent("android.intent.action.BUG_REPORT");
        PackageManager packageManager = context.getPackageManager();
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 64)) {
            if (resolveInfo.activityInfo != null && !TextUtils.isEmpty(resolveInfo.activityInfo.packageName)) {
                try {
                    if ((packageManager.getApplicationInfo(resolveInfo.activityInfo.packageName, 0).flags & 1) != 0 && TextUtils.equals(resolveInfo.activityInfo.packageName, string)) {
                        return string;
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                    continue;
                }
            }
        }
        return null;
    }

    public static String getSecurityPatch() {
        String str = Build.VERSION.SECURITY_PATCH;
        if ("".equals(str)) {
            return null;
        }
        try {
            return DateFormat.format(DateFormat.getBestDateTimePattern(Locale.getDefault(), "dMMMMyyyy"), new SimpleDateFormat("yyyy-MM-dd").parse(str)).toString();
        } catch (ParseException unused) {
            return str;
        }
    }

    public static String getFormattedPhoneNumber(Context context, SubscriptionInfo subscriptionInfo) {
        if (subscriptionInfo != null) {
            String line1Number = ((TelephonyManager) context.getSystemService("phone")).getLine1Number(subscriptionInfo.getSubscriptionId());
            if (!TextUtils.isEmpty(line1Number)) {
                return PhoneNumberUtils.formatNumber(line1Number);
            }
        }
        return null;
    }

    public static String getFormattedPhoneNumbers(Context context, List<SubscriptionInfo> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            int size = list.size();
            for (int i = 0; i < size; i++) {
                String line1Number = telephonyManager.getLine1Number(list.get(i).getSubscriptionId());
                if (!TextUtils.isEmpty(line1Number)) {
                    sb.append(PhoneNumberUtils.formatNumber(line1Number));
                    if (i < size - 1) {
                        sb.append("\n");
                    }
                }
            }
        }
        return sb.toString();
    }
}
