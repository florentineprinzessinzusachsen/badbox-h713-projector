package m0;

import android.content.Context;
import androidx.work.impl.WorkDatabase_Impl;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import d0.a0;
import d0.l0;
import d0.z;
import e0.m0;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import p.b0;
import p.i0;
import r2.v;
import r2.x;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class n extends a2.i implements i2.p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f1421h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1422i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ Object f1423j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public /* synthetic */ Object f1424k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f1425l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f1426m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(ConstraintTrackingWorker constraintTrackingWorker, z zVar, a3.q qVar, l0.p pVar, y1.c cVar) {
        super(2, cVar);
        this.f1421h = 2;
        this.f1425l = constraintTrackingWorker;
        this.f1424k = zVar;
        this.f1426m = qVar;
        this.f1423j = pVar;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) throws Throwable {
        switch (this.f1421h) {
            case 0:
                return ((n) i((v) obj, (y1.c) obj2)).l(u1.k.f2301a);
            case 1:
                return ((n) i((v) obj, (y1.c) obj2)).l(u1.k.f2301a);
            case 2:
                return ((n) i((v) obj, (y1.c) obj2)).l(u1.k.f2301a);
            default:
                ((n) i((u2.h) obj, (y1.c) obj2)).l(u1.k.f2301a);
                return z1.a.f2781d;
        }
    }

    @Override // a2.a
    public final y1.c i(Object obj, y1.c cVar) {
        switch (this.f1421h) {
            case 0:
                return new n((z) this.f1424k, (l0.p) this.f1423j, (q) this.f1425l, (Context) this.f1426m, cVar, 0);
            case 1:
                return new n((a3.q) this.f1424k, (l0.p) this.f1423j, (AtomicInteger) this.f1425l, (r0.a) this.f1426m, cVar, 1);
            case 2:
                return new n((ConstraintTrackingWorker) this.f1425l, (z) this.f1424k, (a3.q) this.f1426m, (l0.p) this.f1423j, cVar);
            default:
                n nVar = new n((i0) this.f1423j, (int[]) this.f1425l, (String[]) this.f1426m, cVar);
                nVar.f1424k = obj;
                return nVar;
        }
    }

    /* JADX WARN: Code duplicated, block: B:35:0x00a8 A[Catch: all -> 0x0032, PHI: r0
      0x00a8: PHI (r0v43 u2.h) = (r0v40 u2.h), (r0v42 u2.h), (r0v47 u2.h) binds: [B:28:0x0086, B:33:0x00a5, B:15:0x0035] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0032, blocks: (B:11:0x0029, B:12:0x0031, B:35:0x00a8), top: B:93:0x0019 }] */
    @Override // a2.a
    public final Object l(Object obj) throws Throwable {
        Object objA;
        Object objA2;
        u2.h hVar;
        Object objE;
        j1.d dVar;
        switch (this.f1421h) {
            case 0:
                String str = ((l0.p) this.f1423j).f1333c;
                z zVar = (z) this.f1424k;
                int i4 = this.f1422i;
                z1.a aVar = z1.a.f2781d;
                if (i4 == 0) {
                    l0.M(obj);
                    g.l lVarA = zVar.a();
                    this.f1422i = 1;
                    objA = m0.a(lVarA, zVar, this);
                    if (objA != aVar) {
                    }
                    return aVar;
                }
                if (i4 != 1) {
                    if (i4 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                    return obj;
                }
                l0.M(obj);
                objA = obj;
                d0.o oVar = (d0.o) objA;
                if (oVar == null) {
                    throw new IllegalStateException("Worker was marked important (" + str + ") but did not provide ForegroundInfo");
                }
                a0.e().a(o.f1427a, "Updating notification for " + str);
                q qVar = (q) this.f1425l;
                Context context = (Context) this.f1426m;
                UUID uuid = zVar.f515b.f310a;
                j jVar = (j) qVar.f1432a.f184e;
                p pVar = new p(qVar, uuid, oVar, context);
                j2.i.e(jVar, "<this>");
                g.l lVarL = a.a.l(new d0.r(jVar, pVar, 0));
                this.f1422i = 2;
                Object objD = l0.d(lVarL, this);
                if (objD != aVar) {
                    return objD;
                }
                return aVar;
            case 1:
                int i5 = this.f1422i;
                if (i5 == 0) {
                    l0.M(obj);
                    a3.q qVar2 = (a3.q) this.f1424k;
                    l0.p pVar2 = (l0.p) this.f1423j;
                    this.f1422i = 1;
                    objA2 = o0.g.a(qVar2, pVar2, this);
                    z1.a aVar2 = z1.a.f2781d;
                    if (objA2 == aVar2) {
                        return aVar2;
                    }
                } else {
                    if (i5 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                    objA2 = obj;
                }
                ((AtomicInteger) this.f1425l).set(((Number) objA2).intValue());
                ((r0.a) this.f1426m).cancel(true);
                return u1.k.f2301a;
            case 2:
                int i6 = this.f1422i;
                if (i6 != 0) {
                    if (i6 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                    return obj;
                }
                l0.M(obj);
                ConstraintTrackingWorker constraintTrackingWorker = (ConstraintTrackingWorker) this.f1425l;
                z zVar2 = (z) this.f1424k;
                a3.q qVar3 = (a3.q) this.f1426m;
                l0.p pVar3 = (l0.p) this.f1423j;
                this.f1422i = 1;
                Object objD2 = ConstraintTrackingWorker.d(constraintTrackingWorker, zVar2, qVar3, pVar3, this);
                z1.a aVar3 = z1.a.f2781d;
                return objD2 == aVar3 ? aVar3 : objD2;
            default:
                int[] iArr = (int[]) this.f1425l;
                i0 i0Var = (i0) this.f1423j;
                int i7 = this.f1422i;
                y1.c cVar = null;
                z1.a aVar4 = z1.a.f2781d;
                try {
                    if (i7 != 0) {
                        if (i7 == 1) {
                            hVar = (u2.h) this.f1424k;
                            l0.M(obj);
                            objE = obj;
                            dVar = new j1.d(i0Var, cVar, 3);
                            this.f1424k = hVar;
                            this.f1422i = 2;
                            if (x.w((y1.h) objE, dVar, this) != aVar4) {
                            }
                        } else {
                            if (i7 != 2) {
                                if (i7 != 3) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                l0.M(obj);
                                throw new a0.c();
                            }
                            hVar = (u2.h) this.f1424k;
                            l0.M(obj);
                        }
                        j2.n nVar = new j2.n();
                        a3.h hVar2 = i0Var.f1672i;
                        b0 b0Var = new b0(nVar, hVar, (String[]) this.f1426m, iArr);
                        this.f1424k = null;
                        this.f1422i = 3;
                        hVar2.e(b0Var, this);
                    } else {
                        l0.M(obj);
                        hVar = (u2.h) this.f1424k;
                        e3.h hVar3 = i0Var.f1671h;
                        hVar3.getClass();
                        j2.i.e(iArr, "tableIds");
                        ReentrantLock reentrantLock = (ReentrantLock) hVar3.f750e;
                        reentrantLock.lock();
                        try {
                            boolean z3 = false;
                            for (int i8 : iArr) {
                                long[] jArr = (long[]) hVar3.f751f;
                                long j4 = jArr[i8];
                                jArr[i8] = j4 + 1;
                                if (j4 == 0) {
                                    z3 = true;
                                    hVar3.f749d = true;
                                }
                            }
                            reentrantLock.unlock();
                            if (z3) {
                                WorkDatabase_Impl workDatabase_Impl = i0Var.f1664a;
                                this.f1424k = hVar;
                                this.f1422i = 1;
                                objE = l3.h.E(workDatabase_Impl, this);
                                if (objE != aVar4) {
                                    dVar = new j1.d(i0Var, cVar, 3);
                                    this.f1424k = hVar;
                                    this.f1422i = 2;
                                    if (x.w((y1.h) objE, dVar, this) != aVar4) {
                                        j2.n nVar2 = new j2.n();
                                        a3.h hVar4 = i0Var.f1672i;
                                        b0 b0Var2 = new b0(nVar2, hVar, (String[]) this.f1426m, iArr);
                                        this.f1424k = null;
                                        this.f1422i = 3;
                                        hVar4.e(b0Var2, this);
                                    }
                                }
                            } else {
                                j2.n nVar3 = new j2.n();
                                a3.h hVar5 = i0Var.f1672i;
                                b0 b0Var3 = new b0(nVar3, hVar, (String[]) this.f1426m, iArr);
                                this.f1424k = null;
                                this.f1422i = 3;
                                hVar5.e(b0Var3, this);
                            }
                        } catch (Throwable th) {
                            reentrantLock.unlock();
                            throw th;
                        }
                    }
                    return aVar4;
                } catch (Throwable th2) {
                    e3.h hVar6 = i0Var.f1671h;
                    hVar6.getClass();
                    j2.i.e(iArr, "tableIds");
                    ReentrantLock reentrantLock2 = (ReentrantLock) hVar6.f750e;
                    reentrantLock2.lock();
                    try {
                        for (int i9 : iArr) {
                            long[] jArr2 = (long[]) hVar6.f751f;
                            long j5 = jArr2[i9];
                            jArr2[i9] = j5 - 1;
                            if (j5 == 1) {
                                hVar6.f749d = true;
                            }
                        }
                        reentrantLock2.unlock();
                        throw th2;
                    } catch (Throwable th3) {
                        reentrantLock2.unlock();
                        throw th3;
                    }
                }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(Object obj, l0.p pVar, Object obj2, Object obj3, y1.c cVar, int i4) {
        super(2, cVar);
        this.f1421h = i4;
        this.f1424k = obj;
        this.f1423j = pVar;
        this.f1425l = obj2;
        this.f1426m = obj3;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(i0 i0Var, int[] iArr, String[] strArr, y1.c cVar) {
        super(2, cVar);
        this.f1421h = 3;
        this.f1423j = i0Var;
        this.f1425l = iArr;
        this.f1426m = strArr;
    }
}
