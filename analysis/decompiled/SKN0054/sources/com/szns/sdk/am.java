package com.szns.sdk;

/* JADX INFO: loaded from: classes.dex */
final class am extends Thread {
    am() {
        super("Okio Watchdog");
        setDaemon(true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0015, code lost:
    
        r1.b();
     */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            r3 = this;
        L0:
            java.lang.Class<com.szns.sdk.aj> r0 = com.szns.sdk.aj.class
            monitor-enter(r0)     // Catch: java.lang.InterruptedException -> L0
            com.szns.sdk.aj r1 = com.szns.sdk.aj.c()     // Catch: java.lang.Throwable -> L19
            if (r1 != 0) goto Lb
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
            goto L0
        Lb:
            com.szns.sdk.aj r2 = com.szns.sdk.aj.a     // Catch: java.lang.Throwable -> L19
            if (r1 != r2) goto L14
            r1 = 0
            com.szns.sdk.aj.a = r1     // Catch: java.lang.Throwable -> L19
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
            return
        L14:
            monitor-exit(r0)     // Catch: java.lang.InterruptedException -> L0
            r1.b()     // Catch: java.lang.InterruptedException -> L0
            goto L0
        L19:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.InterruptedException -> L0
            throw r1     // Catch: java.lang.InterruptedException -> L0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szns.sdk.am.run():void");
    }
}
