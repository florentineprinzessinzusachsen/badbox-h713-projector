package com.umeng.commonsdk.proguard;

/* JADX INFO: compiled from: TTransportException.java */
/* JADX INFO: loaded from: classes.dex */
public class ax extends p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f3942a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f3943b = 1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f3944c = 2;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f3945d = 3;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f3946e = 4;
    private static final long g = 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected int f3947f;

    public ax() {
        this.f3947f = 0;
    }

    public int a() {
        return this.f3947f;
    }

    public ax(int i) {
        this.f3947f = 0;
        this.f3947f = i;
    }

    public ax(int i, String str) {
        super(str);
        this.f3947f = 0;
        this.f3947f = i;
    }

    public ax(String str) {
        super(str);
        this.f3947f = 0;
    }

    public ax(int i, Throwable th) {
        super(th);
        this.f3947f = 0;
        this.f3947f = i;
    }

    public ax(Throwable th) {
        super(th);
        this.f3947f = 0;
    }

    public ax(String str, Throwable th) {
        super(str, th);
        this.f3947f = 0;
    }

    public ax(int i, String str, Throwable th) {
        super(str, th);
        this.f3947f = 0;
        this.f3947f = i;
    }
}
