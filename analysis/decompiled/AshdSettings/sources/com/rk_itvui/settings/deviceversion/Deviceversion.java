package com.rk_itvui.settings.deviceversion;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.hardware.audio.common.V2_0.AudioFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemProperties;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.os.EnvironmentCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.android.settingslib.system.DevelopmentActivity;
import com.ashd.settings.R;
import com.rk_itvui.settings.BuildConfig;
import com.rk_itvui.settings.FullScreenActivity;
import com.rk_itvui.settings.ScreenInformation;
import com.rk_itvui.settings.Utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class Deviceversion extends FullScreenActivity {
    public static boolean DEBUG = true;
    private static final String TAG = "Deviceversion";
    private String mNANDAvailableSpace;
    private String mNANDTotalSpace;
    private static final String PRODUCT_VERSION = SystemProperties.get("ro.rksdk.version", "");
    private static final boolean mIsHideStorageInfo = SystemProperties.getBoolean("persist.sys.hide.capacity", false);
    private boolean b = SystemProperties.getBoolean("persist.product.b", false);
    public String chipType = "rk3128";
    private final int[] mSaveIRData = new int[10];

    @Override // com.rk_itvui.settings.FullScreenActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.version);
        updateStatusNandflash();
        if (BuildConfig.FLAVOR.contains(BuildConfig.FLAVOR)) {
            TextView textView = (TextView) findViewById(R.id.app_text);
            TextView textView2 = (TextView) findViewById(R.id.settings_line);
            textView.setCompoundDrawablesWithIntrinsicBounds(getDrawable(R.drawable.icon_ashd2), (Drawable) null, (Drawable) null, (Drawable) null);
            textView2.setBackgroundResource(R.drawable.settings_head_line_ashd2);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        ScreenInformation.mScreenWidth = displayMetrics.widthPixels;
        ScreenInformation.mScreenHeight = displayMetrics.heightPixels;
        ScreenInformation.mDensityDpi = displayMetrics.densityDpi;
        ScreenInformation.mDpiRatio = ScreenInformation.mDefaultDpi / displayMetrics.densityDpi;
        createContextTitle();
        createContext();
        if (mIsHideStorageInfo) {
            hideStorageInfo();
        } else {
            showDeviceInfO();
        }
    }

    private void hideStorageInfo() {
        View viewFindViewById = findViewById(R.id.ll_ram_info);
        View viewFindViewById2 = findViewById(R.id.imageView_line3);
        View viewFindViewById3 = findViewById(R.id.ll_rom_info);
        View viewFindViewById4 = findViewById(R.id.imageView_line4);
        viewFindViewById.setVisibility(8);
        viewFindViewById2.setVisibility(8);
        viewFindViewById3.setVisibility(8);
        viewFindViewById4.setVisibility(8);
    }

    private void showDeviceInfO() {
        View viewFindViewById = findViewById(R.id.ll_ram_info);
        View viewFindViewById2 = findViewById(R.id.imageView_line3);
        View viewFindViewById3 = findViewById(R.id.ll_rom_info);
        View viewFindViewById4 = findViewById(R.id.imageView_line4);
        viewFindViewById.setVisibility(0);
        viewFindViewById2.setVisibility(0);
        viewFindViewById3.setVisibility(0);
        viewFindViewById4.setVisibility(0);
    }

    private void createContextTitle() {
        float f = (ScreenInformation.mScreenWidth / 52.0f) * ScreenInformation.mDpiRatio;
        ((TextView) findViewById(R.id.mode_title)).setTextSize(f);
        ((TextView) findViewById(R.id.android_title)).setTextSize(f);
        ((TextView) findViewById(R.id.kernel_title)).setTextSize(f);
        ((TextView) findViewById(R.id.build_title)).setTextSize(f);
        ((TextView) findViewById(R.id.storage_title)).setTextSize(f);
        ((TextView) findViewById(R.id.ram_title)).setTextSize(f);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            finish();
        }
        this.mSaveIRData[0] = this.mSaveIRData[1];
        this.mSaveIRData[1] = this.mSaveIRData[2];
        this.mSaveIRData[2] = this.mSaveIRData[3];
        this.mSaveIRData[3] = this.mSaveIRData[4];
        this.mSaveIRData[4] = this.mSaveIRData[5];
        this.mSaveIRData[5] = this.mSaveIRData[6];
        this.mSaveIRData[6] = this.mSaveIRData[7];
        this.mSaveIRData[7] = this.mSaveIRData[8];
        this.mSaveIRData[8] = this.mSaveIRData[9];
        this.mSaveIRData[9] = i;
        Log.e(TAG, String.valueOf(this.mSaveIRData[0]));
        Log.e(TAG, String.valueOf(this.mSaveIRData[1]));
        Log.e(TAG, String.valueOf(this.mSaveIRData[2]));
        Log.e(TAG, String.valueOf(this.mSaveIRData[3]));
        Log.e(TAG, String.valueOf(this.mSaveIRData[4]));
        Log.e(TAG, String.valueOf(this.mSaveIRData[5]));
        Log.e(TAG, String.valueOf(this.mSaveIRData[6]));
        Log.e(TAG, String.valueOf(this.mSaveIRData[7]));
        Log.e(TAG, String.valueOf(this.mSaveIRData[8]));
        Log.e(TAG, String.valueOf(this.mSaveIRData[9]));
        if (this.mSaveIRData[0] == 19 && this.mSaveIRData[1] == 19 && this.mSaveIRData[2] == 20 && this.mSaveIRData[3] == 20 && this.mSaveIRData[4] == 21 && this.mSaveIRData[5] == 21 && this.mSaveIRData[6] == 22 && this.mSaveIRData[7] == 22 && this.mSaveIRData[8] == 23 && this.mSaveIRData[9] == 23) {
            startActivity(new Intent(this, (Class<?>) DevelopmentActivity.class));
            return false;
        }
        if (this.mSaveIRData[5] != 19 || this.mSaveIRData[6] != 20 || this.mSaveIRData[7] != 21 || this.mSaveIRData[8] != 22 || this.mSaveIRData[9] != 23) {
            return false;
        }
        Log.e(TAG, "go go go");
        try {
            Intent intent = new Intent();
            intent.setClassName("com.konka.readmain", "com.konka.readmain.MainActivity");
            intent.setFlags(AudioFormat.EVRC);
            startActivity(intent);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void createContext() {
        float f = (ScreenInformation.mScreenWidth / 52.0f) * ScreenInformation.mDpiRatio;
        TextView textView = (TextView) findViewById(R.id.versiondevices);
        String str = SystemProperties.get("ro.product.model2", EnvironmentCompat.MEDIA_UNKNOWN);
        String str2 = SystemProperties.get("ro.product.modename", EnvironmentCompat.MEDIA_UNKNOWN);
        String str3 = SystemProperties.get("persist.product.modename", EnvironmentCompat.MEDIA_UNKNOWN);
        if (str.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
            str = Build.MODEL;
        }
        Log.i(TAG, "model3:" + str2);
        if (!str2.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
            str = str2;
        }
        if (str2.equals("null") || str3.equals("null")) {
            str = "";
        }
        textView.setText(str);
        textView.setTextSize(f);
        TextView textView2 = (TextView) findViewById(R.id.versionandroid);
        textView2.setText(Build.VERSION.RELEASE);
        textView2.setTextSize(f);
        TextView textView3 = (TextView) findViewById(R.id.tv_email);
        textView3.setText("Support@atmwelt.com");
        textView3.setTextSize(f);
        TextView textView4 = (TextView) findViewById(R.id.versionkernel);
        textView4.setText(getFormattedKernelVersion());
        textView4.setTextSize(f);
        TextView textView5 = (TextView) findViewById(R.id.numberversion);
        Log.d("Settings", PRODUCT_VERSION);
        Log.d("Settings", Build.DISPLAY);
        textView5.setText(PRODUCT_VERSION + "\n" + Build.DISPLAY);
        textView5.setTextSize(f);
        TextView textView6 = (TextView) findViewById(R.id.tv_storage_info);
        TextView textView7 = (TextView) findViewById(R.id.ram_info);
        textView7.setText(getMeminfoString());
        textView6.setText(getTotalROMSize());
        textView7.setTextSize(f);
        textView6.setTextSize(f);
    }

    private Bitmap bitMapScale(int i) {
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(getResources(), i);
        float f = (ScreenInformation.mScreenWidth / 1280.0f) * ScreenInformation.mDpiRatio;
        return Bitmap.createScaledBitmap(bitmapDecodeResource, (int) (bitmapDecodeResource.getWidth() * f), (int) (bitmapDecodeResource.getHeight() * f), true);
    }

    private String getFormattedKernelVersion() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/version"), 256);
            try {
                String line = bufferedReader.readLine();
                bufferedReader.close();
                Matcher matcher = Pattern.compile("\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)").matcher(line);
                if (!matcher.matches() || matcher.groupCount() < 4) {
                    return "Unavailable";
                }
                return matcher.group(1) + "\n" + matcher.group(2) + " " + matcher.group(3) + "\n" + matcher.group(4);
            } catch (Throwable th) {
                bufferedReader.close();
                throw th;
            }
        } catch (IOException unused) {
            return "Unavailable";
        }
    }

    private String formatSize(long j, String str) {
        return Utils.formatSize(j, str);
    }

    private void updateStatusNandflash() {
        String externalStorageState = Environment.getExternalStorageState();
        String string = "";
        if (externalStorageState.equals("mounted_ro")) {
            externalStorageState = "mounted";
            string = getResources().getString(R.string.read_only);
        }
        if (externalStorageState.equals("mounted")) {
            try {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                long blockSizeLong = statFs.getBlockSizeLong();
                long blockCountLong = statFs.getBlockCountLong();
                long availableBlocksLong = statFs.getAvailableBlocksLong();
                this.mNANDTotalSpace = formatSize(blockCountLong * blockSizeLong, "updateStatusNandflash mNANDTotalSpace");
                if (this.mNANDTotalSpace != null) {
                    Log.d("hjc", "mNANDTotalSpace:" + this.mNANDTotalSpace);
                    Log.d("hjc", "mNANDTotalSpace.substring:" + this.mNANDTotalSpace.substring(0, 4));
                    float f = Float.parseFloat(this.mNANDTotalSpace.substring(0, 3));
                    Log.d("hjc", "result:" + f);
                    double d = (double) f;
                    if (d < 2.5d && d > 0.5d) {
                        this.mNANDTotalSpace = "4 Gb";
                    } else if (d < 6.5d && d > 4.5d) {
                        this.mNANDTotalSpace = "8 Gb";
                    } else if (d < 14.5d && d > 12.5d) {
                        this.mNANDTotalSpace = "16 Gb";
                    } else if (d < 0.5d) {
                        this.mNANDTotalSpace = "2 Gb";
                    }
                }
                this.mNANDAvailableSpace = formatSize(availableBlocksLong * blockSizeLong, "updateStatusNandflash  mNANDAvailableSpace") + string;
                return;
            } catch (IllegalArgumentException unused) {
                this.mNANDTotalSpace = getResources().getString(R.string.status_unavailable);
                this.mNANDAvailableSpace = getResources().getString(R.string.status_unavailable);
                return;
            }
        }
        this.mNANDTotalSpace = getResources().getString(R.string.status_unavailable);
        this.mNANDAvailableSpace = getResources().getString(R.string.status_unavailable);
    }

    private String getMeminfoString() {
        long j;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"));
            while (true) {
                String line = bufferedReader.readLine();
                j = 0;
                if (line == null) {
                    break;
                }
                String strTrim = line.trim();
                if (!strTrim.equals("") && strTrim.contains("MemTotal:")) {
                    if (DEBUG) {
                        Log.d(TAG, "the line:" + strTrim);
                    }
                    String[] strArrSplit = strTrim.trim().split(":");
                    if (DEBUG) {
                        Log.d(TAG, "array.length=" + strArrSplit.length);
                    }
                    if (strArrSplit.length != 2) {
                        break;
                    }
                    String[] strArrSplit2 = strArrSplit[1].trim().split(" ");
                    if (strArrSplit2.length == 2 && strArrSplit2[0].trim() != null && !"".equals(strArrSplit2[0].trim())) {
                        if (DEBUG) {
                            Log.i(TAG, "the array2[0]=" + strArrSplit2[0].trim());
                        }
                        long j2 = Integer.parseInt(strArrSplit2[0].trim());
                        if (DEBUG) {
                            Log.i(TAG, "totalsize=" + j2);
                        }
                        j = j2;
                        break;
                    }
                    break;
                    break;
                    break;
                }
            }
            bufferedReader.close();
            int i = (int) (j >> 10);
            if (DEBUG) {
                Log.i(TAG, "totalRAM=" + i + " MB");
            }
            if (this.b) {
                if (i < 512 && i >= 0) {
                    return "512 MB";
                }
                if (i >= 512 && i < 1024) {
                    return "1.0 Gb";
                }
                if (i >= 1024 && i < 2048) {
                    return "2.0 Gb";
                }
                if (i >= 2048 && i <= 4096) {
                    return "4.0 Gb";
                }
                if (i >= 4096 && i < 8192) {
                    return "8.0 Gb";
                }
                if (i >= 8192 && i <= 16384) {
                    return "16.0 Gb";
                }
                if (i >= 16384 && i <= 32768) {
                    return "32.0 Gb";
                }
                if (i < 32768 || i > 65536) {
                    return i >= 65536 ? "128.0 Gb" : "8.0 Gb";
                }
                return "64.0 Gb";
            }
            if (i < 512 && i >= 0) {
                return "512 MB";
            }
            if (i >= 512 && i < 1024) {
                return "1.0 GB";
            }
            if (i >= 1024 && i < 2048) {
                return "2.0 GB";
            }
            if (i >= 2048 && i <= 4096) {
                return "4.0 GB";
            }
            if (i >= 4096 && i < 8192) {
                return "8.0 GB";
            }
            if (i >= 8192 && i <= 16384) {
                return "16.0 GB";
            }
            if (i >= 16384 && i <= 32768) {
                return "32.0 GB";
            }
            if (i < 32768 || i > 65536) {
                return i >= 65536 ? "128.0 GB" : "8.0 GB";
            }
            return "64.0 GB";
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return "8.0 Gb";
        }
    }

    private String getTotalROMSize() {
        long aDirSize = getADirSize("/data");
        long aDirSize2 = getADirSize("/system");
        long j = ((aDirSize + aDirSize2) + 0) >> 20;
        Log.e(TAG, "AllMemory=" + j);
        Log.e(TAG, "dataMemory=" + String.valueOf(aDirSize >> 20));
        Log.e(TAG, "systemMemory=" + String.valueOf(aDirSize2 >> 20));
        Log.e(TAG, "cacheMemory=" + String.valueOf(0L));
        if (this.b) {
            if (j < PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM && j >= 0) {
                return "4.0 Gb";
            }
            if (j > PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM && j <= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                return "8.0 Gb";
            }
            if (j > PlaybackStateCompat.ACTION_PLAY_FROM_URI && j <= 16324) {
                return "16.0 Gb";
            }
            if (j > 16324 && j <= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID) {
                return "32.0 Gb";
            }
            if (j > PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID && j <= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                return "64.0 Gb";
            }
            if (j > PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH && j <= PlaybackStateCompat.ACTION_PREPARE_FROM_URI) {
                return "128.0 Gb";
            }
            if (j <= PlaybackStateCompat.ACTION_PREPARE_FROM_URI || j > PlaybackStateCompat.ACTION_SET_REPEAT_MODE) {
                return j > PlaybackStateCompat.ACTION_SET_REPEAT_MODE ? "512.0 Gb" : "32.0 Gb";
            }
            return "256.0 Gb";
        }
        if (j <= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM && j >= 0) {
            return "4.0 GB";
        }
        if (j > PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM && j <= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
            return "8.0 GB";
        }
        if (j > PlaybackStateCompat.ACTION_PLAY_FROM_URI && j <= 16324) {
            return "16.0 GB";
        }
        if (j > 16324 && j <= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID) {
            return "32.0 GB";
        }
        if (j > PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID && j <= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
            return "64.0 GB";
        }
        if (j > PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH && j <= PlaybackStateCompat.ACTION_PREPARE_FROM_URI) {
            return "128.0 GB";
        }
        if (j <= PlaybackStateCompat.ACTION_PREPARE_FROM_URI || j > PlaybackStateCompat.ACTION_SET_REPEAT_MODE) {
            return j > PlaybackStateCompat.ACTION_SET_REPEAT_MODE ? "512.0 GB" : "32.0 GB";
        }
        return "256.0 GB";
    }

    private long getADirSize(String str) {
        StatFs statFs = new StatFs(new File(str).getPath());
        long blockSizeLong = statFs.getBlockSizeLong();
        long blockCountLong = statFs.getBlockCountLong();
        Log.e(TAG, "blockSize=" + blockSizeLong);
        Log.e(TAG, "totalBlocks=" + blockCountLong);
        return blockCountLong * blockSizeLong;
    }
}
