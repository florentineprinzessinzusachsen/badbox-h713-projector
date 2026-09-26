package com.speed.net.daemon;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import j2.i;
import m1.c;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class StubAuthenticatorService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c f390a;

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        c cVar = this.f390a;
        if (cVar == null) {
            i.h("authenticator");
            throw null;
        }
        IBinder iBinder = cVar.getIBinder();
        i.d(iBinder, "getIBinder(...)");
        return iBinder;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        this.f390a = new c(this);
    }
}
