package o0;

import d0.a0;
import d0.l0;
import j2.i;
import java.util.Iterator;
import java.util.List;
import l0.j;
import l0.k;
import l0.m;
import l0.p;
import l0.v;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1557a;

    static {
        String strG = a0.g("DiagnosticsWrkr");
        i.d(strG, "tagWithPrefix(...)");
        f1557a = strG;
    }

    public static final String a(m mVar, v vVar, j jVar, List list) {
        StringBuilder sb = new StringBuilder("\n Id \t Class Name\t Job Id\t State\t Unique Name\t Tags\t");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            p pVar = (p) it.next();
            k kVarS = l0.s(pVar);
            String str = pVar.f1331a;
            l0.h hVarA = jVar.a(kVarS);
            Integer numValueOf = hVarA != null ? Integer.valueOf(hVarA.f1315c) : null;
            mVar.getClass();
            i.e(str, "workSpecId");
            String strY0 = v1.j.y0((List) l3.h.W(mVar.f1325a, true, false, new l0.b(5, str)), ",", null, null, null, 62);
            vVar.getClass();
            sb.append("\n" + str + "\t " + pVar.f1333c + "\t " + numValueOf + "\t " + pVar.f1332b.name() + "\t " + strY0 + "\t " + v1.j.y0((List) l3.h.W(vVar.f1365a, true, false, new l0.b(17, str)), ",", null, null, null, 62) + '\t');
        }
        return sb.toString();
    }
}
