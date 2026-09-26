package com.baidu.mobstat;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ExtraInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f3355a = "";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    String f3356b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    String f3357c = "";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    String f3358d = "";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    String f3359e = "";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    String f3360f = "";
    String g = "";
    String h = "";
    String i = "";
    String j = "";

    private static boolean a(String str, int i) {
        int length;
        if (str == null) {
            return false;
        }
        try {
            length = str.getBytes().length;
        } catch (Exception unused) {
            length = 0;
        }
        return length > i;
    }

    public JSONObject dumpToJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.f3355a)) {
                jSONObject.put("v1", this.f3355a);
            }
            if (!TextUtils.isEmpty(this.f3356b)) {
                jSONObject.put("v2", this.f3356b);
            }
            if (!TextUtils.isEmpty(this.f3357c)) {
                jSONObject.put("v3", this.f3357c);
            }
            if (!TextUtils.isEmpty(this.f3358d)) {
                jSONObject.put("v4", this.f3358d);
            }
            if (!TextUtils.isEmpty(this.f3359e)) {
                jSONObject.put("v5", this.f3359e);
            }
            if (!TextUtils.isEmpty(this.f3360f)) {
                jSONObject.put("v6", this.f3360f);
            }
            if (!TextUtils.isEmpty(this.g)) {
                jSONObject.put("v7", this.g);
            }
            if (!TextUtils.isEmpty(this.h)) {
                jSONObject.put("v8", this.h);
            }
            if (!TextUtils.isEmpty(this.i)) {
                jSONObject.put("v9", this.i);
            }
            if (!TextUtils.isEmpty(this.j)) {
                jSONObject.put("v10", this.j);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public String getV1() {
        return this.f3355a;
    }

    public String getV10() {
        return this.j;
    }

    public String getV2() {
        return this.f3356b;
    }

    public String getV3() {
        return this.f3357c;
    }

    public String getV4() {
        return this.f3358d;
    }

    public String getV5() {
        return this.f3359e;
    }

    public String getV6() {
        return this.f3360f;
    }

    public String getV7() {
        return this.g;
    }

    public String getV8() {
        return this.h;
    }

    public String getV9() {
        return this.i;
    }

    public void setV1(String str) {
        this.f3355a = a(str);
    }

    public void setV10(String str) {
        this.j = a(str);
    }

    public void setV2(String str) {
        this.f3356b = a(str);
    }

    public void setV3(String str) {
        this.f3357c = a(str);
    }

    public void setV4(String str) {
        this.f3358d = a(str);
    }

    public void setV5(String str) {
        this.f3359e = a(str);
    }

    public void setV6(String str) {
        this.f3360f = a(str);
    }

    public void setV7(String str) {
        this.g = a(str);
    }

    public void setV8(String str) {
        this.h = a(str);
    }

    public void setV9(String str) {
        this.i = a(str);
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        return a(str, 1024) ? "" : str;
    }
}
