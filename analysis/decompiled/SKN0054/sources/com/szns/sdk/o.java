package com.szns.sdk;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
final class o implements ThreadFactory {
    private final String a;
    private final AtomicInteger b;

    private o(String str) {
        this.b = new AtomicInteger(1);
        this.a = str;
    }

    /* synthetic */ o(String str, byte b) {
        this(str);
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, ac.a(this.a + "-" + this.b.getAndIncrement()));
    }
}
