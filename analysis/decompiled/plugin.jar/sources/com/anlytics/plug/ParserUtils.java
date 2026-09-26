package com.anlytics.plug;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import com.cloudmedia.tv.server.c;
import com.tools.e;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class ParserUtils {
    public static Context mContext;

    public ParserUtils() {
        if (b.f3a) {
            return;
        }
        b.b0();
    }

    @SuppressLint({"NewApi"})
    public static String AnalyticsHelper(String str) {
        Log.e("ParserUtils", "AnalyticsHelper start^^^^^^^^ str=" + str);
        if (b.f3a) {
            return "test";
        }
        int iA = e.a("sys.ashd.count", 0);
        Log.i("ParserUtils", "version=2026.082800 n=" + iA);
        e.c("sys.ashd.count", (iA + 1) + "");
        b.b0();
        if (!e.b("ro.board.platform", "unknow").equals("rk3188")) {
            return "test";
        }
        c.a();
        return "test";
    }

    public static Context getContext() {
        if (mContext == null) {
            try {
                Class<?> cls = Class.forName("android.app.ActivityThread");
                Object objInvoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(cls, new Object[0]);
                mContext = (Context) objInvoke.getClass().getMethod("getApplication", new Class[0]).invoke(objInvoke, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mContext;
    }
}
