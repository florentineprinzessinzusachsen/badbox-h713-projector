package t2;

import java.util.concurrent.atomic.AtomicReferenceArray;
import r2.r1;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class m extends w2.r {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e f2223e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ AtomicReferenceArray f2224f;

    public m(long j4, m mVar, e eVar, int i4) {
        super(j4, mVar, i4);
        this.f2223e = eVar;
        this.f2224f = new AtomicReferenceArray(g.f2199b * 2);
    }

    @Override // w2.r
    public final int f() {
        return g.f2199b;
    }

    @Override // w2.r
    public final void g(int i4, y1.h hVar) {
        e eVar;
        int i5 = g.f2199b;
        boolean z3 = i4 >= i5;
        if (z3) {
            i4 -= i5;
        }
        this.f2224f.get(i4 * 2);
        while (true) {
            Object objK = k(i4);
            boolean z4 = objK instanceof r1;
            eVar = this.f2223e;
            if (z4 || (objK instanceof w)) {
                if (j(i4, objK, z3 ? g.f2207j : g.f2208k)) {
                    m(i4, null);
                    l(i4, !z3);
                    if (z3) {
                        j2.i.b(eVar);
                        return;
                    }
                    return;
                }
            } else {
                if (objK == g.f2207j || objK == g.f2208k) {
                    break;
                }
                if (objK != g.f2204g && objK != g.f2203f) {
                    if (objK == g.f2206i || objK == g.f2201d || objK == g.f2209l) {
                        return;
                    }
                    throw new IllegalStateException(("unexpected state: " + objK).toString());
                }
            }
        }
        m(i4, null);
        if (z3) {
            j2.i.b(eVar);
        }
    }

    public final boolean j(int i4, Object obj, Object obj2) {
        AtomicReferenceArray atomicReferenceArray;
        int i5 = (i4 * 2) + 1;
        do {
            atomicReferenceArray = this.f2224f;
            if (atomicReferenceArray.compareAndSet(i5, obj, obj2)) {
                return true;
            }
        } while (atomicReferenceArray.get(i5) == obj);
        return false;
    }

    public final Object k(int i4) {
        return this.f2224f.get((i4 * 2) + 1);
    }

    public final void l(int i4, boolean z3) {
        if (z3) {
            e eVar = this.f2223e;
            j2.i.b(eVar);
            eVar.G((this.f2649c * ((long) g.f2199b)) + ((long) i4));
        }
        h();
    }

    public final void m(int i4, Object obj) {
        this.f2224f.set(i4 * 2, obj);
    }

    public final void n(int i4, Object obj) {
        this.f2224f.set((i4 * 2) + 1, obj);
    }
}
