package androidx.work.impl.foreground;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import d0.a0;
import d0.l;
import e0.y;
import f0.a;
import h0.k;
import j2.i;
import java.util.UUID;
import k0.b;
import l3.h;
import m0.j;
import n.g;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class SystemForegroundService extends g {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f330e = a0.g("SystemFgService");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f331b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b f332c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public NotificationManager f333d;

    public final void a() {
        this.f333d = (NotificationManager) getApplicationContext().getSystemService("notification");
        b bVar = new b(getApplicationContext());
        this.f332c = bVar;
        if (bVar.f1289i != null) {
            a0.e().c(b.f1280j, "A callback already exists.");
        } else {
            bVar.f1289i = this;
        }
    }

    @Override // n.g, android.app.Service
    public final void onCreate() {
        super.onCreate();
        a();
    }

    @Override // n.g, android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        this.f332c.e();
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i4, int i5) {
        super.onStartCommand(intent, i4, i5);
        boolean z3 = this.f331b;
        String str = f330e;
        if (z3) {
            a0.e().f(str, "Re-initializing SystemForegroundService after a request to shut-down.");
            this.f332c.e();
            a();
            this.f331b = false;
        }
        if (intent == null) {
            return 3;
        }
        b bVar = this.f332c;
        bVar.getClass();
        String str2 = b.f1280j;
        String action = intent.getAction();
        if ("ACTION_START_FOREGROUND".equals(action)) {
            a0.e().f(str2, "Started foreground service " + intent);
            String stringExtra = intent.getStringExtra("KEY_WORKSPEC_ID");
            ((j) bVar.f1282b.f184e).execute(new a(1, bVar, stringExtra));
            bVar.c(intent);
            return 3;
        }
        if ("ACTION_NOTIFY".equals(action)) {
            bVar.c(intent);
            return 3;
        }
        if (!"ACTION_CANCEL_WORK".equals(action)) {
            if (!"ACTION_STOP_FOREGROUND".equals(action)) {
                return 3;
            }
            a0.e().f(str2, "Stopping foreground service");
            SystemForegroundService systemForegroundService = bVar.f1289i;
            if (systemForegroundService == null) {
                return 3;
            }
            systemForegroundService.f331b = true;
            a0.e().a(str, "Shutting down.");
            if (Build.VERSION.SDK_INT >= 26) {
                systemForegroundService.stopForeground(true);
            }
            systemForegroundService.stopSelf(i5);
            return 3;
        }
        a0.e().f(str2, "Stopping foreground work for " + intent);
        String stringExtra2 = intent.getStringExtra("KEY_WORKSPEC_ID");
        if (stringExtra2 == null || TextUtils.isEmpty(stringExtra2)) {
            return 3;
        }
        y yVar = bVar.f1281a;
        UUID uuidFromString = UUID.fromString(stringExtra2);
        yVar.getClass();
        i.e(uuidFromString, "id");
        l lVar = yVar.f693b.f416m;
        j jVar = (j) yVar.f695d.f184e;
        i.d(jVar, "getSerialTaskExecutor(...)");
        h.R(lVar, "CancelWorkById", jVar, new k(5, yVar, uuidFromString));
        return 3;
    }

    @Override // android.app.Service
    public final void onTimeout(int i4) {
        if (Build.VERSION.SDK_INT >= 35) {
            return;
        }
        this.f332c.f(i4, 2048);
    }

    public final void onTimeout(int i4, int i5) {
        this.f332c.f(i4, i5);
    }
}
