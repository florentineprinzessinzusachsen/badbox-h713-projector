package y1;

import i2.p;
import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b implements h, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final h f2723d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final f f2724e;

    public b(f fVar, h hVar) {
        j2.i.e(hVar, "left");
        j2.i.e(fVar, "element");
        this.f2723d = hVar;
        this.f2724e = fVar;
    }

    @Override // y1.h
    public final h C(g gVar) {
        j2.i.e(gVar, "key");
        f fVar = this.f2724e;
        f fVarK = fVar.k(gVar);
        h hVar = this.f2723d;
        if (fVarK != null) {
            return hVar;
        }
        h hVarC = hVar.C(gVar);
        if (hVarC == hVar) {
            return this;
        }
        return hVarC == i.f2726d ? fVar : new b(fVar, hVarC);
    }

    @Override // y1.h
    public final Object K(Object obj, p pVar) {
        return pVar.f(this.f2723d.K(obj, pVar), this.f2724e);
    }

    public final boolean equals(Object obj) {
        boolean zA;
        if (this == obj) {
            return true;
        }
        if (obj instanceof b) {
            b bVar = (b) obj;
            int i4 = 2;
            b bVar2 = bVar;
            int i5 = 2;
            while (true) {
                h hVar = bVar2.f2723d;
                bVar2 = hVar instanceof b ? (b) hVar : null;
                if (bVar2 == null) {
                    break;
                }
                i5++;
            }
            b bVar3 = this;
            while (true) {
                h hVar2 = bVar3.f2723d;
                bVar3 = hVar2 instanceof b ? (b) hVar2 : null;
                if (bVar3 == null) {
                    break;
                }
                i4++;
            }
            if (i5 == i4) {
                b bVar4 = this;
                while (true) {
                    f fVar = bVar4.f2724e;
                    if (!j2.i.a(bVar.k(fVar.getKey()), fVar)) {
                        zA = false;
                        break;
                    }
                    h hVar3 = bVar4.f2723d;
                    if (!(hVar3 instanceof b)) {
                        j2.i.c(hVar3, "null cannot be cast to non-null type kotlin.coroutines.CoroutineContext.Element");
                        f fVar2 = (f) hVar3;
                        zA = j2.i.a(bVar.k(fVar2.getKey()), fVar2);
                        break;
                    }
                    bVar4 = (b) hVar3;
                }
                if (zA) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f2724e.hashCode() + this.f2723d.hashCode();
    }

    @Override // y1.h
    public final f k(g gVar) {
        j2.i.e(gVar, "key");
        b bVar = this;
        while (true) {
            f fVarK = bVar.f2724e.k(gVar);
            if (fVarK != null) {
                return fVarK;
            }
            h hVar = bVar.f2723d;
            if (!(hVar instanceof b)) {
                return hVar.k(gVar);
            }
            bVar = (b) hVar;
        }
    }

    @Override // y1.h
    public final h l(h hVar) {
        j2.i.e(hVar, "context");
        return hVar == i.f2726d ? this : (h) hVar.K(this, new r1.b(9));
    }

    public final String toString() {
        return "[" + ((String) K("", new r1.b(8))) + ']';
    }
}
