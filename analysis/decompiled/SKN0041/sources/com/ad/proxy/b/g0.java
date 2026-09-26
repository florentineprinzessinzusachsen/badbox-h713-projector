package com.ad.proxy.b;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public final class g0 extends D {
    public final a0 a;
    public final String b;
    public final int c;
    public final long d;
    public Socket f;
    public InputStream g;
    public OutputStream h;
    public volatile boolean e = false;
    public final ExecutorService i = Executors.newSingleThreadExecutor();
    public final ExecutorService j = Executors.newSingleThreadExecutor();
    public int k = 0;
    public int l = 0;
    public final HashMap m = new HashMap();
    public volatile boolean n = false;
    public volatile boolean o = false;

    public g0(a0 a0Var, String str, int i, long j) {
        this.a = a0Var;
        this.b = str;
        this.c = i;
        this.d = j;
    }

    @Override // com.ad.proxy.b.D
    public final void a(com.ad.proxy.c.A a) {
        this.j.execute(new e0(this, a));
    }

    @Override // com.ad.proxy.b.D
    public final a0 b() {
        return this.a;
    }

    @Override // com.ad.proxy.b.D
    public final void c() {
        this.n = true;
    }

    @Override // com.ad.proxy.b.D
    public final void a() {
        if (this.o) {
            return;
        }
        this.o = true;
        this.e = false;
        Socket socket = this.f;
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                com.ad.proxy.g.F.a("E", e);
            }
        }
        this.i.shutdownNow();
        this.j.shutdownNow();
        J.a().a(this.d);
        J jA = J.a();
        synchronized (jA.c) {
            jA.b.remove(this);
        }
        if (this.n) {
            return;
        }
        a0 a0Var = this.a;
        long j = this.d;
        a0Var.getClass();
        a0Var.e.execute(new T(a0Var, new com.ad.proxy.c.A(j, com.ad.proxy.g.J.a(), (byte) 7, 0, null), null));
    }

    public static void a(g0 g0Var, byte[] bArr) {
        String strA = com.ad.proxy.g.A.a();
        long j = g0Var.d;
        int i = g0Var.k;
        com.ad.proxy.c.A a = new com.ad.proxy.c.A(j, strA, (byte) 6, i, bArr);
        g0Var.k = i + 1;
        a0 a0Var = g0Var.a;
        a0Var.e.execute(new T(a0Var, a, new f0(g0Var, a)));
    }
}
