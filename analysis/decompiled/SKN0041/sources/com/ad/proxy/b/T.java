package com.ad.proxy.b;

import java.io.IOException;
import java.net.Socket;

/* JADX INFO: loaded from: classes.dex */
public final class T implements Runnable {
    public final /* synthetic */ com.ad.proxy.c.A a;
    public final /* synthetic */ H b;
    public final /* synthetic */ a0 c;

    public T(a0 a0Var, com.ad.proxy.c.A a, H h) {
        this.c = a0Var;
        this.a = a;
        this.b = h;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.c.g) {
            try {
                com.ad.proxy.g.F.a("SocketClient", "Sending packet: " + ((int) this.a.d));
                I.a(this.c.c, this.a);
                com.ad.proxy.g.F.a("SocketClient", "Success send packet: " + ((int) this.a.d));
                H h = this.b;
                if (h != null) {
                    h.a(true);
                }
            } catch (IOException e) {
                com.ad.proxy.g.F.b("SocketClient", "Failed to send packet: " + e.getMessage());
                com.ad.proxy.g.F.a("SocketClient", e);
                H h2 = this.b;
                if (h2 != null) {
                    h2.a(false);
                }
                Socket socket = this.c.a;
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e2) {
                        com.ad.proxy.g.F.a("E", e2);
                    }
                }
            }
        }
    }
}
