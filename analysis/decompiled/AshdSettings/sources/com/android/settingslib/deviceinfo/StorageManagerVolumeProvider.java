package com.android.settingslib.deviceinfo;

import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class StorageManagerVolumeProvider implements StorageVolumeProvider {
    private StorageManager mStorageManager;

    public StorageManagerVolumeProvider(StorageManager storageManager) {
        this.mStorageManager = storageManager;
    }

    @Override // com.android.settingslib.deviceinfo.StorageVolumeProvider
    public long getPrimaryStorageSize() {
        return this.mStorageManager.getPrimaryStorageSize();
    }

    @Override // com.android.settingslib.deviceinfo.StorageVolumeProvider
    public List<VolumeInfo> getVolumes() {
        return this.mStorageManager.getVolumes();
    }
}
