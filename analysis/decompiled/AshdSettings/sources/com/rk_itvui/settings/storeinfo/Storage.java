package com.rk_itvui.settings.storeinfo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.DiskInfo;
import android.os.storage.IMountService;
import android.os.storage.StorageEventListener;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;
import com.ashd.settings.R;
import com.rk_itvui.settings.FullScreenActivity;
import com.rk_itvui.settings.ScreenInformation;
import com.rk_itvui.settings.Utils;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Storage extends FullScreenActivity {
    public static final long ONE_GB_SIZE = 1073741824;
    public static final long ONE_KB_SIZE = 1024;
    public static final long ONE_MB_SIZE = 1048576;
    public static final String TAG = "Storage";
    private String mExternalAvailableSpace;
    private String mNANDAvailableSpace;
    private String mNANDTotalSpace;
    private String mSdAvailableSpace;
    private String mSdTotalSpace;
    private IMountService mMountService = null;
    private StorageManager mStorageManager = null;
    private TextView mSdCardTotal = null;
    private TextView mSdCardAvailable = null;
    private TextView mNandTotal = null;
    private TextView mNandAvailable = null;
    StorageEventListener mStorageListener = new StorageEventListener() { // from class: com.rk_itvui.settings.storeinfo.Storage.1
        public void onStorageStateChanged(String str, String str2, String str3) {
            Storage.this.readSDCard();
            Storage.this.updateStatusNandflash();
        }
    };

    private Bitmap bitMapScale(int i) {
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(getResources(), i);
        float f = (ScreenInformation.mScreenWidth / 1280.0f) * ScreenInformation.mDpiRatio;
        return Bitmap.createScaledBitmap(bitmapDecodeResource, (int) (bitmapDecodeResource.getWidth() * f), (int) (bitmapDecodeResource.getHeight() * f), true);
    }

    @Override // com.rk_itvui.settings.FullScreenActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.storage);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        ScreenInformation.mScreenWidth = displayMetrics.widthPixels;
        ScreenInformation.mScreenHeight = displayMetrics.heightPixels;
        ScreenInformation.mDensityDpi = displayMetrics.densityDpi;
        ScreenInformation.mDpiRatio = ScreenInformation.mDefaultDpi / displayMetrics.densityDpi;
        this.mStorageManager = (StorageManager) getSystemService("storage");
        createContentTitle();
        setUpView();
        updateStatusNandflash();
        readSDCard();
    }

    private void createContentTitle() {
        float f = (ScreenInformation.mScreenWidth / 52.0f) * ScreenInformation.mDpiRatio;
        TextView textView = (TextView) findViewById(R.id.sdcard);
        float f2 = 5.0f + f;
        textView.setTextSize(f2);
        TextView textView2 = (TextView) findViewById(R.id.sdcard_total_title);
        textView2.setTextSize(f);
        TextView textView3 = (TextView) findViewById(R.id.sdcard_available_title);
        textView3.setTextSize(f);
        if (getPackageManager().hasSystemFeature("android.settings.sdcard")) {
            textView.setVisibility(8);
            textView2.setVisibility(8);
            textView3.setVisibility(8);
        }
        ((TextView) findViewById(R.id.nandflash)).setTextSize(f2);
        ((TextView) findViewById(R.id.nand_total_title)).setTextSize(f);
        ((TextView) findViewById(R.id.nand_available_title)).setTextSize(f);
    }

    private void setUpView() {
        this.mSdCardTotal = (TextView) findViewById(R.id.sdcard_total);
        this.mSdCardAvailable = (TextView) findViewById(R.id.sdcard_available);
        this.mNandTotal = (TextView) findViewById(R.id.nand_total);
        this.mNandAvailable = (TextView) findViewById(R.id.nand_available);
        if (getPackageManager().hasSystemFeature("android.settings.sdcard")) {
            this.mSdCardTotal.setVisibility(8);
            this.mSdCardAvailable.setVisibility(8);
        }
        float f = (ScreenInformation.mScreenWidth / 52.0f) * ScreenInformation.mDpiRatio;
        this.mSdCardTotal.setTextSize(f);
        this.mSdCardAvailable.setTextSize(f);
        this.mNandTotal.setTextSize(f);
        this.mNandAvailable.setTextSize(f);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mStorageManager.registerListener(this.mStorageListener);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        if (this.mStorageManager == null || this.mStorageListener == null) {
            return;
        }
        this.mStorageManager.unregisterListener(this.mStorageListener);
    }

    private String formatSize(long j) {
        return Utils.formatSize(j, TAG);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStatusNandflash() {
        String externalStorageState = Environment.getExternalStorageState();
        String string = "";
        if ("mounted_ro".equals(externalStorageState)) {
            externalStorageState = "mounted";
            string = getResources().getString(R.string.read_only);
        }
        if ("mounted".equals(externalStorageState)) {
            try {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                long blockSize = statFs.getBlockSize();
                long blockCount = statFs.getBlockCount();
                long availableBlocks = statFs.getAvailableBlocks();
                this.mNANDTotalSpace = formatSize(blockCount * blockSize);
                if (this.mNANDTotalSpace != null) {
                    Log.d("hjc", "mNANDTotalSpace:" + this.mNANDTotalSpace);
                    Log.d("hjc", "mNANDTotalSpace.substring:" + this.mNANDTotalSpace.substring(0, 4));
                    float f = Float.parseFloat(this.mNANDTotalSpace.substring(0, 3));
                    Log.d("hjc", "result:" + f);
                    double d = (double) f;
                    if (d < 2.5d && d > 0.5d) {
                        this.mNANDTotalSpace = "4 GB";
                    } else if (d < 6.5d && d > 4.5d) {
                        this.mNANDTotalSpace = "8 GB";
                    } else if (d < 14.5d && d > 12.5d) {
                        this.mNANDTotalSpace = "16 GB";
                    } else if (d < 0.5d) {
                        this.mNANDTotalSpace = "2 GB";
                    }
                }
                this.mNANDAvailableSpace = formatSize(availableBlocks * blockSize) + string;
            } catch (IllegalArgumentException unused) {
                this.mNANDTotalSpace = getResources().getString(R.string.status_unavailable);
                this.mNANDAvailableSpace = getResources().getString(R.string.status_unavailable);
            }
        } else {
            this.mNANDTotalSpace = getResources().getString(R.string.status_unavailable);
            this.mNANDAvailableSpace = getResources().getString(R.string.status_unavailable);
        }
        this.mNandTotal.setText(this.mNANDTotalSpace);
        this.mNandAvailable.setText(this.mNANDAvailableSpace);
    }

    public void readSDCard() {
        boolean z;
        DiskInfo disk;
        List volumes = this.mStorageManager.getVolumes();
        String path = "";
        if (volumes != null && volumes.size() > 0) {
            Iterator it = volumes.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                VolumeInfo volumeInfo = (VolumeInfo) it.next();
                if (volumeInfo.getType() == 0 && volumeInfo.getPath() != null && (disk = volumeInfo.getDisk()) != null && disk.isSd()) {
                    path = volumeInfo.getPath().getPath();
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
            break;
        }
        if (!z) {
            this.mSdCardTotal.setText(R.string.status_unavailable);
            this.mSdCardAvailable.setText(R.string.status_unavailable);
            return;
        }
        File file = new File(path);
        if (file.exists()) {
            long totalSpace = file.getTotalSpace();
            long usableSpace = file.getUsableSpace();
            double d = totalSpace;
            double d2 = d / 1.073741824E9d;
            double d3 = d / 1048576.0d;
            double d4 = d / 1024.0d;
            if (d2 >= 1.0d) {
                this.mSdCardTotal.setText(String.format("%.2f GB", Double.valueOf(d2)));
            } else if (d3 >= 1.0d) {
                this.mSdCardTotal.setText(String.format("%.2f MB", Double.valueOf(d3)));
            } else if (d4 >= 1.0d) {
                this.mSdCardTotal.setText(String.format("%.2f KB", Double.valueOf(d4)));
            } else {
                this.mSdCardTotal.setText(String.format("%.2f B", Long.valueOf(totalSpace)));
            }
            double d5 = usableSpace;
            double d6 = d5 / 1.073741824E9d;
            double d7 = d5 / 1048576.0d;
            double d8 = d5 / 1024.0d;
            if (d6 >= 1.0d) {
                this.mSdCardAvailable.setText(String.format("%.2f GB", Double.valueOf(d6)));
                return;
            }
            if (d7 >= 1.0d) {
                this.mSdCardAvailable.setText(String.format("%.2f MB", Double.valueOf(d7)));
            } else if (d8 >= 1.0d) {
                this.mSdCardAvailable.setText(String.format("%.2f KB", Double.valueOf(d8)));
            } else {
                this.mSdCardAvailable.setText(String.format("%.2f B", Long.valueOf(totalSpace)));
            }
        }
    }
}
