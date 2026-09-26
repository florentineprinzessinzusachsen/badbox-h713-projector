package n;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class g extends Service implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a2.f f1472a = new a2.f(this);

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        j2.i.e(intent, "intent");
        a2.f fVar = this.f1472a;
        fVar.getClass();
        fVar.a(c.ON_START);
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        a2.f fVar = this.f1472a;
        fVar.getClass();
        fVar.a(c.ON_CREATE);
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        a2.f fVar = this.f1472a;
        fVar.getClass();
        fVar.a(c.ON_STOP);
        fVar.a(c.ON_DESTROY);
        super.onDestroy();
    }

    @Override // android.app.Service
    public final void onStart(Intent intent, int i4) {
        a2.f fVar = this.f1472a;
        fVar.getClass();
        fVar.a(c.ON_START);
        super.onStart(intent, i4);
    }
}
