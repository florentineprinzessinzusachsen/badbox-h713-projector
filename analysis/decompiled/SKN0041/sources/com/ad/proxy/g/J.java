package com.ad.proxy.g;

import android.content.SharedPreferences;
import java.util.prefs.Preferences;

/* JADX INFO: loaded from: classes.dex */
public abstract class J {
    public static final boolean a = B.a();
    public static SharedPreferences b;
    public static Preferences c;

    public static String a() {
        if (a) {
            SharedPreferences sharedPreferences = b;
            if (sharedPreferences != null) {
                return sharedPreferences.getString("device_id", "");
            }
            throw new IllegalStateException("请先调用 PreferencesUtil.initFromAndroid(Context)");
        }
        Preferences preferences = c;
        if (preferences != null) {
            return preferences.get("device_id", "");
        }
        throw new IllegalStateException("请先调用 PreferencesUtil.initFromJava()");
    }

    public static void a(String str) {
        if (a) {
            SharedPreferences sharedPreferences = b;
            if (sharedPreferences != null) {
                sharedPreferences.edit().putString("device_id", str).apply();
                return;
            }
            throw new IllegalStateException("请先调用 PreferencesUtil.initFromAndroid(Context)");
        }
        Preferences preferences = c;
        if (preferences != null) {
            preferences.put("device_id", str);
            return;
        }
        throw new IllegalStateException("请先调用 PreferencesUtil.initFromJava()");
    }
}
