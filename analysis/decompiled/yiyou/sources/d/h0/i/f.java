package d.h0.i;

import d.a0;
import d.c0;
import d.d0;
import d.u;
import d.x;
import d.y;
import e.r;
import e.s;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Http2Codec.java */
/* JADX INFO: loaded from: classes.dex */
public final class f implements d.h0.g.c {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final e.f f4484f = e.f.d("connection");
    private static final e.f g = e.f.d("host");
    private static final e.f h = e.f.d("keep-alive");
    private static final e.f i = e.f.d("proxy-connection");
    private static final e.f j = e.f.d("transfer-encoding");
    private static final e.f k = e.f.d("te");
    private static final e.f l = e.f.d("encoding");
    private static final e.f m = e.f.d("upgrade");
    private static final List<e.f> n = d.h0.c.a(f4484f, g, h, i, k, j, l, m, c.f4462f, c.g, c.h, c.i);
    private static final List<e.f> o = d.h0.c.a(f4484f, g, h, i, k, j, l, m);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final u.a f4485a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final d.h0.f.g f4486b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final g f4487c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private i f4488d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final y f4489e;

    /* JADX INFO: compiled from: Http2Codec.java */
    class a extends e.h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f4490a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        long f4491b;

        a(s sVar) {
            super(sVar);
            this.f4490a = false;
            this.f4491b = 0L;
        }

        private void a(IOException iOException) {
            if (this.f4490a) {
                return;
            }
            this.f4490a = true;
            f fVar = f.this;
            fVar.f4486b.a(false, fVar, this.f4491b, iOException);
        }

        @Override // e.h, e.s, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            super.close();
            a(null);
        }

        @Override // e.h, e.s
        public long read(e.c cVar, long j) throws IOException {
            try {
                long j2 = delegate().read(cVar, j);
                if (j2 > 0) {
                    this.f4491b += j2;
                }
                return j2;
            } catch (IOException e2) {
                a(e2);
                throw e2;
            }
        }
    }

    public f(x xVar, u.a aVar, d.h0.f.g gVar, g gVar2) {
        this.f4485a = aVar;
        this.f4486b = gVar;
        this.f4487c = gVar2;
        this.f4489e = xVar.q().contains(y.H2_PRIOR_KNOWLEDGE) ? y.H2_PRIOR_KNOWLEDGE : y.HTTP_2;
    }

    @Override // d.h0.g.c
    public r a(a0 a0Var, long j2) {
        return this.f4488d.d();
    }

    @Override // d.h0.g.c
    public void b() {
        this.f4487c.flush();
    }

    @Override // d.h0.g.c
    public void cancel() {
        i iVar = this.f4488d;
        if (iVar != null) {
            iVar.b(b.CANCEL);
        }
    }

    public static List<c> b(a0 a0Var) {
        d.s sVarC = a0Var.c();
        ArrayList arrayList = new ArrayList(sVarC.b() + 4);
        arrayList.add(new c(c.f4462f, a0Var.e()));
        arrayList.add(new c(c.g, d.h0.g.i.a(a0Var.g())));
        String strA = a0Var.a("Host");
        if (strA != null) {
            arrayList.add(new c(c.i, strA));
        }
        arrayList.add(new c(c.h, a0Var.g().o()));
        int iB = sVarC.b();
        for (int i2 = 0; i2 < iB; i2++) {
            e.f fVarD = e.f.d(sVarC.a(i2).toLowerCase(Locale.US));
            if (!n.contains(fVarD)) {
                arrayList.add(new c(fVarD, sVarC.b(i2)));
            }
        }
        return arrayList;
    }

    @Override // d.h0.g.c
    public void a(a0 a0Var) {
        if (this.f4488d != null) {
            return;
        }
        this.f4488d = this.f4487c.a(b(a0Var), a0Var.a() != null);
        this.f4488d.h().a(this.f4485a.c(), TimeUnit.MILLISECONDS);
        this.f4488d.l().a(this.f4485a.a(), TimeUnit.MILLISECONDS);
    }

    @Override // d.h0.g.c
    public void a() {
        this.f4488d.d().close();
    }

    @Override // d.h0.g.c
    public c0.a a(boolean z) throws ProtocolException {
        c0.a aVarA = a(this.f4488d.j(), this.f4489e);
        if (z && d.h0.a.f4335a.a(aVarA) == 100) {
            return null;
        }
        return aVarA;
    }

    public static c0.a a(List<c> list, y yVar) throws ProtocolException {
        d.s.a aVar = new d.s.a();
        int size = list.size();
        d.s.a aVar2 = aVar;
        d.h0.g.k kVarA = null;
        for (int i2 = 0; i2 < size; i2++) {
            c cVar = list.get(i2);
            if (cVar == null) {
                if (kVarA != null && kVarA.f4431b == 100) {
                    aVar2 = new d.s.a();
                    kVarA = null;
                }
            } else {
                e.f fVar = cVar.f4463a;
                String strI = cVar.f4464b.i();
                if (fVar.equals(c.f4461e)) {
                    kVarA = d.h0.g.k.a("HTTP/1.1 " + strI);
                } else if (!o.contains(fVar)) {
                    d.h0.a.f4335a.a(aVar2, fVar.i(), strI);
                }
            }
        }
        if (kVarA != null) {
            c0.a aVar3 = new c0.a();
            aVar3.a(yVar);
            aVar3.a(kVarA.f4431b);
            aVar3.a(kVarA.f4432c);
            aVar3.a(aVar2.a());
            return aVar3;
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    @Override // d.h0.g.c
    public d0 a(c0 c0Var) {
        d.h0.f.g gVar = this.f4486b;
        gVar.f4408f.e(gVar.f4407e);
        return new d.h0.g.h(c0Var.a("Content-Type"), d.h0.g.e.a(c0Var), e.l.a(new a(this.f4488d.e())));
    }
}
