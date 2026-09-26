package com.umeng.commonsdk.proguard;

/* JADX INFO: compiled from: TApplicationException.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f3982a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f3983b = 1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f3984c = 2;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f3985d = 3;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f3986e = 4;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int f3987f = 5;
    public static final int g = 6;
    public static final int h = 7;
    private static final an j = new an("TApplicationException");
    private static final ad k = new ad("message", (byte) 11, 1);
    private static final ad l = new ad("type", (byte) 8, 2);
    private static final long m = 1;
    protected int i;

    public i() {
        this.i = 0;
    }

    public int a() {
        return this.i;
    }

    public void b(ai aiVar) {
        aiVar.a(j);
        if (getMessage() != null) {
            aiVar.a(k);
            aiVar.a(getMessage());
            aiVar.c();
        }
        aiVar.a(l);
        aiVar.a(this.i);
        aiVar.c();
        aiVar.d();
        aiVar.b();
    }

    public static i a(ai aiVar) {
        aiVar.j();
        String strZ = null;
        int iW = 0;
        while (true) {
            ad adVarL = aiVar.l();
            byte b2 = adVarL.f3907b;
            if (b2 == 0) {
                aiVar.k();
                return new i(iW, strZ);
            }
            short s = adVarL.f3908c;
            if (s != 1) {
                if (s != 2) {
                    al.a(aiVar, b2);
                } else if (b2 == 8) {
                    iW = aiVar.w();
                } else {
                    al.a(aiVar, b2);
                }
            } else if (b2 == 11) {
                strZ = aiVar.z();
            } else {
                al.a(aiVar, b2);
            }
            aiVar.m();
        }
    }

    public i(int i) {
        this.i = 0;
        this.i = i;
    }

    public i(int i, String str) {
        super(str);
        this.i = 0;
        this.i = i;
    }

    public i(String str) {
        super(str);
        this.i = 0;
    }
}
