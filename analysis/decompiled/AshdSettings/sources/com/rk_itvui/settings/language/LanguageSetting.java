package com.rk_itvui.settings.language;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class LanguageSetting {
    private Context mContext;
    private Handler mHandler;

    private void LOGD(String str) {
        Log.d("LanguageSetting", str);
    }

    public LanguageSetting(Context context, Handler handler) {
        this.mContext = null;
        this.mHandler = null;
        this.mContext = context;
        this.mHandler = handler;
    }

    public String getDefaultLanguageSetting() {
        Configuration configuration = this.mContext.getResources().getConfiguration();
        String displayName = configuration.locale.getDisplayName(configuration.locale);
        if (displayName == null || displayName.length() <= 1) {
            return "";
        }
        String str = Character.toUpperCase(displayName.charAt(0)) + displayName.substring(1);
        LOGD("getDefaultLanguageSetting(),local = " + str);
        return str;
    }

    public void settingLanguage() {
        this.mContext.startActivity(new Intent(this.mContext, (Class<?>) LanguageSettingAlterDialogActivity.class));
    }
}
