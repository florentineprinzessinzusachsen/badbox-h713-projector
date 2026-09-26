package com.rk_itvui.settings.picture.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class SettingItem {
    public static final int TYPE_DO = 1;
    public static final int TYPE_LEVEL = 4;
    public static final int TYPE_SUB_MENU = 2;
    public static final int TYPE_TEXT = 5;
    public static final int TYPE_UNKNOW = 0;
    public static final int TYPE_VALUE = 3;
    private static final String sPackageName = "com.softwinner.tv.factorymenu.";
    private static final Map<String, Integer> sTypeString = new HashMap<String, Integer>() { // from class: com.rk_itvui.settings.picture.model.SettingItem.1
        {
            put("do", 1);
            put("sub_menu", 2);
            put("value", 3);
            put("level", 4);
            put("text", 5);
        }
    };
    private ArrayList<SettingItem> mChildList;
    private String mClassName;
    private String mFieldLevel;
    private String mFieldTitle;
    private String mGroup;
    private String mKey;
    private String[] mLevel;
    private int mMaxValue;
    private int mMinValue;
    boolean mModifiable;
    private String mText;
    private String mTitle;
    private int mType;
    private int mValueStep;
    private int mValueTest;

    public SettingItem(String str, String str2, int i) {
        this(str, str2, i, (String) null);
    }

    public SettingItem(String str, String str2, int i, String str3) {
        this.mType = 0;
        this.mValueTest = 1;
        this.mValueStep = 1;
        this.mModifiable = true;
        this.mChildList = new ArrayList<>();
        this.mKey = str;
        this.mTitle = str2;
        this.mType = i;
        if (this.mType == 2) {
            this.mClassName = sPackageName + str3;
            return;
        }
        if (this.mType == 5) {
            this.mText = str3;
        }
    }

    public SettingItem(String str, String str2, int i, int i2) {
        this.mType = 0;
        this.mValueTest = 1;
        this.mValueStep = 1;
        this.mModifiable = true;
        this.mChildList = new ArrayList<>();
        this.mKey = str;
        this.mTitle = str2;
        this.mMinValue = i;
        this.mMaxValue = i2;
        this.mType = 3;
    }

    public SettingItem(String str, String str2, int i, int i2, int i3) {
        this.mType = 0;
        this.mValueTest = 1;
        this.mValueStep = 1;
        this.mModifiable = true;
        this.mChildList = new ArrayList<>();
        this.mKey = str;
        this.mTitle = str2;
        this.mMinValue = i;
        this.mMaxValue = i2;
        this.mValueStep = i3;
        this.mType = 3;
    }

    public SettingItem(String str, String str2, String[] strArr) {
        this(str, str2, strArr, 0);
    }

    public SettingItem(String str, String str2, String[] strArr, int i) {
        this.mType = 0;
        this.mValueTest = 1;
        this.mValueStep = 1;
        this.mModifiable = true;
        this.mChildList = new ArrayList<>();
        this.mKey = str;
        this.mTitle = str2;
        this.mType = 4;
        setLevel(strArr);
        this.mValueTest = i;
    }

    public SettingItem(String str, String str2, String[] strArr, int i, int i2, int i3) {
        this.mType = 0;
        this.mValueTest = 1;
        this.mValueStep = 1;
        this.mModifiable = true;
        this.mChildList = new ArrayList<>();
        this.mKey = str;
        this.mTitle = str2;
        this.mType = 4;
        setLevel(strArr);
        this.mValueTest = i;
        this.mMinValue = i2;
        this.mMaxValue = i3;
    }

    public SettingItem(String str, int i, String str2) {
        this(str, i, str2, 0, 0);
    }

    public SettingItem(String str, int i, String str2, String str3, String str4) {
        this.mType = 0;
        this.mValueTest = 1;
        this.mValueStep = 1;
        this.mModifiable = true;
        this.mChildList = new ArrayList<>();
        this.mKey = str;
        this.mType = i;
        this.mFieldTitle = str2;
        this.mClassName = sPackageName + str4;
        if (str4 == null || str4.equals("")) {
            return;
        }
        String[] strArrSplit = str4.split("\\.");
        this.mGroup = strArrSplit[strArrSplit.length - 1];
    }

    public SettingItem(String str, int i, String str2, int i2, int i3) {
        this.mType = 0;
        this.mValueTest = 1;
        this.mValueStep = 1;
        this.mModifiable = true;
        this.mChildList = new ArrayList<>();
        this.mKey = str;
        this.mType = i;
        this.mFieldTitle = str2;
        this.mMinValue = i2;
        this.mMaxValue = i3;
    }

    public SettingItem(String str, int i, String str2, int i2, int i3, int i4) {
        this.mType = 0;
        this.mValueTest = 1;
        this.mValueStep = 1;
        this.mModifiable = true;
        this.mChildList = new ArrayList<>();
        this.mKey = str;
        this.mType = i;
        this.mFieldTitle = str2;
        this.mMinValue = i2;
        this.mMaxValue = i3;
        if (i == 3) {
            this.mValueStep = i4;
        }
    }

    public SettingItem(String str, int i, String str2, String str3) {
        this.mType = 0;
        this.mValueTest = 1;
        this.mValueStep = 1;
        this.mModifiable = true;
        this.mChildList = new ArrayList<>();
        this.mKey = str;
        this.mType = i;
        this.mFieldTitle = str2;
        this.mFieldLevel = str3;
    }

    public SettingItem(SettingItem settingItem) {
        this.mType = 0;
        this.mValueTest = 1;
        this.mValueStep = 1;
        this.mModifiable = true;
        this.mChildList = new ArrayList<>();
        this.mKey = settingItem.getKey();
        this.mTitle = settingItem.getTitle();
        this.mType = settingItem.getType();
        this.mMinValue = settingItem.getMinValue();
        this.mMaxValue = settingItem.getMinValue();
        this.mClassName = settingItem.getClassName();
        this.mText = settingItem.getStringValue();
        this.mValueTest = settingItem.getIntValue();
        this.mChildList = settingItem.getChildList();
        this.mGroup = settingItem.getGroup();
        this.mFieldTitle = settingItem.getFieldTitle();
        this.mFieldLevel = settingItem.getFieldLevel();
    }

    public String toString() {
        return "Setting [key=" + this.mKey + ", title=" + this.mTitle + "\n type=" + this.mType + "\n]";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public SettingItem m8clone() {
        return new SettingItem(this);
    }

    public String getKey() {
        return this.mKey;
    }

    public int getType() {
        return this.mType;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public int getMaxValue() {
        return this.mMaxValue;
    }

    public int getMinValue() {
        return this.mMinValue;
    }

    public int getValueStep() {
        return this.mValueStep;
    }

    public int getIntValue() {
        this.mKey.hashCode();
        return this.mValueTest;
    }

    public String getStringValue() {
        int i = this.mType;
        if (i == 5) {
            return this.mText;
        }
        switch (i) {
            case 1:
                return ">>>";
            case 2:
                return "--->";
            default:
                this.mKey.hashCode();
                return "";
        }
    }

    public String getLevelValue() {
        this.mKey.hashCode();
        int i = this.mValueTest;
        if (i < 0) {
            i = 0;
        }
        return this.mLevel[i];
    }

    public String getClassName() {
        if (this.mType == 2) {
            return this.mClassName;
        }
        return null;
    }

    public ArrayList<SettingItem> getChildList() {
        return this.mChildList;
    }

    public String getGroup() {
        return this.mGroup;
    }

    public String getFieldTitle() {
        return this.mFieldTitle;
    }

    public String getFieldLevel() {
        return this.mFieldLevel;
    }

    public boolean setValue(int i) {
        if (this.mValueTest == i) {
            return false;
        }
        this.mValueTest = i;
        return true;
    }

    public void setStringValue(String str) {
        int i = this.mType;
        if (i != 2) {
            if (i != 5) {
                return;
            }
            this.mText = str;
        } else {
            this.mClassName = sPackageName + str;
        }
    }

    public void setType(int i) {
        if (i < 0 || i > 5) {
            return;
        }
        this.mType = i;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setLevel(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            this.mLevel = new String[]{"\\"};
        } else {
            this.mLevel = strArr;
        }
        this.mMinValue = 0;
        this.mMaxValue = this.mLevel.length - 1;
    }

    public void setModifiable(boolean z) {
        this.mModifiable = z;
    }

    public boolean isModifiable() {
        return this.mModifiable;
    }

    public boolean isMax() {
        return this.mValueTest == this.mLevel.length - 1;
    }

    public boolean isMin() {
        return this.mValueTest == this.mMinValue;
    }

    public static int stringToType(String str) {
        if (sTypeString.containsKey(str)) {
            return sTypeString.get(str).intValue();
        }
        return 0;
    }
}
