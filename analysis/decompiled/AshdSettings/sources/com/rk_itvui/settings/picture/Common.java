package com.rk_itvui.settings.picture;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.ashd.settings.R;

/* JADX INFO: loaded from: classes.dex */
public class Common {
    public static final boolean DEBUG = true;
    public static final int RET_FAIL = -1;
    public static final int RET_SUCCESS = 0;
    public static final String Source_Media = "media";

    public static void showTips(Context context, String str) {
        new AlertDialog.Builder(context).setMessage(str).setCancelable(true).setPositiveButton(context.getString(R.string.button_positive), (DialogInterface.OnClickListener) null).show();
    }
}
