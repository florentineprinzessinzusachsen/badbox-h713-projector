package com.rk_itvui.settings.deviceinfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.util.Log;
import com.ashd.settings.R;

/* JADX INFO: loaded from: classes.dex */
public class UsbSettings extends PreferenceActivity {
    private static final boolean DEBUG = true;
    private static final String KEY_MASS = "usb_mass";
    private static final String KEY_MTP = "usb_mtp";
    private static final String KEY_PTP = "usb_ptp";
    private static final String TAG = "UsbSettings";
    private CheckBoxPreference mMass;
    private CheckBoxPreference mMtp;
    private CheckBoxPreference mPtp;
    private final BroadcastReceiver mStateReceiver = new BroadcastReceiver() { // from class: com.rk_itvui.settings.deviceinfo.UsbSettings.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            UsbSettings.this.LOG("usb stateReceiver");
            UsbSettings.this.updateToggles("mtp");
        }
    };
    private UsbManager mUsbManager;

    /* JADX INFO: Access modifiers changed from: private */
    public void LOG(String str) {
        Log.d(TAG, str);
    }

    private PreferenceScreen createPreferenceHierarchy() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen != null) {
            preferenceScreen.removeAll();
        }
        addPreferencesFromResource(R.xml.usb_settings);
        PreferenceScreen preferenceScreen2 = getPreferenceScreen();
        this.mMtp = (CheckBoxPreference) preferenceScreen2.findPreference(KEY_MTP);
        this.mPtp = (CheckBoxPreference) preferenceScreen2.findPreference(KEY_PTP);
        this.mMass = (CheckBoxPreference) preferenceScreen2.findPreference(KEY_MASS);
        return preferenceScreen2;
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mUsbManager = (UsbManager) getSystemService("usb");
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        try {
            unregisterReceiver(this.mStateReceiver);
        } catch (IllegalArgumentException unused) {
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        createPreferenceHierarchy();
        registerReceiver(this.mStateReceiver, new IntentFilter("android.hardware.usb.action.USB_STATE"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateToggles(String str) {
        if ("mtp".equals(str)) {
            this.mMtp.setChecked(true);
            this.mPtp.setChecked(false);
            this.mMass.setChecked(false);
        } else if ("ptp".equals(str)) {
            this.mMtp.setChecked(false);
            this.mPtp.setChecked(true);
            this.mMass.setChecked(false);
        } else {
            this.mMtp.setChecked(false);
            this.mPtp.setChecked(false);
            this.mMass.setChecked(false);
        }
    }

    @Override // android.preference.PreferenceActivity
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        LOG("onPreferenceClick");
        if (preference instanceof CheckBoxPreference) {
            LOG("instanceof.....");
            CheckBoxPreference checkBoxPreference = (CheckBoxPreference) preference;
            if (!checkBoxPreference.isChecked()) {
                LOG("checkbox checked:" + checkBoxPreference.isChecked());
                checkBoxPreference.setChecked(true);
                return true;
            }
        }
        LOG("update state....");
        Intent intent = new Intent();
        if (preference == this.mMtp) {
            LOG("select mtp");
            this.mUsbManager.setCurrentFunction("mtp", false);
            updateToggles("mtp");
            intent.putExtra(UsbMode.USB_MODE, KEY_MTP);
        } else if (preference == this.mPtp) {
            LOG("select ptp");
            this.mUsbManager.setCurrentFunction("ptp", false);
            updateToggles("ptp");
            intent.putExtra(UsbMode.USB_MODE, KEY_PTP);
        }
        LOG("select mass");
        setResult(-1, intent);
        finish();
        return true;
    }
}
