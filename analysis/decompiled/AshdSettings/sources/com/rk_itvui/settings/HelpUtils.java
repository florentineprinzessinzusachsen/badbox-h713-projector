package com.rk_itvui.settings;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class HelpUtils {
    private static final String PARAM_LANGUAGE_CODE = "hl";
    private static final String PARAM_VERSION = "version";
    private static final String TAG = "com.rk_itvui.settings.HelpUtils";
    private static String sCachedVersionCode;

    private HelpUtils() {
    }

    public static boolean prepareHelpMenuItem(Context context, MenuItem menuItem, int i) {
        return prepareHelpMenuItem(context, menuItem, context.getResources().getString(i));
    }

    public static boolean prepareHelpMenuItem(Context context, MenuItem menuItem, String str) {
        if (TextUtils.isEmpty(str)) {
            menuItem.setVisible(false);
            return false;
        }
        Intent intent = new Intent("android.intent.action.VIEW", uriWithAddedParameters(context, Uri.parse(str)));
        intent.setFlags(276824064);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            menuItem.setIntent(intent);
            menuItem.setShowAsAction(0);
            menuItem.setVisible(true);
            return true;
        }
        menuItem.setVisible(false);
        return false;
    }

    public static boolean copyFile(File file, File file2) {
        long length = file.length();
        LogUtils.LOGD(TAG, file.getAbsolutePath());
        LogUtils.LOGD(TAG, file2.getAbsolutePath());
        try {
            File parentFile = file2.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            byte[] bArr = new byte[1024];
            long j = 0;
            int i = 0;
            while (true) {
                int i2 = fileInputStream.read(bArr);
                if (i2 != -1) {
                    fileOutputStream.write(bArr, 0, i2);
                    j += (long) i2;
                    int i3 = (int) ((j / length) * 100.0f);
                    if (i3 != i) {
                        i = i3;
                    }
                } else {
                    fileOutputStream.flush();
                    fileInputStream.close();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            LogUtils.LOGD(TAG, e.getMessage());
            e.printStackTrace();
            return false;
        } catch (IOException e2) {
            LogUtils.LOGD(TAG, e2.getMessage());
            e2.printStackTrace();
            return false;
        }
    }

    public static Uri uriWithAddedParameters(Context context, Uri uri) {
        Uri.Builder builderBuildUpon = uri.buildUpon();
        builderBuildUpon.appendQueryParameter(PARAM_LANGUAGE_CODE, Locale.getDefault().toString());
        if (sCachedVersionCode == null) {
            try {
                sCachedVersionCode = Integer.toString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
                builderBuildUpon.appendQueryParameter(PARAM_VERSION, sCachedVersionCode);
            } catch (PackageManager.NameNotFoundException e) {
                Log.wtf(TAG, "Invalid package name for context", e);
            }
        } else {
            builderBuildUpon.appendQueryParameter(PARAM_VERSION, sCachedVersionCode);
        }
        return builderBuildUpon.build();
    }
}
