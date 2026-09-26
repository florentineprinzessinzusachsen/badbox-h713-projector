package com.szns.sdk;

/* JADX INFO: loaded from: classes.dex */
final class ae implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ ad b;

    ae(ad adVar, int i) {
        this.b = adVar;
        this.a = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.b.a(this.a);
    }
}
