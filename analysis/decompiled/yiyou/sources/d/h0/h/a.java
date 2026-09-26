package d.h0.h;

import d.a0;
import d.c0;
import d.d0;
import d.h0.g.h;
import d.h0.g.k;
import d.x;
import e.i;
import e.l;
import e.r;
import e.s;
import e.t;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Http1Codec.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements d.h0.g.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final x f4433a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final d.h0.f.g f4434b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final e.e f4435c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final e.d f4436d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f4437e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private long f4438f = 262144;

    /* JADX INFO: compiled from: Http1Codec.java */
    private abstract class b implements s {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected final i f4439a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected boolean f4440b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        protected long f4441c;

        private b() {
            this.f4439a = new i(a.this.f4435c.timeout());
            this.f4441c = 0L;
        }

        protected final void a(boolean z, IOException iOException) {
            a aVar = a.this;
            int i = aVar.f4437e;
            if (i == 6) {
                return;
            }
            if (i != 5) {
                throw new IllegalStateException("state: " + a.this.f4437e);
            }
            aVar.a(this.f4439a);
            a aVar2 = a.this;
            aVar2.f4437e = 6;
            d.h0.f.g gVar = aVar2.f4434b;
            if (gVar != null) {
                gVar.a(!z, aVar2, this.f4441c, iOException);
            }
        }

        @Override // e.s
        public long read(e.c cVar, long j) throws IOException {
            try {
                long j2 = a.this.f4435c.read(cVar, j);
                if (j2 > 0) {
                    this.f4441c += j2;
                }
                return j2;
            } catch (IOException e2) {
                a(false, e2);
                throw e2;
            }
        }

        @Override // e.s
        public t timeout() {
            return this.f4439a;
        }
    }

    /* JADX INFO: compiled from: Http1Codec.java */
    private final class c implements r {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final i f4443a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f4444b;

        c() {
            this.f4443a = new i(a.this.f4436d.timeout());
        }

        @Override // e.r
        public void a(e.c cVar, long j) {
            if (this.f4444b) {
                throw new IllegalStateException("closed");
            }
            if (j == 0) {
                return;
            }
            a.this.f4436d.d(j);
            a.this.f4436d.b("\r\n");
            a.this.f4436d.a(cVar, j);
            a.this.f4436d.b("\r\n");
        }

        @Override // e.r, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() {
            if (this.f4444b) {
                return;
            }
            this.f4444b = true;
            a.this.f4436d.b("0\r\n\r\n");
            a.this.a(this.f4443a);
            a.this.f4437e = 3;
        }

        @Override // e.r, java.io.Flushable
        public synchronized void flush() {
            if (this.f4444b) {
                return;
            }
            a.this.f4436d.flush();
        }

        @Override // e.r
        public t timeout() {
            return this.f4443a;
        }
    }

    /* JADX INFO: compiled from: Http1Codec.java */
    private class d extends b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final d.t f4446e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private long f4447f;
        private boolean g;

        d(d.t tVar) {
            super();
            this.f4447f = -1L;
            this.g = true;
            this.f4446e = tVar;
        }

        private void a() throws ProtocolException {
            if (this.f4447f != -1) {
                a.this.f4435c.g();
            }
            try {
                this.f4447f = a.this.f4435c.k();
                String strTrim = a.this.f4435c.g().trim();
                if (this.f4447f < 0 || !(strTrim.isEmpty() || strTrim.startsWith(";"))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.f4447f + strTrim + "\"");
                }
                if (this.f4447f == 0) {
                    this.g = false;
                    d.h0.g.e.a(a.this.f4433a.f(), this.f4446e, a.this.e());
                    a(true, null);
                }
            } catch (NumberFormatException e2) {
                throw new ProtocolException(e2.getMessage());
            }
        }

        @Override // e.s, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.f4440b) {
                return;
            }
            if (this.g && !d.h0.c.a(this, 100, TimeUnit.MILLISECONDS)) {
                a(false, null);
            }
            this.f4440b = true;
        }

        @Override // d.h0.h.a.b, e.s
        public long read(e.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.f4440b) {
                throw new IllegalStateException("closed");
            }
            if (!this.g) {
                return -1L;
            }
            long j2 = this.f4447f;
            if (j2 == 0 || j2 == -1) {
                a();
                if (!this.g) {
                    return -1L;
                }
            }
            long j3 = super.read(cVar, Math.min(j, this.f4447f));
            if (j3 != -1) {
                this.f4447f -= j3;
                return j3;
            }
            ProtocolException protocolException = new ProtocolException("unexpected end of stream");
            a(false, protocolException);
            throw protocolException;
        }
    }

    /* JADX INFO: compiled from: Http1Codec.java */
    private final class e implements r {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final i f4448a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f4449b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private long f4450c;

        e(long j) {
            this.f4448a = new i(a.this.f4436d.timeout());
            this.f4450c = j;
        }

        @Override // e.r
        public void a(e.c cVar, long j) throws ProtocolException {
            if (this.f4449b) {
                throw new IllegalStateException("closed");
            }
            d.h0.c.a(cVar.q(), 0L, j);
            if (j <= this.f4450c) {
                a.this.f4436d.a(cVar, j);
                this.f4450c -= j;
                return;
            }
            throw new ProtocolException("expected " + this.f4450c + " bytes but received " + j);
        }

        @Override // e.r, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws ProtocolException {
            if (this.f4449b) {
                return;
            }
            this.f4449b = true;
            if (this.f4450c > 0) {
                throw new ProtocolException("unexpected end of stream");
            }
            a.this.a(this.f4448a);
            a.this.f4437e = 3;
        }

        @Override // e.r, java.io.Flushable
        public void flush() {
            if (this.f4449b) {
                return;
            }
            a.this.f4436d.flush();
        }

        @Override // e.r
        public t timeout() {
            return this.f4448a;
        }
    }

    /* JADX INFO: compiled from: Http1Codec.java */
    private class f extends b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private long f4452e;

        f(a aVar, long j) {
            super();
            this.f4452e = j;
            if (this.f4452e == 0) {
                a(true, null);
            }
        }

        @Override // e.s, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.f4440b) {
                return;
            }
            if (this.f4452e != 0 && !d.h0.c.a(this, 100, TimeUnit.MILLISECONDS)) {
                a(false, null);
            }
            this.f4440b = true;
        }

        @Override // d.h0.h.a.b, e.s
        public long read(e.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.f4440b) {
                throw new IllegalStateException("closed");
            }
            long j2 = this.f4452e;
            if (j2 == 0) {
                return -1L;
            }
            long j3 = super.read(cVar, Math.min(j2, j));
            if (j3 == -1) {
                ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                a(false, protocolException);
                throw protocolException;
            }
            this.f4452e -= j3;
            if (this.f4452e == 0) {
                a(true, null);
            }
            return j3;
        }
    }

    /* JADX INFO: compiled from: Http1Codec.java */
    private class g extends b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f4453e;

        g(a aVar) {
            super();
        }

        @Override // e.s, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.f4440b) {
                return;
            }
            if (!this.f4453e) {
                a(false, null);
            }
            this.f4440b = true;
        }

        @Override // d.h0.h.a.b, e.s
        public long read(e.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (this.f4440b) {
                throw new IllegalStateException("closed");
            }
            if (this.f4453e) {
                return -1L;
            }
            long j2 = super.read(cVar, j);
            if (j2 != -1) {
                return j2;
            }
            this.f4453e = true;
            a(true, null);
            return -1L;
        }
    }

    public a(x xVar, d.h0.f.g gVar, e.e eVar, e.d dVar) {
        this.f4433a = xVar;
        this.f4434b = gVar;
        this.f4435c = eVar;
        this.f4436d = dVar;
    }

    private String f() {
        String strF = this.f4435c.f(this.f4438f);
        this.f4438f -= (long) strF.length();
        return strF;
    }

    @Override // d.h0.g.c
    public r a(a0 a0Var, long j) {
        if ("chunked".equalsIgnoreCase(a0Var.a("Transfer-Encoding"))) {
            return c();
        }
        if (j != -1) {
            return a(j);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    @Override // d.h0.g.c
    public void b() {
        this.f4436d.flush();
    }

    public r c() {
        if (this.f4437e == 1) {
            this.f4437e = 2;
            return new c();
        }
        throw new IllegalStateException("state: " + this.f4437e);
    }

    @Override // d.h0.g.c
    public void cancel() {
        d.h0.f.c cVarC = this.f4434b.c();
        if (cVarC != null) {
            cVarC.b();
        }
    }

    public s d() {
        if (this.f4437e != 4) {
            throw new IllegalStateException("state: " + this.f4437e);
        }
        d.h0.f.g gVar = this.f4434b;
        if (gVar == null) {
            throw new IllegalStateException("streamAllocation == null");
        }
        this.f4437e = 5;
        gVar.e();
        return new g(this);
    }

    public d.s e() {
        d.s.a aVar = new d.s.a();
        while (true) {
            String strF = f();
            if (strF.length() == 0) {
                return aVar.a();
            }
            d.h0.a.f4335a.a(aVar, strF);
        }
    }

    public s b(long j) {
        if (this.f4437e == 4) {
            this.f4437e = 5;
            return new f(this, j);
        }
        throw new IllegalStateException("state: " + this.f4437e);
    }

    @Override // d.h0.g.c
    public void a(a0 a0Var) {
        a(a0Var.c(), d.h0.g.i.a(a0Var, this.f4434b.c().e().b().type()));
    }

    @Override // d.h0.g.c
    public d0 a(c0 c0Var) {
        d.h0.f.g gVar = this.f4434b;
        gVar.f4408f.e(gVar.f4407e);
        String strA = c0Var.a("Content-Type");
        if (!d.h0.g.e.b(c0Var)) {
            return new h(strA, 0L, l.a(b(0L)));
        }
        if ("chunked".equalsIgnoreCase(c0Var.a("Transfer-Encoding"))) {
            return new h(strA, -1L, l.a(a(c0Var.w().g())));
        }
        long jA = d.h0.g.e.a(c0Var);
        if (jA != -1) {
            return new h(strA, jA, l.a(b(jA)));
        }
        return new h(strA, -1L, l.a(d()));
    }

    @Override // d.h0.g.c
    public void a() {
        this.f4436d.flush();
    }

    public void a(d.s sVar, String str) {
        if (this.f4437e == 0) {
            this.f4436d.b(str).b("\r\n");
            int iB = sVar.b();
            for (int i = 0; i < iB; i++) {
                this.f4436d.b(sVar.a(i)).b(": ").b(sVar.b(i)).b("\r\n");
            }
            this.f4436d.b("\r\n");
            this.f4437e = 1;
            return;
        }
        throw new IllegalStateException("state: " + this.f4437e);
    }

    @Override // d.h0.g.c
    public c0.a a(boolean z) {
        int i = this.f4437e;
        if (i != 1 && i != 3) {
            throw new IllegalStateException("state: " + this.f4437e);
        }
        try {
            k kVarA = k.a(f());
            c0.a aVar = new c0.a();
            aVar.a(kVarA.f4430a);
            aVar.a(kVarA.f4431b);
            aVar.a(kVarA.f4432c);
            aVar.a(e());
            if (z && kVarA.f4431b == 100) {
                return null;
            }
            if (kVarA.f4431b == 100) {
                this.f4437e = 3;
                return aVar;
            }
            this.f4437e = 4;
            return aVar;
        } catch (EOFException e2) {
            IOException iOException = new IOException("unexpected end of stream on " + this.f4434b);
            iOException.initCause(e2);
            throw iOException;
        }
    }

    public r a(long j) {
        if (this.f4437e == 1) {
            this.f4437e = 2;
            return new e(j);
        }
        throw new IllegalStateException("state: " + this.f4437e);
    }

    public s a(d.t tVar) {
        if (this.f4437e == 4) {
            this.f4437e = 5;
            return new d(tVar);
        }
        throw new IllegalStateException("state: " + this.f4437e);
    }

    void a(i iVar) {
        t tVarG = iVar.g();
        iVar.a(t.f4768d);
        tVarG.a();
        tVarG.b();
    }
}
