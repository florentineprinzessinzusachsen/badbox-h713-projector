package com.rk_itvui.settings.bluetooth.dialog;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import com.android.internal.app.AlertActivity;
import com.android.internal.app.AlertController;
import com.android.settingslib.bluetooth.CachedBluetoothDeviceManager;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.bluetooth.LocalBluetoothProfile;
import com.ashd.settings.R;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class BluetoothPairingDialog extends AlertActivity implements CompoundButton.OnCheckedChangeListener, DialogInterface.OnClickListener, TextWatcher, LocalBluetoothManager.BluetoothManagerCallback {
    private static final int BLUETOOTH_PASSKEY_MAX_LENGTH = 6;
    private static final int BLUETOOTH_PIN_MAX_LENGTH = 16;
    private static final String TAG = "BluetoothPairingDialog";
    private LocalBluetoothManager mBluetoothManager;
    private CachedBluetoothDeviceManager mCachedDeviceManager;
    private BluetoothDevice mDevice;
    private Button mOkButton;
    private String mPairingKey;
    private EditText mPairingView;
    private LocalBluetoothProfile mPbapClientProfile;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.rk_itvui.settings.bluetooth.dialog.BluetoothPairingDialog.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.bluetooth.device.action.BOND_STATE_CHANGED".equals(action)) {
                int intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", Integer.MIN_VALUE);
                if (intExtra == 12 || intExtra == 10) {
                    BluetoothPairingDialog.this.dismiss();
                    return;
                }
                return;
            }
            if ("android.bluetooth.device.action.PAIRING_CANCEL".equals(action)) {
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (bluetoothDevice == null || bluetoothDevice.equals(BluetoothPairingDialog.this.mDevice)) {
                    BluetoothPairingDialog.this.dismiss();
                }
            }
        }
    };
    private boolean mReceiverRegistered;
    private int mType;

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mReceiverRegistered = false;
        setWindowSize();
        Intent intent = getIntent();
        if (!intent.getAction().equals("android.bluetooth.device.action.PAIRING_REQUEST")) {
            Log.e(TAG, "Error: this activity may be started only with intent android.bluetooth.device.action.PAIRING_REQUEST");
            finish();
            return;
        }
        this.mBluetoothManager = LocalBluetoothManager.getInstance(this, this);
        if (this.mBluetoothManager == null) {
            Log.e(TAG, "Error: BluetoothAdapter not supported by system");
            finish();
            return;
        }
        this.mCachedDeviceManager = this.mBluetoothManager.getCachedDeviceManager();
        this.mPbapClientProfile = this.mBluetoothManager.getProfileManager().getPbapClientProfile();
        this.mDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        this.mType = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", Integer.MIN_VALUE);
        switch (this.mType) {
            case 0:
            case 1:
            case 7:
                createUserEntryDialog();
                break;
            case 2:
                int intExtra = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_KEY", Integer.MIN_VALUE);
                if (intExtra == Integer.MIN_VALUE) {
                    Log.e(TAG, "Invalid Confirmation Passkey received, not showing any dialog");
                    this.mDevice.setPairingConfirmation(false);
                    finish();
                    return;
                }
                this.mPairingKey = String.format(Locale.US, "%06d", Integer.valueOf(intExtra));
                createConfirmationDialog();
                break;
            case 3:
            case 6:
                createConsentDialog();
                break;
            case 4:
            case 5:
                int intExtra2 = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_KEY", Integer.MIN_VALUE);
                if (intExtra2 == Integer.MIN_VALUE) {
                    Log.e(TAG, "Invalid Confirmation Passkey or PIN received, not showing any dialog");
                    finish();
                    return;
                } else {
                    if (this.mType == 4) {
                        this.mPairingKey = String.format("%06d", Integer.valueOf(intExtra2));
                    } else {
                        this.mPairingKey = String.format("%04d", Integer.valueOf(intExtra2));
                    }
                    createDisplayPasskeyOrPinDialog();
                }
                break;
            default:
                Log.e(TAG, "Incorrect pairing type received, not showing any dialog");
                finish();
                return;
        }
        registerReceiver(this.mReceiver, new IntentFilter("android.bluetooth.device.action.PAIRING_CANCEL"));
        registerReceiver(this.mReceiver, new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED"));
        this.mReceiverRegistered = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setWindowSize() {
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = getScreenWidth(this) / 3;
        attributes.height = getScreenHeight(this) / 3;
        attributes.gravity = 17;
        window.setAttributes(attributes);
    }

    public static Display getDisplay(Context context) {
        WindowManager windowManager;
        if (context instanceof Activity) {
            windowManager = ((Activity) context).getWindowManager();
        } else {
            windowManager = (WindowManager) context.getSystemService("window");
        }
        if (windowManager != null) {
            return windowManager.getDefaultDisplay();
        }
        return null;
    }

    public static DisplayMetrics getMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    public static int getScreenWidth(Context context) {
        return getMetrics(context).widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return getMetrics(context).heightPixels;
    }

    private void createUserEntryDialog() {
        AlertController.AlertParams alertParams = this.mAlertParams;
        alertParams.mTitle = getString(R.string.bluetooth_pairing_request, new Object[]{this.mCachedDeviceManager.getName(this.mDevice)});
        alertParams.mView = createPinEntryView();
        alertParams.mPositiveButtonText = getString(android.R.string.ok);
        alertParams.mPositiveButtonListener = this;
        alertParams.mNegativeButtonText = getString(android.R.string.cancel);
        alertParams.mNegativeButtonListener = this;
        setupAlert();
        this.mOkButton = this.mAlert.getButton(-1);
        this.mOkButton.setEnabled(false);
    }

    private View createPinEntryView() {
        int i;
        int i2;
        View viewInflate = getLayoutInflater().inflate(R.layout.bluetooth_pin_entry, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.pin_values_hint);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.message_below_pin);
        CheckBox checkBox = (CheckBox) viewInflate.findViewById(R.id.alphanumeric_pin);
        CheckBox checkBox2 = (CheckBox) viewInflate.findViewById(R.id.phonebook_sharing_message_entry_pin);
        checkBox2.setText(getString(R.string.bluetooth_pairing_shares_phonebook, new Object[]{this.mCachedDeviceManager.getName(this.mDevice)}));
        checkBox2.setTextColor(-16777216);
        if (this.mPbapClientProfile != null && this.mPbapClientProfile.isProfileReady()) {
            checkBox2.setVisibility(8);
        }
        if (this.mDevice.getPhonebookAccessPermission() == 1) {
            checkBox2.setChecked(true);
        } else if (this.mDevice.getPhonebookAccessPermission() == 2) {
            checkBox2.setChecked(false);
        } else if (this.mDevice.getBluetoothClass().getDeviceClass() == 1032) {
            checkBox2.setChecked(true);
            this.mDevice.setPhonebookAccessPermission(1);
        } else {
            checkBox2.setChecked(false);
            this.mDevice.setPhonebookAccessPermission(2);
        }
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.rk_itvui.settings.bluetooth.dialog.BluetoothPairingDialog.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    BluetoothPairingDialog.this.mDevice.setPhonebookAccessPermission(1);
                } else {
                    BluetoothPairingDialog.this.mDevice.setPhonebookAccessPermission(2);
                }
            }
        });
        this.mPairingView = (EditText) viewInflate.findViewById(R.id.text);
        this.mPairingView.addTextChangedListener(this);
        checkBox.setOnCheckedChangeListener(this);
        int i3 = R.string.bluetooth_pin_values_hint;
        int i4 = this.mType;
        if (i4 != 7) {
            switch (i4) {
                case 0:
                    break;
                case 1:
                    i = R.string.bluetooth_enter_passkey_other_device;
                    i2 = 6;
                    checkBox.setVisibility(8);
                    break;
                default:
                    Log.e(TAG, "Incorrect pairing type for createPinEntryView: " + this.mType);
                    return null;
            }
            textView.setText(i3);
            textView2.setText(i);
            this.mPairingView.setInputType(2);
            this.mPairingView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i2)});
            return viewInflate;
        }
        i3 = R.string.bluetooth_pin_values_hint_16_digits;
        i = R.string.bluetooth_enter_pin_other_device;
        i2 = 16;
        textView.setText(i3);
        textView2.setText(i);
        this.mPairingView.setInputType(2);
        this.mPairingView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i2)});
        return viewInflate;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:25:0x00b7  */
    private View createView() {
        String str = null;
        View viewInflate = getLayoutInflater().inflate(R.layout.bluetooth_pin_confirm, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.pairing_caption);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.pairing_subhead);
        TextView textView3 = (TextView) viewInflate.findViewById(R.id.pairing_code_message);
        CheckBox checkBox = (CheckBox) viewInflate.findViewById(R.id.phonebook_sharing_message_confirm_pin);
        checkBox.setText(getString(R.string.bluetooth_pairing_shares_phonebook, new Object[]{this.mCachedDeviceManager.getName(this.mDevice)}));
        checkBox.setTextColor(-16777216);
        if (this.mPbapClientProfile != null && this.mPbapClientProfile.isProfileReady()) {
            checkBox.setVisibility(8);
        }
        if (this.mDevice.getPhonebookAccessPermission() == 1) {
            checkBox.setChecked(true);
        } else if (this.mDevice.getPhonebookAccessPermission() == 2) {
            checkBox.setChecked(false);
        } else if (this.mDevice.getBluetoothClass().getDeviceClass() == 1032) {
            checkBox.setChecked(true);
            this.mDevice.setPhonebookAccessPermission(1);
        } else {
            checkBox.setChecked(false);
            this.mDevice.setPhonebookAccessPermission(2);
        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.rk_itvui.settings.bluetooth.dialog.BluetoothPairingDialog.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    BluetoothPairingDialog.this.mDevice.setPhonebookAccessPermission(1);
                } else {
                    BluetoothPairingDialog.this.mDevice.setPhonebookAccessPermission(2);
                }
            }
        });
        switch (this.mType) {
            case 2:
                str = this.mPairingKey;
                if (str != null) {
                    textView.setVisibility(0);
                    textView2.setVisibility(0);
                    textView2.setText(str);
                }
                return viewInflate;
            case 3:
            case 6:
                textView3.setVisibility(0);
                if (str != null) {
                    textView.setVisibility(0);
                    textView2.setVisibility(0);
                    textView2.setText(str);
                }
                return viewInflate;
            case 4:
            case 5:
                textView3.setVisibility(0);
                str = this.mPairingKey;
                if (str != null) {
                    textView.setVisibility(0);
                    textView2.setVisibility(0);
                    textView2.setText(str);
                }
                return viewInflate;
            default:
                Log.e(TAG, "Incorrect pairing type received, not creating view");
                return null;
        }
    }

    private void createConfirmationDialog() {
        AlertController.AlertParams alertParams = this.mAlertParams;
        alertParams.mTitle = getString(R.string.bluetooth_pairing_request, new Object[]{this.mCachedDeviceManager.getName(this.mDevice)});
        alertParams.mView = createView();
        alertParams.mPositiveButtonText = getString(R.string.bluetooth_pairing_accept);
        alertParams.mPositiveButtonListener = this;
        alertParams.mNegativeButtonText = getString(R.string.bluetooth_pairing_decline);
        alertParams.mNegativeButtonListener = this;
        setupAlert();
    }

    private void createConsentDialog() {
        AlertController.AlertParams alertParams = this.mAlertParams;
        alertParams.mTitle = getString(R.string.bluetooth_pairing_request, new Object[]{this.mCachedDeviceManager.getName(this.mDevice)});
        alertParams.mView = createView();
        alertParams.mPositiveButtonText = getString(R.string.bluetooth_pairing_accept);
        alertParams.mPositiveButtonListener = this;
        alertParams.mNegativeButtonText = getString(R.string.bluetooth_pairing_decline);
        alertParams.mNegativeButtonListener = this;
        setupAlert();
    }

    private void createDisplayPasskeyOrPinDialog() {
        AlertController.AlertParams alertParams = this.mAlertParams;
        alertParams.mTitle = getString(R.string.bluetooth_pairing_request, new Object[]{this.mCachedDeviceManager.getName(this.mDevice)});
        alertParams.mView = createView();
        alertParams.mNegativeButtonText = getString(android.R.string.cancel);
        alertParams.mNegativeButtonListener = this;
        setupAlert();
        if (this.mType == 4) {
            this.mDevice.setPairingConfirmation(true);
        } else if (this.mType == 5) {
            this.mDevice.setPin(BluetoothDevice.convertPinToBytes(this.mPairingKey));
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.mReceiverRegistered) {
            this.mReceiverRegistered = false;
            unregisterReceiver(this.mReceiver);
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        if (this.mOkButton != null) {
            if (this.mType == 7) {
                this.mOkButton.setEnabled(editable.length() >= 16);
            } else {
                this.mOkButton.setEnabled(editable.length() > 0);
            }
        }
    }

    private void onPair(String str) {
        Log.i(TAG, "Pairing dialog accepted");
        switch (this.mType) {
            case 0:
            case 7:
                byte[] bArrConvertPinToBytes = BluetoothDevice.convertPinToBytes(str);
                if (bArrConvertPinToBytes != null) {
                    this.mDevice.setPin(bArrConvertPinToBytes);
                    break;
                }
                break;
            case 1:
                this.mDevice.setPasskey(Integer.parseInt(str));
                break;
            case 2:
            case 3:
                this.mDevice.setPairingConfirmation(true);
                break;
            case 4:
            case 5:
                break;
            case 6:
                this.mDevice.setRemoteOutOfBandData();
                break;
            default:
                Log.e(TAG, "Incorrect pairing type received");
                break;
        }
    }

    private void onCancel() {
        Log.i(TAG, "Pairing dialog canceled");
        this.mDevice.cancelPairingUserInput();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            onCancel();
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            if (this.mPairingView != null) {
                onPair(this.mPairingView.getText().toString());
                return;
            } else {
                onPair(null);
                return;
            }
        }
        onCancel();
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.mPairingView.setInputType(1);
        } else {
            this.mPairingView.setInputType(2);
        }
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothManager.BluetoothManagerCallback
    public void onBluetoothManagerInitialized(Context context, LocalBluetoothManager localBluetoothManager) {
        Log.d(TAG, "onBluetoothManagerInitialized: ");
    }
}
