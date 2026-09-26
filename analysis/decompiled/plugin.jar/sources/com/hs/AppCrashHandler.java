package com.hs;

import android.app.Application;
import com.hs.common.utils.Logger;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class AppCrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "AppCrashHandler";
    private final Application mApplication;
    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;

    public AppCrashHandler(Application application) {
        this.mDefaultExceptionHandler = null;
        this.mApplication = application;
        try {
            this.mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        } catch (Throwable th) {
            Logger.append(this.mApplication, TAG, "init crash handler failed: " + th);
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        Logger.append(this.mApplication, TAG, "thread(" + thread + ") uncaught exception", th);
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.mDefaultExceptionHandler;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }
}
