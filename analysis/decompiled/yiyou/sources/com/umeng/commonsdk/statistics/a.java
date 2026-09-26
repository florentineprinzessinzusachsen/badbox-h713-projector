package com.umeng.commonsdk.statistics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.statistics.common.d;

/* JADX INFO: compiled from: AnalyticsConfig.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f4058a = "native";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f4059b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f4060c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static String f4061d;

    public static String a(Context context) {
        if (TextUtils.isEmpty(f4061d)) {
            f4061d = d.a(context).b();
        }
        return f4061d;
    }
}
