package com.ad.proxy.b;

/* JADX INFO: loaded from: classes.dex */
public final class O {
    public final /* synthetic */ g0 a;
    public final /* synthetic */ a0 b;

    public O(a0 a0Var, g0 g0Var) {
        this.b = a0Var;
        this.a = g0Var;
    }

    public final void a(boolean z) {
        if (z) {
            J jA = J.a();
            g0 g0Var = this.a;
            jA.a.put(Long.valueOf(g0Var.d), g0Var);
        }
        a0 a0Var = this.b;
        long j = this.a.d;
        a0Var.getClass();
        com.ad.proxy.g.F.a("SocketClient", "Sending tcp syn ack transaction " + j + " isSuccess " + z);
        a0Var.e.execute(new T(a0Var, new com.ad.proxy.c.A(j, com.ad.proxy.g.J.a(), (byte) 4, 0, new byte[]{z ? (byte) 1 : (byte) 0}), null));
    }
}
