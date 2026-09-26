package g3;

import a3.a0;
import a3.c0;
import a3.d0;
import a3.r;
import a3.x;
import d0.l0;
import f3.k;
import j2.i;
import java.io.EOFException;
import java.io.IOException;
import java.net.Proxy;
import java.util.Arrays;
import q3.n;
import q3.o;
import q3.s;
import q3.t;
import q3.u;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class h implements f3.g {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final r f977f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final x f978a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f3.f f979b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a2.f f980c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f981d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f982e;

    static {
        r rVar = r.f198e;
        String[] strArr = (String[]) Arrays.copyOf(new String[]{"OkHttp-Response-Body", "Truncated"}, 2);
        i.e(strArr, "inputNamesAndValues");
        if (strArr.length % 2 != 0) {
            throw new IllegalArgumentException("Expected alternating header names and values");
        }
        String[] strArr2 = (String[]) Arrays.copyOf(strArr, strArr.length);
        int length = strArr2.length;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            if (strArr2[i5] == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            strArr2[i5] = p2.i.S0(strArr[i5]).toString();
        }
        int iW = l0.w(0, strArr2.length - 1, 2);
        if (iW >= 0) {
            while (true) {
                String str = strArr2[i4];
                String str2 = strArr2[i4 + 1];
                l3.h.I(str);
                l3.h.J(str2, str);
                if (i4 == iW) {
                    break;
                } else {
                    i4 += 2;
                }
            }
        }
        f977f = new r(strArr2);
    }

    public h(x xVar, f3.f fVar, a2.f fVar2) {
        i.e(fVar2, "socket");
        this.f978a = xVar;
        this.f979b = fVar;
        this.f980c = fVar2;
        this.f982e = new a((o) fVar2.f46f);
    }

    @Override // f3.g
    public final void a() {
        ((n) this.f980c.f47g).flush();
    }

    @Override // f3.g
    public final boolean b() {
        return this.f981d == 6;
    }

    @Override // f3.g
    public final void c() {
        ((n) this.f980c.f47g).flush();
    }

    @Override // f3.g
    public final void cancel() {
        this.f979b.cancel();
    }

    @Override // f3.g
    public final t d() {
        return this.f980c;
    }

    @Override // f3.g
    public final s e(a0 a0Var, long j4) {
        if ("chunked".equalsIgnoreCase(((r) a0Var.f64d).a("Transfer-Encoding"))) {
            if (this.f981d == 1) {
                this.f981d = 2;
                return new c(this);
            }
            throw new IllegalStateException(("state: " + this.f981d).toString());
        }
        if (j4 == -1) {
            throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
        }
        if (this.f981d == 1) {
            this.f981d = 2;
            return new f(this);
        }
        throw new IllegalStateException(("state: " + this.f981d).toString());
    }

    @Override // f3.g
    public final long f(d0 d0Var) {
        if (!f3.h.a(d0Var)) {
            return 0L;
        }
        String strA = d0Var.f108i.a("Transfer-Encoding");
        if (strA == null) {
            strA = null;
        }
        if ("chunked".equalsIgnoreCase(strA)) {
            return -1L;
        }
        return b3.g.e(d0Var);
    }

    @Override // f3.g
    public final u g(d0 d0Var) {
        a0 a0Var = d0Var.f103d;
        if (!f3.h.a(d0Var)) {
            return k((a3.t) a0Var.f63c, 0L);
        }
        String strA = d0Var.f108i.a("Transfer-Encoding");
        if (strA == null) {
            strA = null;
        }
        if ("chunked".equalsIgnoreCase(strA)) {
            a3.t tVar = (a3.t) a0Var.f63c;
            if (this.f981d == 4) {
                this.f981d = 5;
                return new d(this, tVar);
            }
            throw new IllegalStateException(("state: " + this.f981d).toString());
        }
        long jE = b3.g.e(d0Var);
        if (jE != -1) {
            return k((a3.t) a0Var.f63c, jE);
        }
        a3.t tVar2 = (a3.t) a0Var.f63c;
        if (this.f981d != 4) {
            throw new IllegalStateException(("state: " + this.f981d).toString());
        }
        this.f981d = 5;
        this.f979b.g();
        i.e(tVar2, "url");
        return new g(this, tVar2);
    }

    @Override // f3.g
    public final c0 h(boolean z3) {
        a aVar = this.f982e;
        int i4 = this.f981d;
        if (i4 != 0 && i4 != 1 && i4 != 2 && i4 != 3) {
            throw new IllegalStateException(("state: " + this.f981d).toString());
        }
        try {
            String strT = aVar.f959a.t(aVar.f960b);
            aVar.f960b -= (long) strT.length();
            k kVarC = l0.C(strT);
            int i5 = kVarC.f910b;
            c0 c0Var = new c0();
            c0Var.f89b = kVarC.f909a;
            c0Var.f90c = i5;
            c0Var.f91d = kVarC.f911c;
            c0Var.f93f = aVar.a().c();
            if (z3 && i5 == 100) {
                return null;
            }
            if (i5 == 100) {
                this.f981d = 3;
                return c0Var;
            }
            if (102 > i5 || i5 >= 200) {
                this.f981d = 4;
                return c0Var;
            }
            this.f981d = 3;
            return c0Var;
        } catch (EOFException e4) {
            throw new IOException("unexpected end of stream on " + this.f979b.c().f145a.f58h.f(), e4);
        }
    }

    @Override // f3.g
    public final f3.f i() {
        return this.f979b;
    }

    @Override // f3.g
    public final void j(a0 a0Var) {
        Proxy.Type type = this.f979b.c().f146b.type();
        i.d(type, "type(...)");
        StringBuilder sb = new StringBuilder();
        sb.append(a0Var.f62b);
        sb.append(' ');
        a3.t tVar = (a3.t) a0Var.f63c;
        if (i.a(tVar.f208a, "https") || type != Proxy.Type.HTTP) {
            String strB = tVar.b();
            String strD = tVar.d();
            if (strD != null) {
                strB = strB + '?' + strD;
            }
            sb.append(strB);
        } else {
            sb.append(tVar);
        }
        sb.append(" HTTP/1.1");
        l((r) a0Var.f64d, sb.toString());
    }

    public final e k(a3.t tVar, long j4) {
        if (this.f981d == 4) {
            this.f981d = 5;
            return new e(this, tVar, j4);
        }
        throw new IllegalStateException(("state: " + this.f981d).toString());
    }

    public final void l(r rVar, String str) {
        i.e(rVar, "headers");
        i.e(str, "requestLine");
        if (this.f981d != 0) {
            throw new IllegalStateException(("state: " + this.f981d).toString());
        }
        a2.f fVar = this.f980c;
        n nVar = (n) fVar.f47g;
        n nVar2 = (n) fVar.f47g;
        nVar.H(str);
        nVar.H("\r\n");
        int size = rVar.size();
        for (int i4 = 0; i4 < size; i4++) {
            nVar2.H(rVar.b(i4));
            nVar2.H(": ");
            nVar2.H(rVar.d(i4));
            nVar2.H("\r\n");
        }
        nVar2.H("\r\n");
        this.f981d = 1;
    }
}
