package com.hs.s;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.os.Process;
import com.hs.App;
import com.hs.common.utils.LOG;
import com.hs.common.utils.PROP;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public final class MS extends IntentService {
    private static final String TAG = "MS";
    private static AtomicLong mCounter = new AtomicLong();
    private ScreenReceiver mScreenReceiver = null;

    private class ScreenReceiver extends BroadcastReceiver {
        private boolean mInited;

        private ScreenReceiver() {
            this.mInited = false;
        }

        public synchronized void init() {
            if (!this.mInited) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                MS.this.registerReceiver(this, intentFilter);
                this.mInited = true;
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LOG.i(MS.TAG, MS.strEnv() + "[" + (intent == null ? "" : intent.getAction()) + "] on receive: i=" + intent);
        }

        public synchronized void uninit() {
            if (this.mInited) {
                MS.this.unregisterReceiver(this);
                this.mInited = false;
            }
        }
    }

    private String getIntentAction(Intent intent) {
        String action = intent != null ? intent.getAction() : "";
        return action != null ? action : "";
    }

    private String getIntentString(Intent intent, String str, String str2) {
        return (intent == null || !intent.hasExtra(str)) ? str2 : intent.getStringExtra(str);
    }

    private void initService() {
        AutoRunUtils.enable(this);
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                JS.schedule(this);
            } catch (Throwable th) {
                LOG.e(TAG, strEnv() + " schedule job service failed: " + th);
            }
        }
        if (this.mScreenReceiver == null) {
            ScreenReceiver screenReceiver = new ScreenReceiver();
            this.mScreenReceiver = screenReceiver;
            screenReceiver.init();
        }
    }

    public static void start(Context context, Intent intent, String str) {
        try {
            LOG.setEnabled(PROP.isLogEnabled());
            Intent intent2 = intent != null ? new Intent(intent) : new Intent();
            intent2.setClass(context, MS.class);
            intent2.putExtra("from", str);
            long jCurrentTimeMillis = System.currentTimeMillis();
            ComponentName componentNameStartService = context.startService(intent2);
            long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
            if (componentNameStartService == null) {
                LOG.e(TAG, strEnv() + "[" + intent + "][" + str + "] start MS failed");
                return;
            }
            LOG.i(TAG, strEnv() + "[" + intent + "][" + str + "] start MS done(" + jCurrentTimeMillis2 + "MS)");
        } catch (Throwable th) {
            LOG.e(TAG, strEnv() + "[" + intent + "][" + str + "] start MS failed: e=" + th, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String strEnv() {
        return "[P:" + Process.myPid() + " T:" + Process.myTid() + "]";
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // com.hs.s.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        LOG.setEnabled(PROP.isLogEnabled());
    }

    @Override // com.hs.s.IntentService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ScreenReceiver screenReceiver = this.mScreenReceiver;
        if (screenReceiver != null) {
            screenReceiver.uninit();
            this.mScreenReceiver = null;
        }
    }

    @Override // com.hs.s.IntentService
    protected void onHandleIntent(Intent intent, boolean z, long j) {
        LOG.i(TAG, strEnv() + "[" + getIntentAction(intent) + "][" + getIntentString(intent, "from", "") + "] on handle intent: count=" + mCounter.getAndIncrement() + ", init=" + z);
        if (!z) {
            initService();
        }
        new App().start(getApplicationContext());
    }
}
