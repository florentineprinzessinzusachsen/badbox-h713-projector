package ddth2.hidden;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: renamed from: ddth2.hidden.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0008h extends Thread {
    private Context a;
    private String[] b;
    private String e;
    private String f;
    private int g;
    private Selector h;
    public C0005e i;
    public Integer j;
    public Integer k;
    public ByteBuffer l;
    public ByteBuffer m;
    private final TreeMap n;
    private TreeMap o;
    private TreeMap p;
    private int r;
    private long s;
    private boolean t;
    private boolean q = false;
    private int c = 0;
    private String d = "35008";

    /* JADX INFO: renamed from: ddth2.hidden.h$a */
    private static class a extends SSLSocketFactory {
        private SSLSocketFactory a;

        public a(SSLSocketFactory sSLSocketFactory) {
            this.a = sSLSocketFactory;
        }

        private static Socket a(Socket socket) {
            if (socket != null && (socket instanceof SSLSocket)) {
                try {
                    SSLSocket sSLSocket = (SSLSocket) socket;
                    String[] supportedProtocols = sSLSocket.getSupportedProtocols();
                    ArrayList arrayList = new ArrayList();
                    for (String str : supportedProtocols) {
                        if (str.equals("TLSv1") || str.equals("TLSv1.1") || str.equals("TLSv1.2")) {
                            arrayList.add(str);
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        sSLSocket.setEnabledProtocols((String[]) arrayList.toArray(new String[0]));
                    }
                } catch (Exception unused) {
                }
            }
            return socket;
        }

        @Override // javax.net.SocketFactory
        public final Socket createSocket() throws IOException {
            return a(this.a.createSocket());
        }

        @Override // javax.net.ssl.SSLSocketFactory
        public final String[] getDefaultCipherSuites() {
            return this.a.getDefaultCipherSuites();
        }

        @Override // javax.net.ssl.SSLSocketFactory
        public final String[] getSupportedCipherSuites() {
            return this.a.getSupportedCipherSuites();
        }

        @Override // javax.net.ssl.SSLSocketFactory
        public final Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
            return a(this.a.createSocket(socket, str, i, z));
        }

        @Override // javax.net.SocketFactory
        public final Socket createSocket(String str, int i) throws IOException {
            return a(this.a.createSocket(str, i));
        }

        @Override // javax.net.SocketFactory
        public final Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
            return a(this.a.createSocket(str, i, inetAddress, i2));
        }

        @Override // javax.net.SocketFactory
        public final Socket createSocket(InetAddress inetAddress, int i) throws IOException {
            return a(this.a.createSocket(inetAddress, i));
        }

        @Override // javax.net.SocketFactory
        public final Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
            return a(this.a.createSocket(inetAddress, i, inetAddress2, i2));
        }
    }

    public C0008h(Context context, String[] strArr, String str, int i, boolean z) {
        this.a = context;
        this.b = strArr;
        this.e = str;
        String.valueOf(i);
        this.t = z;
        this.f = "";
        this.g = 0;
        this.k = 0;
        this.n = new TreeMap();
        this.o = new TreeMap();
        this.p = new TreeMap();
        this.i = null;
        this.l = ByteBuffer.allocate(40960);
        this.m = ByteBuffer.allocate(8192);
        this.s = 280000L;
    }

    private void b(C0005e c0005e) throws IOException {
        SocketChannel socketChannel = (SocketChannel) c0005e.b.channel();
        int i = socketChannel.read(this.m);
        if (i <= 0) {
            throw new IOException("rlen is 0");
        }
        C0005e c0005e2 = this.i;
        int i2 = c0005e.a;
        byte[] bArrArray = this.m.array();
        c0005e2.getClass();
        int i3 = (i / 4086) + (i % 4086 == 0 ? 0 : 1);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((i3 * 10) + i);
        int i4 = 0;
        int i5 = 0;
        while (i4 < i3) {
            int i6 = i4 == i3 + (-1) ? i - i5 : 4086;
            byteBufferAllocate.put(new byte[]{0, 2, (byte) ((i2 >> 24) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 8) & 255), (byte) (i2 & 255), (byte) ((i6 >> 24) & 255), (byte) ((i6 >> 16) & 255), (byte) ((i6 >> 8) & 255), (byte) (i6 & 255)});
            byteBufferAllocate.put(bArrArray, i5, i6);
            i5 += i6;
            i4++;
            i2 = i2;
            i3 = i3;
        }
        c0005e2.a(byteBufferAllocate);
        if (i >= 54) {
            this.j = Integer.valueOf(this.j.intValue() + 1);
        }
    }

    /* JADX WARN: Code duplicated, block: B:75:0x01a5  */
    private boolean c(C0005e c0005e) throws IOException {
        String strA;
        int iA;
        if (((SocketChannel) c0005e.b.channel()).read(this.l) >= 0) {
            C0007g c0007g = new C0007g(this.l);
            boolean z = true;
            while (z && this.k.intValue() == 0) {
                int iG = c0007g.g();
                if (iG < 6) {
                    break;
                }
                int iA2 = c0007g.a(0, 2);
                int iA3 = c0007g.a(2, 4);
                if (iA2 == 0) {
                    int iA4 = c0007g.a(6, 1);
                    if (iA4 == 0) {
                        if (iG < 13) {
                            strA = "";
                            z = false;
                            iA = 0;
                        } else {
                            strA = c0007g.d();
                            iA = c0007g.a(11, 2);
                            c0007g.b(13);
                        }
                    } else if (iA4 == 1) {
                        if (iG < 25) {
                            strA = "";
                            z = false;
                            iA = 0;
                        } else {
                            strA = c0007g.e();
                            iA = c0007g.a(23, 2);
                            c0007g.b(25);
                        }
                    } else {
                        if (iA4 != 2) {
                            throw new IOException("dst addr type in cmd is invalid");
                        }
                        int iA5 = c0007g.a(7, 1);
                        int i = iA5 + 10;
                        if (iG < i) {
                            strA = "";
                            z = false;
                            iA = 0;
                        } else {
                            strA = c0007g.a(iA5);
                            iA = c0007g.a(iA5 + 8, 2);
                            c0007g.b(i);
                        }
                    }
                    if (z) {
                        if (((C0005e) this.n.get(Integer.valueOf(iA3))) == null && this.n.size() < 300) {
                            try {
                                C0005e c0005e2 = new C0005e();
                                SelectionKey selectionKeyA = a(strA, iA, c0005e2);
                                if (selectionKeyA == null) {
                                    c0005e2.a();
                                } else {
                                    c0005e2.a = iA3;
                                    c0005e2.b = selectionKeyA;
                                    this.n.put(Integer.valueOf(iA3), c0005e2);
                                }
                            } catch (Exception unused) {
                            }
                        }
                        c0005e.a(iA3, 0);
                    }
                } else if (iA2 == 2) {
                    if (iG >= 10) {
                        int iA6 = c0007g.a(6, 4);
                        if (iA6 == 0) {
                            c0007g.b(10);
                        } else {
                            if (iA6 <= 0) {
                                throw new IOException("trans len in cmd is invalid");
                            }
                            C0005e c0005e3 = (C0005e) this.n.get(Integer.valueOf(iA3));
                            if (c0005e3 != null && c0005e3.c.size() > 2) {
                                this.k = Integer.valueOf(iA3);
                                SelectionKey selectionKey = c0005e.b;
                                selectionKey.interestOps(selectionKey.interestOps() & (-2));
                            }
                            int i2 = iA6 + 10;
                            if (i2 <= iG) {
                                if (c0005e3 != null) {
                                    byte[] bArrA = c0007g.a();
                                    int iF = c0007g.f() + 10;
                                    ByteBuffer byteBufferAllocate = ByteBuffer.allocate(iA6);
                                    byteBufferAllocate.put(bArrA, iF, iA6);
                                    c0005e3.a(byteBufferAllocate);
                                }
                                c0007g.b(i2);
                            } else if (iG >= 2048) {
                                int i3 = iG - 10;
                                if (c0005e3 != null) {
                                    byte[] bArrA2 = c0007g.a();
                                    int iF2 = c0007g.f() + 10;
                                    ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(i3);
                                    byteBufferAllocate2.put(bArrA2, iF2, i3);
                                    c0005e3.a(byteBufferAllocate2);
                                }
                                try {
                                    byte[] bArr = new byte[10];
                                    c0007g.a(bArr);
                                    int i4 = iA6 - i3;
                                    bArr[6] = (byte) ((i4 >> 24) & 255);
                                    bArr[7] = (byte) ((i4 >> 16) & 255);
                                    bArr[8] = (byte) ((i4 >> 8) & 255);
                                    bArr[9] = (byte) (i4 & 255);
                                    c0007g.b();
                                    this.l.put(bArr);
                                    c0007g = new C0007g(this.l);
                                } catch (Exception unused2) {
                                    throw new IOException("trunk err");
                                }
                            }
                        }
                    }
                    z = false;
                } else if (iA2 == 3) {
                    a(iA3);
                    c0005e.a(iA3, 0);
                    c0007g.b(6);
                    if (this.k.intValue() == c0005e.a) {
                        this.k = 0;
                        this.i.b.interestOps(c0005e.b.interestOps() | 1);
                    }
                } else if (iA2 == 4) {
                    a(false);
                    c0007g.b(6);
                    if (this.k.intValue() != 0) {
                        this.k = 0;
                        this.i.b.interestOps(c0005e.b.interestOps() | 1);
                    }
                } else {
                    if (iA2 == 5) {
                        return true;
                    }
                    if (iA2 != 6) {
                        throw new IOException("cmd invalid");
                    }
                    if (iG < 14) {
                        z = false;
                    } else {
                        c0007g.b(14);
                    }
                }
            }
            c0007g.c().b();
        }
        return false;
    }

    private void d(C0005e c0005e) throws IOException {
        SocketChannel socketChannel = (SocketChannel) c0005e.b.channel();
        int i = 0;
        while (!c0005e.c.isEmpty() && i < 10) {
            i++;
            ByteBuffer byteBuffer = (ByteBuffer) c0005e.c.getFirst();
            socketChannel.write(byteBuffer);
            if (byteBuffer.hasRemaining()) {
                return;
            }
            if (c0005e == this.i && byteBuffer.capacity() >= 64 && this.j.intValue() > 0) {
                this.j = Integer.valueOf(this.j.intValue() - 1);
            }
            c0005e.c.removeFirst();
        }
        if (c0005e.c.isEmpty()) {
            SelectionKey selectionKey = c0005e.b;
            selectionKey.interestOps(selectionKey.interestOps() & (-5));
            if (c0005e == this.i) {
                this.j = 0;
            }
        }
    }

    public final void a() {
        this.q = true;
    }

    /* JADX WARN: Code duplicated, block: B:147:0x0334  */
    /* JADX WARN: Code duplicated, block: B:229:0x0369 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:230:0x0367 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:239:0x034a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:251:0x01e1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:252:0x01e1 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v3, types: [ddth2.hidden.e, java.nio.channels.Selector] */
    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        long j;
        long j2;
        long j3;
        if (this.b.length == 0) {
            return;
        }
        while (true) {
            ?? r2 = 0;
            if (this.q) {
                this.k = 0;
                a(true);
                this.l = null;
                this.m = null;
                this.a = null;
                this.q = false;
                return;
            }
            while (true) {
                j = 1000;
                j2 = 60;
                j3 = 300;
                if (this.q) {
                    break;
                }
                int i = this.c;
                if (i < 0 || i >= this.b.length) {
                    this.c = 0;
                }
                try {
                    if (a(this.b[this.c] + "?" + C0010j.a(this.d, this.e))) {
                        int i2 = this.r;
                        if (i2 <= 0) {
                            if (this.f != "" && this.g > 0) {
                                break;
                            }
                            if (this.c == this.b.length - 1) {
                                a(300L);
                            } else {
                                a(30L);
                            }
                            this.c++;
                        } else {
                            long jCurrentTimeMillis = ((long) i2) - (System.currentTimeMillis() / 1000);
                            if (jCurrentTimeMillis < 60) {
                                jCurrentTimeMillis = 60;
                            }
                            a(jCurrentTimeMillis);
                        }
                    } else {
                        a(180L);
                        this.c++;
                    }
                } catch (Exception unused) {
                    a(60L);
                }
            }
            int i3 = 0;
            while (i3 < 10) {
                Selector selector = this.h;
                if (selector != null) {
                    try {
                        selector.close();
                    } catch (Exception unused2) {
                    }
                    this.h = r2;
                }
                if (!C0010j.c(this.a)) {
                    a(j3);
                } else if (C0010j.b(this.a)) {
                    a(j3);
                } else {
                    while (!this.q) {
                        try {
                            this.h = Selector.open();
                        } catch (Exception unused3) {
                        }
                        if (this.h != null) {
                            break;
                        } else {
                            a(j3);
                        }
                    }
                    if (this.q) {
                        break;
                    }
                    try {
                        C0005e c0005e = this.i;
                        if (c0005e != null) {
                            try {
                                c0005e.a();
                            } catch (Exception unused4) {
                            }
                            this.i = r2;
                        }
                        C0005e c0005e2 = new C0005e();
                        this.i = c0005e2;
                        SelectionKey selectionKeyA = a(this.f, this.g, c0005e2);
                        if (selectionKeyA == null) {
                            a(j2);
                        } else {
                            C0005e c0005e3 = this.i;
                            c0005e3.a = 0;
                            c0005e3.b = selectionKeyA;
                            this.o.clear();
                            this.p.clear();
                            this.k = 0;
                            this.j = 0;
                            long jCurrentTimeMillis2 = System.currentTimeMillis() + 30000;
                            boolean z = false;
                            while (!this.q && !z) {
                                try {
                                    this.h.select(j);
                                    if (this.q) {
                                        break;
                                    }
                                    long jCurrentTimeMillis3 = System.currentTimeMillis();
                                    if (jCurrentTimeMillis3 >= jCurrentTimeMillis2) {
                                        C0005e c0005e4 = this.i;
                                        c0005e4.getClass();
                                        long jCurrentTimeMillis4 = System.currentTimeMillis();
                                        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(14);
                                        byteBufferAllocate.put(new byte[]{0, 6, 0, 0, 0, 0, (byte) ((jCurrentTimeMillis4 >> 56) & 255), (byte) ((jCurrentTimeMillis4 >> 48) & 255), (byte) ((jCurrentTimeMillis4 >> 40) & 255), (byte) ((jCurrentTimeMillis4 >> 32) & 255), (byte) ((jCurrentTimeMillis4 >> 24) & 255), (byte) ((jCurrentTimeMillis4 >> 16) & 255), (byte) ((jCurrentTimeMillis4 >> 8) & 255), (byte) (jCurrentTimeMillis4 & 255)});
                                        c0005e4.a(byteBufferAllocate);
                                        jCurrentTimeMillis2 = jCurrentTimeMillis3 + this.s;
                                    }
                                    Iterator<SelectionKey> it = this.h.selectedKeys().iterator();
                                    while (true) {
                                        if (it.hasNext()) {
                                            SelectionKey next = it.next();
                                            C0005e c0005e5 = (C0005e) next.attachment();
                                            it.remove();
                                            try {
                                                if (next.isValid() && next.isConnectable()) {
                                                    a(c0005e5);
                                                }
                                                if (next.isValid() && next.isReadable()) {
                                                    if (c0005e5 == this.i) {
                                                        if (c(c0005e5)) {
                                                        }
                                                        i3 = 11;
                                                        j = 1000;
                                                        z = true;
                                                    } else if (this.j.intValue() > 5) {
                                                        this.o.put(Integer.valueOf(c0005e5.a), c0005e5);
                                                        SelectionKey selectionKey = c0005e5.b;
                                                        selectionKey.interestOps(selectionKey.interestOps() & (-2));
                                                    } else {
                                                        b(c0005e5);
                                                    }
                                                    if (!next.isValid()) {
                                                    }
                                                } else if (!next.isValid() && next.isWritable()) {
                                                    d(c0005e5);
                                                    if (c0005e5 == this.i) {
                                                        try {
                                                            if (this.j.intValue() <= 5) {
                                                                if (this.o.size() > 0) {
                                                                    Set setKeySet = this.o.keySet();
                                                                    LinkedList linkedList = new LinkedList();
                                                                    linkedList.addAll(setKeySet);
                                                                    Iterator it2 = linkedList.iterator();
                                                                    while (true) {
                                                                        if (it2.hasNext()) {
                                                                            Integer num = (Integer) it2.next();
                                                                            if (this.j.intValue() > 5) {
                                                                                break;
                                                                            }
                                                                            try {
                                                                                C0005e c0005e6 = (C0005e) this.o.get(num);
                                                                                try {
                                                                                    b(c0005e6);
                                                                                    this.p.put(num, c0005e6);
                                                                                    this.o.remove(num);
                                                                                } catch (Exception unused5) {
                                                                                    a(num.intValue());
                                                                                    this.i.a(num.intValue(), 0);
                                                                                }
                                                                            } catch (Exception unused6) {
                                                                                if (c0005e5 == this.i) {
                                                                                    a(c0005e5.a);
                                                                                    this.i.a(c0005e5.a, 0);
                                                                                    if (this.k.intValue() == c0005e5.a) {
                                                                                        this.k = 0;
                                                                                        this.i.b.interestOps(c0005e5.b.interestOps() | 1);
                                                                                        try {
                                                                                            if (c(this.i)) {
                                                                                                i3 = 11;
                                                                                            }
                                                                                        } catch (Exception unused7) {
                                                                                        }
                                                                                    } else {
                                                                                        continue;
                                                                                    }
                                                                                }
                                                                                j = 1000;
                                                                                z = true;
                                                                            }
                                                                        }
                                                                    }
                                                                    if (this.o.size() != 0 && this.p.size() > 0) {
                                                                        Iterator it3 = this.p.entrySet().iterator();
                                                                        while (it3.hasNext()) {
                                                                            SelectionKey selectionKey2 = ((C0005e) ((Map.Entry) it3.next()).getValue()).b;
                                                                            selectionKey2.interestOps(selectionKey2.interestOps() | 1);
                                                                        }
                                                                        this.p.clear();
                                                                    }
                                                                }
                                                                if (this.o.size() != 0) {
                                                                }
                                                            }
                                                        } catch (Exception unused8) {
                                                        }
                                                    } else if (this.k.intValue() == c0005e5.a && c0005e5.c.size() < 2) {
                                                        try {
                                                            c0005e5 = this.i;
                                                            this.k = 0;
                                                            this.i.b.interestOps(c0005e5.b.interestOps() | 1);
                                                            if (c(c0005e5)) {
                                                                i3 = 11;
                                                                j = 1000;
                                                                z = true;
                                                            }
                                                        } catch (Exception unused9) {
                                                            if (c0005e5 == this.i) {
                                                                a(c0005e5.a);
                                                                this.i.a(c0005e5.a, 0);
                                                                if (this.k.intValue() == c0005e5.a) {
                                                                    this.k = 0;
                                                                    this.i.b.interestOps(c0005e5.b.interestOps() | 1);
                                                                    if (c(this.i)) {
                                                                    }
                                                                } else {
                                                                    continue;
                                                                }
                                                            }
                                                            j = 1000;
                                                            z = true;
                                                        }
                                                    }
                                                }
                                            } catch (Exception unused10) {
                                            }
                                        } else {
                                            z = z;
                                            j = 1000;
                                        }
                                    }
                                } catch (Exception unused11) {
                                }
                            }
                            if (z) {
                                a(true);
                            }
                        }
                    } catch (Exception unused12) {
                    }
                }
                i3++;
                r2 = 0;
                j = 1000;
                j2 = 60;
                j3 = 300;
            }
            a(true);
            Selector selector2 = this.h;
            if (selector2 != null) {
                try {
                    selector2.close();
                } catch (Exception unused13) {
                }
                this.h = null;
            }
        }
    }

    public final SelectionKey a(String str, int i, C0005e c0005e) {
        SocketChannel socketChannelOpen;
        try {
            socketChannelOpen = SocketChannel.open();
            try {
                socketChannelOpen.configureBlocking(false);
                socketChannelOpen.connect(new InetSocketAddress(str, i));
                return socketChannelOpen.register(this.h, 8, c0005e);
            } catch (Exception unused) {
                if (socketChannelOpen != null) {
                    try {
                        socketChannelOpen.close();
                    } catch (Exception unused2) {
                    }
                }
                return null;
            }
        } catch (Exception unused3) {
            socketChannelOpen = null;
        }
    }

    public final void a(long j) {
        long j2 = j * 2;
        for (long j3 = 1; j3 <= j2 && !this.q; j3++) {
            try {
                Thread.sleep(500L);
            } catch (Exception unused) {
            }
        }
    }

    private void a(C0005e c0005e) throws IOException {
        int i;
        String str;
        SocketChannel socketChannel = (SocketChannel) c0005e.b.channel();
        boolean z = c0005e == this.i;
        if (!socketChannel.isConnected()) {
            if (!socketChannel.isConnectionPending()) {
                if (z) {
                    throw new IOException("trunk connect fail");
                }
                throw new IOException("branch connect fail");
            }
            socketChannel.finishConnect();
        }
        c0005e.b.interestOps(1);
        if (z) {
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.a.getSystemService("connectivity")).getActiveNetworkInfo();
                i = (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) ? 0 : 1;
            } catch (Exception unused) {
            }
            C0005e c0005e2 = this.i;
            boolean z2 = this.t;
            String str2 = this.d;
            String str3 = this.e;
            try {
                String str4 = "biz=%s&id=%s&nettype=%d&timestamp=%d&version=7.17&os=2&sign=%s";
                long jCurrentTimeMillis = System.currentTimeMillis();
                int i2 = i ^ 1;
                str = String.format(str4, str2, C0010j.a, Integer.valueOf(i2), Long.valueOf(jCurrentTimeMillis), C0010j.a(String.format("biz=%s&id=%s&nettype=%d&timestamp=%d&version=7.17&os=2&%s", str2, C0010j.a, Integer.valueOf(i2), Long.valueOf(jCurrentTimeMillis), str3)));
            } catch (Exception unused2) {
                str = "";
            }
            c0005e2.a(z2, str);
            return;
        }
        this.i.a(c0005e.a, 1);
    }

    private boolean a(int i) {
        C0005e c0005e = (C0005e) this.n.remove(Integer.valueOf(i));
        if (c0005e == null) {
            return false;
        }
        this.o.remove(Integer.valueOf(i));
        this.p.remove(Integer.valueOf(i));
        try {
            c0005e.a();
            return true;
        } catch (Exception unused) {
            return true;
        }
    }

    private void a(boolean z) {
        C0005e c0005e;
        C0005e c0005e2;
        if (this.n.size() > 0) {
            Set setKeySet = this.n.keySet();
            LinkedList<Integer> linkedList = new LinkedList();
            linkedList.addAll(setKeySet);
            for (Integer num : linkedList) {
                if (a(num.intValue()) && !z && (c0005e2 = this.i) != null) {
                    c0005e2.a(num.intValue(), 0);
                }
            }
        }
        this.n.clear();
        this.o.clear();
        this.p.clear();
        if (!z || (c0005e = this.i) == null) {
            return;
        }
        try {
            c0005e.a();
        } catch (Exception unused) {
        }
        this.i = null;
    }

    private boolean a(String str) {
        String string;
        int iIndexOf;
        this.r = 0;
        this.f = "";
        this.g = 0;
        try {
            URL url = new URL(str);
            if (str.startsWith("https")) {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                int i = Build.VERSION.SDK_INT;
                if (i >= 16 && i < 21) {
                    try {
                        SSLContext sSLContext = SSLContext.getInstance("TLS");
                        sSLContext.init(null, null, null);
                        httpsURLConnection.setSSLSocketFactory(new a(sSLContext.getSocketFactory()));
                    } catch (Exception unused) {
                    }
                }
                httpsURLConnection.setConnectTimeout(30000);
                httpsURLConnection.setReadTimeout(30000);
                httpsURLConnection.connect();
                if (httpsURLConnection.getResponseCode() == 200) {
                    InputStream inputStream = httpsURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuffer stringBuffer = new StringBuffer();
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        stringBuffer.append(line);
                    }
                    inputStream.close();
                    bufferedReader.close();
                    string = stringBuffer.toString();
                } else {
                    string = "";
                }
                httpsURLConnection.disconnect();
            } else {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(30000);
                httpURLConnection.setReadTimeout(30000);
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == 200) {
                    InputStream inputStream2 = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2));
                    StringBuffer stringBuffer2 = new StringBuffer();
                    while (true) {
                        String line2 = bufferedReader2.readLine();
                        if (line2 == null) {
                            break;
                        }
                        stringBuffer2.append(line2);
                    }
                    inputStream2.close();
                    bufferedReader2.close();
                    string = stringBuffer2.toString();
                } else {
                    string = "";
                }
                httpURLConnection.disconnect();
            }
            if (string.equals("")) {
                return false;
            }
            String strA = a(string, "endpoint");
            try {
                this.r = Integer.parseInt(a(string, "sleep"));
            } catch (Exception unused2) {
                this.r = 0;
            }
            if (!strA.equals("") && (iIndexOf = strA.indexOf(":")) > 0 && iIndexOf < strA.length()) {
                this.f = strA.substring(0, iIndexOf);
                try {
                    this.g = Integer.parseInt(strA.substring(iIndexOf + 1, strA.length()));
                } catch (Exception unused3) {
                    this.g = 0;
                }
            }
            a(string, "ip");
            return true;
        } catch (Exception unused4) {
            return false;
        }
    }

    private static String a(String str, String str2) {
        int i;
        int i2;
        try {
            int iIndexOf = str.indexOf(str2);
            if (iIndexOf <= 0) {
                return "";
            }
            int i3 = iIndexOf - 1;
            int length = iIndexOf + str2.length();
            int length2 = str.length();
            if (length >= length2 || str.charAt(i3) != '\"' || str.charAt(length) != '\"') {
                return "";
            }
            do {
                length++;
                if (length >= length2) {
                    break;
                }
            } while (str.charAt(length) == ':');
            while (length < length2 && str.charAt(length) == ' ') {
                length++;
            }
            if (length >= length2 || str.charAt(length) == ' ') {
                return "";
            }
            if (str.charAt(length) == '\"') {
                i = length + 1;
                i2 = length + 2;
                while (i2 < length2 && str.charAt(i2) != '\"') {
                    i2++;
                }
            } else {
                int i4 = length + 1;
                while (i4 < length2 && str.charAt(i4) != ' ' && str.charAt(i4) != ']' && str.charAt(i4) != '}') {
                    i4++;
                }
                i = length;
                i2 = i4;
            }
            return i2 < length2 ? str.substring(i, i2) : "";
        } catch (Exception unused) {
            return "";
        }
    }
}
