package com.rk_itvui.settings.network;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemProperties;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ashd.settings.R;

/* JADX INFO: loaded from: classes.dex */
public class WireNetworkFragment extends Fragment {
    private TextView dns1;
    private TextView dns2;
    private EthernetIP ethernetIP;
    private TextView gateWay;
    private TextView ipAddr;
    private Context mContext;
    private Handler mHandler;
    private IntentFilter mIntentFilter;
    private int mPppoeState;
    private TextView netMask;
    private TextView netMode;
    private String sGateway;
    private String sIpAddress;
    private String sNetmask;
    private String sdns1;
    private String sdns2;
    private View wirenetworksettingLayout;
    private boolean isConnected = false;
    private String nullIpInfo = "0.0.0.0";
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.rk_itvui.settings.network.WireNetworkFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            intent.getIntExtra("ethernet_state", 0);
            WireNetworkFragment.this.checkNetMode();
        }
    };

    public void getHandlerMethod(Handler handler) {
    }

    public WireNetworkFragment() {
    }

    public WireNetworkFragment(Handler handler, Context context) {
        this.mHandler = handler;
        this.mContext = context;
    }

    public void resume() {
        this.mContext.registerReceiver(this.mReceiver, this.mIntentFilter);
    }

    public void pause() {
        this.mContext.unregisterReceiver(this.mReceiver);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.wirenetworksettingLayout = layoutInflater.inflate(R.layout.wirenetwork_fragment, viewGroup, false);
        this.mIntentFilter = new IntentFilter("android.net.ethernet.ETHERNET_STATE_CHANGED");
        init();
        checkNetMode();
        resume();
        return this.wirenetworksettingLayout;
    }

    void init() {
        this.ipAddr = (TextView) this.wirenetworksettingLayout.findViewById(R.id.ipAddrValue);
        this.netMask = (TextView) this.wirenetworksettingLayout.findViewById(R.id.netMaskValue);
        this.gateWay = (TextView) this.wirenetworksettingLayout.findViewById(R.id.gateWayValue);
        this.dns1 = (TextView) this.wirenetworksettingLayout.findViewById(R.id.masterDNSValue);
        this.dns2 = (TextView) this.wirenetworksettingLayout.findViewById(R.id.backupDNSValue);
        this.netMode = (TextView) this.wirenetworksettingLayout.findViewById(R.id.network_mode);
        this.ethernetIP = new EthernetIP();
        if (this.mContext == null) {
            this.mContext = getActivity();
        }
        this.ethernetIP.transContext(this.mContext);
    }

    public void checkNetMode() {
        char c = 0;
        if (!this.ethernetIP.isConnected()) {
            this.netMode.setText(R.string.disconncect);
            this.isConnected = false;
            updateFragment();
            return;
        }
        if (this.ethernetIP.isUsingStaticIp()) {
            this.netMode.setText(R.string.staticIP);
        } else {
            this.netMode.setText(R.string.dhcp);
            c = 1;
        }
        this.sIpAddress = this.ethernetIP.getIPAddress(c);
        this.sNetmask = this.ethernetIP.getNetMask(c);
        this.sGateway = this.ethernetIP.getGateWay(c);
        this.sdns1 = this.ethernetIP.getDNS1(c);
        this.sdns2 = this.ethernetIP.getDNS2(c);
        this.isConnected = true;
        updateFragment();
    }

    public void updateFragment() {
        if (this.mHandler == null) {
            return;
        }
        if (this.isConnected) {
            this.ipAddr.setText(this.sIpAddress);
            this.netMask.setText(this.sNetmask);
            this.gateWay.setText(this.sGateway);
            this.dns1.setText(this.sdns1);
            this.dns2.setText(this.sdns2);
            this.mHandler.sendEmptyMessage(1);
            return;
        }
        this.ipAddr.setText("");
        this.netMask.setText("");
        this.gateWay.setText("");
        this.dns1.setText("");
        this.dns2.setText("");
        this.mHandler.sendEmptyMessage(0);
    }

    public void getPppoeInfoFromProperties() {
        String str = SystemProperties.get("net.ppp0.local-ip");
        Log.d("net.ppp0.local-ip", "====================================" + str);
        if (str != null && !str.equals("")) {
            this.sIpAddress = str;
        } else {
            this.sIpAddress = this.nullIpInfo;
        }
        String str2 = SystemProperties.get("net.ppp0.mask");
        Log.d("net.ppp0.mask", "====================================" + str2);
        if (str2 != null && !str2.equals("")) {
            this.sNetmask = str2;
        } else {
            this.sNetmask = this.nullIpInfo;
        }
        String str3 = SystemProperties.get("net.ppp0.remote-ip");
        Log.d("net.ppp0.remote-ip", "====================================" + str3);
        if (str3 != null && !str3.equals("")) {
            this.sGateway = str3;
        } else {
            this.sGateway = this.nullIpInfo;
        }
        String str4 = SystemProperties.get("net.ppp0.dns1");
        Log.d("net.ppp0.dns1", "====================================" + str4);
        if (str4 != null && !str4.equals("")) {
            this.sdns1 = str4;
        } else {
            this.sdns1 = this.nullIpInfo;
        }
        String str5 = SystemProperties.get("net.ppp0.dns2");
        Log.d("net.ppp0.dns2", "====================================" + str5);
        if (str5 != null && !str5.equals("")) {
            this.sdns2 = str5;
        } else {
            this.sdns2 = this.nullIpInfo;
        }
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        pause();
        super.onDestroyView();
    }
}
