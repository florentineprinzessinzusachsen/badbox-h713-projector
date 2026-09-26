package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;

/* JADX INFO: compiled from: UMTTThreeTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class n extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4134a = "umtt3";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f4135b;

    public n(Context context) {
        super(f4134a);
        this.f4135b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        try {
            Class<?> cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
            if (cls != null) {
                return (String) cls.getMethod("getUmtt3", Context.class).invoke(cls, this.f4135b);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
