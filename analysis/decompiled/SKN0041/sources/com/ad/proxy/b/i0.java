package com.ad.proxy.b;

import com.ad.proxy.Robin;
import java.net.DatagramPacket;

/* JADX INFO: loaded from: classes.dex */
public final class i0 implements Runnable {
    public final /* synthetic */ com.ad.proxy.c.A a;
    public final /* synthetic */ k0 b;

    public i0(k0 k0Var, com.ad.proxy.c.A a) {
        this.b = k0Var;
        this.a = a;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.a.f;
        k0 k0Var = this.b;
        if (i < k0Var.k) {
            return;
        }
        k0Var.l.put(Integer.valueOf(i), this.a);
        try {
            if (!this.b.g) {
                return;
            }
            while (true) {
                k0 k0Var2 = this.b;
                com.ad.proxy.c.A a = (com.ad.proxy.c.A) k0Var2.l.remove(Integer.valueOf(k0Var2.k));
                if (a == null) {
                    return;
                }
                byte[] bArr = a.g;
                int length = bArr.length;
                k0 k0Var3 = this.b;
                this.b.e.send(new DatagramPacket(bArr, length, k0Var3.f, k0Var3.c));
                this.b.k++;
            }
        } catch (Exception e) {
            String str = "[UDPProxy] Send failed: " + e.getMessage();
            boolean z = com.ad.proxy.g.F.a;
            if (Robin.isDebug()) {
                com.ad.proxy.g.F.b("LogUtil", str);
            }
        }
    }
}
