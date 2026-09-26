package com.rk_itvui.settings.developer;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import com.ashd.settings.R;
import com.rk_itvui.settings.BuildConfig;
import com.rk_itvui.settings.FullScreenActivity;
import com.rk_itvui.settings.ScreenInformation;
import com.rk_itvui.settings.deviceinfo.UsbMode;
import com.rk_itvui.settings.deviceinfo.UsbSettings;
import com.rk_itvui.settings.dialog.EventNotificationSetting;
import com.rk_itvui.settings.dialog.ManageApplications;
import com.rk_itvui.settings.dialog.UnknownSources;
import com.rk_itvui.settings.dialog.UsbDebugging;
import com.rk_itvui.settings.dialog.UsbModeSettings;
import com.rk_itvui.settings.factoryreset.Factoryreset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class DevelopmentSettings extends FullScreenActivity {
    private static final String SLEEP_TIMEOUT_INDEX = "sleep.timeout.index";
    int[] auto_sleep_time_value;
    private String mSocName;
    private String TAG = "DevelopmentSettings";
    private ListView mListView = null;
    private UsbMode mUsbMode = null;
    private SettingItemAddManager mSettingItemManager = null;
    private Map<String, ArrayList<SettingItem>> mMap = new HashMap();
    private ListViewAdapter mListViewAdapter = null;
    UsbModeSettings mUsbModeSetting = null;
    EventNotificationSetting mEventNotification = null;
    UnknownSources mUnknownSources = null;
    UsbDebugging mUsbDebugging = null;
    int AutoSleepIndex = 0;
    private final TabHost.TabContentFactory mContentFactory = new TabHost.TabContentFactory() { // from class: com.rk_itvui.settings.developer.DevelopmentSettings.2
        @Override // android.widget.TabHost.TabContentFactory
        public View createTabContent(String str) {
            return DevelopmentSettings.this.mListView == null ? DevelopmentSettings.this.createListView() : DevelopmentSettings.this.mListView;
        }
    };
    private final AdapterView.OnItemClickListener mListItemClister = new AdapterView.OnItemClickListener() { // from class: com.rk_itvui.settings.developer.DevelopmentSettings.3
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            DevelopmentSettings.this.LOGD("view's tag = " + view.getTag() + ",position = " + i + ",arg3 = " + j);
            int iIntValue = ((Integer) view.getTag()).intValue();
            if (DevelopmentSettings.this.haveChild(DevelopmentSettings.this.mListViewAdapter.getContent(), iIntValue) || DevelopmentSettings.this.settingItemClick(iIntValue)) {
                return;
            }
            DevelopmentSettings.this.onSystemItemClick(view, i);
        }
    };
    private final AdapterView.OnItemLongClickListener mLongListItemClickListener = new AdapterView.OnItemLongClickListener() { // from class: com.rk_itvui.settings.developer.DevelopmentSettings.4
        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            int iIntValue = ((Integer) view.getTag()).intValue();
            if (DevelopmentSettings.this.haveChild(DevelopmentSettings.this.mListViewAdapter.getContent(), iIntValue)) {
                return true;
            }
            DevelopmentSettings.this.settingItemLongClick(iIntValue);
            return true;
        }
    };
    private final Handler mHandler = new Handler() { // from class: com.rk_itvui.settings.developer.DevelopmentSettings.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            DevelopmentSettings.this.LOGD("mHandler,handleMessage() msg.what = " + message.what);
            int i = message.what;
            if (i != 4) {
                switch (i) {
                    case 0:
                        if (DevelopmentSettings.this.mListViewAdapter != null) {
                            DevelopmentSettings.this.mListViewAdapter.invalidate();
                        }
                        break;
                    case 1:
                        DevelopmentSettings.this.setSettingItemClickable(message.arg1, message.arg2 == 1);
                        if (DevelopmentSettings.this.mListViewAdapter != null) {
                            DevelopmentSettings.this.mListViewAdapter.invalidate();
                        }
                        break;
                    case 2:
                        DevelopmentSettings.this.setSettingItemView(message.arg1, (View) message.obj);
                        if (DevelopmentSettings.this.mListViewAdapter != null) {
                            DevelopmentSettings.this.mListViewAdapter.invalidate();
                        }
                        break;
                }
            }
            DevelopmentSettings.this.setSettingItemCallBackFunction(message.arg1, (SettingItemClick) message.obj);
        }
    };

    @SuppressLint({"NewApi"})
    private void getScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        ScreenInformation.mScreenWidth = displayMetrics.widthPixels;
        ScreenInformation.mScreenHeight = displayMetrics.heightPixels;
        ScreenInformation.mDensityDpi = displayMetrics.densityDpi;
        ScreenInformation.mDpiRatio = ScreenInformation.mDefaultDpi / displayMetrics.densityDpi;
    }

    private void createSpace() {
        ((TextView) findViewById(R.id.bottom_space)).setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (ScreenInformation.mScreenWidth / 20.0f)));
    }

    @Override // com.rk_itvui.settings.FullScreenActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        int i;
        int i2;
        super.onCreate(bundle);
        setContentView(R.layout.developmentsetting);
        this.auto_sleep_time_value = getResources().getIntArray(R.array.auto_sleep_time_value);
        this.AutoSleepIndex = getSleepTime();
        if (BuildConfig.FLAVOR.contains(BuildConfig.FLAVOR)) {
            TextView textView = (TextView) findViewById(R.id.app_text);
            TextView textView2 = (TextView) findViewById(R.id.settings_line);
            textView.setCompoundDrawablesWithIntrinsicBounds(getDrawable(R.drawable.icon_ashd2), (Drawable) null, (Drawable) null, (Drawable) null);
            textView2.setBackgroundResource(R.drawable.settings_head_line_ashd2);
        }
        getScreenSize();
        createSpace();
        if (this.mListView == null) {
            createListView();
        }
        int i3 = 0;
        if (bundle != null) {
            int i4 = bundle.getInt("Level");
            int i5 = bundle.getInt("Indicator");
            int i6 = bundle.getInt("Parent");
            LOGD("onCreate(), level = " + i4 + ", indicator = " + i5 + ",parentId = " + i6);
            i = i6;
            i2 = i4;
            i3 = i5;
        } else {
            i = -1;
            i2 = 0;
        }
        setTabHostFocus(i3, i2, i);
        initContentStatus();
        this.mSettingItemManager = SettingItemAddManager.getInstance();
        this.mSettingItemManager.setContentMap(this.mMap);
        if ("release".equals("yiyou")) {
            ((TextView) findViewById(R.id.path)).setText(R.string.reset_upgrade);
        }
    }

    private void setTabHostFocus(int i, int i2, int i3) {
        this.mListViewAdapter.setLevel(i2);
        this.mListViewAdapter.setParentId(i3);
        this.mListViewAdapter.setSelection(i);
        this.mListViewAdapter.invalidate();
        this.mListView.requestFocus();
        this.mListView.setSelection(0);
        this.mListView.invalidate();
    }

    private void getEventNotificationDefault() {
        this.mEventNotification = new EventNotificationSetting(this, this.mHandler, this.mListViewAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUsbModeSettingDefault() {
        if (TextUtils.isEmpty(this.mSocName) || this.mSocName.contains("rk3399")) {
            return;
        }
        this.mUsbModeSetting = new UsbModeSettings(this, this.mHandler, this.mListViewAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUnknownSourcesDefault() {
        this.mUnknownSources = new UnknownSources(this, this.mHandler, this.mListViewAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUsbDebuggingDefault() {
        this.mUsbDebugging = new UsbDebugging(this, this.mHandler, this.mListViewAdapter);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.rk_itvui.settings.developer.DevelopmentSettings$1] */
    @SuppressLint({"NewApi"})
    private void initContentStatus() {
        Log.d(this.TAG, "OnCreate:initContentStatus()==================");
        new AsyncTask<Void, Void, Void>() { // from class: com.rk_itvui.settings.developer.DevelopmentSettings.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                DevelopmentSettings.this.mSocName = SystemProperties.get("sys.rk.soc");
                if (TextUtils.isEmpty(DevelopmentSettings.this.mSocName)) {
                    DevelopmentSettings.this.mSocName = SystemProperties.get("ro.board.platform");
                }
                DevelopmentSettings.this.loadSystemResource();
                DevelopmentSettings.this.getUsbModeSettingDefault();
                DevelopmentSettings.this.getUnknownSourcesDefault();
                DevelopmentSettings.this.getUsbDebuggingDefault();
                DevelopmentSettings.this.mHandler.sendEmptyMessageDelayed(0, 10L);
                return null;
            }
        }.execute(new Void[0]);
    }

    private void UsbModeSettingsInit() {
        this.mUsbMode = new UsbMode(this, this.mHandler);
        this.mUsbMode.Resume();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View createListView() {
        this.mListView = (ListView) findViewById(R.id.tabconent_list);
        this.mListViewAdapter = new ListViewAdapter(this, this.mMap);
        this.mListView.setAdapter((ListAdapter) this.mListViewAdapter);
        this.mListView.setOnItemClickListener(this.mListItemClister);
        this.mListView.setOnItemLongClickListener(this.mLongListItemClickListener);
        return this.mListView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadSystemResource() {
        SettingItem[] settingItemArr = {new SettingItem(0, -1, R.string.display_name, null, null, BitmapFactory.decodeResource(getResources(), R.drawable.ic_display)), new SettingItem(0, -1, R.string.application_manage, null, null, BitmapFactory.decodeResource(getResources(), R.drawable.upgrade_icon)), new SettingItem(0, -1, R.string.master_clear_title, null, null, BitmapFactory.decodeResource(getResources(), R.drawable.advance)), new SettingItem(0, -1, R.string.sys_update, null, null, BitmapFactory.decodeResource(getResources(), R.drawable.upgrade_icon))};
        if (this.mMap != null) {
            ArrayList<SettingItem> arrayList = new ArrayList<>();
            Collections.addAll(arrayList, settingItemArr);
            if (!TextUtils.isEmpty(this.mSocName) && this.mSocName.contains("rk3399")) {
                arrayList.remove(0);
            }
            this.mMap.put("development", arrayList);
        }
    }

    private int getSleepTime() {
        int i = Settings.System.getInt(getContentResolver(), "setting_sleep_mode_time", 60);
        LOGD("getSleepTime=" + i);
        for (int i2 = 0; i2 < this.auto_sleep_time_value.length; i2++) {
            if (i == this.auto_sleep_time_value[i2]) {
                return i2;
            }
        }
        return 0;
    }

    private void setSleepTime(int i) {
        Settings.System.putInt(getContentResolver(), "setting_sleep_mode_time", i);
    }

    private void showToast(String str) {
        Toast.makeText(getApplicationContext(), str, 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean settingItemClick(int i) {
        SettingItem settingItemFindSettingItem = findSettingItem("development", i);
        if (settingItemFindSettingItem != null) {
            return settingItemFindSettingItem.onSettingItemClick(i);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean settingItemLongClick(int i) {
        SettingItem settingItemFindSettingItem = findSettingItem("development", i);
        if (settingItemFindSettingItem != null) {
            return settingItemFindSettingItem.onSettingItemLongClick(i);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean haveChild(String str, int i) {
        ArrayList<SettingItem> arrayList;
        LOGD("havaChild(),name = " + str + ",id = " + i);
        if (this.mMap != null && str != null && (arrayList = this.mMap.get(str)) != null) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                SettingItem settingItem = arrayList.get(i2);
                if (settingItem.mParentId == i) {
                    this.mListViewAdapter.setParentId(i);
                    this.mListViewAdapter.setLevel(settingItem.mLevel);
                    this.mListViewAdapter.invalidate();
                    return true;
                }
            }
        }
        return false;
    }

    private SettingItem findParent(String str, int i) {
        ArrayList<SettingItem> arrayList;
        if (this.mMap == null || str == null || (arrayList = this.mMap.get(str)) == null) {
            return null;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            SettingItem settingItem = arrayList.get(i2);
            if (settingItem.mId == i) {
                return settingItem;
            }
        }
        return null;
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

    /* JADX INFO: Access modifiers changed from: private */
    public void onSystemItemClick(View view, int i) {
        if (view == null) {
            return;
        }
        int iIntValue = ((Integer) view.getTag()).intValue();
        if (iIntValue == R.string.usb_setting) {
            this.mUsbModeSetting.onUsbModeClick();
        }
        if (iIntValue == R.string.display_name) {
            try {
                Intent intent = new Intent();
                if (checkApplication("com.softwinner.tv.factorymenu")) {
                    intent.setClassName("com.softwinner.tv.factorymenu", "com.softwinner.tv.factorymenu.MainActivity");
                    startActivity(intent);
                } else {
                    Toast.makeText(this, R.string.battery_info_health_unspecified_failure, 0).show();
                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, R.string.battery_info_health_unspecified_failure, 0).show();
                return;
            }
        }
        if (iIntValue == R.string.event_notification) {
            this.mEventNotification.onEventNotificationClick();
            return;
        }
        if (iIntValue == R.string.unknown_sources) {
            this.mUnknownSources.SourcesUnknown();
            return;
        }
        if (iIntValue == R.string.usb_debugging) {
            this.mUsbDebugging.DebuggingUsb();
            return;
        }
        if (iIntValue == R.string.storage_menu_usb) {
            StartActivityForResultSafely(new Intent(this, (Class<?>) UsbSettings.class), 1002);
            return;
        }
        if (iIntValue == R.string.more_settings) {
            Intent intent2 = new Intent("android.settings.SETTINGS");
            intent2.setPackage("com.android.tv.settings");
            if (intent2.resolveActivity(getPackageManager()) != null) {
                startActivity(intent2);
                return;
            }
            return;
        }
        if (iIntValue == R.string.master_clear_title) {
            startActivity(new Intent(this, (Class<?>) Factoryreset.class));
            return;
        }
        if (iIntValue == R.string.application_manage) {
            Intent intent3 = new Intent("android.intent.action.VIEW");
            intent3.setClass(this, ManageApplications.class);
            startActivity(intent3);
        } else if (iIntValue == R.string.sys_update) {
            try {
                ComponentName componentName = new ComponentName("com.android.sysapp", "com.android.sysapp.UpdateActivity");
                Intent intent4 = new Intent();
                intent4.setComponent(componentName);
                startActivity(intent4);
            } catch (Exception unused) {
                Toast.makeText(this, R.string.no_update_msg, 0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void LOGD(String str) {
        Log.d(this.TAG, str);
    }

    public SettingItem findSettingItem(String str, int i) {
        ArrayList<SettingItem> arrayList;
        if (this.mMap != null && (arrayList = this.mMap.get(str)) != null) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                SettingItem settingItem = arrayList.get(i2);
                if (settingItem.mId == i) {
                    return settingItem;
                }
            }
        }
        return null;
    }

    public void updateSettingItem(int i, String str, String str2, Bitmap bitmap) {
        SettingItem settingItemFindSettingItem = findSettingItem("development", i);
        if (settingItemFindSettingItem != null) {
            if (str != null) {
                settingItemFindSettingItem.setStatus(str);
            }
            if (str2 != null) {
                settingItemFindSettingItem.setSummary(str2);
            }
            if (bitmap != null) {
                settingItemFindSettingItem.setDrawable(bitmap);
            }
        }
    }

    public void updateSettingItem(int i, int i2, int i3, int i4) {
        updateSettingItem(i, i2 != -1 ? getResources().getString(i2) : null, i3 != -1 ? getResources().getString(i3) : null, i4 != -1 ? BitmapFactory.decodeResource(getResources(), i4) : null);
    }

    public void setSettingItemView(int i, View view) {
        SettingItem settingItemFindSettingItem;
        if (this.mMap == null || (settingItemFindSettingItem = findSettingItem("development", i)) == null) {
            return;
        }
        settingItemFindSettingItem.setView(view);
    }

    public boolean setSettingItemClickable(int i, boolean z) {
        SettingItem settingItemFindSettingItem;
        if (this.mMap == null || (settingItemFindSettingItem = findSettingItem("development", i)) == null) {
            return false;
        }
        settingItemFindSettingItem.setClickable(z);
        return true;
    }

    public void setSettingItemCallBackFunction(int i, SettingItemClick settingItemClick) {
        SettingItem settingItemFindSettingItem;
        if (this.mMap == null || (settingItemFindSettingItem = findSettingItem("development", i)) == null) {
            return;
        }
        LOGD("setSettingItemCallBackFunction(), setFunction, id = " + i);
        settingItemFindSettingItem.setOnSettingItemClick(settingItemClick);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int level;
        int keyCode = keyEvent.getKeyCode();
        LOGD("dispatchKeyEvent(),keyCode = " + keyCode);
        if (keyCode == 4 && keyEvent.getAction() == 0 && (level = this.mListViewAdapter.getLevel()) > 0) {
            this.mListViewAdapter.setLevel(level - 1);
            String content = this.mListViewAdapter.getContent();
            int parentId = this.mListViewAdapter.getParentId();
            SettingItem settingItemFindParent = findParent(content, parentId);
            LOGD("dispatchKey,Key_Back, name = " + content + ",parent id = " + parentId);
            if (settingItemFindParent == null) {
                this.mListViewAdapter.setParentId(-1);
            } else {
                this.mListViewAdapter.setParentId(settingItemFindParent.mParentId);
            }
            this.mListViewAdapter.invalidate();
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        if (this.mListViewAdapter != null) {
            LOGD("onSaveInstanceState(),Level = " + this.mListViewAdapter.getLevel() + ", Indicator = " + this.mListViewAdapter.getSelection() + ",ParentId = " + this.mListViewAdapter.getParentId());
            bundle.putInt("Level", this.mListViewAdapter.getLevel());
            bundle.putInt("Indicator", this.mListViewAdapter.getSelection());
            bundle.putInt("Parent", this.mListViewAdapter.getParentId());
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        Log.d(this.TAG, "OnResume:==================");
        super.onResume();
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        LOGD("onActivityResult requestcode:" + i);
        if (i2 == -1 && i == 1002) {
            String stringExtra = intent.getStringExtra(UsbMode.USB_MODE);
            LOGD("usbmode:" + stringExtra);
            if (this.mUsbMode != null) {
                this.mUsbMode.updateMode(stringExtra);
            }
        }
    }

    public void StartActivityForResultSafely(Intent intent, int i) {
        try {
            startActivityForResult(intent, i);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, R.string.activity_not_found, 0).show();
            Log.e(this.TAG, "Unable to launch intent=" + intent, e);
        } catch (SecurityException e2) {
            Toast.makeText(this, R.string.activity_not_found, 0).show();
            Log.e(this.TAG, "Launcher does not have the permission to launch " + intent + ". Make sure to create a MAIN intent-filter for the corresponding activity or use the exported attribute for this activity.  intent=" + intent, e2);
        }
    }

    public void unregisterReceiverSafe(BroadcastReceiver broadcastReceiver) {
        try {
            unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException unused) {
        }
    }
}
