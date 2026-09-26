package com.baidu.mobstat;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class av extends as {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3479a = "__Baidu_Stat_SDK_SendRem";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static av f3480b = new av();

    private av() {
    }

    public static av a() {
        return f3480b;
    }

    public int b(Context context) {
        return a(context, "sendLogtype", 0);
    }

    public int c(Context context) {
        return a(context, "timeinterval", 1);
    }

    public boolean d(Context context) {
        return a(context, "onlywifi", false);
    }

    public String e(Context context) {
        return a(context, "device_id_1", (String) null);
    }

    public String f(Context context) {
        return a(context, "setchannelwithcodevalue", (String) null);
    }

    public boolean g(Context context) {
        return a(context, "setchannelwithcode", false);
    }

    public String h(Context context) {
        return a(context, "mtjsdkmacss2_1", (String) null);
    }

    public boolean i(Context context) {
        return a(context, "mtjtv", false);
    }

    public String j(Context context) {
        return a(context, "mtjsdkmacsstv_1", (String) null);
    }

    public String k(Context context) {
        return a(context, "he.ext", (String) null);
    }

    public String l(Context context) {
        return a(context, "he.push", (String) null);
    }

    public boolean m(Context context) {
        return a(context, "mtjsdkmactrick", true);
    }

    public String n(Context context) {
        return a(context, "custom_userid", "");
    }

    public String o(Context context) {
        return a(context, "last_custom_userid", "");
    }

    public String p(Context context) {
        return a(context, "encrypt_device_id", "");
    }

    public String q(Context context) {
        return a(context, Config.USER_PROPERTY, "");
    }

    @Override // com.baidu.mobstat.as
    public SharedPreferences a(Context context) {
        return context.getSharedPreferences(f3479a, 0);
    }

    public void b(Context context, int i) {
        b(context, "timeinterval", i);
    }

    public void c(Context context, boolean z) {
        b(context, "mtjtv", z);
    }

    public void d(Context context, String str) {
        b(context, "setchannelwithcodevalue", str);
    }

    public void e(Context context, String str) {
        b(context, "mtjsdkmacss2_1", str);
    }

    public void f(Context context, String str) {
        b(context, "mtjsdkmacsstv_1", str);
    }

    public void g(Context context, String str) {
        b(context, "he.ext", str);
    }

    public void h(Context context, String str) {
        b(context, "he.push", str);
    }

    public void i(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        b(context, "custom_userid", str);
    }

    public void j(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        b(context, "last_custom_userid", str);
    }

    public void k(Context context, String str) {
        b(context, "encrypt_device_id", str);
    }

    public void l(Context context, String str) {
        b(context, Config.USER_PROPERTY, str);
    }

    public void a(Context context, int i) {
        b(context, "sendLogtype", i);
    }

    public void b(Context context, String str) {
        if (a(context, "cuid", (String) null) != null) {
            c(context, "cuid");
        }
        b(context, "cuidsec_1", str);
        c(context, "cuidsec_1");
        c(context, "cuidsec_1");
        c(context, "cuidsec_2");
    }

    public void d(Context context, boolean z) {
        b(context, "mtjsdkmactrick", z);
    }

    public void a(Context context, boolean z) {
        b(context, "onlywifi", z);
    }

    public void a(Context context, String str) {
        b(context, "device_id_1", str);
    }

    public void b(Context context, boolean z) {
        b(context, "setchannelwithcode", z);
    }
}
