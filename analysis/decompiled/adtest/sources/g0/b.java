package g0;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Context;
import android.os.Build;
import d0.a0;
import j2.i;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f946a;

    static {
        String strG = a0.g("SystemJobScheduler");
        i.d(strG, "tagWithPrefix(...)");
        f946a = strG;
    }

    public static final List a(JobScheduler jobScheduler) {
        i.e(jobScheduler, "<this>");
        try {
            List<JobInfo> allPendingJobs = jobScheduler.getAllPendingJobs();
            i.d(allPendingJobs, "getAllPendingJobs(...)");
            return allPendingJobs;
        } catch (Throwable th) {
            a0.e().d(f946a, "getAllPendingJobs() is not reliable on this device.", th);
            return null;
        }
    }

    public static final JobScheduler b(Context context) {
        i.e(context, "<this>");
        Object systemService = context.getSystemService("jobscheduler");
        i.c(systemService, "null cannot be cast to non-null type android.app.job.JobScheduler");
        JobScheduler jobScheduler = (JobScheduler) systemService;
        if (Build.VERSION.SDK_INT < 34) {
            return jobScheduler;
        }
        JobScheduler jobSchedulerForNamespace = jobScheduler.forNamespace("androidx.work.systemjobscheduler");
        i.d(jobSchedulerForNamespace, "forNamespace(...)");
        return jobSchedulerForNamespace;
    }
}
