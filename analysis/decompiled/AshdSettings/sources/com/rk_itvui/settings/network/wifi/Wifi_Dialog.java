package com.rk_itvui.settings.network.wifi;

import android.R;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.IpConfiguration;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.NetworkInfo;
import android.net.NetworkUtils;
import android.net.ProxyInfo;
import android.net.StaticIpConfiguration;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiEnterpriseConfig;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.security.KeyStore;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PointerIconCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.rk_itvui.settings.ProxySelector;
import com.rk_itvui.settings.ReflectionUtils;
import com.rk_itvui.settings.Utils;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Iterator;
import vendor.aw.homlet.tvsystem.tvserver.V1_0.TvSignalID;

/* JADX INFO: loaded from: classes.dex */
public class Wifi_Dialog extends AlertDialog implements View.OnClickListener, TextWatcher, AdapterView.OnItemSelectedListener {
    public static final int BUTTON_FORGET = -3;
    public static final int BUTTON_SUBMIT = -1;
    private static final int DHCP = 0;
    private static final String KEYSTORE_SPACE = "keystore://";
    public static final int MANUAL = 0;
    public static final int PROXY_NONE = 0;
    public static final int PROXY_STATIC = 1;
    private static final int STATIC_IP = 1;
    private static final String TAG = "WifiConfigController";
    public static final int WIFI_EAP_METHOD_PEAP = 0;
    public static final int WIFI_EAP_METHOD_PWD = 3;
    public static final int WIFI_EAP_METHOD_TLS = 1;
    public static final int WIFI_EAP_METHOD_TTLS = 2;
    public static final int WIFI_PEAP_PHASE2_GTC = 2;
    public static final int WIFI_PEAP_PHASE2_MSCHAPV2 = 1;
    public static final int WIFI_PEAP_PHASE2_NONE = 0;
    public static final int WPS_DISPLAY = 3;
    public static final int WPS_KEYPAD = 2;
    public static final int WPS_PBC = 1;
    private static final int unspecifiedCertIndex = 0;
    private final ArrayAdapter<String> PHASE2_FULL_ADAPTER;
    private final ArrayAdapter<String> PHASE2_PEAP_ADAPTER;
    public final boolean edit;
    private final InputMethodManager inputMethodManager;
    private final AccessPoint mAccessPoint;
    private int mAccessPointSecurity;
    private Context mContext;
    private TextView mDns1View;
    private TextView mDns2View;
    private TextView mEapAnonymous;
    private TextView mEapAnonymousView;
    private Spinner mEapCaCert;
    private Spinner mEapCaCertSpinner;
    private TextView mEapIdentity;
    private TextView mEapIdentityView;
    private Spinner mEapMethod;
    private Spinner mEapMethodSpinner;
    private Spinner mEapUserCert;
    private Spinner mEapUserCertSpinner;
    public final boolean mEdit;
    private TextView mGatewayView;
    private ProxyInfo mHttpProxy;
    private TextView mIpAddressView;
    private IpConfiguration.IpAssignment mIpAssignment;
    private Spinner mIpSettingsSpinner;
    private LinkProperties mLinkProperties;
    private final DialogInterface.OnClickListener mListener;
    private TextView mNetworkPrefixLengthView;
    private Spinner mNetworkSetupSpinner;
    private TextView mPasswordView;
    private Spinner mPhase2;
    private ArrayAdapter<String> mPhase2Adapter;
    private Spinner mPhase2Spinner;
    private TextView mProxyExclusionListView;
    private TextView mProxyHostView;
    private TextView mProxyPortView;
    private IpConfiguration.ProxySettings mProxySettings;
    private Spinner mProxySettingsSpinner;
    private int mSecurity;
    private Spinner mSecuritySpinner;
    private TextView mSsid;
    private StaticIpConfiguration mStaticIpConfiguration;
    private View mView;
    private String unspecifiedCert;

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView adapterView) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public Wifi_Dialog(Context context, DialogInterface.OnClickListener onClickListener, AccessPoint accessPoint, boolean z) {
        super(context);
        this.unspecifiedCert = "unspecified";
        this.mLinkProperties = new LinkProperties();
        this.mIpAssignment = IpConfiguration.IpAssignment.UNASSIGNED;
        this.mProxySettings = IpConfiguration.ProxySettings.UNASSIGNED;
        this.mHttpProxy = null;
        this.mStaticIpConfiguration = null;
        this.mContext = context;
        this.inputMethodManager = (InputMethodManager) this.mContext.getSystemService("input_method");
        this.edit = z;
        this.mEdit = z;
        this.mListener = onClickListener;
        this.mAccessPoint = accessPoint;
        this.mSecurity = accessPoint == null ? 0 : accessPoint.security;
        this.PHASE2_PEAP_ADAPTER = new ArrayAdapter<>(context, R.layout.simple_spinner_item, context.getResources().getStringArray(com.ashd.settings.R.array.wifi_peap_phase2_entries));
        this.PHASE2_PEAP_ADAPTER.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        this.PHASE2_FULL_ADAPTER = new ArrayAdapter<>(context, R.layout.simple_spinner_item, context.getResources().getStringArray(com.ashd.settings.R.array.wifi_phase2_entries));
        this.PHASE2_FULL_ADAPTER.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        this.unspecifiedCert = context.getString(com.ashd.settings.R.string.wifi_unspecified);
    }

    public boolean isEdit() {
        return this.mEdit;
    }

    @RequiresApi(api = 30)
    @SuppressLint({"WrongConstant"})
    WifiConfiguration getConfig() {
        if (this.mAccessPoint != null && this.mAccessPoint.networkId != -1 && !this.mEdit) {
            Log.e(TAG, "getConfig null");
            return null;
        }
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        if (this.mAccessPoint == null) {
            wifiConfiguration.SSID = AccessPoint.convertToQuotedString(this.mSsid.getText().toString());
            wifiConfiguration.hiddenSSID = true;
        } else if (this.mAccessPoint.networkId == -1) {
            wifiConfiguration.SSID = AccessPoint.convertToQuotedString(this.mAccessPoint.ssid);
        } else {
            wifiConfiguration.networkId = this.mAccessPoint.networkId;
        }
        int i = this.mSecurity;
        if (i != 5) {
            switch (i) {
                case 0:
                    wifiConfiguration.allowedKeyManagement.set(0);
                    break;
                case 1:
                    wifiConfiguration.allowedKeyManagement.set(0);
                    wifiConfiguration.allowedAuthAlgorithms.set(0);
                    wifiConfiguration.allowedAuthAlgorithms.set(1);
                    if (this.mPasswordView.length() != 0) {
                        int length = this.mPasswordView.length();
                        String string = this.mPasswordView.getText().toString();
                        if ((length == 10 || length == 26 || length == 58) && string.matches("[0-9A-Fa-f]*")) {
                            wifiConfiguration.wepKeys[0] = string;
                        } else {
                            wifiConfiguration.wepKeys[0] = '\"' + string + '\"';
                        }
                    }
                    break;
                case 2:
                    wifiConfiguration.allowedKeyManagement.set(1);
                    if (this.mPasswordView.length() != 0) {
                        String string2 = this.mPasswordView.getText().toString();
                        if (string2.matches("[0-9A-Fa-f]{64}")) {
                            wifiConfiguration.preSharedKey = string2;
                        } else {
                            wifiConfiguration.preSharedKey = '\"' + string2 + '\"';
                        }
                    }
                    break;
                case 3:
                    wifiConfiguration.allowedKeyManagement.set(2);
                    wifiConfiguration.allowedKeyManagement.set(3);
                    wifiConfiguration.enterpriseConfig = new WifiEnterpriseConfig();
                    int selectedItemPosition = this.mEapMethodSpinner.getSelectedItemPosition();
                    int selectedItemPosition2 = this.mPhase2Spinner.getSelectedItemPosition();
                    wifiConfiguration.enterpriseConfig.setEapMethod(selectedItemPosition);
                    if (selectedItemPosition == 0) {
                        switch (selectedItemPosition2) {
                            case 0:
                                wifiConfiguration.enterpriseConfig.setPhase2Method(0);
                                break;
                            case 1:
                                wifiConfiguration.enterpriseConfig.setPhase2Method(3);
                                break;
                            case 2:
                                wifiConfiguration.enterpriseConfig.setPhase2Method(4);
                                break;
                            default:
                                Log.e(TAG, "Unknown phase2 method" + selectedItemPosition2);
                                break;
                        }
                    } else {
                        wifiConfiguration.enterpriseConfig.setPhase2Method(selectedItemPosition2);
                    }
                    String str = (String) this.mEapCaCertSpinner.getSelectedItem();
                    if (str.equals(this.unspecifiedCert)) {
                        str = "";
                    }
                    wifiConfiguration.enterpriseConfig.setCaCertificateAlias(str);
                    String str2 = (String) this.mEapUserCertSpinner.getSelectedItem();
                    if (str2.equals(this.unspecifiedCert)) {
                        str2 = "";
                    }
                    wifiConfiguration.enterpriseConfig.setClientCertificateAlias(str2);
                    wifiConfiguration.enterpriseConfig.setIdentity(this.mEapIdentityView.getText().toString());
                    wifiConfiguration.enterpriseConfig.setAnonymousIdentity(this.mEapAnonymousView.getText().toString());
                    if (!this.mPasswordView.isShown() || this.mPasswordView.length() > 0) {
                        wifiConfiguration.enterpriseConfig.setPassword(this.mPasswordView.getText().toString());
                    }
                    break;
                default:
                    return null;
            }
        } else {
            wifiConfiguration.allowedProtocols.set(1);
            wifiConfiguration.allowedKeyManagement.set(8);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
            wifiConfiguration.allowedPairwiseCiphers.set(3);
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedGroupCiphers.set(5);
            ReflectionUtils.setField(wifiConfiguration, "requirePmf", true);
            if (this.mPasswordView.length() != 0) {
                wifiConfiguration.preSharedKey = '\"' + this.mPasswordView.getText().toString() + '\"';
            }
        }
        wifiConfiguration.setIpConfiguration(new IpConfiguration(this.mIpAssignment, this.mProxySettings, this.mStaticIpConfiguration, this.mHttpProxy));
        return wifiConfiguration;
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        boolean z;
        this.mView = getLayoutInflater().inflate(com.ashd.settings.R.layout.wifi_dialog, (ViewGroup) null);
        setView(this.mView);
        setInverseBackgroundForced(true);
        Context context = getContext();
        Resources resources = context.getResources();
        if (this.mAccessPoint == null) {
            setTitle(com.ashd.settings.R.string.wifi_add_network);
            this.mSsid = (TextView) this.mView.findViewById(com.ashd.settings.R.id.ssid);
            this.mSsid.addTextChangedListener(this);
            this.mSecuritySpinner = (Spinner) this.mView.findViewById(com.ashd.settings.R.id.security);
            this.mSecuritySpinner.setOnItemSelectedListener(this);
            this.mView.findViewById(com.ashd.settings.R.id.type).setVisibility(0);
            this.mSsid.requestFocus();
            setButton(-1, context.getString(com.ashd.settings.R.string.wifi_save), this.mListener);
        } else {
            setTitle(this.mAccessPoint.ssid);
            Log.e(TAG, "mAccessPoint.networkId0=" + this.mAccessPoint.networkId);
            this.mIpSettingsSpinner = (Spinner) this.mView.findViewById(com.ashd.settings.R.id.ip_settings);
            this.mIpSettingsSpinner.setOnItemSelectedListener(this);
            this.mProxySettingsSpinner = (Spinner) this.mView.findViewById(com.ashd.settings.R.id.proxy_settings);
            this.mProxySettingsSpinner.setOnItemSelectedListener(this);
            ViewGroup viewGroup = (ViewGroup) this.mView.findViewById(com.ashd.settings.R.id.info);
            NetworkInfo.DetailedState state = this.mAccessPoint.getState();
            if (state != null) {
                addRow(viewGroup, com.ashd.settings.R.string.wifi_status, Summary.get(getContext(), state));
            }
            int level = this.mAccessPoint.getLevel();
            if (level != -1) {
                addRow(viewGroup, com.ashd.settings.R.string.wifi_signal, resources.getStringArray(com.ashd.settings.R.array.wifi_signal)[level]);
            }
            WifiInfo info = this.mAccessPoint.getInfo();
            if (info != null && info.getLinkSpeed() != -1) {
                addRow(viewGroup, com.ashd.settings.R.string.wifi_speed, info.getLinkSpeed() + "Mbps");
            }
            addRow(viewGroup, com.ashd.settings.R.string.wifi_security, this.mAccessPoint.getSecurityString(false));
            if (this.mAccessPoint.networkId != -1) {
                WifiConfiguration config = this.mAccessPoint.getConfig();
                if (config.getIpAssignment() == IpConfiguration.IpAssignment.STATIC) {
                    this.mIpSettingsSpinner.setSelection(1);
                    z = true;
                } else {
                    this.mIpSettingsSpinner.setSelection(0);
                    z = false;
                }
                StaticIpConfiguration staticIpConfiguration = config.getStaticIpConfiguration();
                if (staticIpConfiguration != null && staticIpConfiguration.ipAddress != null) {
                    addRow(viewGroup, com.ashd.settings.R.string.wifi_ip_address, staticIpConfiguration.ipAddress.getAddress().getHostAddress());
                }
                if (config.getProxySettings() == IpConfiguration.ProxySettings.STATIC) {
                    this.mProxySettingsSpinner.setSelection(1);
                } else {
                    if (config.getProxySettings() == IpConfiguration.ProxySettings.PAC) {
                        this.mProxySettingsSpinner.setVisibility(8);
                        TextView textView = (TextView) this.mView.findViewById(com.ashd.settings.R.id.proxy_pac_info);
                        textView.setVisibility(0);
                        textView.setText(context.getString(com.ashd.settings.R.string.proxy_url) + config.getHttpProxy().getPacFileUrl());
                    } else {
                        this.mProxySettingsSpinner.setSelection(0);
                    }
                    WifiConfiguration.NetworkSelectionStatus networkSelectionStatus = config.getNetworkSelectionStatus();
                    if (config.status == 1 && networkSelectionStatus.getNetworkSelectionDisableReason() == 5) {
                        addRow(viewGroup, com.ashd.settings.R.string.wifi_disabled_heading, context.getString(com.ashd.settings.R.string.wifi_disabled_help));
                    }
                }
                z = true;
                WifiConfiguration.NetworkSelectionStatus networkSelectionStatus2 = config.getNetworkSelectionStatus();
                if (config.status == 1) {
                    addRow(viewGroup, com.ashd.settings.R.string.wifi_disabled_heading, context.getString(com.ashd.settings.R.string.wifi_disabled_help));
                }
            } else {
                z = false;
            }
            Log.e(TAG, "mAccessPoint.networkId0=" + this.mAccessPoint.networkId);
            if (this.mAccessPoint.networkId == -1 && this.mAccessPoint.mWPS_enabled) {
                showNetworkSetupFields();
            }
            if (this.mAccessPoint.networkId == -1 || this.mEdit) {
                showSecurityFields();
                showIpConfigFields();
                showProxyFields();
                this.mView.findViewById(com.ashd.settings.R.id.wifi_advanced_toggle).setVisibility(0);
                this.mView.findViewById(com.ashd.settings.R.id.wifi_advanced_togglebox).setOnClickListener(this);
                if (z) {
                    ((CheckBox) this.mView.findViewById(com.ashd.settings.R.id.wifi_advanced_togglebox)).setChecked(true);
                    this.mView.findViewById(com.ashd.settings.R.id.wifi_advanced_fields).setVisibility(0);
                }
            }
            if (this.mPasswordView == null) {
                addRow(viewGroup, com.ashd.settings.R.string.wifi_ip_address, getIpAddressString());
            }
            if (this.mEdit) {
                setButton(-1, context.getString(com.ashd.settings.R.string.wifi_save), this.mListener);
            } else {
                if (state == null && level != -1) {
                    setButton(-1, context.getString(com.ashd.settings.R.string.wifi_connect), this.mListener);
                } else {
                    this.mView.findViewById(com.ashd.settings.R.id.ip_fields).setVisibility(8);
                }
                if (this.mAccessPoint.networkId != -1) {
                    setButton(-3, context.getString(com.ashd.settings.R.string.wifi_forget), this.mListener);
                }
            }
        }
        setButton(-2, context.getString(com.ashd.settings.R.string.wifi_cancel), this.mListener);
        if (getButton(-1) != null) {
            enableSubmitIfAppropriate();
        }
        super.onCreate(bundle);
        Utils.fixButtonStyle(this);
        setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.rk_itvui.settings.network.wifi.Wifi_Dialog.1
            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
                Wifi_Dialog.this.showSoftInput_2();
            }
        });
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
    }

    private void addRow(ViewGroup viewGroup, int i, String str) {
        View viewInflate = getLayoutInflater().inflate(com.ashd.settings.R.layout.wifi_dialog_row, viewGroup, false);
        ((TextView) viewInflate.findViewById(com.ashd.settings.R.id.name)).setText(i);
        ((TextView) viewInflate.findViewById(com.ashd.settings.R.id.value)).setText(str);
        viewGroup.addView(viewInflate);
    }

    private void validate() {
        if ((this.mSsid != null && this.mSsid.length() == 0) || ((this.mAccessPoint == null || this.mAccessPoint.networkId == -1) && ((this.mSecurity == 1 && this.mPasswordView.length() == 0) || (this.mSecurity == 2 && this.mPasswordView.length() < 8)))) {
            getButton(-1).setEnabled(false);
        } else {
            getButton(-1).setEnabled(true);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Log.i("wifi_dialog", "view id is " + view.getId());
        if (view.getId() == com.ashd.settings.R.id.show_password) {
            Log.i(TAG, "show_password");
            this.mPasswordView.setInputType((((CheckBox) view).isChecked() ? TvSignalID.SIGNALID_SCART_MAC640480 : 128) | 1);
        } else if (view.getId() == com.ashd.settings.R.id.wifi_advanced_togglebox) {
            if (((CheckBox) view).isChecked()) {
                this.mView.findViewById(com.ashd.settings.R.id.wifi_advanced_fields).setVisibility(0);
            } else {
                this.mView.findViewById(com.ashd.settings.R.id.wifi_advanced_fields).setVisibility(8);
            }
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        enableSubmitIfAppropriate();
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        if (adapterView == this.mSecuritySpinner) {
            this.mSecurity = i;
            showSecurityFields();
        } else if (adapterView == this.mNetworkSetupSpinner) {
            showNetworkSetupFields();
        } else if (adapterView == this.mProxySettingsSpinner) {
            showProxyFields();
        } else {
            showIpConfigFields();
        }
        enableSubmitIfAppropriate();
    }

    private void showSoftInput_1() {
        this.mSsid.requestFocus();
        this.inputMethodManager.showSoftInput(this.mSsid, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSoftInput_2() {
        if (this.mPasswordView != null) {
            this.mPasswordView.requestFocus();
            this.mPasswordView.postDelayed(new Runnable() { // from class: com.rk_itvui.settings.network.wifi.Wifi_Dialog.2
                @Override // java.lang.Runnable
                public void run() {
                    Wifi_Dialog.this.inputMethodManager.showSoftInput(Wifi_Dialog.this.mPasswordView, 0);
                }
            }, 500L);
        } else if (this.mSsid != null) {
            this.mSsid.requestFocus();
            this.mSsid.postDelayed(new Runnable() { // from class: com.rk_itvui.settings.network.wifi.Wifi_Dialog.3
                @Override // java.lang.Runnable
                public void run() {
                    Wifi_Dialog.this.inputMethodManager.showSoftInput(Wifi_Dialog.this.mSsid, 0);
                }
            }, 500L);
        }
    }

    private void showSecurityFields() {
        if (this.mSecurity == 0) {
            this.mView.findViewById(com.ashd.settings.R.id.security_fields).setVisibility(8);
            return;
        }
        this.mView.findViewById(com.ashd.settings.R.id.security_fields).setVisibility(0);
        if (this.mPasswordView == null) {
            this.mPasswordView = (TextView) this.mView.findViewById(com.ashd.settings.R.id.password);
            this.mPasswordView.addTextChangedListener(this);
            this.mPasswordView.setOnKeyListener(new View.OnKeyListener() { // from class: com.rk_itvui.settings.network.wifi.Wifi_Dialog.4
                @Override // android.view.View.OnKeyListener
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    Log.i(Wifi_Dialog.TAG, "弹出输入框::" + i);
                    if (keyEvent.getAction() == 1) {
                        return false;
                    }
                    if (i != 23 && i != 66) {
                        return false;
                    }
                    Log.i(Wifi_Dialog.TAG, "弹出输入框");
                    if (Wifi_Dialog.this.inputMethodManager == null) {
                        return false;
                    }
                    Wifi_Dialog.this.inputMethodManager.toggleSoftInput(0, 2);
                    return true;
                }
            });
            CheckBox checkBox = (CheckBox) this.mView.findViewById(com.ashd.settings.R.id.show_password);
            checkBox.setOnClickListener(this);
            checkBox.setChecked(true);
            this.mPasswordView.setInputType((checkBox.isChecked() ? TvSignalID.SIGNALID_SCART_MAC640480 : 128) | 1);
            showSoftInput_2();
            if (this.mAccessPoint != null && this.mAccessPoint.networkId != -1) {
                this.mPasswordView.setHint(com.ashd.settings.R.string.wifi_unchanged);
            }
        }
        if (this.mSecurity != 3) {
            this.mView.findViewById(com.ashd.settings.R.id.eap).setVisibility(8);
            return;
        }
        this.mView.findViewById(com.ashd.settings.R.id.eap).setVisibility(0);
        if (this.mEapMethod == null) {
            this.mEapMethod = (Spinner) this.mView.findViewById(com.ashd.settings.R.id.method);
            this.mPhase2 = (Spinner) this.mView.findViewById(com.ashd.settings.R.id.phase2);
            this.mEapCaCert = (Spinner) this.mView.findViewById(com.ashd.settings.R.id.ca_cert);
            this.mEapUserCert = (Spinner) this.mView.findViewById(com.ashd.settings.R.id.user_cert);
            this.mEapIdentity = (TextView) this.mView.findViewById(com.ashd.settings.R.id.identity);
            this.mEapAnonymous = (TextView) this.mView.findViewById(com.ashd.settings.R.id.anonymous);
            loadCertificates(this.mEapCaCert, "CACERT_");
            loadCertificates(this.mEapUserCert, "USRPKEY_");
            if (this.mAccessPoint != null && this.mAccessPoint.networkId != -1) {
                WifiEnterpriseConfig wifiEnterpriseConfig = this.mAccessPoint.getConfig().enterpriseConfig;
                int eapMethod = wifiEnterpriseConfig.getEapMethod();
                int phase2Method = wifiEnterpriseConfig.getPhase2Method();
                this.mEapMethodSpinner.setSelection(eapMethod);
                showEapFieldsByMethod(eapMethod);
                if (eapMethod != 0) {
                    this.mPhase2Spinner.setSelection(phase2Method);
                } else if (phase2Method == 0) {
                    this.mPhase2Spinner.setSelection(0);
                } else {
                    switch (phase2Method) {
                        case 3:
                            this.mPhase2Spinner.setSelection(1);
                            break;
                        case 4:
                            this.mPhase2Spinner.setSelection(2);
                            break;
                        default:
                            Log.e(TAG, "Invalid phase 2 method " + phase2Method);
                            break;
                    }
                }
                setSelection(this.mEapCaCertSpinner, wifiEnterpriseConfig.getCaCertificateAlias());
                setSelection(this.mEapUserCertSpinner, wifiEnterpriseConfig.getClientCertificateAlias());
                this.mEapIdentityView.setText(wifiEnterpriseConfig.getIdentity());
                this.mEapAnonymousView.setText(wifiEnterpriseConfig.getAnonymousIdentity());
                return;
            }
            this.mEapMethodSpinner = (Spinner) findViewById(com.ashd.settings.R.id.method);
            this.mEapMethodSpinner.setSelection(0);
            showEapFieldsByMethod(0);
            return;
        }
        showEapFieldsByMethod(this.mEapMethodSpinner.getSelectedItemPosition());
    }

    private void showEapFieldsByMethod(int i) {
        this.mView.findViewById(com.ashd.settings.R.id.l_method).setVisibility(0);
        this.mView.findViewById(com.ashd.settings.R.id.l_identity).setVisibility(0);
        this.mView.findViewById(com.ashd.settings.R.id.l_ca_cert).setVisibility(0);
        this.mView.findViewById(com.ashd.settings.R.id.password_layout).setVisibility(0);
        this.mView.findViewById(com.ashd.settings.R.id.show_password_layout).setVisibility(0);
        switch (i) {
            case 0:
                if (this.mPhase2Adapter != this.PHASE2_PEAP_ADAPTER) {
                    this.mPhase2Adapter = this.PHASE2_PEAP_ADAPTER;
                    this.mPhase2Spinner.setAdapter((SpinnerAdapter) this.mPhase2Adapter);
                }
                this.mView.findViewById(com.ashd.settings.R.id.l_phase2).setVisibility(0);
                this.mView.findViewById(com.ashd.settings.R.id.l_anonymous).setVisibility(0);
                setUserCertInvisible();
                break;
            case 1:
                this.mView.findViewById(com.ashd.settings.R.id.l_user_cert).setVisibility(0);
                setPhase2Invisible();
                setAnonymousIdentInvisible();
                setPasswordInvisible();
                break;
            case 2:
                if (this.mPhase2Adapter != this.PHASE2_FULL_ADAPTER) {
                    this.mPhase2Adapter = this.PHASE2_FULL_ADAPTER;
                    this.mPhase2Spinner.setAdapter((SpinnerAdapter) this.mPhase2Adapter);
                }
                this.mView.findViewById(com.ashd.settings.R.id.l_phase2).setVisibility(0);
                this.mView.findViewById(com.ashd.settings.R.id.l_anonymous).setVisibility(0);
                setUserCertInvisible();
                break;
            case 3:
                setPhase2Invisible();
                setCaCertInvisible();
                setAnonymousIdentInvisible();
                setUserCertInvisible();
                break;
        }
    }

    private void setPhase2Invisible() {
        this.mView.findViewById(com.ashd.settings.R.id.l_phase2).setVisibility(8);
        this.mPhase2Spinner.setSelection(0);
    }

    private void setCaCertInvisible() {
        this.mView.findViewById(com.ashd.settings.R.id.l_ca_cert).setVisibility(8);
        this.mEapCaCertSpinner.setSelection(0);
    }

    private void setUserCertInvisible() {
        this.mView.findViewById(com.ashd.settings.R.id.l_user_cert).setVisibility(8);
        this.mEapUserCertSpinner.setSelection(0);
    }

    private void setAnonymousIdentInvisible() {
        this.mView.findViewById(com.ashd.settings.R.id.l_anonymous).setVisibility(8);
        this.mEapAnonymousView.setText("");
    }

    private void setPasswordInvisible() {
        this.mPasswordView.setText("");
        this.mView.findViewById(com.ashd.settings.R.id.password_layout).setVisibility(8);
        this.mView.findViewById(com.ashd.settings.R.id.show_password_layout).setVisibility(8);
    }

    private void loadCertificates(Spinner spinner, String str) {
        String[] strArr;
        String[] list = KeyStore.getInstance().list(str, PointerIconCompat.TYPE_ALIAS);
        Context context = getContext();
        context.getString(com.ashd.settings.R.string.wifi_unspecified);
        if (list == null || list.length == 0) {
            strArr = new String[]{this.unspecifiedCert};
        } else {
            strArr = new String[list.length + 1];
            strArr[0] = this.unspecifiedCert;
            System.arraycopy(list, 0, strArr, 1, list.length);
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.simple_spinner_item, strArr);
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
    }

    private void setCertificate(Spinner spinner, String str, String str2) {
        String str3 = KEYSTORE_SPACE + str;
        if (str2 == null || !str2.startsWith(str3)) {
            return;
        }
        setSelection(spinner, str2.substring(str3.length()));
    }

    private void setSelection(Spinner spinner, String str) {
        if (str != null) {
            ArrayAdapter arrayAdapter = (ArrayAdapter) spinner.getAdapter();
            for (int count = arrayAdapter.getCount() - 1; count >= 0; count--) {
                if (str.equals(arrayAdapter.getItem(count))) {
                    spinner.setSelection(count);
                    return;
                }
            }
        }
    }

    private void showIpConfigFields() {
        StaticIpConfiguration staticIpConfiguration;
        this.mView.findViewById(com.ashd.settings.R.id.ip_fields).setVisibility(0);
        WifiConfiguration config = (this.mAccessPoint == null || this.mAccessPoint.networkId == -1) ? null : this.mAccessPoint.getConfig();
        if (this.mIpSettingsSpinner.getSelectedItemPosition() == 1) {
            this.mView.findViewById(com.ashd.settings.R.id.staticip).setVisibility(0);
            if (this.mIpAddressView == null) {
                this.mIpAddressView = (TextView) this.mView.findViewById(com.ashd.settings.R.id.ipaddress);
                this.mIpAddressView.addTextChangedListener(this);
                this.mGatewayView = (TextView) this.mView.findViewById(com.ashd.settings.R.id.gateway);
                this.mGatewayView.addTextChangedListener(this);
                this.mNetworkPrefixLengthView = (TextView) this.mView.findViewById(com.ashd.settings.R.id.network_prefix_length);
                this.mNetworkPrefixLengthView.addTextChangedListener(this);
                this.mDns1View = (TextView) this.mView.findViewById(com.ashd.settings.R.id.dns1);
                this.mDns1View.addTextChangedListener(this);
                this.mDns2View = (TextView) this.mView.findViewById(com.ashd.settings.R.id.dns2);
                this.mDns2View.addTextChangedListener(this);
            }
            if (config == null || (staticIpConfiguration = config.getStaticIpConfiguration()) == null) {
                return;
            }
            if (staticIpConfiguration.ipAddress != null) {
                this.mIpAddressView.setText(staticIpConfiguration.ipAddress.getAddress().getHostAddress());
                this.mNetworkPrefixLengthView.setText(Integer.toString(staticIpConfiguration.ipAddress.getNetworkPrefixLength()));
            }
            if (staticIpConfiguration.gateway != null) {
                this.mGatewayView.setText(staticIpConfiguration.gateway.getHostAddress());
            }
            Iterator it = staticIpConfiguration.dnsServers.iterator();
            if (it.hasNext()) {
                this.mDns1View.setText(((InetAddress) it.next()).getHostAddress());
            }
            if (it.hasNext()) {
                this.mDns2View.setText(((InetAddress) it.next()).getHostAddress());
                return;
            }
            return;
        }
        this.mView.findViewById(com.ashd.settings.R.id.staticip).setVisibility(8);
    }

    private void showProxyFields() {
        ProxyInfo httpProxy;
        this.mView.findViewById(com.ashd.settings.R.id.proxy_settings_fields).setVisibility(0);
        WifiConfiguration config = (this.mAccessPoint == null || this.mAccessPoint.networkId == -1) ? null : this.mAccessPoint.getConfig();
        if (this.mProxySettingsSpinner.getSelectedItemPosition() == 1) {
            this.mView.findViewById(com.ashd.settings.R.id.proxy_warning_limited_support).setVisibility(0);
            this.mView.findViewById(com.ashd.settings.R.id.proxy_fields).setVisibility(0);
            if (this.mProxyHostView == null) {
                this.mProxyHostView = (TextView) this.mView.findViewById(com.ashd.settings.R.id.proxy_hostname);
                this.mProxyHostView.addTextChangedListener(this);
                this.mProxyPortView = (TextView) this.mView.findViewById(com.ashd.settings.R.id.proxy_port);
                this.mProxyPortView.addTextChangedListener(this);
                this.mProxyExclusionListView = (TextView) this.mView.findViewById(com.ashd.settings.R.id.proxy_exclusionlist);
                this.mProxyExclusionListView.addTextChangedListener(this);
            }
            if (config == null || (httpProxy = config.getHttpProxy()) == null) {
                return;
            }
            this.mProxyHostView.setText(httpProxy.getHost());
            this.mProxyPortView.setText(Integer.toString(httpProxy.getPort()));
            this.mProxyExclusionListView.setText(httpProxy.getExclusionListAsString());
            return;
        }
        this.mView.findViewById(com.ashd.settings.R.id.proxy_warning_limited_support).setVisibility(8);
        this.mView.findViewById(com.ashd.settings.R.id.proxy_fields).setVisibility(8);
    }

    private boolean enableSubmitIfAppropriate() {
        Button button = getButton(-1);
        boolean zIpAndProxyFieldsAreValid = false;
        if (button == null) {
            return false;
        }
        boolean z = true;
        if (chosenNetworkSetupMethod() != 0 || ((this.mSecurity != 1 || this.mPasswordView.length() != 0) && (this.mSecurity != 2 || this.mPasswordView.length() >= 8))) {
            z = false;
        }
        if ((this.mSsid == null || this.mSsid.length() != 0) && ((this.mAccessPoint != null && this.mAccessPoint.networkId != -1) || !z)) {
            zIpAndProxyFieldsAreValid = ipAndProxyFieldsAreValid();
        }
        button.setEnabled(zIpAndProxyFieldsAreValid);
        return zIpAndProxyFieldsAreValid;
    }

    private boolean ipAndProxyFieldsAreValid() {
        int i;
        int iValidate;
        this.mLinkProperties.clear();
        this.mIpAssignment = (this.mIpSettingsSpinner == null || this.mIpSettingsSpinner.getSelectedItemPosition() != 1) ? IpConfiguration.IpAssignment.DHCP : IpConfiguration.IpAssignment.STATIC;
        if (this.mIpAssignment == IpConfiguration.IpAssignment.STATIC) {
            this.mStaticIpConfiguration = new StaticIpConfiguration();
            if (validateIpConfigFields_new(this.mStaticIpConfiguration) != 0) {
                return false;
            }
        }
        this.mProxySettings = (this.mProxySettingsSpinner == null || this.mProxySettingsSpinner.getSelectedItemPosition() != 1) ? IpConfiguration.ProxySettings.NONE : IpConfiguration.ProxySettings.STATIC;
        if (this.mProxySettings == IpConfiguration.ProxySettings.STATIC) {
            String string = this.mProxyHostView.getText().toString();
            String string2 = this.mProxyPortView.getText().toString();
            String string3 = this.mProxyExclusionListView.getText().toString();
            try {
                i = Integer.parseInt(string2);
                try {
                    iValidate = ProxySelector.validate(string, string2, string3);
                } catch (NumberFormatException unused) {
                    iValidate = com.ashd.settings.R.string.proxy_error_invalid_port;
                }
            } catch (NumberFormatException unused2) {
                i = 0;
            }
            if (iValidate != 0) {
                return false;
            }
            this.mLinkProperties.setHttpProxy(new ProxyInfo(string, i, string3));
        }
        return true;
    }

    private void showNetworkSetupFields() {
        this.mView.findViewById(com.ashd.settings.R.id.setup_fields).setVisibility(0);
        if (this.mNetworkSetupSpinner == null) {
            this.mNetworkSetupSpinner = (Spinner) this.mView.findViewById(com.ashd.settings.R.id.network_setup);
            this.mNetworkSetupSpinner.setOnItemSelectedListener(this);
        }
        int selectedItemPosition = this.mNetworkSetupSpinner.getSelectedItemPosition();
        if (selectedItemPosition == 2) {
            this.mView.findViewById(com.ashd.settings.R.id.wps_fields).setVisibility(0);
        } else {
            this.mView.findViewById(com.ashd.settings.R.id.wps_fields).setVisibility(8);
        }
        if (selectedItemPosition == 3 || selectedItemPosition == 2 || selectedItemPosition == 1) {
            this.mView.findViewById(com.ashd.settings.R.id.security_fields).setVisibility(8);
        } else {
            this.mView.findViewById(com.ashd.settings.R.id.security_fields).setVisibility(0);
        }
    }

    int chosenNetworkSetupMethod() {
        if (this.mNetworkSetupSpinner != null) {
            return this.mNetworkSetupSpinner.getSelectedItemPosition();
        }
        return 0;
    }

    private Inet4Address getIPv4Address(String str) {
        try {
            return (Inet4Address) NetworkUtils.numericToInetAddress(str);
        } catch (ClassCastException | IllegalArgumentException unused) {
            return null;
        }
    }

    private int validateIpConfigFields_new(StaticIpConfiguration staticIpConfiguration) {
        Inet4Address iPv4Address;
        if (this.mIpAddressView == null) {
            return 0;
        }
        String string = this.mIpAddressView.getText().toString();
        if (TextUtils.isEmpty(string) || (iPv4Address = getIPv4Address(string)) == null) {
            return com.ashd.settings.R.string.wifi_ip_settings_invalid_ip_address;
        }
        int i = -1;
        try {
            int i2 = Integer.parseInt(this.mNetworkPrefixLengthView.getText().toString());
            if (i2 < 0 || i2 > 32) {
                return com.ashd.settings.R.string.wifi_ip_settings_invalid_network_prefix_length;
            }
            try {
                staticIpConfiguration.ipAddress = new LinkAddress(iPv4Address, i2);
                i = i2;
            } catch (NumberFormatException e) {
                e = e;
                i = i2;
                Log.d("blb", "validateIpConfigFields_new" + e);
            }
            String string2 = this.mGatewayView.getText().toString();
            if (TextUtils.isEmpty(string2)) {
                try {
                    byte[] address = NetworkUtils.getNetworkPart(iPv4Address, i).getAddress();
                    address[address.length - 1] = 1;
                    this.mGatewayView.setText(InetAddress.getByAddress(address).getHostAddress());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else {
                Inet4Address iPv4Address2 = getIPv4Address(string2);
                if (iPv4Address2 == null) {
                    return com.ashd.settings.R.string.wifi_ip_settings_invalid_gateway;
                }
                staticIpConfiguration.gateway = iPv4Address2;
            }
            String string3 = this.mDns1View.getText().toString();
            if (TextUtils.isEmpty(string3)) {
                Log.d("blb", "no dns server");
            } else {
                Inet4Address iPv4Address3 = getIPv4Address(string3);
                if (iPv4Address3 == null) {
                    return com.ashd.settings.R.string.wifi_ip_settings_invalid_dns;
                }
                staticIpConfiguration.dnsServers.add(iPv4Address3);
            }
            if (this.mDns2View.length() > 0) {
                Inet4Address iPv4Address4 = getIPv4Address(this.mDns2View.getText().toString());
                if (iPv4Address4 == null) {
                    return com.ashd.settings.R.string.wifi_ip_settings_invalid_dns;
                }
                staticIpConfiguration.dnsServers.add(iPv4Address4);
            }
            return 0;
        } catch (NumberFormatException e3) {
            e = e3;
        }
    }

    private void updatePasswordVisibility(boolean z) {
        int selectionEnd = this.mPasswordView.getSelectionEnd();
        this.mPasswordView.setInputType((z ? TvSignalID.SIGNALID_SCART_MAC640480 : 128) | 1);
        if (selectionEnd >= 0) {
            ((EditText) this.mPasswordView).setSelection(selectionEnd);
        }
    }

    public static String getIpAddressString() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if ((inetAddressNextElement instanceof Inet4Address) && !inetAddressNextElement.isLoopbackAddress()) {
                        return inetAddressNextElement.getHostAddress();
                    }
                }
            }
            return "0.0.0.0";
        } catch (SocketException e) {
            e.printStackTrace();
            return "0.0.0.0";
        }
    }
}
