package com.ad.proxy.b;

import com.ad.proxy.Robin;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class a0 {
    public static int n = 3;
    public volatile Socket a;
    public DataInputStream b;
    public DataOutputStream c;
    public final ConcurrentHashMap d;
    public final ScheduledExecutorService e;
    public final ScheduledExecutorService f;
    public final Object g;
    public volatile boolean h;
    public final String i;
    public final String j;
    public ScheduledFuture k;
    public int l;
    public final com.ad.proxy.e.A m;

    public a0(com.ad.proxy.e.A a, String str) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        this.d = concurrentHashMap;
        this.e = Executors.newScheduledThreadPool(1);
        this.f = Executors.newScheduledThreadPool(1);
        this.g = new Object();
        this.h = false;
        this.m = a;
        this.i = a.c;
        this.j = str;
        concurrentHashMap.put((byte) 1, new U(this));
        concurrentHashMap.put((byte) 2, new V());
        concurrentHashMap.put((byte) 3, new W(this));
        concurrentHashMap.put((byte) 5, new X(this));
        concurrentHashMap.put((byte) 7, new Y(this));
        concurrentHashMap.put((byte) 8, new Z(this));
        concurrentHashMap.put((byte) 9, new r(this));
        concurrentHashMap.put((byte) 11, new K(this));
        concurrentHashMap.put((byte) 12, new L(this));
        concurrentHashMap.put((byte) 14, new M(this));
    }

    public final void a() throws SocketException {
        com.ad.proxy.e.A a = this.m;
        this.a = new Socket(a.a, a.b);
        this.a.setTcpNoDelay(true);
        this.a.setKeepAlive(true);
        this.b = new DataInputStream(new BufferedInputStream(this.a.getInputStream()));
        this.c = new DataOutputStream(new BufferedOutputStream(this.a.getOutputStream()));
        com.ad.proxy.g.F.a("SocketClient", "Connected to server");
        ScheduledFuture scheduledFuture = this.k;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        int i = com.ad.proxy.d.A.a;
        this.l = i;
        long j = i;
        this.k = this.f.scheduleWithFixedDelay(new N(this), j, j, TimeUnit.MILLISECONDS);
        com.ad.proxy.f.C.a(this.m, 1, Robin.channel);
        String strA = com.ad.proxy.g.J.a();
        String str = this.i + "|" + this.j;
        com.ad.proxy.g.F.a("SocketClient", "Join service with payload: " + str);
        this.e.execute(new T(this, new com.ad.proxy.c.A(0L, strA, (byte) 0, 0, str.getBytes()), null));
    }

    public final void b() throws IOException {
        while (this.h && !this.a.isClosed()) {
            DataInputStream dataInputStream = this.b;
            byte[] bArr = new byte[2];
            dataInputStream.readFully(bArr);
            if (bArr[0] != 75 || bArr[1] != 75) {
                throw new IOException("Invalid Magic Code");
            }
            com.ad.proxy.c.A a = new com.ad.proxy.c.A();
            a.a = dataInputStream.readLong();
            byte[] bArr2 = new byte[8];
            dataInputStream.readFully(bArr2);
            a.b = String.format("%016x", Long.valueOf(ByteBuffer.wrap(bArr2).getLong()));
            a.c = dataInputStream.readByte();
            a.d = dataInputStream.readByte();
            a.e = dataInputStream.readInt();
            a.f = dataInputStream.readInt();
            byte[] bArr3 = new byte[a.e];
            a.g = bArr3;
            dataInputStream.readFully(bArr3);
            E e = (E) this.d.get(Byte.valueOf(a.d));
            if (e != null) {
                e.a(a);
            } else {
                com.ad.proxy.g.F.a("SocketClient", "Unknown command: " + ((int) a.d));
            }
        }
    }

    public final void a(D d) {
        if (d instanceof g0) {
            g0 g0Var = (g0) d;
            g0Var.i.execute(new d0(g0Var, new O(this, g0Var)));
        } else if (d instanceof k0) {
            k0 k0Var = (k0) d;
            k0Var.h.execute(new h0(k0Var));
            J.a().a.put(Long.valueOf(k0Var.d), k0Var);
        }
    }
}
