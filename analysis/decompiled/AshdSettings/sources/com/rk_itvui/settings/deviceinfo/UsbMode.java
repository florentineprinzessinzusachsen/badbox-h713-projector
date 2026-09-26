package com.rk_itvui.settings.deviceinfo;

import android.content.Context;
import android.content.res.Resources;
import android.hardware.usb.UsbManager;
import android.os.Handler;
import android.util.Log;
import com.ashd.settings.R;
import com.rk_itvui.settings.developer.DevelopmentSettings;

/* JADX INFO: loaded from: classes.dex */
public class UsbMode {
    private static final boolean DEBUG = true;
    private static final String KEY_MASS = "usb_mass";
    private static final String KEY_MTP = "usb_mtp";
    private static final String KEY_PTP = "usb_ptp";
    public static final int REQUEST_CODE_USB_CONNECT_MODE = 1002;
    private static final String TAG = "UsbMode";
    public static final String USB_MODE = "usb_mode";
    private Context mContext;
    private Resources mRes = null;
    private Handler mUIHandler;
    private UsbManager mUsbManager;

    public void Pause() {
    }

    private void LOG(String str) {
        Log.d(TAG, str);
    }

    public UsbMode(Context context, Handler handler) {
        this.mContext = null;
        this.mUIHandler = null;
        this.mContext = context;
        this.mUIHandler = handler;
        this.mUsbManager = (UsbManager) context.getSystemService("usb");
    }

    public void Resume() {
        if ("mtp".equals("mtp")) {
            ((DevelopmentSettings) this.mContext).updateSettingItem(R.string.storage_menu_usb, R.string.usb_mtp_title, -1, R.drawable.mtp);
        } else if ("ptp".equals("mtp")) {
            ((DevelopmentSettings) this.mContext).updateSettingItem(R.string.storage_menu_usb, R.string.usb_ptp_title, -1, R.drawable.mtp);
        }
        this.mUIHandler.sendEmptyMessage(0);
    }

    public void updateMode(String str) {
        if (str == null) {
            return;
        }
        LOG("usbmode:" + str);
        if (str.equals(KEY_MTP)) {
            ((DevelopmentSettings) this.mContext).updateSettingItem(R.string.storage_menu_usb, R.string.usb_mtp_title, -1, R.drawable.mtp);
        } else if (str.equals(KEY_PTP)) {
            ((DevelopmentSettings) this.mContext).updateSettingItem(R.string.storage_menu_usb, R.string.usb_ptp_title, -1, R.drawable.mtp);
        }
        this.mUIHandler.sendEmptyMessage(0);
    }
}
