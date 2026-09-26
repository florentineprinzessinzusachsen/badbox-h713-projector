package d.h0.f;

import com.baidu.mobstat.Config;
import d.a0;
import d.c0;
import d.e0;
import d.i;
import d.j;
import d.k;
import d.p;
import d.r;
import d.t;
import d.u;
import d.x;
import d.y;
import e.l;
import e.s;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/* JADX INFO: compiled from: RealConnection.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends d.h0.i.g.h implements i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final j f4387b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e0 f4388c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Socket f4389d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Socket f4390e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private r f4391f;
    private y g;
    private d.h0.i.g h;
    private e.e i;
    private e.d j;
    public boolean k;
    public int l;
    public int m = 1;
    public final List<Reference<g>> n = new ArrayList();
    public long o = Long.MAX_VALUE;

    public c(j jVar, e0 e0Var) {
        this.f4387b = jVar;
        this.f4388c = e0Var;
    }

    private a0 g() {
        a0.a aVar = new a0.a();
        aVar.a(this.f4388c.a().k());
        aVar.b("Host", d.h0.c.a(this.f4388c.a().k(), true));
        aVar.b("Proxy-Connection", "Keep-Alive");
        aVar.b("User-Agent", d.h0.d.a());
        return aVar.a();
    }

    /* JADX WARN: Code duplicated, block: B:55:0x012f  */
    /* JADX WARN: Code duplicated, block: B:56:0x0136  */
    /* JADX WARN: Code duplicated, block: B:58:0x013b  */
    /* JADX WARN: Code duplicated, block: B:77:0x0143 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:78:0x0143 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:79:? A[LOOP:0: B:67:0x0088->B:79:?, LOOP_END, SYNTHETIC] */
    public void a(int i, int i2, int i3, int i4, boolean z, d.e eVar, p pVar) throws Throwable {
        if (this.g != null) {
            throw new IllegalStateException("already connected");
        }
        List<k> listB = this.f4388c.a().b();
        b bVar = new b(listB);
        if (this.f4388c.a().j() == null) {
            if (!listB.contains(k.h)) {
                throw new e(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
            }
            String strG = this.f4388c.a().k().g();
            if (!d.h0.k.f.d().b(strG)) {
                throw new e(new UnknownServiceException("CLEARTEXT communication to " + strG + " not permitted by network security policy"));
            }
        } else if (this.f4388c.a().e().contains(y.H2_PRIOR_KNOWLEDGE)) {
            throw new e(new UnknownServiceException("H2_PRIOR_KNOWLEDGE cannot be used with HTTPS"));
        }
        e eVar2 = null;
        while (true) {
            try {
                if (this.f4388c.c()) {
                    a(i, i2, i3, eVar, pVar);
                    if (this.f4389d == null) {
                        break;
                    }
                } else {
                    try {
                        a(i, i2, eVar, pVar);
                    } catch (IOException e2) {
                        e = e2;
                        d.h0.c.a(this.f4390e);
                        d.h0.c.a(this.f4389d);
                        this.f4390e = null;
                        this.f4389d = null;
                        this.i = null;
                        this.j = null;
                        this.f4391f = null;
                        this.g = null;
                        this.h = null;
                        pVar.a(eVar, this.f4388c.d(), this.f4388c.b(), null, e);
                        if (eVar2 == null) {
                            eVar2 = new e(e);
                        } else {
                            eVar2.a(e);
                        }
                        if (z) {
                            throw eVar2;
                        }
                        if (bVar.a(e)) {
                            throw eVar2;
                        }
                    }
                }
                try {
                    a(bVar, i4, eVar, pVar);
                    pVar.a(eVar, this.f4388c.d(), this.f4388c.b(), this.g);
                    break;
                } catch (IOException e3) {
                    e = e3;
                    d.h0.c.a(this.f4390e);
                    d.h0.c.a(this.f4389d);
                    this.f4390e = null;
                    this.f4389d = null;
                    this.i = null;
                    this.j = null;
                    this.f4391f = null;
                    this.g = null;
                    this.h = null;
                    pVar.a(eVar, this.f4388c.d(), this.f4388c.b(), null, e);
                    if (eVar2 == null) {
                        eVar2 = new e(e);
                    } else {
                        eVar2.a(e);
                    }
                    if (z) {
                        throw eVar2;
                    }
                    if (bVar.a(e)) {
                        throw eVar2;
                    }
                }
            } catch (IOException e4) {
                e = e4;
            }
        }
        if (this.f4388c.c() && this.f4389d == null) {
            throw new e(new ProtocolException("Too many tunnel connections attempted: 21"));
        }
        if (this.h != null) {
            synchronized (this.f4387b) {
                this.m = this.h.b();
            }
        }
    }

    public void b() {
        d.h0.c.a(this.f4389d);
    }

    public r c() {
        return this.f4391f;
    }

    public boolean d() {
        return this.h != null;
    }

    public e0 e() {
        return this.f4388c;
    }

    public Socket f() {
        return this.f4390e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Connection{");
        sb.append(this.f4388c.a().k().g());
        sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
        sb.append(this.f4388c.a().k().k());
        sb.append(", proxy=");
        sb.append(this.f4388c.b());
        sb.append(" hostAddress=");
        sb.append(this.f4388c.d());
        sb.append(" cipherSuite=");
        r rVar = this.f4391f;
        sb.append(rVar != null ? rVar.a() : "none");
        sb.append(" protocol=");
        sb.append(this.g);
        sb.append('}');
        return sb.toString();
    }

    private void a(int i, int i2, int i3, d.e eVar, p pVar) throws IOException {
        a0 a0VarG = g();
        t tVarG = a0VarG.g();
        for (int i4 = 0; i4 < 21; i4++) {
            a(i, i2, eVar, pVar);
            a0VarG = a(i2, i3, a0VarG, tVarG);
            if (a0VarG == null) {
                return;
            }
            d.h0.c.a(this.f4389d);
            this.f4389d = null;
            this.j = null;
            this.i = null;
            pVar.a(eVar, this.f4388c.d(), this.f4388c.b(), null);
        }
    }

    private void a(int i, int i2, d.e eVar, p pVar) throws IOException {
        Socket socketCreateSocket;
        Proxy proxyB = this.f4388c.b();
        d.a aVarA = this.f4388c.a();
        if (proxyB.type() != Proxy.Type.DIRECT && proxyB.type() != Proxy.Type.HTTP) {
            socketCreateSocket = new Socket(proxyB);
        } else {
            socketCreateSocket = aVarA.i().createSocket();
        }
        this.f4389d = socketCreateSocket;
        pVar.a(eVar, this.f4388c.d(), proxyB);
        this.f4389d.setSoTimeout(i2);
        try {
            d.h0.k.f.d().a(this.f4389d, this.f4388c.d(), i);
            try {
                this.i = l.a(l.b(this.f4389d));
                this.j = l.a(l.a(this.f4389d));
            } catch (NullPointerException e2) {
                if ("throw with null exception".equals(e2.getMessage())) {
                    throw new IOException(e2);
                }
            }
        } catch (ConnectException e3) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.f4388c.d());
            connectException.initCause(e3);
            throw connectException;
        }
    }

    private void a(b bVar, int i, d.e eVar, p pVar) throws Throwable {
        if (this.f4388c.a().j() == null) {
            if (this.f4388c.a().e().contains(y.H2_PRIOR_KNOWLEDGE)) {
                this.f4390e = this.f4389d;
                this.g = y.H2_PRIOR_KNOWLEDGE;
                a(i);
                return;
            } else {
                this.f4390e = this.f4389d;
                this.g = y.HTTP_1_1;
                return;
            }
        }
        pVar.g(eVar);
        a(bVar);
        pVar.a(eVar, this.f4391f);
        if (this.g == y.HTTP_2) {
            a(i);
        }
    }

    private void a(int i) throws SocketException {
        this.f4390e.setSoTimeout(0);
        d.h0.i.g.C0100g c0100g = new d.h0.i.g.C0100g(true);
        c0100g.a(this.f4390e, this.f4388c.a().k().g(), this.i, this.j);
        c0100g.a(this);
        c0100g.a(i);
        this.h = c0100g.a();
        this.h.m();
    }

    private void a(b bVar) throws Throwable {
        SSLSocket sSLSocket;
        y yVarA;
        d.a aVarA = this.f4388c.a();
        try {
            try {
                sSLSocket = (SSLSocket) aVarA.j().createSocket(this.f4389d, aVarA.k().g(), aVarA.k().k(), true);
                try {
                    k kVarA = bVar.a(sSLSocket);
                    if (kVarA.c()) {
                        d.h0.k.f.d().a(sSLSocket, aVarA.k().g(), aVarA.e());
                    }
                    sSLSocket.startHandshake();
                    SSLSession session = sSLSocket.getSession();
                    r rVarA = r.a(session);
                    if (aVarA.d().verify(aVarA.k().g(), session)) {
                        aVarA.a().a(aVarA.k().g(), rVarA.c());
                        String strB = kVarA.c() ? d.h0.k.f.d().b(sSLSocket) : null;
                        this.f4390e = sSLSocket;
                        this.i = l.a(l.b(this.f4390e));
                        this.j = l.a(l.a(this.f4390e));
                        this.f4391f = rVarA;
                        if (strB != null) {
                            yVarA = y.a(strB);
                        } else {
                            yVarA = y.HTTP_1_1;
                        }
                        this.g = yVarA;
                        if (sSLSocket != null) {
                            d.h0.k.f.d().a(sSLSocket);
                            return;
                        }
                        return;
                    }
                    X509Certificate x509Certificate = (X509Certificate) rVarA.c().get(0);
                    throw new SSLPeerUnverifiedException("Hostname " + aVarA.k().g() + " not verified:\n    certificate: " + d.g.a((Certificate) x509Certificate) + "\n    DN: " + x509Certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + d.h0.l.d.a(x509Certificate));
                } catch (AssertionError e2) {
                    e = e2;
                    if (!d.h0.c.a(e)) {
                        throw e;
                    }
                    throw new IOException(e);
                } catch (Throwable th) {
                    th = th;
                    if (sSLSocket != null) {
                        d.h0.k.f.d().a(sSLSocket);
                    }
                    d.h0.c.a((Socket) sSLSocket);
                    throw th;
                }
            } catch (AssertionError e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
            sSLSocket = null;
        }
    }

    private a0 a(int i, int i2, a0 a0Var, t tVar) throws IOException {
        String str = "CONNECT " + d.h0.c.a(tVar, true) + " HTTP/1.1";
        while (true) {
            d.h0.h.a aVar = new d.h0.h.a(null, null, this.i, this.j);
            this.i.timeout().a(i, TimeUnit.MILLISECONDS);
            this.j.timeout().a(i2, TimeUnit.MILLISECONDS);
            aVar.a(a0Var.c(), str);
            aVar.a();
            c0.a aVarA = aVar.a(false);
            aVarA.a(a0Var);
            c0 c0VarA = aVarA.a();
            long jA = d.h0.g.e.a(c0VarA);
            if (jA == -1) {
                jA = 0;
            }
            s sVarB = aVar.b(jA);
            d.h0.c.b(sVarB, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            sVarB.close();
            int iM = c0VarA.m();
            if (iM == 200) {
                if (this.i.c().j() && this.j.c().j()) {
                    return null;
                }
                throw new IOException("TLS tunnel buffered too many bytes!");
            }
            if (iM == 407) {
                a0 a0VarA = this.f4388c.a().g().a(this.f4388c, c0VarA);
                if (a0VarA != null) {
                    if ("close".equalsIgnoreCase(c0VarA.a("Connection"))) {
                        return a0VarA;
                    }
                    a0Var = a0VarA;
                } else {
                    throw new IOException("Failed to authenticate with proxy");
                }
            } else {
                throw new IOException("Unexpected response code for CONNECT: " + c0VarA.m());
            }
        }
    }

    public boolean a(d.a aVar, e0 e0Var) {
        if (this.n.size() >= this.m || this.k || !d.h0.a.f4335a.a(this.f4388c.a(), aVar)) {
            return false;
        }
        if (aVar.k().g().equals(e().a().k().g())) {
            return true;
        }
        if (this.h == null || e0Var == null || e0Var.b().type() != Proxy.Type.DIRECT || this.f4388c.b().type() != Proxy.Type.DIRECT || !this.f4388c.d().equals(e0Var.d()) || e0Var.a().d() != d.h0.l.d.f4610a || !a(aVar.k())) {
            return false;
        }
        try {
            aVar.a().a(aVar.k().g(), c().c());
            return true;
        } catch (SSLPeerUnverifiedException unused) {
            return false;
        }
    }

    public boolean a(t tVar) {
        if (tVar.k() != this.f4388c.a().k().k()) {
            return false;
        }
        if (tVar.g().equals(this.f4388c.a().k().g())) {
            return true;
        }
        return this.f4391f != null && d.h0.l.d.f4610a.a(tVar.g(), (X509Certificate) this.f4391f.c().get(0));
    }

    public d.h0.g.c a(x xVar, u.a aVar, g gVar) throws SocketException {
        d.h0.i.g gVar2 = this.h;
        if (gVar2 != null) {
            return new d.h0.i.f(xVar, aVar, gVar, gVar2);
        }
        this.f4390e.setSoTimeout(aVar.c());
        this.i.timeout().a(aVar.c(), TimeUnit.MILLISECONDS);
        this.j.timeout().a(aVar.a(), TimeUnit.MILLISECONDS);
        return new d.h0.h.a(xVar, gVar, this.i, this.j);
    }

    public boolean a(boolean z) {
        if (this.f4390e.isClosed() || this.f4390e.isInputShutdown() || this.f4390e.isOutputShutdown()) {
            return false;
        }
        d.h0.i.g gVar = this.h;
        if (gVar != null) {
            return !gVar.a();
        }
        if (z) {
            try {
                int soTimeout = this.f4390e.getSoTimeout();
                try {
                    this.f4390e.setSoTimeout(1);
                    if (this.i.j()) {
                        this.f4390e.setSoTimeout(soTimeout);
                        return false;
                    }
                    this.f4390e.setSoTimeout(soTimeout);
                    return true;
                } catch (Throwable th) {
                    this.f4390e.setSoTimeout(soTimeout);
                    throw th;
                }
            } catch (SocketTimeoutException unused) {
            } catch (IOException unused2) {
                return false;
            }
        }
        return true;
    }

    @Override // d.h0.i.g.h
    public void a(d.h0.i.i iVar) {
        iVar.a(d.h0.i.b.REFUSED_STREAM);
    }

    @Override // d.h0.i.g.h
    public void a(d.h0.i.g gVar) {
        synchronized (this.f4387b) {
            this.m = gVar.b();
        }
    }

    @Override // d.i
    public y a() {
        return this.g;
    }
}
