package com.baidu.mobstat;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
class ae extends as {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3436a = "baidu_mtj_sdk_record";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static ae f3437b = new ae();

    private ae() {
    }

    public static ae a() {
        return f3437b;
    }

    public Long b(Context context) {
        return Long.valueOf(a(context, "session_first_visit_time", 0L));
    }

    public Long c(Context context) {
        return Long.valueOf(a(context, "session_last_visit_time", 0L));
    }

    public Long d(Context context) {
        return Long.valueOf(a(context, "session_visit_interval", 0L));
    }

    public String e(Context context) {
        return a(context, "session_today_visit_count", "");
    }

    public String f(Context context) {
        return a(context, "session_recent_visit", "");
    }

    @Override // com.baidu.mobstat.as
    public SharedPreferences a(Context context) {
        return android.os.Build.VERSION.SDK_INT >= 11 ? context.getSharedPreferences(f3436a, 4) : context.getSharedPreferences(f3436a, 0);
    }

    public void b(Context context, long j) {
        b(context, "session_last_visit_time", j);
    }

    public void c(Context context, long j) {
        b(context, "session_visit_interval", j);
    }

    public void b(Context context, String str) {
        b(context, "session_recent_visit", str);
    }

    public void a(Context context, long j) {
        b(context, "session_first_visit_time", j);
    }

    public void a(Context context, String str) {
        b(context, "session_today_visit_count", str);
    }
}
