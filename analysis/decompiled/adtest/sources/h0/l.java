package h0;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import d0.a0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class l extends ConnectivityManager.NetworkCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final l f1017a = new l();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f1018b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final LinkedHashMap f1019c = new LinkedHashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static NetworkCapabilities f1020d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f1021e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static boolean f1022f;

    public static void a() {
        int i4;
        ArrayList arrayList = new ArrayList();
        synchronized (f1018b) {
            try {
                Iterator it = f1019c.entrySet().iterator();
                while (true) {
                    i4 = 0;
                    if (!it.hasNext()) {
                        break;
                    }
                    Map.Entry entry = (Map.Entry) it.next();
                    i2.l lVar = (i2.l) entry.getKey();
                    NetworkRequest networkRequest = (NetworkRequest) entry.getValue();
                    l lVar2 = f1017a;
                    NetworkCapabilities networkCapabilities = f1020d;
                    lVar2.getClass();
                    if (!f1022f && networkRequest.canBeSatisfiedBy(networkCapabilities)) {
                        i4 = 1;
                    }
                    arrayList.add(new u1.f(lVar, i4 != 0 ? a.f996a : new b(7)));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        int size = arrayList.size();
        while (i4 < size) {
            Object obj = arrayList.get(i4);
            i4++;
            u1.f fVar = (u1.f) obj;
            ((i2.l) fVar.f2294d).h((c) fVar.f2295e);
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onBlockedStatusChanged(Network network, boolean z3) {
        j2.i.e(network, "network");
        a0.e().a(q.f1032a, "NetworkRequestConstraintController onBlockedStatusChanged callback");
        synchronized (f1018b) {
            if (f1022f == z3) {
                return;
            }
            f1022f = z3;
            a();
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        j2.i.e(network, "network");
        j2.i.e(networkCapabilities, "networkCapabilities");
        a0.e().a(q.f1032a, "NetworkRequestConstraintController onCapabilitiesChanged callback");
        synchronized (f1018b) {
            f1020d = networkCapabilities;
        }
        a();
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onLost(Network network) {
        j2.i.e(network, "network");
        a0.e().a(q.f1032a, "NetworkRequestConstraintController onLost callback");
        synchronized (f1018b) {
            f1020d = null;
            Iterator it = f1019c.keySet().iterator();
            while (it.hasNext()) {
                ((i2.l) it.next()).h(new b(7));
            }
        }
    }
}
