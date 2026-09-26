package m0;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.ApplicationExitInfo;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteAccessPermException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteDiskIOException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.database.sqlite.SQLiteTableLockedException;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.utils.ForceStopRunnable$BroadcastReceiver;
import d0.a0;
import d0.k0;
import e0.y;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import l0.t;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c implements Runnable {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f1396h = a0.g("ForceStopRunnable");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final long f1397i = TimeUnit.DAYS.toMillis(3650);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Context f1398d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final y f1399e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final e f1400f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1401g = 0;

    public c(Context context, y yVar) {
        this.f1398d = context.getApplicationContext();
        this.f1399e = yVar;
        this.f1400f = yVar.f698g;
    }

    public static void c(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        int i4 = Build.VERSION.SDK_INT >= 31 ? 167772160 : 134217728;
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, (Class<?>) ForceStopRunnable$BroadcastReceiver.class));
        intent.setAction("ACTION_FORCE_STOP_RESCHEDULE");
        PendingIntent broadcast = PendingIntent.getBroadcast(context, -1, intent, i4);
        long jCurrentTimeMillis = System.currentTimeMillis() + f1397i;
        if (alarmManager != null) {
            alarmManager.setExact(0, jCurrentTimeMillis, broadcast);
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x01fb  */
    /* JADX WARN: Code duplicated, block: B:123:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:96:0x01e3  */
    public final void a() {
        boolean z3;
        e eVar = this.f1400f;
        y yVar = this.f1399e;
        WorkDatabase workDatabase = yVar.f694c;
        d0.b bVar = yVar.f693b;
        e eVar2 = yVar.f698g;
        WorkDatabase workDatabase2 = yVar.f694c;
        String str = g0.f.f951f;
        Context context = this.f1398d;
        JobScheduler jobSchedulerB = g0.b.b(context);
        ArrayList arrayListF = g0.f.f(context, jobSchedulerB);
        List list = (List) l3.h.W(workDatabase.t().f1319a, true, false, new d0.h(6));
        HashSet hashSet = new HashSet(arrayListF != null ? arrayListF.size() : 0);
        if (arrayListF != null && !arrayListF.isEmpty()) {
            int size = arrayListF.size();
            int i4 = 0;
            while (i4 < size) {
                Object obj = arrayListF.get(i4);
                i4++;
                JobInfo jobInfo = (JobInfo) obj;
                l0.k kVarG = g0.f.g(jobInfo);
                if (kVarG != null) {
                    hashSet.add(kVarG.f1321a);
                } else {
                    g0.f.b(jobSchedulerB, jobInfo.getId());
                }
            }
        }
        Iterator it = list.iterator();
        while (true) {
            if (it.hasNext()) {
                if (!hashSet.contains((String) it.next())) {
                    a0.e().a(g0.f.f951f, "Reconciling jobs");
                    z3 = true;
                    break;
                }
            } else {
                z3 = false;
                break;
            }
        }
        if (z3) {
            workDatabase.b();
            try {
                t tVarW = workDatabase.w();
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    tVarW.e((String) it2.next(), -1L);
                }
                workDatabase.p();
                workDatabase.l();
            } catch (Throwable th) {
                workDatabase.l();
                throw th;
            }
        }
        t tVarW2 = workDatabase2.w();
        l0.n nVarV = workDatabase2.v();
        workDatabase2.b();
        try {
            List<l0.p> list2 = (List) l3.h.W(tVarW2.f1361a, true, false, new d0.h(9));
            boolean z4 = (list2 == null || list2.isEmpty()) ? false : true;
            if (z4) {
                for (l0.p pVar : list2) {
                    k0 k0Var = k0.f467d;
                    String str2 = pVar.f1331a;
                    tVarW2.h(k0Var, str2);
                    tVarW2.i(-512, str2);
                    tVarW2.e(str2, -1L);
                }
            }
            l3.h.W(nVarV.f1327a, false, true, new d0.h(7));
            workDatabase2.p();
            workDatabase2.l();
            boolean z5 = z4 || z3;
            Long lA = eVar2.f1404a.s().a("reschedule_needed");
            String str3 = f1396h;
            if (lA != null && lA.longValue() == 1) {
                a0.e().a(str3, "Rescheduling Workers.");
                yVar.U();
                eVar2.getClass();
                eVar2.f1404a.s().b(new l0.e("reschedule_needed", 0L));
                return;
            }
            try {
                int i5 = Build.VERSION.SDK_INT;
                int i6 = i5 >= 31 ? 570425344 : 536870912;
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(context, (Class<?>) ForceStopRunnable$BroadcastReceiver.class));
                intent.setAction("ACTION_FORCE_STOP_RESCHEDULE");
                PendingIntent broadcast = PendingIntent.getBroadcast(context, -1, intent, i6);
                if (i5 < 30) {
                    if (broadcast == null) {
                        c(context);
                        a0.e().a(str3, "Application was force-stopped, rescheduling.");
                        yVar.U();
                        bVar.f407d.getClass();
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        eVar.getClass();
                        eVar.f1404a.s().b(new l0.e("last_force_stop_ms", Long.valueOf(jCurrentTimeMillis)));
                        return;
                    }
                    if (z5) {
                        a0.e().a(str3, "Found unfinished work, scheduling it.");
                        e0.k.b(bVar, workDatabase2, yVar.f696e);
                    }
                }
                if (broadcast != null) {
                    broadcast.cancel();
                }
                List historicalProcessExitReasons = ((ActivityManager) context.getSystemService("activity")).getHistoricalProcessExitReasons(null, 0, 0);
                if (historicalProcessExitReasons != null && !historicalProcessExitReasons.isEmpty()) {
                    Long lA2 = eVar.f1404a.s().a("last_force_stop_ms");
                    long jLongValue = lA2 != null ? lA2.longValue() : 0L;
                    for (int i7 = 0; i7 < historicalProcessExitReasons.size(); i7++) {
                        ApplicationExitInfo applicationExitInfoC = h0.j.c(historicalProcessExitReasons.get(i7));
                        if (applicationExitInfoC.getReason() == 10 && applicationExitInfoC.getTimestamp() >= jLongValue) {
                            a0.e().a(str3, "Application was force-stopped, rescheduling.");
                            yVar.U();
                            bVar.f407d.getClass();
                            long jCurrentTimeMillis2 = System.currentTimeMillis();
                            eVar.getClass();
                            eVar.f1404a.s().b(new l0.e("last_force_stop_ms", Long.valueOf(jCurrentTimeMillis2)));
                            return;
                        }
                    }
                }
                if (z5) {
                    a0.e().a(str3, "Found unfinished work, scheduling it.");
                    e0.k.b(bVar, workDatabase2, yVar.f696e);
                }
            } catch (IllegalArgumentException e4) {
                e = e4;
                if (a0.e().f403a <= 5) {
                    Log.w(str3, "Ignoring exception", e);
                }
            } catch (SecurityException e5) {
                e = e5;
                if (a0.e().f403a <= 5) {
                    Log.w(str3, "Ignoring exception", e);
                }
            }
        } catch (Throwable th2) {
            workDatabase2.l();
            throw th2;
        }
    }

    public final boolean b() {
        d0.b bVar = this.f1399e.f693b;
        bVar.getClass();
        boolean zIsEmpty = TextUtils.isEmpty(null);
        String str = f1396h;
        if (zIsEmpty) {
            a0.e().a(str, "The default process name was not specified.");
            return true;
        }
        boolean zA = i.a(this.f1398d, bVar);
        a0.e().a(str, "Is default app process = " + zA);
        return zA;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context = this.f1398d;
        String str = f1396h;
        y yVar = this.f1399e;
        try {
            if (!b()) {
                yVar.T();
                return;
            }
            while (true) {
                try {
                    a.a.w(context);
                    a0.e().a(str, "Performing cleanup operations.");
                    try {
                        a();
                        yVar.T();
                        return;
                    } catch (SQLiteAccessPermException | SQLiteCantOpenDatabaseException | SQLiteConstraintException | SQLiteDatabaseCorruptException | SQLiteDatabaseLockedException | SQLiteDiskIOException | SQLiteFullException | SQLiteTableLockedException e4) {
                        int i4 = this.f1401g + 1;
                        this.f1401g = i4;
                        if (i4 >= 3) {
                            String str2 = Build.VERSION.SDK_INT >= 24 ? l.a.a(context) : true ? "The file system on the device is in a bad state. WorkManager cannot access the app's internal data store." : "WorkManager can't be accessed from direct boot, because credential encrypted storage isn't accessible.\nDon't access or initialise WorkManager from directAware components. See https://developer.android.com/training/articles/direct-boot";
                            a0.e().d(str, str2, e4);
                            IllegalStateException illegalStateException = new IllegalStateException(str2, e4);
                            yVar.f693b.getClass();
                            throw illegalStateException;
                        }
                        a0.e().b(str, "Retrying after " + (((long) i4) * 300), e4);
                        try {
                            Thread.sleep(((long) this.f1401g) * 300);
                        } catch (InterruptedException unused) {
                        }
                    }
                } catch (SQLiteException e5) {
                    a0.e().c(str, "Unexpected SQLite exception during migrations");
                    IllegalStateException illegalStateException2 = new IllegalStateException("Unexpected SQLite exception during migrations", e5);
                    yVar.f693b.getClass();
                    throw illegalStateException2;
                }
            }
        } catch (Throwable th) {
            yVar.T();
            throw th;
        }
    }
}
