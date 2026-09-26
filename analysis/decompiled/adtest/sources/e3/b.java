package e3;

import android.net.ConnectivityManager;
import d0.a0;
import d0.l0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements i2.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f706d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f707e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f708f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f709g;

    public /* synthetic */ b(Object obj, Object obj2, Object obj3, int i4) {
        this.f706d = i4;
        this.f707e = obj;
        this.f708f = obj2;
        this.f709g = obj3;
    }

    @Override // i2.a
    public final Object a() {
        switch (this.f706d) {
            case 0:
                a3.e eVar = (a3.e) this.f707e;
                a3.p pVar = (a3.p) this.f708f;
                a3.a aVar = (a3.a) this.f709g;
                l0 l0Var = eVar.f121b;
                j2.i.b(l0Var);
                return l0Var.i(pVar.a(), aVar.f58h.f211d);
            default:
                j2.m mVar = (j2.m) this.f707e;
                ConnectivityManager connectivityManager = (ConnectivityManager) this.f708f;
                h0.d dVar = (h0.d) this.f709g;
                if (mVar.f1275d) {
                    a0.e().a(h0.q.f1032a, "NetworkRequestConstraintController unregister callback");
                    connectivityManager.unregisterNetworkCallback(dVar);
                }
                return u1.k.f2301a;
        }
    }
}
