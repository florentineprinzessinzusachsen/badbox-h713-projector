package t2;

import r2.r1;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class p extends e {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final a f2225n;

    public p(int i4, a aVar) {
        super(i4);
        this.f2225n = aVar;
        if (aVar != a.f2174d) {
            if (i4 < 1) {
                throw new IllegalArgumentException(a1.c.d(i4, "Buffered channel capacity must be at least 1, but ", " was specified").toString());
            }
        } else {
            throw new IllegalArgumentException(("This implementation does not support suspension for senders, use " + j2.o.a(e.class).c() + " instead").toString());
        }
    }

    public final Object H(Object obj, boolean z3) {
        a aVar = this.f2225n;
        a aVar2 = a.f2176f;
        u1.k kVar = u1.k.f2301a;
        if (aVar == aVar2) {
            Object objH = super.h(obj);
            return (!(objH instanceof k) || (objH instanceof j)) ? objH : kVar;
        }
        o.f fVar = g.f2201d;
        m mVar = (m) e.f2191i.get(this);
        while (true) {
            long andIncrement = e.f2187e.getAndIncrement(this);
            long j4 = 1152921504606846975L & andIncrement;
            boolean zT = t(andIncrement, false);
            int i4 = g.f2199b;
            long j5 = i4;
            long j6 = j4 / j5;
            int i5 = (int) (j4 % j5);
            if (mVar.f2649c != j6) {
                m mVarC = e.c(this, j6, mVar);
                if (mVarC != null) {
                    mVar = mVarC;
                } else if (zT) {
                    return new j(q());
                }
            }
            int iG = e.g(this, mVar, i5, obj, j4, fVar, zT);
            if (iG == 0) {
                mVar.a();
                return kVar;
            }
            if (iG != 1) {
                if (iG != 2) {
                    if (iG == 3) {
                        throw new IllegalStateException("unexpected");
                    }
                    if (iG == 4) {
                        if (j4 < e.f2188f.get(this)) {
                            mVar.a();
                        }
                        return new j(q());
                    }
                    if (iG == 5) {
                        mVar.a();
                    }
                } else {
                    if (zT) {
                        mVar.h();
                        return new j(q());
                    }
                    r1 r1Var = fVar instanceof r1 ? (r1) fVar : null;
                    if (r1Var != null) {
                        r1Var.a(mVar, i5 + i4);
                    }
                    m((mVar.f2649c * j5) + ((long) i5));
                }
            }
            return kVar;
        }
    }

    @Override // t2.e, t2.v
    public final Object d(Object obj, y1.c cVar) throws Throwable {
        if (H(obj, true) instanceof j) {
            throw q();
        }
        return u1.k.f2301a;
    }

    @Override // t2.e, t2.v
    public final Object h(Object obj) {
        return H(obj, false);
    }

    @Override // t2.e
    public final boolean v() {
        return this.f2225n == a.f2175e;
    }
}
