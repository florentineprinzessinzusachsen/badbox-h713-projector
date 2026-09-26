package com.rk_itvui.settings.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public class ApplicationSetting {
    private Context mContext;
    private Handler mHandler;

    public ApplicationSetting(Context context, Handler handler) {
        this.mContext = null;
        this.mHandler = null;
        this.mContext = context;
        this.mHandler = handler;
    }

    public void SettingApplication() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setClass(this.mContext, ManageApplications.class);
        this.mContext.startActivity(intent);
    }
}
