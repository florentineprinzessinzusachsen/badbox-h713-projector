package com.umeng.commonsdk.statistics.internal;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.e;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.utils.UMUtils;

/* JADX INFO: compiled from: HeaderHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Context f4155a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f4156b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f4157c;

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.internal.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HeaderHelper.java */
    private static class C0090a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final a f4158a = new a();

        private C0090a() {
        }
    }

    public static a a(Context context) {
        if (f4155a == null && context != null) {
            f4155a = context.getApplicationContext();
        }
        return C0090a.f4158a;
    }

    private void d(String str) {
        try {
            this.f4156b = str.replaceAll("&=", " ").replaceAll("&&", " ").replaceAll("==", "/") + "/Android/" + Build.DISPLAY + "/" + Build.MODEL + "/" + Build.VERSION.RELEASE + " " + HelperUtils.getUmengMD5(UMUtils.getAppkey(f4155a));
        } catch (Throwable th) {
            UMCrashManager.reportCrash(f4155a, th);
        }
    }

    private void e(String str) {
        try {
            String str2 = str.split("&&")[0];
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            String[] strArrSplit = str2.split("&=");
            StringBuilder sb = new StringBuilder();
            sb.append(e.ax);
            for (String str3 : strArrSplit) {
                if (!TextUtils.isEmpty(str3)) {
                    String strSubstring = str3.substring(0, 2);
                    if (strSubstring.endsWith("=")) {
                        strSubstring = strSubstring.replace("=", "");
                    }
                    sb.append(strSubstring);
                }
            }
            this.f4157c = sb.toString();
        } catch (Throwable th) {
            UMCrashManager.reportCrash(f4155a, th);
        }
    }

    public boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("t");
    }

    public void c(String str) {
        String strSubstring = str.substring(0, str.indexOf(95));
        e(strSubstring);
        d(strSubstring);
    }

    private a() {
        this.f4156b = null;
        this.f4157c = null;
    }

    public String b() {
        return this.f4156b;
    }

    public boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("a");
    }

    public String a() {
        return this.f4157c;
    }
}
