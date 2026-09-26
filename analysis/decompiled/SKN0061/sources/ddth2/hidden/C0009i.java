package ddth2.hidden;

import java.io.File;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: ddth2.hidden.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0009i {
    private static C0017q a;

    public static synchronized void a(Object obj) {
        a(obj, "navix260625");
    }

    public static synchronized boolean b() {
        C0017q c0017q;
        c0017q = a;
        return c0017q != null && c0017q.h.get();
    }

    public static synchronized void c() {
        a((Object) null);
    }

    public static synchronized void d() {
        C0017q c0017q = a;
        if (c0017q == null) {
            return;
        }
        c0017q.a();
        a = null;
    }

    public static synchronized void a(String str) {
        a(null, str);
    }

    public static synchronized void a(Object obj, String str) {
        File file;
        Object obj2 = obj;
        synchronized (C0009i.class) {
            C0017q c0017q = a;
            if (c0017q == null || !c0017q.h.get()) {
                if (str != null) {
                    String strTrim = str.trim();
                    int length = strTrim.length();
                    if (length >= 6 && length <= 32) {
                        for (int i = 0; i < length; i++) {
                            char cCharAt = strTrim.charAt(i);
                            if ((cCharAt < 'A' || cCharAt > 'Z') && ((cCharAt < 'a' || cCharAt > 'z') && ((cCharAt < '0' || cCharAt > '9') && cCharAt != '.' && cCharAt != '_' && cCharAt != '-'))) {
                                throw new IllegalArgumentException("provider may contain only letters, numbers, '.', '_', and '-'");
                            }
                        }
                        String absolutePath = null;
                        if (obj2 != null) {
                            Object objInvoke = obj.getClass().getMethod("getApplicationContext", null).invoke(obj2, null);
                            if (objInvoke != null) {
                                obj2 = objInvoke;
                            }
                            try {
                                Object objInvoke2 = obj2.getClass().getMethod("getFilesDir", null).invoke(obj2, null);
                                file = objInvoke2 instanceof File ? (File) objInvoke2 : null;
                            } catch (Throwable unused) {
                            }
                            if (file != null) {
                                absolutePath = new File(file, ".stub_callback_id").getAbsolutePath();
                            }
                        }
                        C0017q c0017q2 = new C0017q(absolutePath, strTrim);
                        a = c0017q2;
                        if (c0017q2.h.compareAndSet(false, true)) {
                            C0022w c0022w = c0017q2.c;
                            ScheduledExecutorService scheduledExecutorService = c0017q2.a;
                            c0022w.getClass();
                            RunnableC0020u runnableC0020u = new RunnableC0020u(c0022w);
                            long j = c0022w.a;
                            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                            scheduledExecutorService.scheduleAtFixedRate(runnableC0020u, j, j, timeUnit);
                            C0011k c0011k = c0017q2.e;
                            c0011k.a.scheduleAtFixedRate(new W(c0011k), 5000L, 5000L, timeUnit);
                            C0014n c0014n = c0017q2.g;
                            if (c0014n.d.compareAndSet(false, true)) {
                                AtomicInteger atomicInteger = new AtomicInteger(1);
                                Thread thread = new Thread(new RunnableC0012l(c0014n), "control-client-" + atomicInteger.getAndIncrement());
                                thread.setDaemon(true);
                                c0014n.h = thread;
                                C0014n.a(AbstractC0015o.a);
                                String str2 = c0014n.a.a;
                                c0014n.h.start();
                            }
                        }
                    } else {
                        throw new IllegalArgumentException("provider length must be 6 to 32");
                    }
                } else {
                    throw new IllegalArgumentException("provider is required");
                }
            }
        }
    }

    public static synchronized boolean a() {
        C0017q c0017q;
        c0017q = a;
        return c0017q != null && c0017q.h.get() && c0017q.g.e.get();
    }
}
