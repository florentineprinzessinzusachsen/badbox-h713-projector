package o0;

import a3.q;
import d0.a0;
import d0.l0;
import h0.o;
import j2.i;
import l0.p;
import u2.m;
import u2.s;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1556a;

    static {
        String strG = a0.g("ConstraintTrkngWrkr");
        i.d(strG, "tagWithPrefix(...)");
        f1556a = strG;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public static final Object a(q qVar, p pVar, a2.c cVar) {
        f fVar;
        if (cVar instanceof f) {
            fVar = (f) cVar;
            int i4 = fVar.f1555h;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                fVar.f1555h = i4 - Integer.MIN_VALUE;
            } else {
                fVar = new f(cVar);
            }
        } else {
            fVar = new f(cVar);
        }
        Object objD = fVar.f1554g;
        int i5 = fVar.f1555h;
        if (i5 == 0) {
            l0.M(objD);
            o oVar = new o(1, new m(qVar.c(pVar), new f1.e(pVar, null, 2)));
            fVar.f1555h = 1;
            objD = s.d(oVar, fVar);
            z1.a aVar = z1.a.f2781d;
            if (objD == aVar) {
                return aVar;
            }
        } else {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            l0.M(objD);
        }
        return new Integer(((h0.b) objD).f997a);
    }
}
