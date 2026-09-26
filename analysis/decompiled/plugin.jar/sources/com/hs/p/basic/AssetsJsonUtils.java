package com.hs.p.basic;

import android.content.Context;
import com.hs.p.common.utils.AssetsUtils;
import com.hs.p.common.utils.JSONUtils;
import com.hs.p.common.utils.TextUtils;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class AssetsJsonUtils {
    public static List<String> getStringList(Context context, String str, String str2) {
        try {
            return getStringList(AssetsUtils.getString(context, str), str2);
        } catch (Exception unused) {
            return Collections.emptyList();
        }
    }

    private static List<String> getStringList(String str, String str2) throws Exception {
        if (TextUtils.empty(str)) {
            return null;
        }
        return JSONUtils.getStringList(new JSONObject(str), str2);
    }
}
