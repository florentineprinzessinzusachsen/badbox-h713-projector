package com.ad.proxy.g;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.util.Log;
import com.ad.proxy.Robin;

/* JADX INFO: loaded from: classes.dex */
public abstract class I {
    public static G a;

    public static boolean a(Context context) {
        NetworkCapabilities networkCapabilities;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        try {
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) == null) {
                return false;
            }
            return networkCapabilities.hasTransport(1);
        } catch (Exception e) {
            boolean z = F.a;
            if (Robin.isDebug()) {
                if (F.a) {
                    Log.e("NetworkUtils", "", e);
                } else {
                    e.printStackTrace();
                }
            }
            return true;
        }
    }

    public static void b(Context context) {
        if (a != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                try {
                    connectivityManager.unregisterNetworkCallback(a);
                } catch (Exception unused) {
                }
            }
            a = null;
        }
    }

    public static void a(Context context, H h) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return;
        }
        b(context);
        NetworkRequest networkRequestBuild = new NetworkRequest.Builder().addTransportType(1).build();
        G g = new G(h);
        a = g;
        connectivityManager.registerNetworkCallback(networkRequestBuild, g);
    }
}
