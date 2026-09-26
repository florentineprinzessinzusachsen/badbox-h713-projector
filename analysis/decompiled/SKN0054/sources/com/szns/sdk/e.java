package com.szns.sdk;

import android.text.TextUtils;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
final class e implements ab {
    final /* synthetic */ a a;

    e(a aVar) {
        this.a = aVar;
    }

    @Override // com.szns.sdk.ab
    public final void a(String str) {
        if (str == null || TextUtils.isEmpty(str)) {
            this.a.b("NETWORK_DATA_EMPTY");
            this.a.a(new int[0]);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(s.b(str)).getJSONObject("data");
            int iOptInt = jSONObject.optInt("schedule", 0);
            int iOptInt2 = jSONObject.optInt("thread", 0);
            int iOptInt3 = jSONObject.optInt("heartbeat", 0);
            int iOptInt4 = jSONObject.optInt("st", 600);
            JSONArray jSONArray = jSONObject.getJSONArray("node");
            if (jSONArray.length() == 0) {
                this.a.b("NETWORK_DATA_INVALID");
                this.a.a(Math.max(iOptInt4, 60) * 1000);
                return;
            }
            n.a().a(iOptInt2);
            a.f(this.a);
            this.a.b("NETWORK_NODE_READY");
            if (this.a.o) {
                this.a.o = false;
                this.a.c();
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                Object obj = jSONArray.get(i);
                if (obj instanceof JSONObject) {
                    JSONObject jSONObject2 = (JSONObject) obj;
                    String strOptString = jSONObject2.optString("connect", "");
                    String strOptString2 = jSONObject2.optString("proxy", "");
                    if (!strOptString.isEmpty() && !strOptString2.isEmpty()) {
                        a.a(this.a, strOptString, strOptString2, iOptInt3, iOptInt2);
                    }
                    this.a.a(Math.max(iOptInt4, 60) * 1000);
                    return;
                }
            }
            if (iOptInt > 0) {
                this.a.d();
                long j = iOptInt;
                this.a.n = n.a().a(new f(this), j, j, TimeUnit.MINUTES);
            }
        } catch (Exception unused) {
            this.a.b("NETWORK_DATA_INVALID");
            this.a.a(new int[0]);
        }
    }

    @Override // com.szns.sdk.ab
    public final void b(String str) {
        this.a.b("NETWORK_DISPATCH_FAIL");
        this.a.a(new int[0]);
    }
}
