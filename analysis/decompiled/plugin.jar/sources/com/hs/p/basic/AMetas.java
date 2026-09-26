package com.hs.p.basic;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import com.hs.p.common.utils.LOG;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class AMetas {
    private static final String KEY_CHANNEL = "com.hs.CID";
    private static final String TAG = "AMetas";
    public static final String VALUE_DEFAULT_CHANNEL = "assdk_huang";
    private static volatile String mChannel;

    private static String getAppMetaValue(Context context, String str, String str2) {
        Bundle bundle;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && (bundle = applicationInfo.metaData) != null && bundle.containsKey(str)) {
                return String.valueOf(applicationInfo.metaData.get(str));
            }
        } catch (Throwable unused) {
        }
        return str2;
    }

    public static String getChannel(Context context) {
        if (mChannel == null) {
            synchronized (AMetas.class) {
                if (mChannel == null) {
                    mChannel = getMetaAsString(context, KEY_CHANNEL, VALUE_DEFAULT_CHANNEL);
                }
            }
        }
        return mChannel;
    }

    private static String getMetaAsString(Context context, String str, String str2) {
        String appMetaValue = getAppMetaValue(context, str, str2);
        LOG.i(TAG, "get meta string: " + str + "=" + appMetaValue);
        return appMetaValue;
    }
}
