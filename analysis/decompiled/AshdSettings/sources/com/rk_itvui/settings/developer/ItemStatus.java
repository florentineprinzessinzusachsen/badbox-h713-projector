package com.rk_itvui.settings.developer;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public class ItemStatus {
    private Drawable mDrawable;
    private String mStatusText;

    public ItemStatus(String str, Drawable drawable) {
        this.mStatusText = null;
        this.mDrawable = null;
        this.mStatusText = str;
        this.mDrawable = drawable;
    }

    public String getString() {
        return this.mStatusText;
    }

    public void setString(String str) {
        this.mStatusText = str;
    }

    public Drawable getDrawable() {
        return this.mDrawable;
    }

    public void setDrawable(Drawable drawable) {
        this.mDrawable = drawable;
    }
}
