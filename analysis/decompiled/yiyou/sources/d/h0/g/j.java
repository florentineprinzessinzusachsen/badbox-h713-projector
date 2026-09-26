package d.h0.g;

import d.a0;
import d.b0;
import d.c0;
import d.d0;
import d.e0;
import d.p;
import d.t;
import d.u;
import d.x;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: compiled from: RetryAndFollowUpInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class j implements u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final x f4425a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f4426b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile d.h0.f.g f4427c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Object f4428d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private volatile boolean f4429e;

    public j(x xVar, boolean z) {
        this.f4425a = xVar;
        this.f4426b = z;
    }

    public void a() {
        this.f4429e = true;
        d.h0.f.g gVar = this.f4427c;
        if (gVar != null) {
            gVar.a();
        }
    }

    public boolean b() {
        return this.f4429e;
    }

    @Override // d.u
    public c0 intercept(u.a aVar) throws IOException {
        a0 a0VarRequest = aVar.request();
        g gVar = (g) aVar;
        d.e eVarE = gVar.e();
        p pVarF = gVar.f();
        d.h0.f.g gVar2 = new d.h0.f.g(this.f4425a.d(), a(a0VarRequest.g()), eVarE, pVarF, this.f4428d);
        this.f4427c = gVar2;
        c0 c0Var = null;
        int i = 0;
        while (!this.f4429e) {
            try {
                try {
                    c0 c0VarA = gVar.a(a0VarRequest, gVar2, null, null);
                    if (c0Var != null) {
                        c0.a aVarS = c0VarA.s();
                        c0.a aVarS2 = c0Var.s();
                        aVarS2.a((d0) null);
                        aVarS.c(aVarS2.a());
                        c0VarA = aVarS.a();
                    }
                    try {
                        a0 a0VarA = a(c0VarA, gVar2.g());
                        if (a0VarA == null) {
                            if (!this.f4426b) {
                                gVar2.f();
                            }
                            return c0VarA;
                        }
                        d.h0.c.a(c0VarA.a());
                        int i2 = i + 1;
                        if (i2 > 20) {
                            gVar2.f();
                            throw new ProtocolException("Too many follow-up requests: " + i2);
                        }
                        a0VarA.a();
                        if (!a(c0VarA, a0VarA.g())) {
                            gVar2.f();
                            gVar2 = new d.h0.f.g(this.f4425a.d(), a(a0VarA.g()), eVarE, pVarF, this.f4428d);
                            this.f4427c = gVar2;
                        } else if (gVar2.b() != null) {
                            throw new IllegalStateException("Closing the body of " + c0VarA + " didn't close its backing stream. Bad interceptor?");
                        }
                        c0Var = c0VarA;
                        a0VarRequest = a0VarA;
                        i = i2;
                    } catch (IOException e2) {
                        gVar2.f();
                        throw e2;
                    }
                } catch (d.h0.f.e e3) {
                    if (!a(e3.b(), gVar2, false, a0VarRequest)) {
                        throw e3.a();
                    }
                } catch (IOException e4) {
                    if (!a(e4, gVar2, !(e4 instanceof d.h0.i.a), a0VarRequest)) {
                        throw e4;
                    }
                }
            } catch (Throwable th) {
                gVar2.a((IOException) null);
                gVar2.f();
                throw th;
            }
        }
        gVar2.f();
        throw new IOException("Canceled");
    }

    public void a(Object obj) {
        this.f4428d = obj;
    }

    private d.a a(t tVar) {
        SSLSocketFactory sSLSocketFactory;
        HostnameVerifier hostnameVerifierL;
        d.g gVarB;
        if (tVar.h()) {
            SSLSocketFactory sSLSocketFactoryX = this.f4425a.x();
            hostnameVerifierL = this.f4425a.l();
            sSLSocketFactory = sSLSocketFactoryX;
            gVarB = this.f4425a.b();
        } else {
            sSLSocketFactory = null;
            hostnameVerifierL = null;
            gVarB = null;
        }
        return new d.a(tVar.g(), tVar.k(), this.f4425a.h(), this.f4425a.w(), sSLSocketFactory, hostnameVerifierL, gVarB, this.f4425a.s(), this.f4425a.r(), this.f4425a.q(), this.f4425a.e(), this.f4425a.t());
    }

    private boolean a(IOException iOException, d.h0.f.g gVar, boolean z, a0 a0Var) {
        gVar.a(iOException);
        if (!this.f4425a.v()) {
            return false;
        }
        if (z) {
            a0Var.a();
        }
        return a(iOException, z) && gVar.d();
    }

    private boolean a(IOException iOException, boolean z) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        if (iOException instanceof InterruptedIOException) {
            return (iOException instanceof SocketTimeoutException) && !z;
        }
        return (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) ? false : true;
    }

    private a0 a(c0 c0Var, e0 e0Var) throws ProtocolException {
        String strA;
        t tVarB;
        Proxy proxyR;
        if (c0Var != null) {
            int iM = c0Var.m();
            String strE = c0Var.w().e();
            if (iM == 307 || iM == 308) {
                if (!strE.equals("GET") && !strE.equals("HEAD")) {
                    return null;
                }
            } else {
                if (iM == 401) {
                    return this.f4425a.a().a(e0Var, c0Var);
                }
                if (iM == 503) {
                    if ((c0Var.t() == null || c0Var.t().m() != 503) && a(c0Var, Integer.MAX_VALUE) == 0) {
                        return c0Var.w();
                    }
                    return null;
                }
                if (iM == 407) {
                    if (e0Var != null) {
                        proxyR = e0Var.b();
                    } else {
                        proxyR = this.f4425a.r();
                    }
                    if (proxyR.type() == Proxy.Type.HTTP) {
                        return this.f4425a.s().a(e0Var, c0Var);
                    }
                    throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                }
                if (iM == 408) {
                    if (!this.f4425a.v()) {
                        return null;
                    }
                    c0Var.w().a();
                    if ((c0Var.t() == null || c0Var.t().m() != 408) && a(c0Var, 0) <= 0) {
                        return c0Var.w();
                    }
                    return null;
                }
                switch (iM) {
                    case 300:
                    case 301:
                    case 302:
                    case 303:
                        break;
                    default:
                        return null;
                }
            }
            if (!this.f4425a.j() || (strA = c0Var.a("Location")) == null || (tVarB = c0Var.w().g().b(strA)) == null) {
                return null;
            }
            if (!tVarB.o().equals(c0Var.w().g().o()) && !this.f4425a.k()) {
                return null;
            }
            a0.a aVarF = c0Var.w().f();
            if (f.b(strE)) {
                boolean zD = f.d(strE);
                if (f.c(strE)) {
                    aVarF.a("GET", (b0) null);
                } else {
                    aVarF.a(strE, zD ? c0Var.w().a() : null);
                }
                if (!zD) {
                    aVarF.a("Transfer-Encoding");
                    aVarF.a("Content-Length");
                    aVarF.a("Content-Type");
                }
            }
            if (!a(c0Var, tVarB)) {
                aVarF.a("Authorization");
            }
            aVarF.a(tVarB);
            return aVarF.a();
        }
        throw new IllegalStateException();
    }

    private int a(c0 c0Var, int i) {
        String strA = c0Var.a("Retry-After");
        if (strA == null) {
            return i;
        }
        if (strA.matches("\\d+")) {
            return Integer.valueOf(strA).intValue();
        }
        return Integer.MAX_VALUE;
    }

    private boolean a(c0 c0Var, t tVar) {
        t tVarG = c0Var.w().g();
        return tVarG.g().equals(tVar.g()) && tVarG.k() == tVar.k() && tVarG.o().equals(tVar.o());
    }
}
