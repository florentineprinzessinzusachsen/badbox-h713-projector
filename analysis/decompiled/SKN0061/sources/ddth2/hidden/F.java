package ddth2.hidden;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public final class F {
    public final C0018s a;
    public final ScheduledExecutorService b;
    public final I c;
    public final C0011k d;
    public final ThreadPoolExecutor g;
    public volatile Socket h;
    public volatile long i;
    public Thread j;
    public final AtomicBoolean e = new AtomicBoolean(false);
    public final AtomicLong f = new AtomicLong(0);
    public int k = 5000;

    public F(C0018s c0018s, ScheduledExecutorService scheduledExecutorService, I i, C0011k c0011k) {
        this.a = c0018s;
        this.b = scheduledExecutorService;
        this.c = i;
        this.d = c0011k;
        SynchronousQueue synchronousQueue = new SynchronousQueue();
        this.g = new ThreadPoolExecutor(0, 1000, 10L, TimeUnit.SECONDS, synchronousQueue, new N("gateway-worker-" + i.b), new ThreadPoolExecutor.AbortPolicy());
    }

    public final boolean a(Runnable runnable) {
        try {
            this.g.execute(runnable);
            return true;
        } catch (RejectedExecutionException unused) {
            return false;
        }
    }

    public final void a() throws Throwable {
        long jIncrementAndGet;
        J jA;
        Socket socket = new Socket();
        try {
            this.h = socket;
            socket.setTcpNoDelay(true);
            socket.setKeepAlive(true);
            socket.setSoTimeout(5000);
            I i = this.c;
            socket.connect(new InetSocketAddress(i.a, i.b), 5000);
            r.a(this.b, socket, K.a(this.a, this.g.getActiveCount(), this.g.getQueue().size()), 5000);
            a(socket);
            this.k = 5000;
            jIncrementAndGet = this.f.incrementAndGet();
            try {
                this.i = jIncrementAndGet;
                socket.setSoTimeout(30000);
                while (this.e.get()) {
                    InputStream inputStream = socket.getInputStream();
                    byte[] bArr = new byte[8];
                    try {
                        int i2 = inputStream.read();
                        if (i2 < 0) {
                            throw new EOFException();
                        }
                        bArr[0] = (byte) i2;
                        try {
                            r.a(inputStream, bArr, 1, 7);
                            jA = K.a(inputStream, bArr);
                            if (jA == null) {
                                int activeCount = this.g.getActiveCount();
                                int size = this.g.getQueue().size();
                                int iMin = Math.min(65535, activeCount);
                                int iMin2 = Math.min(65535, size);
                                a(jIncrementAndGet, socket, K.a(3, new byte[]{(byte) ((iMin >>> 8) & 255), (byte) (iMin & 255), (byte) ((iMin2 >>> 8) & 255), (byte) (iMin2 & 255)}));
                            } else {
                                int i3 = jA.a;
                                if (i3 == 16) {
                                    a(jA.b);
                                } else if (i3 == 127) {
                                    throw new IOException("gateway ERROR " + this.c);
                                }
                            }
                        } catch (SocketTimeoutException e) {
                            throw new IOException("GW frame timed out", e);
                        }
                    } catch (SocketTimeoutException unused) {
                        jA = null;
                    }
                }
                if (jIncrementAndGet != 0 && this.i == jIncrementAndGet) {
                    this.i = 0L;
                }
                if (this.h == socket) {
                    this.h = null;
                }
                z.a(socket);
            } catch (Throwable th) {
                th = th;
                if (jIncrementAndGet != 0 && this.i == jIncrementAndGet) {
                    this.i = 0L;
                }
                if (this.h == socket) {
                    this.h = null;
                }
                z.a(socket);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            jIncrementAndGet = 0;
        }
    }

    public final void a(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        byte[] bArr = new byte[8];
        r.a(inputStream, bArr, 0, 8);
        J jA = K.a(inputStream, bArr);
        if (jA.a == 2) {
            byte[] bArr2 = jA.b;
            int i = 0;
            while (true) {
                int i2 = i + 3;
                if (i2 > bArr2.length) {
                    break;
                }
                int i3 = i + 1;
                int i4 = bArr2[i] & 255;
                int iA = r.a(i3, bArr2);
                int i5 = i2 + iA;
                if (i5 > bArr2.length) {
                    break;
                }
                if (i4 == 16) {
                    if (iA != 8) {
                        break;
                    }
                    int iMin = Math.min(iA, 8);
                    long j = 0;
                    for (int i6 = 0; i6 < iMin; i6++) {
                        j = (j << 8) | (((long) bArr2[i2 + i6]) & 255);
                    }
                    if (j == 0) {
                        break;
                    } else {
                        return;
                    }
                }
                i = i5;
            }
            throw new IOException("REGISTER rejected by " + this.c);
        }
        throw new IOException("expected REGISTER_ACK from " + this.c + ", got " + jA.a);
    }

    public final void a(byte[] bArr) {
        InetAddress inetAddressA;
        try {
            L lA = L.a(bArr);
            M.a(lA.a);
            long j = this.i;
            if (j == 0) {
                return;
            }
            C0011k c0011k = this.d;
            c0011k.getClass();
            M.a(lA.a);
            C0022w c0022w = c0011k.d;
            byte[] bArr2 = lA.a;
            c0022w.getClass();
            if (c0022w.c.putIfAbsent(M.a(bArr2), Long.valueOf(System.nanoTime() + c0022w.b)) == null) {
                if (lA.h > 0 && System.currentTimeMillis() >= lA.h) {
                    c0011k.a(this, j, lA, 3, true);
                    return;
                }
                String str = lA.b;
                int i = lA.c;
                if (str.length() != 0 && i > 0 && i <= 65535 && ((inetAddressA = P.a(str)) == null || (!inetAddressA.isAnyLocalAddress() && !inetAddressA.isLoopbackAddress() && !inetAddressA.isLinkLocalAddress() && !inetAddressA.isSiteLocalAddress() && !inetAddressA.isMulticastAddress()))) {
                    String str2 = lA.d;
                    int i2 = lA.e;
                    if (i2 > 0 && i2 <= 65535 && P.a(str2) != null) {
                        String str3 = lA.f;
                        int i3 = lA.g;
                        if (str3.length() != 0 ? !(i3 <= 0 || i3 > 65535 || P.a(str3) == null) : i3 == 0) {
                            c0011k.e.size();
                            C0011k c0011k2 = this.d;
                            V v = new V(this, lA, j, c0011k2.c, c0011k2.a, c0011k2.b, c0011k2);
                            c0011k2.e.put(M.a(v.b.a), v);
                            v.r = System.nanoTime();
                            v.b();
                            F f = v.a;
                            int activeCount = f.g.getActiveCount();
                            int maximumPoolSize = f.g.getMaximumPoolSize();
                            int iRemainingCapacity = f.g.getQueue().remainingCapacity();
                            if (activeCount + 2 > maximumPoolSize && iRemainingCapacity < 2) {
                                v.b();
                                v.a(4, true);
                            } else {
                                v.p = v.e.schedule(new Q(v), 10000, TimeUnit.MILLISECONDS);
                                boolean zA = v.a.a(new S(v));
                                boolean zA2 = v.a.a(new T(v));
                                if (zA && zA2) {
                                    v.b();
                                    M.a(v.b.a);
                                    c0011k2.e.size();
                                    return;
                                }
                                v.b();
                                v.a(4, true);
                            }
                            c0011k2.e.remove(M.a(v.b.a));
                            M.a(v.b.a);
                            c0011k2.e.size();
                            return;
                        }
                        c0011k.a(this, j, lA, 5, false);
                        return;
                    }
                    c0011k.a(this, j, lA, 2, true);
                    return;
                }
                c0011k.a(this, j, lA, 5, true);
            }
        } catch (RuntimeException unused) {
        }
    }

    public final void a(long j, Socket socket, byte[] bArr) {
        if (j == 0 || this.i != j) {
            return;
        }
        Socket socket2 = this.h;
        if (!this.e.get() || socket2 == null || socket2.isClosed()) {
            return;
        }
        if (socket == null || socket2 == socket) {
            synchronized (this) {
                if (this.i != j) {
                    return;
                }
                if (socket == null || this.h == socket) {
                    try {
                        r.a(this.b, socket2, bArr, 5000);
                    } catch (IOException e) {
                        z.a(socket2);
                        throw e;
                    }
                }
            }
        }
    }
}
