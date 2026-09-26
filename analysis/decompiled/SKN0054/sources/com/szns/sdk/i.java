package com.szns.sdk;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class i extends q {
    private final Object c;
    private Socket d;
    private ScheduledFuture e;
    private ScheduledFuture f;
    private j g;
    private int h;
    private long i;

    public i(r rVar) {
        super(rVar);
        this.c = new Object();
        this.g = j.IDLE;
        this.i = 0L;
        this.h = rVar.a;
    }

    private static InetSocketAddress a(String str, String str2, int i) {
        InetAddress[] allByName;
        try {
            allByName = InetAddress.getAllByName(str2);
        } catch (UnknownHostException unused) {
            allByName = null;
        }
        if (allByName == null) {
            return new InetSocketAddress(str2, i);
        }
        for (InetAddress inetAddress : allByName) {
            if (str.equals("ipv4") && (inetAddress instanceof Inet4Address)) {
                return new InetSocketAddress(inetAddress, i);
            }
            if (str.equals("ipv6") && (inetAddress instanceof Inet6Address)) {
                return new InetSocketAddress(inetAddress, i);
            }
        }
        return new InetSocketAddress(str2, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void d(final long j) {
        final Socket socket;
        Exception e;
        try {
            synchronized (this.c) {
                if (this.i == j && this.g == j.CONNECTING) {
                    socket = new Socket();
                    try {
                        socket.connect(new InetSocketAddress(this.a.c, this.a.e), 5000);
                        socket.setKeepAlive(true);
                        synchronized (this.c) {
                            if (this.i == j && this.g == j.CONNECTING) {
                                this.d = socket;
                                this.g = j.CONNECTED;
                                d();
                                if (this.a.g.booleanValue()) {
                                    ScheduledFuture scheduledFutureA = n.a().a(new Runnable() { // from class: com.szns.sdk.i$$ExternalSyntheticLambda1
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            this.f$0.d(socket, j);
                                        }
                                    }, 0L, this.a.h.longValue(), TimeUnit.SECONDS);
                                    this.e = scheduledFutureA;
                                    if (scheduledFutureA == null) {
                                        a(j, m.WriteError);
                                        return;
                                    }
                                }
                                if (n.a().a(new Runnable() { // from class: com.szns.sdk.i$$ExternalSyntheticLambda2
                                    @Override // java.lang.Runnable
                                    public final void run() throws Throwable {
                                        this.f$0.c(socket, j);
                                    }
                                })) {
                                    this.b.a(m.ConnectSuccess);
                                    return;
                                } else {
                                    a(j, m.ReadError);
                                    return;
                                }
                            }
                            a(socket);
                        }
                    } catch (Exception e2) {
                        e = e2;
                        a(socket);
                        m mVar = m.ConnectError;
                        e.getMessage();
                        a(j, mVar);
                    }
                }
            }
        } catch (Exception e3) {
            socket = null;
            e = e3;
        }
    }

    private void a(final long j, long j2) {
        e();
        this.f = n.a().a(new Runnable() { // from class: com.szns.sdk.i$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.b(j);
            }
        }, Math.max(j2, 0L), TimeUnit.SECONDS);
    }

    private void a(long j, m mVar) {
        synchronized (this.c) {
            if (this.i == j && this.g != j.STOPPING && this.g != j.CLOSED && this.g != j.RECONNECT_WAIT) {
                Socket socket = this.d;
                this.d = null;
                d();
                int i = this.h;
                boolean z = true;
                if (i > 0) {
                    this.h = i - 1;
                    this.g = j.RECONNECT_WAIT;
                    a(j, this.a.b.longValue());
                } else {
                    e();
                    this.g = j.IDLE;
                    z = false;
                }
                a(socket);
                this.b.a(mVar);
                if (z) {
                    return;
                }
                this.b.a(m.ReConnectStop);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.net.Socket] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.net.Socket] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.net.DatagramSocket] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.net.Socket] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    public /* synthetic */ void a(p pVar) {
        ?? r1;
        final ?? socket;
        ?? r0 = 0;
        r0 = 0;
        try {
            boolean zStartsWith = pVar.e.startsWith("udp");
            socket = new Socket();
            try {
                socket.connect(new InetSocketAddress(pVar.c, pVar.d), 5000);
                socket.setKeepAlive(true);
                if (zStartsWith) {
                    final DatagramSocket datagramSocket = new DatagramSocket();
                    try {
                        datagramSocket.connect(pVar.e.contains("v6") ? a("ipv6", pVar.a, pVar.b) : a("ipv4", pVar.a, pVar.b));
                        InetAddress address = ((InetSocketAddress) datagramSocket.getRemoteSocketAddress()).getAddress();
                        if (!t.b(pVar.a) && !t.b(address.getHostAddress())) {
                            av avVarA = ar.a(ar.a((Socket) socket));
                            avVarA.b((pVar.e + "|ok").getBytes());
                            avVarA.flush();
                            if (!a(new k() { // from class: com.szns.sdk.i$$ExternalSyntheticLambda0
                                @Override // com.szns.sdk.k
                                public final void run() {
                                    this.f$0.a(datagramSocket, socket);
                                }
                            })) {
                                a((Socket) socket, (Socket) null, datagramSocket);
                                return;
                            } else {
                                if (a(new k() { // from class: com.szns.sdk.i$$ExternalSyntheticLambda3
                                    @Override // com.szns.sdk.k
                                    public final void run() {
                                        this.f$0.a(socket, datagramSocket);
                                    }
                                })) {
                                    return;
                                }
                                a((Socket) socket, (Socket) null, datagramSocket);
                                return;
                            }
                        }
                        av avVarA2 = ar.a(ar.a((Socket) socket));
                        avVarA2.b((pVar.e + "|err").getBytes());
                        avVarA2.flush();
                        a((Socket) socket, (Socket) null, datagramSocket);
                        return;
                    } catch (Exception e) {
                        e = e;
                        r1 = 0;
                        r0 = socket;
                        socket = datagramSocket;
                    }
                } else {
                    final Socket socket2 = new Socket();
                    try {
                        socket2.connect(pVar.e.startsWith("v6") ? a("ipv6", pVar.a, pVar.b) : a("ipv4", pVar.a, pVar.b), 5000);
                        socket2.setKeepAlive(true);
                        av avVarA3 = ar.a(ar.a((Socket) socket));
                        String hostAddress = socket2.getInetAddress().getHostAddress();
                        if (!t.b(pVar.a) && !t.b(hostAddress)) {
                            avVarA3.b((pVar.e + "|ok").getBytes());
                            avVarA3.flush();
                            if (!a(new k() { // from class: com.szns.sdk.i$$ExternalSyntheticLambda4
                                @Override // com.szns.sdk.k
                                public final void run() {
                                    this.f$0.c(socket, socket2);
                                }
                            })) {
                                a((Socket) socket, socket2, (DatagramSocket) null);
                                return;
                            } else {
                                if (a(new k() { // from class: com.szns.sdk.i$$ExternalSyntheticLambda5
                                    @Override // com.szns.sdk.k
                                    public final void run() {
                                        this.f$0.b(socket2, socket);
                                    }
                                })) {
                                    return;
                                }
                                a((Socket) socket, socket2, (DatagramSocket) null);
                                return;
                            }
                        }
                        avVarA3.b((pVar.e + "|err").getBytes());
                        avVarA3.flush();
                        a((Socket) socket, socket2, (DatagramSocket) null);
                        return;
                    } catch (Exception e2) {
                        e = e2;
                        socket = 0;
                        r0 = socket;
                        r1 = socket2;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                r1 = r0;
                r0 = socket;
                socket = r1;
            }
        } catch (Exception e4) {
            e = e4;
            r1 = 0;
        }
        l lVar = this.b;
        m mVar = m.HandleTunnelError;
        e.getMessage();
        lVar.a(mVar);
        a((Socket) r0, (Socket) r1, (DatagramSocket) socket);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:23:0x0048  */
    public /* synthetic */ void a(DatagramSocket datagramSocket, Socket socket) {
        byte[] bArr = new byte[8192];
        DatagramPacket datagramPacket = new DatagramPacket(bArr, 8192);
        try {
            av avVarA = ar.a(ar.a(socket));
            while (!datagramSocket.isClosed() && !socket.isClosed()) {
                try {
                    try {
                        datagramSocket.receive(datagramPacket);
                        int length = datagramPacket.getLength();
                        if (length > 0) {
                            avVarA.a(bArr, length);
                            avVarA.flush();
                        }
                        datagramPacket.setLength(8192);
                    } catch (SocketException e) {
                        if (!datagramSocket.isClosed() && !socket.isClosed()) {
                            throw e;
                        }
                        avVarA.close();
                        if (!datagramSocket.isClosed()) {
                            datagramSocket.close();
                        }
                        a(socket);
                    }
                } catch (Throwable th) {
                    try {
                        avVarA.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
            avVarA.close();
            if (!datagramSocket.isClosed()) {
                datagramSocket.close();
            }
            a(socket);
        } catch (Throwable th3) {
            if (!datagramSocket.isClosed()) {
                datagramSocket.close();
            }
            a(socket);
            throw th3;
        }
    }

    private static void a(Socket socket) {
        if (socket != null) {
            try {
                if (socket.isClosed()) {
                    return;
                }
                socket.close();
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:84:0x0151  */
    /* JADX WARN: Code duplicated, block: B:85:0x0157  */
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void c(Socket socket, long j) throws Throwable {
        String strA;
        l lVar;
        m mVar;
        String str;
        String[] strArrSplit;
        String str2;
        boolean z;
        aw awVarA = ar.a(ar.b(socket));
        while (true) {
            boolean z2 = false;
            try {
                if (!a(j, socket) || awVarA.c()) {
                    break;
                }
                long jH = awVarA.h();
                if (jH != -1) {
                    strA = awVarA.a(jH);
                    awVarA.b(1L);
                } else if (awVarA.b().a() != 0) {
                    strA = awVarA.a(awVarA.b().a());
                } else {
                    continue;
                }
                if (this.a.d == null) {
                    lVar = this.b;
                    mVar = m.RunFail;
                } else {
                    String strB = s.b(strA);
                    if (strB == null || strB.isEmpty()) {
                        lVar = this.b;
                        mVar = m.RunFail;
                    } else {
                        String[] strArrSplit2 = this.a.d.split(":");
                        String[] strArrSplit3 = strB.split("&");
                        int length = strArrSplit3.length;
                        if (length == 2) {
                            String str3 = strArrSplit3[0];
                            str = strArrSplit3[1];
                            strArrSplit = strArrSplit2;
                            str2 = str3;
                        } else if (length == 3) {
                            str2 = strArrSplit3[0];
                            str = strArrSplit3[1];
                            strArrSplit = strArrSplit3[2].split(":");
                        }
                        String[] strArrSplit4 = str2.split(":");
                        final p pVar = new p(strArrSplit4[0], Integer.parseInt(strArrSplit4[1]), strArrSplit[0], Integer.parseInt(strArrSplit[1]));
                        pVar.e = str;
                        if (pVar.e.startsWith("stop")) {
                            long jMax = Math.max(pVar.b, 0);
                            synchronized (this.c) {
                                if (this.g != j.STOPPING && this.g != j.CLOSED) {
                                    long j2 = this.i;
                                    Socket socket2 = this.d;
                                    this.d = null;
                                    d();
                                    int i = this.h;
                                    if (i <= 0) {
                                        e();
                                        this.g = j.IDLE;
                                        z = true;
                                    } else {
                                        this.h = i - 1;
                                        this.g = j.RECONNECT_WAIT;
                                        a(j2, jMax);
                                        z = false;
                                    }
                                    a(socket2);
                                    if (z) {
                                        lVar = this.b;
                                        mVar = m.ReConnectStop;
                                    }
                                }
                            }
                        } else if (!n.a().a(new Runnable() { // from class: com.szns.sdk.i$$ExternalSyntheticLambda10
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.a(pVar);
                            }
                        })) {
                            lVar = this.b;
                            mVar = m.HandleTunnelError;
                        }
                    }
                }
                lVar.a(mVar);
            } catch (Exception e) {
                try {
                    String message = e.getMessage();
                    try {
                        awVarA.close();
                    } catch (Exception unused) {
                    }
                    m mVar2 = m.ReadError;
                    if (message != null) {
                        message.isEmpty();
                    }
                    a(j, mVar2);
                    return;
                } catch (Throwable th) {
                    th = th;
                    z2 = true;
                    try {
                        awVarA.close();
                    } catch (Exception unused2) {
                    }
                    if (z2) {
                        a(j, m.ReadError);
                        throw th;
                    }
                    a(socket);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                awVarA.close();
                if (z2) {
                    a(j, m.ReadError);
                    throw th;
                }
                a(socket);
                throw th;
            }
        }
        boolean zA = a(j, socket);
        try {
            awVarA.close();
        } catch (Exception unused3) {
        }
        if (zA) {
            a(j, m.ReadError);
        } else {
            a(socket);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(Socket socket, DatagramSocket datagramSocket) {
        byte[] bArr = new byte[8192];
        try {
            aw awVarA = ar.a(ar.b(socket));
            while (!socket.isClosed() && !datagramSocket.isClosed()) {
                try {
                    try {
                        long jA = awVarA.a(bArr, 0, 8192);
                        if (jA == -1) {
                            break;
                        } else if (jA > 0) {
                            datagramSocket.send(new DatagramPacket(bArr, 0, (int) jA));
                        }
                    } catch (SocketException e) {
                        if (!socket.isClosed() && !datagramSocket.isClosed()) {
                            throw e;
                        }
                    }
                } catch (Throwable th) {
                    try {
                        awVarA.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
            awVarA.close();
            a(socket);
            if (datagramSocket.isClosed()) {
                return;
            }
            datagramSocket.close();
        } catch (Throwable th3) {
            a(socket);
            if (!datagramSocket.isClosed()) {
                datagramSocket.close();
            }
            throw th3;
        }
    }

    private static void a(Socket socket, Socket socket2) {
        an anVar = new an();
        try {
            aw awVarA = ar.a(ar.b(socket));
            av avVarA = ar.a(ar.a(socket2));
            while (!awVarA.c()) {
                long jA_ = awVarA.a_(anVar, 8192L);
                if (jA_ > 0) {
                    avVarA.a(anVar, jA_);
                    avVarA.flush();
                }
            }
            awVarA.close();
            avVarA.close();
        } finally {
            anVar.f();
            a(socket);
            a(socket2);
        }
    }

    private static void a(Socket socket, Socket socket2, DatagramSocket datagramSocket) {
        a(socket);
        a(socket2);
        if (datagramSocket != null) {
            try {
                if (datagramSocket.isClosed()) {
                    return;
                }
                datagramSocket.close();
            } catch (Exception unused) {
            }
        }
    }

    private boolean a(long j, Socket socket) {
        boolean z;
        synchronized (this.c) {
            z = this.i == j && this.g == j.CONNECTED && this.d == socket;
        }
        return z;
    }

    private boolean a(final k kVar) {
        if (n.a().a(new Runnable() { // from class: com.szns.sdk.i$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.b(kVar);
            }
        })) {
            return true;
        }
        this.b.a(m.HandleTunnelError);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(final long j) {
        synchronized (this.c) {
            if (this.i == j && this.g == j.RECONNECT_WAIT) {
                this.g = j.CONNECTING;
                if (n.a().a(new Runnable() { // from class: com.szns.sdk.i$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.c(j);
                    }
                })) {
                    return;
                }
                a(j, m.ConnectError);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(k kVar) {
        try {
            kVar.run();
        } catch (IOException e) {
            l lVar = this.b;
            m mVar = m.TunnelError;
            e.getMessage();
            lVar.a(mVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(Socket socket, long j) {
        try {
            if (!a(j, socket) || socket == null || socket.isClosed()) {
                throw new IOException("socket closed");
            }
            av avVarA = ar.a(ar.a(socket));
            if (avVarA.isOpen()) {
                avVarA.b(this.a.i.getBytes());
                avVarA.flush();
            }
        } catch (Exception e) {
            m mVar = m.WriteError;
            e.getMessage();
            a(j, mVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(Socket socket, Socket socket2) {
        a(socket, socket2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(Socket socket, Socket socket2) {
        a(socket, socket2);
    }

    private void d() {
        ScheduledFuture scheduledFuture = this.e;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            this.e = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(final Socket socket, final long j) {
        if (n.a().a(new Runnable() { // from class: com.szns.sdk.i$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.b(socket, j);
            }
        })) {
            return;
        }
        a(j, m.WriteError);
    }

    private void e() {
        ScheduledFuture scheduledFuture = this.f;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            this.f = null;
        }
    }

    @Override // com.szns.sdk.q
    public final void a() {
        synchronized (this.c) {
            if (this.g == j.IDLE || this.g == j.CLOSED) {
                this.h = this.a.a;
                final long j = this.i + 1;
                this.i = j;
                this.g = j.CONNECTING;
                e();
                if (n.a().a(new Runnable() { // from class: com.szns.sdk.i$$ExternalSyntheticLambda8
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.d(j);
                    }
                })) {
                    return;
                }
                a(j, m.ConnectError);
            }
        }
    }

    @Override // com.szns.sdk.q
    public final void b() {
        synchronized (this.c) {
            if (this.g == j.CLOSED) {
                return;
            }
            this.i++;
            this.g = j.STOPPING;
            Socket socket = this.d;
            this.d = null;
            d();
            e();
            a(socket);
            synchronized (this.c) {
                this.g = j.CLOSED;
            }
        }
    }

    @Override // com.szns.sdk.q
    public final Boolean c() {
        Boolean boolValueOf;
        synchronized (this.c) {
            boolValueOf = Boolean.valueOf(this.g == j.CONNECTED);
        }
        return boolValueOf;
    }
}
