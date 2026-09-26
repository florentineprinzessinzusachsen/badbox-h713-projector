package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;

/* JADX INFO: compiled from: IDFATracker.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4105a = "idfa";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f4106b;

    public c(Context context) {
        super(f4105a);
        this.f4106b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        String strA = com.umeng.commonsdk.statistics.common.a.a(this.f4106b);
        return strA == null ? "" : strA;
    }
}
