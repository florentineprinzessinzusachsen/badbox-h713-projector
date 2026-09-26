package com.ad.proxy.b;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public final class k0 extends D {
    public final a0 a;
    public final String b;
    public final int c;
    public final long d;
    public DatagramSocket e;
    public InetAddress f;
    public volatile boolean g = false;
    public final ExecutorService h = Executors.newSingleThreadExecutor();
    public final ExecutorService i = Executors.newSingleThreadExecutor();
    public int j = 0;
    public int k = 0;
    public final HashMap l = new HashMap();

    public k0(a0 a0Var, String str, int i, long j) {
        this.a = a0Var;
        this.b = str;
        this.c = i;
        this.d = j;
    }

    public static void a(k0 k0Var, byte[] bArr) {
        if (bArr.length != 8) {
            throw new IllegalArgumentException("UUID bytes must be exactly 8 bytes");
        }
        String str = String.format("%016x", Long.valueOf(ByteBuffer.wrap(bArr).getLong()));
        long j = k0Var.d;
        int i = k0Var.j;
        com.ad.proxy.c.A a = new com.ad.proxy.c.A(j, str, (byte) 10, i, bArr);
        k0Var.j = i + 1;
        a0 a0Var = k0Var.a;
        a0Var.e.execute(new T(a0Var, a, new j0(k0Var, a)));
    }

    @Override // com.ad.proxy.b.D
    public final a0 b() {
        return this.a;
    }

    @Override // com.ad.proxy.b.D
    public final void c() {
    }

    @Override // com.ad.proxy.b.D
    public final void a(com.ad.proxy.c.A a) {
        this.i.execute(new i0(this, a));
    }

    @Override // com.ad.proxy.b.D
    public final void a() {
        this.g = false;
        DatagramSocket datagramSocket = this.e;
        if (datagramSocket != null) {
            try {
                datagramSocket.close();
            } catch (IOException e) {
                com.ad.proxy.g.F.a("E", e);
            }
        }
        this.h.shutdownNow();
        this.i.shutdownNow();
        J.a().a(this.d);
        J jA = J.a();
        synchronized (jA.c) {
            jA.b.remove(this);
        }
    }
}
