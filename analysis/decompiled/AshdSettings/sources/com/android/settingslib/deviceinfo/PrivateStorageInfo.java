package com.android.settingslib.deviceinfo;

import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.util.Log;
import java.io.File;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class PrivateStorageInfo {
    private static final String TAG = "PrivateStorageInfo";
    public final long freeBytes;
    public final long totalBytes;

    private PrivateStorageInfo(long j, long j2) {
        this.freeBytes = j;
        this.totalBytes = j2;
    }

    public static PrivateStorageInfo getPrivateStorageInfo(StorageVolumeProvider storageVolumeProvider) {
        long primaryStorageSize = storageVolumeProvider.getPrimaryStorageSize();
        long freeSpace = 0;
        long totalSize = 0;
        for (VolumeInfo volumeInfo : storageVolumeProvider.getVolumes()) {
            File path = volumeInfo.getPath();
            if (volumeInfo.getType() == 1 && path != null) {
                totalSize += getTotalSize(volumeInfo, primaryStorageSize);
                freeSpace += path.getFreeSpace();
            }
        }
        return new PrivateStorageInfo(freeSpace, totalSize);
    }

    public static long getTotalSize(VolumeInfo volumeInfo, long j) {
        if (volumeInfo.getType() == 1 && Objects.equals(volumeInfo.getFsUuid(), StorageManager.UUID_PRIVATE_INTERNAL) && j > 0) {
            return j;
        }
        File path = volumeInfo.getPath();
        if (path == null) {
            Log.e(TAG, "info's path is null on getTotalSize(): " + volumeInfo);
            return 0L;
        }
        return path.getTotalSpace();
    }
}
