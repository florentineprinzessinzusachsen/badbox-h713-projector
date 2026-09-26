package com.ad.proxy.f;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class A implements F {
    public final /* synthetic */ com.ad.proxy.b.A a;

    public A(com.ad.proxy.b.A a) {
        this.a = a;
    }

    @Override // com.ad.proxy.f.F
    public final void a(Object obj) {
        com.ad.proxy.e.B b;
        try {
            JSONObject jSONObject = new JSONObject((String) obj);
            b = new com.ad.proxy.e.B();
            JSONArray jSONArray = jSONObject.getJSONArray("gateway_list");
            ArrayList arrayList = new ArrayList(jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(com.ad.proxy.e.A.a(jSONArray.optString(i)));
            }
            b.a = arrayList;
            b.b = jSONObject.getString("geo_ids");
            b.c = jSONObject.optInt("kproto_gap_time", com.ad.proxy.d.A.a / 1000);
            b.d = jSONObject.optInt("gap_time", 60);
            b.e = jSONObject.optInt("threads_num", 1000);
        } catch (JSONException e) {
            com.ad.proxy.g.F.a("SignInResp", e);
            b = null;
        }
        try {
            this.a.a(b);
        } catch (Exception e2) {
            com.ad.proxy.g.F.a("C", e2);
        }
    }

    @Override // com.ad.proxy.f.F
    public final void a(int i, String str) {
        this.a.a(i, str);
    }
}
