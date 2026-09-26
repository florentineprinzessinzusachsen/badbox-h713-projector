package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class PrefOperate {
    public static String getAppKey(Context context) {
        return CooperService.instance().getAppKey(context);
    }

    public static void loadMetaDataConfig(Context context) {
        SendStrategyEnum sendStrategyEnum = SendStrategyEnum.APP_START;
        try {
            String strA = bb.a(context, Config.EXCEPTION_LOG_META_NAME);
            if (!TextUtils.isEmpty(strA) && "true".equals(strA)) {
                ExceptionAnalysis.getInstance().openExceptionAnalysis(context, false);
            }
        } catch (Exception unused) {
        }
        try {
            String strA2 = bb.a(context, Config.SEND_STRATEGY_META_NAME);
            if (!TextUtils.isEmpty(strA2)) {
                if (strA2.equals(SendStrategyEnum.APP_START.name())) {
                    sendStrategyEnum = SendStrategyEnum.APP_START;
                    av.a().a(context, sendStrategyEnum.ordinal());
                } else if (strA2.equals(SendStrategyEnum.ONCE_A_DAY.name())) {
                    sendStrategyEnum = SendStrategyEnum.ONCE_A_DAY;
                    av.a().a(context, sendStrategyEnum.ordinal());
                    av.a().b(context, 24);
                } else if (strA2.equals(SendStrategyEnum.SET_TIME_INTERVAL.name())) {
                    sendStrategyEnum = SendStrategyEnum.SET_TIME_INTERVAL;
                    av.a().a(context, sendStrategyEnum.ordinal());
                }
            }
        } catch (Exception unused2) {
        }
        try {
            String strA3 = bb.a(context, Config.TIME_INTERVAL_META_NAME);
            if (!TextUtils.isEmpty(strA3)) {
                int i = Integer.parseInt(strA3);
                if (sendStrategyEnum.ordinal() == SendStrategyEnum.SET_TIME_INTERVAL.ordinal() && i > 0 && i <= 24) {
                    av.a().b(context, i);
                }
            }
        } catch (Exception unused3) {
        }
        try {
            String strA4 = bb.a(context, Config.ONLY_WIFI_META_NAME);
            if (TextUtils.isEmpty(strA4)) {
                return;
            }
            if ("true".equals(strA4)) {
                av.a().a(context, true);
            } else if ("false".equals(strA4)) {
                av.a().a(context, false);
            }
        } catch (Exception unused4) {
        }
    }

    public static void setAppChannel(String str) {
        if (str == null || str.equals("")) {
            am.c().c("[WARNING] The channel you have set is empty");
        }
        CooperService.instance().getHeadObject().l = str;
    }

    public static void setAppKey(String str) {
        CooperService.instance().getHeadObject().f3366e = str;
    }

    public static void setAppChannel(Context context, String str, boolean z) {
        if (str == null || str.equals("")) {
            am.c().c("[WARNING] The channel you have set is empty");
        }
        CooperService.instance().getHeadObject().l = str;
        if (z && str != null && !str.equals("")) {
            av.a().d(context, str);
            av.a().b(context, true);
        }
        if (z) {
            return;
        }
        av.a().d(context, "");
        av.a().b(context, false);
    }
}
