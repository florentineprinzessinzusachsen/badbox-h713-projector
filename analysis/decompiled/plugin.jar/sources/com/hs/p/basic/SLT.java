package com.hs.p.basic;

import android.content.Context;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.TextUtils;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class SLT {
    private static final String TAG = "SLT";
    private static volatile long mSilentToInMillis = -1;

    public static void clear(Context context) {
        synchronized (SLT.class) {
            Settings.putSilentToTime(context, 0L);
            mSilentToInMillis = 0L;
        }
    }

    public static boolean isSilent(Context context) {
        long jMillis = millis(context);
        long jCurrentTimeMillis = System.currentTimeMillis();
        return 0 < jMillis && jCurrentTimeMillis <= jMillis && jMillis - jCurrentTimeMillis < 5184000000L;
    }

    public static long millis(Context context) {
        if (mSilentToInMillis < 0) {
            synchronized (SLT.class) {
                if (mSilentToInMillis < 0) {
                    mSilentToInMillis = Settings.getSilentToTime(context, 0L);
                }
            }
        }
        return mSilentToInMillis;
    }

    public static void silentTo(Context context, long j) {
        if (j > 0) {
            synchronized (SLT.class) {
                LOG.i(TAG, "app slt to: " + ymd(j));
                Settings.putSilentToTime(context, j);
                mSilentToInMillis = j;
            }
        }
    }

    public static String ymd(long j) {
        return TextUtils.TSTR("yyyy/MM/dd HH:mm:ss", j);
    }
}
