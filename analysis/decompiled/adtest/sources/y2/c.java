package y2;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import r2.x;
import w2.p;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c implements Executor, Closeable {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f2742k = AtomicLongFieldUpdater.newUpdater(c.class, "parkedWorkersStack$volatile");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f2743l = AtomicLongFieldUpdater.newUpdater(c.class, "controlState$volatile");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f2744m = AtomicIntegerFieldUpdater.newUpdater(c.class, "_isTerminated$volatile");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final a3.h f2745n = new a3.h(10, "NOT_IN_STACK");
    private volatile /* synthetic */ int _isTerminated$volatile;
    private volatile /* synthetic */ long controlState$volatile;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2746d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2747e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f2748f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f2749g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final f f2750h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final f f2751i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final p f2752j;
    private volatile /* synthetic */ long parkedWorkersStack$volatile;

    public c(int i4, int i5, long j4, String str) {
        this.f2746d = i4;
        this.f2747e = i5;
        this.f2748f = j4;
        this.f2749g = str;
        if (i4 < 1) {
            throw new IllegalArgumentException(a1.c.d(i4, "Core pool size ", " should be at least 1").toString());
        }
        if (i5 < i4) {
            throw new IllegalArgumentException(a1.c.b(i5, i4, "Max pool size ", " should be greater than or equals to core pool size ").toString());
        }
        if (i5 > 2097150) {
            throw new IllegalArgumentException(a1.c.d(i5, "Max pool size ", " should not exceed maximal supported number of threads 2097150").toString());
        }
        if (j4 <= 0) {
            throw new IllegalArgumentException(("Idle worker keep alive time " + j4 + " must be positive").toString());
        }
        this.f2750h = new f();
        this.f2751i = new f();
        this.f2752j = new p((i4 + 1) * 2);
        this.controlState$volatile = ((long) i4) << 42;
        this._isTerminated$volatile = 0;
    }

    public static /* synthetic */ void k(c cVar, Runnable runnable, int i4) {
        cVar.c(runnable, false, (i4 & 4) == 0);
    }

    public final boolean A(long j4) {
        int i4 = ((int) (2097151 & j4)) - ((int) ((j4 & 4398044413952L) >> 21));
        if (i4 < 0) {
            i4 = 0;
        }
        int i5 = this.f2746d;
        if (i4 < i5) {
            int iB = b();
            if (iB == 1 && i5 > 1) {
                b();
            }
            if (iB > 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean C() {
        a3.h hVar;
        int iB;
        while (true) {
            long j4 = f2742k.get(this);
            a aVar = (a) this.f2752j.b((int) (2097151 & j4));
            if (aVar == null) {
                aVar = null;
            } else {
                long j5 = (2097152 + j4) & (-2097152);
                Object objC = aVar.c();
                while (true) {
                    hVar = f2745n;
                    if (objC == hVar) {
                        iB = -1;
                        break;
                    }
                    if (objC == null) {
                        iB = 0;
                        break;
                    }
                    a aVar2 = (a) objC;
                    iB = aVar2.b();
                    if (iB != 0) {
                        break;
                    }
                    objC = aVar2.c();
                }
                if (iB >= 0) {
                    if (f2742k.compareAndSet(this, j4, ((long) iB) | j5)) {
                        aVar.g(hVar);
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
            if (aVar == null) {
                return false;
            }
            if (a.f2727l.compareAndSet(aVar, -1, 0)) {
                LockSupport.unpark(aVar);
                return true;
            }
        }
    }

    public final int b() {
        synchronized (this.f2752j) {
            try {
                if (f2744m.get(this) != 0) {
                    return -1;
                }
                AtomicLongFieldUpdater atomicLongFieldUpdater = f2743l;
                long j4 = atomicLongFieldUpdater.get(this);
                int i4 = (int) (j4 & 2097151);
                int i5 = i4 - ((int) ((j4 & 4398044413952L) >> 21));
                if (i5 < 0) {
                    i5 = 0;
                }
                if (i5 >= this.f2746d) {
                    return 0;
                }
                if (i4 >= this.f2747e) {
                    return 0;
                }
                int i6 = ((int) (atomicLongFieldUpdater.get(this) & 2097151)) + 1;
                if (i6 <= 0 || this.f2752j.b(i6) != null) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                a aVar = new a(this, i6);
                this.f2752j.c(i6, aVar);
                if (i6 != ((int) (2097151 & atomicLongFieldUpdater.incrementAndGet(this)))) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                int i7 = i5 + 1;
                aVar.start();
                return i7;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void c(Runnable runnable, boolean z3, boolean z4) {
        i jVar;
        b bVar;
        k.f2766f.getClass();
        long jNanoTime = System.nanoTime();
        if (runnable instanceof i) {
            jVar = (i) runnable;
            jVar.f2758d = jNanoTime;
            jVar.f2759e = z3;
        } else {
            jVar = new j(runnable, jNanoTime, z3);
        }
        boolean z5 = jVar.f2759e;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f2743l;
        long jAddAndGet = z5 ? atomicLongFieldUpdater.addAndGet(this, 2097152L) : 0L;
        Thread threadCurrentThread = Thread.currentThread();
        a aVar = threadCurrentThread instanceof a ? (a) threadCurrentThread : null;
        if (aVar == null || !j2.i.a(aVar.f2735k, this)) {
            aVar = null;
        }
        if (aVar != null && (bVar = aVar.f2730f) != b.f2740h && (jVar.f2759e || bVar != b.f2737e)) {
            aVar.f2734j = true;
            m mVar = aVar.f2728d;
            if (z4) {
                jVar = mVar.a(jVar);
            } else {
                mVar.getClass();
                i iVar = (i) m.f2768b.getAndSet(mVar, jVar);
                jVar = iVar == null ? null : mVar.a(iVar);
            }
        }
        if (jVar != null) {
            if (!(jVar.f2759e ? this.f2751i.a(jVar) : this.f2750h.a(jVar))) {
                throw new RejectedExecutionException(this.f2749g + " was terminated");
            }
        }
        boolean z6 = z4 && aVar != null;
        if (z5) {
            if (z6 || C() || A(jAddAndGet)) {
                return;
            }
            C();
            return;
        }
        if (z6 || C() || A(atomicLongFieldUpdater.get(this))) {
            return;
        }
        C();
    }

    /* JADX WARN: Code duplicated, block: B:39:0x008a  */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws InterruptedException {
        int i4;
        i iVarA;
        if (f2744m.compareAndSet(this, 0, 1)) {
            Thread threadCurrentThread = Thread.currentThread();
            a aVar = threadCurrentThread instanceof a ? (a) threadCurrentThread : null;
            if (aVar == null || !j2.i.a(aVar.f2735k, this)) {
                aVar = null;
            }
            synchronized (this.f2752j) {
                i4 = (int) (f2743l.get(this) & 2097151);
            }
            if (1 <= i4) {
                int i5 = 1;
                while (true) {
                    Object objB = this.f2752j.b(i5);
                    j2.i.b(objB);
                    a aVar2 = (a) objB;
                    if (aVar2 != aVar) {
                        while (aVar2.getState() != Thread.State.TERMINATED) {
                            LockSupport.unpark(aVar2);
                            aVar2.join(10000L);
                        }
                        m mVar = aVar2.f2728d;
                        f fVar = this.f2751i;
                        mVar.getClass();
                        i iVar = (i) m.f2768b.getAndSet(mVar, null);
                        if (iVar != null) {
                            fVar.a(iVar);
                        }
                        while (true) {
                            i iVarB = mVar.b();
                            if (iVarB == null) {
                                break;
                            } else {
                                fVar.a(iVarB);
                            }
                        }
                    }
                    if (i5 == i4) {
                        break;
                    } else {
                        i5++;
                    }
                }
            }
            this.f2751i.b();
            this.f2750h.b();
            while (true) {
                if (aVar != null) {
                    iVarA = aVar.a(true);
                    if (iVarA == null) {
                        iVarA = (i) this.f2750h.d();
                        if (iVarA == null) {
                            break;
                            break;
                        }
                    }
                } else {
                    iVarA = (i) this.f2750h.d();
                    if (iVarA == null && (iVarA = (i) this.f2751i.d()) == null) {
                        break;
                    }
                }
                try {
                    iVarA.run();
                } catch (Throwable th) {
                    Thread threadCurrentThread2 = Thread.currentThread();
                    threadCurrentThread2.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread2, th);
                }
            }
            if (aVar != null) {
                aVar.h(b.f2740h);
            }
            f2742k.set(this, 0L);
            f2743l.set(this, 0L);
        }
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        k(this, runnable, 6);
    }

    public final void l(a aVar, int i4, int i5) {
        while (true) {
            long j4 = f2742k.get(this);
            int i6 = (int) (2097151 & j4);
            long j5 = (2097152 + j4) & (-2097152);
            if (i6 == i4) {
                if (i5 == 0) {
                    Object objC = aVar.c();
                    while (true) {
                        if (objC == f2745n) {
                            i6 = -1;
                            break;
                        }
                        if (objC == null) {
                            i6 = 0;
                            break;
                        }
                        a aVar2 = (a) objC;
                        int iB = aVar2.b();
                        if (iB != 0) {
                            i6 = iB;
                            break;
                        }
                        objC = aVar2.c();
                    }
                } else {
                    i6 = i5;
                }
            }
            if (i6 >= 0) {
                if (f2742k.compareAndSet(this, j4, ((long) i6) | j5)) {
                    return;
                }
            }
        }
    }

    public final String toString() {
        ArrayList arrayList = new ArrayList();
        p pVar = this.f2752j;
        int iA = pVar.a();
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 1; i9 < iA; i9++) {
            a aVar = (a) pVar.b(i9);
            if (aVar != null) {
                m mVar = aVar.f2728d;
                mVar.getClass();
                int i10 = m.f2768b.get(mVar) != null ? (m.f2769c.get(mVar) - m.f2770d.get(mVar)) + 1 : m.f2769c.get(mVar) - m.f2770d.get(mVar);
                int iOrdinal = aVar.f2730f.ordinal();
                if (iOrdinal == 0) {
                    i4++;
                    StringBuilder sb = new StringBuilder();
                    sb.append(i10);
                    sb.append('c');
                    arrayList.add(sb.toString());
                } else if (iOrdinal == 1) {
                    i5++;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(i10);
                    sb2.append('b');
                    arrayList.add(sb2.toString());
                } else if (iOrdinal == 2) {
                    i6++;
                } else if (iOrdinal == 3) {
                    i7++;
                    if (i10 > 0) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(i10);
                        sb3.append('d');
                        arrayList.add(sb3.toString());
                    }
                } else {
                    if (iOrdinal != 4) {
                        throw new a0.c();
                    }
                    i8++;
                }
            }
        }
        long j4 = f2743l.get(this);
        StringBuilder sb4 = new StringBuilder();
        sb4.append(this.f2749g);
        sb4.append('@');
        sb4.append(x.k(this));
        sb4.append("[Pool Size {core = ");
        int i11 = this.f2746d;
        sb4.append(i11);
        sb4.append(", max = ");
        sb4.append(this.f2747e);
        sb4.append("}, Worker States {CPU = ");
        sb4.append(i4);
        sb4.append(", blocking = ");
        sb4.append(i5);
        sb4.append(", parked = ");
        sb4.append(i6);
        sb4.append(", dormant = ");
        sb4.append(i7);
        sb4.append(", terminated = ");
        sb4.append(i8);
        sb4.append("}, running workers queues = ");
        sb4.append(arrayList);
        sb4.append(", global CPU queue size = ");
        sb4.append(this.f2750h.c());
        sb4.append(", global blocking queue size = ");
        sb4.append(this.f2751i.c());
        sb4.append(", Control State {created workers= ");
        sb4.append((int) (2097151 & j4));
        sb4.append(", blocking tasks = ");
        sb4.append((int) ((4398044413952L & j4) >> 21));
        sb4.append(", CPUs acquired = ");
        sb4.append(i11 - ((int) ((j4 & 9223367638808264704L) >> 42)));
        sb4.append("}]");
        return sb4.toString();
    }
}
