package com.rk_itvui.settings.network;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ashd.settings.R;

/* JADX INFO: loaded from: classes.dex */
public class WifiFragment extends Fragment {
    private Context mContext;
    private IntentFilter mIntentFilter;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.rk_itvui.settings.network.WifiFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            WifiFragment.this.LOGD("BroadcastReceiver onReceive(), action = " + action);
            if ("android.net.wifi.WIFI_STATE_CHANGED".equals(action)) {
                WifiFragment.this.handleWifiStateChanged(intent.getIntExtra("wifi_state", 4));
            } else if ("android.net.wifi.supplicant.STATE_CHANGE".equals(action)) {
                WifiFragment.this.handleStateChanged(WifiInfo.getDetailedStateOf((SupplicantState) intent.getParcelableExtra("newState")));
            } else if ("android.net.wifi.STATE_CHANGE".equals(action)) {
                WifiFragment.this.handleStateChanged(((NetworkInfo) intent.getParcelableExtra("networkInfo")).getDetailedState());
            }
        }
    };
    private WifiManager mWifiManager;
    private Handler parentHandler;
    private View wifiFragmentLayout;

    public void resume() {
        this.mContext.registerReceiver(this.mReceiver, this.mIntentFilter);
    }

    public void pause() {
        try {
            this.mContext.unregisterReceiver(this.mReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void LOGD(String str) {
        Log.d("WifiEnabler", str);
    }

    public WifiFragment() {
    }

    public WifiFragment(Handler handler, Context context) {
        this.parentHandler = handler;
        this.mContext = context;
    }

    @Override // android.app.Fragment
    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }

    public void init() {
        this.mContext = getContext();
        if (this.mContext != null && this.mContext.getApplicationContext() != null) {
            this.mWifiManager = (WifiManager) getContext().getApplicationContext().getSystemService("wifi");
        }
        this.mIntentFilter = new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED");
        this.mIntentFilter.addAction("android.net.wifi.supplicant.STATE_CHANGE");
        this.mIntentFilter.addAction("android.net.wifi.STATE_CHANGE");
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        init();
        resume();
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.wifiFragmentLayout = layoutInflater.inflate(R.layout.wifi_fragment, viewGroup, false);
        return this.wifiFragmentLayout;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleWifiStateChanged(int i) {
        if (this.parentHandler == null) {
            return;
        }
        this.parentHandler.sendEmptyMessage(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleStateChanged(NetworkInfo.DetailedState detailedState) {
        WifiInfo connectionInfo;
        if (detailedState != null) {
            if (this.mWifiManager == null) {
                this.mWifiManager = (WifiManager) this.mContext.getApplicationContext().getSystemService("wifi");
            }
            if (this.mWifiManager == null || (connectionInfo = this.mWifiManager.getConnectionInfo()) == null) {
                return;
            }
            String string = connectionInfo.getSSID() == null ? this.mContext.getString(R.string.turn_on) : null;
            Log.d("Wifi_Enabler", "info.getSSID() = " + connectionInfo.getSSID() + ",text = " + string);
            if (string != null) {
                upDateFragmentState(string);
            }
            upDateNetInfo();
        }
    }

    private void upDateFragmentState(String str) {
        ((TextView) this.wifiFragmentLayout.findViewById(R.id.network_state)).setText(str);
    }

    private void upDateNetInfo() {
        if (this.mWifiManager == null) {
            this.mWifiManager = (WifiManager) this.mContext.getApplicationContext().getSystemService("wifi");
        }
        if (this.mWifiManager == null) {
            Log.e("WifiFragment", "mWifiManager == null");
            return;
        }
        WifiInfo connectionInfo = this.mWifiManager.getConnectionInfo();
        TextView textView = (TextView) this.wifiFragmentLayout.findViewById(R.id.ipAddrValue);
        TextView textView2 = (TextView) this.wifiFragmentLayout.findViewById(R.id.linkSpeedValue);
        TextView textView3 = (TextView) this.wifiFragmentLayout.findViewById(R.id.macAddressValue);
        String strIntToIp = intToIp(connectionInfo.getIpAddress());
        int linkSpeed = connectionInfo.getLinkSpeed();
        String macAddress = connectionInfo.getMacAddress();
        textView.setText(strIntToIp);
        textView2.setText(linkSpeed + "Mbs");
        textView3.setText(macAddress);
    }

    private String intToIp(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }
}
