package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;

/* JADX INFO: compiled from: NewUMIDTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4121a = "newumid";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f4122b;

    public h(Context context) {
        super(f4121a);
        this.f4122b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        return UMEnvelopeBuild.imprintProperty(this.f4122b, com.umeng.commonsdk.proguard.e.f3967f, null);
    }
}
