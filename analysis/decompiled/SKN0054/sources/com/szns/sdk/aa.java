package com.szns.sdk;

/* JADX INFO: loaded from: classes.dex */
final class aa implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ ab c;

    aa(String str, String str2, e eVar) {
        this.a = str;
        this.b = str2;
        this.c = eVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        try {
            this.c.a(w.a(this.a, this.b));
        } catch (Exception e) {
            this.c.b(e.getMessage());
        }
    }
}
