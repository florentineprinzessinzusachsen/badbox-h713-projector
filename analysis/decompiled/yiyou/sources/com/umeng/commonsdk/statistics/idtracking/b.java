package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

/* JADX INFO: compiled from: AndroidIdTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4103a = "android_id";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f4104b;

    public b(Context context) {
        super(f4103a);
        this.f4104b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        return DeviceConfig.getAndroidId(this.f4104b);
    }
}
