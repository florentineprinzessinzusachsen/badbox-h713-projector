package k0;

import a3.l;
import a3.q;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.work.impl.foreground.SystemForegroundService;
import d0.a0;
import d0.l0;
import d0.o;
import e0.y;
import h0.i;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import l0.k;
import l0.p;
import m0.j;
import r2.v0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b implements i, e0.b {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f1280j = a0.g("SystemFgDispatcher");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y f1281a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final l f1282b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f1283c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public k f1284d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LinkedHashMap f1285e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final HashMap f1286f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final HashMap f1287g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final q f1288h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public SystemForegroundService f1289i;

    public b(Context context) {
        y yVarS = y.S(context);
        this.f1281a = yVarS;
        this.f1282b = yVarS.f695d;
        this.f1284d = null;
        this.f1285e = new LinkedHashMap();
        this.f1287g = new HashMap();
        this.f1286f = new HashMap();
        this.f1288h = new q(yVarS.f701j);
        yVarS.f697f.a(this);
    }

    public static Intent a(Context context, k kVar, o oVar) {
        Intent intent = new Intent(context, (Class<?>) SystemForegroundService.class);
        intent.setAction("ACTION_START_FOREGROUND");
        intent.putExtra("KEY_WORKSPEC_ID", kVar.f1321a);
        intent.putExtra("KEY_GENERATION", kVar.f1322b);
        intent.putExtra("KEY_NOTIFICATION_ID", oVar.f490a);
        intent.putExtra("KEY_FOREGROUND_SERVICE_TYPE", oVar.f491b);
        intent.putExtra("KEY_NOTIFICATION", oVar.f492c);
        return intent;
    }

    @Override // h0.i
    public final void b(p pVar, h0.c cVar) {
        if (cVar instanceof h0.b) {
            String str = pVar.f1331a;
            a0.e().a(f1280j, "Constraints unmet for WorkSpec " + str);
            k kVarS = l0.s(pVar);
            int i4 = ((h0.b) cVar).f997a;
            y yVar = this.f1281a;
            ((j) yVar.f695d.f184e).execute(new m0.k(yVar.f697f, new e0.l(kVarS), true, i4));
        }
    }

    public final void c(Intent intent) {
        if (this.f1289i == null) {
            throw new IllegalStateException("handleNotify was called on the destroyed dispatcher");
        }
        int i4 = 0;
        int intExtra = intent.getIntExtra("KEY_NOTIFICATION_ID", 0);
        int intExtra2 = intent.getIntExtra("KEY_FOREGROUND_SERVICE_TYPE", 0);
        String stringExtra = intent.getStringExtra("KEY_WORKSPEC_ID");
        k kVar = new k(intent.getIntExtra("KEY_GENERATION", 0), stringExtra);
        Notification notification = (Notification) intent.getParcelableExtra("KEY_NOTIFICATION");
        a0.e().a(f1280j, "Notifying with (id:" + intExtra + ", workSpecId: " + stringExtra + ", notificationType :" + intExtra2 + ")");
        if (notification == null) {
            throw new IllegalArgumentException("Notification passed in the intent was null.");
        }
        o oVar = new o(intExtra, notification, intExtra2);
        LinkedHashMap linkedHashMap = this.f1285e;
        linkedHashMap.put(kVar, oVar);
        o oVar2 = (o) linkedHashMap.get(this.f1284d);
        if (oVar2 == null) {
            this.f1284d = kVar;
        } else {
            this.f1289i.f333d.notify(intExtra, notification);
            if (Build.VERSION.SDK_INT >= 29) {
                Iterator it = linkedHashMap.entrySet().iterator();
                while (it.hasNext()) {
                    i4 |= ((o) ((Map.Entry) it.next()).getValue()).f491b;
                }
                oVar = new o(oVar2.f490a, oVar2.f492c, i4);
            } else {
                oVar = oVar2;
            }
        }
        SystemForegroundService systemForegroundService = this.f1289i;
        int i5 = oVar.f490a;
        int i6 = oVar.f491b;
        Notification notification2 = oVar.f492c;
        systemForegroundService.getClass();
        int i7 = Build.VERSION.SDK_INT;
        if (i7 >= 31) {
            c.b(systemForegroundService, i5, notification2, i6);
        } else if (i7 >= 29) {
            c.a(systemForegroundService, i5, notification2, i6);
        } else {
            systemForegroundService.startForeground(i5, notification2);
        }
    }

    @Override // e0.b
    public final void d(k kVar, boolean z3) {
        Map.Entry entry;
        synchronized (this.f1283c) {
            try {
                v0 v0Var = ((p) this.f1286f.remove(kVar)) != null ? (v0) this.f1287g.remove(kVar) : null;
                if (v0Var != null) {
                    v0Var.b(null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        o oVar = (o) this.f1285e.remove(kVar);
        if (kVar.equals(this.f1284d)) {
            if (this.f1285e.size() > 0) {
                Iterator it = this.f1285e.entrySet().iterator();
                Object next = it.next();
                while (true) {
                    entry = (Map.Entry) next;
                    if (!it.hasNext()) {
                        break;
                    } else {
                        next = it.next();
                    }
                }
                this.f1284d = (k) entry.getKey();
                if (this.f1289i != null) {
                    o oVar2 = (o) entry.getValue();
                    SystemForegroundService systemForegroundService = this.f1289i;
                    int i4 = oVar2.f490a;
                    int i5 = oVar2.f491b;
                    Notification notification = oVar2.f492c;
                    systemForegroundService.getClass();
                    int i6 = Build.VERSION.SDK_INT;
                    if (i6 >= 31) {
                        c.b(systemForegroundService, i4, notification, i5);
                    } else if (i6 >= 29) {
                        c.a(systemForegroundService, i4, notification, i5);
                    } else {
                        systemForegroundService.startForeground(i4, notification);
                    }
                    this.f1289i.f333d.cancel(oVar2.f490a);
                }
            } else {
                this.f1284d = null;
            }
        }
        SystemForegroundService systemForegroundService2 = this.f1289i;
        if (oVar == null || systemForegroundService2 == null) {
            return;
        }
        a0.e().a(f1280j, "Removing Notification (id: " + oVar.f490a + ", workSpecId: " + kVar + ", notificationType: " + oVar.f491b);
        systemForegroundService2.f333d.cancel(oVar.f490a);
    }

    public final void e() {
        this.f1289i = null;
        synchronized (this.f1283c) {
            try {
                Iterator it = this.f1287g.values().iterator();
                while (it.hasNext()) {
                    ((v0) it.next()).b(null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.f1281a.f697f.g(this);
    }

    public final void f(int i4, int i5) {
        a0.e().f(f1280j, "Foreground service timed out, FGS type: " + i5);
        for (Map.Entry entry : this.f1285e.entrySet()) {
            if (((o) entry.getValue()).f491b == i5) {
                k kVar = (k) entry.getKey();
                y yVar = this.f1281a;
                ((j) yVar.f695d.f184e).execute(new m0.k(yVar.f697f, new e0.l(kVar), true, -128));
            }
        }
        SystemForegroundService systemForegroundService = this.f1289i;
        if (systemForegroundService != null) {
            systemForegroundService.f331b = true;
            a0.e().a(SystemForegroundService.f330e, "Shutting down.");
            if (Build.VERSION.SDK_INT >= 26) {
                systemForegroundService.stopForeground(true);
            }
            systemForegroundService.stopSelf(i4);
        }
    }
}
