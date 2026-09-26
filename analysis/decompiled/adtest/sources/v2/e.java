package v2;

import r2.x;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e extends d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final u2.g f2532g;

    public e(u2.g gVar, y1.h hVar, int i4, t2.a aVar) {
        super(hVar, i4, aVar);
        this.f2532g = gVar;
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0075  */
    /* JADX WARN: Code duplicated, block: B:29:0x007b A[RETURN] */
    @Override // v2.d, u2.g
    public final Object b(u2.h hVar, y1.c cVar) {
        Object objB;
        int i4 = this.f2530e;
        z1.a aVar = z1.a.f2781d;
        u1.k kVar = u1.k.f2301a;
        if (i4 == -3) {
            y1.h hVarG = cVar.g();
            Boolean bool = Boolean.FALSE;
            r1.b bVar = new r1.b(1);
            y1.h hVar2 = this.f2529d;
            y1.h hVarL = !((Boolean) hVar2.K(bool, bVar)).booleanValue() ? hVarG.l(hVar2) : x.h(hVarG, hVar2, false);
            if (j2.i.a(hVarL, hVarG)) {
                Object objB2 = this.f2532g.b(hVar, cVar);
                if (objB2 != aVar) {
                    objB2 = kVar;
                }
                if (objB2 == aVar) {
                    return objB2;
                }
            } else {
                y1.d dVar = y1.d.f2725d;
                if (j2.i.a(hVarL.k(dVar), hVarG.k(dVar))) {
                    y1.h hVarG2 = cVar.g();
                    if (!(hVar instanceof r) && !(hVar instanceof n)) {
                        hVar = new r.h(hVar, hVarG2);
                    }
                    Object objA = c.a(hVarL, hVar, w2.a.k(hVarL), new h0.f(this, null, 6), cVar);
                    if (objA == aVar) {
                        return objA;
                    }
                } else {
                    objB = super.b(hVar, cVar);
                    if (objB == aVar) {
                        return objB;
                    }
                }
            }
        } else {
            objB = super.b(hVar, cVar);
            if (objB == aVar) {
                return objB;
            }
        }
        return kVar;
    }

    @Override // v2.d
    public final Object c(t2.s sVar, y1.c cVar) {
        Object objB = this.f2532g.b(new r(sVar), cVar);
        u1.k kVar = u1.k.f2301a;
        z1.a aVar = z1.a.f2781d;
        if (objB != aVar) {
            objB = kVar;
        }
        return objB == aVar ? objB : kVar;
    }

    @Override // v2.d
    public final d d(y1.h hVar, int i4, t2.a aVar) {
        return new e(this.f2532g, hVar, i4, aVar);
    }

    @Override // v2.d
    public final String toString() {
        return this.f2532g + " -> " + super.toString();
    }
}
