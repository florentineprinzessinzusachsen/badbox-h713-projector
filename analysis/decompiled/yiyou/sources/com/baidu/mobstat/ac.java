package com.baidu.mobstat;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ac {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f3430a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f3431b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f3432c;

    public ac() {
        this.f3430a = false;
        this.f3431b = "";
        this.f3432c = false;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("SDK_BPLUS_SERVICE", this.f3430a);
        } catch (JSONException e2) {
            al.c().b(e2);
        }
        try {
            jSONObject.put("SDK_PRODUCT_LY", this.f3431b);
        } catch (JSONException e3) {
            al.c().b(e3);
        }
        try {
            jSONObject.put("SDK_LOCAL_SERVER", this.f3432c);
        } catch (JSONException e4) {
            al.c().b(e4);
        }
        return jSONObject;
    }

    public ac(JSONObject jSONObject) {
        this.f3430a = false;
        this.f3431b = "";
        this.f3432c = false;
        try {
            this.f3430a = jSONObject.getBoolean("SDK_BPLUS_SERVICE");
        } catch (Exception e2) {
            al.c().b(e2);
        }
        try {
            this.f3431b = jSONObject.getString("SDK_PRODUCT_LY");
        } catch (Exception e3) {
            al.c().b(e3);
        }
        try {
            this.f3432c = jSONObject.getBoolean("SDK_LOCAL_SERVER");
        } catch (Exception e4) {
            al.c().b(e4);
        }
    }
}
