package t2;

import d0.l0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import r2.r1;
import r2.x;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class e implements i {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f2187e = AtomicLongFieldUpdater.newUpdater(e.class, "sendersAndCloseStatus$volatile");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f2188f = AtomicLongFieldUpdater.newUpdater(e.class, "receivers$volatile");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f2189g = AtomicLongFieldUpdater.newUpdater(e.class, "bufferEnd$volatile");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f2190h = AtomicLongFieldUpdater.newUpdater(e.class, "completedExpandBuffersAndPauseFlag$volatile");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2191i = AtomicReferenceFieldUpdater.newUpdater(e.class, Object.class, "sendSegment$volatile");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2192j = AtomicReferenceFieldUpdater.newUpdater(e.class, Object.class, "receiveSegment$volatile");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2193k = AtomicReferenceFieldUpdater.newUpdater(e.class, Object.class, "bufferEndSegment$volatile");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2194l = AtomicReferenceFieldUpdater.newUpdater(e.class, Object.class, "_closeCause$volatile");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2195m = AtomicReferenceFieldUpdater.newUpdater(e.class, Object.class, "closeHandler$volatile");
    private volatile /* synthetic */ Object _closeCause$volatile;
    private volatile /* synthetic */ long bufferEnd$volatile;
    private volatile /* synthetic */ Object bufferEndSegment$volatile;
    private volatile /* synthetic */ Object closeHandler$volatile;
    private volatile /* synthetic */ long completedExpandBuffersAndPauseFlag$volatile;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2196d;
    private volatile /* synthetic */ Object receiveSegment$volatile;
    private volatile /* synthetic */ long receivers$volatile;
    private volatile /* synthetic */ Object sendSegment$volatile;
    private volatile /* synthetic */ long sendersAndCloseStatus$volatile;

    public e(int i4) {
        this.f2196d = i4;
        if (i4 < 0) {
            throw new IllegalArgumentException(a1.c.d(i4, "Invalid channel capacity: ", ", should be >=0").toString());
        }
        m mVar = g.f2198a;
        this.bufferEnd$volatile = i4 != 0 ? i4 != Integer.MAX_VALUE ? i4 : Long.MAX_VALUE : 0L;
        this.completedExpandBuffersAndPauseFlag$volatile = f2189g.get(this);
        m mVar2 = new m(0L, null, this, 3);
        this.sendSegment$volatile = mVar2;
        this.receiveSegment$volatile = mVar2;
        if (w()) {
            mVar2 = g.f2198a;
            j2.i.c(mVar2, "null cannot be cast to non-null type kotlinx.coroutines.channels.ChannelSegment<E of kotlinx.coroutines.channels.BufferedChannel>");
        }
        this.bufferEndSegment$volatile = mVar2;
        this._closeCause$volatile = g.f2216s;
    }

    public static boolean D(Object obj) {
        if (obj instanceof r2.g) {
            return g.a((r2.g) obj, u1.k.f2301a, null);
        }
        throw new IllegalStateException(("Unexpected waiter: " + obj).toString());
    }

    public static final m c(e eVar, long j4, m mVar) {
        Object objB;
        e eVar2;
        m mVar2 = g.f2198a;
        f fVar = f.f2197k;
        loop0: while (true) {
            objB = w2.a.b(mVar, j4, fVar);
            if (!w2.a.e(objB)) {
                w2.r rVarC = w2.a.c(objB);
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2191i;
                    w2.r rVar = (w2.r) atomicReferenceFieldUpdater.get(eVar);
                    if (rVar.f2649c >= rVarC.f2649c) {
                        break loop0;
                    }
                    if (!rVarC.i()) {
                        break;
                    }
                    do {
                        if (atomicReferenceFieldUpdater.compareAndSet(eVar, rVar, rVarC)) {
                            if (!rVar.e()) {
                                break loop0;
                            }
                            rVar.d();
                            break loop0;
                        }
                    } while (atomicReferenceFieldUpdater.get(eVar) == rVar);
                    if (rVarC.e()) {
                        rVarC.d();
                    }
                }
            } else {
                break;
            }
        }
        boolean zE = w2.a.e(objB);
        AtomicLongFieldUpdater atomicLongFieldUpdater = f2188f;
        if (zE) {
            eVar.u();
            if (mVar.f2649c * ((long) g.f2199b) < atomicLongFieldUpdater.get(eVar)) {
                mVar.a();
                return null;
            }
        } else {
            m mVar3 = (m) w2.a.c(objB);
            long j5 = mVar3.f2649c;
            if (j5 <= j4) {
                return mVar3;
            }
            long j6 = ((long) g.f2199b) * j5;
            while (true) {
                long j7 = f2187e.get(eVar);
                long j8 = 1152921504606846975L & j7;
                if (j8 >= j6) {
                    eVar2 = eVar;
                    break;
                }
                eVar2 = eVar;
                if (f2187e.compareAndSet(eVar2, j7, (((long) ((int) (j7 >> 60))) << 60) + j8)) {
                    break;
                }
                eVar = eVar2;
            }
            if (j5 * ((long) g.f2199b) < atomicLongFieldUpdater.get(eVar2)) {
                mVar3.a();
            }
        }
        return null;
    }

    public static final void e(e eVar, Object obj, r2.i iVar) {
        iVar.j(l0.l(eVar.q()));
    }

    public static final int g(e eVar, m mVar, int i4, Object obj, long j4, Object obj2, boolean z3) {
        mVar.m(i4, obj);
        if (z3) {
            return eVar.F(mVar, i4, obj, j4, obj2, z3);
        }
        Object objK = mVar.k(i4);
        if (objK == null) {
            if (eVar.i(j4)) {
                if (mVar.j(i4, null, g.f2201d)) {
                    return 1;
                }
            } else {
                if (obj2 == null) {
                    return 3;
                }
                if (mVar.j(i4, null, obj2)) {
                    return 2;
                }
            }
        } else if (objK instanceof r1) {
            mVar.m(i4, null);
            if (eVar.C(objK, obj)) {
                mVar.n(i4, g.f2206i);
                return 0;
            }
            a3.h hVar = g.f2208k;
            if (mVar.f2224f.getAndSet((i4 * 2) + 1, hVar) == hVar) {
                return 5;
            }
            mVar.l(i4, true);
            return 5;
        }
        return eVar.F(mVar, i4, obj, j4, obj2, z3);
    }

    public static void s(e eVar) {
        AtomicLongFieldUpdater atomicLongFieldUpdater = f2190h;
        if ((atomicLongFieldUpdater.addAndGet(eVar, 1L) & 4611686018427387904L) != 0) {
            while ((atomicLongFieldUpdater.get(eVar) & 4611686018427387904L) != 0) {
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    public static Object z(e eVar, a2.c cVar) {
        c cVar2;
        m mVar;
        if (cVar instanceof c) {
            cVar2 = (c) cVar;
            int i4 = cVar2.f2183i;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                cVar2.f2183i = i4 - Integer.MIN_VALUE;
            } else {
                cVar2 = new c(eVar, cVar);
            }
        } else {
            cVar2 = new c(eVar, cVar);
        }
        c cVar3 = cVar2;
        Object obj = cVar3.f2181g;
        int i5 = cVar3.f2183i;
        if (i5 != 0) {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            l0.M(obj);
            return ((l) obj).f2222a;
        }
        l0.M(obj);
        m mVar2 = (m) f2192j.get(eVar);
        while (!eVar.t(f2187e.get(eVar), true)) {
            long andIncrement = f2188f.getAndIncrement(eVar);
            long j4 = g.f2199b;
            long j5 = andIncrement / j4;
            int i6 = (int) (andIncrement % j4);
            if (mVar2.f2649c != j5) {
                m mVarO = eVar.o(j5, mVar2);
                if (mVarO == null) {
                    continue;
                } else {
                    mVar = mVarO;
                }
            } else {
                mVar = mVar2;
            }
            e eVar2 = eVar;
            Object objE = eVar2.E(mVar, i6, andIncrement, null);
            if (objE == g.f2210m) {
                throw new IllegalStateException("unexpected");
            }
            if (objE != g.f2212o) {
                if (objE != g.f2211n) {
                    mVar.a();
                    return objE;
                }
                cVar3.f2183i = 1;
                Object objA = eVar2.A(mVar, i6, andIncrement, cVar3);
                z1.a aVar = z1.a.f2781d;
                return objA == aVar ? aVar : objA;
            }
            if (andIncrement < eVar2.r()) {
                mVar.a();
            }
            mVar2 = mVar;
            eVar = eVar2;
        }
        return new j(eVar.p());
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object A(m mVar, int i4, long j4, a2.c cVar) {
        d dVar;
        l lVar;
        m mVar2;
        if (cVar instanceof d) {
            dVar = (d) cVar;
            int i5 = dVar.f2186i;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                dVar.f2186i = i5 - Integer.MIN_VALUE;
            } else {
                dVar = new d(this, cVar);
            }
        } else {
            dVar = new d(this, cVar);
        }
        Object objU = dVar.f2184g;
        int i6 = dVar.f2186i;
        if (i6 == 0) {
            l0.M(objU);
            dVar.f2186i = 1;
            r2.i iVarL = x.l(z1.d.a(dVar));
            try {
                t tVar = new t(iVarL);
                e eVar = this;
                Object objE = eVar.E(mVar, i4, j4, tVar);
                if (objE != g.f2210m) {
                    if (objE == g.f2212o) {
                        if (j4 < r()) {
                            mVar.a();
                        }
                        m mVar3 = (m) f2192j.get(this);
                        while (true) {
                            if (t(f2187e.get(this), true)) {
                                iVarL.j(new l(new j(p())));
                                break;
                            }
                            long andIncrement = f2188f.getAndIncrement(this);
                            long j5 = g.f2199b;
                            long j6 = andIncrement / j5;
                            int i7 = (int) (andIncrement % j5);
                            if (mVar3.f2649c != j6) {
                                m mVarO = o(j6, mVar3);
                                if (mVarO != null) {
                                    mVar2 = mVarO;
                                }
                            } else {
                                mVar2 = mVar3;
                            }
                            Object objE2 = eVar.E(mVar2, i7, andIncrement, tVar);
                            m mVar4 = mVar2;
                            if (objE2 == g.f2210m) {
                                tVar.a(mVar4, i7);
                                break;
                            }
                            if (objE2 == g.f2212o) {
                                if (andIncrement < r()) {
                                    mVar4.a();
                                }
                                eVar = this;
                                mVar3 = mVar4;
                            } else {
                                if (objE2 == g.f2211n) {
                                    throw new IllegalStateException("unexpected");
                                }
                                mVar4.a();
                                lVar = new l(objE2);
                            }
                        }
                    } else {
                        mVar.a();
                        lVar = new l(objE);
                    }
                    iVarL.m(lVar, null);
                    break;
                }
                tVar.a(mVar, i4);
                objU = iVarL.u();
                z1.a aVar = z1.a.f2781d;
                if (objU == aVar) {
                    return aVar;
                }
            } catch (Throwable th) {
                iVarL.C();
                throw th;
            }
        } else {
            if (i6 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            l0.M(objU);
        }
        return ((l) objU).f2222a;
    }

    public final void B(r1 r1Var, boolean z3) {
        Throwable thQ;
        if (r1Var instanceof r2.g) {
            y1.c cVar = (y1.c) r1Var;
            if (z3) {
                thQ = p();
                if (thQ == null) {
                    thQ = new n("Channel was closed");
                }
            } else {
                thQ = q();
            }
            cVar.j(l0.l(thQ));
            return;
        }
        if (r1Var instanceof t) {
            ((t) r1Var).f2230d.j(new l(new j(p())));
            return;
        }
        if (!(r1Var instanceof b)) {
            throw new IllegalStateException(("Unexpected waiter: " + r1Var).toString());
        }
        b bVar = (b) r1Var;
        r2.i iVar = bVar.f2179e;
        j2.i.b(iVar);
        bVar.f2179e = null;
        bVar.f2178d = g.f2209l;
        Throwable thP = bVar.f2180f.p();
        if (thP == null) {
            iVar.j(Boolean.FALSE);
        } else {
            iVar.j(l0.l(thP));
        }
    }

    public final boolean C(Object obj, Object obj2) {
        if (obj instanceof t) {
            return g.a(((t) obj).f2230d, new l(obj2), null);
        }
        if (!(obj instanceof b)) {
            if (obj instanceof r2.g) {
                return g.a((r2.g) obj, obj2, null);
            }
            throw new IllegalStateException(("Unexpected receiver type: " + obj).toString());
        }
        b bVar = (b) obj;
        r2.i iVar = bVar.f2179e;
        j2.i.b(iVar);
        bVar.f2179e = null;
        bVar.f2178d = obj2;
        return g.a(iVar, Boolean.TRUE, null);
    }

    public final Object E(m mVar, int i4, long j4, Object obj) {
        Object objK = mVar.k(i4);
        AtomicReferenceArray atomicReferenceArray = mVar.f2224f;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f2187e;
        if (objK == null) {
            if (j4 >= (atomicLongFieldUpdater.get(this) & 1152921504606846975L)) {
                if (obj == null) {
                    return g.f2211n;
                }
                if (mVar.j(i4, objK, obj)) {
                    n();
                    return g.f2210m;
                }
            }
        } else if (objK == g.f2201d && mVar.j(i4, objK, g.f2206i)) {
            n();
            Object obj2 = atomicReferenceArray.get(i4 * 2);
            mVar.m(i4, null);
            return obj2;
        }
        while (true) {
            Object objK2 = mVar.k(i4);
            if (objK2 == null || objK2 == g.f2202e) {
                if (j4 < (atomicLongFieldUpdater.get(this) & 1152921504606846975L)) {
                    if (mVar.j(i4, objK2, g.f2205h)) {
                        n();
                        return g.f2212o;
                    }
                } else {
                    if (obj == null) {
                        return g.f2211n;
                    }
                    if (mVar.j(i4, objK2, obj)) {
                        n();
                        return g.f2210m;
                    }
                }
            } else if (objK2 != g.f2201d) {
                a3.h hVar = g.f2207j;
                if (objK2 == hVar) {
                    return g.f2212o;
                }
                if (objK2 == g.f2205h) {
                    return g.f2212o;
                }
                if (objK2 == g.f2209l) {
                    n();
                    return g.f2212o;
                }
                if (objK2 != g.f2204g && mVar.j(i4, objK2, g.f2203f)) {
                    boolean z3 = objK2 instanceof w;
                    if (z3) {
                        objK2 = ((w) objK2).f2231a;
                    }
                    if (D(objK2)) {
                        mVar.n(i4, g.f2206i);
                        n();
                        Object obj3 = atomicReferenceArray.get(i4 * 2);
                        mVar.m(i4, null);
                        return obj3;
                    }
                    mVar.n(i4, hVar);
                    mVar.h();
                    if (z3) {
                        n();
                    }
                    return g.f2212o;
                }
            } else if (mVar.j(i4, objK2, g.f2206i)) {
                n();
                Object obj4 = atomicReferenceArray.get(i4 * 2);
                mVar.m(i4, null);
                return obj4;
            }
        }
    }

    public final int F(m mVar, int i4, Object obj, long j4, Object obj2, boolean z3) {
        while (true) {
            Object objK = mVar.k(i4);
            if (objK == null) {
                if (!i(j4) || z3) {
                    if (z3) {
                        if (mVar.j(i4, null, g.f2207j)) {
                            mVar.h();
                            return 4;
                        }
                    } else {
                        if (obj2 == null) {
                            return 3;
                        }
                        if (mVar.j(i4, null, obj2)) {
                            return 2;
                        }
                    }
                } else if (mVar.j(i4, null, g.f2201d)) {
                    break;
                }
            } else {
                if (objK != g.f2202e) {
                    a3.h hVar = g.f2208k;
                    if (objK == hVar) {
                        mVar.m(i4, null);
                        return 5;
                    }
                    if (objK == g.f2205h) {
                        mVar.m(i4, null);
                        return 5;
                    }
                    if (objK == g.f2209l) {
                        mVar.m(i4, null);
                        u();
                        return 4;
                    }
                    mVar.m(i4, null);
                    if (objK instanceof w) {
                        objK = ((w) objK).f2231a;
                    }
                    if (C(objK, obj)) {
                        mVar.n(i4, g.f2206i);
                        return 0;
                    }
                    if (mVar.f2224f.getAndSet((i4 * 2) + 1, hVar) != hVar) {
                        mVar.l(i4, true);
                    }
                    return 5;
                }
                if (mVar.j(i4, objK, g.f2201d)) {
                    break;
                }
            }
        }
        return 1;
    }

    public final void G(long j4) {
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        e eVar = this;
        if (eVar.w()) {
            return;
        }
        while (true) {
            atomicLongFieldUpdater = f2189g;
            if (atomicLongFieldUpdater.get(eVar) > j4) {
                break;
            } else {
                eVar = this;
            }
        }
        int i4 = g.f2200c;
        int i5 = 0;
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater2 = f2190h;
            if (i5 < i4) {
                long j5 = atomicLongFieldUpdater.get(eVar);
                if (j5 == (4611686018427387903L & atomicLongFieldUpdater2.get(eVar)) && j5 == atomicLongFieldUpdater.get(eVar)) {
                    return;
                } else {
                    i5++;
                }
            } else {
                while (true) {
                    long j6 = atomicLongFieldUpdater2.get(eVar);
                    if (atomicLongFieldUpdater2.compareAndSet(eVar, j6, (j6 & 4611686018427387903L) + 4611686018427387904L)) {
                        break;
                    } else {
                        eVar = this;
                    }
                }
                while (true) {
                    long j7 = atomicLongFieldUpdater.get(eVar);
                    long j8 = atomicLongFieldUpdater2.get(eVar);
                    long j9 = j8 & 4611686018427387903L;
                    boolean z3 = (j8 & 4611686018427387904L) != 0;
                    if (j7 == j9 && j7 == atomicLongFieldUpdater.get(eVar)) {
                        break;
                    }
                    if (z3) {
                        eVar = this;
                    } else {
                        eVar = this;
                        atomicLongFieldUpdater2.compareAndSet(eVar, j8, 4611686018427387904L + j9);
                    }
                }
                while (true) {
                    long j10 = atomicLongFieldUpdater2.get(eVar);
                    if (atomicLongFieldUpdater2.compareAndSet(eVar, j10, j10 & 4611686018427387903L)) {
                        return;
                    } else {
                        eVar = this;
                    }
                }
            }
        }
    }

    @Override // t2.u
    public final Object a(v2.i iVar) {
        return z(this, iVar);
    }

    @Override // t2.u
    public final void b(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new CancellationException("Channel was cancelled");
        }
        k(cancellationException, true);
    }

    /* JADX WARN: Code duplicated, block: B:93:0x0160  */
    /* JADX WARN: Code duplicated, block: B:95:0x0163 A[RETURN] */
    @Override // t2.v
    public Object d(Object obj, y1.c cVar) throws Throwable {
        u1.k kVar;
        Object objU;
        Object obj2;
        e eVar;
        m mVar;
        int i4;
        e eVar2 = this;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2191i;
        m mVar2 = (m) atomicReferenceFieldUpdater.get(eVar2);
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f2187e;
            long andIncrement = atomicLongFieldUpdater.getAndIncrement(eVar2);
            long j4 = andIncrement & 1152921504606846975L;
            boolean zT = eVar2.t(andIncrement, false);
            int i5 = g.f2199b;
            long j5 = i5;
            long j6 = j4 / j5;
            int i6 = (int) (j4 % j5);
            long j7 = mVar2.f2649c;
            z1.a aVar = z1.a.f2781d;
            kVar = u1.k.f2301a;
            if (j7 != j6) {
                m mVarC = c(eVar2, j6, mVar2);
                if (mVarC != null) {
                    mVar2 = mVarC;
                } else if (zT) {
                    Object objY = y(obj, cVar);
                    if (objY != aVar) {
                        break;
                    }
                    return objY;
                }
            }
            int iG = g(eVar2, mVar2, i6, obj, j4, null, zT);
            if (iG == 0) {
                mVar2.a();
                return kVar;
            }
            if (iG == 1) {
                break;
            }
            if (iG != 2) {
                AtomicLongFieldUpdater atomicLongFieldUpdater2 = f2188f;
                if (iG == 3) {
                    r2.i iVarL = x.l(z1.d.a(cVar));
                    Object obj3 = obj;
                    try {
                        int iG2 = g(eVar2, mVar2, i6, obj3, j4, iVarL, false);
                        try {
                            if (iG2 != 0) {
                                if (iG2 == 1) {
                                    iVarL.j(kVar);
                                } else if (iG2 != 2) {
                                    if (iG2 != 4) {
                                        String str = "unexpected";
                                        if (iG2 != 5) {
                                            throw new IllegalStateException("unexpected");
                                        }
                                        mVar2.a();
                                        m mVar3 = (m) atomicReferenceFieldUpdater.get(eVar2);
                                        while (true) {
                                            long andIncrement2 = atomicLongFieldUpdater.getAndIncrement(eVar2);
                                            long j8 = andIncrement2 & 1152921504606846975L;
                                            boolean zT2 = eVar2.t(andIncrement2, false);
                                            int i7 = g.f2199b;
                                            long j9 = i7;
                                            str = str;
                                            long j10 = j8 / j9;
                                            int i8 = (int) (j8 % j9);
                                            if (mVar3.f2649c != j10) {
                                                m mVarC2 = c(eVar2, j10, mVar3);
                                                if (mVarC2 != null) {
                                                    i4 = i7;
                                                    mVar = mVarC2;
                                                } else if (zT2) {
                                                    e(eVar2, obj3, iVarL);
                                                    break;
                                                }
                                            } else {
                                                mVar = mVar3;
                                                i4 = i7;
                                            }
                                            int iG3 = g(eVar2, mVar, i8, obj3, j8, iVarL, zT2);
                                            Object obj4 = obj3;
                                            eVar = eVar2;
                                            m mVar4 = mVar;
                                            obj2 = obj4;
                                            if (iG3 == 0) {
                                                mVar4.a();
                                            } else if (iG3 != 1) {
                                                if (iG3 == 2) {
                                                    if (!zT2) {
                                                        iVarL.a(mVar4, i8 + i4);
                                                        break;
                                                    }
                                                    mVar4.h();
                                                } else {
                                                    if (iG3 == 3) {
                                                        throw new IllegalStateException(str);
                                                    }
                                                    if (iG3 != 4) {
                                                        if (iG3 == 5) {
                                                            mVar4.a();
                                                        }
                                                        mVar3 = mVar4;
                                                        eVar2 = eVar;
                                                        obj3 = obj2;
                                                    } else if (j8 < atomicLongFieldUpdater2.get(eVar)) {
                                                        mVar4.a();
                                                    }
                                                }
                                            }
                                        }
                                        iVarL.C();
                                        throw th;
                                    }
                                    obj2 = obj3;
                                    eVar = eVar2;
                                    if (j4 < atomicLongFieldUpdater2.get(eVar)) {
                                        mVar2.a();
                                    }
                                    e(eVar, obj2, iVarL);
                                    break;
                                } else {
                                    iVarL.a(mVar2, i6 + i5);
                                }
                                objU = iVarL.u();
                                if (objU != aVar) {
                                    objU = kVar;
                                }
                                if (objU == aVar) {
                                    return objU;
                                }
                            } else {
                                mVar2.a();
                            }
                            iVarL.j(kVar);
                            objU = iVarL.u();
                            if (objU != aVar) {
                                objU = kVar;
                            }
                            if (objU == aVar) {
                                return objU;
                            }
                        } catch (Throwable th) {
                            th = th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } else {
                    if (iG == 4) {
                        if (j4 < atomicLongFieldUpdater2.get(eVar2)) {
                            mVar2.a();
                        }
                        Object objY2 = y(obj, cVar);
                        if (objY2 != aVar) {
                            break;
                        }
                        return objY2;
                    }
                    if (iG == 5) {
                        mVar2.a();
                    }
                }
            } else if (zT) {
                mVar2.h();
                Object objY3 = y(obj, cVar);
                if (objY3 == aVar) {
                    return objY3;
                }
            }
            return kVar;
        }
        return kVar;
    }

    @Override // t2.u
    public final Object f() {
        m mVar;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f2188f;
        long j4 = atomicLongFieldUpdater.get(this);
        AtomicLongFieldUpdater atomicLongFieldUpdater2 = f2187e;
        long j5 = atomicLongFieldUpdater2.get(this);
        if (t(j5, true)) {
            return new j(p());
        }
        long j6 = j5 & 1152921504606846975L;
        k kVar = l.f2221b;
        if (j4 >= j6) {
            return kVar;
        }
        Object obj = g.f2208k;
        m mVar2 = (m) f2192j.get(this);
        while (!t(atomicLongFieldUpdater2.get(this), true)) {
            long andIncrement = atomicLongFieldUpdater.getAndIncrement(this);
            long j7 = g.f2199b;
            long j8 = andIncrement / j7;
            int i4 = (int) (andIncrement % j7);
            if (mVar2.f2649c != j8) {
                m mVarO = o(j8, mVar2);
                if (mVarO == null) {
                    continue;
                } else {
                    mVar = mVarO;
                }
            } else {
                mVar = mVar2;
            }
            Object objE = E(mVar, i4, andIncrement, obj);
            m mVar3 = mVar;
            if (objE == g.f2210m) {
                r1 r1Var = obj instanceof r1 ? (r1) obj : null;
                if (r1Var != null) {
                    r1Var.a(mVar3, i4);
                }
                G(andIncrement);
                mVar3.h();
                return kVar;
            }
            if (objE != g.f2212o) {
                if (objE == g.f2211n) {
                    throw new IllegalStateException("unexpected");
                }
                mVar3.a();
                return objE;
            }
            if (andIncrement < r()) {
                mVar3.a();
            }
            mVar2 = mVar3;
        }
        return new j(p());
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0068  */
    /* JADX WARN: Code duplicated, block: B:24:0x006b  */
    /* JADX WARN: Code duplicated, block: B:26:0x006e  */
    /* JADX WARN: Code duplicated, block: B:28:0x0071  */
    /* JADX WARN: Code duplicated, block: B:30:0x0074  */
    /* JADX WARN: Code duplicated, block: B:33:0x0078  */
    /* JADX WARN: Code duplicated, block: B:37:0x0087  */
    /* JADX WARN: Code duplicated, block: B:43:0x009e  */
    /* JADX WARN: Code duplicated, block: B:45:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:47:0x00af  */
    /* JADX WARN: Code duplicated, block: B:48:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:50:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:57:0x00be A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:58:0x00bd A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:59:0x009c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:60:0x0094 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:61:0x007d A[SYNTHETIC] */
    @Override // t2.v
    public Object h(Object obj) {
        int iG;
        u1.k kVar;
        r1 r1Var;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f2187e;
        long j4 = atomicLongFieldUpdater.get(this);
        boolean z3 = false;
        long j5 = 1152921504606846975L;
        boolean z4 = t(j4, false) ? false : !i(j4 & 1152921504606846975L);
        k kVar2 = l.f2221b;
        if (z4) {
            return kVar2;
        }
        o.f fVar = g.f2207j;
        m mVar = (m) f2191i.get(this);
        while (true) {
            long andIncrement = atomicLongFieldUpdater.getAndIncrement(this);
            long j6 = andIncrement & j5;
            boolean zT = t(andIncrement, z3);
            int i4 = g.f2199b;
            long j7 = i4;
            long j8 = j6 / j7;
            int i5 = (int) (j6 % j7);
            if (mVar.f2649c == j8) {
                iG = g(this, mVar, i5, obj, j6, fVar, zT);
                kVar = u1.k.f2301a;
                if (iG != 0) {
                    mVar.a();
                    return kVar;
                }
                if (iG != 1) {
                    return kVar;
                }
                if (iG != 2) {
                    if (zT) {
                        mVar.h();
                        return new j(q());
                    }
                    if (fVar instanceof r1) {
                        r1Var = (r1) fVar;
                    } else {
                        r1Var = null;
                    }
                    if (r1Var != null) {
                        r1Var.a(mVar, i5 + i4);
                    }
                    mVar.h();
                    return kVar2;
                }
                if (iG != 3) {
                    throw new IllegalStateException("unexpected");
                }
                if (iG != 4) {
                    if (j6 < f2188f.get(this)) {
                        mVar.a();
                    }
                    return new j(q());
                }
                if (iG == 5) {
                    mVar.a();
                }
                z3 = false;
            } else {
                m mVarC = c(this, j8, mVar);
                if (mVarC != null) {
                    mVar = mVarC;
                    iG = g(this, mVar, i5, obj, j6, fVar, zT);
                    kVar = u1.k.f2301a;
                    if (iG != 0) {
                        mVar.a();
                        return kVar;
                    }
                    if (iG != 1) {
                        return kVar;
                    }
                    if (iG != 2) {
                        if (zT) {
                            mVar.h();
                            return new j(q());
                        }
                        if (fVar instanceof r1) {
                            r1Var = (r1) fVar;
                        } else {
                            r1Var = null;
                        }
                        if (r1Var != null) {
                            r1Var.a(mVar, i5 + i4);
                        }
                        mVar.h();
                        return kVar2;
                    }
                    if (iG != 3) {
                        throw new IllegalStateException("unexpected");
                    }
                    if (iG != 4) {
                        if (j6 < f2188f.get(this)) {
                            mVar.a();
                        }
                        return new j(q());
                    }
                    if (iG == 5) {
                        mVar.a();
                    }
                    z3 = false;
                } else {
                    if (zT) {
                        return new j(q());
                    }
                    z3 = false;
                }
            }
            j5 = 1152921504606846975L;
        }
    }

    public final boolean i(long j4) {
        return j4 < f2189g.get(this) || j4 < f2188f.get(this) + ((long) this.f2196d);
    }

    @Override // t2.u
    public final b iterator() {
        return new b(this);
    }

    public final boolean j(Throwable th) {
        return k(th, false);
    }

    public final boolean k(Throwable th, boolean z3) {
        e eVar;
        boolean z4;
        long j4;
        long j5;
        long j6;
        Object obj;
        long j7;
        long j8;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f2187e;
        if (!z3) {
            eVar = this;
            break;
        }
        do {
            j8 = atomicLongFieldUpdater.get(this);
            if (((int) (j8 >> 60)) != 0) {
                eVar = this;
                break;
            }
            m mVar = g.f2198a;
            eVar = this;
        } while (!atomicLongFieldUpdater.compareAndSet(eVar, j8, (j8 & 1152921504606846975L) + (((long) 1) << 60)));
        a3.h hVar = g.f2216s;
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2194l;
            if (atomicReferenceFieldUpdater.compareAndSet(this, hVar, th)) {
                z4 = true;
                break;
            }
            if (atomicReferenceFieldUpdater.get(this) != hVar) {
                z4 = false;
                break;
            }
        }
        if (z3) {
            do {
                j7 = atomicLongFieldUpdater.get(this);
            } while (!atomicLongFieldUpdater.compareAndSet(eVar, j7, (((long) 3) << 60) + (j7 & 1152921504606846975L)));
        } else {
            do {
                j4 = atomicLongFieldUpdater.get(this);
                int i4 = (int) (j4 >> 60);
                if (i4 == 0) {
                    j5 = j4 & 1152921504606846975L;
                    j6 = 2;
                } else {
                    if (i4 != 1) {
                        break;
                    }
                    j5 = j4 & 1152921504606846975L;
                    j6 = 3;
                }
            } while (!atomicLongFieldUpdater.compareAndSet(eVar, j4, (j6 << 60) + j5));
        }
        u();
        if (z4) {
            loop3: while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f2195m;
                obj = atomicReferenceFieldUpdater2.get(this);
                a3.h hVar2 = obj == null ? g.f2214q : g.f2215r;
                do {
                    if (atomicReferenceFieldUpdater2.compareAndSet(this, obj, hVar2)) {
                        break loop3;
                    }
                } while (atomicReferenceFieldUpdater2.get(this) == obj);
            }
            if (obj != null) {
                j2.q.a(1, obj);
                ((i2.l) obj).h(p());
                return z4;
            }
        }
        return z4;
    }

    public final m l(long j4) {
        Object objF;
        long j5;
        Object obj = f2193k.get(this);
        m mVar = (m) f2191i.get(this);
        if (mVar.f2649c > ((m) obj).f2649c) {
            obj = mVar;
        }
        m mVar2 = (m) f2192j.get(this);
        if (mVar2.f2649c > ((m) obj).f2649c) {
            obj = mVar2;
        }
        w2.b bVar = (w2.b) obj;
        loop0: while (true) {
            bVar.getClass();
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = w2.b.f2616a;
            Object obj2 = atomicReferenceFieldUpdater.get(bVar);
            a3.h hVar = w2.a.f2609a;
            objF = null;
            if (obj2 == hVar) {
                break;
            }
            w2.b bVar2 = (w2.b) obj2;
            if (bVar2 == null) {
                do {
                    if (atomicReferenceFieldUpdater.compareAndSet(bVar, null, hVar)) {
                        break loop0;
                    }
                } while (atomicReferenceFieldUpdater.get(bVar) == null);
            } else {
                bVar = bVar2;
            }
        }
        m mVar3 = (m) bVar;
        if (v()) {
            m mVar4 = mVar3;
            loop2: while (true) {
                int i4 = g.f2199b - 1;
                while (true) {
                    if (-1 < i4) {
                        j5 = (mVar4.f2649c * ((long) g.f2199b)) + ((long) i4);
                        if (j5 >= f2188f.get(this)) {
                            while (true) {
                                Object objK = mVar4.k(i4);
                                if (objK != null && objK != g.f2202e) {
                                    if (objK != g.f2201d) {
                                        break;
                                    }
                                    break loop2;
                                }
                                if (mVar4.j(i4, objK, g.f2209l)) {
                                    mVar4.h();
                                    break;
                                }
                            }
                            i4--;
                        }
                    } else {
                        mVar4 = (m) ((w2.b) w2.b.f2617b.get(mVar4));
                        if (mVar4 == null) {
                        }
                    }
                    j5 = -1;
                    break;
                }
            }
            if (j5 != -1) {
                m(j5);
            }
        }
        loop5: for (m mVar5 = mVar3; mVar5 != null; mVar5 = (m) ((w2.b) w2.b.f2617b.get(mVar5))) {
            for (int i5 = g.f2199b - 1; -1 < i5; i5--) {
                if ((mVar5.f2649c * ((long) g.f2199b)) + ((long) i5) < j4) {
                    break loop5;
                }
                while (true) {
                    Object objK2 = mVar5.k(i5);
                    if (objK2 != null && objK2 != g.f2202e) {
                        if (!(objK2 instanceof w)) {
                            if (!(objK2 instanceof r1)) {
                                break;
                            }
                            if (mVar5.j(i5, objK2, g.f2209l)) {
                                objF = w2.a.f(objF, objK2);
                                mVar5.l(i5, true);
                                break;
                            }
                        } else {
                            if (mVar5.j(i5, objK2, g.f2209l)) {
                                objF = w2.a.f(objF, ((w) objK2).f2231a);
                                mVar5.l(i5, true);
                                break;
                            }
                        }
                    } else {
                        if (mVar5.j(i5, objK2, g.f2209l)) {
                            mVar5.h();
                            break;
                        }
                    }
                }
            }
        }
        if (objF != null) {
            if (!(objF instanceof ArrayList)) {
                B((r1) objF, true);
                return mVar3;
            }
            ArrayList arrayList = (ArrayList) objF;
            for (int size = arrayList.size() - 1; -1 < size; size--) {
                B((r1) arrayList.get(size), true);
            }
        }
        return mVar3;
    }

    public final void m(long j4) {
        m mVar = (m) f2192j.get(this);
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f2188f;
            long j5 = atomicLongFieldUpdater.get(this);
            if (j4 < Math.max(((long) this.f2196d) + j5, f2189g.get(this))) {
                return;
            }
            if (atomicLongFieldUpdater.compareAndSet(this, j5, 1 + j5)) {
                long j6 = g.f2199b;
                long j7 = j5 / j6;
                int i4 = (int) (j5 % j6);
                if (mVar.f2649c != j7) {
                    m mVarO = o(j7, mVar);
                    if (mVarO != null) {
                        mVar = mVarO;
                    }
                }
                m mVar2 = mVar;
                if (E(mVar2, i4, j5, null) != g.f2212o || j5 < r()) {
                    mVar2.a();
                }
                mVar = mVar2;
            }
        }
    }

    public final void n() {
        Object objB;
        if (w()) {
            return;
        }
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2193k;
        m mVar = (m) atomicReferenceFieldUpdater.get(this);
        while (true) {
            long andIncrement = f2189g.getAndIncrement(this);
            long j4 = andIncrement / ((long) g.f2199b);
            if (r() <= andIncrement) {
                if (mVar.f2649c < j4 && mVar.b() != null) {
                    x(j4, mVar);
                }
                s(this);
                return;
            }
            if (mVar.f2649c != j4) {
                f fVar = f.f2197k;
                while (true) {
                    objB = w2.a.b(mVar, j4, fVar);
                    if (!w2.a.e(objB)) {
                        w2.r rVarC = w2.a.c(objB);
                        while (true) {
                            w2.r rVar = (w2.r) atomicReferenceFieldUpdater.get(this);
                            if (rVar.f2649c >= rVarC.f2649c) {
                                break;
                            }
                            if (!rVarC.i()) {
                                break;
                            }
                            do {
                                if (atomicReferenceFieldUpdater.compareAndSet(this, rVar, rVarC)) {
                                    if (!rVar.e()) {
                                        break;
                                    }
                                    rVar.d();
                                    break;
                                }
                            } while (atomicReferenceFieldUpdater.get(this) == rVar);
                            if (rVarC.e()) {
                                rVarC.d();
                            }
                        }
                    } else {
                        break;
                    }
                }
                m mVar2 = null;
                if (w2.a.e(objB)) {
                    u();
                    x(j4, mVar);
                    s(this);
                } else {
                    m mVar3 = (m) w2.a.c(objB);
                    long j5 = mVar3.f2649c;
                    if (j5 > j4) {
                        long j6 = j5 * ((long) g.f2199b);
                        if (f2189g.compareAndSet(this, 1 + andIncrement, j6)) {
                            AtomicLongFieldUpdater atomicLongFieldUpdater = f2190h;
                            if ((atomicLongFieldUpdater.addAndGet(this, j6 - andIncrement) & 4611686018427387904L) != 0) {
                                while ((atomicLongFieldUpdater.get(this) & 4611686018427387904L) != 0) {
                                }
                            }
                        } else {
                            s(this);
                        }
                    } else {
                        mVar2 = mVar3;
                    }
                }
                if (mVar2 == null) {
                    continue;
                } else {
                    mVar = mVar2;
                }
            }
            int i4 = (int) (andIncrement % ((long) g.f2199b));
            Object objK = mVar.k(i4);
            boolean z3 = objK instanceof r1;
            AtomicLongFieldUpdater atomicLongFieldUpdater2 = f2188f;
            if (!z3 || andIncrement < atomicLongFieldUpdater2.get(this) || !mVar.j(i4, objK, g.f2204g)) {
                while (true) {
                    Object objK2 = mVar.k(i4);
                    if (objK2 instanceof r1) {
                        if (andIncrement < atomicLongFieldUpdater2.get(this)) {
                            if (mVar.j(i4, objK2, new w((r1) objK2))) {
                                s(this);
                                return;
                            }
                        } else if (mVar.j(i4, objK2, g.f2204g)) {
                            if (!D(objK2)) {
                                mVar.n(i4, g.f2207j);
                                mVar.h();
                                break;
                            } else {
                                mVar.n(i4, g.f2201d);
                                s(this);
                                return;
                            }
                        }
                    } else {
                        if (objK2 == g.f2207j) {
                            break;
                        }
                        if (objK2 == null) {
                            if (mVar.j(i4, objK2, g.f2202e)) {
                                s(this);
                                return;
                            }
                        } else if (objK2 == g.f2201d || objK2 == g.f2205h || objK2 == g.f2206i || objK2 == g.f2208k || objK2 == g.f2209l) {
                            s(this);
                            return;
                        } else if (objK2 != g.f2203f) {
                            throw new IllegalStateException(("Unexpected cell state: " + objK2).toString());
                        }
                    }
                }
                s(this);
            } else if (D(objK)) {
                mVar.n(i4, g.f2201d);
                s(this);
                return;
            } else {
                mVar.n(i4, g.f2207j);
                mVar.h();
                s(this);
            }
        }
    }

    public final m o(long j4, m mVar) {
        Object objB;
        long j5;
        m mVar2 = g.f2198a;
        f fVar = f.f2197k;
        loop0: while (true) {
            objB = w2.a.b(mVar, j4, fVar);
            if (!w2.a.e(objB)) {
                w2.r rVarC = w2.a.c(objB);
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2192j;
                    w2.r rVar = (w2.r) atomicReferenceFieldUpdater.get(this);
                    if (rVar.f2649c >= rVarC.f2649c) {
                        break loop0;
                    }
                    if (!rVarC.i()) {
                        break;
                    }
                    do {
                        if (atomicReferenceFieldUpdater.compareAndSet(this, rVar, rVarC)) {
                            if (!rVar.e()) {
                                break loop0;
                            }
                            rVar.d();
                            break loop0;
                        }
                    } while (atomicReferenceFieldUpdater.get(this) == rVar);
                    if (rVarC.e()) {
                        rVarC.d();
                    }
                }
            } else {
                break;
            }
        }
        if (w2.a.e(objB)) {
            u();
            if (mVar.f2649c * ((long) g.f2199b) < r()) {
                mVar.a();
                return null;
            }
        } else {
            m mVar3 = (m) w2.a.c(objB);
            long j6 = mVar3.f2649c;
            if (!w() && j4 <= f2189g.get(this) / ((long) g.f2199b)) {
                loop3: while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f2193k;
                    w2.r rVar2 = (w2.r) atomicReferenceFieldUpdater2.get(this);
                    if (rVar2.f2649c >= j6 || !mVar3.i()) {
                        break;
                    }
                    do {
                        if (atomicReferenceFieldUpdater2.compareAndSet(this, rVar2, mVar3)) {
                            if (!rVar2.e()) {
                                break loop3;
                            }
                            rVar2.d();
                            break loop3;
                        }
                    } while (atomicReferenceFieldUpdater2.get(this) == rVar2);
                    if (mVar3.e()) {
                        mVar3.d();
                    }
                }
            }
            if (j6 <= j4) {
                return mVar3;
            }
            long j7 = j6 * ((long) g.f2199b);
            do {
                j5 = f2188f.get(this);
                if (j5 >= j7) {
                    break;
                }
            } while (!f2188f.compareAndSet(this, j5, j7));
            if (j6 * ((long) g.f2199b) < r()) {
                mVar3.a();
            }
        }
        return null;
    }

    public final Throwable p() {
        return (Throwable) f2194l.get(this);
    }

    public final Throwable q() {
        Throwable thP = p();
        return thP == null ? new o("Channel was closed") : thP;
    }

    public final long r() {
        return f2187e.get(this) & 1152921504606846975L;
    }

    public final boolean t(long j4, boolean z3) {
        int i4 = (int) (j4 >> 60);
        if (i4 != 0 && i4 != 1) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f2188f;
            if (i4 == 2) {
                l(1152921504606846975L & j4);
                if (z3) {
                    while (true) {
                        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2192j;
                        m mVarO = (m) atomicReferenceFieldUpdater.get(this);
                        long j5 = atomicLongFieldUpdater.get(this);
                        if (r() <= j5) {
                            break;
                        }
                        long j6 = g.f2199b;
                        long j7 = j5 / j6;
                        if (mVarO.f2649c != j7 && (mVarO = o(j7, mVarO)) == null) {
                            if (((m) atomicReferenceFieldUpdater.get(this)).f2649c < j7) {
                                break;
                            }
                        } else {
                            mVarO.a();
                            int i5 = (int) (j5 % j6);
                            while (true) {
                                Object objK = mVarO.k(i5);
                                if (objK != null && objK != g.f2202e) {
                                    if (objK != g.f2201d && (objK == g.f2207j || objK == g.f2209l || objK == g.f2206i || objK == g.f2205h || (objK != g.f2204g && (objK == g.f2203f || j5 != atomicLongFieldUpdater.get(this))))) {
                                        break;
                                        break;
                                        break;
                                        break;
                                        break;
                                        break;
                                    }
                                } else if (mVarO.j(i5, objK, g.f2205h)) {
                                    n();
                                    break;
                                }
                            }
                            f2188f.compareAndSet(this, j5, j5 + 1);
                        }
                    }
                }
            } else {
                if (i4 != 3) {
                    throw new IllegalStateException(a1.c.c(i4, "unexpected close status: ").toString());
                }
                m mVarL = l(1152921504606846975L & j4);
                Object objF = null;
                loop0: do {
                    for (int i6 = g.f2199b - 1; -1 < i6; i6--) {
                        long j8 = (mVarL.f2649c * ((long) g.f2199b)) + ((long) i6);
                        while (true) {
                            Object objK2 = mVarL.k(i6);
                            if (objK2 == g.f2206i) {
                                break loop0;
                            }
                            if (objK2 != g.f2201d) {
                                if (objK2 != g.f2202e && objK2 != null) {
                                    if (!(objK2 instanceof r1) && !(objK2 instanceof w)) {
                                        a3.h hVar = g.f2204g;
                                        if (objK2 == hVar || objK2 == g.f2203f) {
                                            break loop0;
                                        }
                                        if (objK2 != hVar) {
                                            break;
                                        }
                                    } else {
                                        if (j8 < atomicLongFieldUpdater.get(this)) {
                                            break loop0;
                                        }
                                        r1 r1Var = objK2 instanceof w ? ((w) objK2).f2231a : (r1) objK2;
                                        if (mVarL.j(i6, objK2, g.f2209l)) {
                                            objF = w2.a.f(objF, r1Var);
                                            mVarL.m(i6, null);
                                            mVarL.h();
                                            break;
                                        }
                                    }
                                } else {
                                    if (mVarL.j(i6, objK2, g.f2209l)) {
                                        mVarL.h();
                                        break;
                                    }
                                }
                            } else {
                                if (j8 < atomicLongFieldUpdater.get(this)) {
                                    break loop0;
                                }
                                if (mVarL.j(i6, objK2, g.f2209l)) {
                                    mVarL.m(i6, null);
                                    mVarL.h();
                                    break;
                                }
                            }
                        }
                    }
                    mVarL = (m) ((w2.b) w2.b.f2617b.get(mVarL));
                } while (mVarL != null);
                if (objF != null) {
                    if (objF instanceof ArrayList) {
                        ArrayList arrayList = (ArrayList) objF;
                        for (int size = arrayList.size() - 1; -1 < size; size--) {
                            B((r1) arrayList.get(size), false);
                        }
                    } else {
                        B((r1) objF, false);
                    }
                }
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String toString() {
        String string;
        StringBuilder sb = new StringBuilder();
        int i4 = (int) (f2187e.get(this) >> 60);
        if (i4 == 2) {
            sb.append("closed,");
        } else if (i4 == 3) {
            sb.append("cancelled,");
        }
        sb.append("capacity=" + this.f2196d + ',');
        sb.append("data=[");
        int i5 = 0;
        boolean z3 = true;
        List listR = v1.i.R(new m[]{f2192j.get(this), f2191i.get(this), f2193k.get(this)});
        ArrayList arrayList = new ArrayList();
        for (Object obj : listR) {
            if (((m) obj) != g.f2198a) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        Object next = it.next();
        if (it.hasNext()) {
            long j4 = ((m) next).f2649c;
            do {
                Object next2 = it.next();
                long j5 = ((m) next2).f2649c;
                if (j4 > j5) {
                    next = next2;
                    j4 = j5;
                }
            } while (it.hasNext());
        }
        m mVar = (m) next;
        long j6 = f2188f.get(this);
        long jR = r();
        loop2: while (true) {
            int i6 = g.f2199b;
            int i7 = i5;
            while (i7 < i6) {
                long j7 = (mVar.f2649c * ((long) g.f2199b)) + ((long) i7);
                if (j7 >= jR && j7 >= j6) {
                    break loop2;
                }
                Object objK = mVar.k(i7);
                boolean z4 = z3;
                Object obj2 = mVar.f2224f.get(i7 * 2);
                if (objK instanceof r2.g) {
                    string = (j7 >= j6 || j7 < jR) ? (j7 >= jR || j7 < j6) ? "cont" : "send" : "receive";
                } else if (objK instanceof t) {
                    string = "receiveCatching";
                } else if (objK instanceof w) {
                    string = "EB(" + objK + ')';
                } else if (j2.i.a(objK, g.f2203f) || j2.i.a(objK, g.f2204g)) {
                    string = "resuming_sender";
                } else {
                    if (objK != null && !objK.equals(g.f2202e) && !objK.equals(g.f2206i) && !objK.equals(g.f2205h) && !objK.equals(g.f2208k) && !objK.equals(g.f2207j) && !objK.equals(g.f2209l)) {
                        string = objK.toString();
                    }
                    i7++;
                    z3 = z4;
                }
                if (obj2 != null) {
                    sb.append("(" + string + ',' + obj2 + "),");
                } else {
                    sb.append(string + ',');
                }
                i7++;
                z3 = z4;
            }
            boolean z5 = z3;
            mVar = (m) mVar.b();
            if (mVar == null) {
                break;
            }
            z3 = z5;
            i5 = 0;
        }
        if (sb.length() == 0) {
            throw new NoSuchElementException("Char sequence is empty.");
        }
        if (sb.charAt(p2.i.C0(sb)) == ',') {
            j2.i.d(sb.deleteCharAt(sb.length() - 1), "deleteCharAt(...)");
        }
        sb.append("]");
        return sb.toString();
    }

    public final boolean u() {
        return t(f2187e.get(this), false);
    }

    public boolean v() {
        return false;
    }

    public final boolean w() {
        long j4 = f2189g.get(this);
        return j4 == 0 || j4 == Long.MAX_VALUE;
    }

    public final void x(long j4, m mVar) {
        m mVar2;
        m mVar3;
        while (mVar.f2649c < j4 && (mVar3 = (m) mVar.b()) != null) {
            mVar = mVar3;
        }
        while (true) {
            if (!mVar.c() || (mVar2 = (m) mVar.b()) == null) {
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2193k;
                    w2.r rVar = (w2.r) atomicReferenceFieldUpdater.get(this);
                    if (rVar.f2649c >= mVar.f2649c) {
                        return;
                    }
                    if (!mVar.i()) {
                        break;
                    }
                    do {
                        if (atomicReferenceFieldUpdater.compareAndSet(this, rVar, mVar)) {
                            if (rVar.e()) {
                                rVar.d();
                                return;
                            }
                            return;
                        }
                    } while (atomicReferenceFieldUpdater.get(this) == rVar);
                    if (mVar.e()) {
                        mVar.d();
                    }
                }
            } else {
                mVar = mVar2;
            }
        }
    }

    public final Object y(Object obj, y1.c cVar) {
        r2.i iVar = new r2.i(1, z1.d.a(cVar));
        iVar.v();
        iVar.j(l0.l(q()));
        Object objU = iVar.u();
        return objU == z1.a.f2781d ? objU : u1.k.f2301a;
    }
}
