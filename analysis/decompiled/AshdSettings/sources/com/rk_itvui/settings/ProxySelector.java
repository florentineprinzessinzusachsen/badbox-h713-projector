package com.rk_itvui.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.admin.DevicePolicyManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Proxy;
import android.net.ProxyInfo;
import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class ProxySelector extends Fragment implements DialogCreatable {
    private static final int ERROR_DIALOG_ID = 0;
    private static final String TAG = "ProxySelector";
    Button mClearButton;
    Button mDefaultButton;
    private SettingsPreferenceFragment.SettingsDialogFragment mDialogFragment;
    EditText mExclusionListField;
    EditText mHostnameField;
    Button mOKButton;
    EditText mPortField;
    private View mView;
    View.OnClickListener mOKHandler = new View.OnClickListener() { // from class: com.rk_itvui.settings.ProxySelector.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ProxySelector.this.saveToDb()) {
                ProxySelector.this.getActivity().onBackPressed();
            }
        }
    };
    View.OnClickListener mClearHandler = new View.OnClickListener() { // from class: com.rk_itvui.settings.ProxySelector.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ProxySelector.this.mHostnameField.setText("");
            ProxySelector.this.mPortField.setText("");
            ProxySelector.this.mExclusionListField.setText("");
        }
    };
    View.OnClickListener mDefaultHandler = new View.OnClickListener() { // from class: com.rk_itvui.settings.ProxySelector.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ProxySelector.this.populateFields();
        }
    };
    View.OnFocusChangeListener mOnFocusChangeHandler = new View.OnFocusChangeListener() { // from class: com.rk_itvui.settings.ProxySelector.4
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (z) {
                Selection.selectAll((Spannable) ((TextView) view).getText());
            }
        }
    };

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mView = layoutInflater.inflate(com.ashd.settings.R.layout.proxy, viewGroup, false);
        initView(this.mView);
        populateFields();
        return this.mView;
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        boolean z = ((DevicePolicyManager) getActivity().getSystemService("device_policy")).getGlobalProxyAdmin() == null;
        this.mHostnameField.setEnabled(z);
        this.mPortField.setEnabled(z);
        this.mExclusionListField.setEnabled(z);
        this.mOKButton.setEnabled(z);
        this.mClearButton.setEnabled(z);
        this.mDefaultButton.setEnabled(z);
    }

    @Override // com.rk_itvui.settings.DialogCreatable
    public Dialog onCreateDialog(int i) {
        if (i != 0) {
            return null;
        }
        return new AlertDialog.Builder(getActivity()).setTitle(com.ashd.settings.R.string.proxy_error).setPositiveButton(com.ashd.settings.R.string.proxy_error_dismiss, (DialogInterface.OnClickListener) null).setMessage(getActivity().getString(validate(this.mHostnameField.getText().toString().trim(), this.mPortField.getText().toString().trim(), this.mExclusionListField.getText().toString().trim()))).create();
    }

    private void showDialog(int i) {
        if (this.mDialogFragment != null) {
            Log.e(TAG, "Old dialog fragment not null!");
        }
        this.mDialogFragment = new SettingsPreferenceFragment.SettingsDialogFragment(this, i);
        this.mDialogFragment.show(getActivity().getFragmentManager(), Integer.toString(i));
    }

    private void initView(View view) {
        this.mHostnameField = (EditText) view.findViewById(com.ashd.settings.R.id.hostname);
        this.mHostnameField.setOnFocusChangeListener(this.mOnFocusChangeHandler);
        this.mPortField = (EditText) view.findViewById(com.ashd.settings.R.id.port);
        this.mPortField.setOnClickListener(this.mOKHandler);
        this.mPortField.setOnFocusChangeListener(this.mOnFocusChangeHandler);
        this.mExclusionListField = (EditText) view.findViewById(com.ashd.settings.R.id.exclusionlist);
        this.mExclusionListField.setOnFocusChangeListener(this.mOnFocusChangeHandler);
        this.mOKButton = (Button) view.findViewById(com.ashd.settings.R.id.action);
        this.mOKButton.setOnClickListener(this.mOKHandler);
        this.mClearButton = (Button) view.findViewById(com.ashd.settings.R.id.clear);
        this.mClearButton.setOnClickListener(this.mClearHandler);
        this.mDefaultButton = (Button) view.findViewById(com.ashd.settings.R.id.defaultView);
        this.mDefaultButton.setOnClickListener(this.mDefaultHandler);
    }

    void populateFields() {
        String exclusionListAsString;
        int port;
        Activity activity = getActivity();
        String host = "";
        ProxyInfo globalProxy = ((ConnectivityManager) getActivity().getSystemService("connectivity")).getGlobalProxy();
        if (globalProxy != null) {
            host = globalProxy.getHost();
            port = globalProxy.getPort();
            exclusionListAsString = globalProxy.getExclusionListAsString();
        } else {
            exclusionListAsString = "";
            port = -1;
        }
        if (host == null) {
            host = "";
        }
        this.mHostnameField.setText(host);
        this.mPortField.setText(port == -1 ? "" : Integer.toString(port));
        this.mExclusionListField.setText(exclusionListAsString);
        Intent intent = activity.getIntent();
        String stringExtra = intent.getStringExtra("button-label");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.mOKButton.setText(stringExtra);
        }
        String stringExtra2 = intent.getStringExtra("title");
        if (TextUtils.isEmpty(stringExtra2)) {
            return;
        }
        activity.setTitle(stringExtra2);
    }

    public static int validate(String str, String str2, String str3) {
        switch (Proxy.validate(str, str2, str3)) {
            case 0:
                return 0;
            case 1:
                return com.ashd.settings.R.string.proxy_error_empty_host_set_port;
            case 2:
                return com.ashd.settings.R.string.proxy_error_invalid_host;
            case 3:
                return com.ashd.settings.R.string.proxy_error_empty_port;
            case 4:
                return com.ashd.settings.R.string.proxy_error_invalid_port;
            case 5:
                return com.ashd.settings.R.string.proxy_error_invalid_exclusion_list;
            default:
                Log.e(TAG, "Unknown proxy settings error");
                return -1;
        }
    }

    boolean saveToDb() {
        int i;
        String strTrim = this.mHostnameField.getText().toString().trim();
        String strTrim2 = this.mPortField.getText().toString().trim();
        String strTrim3 = this.mExclusionListField.getText().toString().trim();
        if (validate(strTrim, strTrim2, strTrim3) != 0) {
            showDialog(0);
            return false;
        }
        if (strTrim2.length() > 0) {
            try {
                i = Integer.parseInt(strTrim2);
            } catch (NumberFormatException unused) {
                return false;
            }
        } else {
            i = 0;
        }
        ((ConnectivityManager) getActivity().getSystemService("connectivity")).setGlobalProxy(new ProxyInfo(strTrim, i, strTrim3));
        return true;
    }
}
