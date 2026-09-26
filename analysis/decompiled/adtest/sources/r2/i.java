package r2;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class i extends c0 implements g, a2.d, r1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f1984i = AtomicIntegerFieldUpdater.newUpdater(i.class, "_decisionAndIndex$volatile");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f1985j = AtomicReferenceFieldUpdater.newUpdater(i.class, Object.class, "_state$volatile");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f1986k = AtomicReferenceFieldUpdater.newUpdater(i.class, Object.class, "_parentHandle$volatile");
    private volatile /* synthetic */ int _decisionAndIndex$volatile;
    private volatile /* synthetic */ Object _parentHandle$volatile;
    private volatile /* synthetic */ Object _state$volatile;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final y1.c f1987g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final y1.h f1988h;

    public i(int i4, y1.c cVar) {
        super(i4);
        this.f1987g = cVar;
        this.f1988h = cVar.g();
        this._decisionAndIndex$volatile = 536870911;
        this._state$volatile = b.f1953a;
    }

    public static void A(Object obj, Object obj2) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + obj + ", already has " + obj2).toString());
    }

    public static Object F(h1 h1Var, Object obj, int i4, i2.q qVar) {
        if (obj instanceof q) {
            return obj;
        }
        if (i4 != 1 && i4 != 2) {
            return obj;
        }
        if (qVar != null || (h1Var instanceof f)) {
            return new p(obj, h1Var instanceof f ? (f) h1Var : null, qVar, (CancellationException) null, 16);
        }
        return obj;
    }

    public String B() {
        return "CancellableContinuation";
    }

    public final void C() {
        y1.c cVar = this.f1987g;
        Throwable th = null;
        w2.f fVar = cVar instanceof w2.f ? (w2.f) cVar : null;
        if (fVar != null) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = w2.f.f2621k;
            loop0: while (true) {
                Object obj = atomicReferenceFieldUpdater.get(fVar);
                a3.h hVar = w2.a.f2611c;
                if (obj != hVar) {
                    if (!(obj instanceof Throwable)) {
                        throw new IllegalStateException(("Inconsistent state " + obj).toString());
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(fVar, obj, null)) {
                        if (atomicReferenceFieldUpdater.get(fVar) != obj) {
                            throw new IllegalArgumentException("Failed requirement.");
                        }
                    }
                    th = (Throwable) obj;
                    break;
                }
                do {
                    if (atomicReferenceFieldUpdater.compareAndSet(fVar, hVar, this)) {
                        break loop0;
                    }
                } while (atomicReferenceFieldUpdater.get(fVar) == hVar);
            }
            if (th == null) {
                return;
            }
            r();
            q(th);
        }
    }

    public final void D(Object obj, int i4, i2.q qVar) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1985j;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (!(obj2 instanceof h1)) {
                if (obj2 instanceof j) {
                    j jVar = (j) obj2;
                    if (j.f1990c.compareAndSet(jVar, 0, 1)) {
                        if (qVar != null) {
                            l(qVar, jVar.f2018a, obj);
                            return;
                        }
                        return;
                    }
                }
                throw new IllegalStateException(("Already resumed, but proposed with update " + obj).toString());
            }
            Object objF = F((h1) obj2, obj, i4, qVar);
            do {
                if (atomicReferenceFieldUpdater.compareAndSet(this, obj2, objF)) {
                    if (!z()) {
                        r();
                    }
                    s(i4);
                    return;
                }
            } while (atomicReferenceFieldUpdater.get(this) == obj2);
        }
    }

    public final void E(s sVar) {
        y1.c cVar = this.f1987g;
        w2.f fVar = cVar instanceof w2.f ? (w2.f) cVar : null;
        D(u1.k.f2301a, (fVar != null ? fVar.f2622g : null) == sVar ? 4 : this.f1962f, null);
    }

    @Override // r2.r1
    public final void a(w2.r rVar, int i4) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i5;
        do {
            atomicIntegerFieldUpdater = f1984i;
            i5 = atomicIntegerFieldUpdater.get(this);
            if ((i5 & 536870911) != 536870911) {
                throw new IllegalStateException("invokeOnCancellation should be called at most once");
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i5, ((i5 >> 29) << 29) + i4));
        y(rVar);
    }

    @Override // r2.c0
    public final void b(CancellationException cancellationException) {
        CancellationException cancellationException2;
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1985j;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj instanceof h1) {
                throw new IllegalStateException("Not completed");
            }
            if (obj instanceof q) {
                return;
            }
            if (!(obj instanceof p)) {
                cancellationException2 = cancellationException;
                p pVar = new p(obj, (f) null, (i2.q) null, cancellationException2, 14);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, pVar)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                    }
                }
                return;
            }
            p pVar2 = (p) obj;
            if (pVar2.f2015e != null) {
                throw new IllegalStateException("Must be called at most once");
            }
            p pVarA = p.a(pVar2, null, cancellationException, 15);
            do {
                if (atomicReferenceFieldUpdater.compareAndSet(this, obj, pVarA)) {
                    f fVar = pVar2.f2012b;
                    if (fVar != null) {
                        k(fVar, cancellationException);
                    }
                    i2.q qVar = pVar2.f2013c;
                    if (qVar != null) {
                        l(qVar, cancellationException, pVar2.f2011a);
                        return;
                    }
                    return;
                }
            } while (atomicReferenceFieldUpdater.get(this) == obj);
            cancellationException2 = cancellationException;
            cancellationException = cancellationException2;
        }
    }

    @Override // r2.c0
    public final y1.c c() {
        return this.f1987g;
    }

    @Override // r2.c0
    public final Throwable d(Object obj) {
        Throwable thD = super.d(obj);
        if (thD != null) {
            return thD;
        }
        return null;
    }

    @Override // a2.d
    public final a2.d e() {
        y1.c cVar = this.f1987g;
        if (cVar instanceof a2.d) {
            return (a2.d) cVar;
        }
        return null;
    }

    @Override // r2.c0
    public final Object f(Object obj) {
        return obj instanceof p ? ((p) obj).f2011a : obj;
    }

    @Override // y1.c
    public final y1.h g() {
        return this.f1988h;
    }

    @Override // r2.c0
    public final Object i() {
        return f1985j.get(this);
    }

    @Override // y1.c
    public final void j(Object obj) {
        Throwable thA = u1.h.a(obj);
        if (thA != null) {
            obj = new q(thA, false);
        }
        D(obj, this.f1962f, null);
    }

    public final void k(f fVar, Throwable th) {
        try {
            switch (fVar.f1976a) {
                case 0:
                    ((ScheduledFuture) fVar.f1977b).cancel(false);
                    break;
                case 1:
                    ((i2.l) fVar.f1977b).h(th);
                    break;
                default:
                    ((g0) fVar.f1977b).a();
                    break;
            }
        } catch (Throwable th2) {
            x.m(new a0.c("Exception in invokeOnCancellation handler for " + this, th2), this.f1988h);
        }
    }

    public final void l(i2.q qVar, Throwable th, Object obj) {
        y1.h hVar = this.f1988h;
        try {
            qVar.d(th, obj, hVar);
        } catch (Throwable th2) {
            x.m(new a0.c("Exception in resume onCancellation handler for " + this, th2), hVar);
        }
    }

    @Override // r2.g
    public final void m(Object obj, i2.q qVar) {
        D(obj, this.f1962f, qVar);
    }

    @Override // r2.g
    public final a3.h n(Object obj, i2.q qVar) {
        a3.h hVar = x.f2039a;
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1985j;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (!(obj2 instanceof h1)) {
                return null;
            }
            Object objF = F((h1) obj2, obj, this.f1962f, qVar);
            do {
                if (atomicReferenceFieldUpdater.compareAndSet(this, obj2, objF)) {
                    if (!z()) {
                        r();
                    }
                    return hVar;
                }
            } while (atomicReferenceFieldUpdater.get(this) == obj2);
        }
    }

    @Override // r2.g
    public final void o(Object obj) {
        s(this.f1962f);
    }

    public final void p(w2.r rVar, Throwable th) {
        y1.h hVar = this.f1988h;
        int i4 = f1984i.get(this) & 536870911;
        if (i4 == 536870911) {
            throw new IllegalStateException("The index for Segment.onCancellation(..) is broken");
        }
        try {
            rVar.g(i4, hVar);
        } catch (Throwable th2) {
            x.m(new a0.c("Exception in invokeOnCancellation handler for " + this, th2), hVar);
        }
    }

    public final boolean q(Throwable th) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1985j;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (!(obj instanceof h1)) {
                return false;
            }
            j jVar = new j(this, th, (obj instanceof f) || (obj instanceof w2.r));
            do {
                if (atomicReferenceFieldUpdater.compareAndSet(this, obj, jVar)) {
                    h1 h1Var = (h1) obj;
                    if (h1Var instanceof f) {
                        k((f) obj, th);
                    } else if (h1Var instanceof w2.r) {
                        p((w2.r) obj, th);
                    }
                    if (!z()) {
                        r();
                    }
                    s(this.f1962f);
                    return true;
                }
            } while (atomicReferenceFieldUpdater.get(this) == obj);
        }
    }

    public final void r() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1986k;
        g0 g0Var = (g0) atomicReferenceFieldUpdater.get(this);
        if (g0Var == null) {
            return;
        }
        g0Var.a();
        atomicReferenceFieldUpdater.set(this, g1.f1979d);
    }

    public final void s(int i4) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i5;
        do {
            atomicIntegerFieldUpdater = f1984i;
            i5 = atomicIntegerFieldUpdater.get(this);
            int i6 = i5 >> 29;
            if (i6 != 0) {
                if (i6 != 1) {
                    throw new IllegalStateException("Already resumed");
                }
                boolean z3 = i4 == 4;
                y1.c cVar = this.f1987g;
                if (!z3 && (cVar instanceof w2.f)) {
                    boolean z4 = i4 == 1 || i4 == 2;
                    int i7 = this.f1962f;
                    if (z4 == (i7 == 1 || i7 == 2)) {
                        w2.f fVar = (w2.f) cVar;
                        s sVar = fVar.f2622g;
                        y1.h hVarG = fVar.f2623h.g();
                        if (sVar.U(hVarG)) {
                            sVar.S(hVarG, this);
                            return;
                        }
                        o0 o0VarA = l1.a();
                        if (o0VarA.f2007f >= 4294967296L) {
                            o0VarA.X(this);
                            return;
                        }
                        o0VarA.Z(true);
                        try {
                            x.r(this, cVar, true);
                            do {
                            } while (o0VarA.b0());
                        } catch (Throwable th) {
                            try {
                                h(th);
                            } finally {
                                o0VarA.W(true);
                            }
                        }
                        return;
                    }
                }
                x.r(this, cVar, z3);
                return;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i5, 1073741824 + (536870911 & i5)));
    }

    public Throwable t(d1 d1Var) {
        return d1Var.z();
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(B());
        sb.append('(');
        sb.append(x.t(this.f1987g));
        sb.append("){");
        Object obj = f1985j.get(this);
        if (obj instanceof h1) {
            str = "Active";
        } else {
            str = obj instanceof j ? "Cancelled" : "Completed";
        }
        sb.append(str);
        sb.append("}@");
        sb.append(x.k(this));
        return sb.toString();
    }

    public final Object u() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i4;
        v0 v0Var;
        boolean z3 = z();
        do {
            atomicIntegerFieldUpdater = f1984i;
            i4 = atomicIntegerFieldUpdater.get(this);
            int i5 = i4 >> 29;
            if (i5 != 0) {
                if (i5 != 2) {
                    throw new IllegalStateException("Already suspended");
                }
                if (z3) {
                    C();
                }
                Object obj = f1985j.get(this);
                if (obj instanceof q) {
                    throw ((q) obj).f2018a;
                }
                int i6 = this.f1962f;
                if ((i6 != 1 && i6 != 2) || (v0Var = (v0) this.f1988h.k(t.f2027e)) == null || v0Var.c()) {
                    return f(obj);
                }
                CancellationException cancellationExceptionZ = ((d1) v0Var).z();
                b(cancellationExceptionZ);
                throw cancellationExceptionZ;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i4, 536870912 + (536870911 & i4)));
        if (((g0) f1986k.get(this)) == null) {
            w();
        }
        if (z3) {
            C();
        }
        return z1.a.f2781d;
    }

    public final void v() {
        g0 g0VarW = w();
        if (g0VarW == null || (f1985j.get(this) instanceof h1)) {
            return;
        }
        g0VarW.a();
        f1986k.set(this, g1.f1979d);
    }

    public final g0 w() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        v0 v0Var = (v0) this.f1988h.k(t.f2027e);
        if (v0Var == null) {
            return null;
        }
        g0 g0VarN = x.n(v0Var, true, new k(this, 0));
        do {
            atomicReferenceFieldUpdater = f1986k;
            if (atomicReferenceFieldUpdater.compareAndSet(this, null, g0VarN)) {
                break;
            }
        } while (atomicReferenceFieldUpdater.get(this) == null);
        return g0VarN;
    }

    public final void x(i2.l lVar) {
        y(new f(1, lVar));
    }

    public final void y(h1 h1Var) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f1985j;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj instanceof b) {
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, h1Var)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                    }
                }
                return;
            }
            if ((obj instanceof f) || (obj instanceof w2.r)) {
                A(h1Var, obj);
                throw null;
            }
            if (obj instanceof q) {
                q qVar = (q) obj;
                if (!q.f2017b.compareAndSet(qVar, 0, 1)) {
                    A(h1Var, obj);
                    throw null;
                }
                if (obj instanceof j) {
                    Throwable th = qVar.f2018a;
                    if (h1Var instanceof f) {
                        k((f) h1Var, th);
                        return;
                    } else {
                        j2.i.c(h1Var, "null cannot be cast to non-null type kotlinx.coroutines.internal.Segment<*>");
                        p((w2.r) h1Var, th);
                        return;
                    }
                }
                return;
            }
            if (!(obj instanceof p)) {
                if (h1Var instanceof w2.r) {
                    return;
                }
                j2.i.c(h1Var, "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler");
                p pVar = new p(obj, (f) h1Var, (i2.q) null, (CancellationException) null, 28);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, pVar)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                    }
                }
                return;
            }
            p pVar2 = (p) obj;
            if (pVar2.f2012b != null) {
                A(h1Var, obj);
                throw null;
            }
            if (h1Var instanceof w2.r) {
                return;
            }
            j2.i.c(h1Var, "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler");
            f fVar = (f) h1Var;
            Throwable th2 = pVar2.f2015e;
            if (th2 != null) {
                k(fVar, th2);
                return;
            }
            p pVarA = p.a(pVar2, fVar, null, 29);
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, pVarA)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                }
            }
            return;
        }
    }

    public final boolean z() {
        if (this.f1962f != 2) {
            return false;
        }
        y1.c cVar = this.f1987g;
        j2.i.c(cVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
        return w2.f.f2621k.get((w2.f) cVar) != null;
    }
}
