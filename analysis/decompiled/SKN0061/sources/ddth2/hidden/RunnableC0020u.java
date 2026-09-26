package ddth2.hidden;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: ddth2.hidden.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0020u implements Runnable {
    public final /* synthetic */ C0022w a;

    public RunnableC0020u(C0022w c0022w) {
        this.a = c0022w;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C0022w c0022w = this.a;
        c0022w.getClass();
        long jNanoTime = System.nanoTime();
        Iterator it = c0022w.c.entrySet().iterator();
        while (it.hasNext()) {
            if (((Long) ((Map.Entry) it.next()).getValue()).longValue() - jNanoTime <= 0) {
                it.remove();
            }
        }
    }
}
