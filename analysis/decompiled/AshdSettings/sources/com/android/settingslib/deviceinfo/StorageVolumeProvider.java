package com.android.settingslib.deviceinfo;

import android.os.storage.VolumeInfo;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface StorageVolumeProvider {
    long getPrimaryStorageSize();

    List<VolumeInfo> getVolumes();
}
