package com.rk_itvui.settings.bluetooth;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;

/* JADX INFO: loaded from: classes.dex */
public class DeviceInfo implements Comparable<DeviceInfo> {
    private BluetoothClass btClass;
    private BluetoothDevice device;
    private String deviceMac;
    private String deviceName;
    private String deviceState;
    private int rssi;

    public DeviceInfo() {
    }

    public DeviceInfo(BluetoothDevice bluetoothDevice, int i) {
        this.device = bluetoothDevice;
        if (bluetoothDevice == null) {
            return;
        }
        this.deviceMac = bluetoothDevice.getAddress();
        this.btClass = bluetoothDevice.getBluetoothClass();
        this.deviceName = bluetoothDevice.getName();
        this.rssi = i;
    }

    public BluetoothDevice getDevice() {
        return this.device;
    }

    public BluetoothClass getBtClass() {
        return this.btClass;
    }

    public void setBtClass(BluetoothClass bluetoothClass) {
        this.btClass = bluetoothClass;
    }

    public String getDeviceMac() {
        return this.deviceMac;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public String getDeviceState() {
        return this.deviceState;
    }

    public void setDeviceMac(String str) {
        this.deviceMac = str;
    }

    public void setDeviceState(String str) {
        this.deviceState = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(DeviceInfo deviceInfo) {
        return Integer.compare(deviceInfo.rssi, this.rssi);
    }
}
