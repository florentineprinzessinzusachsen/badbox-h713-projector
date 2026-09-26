package com.umeng.commonsdk.statistics.idtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: AbstractIdTracker.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f4098a = 10;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f4099b = 20;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f4100c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<com.umeng.commonsdk.statistics.proto.a> f4101d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.umeng.commonsdk.statistics.proto.b f4102e;

    public a(String str) {
        this.f4100c = str;
    }

    private boolean g() {
        com.umeng.commonsdk.statistics.proto.b bVar = this.f4102e;
        String strB = bVar == null ? null : bVar.b();
        int iH = bVar == null ? 0 : bVar.h();
        String strA = a(f());
        if (strA == null || strA.equals(strB)) {
            return false;
        }
        if (bVar == null) {
            bVar = new com.umeng.commonsdk.statistics.proto.b();
        }
        bVar.a(strA);
        bVar.a(System.currentTimeMillis());
        bVar.a(iH + 1);
        com.umeng.commonsdk.statistics.proto.a aVar = new com.umeng.commonsdk.statistics.proto.a();
        aVar.a(this.f4100c);
        aVar.c(strA);
        aVar.b(strB);
        aVar.a(bVar.e());
        if (this.f4101d == null) {
            this.f4101d = new ArrayList(2);
        }
        this.f4101d.add(aVar);
        if (this.f4101d.size() > 10) {
            this.f4101d.remove(0);
        }
        this.f4102e = bVar;
        return true;
    }

    public boolean a() {
        return g();
    }

    public String b() {
        return this.f4100c;
    }

    public boolean c() {
        com.umeng.commonsdk.statistics.proto.b bVar = this.f4102e;
        return bVar == null || bVar.h() <= 20;
    }

    public com.umeng.commonsdk.statistics.proto.b d() {
        return this.f4102e;
    }

    public List<com.umeng.commonsdk.statistics.proto.a> e() {
        return this.f4101d;
    }

    public abstract String f();

    public void a(com.umeng.commonsdk.statistics.proto.b bVar) {
        this.f4102e = bVar;
    }

    public void a(List<com.umeng.commonsdk.statistics.proto.a> list) {
        this.f4101d = list;
    }

    public String a(String str) {
        if (str == null) {
            return null;
        }
        String strTrim = str.trim();
        if (strTrim.length() == 0 || "0".equals(strTrim) || "unknown".equals(strTrim.toLowerCase(Locale.US))) {
            return null;
        }
        return strTrim;
    }

    public void a(com.umeng.commonsdk.statistics.proto.c cVar) {
        this.f4102e = cVar.c().get(this.f4100c);
        List<com.umeng.commonsdk.statistics.proto.a> listH = cVar.h();
        if (listH == null || listH.size() <= 0) {
            return;
        }
        if (this.f4101d == null) {
            this.f4101d = new ArrayList();
        }
        for (com.umeng.commonsdk.statistics.proto.a aVar : listH) {
            if (this.f4100c.equals(aVar.f4172a)) {
                this.f4101d.add(aVar);
            }
        }
    }
}
