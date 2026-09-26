package com.rk_itvui.settings;

import android.app.Activity;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

/* JADX INFO: loaded from: classes.dex */
public class WindowHelper {
    private static int mScreenHeight = -1;
    private static int mScreenWidth = -1;

    public static void setFullScreen(Window window) {
        if (Build.VERSION.SDK_INT <= 14) {
            return;
        }
        ReflectionUtils.invokeMethod(window.getDecorView(), "setSystemUiVisibility", new Class[]{Integer.TYPE}, Integer.valueOf(getViewStaticProperty("SYSTEM_UI_FLAG_FULLSCREEN") | getViewStaticProperty("SYSTEM_UI_FLAG_HIDE_NAVIGATION") | getViewStaticProperty("SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN") | getViewStaticProperty("SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION") | getViewStaticProperty("SYSTEM_UI_FLAG_IMMERSIVE_STICKY")));
    }

    private static int getViewStaticProperty(String str) {
        Object staticFieldValue = ReflectionUtils.getStaticFieldValue("android.view.View", str);
        if (staticFieldValue == null) {
            return 0;
        }
        return ((Integer) staticFieldValue).intValue();
    }

    public static Display getWindowDisplay(Window window) {
        return window.getWindowManager().getDefaultDisplay();
    }

    public static Display getWindowDisplay(Activity activity) {
        return activity.getWindowManager().getDefaultDisplay();
    }

    public static int getWinWidth(Activity activity) {
        return getWidth(activity.getWindowManager());
    }

    public static int getWinWidth(Window window) {
        return getWidth(window.getWindowManager());
    }

    private static int getWidth(WindowManager windowManager) {
        if (mScreenWidth == -1) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            mScreenWidth = displayMetrics.widthPixels;
            mScreenHeight = displayMetrics.heightPixels;
        }
        return mScreenWidth;
    }

    public static int getWinHeight(Activity activity) {
        return getHeight(activity.getWindowManager());
    }

    public static int getWinHeight(Window window) {
        return getHeight(window.getWindowManager());
    }

    private static int getHeight(WindowManager windowManager) {
        if (mScreenHeight == -1) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            mScreenWidth = displayMetrics.widthPixels;
            mScreenHeight = displayMetrics.heightPixels;
        }
        return mScreenHeight;
    }
}
