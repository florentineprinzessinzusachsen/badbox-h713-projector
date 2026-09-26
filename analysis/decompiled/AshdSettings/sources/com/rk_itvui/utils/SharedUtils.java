package com.rk_itvui.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class SharedUtils {
    private static final String TAG = "SharedUtils";
    SharedPreferences mSharedPreferences;

    public SharedUtils(Context context) {
        this.mSharedPreferences = context.getSharedPreferences("bt_info", 0);
    }

    public String getString(String str) {
        String string = this.mSharedPreferences.getString(str, null);
        Log.e(TAG, "key=" + str + ",value=" + string);
        return string;
    }

    public void putString(String str, String str2) {
        this.mSharedPreferences.edit().putString(str, str2).apply();
    }

    public int getInt(String str) {
        return this.mSharedPreferences.getInt(str, 0);
    }

    public void putInt(String str, int i) {
        this.mSharedPreferences.edit().putInt(str, i).apply();
    }
}
