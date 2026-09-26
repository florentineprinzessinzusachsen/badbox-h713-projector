package com.szns.sdk;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class z implements Runnable {
    final /* synthetic */ Map a;
    final /* synthetic */ String b;
    final /* synthetic */ ab c;

    z(Map map, String str, ab abVar) {
        this.a = map;
        this.b = str;
        this.c = abVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        try {
            this.c.a(w.a(this.b + "?" + w.a(this.a, "utf-8").toString()));
        } catch (Exception e) {
            this.c.b(e.getMessage());
        }
    }
}
