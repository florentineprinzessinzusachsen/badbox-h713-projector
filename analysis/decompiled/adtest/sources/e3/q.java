package e3;

import a3.g0;
import a3.y;
import a3.z;
import h3.d0;
import h3.e0;
import java.io.IOException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLPeerUnverifiedException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class q extends h3.n implements f3.f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d3.e f785b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g0 f786c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Socket f787d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Socket f788e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final a3.p f789f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final y f790g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final a2.f f791h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public h3.q f792i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f793j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f794k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f795l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f796m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f797n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f798o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ArrayList f799p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long f800q;

    public q(d3.e eVar, r rVar, g0 g0Var, Socket socket, Socket socket2, a3.p pVar, y yVar, a2.f fVar) {
        j2.i.e(eVar, "taskRunner");
        j2.i.e(rVar, "connectionPool");
        j2.i.e(g0Var, "route");
        j2.i.e(socket, "rawSocket");
        j2.i.e(socket2, "javaNetSocket");
        j2.i.e(yVar, "protocol");
        j2.i.e(fVar, "socket");
        this.f785b = eVar;
        this.f786c = g0Var;
        this.f787d = socket;
        this.f788e = socket2;
        this.f789f = pVar;
        this.f790g = yVar;
        this.f791h = fVar;
        this.f798o = 1;
        this.f799p = new ArrayList();
        this.f800q = Long.MAX_VALUE;
    }

    public static void d(a3.x xVar, g0 g0Var, IOException iOException) {
        j2.i.e(g0Var, "failedRoute");
        j2.i.e(iOException, "failure");
        if (g0Var.f146b.type() != Proxy.Type.DIRECT) {
            a3.a aVar = g0Var.f145a;
            aVar.f57g.connectFailed(aVar.f58h.g(), g0Var.f146b.address(), iOException);
        }
        a3.h hVar = xVar.f268y;
        synchronized (hVar) {
            ((LinkedHashSet) hVar.f149e).add(g0Var);
        }
    }

    @Override // h3.n
    public final void a(h3.q qVar, d0 d0Var) {
        j2.i.e(d0Var, "settings");
        synchronized (this) {
            this.f798o = (d0Var.f1088a & 8) != 0 ? d0Var.f1089b[3] : Integer.MAX_VALUE;
        }
    }

    @Override // h3.n
    public final void b(h3.y yVar) {
        yVar.e(h3.b.REFUSED_STREAM, null);
    }

    @Override // f3.f
    public final g0 c() {
        return this.f786c;
    }

    @Override // f3.f
    public final void cancel() {
        b3.g.c(this.f787d);
    }

    public final void e() {
        synchronized (this) {
            this.f796m++;
        }
    }

    public final boolean f(a3.a aVar, List list) {
        a3.t tVar = aVar.f58h;
        TimeZone timeZone = b3.g.f348a;
        if (this.f799p.size() < this.f798o && !this.f793j) {
            g0 g0Var = this.f786c;
            a3.a aVar2 = g0Var.f145a;
            a3.a aVar3 = g0Var.f145a;
            if (aVar2.a(aVar)) {
                String str = tVar.f211d;
                String str2 = tVar.f211d;
                if (!j2.i.a(str, aVar3.f58h.f211d)) {
                    if (this.f792i != null && list != null && !list.isEmpty()) {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            g0 g0Var2 = (g0) it.next();
                            Proxy.Type type = g0Var2.f146b.type();
                            Proxy.Type type2 = Proxy.Type.DIRECT;
                            if (type == type2 && g0Var.f146b.type() == type2 && j2.i.a(g0Var.f147c, g0Var2.f147c)) {
                                if (aVar.f54d != o3.c.f1576a) {
                                    break;
                                }
                                TimeZone timeZone2 = b3.g.f348a;
                                a3.t tVar2 = aVar3.f58h;
                                if (tVar.f212e != tVar2.f212e) {
                                    break;
                                }
                                boolean zA = j2.i.a(str2, tVar2.f211d);
                                a3.p pVar = this.f789f;
                                if (!zA) {
                                    if (!this.f794k && pVar != null) {
                                        List listA = pVar.a();
                                        if (!listA.isEmpty()) {
                                            Object obj = listA.get(0);
                                            j2.i.c(obj, "null cannot be cast to non-null type java.security.cert.X509Certificate");
                                            if (!o3.c.c(str2, (X509Certificate) obj)) {
                                                break;
                                            }
                                        } else {
                                            break;
                                        }
                                    } else {
                                        break;
                                        break;
                                    }
                                }
                                try {
                                    a3.e eVar = aVar.f55e;
                                    j2.i.b(eVar);
                                    j2.i.b(pVar);
                                    List listA2 = pVar.a();
                                    j2.i.e(str2, "hostname");
                                    j2.i.e(listA2, "peerCertificates");
                                    Iterator it2 = eVar.f120a.iterator();
                                    if (!it2.hasNext()) {
                                        return true;
                                    }
                                    it2.next().getClass();
                                    throw new ClassCastException();
                                } catch (SSLPeerUnverifiedException unused) {
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // f3.f
    public final void g() {
        synchronized (this) {
            this.f793j = true;
        }
    }

    @Override // f3.f
    public final void h(p pVar, IOException iOException) {
        synchronized (this) {
            try {
                if (!(iOException instanceof e0)) {
                    if (!(this.f792i != null) || (iOException instanceof h3.a)) {
                        this.f793j = true;
                        if (this.f796m == 0) {
                            if (iOException != null) {
                                d(pVar.f767d, this.f786c, iOException);
                            }
                            this.f795l++;
                        }
                    }
                } else if (((e0) iOException).f1097d == h3.b.REFUSED_STREAM) {
                    int i4 = this.f797n + 1;
                    this.f797n = i4;
                    if (i4 > 1) {
                        this.f793j = true;
                        this.f795l++;
                    }
                } else if (((e0) iOException).f1097d != h3.b.CANCEL || !pVar.f782s) {
                    this.f793j = true;
                    this.f795l++;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean i(boolean z3) {
        long j4;
        TimeZone timeZone = b3.g.f348a;
        long jNanoTime = System.nanoTime();
        if (this.f787d.isClosed() || this.f788e.isClosed() || this.f788e.isInputShutdown() || this.f788e.isOutputShutdown()) {
            return false;
        }
        h3.q qVar = this.f792i;
        if (qVar != null) {
            return qVar.k(jNanoTime);
        }
        synchronized (this) {
            j4 = jNanoTime - this.f800q;
        }
        if (j4 < 10000000000L || !z3) {
            return true;
        }
        Socket socket = this.f788e;
        q3.o oVar = (q3.o) this.f791h.f46f;
        j2.i.e(socket, "<this>");
        j2.i.e(oVar, "source");
        try {
            int soTimeout = socket.getSoTimeout();
            try {
                socket.setSoTimeout(1);
                return !oVar.b();
            } finally {
                socket.setSoTimeout(soTimeout);
            }
        } catch (SocketTimeoutException unused) {
            return true;
        } catch (IOException unused2) {
            return false;
        }
    }

    public final void j() throws SocketException {
        this.f800q = System.nanoTime();
        y yVar = this.f790g;
        if (yVar == y.f274i || yVar == y.f275j) {
            this.f788e.setSoTimeout(0);
            h3.c cVar = h3.c.f1077a;
            d3.e eVar = this.f785b;
            j2.i.e(eVar, "taskRunner");
            z zVar = new z();
            zVar.f281b = eVar;
            zVar.f283d = h3.n.f1125a;
            zVar.f284e = h3.c.f1077a;
            a2.f fVar = this.f791h;
            String str = this.f786c.f145a.f58h.f211d;
            j2.i.e(fVar, "socket");
            j2.i.e(str, "peerName");
            zVar.f282c = fVar;
            String str2 = b3.g.f349b + ' ' + str;
            j2.i.e(str2, "<set-?>");
            zVar.f280a = str2;
            zVar.f283d = this;
            zVar.f284e = cVar;
            h3.q qVar = new h3.q(zVar);
            this.f792i = qVar;
            d0 d0Var = h3.q.C;
            this.f798o = (d0Var.f1088a & 8) != 0 ? d0Var.f1089b[3] : Integer.MAX_VALUE;
            h3.z zVar2 = qVar.f1153z;
            synchronized (zVar2) {
                try {
                    if (zVar2.f1200g) {
                        throw new IOException("closed");
                    }
                    Logger logger = h3.z.f1196i;
                    if (logger.isLoggable(Level.FINE)) {
                        logger.fine(b3.g.d(">> CONNECTION " + h3.h.f1108a.b(), new Object[0]));
                    }
                    zVar2.f1197d.j(h3.h.f1108a);
                    zVar2.f1197d.flush();
                } catch (Throwable th) {
                    throw th;
                }
            }
            qVar.f1153z.K(qVar.f1147t);
            int iA = qVar.f1147t.a();
            if (iA != 65535) {
                qVar.f1153z.S(0, iA - 65535);
            }
            d3.c.c(qVar.f1137j.d(), qVar.f1133f, qVar.A);
        }
    }

    public final String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder("Connection{");
        g0 g0Var = this.f786c;
        sb.append(g0Var.f145a.f58h.f211d);
        sb.append(':');
        sb.append(g0Var.f145a.f58h.f212e);
        sb.append(", proxy=");
        sb.append(g0Var.f146b);
        sb.append(" hostAddress=");
        sb.append(g0Var.f147c);
        sb.append(" cipherSuite=");
        a3.p pVar = this.f789f;
        if (pVar == null || (obj = pVar.f194b) == null) {
            obj = "none";
        }
        sb.append(obj);
        sb.append(" protocol=");
        sb.append(this.f790g);
        sb.append('}');
        return sb.toString();
    }
}
