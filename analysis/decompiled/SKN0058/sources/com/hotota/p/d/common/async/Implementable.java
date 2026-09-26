package com.hotota.p.d.common.async;

import android.content.BroadcastReceiver;
import com.hotota.p.d.common.utils.LOG;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract class Implementable implements Runnable {
    private static final String TAG = "Implementable";
    private final String mName;
    private BroadcastReceiver networkReceiver;

    protected abstract void implement();

    public Implementable(String str) {
        this(str, false);
    }

    public Implementable(String str, boolean z) {
        if (z) {
            str = str + "@" + Thread.currentThread().getName();
        }
        this.mName = str;
    }

    public String getName() {
        return this.mName;
    }

    public final void execute() {
        new Thread(this).start();
    }

    public final void execute(ExecutorService executorService) {
        executorService.execute(this);
    }

    public final ScheduledFuture<?> schedule(ScheduledExecutorService scheduledExecutorService, long j) {
        return scheduledExecutorService.schedule(this, j, TimeUnit.SECONDS);
    }

    @Override // java.lang.Runnable
    public final void run() {
        String threadName = setThreadName(this.mName);
        try {
            implement();
        } catch (Throwable th) {
            try {
                LOG.e(TAG, "[" + this.mName + "] implement failed: " + th, th);
            } finally {
                setThreadName(threadName);
            }
        }
    }

    public String toString() {
        return "Implementable(" + this.mName + ")";
    }

    private String setThreadName(String str) {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(str);
        return name;
    }
}
