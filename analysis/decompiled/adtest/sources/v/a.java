package v;

import d0.l0;
import d0.u;
import i2.l;
import i2.p;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import r.m;
import r2.d1;
import r2.e0;
import r2.j1;
import r2.o;
import r2.q;
import r2.s0;
import r2.v;
import r2.w;
import r2.x;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a extends a2.i implements p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f2366h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f2367i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ u1.a f2368j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public a(p pVar, y1.c cVar) {
        super(2, cVar);
        this.f2366h = 2;
        this.f2368j = (a2.i) pVar;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        switch (this.f2366h) {
            case 0:
                return ((a) i((m) obj, (y1.c) obj2)).l(u1.k.f2301a);
            case 1:
                return ((a) i((m) obj, (y1.c) obj2)).l(u1.k.f2301a);
            default:
                return ((a) i((v) obj, (y1.c) obj2)).l(u1.k.f2301a);
        }
    }

    /* JADX WARN: Type inference failed for: r1v5, types: [a2.i, i2.p] */
    @Override // a2.a
    public final y1.c i(Object obj, y1.c cVar) {
        switch (this.f2366h) {
            case 0:
                a aVar = new a(cVar, (l) this.f2368j, 0);
                aVar.f2367i = obj;
                return aVar;
            case 1:
                a aVar2 = new a(cVar, (l) this.f2368j, 1);
                aVar2.f2367i = obj;
                return aVar2;
            default:
                a aVar3 = new a((a2.i) this.f2368j, cVar);
                aVar3.f2367i = obj;
                return aVar3;
        }
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [a2.i, i2.p] */
    @Override // a2.a
    public final Object l(Object obj) throws Throwable {
        int i4 = this.f2366h;
        u1.a aVar = this.f2368j;
        switch (i4) {
            case 0:
                l0.M(obj);
                m mVar = (m) this.f2367i;
                j2.i.c(mVar, "null cannot be cast to non-null type androidx.room.coroutines.RawConnectionAccessor");
                return ((l) aVar).h(mVar.d());
            case 1:
                l0.M(obj);
                m mVar2 = (m) this.f2367i;
                j2.i.c(mVar2, "null cannot be cast to non-null type androidx.room.coroutines.RawConnectionAccessor");
                return ((l) aVar).h(mVar2.d());
            default:
                l0.M(obj);
                y1.h hVarI = ((v) this.f2367i).i();
                y1.d dVar = y1.d.f2725d;
                y1.f fVarK = hVarI.k(dVar);
                j2.i.b(fVarK);
                y1.e eVar = (y1.e) fVarK;
                o oVar = new o(true);
                y1.c cVar = null;
                oVar.H(null);
                u uVar = new u(oVar, (p) aVar, (y1.c) null);
                y1.h hVarH = x.h(y1.i.f2726d, eVar, true);
                y2.e eVar2 = e0.f1974a;
                if (hVarH != eVar2 && hVarH.k(dVar) == null) {
                    hVarH = hVarH.l(eVar2);
                }
                r2.a j1Var = new j1(hVarH, true);
                j1Var.b0(w.f2036g, j1Var, uVar);
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = d1.f1971d;
                    if (!(atomicReferenceFieldUpdater.get(oVar) instanceof s0)) {
                        Object obj2 = atomicReferenceFieldUpdater.get(oVar);
                        if (obj2 instanceof s0) {
                            throw new IllegalStateException("This job has not completed yet");
                        }
                        if (obj2 instanceof q) {
                            throw ((q) obj2).f2018a;
                        }
                        return x.u(obj2);
                    }
                    try {
                        return x.s(eVar, new j1.d(oVar, cVar, 4));
                    } catch (InterruptedException unused) {
                    }
                }
                break;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(y1.c cVar, l lVar, int i4) {
        super(2, cVar);
        this.f2366h = i4;
        this.f2368j = lVar;
    }
}
