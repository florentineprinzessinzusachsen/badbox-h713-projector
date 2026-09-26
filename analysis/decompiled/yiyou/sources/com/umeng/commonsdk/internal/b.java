package com.umeng.commonsdk.internal;

import android.content.Context;

/* JADX INFO: compiled from: UMInternalData.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static b f3805b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f3806a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c f3807c;

    private b(Context context) {
        this.f3806a = context;
        this.f3807c = new c(context);
    }

    public static synchronized b a(Context context) {
        if (f3805b == null) {
            f3805b = new b(context.getApplicationContext());
        }
        return f3805b;
    }

    public c a() {
        return this.f3807c;
    }
}
