package com.szns.sdk;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Base64;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class t {
    public static String a(String str) {
        String str2 = new String(Base64.decode(str.getBytes(), 2));
        String strA = a(str2.substring(str2.length() - 13), "119aca32a5360e2884c767ceb10889b0");
        return new String(Base64.decode(a(str2.substring(0, str2.length() - 26), v.a(a(str2.substring(str2.length() - 26, str2.length() - 13), strA) + strA + "119aca32a5360e2884c767ceb10889b0").toLowerCase()).getBytes(), 2));
    }

    private static String a(String str, String str2) {
        char[] charArray = str.toCharArray();
        char[] charArray2 = str2.toCharArray();
        char[] cArr = new char[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            cArr[i] = (char) (charArray[i] ^ charArray2[i % str2.length()]);
        }
        return new String(cArr);
    }

    public static boolean a(Context context) {
        try {
            return b(context).contains("android.permission.INTERNET");
        } catch (Exception unused) {
            return true;
        }
    }

    private static List b(Context context) {
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            return strArr == null ? Collections.emptyList() : Arrays.asList(strArr);
        } catch (PackageManager.NameNotFoundException unused) {
            return Collections.emptyList();
        }
    }

    public static boolean b(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.equals("0.0.0.0") || str.matches("^10\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$") || str.matches("^172\\.(1[6-9]|2\\d|3[0-1])\\.\\d{1,3}\\.\\d{1,3}$") || str.matches("^192\\.168\\.\\d{1,3}\\.\\d{1,3}$") || str.matches("^127\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$");
    }
}
