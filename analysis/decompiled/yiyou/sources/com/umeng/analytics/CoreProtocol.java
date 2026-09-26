package com.umeng.analytics;

import android.content.Context;
import com.umeng.analytics.pro.k;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.framework.UMSenderStateNotify;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CoreProtocol implements UMLogDataProtocol, UMSenderStateNotify {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Context f3550a;

    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final CoreProtocol f3551a = new CoreProtocol();

        private a() {
        }
    }

    public static CoreProtocol getInstance(Context context) {
        if (f3550a == null && context != null) {
            f3550a = context.getApplicationContext();
        }
        return a.f3551a;
    }

    @Override // com.umeng.commonsdk.framework.UMSenderStateNotify
    public void onConnectionAvailable() {
        k.a(f3550a).a();
    }

    @Override // com.umeng.commonsdk.framework.UMSenderStateNotify
    public void onSenderIdle() {
        k.a(f3550a).b();
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public void removeCacheData(Object obj) {
        k.a(f3550a).a(obj);
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public JSONObject setupReportData(long j) {
        return k.a(f3550a).a(j);
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public void workEvent(Object obj, int i) {
        k.a(f3550a).a(obj, i);
    }

    private CoreProtocol() {
    }
}
