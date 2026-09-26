package d0;

import androidx.work.Worker;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class n0 implements i2.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f488d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Worker f489e;

    public /* synthetic */ n0(Worker worker, int i4) {
        this.f488d = i4;
        this.f489e = worker;
    }

    @Override // i2.a
    public final Object a() {
        switch (this.f488d) {
            case 0:
                return this.f489e.c();
            default:
                this.f489e.getClass();
                throw new IllegalStateException("Expedited WorkRequests require a Worker to provide an implementation for `getForegroundInfo()`");
        }
    }
}
