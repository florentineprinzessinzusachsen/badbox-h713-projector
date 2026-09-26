package p;

import android.database.SQLException;
import d0.l0;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d0 extends a2.i implements i2.p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f1615h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1616i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public /* synthetic */ Object f1617j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ i0 f1618k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d0(i0 i0Var, y1.c cVar, int i4) {
        super(2, cVar);
        this.f1615h = i4;
        this.f1618k = i0Var;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        switch (this.f1615h) {
            case 0:
                return ((d0) i((r.m) obj, (y1.c) obj2)).l(u1.k.f2301a);
            case 1:
                return ((d0) i((y) obj, (y1.c) obj2)).l(u1.k.f2301a);
            default:
                return ((d0) i((y) obj, (y1.c) obj2)).l(u1.k.f2301a);
        }
    }

    @Override // a2.a
    public final y1.c i(Object obj, y1.c cVar) {
        switch (this.f1615h) {
            case 0:
                d0 d0Var = new d0(this.f1618k, cVar, 0);
                d0Var.f1617j = obj;
                return d0Var;
            case 1:
                d0 d0Var2 = new d0(this.f1618k, cVar, 1);
                d0Var2.f1617j = obj;
                return d0Var2;
            default:
                d0 d0Var3 = new d0(this.f1618k, cVar, 2);
                d0Var3.f1617j = obj;
                return d0Var3;
        }
    }

    @Override // a2.a
    public final Object l(Object obj) {
        y yVar;
        Object objC;
        Object objA;
        y yVar2;
        Object objC2;
        k[] kVarArr;
        k kVar;
        switch (this.f1615h) {
            case 0:
                int i4 = this.f1616i;
                if (i4 != 0) {
                    if (i4 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                    return obj;
                }
                l0.M(obj);
                r.m mVar = (r.m) this.f1617j;
                this.f1616i = 1;
                Object objA2 = i0.a(this.f1618k, mVar, this);
                z1.a aVar = z1.a.f2781d;
                return objA2 == aVar ? aVar : objA2;
            case 1:
                int i5 = this.f1616i;
                z1.a aVar2 = z1.a.f2781d;
                try {
                    if (i5 != 0) {
                        if (i5 == 1) {
                            yVar = (y) this.f1617j;
                            l0.M(obj);
                            objC = obj;
                        } else {
                            if (i5 != 2) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            l0.M(obj);
                            objA = obj;
                        }
                        return (Set) objA;
                    }
                    l0.M(obj);
                    yVar = (y) this.f1617j;
                    this.f1617j = yVar;
                    this.f1616i = 1;
                    objC = yVar.c(this);
                    if (objC == aVar2) {
                        return aVar2;
                    }
                    if (!((Boolean) objC).booleanValue()) {
                        x xVar = x.f1730e;
                        d0 d0Var = new d0(this.f1618k, null, 0);
                        this.f1617j = null;
                        this.f1616i = 2;
                        objA = yVar.a(xVar, d0Var, this);
                        if (objA == aVar2) {
                            return aVar2;
                        }
                        return (Set) objA;
                    }
                } catch (SQLException unused) {
                }
                return v1.r.f2519d;
            default:
                int i6 = this.f1616i;
                u1.k kVar2 = u1.k.f2301a;
                boolean z3 = true;
                z1.a aVar3 = z1.a.f2781d;
                if (i6 == 0) {
                    l0.M(obj);
                    yVar2 = (y) this.f1617j;
                    this.f1617j = yVar2;
                    this.f1616i = 1;
                    objC2 = yVar2.c(this);
                    if (objC2 != aVar3) {
                    }
                    return aVar3;
                }
                if (i6 != 1) {
                    if (i6 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                    return kVar2;
                }
                yVar2 = (y) this.f1617j;
                l0.M(obj);
                objC2 = obj;
                if (((Boolean) objC2).booleanValue()) {
                    return kVar2;
                }
                i0 i0Var = this.f1618k;
                e3.h hVar = i0Var.f1671h;
                long[] jArr = (long[]) hVar.f751f;
                ReentrantLock reentrantLock = (ReentrantLock) hVar.f750e;
                reentrantLock.lock();
                try {
                    if (hVar.f749d) {
                        boolean z4 = false;
                        hVar.f749d = false;
                        int length = jArr.length;
                        kVarArr = new k[length];
                        int i7 = 0;
                        boolean z5 = false;
                        while (i7 < length) {
                            if (jArr[i7] <= 0) {
                                z3 = z4;
                            }
                            boolean[] zArr = (boolean[]) hVar.f752g;
                            if (z3 != zArr[i7]) {
                                zArr[i7] = z3;
                                kVar = z3 ? k.f1677e : k.f1678f;
                                z5 = true;
                            } else {
                                kVar = k.f1676d;
                            }
                            kVarArr[i7] = kVar;
                            i7++;
                            z3 = true;
                            z4 = false;
                        }
                        if (!z5) {
                            kVarArr = null;
                        }
                        reentrantLock.unlock();
                    } else {
                        reentrantLock.unlock();
                        kVarArr = null;
                    }
                    if (kVarArr == null) {
                        return kVar2;
                    }
                    h0 h0Var = new h0(kVarArr, i0Var, yVar2, null);
                    this.f1617j = null;
                    this.f1616i = 2;
                    if (yVar2.a(x.f1730e, h0Var, this) != aVar3) {
                        return kVar2;
                    }
                    return aVar3;
                } catch (Throwable th) {
                    reentrantLock.unlock();
                    throw th;
                }
        }
    }
}
