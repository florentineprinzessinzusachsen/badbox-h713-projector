package com.rk_itvui.settings.network.wifi;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import com.ashd.settings.R;
import com.rk_itvui.settings.FullScreenActivity;
import com.rk_itvui.settings.developer.ListViewAdapter;
import vendor.aw.homlet.tvsystem.tvserver.V1_0.TvSignalID;

/* JADX INFO: loaded from: classes.dex */
public class WifiAp_Settings extends FullScreenActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, TextWatcher {
    public static final int OPEN_INDEX = 0;
    public static final int WPA2_INDEX = 2;
    public static final int WPA_INDEX = 1;
    private LinearLayout ApContentLayout;
    private LinearLayout ApDisconnectedLayout;
    private LinearLayout ApOpenningLayout;
    private Switch apSwitcher;
    private EditText mPassword;
    private Spinner mSecurity;
    private EditText mSsid;
    WifiConfiguration mWifiConfig;
    private WifiManager mWifiManager;
    private Wifi_ApEnabler mWifi_ApEnabler;
    private TextView netState;
    private int mSecurityType = 0;
    private boolean mOpen = false;
    private Button mSwitch = null;
    boolean isClickButton = false;
    private int mDelayTimeBeforRestartWifiAp = 1000;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() { // from class: com.rk_itvui.settings.network.wifi.WifiAp_Settings.1
        @Override // java.lang.Runnable
        public void run() {
            WifiAp_Settings.this.setSwitchState(1000);
        }
    };
    private ListViewAdapter mListViewAdapter = null;
    private boolean mEditTextFocus_1 = false;
    private boolean mEditTextFocus_2 = false;
    private Handler mHandler = new Handler() { // from class: com.rk_itvui.settings.network.wifi.WifiAp_Settings.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            WifiAp_Settings.this.setSwitchState(message.what);
        }
    };

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView adapterView) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // com.rk_itvui.settings.FullScreenActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.wifiap_layout);
        init();
        getWifiApCurrentState();
        getStoredAp();
    }

    private void getWifiApCurrentState() {
        if (!getPackageManager().hasSystemFeature("android.hardware.wifi") || getPackageManager().hasSystemFeature("android.setting.portable_hotspot")) {
            return;
        }
        this.mWifi_ApEnabler = new Wifi_ApEnabler(this, this.mHandler);
        this.mWifi_ApEnabler.resume();
    }

    public void setSwitchState(int i) {
        this.handler.removeCallbacks(this.runnable);
        switch (i) {
            case 10:
                this.mSwitch.setBackgroundResource(R.drawable.switch_off);
                this.mSwitch.setEnabled(false);
                this.mOpen = false;
                this.ApContentLayout.setVisibility(0);
                this.ApDisconnectedLayout.setVisibility(8);
                this.ApOpenningLayout.setVisibility(8);
                break;
            case 11:
                if (!this.isClickButton) {
                    this.mSwitch.setBackgroundResource(R.drawable.switch_off);
                    this.mSwitch.setEnabled(true);
                    this.ApContentLayout.setVisibility(8);
                    this.ApDisconnectedLayout.setVisibility(0);
                    this.ApOpenningLayout.setVisibility(8);
                    this.mOpen = false;
                } else {
                    this.isClickButton = false;
                }
                break;
            case 12:
                this.mSwitch.setBackgroundResource(R.drawable.switch_off);
                this.mSwitch.setEnabled(false);
                this.ApContentLayout.setVisibility(8);
                this.ApDisconnectedLayout.setVisibility(8);
                this.ApOpenningLayout.setVisibility(0);
                break;
            case 13:
                this.mSwitch.setBackgroundResource(R.drawable.switch_on);
                this.mSwitch.setEnabled(true);
                this.ApContentLayout.setVisibility(0);
                this.ApDisconnectedLayout.setVisibility(8);
                this.ApOpenningLayout.setVisibility(8);
                this.mOpen = true;
                break;
            default:
                this.mSwitch.setBackgroundResource(R.drawable.switch_off);
                this.mSwitch.setEnabled(true);
                this.ApContentLayout.setVisibility(8);
                this.ApDisconnectedLayout.setVisibility(0);
                this.ApOpenningLayout.setVisibility(8);
                this.mOpen = false;
                break;
        }
    }

    void init() {
        this.mSecurity = (Spinner) findViewById(R.id.wifi_securityValue);
        this.mSwitch = (Button) findViewById(R.id.switcher);
        this.mSwitch.setBackgroundResource(R.drawable.switch_off);
        this.mOpen = false;
        this.netState = (TextView) findViewById(R.id.netState);
        this.mSsid = (EditText) findViewById(R.id.netSsidValue);
        this.mPassword = (EditText) findViewById(R.id.passwordValue);
        this.ApContentLayout = (LinearLayout) findViewById(R.id.wifi_ap_setting_content);
        this.ApDisconnectedLayout = (LinearLayout) findViewById(R.id.tv_wifi_ap_content);
        this.ApOpenningLayout = (LinearLayout) findViewById(R.id.wifi_ap_wait);
        setSwitchState(11);
        this.mSsid.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.rk_itvui.settings.network.wifi.WifiAp_Settings.2
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 0) {
                    return false;
                }
                WifiAp_Settings.this.showSoftInput_1();
                return true;
            }
        });
        this.mPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.rk_itvui.settings.network.wifi.WifiAp_Settings.3
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 0) {
                    return false;
                }
                WifiAp_Settings.this.showSoftInput_2();
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSoftInput_1() {
        this.mSsid.requestFocus();
        ((InputMethodManager) getSystemService("input_method")).showSoftInput(this.mSsid, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSoftInput_2() {
        this.mPassword.requestFocus();
        ((InputMethodManager) getSystemService("input_method")).showSoftInput(this.mPassword, 0);
    }

    void getStoredAp() {
        initStoredInfo();
    }

    public void onClickAP(View view) {
        this.handler.postDelayed(this.runnable, 10000L);
        if (this.mOpen) {
            this.ApContentLayout.setVisibility(8);
            this.ApDisconnectedLayout.setVisibility(8);
            this.ApOpenningLayout.setVisibility(0);
        }
        this.mWifi_ApEnabler.onPreferenceChange();
    }

    public WifiConfiguration getConfig() {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = this.mSsid.getText().toString();
        switch (this.mSecurityType) {
            case 0:
                wifiConfiguration.allowedKeyManagement.set(0);
                return wifiConfiguration;
            case 1:
                wifiConfiguration.allowedKeyManagement.set(1);
                wifiConfiguration.allowedAuthAlgorithms.set(0);
                if (this.mPassword.length() != 0) {
                    wifiConfiguration.preSharedKey = this.mPassword.getText().toString();
                }
                return wifiConfiguration;
            case 2:
                wifiConfiguration.allowedKeyManagement.set(4);
                wifiConfiguration.allowedAuthAlgorithms.set(0);
                if (this.mPassword.length() != 0) {
                    wifiConfiguration.preSharedKey = this.mPassword.getText().toString();
                }
                return wifiConfiguration;
            default:
                return null;
        }
    }

    public static int getSecurityTypeIndex(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.allowedKeyManagement.get(1)) {
            return 1;
        }
        return wifiConfiguration.allowedKeyManagement.get(4) ? 2 : 0;
    }

    protected void initStoredInfo() {
        this.mWifiManager = (WifiManager) getSystemService("wifi");
        this.mWifiConfig = this.mWifiManager.getWifiApConfiguration();
        this.mSecurityType = getSecurityTypeIndex(this.mWifiConfig);
        if (this.mWifiConfig != null) {
            this.mSsid.setText(this.mWifiConfig.SSID);
            this.mSecurity.setSelection(this.mSecurityType);
            if (this.mSecurityType == 1 || this.mSecurityType == 2) {
                this.mPassword.setText(this.mWifiConfig.preSharedKey);
            }
            this.mSsid.addTextChangedListener(this);
            this.mPassword.addTextChangedListener(this);
            this.mSecurity.setOnItemSelectedListener(this);
            ((CheckBox) findViewById(R.id.show_password)).setOnClickListener(this);
            showSecurityFields();
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        validate();
    }

    private void validate() {
        if ((this.mSsid != null && this.mSsid.length() == 0) || ((this.mSecurityType == 1 || this.mSecurityType == 2) && this.mPassword.length() < 8)) {
            findViewById(R.id.confirm).setEnabled(false);
        } else {
            findViewById(R.id.confirm).setEnabled(true);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        this.mSecurityType = i;
        showSecurityFields();
        validate();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.mPassword.setInputType((((CheckBox) view).isChecked() ? TvSignalID.SIGNALID_SCART_MAC640480 : 128) | 1);
    }

    private void showSecurityFields() {
        findViewById(R.id.passwordRow).setVisibility(0);
        findViewById(R.id.passwordValue).setVisibility(0);
    }

    /* JADX WARN: Type inference failed for: r2v7, types: [com.rk_itvui.settings.network.wifi.WifiAp_Settings$5] */
    public void onButtonSave(View view) {
        this.isClickButton = true;
        this.mWifiConfig = getConfig();
        if (this.mWifiConfig != null) {
            if (this.mWifiManager.getWifiApState() == 13) {
                new Thread() { // from class: com.rk_itvui.settings.network.wifi.WifiAp_Settings.5
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        WifiAp_Settings.this.mWifiManager.setWifiApEnabled(null, false);
                        try {
                            Thread.sleep(WifiAp_Settings.this.mDelayTimeBeforRestartWifiAp);
                        } catch (InterruptedException unused) {
                        }
                        WifiAp_Settings.this.mWifiManager.setWifiApEnabled(WifiAp_Settings.this.mWifiConfig, true);
                    }
                }.start();
                updateConfigSummary(this.mWifiConfig);
            } else {
                this.mWifiManager.setWifiApConfiguration(this.mWifiConfig);
            }
        }
    }

    public void onButtonCancel(View view) {
        finish();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mWifi_ApEnabler.pause();
    }

    void updateConfigSummary(WifiConfiguration wifiConfiguration) {
        String string = getString(android.R.string.face_acquired_too_different);
        String string2 = getString(R.string.wifi_tether_enabled_subtext);
        Object[] objArr = new Object[1];
        if (wifiConfiguration != null) {
            string = wifiConfiguration.SSID;
        }
        objArr[0] = string;
        String.format(string2, objArr);
    }
}
