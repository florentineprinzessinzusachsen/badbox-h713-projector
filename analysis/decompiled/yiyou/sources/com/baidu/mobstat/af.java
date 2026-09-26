package com.baidu.mobstat;

import android.app.Activity;
import android.os.Bundle;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
public class af {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile boolean f3438a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile boolean f3439b = false;

    public static class a implements ActivityLifeObserver.IActivityLifeCallback {
        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityPaused(Activity activity) {
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityResumed(Activity activity) {
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityStarted(Activity activity) {
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityStopped(Activity activity) {
        }
    }

    public static JSONArray a() {
        return new JSONArray();
    }
}
