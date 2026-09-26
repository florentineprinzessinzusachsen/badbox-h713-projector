package com.rk_itvui.settings.network.wifi;

import android.R;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.provider.Settings;

/* JADX INFO: loaded from: classes.dex */
public class Wifi_ApEnabler {
    ConnectivityManager mCm;
    private final Context mContext;
    private Handler mHandler;
    private boolean mOpen;
    private WifiManager mWifiManager;
    private String[] mWifiRegexs;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.rk_itvui.settings.network.wifi.Wifi_ApEnabler.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.net.wifi.WIFI_AP_STATE_CHANGED".equals(action)) {
                Wifi_ApEnabler.this.handleWifiApStateChanged(intent.getIntExtra("wifi_state", 14));
                return;
            }
            if ("android.net.conn.TETHER_STATE_CHANGED".equals(action)) {
                Wifi_ApEnabler.this.updateTetherState(intent.getStringArrayListExtra("availableArray").toArray(), intent.getStringArrayListExtra("activeArray").toArray(), intent.getStringArrayListExtra("erroredArray").toArray());
                return;
            }
            if ("android.intent.action.AIRPLANE_MODE".equals(action)) {
                Wifi_ApEnabler.this.enableWifiSwitch();
            }
        }
    };
    private final IntentFilter mIntentFilter = new IntentFilter("android.net.wifi.WIFI_AP_STATE_CHANGED");

    /* JADX INFO: Access modifiers changed from: private */
    public void enableWifiSwitch() {
    }

    public Wifi_ApEnabler(Context context, Handler handler) {
        this.mOpen = false;
        this.mContext = context;
        this.mHandler = handler;
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
        this.mCm = (ConnectivityManager) this.mContext.getSystemService("connectivity");
        this.mWifiRegexs = this.mCm.getTetherableWifiRegexs();
        this.mOpen = false;
        this.mIntentFilter.addAction("android.net.conn.TETHER_STATE_CHANGED");
        this.mIntentFilter.addAction("android.intent.action.AIRPLANE_MODE");
    }

    public void resume() {
        this.mContext.registerReceiver(this.mReceiver, this.mIntentFilter);
        enableWifiSwitch();
    }

    public void pause() {
        this.mContext.unregisterReceiver(this.mReceiver);
    }

    public void setSoftapEnabled(boolean z) {
        int i;
        ContentResolver contentResolver = this.mContext.getContentResolver();
        int wifiState = this.mWifiManager.getWifiState();
        if (z && (wifiState == 2 || wifiState == 3)) {
            this.mWifiManager.setWifiEnabled(false);
            Settings.Global.putInt(contentResolver, "wifi_saved_state", 1);
        }
        this.mWifiManager.setWifiApEnabled(null, z);
        if (z) {
            return;
        }
        try {
            i = Settings.Global.getInt(contentResolver, "wifi_saved_state");
        } catch (Settings.SettingNotFoundException unused) {
            i = 0;
        }
        if (i == 1) {
            this.mWifiManager.setWifiEnabled(true);
            Settings.Global.putInt(contentResolver, "wifi_saved_state", 0);
        }
    }

    public boolean onPreferenceChange() {
        int i;
        ContentResolver contentResolver = this.mContext.getContentResolver();
        boolean z = !this.mOpen;
        int wifiState = this.mWifiManager.getWifiState();
        if (z && (wifiState == 2 || wifiState == 3)) {
            this.mWifiManager.setWifiEnabled(false);
            Settings.Global.putInt(contentResolver, "wifi_saved_state", 1);
        }
        this.mWifiManager.setWifiApEnabled(null, z);
        if (!z) {
            try {
                i = Settings.Global.getInt(contentResolver, "wifi_saved_state");
            } catch (Settings.SettingNotFoundException unused) {
                i = 0;
            }
            if (i == 1) {
                this.mWifiManager.setWifiEnabled(true);
                Settings.Global.putInt(contentResolver, "wifi_saved_state", 0);
            }
        }
        return false;
    }

    public void updateConfigSummary(WifiConfiguration wifiConfiguration) {
        this.mContext.getString(R.string.face_acquired_too_different);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTetherState(Object[] objArr, Object[] objArr2, Object[] objArr3) {
        int length = objArr2.length;
        int i = 0;
        boolean z = false;
        while (i < length) {
            String str = (String) objArr2[i];
            boolean z2 = z;
            for (String str2 : this.mWifiRegexs) {
                if (str.matches(str2)) {
                    z2 = true;
                }
            }
            i++;
            z = z2;
        }
        for (Object obj : objArr3) {
            String str3 = (String) obj;
            for (String str4 : this.mWifiRegexs) {
                str3.matches(str4);
            }
        }
        if (z) {
            updateConfigSummary(this.mWifiManager.getWifiApConfiguration());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleWifiApStateChanged(int i) {
        this.mHandler.sendEmptyMessage(i);
        this.mOpen = false;
        if (i == 13) {
            this.mOpen = true;
        }
    }
}
