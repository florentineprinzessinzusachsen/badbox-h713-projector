package o0;

import androidx.work.impl.workers.ConstraintTrackingWorker;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b extends a2.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public /* synthetic */ Object f1542g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ ConstraintTrackingWorker f1543h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1544i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(ConstraintTrackingWorker constraintTrackingWorker, a2.c cVar) {
        super(cVar);
        this.f1543h = constraintTrackingWorker;
    }

    @Override // a2.a
    public final Object l(Object obj) {
        this.f1542g = obj;
        this.f1544i |= Integer.MIN_VALUE;
        return ConstraintTrackingWorker.d(this.f1543h, null, null, null, this);
    }
}
