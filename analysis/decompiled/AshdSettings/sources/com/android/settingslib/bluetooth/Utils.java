package com.android.settingslib.bluetooth;

import android.content.Context;
import com.android.settingslib.R;

/* JADX INFO: loaded from: classes.dex */
public class Utils {
    public static final boolean D = true;
    public static final boolean V = false;
    private static ErrorListener sErrorListener;

    public interface ErrorListener {
        void onShowError(Context context, String str, int i);
    }

    public static int getConnectionStateSummary(int i) {
        switch (i) {
            case 0:
                return R.string.bluetooth_disconnected;
            case 1:
                return R.string.bluetooth_connecting;
            case 2:
                return R.string.bluetooth_connected;
            case 3:
                return R.string.bluetooth_disconnecting;
            default:
                return 0;
        }
    }

    static void showError(Context context, String str, int i) {
        if (sErrorListener != null) {
            sErrorListener.onShowError(context, str, i);
        }
    }

    public static void setErrorListener(ErrorListener errorListener) {
        sErrorListener = errorListener;
    }
}
