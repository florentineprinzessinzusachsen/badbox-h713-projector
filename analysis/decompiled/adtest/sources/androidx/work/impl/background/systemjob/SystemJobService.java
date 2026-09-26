package androidx.work.impl.background.systemjob;

import android.app.Application;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.os.Looper;
import android.os.PersistableBundle;
import c.d;
import d0.a0;
import d0.i;
import d0.l;
import d0.t;
import e0.b;
import e0.f;
import e0.y;
import g0.g;
import g0.h;
import java.util.Arrays;
import java.util.HashMap;
import l0.k;
import m0.j;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class SystemJobService extends JobService implements b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f324e = a0.g("SystemJobService");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public y f325a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f326b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final i f327c = new i(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c3.b f328d;

    public static void a(String str) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }

    public static k b(JobParameters jobParameters) {
        try {
            PersistableBundle extras = jobParameters.getExtras();
            if (extras == null || !extras.containsKey("EXTRA_WORK_SPEC_ID")) {
                return null;
            }
            return new k(extras.getInt("EXTRA_WORK_SPEC_GENERATION"), extras.getString("EXTRA_WORK_SPEC_ID"));
        } catch (NullPointerException unused) {
            return null;
        }
    }

    @Override // e0.b
    public final void d(k kVar, boolean z3) {
        a("onExecuted");
        a0.e().a(f324e, kVar.f1321a + " executed on JobScheduler");
        JobParameters jobParameters = (JobParameters) this.f326b.remove(kVar);
        this.f327c.c(kVar);
        if (jobParameters != null) {
            jobFinished(jobParameters, z3);
        }
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        try {
            y yVarS = y.S(getApplicationContext());
            this.f325a = yVarS;
            f fVar = yVarS.f697f;
            this.f328d = new c3.b(fVar, yVarS.f695d);
            fVar.a(this);
        } catch (IllegalStateException e4) {
            if (!Application.class.equals(getApplication().getClass())) {
                throw new IllegalStateException("WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().", e4);
            }
            a0.e().h(f324e, "Could not find WorkManager instance; this may be because an auto-backup is in progress. Ignoring JobScheduler commands for now. Please make sure that you are initializing WorkManager if you have manually disabled WorkManagerInitializer.");
        }
    }

    @Override // android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        y yVar = this.f325a;
        if (yVar != null) {
            yVar.f697f.g(this);
        }
    }

    @Override // android.app.job.JobService
    public final boolean onStartJob(JobParameters jobParameters) {
        l lVar;
        a("onStartJob");
        y yVar = this.f325a;
        String str = f324e;
        if (yVar == null) {
            a0.e().a(str, "WorkManager is not initialized; requesting retry.");
            jobFinished(jobParameters, true);
            return false;
        }
        k kVarB = b(jobParameters);
        if (kVarB == null) {
            a0.e().c(str, "WorkSpec id not found!");
            return false;
        }
        HashMap map = this.f326b;
        if (map.containsKey(kVarB)) {
            a0.e().a(str, "Job is already being executed by SystemJobService: " + kVarB);
            return false;
        }
        a0.e().a(str, "onStartJob for " + kVarB);
        map.put(kVarB, jobParameters);
        int i4 = Build.VERSION.SDK_INT;
        if (i4 >= 24) {
            lVar = new l();
            if (g.b(jobParameters) != null) {
                Arrays.asList(g.b(jobParameters));
            }
            if (g.a(jobParameters) != null) {
                Arrays.asList(g.a(jobParameters));
            }
            if (i4 >= 28) {
                d.c(jobParameters);
            }
        } else {
            lVar = null;
        }
        c3.b bVar = this.f328d;
        e0.l lVarE = this.f327c.e(kVarB);
        bVar.getClass();
        ((j) ((a3.l) bVar.f373e).f184e).execute(new t(bVar, lVarE, lVar, 3));
        return true;
    }

    @Override // android.app.job.JobService
    public final boolean onStopJob(JobParameters jobParameters) {
        boolean zContains;
        a("onStopJob");
        if (this.f325a == null) {
            a0.e().a(f324e, "WorkManager is not initialized; requesting retry.");
            return true;
        }
        k kVarB = b(jobParameters);
        if (kVarB == null) {
            a0.e().c(f324e, "WorkSpec id not found!");
            return false;
        }
        a0.e().a(f324e, "onStopJob for " + kVarB);
        this.f326b.remove(kVarB);
        e0.l lVarC = this.f327c.c(kVarB);
        if (lVarC != null) {
            int iA = Build.VERSION.SDK_INT >= 31 ? h.a(jobParameters) : -512;
            c3.b bVar = this.f328d;
            bVar.getClass();
            bVar.j(lVarC, iA);
        }
        f fVar = this.f325a.f697f;
        String str = kVarB.f1321a;
        synchronized (fVar.f622k) {
            zContains = fVar.f620i.contains(str);
        }
        return !zContains;
    }
}
