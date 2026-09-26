package com.link.core.a;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class e implements com.link.core.b.a.d {
    public Selector a = null;
    public long b = -1;
    public SocketChannel c = null;
    public int d = 0;
    public final byte[] e = new byte[4096];
    public int f = 0;
    public k.a g = null;
    public final d h = new d();

    public static class a {
        public final k.a a;
        public final byte[] b;

        public a(k.a aVar, byte[] bArr) {
            this.a = aVar;
            this.b = bArr;
        }
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [java.util.HashMap, java.util.Map<java.lang.Long, com.link.core.a.e>] */
    @Override // com.link.core.b.a.d
    public final void a(com.link.core.b.a.b bVar, InetAddress inetAddress) {
        Objects.toString(bVar);
        Objects.toString(inetAddress);
        try {
            if (bVar != com.link.core.b.a.b.FAILED && inetAddress != null && this.c != null) {
                this.c.connect(new InetSocketAddress(inetAddress, this.d));
                this.c.register(this.a, 9, this);
                return;
            }
        } catch (IOException unused) {
        }
        w.a(this.b);
        w.a.remove(Long.valueOf(this.b));
        a();
    }

    public final boolean a(long j, InetSocketAddress inetSocketAddress, Selector selector) {
        this.a = selector;
        this.b = -1L;
        try {
            SocketChannel socketChannelOpen = SocketChannel.open();
            this.c = socketChannelOpen;
            socketChannelOpen.configureBlocking(false);
            this.c.connect(inetSocketAddress);
            this.c.register(selector, 9, this);
            this.c.socket().setKeepAlive(true);
            this.c.socket().setReceiveBufferSize(4096000);
            this.c.socket().setSendBufferSize(4096000);
            this.b = j;
            return true;
        } catch (IOException unused) {
            b.a("Failed to open channel");
            return false;
        }
    }

    public final void b() {
        while (true) {
            c cVar = this.h.b;
            if (cVar == null) {
                if (cVar == null) {
                    this.c.register(this.a, 1, this);
                    return;
                }
                return;
            } else {
                this.c.write(cVar.a);
                if (cVar.a.remaining() > 0) {
                    this.c.register(this.a, 5, this);
                    return;
                }
                this.h.a();
            }
        }
    }

    public final a c() {
        byte[] bArr = this.e;
        int i = this.f;
        int i2 = this.c.read(ByteBuffer.wrap(bArr, i, bArr.length - i));
        if (i2 < 0) {
            throw new IOException("prx server close");
        }
        if (i2 > 0) {
            this.f += i2;
        }
        int i3 = this.f;
        if (i3 < 32) {
            return null;
        }
        if (this.g == null) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(this.e, 0, i3);
            byteBufferWrap.order(k.a);
            k.a aVarA = k.a(byteBufferWrap);
            this.g = aVarA;
            if (aVarA == null) {
                throw new IOException("Invalid packet header");
            }
        }
        int i4 = this.f;
        k.a aVar = this.g;
        int i5 = aVar.a;
        if (i4 < i5) {
            return null;
        }
        int i6 = i5 >= 32 ? i5 - 32 : -1;
        byte[] bArr2 = new byte[i6];
        byte[] bArr3 = this.e;
        aVar.getClass();
        System.arraycopy(bArr3, 32, bArr2, 0, i6);
        int i7 = this.f;
        int i8 = this.g.a;
        int i9 = i7 - i8;
        this.f = i9;
        byte[] bArr4 = this.e;
        System.arraycopy(bArr4, i8, bArr4, 0, i9);
        a aVar2 = new a(this.g, bArr2);
        this.g = null;
        return aVar2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a() {
        Objects.toString(this.c);
        while (this.h.a() != null) {
        }
        this.f = 0;
        this.g = null;
        try {
            try {
                SocketChannel socketChannel = this.c;
                if (socketChannel != null) {
                    socketChannel.close();
                }
            } catch (IOException unused) {
                b.a("Closing channel error");
            }
        } finally {
            this.c = null;
            this.b = -1L;
            this.a = null;
        }
    }

    public final void a(byte[] bArr) {
        if (bArr.length > 4096) {
            throw new RuntimeException("Invalid packet size, find the bug!");
        }
        c cVar = this.h.c;
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        if (!this.c.isConnected() || cVar != null) {
            c cVar2 = new c();
            cVar2.a.put(byteBufferWrap.array(), byteBufferWrap.position(), byteBufferWrap.remaining());
            cVar2.a.flip();
            this.h.a(cVar2);
            return;
        }
        this.c.write(byteBufferWrap);
        if (byteBufferWrap.remaining() > 0) {
            c cVar3 = new c();
            cVar3.a.put(byteBufferWrap.array(), byteBufferWrap.position(), byteBufferWrap.remaining());
            cVar3.a.flip();
            this.h.a(cVar3);
            this.c.register(this.a, 5, this);
        }
    }
}
