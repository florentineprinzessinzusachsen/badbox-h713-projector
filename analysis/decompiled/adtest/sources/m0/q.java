package m0;

import androidx.work.impl.WorkDatabase;
import d0.a0;
import l0.t;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a3.l f1432a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k0.a f1433b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final t f1434c;

    static {
        a0.g("WMFgUpdater");
    }

    public q(WorkDatabase workDatabase, k0.a aVar, a3.l lVar) {
        this.f1433b = aVar;
        this.f1432a = lVar;
        this.f1434c = workDatabase.w();
    }
}
