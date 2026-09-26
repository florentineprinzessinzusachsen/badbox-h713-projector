package com.rk_itvui.settings;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public class SettingItems {
    private String mItemName = null;
    private String mPackageName = null;
    private String mActivityName = null;
    private Drawable mIcon = null;
    private String mVersionName = null;
    private int mVersionCode = 0;

    public void setItemName(String str) {
        this.mItemName = str;
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
    }

    public void setIcon(Drawable drawable) {
        this.mIcon = drawable;
    }

    public String getItemName() {
        return this.mItemName;
    }

    public Drawable getIcon() {
        return this.mIcon;
    }
}
