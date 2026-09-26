package d.i0;

import d.a0;
import d.b0;
import d.c0;
import d.d0;
import d.h0.g.e;
import d.h0.k.f;
import d.i;
import d.s;
import d.u;
import d.v;
import e.c;
import e.j;
import java.io.EOFException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: HttpLoggingInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements u {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Charset f4611c = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f4612a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private volatile EnumC0103a f4613b = EnumC0103a.NONE;

    /* JADX INFO: renamed from: d.i0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HttpLoggingInterceptor.java */
    public enum EnumC0103a {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    /* JADX INFO: compiled from: HttpLoggingInterceptor.java */
    public interface b {

        /* JADX INFO: renamed from: d.i0.a$b$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: HttpLoggingInterceptor.java */
        final class C0104a implements b {
            C0104a() {
            }

            @Override // d.i0.a.b
            public void log(String str) {
                f.d().a(4, str, (Throwable) null);
            }
        }

        static {
            new C0104a();
        }

        void log(String str);
    }

    public a(b bVar) {
        this.f4612a = bVar;
    }

    public a a(EnumC0103a enumC0103a) {
        if (enumC0103a == null) {
            throw new NullPointerException("level == null. Use Level.NONE instead.");
        }
        this.f4613b = enumC0103a;
        return this;
    }

    @Override // d.u
    public c0 intercept(u.a aVar) throws Exception {
        boolean z;
        char c2;
        String string;
        Long lValueOf;
        EnumC0103a enumC0103a = this.f4613b;
        a0 a0VarRequest = aVar.request();
        if (enumC0103a == EnumC0103a.NONE) {
            return aVar.a(a0VarRequest);
        }
        boolean z2 = enumC0103a == EnumC0103a.BODY;
        boolean z3 = z2 || enumC0103a == EnumC0103a.HEADERS;
        b0 b0VarA = a0VarRequest.a();
        boolean z4 = b0VarA != null;
        i iVarD = aVar.d();
        StringBuilder sb = new StringBuilder();
        sb.append("--> ");
        sb.append(a0VarRequest.e());
        sb.append(' ');
        sb.append(a0VarRequest.g());
        sb.append(iVarD != null ? " " + iVarD.a() : "");
        String string2 = sb.toString();
        if (!z3 && z4) {
            string2 = string2 + " (" + b0VarA.contentLength() + "-byte body)";
        }
        this.f4612a.log(string2);
        if (z3) {
            if (z4) {
                if (b0VarA.contentType() != null) {
                    this.f4612a.log("Content-Type: " + b0VarA.contentType());
                }
                if (b0VarA.contentLength() != -1) {
                    this.f4612a.log("Content-Length: " + b0VarA.contentLength());
                }
            }
            s sVarC = a0VarRequest.c();
            int iB = sVarC.b();
            int i = 0;
            while (i < iB) {
                String strA = sVarC.a(i);
                int i2 = iB;
                if (!"Content-Type".equalsIgnoreCase(strA) && !"Content-Length".equalsIgnoreCase(strA)) {
                    this.f4612a.log(strA + ": " + sVarC.b(i));
                }
                i++;
                iB = i2;
                z3 = z3;
            }
            z = z3;
            if (!z2 || !z4) {
                this.f4612a.log("--> END " + a0VarRequest.e());
            } else if (a(a0VarRequest.c())) {
                this.f4612a.log("--> END " + a0VarRequest.e() + " (encoded body omitted)");
            } else {
                c cVar = new c();
                b0VarA.writeTo(cVar);
                Charset charsetA = f4611c;
                v vVarContentType = b0VarA.contentType();
                if (vVarContentType != null) {
                    charsetA = vVarContentType.a(f4611c);
                }
                this.f4612a.log("");
                if (a(cVar)) {
                    this.f4612a.log(cVar.a(charsetA));
                    this.f4612a.log("--> END " + a0VarRequest.e() + " (" + b0VarA.contentLength() + "-byte body)");
                } else {
                    this.f4612a.log("--> END " + a0VarRequest.e() + " (binary " + b0VarA.contentLength() + "-byte body omitted)");
                }
            }
        } else {
            z = z3;
        }
        long jNanoTime = System.nanoTime();
        try {
            c0 c0VarA = aVar.a(a0VarRequest);
            long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - jNanoTime);
            d0 d0VarA = c0VarA.a();
            long jContentLength = d0VarA.contentLength();
            String str = jContentLength != -1 ? jContentLength + "-byte" : "unknown-length";
            b bVar = this.f4612a;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("<-- ");
            sb2.append(c0VarA.m());
            if (c0VarA.q().isEmpty()) {
                string = "";
                c2 = ' ';
            } else {
                StringBuilder sb3 = new StringBuilder();
                c2 = ' ';
                sb3.append(' ');
                sb3.append(c0VarA.q());
                string = sb3.toString();
            }
            sb2.append(string);
            sb2.append(c2);
            sb2.append(c0VarA.w().g());
            sb2.append(" (");
            sb2.append(millis);
            sb2.append("ms");
            sb2.append(z ? "" : ", " + str + " body");
            sb2.append(')');
            bVar.log(sb2.toString());
            if (z) {
                s sVarO = c0VarA.o();
                int iB2 = sVarO.b();
                for (int i3 = 0; i3 < iB2; i3++) {
                    this.f4612a.log(sVarO.a(i3) + ": " + sVarO.b(i3));
                }
                if (!z2 || !e.b(c0VarA)) {
                    this.f4612a.log("<-- END HTTP");
                } else if (a(c0VarA.o())) {
                    this.f4612a.log("<-- END HTTP (encoded body omitted)");
                } else {
                    e.e eVarSource = d0VarA.source();
                    eVarSource.c(Long.MAX_VALUE);
                    c cVarC = eVarSource.c();
                    j jVar = null;
                    if ("gzip".equalsIgnoreCase(sVarO.a("Content-Encoding"))) {
                        lValueOf = Long.valueOf(cVarC.q());
                        try {
                            j jVar2 = new j(cVarC.m5clone());
                            try {
                                cVarC = new c();
                                cVarC.a(jVar2);
                                jVar2.close();
                            } catch (Throwable th) {
                                th = th;
                                jVar = jVar2;
                                if (jVar != null) {
                                    jVar.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } else {
                        lValueOf = null;
                    }
                    Charset charsetA2 = f4611c;
                    v vVarContentType2 = d0VarA.contentType();
                    if (vVarContentType2 != null) {
                        charsetA2 = vVarContentType2.a(f4611c);
                    }
                    if (!a(cVarC)) {
                        this.f4612a.log("");
                        this.f4612a.log("<-- END HTTP (binary " + cVarC.q() + "-byte body omitted)");
                        return c0VarA;
                    }
                    if (jContentLength != 0) {
                        this.f4612a.log("");
                        this.f4612a.log(cVarC.m5clone().a(charsetA2));
                    }
                    if (lValueOf != null) {
                        this.f4612a.log("<-- END HTTP (" + cVarC.q() + "-byte, " + lValueOf + "-gzipped-byte body)");
                    } else {
                        this.f4612a.log("<-- END HTTP (" + cVarC.q() + "-byte body)");
                    }
                }
            }
            return c0VarA;
        } catch (Exception e2) {
            this.f4612a.log("<-- HTTP FAILED: " + e2);
            throw e2;
        }
    }

    static boolean a(c cVar) {
        try {
            c cVar2 = new c();
            cVar.a(cVar2, 0L, cVar.q() < 64 ? cVar.q() : 64L);
            for (int i = 0; i < 16 && !cVar2.j(); i++) {
                int iP = cVar2.p();
                if (Character.isISOControl(iP) && !Character.isWhitespace(iP)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    private boolean a(s sVar) {
        String strA = sVar.a("Content-Encoding");
        return (strA == null || strA.equalsIgnoreCase("identity") || strA.equalsIgnoreCase("gzip")) ? false : true;
    }
}
