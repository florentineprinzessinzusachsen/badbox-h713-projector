package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class aq {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f3471a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private WeakReference<WebView> f3472b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private WeakReference<Activity> f3473c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private JSONObject f3474d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f3475e;

    /* JADX WARN: Code duplicated, block: B:15:0x0034 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:16:0x0035  */
    /* JADX WARN: Code duplicated, block: B:18:0x003b  */
    /* JADX WARN: Code duplicated, block: B:19:0x0040  */
    /* JADX WARN: Code duplicated, block: B:22:0x007c  */
    /* JADX WARN: Code duplicated, block: B:23:0x009d  */
    private void a(String str, Activity activity, WebView webView) {
        JSONArray jSONArrayOptJSONArray;
        String strOptString;
        String strOptString2;
        boolean z;
        JSONObject jSONObject;
        JSONArray jSONArray;
        String str2;
        String str3;
        JSONArray jSONArrayA;
        String name;
        String strA;
        String strB;
        String strD;
        Map<String, String> mapE;
        Context applicationContext;
        JSONObject jSONObjectOptJSONObject = null;
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            jSONArrayOptJSONArray = jSONObject2.optJSONArray(Config.EVENT_H5_VIEW_HIERARCHY);
            try {
                strOptString = jSONObject2.optString(Config.EVENT_H5_PAGE);
                try {
                    strOptString2 = jSONObject2.optString("l");
                    try {
                        jSONObjectOptJSONObject = jSONObject2.optJSONObject(Config.EVENT_HEAT_POINT);
                        z = true;
                    } catch (Exception unused) {
                        z = false;
                    }
                } catch (Exception unused2) {
                    strOptString2 = null;
                }
            } catch (Exception unused3) {
                strOptString = null;
                strOptString2 = strOptString;
                z = false;
                jSONObject = jSONObjectOptJSONObject;
                jSONArray = jSONArrayOptJSONArray;
                str2 = strOptString2;
                if (z) {
                    if (TextUtils.isEmpty(strOptString)) {
                        str3 = "/";
                    } else {
                        str3 = strOptString;
                    }
                    jSONArrayA = ap.a(activity, webView);
                    name = activity.getClass().getName();
                    strA = ap.a(jSONArrayA);
                    strB = ap.b(jSONArray);
                    strD = ap.d(webView);
                    mapE = ap.e(webView);
                    applicationContext = activity.getApplicationContext();
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if (a(this.f3474d, activity.getClass().getName(), str3, strA, strB)) {
                        BDStatCore.instance().onEvent(applicationContext, "", str2, 1, System.currentTimeMillis(), jSONArrayA, jSONArray, name, str3, strD, mapE, true);
                    } else if (an.c().b()) {
                        an.c().a("setEventToNative: not circle event, will not take effect");
                    }
                    ai.a().a(applicationContext, "", str2, 1, jCurrentTimeMillis, name, jSONArrayA, str3, jSONArray, strD, mapE, true, jSONObject, "");
                }
                return;
            }
        } catch (Exception unused4) {
            jSONArrayOptJSONArray = null;
            strOptString = null;
        }
        jSONObject = jSONObjectOptJSONObject;
        jSONArray = jSONArrayOptJSONArray;
        str2 = strOptString2;
        if (z) {
            return;
        }
        if (TextUtils.isEmpty(strOptString)) {
            str3 = "/";
        } else {
            str3 = strOptString;
        }
        jSONArrayA = ap.a(activity, webView);
        name = activity.getClass().getName();
        strA = ap.a(jSONArrayA);
        strB = ap.b(jSONArray);
        strD = ap.d(webView);
        mapE = ap.e(webView);
        applicationContext = activity.getApplicationContext();
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        if (a(this.f3474d, activity.getClass().getName(), str3, strA, strB)) {
            BDStatCore.instance().onEvent(applicationContext, "", str2, 1, System.currentTimeMillis(), jSONArrayA, jSONArray, name, str3, strD, mapE, true);
        } else if (an.c().b() && this.f3475e) {
            an.c().a("setEventToNative: not circle event, will not take effect");
        }
        ai.a().a(applicationContext, "", str2, 1, jCurrentTimeMillis2, name, jSONArrayA, str3, jSONArray, strD, mapE, true, jSONObject, "");
    }

    @JavascriptInterface
    public void setEventToNative(String str) {
        Activity activity;
        WeakReference<WebView> weakReference;
        WebView webView;
        if (an.c().b() && this.f3475e) {
            an.c().a("setEventToNative: " + str);
        }
        if (ao.c().b()) {
            ao.c().a("setEventToNative: " + str);
        }
        WeakReference<Activity> weakReference2 = this.f3473c;
        if (weakReference2 == null || (activity = weakReference2.get()) == null || (weakReference = this.f3472b) == null || (webView = weakReference.get()) == null) {
            return;
        }
        a(str, activity, webView);
    }

    @JavascriptInterface
    public void setViewportTreeToNative(String str) {
        if (an.c().b()) {
            an.c().a("setViewportTreeToNative " + str);
        }
        f3471a = str;
    }

    private boolean a(JSONObject jSONObject, String str, String str2, String str3, String str4) {
        if (jSONObject == null || jSONObject.toString().equals(new JSONObject().toString()) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            return false;
        }
        try {
            if (((JSONObject) jSONObject.get("meta")).getInt("matchAll") != 0) {
                return true;
            }
        } catch (Exception unused) {
        }
        try {
            JSONArray jSONArray = (JSONArray) jSONObject.get("data");
            boolean z = false;
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                    String strOptString = jSONObject2.optString("page");
                    String strOptString2 = jSONObject2.optString("layout");
                    String str5 = (String) jSONObject2.opt("url");
                    String str6 = (String) jSONObject2.opt("webLayout");
                    if (str.equals(strOptString) && str2.equals(str5) && str3.equals(strOptString2) && str4.equals(str6)) {
                        z = true;
                    }
                } catch (Exception unused2) {
                    return z;
                }
            }
            return z;
        } catch (Exception unused3) {
            return false;
        }
    }
}
