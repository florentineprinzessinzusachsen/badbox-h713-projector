package m0;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import androidx.work.impl.foreground.SystemForegroundService;
import d0.a0;
import d0.l0;
import e0.k0;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p implements i2.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ q f1428d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ UUID f1429e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ d0.o f1430f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Context f1431g;

    public /* synthetic */ p(q qVar, UUID uuid, d0.o oVar, Context context) {
        this.f1428d = qVar;
        this.f1429e = uuid;
        this.f1430f = oVar;
        this.f1431g = context;
    }

    @Override // i2.a
    public final Object a() {
        q qVar = this.f1428d;
        UUID uuid = this.f1429e;
        d0.o oVar = this.f1430f;
        Context context = this.f1431g;
        String string = uuid.toString();
        l0.p pVarC = qVar.f1434c.c(string);
        if (pVarC == null || pVarC.f1332b.a()) {
            throw new IllegalStateException("Calls to setForegroundAsync() must complete before a ListenableWorker signals completion of work by returning an instance of Result.");
        }
        e0.f fVar = (e0.f) qVar.f1433b;
        synchronized (fVar.f622k) {
            try {
                a0.e().f(e0.f.f611l, "Moving WorkSpec (" + string + ") to the foreground");
                k0 k0Var = (k0) fVar.f618g.remove(string);
                if (k0Var != null) {
                    if (fVar.f612a == null) {
                        PowerManager.WakeLock wakeLockA = l.a(fVar.f613b);
                        fVar.f612a = wakeLockA;
                        wakeLockA.acquire();
                    }
                    fVar.f617f.put(string, k0Var);
                    l0.J(fVar.f613b, k0.b.a(fVar.f613b, l0.s(k0Var.f643a), oVar));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        l0.k kVarS = l0.s(pVarC);
        String str = k0.b.f1280j;
        Intent intent = new Intent(context, (Class<?>) SystemForegroundService.class);
        intent.setAction("ACTION_NOTIFY");
        intent.putExtra("KEY_NOTIFICATION_ID", oVar.f490a);
        intent.putExtra("KEY_FOREGROUND_SERVICE_TYPE", oVar.f491b);
        intent.putExtra("KEY_NOTIFICATION", oVar.f492c);
        intent.putExtra("KEY_WORKSPEC_ID", kVarS.f1321a);
        intent.putExtra("KEY_GENERATION", kVarS.f1322b);
        context.startService(intent);
        return null;
    }
}
