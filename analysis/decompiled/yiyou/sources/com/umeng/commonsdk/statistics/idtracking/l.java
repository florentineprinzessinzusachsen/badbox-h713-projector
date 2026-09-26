package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;

/* JADX INFO: compiled from: UMTTFourTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4130a = "umtt4";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f4131b;

    public l(Context context) {
        super(f4130a);
        this.f4131b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        try {
            Class<?> cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
            if (cls != null) {
                return (String) cls.getMethod("getUmtt4", Context.class).invoke(cls, this.f4131b);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
