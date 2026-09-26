package com.rk_itvui.settings.picture;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import com.ashd.settings.R;
import com.rk_itvui.settings.picture.model.SettingItem;
import com.softwinner.tv.AwTvDisplayManager;
import com.softwinner.tv.common.AwTvDisplayTypes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class PictureModeController extends ListController {
    private static final String KEY_PQ_BRIGHTNESS = "modeBrightness";
    private static final String KEY_PQ_COLOR = "modeColor";
    private static final String KEY_PQ_CONTRAST = "modeContrast";
    private static final String KEY_PQ_MODE = "pictureMode";
    private static final String KEY_PQ_SHARPNESS = "modeSharpness";
    private static final String KEY_PQ_TINT = "modeTint";
    private static String TAG = "PictureModeController";
    private String mMode;
    private PQControlImpl mPQManager;
    private AwTvDisplayManager mTvDisplayManager;
    String[] pictureModeKeys;

    @Override // com.rk_itvui.settings.picture.ListController
    public void init(Context context, ListAdapter listAdapter, ListController listController) {
        this.mPQManager = new PQControlImpl(context);
        this.mTvDisplayManager = AwTvDisplayManager.getInstance();
        super.init(context, listAdapter, listController);
    }

    @Override // com.rk_itvui.settings.picture.ListController, android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        super.onItemSelected(adapterView, view, i, j);
    }

    @Override // com.rk_itvui.settings.picture.ListController
    protected ArrayList<SettingItem> defaultInitSettings() {
        Resources resources = this.mContext.getResources();
        ArrayList<SettingItem> arrayList = new ArrayList<>();
        SettingItem settingItem = new SettingItem(KEY_PQ_MODE, resources.getString(R.string.title_picture_mode), resources.getStringArray(R.array.setting_picture_mode_choices));
        settingItem.setValue(getSettingIntValue(KEY_PQ_MODE));
        arrayList.add(settingItem);
        SettingItem settingItem2 = new SettingItem(KEY_PQ_BRIGHTNESS, resources.getString(R.string.title_picture_brightness), 0, 100);
        settingItem2.setValue(getSettingIntValue(KEY_PQ_BRIGHTNESS));
        arrayList.add(settingItem2);
        SettingItem settingItem3 = new SettingItem(KEY_PQ_CONTRAST, resources.getString(R.string.title_picture_contrast), 0, 100);
        settingItem3.setValue(getSettingIntValue(KEY_PQ_CONTRAST));
        arrayList.add(settingItem3);
        SettingItem settingItem4 = new SettingItem(KEY_PQ_COLOR, resources.getString(R.string.title_picture_color), 0, 100);
        settingItem4.setValue(getSettingIntValue(KEY_PQ_COLOR));
        arrayList.add(settingItem4);
        SettingItem settingItem5 = new SettingItem(KEY_PQ_SHARPNESS, resources.getString(R.string.title_picture_sharpness), 0, 100);
        settingItem5.setValue(getSettingIntValue(KEY_PQ_SHARPNESS));
        arrayList.add(settingItem5);
        SettingItem settingItem6 = new SettingItem(KEY_PQ_TINT, resources.getString(R.string.title_picture_tint), 0, 100);
        settingItem6.setValue(getSettingIntValue(KEY_PQ_TINT));
        arrayList.add(settingItem6);
        return arrayList;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:23:0x005b  */
    @Override // com.rk_itvui.settings.picture.ListController
    public int updateSettingValue(String str, int i) {
        Log.d(TAG, "updateSettingValue " + str);
        switch (str) {
            case "pictureMode":
                Log.d(TAG, "set picture mode " + this.pictureModeKeys[i]);
                this.mTvDisplayManager.setPictureModeByName(AwTvDisplayTypes.EnumPictureMode.EnumValueOf(this.pictureModeKeys[i]));
                this.mMode = this.pictureModeKeys[i];
                this.myAdapter.update(initSettings());
                this.myAdapter.notifyDataSetChanged();
                return 1;
            case "modeBrightness":
                this.mTvDisplayManager.factorySetBasicControl(AwTvDisplayTypes.EnumTvSourceType.E_AW_SOURCE_TYPE_MAX.getValue(), this.mMode, AwTvDisplayTypes.EnumPQBasicType.E_AW_PQ_BASIC_TYPE_BRIGHTNESS, i);
                return 1;
            case "modeContrast":
                this.mTvDisplayManager.factorySetBasicControl(AwTvDisplayTypes.EnumTvSourceType.E_AW_SOURCE_TYPE_MAX.getValue(), this.mMode, AwTvDisplayTypes.EnumPQBasicType.E_AW_PQ_BASIC_TYPE_CONTRAST, i);
                return 1;
            case "modeColor":
                this.mTvDisplayManager.factorySetBasicControl(AwTvDisplayTypes.EnumTvSourceType.E_AW_SOURCE_TYPE_MAX.getValue(), this.mMode, AwTvDisplayTypes.EnumPQBasicType.E_AW_PQ_BASIC_TYPE_SATURATION, i);
                return 1;
            case "modeSharpness":
                this.mTvDisplayManager.factorySetBasicControl(AwTvDisplayTypes.EnumTvSourceType.E_AW_SOURCE_TYPE_MAX.getValue(), this.mMode, AwTvDisplayTypes.EnumPQBasicType.E_AW_PQ_BASIC_TYPE_SHARPNESS, i);
                return 1;
            case "modeTint":
                this.mTvDisplayManager.factorySetBasicControl(AwTvDisplayTypes.EnumTvSourceType.E_AW_SOURCE_TYPE_MAX.getValue(), this.mMode, AwTvDisplayTypes.EnumPQBasicType.E_AW_PQ_BASIC_TYPE_HUE, i);
                return 1;
            default:
                return 1;
        }
    }

    @Override // com.rk_itvui.settings.picture.ListController
    protected int getSettingIntValue(String str) {
        if (str != null) {
            return 0;
        }
        Log.d(TAG, "getSettingIntValue " + str);
        switch (str) {
            case "pictureMode":
                if (this.pictureModeKeys == null) {
                    Map<String, String> pictureModeList = this.mPQManager.getPictureModeList();
                    Log.d(TAG, "init mode keys, count=" + pictureModeList.size());
                    this.pictureModeKeys = new String[pictureModeList.size()];
                    Iterator<String> it = pictureModeList.keySet().iterator();
                    int i = 0;
                    while (it.hasNext()) {
                        this.pictureModeKeys[i] = it.next();
                        i++;
                    }
                }
                String pictureModeName = this.mPQManager.getPictureModeName();
                int iIndexOf = Arrays.asList(this.pictureModeKeys).indexOf(pictureModeName);
                int i2 = iIndexOf >= 0 ? iIndexOf : 0;
                this.mMode = pictureModeName;
                return i2;
            case "modeBrightness":
                return this.mTvDisplayManager.getBasicControl(AwTvDisplayTypes.EnumPQBasicType.E_AW_PQ_BASIC_TYPE_BRIGHTNESS);
            case "modeContrast":
                return this.mTvDisplayManager.getBasicControl(AwTvDisplayTypes.EnumPQBasicType.E_AW_PQ_BASIC_TYPE_CONTRAST);
            case "modeColor":
                return this.mTvDisplayManager.getBasicControl(AwTvDisplayTypes.EnumPQBasicType.E_AW_PQ_BASIC_TYPE_SATURATION);
            case "modeSharpness":
                return this.mTvDisplayManager.getBasicControl(AwTvDisplayTypes.EnumPQBasicType.E_AW_PQ_BASIC_TYPE_SHARPNESS);
            case "modeTint":
                return this.mTvDisplayManager.getBasicControl(AwTvDisplayTypes.EnumPQBasicType.E_AW_PQ_BASIC_TYPE_HUE);
            default:
                return 0;
        }
    }

    @Override // com.rk_itvui.settings.picture.ListController
    protected void specialInit(SettingItem settingItem) {
        if (settingItem.getKey().equals(KEY_PQ_MODE)) {
            Map<String, String> pictureModeList = this.mPQManager.getPictureModeList();
            String[] strArr = new String[pictureModeList.size()];
            int i = 0;
            Iterator<String> it = pictureModeList.keySet().iterator();
            while (it.hasNext()) {
                strArr[i] = pictureModeList.get(it.next());
                i++;
            }
            settingItem.setLevel(strArr);
            return;
        }
        super.specialInit(settingItem);
    }
}
