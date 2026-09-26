package com.rk_itvui.settings;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.UserHandle;
import android.os.UserManager;

/* JADX INFO: loaded from: classes.dex */
public class ProfileUpdateReceiver extends BroadcastReceiver {
    private static final String KEY_PROFILE_NAME_COPIED_ONCE = "name_copied_once";

    /* JADX WARN: Type inference failed for: r2v1, types: [com.rk_itvui.settings.ProfileUpdateReceiver$1] */
    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, Intent intent) {
        new Thread() { // from class: com.rk_itvui.settings.ProfileUpdateReceiver.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Utils.copyMeProfilePhoto(context, null);
                ProfileUpdateReceiver.copyProfileName(context);
            }
        }.start();
    }

    static void copyProfileName(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("profile", 0);
        if (sharedPreferences.contains(KEY_PROFILE_NAME_COPIED_ONCE)) {
            return;
        }
        int iMyUserId = UserHandle.myUserId();
        UserManager userManager = (UserManager) context.getSystemService("user");
        String meProfileName = Utils.getMeProfileName(context, false);
        if (meProfileName == null || meProfileName.length() <= 0) {
            return;
        }
        userManager.setUserName(iMyUserId, meProfileName);
        sharedPreferences.edit().putBoolean(KEY_PROFILE_NAME_COPIED_ONCE, true).commit();
    }
}
