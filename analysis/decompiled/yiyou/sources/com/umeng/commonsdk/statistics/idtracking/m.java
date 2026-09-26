package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;

/* JADX INFO: compiled from: UMTTOneTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class m extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4132a = "umtt1";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f4133b;

    public m(Context context) {
        super(f4132a);
        this.f4133b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        try {
            Class<?> cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
            if (cls != null) {
                return (String) cls.getMethod("getUmtt1", Context.class).invoke(cls, this.f4133b);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
