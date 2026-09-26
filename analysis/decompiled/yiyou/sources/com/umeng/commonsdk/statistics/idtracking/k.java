package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;

/* JADX INFO: compiled from: UMTTFiveTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4128a = "umtt5";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f4129b;

    public k(Context context) {
        super(f4128a);
        this.f4129b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        try {
            Class<?> cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
            if (cls != null) {
                return (String) cls.getMethod("getUmtt5", Context.class).invoke(cls, this.f4129b);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
