package w2;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2636a = AtomicReferenceFieldUpdater.newUpdater(k.class, Object.class, "_cur$volatile");
    private volatile /* synthetic */ Object _cur$volatile = new m(8, false);

    public final boolean a(Runnable runnable) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2636a;
            m mVar = (m) atomicReferenceFieldUpdater.get(this);
            int iA = mVar.a(runnable);
            if (iA == 0) {
                return true;
            }
            if (iA == 1) {
                m mVarC = mVar.c();
                while (!atomicReferenceFieldUpdater.compareAndSet(this, mVar, mVarC) && atomicReferenceFieldUpdater.get(this) == mVar) {
                }
            } else if (iA == 2) {
                return false;
            }
        }
    }

    public final void b() {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2636a;
            m mVar = (m) atomicReferenceFieldUpdater.get(this);
            if (mVar.b()) {
                return;
            }
            m mVarC = mVar.c();
            while (!atomicReferenceFieldUpdater.compareAndSet(this, mVar, mVarC) && atomicReferenceFieldUpdater.get(this) == mVar) {
            }
        }
    }

    public final int c() {
        m mVar = (m) f2636a.get(this);
        mVar.getClass();
        long j4 = m.f2639f.get(mVar);
        return (((int) ((j4 & 1152921503533105152L) >> 30)) - ((int) (1073741823 & j4))) & 1073741823;
    }

    public final Object d() {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2636a;
            m mVar = (m) atomicReferenceFieldUpdater.get(this);
            Object objD = mVar.d();
            if (objD != m.f2640g) {
                return objD;
            }
            m mVarC = mVar.c();
            while (!atomicReferenceFieldUpdater.compareAndSet(this, mVar, mVarC) && atomicReferenceFieldUpdater.get(this) == mVar) {
            }
        }
    }
}
