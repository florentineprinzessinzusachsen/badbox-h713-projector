package z1;

import i2.p;
import j2.i;
import r2.s;
import r2.x;
import w2.f;
import w2.q;
import y1.e;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class d {
    public static y1.c a(y1.c cVar) {
        i.e(cVar, "<this>");
        a2.c cVar2 = cVar instanceof a2.c ? (a2.c) cVar : null;
        if (cVar2 == null || (cVar = cVar2.f43f) != null) {
            return cVar;
        }
        e eVar = (e) cVar2.g().k(y1.d.f2725d);
        y1.c fVar = eVar != null ? new f((s) eVar, cVar2) : cVar2;
        cVar2.f43f = fVar;
        return fVar;
    }

    public static final Object b(q qVar, q qVar2, p pVar) {
        Object qVar3;
        Object objN;
        try {
            j2.q.a(2, pVar);
            qVar3 = pVar.f(qVar2, qVar);
        } catch (Throwable th) {
            qVar3 = new r2.q(th, false);
        }
        a aVar = a.f2781d;
        if (qVar3 == aVar || (objN = qVar.N(qVar3)) == x.f2043e) {
            return aVar;
        }
        if (objN instanceof r2.q) {
            throw ((r2.q) objN).f2018a;
        }
        return x.u(objN);
    }
}
