package d;

import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: compiled from: RealCall.java */
/* JADX INFO: loaded from: classes.dex */
final class z implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final x f4711a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final d.h0.g.j f4712b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private p f4713c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final a0 f4714d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final boolean f4715e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f4716f;

    /* JADX INFO: compiled from: RealCall.java */
    final class a extends d.h0.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final f f4717b;

        a(f fVar) {
            super("OkHttp %s", z.this.b());
            this.f4717b = fVar;
        }

        @Override // d.h0.b
        protected void b() {
            IOException e2;
            boolean z = true;
            try {
                try {
                    c0 c0VarA = z.this.a();
                    try {
                        if (z.this.f4712b.b()) {
                            this.f4717b.onFailure(z.this, new IOException("Canceled"));
                        } else {
                            this.f4717b.onResponse(z.this, c0VarA);
                        }
                    } catch (IOException e3) {
                        e2 = e3;
                        if (z) {
                            d.h0.k.f.d().a(4, "Callback failure for " + z.this.c(), e2);
                        } else {
                            z.this.f4713c.a(z.this, e2);
                            this.f4717b.onFailure(z.this, e2);
                        }
                    }
                } catch (IOException e4) {
                    e2 = e4;
                    z = false;
                }
            } finally {
                z.this.f4711a.g().b(this);
            }
        }

        z c() {
            return z.this;
        }

        String d() {
            return z.this.f4714d.g().g();
        }
    }

    private z(x xVar, a0 a0Var, boolean z) {
        this.f4711a = xVar;
        this.f4714d = a0Var;
        this.f4715e = z;
        this.f4712b = new d.h0.g.j(xVar, z);
    }

    private void d() {
        this.f4712b.a(d.h0.k.f.d().a("response.body().close()"));
    }

    String b() {
        return this.f4714d.g().n();
    }

    String c() {
        StringBuilder sb = new StringBuilder();
        sb.append(isCanceled() ? "canceled " : "");
        sb.append(this.f4715e ? "web socket" : "call");
        sb.append(" to ");
        sb.append(b());
        return sb.toString();
    }

    @Override // d.e
    public void cancel() {
        this.f4712b.a();
    }

    @Override // d.e
    public c0 execute() {
        synchronized (this) {
            if (this.f4716f) {
                throw new IllegalStateException("Already Executed");
            }
            this.f4716f = true;
        }
        d();
        this.f4713c.b(this);
        try {
            try {
                this.f4711a.g().a(this);
                c0 c0VarA = a();
                if (c0VarA == null) {
                    throw new IOException("Canceled");
                }
                this.f4711a.g().b(this);
                return c0VarA;
            } catch (IOException e2) {
                this.f4713c.a(this, e2);
                throw e2;
            }
        } catch (Throwable th) {
            this.f4711a.g().b(this);
            throw th;
        }
    }

    @Override // d.e
    public boolean isCanceled() {
        return this.f4712b.b();
    }

    @Override // d.e
    public a0 request() {
        return this.f4714d;
    }

    static z a(x xVar, a0 a0Var, boolean z) {
        z zVar = new z(xVar, a0Var, z);
        zVar.f4713c = xVar.i().a(zVar);
        return zVar;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public z m4clone() {
        return a(this.f4711a, this.f4714d, this.f4715e);
    }

    @Override // d.e
    public void a(f fVar) {
        synchronized (this) {
            if (!this.f4716f) {
                this.f4716f = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        d();
        this.f4713c.b(this);
        this.f4711a.g().a(new a(fVar));
    }

    c0 a() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f4711a.m());
        arrayList.add(this.f4712b);
        arrayList.add(new d.h0.g.a(this.f4711a.f()));
        arrayList.add(new d.h0.e.a(this.f4711a.n()));
        arrayList.add(new d.h0.f.a(this.f4711a));
        if (!this.f4715e) {
            arrayList.addAll(this.f4711a.o());
        }
        arrayList.add(new d.h0.g.b(this.f4715e));
        return new d.h0.g.g(arrayList, null, null, null, 0, this.f4714d, this, this.f4713c, this.f4711a.c(), this.f4711a.u(), this.f4711a.y()).a(this.f4714d);
    }
}
