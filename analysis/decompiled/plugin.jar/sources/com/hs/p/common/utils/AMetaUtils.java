package com.hs.p.common.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class AMetaUtils {
    public static String getMetaAsString(Context context, String str, String str2) {
        return removePrefix(str, getRawMetaAsString(context, str, str2));
    }

    public static String getRawMetaAsString(Context context, String str, String str2) {
        Bundle bundle;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && (bundle = applicationInfo.metaData) != null && bundle.containsKey(str)) {
                return String.valueOf(applicationInfo.metaData.get(str));
            }
        } catch (Throwable unused) {
        }
        return str2;
    }

    private static String removePrefix(String str, String str2) {
        return (ObjUtils.empty(str2) || !str2.startsWith(str)) ? str2 : str2.replace(str, "");
    }
}
