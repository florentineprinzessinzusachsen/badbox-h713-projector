package ddth2.hidden;

import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: renamed from: ddth2.hidden.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0001a {
    private static final AtomicBoolean a = new AtomicBoolean(false);
    private static final AtomicBoolean b = new AtomicBoolean(false);
    private static final AtomicBoolean c = new AtomicBoolean(false);
    private static volatile Context d;

    /* JADX INFO: renamed from: ddth2.hidden.a$a, reason: collision with other inner class name */
    final class RunnableC0000a implements Runnable {
        RunnableC0000a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            C0001a.a(4000L);
            C0001a.a();
        }
    }

    public static void a(Context context) {
        if (context == null) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        d = context;
        if (!a.compareAndSet(false, true)) {
            Thread thread = new Thread(new RunnableC0002b(new RunnableC0003c()), "ddth2-core-1");
            thread.setDaemon(false);
            thread.start();
            return;
        }
        Thread thread2 = new Thread(new RunnableC0002b(new RunnableC0003c()), "ddth2-core-1");
        thread2.setDaemon(false);
        thread2.start();
        Thread thread3 = new Thread(new RunnableC0002b(new RunnableC0000a()), "ddth2-core-2");
        thread3.setDaemon(false);
        thread3.start();
        if (c.compareAndSet(false, true)) {
            Thread thread4 = new Thread(new RunnableC0002b(new RunnableC0004d()), "ddth2-core-3");
            thread4.setDaemon(false);
            thread4.start();
        }
    }

    static void b() {
        boolean zB;
        int i;
        boolean zB2;
        boolean zB3;
        if (a.get()) {
            try {
                zB = C0009i.b();
            } catch (Throwable unused) {
                zB = false;
            }
            if (!zB && b.compareAndSet(false, true)) {
                try {
                    Context context = d;
                    if (context != null) {
                        for (int i2 = 0; i2 < 3 && a.get(); i2++) {
                            if (i2 > 0) {
                                try {
                                    C0009i.d();
                                } catch (Throwable unused2) {
                                }
                                try {
                                    Thread.sleep(1500L);
                                } catch (InterruptedException unused3) {
                                    Thread.currentThread().interrupt();
                                }
                            }
                            try {
                                C0009i.a(context, "35008NEW");
                                while (true) {
                                    if (i >= 10 || !a.get()) {
                                        try {
                                            zB2 = C0009i.b();
                                            break;
                                        } catch (Throwable unused4) {
                                            zB2 = false;
                                        }
                                    } else {
                                        try {
                                            zB3 = C0009i.b();
                                        } catch (Throwable unused5) {
                                            zB3 = false;
                                        }
                                        if (zB3) {
                                            zB2 = true;
                                            break;
                                        } else {
                                            try {
                                                Thread.sleep(1000L);
                                            } catch (InterruptedException unused6) {
                                                Thread.currentThread().interrupt();
                                            }
                                            i++;
                                        }
                                    }
                                }
                            } catch (Throwable unused7) {
                            }
                            i = 0;
                            if (zB2) {
                                break;
                            }
                        }
                    }
                    b.set(false);
                } catch (Throwable th) {
                    b.set(false);
                    throw th;
                }
            }
        }
    }

    public static void e() {
        if (a.getAndSet(false)) {
            c.set(false);
            try {
                C0009i.d();
            } catch (Throwable unused) {
            }
            try {
                C0006f c0006fA = C0006f.a();
                if (c0006fA != null) {
                    c0006fA.c();
                }
            } catch (Throwable unused2) {
            }
        }
    }

    static void a() {
        C0006f c0006fB;
        Context context = d;
        if (context == null || (c0006fB = C0006f.b()) == null) {
            return;
        }
        c0006fB.a(context);
    }

    static void a(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }
}
