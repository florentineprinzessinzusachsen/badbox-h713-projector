package com.baidu.mobstat;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static a f3529a;

    public static synchronized a a(Context context) {
        a vVar;
        al.c().a("getBPStretegyController begin");
        vVar = f3529a;
        if (vVar == null) {
            try {
                Class<?> clsA = x.a(context, "com.baidu.bottom.remote.BPStretegyController2");
                if (clsA != null) {
                    w wVar = new w(clsA.newInstance());
                    try {
                        al.c().a("Get BPStretegyController load remote class v2");
                        vVar = wVar;
                    } catch (Exception e2) {
                        e = e2;
                        vVar = wVar;
                        al.c().a(e);
                    }
                }
            } catch (Exception e3) {
                e = e3;
            }
        }
        if (vVar == null) {
            vVar = new v();
            al.c().a("Get BPStretegyController load local class");
        }
        f3529a = vVar;
        x.a(context, vVar);
        al.c().a("getBPStretegyController end");
        return vVar;
    }

    public static synchronized void a() {
        f3529a = null;
    }
}
