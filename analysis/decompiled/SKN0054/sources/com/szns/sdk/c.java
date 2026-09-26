package com.szns.sdk;

/* JADX INFO: loaded from: classes.dex */
final class c implements Runnable {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.g = Boolean.TRUE;
        this.a.d();
        this.a.b("FLOW_DISPATCH_READY");
        int iRandom = (int) (Math.random() * 5000.0d);
        this.a.k.removeCallbacks(this.a.q);
        this.a.k.postDelayed(this.a.q, iRandom);
    }
}
