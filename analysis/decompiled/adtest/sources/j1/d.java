package j1;

import a2.i;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import d0.l0;
import i2.p;
import p.h;
import p.i0;
import r2.d1;
import r2.h0;
import r2.o;
import r2.q;
import r2.s0;
import r2.v;
import r2.x;
import r2.z0;
import u1.k;
import u2.m;
import v2.n;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d extends i implements p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f1253h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1254i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ Object f1255j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d(Object obj, y1.c cVar, int i4) {
        super(2, cVar);
        this.f1253h = i4;
        this.f1255j = obj;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        v vVar = (v) obj;
        y1.c cVar = (y1.c) obj2;
        switch (this.f1253h) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
        return ((d) i(vVar, cVar)).l(k.f2301a);
    }

    @Override // a2.a
    public final y1.c i(Object obj, y1.c cVar) {
        switch (this.f1253h) {
            case 0:
                return new d((String) this.f1255j, cVar, 0);
            case 1:
                return new d((ConstraintTrackingWorker) this.f1255j, cVar, 1);
            case 2:
                return new d((h) this.f1255j, cVar, 2);
            case 3:
                return new d((i0) this.f1255j, cVar, 3);
            case 4:
                return new d((o) this.f1255j, cVar, 4);
            default:
                return new d((m) this.f1255j, cVar, 5);
        }
    }

    @Override // a2.a
    public final Object l(Object obj) throws Throwable {
        boolean zBooleanValue;
        Object objU;
        switch (this.f1253h) {
            case 0:
                int i4 = this.f1254i;
                try {
                    if (i4 == 0) {
                        l0.M(obj);
                        f1.e eVar = new f1.e((String) this.f1255j, null, 1);
                        this.f1254i = 1;
                        obj = x.x(3000L, eVar, this);
                        z1.a aVar = z1.a.f2781d;
                        if (obj == aVar) {
                            return aVar;
                        }
                    } else {
                        if (i4 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        l0.M(obj);
                    }
                    zBooleanValue = ((Boolean) obj).booleanValue();
                    break;
                } catch (Exception unused) {
                    zBooleanValue = false;
                }
                return Boolean.valueOf(zBooleanValue);
            case 1:
                int i5 = this.f1254i;
                if (i5 != 0) {
                    if (i5 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                    return obj;
                }
                l0.M(obj);
                ConstraintTrackingWorker constraintTrackingWorker = (ConstraintTrackingWorker) this.f1255j;
                this.f1254i = 1;
                Object objE = ConstraintTrackingWorker.e(constraintTrackingWorker, this);
                z1.a aVar2 = z1.a.f2781d;
                return objE == aVar2 ? aVar2 : objE;
            case 2:
                int i6 = this.f1254i;
                if (i6 == 0) {
                    l0.M(obj);
                    h hVar = (h) this.f1255j;
                    this.f1254i = 1;
                    Object objA = hVar.a(this);
                    z1.a aVar3 = z1.a.f2781d;
                    if (objA == aVar3) {
                        return aVar3;
                    }
                } else {
                    if (i6 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                }
                return k.f2301a;
            case 3:
                int i7 = this.f1254i;
                if (i7 == 0) {
                    l0.M(obj);
                    i0 i0Var = (i0) this.f1255j;
                    this.f1254i = 1;
                    Object objF = i0Var.f(this);
                    z1.a aVar4 = z1.a.f2781d;
                    if (objF == aVar4) {
                        return aVar4;
                    }
                } else {
                    if (i7 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                }
                return k.f2301a;
            case 4:
                int i8 = this.f1254i;
                if (i8 != 0) {
                    if (i8 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                    return obj;
                }
                l0.M(obj);
                o oVar = (o) this.f1255j;
                this.f1254i = 1;
                while (true) {
                    Object obj2 = d1.f1971d.get(oVar);
                    if (obj2 instanceof s0) {
                        if (oVar.V(obj2) >= 0) {
                            z0 z0Var = new z0(z1.d.a(this), oVar);
                            z0Var.v();
                            z0Var.y(new r2.f(2, x.n(oVar, true, new h0(2, z0Var))));
                            objU = z0Var.u();
                        }
                    } else {
                        if (obj2 instanceof q) {
                            throw ((q) obj2).f2018a;
                        }
                        objU = x.u(obj2);
                    }
                }
                z1.a aVar5 = z1.a.f2781d;
                return objU == aVar5 ? aVar5 : objU;
            default:
                int i9 = this.f1254i;
                k kVar = k.f2301a;
                if (i9 != 0) {
                    if (i9 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                    return kVar;
                }
                l0.M(obj);
                m mVar = (m) this.f1255j;
                this.f1254i = 1;
                Object objB = mVar.b(n.f2556d, this);
                z1.a aVar6 = z1.a.f2781d;
                if (objB != aVar6) {
                    objB = kVar;
                }
                return objB == aVar6 ? aVar6 : kVar;
        }
    }
}
