package com.hs;

import android.content.Context;
import android.content.pm.PackageManager;
import com.hs.cld.CLD;
import com.hs.common.utils.LOG;
import com.hs.common.utils.PROP;
import com.hs.q.Q;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class App {
    private static final String TAG = "hslib.App";
    private Context mApplicationContext;

    private void enableUtilsLog() {
        try {
            Class.forName("com.hs.p.common.utils.LOG").getMethod("setEnabled", Boolean.TYPE).invoke(null, Boolean.valueOf(PROP.isLogEnabled()));
            LOG.i(TAG, "Successfully enabled ota-tools/utils LOG");
        } catch (Exception e) {
            LOG.w(TAG, "Failed to enable ota-tools/utils LOG: " + e.getMessage());
        }
    }

    private String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            LOG.e(TAG, "Failed to get version name.", e);
            return "Unknown";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$start$0() {
        LOG.i(TAG, "Dynamic build mode: Proceeding with dynamic loading.");
        loadPlugins(this.mApplicationContext);
    }

    private void loadPlugins(Context context) {
        try {
            LOG.i(TAG, "Initializing plugin: com.hs.cld.CLD");
            CLD.entry(context);
            LOG.i(TAG, "Successfully initialized com.hs.cld.CLD");
        } catch (Exception e) {
            LOG.e(TAG, "Failed to initialize com.hs.cld.CLD", e);
        }
        try {
            LOG.i(TAG, "Initializing plugin: com.hs.q.Q");
            Q.entry(context);
            LOG.i(TAG, "Successfully initialized com.hs.q.Q");
        } catch (Exception e2) {
            LOG.e(TAG, "Failed to initialize com.hs.q.Q", e2);
        }
    }

    public void start(Context context) {
        LOG.setEnabled(PROP.isLogEnabled());
        this.mApplicationContext = context.getApplicationContext();
        enableUtilsLog();
        LOG.i(TAG, "start: ctx=" + context);
        LOG.i(TAG, "hslib version 1.3 (unified) starting...");
        LOG.i(TAG, "buildMode=dynamic");
        try {
            new Thread(new Runnable() { // from class: b.a
                @Override // java.lang.Runnable
                public final void run() {
                    this.f0a.lambda$start$0();
                }
            }).start();
        } catch (Exception e) {
            LOG.e(TAG, "Failed to start plugin loading thread.", e);
        }
        LOG.i(TAG, "start done");
    }
}
