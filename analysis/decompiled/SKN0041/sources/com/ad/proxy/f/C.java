package com.ad.proxy.f;

import com.ad.proxy.Robin;
import com.ad.proxy.g.J;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class C {
    public static final /* synthetic */ int a = 0;

    public static void a(com.ad.proxy.b.A a2) {
        String str;
        String str2 = "/signin?uuid=" + J.a() + "&channel=" + Robin.channel + "&version=" + Robin.version;
        if (Robin.isProd()) {
            str = "https://api.kookjar.com" + str2;
        } else {
            str = "http://152.32.240.141" + str2;
        }
        A a3 = new A(a2);
        ExecutorService executorService = G.a;
        com.ad.proxy.g.F.a("Http", "START GET " + str);
        G.a.execute(new D(str, a3));
    }

    public static void a(com.ad.proxy.e.A a2, int i, String str) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(a2);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList2.add(Integer.valueOf(i));
        String str2 = "/report?uuid=" + J.a() + "&channel=" + str + "&version=" + Robin.version;
        String str3 = Robin.isProd() ? "https://api.kookjar.com" + str2 : "http://152.32.240.141" + str2;
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                com.ad.proxy.e.A a3 = (com.ad.proxy.e.A) arrayList.get(i2);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("host", a3.a);
                jSONObject2.put("port", a3.b);
                jSONObject2.put("status", arrayList2.get(i2));
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("gateway_list", jSONArray);
        } catch (JSONException e) {
            com.ad.proxy.g.F.a("C", e);
        }
        G.a.execute(new E(str3, jSONObject.toString(), new B()));
    }
}
