package com.rk_itvui.settings.network;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiConfiguration;
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
public class WifiApFragment extends Fragment {
    public static final int OPEN_INDEX = 0;
    public static final int WPA2_INDEX = 2;
    public static final int WPA_INDEX = 1;
    ConnectivityManager mCm;
    private Context mContext;
    private IntentFilter mIntentFilter;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.rk_itvui.settings.network.WifiApFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.net.wifi.WIFI_AP_STATE_CHANGED".equals(action)) {
                WifiApFragment.this.handleWifiApStateChanged(intent.getIntExtra("wifi_state", 14));
            } else if ("android.net.conn.TETHER_STATE_CHANGED".equals(action)) {
                WifiApFragment.this.updateTetherState(intent.getStringArrayListExtra("availableArray").toArray(), intent.getStringArrayListExtra("activeArray").toArray(), intent.getStringArrayListExtra("erroredArray").toArray());
            }
        }
    };
    private WifiManager mWifiManager;
    private String[] mWifiRegexs;
    private final Handler mhandler;
    TextView netssid;
    View wifiApFragmentLayout;
    TextView wifiApPassword;
    TextView wifiSecurityType;

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.wifiApFragmentLayout = layoutInflater.inflate(R.layout.wifiap_fragment, viewGroup, false);
        init();
        resume();
        return this.wifiApFragmentLayout;
    }

    public WifiApFragment(Handler handler, Context context) {
        this.mhandler = handler;
        this.mContext = context;
    }

    public void init() {
        this.mWifiManager = (WifiManager) this.mContext.getSystemService("wifi");
        this.mCm = (ConnectivityManager) this.mContext.getSystemService("connectivity");
        this.mWifiRegexs = this.mCm.getTetherableWifiRegexs();
        this.mIntentFilter = new IntentFilter("android.net.wifi.WIFI_AP_STATE_CHANGED");
        this.mIntentFilter.addAction("android.net.conn.TETHER_STATE_CHANGED");
        this.mIntentFilter.addAction("android.intent.action.AIRPLANE_MODE");
        this.netssid = (TextView) this.wifiApFragmentLayout.findViewById(R.id.wifiSsidValue);
        this.wifiSecurityType = (TextView) this.wifiApFragmentLayout.findViewById(R.id.wifiSecurityType);
        this.wifiApPassword = (TextView) this.wifiApFragmentLayout.findViewById(R.id.wifiApPassword);
    }

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
    public void updateTetherState(Object[] objArr, Object[] objArr2, Object[] objArr3) {
        LOGD("updateTetherState~~~~~~~~~~~~~~~~~~~~~~~~~~~");
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

    public static int getSecurityTypeIndex(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.allowedKeyManagement.get(1)) {
            return 1;
        }
        return wifiConfiguration.allowedKeyManagement.get(4) ? 2 : 0;
    }

    void updateConfigSummary(WifiConfiguration wifiConfiguration) {
        String string = this.mContext.getString(android.R.string.face_acquired_too_different);
        if (wifiConfiguration != null) {
            string = wifiConfiguration.SSID;
        }
        String str = String.format(string, new Object[0]);
        String str2 = wifiConfiguration.preSharedKey;
        Log.d("blb", "=========================================updateConfigSummary =" + str);
        switch (getSecurityTypeIndex(wifiConfiguration)) {
            case 0:
                this.wifiSecurityType.setText("Open");
                break;
            case 1:
                this.wifiSecurityType.setText("WPA PSK");
                break;
            case 2:
                this.wifiSecurityType.setText("WPA2 PSK");
                break;
            default:
                this.wifiSecurityType.setText("");
                break;
        }
        this.netssid.setText(str);
        this.wifiApPassword.setText(str2);
    }

    void updataMessage() {
        Log.d("blb", "=========================================updataMessage =");
    }

    void LOGD(String str) {
        Log.d("", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleWifiApStateChanged(int i) {
        if (i == 11) {
            this.netssid.setText("");
            this.wifiSecurityType.setText("");
            this.wifiApPassword.setText("");
        }
        this.mhandler.sendEmptyMessage(i);
    }
}
