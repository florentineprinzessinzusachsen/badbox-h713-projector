package com.rk_itvui.settings.language;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class KeyBoardSetting {
    private Context mContext;
    private Handler mHandler;
    private int mId;

    public KeyBoardSetting(Context context, Handler handler, int i) {
        this.mContext = null;
        this.mHandler = null;
        this.mId = -1;
        this.mContext = context;
        this.mHandler = handler;
        this.mId = i;
    }

    public String getKeyBoardDefault() {
        Context context = this.mContext;
        ContentResolver contentResolver = context.getContentResolver();
        PackageManager packageManager = context.getPackageManager();
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        List<InputMethodInfo> inputMethodList = inputMethodManager.getInputMethodList();
        if (contentResolver == null || inputMethodList == null) {
            return null;
        }
        String string = Settings.Secure.getString(contentResolver, "default_input_method");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        for (InputMethodInfo inputMethodInfo : inputMethodList) {
            if (string.equals(inputMethodInfo.getId())) {
                CharSequence charSequenceLoadLabel = inputMethodInfo.loadLabel(packageManager);
                InputMethodSubtype currentInputMethodSubtype = inputMethodManager.getCurrentInputMethodSubtype();
                CharSequence displayName = currentInputMethodSubtype != null ? currentInputMethodSubtype.getDisplayName(context, inputMethodInfo.getPackageName(), inputMethodInfo.getServiceInfo().applicationInfo) : null;
                if (currentInputMethodSubtype != null) {
                    CharSequence[] charSequenceArr = new CharSequence[2];
                    charSequenceArr[0] = displayName;
                    charSequenceArr[1] = (TextUtils.isEmpty(charSequenceLoadLabel) || charSequenceLoadLabel.equals(displayName)) ? "" : " - " + ((Object) charSequenceLoadLabel);
                    charSequenceLoadLabel = TextUtils.concat(charSequenceArr);
                }
                return charSequenceLoadLabel.toString();
            }
        }
        return null;
    }

    public void settingKeyBoard() {
        KeyBoardSettingAlterDialogActivity.setHandler(this.mHandler);
        this.mContext.startActivity(new Intent(this.mContext, (Class<?>) KeyBoardSettingAlterDialogActivity.class));
    }
}
