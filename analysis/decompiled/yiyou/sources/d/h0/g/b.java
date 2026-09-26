package d.h0.g;

import d.a0;
import d.c0;
import d.u;
import e.l;
import e.r;
import java.net.ProtocolException;

/* JADX INFO: compiled from: CallServerInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f4411a;

    /* JADX INFO: compiled from: CallServerInterceptor.java */
    static final class a extends e.g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        long f4412b;

        a(r rVar) {
            super(rVar);
        }

        @Override // e.g, e.r
        public void a(e.c cVar, long j) {
            super.a(cVar, j);
            this.f4412b += j;
        }
    }

    public b(boolean z) {
        this.f4411a = z;
    }

    @Override // d.u
    public c0 intercept(u.a aVar) throws ProtocolException {
        c0 c0VarA;
        g gVar = (g) aVar;
        c cVarG = gVar.g();
        d.h0.f.g gVarH = gVar.h();
        d.h0.f.c cVar = (d.h0.f.c) gVar.d();
        a0 a0VarRequest = gVar.request();
        long jCurrentTimeMillis = System.currentTimeMillis();
        gVar.f().d(gVar.e());
        cVarG.a(a0VarRequest);
        gVar.f().a(gVar.e(), a0VarRequest);
        c0.a aVarA = null;
        if (f.b(a0VarRequest.e()) && a0VarRequest.a() != null) {
            if ("100-continue".equalsIgnoreCase(a0VarRequest.a("Expect"))) {
                cVarG.b();
                gVar.f().f(gVar.e());
                aVarA = cVarG.a(true);
            }
            if (aVarA == null) {
                gVar.f().c(gVar.e());
                a aVar2 = new a(cVarG.a(a0VarRequest, a0VarRequest.a().contentLength()));
                e.d dVarA = l.a(aVar2);
                a0VarRequest.a().writeTo(dVarA);
                dVarA.close();
                gVar.f().a(gVar.e(), aVar2.f4412b);
            } else if (!cVar.d()) {
                gVarH.e();
            }
        }
        cVarG.a();
        if (aVarA == null) {
            gVar.f().f(gVar.e());
            aVarA = cVarG.a(false);
        }
        aVarA.a(a0VarRequest);
        aVarA.a(gVarH.c().c());
        aVarA.b(jCurrentTimeMillis);
        aVarA.a(System.currentTimeMillis());
        c0 c0VarA2 = aVarA.a();
        int iM = c0VarA2.m();
        if (iM == 100) {
            c0.a aVarA2 = cVarG.a(false);
            aVarA2.a(a0VarRequest);
            aVarA2.a(gVarH.c().c());
            aVarA2.b(jCurrentTimeMillis);
            aVarA2.a(System.currentTimeMillis());
            c0VarA2 = aVarA2.a();
            iM = c0VarA2.m();
        }
        gVar.f().a(gVar.e(), c0VarA2);
        if (this.f4411a && iM == 101) {
            c0.a aVarS = c0VarA2.s();
            aVarS.a(d.h0.c.f4339c);
            c0VarA = aVarS.a();
        } else {
            c0.a aVarS2 = c0VarA2.s();
            aVarS2.a(cVarG.a(c0VarA2));
            c0VarA = aVarS2.a();
        }
        if ("close".equalsIgnoreCase(c0VarA.w().a("Connection")) || "close".equalsIgnoreCase(c0VarA.a("Connection"))) {
            gVarH.e();
        }
        if ((iM != 204 && iM != 205) || c0VarA.a().contentLength() <= 0) {
            return c0VarA;
        }
        throw new ProtocolException("HTTP " + iM + " had non-zero Content-Length: " + c0VarA.a().contentLength());
    }
}
