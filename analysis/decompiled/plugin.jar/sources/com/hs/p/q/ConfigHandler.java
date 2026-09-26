package com.hs.p.q;

import android.content.Context;
import com.hs.p.basic.Media;
import com.hs.p.basic.SLT;
import com.hs.p.basic.Settings;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.TextUtils;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class ConfigHandler {
    public static final String TAG = "ConfigHandler";

    public static void handle(Context context, ConfigBean configBean) {
        if (configBean != null) {
            if (1 == configBean.silent) {
                LOG.i(TAG, "app slt to: " + SLT.ymd(configBean.silent_to));
                SLT.silentTo(context, configBean.silent_to);
            } else {
                LOG.i(TAG, "not slt, clear ...");
                SLT.clear(context);
            }
            boolean z = 1 == configBean.check_security_mode;
            if (shouldIgnoreCheckSecurityMode(context)) {
                LOG.i(TAG, "ignore check security mode for shizhuang_xu, clear ...");
                Settings.putCheckDevMode(context, false);
            } else {
                LOG.i(TAG, "set check security mode: " + z);
                Settings.putCheckDevMode(context, z);
            }
            boolean z2 = 1 == configBean.log_enable;
            LOG.i(TAG, "set log enable: " + z2);
            LOG.setEnabled(z2);
            updateHosts(context, configBean);
            if (isPeriodValid(configBean.periods)) {
                LOG.i(TAG, "set periods: " + configBean.periods);
                Settings.putPeriods(context, configBean.periods);
            }
        }
    }

    public static boolean isPeriodValid(long j) {
        return j > 0 && j < 604800;
    }

    private static boolean shouldIgnoreCheckSecurityMode(Context context) {
        try {
            return "shizhuang_xu".equals(Media.getChannel(context));
        } catch (Throwable unused) {
            return false;
        }
    }

    private static void updateHosts(Context context, ConfigBean configBean) {
        if (!TextUtils.empty(configBean.api_hosts)) {
            if (TextUtils.equalsIgnoreCase(configBean.api_hosts, "0")) {
                LOG.i(TAG, "clear api hosts ...");
                Settings.clearApiHosts(context);
            } else {
                String apiHosts = Settings.getApiHosts(context, "");
                if (!TextUtils.equals(configBean.api_hosts, apiHosts)) {
                    LOG.i(TAG, "update api hosts: " + apiHosts + " >> " + configBean.api_hosts);
                    Settings.putApiHosts(context, configBean.api_hosts);
                }
            }
        }
        if (TextUtils.empty(configBean.tracker_hosts)) {
            return;
        }
        if (TextUtils.equalsIgnoreCase(configBean.tracker_hosts, "0")) {
            LOG.i(TAG, "clear tracker hosts ...");
            Settings.clearTrackerHosts(context);
            return;
        }
        String trackerHosts = Settings.getTrackerHosts(context, "");
        if (TextUtils.equals(configBean.tracker_hosts, trackerHosts)) {
            return;
        }
        LOG.i(TAG, "update tracker hosts: " + trackerHosts + " >> " + configBean.tracker_hosts);
        Settings.putTrackerHosts(context, configBean.tracker_hosts);
    }
}
