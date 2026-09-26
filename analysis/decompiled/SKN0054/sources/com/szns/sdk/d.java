package com.szns.sdk;

/* JADX INFO: loaded from: classes.dex */
final class d implements Runnable {
    final /* synthetic */ a a;

    d(a aVar) {
        this.a = aVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.b("LIFECYCLE_START");
        a.e(this.a);
    }
}
