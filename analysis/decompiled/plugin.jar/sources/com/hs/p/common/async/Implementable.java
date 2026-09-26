package com.hs.p.common.async;

import com.hs.p.common.utils.LOG;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public abstract class Implementable implements Runnable {
    private static final String TAG = "Implementable";
    private final String mName;

    public Implementable(String str) {
        this(str, false);
    }

    private String setThreadName(String str) {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(str);
        return name;
    }

    public final void execute() {
        new Thread(this).start();
    }

    public String getName() {
        return this.mName;
    }

    protected abstract void implement();

    @Override // java.lang.Runnable
    public final void run() {
        try {
            implement();
        } catch (Throwable th) {
            LOG.e(TAG, "[" + this.mName + "] implement failed: " + th, th);
        }
    }

    public final ScheduledFuture<?> schedule(ScheduledExecutorService scheduledExecutorService, long j) {
        return scheduledExecutorService.schedule(this, j, TimeUnit.SECONDS);
    }

    public String toString() {
        return "Implementable(" + this.mName + ")";
    }

    public Implementable(String str, boolean z) {
        if (z) {
            str = str + "@" + Thread.currentThread().getName();
        }
        this.mName = str;
    }

    public final void execute(ExecutorService executorService) {
        executorService.execute(this);
    }
}
