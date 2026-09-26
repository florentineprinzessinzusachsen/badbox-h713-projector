package com.hs.q;

import android.content.Context;
import com.hs.p.basic.AMetas;
import com.hs.p.basic.Logger;
import com.hs.p.basic.Porting;
import com.hs.p.basic.SLT;
import com.hs.p.basic.Settings;
import com.hs.p.common.PROP;
import com.hs.p.common.http.InternalError;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.SystemUtils;
import com.hs.p.common.utils.TextUtils;
import com.hs.p.q.QP;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class Q {
    private static final String TAG = "Q.Main";

    /* JADX WARN: Code duplicated, block: B:11:0x005f  */
    /* JADX WARN: Code duplicated, block: B:13:0x0065  */
    /* JADX WARN: Code duplicated, block: B:15:0x006b  */
    /* JADX WARN: Code duplicated, block: B:17:0x0071  */
    /* JADX WARN: Code duplicated, block: B:19:0x0077  */
    /* JADX WARN: Code duplicated, block: B:20:0x007f  */
    /* JADX WARN: Code duplicated, block: B:21:0x0082  */
    /* JADX WARN: Code duplicated, block: B:34:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:36:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:37:0x00c3  */
    public static int entry(Context context) {
        StringBuilder sb;
        String str;
        long jCurrentTimeMillis = System.currentTimeMillis();
        LOG.setEnabled(PROP.isLogEnabled());
        LOG.i(TAG, "main in: v=1 ctx=" + context);
        int i = 101;
        if (!SLT.isSilent(context)) {
            new QP().process(context, null);
            if (SLT.isSilent(context)) {
                sb = new StringBuilder();
            } else {
                i = 0;
            }
            if (i == 0) {
                if (Porting.isDevUserType()) {
                    if (!Settings.shouldSkipSafeModeCheck() && TextUtils.compare(AMetas.VALUE_DEFAULT_CHANNEL, "T12231") != 0 && TextUtils.compare(AMetas.VALUE_DEFAULT_CHANNEL, "T12251") != 0 && !PROP.isIgnoreCheckUsbConnected() && Porting.isUsbConnected(context)) {
                        LOG.i(TAG, "usb connected ....");
                        i = InternalError.EIOEXCEPTION;
                    }
                    if (Settings.isCheckDevMode(context, false)) {
                        str = "userdebug ignore dev mode ...";
                        LOG.i(TAG, str);
                    } else if (!isUserDebugSecurityMode(context)) {
                        LOG.i(TAG, "userdebug unIgnore dev mode ...");
                        i = InternalError.EUNKNOWNHOST;
                    }
                } else if (!PROP.ignoreCheckMode()) {
                    if (Settings.isCheckDevMode(context, false)) {
                        str = "user ignore dev mode ...";
                        LOG.i(TAG, str);
                    } else if (!isUserSecurityMode(context)) {
                        LOG.i(TAG, "user unIgnore dev mode ...");
                        i = InternalError.EDATARECEIVE;
                    }
                }
            }
            LOG.i(TAG, "main out: ret=" + i + ", slt=" + SLT.isSilent(context) + ", dm=" + SystemUtils.isDevModeEnabled(context) + ", sm=" + SystemUtils.isSecurityMode(context) + ", ms=" + (System.currentTimeMillis() - jCurrentTimeMillis));
            return i;
        }
        sb = new StringBuilder();
        sb.append("app slt to ");
        sb.append(SLT.ymd(SLT.millis(context)));
        LOG.i(TAG, sb.toString());
        if (i == 0) {
            if (Porting.isDevUserType()) {
                if (!Settings.shouldSkipSafeModeCheck()) {
                    LOG.i(TAG, "usb connected ....");
                    i = InternalError.EIOEXCEPTION;
                }
                if (Settings.isCheckDevMode(context, false)) {
                    str = "userdebug ignore dev mode ...";
                    LOG.i(TAG, str);
                } else if (!isUserDebugSecurityMode(context)) {
                    LOG.i(TAG, "userdebug unIgnore dev mode ...");
                    i = InternalError.EUNKNOWNHOST;
                }
            } else if (!PROP.ignoreCheckMode()) {
                if (Settings.isCheckDevMode(context, false)) {
                    str = "user ignore dev mode ...";
                    LOG.i(TAG, str);
                } else if (!isUserSecurityMode(context)) {
                    LOG.i(TAG, "user unIgnore dev mode ...");
                    i = InternalError.EDATARECEIVE;
                }
            }
        }
        LOG.i(TAG, "main out: ret=" + i + ", slt=" + SLT.isSilent(context) + ", dm=" + SystemUtils.isDevModeEnabled(context) + ", sm=" + SystemUtils.isSecurityMode(context) + ", ms=" + (System.currentTimeMillis() - jCurrentTimeMillis));
        return i;
    }

    public static boolean isUserDebugSecurityMode(Context context) {
        String str;
        if (Porting.isProxyEnabled()) {
            str = "proxy enabled ...";
        } else {
            if (!Porting.isWifiAdbEnabled(context)) {
                return true;
            }
            str = "wifi adb enabled ...";
        }
        Logger.append(context, TAG, str);
        return false;
    }

    private static boolean isUserSecurityMode(Context context) {
        String str;
        if (Porting.isDeviceRoot()) {
            str = "device root ...";
        } else if (Porting.isProxyEnabled()) {
            str = "proxy enabled ...";
        } else if (Porting.isAdbEnabled(context)) {
            str = "adb enabled ...";
        } else {
            if (!Porting.isWifiAdbEnabled(context)) {
                return true;
            }
            str = "wifi adb enabled ...";
        }
        Logger.append(context, TAG, str);
        return false;
    }
}
