package w2;

import d0.l0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import r2.d1;
import r2.l1;
import r2.o0;
import r2.p1;
import r2.v0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a3.h f2609a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a3.h f2610b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a3.h f2611c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a3.h f2612d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final r1.b f2613e = new r1.b(5);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final r1.b f2614f = new r1.b(6);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final r1.b f2615g = new r1.b(7);

    static {
        int i4 = 10;
        f2609a = new a3.h(i4, "CLOSED");
        f2610b = new a3.h(i4, "UNDEFINED");
        f2611c = new a3.h(i4, "REUSABLE_CLAIMED");
        f2612d = new a3.h(i4, "NO_THREAD_ELEMENTS");
    }

    public static final void a(int i4) {
        if (i4 < 1) {
            throw new IllegalArgumentException(a1.c.c(i4, "Expected positive parallelism level, but got ").toString());
        }
    }

    public static final Object b(r rVar, long j4, i2.p pVar) {
        while (true) {
            if (rVar.f2649c >= j4 && !rVar.c()) {
                return rVar;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = b.f2616a;
            Object obj = atomicReferenceFieldUpdater.get(rVar);
            a3.h hVar = f2609a;
            if (obj == hVar) {
                return hVar;
            }
            r rVar2 = (r) ((b) obj);
            if (rVar2 == null) {
                rVar2 = (r) pVar.f(Long.valueOf(rVar.f2649c + 1), rVar);
                do {
                    if (atomicReferenceFieldUpdater.compareAndSet(rVar, null, rVar2)) {
                        if (rVar.c()) {
                            rVar.d();
                        }
                    }
                } while (atomicReferenceFieldUpdater.get(rVar) == null);
            }
            rVar = rVar2;
        }
    }

    public static final r c(Object obj) {
        if (obj != f2609a) {
            return (r) obj;
        }
        throw new IllegalStateException("Does not contain segment");
    }

    public static final void d(Throwable th, y1.h hVar) {
        Throwable runtimeException;
        Iterator it = d.f2619a.iterator();
        while (it.hasNext()) {
            try {
                ((s2.b) it.next()).S(th);
            } catch (Throwable th2) {
                if (th == th2) {
                    runtimeException = th;
                } else {
                    runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                    l3.h.a(runtimeException, th);
                }
                Thread threadCurrentThread = Thread.currentThread();
                threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, runtimeException);
            }
        }
        try {
            l3.h.a(th, new e(hVar));
        } catch (Throwable unused) {
        }
        Thread threadCurrentThread2 = Thread.currentThread();
        threadCurrentThread2.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread2, th);
    }

    public static final boolean e(Object obj) {
        return obj == f2609a;
    }

    public static final Object f(Object obj, Object obj2) {
        if (obj == null) {
            return obj2;
        }
        if (obj instanceof ArrayList) {
            ((ArrayList) obj).add(obj2);
            return obj;
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(obj);
        arrayList.add(obj2);
        return arrayList;
    }

    public static final void g(y1.h hVar, Object obj) {
        if (obj == f2612d) {
            return;
        }
        if (!(obj instanceof x)) {
            Object objK = hVar.K(null, f2614f);
            j2.i.c(objK, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
            ((u) objK).a(obj);
            return;
        }
        x xVar = (x) obj;
        u[] uVarArr = xVar.f2660c;
        int length = uVarArr.length - 1;
        if (length < 0) {
            return;
        }
        while (true) {
            int i4 = length - 1;
            u uVar = uVarArr[length];
            j2.i.b(uVar);
            uVar.a(xVar.f2659b[length]);
            if (i4 < 0) {
                return;
            } else {
                length = i4;
            }
        }
    }

    public static final void h(Object obj, y1.c cVar) {
        if (!(cVar instanceof f)) {
            cVar.j(obj);
            return;
        }
        f fVar = (f) cVar;
        r2.s sVar = fVar.f2622g;
        a2.c cVar2 = fVar.f2623h;
        Throwable thA = u1.h.a(obj);
        Object qVar = thA == null ? obj : new r2.q(thA, false);
        if (sVar.U(cVar2.g())) {
            fVar.f2624i = qVar;
            fVar.f1962f = 1;
            sVar.S(cVar2.g(), fVar);
            return;
        }
        o0 o0VarA = l1.a();
        if (o0VarA.f2007f >= 4294967296L) {
            fVar.f2624i = qVar;
            fVar.f1962f = 1;
            o0VarA.X(fVar);
            return;
        }
        o0VarA.Z(true);
        try {
            v0 v0Var = (v0) cVar2.g().k(r2.t.f2027e);
            if (v0Var == null || v0Var.c()) {
                Object obj2 = fVar.f2625j;
                y1.h hVarG = cVar2.g();
                Object objL = l(hVarG, obj2);
                p1 p1VarV = objL != f2612d ? r2.x.v(cVar2, hVarG, objL) : null;
                try {
                    cVar2.j(obj);
                    if (p1VarV == null || p1VarV.c0()) {
                        g(hVarG, objL);
                    }
                } catch (Throwable th) {
                    if (p1VarV == null || p1VarV.c0()) {
                        g(hVarG, objL);
                    }
                    throw th;
                }
            } else {
                fVar.j(l0.l(((d1) v0Var).z()));
            }
            while (o0VarA.b0()) {
            }
        } catch (Throwable th2) {
            try {
                fVar.h(th2);
            } finally {
                o0VarA.W(true);
            }
        }
    }

    public static final long i(String str, long j4, long j5, long j6) {
        String property;
        boolean z3;
        String str2;
        Long lValueOf;
        int i4 = t.f2651a;
        try {
            property = System.getProperty(str);
        } catch (SecurityException unused) {
            property = null;
        }
        if (property == null) {
            return j4;
        }
        l0.h(10);
        int length = property.length();
        if (length == 0) {
            str2 = property;
            lValueOf = null;
        } else {
            int i5 = 0;
            char cCharAt = property.charAt(0);
            long j7 = -9223372036854775807L;
            if (j2.i.f(cCharAt, 48) < 0) {
                z3 = true;
                if (length != 1) {
                    if (cCharAt != '+') {
                        if (cCharAt == '-') {
                            j7 = Long.MIN_VALUE;
                            i5 = 1;
                        }
                        lValueOf = null;
                    } else {
                        z3 = false;
                        i5 = 1;
                    }
                }
                str2 = property;
                lValueOf = null;
            } else {
                z3 = false;
            }
            long j8 = 0;
            long j9 = -256204778801521550L;
            while (true) {
                if (i5 >= length) {
                    str2 = property;
                    lValueOf = z3 ? Long.valueOf(j8) : Long.valueOf(-j8);
                } else {
                    int iDigit = Character.digit((int) property.charAt(i5), 10);
                    if (iDigit >= 0) {
                        if (j8 < j9) {
                            if (j9 == -256204778801521550L) {
                                str2 = property;
                                j9 = j7 / ((long) 10);
                                if (j8 < j9) {
                                }
                            }
                            lValueOf = null;
                        } else {
                            str2 = property;
                        }
                        long j10 = j8 * ((long) 10);
                        long j11 = iDigit;
                        if (j10 < j7 + j11) {
                            lValueOf = null;
                        } else {
                            j8 = j10 - j11;
                            i5++;
                            property = str2;
                        }
                    }
                    str2 = property;
                    lValueOf = null;
                }
            }
        }
        if (lValueOf == null) {
            throw new IllegalStateException(("System property '" + str + "' has unrecognized value '" + str2 + '\'').toString());
        }
        long jLongValue = lValueOf.longValue();
        if (j5 <= jLongValue && jLongValue <= j6) {
            return jLongValue;
        }
        throw new IllegalStateException(("System property '" + str + "' should be in range " + j5 + ".." + j6 + ", but is '" + jLongValue + '\'').toString());
    }

    public static int j(String str, int i4, int i5) {
        return (int) i(str, i4, 1, (i5 & 8) != 0 ? Integer.MAX_VALUE : 2097150);
    }

    public static final Object k(y1.h hVar) {
        Object objK = hVar.K(0, f2613e);
        j2.i.b(objK);
        return objK;
    }

    public static final Object l(y1.h hVar, Object obj) {
        if (obj == null) {
            obj = k(hVar);
        }
        if (obj == 0) {
            return f2612d;
        }
        return obj instanceof Integer ? hVar.K(new x(((Number) obj).intValue(), hVar), f2615g) : ((u) obj).d(hVar);
    }
}
