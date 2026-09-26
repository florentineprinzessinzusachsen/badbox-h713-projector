package com.rk_itvui.settings;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.rk_itvui.settings.screen.ScreenScaleActivity;
import com.rk_itvui.settings.screen.ScreenSetting;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class ScreensSettings extends FullScreenPreferenceActivity implements AdapterView.OnItemClickListener {
    public static final String KEY_DISPLAY_INTERFACE = "persist.sys.screen.interface";
    public static final String KEY_KEYSTONE = "persist.sys.screen.keystone";
    public static final String KEY_SCREEN_SCALE = "persist.sys.screen.scale";
    public static final String KEY_SOURCE_LIST = "persist.sys.screen.source";
    private static final String TAG = "ScreensSettings";
    private ScreenSetting displaysetting_local;
    private String hasHDMIValue;
    private String iface;
    SimpleAdapter listItemAdapter;
    private String setiface;
    private String setmode;
    private String size;
    private String thirdiface;
    private String vediomode;
    private Context mContext = this;
    private String resolution_mode = null;
    private int HDMI_TV = -1;
    HashMap<String, Object> map_dspscaleItem = new HashMap<>();
    HashMap<String, Object> map_dspmodeItem = new HashMap<>();
    HashMap<String, Object> map_xinhaoyuan = new HashMap<>();
    HashMap<String, Object> map_dspifaceiItem = new HashMap<>();
    HashMap<String, Object> map_dspvideoscale = new HashMap<>();
    HashMap<String, Object> map_tixingjiaozheng = new HashMap<>();
    HashMap<String, Object> map_shujubiaodinb = new HashMap<>();
    HashMap<String, Object> map_zidongrumu = new HashMap<>();
    HashMap<String, Object> map_zidongbizhang = new HashMap<>();
    HashMap<String, Object> map_displaymode = new HashMap<>();
    ArrayList<HashMap<String, Object>> listItem = new ArrayList<>();
    private String hdmiStr = "";
    private final Handler mHandler = new Handler() { // from class: com.rk_itvui.settings.ScreensSettings.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    Log.d("ScreenSettings", "Mode Resume");
                    ScreensSettings.this.setmode = ScreensSettings.this.displaysetting_local.getCurrentmode();
                    if (ScreensSettings.this.setiface.equals("TV")) {
                        if (ScreensSettings.this.setmode.equals("720x576i-50")) {
                            ScreensSettings.this.setmode = " PAL";
                        } else if (ScreensSettings.this.setmode.equals("720x480i-60")) {
                            ScreensSettings.this.setmode = " NTSC";
                        }
                    }
                    if (ScreensSettings.this.setmode.equals("0x0p-0") || ScreensSettings.this.setmode.equals("0x0i-0")) {
                        ScreensSettings.this.setmode = "auto";
                    }
                    ScreensSettings.this.map_dspmodeItem.put("ScreenSettingStatus", ScreensSettings.this.setmode);
                    ScreensSettings.this.listItemAdapter.notifyDataSetChanged();
                    ScreensSettings.this.vediomode = ScreensSettings.this.setmode;
                    break;
                case 1:
                    Log.d("ScreenSettings", "Inface Resume");
                    ScreensSettings.this.setiface = ScreensSettings.this.displaysetting_local.getIfaceTitle(ScreensSettings.this.displaysetting_local.getCurrentiface());
                    ScreensSettings.this.map_dspifaceiItem.put("ScreenSettingStatus", ScreensSettings.this.setiface);
                    if (!ScreensSettings.this.setiface.equals("HDMI")) {
                        if (ScreensSettings.this.setiface.equals("TV")) {
                            ScreensSettings.this.resolution_mode = ScreensSettings.this.mContext.getString(com.ashd.settings.R.string.TV_mode);
                            ScreensSettings.this.HDMI_TV = com.ashd.settings.R.drawable.dsp5;
                        }
                    } else {
                        ScreensSettings.this.resolution_mode = ScreensSettings.this.mContext.getString(com.ashd.settings.R.string.resolution);
                        ScreensSettings.this.HDMI_TV = com.ashd.settings.R.drawable.dsp3;
                    }
                    ScreensSettings.this.map_dspmodeItem.put("ScreenSettingItem", ScreensSettings.this.setiface + " " + ScreensSettings.this.resolution_mode);
                    ScreensSettings.this.map_dspmodeItem.put("ScreenSettingimg", Integer.valueOf(ScreensSettings.this.HDMI_TV));
                    ScreensSettings.this.listItemAdapter.notifyDataSetChanged();
                    break;
                case 2:
                    Log.d("ScreenSettings", "output Resume FULL");
                    ScreensSettings.this.map_dspvideoscale.put("ScreenSettingStatus", ScreensSettings.this.getString(com.ashd.settings.R.string.default_size));
                    ScreensSettings.this.listItemAdapter.notifyDataSetChanged();
                    break;
                case 3:
                    Log.d("ScreenSettings", "output Resume 1:1");
                    ScreensSettings.this.map_dspvideoscale.put("ScreenSettingStatus", ScreensSettings.this.getString(com.ashd.settings.R.string.origin_size));
                    ScreensSettings.this.listItemAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    private int[] mSaveIRData = new int[5];
    private boolean isAdd = false;

    @Override // com.rk_itvui.settings.FullScreenPreferenceActivity, android.preference.PreferenceActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(com.ashd.settings.R.layout.screen_settings);
        this.hasHDMIValue = SystemProperties.get("persist.sys.has.hdmi", "true");
        if (BuildConfig.FLAVOR.contains(BuildConfig.FLAVOR)) {
            TextView textView = (TextView) findViewById(com.ashd.settings.R.id.app_text);
            TextView textView2 = (TextView) findViewById(com.ashd.settings.R.id.settings_line);
            textView.setCompoundDrawablesWithIntrinsicBounds(getDrawable(com.ashd.settings.R.drawable.icon_ashd2), (Drawable) null, (Drawable) null, (Drawable) null);
            textView2.setBackgroundResource(com.ashd.settings.R.drawable.settings_head_line_ashd2);
        }
        addListView();
        if (this.hasHDMIValue.equals("true")) {
            this.displaysetting_local.getCurrentmode();
        }
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
        this.mSaveIRData[4] = i;
        Log.e(TAG, String.valueOf(this.mSaveIRData[0]));
        Log.e(TAG, String.valueOf(this.mSaveIRData[1]));
        Log.e(TAG, String.valueOf(this.mSaveIRData[2]));
        Log.e(TAG, String.valueOf(this.mSaveIRData[3]));
        Log.e(TAG, String.valueOf(this.mSaveIRData[4]));
        if (this.mSaveIRData[0] == 21 && this.mSaveIRData[1] == 21 && this.mSaveIRData[2] == 21 && this.mSaveIRData[3] == 21 && this.mSaveIRData[4] == 21 && !this.isAdd) {
            this.map_shujubiaodinb.put("ScreenSettingItem", getString(com.ashd.settings.R.string.str_shujubiaoqing));
            this.map_shujubiaodinb.put("ScreenSettingimg", Integer.valueOf(com.ashd.settings.R.drawable.ic_tixingjiaozheng));
            this.listItem.add(this.map_shujubiaodinb);
            this.listItemAdapter.notifyDataSetChanged();
            this.isAdd = true;
        }
        return false;
    }

    public void addListView() {
        if (this.hasHDMIValue.equals("true")) {
            try {
                this.displaysetting_local = new ScreenSetting(this.mContext, this.mHandler);
                this.vediomode = this.displaysetting_local.getCurrentmode();
                if ("0x0p-0".equals(this.vediomode) || "0x0i-0".equals(this.vediomode)) {
                    this.vediomode = "auto";
                }
                this.setmode = this.vediomode;
                this.iface = this.displaysetting_local.getIfaceTitle(this.displaysetting_local.getCurrentiface());
                this.setiface = this.iface;
                String string = null;
                int i = -1;
                if ("HDMI".equals(this.iface)) {
                    string = this.mContext.getString(com.ashd.settings.R.string.Resolution);
                    i = com.ashd.settings.R.drawable.dsp3;
                } else if ("TV".equals(this.iface)) {
                    string = this.mContext.getString(com.ashd.settings.R.string.TV_mode);
                    i = com.ashd.settings.R.drawable.dsp5;
                }
                if (this.displaysetting_local.getCurrentszie() == 0) {
                    this.size = getString(com.ashd.settings.R.string.default_size);
                } else {
                    this.size = getString(com.ashd.settings.R.string.origin_size);
                }
                Log.d("ScreenSettings", this.vediomode);
                this.hdmiStr = this.iface + " " + string;
                this.map_dspmodeItem.put("ScreenSettingItem", this.iface + " " + string);
                this.map_dspmodeItem.put("ScreenSettingStatus", this.vediomode);
                this.map_dspmodeItem.put("ScreenSettingimg", Integer.valueOf(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.map_dspscaleItem.put("ScreenSettingItem", getString(com.ashd.settings.R.string.display_scale));
        this.map_dspscaleItem.put("ScreenSettingimg", Integer.valueOf(com.ashd.settings.R.drawable.dsp1));
        this.map_dspscaleItem.put("ScreenSettingName", "map_dspscaleItem");
        this.map_dspifaceiItem.put("ScreenSettingItem", getString(com.ashd.settings.R.string.display_iface));
        this.map_dspifaceiItem.put("ScreenSettingStatus", this.iface);
        this.map_dspifaceiItem.put("ScreenSettingimg", Integer.valueOf(com.ashd.settings.R.drawable.dsp2));
        this.map_dspifaceiItem.put("ScreenSettingName", "map_dspifaceiItem");
        this.map_dspvideoscale.put("ScreenSettingItem", getString(com.ashd.settings.R.string.output_size));
        this.map_dspvideoscale.put("ScreenSettingStatus", this.size);
        this.map_dspvideoscale.put("ScreenSettingimg", Integer.valueOf(com.ashd.settings.R.drawable.dsp4));
        this.map_dspvideoscale.put("ScreenSettingName", "map_dspvideoscale");
        this.map_tixingjiaozheng.put("ScreenSettingItem", getString(com.ashd.settings.R.string.keystone_correction));
        this.map_tixingjiaozheng.put("ScreenSettingimg", Integer.valueOf(com.ashd.settings.R.drawable.ic_tixingjiaozheng));
        this.map_tixingjiaozheng.put("ScreenSettingName", "map_tixingjiaozheng");
        this.map_xinhaoyuan.put("ScreenSettingItem", getString(com.ashd.settings.R.string.source_list));
        this.map_xinhaoyuan.put("ScreenSettingimg", Integer.valueOf(com.ashd.settings.R.drawable.dsp1));
        this.map_xinhaoyuan.put("ScreenSettingName", "map_xinhaoyuan");
        this.map_zidongrumu.put("ScreenSettingItem", getString(com.ashd.settings.R.string.keystone_correction_auto_close));
        this.map_zidongrumu.put("ScreenSettingimg", Integer.valueOf(com.ashd.settings.R.drawable.ic_tixingjiaozheng));
        this.map_zidongrumu.put("ScreenSettingStatus", "开启");
        this.map_zidongrumu.put("ScreenSettingName", "map_zidongrumu");
        this.map_zidongbizhang.put("ScreenSettingItem", getString(com.ashd.settings.R.string.keystone_correction));
        this.map_zidongbizhang.put("ScreenSettingimg", Integer.valueOf(com.ashd.settings.R.drawable.ic_tixingjiaozheng));
        this.map_zidongbizhang.put("ScreenSettingStatus", "开启");
        this.map_zidongbizhang.put("ScreenSettingName", "map_zidongbizhang");
        this.map_displaymode.put("ScreenSettingItem", getString(com.ashd.settings.R.string.display_name));
        this.map_displaymode.put("ScreenSettingimg", Integer.valueOf(com.ashd.settings.R.drawable.dsp1));
        this.map_displaymode.put("ScreenSettingName", "map_displaymode");
        if (SystemProperties.getBoolean(KEY_SOURCE_LIST, false)) {
            this.listItem.add(this.map_xinhaoyuan);
        }
        if (this.hasHDMIValue.equals("true")) {
            this.listItem.add(this.map_dspmodeItem);
            this.listItem.add(this.map_dspvideoscale);
        }
        if (SystemProperties.getBoolean(KEY_KEYSTONE, true)) {
            this.listItem.add(this.map_tixingjiaozheng);
        }
        ListView listView = (ListView) findViewById(com.ashd.settings.R.id.display_list);
        this.listItemAdapter = new SimpleAdapter(this, this.listItem, com.ashd.settings.R.layout.screen_item, new String[]{"ScreenSettingItem", "ScreenSettingStatus", "ScreenSettingimg"}, new int[]{com.ashd.settings.R.id.ScreenSettingItem, com.ashd.settings.R.id.ScreenSettingStatus, com.ashd.settings.R.id.ScreenSettingimg});
        listView.setAdapter((ListAdapter) this.listItemAdapter);
        listView.setOnItemClickListener(this);
    }

    public boolean setGenserData(String str) {
        try {
            Object systemService = getSystemService("ashd_securefile");
            Method method = com.rk_itvui.utils.ReflectionUtils.getMethod("android.os.SecureFileManager", "setParameter", String.class, String.class);
            if (method == null) {
                LogUtils.LOGD("SecurityManager", "setParameter  method null");
            } else if (systemService != null) {
                LogUtils.LOGD("SecurityManager", "invoke  setParameter gsensor = NULL ");
                method.invoke(systemService, "gsensor", "null");
            } else {
                LogUtils.LOGD("SecurityManager", "mSecurerileservice  == null ");
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.LOGD("SecurityManager", e.getLocalizedMessage());
            return false;
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        String str = (String) this.listItem.get(i).get("ScreenSettingName");
        LogUtils.LOGD(TAG, "itemStr=" + str);
        if (str.equals("map_dspvideoscale")) {
            startActivity(new Intent(this, (Class<?>) ScreenScaleActivity.class));
            return;
        }
        if (str.equals("map_dspmodeItem")) {
            if (this.displaysetting_local != null) {
                this.displaysetting_local.InitModeDialog(this.mContext);
                this.displaysetting_local.mModeDialog.show();
                return;
            }
            return;
        }
        if (str.equals("map_displaymode")) {
            try {
                Intent intent = new Intent();
                if (checkApplication("com.ashd.picturemode")) {
                    intent.setClassName("com.ashd.picturemode", "com.softwinner.tv.factorymenu.MainActivity");
                    startActivity(intent);
                } else {
                    Toast.makeText(this, com.ashd.settings.R.string.battery_info_health_unspecified_failure, 0).show();
                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, com.ashd.settings.R.string.battery_info_health_unspecified_failure, 0).show();
                return;
            }
        }
        if (str.equals("map_tixingjiaozheng")) {
            try {
                Intent intent2 = new Intent();
                if (checkApplication("ashd.rockchip.keystone")) {
                    intent2.setClassName("ashd.rockchip.keystone", "ashd.rockchip.keystone.MainActivity");
                    startActivity(intent2);
                } else if (checkApplication("com.telanda.keystone")) {
                    startActivity(getPackageManager().getLaunchIntentForPackage("com.telanda.keystone"));
                } else {
                    intent2.setClassName("com.rockchip.keystone", "com.rockchip.keystone.MainActivity");
                    startActivity(intent2);
                }
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
                Toast.makeText(this, com.ashd.settings.R.string.battery_info_health_unspecified_failure, 0).show();
                return;
            }
        }
        if (str.equals("map_xinhaoyuan")) {
            try {
                Intent intent3 = new Intent();
                if (checkApplication("com.hisilicon.tvsetting")) {
                    intent3.setClassName("com.hisilicon.tvsetting", "com.hisilicon.tvsetting.main.QuickSourceActivity");
                    startActivity(intent3);
                    return;
                }
                return;
            } catch (Exception e3) {
                e3.printStackTrace();
                Toast.makeText(this.mContext, com.ashd.settings.R.string.battery_info_health_unspecified_failure, 0).show();
                return;
            }
        }
        if (str.equals(getString(com.ashd.settings.R.string.str_shujubiaoqing))) {
            try {
                setGenserData("");
                Intent intent4 = new Intent();
                if (checkApplication("com.ypfun.focus")) {
                    intent4.setClassName("com.ypfun.focus", "com.ypfun.autofocus.module.activity.FocusActivity");
                    startActivity(intent4);
                } else {
                    Toast.makeText(this.mContext, com.ashd.settings.R.string.battery_info_health_unspecified_failure, 0).show();
                }
            } catch (Exception e4) {
                e4.printStackTrace();
                Toast.makeText(this.mContext, com.ashd.settings.R.string.battery_info_health_unspecified_failure, 0).show();
            }
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
}
