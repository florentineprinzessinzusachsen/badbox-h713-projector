package com.ad.proxy.f;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class G {
    public static final ExecutorService a = Executors.newFixedThreadPool(1);

    public static void a(String str, boolean z, F f) {
        if (!z) {
            f.a(-1, str);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("code");
            String string = jSONObject.getString("msg");
            if (i != 0) {
                f.a(i, string);
            } else if (jSONObject.has("data")) {
                f.a(jSONObject.getJSONObject("data").toString());
            } else {
                f.a(null);
            }
        } catch (JSONException e) {
            com.ad.proxy.g.F.a("Http", e);
            f.a(-1, str);
        }
    }
}
