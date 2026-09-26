package com.rk_itvui.settings.developer;

import android.graphics.Bitmap;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class SettingItem {
    public boolean mAdd;
    private Bitmap mBitmap;
    public boolean mClick;
    public int mId;
    public int mLevel;
    public int mParentId;
    public SettingItemClick mSettingItemClick;
    private String mStatusText;
    private String mSummary;
    public String mText;
    public View mView;

    public SettingItem(int i, int i2, int i3) {
        this.mLevel = 0;
        this.mParentId = -1;
        this.mId = -1;
        this.mClick = true;
        this.mView = null;
        this.mSettingItemClick = null;
        this.mAdd = false;
        this.mSummary = null;
        this.mStatusText = null;
        this.mBitmap = null;
        this.mText = null;
        this.mLevel = i;
        this.mParentId = i2;
        this.mId = i3;
    }

    public SettingItem(int i, int i2, int i3, String str, boolean z) {
        this.mLevel = 0;
        this.mParentId = -1;
        this.mId = -1;
        this.mClick = true;
        this.mView = null;
        this.mSettingItemClick = null;
        this.mAdd = false;
        this.mSummary = null;
        this.mStatusText = null;
        this.mBitmap = null;
        this.mText = null;
        this.mLevel = i;
        this.mParentId = i2;
        this.mId = i3;
        this.mText = str;
        this.mAdd = z;
    }

    public SettingItem(int i, int i2, int i3, String str, String str2, Bitmap bitmap) {
        this.mLevel = 0;
        this.mParentId = -1;
        this.mId = -1;
        this.mClick = true;
        this.mView = null;
        this.mSettingItemClick = null;
        this.mAdd = false;
        this.mSummary = null;
        this.mStatusText = null;
        this.mBitmap = null;
        this.mText = null;
        this.mLevel = i;
        this.mParentId = i2;
        this.mId = i3;
        this.mStatusText = str;
        this.mSummary = str2;
        this.mBitmap = bitmap;
    }

    public void setClickable(boolean z) {
        this.mClick = z;
    }

    public boolean getClickable() {
        return this.mClick;
    }

    public void setTitle(String str) {
        this.mText = str;
    }

    public SettingItem setView(View view) {
        this.mView = view;
        this.mView.setTag(Integer.valueOf(this.mId));
        return this;
    }

    public View getView() {
        return this.mView;
    }

    public boolean isAdd() {
        return this.mAdd;
    }

    public void setAddFlag(boolean z) {
        this.mAdd = z;
    }

    public void setOnSettingItemClick(SettingItemClick settingItemClick) {
        this.mSettingItemClick = settingItemClick;
    }

    public boolean onSettingItemClick(int i) {
        if (!this.mClick || this.mSettingItemClick == null) {
            return false;
        }
        this.mSettingItemClick.onItemClick(this, i);
        return true;
    }

    public boolean onSettingItemLongClick(int i) {
        if (!this.mClick || this.mSettingItemClick == null) {
            return false;
        }
        this.mSettingItemClick.onItemLongClick(this, i);
        return true;
    }

    public int getId() {
        return this.mId;
    }

    public String getStatus() {
        return this.mStatusText;
    }

    public void setStatus(String str) {
        this.mStatusText = str;
    }

    public void setSummary(String str) {
        this.mSummary = str;
    }

    public String getSummary() {
        return this.mSummary;
    }

    public Bitmap getDrawable() {
        return this.mBitmap;
    }

    public void setDrawable(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }
}
