package h0;

import d0.a0;
import d0.u;
import r2.j1;
import r2.s;
import r2.x;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1032a;

    static {
        String strG = a0.g("WorkConstraintsTracker");
        j2.i.d(strG, "tagWithPrefix(...)");
        f1032a = strG;
    }

    public static final j1 a(a3.q qVar, l0.p pVar, s sVar, i iVar) {
        j2.i.e(qVar, "<this>");
        j2.i.e(sVar, "dispatcher");
        j2.i.e(iVar, "listener");
        return x.p(x.a(sVar), null, null, new u(qVar, pVar, iVar, null, 3), 3);
    }
}
