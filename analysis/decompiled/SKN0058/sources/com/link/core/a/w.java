package com.link.core.a;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.NoConnectionPendingException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class w {
    public static final Map<Long, e> a = new HashMap();
    public static final AtomicBoolean b = new AtomicBoolean();
    public static final com.link.core.b.d c = new com.link.core.b.d();
    public static Selector d = null;
    public static i e = null;
    public static byte[] f = null;
    public static e g = null;
    public static boolean h = false;
    public static long i = 0;
    public static long j = 0;
    public static long k = 0;

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.HashMap, java.util.Map<java.lang.Long, com.link.core.a.e>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.HashMap, java.util.Map<java.lang.Long, com.link.core.a.e>] */
    public static void a() {
        Iterator it = a.entrySet().iterator();
        while (it.hasNext()) {
            ((e) ((Map.Entry) it.next()).getValue()).a();
        }
        a.clear();
        c.b();
        j = 0L;
        h = false;
        e eVar = g;
        if (eVar != null) {
            eVar.a();
            g = null;
        }
        f = null;
        e = null;
        try {
            d.close();
        } catch (IOException unused) {
            b.a("Closing selector error");
        }
        d = null;
    }

    public static void a(long j2) {
        if (h) {
            return;
        }
        try {
            g.a(o.a(j2));
        } catch (IOException unused) {
            h = true;
        }
    }

    public static boolean a(InetSocketAddress inetSocketAddress) {
        try {
            long time = new Date().getTime();
            while (time < k) {
                try {
                    Thread.sleep(60000L);
                    time = new Date().getTime();
                } catch (InterruptedException unused) {
                }
            }
            d = Selector.open();
            e = null;
            f = null;
            e eVar = new e();
            g = eVar;
            if (!eVar.a(-1L, inetSocketAddress, d)) {
                g = null;
                d.close();
                return false;
            }
            h = false;
            j = new Date().getTime();
            com.link.core.b.d dVar = c;
            if (!dVar.a(a.a)) {
                return false;
            }
            Selector selector = d;
            DatagramChannel datagramChannel = dVar.g;
            if (datagramChannel != null) {
                try {
                    datagramChannel.register(selector, 1, dVar);
                } catch (ClosedChannelException unused2) {
                    dVar.c();
                }
            }
            return true;
        } catch (IOException unused3) {
            b.a("Failed to open selector");
            return false;
        }
    }

    public static void b(long j2) {
        if (h) {
            return;
        }
        try {
            g.a(p.a(j2));
        } catch (IOException unused) {
            h = true;
        }
    }

    /* JADX WARN: Code duplicated, block: B:102:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:149:0x01c8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:151:0x00a3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:153:0x0123 A[EDGE_INSN: B:153:0x0123->B:67:0x0123 BREAK  A[LOOP:3: B:59:0x0112->B:68:0x0124], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:68:0x0124 A[Catch: CancelledKeyException -> 0x01a8, LOOP:3: B:59:0x0112->B:68:0x0124, LOOP_END, TryCatch #3 {CancelledKeyException -> 0x01a8, blocks: (B:50:0x00f8, B:52:0x00fe, B:54:0x0102, B:56:0x010a, B:59:0x0112, B:62:0x0117, B:68:0x0124, B:64:0x011e, B:69:0x0128, B:70:0x012e, B:72:0x013a, B:74:0x0150, B:77:0x0157, B:80:0x0176, B:82:0x017c, B:84:0x0180, B:87:0x0185, B:90:0x018a, B:92:0x01a1), top: B:119:0x00f8, inners: #0, #5, #7 }] */
    /* JADX WARN: Code duplicated, block: B:79:0x0174 A[DONT_INVERT, PHI: r7
      0x0174: PHI (r7v9 boolean) = 
      (r7v6 boolean)
      (r7v6 boolean)
      (r7v7 boolean)
      (r7v6 boolean)
      (r7v6 boolean)
      (r7v6 boolean)
      (r7v6 boolean)
      (r7v6 boolean)
     binds: [B:49:0x00f6, B:51:0x00fc, B:78:0x0173, B:73:0x014e, B:72:0x013a, B:67:0x0123, B:55:0x0108, B:57:0x010e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:80:0x0176 A[Catch: CancelledKeyException -> 0x01a8, TryCatch #3 {CancelledKeyException -> 0x01a8, blocks: (B:50:0x00f8, B:52:0x00fe, B:54:0x0102, B:56:0x010a, B:59:0x0112, B:62:0x0117, B:68:0x0124, B:64:0x011e, B:69:0x0128, B:70:0x012e, B:72:0x013a, B:74:0x0150, B:77:0x0157, B:80:0x0176, B:82:0x017c, B:84:0x0180, B:87:0x0185, B:90:0x018a, B:92:0x01a1), top: B:119:0x00f8, inners: #0, #5, #7 }] */
    /* JADX WARN: Code duplicated, block: B:99:0x01ad A[PHI: r7
      0x01ad: PHI (r7v2 boolean) = (r7v4 boolean), (r7v9 boolean), (r7v9 boolean), (r7v9 boolean), (r7v9 boolean), (r7v9 boolean), (r7v9 boolean) binds: [B:97:0x01aa, B:79:0x0174, B:81:0x017a, B:92:0x01a1, B:85:0x0182, B:90:0x018a, B:87:0x0185] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.HashMap, java.util.Map<java.lang.String, com.link.core.b.d$a>] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.util.HashMap, java.util.Map<java.lang.String, com.link.core.b.d$b>] */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.util.HashMap, java.util.Map<java.lang.Long, com.link.core.a.e>] */
    public static boolean b() {
        boolean z;
        e eVar;
        e.a aVarC;
        if (new Date().getTime() - j >= 900000) {
            b.a("ServerCheck timeout");
            return false;
        }
        com.link.core.b.d dVar = c;
        dVar.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis();
        Iterator it = dVar.e.entrySet().iterator();
        while (it.hasNext()) {
            if (((com.link.core.b.d.a) ((Map.Entry) it.next()).getValue()).b <= jCurrentTimeMillis) {
                it.remove();
            }
        }
        for (com.link.core.b.d.b bVar : new ArrayList(dVar.d.values())) {
            if (jCurrentTimeMillis - bVar.e >= 3000) {
                dVar.a(bVar);
            } else if (bVar.c < 4 && jCurrentTimeMillis >= bVar.f) {
                dVar.a(bVar, jCurrentTimeMillis);
            }
        }
        dVar.a.c();
        try {
            if (d.select(10L) == 0) {
                try {
                    Thread.sleep(15L);
                } catch (InterruptedException unused) {
                }
            }
            Set<SelectionKey> setSelectedKeys = d.selectedKeys();
            Iterator<SelectionKey> it2 = setSelectedKeys.iterator();
            byte[] bArr = new byte[3808];
            while (true) {
                boolean z2 = true;
                if (!it2.hasNext()) {
                    setSelectedKeys.clear();
                    return true;
                }
                SelectionKey next = it2.next();
                if (next.attachment() instanceof com.link.core.b.a.c) {
                    com.link.core.b.a.c cVar = (com.link.core.b.a.c) next.attachment();
                    if (cVar != c) {
                        throw new RuntimeException("object mismatch");
                    }
                    cVar.a();
                } else {
                    e eVar2 = (e) next.attachment();
                    SocketChannel socketChannel = eVar2.c;
                    try {
                        if (next.isConnectable()) {
                            if (eVar2 == g) {
                                c();
                                d();
                            } else {
                                try {
                                    socketChannel.finishConnect();
                                    eVar2.b();
                                    b(eVar2.b);
                                } catch (IOException | NoConnectionPendingException | Exception unused2) {
                                    z = true;
                                }
                            }
                            z = false;
                        } else {
                            z = false;
                        }
                        if (!z) {
                            try {
                                if (next.isReadable() && (eVar2 == (eVar = g) || (eVar2.h.a <= 30 && d.d <= 800))) {
                                    if (eVar2 == eVar) {
                                        while (true) {
                                            if (h) {
                                                aVarC = null;
                                                if (aVarC == null) {
                                                    break;
                                                }
                                                a(aVarC);
                                            } else {
                                                try {
                                                    aVarC = g.c();
                                                } catch (IOException unused3) {
                                                    h = true;
                                                    aVarC = null;
                                                }
                                                if (aVarC == null) {
                                                    break;
                                                    break;
                                                }
                                                a(aVarC);
                                            }
                                        }
                                    } else {
                                        long j2 = eVar2.b;
                                        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
                                        try {
                                            int i2 = eVar2.c.read(byteBufferWrap);
                                            if (byteBufferWrap.position() > 0) {
                                                byte[] bArr2 = new byte[byteBufferWrap.position()];
                                                System.arraycopy(bArr, 0, bArr2, 0, byteBufferWrap.position());
                                                a(j2, bArr2);
                                                byteBufferWrap.clear();
                                            } else if (i2 < 0) {
                                                Objects.toString(eVar2.c);
                                                z = true;
                                            }
                                        } catch (IOException e2) {
                                            b.a("IoException: ConnectionId=" + j2 + " exception=" + e2);
                                        }
                                    }
                                    if (z) {
                                        z2 = z;
                                    } else {
                                        z2 = z;
                                    }
                                } else if (z || !next.isWritable()) {
                                    z2 = z;
                                } else {
                                    e eVar3 = g;
                                    if (eVar2 != eVar3) {
                                        try {
                                            eVar2.b();
                                        } catch (IOException unused4) {
                                        }
                                    } else if (!h) {
                                        try {
                                            eVar3.b();
                                        } catch (IOException e3) {
                                            b.a("Flush proxy output error: " + e3);
                                            h = true;
                                        }
                                    }
                                    z2 = z;
                                }
                            } catch (CancelledKeyException unused5) {
                                if (eVar2 == g) {
                                }
                                a(false);
                                if (z2) {
                                    long j3 = eVar2.b;
                                    a.remove(Long.valueOf(j3));
                                    eVar2.a();
                                    a(j3);
                                }
                                if (h) {
                                    return false;
                                }
                            }
                        } else if (z) {
                            z2 = z;
                        } else {
                            z2 = z;
                        }
                    } catch (CancelledKeyException unused6) {
                        z = false;
                    }
                    a(false);
                    if (z2) {
                        long j4 = eVar2.b;
                        a.remove(Long.valueOf(j4));
                        eVar2.a();
                        a(j4);
                    }
                    if (h) {
                        return false;
                    }
                }
            }
        } catch (IOException e4) {
            b.a("Selector error " + e4);
            return false;
        }
    }

    public static void c() {
        if (h) {
            throw new RuntimeException("unexpected error before connection is establshed");
        }
        try {
            g.c.finishConnect();
        } catch (IOException e2) {
            b.a("prxServer finish connection error: " + e2);
            h = true;
        }
    }

    public static void d() {
        if (h) {
            return;
        }
        try {
            g.a(n.a(f, e, s.a));
            i = new Date().getTime();
        } catch (IOException e2) {
            b.a("Post chkkey to proxy error: " + e2);
            h = true;
        }
    }

    public static void e() {
        b.set(false);
    }

    public static void a(boolean z) {
        if (h) {
            return;
        }
        long time = new Date().getTime();
        if (z || time - i >= 60000) {
            try {
                byte[] bArr = new byte[32];
                ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
                ByteOrder byteOrder = k.a;
                byteBufferWrap.order(byteOrder);
                if (byteBufferWrap.order() == byteOrder && byteBufferWrap.remaining() >= 32) {
                    byteBufferWrap.putInt(-855637984);
                    byte b2 = (byte) 0;
                    byteBufferWrap.put(b2);
                    byteBufferWrap.put(b2);
                    byteBufferWrap.putShort((short) 0);
                    byteBufferWrap.putLong(0L);
                    byteBufferWrap.putLong(0L);
                    byteBufferWrap.putLong(0L);
                }
                g.a(bArr);
            } catch (IOException unused) {
                h = true;
            }
            i = time;
        }
    }

    /* JADX WARN: Type inference failed for: r0v16, types: [java.util.HashMap, java.util.Map<java.lang.Long, com.link.core.a.e>] */
    /* JADX WARN: Type inference failed for: r0v30, types: [java.util.HashMap, java.util.Map<java.lang.Long, com.link.core.a.e>] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.util.HashMap, java.util.Map<java.lang.Long, com.link.core.a.e>] */
    /* JADX WARN: Type inference failed for: r10v21, types: [java.util.HashMap, java.util.Map<java.lang.Long, com.link.core.a.e>] */
    /* JADX WARN: Type inference failed for: r10v26, types: [java.util.HashMap, java.util.Map<java.lang.Long, com.link.core.a.e>] */
    public static void a(e.a aVar) {
        e eVar;
        e eVar2;
        j = new Date().getTime();
        if (h) {
            return;
        }
        int i2 = aVar.a.d;
        r rVar = null;
        q qVar = null;
        boolean z = true;
        if (i2 == 9) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(aVar.b);
            byteBufferWrap.order(k.a);
            try {
                long j2 = byteBufferWrap.getLong();
                byte[] bArr = new byte[255 & byteBufferWrap.get()];
                byteBufferWrap.get(bArr);
                String str = new String(bArr);
                int i3 = byteBufferWrap.getShort() & 65535;
                r rVar2 = new r();
                rVar2.a = j2;
                rVar2.b = str;
                rVar2.c = i3;
                rVar = rVar2;
            } catch (Exception unused) {
            }
            e eVar3 = new e();
            long j3 = rVar.a;
            String str2 = rVar.b;
            int i4 = rVar.c;
            com.link.core.b.d dVar = c;
            eVar3.a = d;
            eVar3.b = -1L;
            try {
                SocketChannel socketChannelOpen = SocketChannel.open();
                eVar3.c = socketChannelOpen;
                socketChannelOpen.configureBlocking(false);
                eVar3.c.socket().setReceiveBufferSize(4096000);
                eVar3.c.socket().setSendBufferSize(4096000);
                eVar3.d = i4;
                dVar.a(str2, eVar3);
                Objects.toString(eVar3.c);
                eVar3.b = j3;
            } catch (IOException unused2) {
                b.a("Failed to open channel");
                z = false;
            }
            if (!z) {
                a(rVar.a);
            }
            eVar = (e) a.put(Long.valueOf(eVar3.b), eVar3);
            if (eVar == null) {
                return;
            }
        } else {
            if (i2 == 11) {
                ByteBuffer byteBufferWrap2 = ByteBuffer.wrap(aVar.b);
                byteBufferWrap2.order(k.a);
                k = new Date().getTime() + byteBufferWrap2.getLong();
                return;
            }
            if (i2 == 255) {
                a(true);
                return;
            }
            if (i2 != 5) {
                if (i2 == 6) {
                    ByteBuffer byteBufferWrap3 = ByteBuffer.wrap(aVar.b);
                    byteBufferWrap3.order(k.a);
                    long j4 = byteBufferWrap3.getLong();
                    ?? r10 = a;
                    e eVar4 = (e) r10.get(Long.valueOf(j4));
                    if (eVar4 == null) {
                        return;
                    }
                    r10.remove(Long.valueOf(eVar4.b));
                    eVar2 = eVar4;
                } else {
                    if (i2 != 7) {
                        return;
                    }
                    ByteBuffer byteBufferWrap4 = ByteBuffer.wrap(aVar.b);
                    byteBufferWrap4.order(k.a);
                    long j5 = byteBufferWrap4.getLong();
                    byte[] bArr2 = new byte[byteBufferWrap4.remaining()];
                    byteBufferWrap4.get(bArr2);
                    eVar2 = (e) a.get(Long.valueOf(j5));
                    if (eVar2 == null) {
                        return;
                    }
                    try {
                        eVar2.a(bArr2);
                        return;
                    } catch (IOException e2) {
                        b.a("post payload to upstream error: " + e2);
                        a.remove(Long.valueOf(eVar2.b));
                    }
                }
                eVar2.a();
                return;
            }
            ByteBuffer byteBufferWrap5 = ByteBuffer.wrap(aVar.b);
            byteBufferWrap5.order(k.a);
            try {
                long j6 = byteBufferWrap5.getLong();
                byte[] bArr3 = new byte[4];
                byteBufferWrap5.get(bArr3);
                int i5 = byteBufferWrap5.getShort() & 65535;
                q qVar2 = new q();
                qVar2.a = j6;
                qVar2.b = new InetSocketAddress(InetAddress.getByAddress(bArr3), i5);
                qVar = qVar2;
            } catch (UnknownHostException unused3) {
            }
            e eVar5 = new e();
            if (!eVar5.a(qVar.a, qVar.b, d)) {
                a(qVar.a);
            }
            eVar = (e) a.put(Long.valueOf(eVar5.b), eVar5);
            if (eVar == null) {
                return;
            }
        }
        eVar.a();
    }

    public static void a(long j2, byte[] bArr) {
        byte[] bArr2;
        if (h) {
            return;
        }
        int length = bArr.length + 8;
        if (length > 4064) {
            bArr2 = null;
        } else {
            byte[] bArr3 = new byte[length];
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr3);
            ByteOrder byteOrder = k.a;
            byteBufferWrap.order(byteOrder);
            byteBufferWrap.putLong(j2);
            byteBufferWrap.put(bArr);
            int i2 = length + 32;
            byte[] bArr4 = new byte[i2];
            ByteBuffer byteBufferWrap2 = ByteBuffer.wrap(bArr4);
            byteBufferWrap2.order(byteOrder);
            if (byteBufferWrap2.order() == byteOrder && byteBufferWrap2.remaining() >= 32) {
                byteBufferWrap2.putInt((-855638016) | i2);
                byte b2 = (byte) 0;
                byteBufferWrap2.put(b2);
                byteBufferWrap2.put(b2);
                byteBufferWrap2.putShort((short) 7);
                byteBufferWrap2.putLong(0L);
                byteBufferWrap2.putLong(0L);
                byteBufferWrap2.putLong(0L);
                byteBufferWrap2.put(bArr3);
            }
            bArr2 = bArr4;
        }
        try {
            g.a(bArr2);
        } catch (IOException e2) {
            b.a("Post payload to proxy error: " + e2);
            h = true;
        }
    }
}
