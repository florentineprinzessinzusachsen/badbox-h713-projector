package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public final class bc {
    public static String a(Context context) {
        String strA;
        try {
            strA = b.a.a.a.b.a.a(context);
        } catch (Throwable unused) {
            strA = "";
        }
        return TextUtils.isEmpty(strA) ? "" : strA;
    }

    public static HashMap<String, String> a(Map<String, String> map) {
        if (map != null) {
            return new HashMap<>(map);
        }
        return null;
    }

    public static String a(long j) {
        Date date = new Date(j);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        return simpleDateFormat.format(date);
    }

    public static boolean a(Class<?> cls, String str) {
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            String methodName = stackTraceElement.getMethodName();
            if (!TextUtils.isEmpty(methodName) && cls != null && methodName.equals(str)) {
                Class<?> cls2 = null;
                try {
                    cls2 = Class.forName(stackTraceElement.getClassName());
                } catch (Throwable unused) {
                }
                if (cls2 != null && cls.isAssignableFrom(cls2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        return str.length() > i ? str.substring(0, i) : str;
    }
}
