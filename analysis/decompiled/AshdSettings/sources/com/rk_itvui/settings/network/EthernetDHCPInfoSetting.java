package com.rk_itvui.settings.network;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.EthernetManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ashd.settings.R;
import com.rk_itvui.settings.FullScreenActivity;

/* JADX INFO: loaded from: classes.dex */
public class EthernetDHCPInfoSetting extends FullScreenActivity {
    private static final String TAG = "EthernetDHCPInfoSetting";
    public LinearLayout dhcpConnecting;
    public LinearLayout dhcpContent;
    public LinearLayout dhcpFailed;
    public TextView dhcpStateContent;
    public TextView dns1;
    public TextView dns2;
    public TextView gateway;
    public TextView ipaddr;
    EthernetManager mEthMgr;
    private IntentFilter mIntentFilter;
    public TextView netmask;
    public TextView netstate;
    boolean isFirstDHCP = false;
    public EthernetIP ethernetIP = new EthernetIP();
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.rk_itvui.settings.network.EthernetDHCPInfoSetting.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.net.ethernet.ETHERNET_STATE_CHANGED")) {
                EthernetDHCPInfoSetting.this.getEthInfoFromDhcp(intent.getIntExtra("ethernet_state", 0));
            }
        }
    };

    public void getEthInfoFromDhcp(int i) {
        switch (i) {
            case 0:
                this.netstate.setText(R.string.dhcp_disconnected);
                this.dhcpConnecting.setVisibility(8);
                this.dhcpContent.setVisibility(8);
                this.dhcpFailed.setVisibility(0);
                this.dhcpStateContent.setText(R.string.dhcp_notconfig);
                updateNetInfo(false);
                break;
            case 1:
                this.netstate.setText(R.string.dhcp_connecting);
                this.dhcpConnecting.setVisibility(0);
                this.dhcpContent.setVisibility(8);
                this.dhcpFailed.setVisibility(8);
                break;
            case 2:
                this.dhcpStateContent.setText(R.string.dhcp_state_connected);
                this.netstate.setText(R.string.dhcp_connected);
                this.dhcpConnecting.setVisibility(8);
                this.dhcpContent.setVisibility(0);
                this.dhcpFailed.setVisibility(8);
                updateNetInfo(true);
                break;
        }
    }

    public void updateNetInfo(boolean z) {
        if (z) {
            this.ipaddr.setText(this.ethernetIP.getIPAddress((char) 1));
            this.netmask.setText(this.ethernetIP.getNetMask((char) 1));
            this.gateway.setText(this.ethernetIP.getGateWay((char) 1));
            this.dns1.setText(this.ethernetIP.getDNS1((char) 1));
            this.dns2.setText(this.ethernetIP.getDNS2((char) 1));
            return;
        }
        this.ipaddr.setText("");
        this.netmask.setText("");
        this.gateway.setText("");
        this.dns1.setText("");
        this.dns2.setText("");
    }

    public void findView() {
        this.netstate = (TextView) findViewById(R.id.netState);
        this.ipaddr = (TextView) findViewById(R.id.ipAddrValue);
        this.netmask = (TextView) findViewById(R.id.netMaskValue);
        this.gateway = (TextView) findViewById(R.id.gateWayValue);
        this.dns1 = (TextView) findViewById(R.id.dns1Value);
        this.dns2 = (TextView) findViewById(R.id.dns2Value);
        this.dhcpContent = (LinearLayout) findViewById(R.id.dhcp_content);
        this.dhcpFailed = (LinearLayout) findViewById(R.id.dhcp_failed);
        this.dhcpConnecting = (LinearLayout) findViewById(R.id.dhcp_connecting);
        this.dhcpStateContent = (TextView) findViewById(R.id.dhcpstatecontent);
    }

    @Override // com.rk_itvui.settings.FullScreenActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.ethernetdhcpinfo_setting);
        this.ethernetIP.transContext(this);
        findView();
        this.mIntentFilter = new IntentFilter("android.net.ethernet.ETHERNET_STATE_CHANGED");
        this.mEthMgr = (EthernetManager) getSystemService("ethernet");
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.ethernetIP.switchEthernetMode(0);
        registerReceiver(this.mReceiver, this.mIntentFilter);
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mReceiver);
    }

    public void netDialog(String str) {
        new AlertDialog.Builder(this).setMessage(str).setPositiveButton("确定", (DialogInterface.OnClickListener) null).show();
    }
}
