package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public class AutoTrack {
    public static final int JOB_TYPE_BGSEND = 2;
    public static final int JOB_TYPE_SESSIONTIME = 1;

    public static class MyActivityLifeCallback implements ActivityLifeObserver.IActivityLifeCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f3220a;

        public MyActivityLifeCallback(int i) {
            this.f3220a = i;
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityPaused(Activity activity) {
            Context applicationContext = activity.getApplicationContext();
            int i = this.f3220a;
            if (i == 1) {
                BDStatCore.instance().autoTrackSessionEndTime(applicationContext);
            } else if (i == 2) {
                BDStatCore.instance().doSendLogCheck(applicationContext);
            }
        }

        @Override // com.baidu.mobstat.ActivityLifeObserver.IActivityLifeCallback
        public void onActivityResumed(Activity activity) {
            Context applicationContext = activity.getApplicationContext();
            int i = this.f3220a;
            if (i == 1) {
                BDStatCore.instance().autoTrackSessionStartTime(applicationContext);
            } else if (i == 2) {
                BDStatCore.instance().cancelSendLogCheck();
            }
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
}
