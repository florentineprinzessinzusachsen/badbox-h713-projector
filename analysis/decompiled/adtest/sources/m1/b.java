package m1;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import d0.l0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b implements ServiceConnection {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Service f1439a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Intent f1440b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Class f1441c;

    public b(Service service, Intent intent, Class cls) {
        this.f1439a = service;
        this.f1440b = intent;
        this.f1441c = cls;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(final ComponentName componentName, IBinder iBinder) {
        Log.i("DaemonKeeper", "Successfully bound to guard target: " + componentName);
        if (iBinder != null) {
            try {
                final Service service = this.f1439a;
                final Intent intent = this.f1440b;
                final Class cls = this.f1441c;
                iBinder.linkToDeath(new IBinder.DeathRecipient() { // from class: m1.a
                    @Override // android.os.IBinder.DeathRecipient
                    public final void binderDied() {
                        Service service2 = service;
                        Log.e("DaemonKeeper", "Guard target died: " + componentName);
                        try {
                            int i4 = Build.VERSION.SDK_INT;
                            Intent intent2 = intent;
                            if (i4 >= 26) {
                                l0.J(service2, intent2);
                            } else {
                                service2.startService(intent2);
                            }
                        } catch (Exception e4) {
                            Log.e("DaemonKeeper", "Resurrection failed: " + e4.getMessage());
                        }
                        Class cls2 = cls;
                        Intent intent3 = new Intent(service2, (Class<?>) cls2);
                        try {
                            if (Build.VERSION.SDK_INT >= 26) {
                                l0.J(service2, intent3);
                            } else {
                                service2.startService(intent3);
                            }
                        } catch (Exception e5) {
                            Log.e("DaemonKeeper", "Failed to start target service pre-bind: " + e5.getMessage());
                        }
                        service2.bindService(intent3, new b(service2, intent3, cls2), 1);
                    }
                }, 0);
            } catch (Exception e4) {
                Log.e("DaemonKeeper", "linkToDeath failed", e4);
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        Log.w("DaemonKeeper", "Disconnected from guard target: " + componentName);
    }
}
