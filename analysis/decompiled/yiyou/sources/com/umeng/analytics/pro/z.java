package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;

/* JADX INFO: compiled from: SPHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static z f3738a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Context f3739b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static String f3740c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3741d = "mobclick_agent_user_";

    /* JADX INFO: compiled from: SPHelper.java */
    private static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final z f3742a = new z();

        private a() {
        }
    }

    public static synchronized z a(Context context) {
        if (f3739b == null && context != null) {
            f3739b = context.getApplicationContext();
        }
        if (f3739b != null) {
            f3740c = context.getPackageName();
        }
        return a.f3742a;
    }

    private SharedPreferences e() {
        Context context = f3739b;
        if (context == null) {
            return null;
        }
        return context.getSharedPreferences(f3741d + f3740c, 0);
    }

    public void b() {
        SharedPreferences sharedPreferencesE = e();
        if (sharedPreferencesE != null) {
            sharedPreferencesE.edit().remove("au_p").remove("au_u").commit();
        }
    }

    public String c() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f3739b);
        if (sharedPreferences != null) {
            return sharedPreferences.getString("st", null);
        }
        return null;
    }

    public int d() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f3739b);
        if (sharedPreferences != null) {
            return sharedPreferences.getInt("vt", 0);
        }
        return 0;
    }

    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        SharedPreferences.Editor editorEdit = e().edit();
        editorEdit.putString("au_p", str);
        editorEdit.putString("au_u", str2);
        editorEdit.commit();
    }

    public String[] a() {
        SharedPreferences sharedPreferencesE = e();
        if (sharedPreferencesE == null) {
            return null;
        }
        String string = sharedPreferencesE.getString("au_p", null);
        String string2 = sharedPreferencesE.getString("au_u", null);
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
            return null;
        }
        return new String[]{string, string2};
    }

    public void a(String str) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f3739b);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString("st", str).commit();
        }
    }

    public void a(int i) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f3739b);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt("vt", i).commit();
        }
    }
}
