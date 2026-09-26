package j1;

import a2.i;
import a3.q;
import d0.a0;
import d0.l0;
import d0.y;
import d0.z;
import i2.l;
import i2.p;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import m0.n;
import o0.g;
import r2.e0;
import r2.j1;
import r2.v;
import r2.x;
import u1.k;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c extends i implements p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f1245h = 1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1246i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public /* synthetic */ Object f1247j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Object f1248k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f1249l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f1250m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f1251n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f1252o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(z zVar, q qVar, l0.p pVar, y1.c cVar) {
        super(2, cVar);
        this.f1250m = zVar;
        this.f1251n = qVar;
        this.f1252o = pVar;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        v vVar = (v) obj;
        y1.c cVar = (y1.c) obj2;
        switch (this.f1245h) {
            case 0:
                break;
        }
        return ((c) i(vVar, cVar)).l(k.f2301a);
    }

    @Override // a2.a
    public final y1.c i(Object obj, y1.c cVar) {
        switch (this.f1245h) {
            case 0:
                c cVar2 = new c((String) this.f1252o, (l) this.f1251n, cVar);
                cVar2.f1247j = obj;
                return cVar2;
            default:
                c cVar3 = new c((z) this.f1250m, (q) this.f1251n, (l0.p) this.f1252o, cVar);
                cVar3.f1247j = obj;
                return cVar3;
        }
    }

    /* JADX WARN: Code duplicated, block: B:32:0x00c5  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v10, types: [r2.v0] */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v7, types: [r2.v0] */
    @Override // a2.a
    public final Object l(Object obj) {
        int i4;
        AtomicBoolean atomicBoolean;
        boolean z3;
        z2.c cVar;
        l lVar;
        r0.a aVar;
        AtomicInteger atomicInteger;
        Object objD;
        int i5 = this.f1245h;
        boolean z4 = false;
        ?? r4 = this.f1252o;
        z1.a aVar2 = z1.a.f2781d;
        Object obj2 = this.f1251n;
        switch (i5) {
            case 0:
                v vVar = (v) this.f1247j;
                int i6 = this.f1246i;
                int i7 = 2;
                if (i6 != 0) {
                    if (i6 == 1) {
                        atomicBoolean = (AtomicBoolean) this.f1248k;
                        l0.M(obj);
                        z3 = false;
                        i4 = 2;
                    } else {
                        if (i6 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        lVar = (l) this.f1250m;
                        cVar = (z2.c) this.f1249l;
                        l0.M(obj);
                    }
                    try {
                        lVar.h(null);
                        return k.f2301a;
                    } finally {
                        cVar.b(null);
                    }
                }
                l0.M(obj);
                AtomicBoolean atomicBoolean2 = new AtomicBoolean(false);
                m2.c cVar2 = new m2.c(0, 99, 1);
                String str = (String) r4;
                l lVar2 = (l) obj2;
                ArrayList arrayList = new ArrayList(v1.l.u0(cVar2));
                Iterator it = cVar2.iterator();
                while (true) {
                    m2.b bVar = (m2.b) it;
                    boolean z5 = bVar.f1448f;
                    if (z5) {
                        int i8 = bVar.f1449g;
                        if (i8 != bVar.f1447e) {
                            bVar.f1449g = bVar.f1446d + i8;
                        } else {
                            if (!z5) {
                                throw new NoSuchElementException();
                            }
                            bVar.f1448f = z4;
                        }
                        y2.e eVar = e0.f1974a;
                        arrayList.add(x.p(vVar, y2.d.f2753f, null, new b(str, i8, atomicBoolean2, vVar, lVar2, null), 2));
                        i7 = 2;
                        z4 = false;
                    } else {
                        i4 = i7;
                        this.f1247j = null;
                        this.f1248k = atomicBoolean2;
                        this.f1246i = 1;
                        if (x.o(arrayList, this) == aVar2) {
                            return aVar2;
                        }
                        atomicBoolean = atomicBoolean2;
                        z3 = false;
                    }
                }
                if (atomicBoolean.compareAndSet(z3, true)) {
                    z2.c cVar3 = e.f1257b;
                    l lVar3 = (l) obj2;
                    this.f1247j = null;
                    this.f1248k = null;
                    this.f1249l = cVar3;
                    this.f1250m = lVar3;
                    this.f1246i = i4;
                    if (cVar3.c(this) == aVar2) {
                        return aVar2;
                    }
                    cVar = cVar3;
                    lVar = lVar3;
                    lVar.h(null);
                }
                return k.f2301a;
            default:
                z zVar = (z) this.f1250m;
                int i9 = this.f1246i;
                try {
                    try {
                        if (i9 == 0) {
                            l0.M(obj);
                            v vVar2 = (v) this.f1247j;
                            AtomicInteger atomicInteger2 = new AtomicInteger(-256);
                            g.l lVarB = zVar.b();
                            aVar = lVarB;
                            j1 j1VarP = x.p(vVar2, null, null, new n((q) obj2, (l0.p) r4, atomicInteger2, lVarB, null, 1), 3);
                            try {
                                this.f1247j = atomicInteger2;
                                this.f1248k = aVar;
                                this.f1249l = j1VarP;
                                this.f1246i = 1;
                                objD = l0.d(aVar, this);
                                if (objD == aVar2) {
                                    return aVar2;
                                }
                                atomicInteger = atomicInteger2;
                                r4 = j1VarP;
                            } catch (CancellationException e4) {
                                e = e4;
                                atomicInteger = atomicInteger2;
                                String str2 = g.f1556a;
                                a0.e().b(str2, "Delegated worker " + zVar.getClass() + " was cancelled", e);
                                if (atomicInteger.get() != -256) {
                                }
                                if (aVar.isCancelled() || !z4) {
                                    throw e;
                                }
                                throw new o0.a(atomicInteger.get());
                            }
                        } else {
                            if (i9 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            j1 j1Var = (j1) this.f1249l;
                            aVar = (r0.a) this.f1248k;
                            atomicInteger = (AtomicInteger) this.f1247j;
                            try {
                                l0.M(obj);
                                objD = obj;
                                r4 = j1Var;
                            } catch (CancellationException e5) {
                                e = e5;
                                String str3 = g.f1556a;
                                a0.e().b(str3, "Delegated worker " + zVar.getClass() + " was cancelled", e);
                                z4 = atomicInteger.get() != -256;
                                if (aVar.isCancelled()) {
                                }
                                throw e;
                            }
                        }
                        y yVar = (y) objD;
                        r4.b(null);
                        return yVar;
                    } catch (Throwable th) {
                        String str4 = g.f1556a;
                        a0.e().b(str4, "Delegated worker " + zVar.getClass() + " threw exception in startWork.", th);
                        throw th;
                    }
                } catch (Throwable th2) {
                    r4.b(null);
                    throw th2;
                }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(String str, l lVar, y1.c cVar) {
        super(2, cVar);
        this.f1252o = str;
        this.f1251n = lVar;
    }
}
