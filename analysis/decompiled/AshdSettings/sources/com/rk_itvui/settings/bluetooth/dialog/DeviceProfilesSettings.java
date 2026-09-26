package com.rk_itvui.settings.bluetooth.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemProperties;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.CachedBluetoothDeviceManager;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.bluetooth.LocalBluetoothProfile;
import com.android.settingslib.bluetooth.LocalBluetoothProfileManager;
import com.android.settingslib.bluetooth.MapProfile;
import com.android.settingslib.bluetooth.PanProfile;
import com.android.settingslib.bluetooth.PbapServerProfile;
import com.ashd.settings.R;
import com.rk_itvui.settings.Utils;
import com.rk_itvui.settings.bluetooth.BluetoothUtils;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceProfilesSettings extends DialogFragment implements CachedBluetoothDevice.Callback, DialogInterface.OnClickListener, View.OnClickListener {
    public static final String ARG_DEVICE_ADDRESS = "device_address";
    private static final String KEY_PBAP_SERVER = "PBAP Server";
    private static final String KEY_PROFILE_CONTAINER = "profile_container";
    private static final String KEY_UNPAIR = "unpair";
    private static final String TAG = "DeviceProfilesSettings";
    private final HashMap<LocalBluetoothProfile, CheckBoxPreference> mAutoConnectPrefs = new HashMap<>();
    private CachedBluetoothDevice mCachedDevice;
    private EditTextPreference mDeviceNamePref;
    private AlertDialog mDisconnectDialog;
    private LocalBluetoothManager mManager;
    private ViewGroup mProfileContainer;
    private boolean mProfileGroupIsRemoved;
    private TextView mProfileLabel;
    private LocalBluetoothProfileManager mProfileManager;
    private View mRootView;

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mManager = BluetoothUtils.getLocalBtManager(getActivity());
        CachedBluetoothDeviceManager cachedDeviceManager = this.mManager.getCachedDeviceManager();
        BluetoothDevice remoteDevice = this.mManager.getBluetoothAdapter().getRemoteDevice(getArguments().getString(ARG_DEVICE_ADDRESS));
        this.mCachedDevice = cachedDeviceManager.findDevice(remoteDevice);
        if (this.mCachedDevice == null) {
            this.mCachedDevice = cachedDeviceManager.addDevice(this.mManager.getBluetoothAdapter(), this.mManager.getProfileManager(), remoteDevice);
        }
        this.mProfileManager = this.mManager.getProfileManager();
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        this.mRootView = LayoutInflater.from(getContext()).inflate(R.layout.device_profiles_settings, (ViewGroup) null);
        this.mProfileContainer = (ViewGroup) this.mRootView.findViewById(R.id.profiles_section);
        this.mProfileLabel = (TextView) this.mRootView.findViewById(R.id.profiles_label);
        ((EditText) this.mRootView.findViewById(R.id.name)).setText(this.mCachedDevice.getName(), TextView.BufferType.EDITABLE);
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(getContext()).setView(this.mRootView).setNeutralButton(R.string.forget, this).setPositiveButton(R.string.okay, this).setTitle(R.string.bluetooth_preference_paired_devices).create();
        alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.rk_itvui.settings.bluetooth.dialog.DeviceProfilesSettings.1
            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
                Utils.fixButtonStyle(alertDialogCreate);
            }
        });
        return alertDialogCreate;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        if (i != -3) {
            if (i != -1) {
                return;
            }
            this.mCachedDevice.setName(((EditText) this.mRootView.findViewById(R.id.name)).getText().toString());
            return;
        }
        if (BluetoothUtils.isDeviceCanDisconnect(this.mCachedDevice.getName())) {
            SystemProperties.set("persist.sys.yyyklj", "false");
            Log.d(TAG, "persist.sys.yyyklj=false");
            Intent intent = new Intent();
            intent.setAction("com.aispeech.tvui.action.BLE_RC");
            intent.setComponent(new ComponentName("com.aispeech.tvui", "com.aispeech.tvui.recorder.receiver.ExternalCommandReceiver"));
            intent.putExtra("switch", "disconnect");
            getActivity().sendBroadcast(intent);
        }
        this.mCachedDevice.unpair();
        getActivity().sendBroadcast(new Intent("com.ashd.settings.bluetoothsetting.refreshdeviceslist"));
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.mDisconnectDialog != null) {
            this.mDisconnectDialog.dismiss();
            this.mDisconnectDialog = null;
        }
        if (this.mCachedDevice != null) {
            this.mCachedDevice.unregisterCallback(this);
        }
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        this.mManager.setForegroundActivity(getActivity());
        if (this.mCachedDevice != null) {
            this.mCachedDevice.registerCallback(this);
            if (this.mCachedDevice.getBondState() == 10) {
                dismiss();
            } else {
                refresh();
            }
        }
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.mCachedDevice != null) {
            this.mCachedDevice.unregisterCallback(this);
        }
        this.mManager.setForegroundActivity(null);
    }

    private void addPreferencesForProfiles() {
        this.mProfileContainer.removeAllViews();
        Iterator<LocalBluetoothProfile> it = this.mCachedDevice.getConnectableProfiles().iterator();
        while (it.hasNext()) {
            this.mProfileContainer.addView(createProfilePreference(it.next()));
        }
        if (this.mCachedDevice.getPhonebookPermissionChoice() != 0) {
            this.mProfileContainer.addView(createProfilePreference(this.mManager.getProfileManager().getPbapProfile()));
        }
        MapProfile mapProfile = this.mManager.getProfileManager().getMapProfile();
        if (this.mCachedDevice.getMessagePermissionChoice() != 0) {
            this.mProfileContainer.addView(createProfilePreference(mapProfile));
        }
        showOrHideProfileGroup();
    }

    private void showOrHideProfileGroup() {
        int childCount = this.mProfileContainer.getChildCount();
        if (!this.mProfileGroupIsRemoved && childCount == 0) {
            this.mProfileContainer.setVisibility(8);
            this.mProfileLabel.setVisibility(8);
            this.mProfileGroupIsRemoved = true;
        } else {
            if (!this.mProfileGroupIsRemoved || childCount == 0) {
                return;
            }
            this.mProfileContainer.setVisibility(0);
            this.mProfileLabel.setVisibility(0);
            this.mProfileGroupIsRemoved = false;
        }
    }

    private CheckBox createProfilePreference(LocalBluetoothProfile localBluetoothProfile) {
        CheckBox checkBox = new CheckBox(getActivity());
        checkBox.setTag(localBluetoothProfile.toString());
        checkBox.setText(localBluetoothProfile.getNameResource(this.mCachedDevice.getDevice()));
        checkBox.setOnClickListener(this);
        refreshProfilePreference(checkBox, localBluetoothProfile);
        return checkBox;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view instanceof CheckBox) {
            onProfileClicked(getProfileOf(view), (CheckBox) view);
        }
    }

    private void onProfileClicked(LocalBluetoothProfile localBluetoothProfile, CheckBox checkBox) {
        BluetoothDevice device = this.mCachedDevice.getDevice();
        if (KEY_PBAP_SERVER.equals(checkBox.getTag())) {
            int i = this.mCachedDevice.getPhonebookPermissionChoice() == 1 ? 2 : 1;
            this.mCachedDevice.setPhonebookPermissionChoice(i);
            checkBox.setChecked(i == 1);
        } else {
            if (!checkBox.isChecked()) {
                checkBox.setChecked(true);
                askDisconnect(this.mManager.getForegroundActivity(), localBluetoothProfile);
                return;
            }
            if (localBluetoothProfile instanceof MapProfile) {
                this.mCachedDevice.setMessagePermissionChoice(1);
            }
            if (localBluetoothProfile.isPreferred(device)) {
                if (localBluetoothProfile instanceof PanProfile) {
                    this.mCachedDevice.connectProfile(localBluetoothProfile);
                } else {
                    localBluetoothProfile.setPreferred(device, false);
                }
            } else {
                localBluetoothProfile.setPreferred(device, true);
                this.mCachedDevice.connectProfile(localBluetoothProfile);
            }
            refreshProfilePreference(checkBox, localBluetoothProfile);
        }
    }

    private void askDisconnect(Context context, final LocalBluetoothProfile localBluetoothProfile) {
        final CachedBluetoothDevice cachedBluetoothDevice = this.mCachedDevice;
        String name = cachedBluetoothDevice.getName();
        if (TextUtils.isEmpty(name)) {
            name = context.getString(R.string.bluetooth_device);
        }
        String string = context.getString(localBluetoothProfile.getNameResource(cachedBluetoothDevice.getDevice()));
        this.mDisconnectDialog = BluetoothUtils.showDisconnectDialog(context, this.mDisconnectDialog, new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.bluetooth.dialog.DeviceProfilesSettings.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                cachedBluetoothDevice.disconnect(localBluetoothProfile);
                localBluetoothProfile.setPreferred(cachedBluetoothDevice.getDevice(), false);
                if (localBluetoothProfile instanceof MapProfile) {
                    cachedBluetoothDevice.setMessagePermissionChoice(2);
                }
                DeviceProfilesSettings.this.refreshProfilePreference(DeviceProfilesSettings.this.findProfile(localBluetoothProfile.toString()), localBluetoothProfile);
            }
        }, context.getString(R.string.bluetooth_disable_profile_title), Html.fromHtml(context.getString(R.string.bluetooth_disable_profile_message, string, name)));
        Utils.fixButtonStyle(this.mDisconnectDialog);
    }

    @Override // com.android.settingslib.bluetooth.CachedBluetoothDevice.Callback
    public void onDeviceAttributesChanged() {
        refresh();
    }

    private void refresh() {
        EditText editText = (EditText) this.mRootView.findViewById(R.id.name);
        if (editText != null) {
            editText.setText(this.mCachedDevice.getName());
        }
        refreshProfiles();
    }

    private void refreshProfiles() {
        for (LocalBluetoothProfile localBluetoothProfile : this.mCachedDevice.getConnectableProfiles()) {
            CheckBox checkBoxFindProfile = findProfile(localBluetoothProfile.toString());
            if (checkBoxFindProfile == null) {
                this.mProfileContainer.addView(createProfilePreference(localBluetoothProfile));
            } else {
                refreshProfilePreference(checkBoxFindProfile, localBluetoothProfile);
            }
        }
        for (LocalBluetoothProfile localBluetoothProfile2 : this.mCachedDevice.getRemovedProfiles()) {
            CheckBox checkBoxFindProfile2 = findProfile(localBluetoothProfile2.toString());
            if (checkBoxFindProfile2 != null) {
                Log.d(TAG, "Removing " + localBluetoothProfile2.toString() + " from profile list");
                this.mProfileContainer.removeView(checkBoxFindProfile2);
            }
        }
        showOrHideProfileGroup();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CheckBox findProfile(String str) {
        return (CheckBox) this.mProfileContainer.findViewWithTag(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshProfilePreference(CheckBox checkBox, LocalBluetoothProfile localBluetoothProfile) {
        BluetoothDevice device = this.mCachedDevice.getDevice();
        checkBox.setEnabled(!this.mCachedDevice.isBusy());
        if (localBluetoothProfile instanceof MapProfile) {
            checkBox.setChecked(this.mCachedDevice.getMessagePermissionChoice() == 1);
            return;
        }
        if (localBluetoothProfile instanceof PbapServerProfile) {
            checkBox.setChecked(this.mCachedDevice.getPhonebookPermissionChoice() == 1);
        } else if (localBluetoothProfile instanceof PanProfile) {
            checkBox.setChecked(localBluetoothProfile.getConnectionStatus(device) == 2);
        } else {
            checkBox.setChecked(localBluetoothProfile.isPreferred(device));
        }
    }

    private LocalBluetoothProfile getProfileOf(View view) {
        if (!(view instanceof CheckBox)) {
            return null;
        }
        String str = (String) view.getTag();
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return this.mProfileManager.getProfileByName(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }
}
