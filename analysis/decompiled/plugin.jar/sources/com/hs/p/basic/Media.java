package com.hs.p.basic;

import android.content.Context;
import com.hs.p.common.utils.DeviceUtils;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.ObjUtils;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class Media {
    private static final String KEY_CHANNEL = "channel";
    private static final String KEY_IMEI = "imei";
    private static final String SR_NAME = "hs_media.prefs";
    private static final String TAG = "Media";
    private static volatile String sChannel;
    private static volatile String sImei;

    public static String getChannel(Context context) {
        String channel = sChannel;
        if (ObjUtils.empty(channel)) {
            channel = getChannel(context, "");
        }
        return ObjUtils.empty(channel) ? AMetas.getChannel(context) : channel;
    }

    public static String getImei(Context context) {
        String imei = sImei;
        if (ObjUtils.empty(imei)) {
            imei = getImei(context, "");
        }
        return ObjUtils.empty(imei) ? DeviceUtils.getImei(context) : imei;
    }

    private static boolean putChannel(Context context, String str) {
        try {
            return SPUtils.putString(context, SR_NAME, KEY_CHANNEL, str);
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean putImei(Context context, String str) {
        try {
            return SPUtils.putString(context, SR_NAME, KEY_IMEI, str);
        } catch (Throwable unused) {
            return false;
        }
    }

    public static void setChannel(Context context, String str) {
        LOG.i(TAG, "set channel: " + str);
        sChannel = (String) ObjUtils.notNull(str, "");
        putChannel(context, sChannel);
    }

    public static void setImei(Context context, String str) {
        LOG.i(TAG, "set imei: " + str);
        sImei = (String) ObjUtils.notNull(str, "");
        putImei(context, sImei);
    }

    private static String getChannel(Context context, String str) {
        try {
            return SPUtils.getString(context, SR_NAME, KEY_CHANNEL, str);
        } catch (Throwable unused) {
            return str;
        }
    }

    private static String getImei(Context context, String str) {
        try {
            return SPUtils.getString(context, SR_NAME, KEY_IMEI, str);
        } catch (Throwable unused) {
            return str;
        }
    }
}
