package com.android.settingslib.system;

import android.content.Context;
import android.os.SystemProperties;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class UsbModeSettings {
    public static final String HOST_MODE = new String("1");
    public static final String SLAVE_MODE = new String("2");
    private static final String TAG = "UsbModeSettings";
    private static final String filename_rk3328 = "/sys/bus/platform/drivers/usb20_otg/force_usb_mode";
    private static final String filename_rk3399 = "/sys/kernel/debug/usb@fe800000/rk_usb_force_mode";
    private File file;
    private Context mContext;
    private String mSocName;
    private StorageManager mStorageManager;
    private String mMode = null;
    private boolean mLock = false;
    private Runnable mUsbSwitch = new Runnable() { // from class: com.android.settingslib.system.UsbModeSettings.1
        @Override // java.lang.Runnable
        public synchronized void run() {
            Log.d(UsbModeSettings.TAG, "mUsbSwitch Runnable() in*******************");
            if (UsbModeSettings.this.mStorageManager != null) {
                if (UsbModeSettings.this.mMode == UsbModeSettings.HOST_MODE) {
                    UsbModeSettings.this.mStorageManager.disableUsbMassStorage();
                    Log.d(UsbModeSettings.TAG, "mStorageManager.disableUsbMassStorage()*******************");
                    UsbModeSettings.this.Write2File(UsbModeSettings.this.file, UsbModeSettings.this.mMode);
                } else {
                    UsbModeSettings.this.Write2File(UsbModeSettings.this.file, UsbModeSettings.this.mMode);
                    Log.d(UsbModeSettings.TAG, "mStorageManager.enableUsbMassStorage()  in *******************");
                    UsbModeSettings.this.mStorageManager.enableUsbMassStorage();
                    Log.d(UsbModeSettings.TAG, "mStorageManager.enableUsbMassStorage()   out*******************");
                }
            }
            Log.d(UsbModeSettings.TAG, "mUsbSwitch Runnable() out*******************");
            UsbModeSettings.this.mLock = false;
        }
    };

    public UsbModeSettings(Context context) {
        this.file = null;
        this.mStorageManager = null;
        this.mSocName = null;
        this.mContext = context;
        this.mSocName = SystemProperties.get("sys.rk.soc");
        if (TextUtils.isEmpty(this.mSocName)) {
            this.mSocName = SystemProperties.get("ro.board.platform");
        }
        if (!TextUtils.isEmpty(this.mSocName) && this.mSocName.contains("rk3399")) {
            this.file = new File(filename_rk3399);
        } else {
            this.file = new File(filename_rk3328);
        }
        this.mStorageManager = (StorageManager) this.mContext.getSystemService("storage");
        checkFile();
    }

    public boolean getDefaultValue() {
        if (checkFile()) {
            Log.d("UsbModeSelect", "/data/otg.cfg not exist,but temp file exist");
            this.mMode = ReadFromFile(this.file);
            return !this.mMode.equals(HOST_MODE);
        }
        this.mMode = HOST_MODE;
        return false;
    }

    private String ReadFromFile(File file) {
        if (!checkFile()) {
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
        if (!checkFile() || str == null) {
            return;
        }
        Log.d("UsbModeSelect", "Write2File,write mode = " + str);
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

    public void onUsbModeClick(String str) {
        if (this.mLock) {
            return;
        }
        this.mLock = true;
        this.mMode = str;
        synchronized (this) {
            new Thread(this.mUsbSwitch).start();
        }
    }

    private boolean checkFile() {
        if (this.file == null) {
            Log.e(TAG, "file is null pointer");
            return false;
        }
        String name = this.file.getName();
        if (!this.file.exists()) {
            Log.e(TAG, name + " not exist!!!");
            return false;
        }
        if (!this.file.canRead()) {
            Log.e(TAG, name + " can't read!!!");
            return false;
        }
        if (this.file.canWrite()) {
            return true;
        }
        Log.e(TAG, name + " can't write!!!");
        return false;
    }
}
