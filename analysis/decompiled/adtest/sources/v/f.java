package v;

import d0.l0;
import i2.l;
import i2.p;
import p.t;
import p.x;
import p.y;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f extends a2.i implements p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public x f2390h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2391i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public /* synthetic */ Object f2392j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ boolean f2393k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ t f2394l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ l f2395m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(l lVar, t tVar, y1.c cVar, boolean z3) {
        super(2, cVar);
        this.f2393k = z3;
        this.f2394l = tVar;
        this.f2395m = lVar;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        return ((f) i((y) obj, (y1.c) obj2)).l(u1.k.f2301a);
    }

    @Override // a2.a
    public final y1.c i(Object obj, y1.c cVar) {
        f fVar = new f(this.f2395m, this.f2394l, cVar, this.f2393k);
        fVar.f2392j = obj;
        return fVar;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0073 A[PHI: r0 r11
      0x0073: PHI (r0v7 p.y) = (r0v4 p.y), (r0v13 p.y) binds: [B:23:0x0070, B:12:0x0025] A[DONT_GENERATE, DONT_INLINE]
      0x0073: PHI (r11v14 java.lang.Object) = (r11v12 java.lang.Object), (r11v0 java.lang.Object) binds: [B:23:0x0070, B:12:0x0025] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:27:0x0077  */
    /* JADX WARN: Code duplicated, block: B:30:0x0082  */
    /* JADX WARN: Code duplicated, block: B:33:0x008d  */
    /* JADX WARN: Code duplicated, block: B:35:0x009b A[RETURN] */
    @Override // a2.a
    public final Object l(Object obj) {
        x xVar;
        x xVar2;
        y yVar;
        y yVar2;
        Object objC;
        Object obj2;
        int i4 = this.f2391i;
        l lVar = this.f2395m;
        if (i4 == 0) {
            l0.M(obj);
            y yVar3 = (y) this.f2392j;
            j2.i.c(yVar3, "null cannot be cast to non-null type androidx.room.coroutines.RawConnectionAccessor");
            return lVar.h(((r.t) yVar3).d());
        }
        t tVar = this.f2394l;
        z1.a aVar = z1.a.f2781d;
        if (i4 != 1) {
            if (i4 == 2) {
                xVar = this.f2390h;
                yVar2 = (y) this.f2392j;
                l0.M(obj);
            } else {
                if (i4 == 3) {
                    yVar = (y) this.f2392j;
                    l0.M(obj);
                    if (this.f2393k) {
                        return obj;
                    }
                    this.f2392j = obj;
                    this.f2391i = 4;
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
                obj2 = this.f2392j;
                l0.M(obj);
            }
            if (!((Boolean) obj).booleanValue()) {
                p.h hVarF = tVar.f();
                hVarF.f1646b.e(hVarF.f1649e, hVarF.f1650f);
            }
            return obj2;
        }
        xVar = this.f2390h;
        y yVar4 = (y) this.f2392j;
        l0.M(obj);
        if (((Boolean) obj).booleanValue()) {
            xVar2 = xVar;
            yVar = yVar4;
            a aVar2 = new a(null, lVar, 1);
            this.f2392j = yVar;
            this.f2390h = null;
            this.f2391i = 3;
            obj = yVar.a(xVar2, aVar2, this);
            if (obj != aVar) {
                if (this.f2393k) {
                    return obj;
                }
                this.f2392j = obj;
                this.f2391i = 4;
                objC = yVar.c(this);
                if (objC != aVar) {
                    obj2 = obj;
                    obj = objC;
                    if (!((Boolean) obj).booleanValue()) {
                        p.h hVarF2 = tVar.f();
                        hVarF2.f1646b.e(hVarF2.f1649e, hVarF2.f1650f);
                    }
                    return obj2;
                }
            }
        } else {
            p.h hVarF3 = tVar.f();
            this.f2392j = yVar4;
            this.f2390h = xVar;
            this.f2391i = 2;
            if (hVarF3.a(this) != aVar) {
                yVar2 = yVar4;
            }
        }
        return aVar;
        xVar2 = xVar;
        yVar = yVar2;
        a aVar3 = new a(null, lVar, 1);
        this.f2392j = yVar;
        this.f2390h = null;
        this.f2391i = 3;
        obj = yVar.a(xVar2, aVar3, this);
        if (obj != aVar) {
            if (this.f2393k) {
                return obj;
            }
            this.f2392j = obj;
            this.f2391i = 4;
            objC = yVar.c(this);
            if (objC != aVar) {
                obj2 = obj;
                obj = objC;
                if (!((Boolean) obj).booleanValue()) {
                    p.h hVarF4 = tVar.f();
                    hVarF4.f1646b.e(hVarF4.f1649e, hVarF4.f1650f);
                }
                return obj2;
            }
        }
        return aVar;
    }
}
