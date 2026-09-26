package com.ad.proxy.b;

import java.net.Socket;

/* JADX INFO: loaded from: classes.dex */
public final class d0 implements Runnable {
    public final /* synthetic */ O a;
    public final /* synthetic */ g0 b;

    public d0(g0 g0Var, O o) {
        this.b = g0Var;
        this.a = o;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z = true;
        try {
            try {
                g0 g0Var = this.b;
                g0 g0Var2 = this.b;
                g0Var.f = new Socket(g0Var2.b, g0Var2.c);
                this.b.f.setTcpNoDelay(true);
                this.b.f.setKeepAlive(true);
                g0 g0Var3 = this.b;
                g0Var3.g = g0Var3.f.getInputStream();
                g0 g0Var4 = this.b;
                g0Var4.h = g0Var4.f.getOutputStream();
                this.b.e = true;
                this.a.a(true);
                try {
                    byte[] bArr = new byte[1024];
                    com.ad.proxy.g.F.a("TCPProxy", "connected");
                    while (this.b.e) {
                        int i = this.b.g.read(bArr);
                        com.ad.proxy.g.F.a("TCPProxy", "received: " + i);
                        if (i == -1) {
                            break;
                        }
                        byte[] bArr2 = new byte[i];
                        System.arraycopy(bArr, 0, bArr2, 0, i);
                        g0.a(this.b, bArr2);
                    }
                } catch (Exception e) {
                    e = e;
                    if (!z) {
                        this.a.a(false);
                    }
                    com.ad.proxy.g.F.a("TCPProxy", e);
                }
            } finally {
                this.b.a();
            }
        } catch (Exception e2) {
            e = e2;
            z = false;
        }
    }
}
