package r2;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class n0 extends o0 implements a0 {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2002j = AtomicReferenceFieldUpdater.newUpdater(n0.class, Object.class, "_queue$volatile");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2003k = AtomicReferenceFieldUpdater.newUpdater(n0.class, Object.class, "_delayed$volatile");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f2004l = AtomicIntegerFieldUpdater.newUpdater(n0.class, "_isCompleted$volatile");
    private volatile /* synthetic */ Object _delayed$volatile;
    private volatile /* synthetic */ int _isCompleted$volatile = 0;
    private volatile /* synthetic */ Object _queue$volatile;

    @Override // r2.a0
    public g0 A(long j4, n1 n1Var, y1.h hVar) {
        return z.f2053a.A(j4, n1Var, hVar);
    }

    @Override // r2.a0
    public final void J(long j4, i iVar) {
        long j5 = 0;
        if (j4 > 0) {
            j5 = j4 >= 9223372036854L ? Long.MAX_VALUE : 1000000 * j4;
        }
        if (j5 < 4611686018427387903L) {
            long jNanoTime = System.nanoTime();
            j0 j0Var = new j0(this, j5 + jNanoTime, iVar);
            h0(jNanoTime, j0Var);
            iVar.y(new f(2, j0Var));
        }
    }

    @Override // r2.s
    public final void S(y1.h hVar, Runnable runnable) {
        d0(runnable);
    }

    @Override // r2.o0
    public final long a0() {
        Runnable runnable;
        l0 l0Var;
        a3.h hVar = x.f2041c;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2002j;
        if (!b0()) {
            e0();
            loop0: while (true) {
                Object obj = atomicReferenceFieldUpdater.get(this);
                if (obj != null) {
                    if (obj instanceof w2.m) {
                        w2.m mVar = (w2.m) obj;
                        Object objD = mVar.d();
                        if (objD != w2.m.f2640g) {
                            runnable = (Runnable) objD;
                            break;
                        }
                        w2.m mVarC = mVar.c();
                        while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, mVarC) && atomicReferenceFieldUpdater.get(this) == obj) {
                        }
                    } else if (obj != hVar) {
                        do {
                            if (atomicReferenceFieldUpdater.compareAndSet(this, obj, null)) {
                                runnable = (Runnable) obj;
                                break loop0;
                            }
                        } while (atomicReferenceFieldUpdater.get(this) == obj);
                    }
                }
                runnable = null;
                break;
            }
            if (runnable != null) {
                runnable.run();
                return 0L;
            }
            v1.h hVar2 = this.f2009h;
            if (((hVar2 == null || hVar2.isEmpty()) ? Long.MAX_VALUE : 0L) != 0) {
                Object obj2 = atomicReferenceFieldUpdater.get(this);
                if (obj2 != null) {
                    if (obj2 instanceof w2.m) {
                        long j4 = w2.m.f2639f.get((w2.m) obj2);
                        if (((int) (1073741823 & j4)) != ((int) ((j4 & 1152921503533105152L) >> 30))) {
                            return 0L;
                        }
                    } else if (obj2 == hVar) {
                        return Long.MAX_VALUE;
                    }
                }
                m0 m0Var = (m0) f2003k.get(this);
                if (m0Var != null) {
                    synchronized (m0Var) {
                        l0[] l0VarArr = m0Var.f2657a;
                        l0Var = l0VarArr != null ? l0VarArr[0] : null;
                    }
                    if (l0Var != null) {
                        long jNanoTime = l0Var.f1996d - System.nanoTime();
                        if (jNanoTime >= 0) {
                            return jNanoTime;
                        }
                    }
                }
                return Long.MAX_VALUE;
            }
        }
        return 0L;
    }

    public void d0(Runnable runnable) {
        e0();
        if (!f0(runnable)) {
            y.f2050m.d0(runnable);
            return;
        }
        Thread threadY = Y();
        if (Thread.currentThread() != threadY) {
            LockSupport.unpark(threadY);
        }
    }

    public final void e0() {
        l0 l0VarC;
        m0 m0Var = (m0) f2003k.get(this);
        if (m0Var == null || w2.w.f2656b.get(m0Var) == 0) {
            return;
        }
        long jNanoTime = System.nanoTime();
        do {
            synchronized (m0Var) {
                try {
                    l0[] l0VarArr = m0Var.f2657a;
                    l0VarC = null;
                    l0 l0Var = l0VarArr != null ? l0VarArr[0] : null;
                    if (l0Var != null) {
                        l0VarC = ((jNanoTime - l0Var.f1996d) > 0L ? 1 : ((jNanoTime - l0Var.f1996d) == 0L ? 0 : -1)) >= 0 ? f0(l0Var) : false ? m0Var.c(0) : null;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } while (l0VarC != null);
    }

    public final boolean f0(Runnable runnable) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2002j;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (f2004l.get(this) != 0) {
                return false;
            }
            if (obj == null) {
                while (!atomicReferenceFieldUpdater.compareAndSet(this, null, runnable)) {
                    if (atomicReferenceFieldUpdater.get(this) != null) {
                    }
                }
                return true;
            }
            if (!(obj instanceof w2.m)) {
                if (obj == x.f2041c) {
                    return false;
                }
                w2.m mVar = new w2.m(8, true);
                mVar.a((Runnable) obj);
                mVar.a(runnable);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, mVar)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                    }
                }
                return true;
            }
            w2.m mVar2 = (w2.m) obj;
            int iA = mVar2.a(runnable);
            if (iA == 0) {
                return true;
            }
            if (iA == 1) {
                w2.m mVarC = mVar2.c();
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, mVarC) && atomicReferenceFieldUpdater.get(this) == obj) {
                }
            } else if (iA == 2) {
                return false;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0027  */
    /* JADX WARN: Code duplicated, block: B:20:0x0030  */
    /* JADX WARN: Code duplicated, block: B:22:0x0034  */
    /* JADX WARN: Code duplicated, block: B:24:0x004d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:25:0x004e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:26:0x004f  */
    public final boolean g0() {
        Object obj;
        long j4;
        v1.h hVar = this.f2009h;
        if (hVar != null ? hVar.isEmpty() : true) {
            m0 m0Var = (m0) f2003k.get(this);
            if (m0Var == null) {
                obj = f2002j.get(this);
                if (obj != null) {
                    if (obj instanceof w2.m) {
                        j4 = w2.m.f2639f.get((w2.m) obj);
                        if (((int) (1073741823 & j4)) == ((int) ((j4 & 1152921503533105152L) >> 30))) {
                            return true;
                        }
                        return false;
                    }
                    if (obj == x.f2041c) {
                    }
                }
                return true;
            }
            if (w2.w.f2656b.get(m0Var) == 0) {
                obj = f2002j.get(this);
                if (obj != null) {
                    if (obj instanceof w2.m) {
                        j4 = w2.m.f2639f.get((w2.m) obj);
                        if (((int) (1073741823 & j4)) == ((int) ((j4 & 1152921503533105152L) >> 30))) {
                            return true;
                        }
                        return false;
                    }
                    if (obj == x.f2041c) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final void h0(long j4, l0 l0Var) {
        int iD;
        Thread threadY;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2003k;
        l0 l0Var2 = null;
        if (f2004l.get(this) != 0) {
            iD = 1;
        } else {
            m0 m0Var = (m0) atomicReferenceFieldUpdater.get(this);
            if (m0Var == null) {
                m0 m0Var2 = new m0();
                m0Var2.f2000c = j4;
                while (!atomicReferenceFieldUpdater.compareAndSet(this, null, m0Var2) && atomicReferenceFieldUpdater.get(this) == null) {
                }
                Object obj = atomicReferenceFieldUpdater.get(this);
                j2.i.b(obj);
                m0Var = (m0) obj;
            }
            iD = l0Var.d(j4, m0Var, this);
        }
        if (iD != 0) {
            if (iD == 1) {
                c0(j4, l0Var);
                return;
            } else {
                if (iD != 2) {
                    throw new IllegalStateException("unexpected result");
                }
                return;
            }
        }
        m0 m0Var3 = (m0) atomicReferenceFieldUpdater.get(this);
        if (m0Var3 != null) {
            synchronized (m0Var3) {
                l0[] l0VarArr = m0Var3.f2657a;
                l0Var2 = l0VarArr != null ? l0VarArr[0] : null;
            }
        }
        if (l0Var2 != l0Var || Thread.currentThread() == (threadY = Y())) {
            return;
        }
        LockSupport.unpark(threadY);
    }

    @Override // r2.o0
    public void shutdown() {
        l0 l0VarC;
        l1.f1998a.set(null);
        f2004l.set(this, 1);
        a3.h hVar = x.f2041c;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2002j;
        loop0: while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj == null) {
                do {
                    if (atomicReferenceFieldUpdater.compareAndSet(this, null, hVar)) {
                        break loop0;
                    }
                } while (atomicReferenceFieldUpdater.get(this) == null);
            } else if (obj instanceof w2.m) {
                ((w2.m) obj).b();
                break;
            } else {
                if (obj == hVar) {
                    break;
                }
                w2.m mVar = new w2.m(8, true);
                mVar.a((Runnable) obj);
                do {
                    if (atomicReferenceFieldUpdater.compareAndSet(this, obj, mVar)) {
                        break loop0;
                    }
                } while (atomicReferenceFieldUpdater.get(this) == obj);
            }
        }
        while (a0() <= 0) {
        }
        long jNanoTime = System.nanoTime();
        while (true) {
            m0 m0Var = (m0) f2003k.get(this);
            if (m0Var == null) {
                return;
            }
            synchronized (m0Var) {
                l0VarC = w2.w.f2656b.get(m0Var) > 0 ? m0Var.c(0) : null;
            }
            if (l0VarC == null) {
                return;
            } else {
                c0(jNanoTime, l0VarC);
            }
        }
    }
}
