package com.ad.proxy.b;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public final class Q implements Runnable {
    public final /* synthetic */ com.ad.proxy.c.A a;
    public final /* synthetic */ a0 b;

    public Q(a0 a0Var, com.ad.proxy.c.A a) {
        this.b = a0Var;
        this.a = a;
    }

    @Override // java.lang.Runnable
    public final void run() {
        com.ad.proxy.c.A a = this.a;
        com.ad.proxy.c.A a2 = new com.ad.proxy.c.A(a.a, a.b, (byte) 15, 0, new byte[0]);
        try {
            byte[] bArr = new byte[4];
            System.arraycopy(a.g, 0, bArr, 0, 4);
            int i = ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN).getInt();
            byte[] bArr2 = this.a.g;
            int length = bArr2.length - 4;
            byte[] bArr3 = new byte[length];
            System.arraycopy(bArr2, 4, bArr3, 0, length);
            String str = new String(bArr3);
            com.ad.proxy.g.F.a("SocketClient", "url init url: " + str + " contentLen: " + i);
            a2.g = G.a(i, str).toString().getBytes();
            a0 a0Var = this.b;
            a0Var.e.execute(new T(a0Var, a2, null));
        } catch (Exception e) {
            com.ad.proxy.g.F.a("SocketClient", e);
            String message = e.getMessage();
            if (message == null) {
                message = "";
            }
            a2.g = message.getBytes();
            a0 a0Var2 = this.b;
            a0Var2.e.execute(new T(a0Var2, a2, null));
        }
    }
}
