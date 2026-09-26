package com.rk_itvui.settings.system;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.UserManager;
import android.util.Log;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class FallbackHome extends Activity {
    private static final String TAG = "FallbackHome";
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.rk_itvui.settings.system.FallbackHome.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.d(FallbackHome.TAG, "onReceive=" + intent.getAction());
            FallbackHome.this.maybeFinish();
        }
    };
    private Handler mHandler = new Handler() { // from class: com.rk_itvui.settings.system.FallbackHome.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            FallbackHome.this.maybeFinish();
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        registerReceiver(this.mReceiver, new IntentFilter("android.intent.action.USER_UNLOCKED"));
        maybeFinish();
        Log.d(TAG, "onCreate!");
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mReceiver);
        Log.d(TAG, "onDestroy!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeFinish() {
        if (((UserManager) getSystemService(UserManager.class)).isUserUnlocked()) {
            if (Objects.equals(getPackageName(), getPackageManager().resolveActivity(new Intent("android.intent.action.MAIN").addCategory("android.intent.category.HOME"), 0).activityInfo.packageName)) {
                Log.d(TAG, "User unlocked but no home; let's hope someone enables one soon?");
                this.mHandler.sendEmptyMessageDelayed(0, 500L);
            } else {
                Log.d(TAG, "User unlocked and real home found; let's go!");
                finish();
            }
        }
    }
}
