package com.ad.proxy.b;

/* JADX INFO: loaded from: classes.dex */
public final class N implements Runnable {
    public final /* synthetic */ a0 a;

    public N(a0 a0Var) {
        this.a = a0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (!this.a.h || this.a.a == null || !this.a.a.isConnected()) {
            com.ad.proxy.g.F.a("SocketClient", "Not connected to server");
            return;
        }
        com.ad.proxy.g.F.a("SocketClient", "Sending heartbeat");
        com.ad.proxy.c.A a = new com.ad.proxy.c.A(0L, com.ad.proxy.g.J.a(), (byte) 1, 0, new byte[0]);
        a0 a0Var = this.a;
        a0Var.e.execute(new T(a0Var, a, null));
    }
}
