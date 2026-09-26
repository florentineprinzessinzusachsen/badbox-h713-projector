package com.rk_itvui.settings.dialog;

import android.content.Context;
import android.os.Handler;
import android.os.storage.StorageManager;
import android.util.Log;
import com.ashd.settings.R;
import com.rk_itvui.settings.developer.DevelopmentSettings;
import com.rk_itvui.settings.developer.ListViewAdapter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class UsbModeSettings {
    private static final String HOST_MODE = new String("1");
    private static final String SLAVE_MODE = new String("2");
    private static final String TAG = "UsbModeSettings";
    private File file;
    private Context mContext;
    private Handler mHandler;
    private ListViewAdapter mListViewAdapter;
    private String mMode;
    private StorageManager mStorageManager;
    private boolean mLock = false;
    private Runnable mUsbSwitch = new Runnable() { // from class: com.rk_itvui.settings.dialog.UsbModeSettings.1
        @Override // java.lang.Runnable
        public synchronized void run() {
            Log.d(UsbModeSettings.TAG, "mUsbSwitch Runnable() in*******************");
            if (UsbModeSettings.this.mStorageManager != null) {
                if (UsbModeSettings.this.mMode == UsbModeSettings.HOST_MODE) {
                    UsbModeSettings.this.mStorageManager.disableUsbMassStorage();
                    Log.d(UsbModeSettings.TAG, "mStorageManager.disableUsbMassStorage()*******************");
                    UsbModeSettings.this.Write2File(UsbModeSettings.this.file, UsbModeSettings.this.mMode);
                    ((DevelopmentSettings) UsbModeSettings.this.mContext).updateSettingItem(R.string.usb_setting, R.string.host_mode_title, -1, R.drawable.conect_usb);
                } else {
                    UsbModeSettings.this.Write2File(UsbModeSettings.this.file, UsbModeSettings.this.mMode);
                    Log.d(UsbModeSettings.TAG, "mStorageManager.enableUsbMassStorage()  in *******************");
                    UsbModeSettings.this.mStorageManager.enableUsbMassStorage();
                    Log.d(UsbModeSettings.TAG, "mStorageManager.enableUsbMassStorage()   out*******************");
                    ((DevelopmentSettings) UsbModeSettings.this.mContext).updateSettingItem(R.string.usb_setting, R.string.slave_mode_title, -1, R.drawable.conect_usb);
                }
                UsbModeSettings.this.mHandler.sendEmptyMessage(0);
            }
            Log.d(UsbModeSettings.TAG, "mUsbSwitch Runnable() out*******************");
            UsbModeSettings.this.mLock = false;
        }
    };

    public UsbModeSettings(Context context, Handler handler, ListViewAdapter listViewAdapter) {
        this.file = null;
        this.mStorageManager = null;
        this.mMode = null;
        this.mContext = context;
        this.mHandler = handler;
        this.mListViewAdapter = listViewAdapter;
        this.mStorageManager = (StorageManager) this.mContext.getSystemService("storage");
        this.file = new File("/sys/bus/platform/drivers/usb20_otg/force_usb_mode");
        if (this.file.exists()) {
            Log.d("UsbModeSelect", "/data/otg.cfg not exist,but temp file exist");
            this.mMode = ReadFromFile(this.file);
            if (this.mMode == null) {
                this.mMode = HOST_MODE;
            }
        } else {
            this.mMode = HOST_MODE;
        }
        ((DevelopmentSettings) this.mContext).updateSettingItem(R.string.usb_setting, this.mMode.equals(HOST_MODE) ? R.string.host_mode_title : R.string.slave_mode_title, -1, R.drawable.conect_usb);
    }

    private String ReadFromFile(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            String line = new BufferedReader(new InputStreamReader(fileInputStream)).readLine();
            fileInputStream.close();
            return line;
        } catch (IOException e) {
            Log.i(TAG, "ReadFromFile exception:" + e);
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Write2File(File file, String str) {
        Log.d("UsbModeSelect", "Write2File,write mode = " + str);
        if (file == null || !file.exists() || str == null) {
            return;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            PrintWriter printWriter = new PrintWriter(fileOutputStream);
            printWriter.println(str);
            printWriter.flush();
            printWriter.close();
            fileOutputStream.close();
        } catch (IOException unused) {
        }
    }

    public void onUsbModeClick() {
        if (this.mLock) {
            return;
        }
        this.mLock = true;
        Log.d(TAG, "onUsbModeClick*******************");
        if (this.mMode.equals(HOST_MODE)) {
            this.mMode = SLAVE_MODE;
        } else {
            this.mMode = HOST_MODE;
        }
        synchronized (this) {
            this.mHandler.removeCallbacks(this.mUsbSwitch);
            this.mHandler.post(this.mUsbSwitch);
        }
    }
}
