package j0;

import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import d0.a0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1230a;

    static {
        String strG = a0.g("NetworkStateTracker");
        j2.i.d(strG, "tagWithPrefix(...)");
        f1230a = strG;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x001b  */
    /* JADX WARN: Code duplicated, block: B:14:0x0027 A[Catch: SecurityException -> 0x002e, TRY_LEAVE, TryCatch #3 {SecurityException -> 0x002e, blocks: (B:12:0x001d, B:14:0x0027), top: B:42:0x001d, outer: #2 }] */
    /* JADX WARN: Code duplicated, block: B:18:0x0030  */
    /* JADX WARN: Code duplicated, block: B:25:0x004a  */
    public static final h0.h a(ConnectivityManager connectivityManager, boolean z3) {
        boolean z4;
        SecurityException securityException;
        boolean zHasCapability;
        boolean z5;
        NetworkCapabilities networkCapabilities;
        String str = f1230a;
        j2.i.e(connectivityManager, "connectivityManager");
        try {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            boolean z6 = true;
            if (activeNetworkInfo == null) {
                z6 = false;
                networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (networkCapabilities != null) {
                    zHasCapability = networkCapabilities.hasCapability(16);
                } else {
                    zHasCapability = false;
                }
                boolean zA = k.a.a(connectivityManager);
                if (activeNetworkInfo != null) {
                    z5 = false;
                } else {
                    z5 = false;
                }
                z4 = z3;
                return new h0.h(z6, zHasCapability, zA, z5, z4);
            }
            try {
                if (!activeNetworkInfo.isConnected()) {
                    z6 = false;
                }
                try {
                    networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                    if (networkCapabilities != null) {
                        zHasCapability = networkCapabilities.hasCapability(16);
                    } else {
                        zHasCapability = false;
                    }
                } catch (SecurityException e4) {
                    a0.e().d(str, "Unable to validate active network", e4);
                }
                boolean zA2 = k.a.a(connectivityManager);
                if (activeNetworkInfo != null || activeNetworkInfo.isRoaming()) {
                    z5 = false;
                } else {
                    z5 = true;
                }
                z4 = z3;
                try {
                    return new h0.h(z6, zHasCapability, zA2, z5, z4);
                } catch (SecurityException e5) {
                    e = e5;
                    securityException = e;
                    a0.e().d(str, "Unable to get active network state", securityException);
                    return new h0.h(false, false, false, true, z4);
                }
            } catch (SecurityException e6) {
                securityException = e6;
                z4 = z3;
            }
        } catch (SecurityException e7) {
            e = e7;
            z4 = z3;
        }
        a0.e().d(str, "Unable to get active network state", securityException);
        return new h0.h(false, false, false, true, z4);
    }

    public static final h0.h b(NetworkCapabilities networkCapabilities, boolean z3) {
        return new h0.h(networkCapabilities.hasCapability(12), networkCapabilities.hasCapability(16), !networkCapabilities.hasCapability(11), networkCapabilities.hasCapability(18), z3);
    }
}
