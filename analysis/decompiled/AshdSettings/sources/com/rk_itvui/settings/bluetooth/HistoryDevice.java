package com.rk_itvui.settings.bluetooth;

/* JADX INFO: loaded from: classes.dex */
public class HistoryDevice {
    private String deviceMac;
    private String deviceName;
    private String deviceState;

    public HistoryDevice(DeviceInfo deviceInfo) {
        if (deviceInfo != null) {
            this.deviceMac = deviceInfo.getDeviceMac();
            this.deviceName = deviceInfo.getDeviceName();
            this.deviceState = deviceInfo.getDeviceState();
        }
    }

    public String getDeviceMac() {
        return this.deviceMac;
    }

    public void setDeviceMac(String str) {
        this.deviceMac = str;
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

    public void setDeviceState(String str) {
        this.deviceState = str;
    }

    public HistoryDevice() {
    }
}
