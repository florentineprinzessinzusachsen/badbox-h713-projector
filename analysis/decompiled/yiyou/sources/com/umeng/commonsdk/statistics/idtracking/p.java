package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;

/* JADX INFO: compiled from: UMTTZeroTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class p extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4138a = "umtt0";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f4139b;

    public p(Context context) {
        super(f4138a);
        this.f4139b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        try {
            Class<?> cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
            if (cls != null) {
                return (String) cls.getMethod("getUmtt0", Context.class).invoke(cls, this.f4139b);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
