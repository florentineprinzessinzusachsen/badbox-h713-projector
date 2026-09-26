package com.ad.proxy.b;

/* JADX INFO: loaded from: classes.dex */
public final class X implements E {
    public final /* synthetic */ a0 a;

    public X(a0 a0Var) {
        this.a = a0Var;
    }

    @Override // com.ad.proxy.b.E
    public final void a(com.ad.proxy.c.A a) {
        com.ad.proxy.g.F.a("SocketClient", "tcp send transaction " + a.a + " sequence " + a.f);
        this.a.getClass();
        StringBuilder sb = new StringBuilder("tcp send transaction ");
        sb.append(a.a);
        com.ad.proxy.g.F.a("SocketClient", sb.toString());
        D d = (D) J.a().a.get(Long.valueOf(a.a));
        if (d != null) {
            d.a(a);
        }
    }
}
