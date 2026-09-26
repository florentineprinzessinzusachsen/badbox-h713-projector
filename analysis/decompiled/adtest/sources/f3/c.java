package f3;

import a3.a0;
import a3.b0;
import a3.c0;
import a3.d0;
import a3.f0;
import a3.r;
import a3.u;
import d0.l0;
import e3.p;
import java.io.IOException;
import java.net.ProtocolException;
import q3.n;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c implements u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c f894a = new c();

    /* JADX WARN: Code duplicated, block: B:114:0x01e1 A[Catch: IOException -> 0x00ec, TryCatch #10 {IOException -> 0x00ec, blocks: (B:64:0x00e2, B:68:0x00ef, B:82:0x0141, B:88:0x014f, B:89:0x0156, B:91:0x0159, B:94:0x0161, B:99:0x016c, B:107:0x01c1, B:109:0x01d1, B:112:0x01db, B:119:0x01f0, B:122:0x01fd, B:123:0x0221, B:114:0x01e1, B:106:0x01b0, B:125:0x0223, B:126:0x0226, B:76:0x0118, B:101:0x018b, B:105:0x0197), top: B:150:0x00e2, inners: #5 }] */
    /* JADX WARN: Code duplicated, block: B:132:0x022f A[ADDED_TO_REGION, REMOVE] */
    /* JADX WARN: Code duplicated, block: B:60:0x00db  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v10 */
    /* JADX WARN: Type inference failed for: r14v11 */
    /* JADX WARN: Type inference failed for: r14v12 */
    /* JADX WARN: Type inference failed for: r14v13 */
    /* JADX WARN: Type inference failed for: r14v14 */
    /* JADX WARN: Type inference failed for: r14v15 */
    /* JADX WARN: Type inference failed for: r14v16 */
    /* JADX WARN: Type inference failed for: r14v17 */
    /* JADX WARN: Type inference failed for: r14v18 */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r14v4, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r14v6 */
    /* JADX WARN: Type inference failed for: r14v7 */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Type inference failed for: r14v9 */
    @Override // a3.u
    public final d0 a(i iVar) throws IOException {
        c0 c0VarD;
        ?? r14;
        IOException iOException;
        ?? r15;
        d0 d0VarA;
        int i4;
        r rVar;
        f0 f0Var;
        int i5;
        d0 d0VarA2;
        e3.h hVar = iVar.f900d;
        j2.i.b(hVar);
        p pVar = (p) hVar.f750e;
        g gVar = (g) hVar.f752g;
        a0 a0Var = iVar.f901e;
        r rVar2 = (r) a0Var.f64d;
        b0 b0Var = (b0) a0Var.f65e;
        long jCurrentTimeMillis = System.currentTimeMillis();
        boolean z3 = false;
        ?? r16 = 1;
        ?? r17 = 1;
        boolean z4 = a.a.y(a0Var.f62b) && b0Var != null;
        boolean zEqualsIgnoreCase = "upgrade".equalsIgnoreCase(rVar2.a("Connection"));
        try {
            try {
                gVar.j(a0Var);
                try {
                    if (z4) {
                        try {
                            if ("100-continue".equalsIgnoreCase(rVar2.a("Expect"))) {
                                try {
                                    gVar.c();
                                    c0VarD = hVar.d(true);
                                } catch (IOException e4) {
                                    hVar.f(e4);
                                    throw e4;
                                }
                            } else {
                                c0VarD = null;
                            }
                            if (c0VarD == null) {
                                try {
                                    b0Var.getClass();
                                    j2.i.b(b0Var);
                                    try {
                                        long jA = b0Var.a();
                                        try {
                                            r17 = "upgrade";
                                            n nVar = new n(new e3.f(hVar, gVar.e(a0Var, jA), jA, false));
                                            b0Var.c(nVar);
                                            nVar.close();
                                        } catch (IOException e5) {
                                            e = e5;
                                            r14 = "upgrade";
                                            if (!(e instanceof h3.a) || !hVar.f749d) {
                                                throw e;
                                            }
                                            iOException = e;
                                        }
                                    } catch (IOException e6) {
                                        e = e6;
                                        r14 = "upgrade";
                                    }
                                } catch (IOException e7) {
                                    e = e7;
                                    r14 = "upgrade";
                                }
                            } else {
                                r17 = "upgrade";
                                pVar.i(hVar, true, false, false, false, null);
                                if (!(hVar.c().f792i != null)) {
                                    gVar.i().g();
                                }
                            }
                        } catch (IOException e8) {
                            e = e8;
                            r16 = "upgrade";
                            c0VarD = null;
                            r14 = r16;
                            if (!(e instanceof h3.a)) {
                                throw e;
                            }
                            throw e;
                        }
                    } else {
                        r17 = "upgrade";
                        pVar.i(hVar, true, false, false, false, null);
                        c0VarD = null;
                    }
                    try {
                        gVar.a();
                        iOException = null;
                        r15 = r17;
                        while (true) {
                            rVar = d0VarA.f108i;
                            f0Var = d0VarA.f109j;
                            if (i4 != 100 && (102 > i4 || i4 >= 200)) {
                                break;
                            }
                            c0 c0VarD2 = hVar.d(false);
                            j2.i.b(c0VarD2);
                            c0VarD2.f88a = a0Var;
                            c0VarD2.f92e = hVar.c().f789f;
                            c0VarD2.f99l = jCurrentTimeMillis;
                            c0VarD2.f100m = System.currentTimeMillis();
                            d0VarA = c0VarD2.a();
                            i4 = d0VarA.f106g;
                        }
                    } catch (IOException e9) {
                        hVar.f(e9);
                        throw e9;
                    }
                } catch (IOException e10) {
                    e = e10;
                    r14 = r17;
                    if (!(e instanceof h3.a)) {
                        throw e;
                    }
                    throw e;
                }
            } catch (IOException e11) {
                hVar.f(e11);
                throw e11;
            }
        } catch (IOException e12) {
            e = e12;
        }
        if (c0VarD == null) {
            try {
                r15 = r14;
                c0VarD = hVar.d(false);
                j2.i.b(c0VarD);
            } catch (IOException e13) {
                if (iOException == null) {
                    throw e13;
                }
                l3.h.a(iOException, e13);
                throw iOException;
            }
        }
        r15 = r14;
        c0 c0Var = c0VarD;
        c0Var.f88a = a0Var;
        c0Var.f92e = hVar.c().f789f;
        c0Var.f99l = jCurrentTimeMillis;
        c0Var.f100m = System.currentTimeMillis();
        d0VarA = c0Var.a();
        i4 = d0VarA.f106g;
        boolean z5 = i4 == 101;
        if (z5) {
            if (hVar.c().f792i != null) {
                throw new ProtocolException("Unexpected 101 code on HTTP/2 connection");
            }
        }
        if (z5) {
            String strA = rVar.a("Connection");
            if (strA == null) {
                strA = null;
            }
            if (r15.equalsIgnoreCase(strA)) {
                z3 = true;
            }
        }
        if (zEqualsIgnoreCase && z3) {
            c0 c0VarB = d0VarA.b();
            c0VarB.f94g = new b3.b(f0Var.c(), f0Var.b());
            c0VarB.f95h = hVar.g();
            d0VarA2 = c0VarB.a();
            i5 = i4;
        } else {
            try {
                String strA2 = rVar.a("Content-Type");
                String str = strA2 == null ? null : strA2;
                long jF = gVar.f(d0VarA);
                i5 = i4;
                j jVar = new j(str, jF, l0.f(new e3.g(hVar, gVar.g(d0VarA), jF, false)));
                c0 c0VarB2 = d0VarA.b();
                c0VarB2.f94g = jVar;
                c0VarB2.f102o = new b();
                d0VarA2 = c0VarB2.a();
            } catch (IOException e14) {
                hVar.f(e14);
                throw e14;
            }
        }
        if ("close".equalsIgnoreCase(((r) d0VarA2.f103d.f64d).a("Connection"))) {
            gVar.i().g();
        } else {
            String strA3 = d0VarA2.f108i.a("Connection");
            if (strA3 == null) {
                strA3 = null;
            }
            if ("close".equalsIgnoreCase(strA3)) {
                gVar.i().g();
            }
        }
        if ((i5 != 204 && i5 != 205) || d0VarA2.f109j.b() <= 0) {
            return d0VarA2;
        }
        throw new ProtocolException("HTTP " + i5 + " had non-zero Content-Length: " + d0VarA2.f109j.b());
    }
}
