package y2;

import j2.n;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a extends Thread {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f2727l = AtomicIntegerFieldUpdater.newUpdater(a.class, "workerCtl$volatile");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final m f2728d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final n f2729e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b f2730f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f2731g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f2732h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2733i;
    private volatile int indexInArray;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f2734j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ c f2735k;
    private volatile Object nextParkedWorker;
    private volatile /* synthetic */ int workerCtl$volatile;

    public a(c cVar, int i4) {
        this.f2735k = cVar;
        setDaemon(true);
        setContextClassLoader(c.class.getClassLoader());
        this.f2728d = new m();
        this.f2729e = new n();
        this.f2730f = b.f2739g;
        this.nextParkedWorker = c.f2745n;
        int iNanoTime = (int) System.nanoTime();
        this.f2733i = iNanoTime == 0 ? 42 : iNanoTime;
        f(i4);
    }

    public final i a(boolean z3) {
        i iVarE;
        i iVarE2;
        long j4;
        b bVar = this.f2730f;
        c cVar = this.f2735k;
        i iVar = null;
        m mVar = this.f2728d;
        b bVar2 = b.f2736d;
        if (bVar != bVar2) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = c.f2743l;
            do {
                j4 = atomicLongFieldUpdater.get(cVar);
                if (((int) ((9223367638808264704L & j4) >> 42)) == 0) {
                    mVar.getClass();
                    loop1: while (true) {
                        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = m.f2768b;
                        i iVar2 = (i) atomicReferenceFieldUpdater.get(mVar);
                        if (iVar2 == null || !iVar2.f2759e) {
                            int i4 = m.f2770d.get(mVar);
                            int i5 = m.f2769c.get(mVar);
                            while (i4 != i5 && m.f2771e.get(mVar) != 0) {
                                i5--;
                                i iVarC = mVar.c(i5, true);
                                if (iVarC != null) {
                                    iVar = iVarC;
                                    break;
                                }
                            }
                            break;
                        }
                        do {
                            if (atomicReferenceFieldUpdater.compareAndSet(mVar, iVar2, null)) {
                                iVar = iVar2;
                                break loop1;
                            }
                        } while (atomicReferenceFieldUpdater.get(mVar) == iVar2);
                    }
                    if (iVar != null) {
                        return iVar;
                    }
                    i iVar3 = (i) cVar.f2751i.d();
                    return iVar3 == null ? i(1) : iVar3;
                }
            } while (!c.f2743l.compareAndSet(cVar, j4, j4 - 4398046511104L));
            this.f2730f = bVar2;
        }
        if (z3) {
            boolean z4 = d(cVar.f2746d * 2) == 0;
            if (z4 && (iVarE2 = e()) != null) {
                return iVarE2;
            }
            mVar.getClass();
            i iVarB = (i) m.f2768b.getAndSet(mVar, null);
            if (iVarB == null) {
                iVarB = mVar.b();
            }
            if (iVarB != null) {
                return iVarB;
            }
            if (!z4 && (iVarE = e()) != null) {
                return iVarE;
            }
        } else {
            i iVarE3 = e();
            if (iVarE3 != null) {
                return iVarE3;
            }
        }
        return i(3);
    }

    public final int b() {
        return this.indexInArray;
    }

    public final Object c() {
        return this.nextParkedWorker;
    }

    public final int d(int i4) {
        int i5 = this.f2733i;
        int i6 = i5 ^ (i5 << 13);
        int i7 = i6 ^ (i6 >> 17);
        int i8 = i7 ^ (i7 << 5);
        this.f2733i = i8;
        int i9 = i4 - 1;
        return (i9 & i4) == 0 ? i8 & i9 : (i8 & Integer.MAX_VALUE) % i4;
    }

    public final i e() {
        int iD = d(2);
        c cVar = this.f2735k;
        if (iD == 0) {
            i iVar = (i) cVar.f2750h.d();
            return iVar != null ? iVar : (i) cVar.f2751i.d();
        }
        i iVar2 = (i) cVar.f2751i.d();
        return iVar2 != null ? iVar2 : (i) cVar.f2750h.d();
    }

    public final void f(int i4) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f2735k.f2749g);
        sb.append("-worker-");
        sb.append(i4 == 0 ? "TERMINATED" : String.valueOf(i4));
        setName(sb.toString());
        this.indexInArray = i4;
    }

    public final void g(Object obj) {
        this.nextParkedWorker = obj;
    }

    public final boolean h(b bVar) {
        b bVar2 = this.f2730f;
        boolean z3 = bVar2 == b.f2736d;
        if (z3) {
            c.f2743l.addAndGet(this.f2735k, 4398046511104L);
        }
        if (bVar2 != bVar) {
            this.f2730f = bVar;
        }
        return z3;
    }

    public final i i(int i4) {
        long j4;
        i iVarC;
        long j5;
        long j6;
        i iVar;
        AtomicLongFieldUpdater atomicLongFieldUpdater = c.f2743l;
        c cVar = this.f2735k;
        int i5 = (int) (atomicLongFieldUpdater.get(cVar) & 2097151);
        i iVar2 = null;
        if (i5 < 2) {
            return null;
        }
        int iD = d(i5);
        int i6 = 0;
        long jMin = Long.MAX_VALUE;
        while (i6 < i5) {
            iD++;
            if (iD > i5) {
                iD = 1;
            }
            a aVar = (a) cVar.f2752j.b(iD);
            if (aVar != null && aVar != this) {
                m mVar = aVar.f2728d;
                if (i4 != 3) {
                    mVar.getClass();
                    int i7 = m.f2770d.get(mVar);
                    int i8 = m.f2769c.get(mVar);
                    boolean z3 = i4 == 1;
                    while (true) {
                        if (i7 != i8) {
                            j4 = 0;
                            if (!z3 || m.f2771e.get(mVar) != 0) {
                                int i9 = i7 + 1;
                                iVarC = mVar.c(i7, z3);
                                if (iVarC != null) {
                                    break;
                                }
                                i7 = i9;
                            }
                        } else {
                            j4 = 0;
                        }
                        iVarC = iVar2;
                        break;
                    }
                } else {
                    iVarC = mVar.b();
                    j4 = 0;
                }
                n nVar = this.f2729e;
                if (iVarC == null) {
                    while (true) {
                        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = m.f2768b;
                        i iVar3 = (i) atomicReferenceFieldUpdater.get(mVar);
                        if (iVar3 == null) {
                            j5 = -1;
                        } else {
                            j5 = -1;
                            if (((iVar3.f2759e ? 1 : 2) & i4) != 0) {
                                k.f2766f.getClass();
                                m mVar2 = mVar;
                                long jNanoTime = System.nanoTime() - iVar3.f2758d;
                                long j7 = k.f2762b;
                                if (jNanoTime < j7) {
                                    j6 = j7 - jNanoTime;
                                    iVar = null;
                                    break;
                                }
                                do {
                                    iVar = null;
                                    if (atomicReferenceFieldUpdater.compareAndSet(mVar2, iVar3, null)) {
                                        nVar.f1276d = iVar3;
                                        j6 = -1;
                                        break;
                                    }
                                } while (atomicReferenceFieldUpdater.get(mVar2) == iVar3);
                                mVar = mVar2;
                                iVar2 = null;
                            }
                        }
                        j6 = -2;
                        iVar = iVar2;
                        break;
                    }
                } else {
                    nVar.f1276d = iVarC;
                    iVar = iVar2;
                    j6 = -1;
                    j5 = -1;
                }
                if (j6 == j5) {
                    i iVar4 = (i) nVar.f1276d;
                    nVar.f1276d = iVar;
                    return iVar4;
                }
                if (j6 > j4) {
                    jMin = Math.min(jMin, j6);
                }
            }
            i6++;
            iVar2 = null;
        }
        if (jMin == Long.MAX_VALUE) {
            jMin = 0;
        }
        this.f2732h = jMin;
        return null;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        long j4;
        loop0: while (true) {
            boolean z3 = false;
            while (true) {
                if (c.f2744m.get(this.f2735k) == 0) {
                    b bVar = this.f2730f;
                    b bVar2 = b.f2740h;
                    if (bVar == bVar2) {
                        break loop0;
                    }
                    i iVarA = a(this.f2734j);
                    if (iVarA != null) {
                        this.f2732h = 0L;
                        c cVar = this.f2735k;
                        this.f2731g = 0L;
                        if (this.f2730f == b.f2738f) {
                            this.f2730f = b.f2737e;
                        }
                        if (!iVarA.f2759e) {
                            try {
                                iVarA.run();
                                break;
                            } catch (Throwable th) {
                                Thread threadCurrentThread = Thread.currentThread();
                                threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
                                break;
                            }
                        }
                        if (h(b.f2737e) && !cVar.C() && !cVar.A(c.f2743l.get(cVar))) {
                            cVar.C();
                        }
                        try {
                            iVarA.run();
                        } catch (Throwable th2) {
                            Thread threadCurrentThread2 = Thread.currentThread();
                            threadCurrentThread2.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread2, th2);
                        }
                        c.f2743l.addAndGet(cVar, -2097152L);
                        if (this.f2730f == bVar2) {
                            break;
                        }
                        this.f2730f = b.f2739g;
                        break;
                    }
                    this.f2734j = false;
                    if (this.f2732h == 0) {
                        Object obj = this.nextParkedWorker;
                        a3.h hVar = c.f2745n;
                        if (obj != hVar) {
                            f2727l.set(this, -1);
                            while (this.nextParkedWorker != c.f2745n) {
                                AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f2727l;
                                if (atomicIntegerFieldUpdater.get(this) != -1) {
                                    break;
                                }
                                c cVar2 = this.f2735k;
                                AtomicIntegerFieldUpdater atomicIntegerFieldUpdater2 = c.f2744m;
                                if (atomicIntegerFieldUpdater2.get(cVar2) != 0) {
                                    break;
                                }
                                b bVar3 = this.f2730f;
                                b bVar4 = b.f2740h;
                                if (bVar3 == bVar4) {
                                    break;
                                }
                                h(b.f2738f);
                                Thread.interrupted();
                                if (this.f2731g == 0) {
                                    j4 = 2097151;
                                    this.f2731g = System.nanoTime() + this.f2735k.f2748f;
                                } else {
                                    j4 = 2097151;
                                }
                                LockSupport.parkNanos(this.f2735k.f2748f);
                                if (System.nanoTime() - this.f2731g >= 0) {
                                    this.f2731g = 0L;
                                    c cVar3 = this.f2735k;
                                    synchronized (cVar3.f2752j) {
                                        try {
                                            if (!(atomicIntegerFieldUpdater2.get(cVar3) != 0)) {
                                                AtomicLongFieldUpdater atomicLongFieldUpdater = c.f2743l;
                                                if (((int) (atomicLongFieldUpdater.get(cVar3) & j4)) > cVar3.f2746d) {
                                                    if (atomicIntegerFieldUpdater.compareAndSet(this, -1, 1)) {
                                                        int i4 = this.indexInArray;
                                                        f(0);
                                                        cVar3.l(this, i4, 0);
                                                        int andDecrement = (int) (atomicLongFieldUpdater.getAndDecrement(cVar3) & j4);
                                                        if (andDecrement != i4) {
                                                            Object objB = cVar3.f2752j.b(andDecrement);
                                                            j2.i.b(objB);
                                                            a aVar = (a) objB;
                                                            cVar3.f2752j.c(i4, aVar);
                                                            aVar.f(i4);
                                                            cVar3.l(aVar, andDecrement, i4);
                                                        }
                                                        cVar3.f2752j.c(andDecrement, null);
                                                        this.f2730f = bVar4;
                                                    }
                                                }
                                            }
                                        } catch (Throwable th3) {
                                            throw th3;
                                        }
                                    }
                                }
                            }
                        } else {
                            c cVar4 = this.f2735k;
                            if (this.nextParkedWorker == hVar) {
                                AtomicLongFieldUpdater atomicLongFieldUpdater2 = c.f2742k;
                                while (true) {
                                    long j5 = atomicLongFieldUpdater2.get(cVar4);
                                    int i5 = this.indexInArray;
                                    this.nextParkedWorker = cVar4.f2752j.b((int) (j5 & 2097151));
                                    c cVar5 = cVar4;
                                    if (c.f2742k.compareAndSet(cVar5, j5, ((j5 + 2097152) & (-2097152)) | ((long) i5))) {
                                        break;
                                    } else {
                                        cVar4 = cVar5;
                                    }
                                }
                            }
                        }
                    } else {
                        if (z3) {
                            h(b.f2738f);
                            Thread.interrupted();
                            LockSupport.parkNanos(this.f2732h);
                            this.f2732h = 0L;
                            break;
                        }
                        z3 = true;
                    }
                } else {
                    break loop0;
                }
            }
        }
        h(b.f2740h);
    }
}
