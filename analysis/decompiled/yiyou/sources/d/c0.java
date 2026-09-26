package d;

import java.io.Closeable;

/* JADX INFO: compiled from: Response.java */
/* JADX INFO: loaded from: classes.dex */
public final class c0 implements Closeable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final a0 f4281a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final y f4282b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final int f4283c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final String f4284d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final r f4285e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final s f4286f;
    final d0 g;
    final c0 h;
    final c0 i;
    final c0 j;
    final long k;
    final long l;
    private volatile d m;

    /* JADX INFO: compiled from: Response.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        a0 f4287a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        y f4288b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f4289c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        String f4290d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        r f4291e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        s.a f4292f;
        d0 g;
        c0 h;
        c0 i;
        c0 j;
        long k;
        long l;

        public a() {
            this.f4289c = -1;
            this.f4292f = new s.a();
        }

        private void d(c0 c0Var) {
            if (c0Var.g != null) {
                throw new IllegalArgumentException("priorResponse.body != null");
            }
        }

        public a a(a0 a0Var) {
            this.f4287a = a0Var;
            return this;
        }

        public a b(String str, String str2) {
            this.f4292f.c(str, str2);
            return this;
        }

        public a c(c0 c0Var) {
            if (c0Var != null) {
                d(c0Var);
            }
            this.j = c0Var;
            return this;
        }

        public a a(y yVar) {
            this.f4288b = yVar;
            return this;
        }

        public a b(String str) {
            this.f4292f.c(str);
            return this;
        }

        public a a(int i) {
            this.f4289c = i;
            return this;
        }

        public a b(c0 c0Var) {
            if (c0Var != null) {
                a("networkResponse", c0Var);
            }
            this.h = c0Var;
            return this;
        }

        a(c0 c0Var) {
            this.f4289c = -1;
            this.f4287a = c0Var.f4281a;
            this.f4288b = c0Var.f4282b;
            this.f4289c = c0Var.f4283c;
            this.f4290d = c0Var.f4284d;
            this.f4291e = c0Var.f4285e;
            this.f4292f = c0Var.f4286f.a();
            this.g = c0Var.g;
            this.h = c0Var.h;
            this.i = c0Var.i;
            this.j = c0Var.j;
            this.k = c0Var.k;
            this.l = c0Var.l;
        }

        public a a(String str) {
            this.f4290d = str;
            return this;
        }

        public a a(r rVar) {
            this.f4291e = rVar;
            return this;
        }

        public a b(long j) {
            this.k = j;
            return this;
        }

        public a a(String str, String str2) {
            this.f4292f.a(str, str2);
            return this;
        }

        public a a(s sVar) {
            this.f4292f = sVar.a();
            return this;
        }

        public a a(d0 d0Var) {
            this.g = d0Var;
            return this;
        }

        public a a(c0 c0Var) {
            if (c0Var != null) {
                a("cacheResponse", c0Var);
            }
            this.i = c0Var;
            return this;
        }

        private void a(String str, c0 c0Var) {
            if (c0Var.g == null) {
                if (c0Var.h == null) {
                    if (c0Var.i == null) {
                        if (c0Var.j == null) {
                            return;
                        }
                        throw new IllegalArgumentException(str + ".priorResponse != null");
                    }
                    throw new IllegalArgumentException(str + ".cacheResponse != null");
                }
                throw new IllegalArgumentException(str + ".networkResponse != null");
            }
            throw new IllegalArgumentException(str + ".body != null");
        }

        public a a(long j) {
            this.l = j;
            return this;
        }

        public c0 a() {
            if (this.f4287a != null) {
                if (this.f4288b != null) {
                    if (this.f4289c >= 0) {
                        if (this.f4290d != null) {
                            return new c0(this);
                        }
                        throw new IllegalStateException("message == null");
                    }
                    throw new IllegalStateException("code < 0: " + this.f4289c);
                }
                throw new IllegalStateException("protocol == null");
            }
            throw new IllegalStateException("request == null");
        }
    }

    c0(a aVar) {
        this.f4281a = aVar.f4287a;
        this.f4282b = aVar.f4288b;
        this.f4283c = aVar.f4289c;
        this.f4284d = aVar.f4290d;
        this.f4285e = aVar.f4291e;
        this.f4286f = aVar.f4292f.a();
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
        this.k = aVar.k;
        this.l = aVar.l;
    }

    public String a(String str) {
        return a(str, null);
    }

    public d b() {
        d dVar = this.m;
        if (dVar != null) {
            return dVar;
        }
        d dVarA = d.a(this.f4286f);
        this.m = dVarA;
        return dVarA;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        d0 d0Var = this.g;
        if (d0Var == null) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed");
        }
        d0Var.close();
    }

    public int m() {
        return this.f4283c;
    }

    public r n() {
        return this.f4285e;
    }

    public s o() {
        return this.f4286f;
    }

    public boolean p() {
        int i = this.f4283c;
        return i >= 200 && i < 300;
    }

    public String q() {
        return this.f4284d;
    }

    public c0 r() {
        return this.h;
    }

    public a s() {
        return new a(this);
    }

    public c0 t() {
        return this.j;
    }

    public String toString() {
        return "Response{protocol=" + this.f4282b + ", code=" + this.f4283c + ", message=" + this.f4284d + ", url=" + this.f4281a.g() + '}';
    }

    public y u() {
        return this.f4282b;
    }

    public long v() {
        return this.l;
    }

    public a0 w() {
        return this.f4281a;
    }

    public long x() {
        return this.k;
    }

    public String a(String str, String str2) {
        String strA = this.f4286f.a(str);
        return strA != null ? strA : str2;
    }

    public d0 a() {
        return this.g;
    }
}
