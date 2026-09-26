package com.hs.p.common.http;

import android.content.Context;
import com.hs.p.common.utils.SystemUtils;
import com.hs.p.common.utils.TextUtils;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class HTTPBuilder {
    public static final String P_M = "m";
    public static final String P_N = "n";

    public static void appendCommonParameters(Context context, HTTPHelper hTTPHelper) {
    }

    private static void appendQueryParameter(HTTPHelper hTTPHelper, String str, String str2) {
        if (TextUtils.empty(str2)) {
            return;
        }
        hTTPHelper.putQueryParameter(str, str2);
    }

    public static HTTPHelper createHelper(Context context) {
        HTTPHelper hTTPHelper = HTTPHelper.get(context);
        hTTPHelper.setUserAgent(SystemUtils.getUserAgent(context));
        return hTTPHelper;
    }
}
