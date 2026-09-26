package com.rk_itvui.settings.bluetooth;

import android.app.AlertDialog;
import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.bluetooth.Utils;
import com.ashd.settings.R;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothUtils {
    public static final String[] deviceWhiteList = {"语音助手", "BLE_YT_RMC", "MK"};
    private static final Utils.ErrorListener mErrorListener = new Utils.ErrorListener() { // from class: com.rk_itvui.settings.bluetooth.BluetoothUtils.1
        @Override // com.android.settingslib.bluetooth.Utils.ErrorListener
        public void onShowError(Context context, String str, int i) {
            BluetoothUtils.showError(context, str, i);
        }
    };
    private static final LocalBluetoothManager.BluetoothManagerCallback mOnInitCallback = new LocalBluetoothManager.BluetoothManagerCallback() { // from class: com.rk_itvui.settings.bluetooth.BluetoothUtils.2
        @Override // com.android.settingslib.bluetooth.LocalBluetoothManager.BluetoothManagerCallback
        public void onBluetoothManagerInitialized(Context context, LocalBluetoothManager localBluetoothManager) {
            Utils.setErrorListener(BluetoothUtils.mErrorListener);
        }
    };

    public static boolean isDeviceCanDisconnect(String str) {
        if (str == null || TextUtils.isEmpty(str)) {
            return false;
        }
        for (int length = deviceWhiteList.length - 1; length >= 0; length--) {
            if (deviceWhiteList[length].contains(str)) {
                return true;
            }
        }
        return false;
    }

    public static Drawable toDrawable(Context context, int i) {
        return context.getResources().getDrawable(i);
    }

    public static int getBtClassDrawableWithDescription(BluetoothClass bluetoothClass) {
        if (bluetoothClass != null) {
            int majorDeviceClass = bluetoothClass.getMajorDeviceClass();
            if (majorDeviceClass == 256) {
                return R.drawable.type_pc2;
            }
            if (majorDeviceClass == 512) {
                return R.drawable.type_2;
            }
            if (majorDeviceClass == 1280) {
                int deviceClass = bluetoothClass.getDeviceClass();
                if (deviceClass == 1344) {
                    return R.drawable.type_11;
                }
                if (deviceClass != 1408) {
                    return deviceClass != 1472 ? R.drawable.type13 : R.drawable.type_11;
                }
                return R.drawable.type_12;
            }
            if (majorDeviceClass == 1536) {
                return R.drawable.type_10;
            }
        } else {
            Log.w("Settings", "mBtClass is null");
        }
        if (bluetoothClass != null) {
            return (bluetoothClass.doesClassMatch(1) || bluetoothClass.doesClassMatch(0)) ? R.drawable.type_4 : R.drawable.type_bluettoth;
        }
        return R.drawable.type_bluettoth;
    }

    public static String BondToString(Context context, int i) {
        switch (i) {
            case 11:
                return context.getResources().getString(R.string.bluetooth_pairing);
            case 12:
                return context.getResources().getString(R.string.bluetooth_preference_paired_devices);
            default:
                return "";
        }
    }

    public static String ConnectToString(Context context, int i, boolean z) {
        switch (i) {
            case 1:
                return context.getResources().getString(R.string.bluetooth_connecting);
            case 2:
                return context.getResources().getString(R.string.bluetooth_connected);
            default:
                return z ? context.getResources().getString(R.string.bluetooth_preference_paired_devices) : "";
        }
    }

    static void showError(Context context, String str, int i) {
        String string = context.getString(i, str);
        LocalBluetoothManager localBtManager = getLocalBtManager(context);
        Context foregroundActivity = localBtManager.getForegroundActivity();
        if (localBtManager.isForegroundActivity()) {
            new AlertDialog.Builder(foregroundActivity).setTitle(R.string.bluetooth_error_title).setMessage(string).setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null).show();
        } else {
            Toast.makeText(context, string, 0).show();
        }
    }

    public static LocalBluetoothManager getLocalBtManager(Context context) {
        return LocalBluetoothManager.getInstance(context, mOnInitCallback);
    }

    public static AlertDialog showDisconnectDialog(Context context, AlertDialog alertDialog, DialogInterface.OnClickListener onClickListener, CharSequence charSequence, CharSequence charSequence2) {
        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(context).setPositiveButton(android.R.string.ok, onClickListener).setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null).create();
        } else {
            if (alertDialog.isShowing()) {
                alertDialog.dismiss();
            }
            alertDialog.setButton(-1, context.getText(android.R.string.ok), onClickListener);
        }
        alertDialog.setTitle(charSequence);
        alertDialog.setMessage(charSequence2);
        com.rk_itvui.settings.Utils.fixButtonStyle(alertDialog);
        alertDialog.show();
        return alertDialog;
    }
}
