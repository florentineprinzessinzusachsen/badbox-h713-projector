package com.rk_itvui.settings.network.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.provider.Settings;
import android.support.v17.leanback.media.MediaPlayerGlue;
import android.util.Log;
import com.ashd.settings.R;

/* JADX INFO: loaded from: classes.dex */
public class Wifi_Enabler {
    private final Context mContext;
    private final Handler mHandler;
    private final WifiManager mWifiManager;
    private boolean mOpen = false;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.rk_itvui.settings.network.wifi.Wifi_Enabler.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Wifi_Enabler.this.LOGD("BroadcastReceiver onReceive(), action = " + action);
            if ("android.net.wifi.WIFI_STATE_CHANGED".equals(action)) {
                Wifi_Enabler.this.handleWifiStateChanged(intent.getIntExtra("wifi_state", 4));
            } else if ("android.net.wifi.supplicant.STATE_CHANGE".equals(action)) {
                Wifi_Enabler.this.handleStateChanged(WifiInfo.getDetailedStateOf((SupplicantState) intent.getParcelableExtra("newState")));
            } else if ("android.net.wifi.STATE_CHANGE".equals(action)) {
                Wifi_Enabler.this.handleStateChanged(((NetworkInfo) intent.getParcelableExtra("networkInfo")).getDetailedState());
            }
        }
    };
    private final IntentFilter mIntentFilter = new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED");

    public void get_wifisetting() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void LOGD(String str) {
        Log.d("Wifi Enabler", str);
    }

    public Wifi_Enabler(Context context, Handler handler) {
        this.mContext = context;
        this.mHandler = handler;
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
        this.mIntentFilter.addAction("android.net.wifi.supplicant.STATE_CHANGE");
        this.mIntentFilter.addAction("android.net.wifi.STATE_CHANGE");
    }

    public void getWifiDefault() {
        if (Settings.Secure.getInt(this.mContext.getContentResolver(), "wifi_networks_available_notification_on", 0) != 1) {
        }
        if (this.mOpen) {
            this.mHandler.sendEmptyMessage(100);
        } else {
            this.mHandler.sendEmptyMessage(1000);
        }
    }

    private void upDateWifiStatus(String str) {
        if (Settings.Secure.getInt(this.mContext.getContentResolver(), "wifi_networks_available_notification_on", 0) != 1) {
        }
        boolean z = this.mOpen;
    }

    public void resume() {
        this.mContext.registerReceiver(this.mReceiver, this.mIntentFilter);
    }

    public void pause() {
        this.mContext.unregisterReceiver(this.mReceiver);
    }

    public void onWiFiClick() {
        boolean z = !this.mOpen;
        int wifiApState = this.mWifiManager.getWifiApState();
        if (z && (wifiApState == 12 || wifiApState == 13)) {
            this.mWifiManager.setWifiApEnabled(null, false);
        }
        if (this.mWifiManager.setWifiEnabled(z)) {
            this.mOpen = z;
            this.mContext.getResources().getString(R.string.turn_on);
        } else {
            this.mHandler.sendEmptyMessage(MediaPlayerGlue.FAST_FORWARD_REWIND_STEP);
        }
    }

    public void onNetWorkNotificaiton() {
        Settings.Secure.putInt(this.mContext.getContentResolver(), "wifi_networks_available_notification_on", (Settings.Secure.getInt(this.mContext.getContentResolver(), "wifi_networks_available_notification_on", 0) == 1) ^ true ? 1 : 0);
        this.mHandler.sendEmptyMessage(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleWifiStateChanged(int i) {
        LOGD("handleWifiStateChanged(),state = " + i);
        this.mHandler.sendEmptyMessage(i);
        switch (i) {
            case 0:
                this.mOpen = false;
                break;
            case 1:
                this.mOpen = false;
                break;
            case 2:
                this.mOpen = true;
                break;
            case 3:
                this.mOpen = true;
                break;
            default:
                this.mOpen = false;
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleStateChanged(NetworkInfo.DetailedState detailedState) {
        WifiInfo connectionInfo;
        String string;
        if (detailedState == null || (connectionInfo = this.mWifiManager.getConnectionInfo()) == null) {
            return;
        }
        if (connectionInfo.getSSID() != null) {
            string = Summary.get(this.mContext, connectionInfo.getSSID(), detailedState);
        } else {
            string = this.mContext.getString(R.string.turn_on);
        }
        Log.d("Wifi_Enabler", "info.getSSID() = " + connectionInfo.getSSID() + ",text = " + string);
    }

    public boolean getCurrentState() {
        this.mOpen = this.mWifiManager.isWifiEnabled();
        return this.mOpen;
    }
}
