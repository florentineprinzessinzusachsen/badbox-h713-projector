package com.hotota.p.d.common.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class TextUtils {
    public static boolean equals(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        return str.equals(str2);
    }

    public static boolean equalsIgnoreCase(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equalsIgnoreCase(str2);
    }

    public static boolean empty(String str) {
        return str == null || str.length() <= 0;
    }

    public static boolean empty(byte[] bArr) {
        return bArr == null || bArr.length <= 0;
    }

    public static <T> boolean empty(T[] tArr) {
        return tArr == null || tArr.length <= 0;
    }

    public static String strTime(long j) {
        try {
            return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault()).format(Long.valueOf(j));
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String tidy(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            char[] charArray = str.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                if (31 < charArray[i] && charArray[i] < 127) {
                    stringBuffer.append(charArray[i]);
                }
            }
        } catch (Throwable unused) {
        }
        return stringBuffer.toString();
    }

    public static boolean contains(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.contains(str2);
    }
}
