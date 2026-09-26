package w2;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2638e = AtomicReferenceFieldUpdater.newUpdater(m.class, Object.class, "_next$volatile");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f2639f = AtomicLongFieldUpdater.newUpdater(m.class, "_state$volatile");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final a3.h f2640g = new a3.h(10, "REMOVE_FROZEN");
    private volatile /* synthetic */ Object _next$volatile;
    private volatile /* synthetic */ long _state$volatile;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2641a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f2642b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2643c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ AtomicReferenceArray f2644d;

    public m(int i4, boolean z3) {
        this.f2641a = i4;
        this.f2642b = z3;
        int i5 = i4 - 1;
        this.f2643c = i5;
        this.f2644d = new AtomicReferenceArray(i4);
        if (i5 > 1073741823) {
            throw new IllegalStateException("Check failed.");
        }
        if ((i4 & i5) != 0) {
            throw new IllegalStateException("Check failed.");
        }
    }

    public final int a(Object obj) {
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f2639f;
            long j4 = atomicLongFieldUpdater.get(this);
            if ((3458764513820540928L & j4) != 0) {
                return (2305843009213693952L & j4) != 0 ? 2 : 1;
            }
            int i4 = (int) (1073741823 & j4);
            int i5 = (int) ((1152921503533105152L & j4) >> 30);
            int i6 = this.f2643c;
            if (((i5 + 2) & i6) == (i4 & i6)) {
                return 1;
            }
            boolean z3 = this.f2642b;
            AtomicReferenceArray atomicReferenceArray = this.f2644d;
            if (z3 || atomicReferenceArray.get(i5 & i6) == null) {
                if (f2639f.compareAndSet(this, j4, ((-1152921503533105153L) & j4) | (((long) ((i5 + 1) & 1073741823)) << 30))) {
                    atomicReferenceArray.set(i5 & i6, obj);
                    m mVarC = this;
                    while ((atomicLongFieldUpdater.get(mVarC) & 1152921504606846976L) != 0) {
                        mVarC = mVarC.c();
                        AtomicReferenceArray atomicReferenceArray2 = mVarC.f2644d;
                        int i7 = mVarC.f2643c & i5;
                        Object obj2 = atomicReferenceArray2.get(i7);
                        if ((obj2 instanceof l) && ((l) obj2).f2637a == i5) {
                            atomicReferenceArray2.set(i7, obj);
                        } else {
                            mVarC = null;
                        }
                        if (mVarC == null) {
                            return 0;
                        }
                    }
                    return 0;
                }
            } else {
                int i8 = this.f2641a;
                if (i8 < 1024 || ((i5 - i4) & 1073741823) > (i8 >> 1)) {
                    return 1;
                }
            }
        }
    }

    public final boolean b() {
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j4;
        do {
            atomicLongFieldUpdater = f2639f;
            j4 = atomicLongFieldUpdater.get(this);
            if ((j4 & 2305843009213693952L) != 0) {
                return true;
            }
            if ((1152921504606846976L & j4) != 0) {
                return false;
            }
        } while (!atomicLongFieldUpdater.compareAndSet(this, j4, 2305843009213693952L | j4));
        return true;
    }

    public final m c() {
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j4;
        m mVar;
        while (true) {
            atomicLongFieldUpdater = f2639f;
            j4 = atomicLongFieldUpdater.get(this);
            if ((j4 & 1152921504606846976L) != 0) {
                mVar = this;
                break;
            }
            long j5 = 1152921504606846976L | j4;
            mVar = this;
            if (atomicLongFieldUpdater.compareAndSet(mVar, j4, j5)) {
                j4 = j5;
                break;
            }
        }
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2638e;
            m mVar2 = (m) atomicReferenceFieldUpdater.get(this);
            if (mVar2 != null) {
                return mVar2;
            }
            m mVar3 = new m(mVar.f2641a * 2, mVar.f2642b);
            int i4 = (int) (1073741823 & j4);
            int i5 = (int) ((1152921503533105152L & j4) >> 30);
            while (true) {
                int i6 = mVar.f2643c;
                int i7 = i4 & i6;
                if (i7 == (i6 & i5)) {
                    break;
                }
                Object lVar = mVar.f2644d.get(i7);
                if (lVar == null) {
                    lVar = new l(i4);
                }
                mVar3.f2644d.set(mVar3.f2643c & i4, lVar);
                i4++;
            }
            atomicLongFieldUpdater.set(mVar3, (-1152921504606846977L) & j4);
            while (!atomicReferenceFieldUpdater.compareAndSet(this, null, mVar3) && atomicReferenceFieldUpdater.get(this) == null) {
            }
        }
    }

    public final Object d() {
        m mVarC = this;
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f2639f;
            long j4 = atomicLongFieldUpdater.get(mVarC);
            if ((j4 & 1152921504606846976L) != 0) {
                return f2640g;
            }
            int i4 = (int) (j4 & 1073741823);
            int i5 = mVarC.f2643c;
            int i6 = i4 & i5;
            if ((((int) ((1152921503533105152L & j4) >> 30)) & i5) != i6) {
                AtomicReferenceArray atomicReferenceArray = mVarC.f2644d;
                Object obj = atomicReferenceArray.get(i6);
                boolean z3 = mVarC.f2642b;
                if (obj == null) {
                    if (z3) {
                    }
                } else if (!(obj instanceof l)) {
                    long j5 = (i4 + 1) & 1073741823;
                    if (f2639f.compareAndSet(mVarC, j4, (j4 & (-1073741824)) | j5)) {
                        atomicReferenceArray.set(i6, null);
                        return obj;
                    }
                    mVarC = this;
                    if (z3) {
                        while (true) {
                            long j6 = atomicLongFieldUpdater.get(mVarC);
                            int i7 = (int) (j6 & 1073741823);
                            if ((j6 & 1152921504606846976L) != 0) {
                                mVarC = mVarC.c();
                            } else {
                                m mVar = mVarC;
                                if (f2639f.compareAndSet(mVar, j6, (j6 & (-1073741824)) | j5)) {
                                    mVar.f2644d.set(i7 & mVar.f2643c, null);
                                    mVarC = null;
                                } else {
                                    mVarC = mVar;
                                }
                            }
                            if (mVarC == null) {
                                return obj;
                            }
                        }
                    }
                }
            }
            return null;
        }
    }
}
