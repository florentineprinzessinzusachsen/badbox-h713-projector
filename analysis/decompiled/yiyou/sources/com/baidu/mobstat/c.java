package com.baidu.mobstat;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static void a(Context context) {
        b.f3482a.a(context);
        y.a(context).a(g.AP_LIST, System.currentTimeMillis());
    }

    public static void b(Context context, boolean z) {
        f.f3493a.a(context, z);
        y.a(context).a(z ? g.APP_TRACE_CURRENT : g.APP_TRACE_HIS, System.currentTimeMillis());
    }

    public static void a(Context context, boolean z) {
        e.f3492a.a(context, z);
        y.a(context).a(z ? g.APP_SYS_LIST : g.APP_USER_LIST, System.currentTimeMillis());
    }

    public static void b(Context context) {
        d.f3487a.a(context);
        y.a(context).a(g.APP_APK, System.currentTimeMillis());
    }
}
