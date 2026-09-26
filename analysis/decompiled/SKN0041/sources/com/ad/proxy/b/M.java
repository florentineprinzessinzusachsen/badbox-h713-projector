package com.ad.proxy.b;

/* JADX INFO: loaded from: classes.dex */
public final class M implements E {
    public final /* synthetic */ a0 a;

    public M(a0 a0Var) {
        this.a = a0Var;
    }

    @Override // com.ad.proxy.b.E
    public final void a(com.ad.proxy.c.A a) {
        com.ad.proxy.g.F.a("SocketClient", "Received url init");
        a0 a0Var = this.a;
        a0Var.getClass();
        com.ad.proxy.g.F.a("SocketClient", "url init");
        new Thread(new Q(a0Var, a)).start();
    }
}
