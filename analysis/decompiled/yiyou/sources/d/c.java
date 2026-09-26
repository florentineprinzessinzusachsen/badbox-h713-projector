package d;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: Cache.java */
/* JADX INFO: loaded from: classes.dex */
public final class c implements Closeable, Flushable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final d.h0.e.f f4256a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final d.h0.e.d f4257b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f4258c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f4259d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4260e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4261f;
    private int g;

    /* JADX INFO: compiled from: Cache.java */
    class a implements d.h0.e.f {
        a() {
        }

        @Override // d.h0.e.f
        public c0 a(a0 a0Var) {
            return c.this.a(a0Var);
        }

        @Override // d.h0.e.f
        public void b(a0 a0Var) {
            c.this.b(a0Var);
        }

        @Override // d.h0.e.f
        public d.h0.e.b a(c0 c0Var) {
            return c.this.a(c0Var);
        }

        @Override // d.h0.e.f
        public void a(c0 c0Var, c0 c0Var2) {
            c.this.a(c0Var, c0Var2);
        }

        @Override // d.h0.e.f
        public void a() {
            c.this.a();
        }

        @Override // d.h0.e.f
        public void a(d.h0.e.c cVar) {
            c.this.a(cVar);
        }
    }

    /* JADX INFO: compiled from: Cache.java */
    private final class b implements d.h0.e.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final d.h0.e.d.c f4263a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private e.r f4264b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private e.r f4265c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f4266d;

        /* JADX INFO: compiled from: Cache.java */
        class a extends e.g {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ d.h0.e.d.c f4268b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(e.r rVar, c cVar, d.h0.e.d.c cVar2) {
                super(rVar);
                this.f4268b = cVar2;
            }

            @Override // e.g, e.r, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                synchronized (c.this) {
                    if (b.this.f4266d) {
                        return;
                    }
                    b.this.f4266d = true;
                    c.this.f4258c++;
                    super.close();
                    this.f4268b.b();
                }
            }
        }

        b(d.h0.e.d.c cVar) {
            this.f4263a = cVar;
            this.f4264b = cVar.a(1);
            this.f4265c = new a(this.f4264b, c.this, cVar);
        }

        @Override // d.h0.e.b
        public void a() {
            synchronized (c.this) {
                if (this.f4266d) {
                    return;
                }
                this.f4266d = true;
                c.this.f4259d++;
                d.h0.c.a(this.f4264b);
                try {
                    this.f4263a.a();
                } catch (IOException unused) {
                }
            }
        }

        @Override // d.h0.e.b
        public e.r b() {
            return this.f4265c;
        }
    }

    /* JADX INFO: renamed from: d.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Cache.java */
    private static class C0096c extends d0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final d.h0.e.d.e f4270a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e.e f4271b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final String f4272c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final String f4273d;

        /* JADX INFO: renamed from: d.c$c$a */
        /* JADX INFO: compiled from: Cache.java */
        class a extends e.h {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ d.h0.e.d.e f4274a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(C0096c c0096c, e.s sVar, d.h0.e.d.e eVar) {
                super(sVar);
                this.f4274a = eVar;
            }

            @Override // e.h, e.s, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                this.f4274a.close();
                super.close();
            }
        }

        C0096c(d.h0.e.d.e eVar, String str, String str2) {
            this.f4270a = eVar;
            this.f4272c = str;
            this.f4273d = str2;
            this.f4271b = e.l.a(new a(this, eVar.a(1), eVar));
        }

        @Override // d.d0
        public long contentLength() {
            try {
                if (this.f4273d != null) {
                    return Long.parseLong(this.f4273d);
                }
                return -1L;
            } catch (NumberFormatException unused) {
                return -1L;
            }
        }

        @Override // d.d0
        public v contentType() {
            String str = this.f4272c;
            if (str != null) {
                return v.b(str);
            }
            return null;
        }

        @Override // d.d0
        public e.e source() {
            return this.f4271b;
        }
    }

    public c(File file, long j) {
        this(file, j, d.h0.j.a.f4582a);
    }

    public static String a(t tVar) {
        return e.f.d(tVar.toString()).c().b();
    }

    void b(a0 a0Var) {
        this.f4257b.d(a(a0Var.g()));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f4257b.close();
    }

    @Override // java.io.Flushable
    public void flush() {
        this.f4257b.flush();
    }

    c(File file, long j, d.h0.j.a aVar) {
        this.f4256a = new a();
        this.f4257b = d.h0.e.d.a(aVar, file, 201105, 2, j);
    }

    c0 a(a0 a0Var) {
        try {
            d.h0.e.d.e eVarC = this.f4257b.c(a(a0Var.g()));
            if (eVarC == null) {
                return null;
            }
            try {
                d dVar = new d(eVarC.a(0));
                c0 c0VarA = dVar.a(eVarC);
                if (dVar.a(a0Var, c0VarA)) {
                    return c0VarA;
                }
                d.h0.c.a(c0VarA.a());
                return null;
            } catch (IOException unused) {
                d.h0.c.a(eVarC);
                return null;
            }
        } catch (IOException unused2) {
        }
    }

    d.h0.e.b a(c0 c0Var) {
        d.h0.e.d.c cVarA;
        String strE = c0Var.w().e();
        if (d.h0.g.f.a(c0Var.w().e())) {
            try {
                b(c0Var.w());
            } catch (IOException unused) {
            }
            return null;
        }
        if (!strE.equals("GET") || d.h0.g.e.c(c0Var)) {
            return null;
        }
        d dVar = new d(c0Var);
        try {
            cVarA = this.f4257b.a(a(c0Var.w().g()));
            if (cVarA == null) {
                return null;
            }
            try {
                dVar.a(cVarA);
                return new b(cVarA);
            } catch (IOException unused2) {
                a(cVarA);
                return null;
            }
        } catch (IOException unused3) {
            cVarA = null;
        }
    }

    void a(c0 c0Var, c0 c0Var2) {
        d.h0.e.d.c cVarA;
        d dVar = new d(c0Var2);
        try {
            cVarA = ((C0096c) c0Var.a()).f4270a.a();
            if (cVarA != null) {
                try {
                    dVar.a(cVarA);
                    cVarA.b();
                } catch (IOException unused) {
                    a(cVarA);
                }
            }
        } catch (IOException unused2) {
            cVarA = null;
        }
    }

    private void a(d.h0.e.d.c cVar) {
        if (cVar != null) {
            try {
                cVar.a();
            } catch (IOException unused) {
            }
        }
    }

    synchronized void a(d.h0.e.c cVar) {
        this.g++;
        if (cVar.f4350a != null) {
            this.f4260e++;
        } else if (cVar.f4351b != null) {
            this.f4261f++;
        }
    }

    synchronized void a() {
        this.f4261f++;
    }

    static int a(e.e eVar) throws IOException {
        try {
            long jF = eVar.f();
            String strG = eVar.g();
            if (jF >= 0 && jF <= 2147483647L && strG.isEmpty()) {
                return (int) jF;
            }
            throw new IOException("expected an int but was \"" + jF + strG + "\"");
        } catch (NumberFormatException e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* JADX INFO: compiled from: Cache.java */
    private static final class d {
        private static final String k = d.h0.k.f.d().a() + "-Sent-Millis";
        private static final String l = d.h0.k.f.d().a() + "-Received-Millis";

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f4275a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final s f4276b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final String f4277c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final y f4278d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int f4279e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final String f4280f;
        private final s g;
        private final r h;
        private final long i;
        private final long j;

        d(e.s sVar) {
            try {
                e.e eVarA = e.l.a(sVar);
                this.f4275a = eVarA.g();
                this.f4277c = eVarA.g();
                s.a aVar = new s.a();
                int iA = c.a(eVarA);
                for (int i = 0; i < iA; i++) {
                    aVar.a(eVarA.g());
                }
                this.f4276b = aVar.a();
                d.h0.g.k kVarA = d.h0.g.k.a(eVarA.g());
                this.f4278d = kVarA.f4430a;
                this.f4279e = kVarA.f4431b;
                this.f4280f = kVarA.f4432c;
                s.a aVar2 = new s.a();
                int iA2 = c.a(eVarA);
                for (int i2 = 0; i2 < iA2; i2++) {
                    aVar2.a(eVarA.g());
                }
                String strB = aVar2.b(k);
                String strB2 = aVar2.b(l);
                aVar2.c(k);
                aVar2.c(l);
                this.i = strB != null ? Long.parseLong(strB) : 0L;
                this.j = strB2 != null ? Long.parseLong(strB2) : 0L;
                this.g = aVar2.a();
                if (a()) {
                    String strG = eVarA.g();
                    if (strG.length() > 0) {
                        throw new IOException("expected \"\" but was \"" + strG + "\"");
                    }
                    this.h = r.a(!eVarA.j() ? f0.a(eVarA.g()) : f0.SSL_3_0, h.a(eVarA.g()), a(eVarA), a(eVarA));
                } else {
                    this.h = null;
                }
                sVar.close();
            } catch (Throwable th) {
                sVar.close();
                throw th;
            }
        }

        public void a(d.h0.e.d.c cVar) throws IOException {
            e.d dVarA = e.l.a(cVar.a(0));
            dVarA.b(this.f4275a).writeByte(10);
            dVarA.b(this.f4277c).writeByte(10);
            dVarA.h(this.f4276b.b()).writeByte(10);
            int iB = this.f4276b.b();
            for (int i = 0; i < iB; i++) {
                dVarA.b(this.f4276b.a(i)).b(": ").b(this.f4276b.b(i)).writeByte(10);
            }
            dVarA.b(new d.h0.g.k(this.f4278d, this.f4279e, this.f4280f).toString()).writeByte(10);
            dVarA.h(this.g.b() + 2).writeByte(10);
            int iB2 = this.g.b();
            for (int i2 = 0; i2 < iB2; i2++) {
                dVarA.b(this.g.a(i2)).b(": ").b(this.g.b(i2)).writeByte(10);
            }
            dVarA.b(k).b(": ").h(this.i).writeByte(10);
            dVarA.b(l).b(": ").h(this.j).writeByte(10);
            if (a()) {
                dVarA.writeByte(10);
                dVarA.b(this.h.a().a()).writeByte(10);
                a(dVarA, this.h.c());
                a(dVarA, this.h.b());
                dVarA.b(this.h.d().a()).writeByte(10);
            }
            dVarA.close();
        }

        private boolean a() {
            return this.f4275a.startsWith("https://");
        }

        d(c0 c0Var) {
            this.f4275a = c0Var.w().g().toString();
            this.f4276b = d.h0.g.e.e(c0Var);
            this.f4277c = c0Var.w().e();
            this.f4278d = c0Var.u();
            this.f4279e = c0Var.m();
            this.f4280f = c0Var.q();
            this.g = c0Var.o();
            this.h = c0Var.n();
            this.i = c0Var.x();
            this.j = c0Var.v();
        }

        private List<Certificate> a(e.e eVar) throws IOException {
            int iA = c.a(eVar);
            if (iA == -1) {
                return Collections.emptyList();
            }
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                ArrayList arrayList = new ArrayList(iA);
                for (int i = 0; i < iA; i++) {
                    String strG = eVar.g();
                    e.c cVar = new e.c();
                    cVar.a(e.f.a(strG));
                    arrayList.add(certificateFactory.generateCertificate(cVar.l()));
                }
                return arrayList;
            } catch (CertificateException e2) {
                throw new IOException(e2.getMessage());
            }
        }

        private void a(e.d dVar, List<Certificate> list) throws IOException {
            try {
                dVar.h(list.size()).writeByte(10);
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    dVar.b(e.f.a(list.get(i).getEncoded()).a()).writeByte(10);
                }
            } catch (CertificateEncodingException e2) {
                throw new IOException(e2.getMessage());
            }
        }

        public boolean a(a0 a0Var, c0 c0Var) {
            return this.f4275a.equals(a0Var.g().toString()) && this.f4277c.equals(a0Var.e()) && d.h0.g.e.a(c0Var, this.f4276b, a0Var);
        }

        public c0 a(d.h0.e.d.e eVar) {
            String strA = this.g.a("Content-Type");
            String strA2 = this.g.a("Content-Length");
            a0.a aVar = new a0.a();
            aVar.b(this.f4275a);
            aVar.a(this.f4277c, (b0) null);
            aVar.a(this.f4276b);
            a0 a0VarA = aVar.a();
            c0.a aVar2 = new c0.a();
            aVar2.a(a0VarA);
            aVar2.a(this.f4278d);
            aVar2.a(this.f4279e);
            aVar2.a(this.f4280f);
            aVar2.a(this.g);
            aVar2.a(new C0096c(eVar, strA, strA2));
            aVar2.a(this.h);
            aVar2.b(this.i);
            aVar2.a(this.j);
            return aVar2.a();
        }
    }
}
