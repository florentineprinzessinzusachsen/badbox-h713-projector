package e3;

import a3.a0;
import a3.c0;
import a3.d0;
import a3.g0;
import a3.y;
import d0.l0;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Socket;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d implements v, f3.f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d3.e f711a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r f712b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f713c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f714d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f715e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f716f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f717g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final p f718h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final s f719i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final g0 f720j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final List f721k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final a0 f722l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f723m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f724n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public volatile boolean f725o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Socket f726p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Socket f727q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public a3.p f728r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public y f729s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public a2.f f730t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public q f731u;

    public d(d3.e eVar, r rVar, int i4, int i5, int i6, int i7, boolean z3, p pVar, s sVar, g0 g0Var, List list, a0 a0Var, int i8, boolean z4) {
        j2.i.e(eVar, "taskRunner");
        j2.i.e(rVar, "connectionPool");
        j2.i.e(g0Var, "route");
        this.f711a = eVar;
        this.f712b = rVar;
        this.f713c = i4;
        this.f714d = i5;
        this.f715e = i6;
        this.f716f = i7;
        this.f717g = z3;
        this.f718h = pVar;
        this.f719i = sVar;
        this.f720j = g0Var;
        this.f721k = list;
        this.f722l = a0Var;
        this.f723m = i8;
        this.f724n = z4;
    }

    @Override // e3.v
    public final v a() {
        return new d(this.f711a, this.f712b, this.f713c, this.f714d, this.f715e, this.f716f, this.f717g, this.f718h, this.f719i, this.f720j, this.f721k, this.f722l, this.f723m, this.f724n);
    }

    @Override // e3.v
    public final boolean b() {
        return this.f729s != null;
    }

    @Override // f3.f
    public final g0 c() {
        return this.f720j;
    }

    @Override // e3.v, f3.f
    public final void cancel() {
        this.f725o = true;
        Socket socket = this.f726p;
        if (socket != null) {
            b3.g.c(socket);
        }
    }

    @Override // e3.v
    public final u d() {
        Socket socket;
        Socket socket2;
        r rVar = this.f712b;
        CopyOnWriteArrayList copyOnWriteArrayList = this.f718h.f784u;
        g0 g0Var = this.f720j;
        if (this.f726p != null) {
            throw new IllegalStateException("TCP already connected");
        }
        copyOnWriteArrayList.add(this);
        boolean z3 = false;
        try {
            try {
                j2.i.e(g0Var.f147c, "inetSocketAddress");
                rVar.getClass();
                i();
                z3 = true;
                u uVar = new u(this, (Throwable) null, 6);
                copyOnWriteArrayList.remove(this);
                return uVar;
            } catch (IOException e4) {
                a3.a aVar = g0Var.f145a;
                if (g0Var.f146b.type() != Proxy.Type.DIRECT) {
                    a3.a aVar2 = g0Var.f145a;
                    aVar2.f57g.connectFailed(aVar2.f58h.g(), g0Var.f146b.address(), e4);
                }
                j2.i.e(g0Var.f147c, "inetSocketAddress");
                rVar.getClass();
                u uVar2 = new u(this, e4, 2);
                copyOnWriteArrayList.remove(this);
                if (!z3 && (socket = this.f726p) != null) {
                    b3.g.c(socket);
                }
                return uVar2;
            }
        } catch (Throwable th) {
            copyOnWriteArrayList.remove(this);
            if (!z3 && (socket2 = this.f726p) != null) {
                b3.g.c(socket2);
            }
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:117:0x0171  */
    /* JADX WARN: Code duplicated, block: B:119:0x0175  */
    @Override // e3.v
    public final u e() throws Throwable {
        boolean z3;
        d dVar;
        boolean z4;
        Socket socket;
        d dVar2;
        r rVar = this.f712b;
        CopyOnWriteArrayList copyOnWriteArrayList = this.f718h.f784u;
        Socket socket2 = this.f726p;
        if (socket2 == null) {
            throw new IllegalArgumentException("TCP not connected");
        }
        if (b()) {
            throw new IllegalStateException("already connected");
        }
        g0 g0Var = this.f720j;
        a3.a aVar = g0Var.f145a;
        InetSocketAddress inetSocketAddress = g0Var.f147c;
        a3.a aVar2 = g0Var.f145a;
        List list = aVar.f60j;
        copyOnWriteArrayList.add(this);
        d dVar3 = null;
        try {
            try {
                if (this.f722l != null) {
                    u uVarK = k();
                    if (uVarK.f824c != null) {
                        copyOnWriteArrayList.remove(this);
                        Socket socket3 = this.f727q;
                        if (socket3 != null) {
                            b3.g.c(socket3);
                        }
                        b3.g.c(socket2);
                        return uVarK;
                    }
                }
                if (aVar2.f53c != null) {
                    a2.f fVar = this.f730t;
                    if (fVar == null) {
                        j2.i.h("socket");
                        throw null;
                    }
                    if (((q3.o) fVar.f46f).f1845e.c()) {
                        a2.f fVar2 = this.f730t;
                        if (fVar2 == null) {
                            j2.i.h("socket");
                            throw null;
                        }
                        if (((q3.n) fVar2.f47g).f1842e.c()) {
                            SSLSocketFactory sSLSocketFactory = aVar2.f53c;
                            a3.t tVar = aVar2.f58h;
                            Socket socketCreateSocket = sSLSocketFactory.createSocket(socket2, tVar.f211d, tVar.f212e, true);
                            j2.i.c(socketCreateSocket, "null cannot be cast to non-null type javax.net.ssl.SSLSocket");
                            SSLSocket sSLSocket = (SSLSocket) socketCreateSocket;
                            d dVarM = m(list, sSLSocket);
                            a3.j jVar = (a3.j) list.get(dVarM.f723m);
                            d dVarL = dVarM.l(list, sSLSocket);
                            try {
                                jVar.a(sSLSocket, dVarM.f724n);
                                j(sSLSocket, jVar);
                                dVar2 = dVarL;
                            } catch (IOException e4) {
                                e = e4;
                                dVar = null;
                                z4 = false;
                                dVar3 = dVarL;
                            }
                        }
                    }
                    throw new IOException("TLS tunnel buffered too many bytes!");
                }
                this.f727q = socket2;
                List list2 = aVar2.f59i;
                y yVar = y.f275j;
                if (!list2.contains(yVar)) {
                    yVar = y.f272g;
                }
                this.f729s = yVar;
                dVar2 = null;
                try {
                    try {
                        d3.e eVar = this.f711a;
                        try {
                            r rVar2 = this.f712b;
                            g0 g0Var2 = this.f720j;
                            Socket socket4 = this.f727q;
                            j2.i.b(socket4);
                            try {
                                a3.p pVar = this.f728r;
                                y yVar2 = this.f729s;
                                j2.i.b(yVar2);
                                try {
                                    a2.f fVar3 = this.f730t;
                                    try {
                                        if (fVar3 == null) {
                                            j2.i.h("socket");
                                            throw null;
                                        }
                                        rVar.getClass();
                                        dVar = null;
                                        q qVar = new q(eVar, rVar2, g0Var2, socket2, socket4, pVar, yVar2, fVar3);
                                        this.f731u = qVar;
                                        qVar.j();
                                        j2.i.e(inetSocketAddress, "inetSocketAddress");
                                        try {
                                            u uVar = new u(this, (Throwable) null, 6);
                                            copyOnWriteArrayList.remove(this);
                                            return uVar;
                                        } catch (IOException e5) {
                                            e = e5;
                                            dVar3 = dVar2;
                                            z4 = true;
                                        } catch (Throwable th) {
                                            th = th;
                                            z3 = true;
                                            copyOnWriteArrayList.remove(this);
                                            if (!z3) {
                                                socket = this.f727q;
                                                if (socket != null) {
                                                    b3.g.c(socket);
                                                }
                                                b3.g.c(socket2);
                                            }
                                            throw th;
                                        }
                                    } catch (IOException e6) {
                                        e = e6;
                                        dVar3 = dVar2;
                                        z4 = false;
                                    }
                                } catch (IOException e7) {
                                    e = e7;
                                    dVar = null;
                                }
                            } catch (IOException e8) {
                                e = e8;
                                dVar = null;
                            }
                        } catch (IOException e9) {
                            e = e9;
                            dVar = null;
                        }
                    } catch (IOException e10) {
                        e = e10;
                        dVar = null;
                    }
                } catch (IOException e11) {
                    e = e11;
                    dVar = null;
                }
                dVar3 = dVar2;
            } catch (IOException e12) {
                e = e12;
                dVar = null;
            }
        } catch (Throwable th2) {
            th = th2;
            z3 = false;
        }
        z4 = false;
        try {
            j2.i.e(inetSocketAddress, "inetSocketAddress");
            rVar.getClass();
            if (!this.f717g || (e instanceof ProtocolException) || (e instanceof InterruptedIOException) || (((e instanceof SSLHandshakeException) && (e.getCause() instanceof CertificateException)) || (e instanceof SSLPeerUnverifiedException) || !(e instanceof SSLException))) {
                dVar3 = dVar;
            }
            u uVar2 = new u(this, dVar3, e);
            copyOnWriteArrayList.remove(this);
            if (!z4) {
                Socket socket5 = this.f727q;
                if (socket5 != null) {
                    b3.g.c(socket5);
                }
                b3.g.c(socket2);
            }
            return uVar2;
        } catch (Throwable th3) {
            th = th3;
            z3 = z4;
            copyOnWriteArrayList.remove(this);
            if (!z3) {
                socket = this.f727q;
                if (socket != null) {
                    b3.g.c(socket);
                }
                b3.g.c(socket2);
            }
            throw th;
        }
    }

    @Override // e3.v
    public final q f() {
        this.f718h.f767d.f268y.g(this.f720j);
        q qVar = this.f731u;
        j2.i.b(qVar);
        j2.i.e(this.f720j, "route");
        t tVarE = this.f719i.e(this, this.f721k);
        if (tVarE != null) {
            return tVarE.f821a;
        }
        synchronized (qVar) {
            r rVar = this.f712b;
            rVar.getClass();
            TimeZone timeZone = b3.g.f348a;
            rVar.f804d.add(qVar);
            rVar.f802b.d(rVar.f803c, 0L);
            this.f718h.b(qVar);
        }
        return qVar;
    }

    public final void i() throws IOException {
        Socket socketCreateSocket;
        Proxy.Type type = this.f720j.f146b.type();
        int i4 = type == null ? -1 : c.f710a[type.ordinal()];
        if (i4 == 1 || i4 == 2) {
            socketCreateSocket = this.f720j.f145a.f52b.createSocket();
            j2.i.b(socketCreateSocket);
        } else {
            socketCreateSocket = new Socket(this.f720j.f146b);
        }
        this.f726p = socketCreateSocket;
        if (this.f725o) {
            throw new IOException("canceled");
        }
        socketCreateSocket.setSoTimeout(this.f716f);
        try {
            k3.e eVar = k3.e.f1300a;
            k3.e.f1300a.f(socketCreateSocket, this.f720j.f147c, this.f715e);
            try {
                this.f730t = new a2.f(new a3.l(socketCreateSocket));
            } catch (NullPointerException e4) {
                if (j2.i.a(e4.getMessage(), "throw with null exception")) {
                    throw new IOException(e4);
                }
            }
        } catch (ConnectException e5) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.f720j.f147c);
            connectException.initCause(e5);
            throw connectException;
        }
    }

    public final void j(SSLSocket sSLSocket, a3.j jVar) {
        String strG;
        y yVarF;
        a3.a aVar = this.f720j.f145a;
        try {
            if (jVar.f166b) {
                k3.e eVar = k3.e.f1300a;
                k3.e.f1300a.e(sSLSocket, aVar.f58h.f211d, aVar.f59i);
            }
            sSLSocket.startHandshake();
            SSLSession session = sSLSocket.getSession();
            j2.i.b(session);
            a3.p pVarJ = a.a.j(session);
            HostnameVerifier hostnameVerifier = aVar.f54d;
            j2.i.b(hostnameVerifier);
            boolean zVerify = hostnameVerifier.verify(aVar.f58h.f211d, session);
            int i4 = 0;
            if (!zVerify) {
                List listA = pVarJ.a();
                if (listA.isEmpty()) {
                    throw new SSLPeerUnverifiedException("Hostname " + aVar.f58h.f211d + " not verified (no certificates)");
                }
                Object obj = listA.get(0);
                j2.i.c(obj, "null cannot be cast to non-null type java.security.cert.X509Certificate");
                X509Certificate x509Certificate = (X509Certificate) obj;
                StringBuilder sb = new StringBuilder("\n            |Hostname ");
                sb.append(aVar.f58h.f211d);
                sb.append(" not verified:\n            |    certificate: ");
                a3.e eVar2 = a3.e.f119c;
                sb.append(l0.G(x509Certificate));
                sb.append("\n            |    DN: ");
                sb.append(x509Certificate.getSubjectDN().getName());
                sb.append("\n            |    subjectAltNames: ");
                sb.append(v1.j.A0(o3.c.a(x509Certificate, 7), o3.c.a(x509Certificate, 2)));
                sb.append("\n            ");
                throw new SSLPeerUnverifiedException(p2.j.t0(sb.toString()));
            }
            a3.e eVar3 = aVar.f55e;
            j2.i.b(eVar3);
            this.f728r = new a3.p(pVarJ.f193a, pVarJ.f194b, pVarJ.f195c, new b(eVar3, pVarJ, aVar, i4));
            j2.i.e(aVar.f58h.f211d, "hostname");
            Iterator it = eVar3.f120a.iterator();
            if (it.hasNext()) {
                it.next().getClass();
                throw new ClassCastException();
            }
            if (jVar.f166b) {
                k3.e eVar4 = k3.e.f1300a;
                strG = k3.e.f1300a.g(sSLSocket);
            } else {
                strG = null;
            }
            this.f727q = sSLSocket;
            this.f730t = new a2.f(new a3.l(sSLSocket));
            if (strG != null) {
                y.f270e.getClass();
                yVarF = a3.b.f(strG);
            } else {
                yVarF = y.f272g;
            }
            this.f729s = yVarF;
            k3.e eVar5 = k3.e.f1300a;
            k3.e.f1300a.getClass();
        } catch (Throwable th) {
            k3.e eVar6 = k3.e.f1300a;
            k3.e.f1300a.getClass();
            b3.g.c(sSLSocket);
            throw th;
        }
    }

    public final u k() throws IOException {
        a0 a0Var = this.f722l;
        j2.i.b(a0Var);
        g0 g0Var = this.f720j;
        String str = "CONNECT " + b3.g.i(g0Var.f145a.f58h, true) + " HTTP/1.1";
        a2.f fVar = this.f730t;
        if (fVar == null) {
            j2.i.h("socket");
            throw null;
        }
        g3.h hVar = new g3.h(null, this, fVar);
        a2.f fVar2 = this.f730t;
        if (fVar2 == null) {
            j2.i.h("socket");
            throw null;
        }
        q3.w wVarF = ((q3.o) fVar2.f46f).f1844d.f();
        long j4 = this.f713c;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        wVarF.g(j4);
        a2.f fVar3 = this.f730t;
        if (fVar3 == null) {
            j2.i.h("socket");
            throw null;
        }
        ((q3.n) fVar3.f47g).f1841d.f().g(this.f714d);
        hVar.l((a3.r) a0Var.f64d, str);
        hVar.a();
        c0 c0VarH = hVar.h(false);
        j2.i.b(c0VarH);
        c0VarH.f88a = a0Var;
        d0 d0VarA = c0VarH.a();
        int i4 = d0VarA.f106g;
        long jE = b3.g.e(d0VarA);
        if (jE != -1) {
            g3.e eVarK = hVar.k((a3.t) d0VarA.f103d.f63c, jE);
            b3.g.g(eVarK, Integer.MAX_VALUE);
            eVarK.close();
        }
        if (i4 == 200) {
            return new u(this, (Throwable) null, 6);
        }
        if (i4 != 407) {
            throw new IOException(a1.c.c(i4, "Unexpected response code for CONNECT: "));
        }
        g0Var.f145a.f56f.getClass();
        throw new IOException("Failed to authenticate with proxy");
    }

    public final d l(List list, SSLSocket sSLSocket) {
        String[] strArr;
        String[] strArr2;
        j2.i.e(list, "connectionSpecs");
        int i4 = this.f723m;
        int size = list.size();
        for (int i5 = i4 + 1; i5 < size; i5++) {
            a3.j jVar = (a3.j) list.get(i5);
            jVar.getClass();
            if (jVar.f165a && (((strArr = jVar.f168d) == null || b3.d.e(strArr, sSLSocket.getEnabledProtocols(), x1.a.f2672b)) && ((strArr2 = jVar.f167c) == null || b3.d.e(strArr2, sSLSocket.getEnabledCipherSuites(), a3.g.f126c)))) {
                return new d(this.f711a, this.f712b, this.f713c, this.f714d, this.f715e, this.f716f, this.f717g, this.f718h, this.f719i, this.f720j, this.f721k, this.f722l, i5, i4 != -1);
            }
        }
        return null;
    }

    public final d m(List list, SSLSocket sSLSocket) throws UnknownServiceException {
        j2.i.e(list, "connectionSpecs");
        if (this.f723m != -1) {
            return this;
        }
        d dVarL = l(list, sSLSocket);
        if (dVarL != null) {
            return dVarL;
        }
        StringBuilder sb = new StringBuilder("Unable to find acceptable protocols. isFallback=");
        sb.append(this.f724n);
        sb.append(", modes=");
        sb.append(list);
        sb.append(", supported protocols=");
        String[] enabledProtocols = sSLSocket.getEnabledProtocols();
        j2.i.b(enabledProtocols);
        String string = Arrays.toString(enabledProtocols);
        j2.i.d(string, "toString(...)");
        sb.append(string);
        throw new UnknownServiceException(sb.toString());
    }

    @Override // f3.f
    public final void g() {
    }

    @Override // f3.f
    public final void h(p pVar, IOException iOException) {
    }
}
