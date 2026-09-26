package com.baidu.mobstat;

import android.content.Context;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class NativeCrashHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f3397a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Context f3398b;

    static {
        try {
            System.loadLibrary("crash_analysis");
            f3397a = true;
        } catch (Throwable unused) {
        }
    }

    private NativeCrashHandler() {
    }

    public static void doNativeCrash() {
        if (f3397a) {
            try {
                nativeException();
            } catch (Throwable unused) {
            }
        }
    }

    public static void init(Context context) {
        if (context == null) {
            return;
        }
        f3398b = context;
        if (f3397a) {
            File cacheDir = context.getCacheDir();
            if (cacheDir.exists() && cacheDir.isDirectory()) {
                try {
                    nativeInit(cacheDir.getAbsolutePath());
                } catch (Throwable unused) {
                }
            }
        }
    }

    private static native void nativeException();

    private static native void nativeInit(String str);

    private static native void nativeProcess(String str);

    private static native void nativeUnint();

    public static void onCrashCallbackFromNative(String str) {
        ExceptionAnalysis.getInstance().saveCrashInfo(f3398b, System.currentTimeMillis(), str, "NativeException", 1, 0);
    }

    public static void process(String str) {
        if (str == null || str.length() == 0 || !f3397a) {
            return;
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            try {
                nativeProcess(str);
            } catch (Throwable unused) {
            }
        }
    }

    public static void uninit() {
        if (f3397a) {
            try {
                nativeUnint();
            } catch (Throwable unused) {
            }
        }
    }
}
