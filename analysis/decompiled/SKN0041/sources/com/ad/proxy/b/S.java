package com.ad.proxy.b;

import com.ad.proxy.Robin;
import com.ad.proxy.Status;
import java.io.IOException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class S implements Runnable {
    public final /* synthetic */ a0 a;

    public S(a0 a0Var) {
        this.a = a0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        a0 a0Var = this.a;
        do {
            try {
                Robin.notifyStatusChanged(Status.CONNECTING);
                a0Var.a();
                Robin.notifyStatusChanged(Status.CONNECTED);
                a0Var.b();
            } catch (IOException e) {
                Robin.notifyStatusChanged(Status.DISCONNECTED);
                com.ad.proxy.g.F.a("SocketClient", e);
                ScheduledFuture scheduledFuture = a0Var.k;
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(true);
                }
                com.ad.proxy.f.C.a(a0Var.m, 0, Robin.channel);
                com.ad.proxy.g.F.a("SocketClient", "Sleeping for 5 seconds");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e2) {
                    com.ad.proxy.g.F.a("SocketClient", e2);
                }
                if (a0Var.h) {
                    Robin.notifyStatusChanged(Status.RECONNECTING);
                }
            }
        } while (a0Var.h);
    }
}
