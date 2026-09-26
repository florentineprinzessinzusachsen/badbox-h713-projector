package com.ad.proxy.b;

/* JADX INFO: loaded from: classes.dex */
public final class r implements E {
    public final /* synthetic */ a0 a;

    public r(a0 a0Var) {
        this.a = a0Var;
    }

    @Override // com.ad.proxy.b.E
    public final void a(com.ad.proxy.c.A a) {
        com.ad.proxy.g.F.a("SocketClient", "Received udp send transaction " + a.a);
        this.a.getClass();
        com.ad.proxy.g.F.a("SocketClient", "udp send transaction " + a.a);
        D d = (D) J.a().a.get(Long.valueOf(a.a));
        if (d != null) {
            d.a(a);
        }
    }
}
