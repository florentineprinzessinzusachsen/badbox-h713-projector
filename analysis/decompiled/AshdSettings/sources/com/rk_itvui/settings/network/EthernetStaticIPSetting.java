package com.rk_itvui.settings.network;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.ashd.settings.R;
import com.rk_itvui.settings.FullScreenActivity;

/* JADX INFO: loaded from: classes.dex */
public class EthernetStaticIPSetting extends FullScreenActivity {
    private static final int KEY_DNS1 = 2131361974;
    private static final int KEY_DNS2 = 2131361976;
    private static final int KEY_GATEWAY = 2131361999;
    private static final int KEY_IP_ADDRESS = 2131362062;
    private static final int KEY_NETMASK = 2131362193;
    private static final int KEY_USE_STATIC_IP = 2131362062;
    private static final String TAG = "EthernetStaticIPSetting";
    public EditText dns1;
    public EditText dns2;
    public EditText gateWay;
    public EditText ipAddr;
    public EditText netMask;
    private int[] mTexteditKeys = {R.id.ipAddrValue, R.id.gateWayValue, R.id.netMaskValue, R.id.dns1Value, R.id.dns2Value};
    EthernetIP ethernetIP = new EthernetIP();

    @Override // com.rk_itvui.settings.FullScreenActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.ethernetstaticip_setting);
        this.ethernetIP.transContext(this);
        updateIpSettingsInfo();
    }

    public void updateIpSettingsInfo() {
        this.ipAddr = (EditText) findViewById(R.id.ipAddrValue);
        this.netMask = (EditText) findViewById(R.id.netMaskValue);
        this.gateWay = (EditText) findViewById(R.id.gateWayValue);
        this.dns1 = (EditText) findViewById(R.id.dns1Value);
        this.dns2 = (EditText) findViewById(R.id.dns2Value);
        this.ipAddr.setText(TextUtils.isEmpty(this.ethernetIP.getIPAddress((char) 0)) ? EthernetIP.defaultIPAdress : this.ethernetIP.getIPAddress((char) 0));
        this.netMask.setText(TextUtils.isEmpty(this.ethernetIP.getNetMask((char) 0)) ? EthernetIP.defaultIPNetMask : this.ethernetIP.getNetMask((char) 0));
        this.gateWay.setText(TextUtils.isEmpty(this.ethernetIP.getGateWay((char) 0)) ? EthernetIP.defaultGateWay : this.ethernetIP.getGateWay((char) 0));
        this.dns1.setText(TextUtils.isEmpty(this.ethernetIP.getDNS1((char) 0)) ? EthernetIP.defaultDNS1 : this.ethernetIP.getDNS1((char) 0));
        this.dns2.setText(TextUtils.isEmpty(this.ethernetIP.getDNS2((char) 0)) ? "" : this.ethernetIP.getDNS2((char) 0));
    }

    public void onButtonSave(View view) {
        boolean z;
        if (isIpDataInUiComplete()) {
            this.ipAddr = (EditText) findViewById(R.id.ipAddrValue);
            this.netMask = (EditText) findViewById(R.id.netMaskValue);
            this.gateWay = (EditText) findViewById(R.id.gateWayValue);
            this.dns1 = (EditText) findViewById(R.id.dns1Value);
            this.dns2 = (EditText) findViewById(R.id.dns2Value);
            String string = this.ipAddr.getText().toString();
            String string2 = this.netMask.getText().toString();
            String string3 = this.gateWay.getText().toString();
            String string4 = this.dns1.getText().toString();
            String string5 = this.dns2.getText().toString();
            if (this.ethernetIP.setIPAddress(string)) {
                z = true;
            } else {
                netDialog("IP地址不正确");
                z = false;
            }
            if (!this.ethernetIP.setNetMask(string2)) {
                netDialog("子网掩码不正确");
                z = false;
            }
            if (!this.ethernetIP.setGateWay(string3)) {
                netDialog("网关不正确");
                z = false;
            }
            if (!this.ethernetIP.setDNS1(string4)) {
                netDialog("DNS1 is not right");
                z = false;
            }
            if (!string5.isEmpty() && !this.ethernetIP.setDNS2(string5)) {
                netDialog("DNS is not right");
                z = false;
            }
            if (z) {
                this.ethernetIP.switchEthernetMode(1);
                netDialog("OK");
                return;
            }
            return;
        }
        netDialog("填写不完整");
    }

    public void netDialog(String str) {
        new AlertDialog.Builder(this).setMessage(str).setPositiveButton("确定", (DialogInterface.OnClickListener) null).show();
    }

    public void onButtonCancel(View view) {
        updateIpSettingsInfo();
        finish();
    }

    private boolean isIpDataInUiComplete() {
        for (int i = 0; i < this.mTexteditKeys.length - 1; i++) {
            String string = ((EditText) findViewById(this.mTexteditKeys[i])).getText().toString();
            Log.d(TAG, " text = " + string);
            if (string == null || TextUtils.isEmpty(string)) {
                return false;
            }
        }
        return true;
    }
}
