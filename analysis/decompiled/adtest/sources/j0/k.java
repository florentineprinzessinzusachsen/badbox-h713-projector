package j0;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import d0.a0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class k extends e {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ConnectivityManager f1231g;

    public k(Context context, a3.l lVar) {
        super(context, lVar);
        Object systemService = this.f1221b.getSystemService("connectivity");
        j2.i.c(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        this.f1231g = (ConnectivityManager) systemService;
    }

    @Override // j0.g
    public final Object a() {
        return j.a(this.f1231g, false);
    }

    @Override // j0.e
    public final IntentFilter e() {
        return new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    }

    @Override // j0.e
    public final void f(Intent intent) {
        if (j2.i.a(intent.getAction(), "android.net.conn.CONNECTIVITY_CHANGE")) {
            a0.e().a(j.f1230a, "Network broadcast received");
            b(j.a(this.f1231g, false));
        }
    }
}
