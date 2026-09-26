package o0;

import androidx.work.impl.workers.ConstraintTrackingWorker;
import d0.z;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c extends a2.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public z f1545g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public /* synthetic */ Object f1546h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ ConstraintTrackingWorker f1547i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1548j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(ConstraintTrackingWorker constraintTrackingWorker, a2.c cVar) {
        super(cVar);
        this.f1547i = constraintTrackingWorker;
    }

    @Override // a2.a
    public final Object l(Object obj) {
        this.f1546h = obj;
        this.f1548j |= Integer.MIN_VALUE;
        return ConstraintTrackingWorker.e(this.f1547i, this);
    }
}
