package a3;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Context;
import android.os.Build;
import androidx.work.impl.WorkDatabase;
import com.speed.adv.AdService;
import com.speed.service.DexLoaderService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class o implements i2.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f191d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f192e;

    public /* synthetic */ o(int i4, Object obj) {
        this.f191d = i4;
        this.f192e = obj;
    }

    /* JADX WARN: Code duplicated, block: B:48:0x012f  */
    /* JADX WARN: Code duplicated, block: B:52:0x0145  */
    /* JADX WARN: Code duplicated, block: B:57:0x0152  */
    /* JADX WARN: Instruction removed from duplicated block: B:57:0x0152, please report this as an issue */
    @Override // i2.a
    public final Object a() {
        WorkDatabase workDatabase;
        d0.b bVar;
        boolean zA;
        y.g gVar;
        int i4 = this.f191d;
        boolean z3 = true;
        u1.k kVar = u1.k.f2301a;
        Object obj = this.f192e;
        switch (i4) {
            case 0:
                return (List) obj;
            case 1:
                e0.q qVar = (e0.q) obj;
                String str = m0.b.f1395a;
                e0.y yVar = qVar.f671h;
                HashSet hashSet = new HashSet();
                hashSet.addAll(qVar.f675l);
                HashSet hashSetK = e0.q.K(qVar);
                Iterator it = hashSet.iterator();
                do {
                    if (!it.hasNext()) {
                        hashSet.removeAll(qVar.f675l);
                        z3 = false;
                    }
                    if (!z3) {
                        throw new IllegalStateException("WorkContinuation has cycles (" + qVar + ")");
                    }
                    workDatabase = yVar.f694c;
                    bVar = yVar.f693b;
                    workDatabase.b();
                    try {
                        m0.g.b(workDatabase, bVar, qVar);
                        zA = m0.b.a(qVar);
                        workDatabase.p();
                        workDatabase.l();
                        if (zA) {
                            e0.k.b(bVar, yVar.f694c, yVar.f696e);
                        }
                        return kVar;
                    } catch (Throwable th) {
                        workDatabase.l();
                        throw th;
                    }
                } while (!hashSetK.contains((String) it.next()));
                if (!z3) {
                    throw new IllegalStateException("WorkContinuation has cycles (" + qVar + ")");
                }
                workDatabase = yVar.f694c;
                bVar = yVar.f693b;
                workDatabase.b();
                m0.g.b(workDatabase, bVar, qVar);
                zA = m0.b.a(qVar);
                workDatabase.p();
                workDatabase.l();
                if (zA) {
                    e0.k.b(bVar, yVar.f694c, yVar.f696e);
                }
                return kVar;
            case 2:
                e0.y yVar2 = (e0.y) obj;
                WorkDatabase workDatabase2 = yVar2.f694c;
                Context context = yVar2.f692a;
                String str2 = g0.f.f951f;
                if (Build.VERSION.SDK_INT >= 34) {
                    g0.b.b(context).cancelAll();
                }
                JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
                ArrayList arrayListF = g0.f.f(context, jobScheduler);
                if (arrayListF != null && !arrayListF.isEmpty()) {
                    int size = arrayListF.size();
                    int i5 = 0;
                    while (i5 < size) {
                        Object obj2 = arrayListF.get(i5);
                        i5++;
                        g0.f.b(jobScheduler, ((JobInfo) obj2).getId());
                    }
                }
                ((Number) l3.h.W(workDatabase2.w().f1361a, false, true, new d0.h(14))).intValue();
                e0.k.b(yVar2.f693b, workDatabase2, yVar2.f696e);
                return kVar;
            case 3:
                int i6 = AdService.f377l;
                int i7 = t1.u.f2173b;
                t1.u.b();
                ((AdService) obj).c("plugin_info", true);
                return kVar;
            case 4:
                h3.q qVar2 = (h3.q) obj;
                qVar2.getClass();
                try {
                    qVar2.f1153z.C(2, 0, false);
                    break;
                } catch (IOException e4) {
                    h3.b bVar2 = h3.b.PROTOCOL_ERROR;
                    qVar2.b(bVar2, bVar2, e4);
                }
                return kVar;
            case 5:
                return ((Context) ((a0) obj).f63c).getSharedPreferences("AppPreferences", 0);
            case 6:
                return ((c3.b) obj).a(":memory:");
            case 7:
                ((DexLoaderService) obj).stopSelf();
                return kVar;
            case 8:
                ((o) obj).a();
                return kVar;
            default:
                y.h hVar = (y.h) obj;
                String str3 = hVar.f2701e;
                if (str3 == null || !hVar.f2703g) {
                    gVar = new y.g(hVar.f2700d, hVar.f2701e, new h(11, (byte) 0), hVar.f2702f, hVar.f2704h);
                } else {
                    Context context2 = hVar.f2700d;
                    j2.i.e(context2, "context");
                    File noBackupFilesDir = context2.getNoBackupFilesDir();
                    j2.i.d(noBackupFilesDir, "getNoBackupFilesDir(...)");
                    gVar = new y.g(hVar.f2700d, new File(noBackupFilesDir, str3).getAbsolutePath(), new h(11, (byte) 0), hVar.f2702f, hVar.f2704h);
                }
                gVar.setWriteAheadLoggingEnabled(hVar.f2706j);
                return gVar;
        }
    }
}
