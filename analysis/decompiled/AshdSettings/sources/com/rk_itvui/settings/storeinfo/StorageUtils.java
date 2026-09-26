package com.rk_itvui.settings.storeinfo;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class StorageUtils {
    public static boolean DEBUG = false;
    public static String TAG = "StorageUtils.java";

    public static void LOG(String str) {
        if (DEBUG) {
            Log.i(TAG, str);
        }
    }

    @SuppressLint({"ObsoleteSdkInt"})
    public static String getFlashDir() {
        if (Build.VERSION.SDK_INT == 9) {
            return ((File) invokeStaticMethod("android.os.Environment", "getFlashStorageDirectory", new Object[0])).getPath();
        }
        if (Build.VERSION.SDK_INT >= 9) {
            return ((File) invokeStaticMethod("android.os.Environment", "getExternalStorageDirectory", new Object[0])).getPath();
        }
        return null;
    }

    @SuppressLint({"ObsoleteSdkInt"})
    public static String getFlashState() {
        if (Build.VERSION.SDK_INT == 9) {
            return (String) invokeStaticMethod("android.os.Environment", "getFlashStorageState", new Object[0]);
        }
        return Build.VERSION.SDK_INT >= 9 ? (String) invokeStaticMethod("android.os.Environment", "getExternalStorageState", new Object[0]) : "removed";
    }

    @SuppressLint({"ObsoleteSdkInt"})
    public static String getAction() {
        LOG("getAction(),android.os.Build.VERSION.SDK_INT = " + Build.VERSION.SDK_INT + ",android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1 = 15");
        if (Build.VERSION.SDK_INT == 16) {
            return "com.rk.settins.basicSetting.wifi4.1";
        }
        if (Build.VERSION.SDK_INT == 15) {
            return "com.rk.settins.basicSetting.wifi4.03";
        }
        if (Build.VERSION.SDK_INT == 17) {
            return "com.rk.settins.basicSetting.wifi4.2";
        }
        return null;
    }

    public static Object invokeStaticMethod(Class<?> cls, String str, Object... objArr) {
        Class<?>[] clsArr;
        if (objArr != null) {
            try {
                clsArr = new Class[objArr.length];
                for (int i = 0; i < objArr.length; i++) {
                    clsArr[i] = objArr[i].getClass();
                }
            } catch (Exception e) {
                LOG("Invoke method error. " + e.getMessage());
                return null;
            }
        } else {
            clsArr = null;
        }
        return cls.getMethod(str, clsArr).invoke(null, objArr);
    }

    public static Object invokeStaticMethod(String str, String str2, Object... objArr) {
        try {
            return invokeStaticMethod(Class.forName(str), str2, objArr);
        } catch (Exception e) {
            LOG("Invoke method error. " + e.getMessage());
            return null;
        }
    }

    public static Object invokeStaticMethod(String str, String str2, Class<?>[] clsArr, Object... objArr) {
        try {
            return invokeStaticMethod(Class.forName(str), str2, clsArr, objArr);
        } catch (Exception e) {
            LOG("Invoke method error. " + e.getMessage());
            return null;
        }
    }

    public static Object invokeStaticMethod(Class<?> cls, String str, Class<?>[] clsArr, Object... objArr) {
        try {
            return cls.getMethod(str, clsArr).invoke(null, objArr);
        } catch (Exception e) {
            LOG("Invoke method error. " + e.getMessage());
            return null;
        }
    }
}
