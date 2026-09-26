package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;

/* JADX INFO: compiled from: UMTTTwoTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class o extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4136a = "umtt2";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f4137b;

    public o(Context context) {
        super(f4136a);
        this.f4137b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        try {
            Class<?> cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
            if (cls != null) {
                return (String) cls.getMethod("getUmtt2", Context.class).invoke(cls, this.f4137b);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
