package w2;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import r2.a0;
import r2.g0;
import r2.n1;
import r2.z;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class g extends r2.s implements a0 {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f2626k = AtomicIntegerFieldUpdater.newUpdater(g.class, "runningWorkers$volatile");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ a0 f2627f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final r2.s f2628g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f2629h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final k f2630i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Object f2631j;
    private volatile /* synthetic */ int runningWorkers$volatile;

    /* JADX WARN: Multi-variable type inference failed */
    public g(r2.s sVar, int i4) {
        a0 a0Var = sVar instanceof a0 ? (a0) sVar : null;
        this.f2627f = a0Var == null ? z.f2053a : a0Var;
        this.f2628g = sVar;
        this.f2629h = i4;
        this.f2630i = new k();
        this.f2631j = new Object();
    }

    @Override // r2.a0
    public final g0 A(long j4, n1 n1Var, y1.h hVar) {
        return this.f2627f.A(j4, n1Var, hVar);
    }

    @Override // r2.a0
    public final void J(long j4, r2.i iVar) {
        this.f2627f.J(j4, iVar);
    }

    @Override // r2.s
    public final void S(y1.h hVar, Runnable runnable) {
        Runnable runnableW;
        this.f2630i.a(runnable);
        if (f2626k.get(this) >= this.f2629h || !X() || (runnableW = W()) == null) {
            return;
        }
        this.f2628g.S(this, new f0.a(11, this, runnableW));
    }

    @Override // r2.s
    public final void T(y1.h hVar, Runnable runnable) {
        Runnable runnableW;
        this.f2630i.a(runnable);
        if (f2626k.get(this) >= this.f2629h || !X() || (runnableW = W()) == null) {
            return;
        }
        this.f2628g.T(this, new f0.a(11, this, runnableW));
    }

    public final Runnable W() {
        while (true) {
            Runnable runnable = (Runnable) this.f2630i.d();
            if (runnable != null) {
                return runnable;
            }
            synchronized (this.f2631j) {
                AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f2626k;
                atomicIntegerFieldUpdater.decrementAndGet(this);
                if (this.f2630i.c() == 0) {
                    return null;
                }
                atomicIntegerFieldUpdater.incrementAndGet(this);
            }
        }
    }

    public final boolean X() {
        synchronized (this.f2631j) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f2626k;
            if (atomicIntegerFieldUpdater.get(this) >= this.f2629h) {
                return false;
            }
            atomicIntegerFieldUpdater.incrementAndGet(this);
            return true;
        }
    }

    @Override // r2.s
    public final String toString() {
        return this.f2628g + ".limitedParallelism(" + this.f2629h + ')';
    }
}
