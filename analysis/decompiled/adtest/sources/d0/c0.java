package d0;

import android.os.Build;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public UUID f426a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public l0.p f427b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LinkedHashSet f428c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f429d;

    public c0(Class cls, int i4) {
        this.f429d = i4;
        UUID uuidRandomUUID = UUID.randomUUID();
        j2.i.d(uuidRandomUUID, "randomUUID(...)");
        this.f426a = uuidRandomUUID;
        String string = this.f426a.toString();
        j2.i.d(string, "toString(...)");
        this.f427b = new l0.p(string, (k0) null, cls.getName(), (String) null, (j) null, (j) null, 0L, 0L, 0L, (e) null, 0, (a) null, 0L, 0L, 0L, 0L, false, (i0) null, 0, 0L, 0, 0, (String) null, (Boolean) null, 33554426);
        String[] strArr = {cls.getName()};
        LinkedHashSet linkedHashSet = new LinkedHashSet(v1.t.J(1));
        linkedHashSet.add(strArr[0]);
        this.f428c = linkedHashSet;
    }

    public final m0 a() {
        m0 d0Var;
        switch (this.f429d) {
            case 0:
                d0Var = new d0(this.f426a, this.f427b, this.f428c);
                break;
            default:
                l0.p pVar = this.f427b;
                if (pVar.f1347q) {
                    throw new IllegalArgumentException("PeriodicWorkRequests cannot be expedited");
                }
                d0Var = new j0(this.f426a, pVar, this.f428c);
                break;
        }
        e eVar = this.f427b.f1340j;
        boolean z3 = (Build.VERSION.SDK_INT >= 24 && eVar.b()) || eVar.f437e || eVar.f435c || eVar.f436d;
        l0.p pVar2 = this.f427b;
        if (pVar2.f1347q) {
            if (z3) {
                throw new IllegalArgumentException("Expedited jobs only support network and storage constraints");
            }
            if (pVar2.f1337g > 0) {
                throw new IllegalArgumentException("Expedited jobs cannot be delayed");
            }
        }
        String str = pVar2.f1354x;
        if (str == null) {
            List listO0 = p2.i.O0(pVar2.f1333c, new String[]{"."}, 6);
            String strR0 = listO0.size() == 1 ? (String) listO0.get(0) : (String) v1.j.z0(listO0);
            if (strR0.length() > 127) {
                strR0 = p2.i.R0(127, strR0);
            }
            pVar2.f1354x = strR0;
        } else if (str.length() > 127) {
            this.f427b.f1354x = p2.i.R0(127, str);
        }
        UUID uuidRandomUUID = UUID.randomUUID();
        j2.i.d(uuidRandomUUID, "randomUUID(...)");
        this.f426a = uuidRandomUUID;
        String string = uuidRandomUUID.toString();
        j2.i.d(string, "toString(...)");
        l0.p pVar3 = this.f427b;
        j2.i.e(pVar3, "other");
        m0 m0Var = d0Var;
        this.f427b = new l0.p(string, pVar3.f1332b, pVar3.f1333c, pVar3.f1334d, new j(pVar3.f1335e), new j(pVar3.f1336f), pVar3.f1337g, pVar3.f1338h, pVar3.f1339i, new e(pVar3.f1340j), pVar3.f1341k, pVar3.f1342l, pVar3.f1343m, pVar3.f1344n, pVar3.f1345o, pVar3.f1346p, pVar3.f1347q, pVar3.f1348r, pVar3.f1349s, pVar3.f1351u, pVar3.f1352v, pVar3.f1353w, pVar3.f1354x, pVar3.f1355y, 524288);
        return m0Var;
    }
}
