package com.umeng.analytics.pro;

import com.umeng.analytics.AnalyticsConfig;

/* JADX INFO: compiled from: CrashHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class l implements Thread.UncaughtExceptionHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Thread.UncaughtExceptionHandler f3699a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private p f3700b;

    public l() {
        if (Thread.getDefaultUncaughtExceptionHandler() == this) {
            return;
        }
        this.f3699a = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void a(p pVar) {
        this.f3700b = pVar;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        a(th);
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f3699a;
        if (uncaughtExceptionHandler == null || uncaughtExceptionHandler == Thread.getDefaultUncaughtExceptionHandler()) {
            return;
        }
        this.f3699a.uncaughtException(thread, th);
    }

    private void a(Throwable th) {
        if (AnalyticsConfig.CATCH_EXCEPTION) {
            this.f3700b.a(th);
        } else {
            this.f3700b.a(null);
        }
    }
}
