package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.utils.UMUtils;

/* JADX INFO: compiled from: UMSysLocation.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3948a = "UMSysLocation";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f3949c = 10000;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private LocationManager f3950b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f3951d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private d f3952e;

    private b() {
    }

    /* JADX WARN: Code duplicated, block: B:35:0x0091  */
    public synchronized void a(d dVar) {
        boolean zIsProviderEnabled;
        boolean zIsProviderEnabled2;
        Location lastKnownLocation;
        ULog.i(f3948a, "getSystemLocation");
        if (dVar != null && this.f3951d != null) {
            this.f3952e = dVar;
            boolean zCheckPermission = UMUtils.checkPermission(this.f3951d, "android.permission.ACCESS_COARSE_LOCATION");
            boolean zCheckPermission2 = UMUtils.checkPermission(this.f3951d, "android.permission.ACCESS_FINE_LOCATION");
            if (!zCheckPermission && !zCheckPermission2) {
                if (this.f3952e != null) {
                    this.f3952e.a(null);
                }
                return;
            }
            try {
                if (this.f3950b != null) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        zIsProviderEnabled = this.f3950b.isProviderEnabled("gps");
                        zIsProviderEnabled2 = this.f3950b.isProviderEnabled("network");
                    } else {
                        zIsProviderEnabled = zCheckPermission2 ? this.f3950b.isProviderEnabled("gps") : false;
                        zIsProviderEnabled2 = zCheckPermission ? this.f3950b.isProviderEnabled("network") : false;
                    }
                    if (zIsProviderEnabled || zIsProviderEnabled2) {
                        ULog.i(f3948a, "getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)");
                        if (zCheckPermission2) {
                            lastKnownLocation = this.f3950b.getLastKnownLocation("passive");
                        } else if (zCheckPermission) {
                            lastKnownLocation = this.f3950b.getLastKnownLocation("network");
                        } else {
                            lastKnownLocation = null;
                        }
                    } else {
                        lastKnownLocation = null;
                    }
                    this.f3952e.a(lastKnownLocation);
                }
            } catch (Throwable th) {
                ULog.i(f3948a, "e is " + th);
                if (dVar != null) {
                    try {
                        dVar.a(null);
                    } catch (Throwable th2) {
                        UMCrashManager.reportCrash(this.f3951d, th2);
                    }
                }
                UMCrashManager.reportCrash(this.f3951d, th);
            }
        }
    }

    public b(Context context) {
        if (context == null) {
            MLog.e("Context参数不能为null");
        } else {
            this.f3951d = context.getApplicationContext();
            this.f3950b = (LocationManager) context.getApplicationContext().getSystemService("location");
        }
    }

    public synchronized void a() {
        ULog.i(f3948a, "destroy");
        try {
            if (this.f3950b != null) {
                this.f3950b = null;
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.f3951d, th);
        }
    }
}
