package r;

import android.database.SQLException;
import d0.l0;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicBoolean;
import p.x;
import p.y;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class s implements y, t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f1941a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f1942b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final v1.h f1943c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final AtomicBoolean f1944d;

    public s(f fVar, boolean z3) {
        j2.i.e(fVar, "delegate");
        this.f1941a = fVar;
        this.f1942b = z3;
        this.f1943c = new v1.h();
        this.f1944d = new AtomicBoolean(false);
    }

    @Override // p.y
    public final Object a(x xVar, i2.p pVar, a2.i iVar) {
        if (this.f1944d.get()) {
            l3.h.m0(21, "Connection is recycled");
            throw null;
        }
        y1.h hVar = iVar.f42e;
        j2.i.b(hVar);
        a aVar = (a) hVar.k(a.f1863e);
        if (aVar != null && aVar.f1864d == this) {
            return g(xVar, pVar, iVar);
        }
        l3.h.m0(21, "Attempted to use connection on a different coroutine");
        throw null;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // p.n
    public final Object b(String str, i2.l lVar, a2.c cVar) {
        r rVar;
        f fVar;
        s sVar;
        if (cVar instanceof r) {
            rVar = (r) cVar;
            int i4 = rVar.f1940m;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                rVar.f1940m = i4 - Integer.MIN_VALUE;
            } else {
                rVar = new r(this, cVar);
            }
        } else {
            rVar = new r(this, cVar);
        }
        Object obj = rVar.f1938k;
        int i5 = rVar.f1940m;
        if (i5 == 0) {
            l0.M(obj);
            if (this.f1944d.get()) {
                l3.h.m0(21, "Connection is recycled");
                throw null;
            }
            y1.h hVar = rVar.f42e;
            j2.i.b(hVar);
            a aVar = (a) hVar.k(a.f1863e);
            if (aVar == null || aVar.f1864d != this) {
                l3.h.m0(21, "Attempted to use connection on a different coroutine");
                throw null;
            }
            rVar.f1934g = this;
            rVar.f1935h = str;
            rVar.f1936i = lVar;
            fVar = this.f1941a;
            rVar.f1937j = fVar;
            rVar.f1940m = 1;
            Object objC = fVar.f1884e.c(rVar);
            z1.a aVar2 = z1.a.f2781d;
            if (objC == aVar2) {
                return aVar2;
            }
            sVar = this;
        } else {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            f fVar2 = rVar.f1937j;
            lVar = rVar.f1936i;
            String str2 = rVar.f1935h;
            sVar = rVar.f1934g;
            l0.M(obj);
            fVar = fVar2;
            str = str2;
        }
        try {
            l lVar2 = new l(sVar, sVar.f1941a.P(str));
            try {
                Object objH = lVar.h(lVar2);
                l3.h.k(lVar2, null);
                fVar.b(null);
                return objH;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    l3.h.k(lVar2, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            fVar.b(null);
            throw th3;
        }
    }

    @Override // p.y
    public final Object c(a2.i iVar) {
        if (this.f1944d.get()) {
            l3.h.m0(21, "Connection is recycled");
            throw null;
        }
        y1.h hVar = iVar.f42e;
        j2.i.b(hVar);
        a aVar = (a) hVar.k(a.f1863e);
        if (aVar != null && aVar.f1864d == this) {
            return Boolean.valueOf(!this.f1943c.isEmpty());
        }
        l3.h.m0(21, "Attempted to use connection on a different coroutine");
        throw null;
    }

    @Override // r.t
    public final w.a d() {
        return this.f1941a;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0015  */
    public final Object e(x xVar, a2.c cVar) {
        o oVar;
        f fVar;
        s sVar;
        if (cVar instanceof o) {
            oVar = (o) cVar;
            int i4 = oVar.f1921l;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                oVar.f1921l = i4 - Integer.MIN_VALUE;
            } else {
                oVar = new o(this, cVar);
            }
        } else {
            oVar = new o(this, cVar);
        }
        Object obj = oVar.f1919j;
        int i5 = oVar.f1921l;
        if (i5 == 0) {
            l0.M(obj);
            oVar.f1916g = this;
            oVar.f1917h = xVar;
            fVar = this.f1941a;
            oVar.f1918i = fVar;
            oVar.f1921l = 1;
            Object objC = fVar.f1884e.c(oVar);
            z1.a aVar = z1.a.f2781d;
            if (objC == aVar) {
                return aVar;
            }
            sVar = this;
        } else {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            f fVar2 = oVar.f1918i;
            x xVar2 = oVar.f1917h;
            sVar = oVar.f1916g;
            l0.M(obj);
            fVar = fVar2;
            xVar = xVar2;
        }
        try {
            v1.h hVar = sVar.f1943c;
            f fVar3 = sVar.f1941a;
            int i6 = hVar.f2515f;
            if (hVar.isEmpty()) {
                int iOrdinal = xVar.ordinal();
                if (iOrdinal == 0) {
                    l3.h.y(fVar3, "BEGIN DEFERRED TRANSACTION");
                } else if (iOrdinal == 1) {
                    l3.h.y(fVar3, "BEGIN IMMEDIATE TRANSACTION");
                } else {
                    if (iOrdinal != 2) {
                        throw new a0.c();
                    }
                    l3.h.y(fVar3, "BEGIN EXCLUSIVE TRANSACTION");
                }
            } else {
                l3.h.y(fVar3, "SAVEPOINT '" + i6 + '\'');
            }
            hVar.addLast(new n(i6));
            u1.k kVar = u1.k.f2301a;
            fVar.b(null);
            return kVar;
        } catch (Throwable th) {
            fVar.b(null);
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    public final Object f(boolean z3, a2.c cVar) {
        p pVar;
        f fVar;
        s sVar;
        if (cVar instanceof p) {
            pVar = (p) cVar;
            int i4 = pVar.f1927l;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                pVar.f1927l = i4 - Integer.MIN_VALUE;
            } else {
                pVar = new p(this, cVar);
            }
        } else {
            pVar = new p(this, cVar);
        }
        Object obj = pVar.f1925j;
        int i5 = pVar.f1927l;
        if (i5 == 0) {
            l0.M(obj);
            pVar.f1922g = this;
            fVar = this.f1941a;
            pVar.f1923h = fVar;
            pVar.f1924i = z3;
            pVar.f1927l = 1;
            Object objC = fVar.f1884e.c(pVar);
            z1.a aVar = z1.a.f2781d;
            if (objC == aVar) {
                return aVar;
            }
            sVar = this;
        } else {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            z3 = pVar.f1924i;
            fVar = pVar.f1923h;
            sVar = pVar.f1922g;
            l0.M(obj);
        }
        try {
            v1.h hVar = sVar.f1943c;
            f fVar2 = sVar.f1941a;
            if (hVar.isEmpty()) {
                throw new IllegalStateException("Not in a transaction");
            }
            n nVar = (n) v1.j.B0(hVar);
            if (z3) {
                nVar.getClass();
                if (hVar.isEmpty()) {
                    l3.h.y(fVar2, "END TRANSACTION");
                } else {
                    l3.h.y(fVar2, "RELEASE SAVEPOINT '" + nVar.f1915a + '\'');
                }
            } else if (hVar.isEmpty()) {
                l3.h.y(fVar2, "ROLLBACK TRANSACTION");
            } else {
                l3.h.y(fVar2, "ROLLBACK TRANSACTION TO SAVEPOINT '" + nVar.f1915a + '\'');
            }
            u1.k kVar = u1.k.f2301a;
            fVar.b(null);
            return kVar;
        } catch (Throwable th) {
            fVar.b(null);
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:43:0x009c  */
    /* JADX WARN: Code duplicated, block: B:47:0x00a8 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:58:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:60:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object g(x xVar, i2.p pVar, a2.c cVar) throws Throwable {
        q qVar;
        s sVar;
        s sVar2;
        int i4;
        SQLException e4;
        Throwable th;
        boolean z3;
        if (cVar instanceof q) {
            qVar = (q) cVar;
            int i5 = qVar.f1933l;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                qVar.f1933l = i5 - Integer.MIN_VALUE;
            } else {
                qVar = new q(this, cVar);
            }
        } else {
            qVar = new q(this, cVar);
        }
        Object objF = qVar.f1931j;
        int i6 = qVar.f1933l;
        z1.a aVar = z1.a.f2781d;
        try {
            if (i6 == 0) {
                l0.M(objF);
                if (xVar == null) {
                    xVar = x.f1729d;
                }
                qVar.f1928g = this;
                qVar.f1929h = (Serializable) pVar;
                qVar.f1933l = 1;
                if (e(xVar, qVar) != aVar) {
                    sVar = this;
                }
                return aVar;
            }
            if (i6 == 1) {
                pVar = (i2.p) qVar.f1929h;
                sVar = (s) qVar.f1928g;
                l0.M(objF);
            } else {
                if (i6 != 2) {
                    if (i6 == 3 || i6 == 4) {
                        Object obj = qVar.f1928g;
                        l0.M(objF);
                        return obj;
                    }
                    if (i6 != 5) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    th = (Throwable) qVar.f1929h;
                    th = (Throwable) qVar.f1928g;
                    try {
                        l0.M(objF);
                        throw th;
                    } catch (SQLException e5) {
                        e4 = e5;
                        if (th != null) {
                            throw e4;
                        }
                        l3.h.a(th, e4);
                        throw th;
                    }
                }
                i4 = qVar.f1930i;
                sVar2 = (s) qVar.f1928g;
                try {
                    l0.M(objF);
                    z3 = i4 != 0;
                    qVar.f1928g = objF;
                    qVar.f1933l = 3;
                    if (sVar2.f(z3, qVar) != aVar) {
                        return aVar;
                    }
                    return objF;
                } catch (Throwable th2) {
                    th = th2;
                    sVar = sVar2;
                    try {
                        throw th;
                    } catch (Throwable th3) {
                        try {
                            qVar.f1928g = th;
                            qVar.f1929h = th3;
                            qVar.f1933l = 5;
                            if (sVar.f(false, qVar) != aVar) {
                                throw th3;
                            }
                        } catch (SQLException e6) {
                            e4 = e6;
                            th = th3;
                            if (th != null) {
                                throw e4;
                            }
                            l3.h.a(th, e4);
                            throw th;
                        }
                    }
                }
            }
            m mVar = new m(0, sVar);
            qVar.f1928g = sVar;
            qVar.f1929h = null;
            qVar.f1930i = 1;
            qVar.f1933l = 2;
            objF = pVar.f(mVar, qVar);
            if (objF != aVar) {
                sVar2 = sVar;
                i4 = 1;
                if (i4 != 0) {
                }
                qVar.f1928g = objF;
                qVar.f1933l = 3;
                if (sVar2.f(z3, qVar) != aVar) {
                    return objF;
                }
            }
            return aVar;
        } catch (Throwable th4) {
            th = th4;
            throw th;
        }
    }
}
