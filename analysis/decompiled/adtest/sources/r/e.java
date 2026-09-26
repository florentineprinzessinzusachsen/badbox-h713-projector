package r;

import android.database.SQLException;
import d0.l0;
import d0.u;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicBoolean;
import r2.m1;
import r2.x;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e implements b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final k f1878d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final k f1879e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ThreadLocal f1880f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final AtomicBoolean f1881g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f1882h;

    public e(c3.b bVar) {
        this.f1880f = new ThreadLocal();
        this.f1881g = new AtomicBoolean(false);
        a1.a aVar = q2.a.f1800d;
        this.f1882h = l0.N(30, q2.c.SECONDS);
        k kVar = new k(1, new a3.o(6, bVar));
        this.f1878d = kVar;
        this.f1879e = kVar;
    }

    public final void b(boolean z3) {
        String str = z3 ? "reader" : "writer";
        StringBuilder sb = new StringBuilder();
        sb.append("Timed out attempting to acquire a " + str + " connection.");
        sb.append("\n\nWriter pool:\n");
        this.f1879e.c(sb);
        sb.append("Reader pool:");
        sb.append('\n');
        this.f1878d.c(sb);
        l3.h.m0(5, sb.toString());
        throw null;
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        if (this.f1881g.compareAndSet(false, true)) {
            this.f1878d.b();
            this.f1879e.b();
        }
    }

    /* JADX WARN: Code duplicated, block: B:125:0x01b0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x001b  */
    /* JADX WARN: Code duplicated, block: B:91:0x01a6 A[Catch: all -> 0x01be, TRY_LEAVE, TryCatch #9 {all -> 0x01be, blocks: (B:89:0x01a0, B:91:0x01a6, B:93:0x01b0, B:94:0x01b5), top: B:138:0x01a0 }] */
    @Override // r.b
    public final Object x(boolean z3, i2.p pVar, a2.c cVar) {
        d dVar;
        j2.n nVar;
        Throwable th;
        k kVar;
        j2.n nVar2;
        k kVar2;
        j2.n nVar3;
        e eVar;
        i2.p pVar2;
        j2.n nVar4;
        boolean z4;
        j2.n nVar5;
        i2.p pVar3;
        s sVar;
        s sVar2;
        boolean z5 = z3;
        i2.p pVar4 = pVar;
        if (cVar instanceof d) {
            dVar = (d) cVar;
            int i4 = dVar.f1877p;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                dVar.f1877p = i4 - Integer.MIN_VALUE;
            } else {
                dVar = new d(this, cVar);
            }
        } else {
            dVar = new d(this, cVar);
        }
        y1.h hVar = dVar.f42e;
        Object objW = dVar.f1875n;
        int i5 = dVar.f1877p;
        y1.c cVar2 = null;
        z1.a aVar = z1.a.f2781d;
        try {
            if (i5 == 0) {
                l0.M(objW);
                if (this.f1881g.get()) {
                    l3.h.m0(21, "Connection pool is closed");
                    throw null;
                }
                ThreadLocal threadLocal = this.f1880f;
                s sVar3 = (s) threadLocal.get();
                a1.a aVar2 = a.f1863e;
                if (sVar3 == null) {
                    j2.i.b(hVar);
                    a aVar3 = (a) hVar.k(aVar2);
                    sVar3 = aVar3 != null ? aVar3.f1864d : null;
                }
                if (sVar3 == null) {
                    k kVar3 = z5 ? this.f1878d : this.f1879e;
                    nVar = new j2.n();
                    try {
                        j2.i.b(hVar);
                        nVar2 = new j2.n();
                        try {
                            long j4 = this.f1882h;
                            u uVar = new u(nVar2, kVar3, cVar2, 4);
                            dVar.f1868g = this;
                            dVar.f1869h = (Serializable) pVar4;
                            dVar.f1870i = kVar3;
                            dVar.f1871j = nVar;
                            dVar.f1872k = hVar;
                            dVar.f1873l = nVar2;
                            dVar.f1874m = z5;
                            dVar.f1877p = 3;
                            if (x.y(j4, uVar, dVar) != aVar) {
                                pVar2 = pVar4;
                                nVar4 = nVar2;
                                kVar2 = kVar3;
                                nVar3 = nVar;
                                eVar = this;
                                j2.n nVar6 = nVar4;
                                z4 = z5;
                                nVar5 = nVar3;
                                pVar3 = pVar2;
                                nVar2 = nVar6;
                                th = null;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            kVar2 = kVar3;
                            nVar3 = nVar;
                            eVar = this;
                            i2.p pVar5 = pVar4;
                            z4 = z5;
                            nVar5 = nVar3;
                            pVar3 = pVar5;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        kVar = kVar3;
                        throw th;
                    }
                } else {
                    if (!z5 && sVar3.f1942b) {
                        l3.h.m0(1, "Cannot upgrade connection from reader to writer");
                        throw null;
                    }
                    j2.i.b(hVar);
                    if (hVar.k(aVar2) == null) {
                        a aVar4 = new a(sVar3);
                        j2.i.e(threadLocal, "<this>");
                        y1.h hVarY = l3.h.Y(aVar4, new w2.u(sVar3, threadLocal));
                        h0.f fVar = new h0.f(pVar4, sVar3, cVar2, 3);
                        dVar.f1877p = 1;
                        Object objW2 = x.w(hVarY, fVar, dVar);
                        if (objW2 != aVar) {
                            return objW2;
                        }
                    } else {
                        dVar.f1877p = 2;
                        Object objF = pVar4.f(sVar3, dVar);
                        if (objF != aVar) {
                            return objF;
                        }
                    }
                }
                return aVar;
            }
            if (i5 == 1) {
                l0.M(objW);
                return objW;
            }
            if (i5 == 2) {
                l0.M(objW);
                return objW;
            }
            if (i5 != 3) {
                if (i5 != 4) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                nVar5 = (j2.n) dVar.f1869h;
                kVar = (k) dVar.f1868g;
                try {
                    l0.M(objW);
                    try {
                        sVar2 = (s) nVar5.f1276d;
                        if (sVar2 != null) {
                            if (sVar2.f1944d.compareAndSet(false, true)) {
                                try {
                                    l3.h.y(sVar2.f1941a, "ROLLBACK TRANSACTION");
                                } catch (SQLException unused) {
                                }
                            }
                            f fVar2 = sVar2.f1941a;
                            fVar2.f1885f = null;
                            fVar2.f1886g = null;
                            kVar.d(fVar2);
                        }
                    } catch (Throwable unused2) {
                    }
                    return objW;
                } catch (Throwable th4) {
                    th = th4;
                    nVar = nVar5;
                    th = th;
                    try {
                        throw th;
                    } catch (Throwable th5) {
                        try {
                            s sVar4 = (s) nVar.f1276d;
                            if (sVar4 == null) {
                                throw th5;
                            }
                            if (sVar4.f1944d.compareAndSet(false, true)) {
                                try {
                                    l3.h.y(sVar4.f1941a, "ROLLBACK TRANSACTION");
                                } catch (SQLException unused3) {
                                }
                            }
                            f fVar3 = sVar4.f1941a;
                            fVar3.f1885f = null;
                            fVar3.f1886g = null;
                            kVar.d(fVar3);
                            throw th5;
                        } catch (Throwable th6) {
                            l3.h.a(th, th6);
                            throw th5;
                        }
                    }
                }
            }
            z5 = dVar.f1874m;
            nVar4 = dVar.f1873l;
            hVar = dVar.f1872k;
            nVar3 = dVar.f1871j;
            kVar2 = dVar.f1870i;
            pVar2 = (i2.p) dVar.f1869h;
            eVar = (e) dVar.f1868g;
            try {
                l0.M(objW);
                j2.n nVar7 = nVar4;
                z4 = z5;
                nVar5 = nVar3;
                pVar3 = pVar2;
                nVar2 = nVar7;
                th = null;
            } catch (Throwable th7) {
                th = th7;
                nVar2 = nVar4;
                pVar4 = pVar2;
                i2.p pVar6 = pVar4;
                z4 = z5;
                nVar5 = nVar3;
                pVar3 = pVar6;
            }
            f fVar4 = (f) nVar2.f1276d;
            if (fVar4 != null) {
                j2.i.e(hVar, "context");
                fVar4.f1885f = hVar;
                fVar4.f1886g = new Throwable();
                sVar = new s(fVar4, eVar.f1878d != eVar.f1879e && z4);
            } else {
                sVar = null;
            }
            nVar5.f1276d = sVar;
            if (th instanceof m1) {
                eVar.b(z4);
                throw null;
            }
            if (th != null) {
                throw th;
            }
            if (sVar == null) {
                throw new IllegalArgumentException("Required value was null.");
            }
            eVar.getClass();
            a aVar5 = new a(sVar);
            ThreadLocal threadLocal2 = eVar.f1880f;
            j2.i.e(threadLocal2, "<this>");
            y1.h hVarY2 = l3.h.Y(aVar5, new w2.u(sVar, threadLocal2));
            h0.f fVar5 = new h0.f(pVar3, nVar5, cVar2, 4);
            dVar.f1868g = kVar2;
            dVar.f1869h = nVar5;
            dVar.f1870i = null;
            dVar.f1871j = null;
            dVar.f1872k = null;
            dVar.f1873l = null;
            dVar.f1877p = 4;
            objW = x.w(hVarY2, fVar5, dVar);
            if (objW != aVar) {
                kVar = kVar2;
                sVar2 = (s) nVar5.f1276d;
                if (sVar2 != null) {
                    if (sVar2.f1944d.compareAndSet(false, true)) {
                        l3.h.y(sVar2.f1941a, "ROLLBACK TRANSACTION");
                    }
                    f fVar6 = sVar2.f1941a;
                    fVar6.f1885f = null;
                    fVar6.f1886g = null;
                    kVar.d(fVar6);
                }
                return objW;
            }
            return aVar;
        } catch (Throwable th8) {
            th = th8;
            nVar = nVar5;
            kVar = kVar2;
            th = th;
            throw th;
        }
    }

    public e(final c3.b bVar, final String str, int i4) {
        j2.i.e(str, "fileName");
        this.f1880f = new ThreadLocal();
        final int i5 = 0;
        this.f1881g = new AtomicBoolean(false);
        a1.a aVar = q2.a.f1800d;
        this.f1882h = l0.N(30, q2.c.SECONDS);
        if (i4 > 0) {
            this.f1878d = new k(i4, new i2.a() { // from class: r.c
                @Override // i2.a
                public final Object a() {
                    switch (i5) {
                        case 0:
                            w.a aVarA = bVar.a(str);
                            l3.h.y(aVarA, "PRAGMA query_only = 1");
                            return aVarA;
                        default:
                            return bVar.a(str);
                    }
                }
            });
            final int i6 = 1;
            this.f1879e = new k(1, new i2.a() { // from class: r.c
                @Override // i2.a
                public final Object a() {
                    switch (i6) {
                        case 0:
                            w.a aVarA = bVar.a(str);
                            l3.h.y(aVarA, "PRAGMA query_only = 1");
                            return aVarA;
                        default:
                            return bVar.a(str);
                    }
                }
            });
            return;
        }
        throw new IllegalArgumentException("Maximum number of readers must be greater than 0");
    }
}
