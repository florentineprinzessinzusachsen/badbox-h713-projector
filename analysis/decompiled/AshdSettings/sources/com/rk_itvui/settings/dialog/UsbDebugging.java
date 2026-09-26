package com.rk_itvui.settings.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.SystemProperties;
import android.provider.Settings;
import com.ashd.settings.R;
import com.rk_itvui.settings.developer.DevelopmentSettings;
import com.rk_itvui.settings.developer.ListViewAdapter;

/* JADX INFO: loaded from: classes.dex */
public class UsbDebugging {
    private Context mContext;
    private Handler mHandler;
    private ListViewAdapter mListViewAdapter;
    private boolean mOkClicked;
    private DialogInterface mOkDialog;
    private int mstate;
    private boolean mselect = false;
    private boolean mreflash = false;

    public UsbDebugging(Context context, Handler handler, ListViewAdapter listViewAdapter) {
        this.mContext = null;
        this.mHandler = null;
        this.mContext = context;
        this.mHandler = handler;
        this.mListViewAdapter = listViewAdapter;
        this.mstate = Settings.Secure.getInt(this.mContext.getContentResolver(), "adb_enabled", 0);
        ((DevelopmentSettings) this.mContext).updateSettingItem(R.string.usb_debugging, this.mstate == 1 ? R.string.open : R.string.off, -1, R.drawable.usbdebug);
    }

    public static boolean isMonkeyRunning() {
        return SystemProperties.getBoolean("ro.monkey", false);
    }

    public void DebuggingUsb() {
        if (isMonkeyRunning()) {
            return;
        }
        this.mselect = this.mstate == 1;
        if (!this.mselect) {
            this.mOkDialog = new AlertDialog.Builder(this.mContext).setMessage(this.mContext.getResources().getString(R.string.adb_warning_message)).setTitle(R.string.adb_warning_title).setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.dialog.UsbDebugging.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    Settings.Secure.putInt(UsbDebugging.this.mContext.getContentResolver(), "adb_enabled", 1);
                    UsbDebugging.this.mstate = Settings.Secure.getInt(UsbDebugging.this.mContext.getContentResolver(), "adb_enabled", 0);
                    UsbDebugging.this.mselect = true;
                    ((DevelopmentSettings) UsbDebugging.this.mContext).updateSettingItem(R.string.usb_debugging, UsbDebugging.this.mstate == 1 ? R.string.open : R.string.off, -1, R.drawable.usbdebug);
                    UsbDebugging.this.mHandler.sendEmptyMessage(0);
                }
            }).setNegativeButton(android.R.string.no, (DialogInterface.OnClickListener) null).show();
            return;
        }
        Settings.Secure.putInt(this.mContext.getContentResolver(), "adb_enabled", 0);
        this.mstate = Settings.Secure.getInt(this.mContext.getContentResolver(), "adb_enabled", 0);
        this.mselect = false;
        ((DevelopmentSettings) this.mContext).updateSettingItem(R.string.usb_debugging, this.mstate == 1 ? R.string.open : R.string.off, -1, R.drawable.usbdebug);
        this.mHandler.sendEmptyMessage(0);
    }
}
