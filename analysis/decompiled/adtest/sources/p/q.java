package p;

import android.content.Context;
import androidx.work.impl.WorkDatabase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f1691b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f1692c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Executor f1695f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Executor f1696g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public e0.r f1697h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1698i;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1706q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1707r;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f1693d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f1694e = new ArrayList();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final r f1699j = r.f1709d;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final long f1700k = -1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final d0.i f1701l = new d0.i(2);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final LinkedHashSet f1702m = new LinkedHashSet();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final LinkedHashSet f1703n = new LinkedHashSet();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final ArrayList f1704o = new ArrayList();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f1705p = true;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final boolean f1708s = true;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j2.e f1690a = j2.o.a(WorkDatabase.class);

    public q(Context context, String str) {
        this.f1691b = context;
        this.f1692c = str;
    }

    public final void a(t.a... aVarArr) {
        for (t.a aVar : aVarArr) {
            Integer numValueOf = Integer.valueOf(aVar.f2149a);
            LinkedHashSet linkedHashSet = this.f1703n;
            linkedHashSet.add(numValueOf);
            linkedHashSet.add(Integer.valueOf(aVar.f2150b));
        }
        t.a[] aVarArr2 = (t.a[]) Arrays.copyOf(aVarArr, aVarArr.length);
        d0.i iVar = this.f1701l;
        iVar.getClass();
        j2.i.e(aVarArr2, "migrations");
        for (t.a aVar2 : aVarArr2) {
            iVar.a(aVar2);
        }
    }
}
