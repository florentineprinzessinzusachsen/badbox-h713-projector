package com.tools;

import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f51a = "ParserUtils_SystemPropertiesInvoke";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Method f52b;

    public static int a(String str, int i) {
        try {
            if (f52b == null) {
                f52b = Class.forName("android.os.SystemProperties").getMethod("getInt", String.class, Integer.TYPE);
            }
            return ((Integer) f52b.invoke(null, str, Integer.valueOf(i))).intValue();
        } catch (Exception e) {
            Log.e(f51a, "Platform error: " + e.toString());
            return i;
        }
    }

    public static String b(String str, String str2) {
        try {
            String str3 = (String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class).invoke(null, str);
            return !TextUtils.isEmpty(str3) ? str3 : str2;
        } catch (Exception unused) {
            Log.d(f51a, "Unable to read system properties");
        }
    }

    public static void c(String str, String str2) {
        try {
            Class.forName("android.os.SystemProperties").getDeclaredMethod("set", String.class, String.class).invoke(null, str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(f51a, "Unable to set system properties");
        }
    }
}
