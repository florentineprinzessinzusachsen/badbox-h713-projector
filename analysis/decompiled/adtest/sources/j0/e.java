package j0;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import d0.a0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class e extends g {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final d f1218f;

    public e(Context context, a3.l lVar) {
        super(context, lVar);
        this.f1218f = new d(this);
    }

    @Override // j0.g
    public final void c() {
        a0.e().a(f.f1219a, getClass().getSimpleName().concat(": registering receiver"));
        this.f1221b.registerReceiver(this.f1218f, e());
    }

    @Override // j0.g
    public final void d() {
        a0.e().a(f.f1219a, getClass().getSimpleName().concat(": unregistering receiver"));
        this.f1221b.unregisterReceiver(this.f1218f);
    }

    public abstract IntentFilter e();

    public abstract void f(Intent intent);
}
