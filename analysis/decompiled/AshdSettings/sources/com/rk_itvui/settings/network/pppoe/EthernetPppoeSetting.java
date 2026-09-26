package com.rk_itvui.settings.network.pppoe;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.EthernetManager;
import android.net.IpConfiguration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.ashd.settings.R;
import com.rk_itvui.settings.FullScreenActivity;
import com.rk_itvui.settings.network.EthernetIP;

/* JADX INFO: loaded from: classes.dex */
public class EthernetPppoeSetting extends FullScreenActivity {
    private static final String TAG = "EthernetPppoeSetting";
    Button cancel;
    Button confirm;
    private EthernetManager mEthMgr;
    private int mEthState;
    private EditText passWord;
    private EditText userName;
    public String USERINFO = "pppoeAccounts";
    private String DEFAULT_PHY_IFACE = "ehernet";
    private String mIface = this.DEFAULT_PHY_IFACE;
    EthernetIP ethernetIP = new EthernetIP();
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.rk_itvui.settings.network.pppoe.EthernetPppoeSetting.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.net.ethernet.ETHERNET_STATE_CHANGED")) {
                EthernetPppoeSetting.this.mEthState = intent.getIntExtra("ethernet_state", 0);
            } else {
                Log.d(EthernetPppoeSetting.TAG, "mEthState = " + EthernetPppoeSetting.this.mEthState);
            }
            EthernetPppoeSetting.this.refreshView();
        }
    };

    @Override // com.rk_itvui.settings.FullScreenActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.ethernetpppoe_setting);
        this.ethernetIP.transContext(this);
        readAccounts();
        init();
    }

    public void init() {
        this.mEthMgr = (EthernetManager) getSystemService("ethernet");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.ethernet.ETHERNET_STATE_CHANGED");
        this.confirm = (Button) findViewById(R.id.confirm);
        this.cancel = (Button) findViewById(R.id.cancel);
        this.mEthState = this.mEthMgr.getEthernetConnectState();
        registerReceiver(this.mReceiver, intentFilter);
    }

    public void refreshView() {
        TextView textView = (TextView) findViewById(R.id.netState);
        if (this.mEthMgr.getConfiguration().ipAssignment != IpConfiguration.IpAssignment.PPPOE) {
            Log.d(TAG, "do not in pppoe mode");
            this.confirm.setEnabled(true);
            textView.setText(R.string.pppoe_disconnected);
            this.cancel.setText(R.string.pppoe_button_cancel);
            return;
        }
        Log.d(TAG, "in pppoe mode: state = " + this.mEthMgr.dumpCurrentState(this.mEthState));
        if (this.mEthState == 0) {
            this.confirm.setEnabled(true);
            textView.setText(R.string.pppoe_disconnected);
            this.cancel.setText(R.string.pppoe_button_cancel);
            return;
        }
        if (3 == this.mEthState) {
            this.confirm.setEnabled(false);
            textView.setText(R.string.pppoe_disconnecting);
            this.cancel.setText(R.string.pppoe_button_cancel);
        } else if (1 == this.mEthState) {
            this.confirm.setEnabled(false);
            textView.setText(R.string.pppoe_connecting);
            this.cancel.setText(R.string.pppoe_button_cancel);
        } else if (2 == this.mEthState) {
            this.confirm.setEnabled(false);
            textView.setText(R.string.pppoe_connected);
            this.cancel.setText(R.string.pppoe_button_disconnect);
        }
    }

    public void onButtonSave(View view) {
        try {
            SharedPreferences.Editor editorEdit = getSharedPreferences(this.USERINFO, 0).edit();
            editorEdit.putString("username", this.userName.getText().toString());
            editorEdit.putString("password", this.passWord.getText().toString());
            editorEdit.commit();
        } catch (Exception unused) {
            Toast.makeText(this, "error", 1).show();
        }
        PppoeEnable(true, this.userName.getText().toString(), this.passWord.getText().toString(), "", "");
    }

    public void PppoeEnable(boolean z, String str, String str2, String str3, String str4) {
        if (z) {
            this.ethernetIP.setPppoeAccount(str);
            this.ethernetIP.setPppoePassword(str2);
            this.ethernetIP.switchEthernetMode(2);
            this.confirm.setEnabled(false);
            return;
        }
        int i = this.mEthState;
    }

    public void onButtonCancel(View view) {
        if (this.mEthState == 2) {
            this.mEthMgr.disconnect((String) null);
        } else {
            finish();
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mReceiver);
    }

    public void readAccounts() {
        this.userName = (EditText) findViewById(R.id.userNameValue);
        this.passWord = (EditText) findViewById(R.id.passWordValue);
        SharedPreferences sharedPreferences = getSharedPreferences(this.USERINFO, 0);
        this.userName.setText(sharedPreferences.getString("username", ""));
        this.passWord.setText(sharedPreferences.getString("password", ""));
    }

    public void netDialog(String str) {
        new AlertDialog.Builder(this).setMessage(str).setPositiveButton("确定", (DialogInterface.OnClickListener) null).show();
    }
}
