package androidx.work.impl.workers;

import a2.c;
import a3.q;
import android.content.Context;
import android.os.Build;
import androidx.work.CoroutineWorker;
import androidx.work.WorkerParameters;
import d0.a0;
import d0.h;
import d0.j;
import d0.l;
import d0.l0;
import d0.v;
import d0.w;
import d0.z;
import e0.y;
import j2.i;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import l0.p;
import l0.t;
import m0.n;
import o0.b;
import o0.g;
import r2.s;
import r2.x;
import z1.a;
import z1.d;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class ConstraintTrackingWorker extends CoroutineWorker {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final WorkerParameters f335g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConstraintTrackingWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        i.e(context, "appContext");
        i.e(workerParameters, "workerParameters");
        this.f335g = workerParameters;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object d(ConstraintTrackingWorker constraintTrackingWorker, z zVar, q qVar, p pVar, c cVar) {
        b bVar;
        constraintTrackingWorker.getClass();
        if (cVar instanceof b) {
            bVar = (b) cVar;
            int i4 = bVar.f1544i;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                bVar.f1544i = i4 - Integer.MIN_VALUE;
            } else {
                bVar = new b(constraintTrackingWorker, cVar);
            }
        } else {
            bVar = new b(constraintTrackingWorker, cVar);
        }
        Object objB = bVar.f1542g;
        int i5 = bVar.f1544i;
        if (i5 == 0) {
            l0.M(objB);
            j1.c cVar2 = new j1.c(zVar, qVar, pVar, null);
            bVar.f1544i = 1;
            w2.q qVar2 = new w2.q(bVar, bVar.g());
            objB = d.b(qVar2, qVar2, cVar2);
            a aVar = a.f2781d;
            if (objB == aVar) {
                return aVar;
            }
        } else {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            l0.M(objB);
        }
        i.d(objB, "coroutineScope(...)");
        return objB;
    }

    /* JADX WARN: Code duplicated, block: B:66:0x0170  */
    /* JADX WARN: Code duplicated, block: B:68:0x0176  */
    /* JADX WARN: Code duplicated, block: B:69:0x0179  */
    /* JADX WARN: Code duplicated, block: B:71:0x017f  */
    /* JADX WARN: Code duplicated, block: B:72:0x0184  */
    /* JADX WARN: Code duplicated, block: B:74:0x0188  */
    /* JADX WARN: Code duplicated, block: B:76:0x0193  */
    /* JADX WARN: Code duplicated, block: B:80:0x019f  */
    /* JADX WARN: Code duplicated, block: B:81:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:8:0x0020  */
    public static final Object e(ConstraintTrackingWorker constraintTrackingWorker, c cVar) {
        o0.c cVar2;
        z zVarA;
        int i4;
        WorkerParameters workerParameters = constraintTrackingWorker.f335g;
        AtomicInteger atomicInteger = constraintTrackingWorker.f516c;
        Context context = constraintTrackingWorker.f514a;
        WorkerParameters workerParameters2 = constraintTrackingWorker.f515b;
        if (cVar instanceof o0.c) {
            cVar2 = (o0.c) cVar;
            int i5 = cVar2.f1548j;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                cVar2.f1548j = i5 - Integer.MIN_VALUE;
            } else {
                cVar2 = new o0.c(constraintTrackingWorker, cVar);
            }
        } else {
            cVar2 = new o0.c(constraintTrackingWorker, cVar);
        }
        o0.c cVar3 = cVar2;
        Object objW = cVar3.f1546h;
        int i6 = cVar3.f1548j;
        if (i6 == 0) {
            l0.M(objW);
            j jVar = workerParameters2.f311b;
            jVar.getClass();
            Object obj = jVar.f465a.get("androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME");
            String str = obj instanceof String ? (String) obj : null;
            if (str == null || str.length() == 0) {
                a0.e().c(g.f1556a, "No worker to delegate to.");
                return new v();
            }
            y yVarS = y.S(context);
            t tVarW = yVarS.f694c.w();
            String string = workerParameters2.f310a.toString();
            i.d(string, "toString(...)");
            p pVarC = tVarW.c(string);
            if (pVarC == null) {
                return new v();
            }
            a3.z zVar = yVarS.f701j;
            i.d(zVar, "getTrackers(...)");
            q qVar = new q(zVar);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = qVar.f197a;
            int size = arrayList2.size();
            int i7 = 0;
            while (i7 < size) {
                Object obj2 = arrayList2.get(i7);
                i7++;
                if (((i0.d) obj2).b(pVarC)) {
                    arrayList.add(obj2);
                }
            }
            if (!arrayList.isEmpty()) {
                a0.e().a(h0.q.f1032a, "Work " + pVarC.f1331a + " constrained by " + v1.j.y0(arrayList, null, null, null, new h(1), 31));
            }
            if (!arrayList.isEmpty()) {
                String str2 = g.f1556a;
                a0.e().a(str2, "Constraints not met for delegate " + str + ". Requesting retry.");
                return new w();
            }
            a0.e().a(g.f1556a, "Constraints met for delegate ".concat(str));
            try {
                l lVar = workerParameters2.f315f;
                i.d(context, "getApplicationContext(...)");
                zVarA = lVar.a(context, str, workerParameters);
                n0.a aVar = (n0.a) workerParameters.f314e.f187h;
                i.d(aVar, "getMainThreadExecutor(...)");
                try {
                    s sVarI = x.i(aVar);
                    try {
                        n nVar = new n(constraintTrackingWorker, zVarA, qVar, pVarC, null);
                        cVar3.f1545g = zVarA;
                        cVar3.f1548j = 1;
                        objW = x.w(sVarI, nVar, cVar3);
                        a aVar2 = a.f2781d;
                        if (objW == aVar2) {
                            return aVar2;
                        }
                        zVarA = zVarA;
                    } catch (CancellationException e4) {
                        e = e4;
                        zVarA = zVarA;
                        if (atomicInteger.get() == -256 || (e instanceof o0.a)) {
                            if (Build.VERSION.SDK_INT < 31) {
                                i4 = -512;
                            } else if (atomicInteger.get() != -256) {
                                i4 = atomicInteger.get();
                            } else {
                                if (!(e instanceof o0.a)) {
                                    throw new IllegalStateException("Unreachable");
                                }
                                i4 = ((o0.a) e).f1541d;
                            }
                            zVarA.f516c.compareAndSet(-256, i4);
                        }
                        if (e instanceof o0.a) {
                            return new w();
                        }
                        throw e;
                    }
                } catch (CancellationException e5) {
                    e = e5;
                }
            } catch (Throwable unused) {
                a0.e().a(g.f1556a, "No worker to delegate to.");
                yVarS.f693b.getClass();
                return new v();
            }
        } else {
            if (i6 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            zVarA = cVar3.f1545g;
            try {
                l0.M(objW);
            } catch (CancellationException e6) {
                e = e6;
                if (atomicInteger.get() == -256) {
                    if (Build.VERSION.SDK_INT < 31) {
                        i4 = -512;
                    } else if (atomicInteger.get() != -256) {
                        i4 = atomicInteger.get();
                    } else {
                        if (!(e instanceof o0.a)) {
                            throw new IllegalStateException("Unreachable");
                        }
                        i4 = ((o0.a) e).f1541d;
                    }
                    zVarA.f516c.compareAndSet(-256, i4);
                } else {
                    if (Build.VERSION.SDK_INT < 31) {
                        i4 = -512;
                    } else if (atomicInteger.get() != -256) {
                        i4 = atomicInteger.get();
                    } else {
                        if (!(e instanceof o0.a)) {
                            throw new IllegalStateException("Unreachable");
                        }
                        i4 = ((o0.a) e).f1541d;
                    }
                    zVarA.f516c.compareAndSet(-256, i4);
                }
                if (e instanceof o0.a) {
                    return new w();
                }
                throw e;
            }
        }
        return (d0.y) objW;
    }

    @Override // androidx.work.CoroutineWorker
    public final Object c(d0.g gVar) {
        ExecutorService executorService = this.f515b.f312c;
        i.d(executorService, "getBackgroundExecutor(...)");
        return x.w(x.i(executorService), new j1.d(this, null, 1), gVar);
    }
}
