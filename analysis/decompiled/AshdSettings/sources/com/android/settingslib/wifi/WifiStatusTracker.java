package com.android.settingslib.wifi;

import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WifiStatusTracker {
    public boolean connected;
    public boolean enabled;
    public int level;
    private final WifiManager mWifiManager;
    public int rssi;
    public String ssid;

    public WifiStatusTracker(WifiManager wifiManager) {
        this.mWifiManager = wifiManager;
    }

    public void handleBroadcast(Intent intent) {
        WifiInfo connectionInfo;
        String action = intent.getAction();
        boolean z = false;
        if (action.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
            this.enabled = intent.getIntExtra("wifi_state", 4) == 3;
            return;
        }
        if (action.equals("android.net.wifi.STATE_CHANGE")) {
            NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
            if (networkInfo != null && networkInfo.isConnected()) {
                z = true;
            }
            this.connected = z;
            if (this.connected) {
                if (intent.getParcelableExtra("wifiInfo") != null) {
                    connectionInfo = (WifiInfo) intent.getParcelableExtra("wifiInfo");
                } else {
                    connectionInfo = this.mWifiManager.getConnectionInfo();
                }
                if (connectionInfo != null) {
                    this.ssid = getSsid(connectionInfo);
                    return;
                } else {
                    this.ssid = null;
                    return;
                }
            }
            if (this.connected) {
                return;
            }
            this.ssid = null;
            return;
        }
        if (action.equals("android.net.wifi.RSSI_CHANGED")) {
            this.rssi = intent.getIntExtra("newRssi", -200);
            this.level = WifiManager.calculateSignalLevel(this.rssi, 5);
        }
    }

    private String getSsid(WifiInfo wifiInfo) {
        String ssid = wifiInfo.getSSID();
        if (ssid != null) {
            return ssid;
        }
        List<WifiConfiguration> configuredNetworks = this.mWifiManager.getConfiguredNetworks();
        int size = configuredNetworks.size();
        for (int i = 0; i < size; i++) {
            if (configuredNetworks.get(i).networkId == wifiInfo.getNetworkId()) {
                return configuredNetworks.get(i).SSID;
            }
        }
        return null;
    }
}
