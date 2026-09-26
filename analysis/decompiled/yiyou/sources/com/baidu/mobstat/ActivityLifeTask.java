package com.baidu.mobstat;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class ActivityLifeTask {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f3215a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static ActivityLifeObserver.IActivityLifeCallback f3216b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static ActivityLifeObserver.IActivityLifeCallback f3217c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static ActivityLifeObserver.IActivityLifeCallback f3218d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static ActivityLifeObserver.IActivityLifeCallback f3219e;

    private static synchronized void a(Context context) {
        f3216b = new AutoTrack.MyActivityLifeCallback(1);
        f3218d = new af.a();
        f3217c = new ah.a();
        f3219e = new AutoTrack.MyActivityLifeCallback(2);
    }

    public static synchronized void registerActivityLifeCallback(Context context) {
        if (f3215a) {
            return;
        }
        a(context);
        ActivityLifeObserver.instance().clearObservers();
        ActivityLifeObserver.instance().addObserver(f3216b);
        ActivityLifeObserver.instance().addObserver(f3219e);
        ActivityLifeObserver.instance().registerActivityLifeCallback(context);
        f3215a = true;
    }
}
