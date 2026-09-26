package r2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class d1 implements v0, i1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f1971d = AtomicReferenceFieldUpdater.newUpdater(d1.class, Object.class, "_state$volatile");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f1972e = AtomicReferenceFieldUpdater.newUpdater(d1.class, Object.class, "_parentHandle$volatile");
    private volatile /* synthetic */ Object _parentHandle$volatile;
    private volatile /* synthetic */ Object _state$volatile;

    public d1(boolean z3) {
        this._state$volatile = z3 ? x.f2048j : x.f2047i;
    }

    public static m P(w2.j jVar) {
        while (jVar.i()) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = w2.j.f2634e;
            w2.j jVarF = jVar.f();
            if (jVarF == null) {
                Object obj = atomicReferenceFieldUpdater.get(jVar);
                while (true) {
                    jVar = (w2.j) obj;
                    if (!jVar.i()) {
                        break;
                    }
                    obj = atomicReferenceFieldUpdater.get(jVar);
                }
            } else {
                jVar = jVarF;
            }
        }
        while (true) {
            jVar = jVar.h();
            if (!jVar.i()) {
                if (jVar instanceof m) {
                    return (m) jVar;
                }
                if (jVar instanceof f1) {
                    return null;
                }
            }
        }
    }

    public static String W(Object obj) {
        if (!(obj instanceof b1)) {
            if (obj instanceof s0) {
                return ((s0) obj).c() ? "Active" : "New";
            }
            return obj instanceof q ? "Cancelled" : "Completed";
        }
        b1 b1Var = (b1) obj;
        if (b1Var.e()) {
            return "Cancelling";
        }
        return b1.f1955e.get(b1Var) != 0 ? "Completing" : "Active";
    }

    public final Throwable A(b1 b1Var, ArrayList arrayList) {
        Object obj;
        Object obj2 = null;
        if (arrayList.isEmpty()) {
            if (b1Var.e()) {
                return new w0(u(), null, this);
            }
            return null;
        }
        int size = arrayList.size();
        int i4 = 0;
        int i5 = 0;
        do {
            if (i5 >= size) {
                obj = null;
                break;
            }
            obj = arrayList.get(i5);
            i5++;
        } while (((Throwable) obj) instanceof CancellationException);
        Throwable th = (Throwable) obj;
        if (th != null) {
            return th;
        }
        Throwable th2 = (Throwable) arrayList.get(0);
        if (th2 instanceof m1) {
            int size2 = arrayList.size();
            while (i4 < size2) {
                Object obj3 = arrayList.get(i4);
                i4++;
                Throwable th3 = (Throwable) obj3;
                if (th3 != th2 && (th3 instanceof m1)) {
                    obj2 = obj3;
                    break;
                }
            }
            Throwable th4 = (Throwable) obj2;
            if (th4 != null) {
                return th4;
            }
        }
        return th2;
    }

    public boolean B() {
        return true;
    }

    @Override // y1.h
    public final y1.h C(y1.g gVar) {
        return l3.h.U(this, gVar);
    }

    public boolean D() {
        return this instanceof o;
    }

    public final f1 E(s0 s0Var) {
        f1 f1VarD = s0Var.d();
        if (f1VarD != null) {
            return f1VarD;
        }
        if (s0Var instanceof i0) {
            return new f1();
        }
        if (s0Var instanceof y0) {
            U((y0) s0Var);
            return null;
        }
        throw new IllegalStateException(("State should have list: " + s0Var).toString());
    }

    public boolean F(Throwable th) {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Object, r2.m, r2.y0, w2.j] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.Object, r2.g0] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r5v8, types: [r2.f1, w2.j] */
    public final void H(v0 v0Var) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        int iV;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f1972e;
        g1 g1Var = g1.f1979d;
        if (v0Var == null) {
            atomicReferenceFieldUpdater2.set(this, g1Var);
            return;
        }
        d1 d1Var = (d1) v0Var;
        do {
            atomicReferenceFieldUpdater = f1971d;
            iV = d1Var.V(atomicReferenceFieldUpdater.get(d1Var));
            if (iV == 0) {
                break;
            }
        } while (iV != 1);
        ?? mVar = new m(this);
        mVar.f2052g = d1Var;
        loop1: while (true) {
            Object obj = atomicReferenceFieldUpdater.get(d1Var);
            if (!(obj instanceof i0)) {
                Throwable thB = null;
                if (obj instanceof s0) {
                    ?? D = ((s0) obj).d();
                    if (D != 0) {
                        if (!D.e(mVar, 7)) {
                            boolean zE = D.e(mVar, 3);
                            Object obj2 = atomicReferenceFieldUpdater.get(d1Var);
                            if (obj2 instanceof b1) {
                                thB = ((b1) obj2).b();
                            } else {
                                q qVar = obj2 instanceof q ? (q) obj2 : null;
                                if (qVar != null) {
                                    thB = qVar.f2018a;
                                }
                            }
                            mVar.l(thB);
                            if (!zE) {
                                break;
                            } else {
                                break;
                            }
                        }
                        break;
                    }
                    d1Var.U((y0) obj);
                } else {
                    Object obj3 = atomicReferenceFieldUpdater.get(d1Var);
                    q qVar2 = obj3 instanceof q ? (q) obj3 : null;
                    mVar.l(qVar2 != null ? qVar2.f2018a : null);
                }
                mVar = g1Var;
                break;
            }
            i0 i0Var = (i0) obj;
            if (i0Var.f1989d) {
                do {
                    if (atomicReferenceFieldUpdater.compareAndSet(d1Var, obj, mVar)) {
                        break loop1;
                    }
                } while (atomicReferenceFieldUpdater.get(d1Var) == obj);
            } else {
                d1Var.T(i0Var);
            }
        }
        atomicReferenceFieldUpdater2.set(this, mVar);
        if (atomicReferenceFieldUpdater.get(this) instanceof s0) {
            return;
        }
        mVar.a();
        atomicReferenceFieldUpdater2.set(this, g1Var);
    }

    public final g0 I(boolean z3, y0 y0Var) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        boolean z4;
        boolean zE;
        y0Var.f2052g = this;
        loop0: while (true) {
            atomicReferenceFieldUpdater = f1971d;
            Object obj = atomicReferenceFieldUpdater.get(this);
            boolean z5 = obj instanceof i0;
            g1 g1Var = g1.f1979d;
            z4 = true;
            if (!z5) {
                if (!(obj instanceof s0)) {
                    z4 = false;
                    break;
                }
                s0 s0Var = (s0) obj;
                f1 f1VarD = s0Var.d();
                if (f1VarD == null) {
                    U((y0) obj);
                } else {
                    if (y0Var.k()) {
                        b1 b1Var = s0Var instanceof b1 ? (b1) s0Var : null;
                        Throwable thB = b1Var != null ? b1Var.b() : null;
                        if (thB == null) {
                            zE = f1VarD.e(y0Var, 5);
                        } else if (z3) {
                            y0Var.l(thB);
                            return g1Var;
                        }
                    } else {
                        zE = f1VarD.e(y0Var, 1);
                    }
                    if (zE) {
                        break;
                    }
                }
            } else {
                i0 i0Var = (i0) obj;
                if (i0Var.f1989d) {
                    do {
                        if (atomicReferenceFieldUpdater.compareAndSet(this, obj, y0Var)) {
                            break loop0;
                        }
                    } while (atomicReferenceFieldUpdater.get(this) == obj);
                } else {
                    T(i0Var);
                }
            }
            return g1Var;
        }
        if (z4) {
            return y0Var;
        }
        if (z3) {
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            q qVar = obj2 instanceof q ? (q) obj2 : null;
            y0Var.l(qVar != null ? qVar.f2018a : null);
        }
        return g1Var;
    }

    public final boolean J() {
        Object obj = f1971d.get(this);
        if (obj instanceof q) {
            return true;
        }
        return (obj instanceof b1) && ((b1) obj).e();
    }

    @Override // y1.h
    public final Object K(Object obj, i2.p pVar) {
        return pVar.f(obj, this);
    }

    public boolean L() {
        return this instanceof d;
    }

    public final boolean M(Object obj) {
        Object objX;
        do {
            objX = X(f1971d.get(this), obj);
            if (objX == x.f2042d) {
                return false;
            }
            if (objX == x.f2043e) {
                return true;
            }
        } while (objX == x.f2044f);
        p(objX);
        return true;
    }

    public final Object N(Object obj) {
        Object objX;
        do {
            objX = X(f1971d.get(this), obj);
            if (objX == x.f2042d) {
                String str = "Job " + this + " is already complete or completing, but is being completed with " + obj;
                q qVar = obj instanceof q ? (q) obj : null;
                throw new IllegalStateException(str, qVar != null ? qVar.f2018a : null);
            }
        } while (objX == x.f2044f);
        return objX;
    }

    public String O() {
        return getClass().getSimpleName();
    }

    public final void Q(f1 f1Var, Throwable th) {
        f1Var.e(new w2.h(4), 4);
        Object obj = w2.j.f2633d.get(f1Var);
        j2.i.c(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode");
        a0.c cVar = null;
        for (w2.j jVarH = (w2.j) obj; !jVarH.equals(f1Var); jVarH = jVarH.h()) {
            if ((jVarH instanceof y0) && ((y0) jVarH).k()) {
                try {
                    ((y0) jVarH).l(th);
                } catch (Throwable th2) {
                    if (cVar != null) {
                        l3.h.a(cVar, th2);
                    } else {
                        cVar = new a0.c("Exception in completion handler " + jVarH + " for " + this, th2);
                    }
                }
            }
        }
        if (cVar != null) {
            G(cVar);
        }
        t(th);
    }

    public final void T(i0 i0Var) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        f1 f1Var = new f1();
        Object r0Var = f1Var;
        if (!i0Var.f1989d) {
            r0Var = new r0(f1Var);
        }
        do {
            atomicReferenceFieldUpdater = f1971d;
            if (atomicReferenceFieldUpdater.compareAndSet(this, i0Var, r0Var)) {
                return;
            }
        } while (atomicReferenceFieldUpdater.get(this) == i0Var);
    }

    public final void U(y0 y0Var) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        f1 f1Var = new f1();
        y0Var.getClass();
        w2.j.f2634e.set(f1Var, y0Var);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = w2.j.f2633d;
        atomicReferenceFieldUpdater2.set(f1Var, y0Var);
        loop0: while (atomicReferenceFieldUpdater2.get(y0Var) == y0Var) {
            do {
                if (atomicReferenceFieldUpdater2.compareAndSet(y0Var, y0Var, f1Var)) {
                    f1Var.g(y0Var);
                    break loop0;
                }
            } while (atomicReferenceFieldUpdater2.get(y0Var) == y0Var);
        }
        w2.j jVarH = y0Var.h();
        do {
            atomicReferenceFieldUpdater = f1971d;
            if (atomicReferenceFieldUpdater.compareAndSet(this, y0Var, jVarH)) {
                return;
            }
        } while (atomicReferenceFieldUpdater.get(this) == y0Var);
    }

    public final int V(Object obj) {
        boolean z3 = obj instanceof i0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1971d;
        if (z3) {
            if (((i0) obj).f1989d) {
                return 0;
            }
            i0 i0Var = x.f2048j;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, i0Var)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                    return -1;
                }
            }
            S();
            return 1;
        }
        if (!(obj instanceof r0)) {
            return 0;
        }
        f1 f1Var = ((r0) obj).f2023d;
        while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, f1Var)) {
            if (atomicReferenceFieldUpdater.get(this) != obj) {
                return -1;
            }
        }
        S();
        return 1;
    }

    public final Object X(Object obj, Object obj2) {
        if (!(obj instanceof s0)) {
            return x.f2042d;
        }
        if (((obj instanceof i0) || (obj instanceof y0)) && !(obj instanceof m) && !(obj2 instanceof q)) {
            s0 s0Var = (s0) obj;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1971d;
            Object t0Var = obj2 instanceof s0 ? new t0((s0) obj2) : obj2;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, s0Var, t0Var)) {
                if (atomicReferenceFieldUpdater.get(this) != s0Var) {
                    return x.f2044f;
                }
            }
            R(obj2);
            w(s0Var, obj2);
            return obj2;
        }
        s0 s0Var2 = (s0) obj;
        f1 f1VarE = E(s0Var2);
        if (f1VarE == null) {
            return x.f2044f;
        }
        b1 b1Var = s0Var2 instanceof b1 ? (b1) s0Var2 : null;
        if (b1Var == null) {
            b1Var = new b1(f1VarE, null);
        }
        synchronized (b1Var) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = b1.f1955e;
            if (atomicIntegerFieldUpdater.get(b1Var) != 0) {
                return x.f2042d;
            }
            atomicIntegerFieldUpdater.set(b1Var, 1);
            if (b1Var != s0Var2) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f1971d;
                while (!atomicReferenceFieldUpdater2.compareAndSet(this, s0Var2, b1Var)) {
                    if (atomicReferenceFieldUpdater2.get(this) != s0Var2) {
                        return x.f2044f;
                    }
                }
            }
            boolean zE = b1Var.e();
            q qVar = obj2 instanceof q ? (q) obj2 : null;
            if (qVar != null) {
                b1Var.a(qVar.f2018a);
            }
            Throwable thB = zE ? null : b1Var.b();
            if (thB != null) {
                Q(f1VarE, thB);
            }
            m mVarP = P(f1VarE);
            if (mVarP != null && Y(b1Var, mVarP, obj2)) {
                return x.f2043e;
            }
            f1VarE.e(new w2.h(2), 2);
            m mVarP2 = P(f1VarE);
            return (mVarP2 == null || !Y(b1Var, mVarP2, obj2)) ? y(b1Var, obj2) : x.f2043e;
        }
    }

    public final boolean Y(b1 b1Var, m mVar, Object obj) {
        while (x.n(mVar.f1999h, false, new a1(this, b1Var, mVar, obj)) == g1.f1979d) {
            mVar = P(mVar);
            if (mVar == null) {
                return false;
            }
        }
        return true;
    }

    @Override // r2.v0
    public void b(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new w0(u(), null, this);
        }
        s(cancellationException);
    }

    @Override // r2.v0
    public boolean c() {
        Object obj = f1971d.get(this);
        return (obj instanceof s0) && ((s0) obj).c();
    }

    @Override // y1.f
    public final y1.g getKey() {
        return t.f2027e;
    }

    @Override // y1.h
    public final y1.f k(y1.g gVar) {
        return l3.h.C(this, gVar);
    }

    @Override // y1.h
    public final y1.h l(y1.h hVar) {
        return l3.h.Y(this, hVar);
    }

    public void q(Object obj) {
        p(obj);
    }

    /* JADX WARN: Code duplicated, block: B:18:0x003e A[PHI: r0
      0x003e: PHI (r0v1 java.lang.Object) = (r0v0 java.lang.Object), (r0v13 java.lang.Object) binds: [B:3:0x0008, B:16:0x003a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:20:0x0042  */
    /* JADX WARN: Code duplicated, block: B:26:0x005c  */
    /* JADX WARN: Code duplicated, block: B:27:0x005e  */
    /* JADX WARN: Code duplicated, block: B:29:0x0061 A[Catch: all -> 0x0067, TRY_LEAVE, TryCatch #0 {, blocks: (B:24:0x004f, B:29:0x0061, B:34:0x0069, B:36:0x0072, B:37:0x0076), top: B:81:0x004f }] */
    /* JADX WARN: Code duplicated, block: B:34:0x0069 A[Catch: all -> 0x0067, TRY_ENTER, TryCatch #0 {, blocks: (B:24:0x004f, B:29:0x0061, B:34:0x0069, B:36:0x0072, B:37:0x0076), top: B:81:0x004f }] */
    /* JADX WARN: Code duplicated, block: B:36:0x0072 A[Catch: all -> 0x0067, TryCatch #0 {, blocks: (B:24:0x004f, B:29:0x0061, B:34:0x0069, B:36:0x0072, B:37:0x0076), top: B:81:0x004f }] */
    /* JADX WARN: Code duplicated, block: B:39:0x0085  */
    /* JADX WARN: Code duplicated, block: B:42:0x0089  */
    /* JADX WARN: Code duplicated, block: B:46:0x0095  */
    /* JADX WARN: Code duplicated, block: B:48:0x0099 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:49:0x009b  */
    /* JADX WARN: Code duplicated, block: B:59:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:64:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:78:0x0105 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:79:0x0106  */
    /* JADX WARN: Code duplicated, block: B:81:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:86:0x00c8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:87:0x00af A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:88:0x004e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:89:0x00f3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:90:0x00ba A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:91:0x00d9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:92:0x00a8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:93:0x00db A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:95:0x0044 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:96:0x0044 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:97:0x0044 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:98:? A[LOOP:2: B:56:0x00b4->B:98:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:20:0x0042, please report this as an issue */
    public final boolean r(Object obj) {
        Throwable thX;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        Object obj2;
        boolean z3;
        Throwable thB;
        a3.h hVar;
        s0 s0Var;
        f1 f1VarE;
        b1 b1Var;
        Object objX;
        Object objX2 = x.f2042d;
        if (D()) {
            do {
                Object obj3 = f1971d.get(this);
                if (obj3 instanceof s0) {
                    if (obj3 instanceof b1) {
                        if (b1.f1955e.get((b1) obj3) != 0) {
                        }
                    }
                    objX2 = X(obj3, new q(x(obj), false));
                }
                objX2 = x.f2042d;
                break;
            } while (objX2 == x.f2044f);
            if (objX2 != x.f2043e) {
                if (objX2 == x.f2042d) {
                    thX = null;
                    loop1: while (true) {
                        atomicReferenceFieldUpdater = f1971d;
                        obj2 = atomicReferenceFieldUpdater.get(this);
                        if (obj2 instanceof b1) {
                            synchronized (obj2) {
                                if (b1.f1957g.get((b1) obj2) == x.f2046h) {
                                    z3 = true;
                                } else {
                                    z3 = false;
                                }
                                if (z3) {
                                    hVar = x.f2045g;
                                } else {
                                    boolean zE = ((b1) obj2).e();
                                    if (thX == null) {
                                        thX = x(obj);
                                    }
                                    ((b1) obj2).a(thX);
                                    thB = zE ? null : ((b1) obj2).b();
                                    if (thB != null) {
                                        Q(((b1) obj2).f1958d, thB);
                                    }
                                    hVar = x.f2042d;
                                }
                            }
                        } else if (obj2 instanceof s0) {
                            if (thX == null) {
                                thX = x(obj);
                            }
                            s0Var = (s0) obj2;
                            if (s0Var.c()) {
                                f1VarE = E(s0Var);
                                if (f1VarE == null) {
                                    continue;
                                } else {
                                    b1Var = new b1(f1VarE, thX);
                                    while (true) {
                                        if (atomicReferenceFieldUpdater.compareAndSet(this, s0Var, b1Var)) {
                                            Q(f1VarE, thX);
                                            hVar = x.f2042d;
                                        } else if (atomicReferenceFieldUpdater.get(this) != s0Var) {
                                        }
                                    }
                                }
                            } else {
                                objX = X(obj2, new q(thX, false));
                                if (objX != x.f2042d) {
                                    throw new IllegalStateException(("Cannot happen in " + obj2).toString());
                                }
                                if (objX != x.f2044f) {
                                    objX2 = objX;
                                    break;
                                }
                            }
                        } else {
                            hVar = x.f2045g;
                        }
                        objX2 = hVar;
                        break;
                    }
                }
                if (objX2 != x.f2042d && objX2 != x.f2043e) {
                    if (objX2 == x.f2045g) {
                        return false;
                    }
                    p(objX2);
                    return true;
                }
            }
        } else {
            if (objX2 == x.f2042d) {
                thX = null;
                loop1: while (true) {
                    atomicReferenceFieldUpdater = f1971d;
                    obj2 = atomicReferenceFieldUpdater.get(this);
                    if (obj2 instanceof b1) {
                        synchronized (obj2) {
                            if (b1.f1957g.get((b1) obj2) == x.f2046h) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            if (z3) {
                                hVar = x.f2045g;
                            } else {
                                boolean zE2 = ((b1) obj2).e();
                                if (thX == null) {
                                    thX = x(obj);
                                }
                                ((b1) obj2).a(thX);
                                if (zE2) {
                                }
                                if (thB != null) {
                                    Q(((b1) obj2).f1958d, thB);
                                }
                                hVar = x.f2042d;
                            }
                        }
                    } else if (obj2 instanceof s0) {
                        if (thX == null) {
                            thX = x(obj);
                        }
                        s0Var = (s0) obj2;
                        if (s0Var.c()) {
                            f1VarE = E(s0Var);
                            if (f1VarE == null) {
                                continue;
                            } else {
                                b1Var = new b1(f1VarE, thX);
                                while (true) {
                                    if (atomicReferenceFieldUpdater.compareAndSet(this, s0Var, b1Var)) {
                                        Q(f1VarE, thX);
                                        hVar = x.f2042d;
                                    } else if (atomicReferenceFieldUpdater.get(this) != s0Var) {
                                    }
                                }
                            }
                        } else {
                            objX = X(obj2, new q(thX, false));
                            if (objX != x.f2042d) {
                                throw new IllegalStateException(("Cannot happen in " + obj2).toString());
                            }
                            if (objX != x.f2044f) {
                                objX2 = objX;
                                break;
                            }
                        }
                    } else {
                        hVar = x.f2045g;
                    }
                    objX2 = hVar;
                    break;
                }
            }
            if (objX2 != x.f2042d) {
                if (objX2 == x.f2045g) {
                    return false;
                }
                p(objX2);
                return true;
            }
        }
        return true;
    }

    public void s(CancellationException cancellationException) {
        r(cancellationException);
    }

    public final boolean t(Throwable th) {
        if (L()) {
            return true;
        }
        boolean z3 = th instanceof CancellationException;
        l lVar = (l) f1972e.get(this);
        if (lVar == null || lVar == g1.f1979d) {
            return z3;
        }
        return lVar.b(th) || z3;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(O() + '{' + W(f1971d.get(this)) + '}');
        sb.append('@');
        sb.append(x.k(this));
        return sb.toString();
    }

    public String u() {
        return "Job was cancelled";
    }

    public boolean v(Throwable th) {
        if (th instanceof CancellationException) {
            return true;
        }
        return r(th) && B();
    }

    public final void w(s0 s0Var, Object obj) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1972e;
        l lVar = (l) atomicReferenceFieldUpdater.get(this);
        if (lVar != null) {
            lVar.a();
            atomicReferenceFieldUpdater.set(this, g1.f1979d);
        }
        a0.c cVar = null;
        q qVar = obj instanceof q ? (q) obj : null;
        Throwable th = qVar != null ? qVar.f2018a : null;
        if (s0Var instanceof y0) {
            try {
                ((y0) s0Var).l(th);
                return;
            } catch (Throwable th2) {
                G(new a0.c("Exception in completion handler " + s0Var + " for " + this, th2));
                return;
            }
        }
        f1 f1VarD = s0Var.d();
        if (f1VarD != null) {
            f1VarD.e(new w2.h(1), 1);
            Object obj2 = w2.j.f2633d.get(f1VarD);
            j2.i.c(obj2, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode");
            for (w2.j jVarH = (w2.j) obj2; !jVarH.equals(f1VarD); jVarH = jVarH.h()) {
                if (jVarH instanceof y0) {
                    try {
                        ((y0) jVarH).l(th);
                    } catch (Throwable th3) {
                        if (cVar != null) {
                            l3.h.a(cVar, th3);
                        } else {
                            cVar = new a0.c("Exception in completion handler " + jVarH + " for " + this, th3);
                        }
                    }
                }
            }
            if (cVar != null) {
                G(cVar);
            }
        }
    }

    public final Throwable x(Object obj) {
        Throwable thB;
        if (obj instanceof Throwable) {
            return (Throwable) obj;
        }
        d1 d1Var = (d1) ((i1) obj);
        Object obj2 = f1971d.get(d1Var);
        if (obj2 instanceof b1) {
            thB = ((b1) obj2).b();
        } else if (obj2 instanceof q) {
            thB = ((q) obj2).f2018a;
        } else {
            if (obj2 instanceof s0) {
                throw new IllegalStateException(("Cannot be cancelling child in this state: " + obj2).toString());
            }
            thB = null;
        }
        CancellationException cancellationException = thB instanceof CancellationException ? (CancellationException) thB : null;
        return cancellationException == null ? new w0("Parent job is ".concat(W(obj2)), thB, d1Var) : cancellationException;
    }

    public final Object y(b1 b1Var, Object obj) {
        Throwable thA;
        q qVar = obj instanceof q ? (q) obj : null;
        Throwable th = qVar != null ? qVar.f2018a : null;
        synchronized (b1Var) {
            b1Var.e();
            ArrayList arrayListF = b1Var.f(th);
            thA = A(b1Var, arrayListF);
            if (thA != null && arrayListF.size() > 1) {
                Set setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap(arrayListF.size()));
                int size = arrayListF.size();
                int i4 = 0;
                while (i4 < size) {
                    Object obj2 = arrayListF.get(i4);
                    i4++;
                    Throwable th2 = (Throwable) obj2;
                    if (th2 != thA && th2 != thA && !(th2 instanceof CancellationException) && setNewSetFromMap.add(th2)) {
                        l3.h.a(thA, th2);
                    }
                }
            }
        }
        if (thA != null && thA != th) {
            obj = new q(thA, false);
        }
        if (thA != null && (t(thA) || F(thA))) {
            j2.i.c(obj, "null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
            q.f2017b.compareAndSet((q) obj, 0, 1);
        }
        R(obj);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1971d;
        Object t0Var = obj instanceof s0 ? new t0((s0) obj) : obj;
        while (!atomicReferenceFieldUpdater.compareAndSet(this, b1Var, t0Var) && atomicReferenceFieldUpdater.get(this) == b1Var) {
        }
        w(b1Var, obj);
        return obj;
    }

    public final CancellationException z() {
        CancellationException cancellationException;
        Object obj = f1971d.get(this);
        if (!(obj instanceof b1)) {
            if (obj instanceof s0) {
                throw new IllegalStateException(("Job is still new or active: " + this).toString());
            }
            if (!(obj instanceof q)) {
                return new w0(getClass().getSimpleName().concat(" has completed normally"), null, this);
            }
            Throwable th = ((q) obj).f2018a;
            cancellationException = th instanceof CancellationException ? (CancellationException) th : null;
            return cancellationException == null ? new w0(u(), th, this) : cancellationException;
        }
        Throwable thB = ((b1) obj).b();
        if (thB == null) {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
        String strConcat = getClass().getSimpleName().concat(" is cancelling");
        cancellationException = thB instanceof CancellationException ? (CancellationException) thB : null;
        if (cancellationException != null) {
            return cancellationException;
        }
        if (strConcat == null) {
            strConcat = u();
        }
        return new w0(strConcat, thB, this);
    }

    public void S() {
    }

    public void G(a0.c cVar) {
        throw cVar;
    }

    public void R(Object obj) {
    }

    public void p(Object obj) {
    }
}
