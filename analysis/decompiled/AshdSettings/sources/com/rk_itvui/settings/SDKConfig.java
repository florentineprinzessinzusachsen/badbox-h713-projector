package com.rk_itvui.settings;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public class SDKConfig {
    public static boolean getIsAndroid23() {
        return Build.VERSION.SDK_INT == 9;
    }

    public static boolean getIsAndroid40() {
        return Build.VERSION.SDK_INT >= 9;
    }
}
