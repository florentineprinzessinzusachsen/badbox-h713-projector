package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.PackageInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class h {
    public static JSONObject a(Context context) throws Throwable {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("s", android.os.Build.VERSION.SDK_INT);
            jSONObject.put("sv", android.os.Build.VERSION.RELEASE);
            jSONObject.put(Config.CUID_SEC, bb.a(2, context));
            jSONObject.put(Config.DEVICE_WIDTH, bb.c(context));
            jSONObject.put("h", bb.d(context));
            jSONObject.put("ly", ab.f3427c);
            jSONObject.put("pv", "33");
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                jSONObject.put("pn", bb.h(2, context));
                jSONObject.put("a", packageInfo.versionCode);
                jSONObject.put("n", packageInfo.versionName);
            } catch (Exception e2) {
                al.c().a(e2);
            }
            jSONObject.put("mc", bb.b(2, context));
            jSONObject.put(Config.DEVICE_BLUETOOTH_MAC, bb.f(2, context));
            jSONObject.put(Config.MODEL, android.os.Build.MODEL);
            jSONObject.put(Config.DEVICE_NAME, bb.a(context, 2));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(Config.TRACE_FAILED_CNT, 0);
            jSONObject2.put("send_index", 0);
            String strB = bb.b();
            if (strB == null) {
                strB = "";
            }
            jSONObject2.put(Config.ROM, strB);
            jSONObject.put(Config.TRACE_PART, jSONObject2);
        } catch (JSONException e3) {
            al.c().b(e3);
        }
        return jSONObject;
    }

    public static void b(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(Config.TRACE_PART);
            jSONObject2.put(Config.TRACE_FAILED_CNT, jSONObject2.getLong(Config.TRACE_FAILED_CNT) + 1);
        } catch (Exception unused) {
        }
    }

    public static void c(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(Config.TRACE_PART);
            jSONObject2.put("send_index", jSONObject2.getLong("send_index") + 1);
        } catch (Exception unused) {
        }
    }

    public static JSONObject a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            JSONArray jSONArray = (JSONArray) jSONObject.get("payload");
            JSONObject jSONObject2 = (jSONArray == null || jSONArray.length() <= 0) ? null : (JSONObject) jSONArray.get(0);
            if (jSONObject2 != null) {
                return jSONObject2.getJSONObject(Config.HEADER_PART);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }
}
