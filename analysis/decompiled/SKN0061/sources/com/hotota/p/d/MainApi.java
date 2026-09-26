package com.hotota.p.d;

import android.content.Context;
import android.os.Process;
import com.hotota.p.d.common.utils.LOG;

/* JADX INFO: loaded from: classes.dex */
public class MainApi {
    private static final String TAG = "MainApi";
    private static volatile MainThread mMainThread;

    public static String start_W0035(Context context, String str, String str2, Context context2) {
        LOG("start: dex=" + str + ", output=" + str2);
        synchronized (MainApi.class) {
            if (mMainThread == null) {
                MainThread mainThread = new MainThread(context.getApplicationContext(), context2);
                mainThread.start();
                mMainThread = mainThread;
            }
        }
        return "OK";
    }

    public static String stop(Context context, String str, String str2, Context context2) {
        LOG("stop:  dex=" + str + ", output=" + str2);
        try {
            if (mMainThread != null) {
                mMainThread.close();
            }
        } catch (Throwable unused) {
        }
        mMainThread = null;
        return "OK";
    }

    private static void LOG(String str) {
        LOG.i(TAG, strEnv() + str);
    }

    private static String strEnv() {
        return "[P:" + Process.myPid() + " T:" + Process.myTid() + "]";
    }
}
