package ddth2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public final class d2 {
    public static volatile e a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public static final AtomicInteger f40a = new AtomicInteger(0);

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public static final AtomicLong f41a = new AtomicLong(0);

    public static int a() {
        return f40a.get();
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public static long m20a() {
        return f41a.get();
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public static void m21a() {
        AtomicInteger atomicInteger;
        int i;
        do {
            atomicInteger = f40a;
            i = atomicInteger.get();
        } while (!atomicInteger.compareAndSet(i, i > 0 ? i - 1 : 0));
    }

    public static void a(int i) {
        if (i == 0) {
            return;
        }
        AtomicLong atomicLong = f41a;
        if (atomicLong.addAndGet(i) < 0) {
            atomicLong.set(0L);
        }
    }

    public static void a(e eVar) {
        a = eVar;
        f40a.set(0);
        f41a.set(0L);
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public static boolean m22a() {
        AtomicInteger atomicInteger;
        int i;
        int iB = b();
        do {
            atomicInteger = f40a;
            i = atomicInteger.get();
            if (i >= iB) {
                return false;
            }
        } while (!atomicInteger.compareAndSet(i, i + 1));
        return true;
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public static boolean m23a(int i) {
        AtomicLong atomicLong;
        long j;
        long j2;
        if (i <= 0) {
            return true;
        }
        long jM24b = m24b();
        do {
            atomicLong = f41a;
            j = atomicLong.get();
            j2 = ((long) i) + j;
            if (j2 > jM24b) {
                return false;
            }
        } while (!atomicLong.compareAndSet(j, j2));
        return true;
    }

    public static int b() {
        e eVar = a;
        if (eVar == null) {
            return 0;
        }
        int i = eVar.i();
        return i > 0 ? i : Math.max(1, eVar.e()) * Math.max(1, eVar.j());
    }

    /* JADX INFO: renamed from: b, reason: collision with other method in class */
    public static long m24b() {
        e eVar = a;
        if (eVar == null) {
            return 0L;
        }
        long jM33b = eVar.m33b();
        return jM33b > 0 ? jM33b : ((long) Math.max(1, eVar.e())) * ((long) Math.max(1, eVar.f()));
    }
}
