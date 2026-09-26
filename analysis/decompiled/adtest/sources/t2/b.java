package t2;

import d0.l0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import r2.r1;
import r2.x;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b implements r1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f2178d = g.f2213p;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public r2.i f2179e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ e f2180f;

    public b(e eVar) {
        this.f2180f = eVar;
    }

    @Override // r2.r1
    public final void a(w2.r rVar, int i4) {
        r2.i iVar = this.f2179e;
        if (iVar != null) {
            iVar.a(rVar, i4);
        }
    }

    public final Object b(u2.i iVar) throws Throwable {
        m mVar;
        m mVarO;
        Object obj = this.f2178d;
        boolean z3 = true;
        if (obj == g.f2213p || obj == g.f2209l) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = e.f2192j;
            e eVar = this.f2180f;
            m mVar2 = (m) atomicReferenceFieldUpdater.get(eVar);
            while (!eVar.t(e.f2187e.get(eVar), true)) {
                long andIncrement = e.f2188f.getAndIncrement(eVar);
                long j4 = g.f2199b;
                long j5 = andIncrement / j4;
                int i4 = (int) (andIncrement % j4);
                if (mVar2.f2649c != j5) {
                    m mVarO2 = eVar.o(j5, mVar2);
                    if (mVarO2 == null) {
                        continue;
                    } else {
                        mVar = mVarO2;
                    }
                } else {
                    mVar = mVar2;
                }
                Object objE = eVar.E(mVar, i4, andIncrement, null);
                a3.h hVar = g.f2210m;
                if (objE == hVar) {
                    throw new IllegalStateException("unreachable");
                }
                a3.h hVar2 = g.f2212o;
                if (objE == hVar2) {
                    if (andIncrement < eVar.r()) {
                        mVar.a();
                    }
                    mVar2 = mVar;
                } else if (objE == g.f2211n) {
                    r2.i iVarL = x.l(z1.d.a(iVar));
                    try {
                        this.f2179e = iVarL;
                        try {
                            Object objE2 = eVar.E(mVar, i4, andIncrement, this);
                            if (objE2 == hVar) {
                                a(mVar, i4);
                            } else {
                                if (objE2 == hVar2) {
                                    if (andIncrement < eVar.r()) {
                                        mVar.a();
                                    }
                                    m mVar3 = (m) e.f2192j.get(eVar);
                                    while (true) {
                                        if (eVar.t(e.f2187e.get(eVar), true)) {
                                            r2.i iVar2 = this.f2179e;
                                            j2.i.b(iVar2);
                                            this.f2179e = null;
                                            this.f2178d = g.f2209l;
                                            Throwable thP = eVar.p();
                                            if (thP == null) {
                                                iVar2.j(Boolean.FALSE);
                                            } else {
                                                iVar2.j(l0.l(thP));
                                            }
                                        } else {
                                            long andIncrement2 = e.f2188f.getAndIncrement(eVar);
                                            long j6 = g.f2199b;
                                            long j7 = andIncrement2 / j6;
                                            int i5 = (int) (andIncrement2 % j6);
                                            if (mVar3.f2649c != j7) {
                                                mVarO = eVar.o(j7, mVar3);
                                                if (mVarO == null) {
                                                }
                                            } else {
                                                mVarO = mVar3;
                                            }
                                            Object objE3 = eVar.E(mVarO, i5, andIncrement2, this);
                                            if (objE3 == g.f2210m) {
                                                a(mVarO, i5);
                                            } else {
                                                if (objE3 != g.f2212o) {
                                                    if (objE3 == g.f2211n) {
                                                        throw new IllegalStateException("unexpected");
                                                    }
                                                    mVarO.a();
                                                    this.f2178d = objE3;
                                                    this.f2179e = null;
                                                    break;
                                                }
                                                if (andIncrement2 < eVar.r()) {
                                                    mVarO.a();
                                                }
                                                mVar3 = mVarO;
                                            }
                                        }
                                    }
                                } else {
                                    mVar.a();
                                    this.f2178d = objE2;
                                    this.f2179e = null;
                                }
                                iVarL.m(Boolean.TRUE, null);
                            }
                            return iVarL.u();
                        } catch (Throwable th) {
                            th = th;
                            iVarL.C();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } else {
                    mVar.a();
                    this.f2178d = objE;
                }
            }
            this.f2178d = g.f2209l;
            Throwable thP2 = eVar.p();
            if (thP2 != null) {
                int i6 = w2.s.f2650a;
                throw thP2;
            }
            z3 = false;
        }
        return Boolean.valueOf(z3);
    }
}
