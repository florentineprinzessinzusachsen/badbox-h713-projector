package z2;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import r2.r1;
import u1.k;
import w2.r;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2789f = AtomicReferenceFieldUpdater.newUpdater(g.class, Object.class, "head$volatile");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f2790g = AtomicLongFieldUpdater.newUpdater(g.class, "deqIdx$volatile");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2791h = AtomicReferenceFieldUpdater.newUpdater(g.class, Object.class, "tail$volatile");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f2792i = AtomicLongFieldUpdater.newUpdater(g.class, "enqIdx$volatile");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f2793j = AtomicIntegerFieldUpdater.newUpdater(g.class, "_availablePermits$volatile");
    private volatile /* synthetic */ int _availablePermits$volatile;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2794d;
    private volatile /* synthetic */ long deqIdx$volatile;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final r2.h f2795e;
    private volatile /* synthetic */ long enqIdx$volatile;
    private volatile /* synthetic */ Object head$volatile;
    private volatile /* synthetic */ Object tail$volatile;

    public g(int i4) {
        this.f2794d = i4;
        if (i4 <= 0) {
            throw new IllegalArgumentException(a1.c.c(i4, "Semaphore should have at least 1 permit, but had ").toString());
        }
        if (i4 < 0) {
            throw new IllegalArgumentException(a1.c.c(i4, "The number of acquired permits should be in 0..").toString());
        }
        j jVar = new j(0L, null, 2);
        this.head$volatile = jVar;
        this.tail$volatile = jVar;
        this._availablePermits$volatile = i4;
        this.f2795e = new r2.h(2, this);
    }

    public final boolean a(r1 r1Var) {
        Object objB;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2791h;
        j jVar = (j) atomicReferenceFieldUpdater.get(this);
        long andIncrement = f2792i.getAndIncrement(this);
        e eVar = e.f2787k;
        long j4 = andIncrement / ((long) i.f2801f);
        loop0: while (true) {
            objB = w2.a.b(jVar, j4, eVar);
            if (!w2.a.e(objB)) {
                r rVarC = w2.a.c(objB);
                while (true) {
                    r rVar = (r) atomicReferenceFieldUpdater.get(this);
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
        j jVar2 = (j) w2.a.c(objB);
        AtomicReferenceArray atomicReferenceArray = jVar2.f2802e;
        int i4 = (int) (andIncrement % ((long) i.f2801f));
        while (!atomicReferenceArray.compareAndSet(i4, null, r1Var)) {
            if (atomicReferenceArray.get(i4) != null) {
                a3.h hVar = i.f2797b;
                a3.h hVar2 = i.f2798c;
                while (!atomicReferenceArray.compareAndSet(i4, hVar, hVar2)) {
                    if (atomicReferenceArray.get(i4) != hVar) {
                        return false;
                    }
                }
                ((r2.g) r1Var).m(k.f2301a, this.f2795e);
                return true;
            }
        }
        r1Var.a(jVar2, i4);
        return true;
    }

    public final void d() {
        int i4;
        Object objB;
        boolean z3;
        do {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f2793j;
            int andIncrement = atomicIntegerFieldUpdater.getAndIncrement(this);
            int i5 = this.f2794d;
            if (andIncrement >= i5) {
                do {
                    i4 = atomicIntegerFieldUpdater.get(this);
                    if (i4 <= i5) {
                        break;
                    }
                } while (!atomicIntegerFieldUpdater.compareAndSet(this, i4, i5));
                throw new IllegalStateException(("The number of released permits cannot be greater than " + i5).toString());
            }
            if (andIncrement >= 0) {
                return;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2789f;
            j jVar = (j) atomicReferenceFieldUpdater.get(this);
            long andIncrement2 = f2790g.getAndIncrement(this);
            long j4 = andIncrement2 / ((long) i.f2801f);
            f fVar = f.f2788k;
            while (true) {
                objB = w2.a.b(jVar, j4, fVar);
                if (!w2.a.e(objB)) {
                    r rVarC = w2.a.c(objB);
                    while (true) {
                        r rVar = (r) atomicReferenceFieldUpdater.get(this);
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
            j jVar2 = (j) w2.a.c(objB);
            AtomicReferenceArray atomicReferenceArray = jVar2.f2802e;
            jVar2.a();
            z3 = false;
            if (jVar2.f2649c <= j4) {
                int i6 = (int) (andIncrement2 % ((long) i.f2801f));
                Object andSet = atomicReferenceArray.getAndSet(i6, i.f2797b);
                if (andSet == null) {
                    int i7 = i.f2796a;
                    int i8 = 0;
                    while (true) {
                        if (i8 >= i7) {
                            a3.h hVar = i.f2797b;
                            a3.h hVar2 = i.f2799d;
                            do {
                                if (atomicReferenceArray.compareAndSet(i6, hVar, hVar2)) {
                                    z3 = true;
                                    break;
                                }
                            } while (atomicReferenceArray.get(i6) == hVar);
                            z3 = !z3;
                            break;
                        }
                        if (atomicReferenceArray.get(i6) == i.f2798c) {
                            z3 = true;
                            break;
                        }
                        i8++;
                    }
                } else if (andSet != i.f2800e) {
                    if (!(andSet instanceof r2.g)) {
                        throw new IllegalStateException(("unexpected: " + andSet).toString());
                    }
                    r2.g gVar = (r2.g) andSet;
                    a3.h hVarN = gVar.n(k.f2301a, this.f2795e);
                    if (hVarN != null) {
                        gVar.o(hVarN);
                        z3 = true;
                        break;
                        break;
                    }
                }
            }
        } while (!z3);
    }
}
