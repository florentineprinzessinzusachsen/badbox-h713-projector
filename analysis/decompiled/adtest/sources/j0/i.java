package j0;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import d0.a0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class i extends g {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ConnectivityManager f1226f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f1227g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public volatile boolean f1228h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final h0.d f1229i;

    public i(Context context, a3.l lVar) {
        super(context, lVar);
        Object systemService = this.f1221b.getSystemService("connectivity");
        j2.i.c(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        this.f1226f = (ConnectivityManager) systemService;
        this.f1227g = new Object();
        this.f1229i = new h0.d(this);
    }

    @Override // j0.g
    public final Object a() {
        if (Build.VERSION.SDK_INT >= 28) {
            ConnectivityManager connectivityManager = this.f1226f;
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null) {
                return j.b(networkCapabilities, this.f1228h);
            }
        }
        return j.a(this.f1226f, this.f1228h);
    }

    @Override // j0.g
    public final void c() {
        try {
            a0.e().a(j.f1230a, "Registering network callback");
            ConnectivityManager connectivityManager = this.f1226f;
            h0.d dVar = this.f1229i;
            j2.i.e(connectivityManager, "<this>");
            j2.i.e(dVar, "networkCallback");
            connectivityManager.registerDefaultNetworkCallback(dVar);
        } catch (IllegalArgumentException e4) {
            a0.e().d(j.f1230a, "Received exception while registering network callback", e4);
        } catch (SecurityException e5) {
            a0.e().d(j.f1230a, "Received exception while registering network callback", e5);
        }
    }

    @Override // j0.g
    public final void d() {
        try {
            a0.e().a(j.f1230a, "Unregistering network callback");
            this.f1226f.unregisterNetworkCallback(this.f1229i);
        } catch (IllegalArgumentException e4) {
            a0.e().d(j.f1230a, "Received exception while unregistering network callback", e4);
        } catch (SecurityException e5) {
            a0.e().d(j.f1230a, "Received exception while unregistering network callback", e5);
        }
    }
}
