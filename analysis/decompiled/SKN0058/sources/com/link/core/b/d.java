package com.link.core.b;

import com.link.core.a.e;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public final class d implements com.link.core.b.a.c {
    public static final String[] i = {"8.8.8.8", "1.1.1.1", "9.9.9.9", "1.0.0.1"};
    public DatagramChannel g;
    public final com.link.core.b.c a = new com.link.core.b.c();
    public final Random b = new Random();
    public final Map<Integer, b> c = new HashMap();
    public final Map<String, b> d = new HashMap();
    public final Map<String, a> e = new HashMap();
    public final byte[] f = new byte[2048];
    public InetSocketAddress[] h = new InetSocketAddress[0];

    public static class a {
        public InetAddress a;
        public long b;
    }

    public static class b {
        public String a;
        public int b;
        public int c;
        public int d;
        public long e;
        public long f;
        public final ArrayList<com.link.core.b.a.d> g = new ArrayList<>();
    }

    public class c implements com.link.core.b.a.d {
        public final String a;
        public final ArrayList<com.link.core.b.a.d> b;

        public c(String str, ArrayList<com.link.core.b.a.d> arrayList) {
            this.a = str;
            this.b = arrayList;
        }

        @Override // com.link.core.b.a.d
        public final void a(com.link.core.b.a.b bVar, InetAddress inetAddress) {
            com.link.core.b.a.b bVar2 = com.link.core.b.a.b.FAILED;
            boolean z = (bVar == bVar2 || inetAddress == null) ? false : true;
            d.this.a(this.a, z ? inetAddress : null);
            d dVar = d.this;
            if (!z) {
                bVar = bVar2;
            }
            if (!z) {
                inetAddress = null;
            }
            ArrayList<com.link.core.b.a.d> arrayList = this.b;
            dVar.getClass();
            Iterator<com.link.core.b.a.d> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().a(bVar, inetAddress);
            }
        }
    }

    public final int a(byte[] bArr, int i2) {
        return (bArr[i2 + 1] & 255) | ((bArr[i2] & 255) << 8);
    }

    public final int a(byte[] bArr, int i2, int i3) {
        while (i2 < i3) {
            int i4 = bArr[i2] & 255;
            if (i4 == 0) {
                return i2 + 1;
            }
            int i5 = i4 & 192;
            if (i5 == 192) {
                int i6 = i2 + 2;
                if (i6 <= i3) {
                    return i6;
                }
                return -1;
            }
            if (i5 != 0) {
                return -1;
            }
            i2 += i4 + 1;
        }
        return -1;
    }

    public final InetAddress a(byte[] bArr, int i2, String str) {
        int i3;
        int iA = a(bArr, 4);
        int iA2 = a(bArr, 6);
        int i4 = 12;
        for (int i5 = 0; i5 < iA; i5++) {
            int iA3 = a(bArr, i4, i2);
            if (iA3 < 0 || (i4 = iA3 + 4) > i2) {
                return null;
            }
        }
        int i6 = 0;
        while (i6 < iA2) {
            int iA4 = a(bArr, i4, i2);
            if (iA4 < 0 || (i3 = iA4 + 10) > i2) {
                break;
            }
            int iA5 = a(bArr, iA4);
            int iA6 = a(bArr, iA4 + 2);
            int iA7 = a(bArr, iA4 + 8);
            int i7 = i3 + iA7;
            if (i7 > i2) {
                return null;
            }
            if (iA5 == 1 && iA6 == 1 && iA7 == 4) {
                byte[] bArr2 = new byte[4];
                System.arraycopy(bArr, i3, bArr2, 0, 4);
                return InetAddress.getByAddress(str, bArr2);
            }
            i6++;
            i4 = i7;
        }
        return null;
    }

    public final void a(b bVar) {
        if (b(bVar)) {
            ArrayList arrayList = new ArrayList(bVar.g);
            com.link.core.b.c cVar = this.a;
            String str = bVar.a;
            cVar.a(str, new c(str, arrayList));
        }
    }

    public final void a(b bVar, long j) {
        int i2;
        if (this.g == null || (i2 = bVar.b) == 0) {
            a(bVar);
            return;
        }
        try {
            byte[] bArrA = a(bVar.a, i2);
            InetSocketAddress[] inetSocketAddressArr = this.h;
            this.g.send(ByteBuffer.wrap(bArrA), inetSocketAddressArr[(bVar.d + bVar.c) % inetSocketAddressArr.length]);
            bVar.c++;
            bVar.f = j + 800;
        } catch (IOException unused) {
            bVar.f = j + 800;
        } catch (RuntimeException unused2) {
            a(bVar);
        }
    }

    /* JADX WARN: Type inference failed for: r7v1, types: [java.util.HashMap, java.util.Map<java.lang.String, com.link.core.b.d$a>] */
    public final void a(String str, InetAddress inetAddress) {
        a aVar = new a();
        aVar.a = inetAddress;
        aVar.b = System.currentTimeMillis() + (inetAddress == null ? 5000L : 30000L);
        this.e.put(str, aVar);
    }

    public final byte[] a(String str, int i2) {
        String[] strArrSplit = str.split("\\.");
        ArrayList<byte[]> arrayList = new ArrayList();
        int length = 1;
        for (String str2 : strArrSplit) {
            if (str2.length() == 0) {
                throw new IllegalArgumentException("empty dns label");
            }
            byte[] bytes = str2.getBytes(StandardCharsets.UTF_8);
            if (bytes.length > 63) {
                throw new IllegalArgumentException("dns label too long");
            }
            arrayList.add(bytes);
            length += bytes.length + 1;
        }
        if (length > 255) {
            throw new IllegalArgumentException("dns name too long");
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length + 12 + 4);
        byteBufferAllocate.putShort((short) i2);
        byteBufferAllocate.putShort((short) 256);
        byteBufferAllocate.putShort((short) 1);
        byteBufferAllocate.putShort((short) 0);
        byteBufferAllocate.putShort((short) 0);
        byteBufferAllocate.putShort((short) 0);
        for (byte[] bArr : arrayList) {
            byteBufferAllocate.put((byte) bArr.length);
            byteBufferAllocate.put(bArr);
        }
        byteBufferAllocate.put((byte) 0);
        byteBufferAllocate.putShort((short) 1);
        byteBufferAllocate.putShort((short) 1);
        return byteBufferAllocate.array();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.HashMap, java.util.Map<java.lang.Integer, com.link.core.b.d$b>] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.HashMap, java.util.Map<java.lang.String, com.link.core.b.d$b>] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.HashMap, java.util.Map<java.lang.String, com.link.core.b.d$a>] */
    public final void b() {
        c();
        this.c.clear();
        this.d.clear();
        this.e.clear();
        this.a.b();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.HashMap, java.util.Map<java.lang.String, com.link.core.b.d$b>] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.HashMap, java.util.Map<java.lang.Integer, com.link.core.b.d$b>] */
    public final boolean b(b bVar) {
        b bVar2 = (b) this.d.remove(bVar.a);
        this.c.remove(Integer.valueOf(bVar.b));
        return bVar2 == bVar;
    }

    public final void c() {
        DatagramChannel datagramChannel = this.g;
        if (datagramChannel == null) {
            return;
        }
        try {
            datagramChannel.close();
        } catch (IOException unused) {
        }
        this.g = null;
    }

    /* JADX WARN: Code duplicated, block: B:47:0x007b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:53:0x004c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:54:0x004b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:55:0x0057 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:56:0x0056 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:57:0x006a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:58:0x0069 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:59:0x0076 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:61:0x008d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:62:0x0088 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:63:0x0083 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:65:0x0005 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.util.HashMap, java.util.Map<java.lang.Integer, com.link.core.b.d$b>] */
    @Override // com.link.core.b.a.c
    public final void a() {
        boolean z;
        byte[] bArr;
        int iPosition;
        b bVar;
        int iA;
        InetAddress inetAddressA;
        if (this.g == null) {
            return;
        }
        while (true) {
            try {
                ByteBuffer byteBufferWrap = ByteBuffer.wrap(this.f);
                SocketAddress socketAddressReceive = this.g.receive(byteBufferWrap);
                if (socketAddressReceive != null && byteBufferWrap.position() > 0) {
                    if (socketAddressReceive instanceof InetSocketAddress) {
                        InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddressReceive;
                        InetSocketAddress[] inetSocketAddressArr = this.h;
                        int length = inetSocketAddressArr.length;
                        int i2 = 0;
                        while (true) {
                            if (i2 < length) {
                                InetSocketAddress inetSocketAddress2 = inetSocketAddressArr[i2];
                                if (inetSocketAddress2.getPort() == inetSocketAddress.getPort() && inetSocketAddress2.getAddress().equals(inetSocketAddress.getAddress())) {
                                    z = true;
                                    break;
                                }
                                i2++;
                            }
                        }
                        if (!z) {
                            bArr = this.f;
                            iPosition = byteBufferWrap.position();
                            if (iPosition < 12) {
                                bVar = (b) this.c.get(Integer.valueOf(a(bArr, 0)));
                                if (bVar == null) {
                                    iA = a(bArr, 2);
                                    if ((32768 & iA) != 0) {
                                        if ((iA & 15) != 0) {
                                            try {
                                                inetAddressA = a(bArr, iPosition, bVar.a);
                                                if (inetAddressA == null) {
                                                    a(bVar);
                                                } else {
                                                    a(bVar, inetAddressA);
                                                }
                                            } catch (RuntimeException | UnknownHostException unused) {
                                                a(bVar);
                                            }
                                        }
                                        a(bVar);
                                    }
                                }
                            }
                        }
                    }
                    z = false;
                    if (!z) {
                        bArr = this.f;
                        iPosition = byteBufferWrap.position();
                        if (iPosition < 12) {
                            bVar = (b) this.c.get(Integer.valueOf(a(bArr, 0)));
                            if (bVar == null) {
                                iA = a(bArr, 2);
                                if ((32768 & iA) != 0) {
                                    if ((iA & 15) != 0) {
                                        inetAddressA = a(bArr, iPosition, bVar.a);
                                        if (inetAddressA == null) {
                                            a(bVar);
                                        } else {
                                            a(bVar, inetAddressA);
                                        }
                                    }
                                    a(bVar);
                                }
                            }
                        }
                    }
                }
                return;
            } catch (IOException unused2) {
                return;
            }
        }
    }

    public final void a(b bVar, InetAddress inetAddress) {
        com.link.core.b.a.b bVar2 = com.link.core.b.a.b.DONE;
        if (b(bVar)) {
            a(bVar.a, inetAddress);
            Iterator<com.link.core.b.a.d> it = bVar.g.iterator();
            while (it.hasNext()) {
                it.next().a(bVar2, inetAddress);
            }
        }
    }

    public final boolean a(Object obj) {
        this.a.a(obj);
        String[] strArr = i;
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (str != null) {
                String strTrim = str.trim();
                if (strTrim.length() != 0) {
                    int iLastIndexOf = strTrim.lastIndexOf(58);
                    int i2 = 53;
                    if (iLastIndexOf > 0 && iLastIndexOf < strTrim.length() - 1 && strTrim.indexOf(58) == iLastIndexOf) {
                        try {
                            int i3 = Integer.parseInt(strTrim.substring(iLastIndexOf + 1));
                            strTrim = strTrim.substring(0, iLastIndexOf);
                            i2 = i3;
                        } catch (NumberFormatException unused) {
                        }
                    }
                    try {
                        arrayList.add(new InetSocketAddress(InetAddress.getByName(strTrim), i2));
                    } catch (UnknownHostException unused2) {
                    }
                }
            }
        }
        InetSocketAddress[] inetSocketAddressArr = (InetSocketAddress[]) arrayList.toArray(new InetSocketAddress[arrayList.size()]);
        this.h = inetSocketAddressArr;
        if (inetSocketAddressArr.length == 0) {
            return true;
        }
        try {
            DatagramChannel datagramChannelOpen = DatagramChannel.open();
            this.g = datagramChannelOpen;
            datagramChannelOpen.configureBlocking(false);
            this.g.socket().bind(new InetSocketAddress(0));
            return true;
        } catch (IOException unused3) {
            c();
            return true;
        }
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [java.util.HashMap, java.util.Map<java.lang.String, com.link.core.b.d$b>] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.util.HashMap, java.util.Map<java.lang.String, com.link.core.b.d$a>] */
    /* JADX WARN: Type inference failed for: r10v4, types: [java.util.HashMap, java.util.Map<java.lang.String, com.link.core.b.d$b>] */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.util.HashMap, java.util.Map<java.lang.String, com.link.core.b.d$a>] */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.util.HashMap, java.util.Map<java.lang.Integer, com.link.core.b.d$b>] */
    /* JADX WARN: Type inference failed for: r9v4, types: [java.util.HashMap, java.util.Map<java.lang.Integer, com.link.core.b.d$b>] */
    public final void a(String str, com.link.core.b.a.d dVar) {
        if (str == null || str.length() == 0) {
            ((e) dVar).a(com.link.core.b.a.b.FAILED, null);
            return;
        }
        if (com.link.core.b.a.a(str)) {
            try {
                ((e) dVar).a(com.link.core.b.a.b.CACHED, InetAddress.getByName(str));
                return;
            } catch (UnknownHostException unused) {
                ((e) dVar).a(com.link.core.b.a.b.FAILED, null);
                return;
            }
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        a aVar = (a) this.e.get(str);
        if (aVar != null && aVar.b > jCurrentTimeMillis) {
            InetAddress inetAddress = aVar.a;
            if (inetAddress != null) {
                ((e) dVar).a(com.link.core.b.a.b.CACHED, inetAddress);
                return;
            } else {
                ((e) dVar).a(com.link.core.b.a.b.FAILED, null);
                return;
            }
        }
        if (aVar != null) {
            this.e.remove(str);
        }
        if (this.g == null || this.h.length == 0) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(dVar);
            this.a.a(str, new c(str, arrayList));
            return;
        }
        b bVar = (b) this.d.get(str);
        if (bVar != null) {
            bVar.g.add(dVar);
            return;
        }
        b bVar2 = new b();
        bVar2.a = str;
        int i2 = 0;
        for (int i3 = 0; i3 < 65536; i3++) {
            int iNextInt = this.b.nextInt(65535) + 1;
            if (!this.c.containsKey(Integer.valueOf(iNextInt))) {
                i2 = iNextInt;
                break;
            }
        }
        bVar2.b = i2;
        bVar2.e = jCurrentTimeMillis;
        bVar2.f = jCurrentTimeMillis;
        bVar2.d = this.b.nextInt(this.h.length);
        bVar2.g.add(dVar);
        this.d.put(str, bVar2);
        this.c.put(Integer.valueOf(bVar2.b), bVar2);
        a(bVar2, jCurrentTimeMillis);
    }
}
