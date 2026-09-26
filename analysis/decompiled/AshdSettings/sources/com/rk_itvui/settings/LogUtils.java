package com.rk_itvui.settings;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class LogUtils {
    public static final boolean DEBUG = true;

    public static synchronized void LOGD(String str, String str2) {
        Log.d(str, str2);
    }

    public static synchronized void LOGI(String str, String str2) {
        Log.i(str, str2);
    }
}
