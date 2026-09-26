package com.szns.sdk;

import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
final class b implements ai {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    @Override // com.szns.sdk.ai
    public final void a(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            this.a.m = new String[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                this.a.m[i] = jSONArray.getString(i);
            }
        } catch (Exception unused) {
        }
    }
}
