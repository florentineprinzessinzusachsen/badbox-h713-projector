package com.baidu.mobstat;

/* JADX INFO: loaded from: classes.dex */
public final class aa {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f3422a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f3423b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f3424c;

    static {
        f3423b = android.os.Build.VERSION.SDK_INT < 9 ? "http://datax.baidu.com/xs.gif" : "https://datax.baidu.com/xs.gif";
        f3424c = android.os.Build.VERSION.SDK_INT < 9 ? "http://dxp.baidu.com/upgrade" : "https://dxp.baidu.com/upgrade";
    }
}
