package com.szns.sdk;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
final class ag implements ab {
    final /* synthetic */ int a;
    final /* synthetic */ ad b;

    ag(ad adVar, int i) {
        this.b = adVar;
        this.a = i;
    }

    @Override // com.szns.sdk.ab
    public final void a(String str) {
        ad adVar;
        int i;
        String str2;
        if (str == null || TextUtils.isEmpty(str)) {
            adVar = this.b;
            i = this.a;
            str2 = "data empty";
        } else {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("du", str);
                jSONObject.put("dct", System.currentTimeMillis() + 86400000);
                this.b.b(jSONObject, "oss_domain_info");
                this.b.h = str;
                this.b.b(this.a);
                return;
            } catch (JSONException unused) {
                adVar = this.b;
                i = this.a;
                str2 = "json opt fail";
            }
        }
        adVar.a(i, str2);
    }

    @Override // com.szns.sdk.ab
    public final void b(String str) {
        this.b.a(this.a, str);
    }
}
