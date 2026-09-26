package com.hs.p.basic;

import android.content.Context;
import android.content.SharedPreferences;
import com.hs.p.common.utils.TextUtils;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class SPUtils {
    private static int asIntOf(String str, int i) {
        try {
            if (!TextUtils.empty(str)) {
                return Integer.parseInt(str);
            }
        } catch (Exception unused) {
        }
        return i;
    }

    private static long asLongOf(String str, long j) {
        try {
            if (!TextUtils.empty(str)) {
                return Long.parseLong(str);
            }
        } catch (Exception unused) {
        }
        return j;
    }

    public static boolean clearAll(Context context, String str) {
        try {
            return sprefs(context, str).edit().clear().commit();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean getBoolean(Context context, String str, String str2, boolean z) {
        String string = getString(context, str, str2, "");
        return !TextUtils.empty(string) ? TextUtils.equals(string, "1") : z;
    }

    public static int getInt(Context context, String str, String str2, int i) {
        String string = getString(context, str, str2, "");
        return !TextUtils.empty(string) ? asIntOf(string, i) : i;
    }

    public static long getLong(Context context, String str, String str2, long j) {
        String string = getString(context, str, str2, "");
        return !TextUtils.empty(string) ? asLongOf(string, j) : j;
    }

    public static String getString(Context context, String str, String str2, String str3) {
        try {
            return sprefs(context, str).getString(str2, str3);
        } catch (Exception unused) {
            return str3;
        }
    }

    public static boolean putBoolean(Context context, String str, String str2, boolean z) {
        return putString(context, str, str2, z ? "1" : "0");
    }

    public static boolean putInt(Context context, String str, String str2, int i) {
        return putString(context, str, str2, "" + i);
    }

    public static boolean putLong(Context context, String str, String str2, long j) {
        return putString(context, str, str2, "" + j);
    }

    public static boolean putString(Context context, String str, String str2, String str3) {
        try {
            SharedPreferences.Editor editorEdit = sprefs(context, str).edit();
            if (str3 == null) {
                str3 = "";
            }
            editorEdit.putString(str2, str3);
            return editorEdit.commit();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean remove(Context context, String str, String str2) {
        try {
            return sprefs(context, str).edit().remove(str2).commit();
        } catch (Exception unused) {
            return false;
        }
    }

    private static SharedPreferences sprefs(Context context, String str) {
        return context.getSharedPreferences(str, 0);
    }
}
