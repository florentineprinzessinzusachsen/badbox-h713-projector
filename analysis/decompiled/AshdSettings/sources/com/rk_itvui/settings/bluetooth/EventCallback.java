package com.rk_itvui.settings.bluetooth;

import android.bluetooth.BluetoothClass;
import android.content.Intent;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;

/* JADX INFO: loaded from: classes.dex */
public interface EventCallback {
    void onBluetoothStateChanged(int i);

    void onConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i);

    void onDeviceAdded(CachedBluetoothDevice cachedBluetoothDevice, int i);

    void onDeviceBond(Intent intent);

    void onDeviceBondStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i);

    void onDeviceClassChanged(CachedBluetoothDevice cachedBluetoothDevice, BluetoothClass bluetoothClass);

    void onDeviceDisappeared(CachedBluetoothDevice cachedBluetoothDevice);

    void onDeviceNameChanged(CachedBluetoothDevice cachedBluetoothDevice);

    void onLocalDeviceNameChanged();

    void onScanningStateChanged(boolean z);
}
