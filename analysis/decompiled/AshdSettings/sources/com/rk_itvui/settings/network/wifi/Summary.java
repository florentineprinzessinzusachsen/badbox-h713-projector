package com.rk_itvui.settings.network.wifi;

import android.content.Context;
import android.net.NetworkInfo;
import com.ashd.settings.R;

/* JADX INFO: loaded from: classes.dex */
public class Summary {
    public static String get(Context context, String str, NetworkInfo.DetailedState detailedState) {
        String[] stringArray = context.getResources().getStringArray(str == null ? R.array.wifi_status : R.array.wifi_status_with_ssid);
        int iOrdinal = detailedState.ordinal();
        if (iOrdinal >= stringArray.length || stringArray[iOrdinal].length() == 0) {
            return null;
        }
        return String.format(stringArray[iOrdinal], str);
    }

    public static String get(Context context, NetworkInfo.DetailedState detailedState) {
        return get(context, null, detailedState);
    }
}
