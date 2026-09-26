package a.a.a.a;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: DefaultTaskExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f3a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ExecutorService f4b = Executors.newFixedThreadPool(2, new a(this));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile Handler f5c;

    /* JADX INFO: compiled from: DefaultTaskExecutor.java */
    class a implements ThreadFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AtomicInteger f6a = new AtomicInteger(0);

        a(b bVar) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName(String.format("arch_disk_io_%d", Integer.valueOf(this.f6a.getAndIncrement())));
            return thread;
        }
    }

    @Override // a.a.a.a.c
    public void a(Runnable runnable) {
        this.f4b.execute(runnable);
    }

    @Override // a.a.a.a.c
    public void b(Runnable runnable) {
        if (this.f5c == null) {
            synchronized (this.f3a) {
                if (this.f5c == null) {
                    this.f5c = new Handler(Looper.getMainLooper());
                }
            }
        }
        this.f5c.post(runnable);
    }

    @Override // a.a.a.a.c
    public boolean a() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
