package com.umeng.commonsdk.proguard;

/* JADX INFO: compiled from: TProtocolException.java */
/* JADX INFO: loaded from: classes.dex */
public class aj extends p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f3921a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f3922b = 1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f3923c = 2;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f3924d = 3;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f3925e = 4;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int f3926f = 5;
    private static final long h = 1;
    protected int g;

    public aj() {
        this.g = 0;
    }

    public int a() {
        return this.g;
    }

    public aj(int i) {
        this.g = 0;
        this.g = i;
    }

    public aj(int i, String str) {
        super(str);
        this.g = 0;
        this.g = i;
    }

    public aj(String str) {
        super(str);
        this.g = 0;
    }

    public aj(int i, Throwable th) {
        super(th);
        this.g = 0;
        this.g = i;
    }

    public aj(Throwable th) {
        super(th);
        this.g = 0;
    }

    public aj(String str, Throwable th) {
        super(str, th);
        this.g = 0;
    }

    public aj(int i, String str, Throwable th) {
        super(str, th);
        this.g = 0;
        this.g = i;
    }
}
