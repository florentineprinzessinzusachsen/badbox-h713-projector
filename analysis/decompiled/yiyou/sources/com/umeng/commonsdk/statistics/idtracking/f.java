package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

/* JADX INFO: compiled from: ImeiTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4117a = "imei";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f4118b;

    public f(Context context) {
        super(f4117a);
        this.f4118b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        return DeviceConfig.getImeiNew(this.f4118b);
    }
}
