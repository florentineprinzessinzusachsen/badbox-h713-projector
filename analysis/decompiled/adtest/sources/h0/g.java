package h0;

import android.net.ConnectivityManager;
import d0.u;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class g implements i0.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ConnectivityManager f1008a;

    public g(ConnectivityManager connectivityManager) {
        this.f1008a = connectivityManager;
    }

    @Override // i0.d
    public final boolean a(l0.p pVar) {
        j2.i.e(pVar, "workSpec");
        return pVar.f1340j.a() != null;
    }

    @Override // i0.d
    public final boolean b(l0.p pVar) {
        if (a(pVar)) {
            throw new IllegalStateException("isCurrentlyConstrained() must never be called onNetworkRequestConstraintController. isCurrentlyConstrained() is called only on older platforms where NetworkRequest isn't supported");
        }
        return false;
    }

    @Override // i0.d
    public final u2.c c(d0.e eVar) {
        j2.i.e(eVar, "constraints");
        return new u2.c(new u(eVar, this, null, 2), y1.i.f2726d, -2, t2.a.f2174d);
    }
}
