package com.ad.proxy.b;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

/* JADX INFO: loaded from: classes.dex */
public final class C {
    public static long c = 60;
    public final ScheduledExecutorService a = Executors.newScheduledThreadPool(1);
    public ScheduledFuture b;
}
