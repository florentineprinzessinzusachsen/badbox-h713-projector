package androidx.appcompat.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import java.util.Calendar;

/* JADX INFO: compiled from: TwilightManager.java */
/* JADX INFO: loaded from: classes.dex */
class j {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static j f313d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f314a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final LocationManager f315b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final a f316c = new a();

    /* JADX INFO: compiled from: TwilightManager.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f317a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        long f318b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f319c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        long f320d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        long f321e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        long f322f;

        a() {
        }
    }

    j(Context context, LocationManager locationManager) {
        this.f314a = context;
        this.f315b = locationManager;
    }

    static j a(Context context) {
        if (f313d == null) {
            Context applicationContext = context.getApplicationContext();
            f313d = new j(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return f313d;
    }

    @SuppressLint({"MissingPermission"})
    private Location b() {
        Location locationA = androidx.core.content.b.a(this.f314a, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? a("network") : null;
        Location locationA2 = androidx.core.content.b.a(this.f314a, "android.permission.ACCESS_FINE_LOCATION") == 0 ? a("gps") : null;
        if (locationA2 == null || locationA == null) {
            return locationA2 != null ? locationA2 : locationA;
        }
        return locationA2.getTime() > locationA.getTime() ? locationA2 : locationA;
    }

    private boolean c() {
        return this.f316c.f322f > System.currentTimeMillis();
    }

    boolean a() {
        a aVar = this.f316c;
        if (c()) {
            return aVar.f317a;
        }
        Location locationB = b();
        if (locationB != null) {
            a(locationB);
            return aVar.f317a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i = Calendar.getInstance().get(11);
        return i < 6 || i >= 22;
    }

    private Location a(String str) {
        try {
            if (this.f315b.isProviderEnabled(str)) {
                return this.f315b.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e2) {
            Log.d("TwilightManager", "Failed to get last known location", e2);
            return null;
        }
    }

    private void a(Location location) {
        long j;
        long j2;
        a aVar = this.f316c;
        long jCurrentTimeMillis = System.currentTimeMillis();
        i iVarA = i.a();
        iVarA.a(jCurrentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j3 = iVarA.f310a;
        iVarA.a(jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z = iVarA.f312c == 1;
        long j4 = iVarA.f311b;
        long j5 = iVarA.f310a;
        boolean z2 = z;
        iVarA.a(86400000 + jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        long j6 = iVarA.f311b;
        if (j4 == -1 || j5 == -1) {
            j = 43200000 + jCurrentTimeMillis;
        } else {
            if (jCurrentTimeMillis > j5) {
                j2 = 0 + j6;
            } else {
                j2 = jCurrentTimeMillis > j4 ? 0 + j5 : 0 + j4;
            }
            j = j2 + 60000;
        }
        aVar.f317a = z2;
        aVar.f318b = j3;
        aVar.f319c = j4;
        aVar.f320d = j5;
        aVar.f321e = j6;
        aVar.f322f = j;
    }
}
