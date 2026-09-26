package e3;

import a3.c0;
import a3.d0;
import com.speed.adv.AdService;
import com.speed.net.ApiResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketException;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class h implements a3.d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f749d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f750e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f751f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f752g;

    public h(p pVar, i iVar, f3.g gVar) {
        j2.i.e(iVar, "finder");
        this.f750e = pVar;
        this.f751f = iVar;
        this.f752g = gVar;
    }

    public static IOException a(h hVar, boolean z3, IOException iOException, int i4) {
        boolean z4 = (i4 & 4) == 0;
        boolean z5 = (i4 & 8) == 0;
        if (iOException != null) {
            hVar.f(iOException);
        }
        return ((p) hVar.f750e).i(hVar, z5 && !z3, z4 && !z3, z4 && z3, z5 && z3, iOException);
    }

    @Override // a3.d
    public void b(p pVar, d0 d0Var) {
        AdService adService = (AdService) this.f752g;
        if (!d0Var.f118s) {
            l1.f.f1370c.post(new f1.p(d0Var, adService, 0));
            return;
        }
        try {
            String strL = d0Var.f109j.l();
            if (strL.length() != 0 && !j2.i.a(p2.i.S0(strL).toString(), "null")) {
                if (((o1.a) this.f750e) == o1.a.f1559e && this.f749d) {
                    strL = l3.h.u(strL);
                }
                try {
                    try {
                        l3.h.d0(d0Var, strL);
                        Object objB = l1.f.f1369b.b(strL, (Type) this.f751f);
                        j2.i.d(objB, "fromJson(...)");
                        l1.f.f1370c.post(new f1.l((ApiResponse) objB, strL, adService, 0));
                        return;
                    } catch (Exception e4) {
                        l3.h.c0("直接解析为T失败: " + e4.getMessage() + ", , 响应数据: " + strL);
                        l1.f.f1370c.post(new f1.o(e4, adService, 0));
                        return;
                    }
                } catch (Exception unused) {
                    l1.f.f1370c.post(new f1.n(l1.f.f1369b.b(strL, new f1.m().f2779b), adService, 0));
                    return;
                }
            }
            l1.f.f1370c.post(new f1.j(adService, 0));
        } catch (Exception e5) {
            l3.h.c0("解密或解析失败: " + e5.getMessage() + ", 响应数据: ");
            l1.f.f1370c.post(new f1.o(e5, adService, 1));
        }
    }

    public q c() {
        f3.f fVarI = ((f3.g) this.f752g).i();
        q qVar = fVarI instanceof q ? (q) fVarI : null;
        if (qVar != null) {
            return qVar;
        }
        throw new IllegalStateException("no connection for CONNECT tunnels");
    }

    public c0 d(boolean z3) throws IOException {
        try {
            c0 c0VarH = ((f3.g) this.f752g).h(z3);
            if (c0VarH == null) {
                return c0VarH;
            }
            c0VarH.f101n = this;
            return c0VarH;
        } catch (IOException e4) {
            f(e4);
            throw e4;
        }
    }

    @Override // a3.d
    public void e(p pVar, IOException iOException) {
        l1.f.f1370c.post(new f1.i(iOException, (AdService) this.f752g, 0));
    }

    public void f(IOException iOException) {
        this.f749d = true;
        ((f3.g) this.f752g).i().h((p) this.f750e, iOException);
    }

    public c3.b g() throws SocketException {
        p pVar = (p) this.f750e;
        if (pVar.f775l) {
            throw new IllegalStateException("Check failed.");
        }
        pVar.f775l = true;
        pVar.f770g.i();
        synchronized (pVar) {
            if (pVar.f783t == null) {
                throw new IllegalStateException("Check failed.");
            }
            if (pVar.f779p || pVar.f780q) {
                throw new IllegalStateException("Check failed.");
            }
            if (pVar.f777n) {
                throw new IllegalStateException("Check failed.");
            }
            if (!pVar.f778o) {
                throw new IllegalStateException("Check failed.");
            }
            pVar.f778o = false;
            pVar.f779p = true;
            pVar.f780q = true;
        }
        f3.f fVarI = ((f3.g) this.f752g).i();
        j2.i.c(fVarI, "null cannot be cast to non-null type okhttp3.internal.connection.RealConnection");
        q qVar = (q) fVarI;
        qVar.f788e.setSoTimeout(0);
        qVar.g();
        return new c3.b(this);
    }

    public h(o1.a aVar, boolean z3, Type type, AdService adService) {
        this.f750e = aVar;
        this.f749d = z3;
        this.f751f = type;
        this.f752g = adService;
    }

    public h(int i4) {
        this.f750e = new ReentrantLock();
        this.f751f = new long[i4];
        this.f752g = new boolean[i4];
    }
}
