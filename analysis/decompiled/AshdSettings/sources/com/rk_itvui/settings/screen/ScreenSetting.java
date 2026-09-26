package com.rk_itvui.settings.screen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.DisplayOutputManager;
import android.os.Handler;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.Log;
import android.view.LayoutInflater;
import com.ashd.settings.R;
import com.rk_itvui.settings.developer.SettingItem;
import com.rk_itvui.settings.developer.SettingItemAddManager;
import com.rk_itvui.settings.developer.SettingItemClick;
import com.softwinner.tv.AwTvSourceManager;

/* JADX INFO: loaded from: classes.dex */
public class ScreenSetting {
    private static final boolean DEBUG = true;
    private static final String TAG = "ScreenSettings";
    private Context mContext;
    private DisplayOutputManager mDisplayManagement;
    private Handler mHandler;
    private Runnable mRunnable;
    private SettingItemAddManager mSettingItemAddManager;
    private Handler mUIHandler;
    private LayoutInflater mInflater = null;
    private AlertDialog mDialog = null;
    private int mTime = -1;
    private int mMainDisplay_last = -1;
    public int mMainDisplay_set = -1;
    private int mMainOutput_set = -1;
    private String mMainMode_last = null;
    private String mMainMode_set = null;
    private int mAuxDisplay_last = -1;
    private int mAuxDisplay_set = -1;
    private String mAuxMode_last = null;
    private String mAuxMode_set = null;
    private CharSequence[] mMainIfaceEntries = null;
    public CharSequence[] mMainIfaceValue = null;
    private CharSequence[] mAuxIfaceEntries = null;
    private CharSequence[] mAuxIfaceValue = null;
    private CharSequence[] mMainModeEntries = null;
    private CharSequence[] mMainModeValue = null;
    private CharSequence[] mAuxModeEntries = null;
    private CharSequence[] mAuxModeValue = null;
    private DialogInterface.OnClickListener mIfaceItemClickListener = null;
    public Dialog mOutputInterfaceDialog = null;
    private Dialog mAuxOutputInterfaceDialog = null;
    private DialogInterface.OnClickListener mModeItemClickListener = null;
    public Dialog mModeDialog = null;
    private Dialog mAuxModeDialog = null;
    private DialogInterface.OnClickListener mOutputItemClickListener = null;
    public Dialog mOutputDialog = null;
    private Dialog mAuxOutputDialog = null;
    public CharSequence[] mOutputitemValue = new CharSequence[2];
    private int mInterfaceId = -1;
    private int mModeId = -1;
    private int mDellist = -1;
    BroadcastReceiver mHdmiReceiver = new BroadcastReceiver() { // from class: com.rk_itvui.settings.screen.ScreenSetting.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.HDMI_PLUGGED".equals(intent.getAction())) {
                boolean booleanExtra = intent.getBooleanExtra(AwTvSourceManager.EXTRA_HOTPLUG_STATE, true);
                ScreenSetting.this.LOG("HDMI_PLUGGED state is " + booleanExtra);
                if (booleanExtra) {
                    ScreenSetting.this.Resume();
                    DisplayOutputManager displayOutputManager = ScreenSetting.this.mDisplayManagement;
                    ScreenSetting.this.mDisplayManagement.getClass();
                    ScreenSetting.this.mMainMode_set = displayOutputManager.getCurrentMode(0, ScreenSetting.this.mMainDisplay_set);
                    if (ScreenSetting.this.mModeDialog != null && ScreenSetting.this.mModeDialog.isShowing()) {
                        ScreenSetting.this.mModeDialog.cancel();
                    }
                    ScreenSetting.this.mUIHandler.sendEmptyMessage(0);
                    ScreenSetting.this.mUIHandler.sendEmptyMessage(1);
                    return;
                }
                Log.d("HDMI_PLUGGED state is ", "remove");
                ScreenSetting screenSetting = ScreenSetting.this;
                ScreenSetting.this.mDisplayManagement.getClass();
                screenSetting.SetModeList(0, 1);
                DisplayOutputManager displayOutputManager2 = ScreenSetting.this.mDisplayManagement;
                ScreenSetting.this.mDisplayManagement.getClass();
                displayOutputManager2.setInterface(0, 1, true);
                ScreenSetting.this.Resume();
                if (ScreenSetting.this.mModeDialog != null && ScreenSetting.this.mModeDialog.isShowing()) {
                    ScreenSetting.this.mModeDialog.cancel();
                }
                ScreenSetting.this.mUIHandler.sendEmptyMessage(1);
                ScreenSetting.this.mUIHandler.sendEmptyMessage(0);
            }
        }
    };
    private SettingItemClick mItemClickListener = new SettingItemClick() { // from class: com.rk_itvui.settings.screen.ScreenSetting.3
        public void onItemLongClick(SettingItem settingItem, int i) {
        }

        public void onItemClick(SettingItem settingItem, int i) {
            if (i == ScreenSetting.this.mInterfaceId) {
                ScreenSetting.this.InitAuxIfaceDialog(ScreenSetting.this.mContext);
                ScreenSetting.this.mAuxOutputInterfaceDialog.show();
            } else if (i == ScreenSetting.this.mModeId) {
                ScreenSetting.this.createAuxModeDialog(ScreenSetting.this.mContext);
                ScreenSetting.this.mAuxModeDialog.show();
            }
        }
    };

    public void Pause() {
    }

    public int getOutputIndex(int i) {
        if (i > 0) {
            return i;
        }
        return 0;
    }

    static /* synthetic */ int access$010(ScreenSetting screenSetting) {
        int i = screenSetting.mTime;
        screenSetting.mTime = i - 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void LOG(String str) {
        Log.d(TAG, str);
    }

    public ScreenSetting(Context context, Handler handler) {
        this.mContext = null;
        this.mUIHandler = null;
        this.mDisplayManagement = null;
        this.mSettingItemAddManager = null;
        this.mContext = context;
        this.mUIHandler = handler;
        this.mOutputitemValue[0] = this.mContext.getString(R.string.default_size);
        this.mOutputitemValue[1] = this.mContext.getString(R.string.origin_size);
        this.mSettingItemAddManager = SettingItemAddManager.getInstance();
        try {
            this.mDisplayManagement = new DisplayOutputManager();
        } catch (RemoteException unused) {
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.HDMI_PLUGGED");
        this.mContext.registerReceiver(this.mHdmiReceiver, intentFilter);
        this.mRunnable = new Runnable() { // from class: com.rk_itvui.settings.screen.ScreenSetting.1
            @Override // java.lang.Runnable
            public void run() {
                ScreenSetting.this.LOG("time:" + ScreenSetting.this.mTime);
                if (ScreenSetting.this.mDialog == null || ScreenSetting.this.mTime < 0) {
                    return;
                }
                if (ScreenSetting.this.mTime <= 0) {
                    ScreenSetting.this.RestoreDisplaySetting();
                    ScreenSetting.this.mDialog.dismiss();
                    return;
                }
                ScreenSetting.access$010(ScreenSetting.this);
                ScreenSetting.this.mDialog.getButton(-1).setText(ScreenSetting.this.mContext.getString(R.string.screen_control_ok_title) + " (" + String.valueOf(ScreenSetting.this.mTime) + ")");
                ScreenSetting.this.mHandler.postDelayed(this, 1000L);
            }
        };
        Resume();
        this.mUIHandler.sendEmptyMessage(0);
    }

    public int getIfaceIndex(CharSequence[] charSequenceArr, int i) {
        String string = Integer.toString(i);
        for (int i2 = 0; i2 < charSequenceArr.length; i2++) {
            if (string.equals(charSequenceArr[i2].toString())) {
                return i2;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getModeIndex(CharSequence[] charSequenceArr, String str) {
        if (str.equals(null)) {
            return 0;
        }
        for (int i = 0; i < charSequenceArr.length; i++) {
            if (str.equals(charSequenceArr[i].toString())) {
                return i;
            }
        }
        return 0;
    }

    public String getIfaceTitle(int i) {
        this.mDisplayManagement.getClass();
        String string = i == 5 ? this.mContext.getString(R.string.screen_iface_lcd_title) : null;
        this.mDisplayManagement.getClass();
        if (i == 4) {
            return this.mContext.getString(R.string.screen_iface_hdmi_title);
        }
        this.mDisplayManagement.getClass();
        if (i == 3) {
            return this.mContext.getString(R.string.screen_iface_vga_title);
        }
        this.mDisplayManagement.getClass();
        if (i == 2) {
            return this.mContext.getString(R.string.screen_iface_ypbpr_title);
        }
        this.mDisplayManagement.getClass();
        return i == 1 ? this.mContext.getString(R.string.screen_iface_tv_title) : string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SetModeList(int i, int i2) {
        LOG("SetModeList display " + i + " iface " + i2);
        String[] modeList = this.mDisplayManagement.getModeList(i, i2);
        if (modeList == null) {
            return;
        }
        CharSequence[] charSequenceArr = new CharSequence[modeList.length];
        CharSequence[] charSequenceArr2 = new CharSequence[modeList.length];
        for (int i3 = 0; i3 < modeList.length; i3++) {
            charSequenceArr[i3] = modeList[i3];
            this.mDisplayManagement.getClass();
            if (i2 == 1) {
                String str = modeList[i3];
                if (str.equals("720x576i-50")) {
                    charSequenceArr[i3] = "CVBS: PAL";
                } else if (str.equals("720x480i-60")) {
                    charSequenceArr[i3] = "CVBS: NTSC";
                } else {
                    charSequenceArr[i3] = "YPbPr: " + modeList[i3];
                }
            }
            charSequenceArr2[i3] = modeList[i3];
        }
        this.mDisplayManagement.getClass();
        if (i == 0) {
            this.mMainModeEntries = charSequenceArr;
            this.mMainModeValue = charSequenceArr2;
        } else {
            this.mAuxModeEntries = charSequenceArr;
            this.mAuxModeValue = charSequenceArr2;
        }
    }

    public void Resume() {
        LOG("resume fill interface and mode");
        DisplayOutputManager displayOutputManager = this.mDisplayManagement;
        this.mDisplayManagement.getClass();
        int[] ifaceList = displayOutputManager.getIfaceList(0);
        if (ifaceList == null) {
            return;
        }
        if (ifaceList != null) {
            CharSequence[] charSequenceArr = new CharSequence[ifaceList.length];
            CharSequence[] charSequenceArr2 = new CharSequence[ifaceList.length];
            for (int i = 0; i < ifaceList.length; i++) {
                charSequenceArr[i] = getIfaceTitle(ifaceList[i]);
                charSequenceArr2[i] = Integer.toString(ifaceList[i]);
            }
            this.mMainIfaceEntries = charSequenceArr;
            this.mMainIfaceValue = charSequenceArr2;
            DisplayOutputManager displayOutputManager2 = this.mDisplayManagement;
            this.mDisplayManagement.getClass();
            int currentInterface = displayOutputManager2.getCurrentInterface(0);
            this.mMainDisplay_last = currentInterface;
            LOG("cur interface:" + getIfaceTitle(currentInterface));
            this.mDisplayManagement.getClass();
            SetModeList(0, currentInterface);
            DisplayOutputManager displayOutputManager3 = this.mDisplayManagement;
            this.mDisplayManagement.getClass();
            String currentMode = displayOutputManager3.getCurrentMode(0, currentInterface);
            if (currentMode != null) {
                this.mMainMode_last = currentMode;
                this.mMainDisplay_set = this.mMainDisplay_last;
                this.mMainMode_set = this.mMainMode_last;
            }
            if (currentMode != null) {
                LOG("mode index:" + getModeIndex(this.mMainModeValue, currentMode));
                if (this.mMainModeValue != null && this.mMainModeValue.length > 0) {
                    this.mMainModeEntries[getModeIndex(this.mMainModeValue, currentMode)].toString();
                }
            }
        }
        DisplayOutputManager displayOutputManager4 = this.mDisplayManagement;
        this.mDisplayManagement.getClass();
        int[] ifaceList2 = displayOutputManager4.getIfaceList(1);
        if (ifaceList2 != null) {
            this.mInterfaceId = this.mSettingItemAddManager.findID();
            SettingItem settingItem = new SettingItem(1, R.string.screen_settings, this.mInterfaceId, this.mContext.getString(R.string.screen_interface) + "1", true);
            this.mSettingItemAddManager.addSettingItem("device", settingItem, R.string.screen_mode_title, true);
            settingItem.setOnSettingItemClick(this.mItemClickListener);
            this.mModeId = this.mSettingItemAddManager.findID();
            SettingItem settingItem2 = new SettingItem(1, R.string.screen_settings, this.mModeId, this.mContext.getString(R.string.screen_mode_title) + "1", true);
            this.mSettingItemAddManager.addSettingItem("device", settingItem2, this.mInterfaceId, true);
            settingItem2.setOnSettingItemClick(this.mItemClickListener);
            DisplayOutputManager displayOutputManager5 = this.mDisplayManagement;
            this.mDisplayManagement.getClass();
            int currentInterface2 = displayOutputManager5.getCurrentInterface(1);
            this.mAuxDisplay_last = currentInterface2;
            CharSequence[] charSequenceArr3 = new CharSequence[ifaceList2.length];
            CharSequence[] charSequenceArr4 = new CharSequence[ifaceList2.length];
            for (int i2 = 0; i2 < ifaceList2.length; i2++) {
                charSequenceArr3[i2] = getIfaceTitle(ifaceList2[i2]);
                charSequenceArr4[i2] = Integer.toString(ifaceList2[i2]);
            }
            this.mAuxIfaceEntries = charSequenceArr3;
            this.mAuxIfaceValue = charSequenceArr4;
            this.mDisplayManagement.getClass();
            SetModeList(1, currentInterface2);
            DisplayOutputManager displayOutputManager6 = this.mDisplayManagement;
            this.mDisplayManagement.getClass();
            String currentMode2 = displayOutputManager6.getCurrentMode(1, currentInterface2);
            if (currentMode2 != null) {
                this.mAuxMode_last = currentMode2;
                this.mAuxDisplay_set = this.mAuxDisplay_last;
                this.mAuxMode_set = this.mAuxMode_last;
            }
            if (currentMode2 != null) {
                LOG("mode index:" + getModeIndex(this.mAuxModeValue, currentMode2));
                this.mAuxModeEntries[getModeIndex(this.mAuxModeValue, currentMode2)].toString();
            }
        }
        getCurrentmode();
        getCurrentiface();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void RestoreDisplaySetting() {
        LOG("RestoreDisplaySetting,mMainDisplay_set = " + this.mMainDisplay_set + ",mMainDisplay_last = " + this.mMainDisplay_last);
        if (this.mMainDisplay_set != this.mMainDisplay_last || !this.mMainMode_last.equals(this.mMainMode_set)) {
            if (this.mMainDisplay_set != this.mMainDisplay_last) {
                DisplayOutputManager displayOutputManager = this.mDisplayManagement;
                this.mDisplayManagement.getClass();
                displayOutputManager.setInterface(0, this.mMainDisplay_set, false);
                LOG("RestoreDisplaySetting(),mMainMode_set = " + this.mMainMode_set + ",mMainDisplay_last = " + this.mMainDisplay_last);
                this.mDisplayManagement.getClass();
                SetModeList(0, this.mMainDisplay_last);
            }
            if (!this.mMainMode_last.equals("720x576i-50") && !this.mMainMode_last.equals("720x480i-60")) {
                String str = this.mMainMode_last;
            }
            DisplayOutputManager displayOutputManager2 = this.mDisplayManagement;
            this.mDisplayManagement.getClass();
            displayOutputManager2.setMode(0, this.mMainDisplay_last, this.mMainMode_last);
            DisplayOutputManager displayOutputManager3 = this.mDisplayManagement;
            this.mDisplayManagement.getClass();
            displayOutputManager3.setInterface(0, this.mMainDisplay_last, true);
            this.mMainDisplay_set = this.mMainDisplay_last;
            this.mMainMode_set = this.mMainMode_last;
        }
        if (this.mDisplayManagement.getDisplayNumber() > 1 && (this.mAuxDisplay_set != this.mAuxDisplay_last || !this.mAuxMode_last.equals(this.mAuxMode_set))) {
            if (this.mAuxDisplay_set != this.mAuxDisplay_last) {
                DisplayOutputManager displayOutputManager4 = this.mDisplayManagement;
                this.mDisplayManagement.getClass();
                displayOutputManager4.setInterface(1, this.mAuxDisplay_set, false);
                this.mDisplayManagement.getClass();
                SetModeList(1, this.mAuxDisplay_last);
            }
            if (!this.mAuxMode_last.equals("720x576i-50") && !this.mAuxMode_last.equals("720x480i-60")) {
                String str2 = this.mAuxMode_last;
            }
            if (this.mAuxModeEntries != null && this.mAuxModeEntries.length > 0 && this.mAuxDisplay_last < this.mAuxModeEntries.length) {
                int i = this.mAuxDisplay_last;
            }
            DisplayOutputManager displayOutputManager5 = this.mDisplayManagement;
            this.mDisplayManagement.getClass();
            displayOutputManager5.setMode(1, this.mAuxDisplay_last, this.mAuxMode_last);
            DisplayOutputManager displayOutputManager6 = this.mDisplayManagement;
            this.mDisplayManagement.getClass();
            displayOutputManager6.setInterface(1, this.mAuxDisplay_last, true);
            this.mAuxDisplay_set = this.mAuxDisplay_last;
            this.mAuxMode_set = this.mAuxMode_last;
        }
        this.mUIHandler.sendEmptyMessage(0);
    }

    public void OnClick(int i) {
        switch (i) {
            case R.string.screen_interface /* 2131690948 */:
                LOG("screen interface onClick");
                InitIfaceDialog(this.mContext);
                this.mOutputInterfaceDialog.show();
                break;
            case R.string.screen_mode_title /* 2131690950 */:
                LOG("screen mode onClick");
                InitModeDialog(this.mContext);
                this.mModeDialog.show();
                break;
            case R.string.screen_settings /* 2131690951 */:
                LOG("screensettings onClick");
                break;
            case R.string.screenscale /* 2131690969 */:
                LOG("screen scale onClick");
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void InitDialog(Context context) {
        this.mContext = context;
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        builder.setTitle(this.mContext.getString(R.string.screen_mode_switch_title));
        builder.setCancelable(false);
        builder.setNegativeButton(this.mContext.getString(R.string.screen_control_cancel_title), new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.screen.ScreenSetting.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ScreenSetting.this.mTime = -1;
                ScreenSetting.this.RestoreDisplaySetting();
                ScreenSetting.this.mUIHandler.sendEmptyMessage(1);
            }
        });
        builder.setPositiveButton(this.mContext.getString(R.string.screen_control_ok_title), new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.screen.ScreenSetting.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ScreenSetting.this.mTime = -1;
                ScreenSetting.this.mDisplayManagement.saveConfig();
                ScreenSetting.this.mMainDisplay_last = ScreenSetting.this.mMainDisplay_set;
                ScreenSetting.this.mMainMode_last = ScreenSetting.this.mMainMode_set;
                ScreenSetting.this.mAuxDisplay_last = ScreenSetting.this.mAuxDisplay_set;
                ScreenSetting.this.mAuxMode_last = ScreenSetting.this.mAuxMode_set;
            }
        });
        this.mDialog = builder.create();
    }

    public void InitIfaceDialog(Context context) {
        this.mContext = context;
        this.mIfaceItemClickListener = new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.screen.ScreenSetting.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ScreenSetting.this.LOG("item index:" + i);
                int i2 = Integer.parseInt(ScreenSetting.this.mMainIfaceValue[i].toString());
                ScreenSetting.this.mMainDisplay_set = i2;
                ScreenSetting.this.LOG("InitIfaceDialog(),mMainDisplay_set = " + ScreenSetting.this.mMainDisplay_set);
                ScreenSetting screenSetting = ScreenSetting.this;
                ScreenSetting.this.mDisplayManagement.getClass();
                screenSetting.SetModeList(0, i2);
                DisplayOutputManager displayOutputManager = ScreenSetting.this.mDisplayManagement;
                ScreenSetting.this.mDisplayManagement.getClass();
                String currentMode = displayOutputManager.getCurrentMode(0, i2);
                if (currentMode != null) {
                    ScreenSetting.this.LOG("mode index:" + ScreenSetting.this.getModeIndex(ScreenSetting.this.mMainModeValue, currentMode));
                    ScreenSetting.this.mMainModeEntries[ScreenSetting.this.getModeIndex(ScreenSetting.this.mMainModeValue, currentMode)].toString();
                }
                ScreenSetting.this.mUIHandler.sendEmptyMessage(1);
                ScreenSetting.this.mOutputInterfaceDialog.cancel();
            }
        };
        this.mOutputInterfaceDialog = new AlertDialog.Builder(this.mContext).setTitle(R.string.screen_interface).setSingleChoiceItems(this.mMainIfaceEntries, getIfaceIndex(this.mMainIfaceValue, this.mMainDisplay_set), this.mIfaceItemClickListener).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.rk_itvui.settings.screen.ScreenSetting.7
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
            }
        }).create();
    }

    public void InitModeDialog(Context context) {
        String string;
        this.mContext = context;
        this.mModeItemClickListener = new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.screen.ScreenSetting.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ScreenSetting.this.LOG("item index:" + i);
                ScreenSetting.this.mMainMode_set = ScreenSetting.this.mMainModeValue[i].toString();
                ScreenSetting.this.LOG("mode:" + ScreenSetting.this.mMainMode_set);
                ScreenSetting.this.LOG("mMainDisplay_set = " + ScreenSetting.this.mMainDisplay_set + ",mMainDisplay_last = " + ScreenSetting.this.mMainDisplay_last);
                ScreenSetting.this.LOG("mMainMode_set = " + ScreenSetting.this.mMainMode_set + ",mMainMode_last = " + ScreenSetting.this.mMainMode_last);
                if (ScreenSetting.this.mMainDisplay_set != ScreenSetting.this.mMainDisplay_last || !ScreenSetting.this.mMainMode_last.equals(ScreenSetting.this.mMainMode_set)) {
                    if (ScreenSetting.this.mMainDisplay_set != ScreenSetting.this.mMainDisplay_last) {
                        DisplayOutputManager displayOutputManager = ScreenSetting.this.mDisplayManagement;
                        ScreenSetting.this.mDisplayManagement.getClass();
                        displayOutputManager.setInterface(0, ScreenSetting.this.mMainDisplay_last, false);
                        ScreenSetting.this.mTime = 30;
                    } else {
                        ScreenSetting.this.mTime = 15;
                    }
                    DisplayOutputManager displayOutputManager2 = ScreenSetting.this.mDisplayManagement;
                    ScreenSetting.this.mDisplayManagement.getClass();
                    displayOutputManager2.setMode(0, ScreenSetting.this.mMainDisplay_set, ScreenSetting.this.mMainMode_set);
                    DisplayOutputManager displayOutputManager3 = ScreenSetting.this.mDisplayManagement;
                    ScreenSetting.this.mDisplayManagement.getClass();
                    displayOutputManager3.setInterface(0, ScreenSetting.this.mMainDisplay_set, true);
                    if (ScreenSetting.this.mDialog == null) {
                        ScreenSetting.this.InitDialog(ScreenSetting.this.mContext);
                    }
                    ScreenSetting.this.mDialog.show();
                    ScreenSetting.this.mDialog.getButton(-2).requestFocus();
                    ScreenSetting.this.mDialog.getButton(-1).setText(ScreenSetting.this.mContext.getString(R.string.screen_control_ok_title) + " (" + String.valueOf(ScreenSetting.this.mTime) + ")");
                    if (ScreenSetting.this.mHandler == null) {
                        ScreenSetting.this.mHandler = new Handler();
                    }
                    ScreenSetting.this.mHandler.postDelayed(ScreenSetting.this.mRunnable, 1000L);
                    ScreenSetting.this.mUIHandler.sendEmptyMessage(0);
                }
                ScreenSetting.this.mModeDialog.cancel();
            }
        };
        DisplayOutputManager displayOutputManager = this.mDisplayManagement;
        this.mDisplayManagement.getClass();
        this.mMainMode_set = displayOutputManager.getCurrentMode(0, this.mMainDisplay_set);
        LOG("mMainMode_set = " + this.mMainMode_set + ",mMainDisplay_set = " + this.mMainDisplay_set + ",mMainDisplay_last = " + this.mMainDisplay_last);
        int i = this.mMainDisplay_set;
        this.mDisplayManagement.getClass();
        if (i == 4) {
            string = this.mContext.getString(R.string.Resolution);
        } else {
            int i2 = this.mMainDisplay_set;
            this.mDisplayManagement.getClass();
            string = i2 == 1 ? this.mContext.getString(R.string.screen_mode_title) : null;
        }
        this.mModeDialog = new AlertDialog.Builder(this.mContext).setTitle(string).setSingleChoiceItems(this.mMainModeEntries, getModeIndex(this.mMainModeValue, this.mMainMode_set), this.mModeItemClickListener).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.rk_itvui.settings.screen.ScreenSetting.9
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
            }
        }).create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void InitAuxIfaceDialog(Context context) {
        this.mContext = context;
        this.mAuxOutputInterfaceDialog = new AlertDialog.Builder(this.mContext).setTitle(R.string.screen_interface).setSingleChoiceItems(this.mAuxIfaceEntries, getIfaceIndex(this.mAuxIfaceValue, this.mAuxDisplay_set), new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.screen.ScreenSetting.10
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ScreenSetting.this.LOG("item index:" + i);
                int i2 = Integer.parseInt(ScreenSetting.this.mAuxIfaceValue[i].toString());
                ScreenSetting.this.LOG("iface:" + i2);
                ScreenSetting.this.mAuxDisplay_set = i2;
                ScreenSetting screenSetting = ScreenSetting.this;
                ScreenSetting.this.mDisplayManagement.getClass();
                screenSetting.SetModeList(1, i2);
                DisplayOutputManager displayOutputManager = ScreenSetting.this.mDisplayManagement;
                ScreenSetting.this.mDisplayManagement.getClass();
                String currentMode = displayOutputManager.getCurrentMode(1, i2);
                if (currentMode != null) {
                    ScreenSetting.this.LOG("mode index:" + ScreenSetting.this.getModeIndex(ScreenSetting.this.mAuxModeValue, currentMode));
                    if (ScreenSetting.this.mAuxModeEntries != null && ScreenSetting.this.mAuxModeEntries.length > 0) {
                        ScreenSetting.this.mAuxModeEntries[ScreenSetting.this.getModeIndex(ScreenSetting.this.mAuxModeValue, currentMode)].toString();
                    }
                }
                ScreenSetting.this.mUIHandler.sendEmptyMessage(0);
                ScreenSetting.this.mAuxOutputInterfaceDialog.cancel();
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.rk_itvui.settings.screen.ScreenSetting.12
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.screen.ScreenSetting.11
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createAuxModeDialog(Context context) {
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.screen.ScreenSetting.13
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ScreenSetting.this.LOG("item index:" + i);
                ScreenSetting.this.mAuxMode_set = ScreenSetting.this.mAuxModeValue[i].toString();
                ScreenSetting.this.LOG("mode:" + ScreenSetting.this.mAuxMode_set);
                if (ScreenSetting.this.mAuxDisplay_set != ScreenSetting.this.mAuxDisplay_last || !ScreenSetting.this.mAuxMode_last.equals(ScreenSetting.this.mAuxMode_set)) {
                    if (ScreenSetting.this.mAuxDisplay_set != ScreenSetting.this.mAuxDisplay_last) {
                        DisplayOutputManager displayOutputManager = ScreenSetting.this.mDisplayManagement;
                        ScreenSetting.this.mDisplayManagement.getClass();
                        displayOutputManager.setInterface(1, ScreenSetting.this.mAuxDisplay_last, false);
                        ScreenSetting.this.mTime = 30;
                    } else {
                        ScreenSetting.this.mTime = 15;
                    }
                    DisplayOutputManager displayOutputManager2 = ScreenSetting.this.mDisplayManagement;
                    ScreenSetting.this.mDisplayManagement.getClass();
                    displayOutputManager2.setMode(1, ScreenSetting.this.mAuxDisplay_set, ScreenSetting.this.mAuxMode_set);
                    DisplayOutputManager displayOutputManager3 = ScreenSetting.this.mDisplayManagement;
                    ScreenSetting.this.mDisplayManagement.getClass();
                    displayOutputManager3.setInterface(1, ScreenSetting.this.mAuxDisplay_set, true);
                    if (ScreenSetting.this.mDialog == null) {
                        ScreenSetting.this.InitDialog(ScreenSetting.this.mContext);
                    }
                    ScreenSetting.this.mDialog.show();
                    ScreenSetting.this.mDialog.getButton(-2).requestFocus();
                    ScreenSetting.this.mDialog.getButton(-1).setText(ScreenSetting.this.mContext.getString(R.string.screen_control_ok_title) + " (" + String.valueOf(ScreenSetting.this.mTime) + ")");
                    if (ScreenSetting.this.mHandler == null) {
                        ScreenSetting.this.mHandler = new Handler();
                    }
                    ScreenSetting.this.mHandler.postDelayed(ScreenSetting.this.mRunnable, 1000L);
                    ScreenSetting.this.mUIHandler.sendEmptyMessage(0);
                }
                ScreenSetting.this.mAuxModeDialog.cancel();
            }
        };
        DisplayOutputManager displayOutputManager = this.mDisplayManagement;
        this.mDisplayManagement.getClass();
        this.mAuxMode_set = displayOutputManager.getCurrentMode(1, this.mAuxDisplay_set);
        LOG("mAuxMode_set = " + this.mAuxMode_set + ",mAuxDisplay_set = " + this.mAuxDisplay_set + ",mAuxnDisplay_last = " + this.mAuxDisplay_last);
        this.mAuxModeDialog = new AlertDialog.Builder(this.mContext).setTitle(R.string.screen_mode_title).setSingleChoiceItems(this.mAuxModeEntries, getModeIndex(this.mAuxModeValue, this.mAuxMode_set), onClickListener).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.rk_itvui.settings.screen.ScreenSetting.15
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.screen.ScreenSetting.14
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create();
    }

    public void InitOutputDialog(Context context) {
        this.mContext = context;
        this.mOutputItemClickListener = new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.screen.ScreenSetting.16
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ScreenSetting.this.LOG("item index:" + i);
                switch (i) {
                    case 0:
                        ScreenSetting.this.LOG("\n start set fullscreen");
                        ScreenSetting.this.mMainOutput_set = 0;
                        SystemProperties.set("persist.sys.video.fullscreen", "0");
                        ScreenSetting.this.mUIHandler.sendEmptyMessage(2);
                        break;
                    case 1:
                        ScreenSetting.this.LOG("\n start set 1:1");
                        ScreenSetting.this.mMainOutput_set = 1;
                        SystemProperties.set("persist.sys.video.fullscreen", "1");
                        ScreenSetting.this.mUIHandler.sendEmptyMessage(3);
                        break;
                }
                ScreenSetting.this.mOutputDialog.cancel();
            }
        };
        this.mMainOutput_set = Integer.parseInt(SystemProperties.get("persist.sys.video.fullscreen", "0"));
        this.mOutputDialog = new AlertDialog.Builder(this.mContext).setTitle(R.string.player_windowchoose).setSingleChoiceItems(this.mOutputitemValue, this.mMainOutput_set, this.mOutputItemClickListener).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.rk_itvui.settings.screen.ScreenSetting.17
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
            }
        }).create();
    }

    public String getCurrentmode() {
        return this.mMainMode_set;
    }

    public int getCurrentiface() {
        return this.mMainDisplay_set;
    }

    public int getCurrentszie() {
        this.mMainOutput_set = Integer.parseInt(SystemProperties.get("persist.sys.video.fullscreen", "0"));
        return this.mMainOutput_set;
    }
}
