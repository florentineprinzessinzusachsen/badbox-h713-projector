package com.ad.proxy.b;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public final class Z implements E {
    public final /* synthetic */ a0 a;

    public Z(a0 a0Var) {
        this.a = a0Var;
    }

    @Override // com.ad.proxy.b.E
    public final void a(com.ad.proxy.c.A a) {
        com.ad.proxy.g.F.a("SocketClient", "Received udp int transaction " + a.a);
        a0 a0Var = this.a;
        a0Var.getClass();
        com.ad.proxy.g.F.a("SocketClient", "udp int");
        byte[] bArr = new byte[2];
        System.arraycopy(a.g, 0, bArr, 0, 2);
        String strValueOf = String.valueOf((int) ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN).getShort());
        byte[] bArr2 = a.g;
        byte[] bArr3 = new byte[bArr2.length - 2];
        System.arraycopy(bArr2, 2, bArr3, 0, bArr2.length - 2);
        String str = new String(bArr3);
        com.ad.proxy.g.F.a("SocketClient", "udp int host: " + str + " port: " + strValueOf);
        k0 k0Var = new k0(a0Var, str, Integer.parseInt(strValueOf), a.a);
        if (J.a().a.size() <= a0.n) {
            a0Var.a(k0Var);
            return;
        }
        J jA = J.a();
        synchronized (jA.c) {
            jA.b.add(k0Var);
        }
    }
}
