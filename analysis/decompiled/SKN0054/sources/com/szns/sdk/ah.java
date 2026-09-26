package com.szns.sdk;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
final class ah implements ab {
    final /* synthetic */ int a;
    final /* synthetic */ ad b;

    ah(ad adVar, int i) {
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
                JSONObject jSONObject = new JSONObject(str);
                if (this.b.a(jSONObject, "ip_tcp_info", Boolean.TRUE)) {
                    this.b.b(jSONObject, "domain_info");
                    return;
                } else {
                    this.b.a(this.a, "get fail");
                    return;
                }
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
