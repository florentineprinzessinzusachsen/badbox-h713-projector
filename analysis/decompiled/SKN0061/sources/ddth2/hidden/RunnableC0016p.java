package ddth2.hidden;

import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: renamed from: ddth2.hidden.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0016p implements Runnable {
    public final /* synthetic */ AtomicBoolean a;
    public final /* synthetic */ AtomicBoolean b;
    public final /* synthetic */ Socket c;

    public RunnableC0016p(AtomicBoolean atomicBoolean, AtomicBoolean atomicBoolean2, Socket socket) {
        this.a = atomicBoolean;
        this.b = atomicBoolean2;
        this.c = socket;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.a.compareAndSet(false, true)) {
            this.b.set(true);
            z.a(this.c);
        }
    }
}
