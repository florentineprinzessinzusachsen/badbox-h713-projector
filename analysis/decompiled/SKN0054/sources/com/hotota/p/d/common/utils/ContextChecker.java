package com.hotota.p.d.common.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class ContextChecker {
    private static final String TAG = "ContextChecker";

    public static void logContext(Context context) {
        if (context == null) {
            Log.e(TAG, "传入的 context == null");
            return;
        }
        Log.d(TAG, "Context class = " + context.getClass().getName());
        boolean z = context instanceof Activity;
        if (z) {
            Log.d(TAG, "当前 context 是 Activity: " + context);
        } else {
            Log.d(TAG, "当前 context 不是 Activity");
        }
        boolean z2 = context instanceof Application;
        if (z2) {
            Log.d(TAG, "当前 context 是 Application");
        }
        if (z || z2) {
            return;
        }
        Log.d(TAG, "当前 context 是 ContextWrapper 或 Service，class=" + context.getClass().getName());
    }
}
