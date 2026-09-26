package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

/* JADX INFO: compiled from: MacTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4119a = "mac";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f4120b;

    public g(Context context) {
        super(f4119a);
        this.f4120b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        try {
            return DeviceConfig.getMac(this.f4120b);
        } catch (Exception e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                e2.printStackTrace();
            }
            UMCrashManager.reportCrash(this.f4120b, e2);
            return null;
        }
    }
}
