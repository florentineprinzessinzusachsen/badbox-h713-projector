package com.hs.p;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.os.Process;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class AS extends AccessibilityService {
    private static volatile OnASListener mAsListener;

    private static void D(String str) {
        Log.d("HS", strEnv() + "[AS] " + str);
    }

    public static void setASListener(OnASListener onASListener) {
        D("set AS listener ...");
        mAsListener = onASListener;
    }

    private static String strEnv() {
        return "[P:" + Process.myPid() + " T:" + Process.myTid() + "]";
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        try {
            if (mAsListener != null) {
                mAsListener.onAccessibilityEvent(this, accessibilityEvent);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        D("on destroy ...");
        mAsListener = null;
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onInterrupt() {
        D("on interrupt ...");
        mAsListener = null;
    }

    @Override // android.accessibilityservice.AccessibilityService
    protected void onServiceConnected() {
        super.onServiceConnected();
        D("on service connected ...");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }
}
