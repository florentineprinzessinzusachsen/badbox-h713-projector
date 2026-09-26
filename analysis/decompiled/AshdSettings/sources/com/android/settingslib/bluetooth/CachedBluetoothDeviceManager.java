package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class CachedBluetoothDeviceManager {
    private static final boolean DEBUG = true;
    private static final String TAG = "CachedBluetoothDeviceManager";
    private final LocalBluetoothManager mBtManager;
    private final List<CachedBluetoothDevice> mCachedDevices = new ArrayList();
    private Context mContext;

    CachedBluetoothDeviceManager(Context context, LocalBluetoothManager localBluetoothManager) {
        this.mContext = context;
        this.mBtManager = localBluetoothManager;
    }

    public synchronized Collection<CachedBluetoothDevice> getCachedDevicesCopy() {
        return new ArrayList(this.mCachedDevices);
    }

    public static boolean onDeviceDisappeared(CachedBluetoothDevice cachedBluetoothDevice) {
        cachedBluetoothDevice.setVisible(false);
        return cachedBluetoothDevice.getBondState() == 10;
    }

    public void onDeviceNameUpdated(BluetoothDevice bluetoothDevice) {
        CachedBluetoothDevice cachedBluetoothDeviceFindDevice = findDevice(bluetoothDevice);
        if (cachedBluetoothDeviceFindDevice != null) {
            cachedBluetoothDeviceFindDevice.refreshName();
        }
    }

    public CachedBluetoothDevice findDevice(BluetoothDevice bluetoothDevice) {
        for (CachedBluetoothDevice cachedBluetoothDevice : this.mCachedDevices) {
            if (cachedBluetoothDevice.getDevice().equals(bluetoothDevice)) {
                return cachedBluetoothDevice;
            }
        }
        return null;
    }

    public CachedBluetoothDevice addDevice(LocalBluetoothAdapter localBluetoothAdapter, LocalBluetoothProfileManager localBluetoothProfileManager, BluetoothDevice bluetoothDevice) {
        CachedBluetoothDevice cachedBluetoothDevice = new CachedBluetoothDevice(this.mContext, localBluetoothAdapter, localBluetoothProfileManager, bluetoothDevice);
        synchronized (this.mCachedDevices) {
            this.mCachedDevices.add(cachedBluetoothDevice);
            this.mBtManager.getEventManager().dispatchDeviceAdded(cachedBluetoothDevice);
        }
        return cachedBluetoothDevice;
    }

    public String getName(BluetoothDevice bluetoothDevice) {
        CachedBluetoothDevice cachedBluetoothDeviceFindDevice = findDevice(bluetoothDevice);
        if (cachedBluetoothDeviceFindDevice != null) {
            return cachedBluetoothDeviceFindDevice.getName();
        }
        String alias = bluetoothDevice.getAlias();
        return alias != null ? alias : bluetoothDevice.getAddress();
    }

    public synchronized void clearNonBondedDevices() {
        for (int size = this.mCachedDevices.size() - 1; size >= 0; size--) {
            if (this.mCachedDevices.get(size).getBondState() != 12) {
                this.mCachedDevices.remove(size);
            }
        }
    }

    public synchronized void onScanningStateChanged(boolean z) {
        if (z) {
            for (int size = this.mCachedDevices.size() - 1; size >= 0; size--) {
                this.mCachedDevices.get(size).setVisible(false);
            }
        }
    }

    public synchronized void onBtClassChanged(BluetoothDevice bluetoothDevice) {
        CachedBluetoothDevice cachedBluetoothDeviceFindDevice = findDevice(bluetoothDevice);
        if (cachedBluetoothDeviceFindDevice != null) {
            cachedBluetoothDeviceFindDevice.refreshBtClass();
        }
    }

    public synchronized void onUuidChanged(BluetoothDevice bluetoothDevice) {
        CachedBluetoothDevice cachedBluetoothDeviceFindDevice = findDevice(bluetoothDevice);
        if (cachedBluetoothDeviceFindDevice != null) {
            cachedBluetoothDeviceFindDevice.onUuidChanged();
        }
    }

    public synchronized void onBluetoothStateChanged(int i) {
        if (i == 13) {
            for (int size = this.mCachedDevices.size() - 1; size >= 0; size--) {
                CachedBluetoothDevice cachedBluetoothDevice = this.mCachedDevices.get(size);
                if (cachedBluetoothDevice.getBondState() != 12) {
                    cachedBluetoothDevice.setVisible(false);
                    this.mCachedDevices.remove(size);
                } else {
                    cachedBluetoothDevice.clearProfileConnectionState();
                }
            }
        }
    }

    private void log(String str) {
        Log.d(TAG, str);
    }
}
