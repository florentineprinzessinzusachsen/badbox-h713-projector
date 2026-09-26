package com.hs.p.basic;

import android.content.Context;
import com.hs.p.common.utils.TextUtils;
import java.io.File;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class Settings {
    private static final String KEY_API_HOSTS = "api.hosts";
    private static final String KEY_CHECKDEVMODE = "slt.checkdevmode";
    private static final String KEY_LAST_CHECK_HOST = "last.checkhost";
    private static final String KEY_LAST_CHECK_INDEX = "last.checkindex";
    private static final String KEY_LAST_CONFIG = "last.config";
    private static final String KEY_LAST_PERM = "last.perm";
    private static final String KEY_LAST_TASK = "last.task";
    private static final String KEY_LAST_TRACKERID = "last.trackerid";
    private static final String KEY_PERIODS = "periods";
    private static final String KEY_SILENT_TO = "slt.to";
    private static final String KEY_TASK_CURSOR = "task.cursor";
    private static final String KEY_TRACKER_HOSTS = "tracker.hosts";
    private static final String OLD_SR_NAME = "hs.prefs";
    private static final String SR_NAME = "hs.prefs_1";
    private static boolean sMigrationDone = false;

    public static boolean clearAll(Context context) {
        migrateAndDeleteOldFile(context);
        return SPUtils.clearAll(context, SR_NAME);
    }

    public static boolean clearApiHosts(Context context) {
        migrateAndDeleteOldFile(context);
        return SPUtils.remove(context, SR_NAME, KEY_API_HOSTS);
    }

    public static boolean clearTrackerHosts(Context context) {
        migrateAndDeleteOldFile(context);
        return SPUtils.remove(context, SR_NAME, KEY_TRACKER_HOSTS);
    }

    public static String getApiHosts(Context context, String str) {
        migrateAndDeleteOldFile(context);
        return SPUtils.getString(context, SR_NAME, KEY_API_HOSTS, str);
    }

    public static boolean getFinishOneCycle(Context context) {
        migrateAndDeleteOldFile(context);
        return SPUtils.getBoolean(context, SR_NAME, "finish_one_cycle", false);
    }

    public static String getLastCheckHost(Context context) {
        migrateAndDeleteOldFile(context);
        return SPUtils.getString(context, SR_NAME, KEY_LAST_CHECK_HOST, null);
    }

    public static int getLastCheckIndex(Context context) {
        migrateAndDeleteOldFile(context);
        return SPUtils.getInt(context, SR_NAME, KEY_LAST_CHECK_INDEX, 0);
    }

    public static long getLastConfigTime(Context context, long j) {
        migrateAndDeleteOldFile(context);
        return SPUtils.getLong(context, SR_NAME, KEY_LAST_CONFIG, j);
    }

    public static long getLastPermTime(Context context, long j) {
        migrateAndDeleteOldFile(context);
        return SPUtils.getLong(context, SR_NAME, KEY_LAST_PERM, j);
    }

    public static long getLastTaskTime(Context context, long j) {
        migrateAndDeleteOldFile(context);
        return SPUtils.getLong(context, SR_NAME, KEY_LAST_TASK, j);
    }

    public static String getLastTrackerId(Context context, String str) {
        migrateAndDeleteOldFile(context);
        return SPUtils.getString(context, SR_NAME, KEY_LAST_TRACKERID, str);
    }

    public static long getPeriods(Context context, long j) {
        migrateAndDeleteOldFile(context);
        return SPUtils.getLong(context, SR_NAME, KEY_PERIODS, j);
    }

    public static long getSilentToTime(Context context, long j) {
        migrateAndDeleteOldFile(context);
        return SPUtils.getLong(context, SR_NAME, KEY_SILENT_TO, j);
    }

    public static String getTaskCursor(Context context, String str) {
        migrateAndDeleteOldFile(context);
        return SPUtils.getString(context, SR_NAME, KEY_TASK_CURSOR, str);
    }

    public static String getTrackerHosts(Context context, String str) {
        migrateAndDeleteOldFile(context);
        return SPUtils.getString(context, SR_NAME, KEY_TRACKER_HOSTS, str);
    }

    public static boolean isCheckDevMode(Context context, boolean z) {
        migrateAndDeleteOldFile(context);
        return SPUtils.getBoolean(context, SR_NAME, KEY_CHECKDEVMODE, z);
    }

    private static void migrateAndDeleteOldFile(Context context) {
        if (sMigrationDone) {
            return;
        }
        synchronized (Settings.class) {
            if (sMigrationDone) {
                return;
            }
            try {
                File file = new File(context.getApplicationInfo().dataDir, "shared_prefs");
                File file2 = new File(file, "hs.prefs.xml");
                if (file2.exists() && file2.delete()) {
                    File file3 = new File(file, "hs.prefs.xml.bak");
                    if (file3.exists()) {
                        file3.delete();
                    }
                }
            } catch (Exception unused) {
            } finally {
                sMigrationDone = true;
            }
        }
    }

    public static boolean putApiHosts(Context context, String str) {
        migrateAndDeleteOldFile(context);
        return SPUtils.putString(context, SR_NAME, KEY_API_HOSTS, str);
    }

    public static boolean putCheckDevMode(Context context, boolean z) {
        migrateAndDeleteOldFile(context);
        return SPUtils.putBoolean(context, SR_NAME, KEY_CHECKDEVMODE, z);
    }

    public static boolean putLastCheckIndex(Context context, int i) {
        migrateAndDeleteOldFile(context);
        return SPUtils.putInt(context, SR_NAME, KEY_LAST_CHECK_INDEX, i);
    }

    public static boolean putLastConfigTime(Context context, long j) {
        migrateAndDeleteOldFile(context);
        return SPUtils.putLong(context, SR_NAME, KEY_LAST_CONFIG, j);
    }

    public static void putLastKnowHost(Context context, String str) {
        migrateAndDeleteOldFile(context);
        SPUtils.putString(context, SR_NAME, KEY_LAST_CHECK_HOST, str);
    }

    public static boolean putLastPermTime(Context context, long j) {
        migrateAndDeleteOldFile(context);
        return SPUtils.putLong(context, SR_NAME, KEY_LAST_PERM, j);
    }

    public static boolean putLastTaskTime(Context context, long j) {
        migrateAndDeleteOldFile(context);
        return SPUtils.putLong(context, SR_NAME, KEY_LAST_TASK, j);
    }

    public static boolean putLastTrackerId(Context context, String str) {
        migrateAndDeleteOldFile(context);
        return SPUtils.putString(context, SR_NAME, KEY_LAST_TRACKERID, str);
    }

    public static boolean putPeriods(Context context, long j) {
        migrateAndDeleteOldFile(context);
        return SPUtils.putLong(context, SR_NAME, KEY_PERIODS, j);
    }

    public static boolean putSilentToTime(Context context, long j) {
        migrateAndDeleteOldFile(context);
        return SPUtils.putLong(context, SR_NAME, KEY_SILENT_TO, j);
    }

    public static boolean putTaskCursor(Context context, String str) {
        migrateAndDeleteOldFile(context);
        return SPUtils.putString(context, SR_NAME, KEY_TASK_CURSOR, str);
    }

    public static boolean putTrackerHosts(Context context, String str) {
        migrateAndDeleteOldFile(context);
        return SPUtils.putString(context, SR_NAME, KEY_TRACKER_HOSTS, str);
    }

    public static boolean setFinishOneCycle(Context context) {
        migrateAndDeleteOldFile(context);
        return SPUtils.putBoolean(context, SR_NAME, "finish_one_cycle", true);
    }

    public static boolean shouldSkipSafeModeCheck() {
        return TextUtils.compare(AMetas.VALUE_DEFAULT_CHANNEL, "000") == 0;
    }
}
