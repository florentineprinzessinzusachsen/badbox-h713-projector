package com.hs.common.utils;

import android.os.Environment;
import java.io.File;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class PROP {
    private static boolean empty(String str) {
        return str == null || str.length() <= 0;
    }

    private static boolean equalsIgnoreCase(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equalsIgnoreCase(str2);
    }

    private static boolean getValueAsBoolean(String str, boolean z) {
        return readPropertyAsBoolean(str, z) || isFileExist(str);
    }

    public static boolean ignoreCheckMode() {
        return getValueAsBoolean("debug.hs.ignore.checkmode", false);
    }

    public static boolean isBeta() {
        return getValueAsBoolean("debug.hs.beta", false);
    }

    public static boolean isExpDir() {
        return getValueAsBoolean("debug.hs.expdir", false);
    }

    private static boolean isFileExist(String str) {
        try {
            return new File(Environment.getExternalStorageDirectory(), str).exists();
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isLogEnabled() {
        return getValueAsBoolean("debug.hs.log.enabled", false);
    }

    private static String readProperty(String str, String str2) {
        try {
            Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class);
            declaredMethod.setAccessible(true);
            return (String) declaredMethod.invoke(null, str);
        } catch (Throwable unused) {
            return str2;
        }
    }

    private static boolean readPropertyAsBoolean(String str, boolean z) {
        try {
            String property = readProperty(str, "");
            return empty(property) ? z : equalsIgnoreCase(property, "1");
        } catch (Throwable unused) {
            return z;
        }
    }
}
