package r2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a3.h f2039a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a3.h f2040b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a3.h f2041c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a3.h f2042d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a3.h f2043e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final a3.h f2044f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final a3.h f2045g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final a3.h f2046h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final i0 f2047i = new i0(false);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final i0 f2048j = new i0(true);

    static {
        int i4 = 10;
        f2039a = new a3.h(i4, "RESUME_TOKEN");
        f2040b = new a3.h(i4, "REMOVED_TASK");
        f2041c = new a3.h(i4, "CLOSED_EMPTY");
        f2042d = new a3.h(i4, "COMPLETING_ALREADY");
        f2043e = new a3.h(i4, "COMPLETING_WAITING_CHILDREN");
        f2044f = new a3.h(i4, "COMPLETING_RETRY");
        f2045g = new a3.h(i4, "TOO_LATE_TO_CANCEL");
        f2046h = new a3.h(i4, "SEALED");
    }

    public static final w2.c a(y1.h hVar) {
        if (hVar.k(t.f2027e) == null) {
            hVar = hVar.l(b());
        }
        return new w2.c(hVar);
    }

    public static x0 b() {
        return new x0(null);
    }

    public static k1 c() {
        return new k1(null);
    }

    public static final void d(y1.h hVar, CancellationException cancellationException) {
        v0 v0Var = (v0) hVar.k(t.f2027e);
        if (v0Var != null) {
            v0Var.b(cancellationException);
        }
    }

    public static void e(v vVar) {
        v0 v0Var = (v0) vVar.i().k(t.f2027e);
        if (v0Var != null) {
            v0Var.b(null);
        } else {
            throw new IllegalStateException(("Scope cannot be cancelled because it does not have a job: " + vVar).toString());
        }
    }

    public static final Object f(long j4, a2.i iVar) {
        if (j4 > 0) {
            i iVar2 = new i(1, z1.d.a(iVar));
            iVar2.v();
            if (j4 < Long.MAX_VALUE) {
                j(iVar2.f1988h).J(j4, iVar2);
            }
            Object objU = iVar2.u();
            if (objU == z1.a.f2781d) {
                return objU;
            }
        }
        return u1.k.f2301a;
    }

    public static final void g(y1.h hVar) {
        v0 v0Var = (v0) hVar.k(t.f2027e);
        if (v0Var != null && !v0Var.c()) {
            throw ((d1) v0Var).z();
        }
    }

    public static final y1.h h(y1.h hVar, y1.h hVar2, boolean z3) {
        Boolean bool = Boolean.FALSE;
        boolean zBooleanValue = ((Boolean) hVar.K(bool, new r1.b(1))).booleanValue();
        boolean zBooleanValue2 = ((Boolean) hVar2.K(bool, new r1.b(1))).booleanValue();
        if (!zBooleanValue && !zBooleanValue2) {
            return hVar.l(hVar2);
        }
        r1.b bVar = new r1.b(2);
        y1.i iVar = y1.i.f2726d;
        y1.h hVar3 = (y1.h) hVar.K(iVar, bVar);
        Object objK = hVar2;
        if (zBooleanValue2) {
            objK = hVar2.K(iVar, new r1.b(3));
        }
        return hVar3.l((y1.h) objK);
    }

    public static final s i(Executor executor) {
        return new q0(executor);
    }

    public static final a0 j(y1.h hVar) {
        y1.f fVarK = hVar.k(y1.d.f2725d);
        a0 a0Var = fVarK instanceof a0 ? (a0) fVarK : null;
        return a0Var == null ? z.f2053a : a0Var;
    }

    public static final String k(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    public static final i l(y1.c cVar) {
        i iVar;
        i iVar2;
        if (!(cVar instanceof w2.f)) {
            return new i(1, cVar);
        }
        w2.f fVar = (w2.f) cVar;
        a3.h hVar = w2.a.f2611c;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = w2.f.f2621k;
        loop0: while (true) {
            Object obj = atomicReferenceFieldUpdater.get(fVar);
            iVar = null;
            if (obj == null) {
                atomicReferenceFieldUpdater.set(fVar, hVar);
                iVar2 = null;
                break;
            }
            if (obj instanceof i) {
                do {
                    if (atomicReferenceFieldUpdater.compareAndSet(fVar, obj, hVar)) {
                        iVar2 = (i) obj;
                        break loop0;
                    }
                } while (atomicReferenceFieldUpdater.get(fVar) == obj);
            } else if (obj != hVar && !(obj instanceof Throwable)) {
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        }
        if (iVar2 != null) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = i.f1985j;
            Object obj2 = atomicReferenceFieldUpdater2.get(iVar2);
            if (!(obj2 instanceof p) || ((p) obj2).f2014d == null) {
                i.f1984i.set(iVar2, 536870911);
                atomicReferenceFieldUpdater2.set(iVar2, b.f1953a);
                iVar = iVar2;
            } else {
                iVar2.r();
            }
            if (iVar != null) {
                return iVar;
            }
        }
        return new i(2, cVar);
    }

    public static final void m(Throwable th, y1.h hVar) {
        try {
            s2.b bVar = (s2.b) hVar.k(t.f2026d);
            if (bVar != null) {
                bVar.S(th);
            } else {
                w2.a.d(th, hVar);
            }
        } catch (Throwable th2) {
            if (th != th2) {
                RuntimeException runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                l3.h.a(runtimeException, th);
                th = runtimeException;
            }
            w2.a.d(th, hVar);
        }
    }

    public static final g0 n(v0 v0Var, boolean z3, y0 y0Var) {
        if (v0Var instanceof d1) {
            return ((d1) v0Var).I(z3, y0Var);
        }
        boolean zK = y0Var.k();
        p.g gVar = new p.g(1, y0Var, y0.class, "invoke", "invoke(Ljava/lang/Throwable;)V", 0, 0, 1);
        return ((d1) v0Var).I(z3, zK ? new u0(gVar) : new h0(1, gVar));
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public static final Object o(ArrayList arrayList, a2.c cVar) {
        c cVar2;
        Iterator it;
        Object obj;
        z1.a aVar;
        if (cVar instanceof c) {
            cVar2 = (c) cVar;
            int i4 = cVar2.f1961i;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                cVar2.f1961i = i4 - Integer.MIN_VALUE;
            } else {
                cVar2 = new c(cVar);
            }
        } else {
            cVar2 = new c(cVar);
        }
        Object obj2 = cVar2.f1960h;
        int i5 = cVar2.f1961i;
        if (i5 == 0) {
            d0.l0.M(obj2);
            it = arrayList.iterator();
        } else {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = cVar2.f1959g;
            d0.l0.M(obj2);
        }
        do {
            boolean zHasNext = it.hasNext();
            obj = u1.k.f2301a;
            if (!zHasNext) {
                return obj;
            }
            v0 v0Var = (v0) it.next();
            cVar2.f1959g = it;
            cVar2.f1961i = 1;
            d1 d1Var = (d1) v0Var;
            d1Var.getClass();
            while (true) {
                Object obj3 = d1.f1971d.get(d1Var);
                boolean z3 = obj3 instanceof s0;
                aVar = z1.a.f2781d;
                if (!z3) {
                    y1.h hVar = cVar2.f42e;
                    j2.i.b(hVar);
                    g(hVar);
                    break;
                }
                if (d1Var.V(obj3) >= 0) {
                    i iVar = new i(1, z1.d.a(cVar2));
                    iVar.v();
                    iVar.y(new f(2, n(d1Var, true, new k(iVar, 1))));
                    Object objU = iVar.u();
                    if (objU != aVar) {
                        objU = obj;
                    }
                    if (objU != aVar) {
                        break;
                    }
                    obj = objU;
                    break;
                }
            }
        } while (obj != aVar);
        return aVar;
    }

    public static j1 p(v vVar, y1.h hVar, w wVar, i2.p pVar, int i4) {
        if ((i4 & 1) != 0) {
            hVar = y1.i.f2726d;
        }
        if ((i4 & 2) != 0) {
            wVar = w.f2033d;
        }
        y1.h hVarH = h(vVar.i(), hVar, true);
        y2.e eVar = e0.f1974a;
        if (hVarH != eVar && hVarH.k(y1.d.f2725d) == null) {
            hVarH = hVarH.l(eVar);
        }
        j1 e1Var = wVar == w.f2034e ? new e1(hVarH, pVar) : new j1(hVarH, true);
        e1Var.b0(wVar, e1Var, pVar);
        return e1Var;
    }

    public static final Object q(Object obj) {
        return obj instanceof q ? d0.l0.l(((q) obj).f2018a) : obj;
    }

    public static final void r(i iVar, y1.c cVar, boolean z3) {
        Object obj = i.f1985j.get(iVar);
        Throwable thD = iVar.d(obj);
        Object objL = thD != null ? d0.l0.l(thD) : iVar.f(obj);
        if (!z3) {
            cVar.j(objL);
            return;
        }
        j2.i.c(cVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTaskKt.resume>");
        w2.f fVar = (w2.f) cVar;
        a2.c cVar2 = fVar.f2623h;
        Object obj2 = fVar.f2625j;
        y1.h hVarG = cVar2.g();
        Object objL2 = w2.a.l(hVarG, obj2);
        p1 p1VarV = objL2 != w2.a.f2612d ? v(cVar2, hVarG, objL2) : null;
        try {
            cVar2.j(objL);
        } finally {
            if (p1VarV == null || p1VarV.c0()) {
                w2.a.g(hVarG, objL2);
            }
        }
    }

    public static final Object s(y1.h hVar, i2.p pVar) throws Throwable {
        o0 o0VarA;
        y1.h hVarH;
        Thread threadCurrentThread = Thread.currentThread();
        y1.g gVar = y1.d.f2725d;
        y1.e eVar = (y1.e) hVar.k(gVar);
        y1.i iVar = y1.i.f2726d;
        if (eVar == null) {
            o0VarA = l1.a();
            hVarH = h(iVar, hVar.l(o0VarA), true);
            y2.e eVar2 = e0.f1974a;
            if (hVarH != eVar2 && hVarH.k(gVar) == null) {
                hVarH = hVarH.l(eVar2);
            }
        } else {
            if (eVar instanceof o0) {
            }
            o0VarA = (o0) l1.f1998a.get();
            hVarH = h(iVar, hVar, true);
            y2.e eVar3 = e0.f1974a;
            if (hVarH != eVar3 && hVarH.k(gVar) == null) {
                hVarH = hVarH.l(eVar3);
            }
        }
        d dVar = new d(hVarH, threadCurrentThread, o0VarA);
        dVar.b0(w.f2033d, dVar, pVar);
        o0 o0Var = dVar.f1970h;
        if (o0Var != null) {
            int i4 = o0.f2006i;
            o0Var.Z(false);
        }
        while (!Thread.interrupted()) {
            try {
                long jA0 = o0Var != null ? o0Var.a0() : Long.MAX_VALUE;
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = d1.f1971d;
                if (!(atomicReferenceFieldUpdater.get(dVar) instanceof s0)) {
                    if (o0Var != null) {
                        int i5 = o0.f2006i;
                        o0Var.W(false);
                    }
                    Object objU = u(atomicReferenceFieldUpdater.get(dVar));
                    q qVar = objU instanceof q ? (q) objU : null;
                    if (qVar == null) {
                        return objU;
                    }
                    throw qVar.f2018a;
                }
                LockSupport.parkNanos(dVar, jA0);
            } catch (Throwable th) {
                if (o0Var != null) {
                    int i6 = o0.f2006i;
                    o0Var.W(false);
                }
                throw th;
            }
        }
        InterruptedException interruptedException = new InterruptedException();
        dVar.r(interruptedException);
        throw interruptedException;
    }

    public static final String t(y1.c cVar) {
        Object objL;
        if (cVar instanceof w2.f) {
            return ((w2.f) cVar).toString();
        }
        try {
            objL = cVar + '@' + k(cVar);
        } catch (Throwable th) {
            objL = d0.l0.l(th);
        }
        if (u1.h.a(objL) != null) {
            objL = cVar.getClass().getName() + '@' + k(cVar);
        }
        return (String) objL;
    }

    public static final Object u(Object obj) {
        s0 s0Var;
        t0 t0Var = obj instanceof t0 ? (t0) obj : null;
        return (t0Var == null || (s0Var = t0Var.f2028a) == null) ? obj : s0Var;
    }

    public static final p1 v(y1.c cVar, y1.h hVar, Object obj) {
        p1 p1Var = null;
        if ((cVar instanceof a2.d) && hVar.k(q1.f2020d) != null) {
            a2.d dVarE = (a2.d) cVar;
            while (!(dVarE instanceof b0) && (dVarE = dVarE.e()) != null) {
                if (dVarE instanceof p1) {
                    p1Var = (p1) dVarE;
                    break;
                }
            }
            if (p1Var != null) {
                p1Var.d0(hVar, obj);
            }
        }
        return p1Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Object w(y1.h hVar, i2.p pVar, a2.c cVar) {
        y1.h hVarG = cVar.g();
        y1.h hVarL = !((Boolean) hVar.K(Boolean.FALSE, new r1.b(1))).booleanValue() ? hVarG.l(hVar) : h(hVarG, hVar, false);
        g(hVarL);
        if (hVarL == hVarG) {
            w2.q qVar = new w2.q(cVar, hVarL);
            return z1.d.b(qVar, qVar, pVar);
        }
        y1.d dVar = y1.d.f2725d;
        if (j2.i.a(hVarL.k(dVar), hVarG.k(dVar))) {
            p1 p1Var = new p1(hVarL, cVar);
            y1.h hVar2 = p1Var.f1948f;
            Object objL = w2.a.l(hVar2, null);
            try {
                return z1.d.b(p1Var, p1Var, pVar);
            } finally {
                w2.a.g(hVar2, objL);
            }
        }
        b0 b0Var = new b0(cVar, hVarL);
        try {
            w2.a.h(u1.k.f2301a, z1.d.a(((a2.a) pVar).i(b0Var, b0Var)));
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = b0.f1954h;
            do {
                int i4 = atomicIntegerFieldUpdater.get(b0Var);
                if (i4 != 0) {
                    if (i4 != 2) {
                        throw new IllegalStateException("Already suspended");
                    }
                    Object objU = u(d1.f1971d.get(b0Var));
                    if (objU instanceof q) {
                        throw ((q) objU).f2018a;
                    }
                    return objU;
                }
            } while (!atomicIntegerFieldUpdater.compareAndSet(b0Var, 0, 1));
            return z1.a.f2781d;
        } catch (Throwable th) {
            b0Var.j(d0.l0.l(th));
            throw th;
        }
    }

    public static final Object x(long j4, i2.p pVar, a2.c cVar) throws Throwable {
        Object qVar;
        Object objN;
        if (j4 <= 0) {
            throw new m1("Timed out immediately", null);
        }
        n1 n1Var = new n1(j4, cVar);
        n(n1Var, true, new h0(0, j(n1Var.f2647g.g()).A(n1Var.f2005h, n1Var, n1Var.f1948f)));
        try {
            j2.q.a(2, pVar);
            qVar = pVar.f(n1Var, n1Var);
        } catch (Throwable th) {
            qVar = new q(th, false);
        }
        Object obj = z1.a.f2781d;
        if (qVar == obj || (objN = n1Var.N(qVar)) == f2043e) {
            return obj;
        }
        if (objN instanceof q) {
            Throwable th2 = ((q) objN).f2018a;
            if (!(th2 instanceof m1) || ((m1) th2).f2001d != n1Var) {
                throw th2;
            }
            if (qVar instanceof q) {
                throw ((q) qVar).f2018a;
            }
        } else {
            qVar = u(objN);
        }
        return qVar;
    }

    public static final Object y(long j4, d0.u uVar, r.d dVar) {
        long jA;
        a1.a aVar = q2.a.f1800d;
        long jB = 0;
        boolean z3 = j4 > 0;
        if (z3) {
            long jO = d0.l0.O(999999L, q2.c.NANOSECONDS);
            int i4 = ((int) j4) & 1;
            if (i4 != (((int) jO) & 1)) {
                jA = i4 == 1 ? q2.a.a(j4 >> 1, jO >> 1) : q2.a.a(jO >> 1, j4 >> 1);
            } else if (i4 == 0) {
                long j5 = (j4 >> 1) + (jO >> 1);
                if (-4611686018426999999L > j5 || j5 >= 4611686018427000000L) {
                    jA = d0.l0.p(j5 / ((long) 1000000));
                } else {
                    jA = j5 << 1;
                    int i5 = q2.b.f1803a;
                }
            } else {
                long jA2 = d0.l0.a(j4 >> 1, jO >> 1);
                if (jA2 == 9223372036854759646L) {
                    throw new IllegalArgumentException("Summing infinite durations of different signs yields an undefined result.");
                }
                if (jA2 == 4611686018427387903L || jA2 == -4611686018427387903L) {
                    jA = d0.l0.p(jA2);
                } else if (-4611686018426L > jA2 || jA2 >= 4611686018427L) {
                    jA = d0.l0.p(d0.l0.j(jA2, -4611686018427387903L, 4611686018427387903L));
                } else {
                    jA = (jA2 * ((long) 1000000)) << 1;
                    int i6 = q2.b.f1803a;
                }
            }
            jB = ((((int) jA) & 1) != 1 || jA == q2.a.f1801e || jA == q2.a.f1802f) ? q2.a.b(jA, q2.c.MILLISECONDS) : jA >> 1;
        } else if (z3) {
            throw new a0.c();
        }
        return x(jB, uVar, dVar);
    }
}
