package h0;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import d0.a0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d extends ConnectivityManager.NetworkCallback {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ int f998c = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f999a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f1000b;

    public d(j0.i iVar) {
        this.f1000b = iVar;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onBlockedStatusChanged(Network network, boolean z3) {
        switch (this.f999a) {
            case 1:
                j2.i.e(network, "network");
                if (network.equals(((j0.i) this.f1000b).f1226f.getActiveNetwork())) {
                    a0.e().a(j0.j.f1230a, "Network blocked status changed: " + z3);
                    j0.i iVar = (j0.i) this.f1000b;
                    Object objA = iVar.f1224e;
                    if (objA == null) {
                        objA = iVar.a();
                    }
                    h hVar = (h) objA;
                    j0.i iVar2 = (j0.i) this.f1000b;
                    synchronized (iVar2.f1227g) {
                        if (iVar2.f1228h == z3) {
                            return;
                        }
                        iVar2.f1228h = z3;
                        ((j0.i) this.f1000b).b(new h(hVar.f1009a, hVar.f1010b, hVar.f1011c, hVar.f1012d, z3));
                        return;
                    }
                }
                return;
            default:
                super.onBlockedStatusChanged(network, z3);
                return;
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        switch (this.f999a) {
            case 0:
                j2.i.e(network, "network");
                j2.i.e(networkCapabilities, "networkCapabilities");
                a0.e().a(q.f1032a, "NetworkRequestConstraintController onCapabilitiesChanged callback");
                ((e) this.f1000b).h(a.f996a);
                break;
            default:
                j2.i.e(network, "network");
                j2.i.e(networkCapabilities, "capabilities");
                a0.e().a(j0.j.f1230a, "Network capabilities changed: " + networkCapabilities);
                j0.i iVar = (j0.i) this.f1000b;
                iVar.b(Build.VERSION.SDK_INT >= 28 ? j0.j.b(networkCapabilities, iVar.f1228h) : j0.j.a(iVar.f1226f, iVar.f1228h));
                break;
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onLost(Network network) {
        switch (this.f999a) {
            case 0:
                j2.i.e(network, "network");
                a0.e().a(q.f1032a, "NetworkRequestConstraintController onLost callback");
                ((e) this.f1000b).h(new b(7));
                break;
            default:
                j2.i.e(network, "network");
                a0.e().a(j0.j.f1230a, "Network connection lost");
                ((j0.i) this.f1000b).b(new h(false, false, false, false, false));
                break;
        }
    }

    public d(e eVar) {
        this.f1000b = eVar;
    }
}
