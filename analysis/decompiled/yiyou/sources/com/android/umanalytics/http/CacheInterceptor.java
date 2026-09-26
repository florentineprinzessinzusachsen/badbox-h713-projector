package com.android.umanalytics.http;

import com.blankj.utilcode.util.NetworkUtils;
import d.a0;
import d.c0;
import d.d;
import d.u;

/* JADX INFO: loaded from: classes.dex */
public class CacheInterceptor implements u {
    @Override // d.u
    public c0 intercept(u.a aVar) {
        a0 a0VarRequest = aVar.request();
        boolean zIsConnected = NetworkUtils.isConnected();
        if (!zIsConnected) {
            a0.a aVarF = a0VarRequest.f();
            aVarF.a(d.n);
            a0VarRequest = aVarF.a();
        }
        c0 c0VarA = aVar.a(a0VarRequest);
        if (zIsConnected) {
            c0.a aVarS = c0VarA.s();
            aVarS.b("Pragma");
            aVarS.b("Cache-Control", "public, max-age=36000");
            return aVarS.a();
        }
        c0.a aVarS2 = c0VarA.s();
        aVarS2.b("Pragma");
        aVarS2.b("Cache-Control", "public, only-if-cached, max-stale=604800");
        return aVarS2.a();
    }
}
