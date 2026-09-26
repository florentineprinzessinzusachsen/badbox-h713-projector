package com.rk_itvui.settings.developer;

import android.util.Log;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class SettingItemAddManager {
    private static final int MAXID = Integer.MAX_VALUE;
    private static final int MINID = 2113929215;
    private static SettingItemAddManager mInstance;
    private SettingItem mSettingItem = null;
    private int mId = -1;
    private ArrayList<Integer> mUserSettingId = new ArrayList<>();
    private Map<String, ArrayList<SettingItem>> mMap = null;

    public static SettingItemAddManager getInstance() {
        if (mInstance == null) {
            mInstance = new SettingItemAddManager();
        }
        return mInstance;
    }

    public boolean addSettingItem(String str, SettingItem settingItem, int i, boolean z) {
        ArrayList<SettingItem> arrayList;
        if (this.mMap == null || settingItem == null || str == null || (arrayList = this.mMap.get(str)) == null) {
            return false;
        }
        int i2 = 0;
        while (i2 < arrayList.size() && arrayList.get(i2).mId != i) {
            i2++;
        }
        if (arrayList.size() == i2) {
            return false;
        }
        if (i2 == 0) {
            arrayList.add(settingItem);
        } else if (z) {
            arrayList.add(i2 + 1, settingItem);
        } else {
            arrayList.add(i2, settingItem);
        }
        settingItem.setAddFlag(true);
        return true;
    }

    public boolean deleteSettingItem(String str, int i) {
        if (this.mMap == null) {
            return false;
        }
        Log.d("SettingItemAddManager", "deleteSettingItem(), content = " + str + ",id = " + i);
        ArrayList<SettingItem> arrayList = this.mMap.get(str);
        if (arrayList != null) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                SettingItem settingItem = arrayList.get(i2);
                Log.d("SettingItemAddManager", "deleteSettingItem(),item id = " + settingItem.mId + ", item is add? " + settingItem.isAdd());
                if (settingItem.mId == i && settingItem.isAdd()) {
                    arrayList.remove(settingItem);
                    Log.d("SettingItemAddManager", "delete settingItem' id = " + i + " from mMap");
                    for (int i3 = 0; i3 < this.mUserSettingId.size(); i3++) {
                        Integer num = this.mUserSettingId.get(i3);
                        if (i == num.intValue()) {
                            this.mUserSettingId.remove(num);
                            Log.d("SettingItemAddManager", "delete id = " + i + " from mUserSettingId");
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean deleteSettingItem(String str, SettingItem settingItem) {
        if (settingItem == null || str == null) {
            return false;
        }
        return deleteSettingItem(str, settingItem.getId());
    }

    public int findID() {
        for (int i = Integer.MAX_VALUE; i > MINID; i--) {
            if (!findID(i)) {
                this.mUserSettingId.add(Integer.valueOf(i));
                dump();
                return i;
            }
        }
        return -1;
    }

    private boolean findID(int i) {
        for (int size = this.mUserSettingId.size() - 1; size >= 0; size--) {
            if (i == this.mUserSettingId.get(size).intValue()) {
                return true;
            }
        }
        return false;
    }

    public void setContentMap(Map<String, ArrayList<SettingItem>> map) {
        this.mMap = map;
    }

    private void dump() {
        for (int size = this.mUserSettingId.size() - 1; size >= 0; size += -1) {
            Log.d("SettingItemAddManager", "id = " + this.mUserSettingId.get(size).intValue());
        }
    }
}
