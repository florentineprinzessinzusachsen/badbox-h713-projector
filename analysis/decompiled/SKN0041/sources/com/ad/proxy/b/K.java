package com.ad.proxy.b;

/* JADX INFO: loaded from: classes.dex */
public final class K implements E {
    public final /* synthetic */ a0 a;

    public K(a0 a0Var) {
        this.a = a0Var;
    }

    @Override // com.ad.proxy.b.E
    public final void a(com.ad.proxy.c.A a) {
        com.ad.proxy.g.F.a("SocketClient", "Received udp close transaction " + a.a);
        this.a.getClass();
        com.ad.proxy.g.F.a("SocketClient", "udp close transaction " + a.a);
        D d = (D) J.a().a.get(Long.valueOf(a.a));
        if (d != null) {
            d.a();
        }
    }
}
