package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.Context;
import android.os.ParcelUuid;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class LocalBluetoothAdapter {
    private static final int SCAN_EXPIRATION_MS = 300000;
    private static final String TAG = "LocalBluetoothAdapter";
    private static LocalBluetoothAdapter sInstance;
    private final BluetoothAdapter mAdapter;
    private long mLastScan;
    private LocalBluetoothProfileManager mProfileManager;
    private int mState = Integer.MIN_VALUE;

    private LocalBluetoothAdapter(BluetoothAdapter bluetoothAdapter) {
        this.mAdapter = bluetoothAdapter;
    }

    void setProfileManager(LocalBluetoothProfileManager localBluetoothProfileManager) {
        this.mProfileManager = localBluetoothProfileManager;
    }

    static synchronized LocalBluetoothAdapter getInstance() {
        BluetoothAdapter defaultAdapter;
        if (sInstance == null && (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) != null) {
            sInstance = new LocalBluetoothAdapter(defaultAdapter);
        }
        return sInstance;
    }

    public void cancelDiscovery() {
        this.mAdapter.cancelDiscovery();
    }

    public boolean enable() {
        return this.mAdapter.enable();
    }

    public boolean disable() {
        return this.mAdapter.disable();
    }

    void getProfileProxy(Context context, BluetoothProfile.ServiceListener serviceListener, int i) {
        this.mAdapter.getProfileProxy(context, serviceListener, i);
    }

    public Set<BluetoothDevice> getBondedDevices() {
        return this.mAdapter.getBondedDevices();
    }

    public String getName() {
        return this.mAdapter.getName();
    }

    public int getScanMode() {
        return this.mAdapter.getScanMode();
    }

    public BluetoothLeScanner getBluetoothLeScanner() {
        return this.mAdapter.getBluetoothLeScanner();
    }

    public int getState() {
        return this.mAdapter.getState();
    }

    public ParcelUuid[] getUuids() {
        return this.mAdapter.getUuids();
    }

    public boolean isDiscovering() {
        return this.mAdapter.isDiscovering();
    }

    public boolean isEnabled() {
        return this.mAdapter.isEnabled();
    }

    public int getConnectionState() {
        return this.mAdapter.getConnectionState();
    }

    public void setDiscoverableTimeout(int i) {
        this.mAdapter.setDiscoverableTimeout(i);
    }

    public void setName(String str) {
        this.mAdapter.setName(str);
    }

    public void setScanMode(int i) {
        this.mAdapter.setScanMode(i);
    }

    public boolean setScanMode(int i, int i2) {
        return this.mAdapter.setScanMode(23);
    }

    public void startScanning(boolean z) {
        if (this.mAdapter.isDiscovering()) {
            return;
        }
        if (!z) {
            if (this.mLastScan + 300000 > System.currentTimeMillis()) {
                return;
            }
            A2dpProfile a2dpProfile = this.mProfileManager.getA2dpProfile();
            if (a2dpProfile != null && a2dpProfile.isA2dpPlaying()) {
                return;
            }
            A2dpSinkProfile a2dpSinkProfile = this.mProfileManager.getA2dpSinkProfile();
            if (a2dpSinkProfile != null && a2dpSinkProfile.isA2dpPlaying()) {
                return;
            }
        }
        if (this.mAdapter.startDiscovery()) {
            this.mLastScan = System.currentTimeMillis();
        }
    }

    public void stopScanning() {
        if (this.mAdapter.isDiscovering()) {
            this.mAdapter.cancelDiscovery();
        }
    }

    public synchronized int getBluetoothState() {
        syncBluetoothState();
        return this.mState;
    }

    synchronized void setBluetoothStateInt(int i) {
        this.mState = i;
        if (i == 12 && this.mProfileManager != null) {
            this.mProfileManager.setBluetoothStateOn();
        }
    }

    boolean syncBluetoothState() {
        if (this.mAdapter.getState() == this.mState) {
            return false;
        }
        setBluetoothStateInt(this.mAdapter.getState());
        return true;
    }

    public boolean setBluetoothEnabled(boolean z) {
        boolean zDisable;
        if (z) {
            zDisable = this.mAdapter.enable();
        } else {
            zDisable = this.mAdapter.disable();
        }
        if (zDisable) {
            setBluetoothStateInt(z ? 11 : 13);
        } else {
            syncBluetoothState();
        }
        return zDisable;
    }

    public BluetoothDevice getRemoteDevice(String str) {
        return this.mAdapter.getRemoteDevice(str);
    }
}
