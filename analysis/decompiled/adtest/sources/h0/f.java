package h0;

import d0.a0;
import d0.l0;
import p.i0;
import r2.v;
import r2.x;
import t2.r;
import t2.s;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f extends a2.i implements i2.p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f1004h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1005i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public /* synthetic */ Object f1006j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ Object f1007k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f(Object obj, Object obj2, y1.c cVar, int i4) {
        super(2, cVar);
        this.f1004h = i4;
        this.f1006j = obj;
        this.f1007k = obj2;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        switch (this.f1004h) {
            case 0:
                return ((f) i((v) obj, (y1.c) obj2)).l(u1.k.f2301a);
            case 1:
                return ((f) i((s) obj, (y1.c) obj2)).l(u1.k.f2301a);
            case 2:
                return ((f) i((v) obj, (y1.c) obj2)).l(u1.k.f2301a);
            case 3:
                return ((f) i((v) obj, (y1.c) obj2)).l(u1.k.f2301a);
            case 4:
                return ((f) i((v) obj, (y1.c) obj2)).l(u1.k.f2301a);
            case 5:
                return ((f) i((s) obj, (y1.c) obj2)).l(u1.k.f2301a);
            case 6:
                return ((f) i((u2.h) obj, (y1.c) obj2)).l(u1.k.f2301a);
            default:
                return ((f) i(obj, (y1.c) obj2)).l(u1.k.f2301a);
        }
    }

    @Override // a2.a
    public final y1.c i(Object obj, y1.c cVar) {
        switch (this.f1004h) {
            case 0:
                return new f((g) this.f1006j, (s) this.f1007k, cVar, 0);
            case 1:
                f fVar = new f((i0.b) this.f1007k, cVar, 1);
                fVar.f1006j = obj;
                return fVar;
            case 2:
                return new f((i0) this.f1006j, (i2.a) this.f1007k, cVar, 2);
            case 3:
                return new f((i2.p) this.f1006j, (r.s) this.f1007k, cVar, 3);
            case 4:
                return new f((i2.p) this.f1006j, (j2.n) this.f1007k, cVar, 4);
            case 5:
                f fVar2 = new f((v2.d) this.f1007k, cVar, 5);
                fVar2.f1006j = obj;
                return fVar2;
            case 6:
                f fVar3 = new f((v2.e) this.f1007k, cVar, 6);
                fVar3.f1006j = obj;
                return fVar3;
            default:
                f fVar4 = new f((u2.h) this.f1007k, cVar, 7);
                fVar4.f1006j = obj;
                return fVar4;
        }
    }

    @Override // a2.a
    public final Object l(Object obj) {
        switch (this.f1004h) {
            case 0:
                z1.a aVar = z1.a.f2781d;
                int i4 = this.f1005i;
                if (i4 == 0) {
                    l0.M(obj);
                    this.f1005i = 1;
                    if (x.f(1000L, this) == aVar) {
                        return aVar;
                    }
                } else {
                    if (i4 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                }
                a0.e().a(q.f1032a, "NetworkRequestConstraintController didn't receive neither onCapabilitiesChanged/onLost callback, sending `ConstraintsNotMet` after 1000 ms");
                ((r) ((s) this.f1007k)).h(new b(7));
                return u1.k.f2301a;
            case 1:
                z1.a aVar2 = z1.a.f2781d;
                int i5 = this.f1005i;
                if (i5 == 0) {
                    l0.M(obj);
                    s sVar = (s) this.f1006j;
                    i0.b bVar = (i0.b) this.f1007k;
                    i0.a aVar3 = new i0.a(bVar, sVar);
                    j0.g gVar = bVar.f1204a;
                    gVar.getClass();
                    synchronized (gVar.f1222c) {
                        try {
                            if (gVar.f1223d.add(aVar3)) {
                                if (gVar.f1223d.size() == 1) {
                                    gVar.f1224e = gVar.a();
                                    a0.e().a(j0.h.f1225a, gVar.getClass().getSimpleName() + ": initial state = " + gVar.f1224e);
                                    gVar.c();
                                }
                                aVar3.a(gVar.f1224e);
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                        break;
                    }
                    k kVar = new k(4, (i0.b) this.f1007k, aVar3);
                    this.f1005i = 1;
                    if (l0.e(sVar, kVar, this) == aVar2) {
                        return aVar2;
                    }
                } else {
                    if (i5 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                }
                return u1.k.f2301a;
            case 2:
                i2.a aVar4 = (i2.a) this.f1007k;
                z1.a aVar5 = z1.a.f2781d;
                int i6 = this.f1005i;
                try {
                    if (i6 == 0) {
                        l0.M(obj);
                        i0 i0Var = (i0) this.f1006j;
                        this.f1005i = 1;
                        obj = i0.b(i0Var, this);
                        if (obj == aVar5) {
                            return aVar5;
                        }
                    } else {
                        if (i6 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        l0.M(obj);
                    }
                    aVar4.a();
                    return u1.k.f2301a;
                } catch (Throwable th2) {
                    aVar4.a();
                    throw th2;
                }
            case 3:
                z1.a aVar6 = z1.a.f2781d;
                int i7 = this.f1005i;
                if (i7 != 0) {
                    if (i7 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                    return obj;
                }
                l0.M(obj);
                i2.p pVar = (i2.p) this.f1006j;
                r.s sVar2 = (r.s) this.f1007k;
                this.f1005i = 1;
                Object objF = pVar.f(sVar2, this);
                return objF == aVar6 ? aVar6 : objF;
            case 4:
                z1.a aVar7 = z1.a.f2781d;
                int i8 = this.f1005i;
                if (i8 != 0) {
                    if (i8 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                    return obj;
                }
                l0.M(obj);
                i2.p pVar2 = (i2.p) this.f1006j;
                Object obj2 = ((j2.n) this.f1007k).f1276d;
                this.f1005i = 1;
                Object objF2 = pVar2.f(obj2, this);
                return objF2 == aVar7 ? aVar7 : objF2;
            case 5:
                z1.a aVar8 = z1.a.f2781d;
                int i9 = this.f1005i;
                if (i9 == 0) {
                    l0.M(obj);
                    s sVar3 = (s) this.f1006j;
                    v2.d dVar = (v2.d) this.f1007k;
                    this.f1005i = 1;
                    if (dVar.c(sVar3, this) == aVar8) {
                        return aVar8;
                    }
                } else {
                    if (i9 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                }
                return u1.k.f2301a;
            case 6:
                u1.k kVar2 = u1.k.f2301a;
                z1.a aVar9 = z1.a.f2781d;
                int i10 = this.f1005i;
                if (i10 != 0) {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                    return kVar2;
                }
                l0.M(obj);
                u2.h hVar = (u2.h) this.f1006j;
                v2.e eVar = (v2.e) this.f1007k;
                this.f1005i = 1;
                Object objB = eVar.f2532g.b(hVar, this);
                if (objB != aVar9) {
                    objB = kVar2;
                }
                return objB == aVar9 ? aVar9 : kVar2;
            default:
                z1.a aVar10 = z1.a.f2781d;
                int i11 = this.f1005i;
                if (i11 == 0) {
                    l0.M(obj);
                    Object obj3 = this.f1006j;
                    u2.h hVar2 = (u2.h) this.f1007k;
                    this.f1005i = 1;
                    if (hVar2.c(obj3, this) == aVar10) {
                        return aVar10;
                    }
                } else {
                    if (i11 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                }
                return u1.k.f2301a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f(Object obj, y1.c cVar, int i4) {
        super(2, cVar);
        this.f1004h = i4;
        this.f1007k = obj;
    }
}
