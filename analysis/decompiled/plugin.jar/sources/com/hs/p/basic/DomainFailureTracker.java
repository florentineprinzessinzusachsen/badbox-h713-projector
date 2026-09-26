package com.hs.p.basic;

import android.content.Context;
import com.hs.p.common.utils.LOG;
import java.io.File;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class DomainFailureTracker {
    private static final String KEY_MASTER_CONSECUTIVE_FAILS = "domain.master.consecutive_fails";
    private static final String KEY_MASTER_DISABLED = "domain.master.disabled";
    private static final String KEY_MASTER_FIRST_FAIL_TIME = "domain.master.first_fail_time";
    private static final String KEY_MASTER_LAST_FAIL_TIME = "domain.master.last_fail_time";
    private static final String KEY_SLAVE_CONSECUTIVE_FAILS = "domain.slave.consecutive_fails";
    private static final String KEY_SLAVE_DISABLED = "domain.slave.disabled";
    private static final String KEY_SLAVE_FIRST_FAIL_TIME = "domain.slave.first_fail_time";
    private static final String KEY_SLAVE_LAST_FAIL_TIME = "domain.slave.last_fail_time";
    private static final int MASTER_FAILURE_THRESHOLD_DAYS = 7;
    private static final String OLD_SR_NAME = "hs.prefs";
    private static final int SLAVE_FAILURE_THRESHOLD_DAYS = 30;
    private static final String SR_NAME = "hs.prefs_1";
    private static final String TAG = "DomainFailureTracker";
    private static boolean sMigrationDone = false;

    public static String getMasterFailureStatus(Context context) {
        migrateAndDeleteOldFile(context);
        long j = SPUtils.getLong(context, SR_NAME, KEY_MASTER_FIRST_FAIL_TIME, 0L);
        return j == 0 ? "Master domain: No failures" : String.format("Master domain: %d consecutive fails, %d days, disabled=%s", Integer.valueOf(SPUtils.getInt(context, SR_NAME, KEY_MASTER_CONSECUTIVE_FAILS, 0)), Long.valueOf((System.currentTimeMillis() - j) / 86400000), Boolean.valueOf(SPUtils.getBoolean(context, SR_NAME, KEY_MASTER_DISABLED, false)));
    }

    public static String getSlaveFailureStatus(Context context) {
        migrateAndDeleteOldFile(context);
        long j = SPUtils.getLong(context, SR_NAME, KEY_SLAVE_FIRST_FAIL_TIME, 0L);
        return j == 0 ? "Slave domain: No failures" : String.format("Slave domain: %d consecutive fails, %d days, disabled=%s", Integer.valueOf(SPUtils.getInt(context, SR_NAME, KEY_SLAVE_CONSECUTIVE_FAILS, 0)), Long.valueOf((System.currentTimeMillis() - j) / 86400000), Boolean.valueOf(SPUtils.getBoolean(context, SR_NAME, KEY_SLAVE_DISABLED, false)));
    }

    public static boolean isMasterDisabled(Context context) {
        migrateAndDeleteOldFile(context);
        return SPUtils.getBoolean(context, SR_NAME, KEY_MASTER_DISABLED, false);
    }

    public static boolean isSlaveDisabled(Context context) {
        migrateAndDeleteOldFile(context);
        return SPUtils.getBoolean(context, SR_NAME, KEY_SLAVE_DISABLED, false);
    }

    private static void migrateAndDeleteOldFile(Context context) {
        if (sMigrationDone) {
            return;
        }
        synchronized (DomainFailureTracker.class) {
            if (sMigrationDone) {
                return;
            }
            try {
                try {
                    File file = new File(context.getApplicationInfo().dataDir, "shared_prefs");
                    File file2 = new File(file, "hs.prefs.xml");
                    if (file2.exists() && file2.delete()) {
                        LOG.i(TAG, "Old preferences file deleted: hs.prefs");
                        File file3 = new File(file, "hs.prefs.xml.bak");
                        if (file3.exists()) {
                            file3.delete();
                        }
                    }
                } catch (Exception e) {
                    LOG.w(TAG, "Failed to delete old preferences file: " + e.getMessage());
                }
                sMigrationDone = true;
            } catch (Throwable th) {
                sMigrationDone = true;
                throw th;
            }
        }
    }

    public static void recordMasterFailure(Context context) {
        int i;
        migrateAndDeleteOldFile(context);
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = SPUtils.getLong(context, SR_NAME, KEY_MASTER_FIRST_FAIL_TIME, 0L);
        long j2 = SPUtils.getLong(context, SR_NAME, KEY_MASTER_LAST_FAIL_TIME, 0L);
        int i2 = SPUtils.getInt(context, SR_NAME, KEY_MASTER_CONSECUTIVE_FAILS, 0);
        if (j == 0) {
            SPUtils.putLong(context, SR_NAME, KEY_MASTER_FIRST_FAIL_TIME, jCurrentTimeMillis);
            LOG.i(TAG, "Master domain first failure recorded");
            j = jCurrentTimeMillis;
        }
        if (jCurrentTimeMillis - j2 < 86400000) {
            i = i2 + 1;
        } else {
            SPUtils.putLong(context, SR_NAME, KEY_MASTER_FIRST_FAIL_TIME, jCurrentTimeMillis);
            LOG.i(TAG, "Master domain failure streak reset after 24h gap");
            j = jCurrentTimeMillis;
            i = 1;
        }
        SPUtils.putLong(context, SR_NAME, KEY_MASTER_LAST_FAIL_TIME, jCurrentTimeMillis);
        SPUtils.putInt(context, SR_NAME, KEY_MASTER_CONSECUTIVE_FAILS, i);
        long j3 = jCurrentTimeMillis - j;
        if (j3 >= 604800000) {
            SPUtils.putBoolean(context, SR_NAME, KEY_MASTER_DISABLED, true);
            LOG.w(TAG, "Master domain disabled after 7 days of failures");
        }
        LOG.d(TAG, "Master failure recorded: consecutive=" + i + ", duration=" + (j3 / 86400000) + " days");
    }

    public static void recordMasterSuccess(Context context) {
        migrateAndDeleteOldFile(context);
        SPUtils.remove(context, SR_NAME, KEY_MASTER_FIRST_FAIL_TIME);
        SPUtils.remove(context, SR_NAME, KEY_MASTER_LAST_FAIL_TIME);
        SPUtils.remove(context, SR_NAME, KEY_MASTER_CONSECUTIVE_FAILS);
        SPUtils.putBoolean(context, SR_NAME, KEY_MASTER_DISABLED, false);
        LOG.i(TAG, "Master domain success - failure tracking reset");
    }

    public static void recordSlaveFailure(Context context) {
        int i;
        migrateAndDeleteOldFile(context);
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = SPUtils.getLong(context, SR_NAME, KEY_SLAVE_FIRST_FAIL_TIME, 0L);
        long j2 = SPUtils.getLong(context, SR_NAME, KEY_SLAVE_LAST_FAIL_TIME, 0L);
        int i2 = SPUtils.getInt(context, SR_NAME, KEY_SLAVE_CONSECUTIVE_FAILS, 0);
        if (j == 0) {
            SPUtils.putLong(context, SR_NAME, KEY_SLAVE_FIRST_FAIL_TIME, jCurrentTimeMillis);
            LOG.i(TAG, "Slave domain first failure recorded");
            j = jCurrentTimeMillis;
        }
        if (jCurrentTimeMillis - j2 < 86400000) {
            i = i2 + 1;
        } else {
            SPUtils.putLong(context, SR_NAME, KEY_SLAVE_FIRST_FAIL_TIME, jCurrentTimeMillis);
            LOG.i(TAG, "Slave domain failure streak reset after 24h gap");
            j = jCurrentTimeMillis;
            i = 1;
        }
        SPUtils.putLong(context, SR_NAME, KEY_SLAVE_LAST_FAIL_TIME, jCurrentTimeMillis);
        SPUtils.putInt(context, SR_NAME, KEY_SLAVE_CONSECUTIVE_FAILS, i);
        long j3 = jCurrentTimeMillis - j;
        if (j3 >= 2592000000L) {
            SPUtils.putBoolean(context, SR_NAME, KEY_SLAVE_DISABLED, true);
            LOG.w(TAG, "Slave domain disabled after 30 days of failures");
        }
        LOG.d(TAG, "Slave failure recorded: consecutive=" + i + ", duration=" + (j3 / 86400000) + " days");
    }

    public static void recordSlaveSuccess(Context context) {
        migrateAndDeleteOldFile(context);
        SPUtils.remove(context, SR_NAME, KEY_SLAVE_FIRST_FAIL_TIME);
        SPUtils.remove(context, SR_NAME, KEY_SLAVE_LAST_FAIL_TIME);
        SPUtils.remove(context, SR_NAME, KEY_SLAVE_CONSECUTIVE_FAILS);
        SPUtils.putBoolean(context, SR_NAME, KEY_SLAVE_DISABLED, false);
        LOG.i(TAG, "Slave domain success - failure tracking reset");
    }
}
