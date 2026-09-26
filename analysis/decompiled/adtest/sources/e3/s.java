package e3;

import a3.a0;
import a3.e0;
import a3.f0;
import a3.g0;
import a3.y;
import a3.z;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d3.e f805a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r f806b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f807c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f808d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f809e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f810f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f811g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f812h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final a3.a f813i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final a3.h f814j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final p f815k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f816l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public w f817m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public x f818n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public g0 f819o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final v1.h f820p;

    public s(d3.e eVar, r rVar, int i4, int i5, int i6, int i7, boolean z3, boolean z4, a3.a aVar, a3.h hVar, p pVar, a0 a0Var) {
        j2.i.e(eVar, "taskRunner");
        j2.i.e(rVar, "connectionPool");
        j2.i.e(hVar, "routeDatabase");
        this.f805a = eVar;
        this.f806b = rVar;
        this.f807c = i4;
        this.f808d = i5;
        this.f809e = i6;
        this.f810f = i7;
        this.f811g = z3;
        this.f812h = z4;
        this.f813i = aVar;
        this.f814j = hVar;
        this.f815k = pVar;
        this.f816l = !j2.i.a(a0Var.f62b, "GET");
        this.f820p = new v1.h();
    }

    public final boolean a(q qVar) {
        x xVar;
        g0 g0Var;
        if (this.f820p.isEmpty() && this.f819o == null) {
            if (qVar != null) {
                synchronized (qVar) {
                    g0Var = null;
                    if (qVar.f795l == 0 && qVar.f793j && b3.g.a(qVar.f786c.f145a.f58h, this.f813i.f58h)) {
                        g0Var = qVar.f786c;
                    }
                }
                if (g0Var != null) {
                    this.f819o = g0Var;
                    return true;
                }
            }
            w wVar = this.f817m;
            if ((wVar == null || wVar.f825a >= ((ArrayList) wVar.f826b).size()) && (xVar = this.f818n) != null) {
                return xVar.a();
            }
        }
        return true;
    }

    public final v b() {
        Socket socketK;
        t tVar;
        q qVar = this.f815k.f774k;
        if (qVar == null) {
            tVar = null;
        } else {
            boolean zI = qVar.i(this.f816l);
            synchronized (qVar) {
                try {
                    if (zI) {
                        socketK = (qVar.f793j || !f(qVar.f786c.f145a.f58h)) ? this.f815k.k() : null;
                    } else {
                        qVar.f793j = true;
                        socketK = this.f815k.k();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (this.f815k.f774k == null) {
                if (socketK != null) {
                    b3.g.c(socketK);
                }
                tVar = null;
            } else {
                if (socketK != null) {
                    throw new IllegalStateException("Check failed.");
                }
                tVar = new t(qVar);
            }
        }
        if (tVar != null) {
            return tVar;
        }
        t tVarE = e(null, null);
        if (tVarE != null) {
            return tVarE;
        }
        if (!this.f820p.isEmpty()) {
            return (v) this.f820p.removeFirst();
        }
        d dVarC = c();
        t tVarE2 = e(dVarC, dVarC.f721k);
        return tVarE2 != null ? tVarE2 : dVarC;
    }

    /* JADX WARN: Type inference failed for: r2v30, types: [java.lang.Object, java.util.List] */
    public final d c() throws IOException {
        String hostAddress;
        int port;
        List listD;
        boolean zContains;
        g0 g0Var = this.f819o;
        if (g0Var != null) {
            this.f819o = null;
            return d(g0Var, null);
        }
        w wVar = this.f817m;
        if (wVar != null && wVar.f825a < ((ArrayList) wVar.f826b).size()) {
            int i4 = wVar.f825a;
            ArrayList arrayList = (ArrayList) wVar.f826b;
            if (i4 >= arrayList.size()) {
                throw new NoSuchElementException();
            }
            int i5 = wVar.f825a;
            wVar.f825a = i5 + 1;
            return d((g0) arrayList.get(i5), null);
        }
        x xVar = this.f818n;
        if (xVar == null) {
            xVar = new x(this.f813i, this.f814j, this.f815k, this.f812h);
            this.f818n = xVar;
        }
        if (!xVar.a()) {
            throw new IOException("exhausted all routes");
        }
        if (!xVar.a()) {
            throw new NoSuchElementException();
        }
        ArrayList arrayList2 = new ArrayList();
        while (xVar.f831e < xVar.f830d.size()) {
            a3.a aVar = xVar.f827a;
            if (xVar.f831e >= xVar.f830d.size()) {
                throw new SocketException("No route to " + aVar.f58h.f211d + "; exhausted proxy configurations: " + xVar.f830d);
            }
            List list = xVar.f830d;
            int i6 = xVar.f831e;
            xVar.f831e = i6 + 1;
            Proxy proxy = (Proxy) list.get(i6);
            ArrayList arrayList3 = new ArrayList();
            xVar.f832f = arrayList3;
            if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) {
                a3.t tVar = aVar.f58h;
                hostAddress = tVar.f211d;
                port = tVar.f212e;
            } else {
                SocketAddress socketAddressAddress = proxy.address();
                if (!(socketAddressAddress instanceof InetSocketAddress)) {
                    throw new IllegalArgumentException(("Proxy.address() is not an InetSocketAddress: " + socketAddressAddress.getClass()).toString());
                }
                InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddressAddress;
                InetAddress address = inetSocketAddress.getAddress();
                if (address == null) {
                    hostAddress = inetSocketAddress.getHostName();
                    j2.i.d(hostAddress, "getHostName(...)");
                } else {
                    hostAddress = address.getHostAddress();
                    j2.i.d(hostAddress, "getHostAddress(...)");
                }
                port = inetSocketAddress.getPort();
            }
            if (1 > port || port >= 65536) {
                throw new SocketException("No route to " + hostAddress + ':' + port + "; port is out of range");
            }
            if (proxy.type() == Proxy.Type.SOCKS) {
                arrayList3.add(InetSocketAddress.createUnresolved(hostAddress, port));
            } else {
                p2.h hVar = b3.c.f342a;
                j2.i.e(hostAddress, "<this>");
                p2.h hVar2 = b3.c.f342a;
                hVar2.getClass();
                if (hVar2.f1761d.matcher(hostAddress).matches()) {
                    listD = l3.h.S(InetAddress.getByName(hostAddress));
                } else {
                    List listE = aVar.f51a.e(hostAddress);
                    if (listE.isEmpty()) {
                        throw new UnknownHostException(aVar.f51a + " returned no addresses for " + hostAddress);
                    }
                    listD = listE;
                }
                if (xVar.f829c && listD.size() >= 2) {
                    ArrayList arrayList4 = new ArrayList();
                    ArrayList arrayList5 = new ArrayList();
                    for (Object obj : listD) {
                        if (((InetAddress) obj) instanceof Inet6Address) {
                            arrayList4.add(obj);
                        } else {
                            arrayList5.add(obj);
                        }
                    }
                    if (!arrayList4.isEmpty() && !arrayList5.isEmpty()) {
                        byte[] bArr = b3.d.f343a;
                        Iterator it = arrayList4.iterator();
                        Iterator it2 = arrayList5.iterator();
                        w1.c cVar = new w1.c(10);
                        while (true) {
                            if (!it.hasNext() && !it2.hasNext()) {
                                break;
                            }
                            if (it.hasNext()) {
                                cVar.add(it.next());
                            }
                            if (it2.hasNext()) {
                                cVar.add(it2.next());
                            }
                        }
                        listD = l3.h.d(cVar);
                    }
                }
                Iterator it3 = listD.iterator();
                while (it3.hasNext()) {
                    arrayList3.add(new InetSocketAddress((InetAddress) it3.next(), port));
                }
            }
            Iterator it4 = xVar.f832f.iterator();
            while (it4.hasNext()) {
                g0 g0Var2 = new g0(xVar.f827a, proxy, (InetSocketAddress) it4.next());
                a3.h hVar3 = xVar.f828b;
                synchronized (hVar3) {
                    zContains = ((LinkedHashSet) hVar3.f149e).contains(g0Var2);
                }
                if (zContains) {
                    xVar.f833g.add(g0Var2);
                } else {
                    arrayList2.add(g0Var2);
                }
            }
            if (!arrayList2.isEmpty()) {
                break;
            }
        }
        if (arrayList2.isEmpty()) {
            v1.j.v0(arrayList2, xVar.f833g);
            xVar.f833g.clear();
        }
        w wVar2 = new w();
        wVar2.f826b = arrayList2;
        this.f817m = wVar2;
        if (this.f815k.f782s) {
            throw new IOException("Canceled");
        }
        if (wVar2.f825a >= arrayList2.size()) {
            throw new NoSuchElementException();
        }
        int i7 = wVar2.f825a;
        wVar2.f825a = i7 + 1;
        return d((g0) arrayList2.get(i7), arrayList2);
    }

    public final d d(g0 g0Var, ArrayList arrayList) throws UnknownServiceException {
        y yVar = y.f275j;
        j2.i.e(g0Var, "route");
        a3.a aVar = g0Var.f145a;
        if (aVar.f53c == null) {
            if (!aVar.f60j.contains(a3.j.f164f)) {
                throw new UnknownServiceException("CLEARTEXT communication not enabled for client");
            }
            String str = g0Var.f145a.f58h.f211d;
            k3.e eVar = k3.e.f1300a;
            if (!k3.e.f1300a.i(str)) {
                throw new UnknownServiceException("CLEARTEXT communication to " + str + " not permitted by network security policy");
            }
        } else if (aVar.f59i.contains(yVar)) {
            throw new UnknownServiceException("H2_PRIOR_KNOWLEDGE cannot be used with HTTPS");
        }
        a0 a0Var = null;
        if (g0Var.f146b.type() == Proxy.Type.HTTP) {
            a3.a aVar2 = g0Var.f145a;
            if (aVar2.f53c != null || aVar2.f59i.contains(yVar)) {
                z zVar = new z();
                a3.t tVar = g0Var.f145a.f58h;
                j2.i.e(tVar, "url");
                zVar.f281b = tVar;
                zVar.c("CONNECT", null);
                a3.a aVar3 = g0Var.f145a;
                zVar.b("Host", b3.g.i(aVar3.f58h, true));
                zVar.b("Proxy-Connection", "Keep-Alive");
                zVar.b("User-Agent", "okhttp/5.3.2");
                a0Var = new a0(zVar);
                e0 e0Var = f0.f124d;
                ArrayList arrayList2 = new ArrayList(20);
                l3.h.I("Proxy-Authenticate");
                l3.h.J("OkHttp-Preemptive", "Proxy-Authenticate");
                int i4 = 0;
                while (i4 < arrayList2.size()) {
                    if ("Proxy-Authenticate".equalsIgnoreCase((String) arrayList2.get(i4))) {
                        arrayList2.remove(i4);
                        arrayList2.remove(i4);
                        i4 -= 2;
                    }
                    i4 += 2;
                }
                arrayList2.add("Proxy-Authenticate");
                arrayList2.add(p2.i.S0("OkHttp-Preemptive").toString());
                new a3.r((String[]) arrayList2.toArray(new String[0]));
                j2.i.e(e0Var, "body");
                aVar3.f56f.getClass();
            }
        }
        return new d(this.f805a, this.f806b, this.f807c, this.f808d, this.f809e, this.f810f, this.f811g, this.f815k, this, g0Var, arrayList, a0Var, -1, false);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0043 A[Catch: all -> 0x0041, TryCatch #0 {all -> 0x0041, blocks: (B:14:0x0036, B:22:0x0043, B:25:0x004a), top: B:51:0x0036 }] */
    /* JADX WARN: Code duplicated, block: B:24:0x0049  */
    /* JADX WARN: Code duplicated, block: B:25:0x004a A[Catch: all -> 0x0041, TRY_LEAVE, TryCatch #0 {all -> 0x0041, blocks: (B:14:0x0036, B:22:0x0043, B:25:0x004a), top: B:51:0x0036 }] */
    public final t e(d dVar, List list) {
        q qVar;
        boolean z3;
        Socket socketK;
        r rVar = this.f806b;
        boolean z4 = this.f816l;
        a3.a aVar = this.f813i;
        p pVar = this.f815k;
        boolean z5 = dVar != null && dVar.b();
        rVar.getClass();
        Iterator it = rVar.f804d.iterator();
        j2.i.d(it, "iterator(...)");
        while (true) {
            if (!it.hasNext()) {
                qVar = null;
                break;
            }
            qVar = (q) it.next();
            j2.i.b(qVar);
            synchronized (qVar) {
                if (z5) {
                    try {
                        if (!(qVar.f792i != null)) {
                            z3 = false;
                        } else if (qVar.f(aVar, list)) {
                            pVar.b(qVar);
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                } else if (qVar.f(aVar, list)) {
                    z3 = false;
                } else {
                    pVar.b(qVar);
                    z3 = true;
                }
            }
            if (z3) {
                if (qVar.i(z4)) {
                    break;
                }
                synchronized (qVar) {
                    qVar.f793j = true;
                    socketK = pVar.k();
                }
                if (socketK != null) {
                    b3.g.c(socketK);
                }
            }
        }
        if (qVar == null) {
            return null;
        }
        if (dVar != null) {
            this.f819o = dVar.f720j;
            Socket socket = dVar.f727q;
            if (socket != null) {
                b3.g.c(socket);
            }
        }
        return new t(qVar);
    }

    public final boolean f(a3.t tVar) {
        j2.i.e(tVar, "url");
        a3.t tVar2 = this.f813i.f58h;
        return tVar.f212e == tVar2.f212e && j2.i.a(tVar.f211d, tVar2.f211d);
    }
}
