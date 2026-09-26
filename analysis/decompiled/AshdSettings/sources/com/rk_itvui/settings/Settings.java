package com.rk_itvui.settings;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.hardware.audio.common.V2_0.AudioFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemProperties;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.android.internal.widget.ViewPager;
import com.android.settingslib.accessibility.AccessibilityUtils;
import com.rk_itvui.settings.adapter.GridViewAdapter;
import com.rk_itvui.settings.bluetooth.BluetoothPairingDialogService;
import com.rk_itvui.settings.bluetooth.BluetoothSettingActivity;
import com.rk_itvui.settings.datetime.DateTimeSetting;
import com.rk_itvui.settings.developer.DevelopmentSettings;
import com.rk_itvui.settings.deviceversion.Deviceversion;
import com.rk_itvui.settings.language.LanguageInputmethod;
import com.rk_itvui.settings.sound.SoundSetting;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class Settings extends Activity {
    private static final int APP_MANERGE = 6;
    private static final int BT_SETTINGS = 1;
    public static final boolean DEFAULT_IS_HIDE_DEVICE_INFO = false;
    private static final int DEVICES_MESSAGE = 7;
    private static final int DISPLAYS_SETTINGS = 4;
    private static final int LANGRAGE_SETTINGS = 3;
    private static final int NETWORK_SETTINGS = 0;
    private static final int SOUND_SETTINGS = 2;
    private static final String TAG = "SettingsActivity";
    private static final int TIME_AND_DATE = 5;
    private static final int UPDATA_UI = 0;
    private static ArrayList<SettingItems> mItemInformation;
    boolean bluetoothIsOn;
    private TvGridView grid_view;
    ImageView img_back;
    private String[] items;
    private BluetoothAdapter mBluetoothAdapter;
    private long mCurrentFocusPosition;
    private final SettingsGridViewAdpter.OnItemClickListener mOnItemClickListener;
    private final int COUNT_PER_PAGE = 8;
    private int focusposition = -1;
    private final TextView[] mLayoutItems = new TextView[8];
    private final Integer[] mBackground = {Integer.valueOf(com.ashd.settings.R.drawable.net_set), Integer.valueOf(com.ashd.settings.R.drawable.time_set), Integer.valueOf(com.ashd.settings.R.drawable.sound_set), Integer.valueOf(com.ashd.settings.R.drawable.display_set), Integer.valueOf(com.ashd.settings.R.drawable.language_set), Integer.valueOf(com.ashd.settings.R.drawable.ab_set), Integer.valueOf(com.ashd.settings.R.drawable.app_set), Integer.valueOf(com.ashd.settings.R.drawable.develop_set)};
    private final Integer[] mBackground_ashd2 = {Integer.valueOf(com.ashd.settings.R.drawable.net_set_ashd2), Integer.valueOf(com.ashd.settings.R.drawable.time_set_ashd2), Integer.valueOf(com.ashd.settings.R.drawable.sound_set_ashd2), Integer.valueOf(com.ashd.settings.R.drawable.display_set_ashd2), Integer.valueOf(com.ashd.settings.R.drawable.language_set_ashd2), Integer.valueOf(com.ashd.settings.R.drawable.ab_set_ashd2), Integer.valueOf(com.ashd.settings.R.drawable.app_set_ashd2), Integer.valueOf(com.ashd.settings.R.drawable.develop_set_ashd2)};
    private final Integer[] mBackground_ashd3 = {Integer.valueOf(com.ashd.settings.R.drawable.ashd3_wifi), Integer.valueOf(com.ashd.settings.R.drawable.ashd3_bluetooth), Integer.valueOf(com.ashd.settings.R.drawable.ashd3_voice), Integer.valueOf(com.ashd.settings.R.drawable.ashd3_language), Integer.valueOf(com.ashd.settings.R.drawable.ashd3_keystone), Integer.valueOf(com.ashd.settings.R.drawable.ashd3_date), Integer.valueOf(com.ashd.settings.R.drawable.ashd3_settings), Integer.valueOf(com.ashd.settings.R.drawable.ashd3_about_us)};
    String devicesInfo = SystemProperties.get("persist.sys.wifitype", "unkown");

    public Settings() {
        this.bluetoothIsOn = SystemProperties.getInt("persist.sys.settings.bluetooth", 2) != 0;
        this.mOnItemClickListener = new SettingsGridViewAdpter.OnItemClickListener() { // from class: com.rk_itvui.settings.Settings.5
            @Override // com.rk_itvui.settings.SettingsGridViewAdpter.OnItemClickListener
            public void onItemClick(ViewPager viewPager, View view, int i) {
                Log.i("test", "onItemClick");
                Settings.this.startItems(i);
            }
        };
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        Log.d(TAG, "==================================Activity:onCreate");
        if (checkSHA1()) {
            Log.d("Settings", "签名校验成功");
            super.onCreate(bundle);
            requestWindowFeature(1);
            getWindow().setFlags(1024, 1024);
            getWindow().setFlags(128, 128);
            setContentView(com.ashd.settings.R.layout.settings_activity);
            if (BuildConfig.FLAVOR.contains(BuildConfig.FLAVOR)) {
                TextView textView = (TextView) findViewById(com.ashd.settings.R.id.app_text);
                TextView textView2 = (TextView) findViewById(com.ashd.settings.R.id.settings_line);
                textView.setCompoundDrawablesWithIntrinsicBounds(getDrawable(com.ashd.settings.R.drawable.icon_ashd2), (Drawable) null, (Drawable) null, (Drawable) null);
                textView2.setBackgroundResource(com.ashd.settings.R.drawable.settings_head_line_ashd2);
            }
            boolean z = SystemProperties.getBoolean("persist.ashd.bt.showback", true);
            this.img_back = (ImageView) findViewById(com.ashd.settings.R.id.img_back);
            if (z) {
                this.img_back.setVisibility(0);
                this.img_back.setOnClickListener(new View.OnClickListener() { // from class: com.rk_itvui.settings.Settings.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        Settings.this.onBackPressed();
                    }
                });
            } else {
                this.img_back.setVisibility(4);
            }
            mItemInformation = new ArrayList<>();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            ScreenInfo.DENSITY = displayMetrics.densityDpi;
            ScreenInfo.WIDTH = displayMetrics.widthPixels;
            ScreenInfo.HEIGHT = displayMetrics.heightPixels;
            Log.e(TAG, ScreenInfo.WIDTH + ":" + ScreenInfo.HEIGHT + ":" + ScreenInfo.DENSITY);
            ScreenInformation.mScreenWidth = displayMetrics.widthPixels;
            ScreenInformation.mScreenHeight = displayMetrics.heightPixels;
            ScreenInformation.mDensityDpi = displayMetrics.densityDpi;
            ScreenInformation.mDpiRatio = ((float) ScreenInformation.mDefaultDpi) / ((float) displayMetrics.densityDpi);
            Log.d("Settings", "ScreenInformation.mScreenWidth=" + ScreenInformation.mScreenWidth + ":mScreenHeight=" + ScreenInformation.mScreenHeight);
            String[] stringArray = getResources().getStringArray(com.ashd.settings.R.array.settings_items);
            this.items = new String[8];
            this.items[0] = stringArray[0];
            this.items[1] = stringArray[1];
            this.items[2] = stringArray[2];
            this.items[3] = stringArray[3];
            this.items[4] = stringArray[4];
            this.items[5] = stringArray[5];
            this.items[6] = stringArray[6];
            this.items[7] = stringArray[7];
            this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            this.bluetoothIsOn = this.bluetoothIsOn && ("RTL8723BU".equals(this.devicesInfo) || "AP6330".equals(this.devicesInfo) || "RTL8723BS".equals(this.devicesInfo) || "RTL8723DU".equals(this.devicesInfo) || "RTL8723DS".equals(this.devicesInfo) || "LG642".equals(this.devicesInfo));
            if (this.mBluetoothAdapter.isEnabled()) {
                Intent intent = new Intent(this, (Class<?>) BluetoothPairingDialogService.class);
                intent.setAction("android.intent.action.RESPOND_VIA_MESSAGE");
                startService(intent);
            }
            this.grid_view = (TvGridView) findViewById(com.ashd.settings.R.id.grid_view);
            this.grid_view.bringToFront();
            this.grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.rk_itvui.settings.Settings.2
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    Settings.this.startItems(i);
                }
            });
            this.grid_view.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.rk_itvui.settings.Settings.3
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            GridViewAdapter gridViewAdapter = new GridViewAdapter(BuildConfig.FLAVOR.equals(BuildConfig.FLAVOR) ? this.mBackground_ashd3 : this.mBackground, this.items);
            this.grid_view.setAdapter((ListAdapter) gridViewAdapter);
            gridViewAdapter.notifyDataSetChanged();
            this.grid_view.setFocusable(true);
            this.grid_view.setSelection(0);
            this.grid_view.setBackgroundResource(com.ashd.settings.R.drawable.list_selector);
            try {
                HelpUtils.copyFile(new File("/data/misc/bluedroid/bt_config.conf"), new File("/sdcard/bt_config.conf"));
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d("Settings", "签名校验失败，关闭应用");
        finish();
        throw new RuntimeException("Error");
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideSystemUI() {
        Log.d(TAG, "*********hideSystemUI************");
        getWindow().setFlags(AudioFormat.OPUS, AudioFormat.OPUS);
        getWindow().setFlags(67108864, 67108864);
        getWindow().getDecorView().setSystemUiVisibility(3846);
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.rk_itvui.settings.Settings.4
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public void onSystemUiVisibilityChange(int i) {
                Log.d(Settings.TAG, "**********onSystemUiVisibilityChange**********");
                new Handler().postDelayed(new Runnable() { // from class: com.rk_itvui.settings.Settings.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Settings.this.hideSystemUI();
                    }
                }, 1000L);
            }
        });
    }

    @Override // android.app.Activity
    public void onResume() {
        Log.d(TAG, "==================================Activity:onResume");
        hideSystemUI();
        super.onResume();
    }

    void startItems(int i) {
        switch (i) {
            case 0:
                startActivity(new Intent(this, (Class<?>) network_settingnew.class));
                break;
            case 1:
                startActivity(new Intent(this, (Class<?>) BluetoothSettingActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, (Class<?>) SoundSetting.class));
                break;
            case 3:
                startActivity(new Intent(this, (Class<?>) LanguageInputmethod.class));
                break;
            case 4:
                try {
                    Intent intent = new Intent();
                    if (checkApplication("ashd.rockchip.keystone")) {
                        intent.setClassName("ashd.rockchip.keystone", "ashd.rockchip.keystone.MainActivity");
                        startActivity(intent);
                    } else if (checkApplication("com.telanda.keystone")) {
                        startActivity(getPackageManager().getLaunchIntentForPackage("com.telanda.keystone"));
                    } else {
                        intent.setClassName("com.rockchip.keystone", "com.rockchip.keystone.MainActivity");
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, com.ashd.settings.R.string.battery_info_health_unspecified_failure, 0).show();
                    return;
                }
                break;
            case 5:
                startActivity(new Intent(this, (Class<?>) DateTimeSetting.class));
                break;
            case 6:
                startActivity(new Intent(this, (Class<?>) DevelopmentSettings.class));
                break;
            case 7:
                startActivity(new Intent(this, (Class<?>) Deviceversion.class));
                break;
        }
    }

    public boolean checkApplication(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        try {
            getPackageManager().getApplicationInfo(str, 8192);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public void getSettingsItems() {
        mItemInformation.clear();
        for (String str : this.items) {
            SettingItems settingItems = new SettingItems();
            if (str == null) {
                return;
            }
            settingItems.setItemName(str);
            mItemInformation.add(settingItems);
        }
    }

    private void showLooseFocusAinimation(View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.2f, 1.0f, 1.2f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(100L);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setRepeatCount(0);
        view.startAnimation(scaleAnimation);
    }

    private void showOnFocusAnimation(View view) {
        view.bringToFront();
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(100L);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setRepeatCount(0);
        view.startAnimation(scaleAnimation);
    }

    private boolean checkSHA1() {
        PackageInfo packageInfo;
        String appSignSha1 = getAppSignSha1(this);
        Log.e("checkSHA1", "checkSt=" + appSignSha1);
        if (appSignSha1 == null) {
            return false;
        }
        if (!((appSignSha1.equals("CD:B1:B7:56:85:FE:16:C2:7E:4C:45:59:54:43:AC:B0:1F:68:40:91") || appSignSha1.equals("60:35:FD:9E:68:E9:20:C2:B5:B0:7C:F2:B5:10:F2:83:47:06:DE:15") || appSignSha1.equals("6E:40:BA:7F:F7:90:7D:40:C2:F1:6D:12:E4:63:E5:E4:A3:F1:5A:01") || appSignSha1.equals("27:19:6E:38:6B:87:5E:76:AD:F7:00:E7:EA:84:E4:C6:EE:E3:3D:FA")) ? true : appSignSha1.equals("F6:08:8C:ED:B9:9A:EC:82:A7:7D:F3:F9:01:97:06:BE:C0:65:E4:9A"))) {
            return false;
        }
        PackageManager packageManager = getPackageManager();
        try {
            try {
                return packageManager.getPackageInfo("com.android.umanalytics.yiyou", 0) != null;
            } catch (PackageManager.NameNotFoundException unused) {
                try {
                    packageInfo = packageManager.getPackageInfo("com.android.umanalytics.kege", 0);
                } catch (PackageManager.NameNotFoundException unused2) {
                    packageInfo = null;
                }
                return packageInfo != null;
            }
        } catch (Throwable unused3) {
            return false;
        }
    }

    private static String byte2HexFormatted(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase());
            if (i < bArr.length - 1) {
                sb.append(AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR);
            }
        }
        return sb.toString();
    }

    public static String getAppSignSha1(Context context) {
        try {
            return byte2HexFormatted(MessageDigest.getInstance("SHA1").digest(((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray()))).getEncoded()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
