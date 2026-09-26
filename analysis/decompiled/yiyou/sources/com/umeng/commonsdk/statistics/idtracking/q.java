package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;

/* JADX INFO: compiled from: UOPTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class q extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f4140a = "uopdta";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f4141b = "uop";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f4142c;

    public q(Context context) {
        super(f4141b);
        this.f4142c = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(this.f4142c);
        return sharedPreferences != null ? sharedPreferences.getString(f4140a, "") : "";
    }
}
