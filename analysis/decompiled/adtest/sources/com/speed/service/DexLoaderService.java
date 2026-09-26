package com.speed.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import d0.u;
import l3.h;
import r2.e0;
import r2.k1;
import r2.x;
import t1.o;
import w2.c;
import y2.d;
import y2.e;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class DexLoaderService extends Service {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ int f393c = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f394a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Boolean f395b;

    public DexLoaderService() {
        k1 k1VarC = x.c();
        e eVar = e0.f1974a;
        this.f394a = x.a(h.Y(k1VarC, d.f2753f));
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        x.e(this.f394a);
        h.a0("DexLoaderService 销毁，调度重启");
        o.f2158a.a("dexloader_destroy", "DexLoaderService 销毁，调度重启");
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i4, int i5) {
        x.p(this.f394a, null, null, new u(this, intent, null, 6), 3);
        return 1;
    }
}
