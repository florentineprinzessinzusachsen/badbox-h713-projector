package com.blankj.utilcode.util;

import android.os.Build;
import android.os.Environment;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class PathUtils {
    private PathUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static String getAbsolutePath(File file) {
        return file == null ? "" : file.getAbsolutePath();
    }

    public static String getDataPath() {
        return getAbsolutePath(Environment.getDataDirectory());
    }

    public static String getDownloadCachePath() {
        return getAbsolutePath(Environment.getDownloadCacheDirectory());
    }

    public static String getExternalAlarmsPath() {
        return isExternalStorageDisable() ? "" : getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS));
    }

    public static String getExternalAppAlarmsPath() {
        return isExternalStorageDisable() ? "" : getAbsolutePath(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_ALARMS));
    }

    public static String getExternalAppCachePath() {
        return isExternalStorageDisable() ? "" : getAbsolutePath(Utils.getApp().getExternalCacheDir());
    }

    public static String getExternalAppDataPath() {
        File externalCacheDir;
        return (isExternalStorageDisable() || (externalCacheDir = Utils.getApp().getExternalCacheDir()) == null) ? "" : getAbsolutePath(externalCacheDir.getParentFile());
    }

    public static String getExternalAppDcimPath() {
        return isExternalStorageDisable() ? "" : getAbsolutePath(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_DCIM));
    }

    public static String getExternalAppDocumentsPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return getAbsolutePath(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS));
        }
        return getAbsolutePath(Utils.getApp().getExternalFilesDir(null)) + "/Documents";
    }

    public static String getExternalAppDownloadPath() {
        return isExternalStorageDisable() ? "" : getAbsolutePath(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS));
    }

    public static String getExternalAppFilesPath() {
        return isExternalStorageDisable() ? "" : getAbsolutePath(Utils.getApp().getExternalFilesDir(null));
    }

    public static String getExternalAppMoviesPath() {
        return isExternalStorageDisable() ? "" : getAbsolutePath(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_MOVIES));
    }

    public static String getExternalAppMusicPath() {
        return isExternalStorageDisable() ? "" : getAbsolutePath(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_MUSIC));
    }

    public static String getExternalAppNotificationsPath() {
        return isExternalStorageDisable() ? "" : getAbsolutePath(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS));
    }

    public static String getExternalAppObbPath() {
        return isExternalStorageDisable() ? "" : getAbsolutePath(Utils.getApp().getObbDir());
    }

    public static String getExternalAppPicturesPath() {
        return isExternalStorageDisable() ? "" : getAbsolutePath(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_PICTURES));
    }

    public static String getExternalAppPodcastsPath() {
        return isExternalStorageDisable() ? "" : getAbsolutePath(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_PODCASTS));
    }

    public static String getExternalAppRingtonesPath() {
        return isExternalStorageDisable() ? "" : getAbsolutePath(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_RINGTONES));
    }

    public static String getExternalDcimPath() {
        return isExternalStorageDisable() ? "" : getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
    }

    public static String getExternalDocumentsPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS));
        }
        return getAbsolutePath(Environment.getExternalStorageDirectory()) + "/Documents";
    }

    public static String getExternalDownloadsPath() {
        return isExternalStorageDisable() ? "" : getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
    }

    public static String getExternalMoviesPath() {
        return isExternalStorageDisable() ? "" : getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES));
    }

    public static String getExternalMusicPath() {
        return isExternalStorageDisable() ? "" : getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC));
    }

    public static String getExternalNotificationsPath() {
        return isExternalStorageDisable() ? "" : getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS));
    }

    public static String getExternalPicturesPath() {
        return isExternalStorageDisable() ? "" : getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
    }

    public static String getExternalPodcastsPath() {
        return isExternalStorageDisable() ? "" : getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS));
    }

    public static String getExternalRingtonesPath() {
        return isExternalStorageDisable() ? "" : getAbsolutePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES));
    }

    public static String getExternalStoragePath() {
        return isExternalStorageDisable() ? "" : getAbsolutePath(Environment.getExternalStorageDirectory());
    }

    public static String getInternalAppCachePath() {
        return getAbsolutePath(Utils.getApp().getCacheDir());
    }

    public static String getInternalAppCodeCacheDir() {
        if (Build.VERSION.SDK_INT >= 21) {
            return getAbsolutePath(Utils.getApp().getCodeCacheDir());
        }
        return Utils.getApp().getApplicationInfo().dataDir + "/code_cache";
    }

    public static String getInternalAppDataPath() {
        return Build.VERSION.SDK_INT < 24 ? Utils.getApp().getApplicationInfo().dataDir : getAbsolutePath(Utils.getApp().getDataDir());
    }

    public static String getInternalAppDbPath(String str) {
        return getAbsolutePath(Utils.getApp().getDatabasePath(str));
    }

    public static String getInternalAppDbsPath() {
        return Utils.getApp().getApplicationInfo().dataDir + "/databases";
    }

    public static String getInternalAppFilesPath() {
        return getAbsolutePath(Utils.getApp().getFilesDir());
    }

    public static String getInternalAppNoBackupFilesPath() {
        if (Build.VERSION.SDK_INT >= 21) {
            return getAbsolutePath(Utils.getApp().getNoBackupFilesDir());
        }
        return Utils.getApp().getApplicationInfo().dataDir + "/no_backup";
    }

    public static String getInternalAppSpPath() {
        return Utils.getApp().getApplicationInfo().dataDir + "/shared_prefs";
    }

    public static String getRootPath() {
        return getAbsolutePath(Environment.getRootDirectory());
    }

    private static boolean isExternalStorageDisable() {
        return !"mounted".equals(Environment.getExternalStorageState());
    }
}
