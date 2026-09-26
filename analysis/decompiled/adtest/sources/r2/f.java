package r2;

import java.util.concurrent.ScheduledFuture;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f implements h1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1976a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f1977b;

    public /* synthetic */ f(int i4, Object obj) {
        this.f1976a = i4;
        this.f1977b = obj;
    }

    public final String toString() {
        switch (this.f1976a) {
            case 0:
                return "CancelFutureOnCancel[" + ((ScheduledFuture) this.f1977b) + ']';
            case 1:
                return "CancelHandler.UserSupplied[" + ((i2.l) this.f1977b).getClass().getSimpleName() + '@' + x.k(this) + ']';
            default:
                return "DisposeOnCancel[" + ((g0) this.f1977b) + ']';
        }
    }
}
