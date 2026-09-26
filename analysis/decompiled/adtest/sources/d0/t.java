package d0;

import androidx.work.impl.WorkDatabase;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import r2.x0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class t implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f503d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f504e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f505f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f506g;

    public /* synthetic */ t(Object obj, Object obj2, Object obj3, int i4) {
        this.f503d = i4;
        this.f504e = obj;
        this.f505f = obj2;
        this.f506g = obj3;
    }

    private final void a() {
        c3.b bVar = (c3.b) this.f504e;
        e0.l lVar = (e0.l) this.f505f;
        final e0.f fVar = (e0.f) bVar.f372d;
        fVar.getClass();
        l0.k kVar = lVar.f656a;
        final String str = kVar.f1321a;
        final ArrayList arrayList = new ArrayList();
        l0.p pVar = (l0.p) fVar.f616e.n(new Callable() { // from class: e0.d
            @Override // java.util.concurrent.Callable
            public final Object call() {
                WorkDatabase workDatabase = fVar.f616e;
                l0.v vVarX = workDatabase.x();
                vVarX.getClass();
                String str2 = str;
                j2.i.e(str2, "id");
                arrayList.addAll((List) l3.h.W(vVarX.f1365a, true, false, new l0.b(17, str2)));
                return workDatabase.w().c(str2);
            }
        });
        if (pVar == null) {
            a0.e().h(e0.f.f611l, "Didn't find WorkSpec for id " + kVar);
            ((n0.a) fVar.f615d.f187h).execute(new e0.e(0, fVar, kVar));
            return;
        }
        synchronized (fVar.f622k) {
            try {
                if (fVar.f(str)) {
                    Set set = (Set) fVar.f619h.get(str);
                    if (((e0.l) set.iterator().next()).f656a.f1322b == kVar.f1322b) {
                        set.add(lVar);
                        a0.e().a(e0.f.f611l, "Work " + kVar + " is already enqueued for processing");
                    } else {
                        ((n0.a) fVar.f615d.f187h).execute(new e0.e(0, fVar, kVar));
                    }
                    return;
                }
                if (pVar.f1350t != kVar.f1322b) {
                    ((n0.a) fVar.f615d.f187h).execute(new e0.e(0, fVar, kVar));
                    return;
                }
                e0.k0 k0Var = new e0.k0(new e0.c0(fVar.f613b, fVar.f614c, fVar.f615d, fVar, fVar.f616e, pVar, arrayList));
                r2.s sVar = (r2.s) k0Var.f646d.f185f;
                x0 x0VarB = r2.x.b();
                sVar.getClass();
                g.l lVarU = a.a.u(l3.h.Y(sVar, x0VarB), new e0.i0(k0Var, null, 1));
                lVarU.f942e.a(new t(fVar, lVarU, k0Var, 2), (n0.a) fVar.f615d.f187h);
                fVar.f618g.put(str, k0Var);
                HashSet hashSet = new HashSet();
                hashSet.add(lVar);
                fVar.f619h.put(str, hashSet);
                a0.e().a(e0.f.f611l, e0.f.class.getSimpleName() + ": processing " + kVar);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean zBooleanValue;
        switch (this.f503d) {
            case 0:
                AtomicBoolean atomicBoolean = (AtomicBoolean) this.f504e;
                g.i iVar = (g.i) this.f505f;
                m0.p pVar = (m0.p) this.f506g;
                if (atomicBoolean.get()) {
                    return;
                }
                try {
                    pVar.a();
                    iVar.a(null);
                    return;
                } catch (Throwable th) {
                    iVar.b(th);
                    return;
                }
            case 1:
                AtomicBoolean atomicBoolean2 = (AtomicBoolean) this.f504e;
                g.i iVar2 = (g.i) this.f505f;
                i2.a aVar = (i2.a) this.f506g;
                if (atomicBoolean2.get()) {
                    return;
                }
                try {
                    iVar2.a(aVar.a());
                    return;
                } catch (Throwable th2) {
                    iVar2.b(th2);
                    return;
                }
            case 2:
                e0.f fVar = (e0.f) this.f504e;
                g.l lVar = (g.l) this.f505f;
                e0.k0 k0Var = (e0.k0) this.f506g;
                fVar.getClass();
                try {
                    zBooleanValue = ((Boolean) lVar.f942e.get()).booleanValue();
                    break;
                } catch (InterruptedException | ExecutionException unused) {
                    zBooleanValue = true;
                }
                synchronized (fVar.f622k) {
                    try {
                        l0.k kVarS = l0.s(k0Var.f643a);
                        String str = kVarS.f1321a;
                        if (fVar.d(str) == k0Var) {
                            fVar.b(str);
                        }
                        a0.e().a(e0.f.f611l, e0.f.class.getSimpleName() + " " + str + " executed; reschedule = " + zBooleanValue);
                        ArrayList arrayList = fVar.f621j;
                        int size = arrayList.size();
                        int i4 = 0;
                        while (i4 < size) {
                            Object obj = arrayList.get(i4);
                            i4++;
                            ((e0.b) obj).d(kVarS, zBooleanValue);
                        }
                    } catch (Throwable th3) {
                        throw th3;
                    }
                    break;
                }
                return;
            case 3:
                a();
                return;
            case 4:
                WorkDatabase workDatabase = (WorkDatabase) this.f504e;
                String str2 = (String) this.f505f;
                e0.y yVar = (e0.y) this.f506g;
                l0.t tVarW = workDatabase.w();
                tVarW.getClass();
                j2.i.e(str2, "name");
                Iterator it = ((List) l3.h.W(tVarW.f1361a, true, false, new l0.b(9, str2))).iterator();
                while (it.hasNext()) {
                    m0.g.a(yVar, (String) it.next());
                }
                return;
            default:
                String str3 = (String) this.f504e;
                String str4 = (String) this.f505f;
                AtomicReference atomicReference = (AtomicReference) this.f506g;
                try {
                    ArrayList arrayListC = n1.c.c(str3, str4);
                    if (arrayListC.isEmpty()) {
                        return;
                    }
                    n1.a aVar2 = new n1.a(arrayListC, str4);
                    while (!atomicReference.compareAndSet(null, aVar2) && atomicReference.get() == null) {
                    }
                    return;
                } catch (Exception unused2) {
                    return;
                }
        }
    }
}
