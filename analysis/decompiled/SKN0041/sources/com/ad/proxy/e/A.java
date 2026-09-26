package com.ad.proxy.e;

import com.ad.proxy.g.F;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class A {
    public String a;
    public int b;
    public String c;

    public final String a() {
        return this.a + ":" + this.b + ":" + this.c;
    }

    public static A a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            A a = new A();
            a.a = jSONObject.getString("host");
            a.b = jSONObject.getInt("port");
            a.c = jSONObject.getString("pwd");
            return a;
        } catch (JSONException e) {
            F.a("Gateway", e);
            return null;
        }
    }
}
