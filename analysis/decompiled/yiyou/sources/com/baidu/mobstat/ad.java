package com.baidu.mobstat;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
class ad implements Thread.UncaughtExceptionHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ad f3433a = new ad();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Thread.UncaughtExceptionHandler f3434b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f3435c;

    private ad() {
    }

    public static ad a() {
        return f3433a;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        ExceptionAnalysis.getInstance().saveCrashInfo(this.f3435c, th, true);
        if (this.f3434b.equals(this)) {
            return;
        }
        this.f3434b.uncaughtException(thread, th);
    }

    public void a(Context context) {
        this.f3435c = context;
        if (this.f3434b == null) {
            this.f3434b = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }
}
