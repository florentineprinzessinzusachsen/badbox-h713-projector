package v2;

import r2.x;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class o extends a2.c implements u2.h {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final u2.h f2557g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final y1.h f2558h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f2559i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public y1.h f2560j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public y1.c f2561k;

    public o(u2.h hVar, y1.h hVar2) {
        super(m.f2555d, y1.i.f2726d);
        this.f2557g = hVar;
        this.f2558h = hVar2;
        this.f2559i = ((Number) hVar2.K(0, new r1.b(4))).intValue();
    }

    @Override // u2.h
    public final Object c(Object obj, y1.c cVar) {
        try {
            Object objQ = q(cVar, obj);
            return objQ == z1.a.f2781d ? objQ : u1.k.f2301a;
        } catch (Throwable th) {
            this.f2560j = new j(th, cVar.g());
            throw th;
        }
    }

    @Override // a2.a, a2.d
    public final a2.d e() {
        y1.c cVar = this.f2561k;
        if (cVar instanceof a2.d) {
            return (a2.d) cVar;
        }
        return null;
    }

    @Override // a2.c, y1.c
    public final y1.h g() {
        y1.h hVar = this.f2560j;
        return hVar == null ? y1.i.f2726d : hVar;
    }

    @Override // a2.a
    public final StackTraceElement k() {
        return null;
    }

    @Override // a2.a
    public final Object l(Object obj) {
        Throwable thA = u1.h.a(obj);
        if (thA != null) {
            this.f2560j = new j(thA, g());
        }
        y1.c cVar = this.f2561k;
        if (cVar != null) {
            cVar.j(obj);
        }
        return z1.a.f2781d;
    }

    public final Object q(y1.c cVar, Object obj) {
        y1.h hVarG = cVar.g();
        x.g(hVarG);
        y1.h hVar = this.f2560j;
        if (hVar != hVarG) {
            if (hVar instanceof j) {
                throw new IllegalStateException(p2.j.s0("\n            Flow exception transparency is violated:\n                Previous 'emit' call has thrown exception " + ((j) hVar).f2554e + ", but then emission attempt of value '" + obj + "' has been detected.\n                Emissions from 'catch' blocks are prohibited in order to avoid unspecified behaviour, 'Flow.catch' operator can be used instead.\n                For a more detailed explanation, please refer to Flow documentation.\n            ").toString());
            }
            if (((Number) hVarG.K(0, new p2.q(3, this))).intValue() != this.f2559i) {
                throw new IllegalStateException(("Flow invariant is violated:\n\t\tFlow was collected in " + this.f2558h + ",\n\t\tbut emission happened in " + hVarG + ".\n\t\tPlease refer to 'flow' documentation or use 'flowOn' instead").toString());
            }
            this.f2560j = hVarG;
        }
        this.f2561k = cVar;
        i2.q qVar = q.f2563a;
        u2.h hVar2 = this.f2557g;
        j2.i.c(hVar2, "null cannot be cast to non-null type kotlinx.coroutines.flow.FlowCollector<kotlin.Any?>");
        Object objD = qVar.d(hVar2, obj, this);
        if (!j2.i.a(objD, z1.a.f2781d)) {
            this.f2561k = null;
        }
        return objD;
    }
}
