package com.umeng.analytics.pro;

import android.content.Context;
import com.umeng.commonsdk.debug.UMRTLog;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: DataSpliter.java */
/* JADX INFO: loaded from: classes.dex */
public class m {
    public static JSONObject a(Context context, long j, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            if (!jSONObject.has("content")) {
                return jSONObject2;
            }
            JSONObject jSONObject3 = jSONObject.getJSONObject("content");
            if (jSONObject3.has("analytics")) {
                JSONObject jSONObject4 = jSONObject3.getJSONObject("analytics");
                if (jSONObject4.has(b.R)) {
                    jSONObject4.remove(b.R);
                }
                if (jSONObject4.has(b.S)) {
                    jSONObject4.remove(b.S);
                }
                if (jSONObject4.has(b.N)) {
                    jSONObject4.remove(b.N);
                }
                jSONObject3.put("analytics", jSONObject4);
            }
            jSONObject2.put("content", jSONObject3);
            if (jSONObject.has("header")) {
                jSONObject2.put("header", jSONObject.getJSONObject("header"));
            }
            if (a(jSONObject2) <= j) {
                return jSONObject2;
            }
            jSONObject2 = null;
            g.a(context).i();
            g.a(context).h();
            g.a(context).b(true, false);
            g.a(context).a();
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> u-app packet overload !!! ");
            return null;
        } catch (Throwable unused) {
            return jSONObject2;
        }
    }

    public static long a(JSONObject jSONObject) {
        return jSONObject.toString().getBytes().length;
    }

    public static long a(JSONArray jSONArray) {
        return jSONArray.toString().getBytes().length;
    }
}
