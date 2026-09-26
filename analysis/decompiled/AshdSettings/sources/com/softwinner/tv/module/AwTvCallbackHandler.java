package com.softwinner.tv.module;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class AwTvCallbackHandler {
    private static final String TAG = "AwTvCallbackHandler";

    public int notifyTvScanProgressInfo(int i, int i2, int i3, int i4, int i5) {
        Log.d(TAG, "(Default Handler) notifyScanNotification msg_id=" + i2 + "scanProgress=" + i3 + "channelNum=" + i4 + "argv=" + i5);
        return 0;
    }

    public int notifyTvScanProgramData(int i, Object obj) {
        Log.d(TAG, "(Default Handler) notifyScanProgramData type=" + i + "data=" + obj.toString());
        return 0;
    }

    public int notifyTvScanInfo(int i, int i2, Object obj) {
        Log.d(TAG, "(Default Handler) notifyScanInfo :  msg = " + i2);
        return 0;
    }

    public int notifyTvTuneState(int i, int i2, int i3) {
        Log.d(TAG, "(Default Handler) notifyTvTuneState: msg =" + i2 + "- stae=" + i3);
        return 0;
    }

    public int notifyTvSingalState(int i, int i2, int i3) {
        Log.d(TAG, "(Default Handler) notifyTvSingalState: msg =" + i2 + "- stae=" + i3);
        return 0;
    }

    public int notifyTvAfcCheckFrequncyChanged(int i, int i2, int i3) {
        Log.d(TAG, "(Default Handler) notifyTvAfcCheckFrequncyChanged: type =" + i + "- freq=" + i2);
        return 0;
    }

    public int notifyDVBInfo(int i, Object obj) {
        Log.d(TAG, "(Default Handler) notifyDVBInfo: msg =" + i);
        return 0;
    }
}
