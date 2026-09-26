package com.link.core.a;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class s {
    public static String a;
    public static AtomicBoolean b = new AtomicBoolean(false);
    public static a c = new a();
    public static final AtomicReference<Thread> d = new AtomicReference<>();

    public class a implements Runnable {
        public final byte[] a = new byte[4096];
        public InetSocketAddress b = null;
        public DatagramSocket c = null;
        public InetAddress d = null;
        public long e = 3000;
        public InetAddress f = null;
        public v g = null;
        public i h = null;

        /* JADX INFO: renamed from: com.link.core.a.s$a$a, reason: collision with other inner class name */
        public class RunnableC0000a implements Runnable {
            public RunnableC0000a() {
            }

            /* JADX WARN: Code restructure failed: missing block: B:20:0x0048, code lost:
            
                r0 = move-exception;
             */
            /* JADX WARN: Code restructure failed: missing block: B:21:0x0049, code lost:
            
                com.link.core.a.w.a();
                com.link.core.a.b.a("RuntimeException: " + r0);
                r0.printStackTrace();
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void run() {
                /*
                    r7 = this;
                    com.link.core.a.s$a r0 = com.link.core.a.s.a.this
                    com.link.core.a.v r1 = r0.g
                    com.link.core.a.i r0 = r0.h
                    java.util.Map<java.lang.Long, com.link.core.a.e> r2 = com.link.core.a.w.a
                    java.lang.Class<com.link.core.a.w> r2 = com.link.core.a.w.class
                    monitor-enter(r2)
                Lb:
                    java.util.concurrent.atomic.AtomicBoolean r3 = com.link.core.a.w.b     // Catch: java.lang.Throwable -> L65
                    r4 = 1
                    r5 = 0
                    boolean r3 = r3.compareAndSet(r5, r4)     // Catch: java.lang.Throwable -> L65
                    if (r3 != 0) goto L16
                    goto Lb
                L16:
                    java.net.InetSocketAddress r3 = new java.net.InetSocketAddress     // Catch: java.lang.Exception -> L48 java.lang.Throwable -> L65
                    java.net.InetAddress r4 = r1.a     // Catch: java.lang.Exception -> L48 java.lang.Throwable -> L65
                    int r6 = r1.b     // Catch: java.lang.Exception -> L48 java.lang.Throwable -> L65
                    r3.<init>(r4, r6)     // Catch: java.lang.Exception -> L48 java.lang.Throwable -> L65
                    boolean r3 = com.link.core.a.w.a(r3)     // Catch: java.lang.Exception -> L48 java.lang.Throwable -> L65
                    if (r3 != 0) goto L2c
                    java.lang.String r0 = "Failed to init connection to prxserver"
                    com.link.core.a.b.a(r0)     // Catch: java.lang.Exception -> L48 java.lang.Throwable -> L65
                L2a:
                    monitor-exit(r2)     // Catch: java.lang.Throwable -> L65
                    goto L64
                L2c:
                    com.link.core.a.w.e = r0     // Catch: java.lang.Exception -> L48 java.lang.Throwable -> L65
                    byte[] r0 = r1.c     // Catch: java.lang.Exception -> L48 java.lang.Throwable -> L65
                    com.link.core.a.w.f = r0     // Catch: java.lang.Exception -> L48 java.lang.Throwable -> L65
                L32:
                    java.util.concurrent.atomic.AtomicBoolean r0 = com.link.core.a.w.b     // Catch: java.lang.Exception -> L48 java.lang.Throwable -> L65
                    boolean r1 = r0.get()     // Catch: java.lang.Exception -> L48 java.lang.Throwable -> L65
                    if (r1 == 0) goto L41
                    boolean r1 = com.link.core.a.w.b()     // Catch: java.lang.Exception -> L48 java.lang.Throwable -> L65
                    if (r1 == 0) goto L41
                    goto L32
                L41:
                    com.link.core.a.w.a()     // Catch: java.lang.Exception -> L48 java.lang.Throwable -> L65
                    r0.set(r5)     // Catch: java.lang.Exception -> L48 java.lang.Throwable -> L65
                    goto L2a
                L48:
                    r0 = move-exception
                    com.link.core.a.w.a()     // Catch: java.lang.Throwable -> L65
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L65
                    r1.<init>()     // Catch: java.lang.Throwable -> L65
                    java.lang.String r3 = "RuntimeException: "
                    r1.append(r3)     // Catch: java.lang.Throwable -> L65
                    r1.append(r0)     // Catch: java.lang.Throwable -> L65
                    java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L65
                    com.link.core.a.b.a(r1)     // Catch: java.lang.Throwable -> L65
                    r0.printStackTrace()     // Catch: java.lang.Throwable -> L65
                    goto L2a
                L64:
                    return
                L65:
                    r0 = move-exception
                    monitor-exit(r2)     // Catch: java.lang.Throwable -> L65
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.link.core.a.s.a.RunnableC0000a.run():void");
            }
        }

        public final void a() {
            DatagramSocket datagramSocket = this.c;
            if (datagramSocket != null) {
                datagramSocket.close();
                this.c = null;
            }
            this.b = null;
            this.d = null;
            u.a = 1;
            long j = this.e;
            long j2 = 3000;
            if (j >= 3000) {
                long jRandom = j + ((long) (j * 0.1d)) + ((long) (Math.random() * 5.0d));
                this.e = jRandom;
                j2 = 3600000;
                if (jRandom <= 3600000) {
                    return;
                }
            }
            this.e = j2;
        }

        /* JADX WARN: Code duplicated, block: B:101:0x01d4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:103:0x01e3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:111:0x01fc A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:112:0x01b6 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:118:0x000b A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:51:0x013f  */
        /* JADX WARN: Code duplicated, block: B:53:0x0144  */
        /* JADX WARN: Code duplicated, block: B:73:0x01ae  */
        /* JADX WARN: Code duplicated, block: B:76:0x01b2  */
        /* JADX WARN: Code duplicated, block: B:92:0x01fa  */
        @Override // java.lang.Runnable
        public final void run() {
            Thread thread;
            long time;
            InetAddress inetAddress;
            boolean z;
            DatagramPacket datagramPacket;
            InetAddress inetAddress2;
            boolean z2;
            DatagramPacket datagramPacket2;
            boolean z3;
            while (!s.b.compareAndSet(false, true)) {
            }
            while (s.b.get()) {
                if (u.a == 1) {
                    try {
                        Thread.sleep(this.e);
                        String[] strArrSplit = g.a[(int) (Math.random() * ((double) 1))].split(":");
                        String str = strArrSplit[0];
                        int i = Integer.parseInt(strArrSplit[1]);
                        InetAddress[] allByName = InetAddress.getAllByName(str);
                        if (allByName.length == 0) {
                            z3 = false;
                        } else {
                            for (InetAddress inetAddress3 : allByName) {
                                inetAddress3.getHostAddress();
                            }
                            this.b = new InetSocketAddress(allByName[(int) (Math.random() * ((double) allByName.length))], i);
                            u.a = 2;
                            z3 = true;
                        }
                    } catch (Exception e) {
                        b.a("Init Phase Error: " + e.getMessage());
                    }
                    if (!z3) {
                    }
                }
                if (u.a == 2) {
                    if (this.c == null) {
                        try {
                            DatagramSocket datagramSocket = new DatagramSocket();
                            this.c = datagramSocket;
                            datagramSocket.setSoTimeout(5000);
                        } catch (SocketException e2) {
                            b.a(e2.getMessage());
                        }
                    }
                    try {
                        byte[] bArr = new byte[32];
                        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
                        ByteOrder byteOrder = k.a;
                        byteBufferWrap.order(byteOrder);
                        if (byteBufferWrap.order() == byteOrder && byteBufferWrap.remaining() >= 32) {
                            byteBufferWrap.putInt(-855637984);
                            byte b = (byte) 0;
                            byteBufferWrap.put(b);
                            byteBufferWrap.put(b);
                            byteBufferWrap.putShort((short) 1);
                            byteBufferWrap.putLong(0L);
                            byteBufferWrap.putLong(0L);
                            byteBufferWrap.putLong(0L);
                        }
                        this.c.send(new DatagramPacket(bArr, 32, this.b));
                        do {
                            byte[] bArr2 = this.a;
                            datagramPacket2 = new DatagramPacket(bArr2, bArr2.length);
                            this.c.receive(datagramPacket2);
                        } while (!datagramPacket2.getAddress().equals(this.b.getAddress()));
                        x xVarA = l.a(datagramPacket2);
                        this.d = xVarA.c;
                        if (xVarA.a != 0 && xVarA.b != 0) {
                            i iVar = new i();
                            this.h = iVar;
                            iVar.a.a = "" + xVarA.b;
                            this.f = this.d;
                        }
                        u.a = 3;
                        z2 = true;
                    } catch (IOException e3) {
                        b.a(e3.getMessage());
                        z2 = false;
                    }
                    if (z2) {
                        if (u.a != 3) {
                            inetAddress = this.f;
                            if (inetAddress == null) {
                                z = false;
                            } else {
                                z = false;
                            }
                            if (!z) {
                                if (u.a == 4) {
                                    thread = new Thread(new RunnableC0000a());
                                    thread.start();
                                    time = new Date().getTime();
                                    while (s.b.get()) {
                                        thread.join();
                                        break;
                                    }
                                    w.e();
                                    while (thread.isAlive()) {
                                        thread.join();
                                    }
                                    if (new Date().getTime() - time >= 300000) {
                                        this.e = 0L;
                                    }
                                }
                            }
                        } else if (u.a == 4) {
                            thread = new Thread(new RunnableC0000a());
                            thread.start();
                            time = new Date().getTime();
                            while (s.b.get()) {
                                thread.join();
                                break;
                            }
                            w.e();
                            while (thread.isAlive()) {
                                thread.join();
                            }
                            if (new Date().getTime() - time >= 300000) {
                                this.e = 0L;
                            }
                        }
                    }
                    a();
                } else if (u.a != 3) {
                    inetAddress = this.f;
                    if (inetAddress == null && inetAddress.equals(this.d) && this.h != null) {
                        Objects.toString(this.d);
                        try {
                            InetAddress inetAddress4 = this.d;
                            this.h.a.getClass();
                            this.c.send(new DatagramPacket(m.a(inetAddress4), 132, this.b));
                            do {
                                byte[] bArr3 = this.a;
                                datagramPacket = new DatagramPacket(bArr3, bArr3.length);
                                this.c.receive(datagramPacket);
                            } while (!datagramPacket.getAddress().equals(this.b.getAddress()));
                            v vVarA = m.a(datagramPacket);
                            this.g = vVarA;
                            if (vVarA == null || (inetAddress2 = vVarA.a) == null) {
                                z = false;
                            } else {
                                Objects.toString(inetAddress2);
                                u.a = 4;
                                z = true;
                            }
                        } catch (IOException e4) {
                            b.a(e4.getMessage());
                        }
                    } else {
                        z = false;
                    }
                    if (!z) {
                        if (u.a == 4) {
                            thread = new Thread(new RunnableC0000a());
                            thread.start();
                            time = new Date().getTime();
                            while (s.b.get()) {
                                thread.join();
                                break;
                            }
                            w.e();
                            while (thread.isAlive()) {
                                thread.join();
                            }
                            if (new Date().getTime() - time >= 300000) {
                                this.e = 0L;
                            }
                        }
                    }
                    a();
                } else if (u.a == 4) {
                    thread = new Thread(new RunnableC0000a());
                    thread.start();
                    time = new Date().getTime();
                    while (s.b.get()) {
                        try {
                            thread.join();
                            break;
                        } catch (InterruptedException unused) {
                        }
                    }
                    w.e();
                    while (thread.isAlive()) {
                        try {
                            thread.join();
                        } catch (InterruptedException unused2) {
                        }
                    }
                    if (new Date().getTime() - time >= 300000) {
                        this.e = 0L;
                    }
                    a();
                }
            }
        }
    }
}
