package ddth2.hidden;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class N implements ThreadFactory {
    public final String a;
    public final AtomicInteger b = new AtomicInteger(1);

    public N(String str) {
        this.a = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.a + "-" + this.b.getAndIncrement());
        thread.setDaemon(true);
        return thread;
    }
}
