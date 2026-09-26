package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.h;
import com.umeng.analytics.pro.z;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.utils.UMUtils;

/* JADX INFO: loaded from: classes.dex */
public class AnalyticsConfig {
    public static String GPU_RENDERER = "";
    public static String GPU_VENDER = "";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f3546b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static String f3547c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static String f3548d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static int f3549e;
    public static String mWrapperType;
    public static String mWrapperVersion;
    public static MobclickAgent.PageMode AUTO_ACTIVITY_PAGE_COLLECTION = MobclickAgent.PageMode.LEGACY_AUTO;
    public static boolean CHANGE_CATCH_EXCEPTION_NOTALLOW = false;
    public static boolean CATCH_EXCEPTION = true;
    public static long kContinueSessionMillis = com.umeng.commonsdk.proguard.c.f3956d;
    public static boolean CLEAR_EKV_BL = false;
    public static boolean CLEAR_EKV_WL = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static double[] f3545a = null;

    static void a(String str) {
        f3547c = str;
    }

    public static String getAppkey(Context context) {
        return UMUtils.getAppkey(context);
    }

    public static String getChannel(Context context) {
        return UMUtils.getChannel(context);
    }

    public static double[] getLocation() {
        return f3545a;
    }

    public static String getSecretKey(Context context) {
        if (TextUtils.isEmpty(f3548d)) {
            f3548d = z.a(context).c();
        }
        return f3548d;
    }

    public static int getVerticalType(Context context) {
        if (f3549e == 0) {
            f3549e = z.a(context).d();
        }
        return f3549e;
    }

    static void a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            UMLog.aq(h.A, 0, "\\|");
        } else {
            f3548d = str;
            z.a(context).a(f3548d);
        }
    }

    static void a(Context context, int i) {
        f3549e = i;
        z.a(context).a(f3549e);
    }
}
