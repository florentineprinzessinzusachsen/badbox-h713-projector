package f3;

import a3.a0;
import a3.b0;
import a3.c0;
import a3.d0;
import a3.f0;
import a3.g0;
import a3.q;
import a3.r;
import a3.t;
import a3.u;
import a3.v;
import a3.x;
import a3.z;
import d0.l0;
import e3.l;
import e3.p;
import e3.s;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a implements u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f892a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f893b;

    public a(a3.b bVar) {
        j2.i.e(bVar, "cookieJar");
        this.f893b = bVar;
    }

    public static int d(d0 d0Var, int i4) {
        String strA = d0Var.f108i.a("Retry-After");
        if (strA == null) {
            strA = null;
        }
        if (strA == null) {
            return i4;
        }
        Pattern patternCompile = Pattern.compile("\\d+");
        j2.i.d(patternCompile, "compile(...)");
        if (!patternCompile.matcher(strA).matches()) {
            return Integer.MAX_VALUE;
        }
        Integer numValueOf = Integer.valueOf(strA);
        j2.i.d(numValueOf, "valueOf(...)");
        return numValueOf.intValue();
    }

    @Override // a3.u
    public final d0 a(i iVar) throws Throwable {
        f0 f0Var;
        p pVar;
        List list;
        SSLSocketFactory sSLSocketFactory;
        HostnameVerifier hostnameVerifier;
        a3.e eVar;
        switch (this.f892a) {
            case 0:
                a3.b bVar = (a3.b) this.f893b;
                a0 a0Var = iVar.f901e;
                r rVar = (r) a0Var.f64d;
                z zVarC = a0Var.c();
                t tVar = (t) a0Var.f63c;
                b0 b0Var = (b0) a0Var.f65e;
                if (b0Var != null) {
                    v vVarB = b0Var.b();
                    if (vVarB != null) {
                        zVarC.b("Content-Type", vVarB.f218a);
                    }
                    long jA = b0Var.a();
                    if (jA != -1) {
                        zVarC.b("Content-Length", String.valueOf(jA));
                        ((q) zVarC.f282c).b("Transfer-Encoding");
                    } else {
                        zVarC.b("Transfer-Encoding", "chunked");
                        ((q) zVarC.f282c).b("Content-Length");
                    }
                }
                boolean z3 = false;
                if (rVar.a("Host") == null) {
                    zVarC.b("Host", b3.g.i(tVar, false));
                }
                if (rVar.a("Connection") == null) {
                    zVarC.b("Connection", "Keep-Alive");
                }
                if (rVar.a("Accept-Encoding") == null && rVar.a("Range") == null) {
                    zVarC.b("Accept-Encoding", "gzip");
                    z3 = true;
                }
                bVar.getClass();
                j2.i.e(tVar, "url");
                if (rVar.a("User-Agent") == null) {
                    zVarC.b("User-Agent", "okhttp/5.3.2");
                }
                a0 a0Var2 = new a0(zVarC);
                d0 d0VarB = iVar.b(a0Var2);
                r rVar2 = d0VarB.f108i;
                h.b(bVar, (t) a0Var2.f63c, rVar2);
                c0 c0VarB = d0VarB.b();
                c0VarB.f88a = a0Var2;
                if (z3) {
                    String strA = rVar2.a("Content-Encoding");
                    if (strA == null) {
                        strA = null;
                    }
                    if ("gzip".equalsIgnoreCase(strA) && h.a(d0VarB) && (f0Var = d0VarB.f109j) != null) {
                        q3.j jVar = new q3.j(f0Var.k());
                        q qVarC = rVar2.c();
                        qVarC.b("Content-Encoding");
                        qVarC.b("Content-Length");
                        c0VarB.f93f = qVarC.a().c();
                        String strA2 = rVar2.a("Content-Type");
                        if (strA2 == null) {
                            strA2 = null;
                        }
                        c0VarB.f94g = new j(strA2, -1L, l0.f(jVar));
                    }
                }
                return c0VarB.a();
            default:
                a0 a0Var3 = iVar.f901e;
                p pVar2 = iVar.f897a;
                List list2 = v1.p.f2517d;
                d0 d0Var = null;
                int i4 = 0;
                a0 a0VarB = a0Var3;
                while (true) {
                    boolean z4 = true;
                    List list3 = list2;
                    while (true) {
                        if (pVar2.f776m != null) {
                            throw new IllegalStateException("Check failed.");
                        }
                        synchronized (pVar2) {
                            try {
                                try {
                                    if (pVar2.f778o) {
                                        throw new IllegalStateException("cannot make a new request because the previous response is still open: please call response.close()");
                                    }
                                    if (pVar2.f777n || pVar2.f780q || pVar2.f779p) {
                                        throw new IllegalStateException("Check failed.");
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                pVar = pVar2;
                            }
                            throw th;
                        }
                        if (z4) {
                            x xVar = pVar2.f767d;
                            d3.e eVar2 = xVar.f269z;
                            List list4 = list3;
                            e3.r rVar3 = pVar2.f769f;
                            int i5 = xVar.f266w;
                            int i6 = xVar.f267x;
                            int i7 = iVar.f902f;
                            int i8 = iVar.f903g;
                            boolean z5 = xVar.f248e;
                            boolean z6 = xVar.f249f;
                            t tVar2 = (t) a0VarB.f63c;
                            j2.i.e(tVar2, "url");
                            if (j2.i.a(tVar2.f208a, "https")) {
                                SSLSocketFactory sSLSocketFactory2 = xVar.f258o;
                                if (sSLSocketFactory2 == null) {
                                    throw new IllegalStateException("CLEARTEXT-only client");
                                }
                                HostnameVerifier hostnameVerifier2 = xVar.f262s;
                                eVar = xVar.f263t;
                                sSLSocketFactory = sSLSocketFactory2;
                                hostnameVerifier = hostnameVerifier2;
                            } else {
                                sSLSocketFactory = null;
                                hostnameVerifier = null;
                                eVar = null;
                            }
                            list = list4;
                            p pVar3 = pVar2;
                            a0 a0Var4 = a0VarB;
                            s sVar = new s(eVar2, rVar3, i5, i6, i7, i8, z5, z6, new a3.a(tVar2.f211d, tVar2.f212e, xVar.f254k, xVar.f257n, sSLSocketFactory, hostnameVerifier, eVar, xVar.f256m, xVar.f261r, xVar.f260q, xVar.f255l), pVar2.f767d.f268y, pVar3, a0Var4);
                            pVar = pVar3;
                            a0VarB = a0Var4;
                            x xVar2 = pVar.f767d;
                            pVar.f773j = xVar2.f249f ? new l(sVar, xVar2.f269z) : new a3.h(5, sVar);
                        } else {
                            pVar = pVar2;
                            list = list3;
                        }
                        try {
                            if (pVar.f782s) {
                                throw new IOException("Canceled");
                            }
                            try {
                            } catch (IOException e4) {
                                if (!c(e4, pVar, a0VarB)) {
                                    byte[] bArr = b3.d.f343a;
                                    j2.i.e(list, "suppressed");
                                    Iterator it = list.iterator();
                                    while (it.hasNext()) {
                                        l3.h.a(e4, (Exception) it.next());
                                    }
                                    throw e4;
                                }
                                ArrayList arrayList = new ArrayList(list.size() + 1);
                                arrayList.addAll(list);
                                arrayList.add(e4);
                                pVar.g(true);
                                pVar2 = pVar;
                                z4 = false;
                                list3 = arrayList;
                            }
                        } catch (Throwable th3) {
                            pVar.g(true);
                            throw th3;
                        }
                        break;
                    }
                    c0 c0VarB2 = iVar.b(a0VarB).b();
                    c0VarB2.f88a = a0VarB;
                    c0VarB2.f98k = d0Var != null ? l0.L(d0Var) : null;
                    d0 d0VarA = c0VarB2.a();
                    a0VarB = b(d0VarA, pVar.f776m);
                    if (a0VarB == null) {
                        pVar.g(false);
                        return d0VarA;
                    }
                    b3.d.b(d0VarA.f109j);
                    int i9 = i4 + 1;
                    if (i9 > 20) {
                        throw new ProtocolException("Too many follow-up requests: " + i9);
                    }
                    pVar.g(true);
                    d0Var = d0VarA;
                    list2 = list;
                    pVar2 = pVar;
                    i4 = i9;
                }
                break;
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x013c  */
    /* JADX WARN: Code duplicated, block: B:103:0x0146  */
    /* JADX WARN: Code duplicated, block: B:106:0x016b  */
    /* JADX WARN: Code duplicated, block: B:64:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:67:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:69:0x00de  */
    /* JADX WARN: Code duplicated, block: B:73:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:78:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:79:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:89:0x011d  */
    /* JADX WARN: Code duplicated, block: B:93:0x0129  */
    /* JADX WARN: Code duplicated, block: B:99:0x013a A[DONT_INVERT] */
    public a0 b(d0 d0Var, e3.h hVar) throws ProtocolException {
        x xVar;
        String strA;
        a0 a0Var;
        a3.s sVar;
        t tVarA;
        z zVarC;
        boolean z3;
        d0 d0Var2;
        g0 g0Var = hVar != null ? hVar.c().f786c : null;
        int i4 = d0Var.f106g;
        String str = d0Var.f103d.f62b;
        if (i4 == 307 || i4 == 308) {
            xVar = (x) this.f893b;
            if (xVar.f251h) {
                strA = d0Var.f108i.a("Location");
                if (strA == null) {
                    strA = null;
                }
                a0Var = d0Var.f103d;
                if (strA != null) {
                    t tVar = (t) a0Var.f63c;
                    tVar.getClass();
                    try {
                        sVar = new a3.s();
                        sVar.c(tVar, strA);
                    } catch (IllegalArgumentException unused) {
                        sVar = null;
                    }
                    if (sVar != null) {
                        tVarA = sVar.a();
                    } else {
                        tVarA = null;
                    }
                    if (tVarA != null && (j2.i.a(tVarA.f208a, ((t) a0Var.f63c).f208a) || xVar.f252i)) {
                        zVarC = a0Var.c();
                        if (a.a.y(str)) {
                            int i5 = d0Var.f106g;
                            z3 = !str.equals("PROPFIND") || i5 == 308 || i5 == 307;
                            if (!str.equals("PROPFIND") || i5 == 308 || i5 == 307) {
                                zVarC.c(str, z3 ? (b0) a0Var.f65e : null);
                            } else {
                                zVarC.c("GET", null);
                            }
                            if (!z3) {
                                ((q) zVarC.f282c).b("Transfer-Encoding");
                                ((q) zVarC.f282c).b("Content-Length");
                                ((q) zVarC.f282c).b("Content-Type");
                            }
                        }
                        if (!b3.g.a((t) a0Var.f63c, tVarA)) {
                            ((q) zVarC.f282c).b("Authorization");
                        }
                        zVarC.f281b = tVarA;
                        return new a0(zVarC);
                    }
                }
            }
        } else {
            if (i4 == 401) {
                ((x) this.f893b).f250g.getClass();
                return null;
            }
            if (i4 != 421) {
                if (i4 == 503) {
                    d0 d0Var3 = d0Var.f113n;
                    if ((d0Var3 == null || d0Var3.f106g != 503) && d(d0Var, Integer.MAX_VALUE) == 0) {
                        return d0Var.f103d;
                    }
                } else {
                    if (i4 == 407) {
                        j2.i.b(g0Var);
                        if (g0Var.f146b.type() != Proxy.Type.HTTP) {
                            throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                        }
                        ((x) this.f893b).f256m.getClass();
                        return null;
                    }
                    if (i4 != 408) {
                        switch (i4) {
                            case 300:
                            case 301:
                            case 302:
                            case 303:
                                xVar = (x) this.f893b;
                                if (xVar.f251h) {
                                    strA = d0Var.f108i.a("Location");
                                    if (strA == null) {
                                        strA = null;
                                    }
                                    a0Var = d0Var.f103d;
                                    if (strA != null) {
                                        t tVar2 = (t) a0Var.f63c;
                                        tVar2.getClass();
                                        sVar = new a3.s();
                                        sVar.c(tVar2, strA);
                                        if (sVar != null) {
                                            tVarA = sVar.a();
                                        } else {
                                            tVarA = null;
                                        }
                                        if (tVarA != null) {
                                            zVarC = a0Var.c();
                                            if (a.a.y(str)) {
                                                int i6 = d0Var.f106g;
                                                if (str.equals("PROPFIND")) {
                                                }
                                                if (str.equals("PROPFIND")) {
                                                    zVarC.c(str, z3 ? (b0) a0Var.f65e : null);
                                                } else {
                                                    zVarC.c(str, z3 ? (b0) a0Var.f65e : null);
                                                }
                                                if (!z3) {
                                                    ((q) zVarC.f282c).b("Transfer-Encoding");
                                                    ((q) zVarC.f282c).b("Content-Length");
                                                    ((q) zVarC.f282c).b("Content-Type");
                                                }
                                            }
                                            if (!b3.g.a((t) a0Var.f63c, tVarA)) {
                                                ((q) zVarC.f282c).b("Authorization");
                                            }
                                            zVarC.f281b = tVarA;
                                            return new a0(zVarC);
                                        }
                                    }
                                }
                            default:
                                return null;
                        }
                    } else if (((x) this.f893b).f248e && (((d0Var2 = d0Var.f113n) == null || d0Var2.f106g != 408) && d(d0Var, 0) <= 0)) {
                        return d0Var.f103d;
                    }
                }
            } else if (hVar != null && !j2.i.a(((e3.i) hVar.f751f).d().f813i.f58h.f211d, ((g) hVar.f752g).i().c().f145a.f58h.f211d)) {
                e3.q qVarC = hVar.c();
                synchronized (qVarC) {
                    qVarC.f794k = true;
                }
                return d0Var.f103d;
            }
        }
        return null;
    }

    public boolean c(IOException iOException, p pVar, a0 a0Var) {
        boolean z3 = iOException instanceof h3.a;
        if (!((x) this.f893b).f248e) {
            return false;
        }
        if ((!z3 && (iOException instanceof FileNotFoundException)) || (iOException instanceof ProtocolException)) {
            return false;
        }
        if (iOException instanceof InterruptedIOException) {
            if (!(iOException instanceof SocketTimeoutException) || !z3) {
                return false;
            }
        } else if (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        }
        e3.h hVar = pVar.f783t;
        if (hVar == null || !hVar.f749d) {
            return false;
        }
        e3.i iVar = pVar.f773j;
        j2.i.b(iVar);
        s sVarD = iVar.d();
        e3.h hVar2 = pVar.f783t;
        return sVarD.a(hVar2 != null ? hVar2.c() : null);
    }

    public a(x xVar) {
        this.f893b = xVar;
    }
}
