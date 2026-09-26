package com.ad.proxy.g;

import android.net.ConnectivityManager;
import android.net.Network;
import com.ad.proxy.Robin;

/* JADX INFO: loaded from: classes.dex */
public final class G extends ConnectivityManager.NetworkCallback {
    public final /* synthetic */ H a;

    public G(H h) {
        this.a = h;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onAvailable(Network network) {
        ((com.ad.proxy.a.A) this.a).getClass();
        F.a("Robin", "onWifiConnected");
        if (Robin.isStarted) {
            Robin.startActiveReporter();
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onLost(Network network) {
        ((com.ad.proxy.a.A) this.a).getClass();
        F.a("Robin", "onWifiDisconnected");
        if (Robin.isForceWifi) {
            Robin.stopActiveReporter();
        }
    }
}
