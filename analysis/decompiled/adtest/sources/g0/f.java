package g0;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.PersistableBundle;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.background.systemjob.SystemJobService;
import d0.a0;
import d0.k0;
import d0.l0;
import j2.i;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import l0.j;
import l0.k;
import l0.p;
import l0.t;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f implements e0.h {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f951f = a0.g("SystemJobScheduler");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f952a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final JobScheduler f953b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final e f954c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WorkDatabase f955d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d0.b f956e;

    public f(Context context, WorkDatabase workDatabase, d0.b bVar) {
        JobScheduler jobSchedulerB = b.b(context);
        e eVar = new e(context, bVar.f407d, bVar.f415l);
        this.f952a = context;
        this.f953b = jobSchedulerB;
        this.f954c = eVar;
        this.f955d = workDatabase;
        this.f956e = bVar;
    }

    public static void b(JobScheduler jobScheduler, int i4) {
        try {
            jobScheduler.cancel(i4);
        } catch (Throwable th) {
            a0.e().d(f951f, String.format(Locale.getDefault(), "Exception while trying to cancel job (%d)", Integer.valueOf(i4)), th);
        }
    }

    public static ArrayList d(Context context, JobScheduler jobScheduler, String str) {
        ArrayList arrayListF = f(context, jobScheduler);
        if (arrayListF == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(2);
        int size = arrayListF.size();
        int i4 = 0;
        while (i4 < size) {
            Object obj = arrayListF.get(i4);
            i4++;
            JobInfo jobInfo = (JobInfo) obj;
            k kVarG = g(jobInfo);
            if (kVarG != null && str.equals(kVarG.f1321a)) {
                arrayList.add(Integer.valueOf(jobInfo.getId()));
            }
        }
        return arrayList;
    }

    public static ArrayList f(Context context, JobScheduler jobScheduler) {
        List<JobInfo> listA = b.a(jobScheduler);
        if (listA == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(listA.size());
        ComponentName componentName = new ComponentName(context, (Class<?>) SystemJobService.class);
        for (JobInfo jobInfo : listA) {
            if (componentName.equals(jobInfo.getService())) {
                arrayList.add(jobInfo);
            }
        }
        return arrayList;
    }

    public static k g(JobInfo jobInfo) {
        PersistableBundle extras = jobInfo.getExtras();
        if (extras == null) {
            return null;
        }
        try {
            if (extras.containsKey("EXTRA_WORK_SPEC_ID")) {
                return new k(extras.getInt("EXTRA_WORK_SPEC_GENERATION", 0), extras.getString("EXTRA_WORK_SPEC_ID"));
            }
            return null;
        } catch (NullPointerException unused) {
            return null;
        }
    }

    @Override // e0.h
    public final void a(String str) {
        Context context = this.f952a;
        JobScheduler jobScheduler = this.f953b;
        ArrayList arrayListD = d(context, jobScheduler, str);
        if (arrayListD == null || arrayListD.isEmpty()) {
            return;
        }
        int size = arrayListD.size();
        int i4 = 0;
        while (i4 < size) {
            Object obj = arrayListD.get(i4);
            i4++;
            b(jobScheduler, ((Integer) obj).intValue());
        }
        j jVarT = this.f955d.t();
        jVarT.getClass();
        i.e(str, "workSpecId");
        l3.h.W(jVarT.f1319a, false, true, new l0.b(4, str));
    }

    @Override // e0.h
    public final void c(p... pVarArr) {
        int iIntValue;
        ArrayList arrayListD;
        int iIntValue2;
        p[] pVarArr2 = pVarArr;
        WorkDatabase workDatabase = this.f955d;
        final m0.e eVar = new m0.e(workDatabase, 0);
        int length = pVarArr2.length;
        int i4 = 0;
        while (i4 < length) {
            p pVar = pVarArr2[i4];
            workDatabase.b();
            try {
                t tVarW = workDatabase.w();
                String str = pVar.f1331a;
                p pVarC = tVarW.c(str);
                String str2 = f951f;
                if (pVarC == null) {
                    a0.e().h(str2, "Skipping scheduling " + str + " because it's no longer in the DB");
                    workDatabase.p();
                } else {
                    if (pVarC.f1332b != k0.f467d) {
                        a0.e().h(str2, "Skipping scheduling " + str + " because it is no longer enqueued");
                        workDatabase.p();
                    } else {
                        k kVarS = l0.s(pVar);
                        l0.h hVarA = workDatabase.t().a(kVarS);
                        d0.b bVar = this.f956e;
                        WorkDatabase workDatabase2 = eVar.f1404a;
                        if (hVarA != null) {
                            iIntValue = hVarA.f1315c;
                        } else {
                            bVar.getClass();
                            final int i5 = bVar.f412i;
                            Object objN = workDatabase2.n(new Callable() { // from class: m0.d
                                @Override // java.util.concurrent.Callable
                                public final Object call() {
                                    WorkDatabase workDatabase3 = eVar.f1404a;
                                    Long lA = workDatabase3.s().a("next_job_scheduler_id");
                                    int i6 = 0;
                                    int iLongValue = lA != null ? (int) lA.longValue() : 0;
                                    workDatabase3.s().b(new l0.e("next_job_scheduler_id", Long.valueOf(iLongValue == Integer.MAX_VALUE ? 0 : iLongValue + 1)));
                                    if (iLongValue < 0 || iLongValue > i5) {
                                        workDatabase3.s().b(new l0.e("next_job_scheduler_id", Long.valueOf(1)));
                                    } else {
                                        i6 = iLongValue;
                                    }
                                    return Integer.valueOf(i6);
                                }
                            });
                            i.d(objN, "runInTransaction(...)");
                            iIntValue = ((Number) objN).intValue();
                        }
                        if (hVarA == null) {
                            l0.h hVar = new l0.h(kVarS.f1321a, kVarS.f1322b, iIntValue);
                            j jVarT = workDatabase.t();
                            jVarT.getClass();
                            l3.h.W(jVarT.f1319a, false, true, new h0.e(3, jVarT, hVar));
                        }
                        h(pVar, iIntValue);
                        if (Build.VERSION.SDK_INT == 23 && (arrayListD = d(this.f952a, this.f953b, str)) != null) {
                            int iIndexOf = arrayListD.indexOf(Integer.valueOf(iIntValue));
                            if (iIndexOf >= 0) {
                                arrayListD.remove(iIndexOf);
                            }
                            if (arrayListD.isEmpty()) {
                                bVar.getClass();
                                final int i6 = bVar.f412i;
                                Object objN2 = workDatabase2.n(new Callable() { // from class: m0.d
                                    @Override // java.util.concurrent.Callable
                                    public final Object call() {
                                        WorkDatabase workDatabase3 = eVar.f1404a;
                                        Long lA = workDatabase3.s().a("next_job_scheduler_id");
                                        int i7 = 0;
                                        int iLongValue = lA != null ? (int) lA.longValue() : 0;
                                        workDatabase3.s().b(new l0.e("next_job_scheduler_id", Long.valueOf(iLongValue == Integer.MAX_VALUE ? 0 : iLongValue + 1)));
                                        if (iLongValue < 0 || iLongValue > i6) {
                                            workDatabase3.s().b(new l0.e("next_job_scheduler_id", Long.valueOf(1)));
                                        } else {
                                            i7 = iLongValue;
                                        }
                                        return Integer.valueOf(i7);
                                    }
                                });
                                i.d(objN2, "runInTransaction(...)");
                                iIntValue2 = ((Number) objN2).intValue();
                            } else {
                                iIntValue2 = ((Integer) arrayListD.get(0)).intValue();
                            }
                            h(pVar, iIntValue2);
                        }
                        workDatabase.p();
                        workDatabase.l();
                    }
                    i4++;
                    pVarArr2 = pVarArr;
                }
                workDatabase.l();
                i4++;
                pVarArr2 = pVarArr;
            } catch (Throwable th) {
                workDatabase.l();
                throw th;
            }
        }
    }

    @Override // e0.h
    public final boolean e() {
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0089, code lost:
    
        if (r9 < 26) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x008c, code lost:
    
        if (r9 >= 24) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void h(l0.p r17, int r18) {
        /*
            Method dump skipped, instruction units count: 773
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: g0.f.h(l0.p, int):void");
    }
}
