package ddth2.hidden;

import java.net.DatagramSocket;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: renamed from: ddth2.hidden.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0017q {
    public final ScheduledExecutorService a;
    public final ScheduledExecutorService b;
    public final C0022w c;
    public final G d;
    public final C0011k e;
    public final H f;
    public final C0014n g;
    public final AtomicBoolean h = new AtomicBoolean(false);

    public C0017q(String str, String str2) {
        C0018s c0018s = new C0018s(B.a(str), str2);
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(3, new N("stub-callback-scheduler"));
        this.a = scheduledExecutorServiceNewScheduledThreadPool;
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool2 = Executors.newScheduledThreadPool(1, new N("stub-callback-watchdog"));
        this.b = scheduledExecutorServiceNewScheduledThreadPool2;
        C0022w c0022w = new C0022w();
        this.c = c0022w;
        G g = new G(scheduledExecutorServiceNewScheduledThreadPool);
        this.d = g;
        C0011k c0011k = new C0011k(scheduledExecutorServiceNewScheduledThreadPool, scheduledExecutorServiceNewScheduledThreadPool2, g, c0022w);
        this.e = c0011k;
        H h = new H(c0018s, scheduledExecutorServiceNewScheduledThreadPool2, c0011k);
        this.f = h;
        this.g = new C0014n(c0018s, scheduledExecutorServiceNewScheduledThreadPool2, h);
    }

    public final void a() {
        if (this.h.compareAndSet(true, false)) {
            C0014n c0014n = this.g;
            c0014n.d.set(false);
            c0014n.e.set(false);
            z.a(c0014n.f);
            Thread thread = c0014n.h;
            if (thread != null) {
                thread.interrupt();
            }
            H h = this.f;
            synchronized (h) {
                for (F f : h.d.values()) {
                    f.e.set(false);
                    z.a(f.h);
                    f.g.shutdownNow();
                    Thread thread2 = f.j;
                    if (thread2 != null) {
                        thread2.interrupt();
                    }
                }
                h.d.clear();
            }
            C0011k c0011k = this.e;
            Iterator it = c0011k.e.values().iterator();
            while (it.hasNext()) {
                ((V) it.next()).a(7);
            }
            c0011k.e.clear();
            DatagramSocket datagramSocket = this.d.a;
            if (datagramSocket != null) {
                datagramSocket.close();
            }
            this.a.shutdownNow();
            this.b.shutdownNow();
        }
    }
}
