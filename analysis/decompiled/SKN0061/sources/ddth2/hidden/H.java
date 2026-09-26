package ddth2.hidden;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class H {
    public final C0018s a;
    public final ScheduledExecutorService b;
    public final C0011k c;
    public final HashMap d = new HashMap();

    public H(C0018s c0018s, ScheduledExecutorService scheduledExecutorService, C0011k c0011k) {
        this.a = c0018s;
        this.b = scheduledExecutorService;
        this.c = c0011k;
    }

    public final synchronized void a(ArrayList arrayList) {
        F f;
        HashSet hashSet = new HashSet();
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            I i2 = (I) obj;
            String str = i2.a + ":" + i2.b;
            hashSet.add(str);
            if (!this.d.containsKey(str)) {
                F f2 = new F(this.a, this.b, i2, this.c);
                this.d.put(str, f2);
                if (f2.e.compareAndSet(false, true)) {
                    AtomicInteger atomicInteger = new AtomicInteger(1);
                    Thread thread = new Thread(new D(f2), "gateway-client-" + atomicInteger.getAndIncrement());
                    thread.setDaemon(true);
                    f2.j = thread;
                    thread.start();
                }
            }
        }
        for (String str2 : new HashSet(this.d.keySet())) {
            if (!hashSet.contains(str2) && (f = (F) this.d.remove(str2)) != null) {
                f.e.set(false);
                z.a(f.h);
                f.g.shutdownNow();
                Thread thread2 = f.j;
                if (thread2 != null) {
                    thread2.interrupt();
                }
            }
        }
    }
}
