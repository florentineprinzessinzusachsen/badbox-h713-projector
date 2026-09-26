package com.ad.proxy.b;

/* JADX INFO: loaded from: classes.dex */
public final class U implements E {
    public final /* synthetic */ a0 a;

    public U(a0 a0Var) {
        this.a = a0Var;
    }

    @Override // com.ad.proxy.b.E
    public final void a(com.ad.proxy.c.A a) {
        com.ad.proxy.g.F.a("SocketClient", "Received heartbeat request");
        a0 a0Var = this.a;
        a0Var.e.execute(new T(a0Var, new com.ad.proxy.c.A(a.a, a.b, (byte) 2, 0, null), null));
    }
}
