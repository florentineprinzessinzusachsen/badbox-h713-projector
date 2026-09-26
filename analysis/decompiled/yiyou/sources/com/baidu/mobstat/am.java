package com.baidu.mobstat;

/* JADX INFO: loaded from: classes.dex */
public class am extends ak {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static am f3467c = new am();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f3468b;

    private am() {
    }

    public static am c() {
        return f3467c;
    }

    @Override // com.baidu.mobstat.ak
    public String a() {
        return "BaiduMobStat";
    }

    public void a(boolean z) {
        this.f3468b = z;
    }

    @Override // com.baidu.mobstat.ak
    public boolean b() {
        return this.f3468b;
    }
}
