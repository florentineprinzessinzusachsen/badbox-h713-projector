package com.ad.proxy.b;

import com.ad.proxy.Robin;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/* JADX INFO: loaded from: classes.dex */
public final class h0 implements Runnable {
    public final /* synthetic */ k0 a;

    public h0(k0 k0Var) {
        this.a = k0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            k0 k0Var = this.a;
            k0Var.f = InetAddress.getByName(k0Var.b);
            this.a.e = new DatagramSocket();
            this.a.g = true;
            byte[] bArr = new byte[1024];
            while (this.a.g) {
                DatagramPacket datagramPacket = new DatagramPacket(bArr, 1024);
                this.a.e.receive(datagramPacket);
                byte[] bArr2 = new byte[datagramPacket.getLength()];
                System.arraycopy(datagramPacket.getData(), datagramPacket.getOffset(), bArr2, 0, datagramPacket.getLength());
                k0.a(this.a, bArr2);
            }
        } catch (Exception e) {
            String str = "[UDPProxy] Error: " + e.getMessage();
            boolean z = com.ad.proxy.g.F.a;
            if (Robin.isDebug()) {
                com.ad.proxy.g.F.b("LogUtil", str);
            }
        } finally {
            this.a.a();
        }
    }
}
