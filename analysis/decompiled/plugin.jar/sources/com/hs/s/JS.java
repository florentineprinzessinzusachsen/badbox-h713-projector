package com.hs.s;

import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.Process;
import com.hs.common.utils.LOG;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
@SuppressLint({"NewApi"})
public final class JS extends JobService {
    private static final int JOB_ID = 65281;
    private static final long PERIOD_MILLIS = 7200000;
    private static final String TAG = "JS";
    private static AtomicLong mCounter = new AtomicLong();
    private static boolean mScheduled = false;

    public static synchronized boolean schedule(Context context) {
        if (!mScheduled) {
            try {
                ComponentName componentName = new ComponentName(context, (Class<?>) JS.class);
                JobScheduler jobSchedulerA = c.a(context.getSystemService("jobscheduler"));
                if (jobSchedulerA != null) {
                    JobInfo.Builder persisted = new JobInfo.Builder(JOB_ID, componentName).setRequiredNetworkType(1).setPeriodic(PERIOD_MILLIS).setRequiresCharging(false).setPersisted(true);
                    jobSchedulerA.cancel(JOB_ID);
                    jobSchedulerA.schedule(persisted.build());
                    mScheduled = true;
                    LOG.i(TAG, strEnv() + "[job:" + JOB_ID + "] schedule job ...");
                }
            } catch (Throwable th) {
                LOG.e(TAG, strEnv() + "[job:" + JOB_ID + "] schedule job failed: " + th);
            }
        }
        return mScheduled;
    }

    private static String strEnv() {
        return "[P:" + Process.myPid() + " T:" + Process.myTid() + "]";
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        MS.start(this, null, "JS/" + (jobParameters != null ? jobParameters.getJobId() : -1) + "-" + mCounter.getAndIncrement());
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
