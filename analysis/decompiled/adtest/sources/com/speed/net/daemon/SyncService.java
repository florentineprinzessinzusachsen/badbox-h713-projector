package com.speed.net.daemon;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import j2.i;
import m1.d;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class SyncService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f391a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static d f392b;

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        d dVar = f392b;
        i.b(dVar);
        IBinder syncAdapterBinder = dVar.getSyncAdapterBinder();
        i.d(syncAdapterBinder, "getSyncAdapterBinder(...)");
        return syncAdapterBinder;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        synchronized (f391a) {
            if (f392b == null) {
                Context applicationContext = getApplicationContext();
                i.d(applicationContext, "getApplicationContext(...)");
                f392b = new d(applicationContext, true);
            }
        }
    }
}
