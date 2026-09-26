package v;

import d0.l0;
import i2.l;
import i2.p;
import p.t;
import p.x;
import p.y;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b extends a2.i implements p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public x f2369h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2370i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public /* synthetic */ Object f2371j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ boolean f2372k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f2373l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ t f2374m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ l f2375n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(l lVar, t tVar, y1.c cVar, boolean z3, boolean z4) {
        super(2, cVar);
        this.f2372k = z3;
        this.f2373l = z4;
        this.f2374m = tVar;
        this.f2375n = lVar;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        return ((b) i((y) obj, (y1.c) obj2)).l(u1.k.f2301a);
    }

    @Override // a2.a
    public final y1.c i(Object obj, y1.c cVar) {
        b bVar = new b(this.f2375n, this.f2374m, cVar, this.f2372k, this.f2373l);
        bVar.f2371j = obj;
        return bVar;
    }

    /* JADX WARN: Code duplicated, block: B:36:0x009f A[DONT_INVERT, PHI: r0 r12
      0x009f: PHI (r0v12 p.y) = (r0v9 p.y), (r0v19 p.y) binds: [B:34:0x009c, B:11:0x0027] A[DONT_GENERATE, DONT_INLINE]
      0x009f: PHI (r12v17 java.lang.Object) = (r12v15 java.lang.Object), (r12v0 java.lang.Object) binds: [B:34:0x009c, B:11:0x0027] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:37:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:40:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:43:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:45:0x00c5 A[RETURN] */
    @Override // a2.a
    public final Object l(Object obj) {
        x xVar;
        y yVar;
        x xVar2;
        y yVar2;
        y yVar3;
        Object objC;
        Object obj2;
        int i4 = this.f2370i;
        l lVar = this.f2375n;
        t tVar = this.f2374m;
        boolean z3 = this.f2373l;
        z1.a aVar = z1.a.f2781d;
        if (i4 == 0) {
            l0.M(obj);
            y yVar4 = (y) this.f2371j;
            if (!this.f2372k) {
                j2.i.c(yVar4, "null cannot be cast to non-null type androidx.room.coroutines.RawConnectionAccessor");
                return lVar.h(((r.t) yVar4).d());
            }
            xVar = z3 ? x.f1729d : x.f1730e;
            if (z3) {
                x xVar3 = xVar;
                yVar = yVar4;
                xVar2 = xVar3;
                a aVar2 = new a(null, lVar, 0);
                this.f2371j = yVar;
                this.f2369h = null;
                this.f2370i = 3;
                obj = yVar.a(xVar2, aVar2, this);
                if (obj != aVar) {
                    if (z3) {
                        return obj;
                    }
                    this.f2371j = obj;
                    this.f2370i = 4;
                    objC = yVar.c(this);
                    if (objC != aVar) {
                        obj2 = obj;
                        obj = objC;
                        if (!((Boolean) obj).booleanValue()) {
                            p.h hVarF = tVar.f();
                            hVarF.f1646b.e(hVarF.f1649e, hVarF.f1650f);
                        }
                        return obj2;
                    }
                }
            } else {
                this.f2371j = yVar4;
                this.f2369h = xVar;
                this.f2370i = 1;
                Object objC2 = yVar4.c(this);
                if (objC2 != aVar) {
                    yVar2 = yVar4;
                    obj = objC2;
                }
            }
            return aVar;
        }
        if (i4 == 1) {
            xVar = this.f2369h;
            yVar2 = (y) this.f2371j;
            l0.M(obj);
        } else {
            if (i4 == 2) {
                xVar = this.f2369h;
                yVar3 = (y) this.f2371j;
                l0.M(obj);
                xVar2 = xVar;
                yVar = yVar3;
                a aVar3 = new a(null, lVar, 0);
                this.f2371j = yVar;
                this.f2369h = null;
                this.f2370i = 3;
                obj = yVar.a(xVar2, aVar3, this);
                if (obj != aVar) {
                    if (z3) {
                        return obj;
                    }
                    this.f2371j = obj;
                    this.f2370i = 4;
                    objC = yVar.c(this);
                    if (objC != aVar) {
                        obj2 = obj;
                        obj = objC;
                    }
                }
                return aVar;
            }
            if (i4 == 3) {
                yVar = (y) this.f2371j;
                l0.M(obj);
                if (z3) {
                    return obj;
                }
                this.f2371j = obj;
                this.f2370i = 4;
                objC = yVar.c(this);
                if (objC != aVar) {
                    obj2 = obj;
                    obj = objC;
                }
                return aVar;
            }
            if (i4 != 4) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            obj2 = this.f2371j;
            l0.M(obj);
        }
        if (!((Boolean) obj).booleanValue()) {
            p.h hVarF2 = tVar.f();
            hVarF2.f1646b.e(hVarF2.f1649e, hVarF2.f1650f);
        }
        return obj2;
        if (((Boolean) obj).booleanValue()) {
            xVar2 = xVar;
            yVar = yVar2;
            a aVar4 = new a(null, lVar, 0);
            this.f2371j = yVar;
            this.f2369h = null;
            this.f2370i = 3;
            obj = yVar.a(xVar2, aVar4, this);
            if (obj != aVar) {
                if (z3) {
                    return obj;
                }
                this.f2371j = obj;
                this.f2370i = 4;
                objC = yVar.c(this);
                if (objC != aVar) {
                    obj2 = obj;
                    obj = objC;
                    if (!((Boolean) obj).booleanValue()) {
                        p.h hVarF3 = tVar.f();
                        hVarF3.f1646b.e(hVarF3.f1649e, hVarF3.f1650f);
                    }
                    return obj2;
                }
            }
        } else {
            p.h hVarF4 = tVar.f();
            this.f2371j = yVar2;
            this.f2369h = xVar;
            this.f2370i = 2;
            if (hVarF4.a(this) != aVar) {
                yVar3 = yVar2;
                xVar2 = xVar;
                yVar = yVar3;
                a aVar5 = new a(null, lVar, 0);
                this.f2371j = yVar;
                this.f2369h = null;
                this.f2370i = 3;
                obj = yVar.a(xVar2, aVar5, this);
                if (obj != aVar) {
                    if (z3) {
                        return obj;
                    }
                    this.f2371j = obj;
                    this.f2370i = 4;
                    objC = yVar.c(this);
                    if (objC != aVar) {
                        obj2 = obj;
                        obj = objC;
                        if (!((Boolean) obj).booleanValue()) {
                            p.h hVarF5 = tVar.f();
                            hVarF5.f1646b.e(hVarF5.f1649e, hVarF5.f1650f);
                        }
                        return obj2;
                    }
                }
            }
        }
        return aVar;
    }
}
