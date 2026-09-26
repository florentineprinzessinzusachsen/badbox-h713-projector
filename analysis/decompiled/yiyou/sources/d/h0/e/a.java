package d.h0.e;

import d.a0;
import d.c0;
import d.d0;
import d.h0.g.h;
import d.u;
import d.y;
import e.l;
import e.r;
import e.s;
import e.t;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: CacheInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final f f4345a;

    /* JADX INFO: renamed from: d.h0.e.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CacheInterceptor.java */
    class C0097a implements s {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f4346a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ e.e f4347b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ b f4348c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ e.d f4349d;

        C0097a(a aVar, e.e eVar, b bVar, e.d dVar) {
            this.f4347b = eVar;
            this.f4348c = bVar;
            this.f4349d = dVar;
        }

        @Override // e.s, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (!this.f4346a && !d.h0.c.a(this, 100, TimeUnit.MILLISECONDS)) {
                this.f4346a = true;
                this.f4348c.a();
            }
            this.f4347b.close();
        }

        @Override // e.s
        public long read(e.c cVar, long j) throws IOException {
            try {
                long j2 = this.f4347b.read(cVar, j);
                if (j2 != -1) {
                    cVar.a(this.f4349d.c(), cVar.q() - j2, j2);
                    this.f4349d.e();
                    return j2;
                }
                if (!this.f4346a) {
                    this.f4346a = true;
                    this.f4349d.close();
                }
                return -1L;
            } catch (IOException e2) {
                if (!this.f4346a) {
                    this.f4346a = true;
                    this.f4348c.a();
                }
                throw e2;
            }
        }

        @Override // e.s
        public t timeout() {
            return this.f4347b.timeout();
        }
    }

    public a(f fVar) {
        this.f4345a = fVar;
    }

    private static c0 a(c0 c0Var) {
        if (c0Var == null || c0Var.a() == null) {
            return c0Var;
        }
        c0.a aVarS = c0Var.s();
        aVarS.a((d0) null);
        return aVarS.a();
    }

    static boolean b(String str) {
        return ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) ? false : true;
    }

    @Override // d.u
    public c0 intercept(u.a aVar) {
        f fVar = this.f4345a;
        c0 c0VarA = fVar != null ? fVar.a(aVar.request()) : null;
        c cVarA = new c.a(System.currentTimeMillis(), aVar.request(), c0VarA).a();
        a0 a0Var = cVarA.f4350a;
        c0 c0Var = cVarA.f4351b;
        f fVar2 = this.f4345a;
        if (fVar2 != null) {
            fVar2.a(cVarA);
        }
        if (c0VarA != null && c0Var == null) {
            d.h0.c.a(c0VarA.a());
        }
        if (a0Var == null && c0Var == null) {
            c0.a aVar2 = new c0.a();
            aVar2.a(aVar.request());
            aVar2.a(y.HTTP_1_1);
            aVar2.a(504);
            aVar2.a("Unsatisfiable Request (only-if-cached)");
            aVar2.a(d.h0.c.f4339c);
            aVar2.b(-1L);
            aVar2.a(System.currentTimeMillis());
            return aVar2.a();
        }
        if (a0Var == null) {
            c0.a aVarS = c0Var.s();
            aVarS.a(a(c0Var));
            return aVarS.a();
        }
        try {
            c0 c0VarA2 = aVar.a(a0Var);
            if (c0VarA2 == null && c0VarA != null) {
                d.h0.c.a(c0VarA.a());
            }
            if (c0Var != null) {
                if (c0VarA2.m() == 304) {
                    c0.a aVarS2 = c0Var.s();
                    aVarS2.a(a(c0Var.o(), c0VarA2.o()));
                    aVarS2.b(c0VarA2.x());
                    aVarS2.a(c0VarA2.v());
                    aVarS2.a(a(c0Var));
                    aVarS2.b(a(c0VarA2));
                    c0 c0VarA3 = aVarS2.a();
                    c0VarA2.a().close();
                    this.f4345a.a();
                    this.f4345a.a(c0Var, c0VarA3);
                    return c0VarA3;
                }
                d.h0.c.a(c0Var.a());
            }
            c0.a aVarS3 = c0VarA2.s();
            aVarS3.a(a(c0Var));
            aVarS3.b(a(c0VarA2));
            c0 c0VarA4 = aVarS3.a();
            if (this.f4345a != null) {
                if (d.h0.g.e.b(c0VarA4) && c.a(c0VarA4, a0Var)) {
                    return a(this.f4345a.a(c0VarA4), c0VarA4);
                }
                if (d.h0.g.f.a(a0Var.e())) {
                    try {
                        this.f4345a.b(a0Var);
                    } catch (IOException unused) {
                    }
                }
            }
            return c0VarA4;
        } catch (Throwable th) {
            if (c0VarA != null) {
                d.h0.c.a(c0VarA.a());
            }
            throw th;
        }
    }

    private c0 a(b bVar, c0 c0Var) {
        r rVarB;
        if (bVar == null || (rVarB = bVar.b()) == null) {
            return c0Var;
        }
        C0097a c0097a = new C0097a(this, c0Var.a().source(), bVar, l.a(rVarB));
        String strA = c0Var.a("Content-Type");
        long jContentLength = c0Var.a().contentLength();
        c0.a aVarS = c0Var.s();
        aVarS.a(new h(strA, jContentLength, l.a(c0097a)));
        return aVarS.a();
    }

    private static d.s a(d.s sVar, d.s sVar2) {
        d.s.a aVar = new d.s.a();
        int iB = sVar.b();
        for (int i = 0; i < iB; i++) {
            String strA = sVar.a(i);
            String strB = sVar.b(i);
            if ((!"Warning".equalsIgnoreCase(strA) || !strB.startsWith("1")) && (a(strA) || !b(strA) || sVar2.a(strA) == null)) {
                d.h0.a.f4335a.a(aVar, strA, strB);
            }
        }
        int iB2 = sVar2.b();
        for (int i2 = 0; i2 < iB2; i2++) {
            String strA2 = sVar2.a(i2);
            if (!a(strA2) && b(strA2)) {
                d.h0.a.f4335a.a(aVar, strA2, sVar2.b(i2));
            }
        }
        return aVar.a();
    }

    static boolean a(String str) {
        return "Content-Length".equalsIgnoreCase(str) || "Content-Encoding".equalsIgnoreCase(str) || "Content-Type".equalsIgnoreCase(str);
    }
}
