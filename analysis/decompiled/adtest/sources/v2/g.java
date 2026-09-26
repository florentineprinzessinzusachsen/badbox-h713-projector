package v2;

import d0.l0;
import r2.s1;
import r2.x;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class g implements u2.h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ t2.e f2536d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f2537e;

    public g(t2.e eVar, int i4) {
        this.f2536d = eVar;
        this.f2537e = i4;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // u2.h
    public final Object c(Object obj, y1.c cVar) {
        f fVar;
        Object obj2;
        if (cVar instanceof f) {
            fVar = (f) cVar;
            int i4 = fVar.f2535i;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                fVar.f2535i = i4 - Integer.MIN_VALUE;
            } else {
                fVar = new f(this, cVar);
            }
        } else {
            fVar = new f(this, cVar);
        }
        Object obj3 = fVar.f2533g;
        int i5 = fVar.f2535i;
        z1.a aVar = z1.a.f2781d;
        u1.k kVar = u1.k.f2301a;
        if (i5 == 0) {
            l0.M(obj3);
            v1.s sVar = new v1.s(this.f2537e, obj);
            fVar.f2535i = 1;
            if (this.f2536d.d(sVar, fVar) != aVar) {
            }
        }
        if (i5 != 1) {
            if (i5 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            l0.M(obj3);
            return kVar;
        }
        l0.M(obj3);
        fVar.f2535i = 2;
        y1.h hVar = fVar.f42e;
        j2.i.b(hVar);
        x.g(hVar);
        y1.c cVarA = z1.d.a(fVar);
        w2.f fVar2 = cVarA instanceof w2.f ? (w2.f) cVarA : null;
        if (fVar2 == null) {
            obj2 = kVar;
        } else {
            r2.s sVar2 = fVar2.f2622g;
            if (sVar2.U(hVar)) {
                fVar2.f2624i = kVar;
                fVar2.f1962f = 1;
                sVar2.T(hVar, fVar2);
            } else {
                y1.h hVarL = hVar.l(new s1(s1.f2025e));
                fVar2.f2624i = kVar;
                fVar2.f1962f = 1;
                sVar2.T(hVarL, fVar2);
            }
            obj2 = aVar;
        }
        if (obj2 != aVar) {
            obj2 = kVar;
        }
        return obj2 == aVar ? aVar : kVar;
    }
}
